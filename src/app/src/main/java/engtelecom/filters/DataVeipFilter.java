package engtelecom.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataVeipFilter {
    private final String path;
    private final List<String[]> prfMgrConfigs;
    private final List<String[]> veipConfigs;

    public DataVeipFilter(final String path) {
        this.path = path;
        this.prfMgrConfigs = new ArrayList<>();
        this.veipConfigs = new ArrayList<>();
    }

    /**
     * [0] Index do perfil
     * [1] Nome do perfil
     * [2] Tipo do perfil
     * 
     * @return ArrayList contendo os dados de profile mgr config
     */
    public List<String[]> getPrfMgrConfigs() {
        return prfMgrConfigs;
    }

    /**
     * [0] slot
     * [1] PON
     * [2] ONU
     * [3] Port
     * [4] Index do onuVeip
     * [5] VLAN usada
     * [6] Perfil associado
     * 
     * @return ArrayList contendo os dados da config veip
     */
    public List<String[]> getVeipConfigs() {
        return veipConfigs;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Regex para capturar perfis do prf_mgr
                final Pattern prfMgrPattern = Pattern.compile("add servm pro ind (\\d+) na (\\S+) ty (\\S+)");
                final Matcher prfMgrMatcher = prfMgrPattern.matcher(line);

                if (prfMgrMatcher.find()) {
                    final String index = prfMgrMatcher.group(1); // Index do perfil
                    final String name = prfMgrMatcher.group(2); // Nome do perfil
                    final String type = prfMgrMatcher.group(3); // Tipo do perfil

                    prfMgrConfigs.add(new String[] { index, name, type });
                    continue;
                }

                // Regex para capturar configurações do veip
                final Pattern veipPattern = Pattern.compile(
                        "set ep sl (\\d+) p (\\d+) o (\\d+) p (\\d+) onuveip (\\d+) 33024 (\\d+) .*? 0 (\\d+) 65535");
                final Matcher veipMatcher = veipPattern.matcher(line);

                if (veipMatcher.find()) {
                    final String slot = veipMatcher.group(1); // Slot
                    final String pon = veipMatcher.group(2); // PON
                    final String onu = veipMatcher.group(3); // ONU
                    final String port = veipMatcher.group(4); // Port
                    final String onuVeipIndex = veipMatcher.group(5); // Index do onuVeip
                    final String vlan = veipMatcher.group(6); // VLAN usada
                    final String profile = veipMatcher.group(7); // Perfil associado

                    veipConfigs.add(new String[] { slot, pon, onu, port, onuVeipIndex, vlan, profile });
                }
            }

            // [0] Index do perfil
            // [1] Nome do perfil
            // [2] Tipo do perfil

            System.out.println("Perfis prf_mgr:");
            for (final String[] config : prfMgrConfigs) {
                System.out.println(Arrays.toString(config));
            }

            // [0] Slot
            // [1] PON
            // [2] ONU
            // [3] Port
            // [4] Index do onuVeip
            // [5] VLAN usada
            // [6] Perfil associado

            // System.out.println("\nConfigurações veip:");
            // for (final String[] config : veipConfigs) {
            //     System.out.println(Arrays.toString(config));
            // }

        } catch (final Exception e) {
            e.printStackTrace();
        }

    }
}
