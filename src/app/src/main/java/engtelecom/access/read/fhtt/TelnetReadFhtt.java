package engtelecom.access.read.fhtt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class TelnetReadFhtt implements Runnable {
    public interface TelnetOperationListener {
        void onOperationComplete(String fileName);

        void onOperationFailed(Exception e);
    }

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Thread thread;
    private boolean active;

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String fileName;
    private TelnetOperationListener listener;

    public TelnetReadFhtt(String host, int port, String username, String password, String fileName) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.fileName = fileName;
    }

    public void setListener(TelnetOperationListener listener) {
        this.listener = listener;
    }

    public void startConnection() {
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        try {
            final int timeout = 5000;
            final SocketAddress socketAddress = new InetSocketAddress(host, port);
            socket = new Socket();
            socket.connect(socketAddress, timeout);

            if (!socket.isConnected()) {
                throw new IOException("Falha ao conectar ao host " + host + " na porta " + port);
            }

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            active = true;

            // Login sequence
            sendCommand(username, 100);
            sendCommand(password, 100);
            sendCommand("enable", 100);
            sendCommand(password, 100);
            sendCommand("GEPON", 100);

            applyCommands();

            // Read responses
            readResponses();

            if (listener != null) {
                SwingUtilities.invokeLater(() -> listener.onOperationComplete(fileName));
            }
        } catch (final UnknownHostException exception) {
            notifyError("Erro na entrada.\nHost desconhecido: " + host, exception);
        } catch (final IOException exception) {
            notifyError("Erro de I/O ao tentar conectar ao host " + host + " na porta " + port, exception);
        } catch (final InterruptedException ex) {
            notifyError("Operação interrompida", ex);
        } finally {
            closeResources();
        }
    }

    private void sendCommand(String command, int delay) throws InterruptedException {
        out.println(command);
        Thread.sleep(delay);
    }

    private void readResponses() throws IOException, InterruptedException {
        final String prompt = "Admin(DEBUG_H)>";
        final String prompt2 = "!end  of config -----------------------------------------------";

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(this.fileName, false))) {
            String answer;
            while (active && !Thread.currentThread().isInterrupted()) {
                if ((answer = in.readLine()) != null) {
                    fileWriter.write(answer);
                    fileWriter.newLine();

                    if (answer.trim().endsWith(prompt) || answer.trim().endsWith(prompt2)) {
                        active = false;
                        break;
                    }
                }
            }
        }
    }

    private void applyCommands() throws InterruptedException {
        sendCommand("cd service", 100);
        sendCommand("terminal length 0", 100);
        sendCommand("cd ..", 100);
        sendCommand("lll", 100);
        sendCommand("show running-config", 100);
        sendCommand("", 100);
    }

    private void notifyError(String message, Exception exception) {
        if (listener != null) {
            SwingUtilities.invokeLater(() -> {
                listener.onOperationFailed(exception);
                JOptionPane.showMessageDialog(null, message, "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            });
        }
    }

    private void closeResources() {
        active = false;
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        active = false;
        if (thread != null) {
            thread.interrupt();
        }
        closeResources();
    }
}