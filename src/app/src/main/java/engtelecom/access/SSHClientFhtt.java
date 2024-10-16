package engtelecom.access;

import static java.lang.Thread.sleep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHClientFhtt implements Runnable {

    private Session session;
    private ChannelExec channelExec;
    private PrintWriter out;
    private Thread thread;
    private boolean active;

    // Atributos para oltAccess
    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String oltName;

    /**
     * Construtor padrão que inicializa os atributos com os valores fornecidos.
     *
     * @param host O endereço IP ou nome do host para a conexão SSH.
     * @param port A porta para a conexão SSH.
     * @param user O nome de usuário para autenticação SSH.
     * @param pwd  A senha para autenticação SSH.
     */
    public SSHClientFhtt(final String host, final int port, final String user, final String pwd, final String oltName) {
        this.host = host;
        this.port = port;
        this.username = user;
        this.password = pwd;
        this.oltName = oltName;
    }

    public boolean oltAccess(final String nomeArq) {
        try {
            // Configura a sessão SSH
            final JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setPassword(password);

            // Desativa a verificação de chave de host
            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            // Estabelece a conexão SSH
            System.out.println("Establishing Connection...");
            session.connect(5000); // Tempo limite de 5 segundos
            System.out.println("Connection established.");

            // Cria o canal para execução de comandos
            channelExec = (ChannelExec) session.openChannel("exec");

            // Configuração de leitura de dados
            final OutputStream inputStream = channelExec.getOutputStream();
            out = new PrintWriter(inputStream, true);

            // Início da Leitura em tempo real
            active = true;
            thread = new Thread(this);
            thread.start();

            applyCommands();

            readCommandsFromFile(nomeArq);
            // Espera até que o processamento dos comandos esteja completo
            // thread.join(); // Aguarda o término da thread de leitura (run)

            // Finaliza a conexão
            finalMessage();
            return true;
        } catch (final JSchException e) {
            System.err.println("Erro na conexão SSH.");

            e.printStackTrace();
        } catch (final Exception e) {
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
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(
                "log" + this.oltName + "Telnet.txt", true))) {
            final BufferedReader in = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));
            String answer;
            while (active && !Thread.currentThread().isInterrupted()) {
                if ((answer = in.readLine()) != null) {
                    fileWriter.write(answer);
                    fileWriter.newLine();
                    System.out.println(answer);
                }
            }
        } catch (final Exception e) {
            if (active) {
                System.err.println("Erro de comunicação.");
            }
        }
    }

    private void readCommandsFromFile(final String filename) throws IOException {
        final File file = new File(filename);
        final long totalBytes = file.length();
        long bytesRead = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String command;

            while ((command = fileReader.readLine()) != null) {
                out.println(command);

                // Atualiza bytes lidos
                bytesRead += command.getBytes().length + System.lineSeparator().getBytes().length;

                // Exibe a barra de progresso
                final int progressPercentage = (int) (((double) bytesRead / totalBytes) * 100);
                System.out.print("Progresso " + this.oltName + ": " + progressPercentage + "%\r");
                sleep(100);
                // Adiciona um atraso de 100ms após cada out.println
            }
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nTodos os comandos da OLT " + this.oltName + " foram enviados!");
    }

    private void applyCommands() throws InterruptedException, JSchException {
        channelExec.connect(1000);

        out.println(password);
        sleep(100);
        out.println("enable");
        sleep(100);
        out.println(password);
        sleep(100);
        out.println("config");
        sleep(100);
        out.println(""); // Envia um enter para finalizar o comando
    }

    /**
     * Finaliza a sessão e fecha o canal.
     */
    private void finalMessage() {
        JOptionPane.showMessageDialog(null, "Comandos aplicados com sucesso!", "Aviso!",
                JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "NAO ESQUECA DE VALIDAR E SALVAR AS CONFIGURACOES!", "Aviso!",
                JOptionPane.INFORMATION_MESSAGE);
        // Interrompe a thread, caso esteja esperando
        if (thread != null) {
            thread.interrupt();
            active = false;

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
