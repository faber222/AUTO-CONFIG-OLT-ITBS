package engtelecom.access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 * Esta classe implementa uma conexão Telnet para acessar um dispositivo de
 * rede,
 * como uma OLT (Optical Line Terminal).
 * 
 * <p>
 * Os atributos padrão são configurados para acesso a uma OLT, mas podem ser
 * personalizados conforme necessário.
 * </p>
 * 
 * <p>
 * Os comandos padrão executados na OLT incluem a ativação, exibição de
 * interfaces,
 * exibição de informações de IP e exibição de rotas IP.
 * </p>
 * 
 * <p>
 * O acesso Telnet é realizado por meio de um socket, e a leitura das respostas
 * é feita em uma thread separada para permitir a leitura em tempo real.
 * </p>
 * 
 * <p>
 * O usuário pode interagir inserindo comandos adicionais por meio de uma janela
 * de entrada de dados.
 * </p>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Telnet">Telnet - Wikipedia</a>
 */
public class Telnet implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Thread thread;
    private boolean active;

    // Atributos para oltAccess
    private String host;
    private int port;
    private String username;
    private String password;
    private String[] commands;

    /**
     * Construtor padrão que inicializa os atributos com valores padrão.
     */
    public Telnet() {
        // Inicialização dos atributos padrão
        host = getIpAddress();
        port = 23;
        username = getOltUser();
        password = getOltPwd();
        commands = new String[] { "enable", "sh in br", "sh ip interface", "sh ip rou" };
    }

    /**
     * Método principal para realizar o acesso Telnet à OLT.
     */
    public void oltAccess() {
        try {
            // Configuração do socket e streams de entrada/saída
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Início da Leitura em tempo real
            active = true;
            thread = new Thread(this);
            thread.start();

            // Login
            out.println(username);
            out.println(password);

            // Executa comandos padrão
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

        // Interatividade opcional para inserir comandos adicionais
        while (true) {
            String command = getUserInput();
            if ("Bye.".equals(command)) {
                System.exit(0);
            }
            out.println(command);
        }
    }

    /**
     * Implementação do método run() da interface Runnable.
     * Este método é executado em uma thread separada para permitir a leitura
     * em tempo real das respostas do dispositivo de rede.
     */
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

    /**
     * Método auxiliar para obter a entrada do usuário por meio de uma janela de
     * diálogo.
     * 
     * @return O comando inserido pelo usuário.
     */
    private static String getUserInput() {
        return JOptionPane.showInputDialog("Digite um comando:");
    }

    /**
     * Método auxiliar para obter o endereço IP da OLT por meio de uma janela de
     * diálogo.
     * 
     * @return O endereço IP da OLT.
     */
    private static String getIpAddress() {
        return JOptionPane.showInputDialog("Digite o IP da OLT:");
    }

    /**
     * Método auxiliar para obter o nome de usuário da OLT por meio de uma janela de
     * diálogo.
     * 
     * @return O nome de usuário da OLT.
     */
    private static String getOltUser() {
        return JOptionPane.showInputDialog("Digite o usuário da OLT:");
    }

    /**
     * Método auxiliar para obter a senha da OLT por meio de uma janela de diálogo.
     * 
     * @return A senha da OLT.
     */
    private static String getOltPwd() {
        return JOptionPane.showInputDialog("Digite a senha da OLT:");
    }
}
