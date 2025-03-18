package engtelecom.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataPortEthFilter {
    private final String path;
    private final List<String[]> ethConfigs;

    public DataPortEthFilter(final String path) {
        this.path = path;
        this.ethConfigs = new ArrayList<>();
    }

    /**
     * [0] Slot
     * [1] PON
     * [2] ONU
     * [3] Porta associada
     * [4] Index Serv Number
     * [5] VLAN Mode (tag ou tra)
     * [6] VLAN utilizada
     * 
     * @return retorna um ArrayList contendo todos os dados de configuração de port
     *         eth das cpes
     */
    public List<String[]> getEthConfigs() {
        return ethConfigs;
    }

    public void start() {

        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Regex para capturar as configurações de Ethernet, incluindo serv num
                final Pattern ethPattern = Pattern.compile(
                        "set ep sl (\\d+) p (\\d+) o ([\\d,-]+) p (\\d+) serv (\\d+) vlan_m (tag|tra) \\d+ 33024 ([\\d,]+)");
                final Matcher matcher = ethPattern.matcher(line);

                if (matcher.find()) {
                    final String slot = matcher.group(1); // Slot
                    final String pon = matcher.group(2); // PON
                    final String onuRange = matcher.group(3); // ONU
                    final String port = matcher.group(4); // Porta associada
                    final String servNum = matcher.group(5); // Número do serviço
                    final String vlanMode = matcher.group(6); // VLAN Mode (tag ou tra)
                    final String vlanList = matcher.group(7); // VLAN utilizada

                    // Expandir ranges de ONUs
                    final List<String> onuList = expandRange(onuRange);
                    final List<String> vlanValues = Arrays.asList(vlanList.split(","));

                    // Garantir que a quantidade de ONUs e VLANs seja a mesma
                    if (onuList.size() != vlanValues.size()) {
                        System.err.println("Erro: Quantidade de ONUs e VLANs não corresponde!");
                        continue;
                    }

                    // Associar cada ONU à VLAN correspondente
                    for (int i = 0; i < onuList.size(); i++) {
                        ethConfigs.add(new String[] {
                                slot, pon, onuList.get(i), port, servNum, vlanMode, vlanValues.get(i)
                        });
                    }
                }
            }

            // [0] Slot
            // [1] PON
            // [2] ONU
            // [3] Porta associada
            // [4] Número do serviço
            // [5] VLAN Mode (tag ou tra)
            // [6] VLAN utilizada

            System.out.println("Configurações de Ethernet:");
            for (final String[] config : ethConfigs) {
                System.out.println(Arrays.toString(config));
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Expande um range de números em uma lista completa.
     * Exemplo: "1-3,5,7-8" → ["1", "2", "3", "5", "7", "8"]
     */
    private List<String> expandRange(final String range) {
        final List<String> result = new ArrayList<>();
        final String[] parts = range.split(",");

        for (final String part : parts) {
            if (part.contains("-")) {
                final String[] limits = part.split("-");
                final int start = Integer.parseInt(limits[0]);
                final int end = Integer.parseInt(limits[1]);

                for (int i = start; i <= end; i++) {
                    result.add(String.valueOf(i));
                }
            } else {
                result.add(part);
            }
        }
        return result;
    }
}
