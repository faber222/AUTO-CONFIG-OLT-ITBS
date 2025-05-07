package engtelecom.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataVlanUpFilter {
    private final String path;
    private final List<String[]> uplinkVlans;
    private final List<String[]> serviceVlans;
    private final List<String[]> trunkVlans; 

    public DataVlanUpFilter(final String path) {
        this.path = path;
        this.uplinkVlans = new ArrayList<>();
        this.serviceVlans = new ArrayList<>();
        this.trunkVlans = new ArrayList<>();
    }

    /**
     * [0] Slot
     * [1] Porta
     * [2] VLAN inicial
     * [3] VLAN final
     * 
     * @return
     */
    public List<String[]> getUplinkVlans() {
        return uplinkVlans;
    }

    /**
     * [0] ID da VLAN de serviço
     * [1] Nome do serviço
     * [2] Tipo de serviço
     * [3] VLAN inicial
     * [4] VLAN final
     * 
     * @return
     */
    public List<String[]> getServiceVlans() {
        return serviceVlans;
    }


    /**
     * [0] VLAN inicial
     * [1] VLAN final
     * [2] Número do trunk
     * 
     * @return
     */
    public List<String[]> getTrunkVlans() {
        return trunkVlans;
    }

    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;
            String currentServiceId = "", currentServiceName = "", currentServiceType = "";
            String currentVlanBegin = "", currentVlanEnd = "";

            while ((line = br.readLine()) != null) {
                // Regex para capturar VLANs de uplink
                Pattern uplinkPattern = Pattern.compile(
                        "add vlan vlan_begin (\\d+) vlan_end (\\d+) tag uplink slot (\\d+) port (\\d+)");
                Matcher uplinkMatcher = uplinkPattern.matcher(line);

                // Regex para capturar VLANs trunk 
                Pattern trunkPattern = Pattern.compile(
                        "add vlan vlan_begin (\\d+) vlan_end (\\d+) tag trunk (\\d+)");
                Matcher trunkMatcher = trunkPattern.matcher(line);

                if (uplinkMatcher.find()) {
                    String vlanBegin = uplinkMatcher.group(1); // Slot
                    String vlanEnd = uplinkMatcher.group(2); // Porta
                    String slot = uplinkMatcher.group(3); // VLAN inicial
                    String port = uplinkMatcher.group(4); // VLAN final

                    uplinkVlans.add(new String[] { slot, port, vlanBegin, vlanEnd });
                    continue;
                }
                
                if (trunkMatcher.find()) {
                    String vlanBegin = trunkMatcher.group(1);
                    String vlanEnd = trunkMatcher.group(2);
                    String trunkNumber = trunkMatcher.group(3);

                    trunkVlans.add(new String[] { vlanBegin, vlanEnd, trunkNumber });
                    continue;
                }

                // Regex para capturar criação de VLANs de serviço
                Pattern serviceCreatePattern = Pattern.compile("create service_vlan (\\d+)");
                Matcher serviceCreateMatcher = serviceCreatePattern.matcher(line);

                if (serviceCreateMatcher.find()) {
                    // Se já temos um serviço VLAN anterior, adicionamos ele na lista
                    if (!currentServiceId.isEmpty()) {
                        serviceVlans.add(new String[] { currentServiceId, currentServiceName, currentServiceType,
                                currentVlanBegin, currentVlanEnd });
                    }

                    // Inicia nova VLAN de serviço
                    currentServiceId = serviceCreateMatcher.group(1); // ID da VLAN de serviço
                    currentServiceName = "";
                    currentServiceType = "";
                    currentVlanBegin = "";
                    currentVlanEnd = "";
                    continue;
                }

                // Regex para capturar nome e tipo de VLAN de serviço
                Pattern serviceNameTypePattern = Pattern.compile("set service_vlan (\\d+) (\\S+) type (\\S+)");
                Matcher serviceNameTypeMatcher = serviceNameTypePattern.matcher(line);

                if (serviceNameTypeMatcher.find() && serviceNameTypeMatcher.group(1).equals(currentServiceId)) {
                    currentServiceName = serviceNameTypeMatcher.group(2); // Nome do serviço
                    currentServiceType = serviceNameTypeMatcher.group(3); // Tipo de serviço
                    continue;
                }

                // Regex para capturar VLANs associadas a uma VLAN de serviço
                Pattern serviceVlanPattern = Pattern
                        .compile("set service_vlan (\\d+) vlan_begin (\\d+) vlan_end (\\d+)");
                Matcher serviceVlanMatcher = serviceVlanPattern.matcher(line);

                if (serviceVlanMatcher.find() && serviceVlanMatcher.group(1).equals(currentServiceId)) {
                    currentVlanBegin = serviceVlanMatcher.group(2); // VLAN inicial
                    currentVlanEnd = serviceVlanMatcher.group(3); // VLAN final
                }
            }

            // Adiciona a última VLAN de serviço, se houver
            if (!currentServiceId.isEmpty()) {
                serviceVlans.add(new String[] { currentServiceId, currentServiceName, currentServiceType,
                        currentVlanBegin, currentVlanEnd });
            }

            // [0] Slot
            // [1] Porta
            // [2] VLAN inicial
            // [3] VLAN final

            // System.out.println("VLANs de Uplink:");
            // for (String[] entry : uplinkVlans) {
            // System.out.println(Arrays.toString(entry));
            // }

            // [0] ID da VLAN de serviço
            // [1] Nome do serviço
            // [2] Tipo de serviço
            // [3] VLAN inicial do serviço
            // [4] VLAN final do serviço

            // System.out.println("\nVLANs de Serviço:");
            // for (String[] entry : serviceVlans) {
            // System.out.println(Arrays.toString(entry));
            // }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
