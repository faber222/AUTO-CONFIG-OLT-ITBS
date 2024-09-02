package engtelecom.access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class TelnetFhtt implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Thread thread;
    private volatile boolean active;

    // Atributos para oltAccess
    private final String host;
    private final int port;
    private final String username;
    private final String password;

    public TelnetFhtt(String host, int port, String user, String pwd) {
        this.host = host;
        this.port = port;
        this.username = user;
        this.password = pwd;
    }

    public void oltAccess(final String nomeArq) throws InterruptedException {
        try {
            // Configuração do socket e streams de entrada/saída
            thread = new Thread(this);
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            thread.start();
            // Início da Leitura em tempo real
            active = true;

            // Login
            out.println(username);
            Thread.sleep(100);
            out.println(password);
            Thread.sleep(100);
            out.println("enable");
            Thread.sleep(100);
            out.println(password);
            Thread.sleep(100);
            out.println("config");
            Thread.sleep(100);

            // Ler comandos do arquivo
            readCommandsFromFile(nomeArq);

            // Mostra a mensagem que deu tudo certo e alerta o usuario para salvar as
            // configurações
            finalMessage();
        } catch (final UnknownHostException exception) {
            System.err.println("Host " + host + " desconhecido");
            JOptionPane.showMessageDialog(null, "Host " + host
                    + " desconhecido", "Aviso!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        } catch (final IOException exception) {
            System.err.println("Erro na entrada.");
            JOptionPane.showMessageDialog(null,
                    "Erro na entrada.", "Aviso!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        }
    }

    private void readCommandsFromFile(final String filename) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String command;
            while ((command = fileReader.readLine()) != null) {
                out.println(command);
                try {
                    // Adiciona um atraso de 100ms após cada out.println
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Respeita a interrupção
                }
            }
        }
    }

    private void finalMessage() {
        JOptionPane.showMessageDialog(null, "Comandos aplicados com sucesso!", "Aviso!",
                JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "NAO ESQUECA DE VALIDAR E SALVAR AS CONFIGURACOES!", "Aviso!",
                JOptionPane.INFORMATION_MESSAGE);

        // Define active como false para encerrar a thread
        active = false;

        // Interrompe a thread, caso esteja esperando
        if (thread != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            thread.interrupt();
        }
    }

    public void run() {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("logTelnet.txt", true))) {
            String answer;
            while (active && !Thread.currentThread().isInterrupted()) {
                if ((answer = in.readLine()) != null) {
                    fileWriter.write(answer);
                    fileWriter.newLine(); // Adiciona uma nova linha após cada resposta
                }
            }
        } catch (IOException exception) {
            if (active) {
                System.err.println("Erro de comunicacao.");
                JOptionPane.showMessageDialog(null,
                        "Erro de comunicacao.", "Aviso!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
