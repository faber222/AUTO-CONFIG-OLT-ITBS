package engtelecom.access;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import org.apache.commons.net.telnet.TelnetClient;

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

    public Telnet8820Plus(String host, int port, String user, String pwd) {
        this.host = host;
        this.port = port;
        this.username = user;
        this.password = pwd;
    }

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
                // Adiciona um atraso de 100ms para o usuario
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
                // Adiciona um atraso de 10s 
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            readCommandsFromFile(nomeArq);

            finalMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readCommandsFromFile(final String filename) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String command;
            while ((command = fileReader.readLine()) != null) {
                writeToTelnet(command);
                try {
                    // Adiciona um atraso de 1s após cada out.println
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeToTelnet(String command) {
        command += "\r\n"; // Adicione a quebra de linha ao final de cada comando
        out.write(command);
        out.flush();
    }

    public static void main(String[] args) {
        Telnet8820Plus telnetClient = new Telnet8820Plus("10.1.40.133", 23, "admin", "admin");
        telnetClient.oltAccess("teste.txt");
    }

    /**
     * Mensagem de alerta ao usuário
     */
    private void finalMessage() {
        JOptionPane.showInternalMessageDialog(null, "Comandos aplicados com sucesso!", "Aviso!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void run() {
        try {
            String answer;
            while (active) {
                while ((answer = in.readLine()) != null) {
                    if (answer.equals("Bye.")) {
                        System.exit(0);
                    }
                    System.out.println(answer);
                }
            }
        } catch (IOException exception) {
            System.err.println("Erro de comunicação.");
            System.exit(1);
        }
    }

}
