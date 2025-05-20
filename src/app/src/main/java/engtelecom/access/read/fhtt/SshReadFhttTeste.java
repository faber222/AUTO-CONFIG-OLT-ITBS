package engtelecom.access.read.fhtt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

// FALTA IMPLEMENTAR
// FALTA IMPLEMENTAR
// FALTA IMPLEMENTAR
// FALTA IMPLEMENTAR
// FALTA IMPLEMENTAR
public class SshReadFhttTeste implements Runnable {

    private Session session;
    private ChannelExec channelExec;
    private PrintWriter out;
    private Thread thread;
    private boolean active;
    private boolean isFinal;

    // Atributos para oltAccess
    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private String fileName;

    public SshReadFhttTeste(String host, int port, String username, String password, String fileName) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.fileName = fileName;
    }

    public boolean oltAccess() {
        try {
            // Configura a sessão SSH
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setPassword(password);

            // Desativa a verificação de chave de host
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            // Estabelece a conexão SSH
            System.out.println("Establishing Connection...");
            session.connect(5000); // Tempo limite de 5 segundos
            System.out.println("Connection established.");

            // Cria o canal para execução de comandos
            channelExec = (ChannelExec) session.openChannel("exec");

            // Configuração de leitura de dados
            OutputStream inputStream = channelExec.getOutputStream();
            out = new PrintWriter(inputStream, true);

            // Início da Leitura em tempo real
            active = true;
            isFinal = false;
            thread = new Thread(this);
            thread.start();

            // Login e envio de comandos
            applyCommands();

            // // Espera até que o processamento dos comandos esteja completo
            thread.join(); // Aguarda o término da thread de leitura (run)

            // Finaliza a conexão
            finalMessage();
            return true;
        } catch (JSchException e) {
            System.err.println("Erro na conexão SSH.");
            JOptionPane.showMessageDialog(null, "Erro ao tentar conectar via SSH.", "Aviso!",
                    JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Implementação do método run() da interface Runnable.
     * Este método é executado em uma thread separada para permitir a leitura
     * em tempo real das respostas do dispositivo de rede.
     */
    public void run() {
        final String prompt = "Admin(DEBUG_H)>";
        final String prompt2 = "!end  of config -----------------------------------------------";
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(this.fileName, false))) {
            BufferedReader in = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));
            String answer;
            while (active && !Thread.currentThread().isInterrupted()) {
                if ((answer = in.readLine()) != null) {
                    fileWriter.write(answer);
                    fileWriter.newLine();
                    // System.out.println(answer); // Mostra o retorno no console
                    // Verifica se o prompt final foi recebido
                    if ((answer.trim().endsWith(prompt) || answer.trim().endsWith(prompt2)) && isFinal) {
                        active = false; // Termina a leitura
                        break;
                    }
                }
            }
        } catch (Exception e) {
            if (active) {
                System.err.println("Erro de comunicação.");
            }
        }
    }

    private void sendCommand(String command, int delay) throws InterruptedException {
        out.println(command);
        Thread.sleep(delay);
    }

    /**
     * Envia os comandos para o servidor SSH
     * 
     * @throws JSchException
     */
    private void applyCommands() throws InterruptedException, JSchException {
        channelExec.connect(1000);
        sendCommand(password, 100);
        sendCommand("enable", 100);
        sendCommand(password, 100);
        sendCommand("GEPON", 100);
        sendCommand("cd service", 100);
        sendCommand("terminal length 0", 100);
        sendCommand("cd ..", 100);
        sendCommand("lll", 100);
        sendCommand("show running-config", 100);
        sendCommand("", 100);
        isFinal = true;
        sendCommand("", 100);
    }

    /**
     * Finaliza a sessão e fecha o canal.
     */
    private void finalMessage() {
        // Interrompe a thread, caso esteja esperando
        if (thread != null) {
            thread.interrupt();
        }
        if (channelExec != null && channelExec.isConnected()) {
            channelExec.disconnect();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
            System.out.println("Connection closed.");
        }
    }
}
