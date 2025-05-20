/**
 * @author faber222
 * @since 2024
*/
package engtelecom.access.write.itbs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
    private final String oltName;

    /**
     * Construtor da classe Telnet8820Plus.
     * 
     * @param host O endereço IP do host.
     * @param port A porta para a conexão Telnet.
     * @param user O nome de usuário para autenticação.
     * @param pwd  A senha para autenticação.
     */
    public Telnet8820Plus(final String host, final int port, final String user, final String pwd,
            final String oltName) {
        this.host = host;
        this.port = port;
        this.username = user;
        this.password = pwd;
        this.oltName = oltName;
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
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }

            out.println(username);

            try {
                // Adiciona um atraso de 1s para a senha
                Thread.sleep(1000);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }

            out.println(password);

            try {
                // Adiciona um atraso de 8s
                Thread.sleep(6000);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }

            readCommandsFromFile(nomeArq);

            finalMessage();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método run da interface Runnable para processar as mensagens recebidas do
     * Telnet.
     */
    public void run() {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(
                "log" + this.oltName + "Telnet.txt", true))) {
            String answer;
            while (active && !Thread.currentThread().isInterrupted()) {
                if ((answer = in.readLine()) != null) {
                    fileWriter.write(answer);
                    // System.out.println(answer);
                    fileWriter.newLine(); // Adiciona uma nova linha após cada resposta
                }
            }
        } catch (final IOException exception) {
            if (active) {
                System.err.println("Erro de comunicacao.");
                JOptionPane.showMessageDialog(null,
                        "Erro de comunicacao.", "Aviso!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Lê comandos de um arquivo e os envia para a OLT via Telnet.
     * 
     * @param filename O nome do arquivo contendo os comandos.
     * @throws IOException Exceção lançada em caso de erro na leitura do arquivo.
     */
    private void readCommandsFromFile(final String filename) throws IOException {
        final File file = new File(filename);
        final long totalBytes = file.length();
        long bytesRead = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String command;
            while ((command = fileReader.readLine()) != null) {
                writeToTelnet(command);

                // Atualiza bytes lidos
                bytesRead += command.getBytes().length + System.lineSeparator().getBytes().length;

                // Exibe a barra de progresso
                final int progressPercentage = (int) (((double) bytesRead / totalBytes) * 100);
                System.out.print("Progresso " + this.oltName + ": " + progressPercentage + "%\r");

                try {
                    // Adiciona um atraso de 1,5s após cada comando
                    Thread.sleep(1500);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("\nTodos os comandos da OLT " + this.oltName + " foram enviados!");
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
        if (thread != null) {
            try {
                active = false;
                telnetClient.disconnect();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            thread.interrupt();
        }
    }
}
