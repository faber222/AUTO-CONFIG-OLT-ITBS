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
 * Mas esse código fornece apenas um acesso ao socket telnet, e não uma conexão
 * real.
 * Portanto, caso seu produto só forneça usando um acesso real, deve ser usado o
 * Telnet8820Plus
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
    private final String oltName;

    /**
     * Construtor padrão que inicializa os atributos com os valores fornecidos.
     *
     * @param host O endereço IP ou nome do host para a conexão Telnet.
     * @param port A porta para a conexão Telnet.
     * @param user O nome de usuário para autenticação Telnet.
     * @param pwd  A senha para autenticação Telnet.
     */
    public Telnet(final String host, final int port, final String user, final String pwd, final String oltName) {
        // Inicialização dos atributos com os valores fornecidos
        this.host = host;
        this.port = port;
        this.username = user;
        this.password = pwd;
        this.oltName = oltName;
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

            // Mostra a mensagem que deu tudo certo e alerta o usuario para salvar as
            // configurações
            finalMessage();
        } catch (final UnknownHostException exception) {
            System.err.println("Host " + host + " desconhecido");
            JOptionPane.showMessageDialog(null, "Host " + host
                    + " desconhecido", "Aviso!",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (final IOException exception) {
            System.err.println("Erro na entrada.");
            JOptionPane.showMessageDialog(null,
                    "Erro na entrada.", "Aviso!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Implementação do método run() da interface Runnable.
     * Este método é executado em uma thread separada para permitir a leitura
     * em tempo real das respostas do dispositivo de rede.
     */
    public void run() {
        try (BufferedWriter fileWriter = new BufferedWriter(
                new FileWriter("log" + this.oltName + "Telnet.txt", true))) {
            String answer;
            while (active && !Thread.currentThread().isInterrupted()) {
                if ((answer = in.readLine()) != null) {
                    fileWriter.write(answer);
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
     * Método para ler comandos de um arquivo.
     * 
     * @param filename O nome do arquivo contendo os comandos.
     * @throws IOException Se houver um erro de leitura do arquivo.
     */
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
                try {
                    // Adiciona um atraso de 100ms após cada out.println
                    Thread.sleep(100);
                } catch (final InterruptedException e) {
                    // Lida com exceção de interrupção (se necessário)
                    e.printStackTrace();
                }
            }
        }
        System.out.println("\nTodos os comandos da OLT " + this.oltName + " foram enviados!");
    }

    /**
     * Mensagem de alerta ao usuário
     */
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
            } catch (final IOException e) {
                e.printStackTrace();
            }
            thread.interrupt();
        }
    }
}
