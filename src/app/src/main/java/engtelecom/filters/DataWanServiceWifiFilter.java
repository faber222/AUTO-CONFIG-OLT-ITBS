package engtelecom.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataWanServiceWifiFilter {
    private final String path;
    private final List<String[]> wifiConfigs;

    public DataWanServiceWifiFilter(final String path) {
        this.path = path;
        this.wifiConfigs = new ArrayList<>();
    }

    /**
     * [0] Slot
     * [1] Pon
     * [2] ONU
     * [3] Índice do Wi-Fi (1-2)
     * [4] Index
     * [5] Enable/Disable
     * [6] Nome do SSID
     * [7] SSID oculto ou não
     * [8] Tipo de autenticação (WPA, WPA2, etc.)
     * [9] Tipo de criptografia
     * [10] Senha do Wi-Fi
     * [11] IP do Radius
     * [12] Porta do Radius
     * [13] Senha do Radius
     *
     * @return Retorna um arrayList contendo todos os dados de configuração de wifi
     */
    public List<String[]> getWifiConfigs() {
        return wifiConfigs;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Regex para capturar configurações do wifi_serv_wlan
                final Pattern wlanPattern = Pattern.compile(
                        "set wifi_serv_wlan slot (\\d+) pon (\\d+) onu (\\d+) serv_no (\\d+) index (\\d+) " +
                                "ssid (\\S+) ([^\\s]+) hide (\\S+) authmode (\\S+) encrypt_type (\\S+) wpakey (\\S+) " +
                                "interval (\\d+) radius_serv ipv4 (\\S+) port (\\d+) pswd (\\S+)");

                final Matcher wlanMatcher = wlanPattern.matcher(line);

                if (wlanMatcher.find()) {
                    final String slot = wlanMatcher.group(1); // Slot
                    final String pon = wlanMatcher.group(2); // Pon
                    final String onu = wlanMatcher.group(3); // ONU
                    final String servNo = wlanMatcher.group(4); // Índice do Wi-Fi (1-2)
                    final String index = wlanMatcher.group(5); // Index
                    final String ssidStatus = wlanMatcher.group(6); // Enable/Disable
                    final String ssid = wlanMatcher.group(7); // Nome do SSID
                    final String hideStatus = wlanMatcher.group(8); // SSID oculto ou não
                    final String authmode = wlanMatcher.group(9); // Tipo de autenticação
                    final String encryptType = wlanMatcher.group(10); // Tipo de criptografia
                    // Senha do Wi-Fi
                    final String wpakey = wlanMatcher.group(11).equals("null") ? "N/A" : wlanMatcher.group(11);

                    @SuppressWarnings("unused")
                    final String interval = wlanMatcher.group(12); // Intervalo de autenticação Radius (NÃO USADO)
                    final String radiusIP = wlanMatcher.group(13); // IP do Radius
                    final String radiusPort = wlanMatcher.group(14); // Porta do Radius
                    // Senha do Radius
                    final String radiusPswd = wlanMatcher.group(15).equals("null") ? "N/A" : wlanMatcher.group(15);

                    wifiConfigs.add(new String[] {
                            slot, pon, onu, servNo, index, ssidStatus, ssid, hideStatus, authmode,
                            encryptType, wpakey, radiusIP, radiusPort, radiusPswd
                    });
                }
            }
            // [0] Slot
            // [1] Pon
            // [2] ONU
            // [3] Índice do Wi-Fi (1-2)
            // [4] Index
            // [5] Enable/Disable
            // [6] Nome do SSID
            // [7] SSID oculto ou não
            // [8] Tipo de autenticação (WPA, WPA2, etc.)
            // [9] Tipo de criptografia
            // [10] Senha do Wi-Fi
            // [11] IP do Radius
            // [12] Porta do Radius
            // [13] Senha do Radius

            // System.out.println("Configurações Wi-Fi:");
            // for (final String[] config : wifiConfigs) {
            // System.out.println(Arrays.toString(config));
            // }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
