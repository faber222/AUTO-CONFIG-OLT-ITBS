package engtelecom.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataWanServicePPPFilter {
    private final String path;
    private final List<String[]> wanRouterConfigs; // Configs de "ty r"
    private final List<String[]> wanBridgeConfigs; // Configs de "ty b"
    private final List<String[]> wanStaticConfigs; // Configs de "disp sta"
    private final List<String[]> wanIpoeConfigs; // Configs de "disp dhcp"
    // private final List<String[]> wanIpv6Configs; // Configs de ipv6

    private static final Map<Character, Character> encodingMap = new HashMap<>();
    private static final Map<Character, Character> decodingMap = new HashMap<>();

    static {
        // Números
        encodingMap.put('1', 'm');
        encodingMap.put('2', 'l');
        encodingMap.put('3', 'k');
        encodingMap.put('4', 'j');
        encodingMap.put('5', 'i');
        encodingMap.put('6', 'h');
        encodingMap.put('7', 'g');
        encodingMap.put('8', 'f');
        encodingMap.put('9', 'e');
        encodingMap.put('0', 'n');

        // Letras minúsculas
        encodingMap.put('a', '=');
        encodingMap.put('b', '<');
        encodingMap.put('c', ';');
        encodingMap.put('d', ':');
        encodingMap.put('e', '9');
        encodingMap.put('f', '8');
        encodingMap.put('g', '7');
        encodingMap.put('h', '6');
        encodingMap.put('i', '5');
        encodingMap.put('j', '4');
        encodingMap.put('k', '3');
        encodingMap.put('l', '2');
        encodingMap.put('m', '1');
        encodingMap.put('n', '0');
        encodingMap.put('o', '/');
        encodingMap.put('p', '.');
        encodingMap.put('q', '-');
        encodingMap.put('r', ',');
        encodingMap.put('s', '+');
        encodingMap.put('t', '*');
        encodingMap.put('u', ')');
        encodingMap.put('v', '(');
        encodingMap.put('w', '\'');
        encodingMap.put('x', '&');
        encodingMap.put('y', '%');
        encodingMap.put('z', '$');

        // Letras maiúsculas
        encodingMap.put('A', ']');
        encodingMap.put('B', '\\');
        encodingMap.put('C', '[');
        encodingMap.put('D', 'Z');
        encodingMap.put('E', 'Y');
        encodingMap.put('F', 'X');
        encodingMap.put('G', 'W');
        encodingMap.put('H', 'V');
        encodingMap.put('I', 'U');
        encodingMap.put('J', 'T');
        encodingMap.put('K', 'S');
        encodingMap.put('L', 'R');
        encodingMap.put('M', 'Q');
        encodingMap.put('N', 'P');
        encodingMap.put('O', 'O');
        encodingMap.put('P', 'N');
        encodingMap.put('Q', 'M');
        encodingMap.put('R', 'L');
        encodingMap.put('S', 'K');
        encodingMap.put('T', 'J');
        encodingMap.put('U', 'I');
        encodingMap.put('V', 'H');
        encodingMap.put('W', 'G');
        encodingMap.put('X', 'F');
        encodingMap.put('Y', 'E');
        encodingMap.put('Z', 'D');

        // Caracteres especiais
        encodingMap.put('!', '}');
        encodingMap.put('@', '^');
        encodingMap.put('#', '{');
        encodingMap.put('$', 'z');
        encodingMap.put('%', 'y');
        encodingMap.put('&', 'x');
        encodingMap.put('*', 't');
        encodingMap.put('(', 'v');
        encodingMap.put(')', 'u');
        encodingMap.put('-', 'q');
        encodingMap.put('_', '?');
        encodingMap.put('+', 's');
        encodingMap.put('=', 'a');
        encodingMap.put('^', '@');
        encodingMap.put('~', ' ');
        encodingMap.put('<', 'b');
        encodingMap.put('>', '`');
        encodingMap.put(',', 'r');
        encodingMap.put('.', 'p');
        encodingMap.put(';', 'c');
        encodingMap.put(':', 'd');
        encodingMap.put('/', 'o');
        encodingMap.put('?', '_');
        encodingMap.put('[', 'C');
        encodingMap.put(']', 'A');
        encodingMap.put('{', '#');
        encodingMap.put('}', '!');
        encodingMap.put('\\', 'B');
        encodingMap.put('|', '"');
        encodingMap.put('"', '|');
        encodingMap.put('\'', 'w');

        // Criar o mapa de decodificação
        for (Map.Entry<Character, Character> entry : encodingMap.entrySet()) {
            decodingMap.put(entry.getValue(), entry.getKey());
        }
    }

    public static String decode(String key) {
        StringBuilder result = new StringBuilder();
        for (char c : key.toCharArray()) {
            result.append(decodingMap.getOrDefault(c, c));
        }
        return result.toString();
    }

    public DataWanServicePPPFilter(final String path) {
        this.path = path;
        this.wanRouterConfigs = new ArrayList<>();
        this.wanBridgeConfigs = new ArrayList<>();
        this.wanStaticConfigs = new ArrayList<>();
        this.wanIpoeConfigs = new ArrayList<>();
        // this.wanIpv6Configs = new ArrayList<>();
    }

    // /**
    // * [0] slot
    // * [1] pon
    // * [2] onu
    // * [3] index
    // * [4] stackMode
    // * [5] ipv6Type
    // * [6] prefixType
    // *
    // * @return List com as configurações do tipo de ipv6
    // */
    // public List<String[]> getWanIpv6Configs() {
    // return wanIpv6Configs;
    // }

    /**
     * [0] slot
     * [1] pon
     * [2] onu
     * [3] index
     * [4] mode
     * [5] type
     * [6] vlan
     * [7] nat
     * [8] IP
     * [9] MASK
     * [10] GATEWAY
     * [11] DNS Master
     * [12] DNS Slave
     * [13] stackMode
     * [14] ipv6Type
     * [15] prefixType
     * 
     * @return Lista com configurações do tipo static
     */
    public List<String[]> getWanStaticConfigs() {
        return wanStaticConfigs;
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
     * [8] stackMode
     * [9] ipv6Type
     * [10] prefixType
     * 
     * @return Lista com configurações do tipo "r" (router) ipoe
     */
    public List<String[]> getWanIpoeConfigs() {
        return wanIpoeConfigs;
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
     * [11] stackMode
     * [12] ipv6Type
     * [13] prefixType
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
     * [11] stackMode
     * [12] ipv6Type
     * [13] prefixType
     * 
     * @return Lista com configurações do tipo "b" (bridge)
     */
    public List<String[]> getWanBridgeConfigs() {
        return wanBridgeConfigs;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void start() {
        Map<String, String[]> ipv6Map = new HashMap<>();
        Pattern ipv6Pattern = Pattern.compile(
                "set wancfg sl (\\d+) (\\d+) (\\d+) ind (\\d+) ip-stack-mode (\\S+) ipv6-src-type (\\S+) prefix-src-type (\\S+)");

        // 1ª leitura: coletar IPv6
        try (BufferedReader br1 = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = br1.readLine()) != null) {
                Matcher ipv6Matcher = ipv6Pattern.matcher(line);
                if (ipv6Matcher.find()) {
                    String key = ipv6Matcher.group(1) + ":" + ipv6Matcher.group(2) + ":" +
                            ipv6Matcher.group(3) + ":" + ipv6Matcher.group(4);
                    ipv6Map.put(key, new String[] {
                            ipv6Matcher.group(5), ipv6Matcher.group(6), ipv6Matcher.group(7)
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

                    String key = slot + ":" + pon + ":" + onu + ":" + ind;
                    String[] ipv6 = ipv6Map.getOrDefault(key, new String[] { "N/A", "N/A", "N/A" });

                    final String decriptoPass = decode(pass);
                    wanRouterConfigs
                            .add(new String[] { slot, pon, onu, ind, mode, tipo, vlan, nat, pppoe, user,
                                    decriptoPass, ipv6[0], ipv6[1], ipv6[2] });
                    continue;
                }

                // Regex para capturar configurações do tipo "r" (router) ipoe
                Pattern ipoePattern = Pattern.compile(
                        "set wancfg sl (\\d+) (\\d+) (\\d+) ind (\\d+) mode (\\S+) ty r (\\d+) 0 nat (\\S+).*? dsp dhcp ?");
                Matcher ipoeMatcher = ipoePattern.matcher(line);

                if (ipoeMatcher.find()) {
                    final String slot = ipoeMatcher.group(1);
                    final String pon = ipoeMatcher.group(2);
                    final String onu = ipoeMatcher.group(3);
                    final String ind = ipoeMatcher.group(4);
                    final String mode = ipoeMatcher.group(5);
                    final String tipo = "r"; // Tipo router
                    final String vlan = ipoeMatcher.group(6);
                    final String nat = ipoeMatcher.group(7);

                    String key = slot + ":" + pon + ":" + onu + ":" + ind;
                    String[] ipv6 = ipv6Map.getOrDefault(key, new String[] { "N/A", "N/A", "N/A" });

                    wanIpoeConfigs
                            .add(new String[] { slot, pon, onu, ind, mode, tipo, vlan, nat, ipv6[0], ipv6[1],
                                    ipv6[2] });
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

                    String key = slot + ":" + pon + ":" + onu + ":" + ind;
                    String[] ipv6 = ipv6Map.getOrDefault(key, new String[] { "N/A", "N/A", "N/A" });

                    wanBridgeConfigs.add(new String[] { slot, pon, onu, ind, mode, tipo, vlan, nat, vlanMode, tvlan,
                            tvid, ipv6[0], ipv6[1], ipv6[2] });
                }

                Pattern staticPattern = Pattern.compile(
                        "set wancfg sl (\\d+) (\\d+) (\\d+) ind (\\d+) mode (\\S+) ty r (\\d+) \\d+ nat (\\S+).*? dsp sta ip ([\\d.]+) mask ([\\d.]+) gate ([\\d.]+) mas ([\\d.]+) sla ([\\d.]+)");
                Matcher staticMatcher = staticPattern.matcher(line);

                if (staticMatcher.find()) {
                    final String slot = staticMatcher.group(1);
                    final String pon = staticMatcher.group(2);
                    final String onu = staticMatcher.group(3);
                    final String ind = staticMatcher.group(4);
                    final String mod = staticMatcher.group(5);
                    final String type = "r";
                    final String vlan = staticMatcher.group(6);
                    final String nat = staticMatcher.group(7);
                    final String ip = staticMatcher.group(8);
                    final String mask = staticMatcher.group(9);
                    final String gate = staticMatcher.group(10);
                    final String dns = staticMatcher.group(11);
                    final String sla = staticMatcher.group(12);

                    String key = slot + ":" + pon + ":" + onu + ":" + ind;
                    String[] ipv6 = ipv6Map.getOrDefault(key, new String[] { "N/A", "N/A", "N/A" });

                    wanStaticConfigs.add(new String[] {
                            slot, pon, onu, ind, mod, type, vlan, nat, ip, mask, gate, dns, sla,
                            ipv6[0], ipv6[1], ipv6[2]
                    });
                }

                // Pattern ipv6Pattern = Pattern.compile(
                // "set wancfg sl (\\d+) (\\d+) (\\d+) ind (\\d+) ip-stack-mode (\\S+)
                // ipv6-src-type (\\S+) prefix-src-type (\\S+)");
                // Matcher ipv6Matcher = ipv6Pattern.matcher(line);

                // if (ipv6Matcher.find()) {
                // final String slot = ipv6Matcher.group(1);
                // final String pon = ipv6Matcher.group(2);
                // final String onu = ipv6Matcher.group(3);
                // final String ind = ipv6Matcher.group(4);
                // final String stackMode = ipv6Matcher.group(5);
                // final String ipv6SrcType = ipv6Matcher.group(6);
                // final String prefixSrcType = ipv6Matcher.group(7);

                // wanIpv6Configs.add(new String[] {
                // slot, pon, onu, ind, stackMode, ipv6SrcType, prefixSrcType
                // });
                // }
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
