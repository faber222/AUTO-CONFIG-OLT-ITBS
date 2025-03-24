package engtelecom.filters;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class DataWanServicePPPFilter {
    private final String path;
    private final List<String[]> wanRouterConfigs; // Configs de "ty r"
    private final List<String[]> wanBridgeConfigs; // Configs de "ty b"

    public DataWanServicePPPFilter(final String path) {
        this.path = path;
        this.wanRouterConfigs = new ArrayList<>();
        this.wanBridgeConfigs = new ArrayList<>();
    }

    /**
     * [0] slot
     * [1] pon
     * [2] onu
     * [3] index
     * [4] Mode
     * [5] Tipo (r)
     * [6] VLAN
     * [7] NAT (en/dis)
     * [8] PPPoE (pppoe/null)
     * [9] Usuário PPPoE (se existir)
     * [10] Senha PPPoE (se existir)
     * 
     * @return Lista com configurações do tipo "r" (router)
     */
    public List<String[]> getWanRouterConfigs() {
        return wanRouterConfigs;
    }

    /**
     * [0] slot
     * [1] pon
     * [2] onu
     * [3] index
     * [4] Mode
     * [5] Tipo (b)
     * [6] VLAN
     * [7] NAT (en/dis)
     * [8] VLAN Mode (tag/transparent)
     * [9] TVLAN (dis ou número)
     * [10] TVID
     * 
     * @return Lista com configurações do tipo "b" (bridge)
     */
    public List<String[]> getWanBridgeConfigs() {
        return wanBridgeConfigs;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Regex para capturar configurações do tipo "r" (router)
                Pattern routerPattern = Pattern.compile(
                        "set wancfg sl (\\d+) (\\d+) (\\d+) ind (\\d+) mode (\\S+) ty r (\\d+) \\d+ nat (\\S+).*? dsp (pppoe|null)(?: pro dis (\\S+) key:([^\\s]+))?");
                Matcher routerMatcher = routerPattern.matcher(line);

                if (routerMatcher.find()) {
                    final String slot = routerMatcher.group(1);
                    final String pon = routerMatcher.group(2);
                    final String onu = routerMatcher.group(3);
                    final String ind = routerMatcher.group(4);
                    final String mode = routerMatcher.group(5);
                    final String tipo = "r"; // Tipo router
                    final String vlan = routerMatcher.group(6);
                    final String nat = routerMatcher.group(7);
                    final String pppoe = routerMatcher.group(8);
                    final String user = routerMatcher.group(9) != null ? routerMatcher.group(9) : "N/A";
                    final String pass = routerMatcher.group(10) != null ? routerMatcher.group(10) : "N/A";

                    wanRouterConfigs
                            .add(new String[] { slot, pon, onu, ind, mode, tipo, vlan, nat, pppoe, user, pass });
                    continue;
                }

                // Regex para capturar configurações do tipo "b" (bridge)
                Pattern bridgePattern = Pattern.compile(
                        "set wancfg sl (\\d+) (\\d+) (\\d+) ind (\\d+) mode (\\S+) ty b (\\d+) \\d+ nat (\\S+).*? vlanm (\\S+) tvlan (\\S+) (\\d+)");
                Matcher bridgeMatcher = bridgePattern.matcher(line);

                if (bridgeMatcher.find()) {
                    final String slot = bridgeMatcher.group(1);
                    final String pon = bridgeMatcher.group(2);
                    final String onu = bridgeMatcher.group(3);
                    final String ind = bridgeMatcher.group(4);
                    final String mode = bridgeMatcher.group(5);
                    final String tipo = "b"; // Tipo bridge
                    final String vlan = bridgeMatcher.group(6);
                    final String nat = bridgeMatcher.group(7);
                    final String vlanMode = bridgeMatcher.group(8); // Ex: "tag" ou "transparent"
                    final String tvlan = bridgeMatcher.group(9); // VLAN de transporte
                    final String tvid = bridgeMatcher.group(10); // TVID extraído

                    wanBridgeConfigs.add(new String[] { slot, pon, onu, ind, mode, tipo, vlan, nat, vlanMode, tvlan,
                            tvid });
                }
            }

            // Debug: Exibir os resultados processados
            // System.out.println("Configurações WAN - Router:");
            // for (final String[] config : wanRouterConfigs) {
            // System.out.println(Arrays.toString(config));
            // }

            // System.out.println("\nConfigurações WAN - Bridge:");
            // for (final String[] config : wanBridgeConfigs) {
            // System.out.println(Arrays.toString(config));
            // }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
