/**
 * @author faber222
 * @since 2024
*/
package engtelecom.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import engtelecom.scripts.Scripts8820Plus;

/**
 * Classe responsável por gerar configurações e scripts com base nos parâmetros fornecidos.
 * Estende a classe Config8820Plus para herdar as configurações básicas.
 */
public class ConfigGenerator8820Plus extends Config {
    private final String ponVlanType;
    private final String[] vlanType;
    private final String defaultCpe;
    private final String[] interfaceGpon;
    private final String[] defaultCpeType;
    private final String[] uplinkType;
    private final String bridgeInterfaceUplink;
    private final String bridgeInterfaceUplinkVlanMode;

    /**
     * Construtor da classe ConfigGenerator8820Plus.
     *
     * @param vlans                         Lista de VLANs.
     * @param interfaceEthernet             Nome da interface Ethernet.
     * @param deviceType                    Lista de tipos de dispositivo.
     * @param modelConfiguration            Tipo de VLAN da PON.
     * @param vlanType                      Lista de tipos de VLAN.
     * @param defaultCpe                    Tipo padrão da CPE.
     * @param interfaceGpon                 Lista de interfaces GPON.
     * @param defaultCpeType                Lista de tipos padrão de CPE.
     * @param bridgeInterfaceUplink         Interface de uplink para a ponte.
     * @param bridgeInterfaceUplinkVlanMode Modo de VLAN para a interface de uplink
     *                                      da ponte.
     * @param uplinkType                    Se e TLS ou DOWNLINK
     */
    public ConfigGenerator8820Plus(final List<String> vlans, final String interfaceEthernet,
            final String[] deviceType, final String modelConfiguration,
            final String[] vlanType, final String defaultCpe, final String[] interfaceGpon,
            final String[] defaultCpeType, final String bridgeInterfaceUplink,
            final String bridgeInterfaceUplinkVlanMode, final String[] uplinkType) {
        super(vlans, interfaceEthernet, deviceType);
        this.ponVlanType = modelConfiguration;
        this.vlanType = vlanType;
        this.defaultCpe = defaultCpe;
        this.interfaceGpon = interfaceGpon;
        this.defaultCpeType = defaultCpeType;
        this.bridgeInterfaceUplink = bridgeInterfaceUplink;
        this.bridgeInterfaceUplinkVlanMode = bridgeInterfaceUplinkVlanMode;
        this.uplinkType = uplinkType;
    }

    /**
     * Obtém a interface de uplink.
     *
     * @return A interface de uplink.
     */
    public String[] getUplinkType() {
        return this.uplinkType;
    }

    /**
     * Obtém a interface de uplink da ponte.
     *
     * @return A interface de uplink da ponte.
     */
    public String getBridgeInterfaceUplink() {
        return bridgeInterfaceUplink;
    }

    /**
     * Obtém o modo de VLAN para a interface de uplink da ponte.
     *
     * @return O modo de VLAN para a interface de uplink da ponte.
     */
    public String getBridgeInterfaceUplinkVlanMode() {
        return bridgeInterfaceUplinkVlanMode;
    }

    /**
     * Obtém o tipo de VLAN escolhida.
     *
     * @return Array com o tipo de VLAN escolhida.
     */
    public String[] getVlanType() {
        return vlanType;
    }

    /**
     * Obtém o tipo de VLAN da PON.
     *
     * @return O tipo de VLAN da PON.
     */
    public String getPonVlanType() {
        return ponVlanType;
    }

    /**
     * Obtém a lista de interfaces GPON da OLT.
     *
     * @return A lista de interfaces GPON.
     */
    public String[] getInterfaceGpon() {
        return interfaceGpon;
    }

    /**
     * Obtém o tipo padrão da CPE.
     *
     * @return O tipo padrão da CPE.
     */
    public String getDefaultCpe() {
        return defaultCpe;
    }

    /**
     * Obtém a lista de tipos padrão de CPE.
     *
     * @return A lista de tipos padrão de CPE.
     */
    public String[] getDefaultCpeType() {
        return defaultCpeType;
    }

    /**
     * Método para escrever o script em um arquivo.
     *
     * @param script               Arquivo de script.
     * @param autoConfig           Lista de comandos de autoconfiguração.
     * @param bridgeUplink         Lista de comandos para configurar a interface de uplink da bridge.
     * @param bridgeProfile        Lista de comandos para configurar o perfil da bridge.
     * @param bridgeProfileRouter  Lista de comandos para configurar o perfil do roteador da bridge.
     * @param bridgeProfileBind    Lista de comandos para vincular o perfil da bridge.
     * @param bridgeProfileBindRouter Lista de comandos para vincular o perfil do roteador da bridge.
     * @return true se a escrita for bem-sucedida, false caso contrário.
     */
    public boolean writeScript(final File script, List<String> autoConfig, List<String> bridgeUplink,
            List<String> bridgeProfile, List<String> bridgeProfileRouter, List<String> bridgeProfileBind,
            List<String> bridgeProfileBindRouter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(script))) {
            for (final String uplink : bridgeUplink) {
                writer.write(uplink);
                writer.newLine();
            }

            writer.newLine();

            for (final String bridge : bridgeProfile) {
                writer.write(bridge);
                writer.newLine();
            }

            writer.newLine();

            for (final String bridgeRouter : bridgeProfileRouter) {
                writer.write(bridgeRouter);
                writer.newLine();
            }

            writer.newLine();

            for (final String bind : bridgeProfileBind) {
                writer.write(bind);
                writer.newLine();
            }

            writer.newLine();

            for (final String bindRouter : bridgeProfileBindRouter) {
                writer.write(bindRouter);
                writer.newLine();
            }

            writer.newLine();

            for (final String ontAutoConfig : autoConfig) {
                writer.write(ontAutoConfig);
                writer.newLine();
            }

            writer.newLine();

            return true;
        } catch (final IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Cria um script com base nos parâmetros fornecidos.
     *
     * @param nomeArq Nome do arquivo para o script.
     * @return true se a criação do script for bem-sucedida, false caso contrário.
     */
    public boolean createScript(String nomeArq) {
        // Caminho do novo script
        final File newScript = new File(nomeArq);

        // Instância da classe Scripts para obter scripts pré-definidos
        final Scripts8820Plus oltGpon = new Scripts8820Plus();

        // Obtenção de listas de scripts pré-definidos
        final List<String> autoConfig = oltGpon.autoConfig();

        List<String> bridgeUplink = new ArrayList<>();
        List<String> bridgeProfile = new ArrayList<>();
        List<String> bridgeProfileRouter = new ArrayList<>();
        List<String> bridgeProfileBind = new ArrayList<>();
        List<String> bridgeProfileBindRouter = new ArrayList<>();

        if (getPonVlanType().equals(getVlanType()[1])) {
            // coloca a bridge uplink da olt
            bridgeUplink.add(oltGpon.bridgeUplink(getVlans().get(0),
                    getInterfaceEthernet(), getBridgeInterfaceUplink(), getBridgeInterfaceUplinkVlanMode()));
            if (getBridgeInterfaceUplink().equals(getUplinkType()[0])) {
                // coloca tls no bridge profile
                bridgeProfile.add(oltGpon.bridgeProfile(getVlans().get(0), getBridgeInterfaceUplink()));
                bridgeProfileRouter.add(oltGpon.bridgeProfileRouter(getVlans().get(0), getBridgeInterfaceUplink()));
            } else {
                // coloca downlink no bridge profile
                bridgeProfile.add(oltGpon.bridgeProfile(getVlans().get(0), getUplinkType()[1]));
                bridgeProfileRouter.add(oltGpon.bridgeProfileRouter(getVlans().get(0), getUplinkType()[1]));
            }

            for (int i = 0; i < getDeviceType().length - 1; i++) {
                if (i <= 3) {
                    // define o bridge-profile bind default em bridge
                    bridgeProfileBind.add(oltGpon.bridgeProfileBind(getDeviceType()[i]));
                } else {
                    // define o bridge-profile bind default em router
                    bridgeProfileBindRouter.add(oltGpon.bridgeProfileBindRouter(getDeviceType()[i]));
                }
            }
            if (getDefaultCpe().equals(getDefaultCpeType()[0])) {
                // define o bridge profile bind em modo bridge
                bridgeProfileBind.add(oltGpon.bridgeProfileBind(getDeviceType()[getDeviceType().length - 1]));
            } else {
                // define o bridge profile bind em modo router
                bridgeProfileBindRouter
                        .add(oltGpon.bridgeProfileBindRouter(getDeviceType()[getDeviceType().length - 1]));
            }
        } else {
            int j = 0;
            for (String vlans : getVlans()) {
                // coloca a bridge uplink da olt
                bridgeUplink.add(oltGpon.bridgeUplink(vlans, getInterfaceEthernet(), getBridgeInterfaceUplink(),
                        getBridgeInterfaceUplinkVlanMode()));

                if (getBridgeInterfaceUplink().equals(getUplinkType()[0])) {
                    // coloca tls no bridge profile
                    bridgeProfile.add(oltGpon.bridgeProfile(vlans, Integer.toString(j + 1),
                            getBridgeInterfaceUplink()));
                    bridgeProfileRouter.add(oltGpon.bridgeProfileRouter(vlans, Integer.toString(j + 1),
                            getBridgeInterfaceUplink()));
                } else {
                    // coloca downlink no bridge profile
                    bridgeProfile.add(oltGpon.bridgeProfile(vlans, Integer.toString(j + 1), getUplinkType()[1]));
                    bridgeProfileRouter.add(oltGpon.bridgeProfileRouter(vlans, Integer.toString(j + 1),
                            getUplinkType()[1]));
                }
                for (int i = 0; i < getDeviceType().length - 1; i++) {
                    if (i <= 3) {
                        // define o bridge profile bind em modo bridge
                        bridgeProfileBind.add(oltGpon.bridgeProfileBind(Integer.toString(j + 1), getDeviceType()[i],
                                getInterfaceGpon()[j]));
                    } else {
                        // define o bridge profile bind em modo router
                        bridgeProfileBindRouter.add(oltGpon.bridgeProfileBindRouter(Integer.toString(j + 1),
                                getDeviceType()[i], getInterfaceGpon()[j]));
                    }
                }
                if (getDefaultCpe().equals(getDefaultCpeType()[0])) {
                    // define o bridge-profile bind default em bridge
                    bridgeProfileBind.add(oltGpon.bridgeProfileBind(Integer.toString(j + 1),
                            getDeviceType()[getDeviceType().length - 1], getInterfaceGpon()[j]));
                } else {
                    // define o bridge-profile bind default em router
                    bridgeProfileBindRouter
                            .add(oltGpon.bridgeProfileBindRouter(Integer.toString(j + 1),
                                    getDeviceType()[getDeviceType().length - 1], getInterfaceGpon()[j]));
                }
                j++;
            }
        }

        // Se o código chegar aqui, significa que a escrita foi bem-sucedida
        return writeScript(newScript, autoConfig, bridgeUplink, bridgeProfile, bridgeProfileRouter, bridgeProfileBind,
                bridgeProfileBindRouter);
    }

    /**
     * Obtém o tipo de dispositivo.
     *
     * @return Array com os tipos de dispositivo.
     */
    @Override
    public String[] getDeviceType() {
        return super.getDeviceType();
    }

    /**
     * Obtém a lista de VLANs.
     *
     * @return Lista de VLANs.
     */
    @Override
    public List<String> getVlans() {
        return super.getVlans();
    }

    /**
     * Define a lista de VLANs.
     *
     * @param vlans Lista de VLANs.
     */
    @Override
    public void setVlans(final List<String> vlans) {
        super.setVlans(vlans);
    }

    /**
     * Obtém o nome da interface Ethernet.
     *
     * @return Nome da interface Ethernet.
     */
    @Override
    public String getInterfaceEthernet() {
        return super.getInterfaceEthernet();
    }

    /**
     * Define o nome da interface Ethernet.
     *
     * @param interfaceEthernet Nome da interface Ethernet.
     */
    @Override
    public void setInterfaceEthernet(final String interfaceEthernet) {
        super.setInterfaceEthernet(interfaceEthernet);
    }
}
