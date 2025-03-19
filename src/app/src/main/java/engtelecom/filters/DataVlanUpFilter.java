package engtelecom.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataVlanUpFilter {
    private final String path;

    // Map para armazenar VLANs de uplink: (slot-port) -> [vlans]
    private final Map<String, List<Integer>> uplinkVlans;

    // Map para armazenar VLANs de serviço: (id) -> [nome, tipo, vlans]
    private final Map<Integer, String[]> serviceVlans;

    public DataVlanUpFilter(final String path) {
        this.path = path;
        this.uplinkVlans = new HashMap<>();
        this.serviceVlans = new HashMap<>();
    }

    /**
     * Map para armazenar VLANs de uplink: (slot-port) -> [vlans]
     * 
     */
    public Map<String, List<Integer>> getUplinkVlans() {
        return uplinkVlans;
    }

    /**
     * // Map para armazenar VLANs de serviço: (id) -> [nome, tipo, vlans]
     * 
     */
    public Map<Integer, String[]> getServiceVlans() {
        return serviceVlans;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;
            Integer currentServiceVlan;

            while ((line = br.readLine()) != null) {
                // Regex para capturar VLANs de uplink
                final Pattern uplinkPattern = Pattern.compile(
                        "add vlan vlan_begin (\\d+) vlan_end (\\d+) tag uplink slot (\\d+) port (\\d+)");
                final Matcher uplinkMatcher = uplinkPattern.matcher(line);

                if (uplinkMatcher.find()) {
                    final int vlanStart = Integer.parseInt(uplinkMatcher.group(1));
                    final int vlanEnd = Integer.parseInt(uplinkMatcher.group(2));
                    final String slotPort = uplinkMatcher.group(3) + "-" + uplinkMatcher.group(4);

                    // Adiciona VLANs ao slot-port correspondente
                    uplinkVlans.putIfAbsent(slotPort, new ArrayList<>());
                    for (int vlan = vlanStart; vlan <= vlanEnd; vlan++) {
                        uplinkVlans.get(slotPort).add(vlan);
                    }
                    continue;
                }

                // Regex para capturar criação de VLANs de serviço
                final Pattern serviceCreatePattern = Pattern.compile("create service_vlan (\\d+)");
                final Matcher serviceCreateMatcher = serviceCreatePattern.matcher(line);

                if (serviceCreateMatcher.find()) {
                    currentServiceVlan = Integer.valueOf(serviceCreateMatcher.group(1));
                    serviceVlans.put(currentServiceVlan, new String[] { "", "", "" });
                    continue;
                }

                // Regex para capturar nome e tipo de VLAN de serviço
                final Pattern serviceNameTypePattern = Pattern.compile("set service_vlan (\\d+) (\\S+) type (\\S+)");
                final Matcher serviceNameTypeMatcher = serviceNameTypePattern.matcher(line);

                if (serviceNameTypeMatcher.find()) {
                    final int vlanId = Integer.parseInt(serviceNameTypeMatcher.group(1));
                    final String name = serviceNameTypeMatcher.group(2);
                    final String type = serviceNameTypeMatcher.group(3);

                    if (serviceVlans.containsKey(vlanId)) {
                        serviceVlans.get(vlanId)[0] = name;
                        serviceVlans.get(vlanId)[1] = type;
                    }
                    continue;
                }

                // Regex para capturar VLANs associadas a uma VLAN de serviço
                final Pattern serviceVlanPattern = Pattern
                        .compile("set service_vlan (\\d+) vlan_begin (\\d+) vlan_end (\\d+)");
                final Matcher serviceVlanMatcher = serviceVlanPattern.matcher(line);

                if (serviceVlanMatcher.find()) {
                    final int vlanId = Integer.parseInt(serviceVlanMatcher.group(1));
                    final int vlanStart = Integer.parseInt(serviceVlanMatcher.group(2));
                    final int vlanEnd = Integer.parseInt(serviceVlanMatcher.group(3));

                    if (serviceVlans.containsKey(vlanId)) {
                        final List<Integer> vlans = new ArrayList<>();
                        for (int vlan = vlanStart; vlan <= vlanEnd; vlan++) {
                            vlans.add(vlan);
                        }
                        serviceVlans.get(vlanId)[2] = vlans.toString().replaceAll("[\\[\\]]", "");
                    }
                }
            }

            // Exibir os resultados
            System.out.println("VLANs de Uplink:");
            uplinkVlans.forEach((key, value) -> System.out.println("Slot-Port: " + key + " VLANs: " + value));

            System.out.println("\nVLANs de Serviço:");
            serviceVlans.forEach((key, value) -> System.out
                    .println("ID: " + key + ", Nome: " + value[0] + ", Tipo: " + value[1] + ", VLANs: " + value[2]));

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }
}
