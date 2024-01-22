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
 * Mas esse código fornece apenas um acesso ao socket telnet, e não uma conexão real.
 * Portanto, caso seu produto só forneça usando um acesso real, deve ser usado o Telnet8820Plus
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
    private final String host;
    private final int port;
    private final String username;
    private final String password;

    /**
     * Construtor padrão que inicializa os atributos com os valores fornecidos.
     *
     * @param host O endereço IP ou nome do host para a conexão Telnet.
     * @param port A porta para a conexão Telnet.
     * @param user O nome de usuário para autenticação Telnet.
     * @param pwd  A senha para autenticação Telnet.
     */
    public Telnet(String host, int port, String user, String pwd) {
        // Inicialização dos atributos com os valores fornecidos
        this.host = host;
        this.port = port;
        this.username = user;
        this.password = pwd;
    }

    /**
     * Método principal para realizar o acesso Telnet à OLT.
     * 
     * @param nomeArq Nome do script gerado
     */
    public void oltAccess(final String nomeArq) {
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

            // Ler comandos do arquivo
            readCommandsFromFile(nomeArq);

            // Mostra a mensagem que deu tudo certo e alerta o usuario para salvar as configurações
            finalMessage();

        } catch (final UnknownHostException exception) {
            System.err.println("Host " + host + " desconhecido");
            JOptionPane.showInternalMessageDialog(null, "Host " + host
                    + " desconhecido", "Aviso!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        } catch (final IOException exception) {
            System.err.println("Erro na entrada.");
            JOptionPane.showInternalMessageDialog(null, 
                    "Erro na entrada.", "Aviso!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        }

        // // Interatividade opcional para inserir comandos adicionais
        // while (true) {
        //     final String command = getUserInput();
        //     if ("Bye.".equals(command)) {
        //         System.exit(0);
        //     }
        //     out.println(command);
        // }
    }

    /**
     * Método para ler comandos de um arquivo.
     * 
     * @param filename O nome do arquivo contendo os comandos.
     * @throws IOException Se houver um erro de leitura do arquivo.
     */
    private void readCommandsFromFile(final String filename) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String command;
            while ((command = fileReader.readLine()) != null) {
                out.println(command);
                try {
                    // Adiciona um atraso de 100ms após cada out.println
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // Lida com exceção de interrupção (se necessário)
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Mensagem de alerta ao usuário
     */
    private void finalMessage() {
        JOptionPane.showInternalMessageDialog(null, "Comandos aplicados com sucesso!", "Aviso!",
                JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showInternalMessageDialog(null, "NAO ESQUECA DE VALIDAR E SALVAR AS CONFIGURACOES!", "Aviso!",
                JOptionPane.INFORMATION_MESSAGE);
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
        } catch (final IOException exception) {
            System.err.println("Erro de comunicacao.");
            JOptionPane.showInternalMessageDialog(null, 
                    "Erro de comunicacao.", "Aviso!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        }
    }

    // /**
    //  * Método auxiliar para obter a entrada do usuário por meio de uma janela de
    //  * diálogo.
    //  * 
    //  * @return O comando inserido pelo usuário.
    //  */
    // private static String getUserInput() {
    //     return JOptionPane.showInputDialog(null,"Digite um comando:", "Para sair, digite Bye.", JOptionPane.OK_OPTION);
    // }
}
