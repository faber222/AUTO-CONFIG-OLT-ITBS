package engtelecom.access;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import engtelecom.access.read.fhtt.TelnetReadFhtt;

public class TelnetManager {
    private final List<TelnetReadFhtt> activeConnections = new ArrayList<>();

    public void startConnection(final String host, final int port, final String username,
            final String password, final String fileName,
            final Consumer<String> onSuccess, final Consumer<Exception> onError) {

        final TelnetReadFhtt telnet = new TelnetReadFhtt(host, port, username, password, fileName);
        telnet.setListener(new TelnetReadFhtt.TelnetOperationListener() {
            @Override
            public void onOperationComplete(final String fileName) {
                activeConnections.remove(telnet);
                onSuccess.accept(fileName);
            }

            @Override
            public void onOperationFailed(final Exception e) {
                activeConnections.remove(telnet);
                onError.accept(e);
            }
        });

        activeConnections.add(telnet);
        telnet.startConnection();
    }

    public void stopAllConnections() {
        new ArrayList<>(activeConnections).forEach(TelnetReadFhtt::stop);
    }
}