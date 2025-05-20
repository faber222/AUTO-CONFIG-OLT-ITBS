package engtelecom.access.read.fhtt;

import com.jcraft.jsch.*;
import java.io.*;
import java.util.Properties;
import javax.swing.*;

public class SshReadFhtt implements Runnable {
    public interface SshOperationListener {
        void onOperationComplete(String fileName);

        void onOperationFailed(Exception e);
    }

    private Session session;
    private ChannelExec channel;
    private PrintWriter out;
    private Thread thread;
    private boolean active;

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String fileName;
    private SshOperationListener listener;

    public SshReadFhtt(String host, int port, String username, String password, String fileName) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.fileName = fileName;
    }

    public void setListener(SshOperationListener listener) {
        this.listener = listener;
    }

    public void startConnection() {
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setPassword(password);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect(5000);

            channel = (ChannelExec) session.openChannel("exec");
            out = new PrintWriter(channel.getOutputStream(), true);

            active = true;
            channel.connect();

            applyCommands();
            readResponses();

            if (listener != null) {
                SwingUtilities.invokeLater(() -> listener.onOperationComplete(fileName));
            }
        } catch (JSchException e) {
            notifyError("Erro na conexão SSH: " + e.getMessage(), e);
        } catch (Exception e) {
            notifyError("Erro durante a operação SSH", e);
        } finally {
            closeResources();
        }
    }

    private void readResponses() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(channel.getInputStream()));

            String line;
            while (active && !Thread.currentThread().isInterrupted() &&
                    (line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();

                if (line.trim().endsWith("Admin(DEBUG_H)>") ||
                        line.trim().endsWith("!end  of config -----------------------------------------------")) {
                    active = false;
                    break;
                }
            }
        }
    }

    private void applyCommands() throws Exception {
        sendCommand(password, 100);
        sendCommand("enable", 100);
        sendCommand(password, 100);
        sendCommand("GEPON", 100);
        sendCommand("cd service", 100);
        sendCommand("terminal length 0", 100);
        sendCommand("cd ..", 100);
        sendCommand("lll", 100);
        sendCommand("show running-config", 100);
        sendCommand("", 100);
    }

    private void sendCommand(String command, int delay) throws InterruptedException {
        out.println(command);
        Thread.sleep(delay);
    }

    private void notifyError(String message, Exception e) {
        if (listener != null) {
            SwingUtilities.invokeLater(() -> {
                listener.onOperationFailed(e);
                JOptionPane.showMessageDialog(null, message, "Erro SSH", JOptionPane.ERROR_MESSAGE);
            });
        }
    }

    private void closeResources() {
        active = false;
        if (channel != null && channel.isConnected()) {
            channel.disconnect();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
        if (thread != null) {
            thread.interrupt();
        }
    }

    public void stop() {
        closeResources();
    }
}