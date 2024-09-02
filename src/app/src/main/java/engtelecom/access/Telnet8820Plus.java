/**
 * @author faber222
 * @since 2024
*/
package engtelecom.access;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import org.apache.commons.net.telnet.TelnetClient;

/**
 * Classe que realiza acesso via Telnet a uma OLT 8820 Plus.
 */
public class Telnet8820Plus implements Runnable {
    private TelnetClient telnetClient;
    private BufferedReader in;
    private PrintWriter out;
    private Thread thread;
    private boolean active;

    // Atributos para oltAccess
    private final String host;
    private final int port;
    private final String username;
    private final String password;

    /**
     * Construtor da classe Telnet8820Plus.
     * 
     * @param host O endereço IP do host.
     * @param port A porta para a conexão Telnet.
     * @param user O nome de usuário para autenticação.
     * @param pwd  A senha para autenticação.
     */
    public Telnet8820Plus(String host, int port, String user, String pwd) {
        this.host = host;
        this.port = port;
        this.username = user;
        this.password = pwd;
    }

    /**
     * Método principal que realiza o acesso Telnet à OLT 8820 Plus.
     * 
     * @param nomeArq O nome do arquivo contendo os comandos a serem executados.
     */
    public void oltAccess(final String nomeArq) {
        try {
            telnetClient = new TelnetClient();
            telnetClient.connect(host, port);

            in = new BufferedReader(new InputStreamReader(telnetClient.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(telnetClient.getOutputStream(), true);

            active = true;
            thread = new Thread(this);
            thread.start();

            try {
                // Adiciona um atraso de 100ms para o usuário
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            out.println(username);

            try {
                // Adiciona um atraso de 1s para a senha
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            out.println(password);

            try {
                // Adiciona um atraso de 8s
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            readCommandsFromFile(nomeArq);

            finalMessage();
            // telnetClient.disconnect();
            // thread.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lê comandos de um arquivo e os envia para a OLT via Telnet.
     * 
     * @param filename O nome do arquivo contendo os comandos.
     * @throws IOException Exceção lançada em caso de erro na leitura do arquivo.
     */
    private void readCommandsFromFile(final String filename) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String command;
            while ((command = fileReader.readLine()) != null) {
                writeToTelnet(command);
                try {
                    // Adiciona um atraso de 1s após cada out.println
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Envia um comando para a OLT via Telnet.
     * 
     * @param command O comando a ser enviado.
     */
    private void writeToTelnet(String command) {
        command += "\r\n"; // Adiciona a quebra de linha ao final de cada comando
        out.write(command);
        out.flush();
    }

    /**
     * Exibe uma mensagem de alerta ao usuário.
     */
    private void finalMessage() {
        JOptionPane.showMessageDialog(null, "Comandos aplicados com sucesso!", "Aviso!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método run da interface Runnable para processar as mensagens recebidas do
     * Telnet.
     */
    public void run() {
        try {
            String answer;
            while (active) {
                while ((answer = in.readLine()) != null) {
                    System.out.println(answer);
                }
            }
        } catch (IOException exception) {
            System.err.println("Erro de comunicação.");
            JOptionPane.showMessageDialog(null,
                    "Erro de comunicacao.", "Aviso!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
