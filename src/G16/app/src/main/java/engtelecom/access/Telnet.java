package engtelecom.access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Telnet implements Runnable {

    private Socket socket;
    private BufferedReader in, stdIn;
    private PrintWriter out;
    private Thread thread;
    private boolean active;

    public Telnet(String host, int port, String username, String password, String[] commands) {
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Início da Leitura em tempo real:
            active = true;
            thread = new Thread(this);
            thread.start();

            // Login
            out.println(username);
            out.println(password);

            // Executa comandos
            for (String command : commands) {
                out.println(command);
            }

        } catch (UnknownHostException exception) {
            System.err.println("Host " + host + " desconhecido");
            System.exit(1);
        } catch (IOException exception) {
            System.err.println("Erro na entrada.");
            System.exit(1);
        }

        // Caso queria liberar o prompt para digitação.
        // Executa comandos
        while (true) {
            String command = getUserInput();
            if ("Bye.".equals(command)) {
                System.exit(0);
            }
            out.println(command);
        }
        // try {

        // stdIn = new BufferedReader(new InputStreamReader(System.in));
        // String input;

        // while ((input = stdIn.readLine()) != null || !active) {
        // if (input == "Bye.") {
        // System.exit(0);
        // }

        // out.println(input);
        // }

        // }

        // catch (IOException exception) {
        // System.err.println("Erro de entrada na comunicacao.");
        // System.exit(1);
        // }

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

    private static String getUserInput() {
        return JOptionPane.showInputDialog("Digite um comando:");
    }

    private static String getIpAddress() {
        return JOptionPane.showInputDialog("Digite o ip da olt:");
    }

    private static String getOltUser() {
        return JOptionPane.showInputDialog("Digite o usuario da olt:");
    }

    private static String getOltPwd() {
        return JOptionPane.showInputDialog("Digite a senha da olt:");
    }

    public static void main(String args[]) {
        String host;
        int port;
        String username;
        String password;
        String[] commands;

        host = getIpAddress();
        port = 23;
        username = getOltUser();
        password = getOltPwd();
        commands = new String[] { "enable", "sh in br", "sh ip interface", "sh ip rou" };
        // File commands = new File("scriptG16.txt");

        new Telnet(host, port, username, password, commands);
    }
}
