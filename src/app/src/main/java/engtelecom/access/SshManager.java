package engtelecom.access;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import engtelecom.access.read.fhtt.SshReadFhtt;

public class SshManager {
    private final List<SshReadFhtt> activeConnections = new ArrayList<>();

    public void startConnection(final String host, final int port, final String username,
            final String password, final String fileName,
            final Consumer<String> onSuccess, final Consumer<Exception> onError) {

        final SshReadFhtt ssh = new SshReadFhtt(host, port, username, password, fileName);
        ssh.setListener(new SshReadFhtt.SshOperationListener() {
            @Override
            public void onOperationComplete(final String fileName) {
                activeConnections.remove(ssh);
                onSuccess.accept(fileName);
            }

            @Override
            public void onOperationFailed(final Exception e) {
                activeConnections.remove(ssh);
                onError.accept(e);
            }
        });

        activeConnections.add(ssh);
        ssh.startConnection();
    }

    public void stopAllConnections() {
        new ArrayList<>(activeConnections).forEach(SshReadFhtt::stop);
    }
}