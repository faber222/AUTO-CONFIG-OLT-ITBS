package engtelecom.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataWanServicePPPFilter {
    private final String path;

    private final List<String[]> wanConfigs;

    public DataWanServicePPPFilter(final String path) {
        this.path = path;
        this.wanConfigs = new ArrayList<>();
    }

    /**
     * [0] slot
     * [1] pon
     * [2] onu
     * [3] index
     * [4] Mode
     * [5] Tipo (r/b)
     * [6] VLAN
     * [7] NAT (en/dis)
     * [8] PPPoE (pppoe/null)
     * [9] Usuário PPPoE (se existir)
     * [10] Senha PPPoE (se existir)
     * 
     * @return ArrayList contendo todos os dados de wanService
     */
    public List<String[]> getWanConfigs() {
        return wanConfigs;
    }

    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Regex para capturar todas as informações desejadas
                final Pattern wanPattern = Pattern.compile(
                        "set wancfg sl (\\d+) (\\d+) (\\d+) ind (\\d+) mode (\\S+) ty (r|b) (\\d+) \\d+ nat (\\S+).*? dsp (pppoe|null)(?: pro dis (\\S+) key:([^\\s]+))?");
                final Matcher matcher = wanPattern.matcher(line);

                if (matcher.find()) {
                    final String slot = matcher.group(1); // slot
                    final String pon = matcher.group(2); // pon
                    final String onu = matcher.group(3); // onu
                    final String ind = matcher.group(4); // Índice (ind)
                    final String mode = matcher.group(5); // Mode
                    final String tipo = matcher.group(6); // Tipo (r/b)
                    final String vlan = matcher.group(7); // VLAN
                    final String nat = matcher.group(8); // NAT (en/dis)
                    final String pppoe = matcher.group(9); // PPPoE (pppoe/null)
                    final String user = matcher.group(10) != null ? matcher.group(10) : "N/A"; // Usuário PPPoE (se
                                                                                               // existir)
                    final String pass = matcher.group(11) != null ? matcher.group(11) : "N/A"; // Senha PPPoE (se
                                                                                               // existir)

                    // [0] slot
                    // [1] pon
                    // [2] onu
                    // [3] Índice (ind)
                    // [4] Mode
                    // [5] Tipo (r/b)
                    // [6] VLAN
                    // [7] NAT (en/dis)
                    // [8] PPPoE (pppoe/null)
                    // [9] Usuário PPPoE (se existir)
                    // [10] Senha PPPoE (se existir)

                    // Adiciona os dados como array de strings na lista
                    wanConfigs.add(new String[] { slot, pon, onu, ind, mode, tipo, vlan, nat, pppoe, user, pass });
                }
            }

            // Exibir os resultados (opcional, apenas para conferência)
            System.out.println("Configurações WAN:");
            for (final String[] config : wanConfigs) {
                System.out.println(Arrays.toString(config));
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
