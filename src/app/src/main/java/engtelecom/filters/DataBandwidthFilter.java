package engtelecom.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBandwidthFilter {
    private final String path;
    private final List<String[]> bandwidthConfigs;

    public DataBandwidthFilter(final String path) {
        this.path = path;
        this.bandwidthConfigs = new ArrayList<>();
    }

    /**
     * [0] Slot
     * [1] PON
     * [2] ONU
     * [3] Tipo (iptv, data, voip)
     * [4] Fix
     * [5] Assured
     * [6] Max
     * 
     * @return ArrayList contendo todas as configurações de banda
     */
    public List<String[]> getBandwidthConfigs() {
        return bandwidthConfigs;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void start() {

        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Regex para capturar as configurações de largura de banda
                final Pattern bwPattern = Pattern.compile(
                        "set service_ba sl (\\d+) p (\\d+) o (\\d+) ty (\\S+) fix (\\d+) as (\\d+) max (\\d+)");
                final Matcher matcher = bwPattern.matcher(line);

                if (matcher.find()) { 
                    final String slot = matcher.group(1); // Slot
                    final String pon = matcher.group(2); // PON
                    final String onu = matcher.group(3); // ONU
                    final String type = matcher.group(4); // Tipo (iptv, data, voip)
                    final String fix = matcher.group(5); // Fix
                    final String assured = matcher.group(6); // Assured
                    final String max = matcher.group(7); // Max

                    bandwidthConfigs.add(new String[] { slot, pon, onu, type, fix, assured, max });
                }
            }

            // [0] Slot
            // [1] PON
            // [2] ONU
            // [3] Tipo (iptv, data, voi)
            // [4] Fix
            // [5] Assured
            // [6] Max

            System.out.println("Configurações de largura de banda:");
            for (final String[] config : bandwidthConfigs) {
                System.out.println(Arrays.toString(config));
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }

    }
}
