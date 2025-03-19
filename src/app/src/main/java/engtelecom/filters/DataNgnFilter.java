package engtelecom.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataNgnFilter {
    private final String path;
    private final List<String[]> ngnInterfaceConfigs;
    private final List<String[]> ngnUserConfigs;
    private final List<String[]> ngnKeepaliveConfigs;
    private final List<String[]> ngnRegParaConfigs;
    private final List<String[]> ngnVoiceServiceConfigs;
    private final List<String[]> ngnPrivateSubnetConfigs;
    private String ngnVoiceActivation;

    public DataNgnFilter(final String path) {
        this.path = path;
        this.ngnInterfaceConfigs = new ArrayList<>();
        this.ngnUserConfigs = new ArrayList<>();
        this.ngnKeepaliveConfigs = new ArrayList<>();
        this.ngnRegParaConfigs = new ArrayList<>();
        this.ngnVoiceServiceConfigs = new ArrayList<>();
        this.ngnPrivateSubnetConfigs = new ArrayList<>();
        this.ngnVoiceActivation = "";
    }

    /**
     * [0] Nome do serviço
     * [1] Protocolo
     * [2] MGC1
     * [3] Porta MGC1
     * [4] MGC2
     * [5] Porta MGC2
     * [6] DNS Primário
     * [7] DNS Secundário
     * [8] SIP Registrar
     * [9] SIP Proxy
     * 
     * @return ArrayList contendo todos os dados de config da interface voip
     */
    public List<String[]> getNgnInterfaceConfigs() {
        return ngnInterfaceConfigs;
    }

    /**
     * [0] Nome do serviço
     * [1] Vlan
     * [2] Modo de operacao
     * [3] Index
     * [4] Telefone
     * [5] Usuario
     * [6] Usuario sip
     * [7] Senha sip
     * 
     * @return ArrayList contendo todos os dados de configuração dos usuários sip
     */
    public List<String[]> getNgnUserConfigs() {
        return ngnUserConfigs;
    }

    /**
     * [0] Nome do serviço
     * [1] Intervalo Keepalive
     * [2] Tentativas Keepalive
     * 
     */
    public List<String[]> getNgnKeepaliveConfigs() {
        return ngnKeepaliveConfigs;
    }

    /**
     * [0] Nome do serviço
     * [1] Tempo de registro
     * [2] Intervalo de registro
     * 
     */
    public List<String[]> getNgnRegParaConfigs() {
        return ngnRegParaConfigs;
    }

    /**
     * [0] Slot
     * [1] PON
     * [2] ONU
     * [3] Porta
     * [4] Telefone
     * 
     * @return ArrayList contendo todos os dados de configuração da cpe com voip
     */
    public List<String[]> getNgnVoiceServiceConfigs() {
        return ngnVoiceServiceConfigs;
    }

    /**
     * [0] IP Privado
     * [1] Máscara de Subrede
     * [2] Índice
     * [3] Tipo
     * 
     * @return ArrayList contendo todos os dados das configuraçṍes de redes internas
     */
    public List<String[]> getNgnPrivateSubnetConfigs() {
        return ngnPrivateSubnetConfigs;
    }

    /**
     * 
     * @return
     */
    public String getNgnVoiceActivation() {
        return ngnVoiceActivation;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;

            String service = null, vlan = null, ipMode = null, userIn = null;
            while ((line = br.readLine()) != null) {
                // NGN Interface Config
                Pattern interfacePattern = Pattern.compile(
                        "set new_ngn_uplink_interface name (\\S+) protocol_type (\\S+) " +
                                "mgc 1 (\\S+) (\\d+) mgc 2 (\\S+) (\\d+) " +
                                "(?:keepalive \\S+ )?" +
                                "m_dns ipv4 (\\S+) s_dns ipv4 (\\S+) " +
                                "(?:dhcp \\S+ )?" +
                                "sip_reg_addr (\\S+) sip_proxy_addr (\\S+)");
                Matcher interfaceMatcher = interfacePattern.matcher(line);
                if (interfaceMatcher.find()) {
                    ngnInterfaceConfigs.add(new String[] {
                            interfaceMatcher.group(1), // Nome do serviço
                            interfaceMatcher.group(2), // Protocolo
                            interfaceMatcher.group(3), // MGC1
                            interfaceMatcher.group(4), // Porta MGC1
                            interfaceMatcher.group(5), // MGC2
                            interfaceMatcher.group(6), // Porta MGC2
                            interfaceMatcher.group(7), // DNS Primário
                            interfaceMatcher.group(8), // DNS Secundário
                            interfaceMatcher.group(9), // SIP Registrar
                            interfaceMatcher.group(10) // SIP Proxy
                    });
                    continue;
                }

                // Capturar `new_ngn_uplink_user`
                Pattern newUserPattern = Pattern.compile(
                        "set new_ngn_uplink_user serv (\\S+) vid (\\d+) ip_m (\\S+) .*? user_in (\\d+)");
                Matcher newUserMatcher = newUserPattern.matcher(line);
                if (newUserMatcher.find()) {
                    service = newUserMatcher.group(1); // Nome do serviço
                    vlan = newUserMatcher.group(2); // Vlan
                    ipMode = newUserMatcher.group(3); // Modo de operacao
                    userIn = newUserMatcher.group(4); // Index
                    continue;
                }

                // Capturar `ngn_uplink_user_port`
                String phone, username, sipUser, sipPassword;
                Pattern userPortPattern = Pattern.compile(
                        "set ngn_uplink_user_port phone (\\S+) usern (\\S+) sip_user_n (\\S+) sip_user_p (\\S+) user_in (\\d+)");
                Matcher userPortMatcher = userPortPattern.matcher(line);

                if (userPortMatcher.find()) {
                    phone = userPortMatcher.group(1); // Telefone
                    username = userPortMatcher.group(2); // Usuario
                    sipUser = userPortMatcher.group(3); // Usuario sip 
                    sipPassword = userPortMatcher.group(4); // Senha sip

                    // Adiciona os dados coletados à lista
                    ngnUserConfigs.add(new String[] {
                            service, vlan, ipMode, userIn, phone, username, sipUser, sipPassword
                    });

                    // Reseta as variáveis para a próxima iteração
                    service = vlan = ipMode = userIn = null;
                }

                // NGN Keepalive Config
                Pattern keepalivePattern = Pattern.compile(
                        "set ngn_keepalive servicename (\\S+) aliveinterval (\\d+) alivetimes (\\d+)");
                Matcher keepaliveMatcher = keepalivePattern.matcher(line);
                if (keepaliveMatcher.find()) {
                    ngnKeepaliveConfigs.add(new String[] {
                            keepaliveMatcher.group(1), // Nome do serviço
                            keepaliveMatcher.group(2), // Intervalo Keepalive
                            keepaliveMatcher.group(3) // Tentativas Keepalive
                    });
                    continue;
                }

                // NGN Voice Activation Type
                Pattern activationPattern = Pattern.compile("set ngn_activation_type (\\S+)");
                Matcher activationMatcher = activationPattern.matcher(line);
                if (activationMatcher.find()) {
                    ngnVoiceActivation = activationMatcher.group(1);
                    continue;
                }

                // NGN Reg Para Config
                Pattern regParaPattern = Pattern.compile(
                        "set ngn_reg_para name (\\S+) reg_time (\\d+) .*? reg_interval (\\d+)");
                Matcher regParaMatcher = regParaPattern.matcher(line);
                if (regParaMatcher.find()) {
                    ngnRegParaConfigs.add(new String[] {
                            regParaMatcher.group(1), // Nome do serviço
                            regParaMatcher.group(2), // Tempo de registro
                            regParaMatcher.group(3) // Intervalo de registro
                    });
                    continue;
                }

                // NGN Voice Service Config
                Pattern voiceServicePattern = Pattern.compile(
                        "set ngn_v sl (\\d+) p (\\d+) o (\\d+) p (\\d+) ph (\\d+)");
                Matcher voiceServiceMatcher = voiceServicePattern.matcher(line);
                if (voiceServiceMatcher.find()) {
                    ngnVoiceServiceConfigs.add(new String[] {
                            voiceServiceMatcher.group(1), // Slot
                            voiceServiceMatcher.group(2), // PON
                            voiceServiceMatcher.group(3), // ONU
                            voiceServiceMatcher.group(4), // Porta
                            voiceServiceMatcher.group(5) // Telefone
                    });
                    continue;
                }

                // NGN Private Subnet Config
                Pattern privateSubnetPattern = Pattern.compile(
                        "set ngn_private_subnet private_ip (\\S+) private_subnet (\\S+) index (\\d+) iptype (\\S+)");
                Matcher privateSubnetMatcher = privateSubnetPattern.matcher(line);
                if (privateSubnetMatcher.find()) {
                    ngnPrivateSubnetConfigs.add(new String[] {
                            privateSubnetMatcher.group(1), // IP Privado
                            privateSubnetMatcher.group(2), // Máscara de Subrede
                            privateSubnetMatcher.group(3), // Índice
                            privateSubnetMatcher.group(4) // Tipo
                    });
                }
            }

            // System.out.println("NGN Interface Config:");
            // ngnInterfaceConfigs.forEach(config -> System.out.println(Arrays.toString(config)));

            // System.out.println("\nNGN User Config:");
            // ngnUserConfigs.forEach(config -> System.out.println(Arrays.toString(config)));

            // System.out.println("\nNGN Keepalive Config:");
            // ngnKeepaliveConfigs.forEach(config -> System.out.println(Arrays.toString(config)));

            // System.out.println("\nNGN Reg Para Config:");
            // ngnRegParaConfigs.forEach(config -> System.out.println(Arrays.toString(config)));

            // System.out.println("\nNGN Voice Activation Type: " + ngnVoiceActivation);

            // System.out.println("\nNGN Voice Service Config:");
            // ngnVoiceServiceConfigs.forEach(config -> System.out.println(Arrays.toString(config)));

            // System.out.println("\nNGN Private Subnet Config:");
            // ngnPrivateSubnetConfigs.forEach(config -> System.out.println(Arrays.toString(config)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
