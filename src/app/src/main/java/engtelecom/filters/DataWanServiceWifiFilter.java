package engtelecom.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataWanServiceWifiFilter {
    private String path;

    public DataWanServiceWifiFilter(String path) {
        this.path = path;
    }

    public void start() {
        List<String[]> wifiConfigs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Regex para capturar configurações do wifi_serv_wlan
                Pattern wlanPattern = Pattern.compile(
                        "set wifi_serv_wlan slot (\\d+) pon (\\d+) onu (\\d+) serv_no (\\d+) index (\\d+) " +
                                "ssid (\\S+) ([^\\s]+) hide (\\S+) authmode (\\S+) encrypt_type (\\S+) wpakey (\\S+) " +
                                "interval (\\d+) radius_serv ipv4 (\\S+) port (\\d+) pswd (\\S+)");

                Matcher wlanMatcher = wlanPattern.matcher(line);

                if (wlanMatcher.find()) {
                    String slot = wlanMatcher.group(1); // Slot
                    String pon = wlanMatcher.group(2); // Pon
                    String onu = wlanMatcher.group(3); // ONU
                    String servNo = wlanMatcher.group(4); // Número do serviço
                    String index = wlanMatcher.group(5); // Índice do Wi-Fi
                    String ssidStatus = wlanMatcher.group(6); // Enable/Disable
                    String ssid = wlanMatcher.group(7); // Nome do SSID
                    String hideStatus = wlanMatcher.group(8); // SSID oculto ou não
                    String authmode = wlanMatcher.group(9); // Tipo de autenticação
                    String encryptType = wlanMatcher.group(10); // Tipo de criptografia
                    String wpakey = wlanMatcher.group(11).equals("null") ? "N/A" : wlanMatcher.group(11); // Senha do
                                                                                                          // Wi-Fi
                    String interval = wlanMatcher.group(12); // Intervalo de autenticação Radius
                    String radiusIP = wlanMatcher.group(13); // IP do Radius
                    String radiusPort = wlanMatcher.group(14); // Porta do Radius
                    String radiusPswd = wlanMatcher.group(15).equals("null") ? "N/A" : wlanMatcher.group(15);// Senha do
                                                                                                             // Radius

                    wifiConfigs.add(new String[] {
                            slot, pon, onu, servNo, index, ssidStatus, ssid, hideStatus, authmode,
                            encryptType, wpakey, radiusIP, radiusPort, radiusPswd
                    });
                }
                // [0]; // Slot
                // [1]; // Pon
                // [2]; // ONU
                // [3]; // Número do serviço
                // [4]; // Índice do Wi-Fi
                // [5]; // Enable/Disable
                // [6]; // Nome do SSID
                // [7]; // SSID oculto ou não
                // [8]; // Tipo de autenticação (WPA, WPA2, etc.)
                // [9]; // Tipo de criptografia
                // [10]; // Senha do Wi-Fi
                // [11]; // Intervalo de autenticação
                // [12]; // IP do Radius
                // [13]; // Porta do Radius
                // [14]; // Senha do Radius

            }

            // Exibir os resultados (opcional, apenas para conferência)
            System.out.println("Configurações Wi-Fi:");
            for (String[] config : wifiConfigs) {
                System.out.println(Arrays.toString(config));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
