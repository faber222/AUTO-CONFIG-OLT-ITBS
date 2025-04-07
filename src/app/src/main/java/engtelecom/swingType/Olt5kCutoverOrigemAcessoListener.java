package engtelecom.swingType;

public interface Olt5kCutoverOrigemAcessoListener {
    void onProfileDadosOrigemCreated(String ip, String user, String passwd,
            String port, boolean isTelnet);
}
