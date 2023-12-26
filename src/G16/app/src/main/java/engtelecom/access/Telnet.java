package engtelecom.access;

// import java.awt.FlowLayout;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.PrintWriter;
// import java.net.Socket;
// import java.net.UnknownHostException;

// import javax.swing.JDialog;
// import javax.swing.JOptionPane;
// import javax.swing.JProgressBar;
// import javax.swing.SwingUtilities;

// /**
//  * Esta classe implementa uma conexão Telnet para acessar um dispositivo de
//  * rede,
//  * como uma OLT (Optical Line Terminal).
//  * 
//  * <p>
//  * Os atributos padrão são configurados para acesso a uma OLT, mas podem ser
//  * personalizados conforme necessário.
//  * </p>
//  * 
//  * <p>
//  * Os comandos padrão executados na OLT incluem a ativação, exibição de
//  * interfaces,
//  * exibição de informações de IP e exibição de rotas IP.
//  * </p>
//  * 
//  * <p>
//  * O acesso Telnet é realizado por meio de um socket, e a leitura das respostas
//  * é feita em uma thread separada para permitir a leitura em tempo real.
//  * </p>
//  * 
//  * <p>
//  * O usuário pode interagir inserindo comandos adicionais por meio de uma janela
//  * de entrada de dados.
//  * </p>
//  * 
//  * @see <a href="https://en.wikipedia.org/wiki/Telnet">Telnet - Wikipedia</a>
//  */
// public class Telnet implements Runnable {

//     private Socket socket;
//     private BufferedReader in;
//     private PrintWriter out;
//     private Thread thread;
//     private boolean active;

//     // Atributos para oltAccess
//     private String host;
//     private int port;
//     private String username;
//     private String password;

//     private JDialog loadingDialog;
//     private JProgressBar progressBar;

//     /**
//      * Construtor padrão que inicializa os atributos com valores padrão.
//      */
//     public Telnet() {
//         // Inicialização dos atributos padrão
//         host = getIpAddress();
//         port = getPort();
//         username = getOltUser();
//         password = getOltPwd();
//     }

//     /**
//      * Método principal para realizar o acesso Telnet à OLT.
//      */
//     public void oltAccess() {
//         try {
//             // Configuração do socket e streams de entrada/saída
//             socket = new Socket(host, port);
//             in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//             out = new PrintWriter(socket.getOutputStream(), true);

//             // Início da Leitura em tempo real
//             active = true;
//             thread = new Thread(this);
//             thread.start();

//             // Login
//             out.println(username);
//             out.println(password);

//             // Mostrar tela de carregamento enquanto lê comandos do arquivo
//             SwingUtilities.invokeLater(new Runnable() {
//                 @Override
//                 public void run() {
//                     showLoadingScreen();
//                 }
//             });

//             // Ler e aplicar comandos do arquivo
//             readAndApplyCommands("scriptG16.txt");

//             // Ocultar tela de carregamento
//             SwingUtilities.invokeLater(new Runnable() {
//                 @Override
//                 public void run() {
//                     hideLoadingScreen();
//                 }
//             });

//         } catch (UnknownHostException exception) {
//             System.err.println("Host " + host + " desconhecido");
//             System.exit(1);
//         } catch (IOException exception) {
//             System.err.println("Erro na entrada.");
//             System.exit(1);
//         }

//         // Interatividade opcional para inserir comandos adicionais
//         while (true) {
//             String command = getUserInput();
//             if ("Bye.".equals(command)) {
//                 System.exit(0);
//             }
//             out.println(command);
//         }
//     }

//     private void showLoadingScreen() {
//         // Criar a tela de carregamento
//         loadingDialog = new JDialog();
//         loadingDialog.setSize(300, 100);
//         loadingDialog.setLayout(new FlowLayout());
//         loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//         loadingDialog.setLocationRelativeTo(null);

//         // Adicionar barra de progresso
//         progressBar = new JProgressBar(0, 100);
//         progressBar.setStringPainted(true);
//         loadingDialog.add(progressBar);

//         loadingDialog.setVisible(true);
//     }

//     private void hideLoadingScreen() {
//         // Ocultar a tela de carregamento
//         loadingDialog.dispose();
//     }

//     private void readAndApplyCommands(String filename) {
//         try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
//             String command;
//             int totalLines = 0;

//             // Contar o número total de linhas no arquivo
//             while (fileReader.readLine() != null) {
//                 totalLines++;
//             }

//             // Resetar o leitor para o início do arquivo
//             try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//                 int linesRead = 0;

//                 while ((command = reader.readLine()) != null) {
//                     // Simular a aplicação de comandos
//                     applyCommand(command);

//                     // Atualizar a barra de progresso
//                     linesRead++;
//                     int progress = (int) (((double) linesRead / totalLines) * 100);

//                     // Atualizar a barra de progresso na interface gráfica
//                     SwingUtilities.invokeLater(() -> progressBar.setValue(progress));
//                 }
//             }

//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     private void applyCommand(String command) {
//         // Lógica para aplicar o comando na OLT
//         out.println(command);

//         // Simular uma operação demorada
//         try {
//             Thread.sleep(100); // Simula uma operação demorada
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }
//     }

//     /**
//      * Método para ler comandos de um arquivo.
//      * 
//      * @param filename O nome do arquivo contendo os comandos.
//      * @throws IOException Se houver um erro de leitura do arquivo.
//      */
//     private void readCommandsFromFile(String filename) throws IOException {
//         try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
//             String command;
//             while ((command = fileReader.readLine()) != null) {
//                 out.println(command);
//             }
//         }
//     }

//     /**
//      * Implementação do método run() da interface Runnable.
//      * Este método é executado em uma thread separada para permitir a leitura
//      * em tempo real das respostas do dispositivo de rede.
//      */
//     public void run() {
//         try {
//             String answer;
//             while (active) {
//                 while ((answer = in.readLine()) != null) {
//                     if (answer.equals("Bye.")) {
//                         System.exit(0);
//                     }
//                     System.out.println(answer);
//                 }
//             }
//         } catch (IOException exception) {
//             System.err.println("Erro de comunicação.");
//             System.exit(1);
//         }
//     }

//     /**
//      * Método auxiliar para obter a entrada do usuário por meio de uma janela de
//      * diálogo.
//      * 
//      * @return O comando inserido pelo usuário.
//      */
//     private static String getUserInput() {
//         return JOptionPane.showInputDialog("Digite um comando:");
//     }

//     /**
//      * Método auxiliar para obter o endereço IP da OLT por meio de uma janela de
//      * diálogo.
//      * 
//      * @return O endereço IP da OLT.
//      */
//     private static String getIpAddress() {
//         return JOptionPane.showInputDialog("Digite o IP da OLT:");
//     }

//     /**
//      * Método auxiliar para obter o nome de usuário da OLT por meio de uma janela de
//      * diálogo.
//      * 
//      * @return O nome de usuário da OLT.
//      */
//     private static String getOltUser() {
//         return JOptionPane.showInputDialog("Digite o usuário da OLT:");
//     }

//     /**
//      * Método auxiliar para obter a senha da OLT por meio de uma janela de diálogo.
//      * 
//      * @return A senha da OLT.
//      */
//     private static String getOltPwd() {
//         return JOptionPane.showInputDialog("Digite a senha da OLT:");
//     }

//     /**
//      * Método auxiliar para obter a porta da OLT por meio de uma janela de diálogo.
//      * 
//      * @return A porta da OLT.
//      */
//     private static int getPort() {
//         String portStr = JOptionPane.showInputDialog("Digite a porta da OLT:");
//         try {
//             return Integer.parseInt(portStr);
//         } catch (NumberFormatException e) {
//             System.err.println("Porta inválida. Usando porta padrão 23.");
//             return 23;
//         }
//     }
// }

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
    private final String host;
    private final int port;
    private final String username;
    private final String password;

    /**
     * Construtor padrão que inicializa os atributos com valores padrão.
     */
    public Telnet() {
        // Inicialização dos atributos padrão
        host = getIpAddress();
        port = getPort();
        username = getOltUser();
        password = getOltPwd();
    }

    /**
     * Método principal para realizar o acesso Telnet à OLT.
     * @param nomeArq       Nome do script gerado
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

        } catch (final UnknownHostException exception) {
            System.err.println("Host " + host + " desconhecido");
            System.exit(1);
        } catch (final IOException exception) {
            System.err.println("Erro na entrada.");
            System.exit(1);
        }

        // Interatividade opcional para inserir comandos adicionais
        while (true) {
            final String command = getUserInput();
            if ("Bye.".equals(command)) {
                System.exit(0);
            }
            out.println(command);
        }
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
            }
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
        } catch (final IOException exception) {
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

    /**
     * Método auxiliar para obter a porta da OLT por meio de uma janela de diálogo.
     * 
     * @return A porta da OLT.
     */
    private static int getPort() {
        final String portStr = JOptionPane.showInputDialog("Digite a porta da OLT:");
        try {
            return Integer.parseInt(portStr);
        } catch (final NumberFormatException e) {
            System.err.println("Porta inválida. Usando porta padrão 23.");
            return 23;
        }
    }
}
