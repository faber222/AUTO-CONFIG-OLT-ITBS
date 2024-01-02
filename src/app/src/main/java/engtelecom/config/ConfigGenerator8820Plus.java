package engtelecom.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import engtelecom.scripts.Scripts8820Plus;

/**
 * Classe responsável por gerar configurações e scripts com base nos parâmetros
 * fornecidos.
 * Estende a classe Config para herdar as configurações básicas.
 */
public class ConfigGenerator8820Plus extends Config8820Plus {
    private final String ponVlanType;
    private final String[] vlanType;
    private final String defaultCpe;
    private final String[] interfaceGpon;
    private final String[] defaultCpeType;
    private final String bridgeInterfaceUplink;
    private final String bridgeInterfaceUplinkVlanMode;

    /**
     * Construtor da classe ConfigGenerator.
     *
     * @param vlans             Lista de VLANs a serem configuradas.
     * @param interfaceEthernet Nome da interface Ethernet.
     * @param deviceType        Array que contem os devices de ont
     * @param ponVlanType       Palavra escolhida que define o tipo de config.
     * @param vlanType          Array de comparacao do tipo de config.
     * @param defaultCpe        String que define se é Router ou Bridge
     * @param interfaceGpon     Array com as interfaces gpon que a olt tem
     * @param defaultCpeType    Array contendo o default bridge ou router
     */
    public ConfigGenerator8820Plus(final List<String> vlans, final String interfaceEthernet,
            final String[] deviceType, final String ponVlanType,
            final String[] vlanType, final String defaultCpe, final String[] interfaceGpon,
            final String[] defaultCpeType, final String bridgeInterfaceUplink,
            final String bridgeInterfaceUplinkVlanMode) {
        super(vlans, interfaceEthernet, deviceType);
        this.ponVlanType = ponVlanType;
        this.vlanType = vlanType;
        this.defaultCpe = defaultCpe;
        this.interfaceGpon = interfaceGpon;
        this.defaultCpeType = defaultCpeType;
        this.bridgeInterfaceUplink = bridgeInterfaceUplink;
        this.bridgeInterfaceUplinkVlanMode = bridgeInterfaceUplinkVlanMode;
    }

    public String getBridgeInterfaceUplink() {
        return bridgeInterfaceUplink;
    }

    public String getBridgeInterfaceUplinkVlanMode() {
        return bridgeInterfaceUplinkVlanMode;
    }

    /**
     * Obtem o tipo de config escolhida
     * 
     * @return Array da config escolhida
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
     * Obtém a interafce gpon da olt.
     *
     * @return O numero da pon.
     */
    public String[] getInterfaceGpon() {
        return interfaceGpon;
    }

    /**
     * Obtém o valor default da cpe de terceiros
     * 
     * @return O tipo da cpe de terceiros {bridge ou router}
     */
    public String getDefaultCpe() {
        return defaultCpe;
    }

    /**
     * Obtém a lista de escolha de bridge ou router
     * 
     * @return Ou bridge ou router
     */
    public String[] getDefaultCpeType() {
        return defaultCpeType;
    }

    /**
     * Escreve o script de configuração em um arquivo.
     *
     * @param script            Arquivo onde o script será gravado.
     * @param accessLevel       Lista de comandos para o nível de acesso.
     * @param dba               Lista de comandos para a configuração DBA.
     * @param mgrInterface      Comandos para a configuração da interface de
     *                          gerenciamento.
     * @param vlan              Comandos para a configuração VLAN.
     * @param profileVlan       Lista de comandos para o deploy do perfil VLAN.
     * @param profileLineBridge Lista de comandos para o deploy do perfil de linha
     *                          Bridge.
     * @param profileLineRouter Lista de comandos para o deploy do perfil de linha
     *                          Router.
     * @param autoConfig        Lista de comandos para a autoconfiguração.
     * @param ontAutoConfig     Lista de comandos para a autoconfiguração ONT.
     *
     * @return true se a escrita do script for bem-sucedida, false caso contrário.
     */
    public boolean writeScript(final File script, final List<String> accessLevel, final List<String> dba,
            final String mgrInterface, final String vlan, final List<List<String>> profileVlan,
            final List<List<String>> profileLineBridge, final List<List<String>> profileLineRouter,
            final List<String> autoConfig, final List<String> ontAutoConfig) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(script))) {
            // Escreve os comandos de nível de acesso
            for (final String access : accessLevel) {
                writer.write(access);
                writer.newLine();
            }

            writer.newLine();

            // Escreve os comandos de configuração DBA
            for (final String dbaLine : dba) {
                writer.write(dbaLine);
                writer.newLine();
            }

            writer.newLine();

            // Escreve os comandos VLAN
            writer.write(vlan);
            writer.newLine();

            writer.newLine();

            // Escreve os comandos da interface de gerenciamento
            writer.write(mgrInterface);
            writer.newLine();

            writer.newLine();

            // Escreve os comandos de deploy de perfil de VLAN
            writer.write("deploy profile vlan");
            writer.newLine();
            for (final List<String> profileVlanLine : profileVlan) {
                for (final String item : profileVlanLine) {
                    writer.write(item);
                    writer.newLine();
                }
            }
            writer.write("exit");
            writer.newLine();
            writer.write("exit");
            writer.newLine();
            writer.newLine();

            // Escreve os comandos de deploy de perfil de linha Bridge
            writer.write("deploy profile line");
            writer.newLine();
            for (final List<String> profileLineBridgeLine : profileLineBridge) {
                for (final String item : profileLineBridgeLine) {
                    writer.write(item);
                    writer.newLine();
                }
            }

            writer.newLine();

            // Escreve os comandos de deploy de perfil de linha Router
            for (final List<String> profileLineRouterLine : profileLineRouter) {
                for (final String item : profileLineRouterLine) {
                    writer.write(item);
                    writer.newLine();
                }
            }
            writer.write("exit");
            writer.newLine();
            writer.write("exit");
            writer.newLine();
            writer.newLine();

            // Escreve os comandos de autoconfiguração ONT
            for (String ontAuto : ontAutoConfig) {
                writer.write(ontAuto);
                writer.newLine();
            }
            writer.newLine();

            // Escreve os comandos de autoconfiguração
            for (final String config : autoConfig) {
                writer.write(config);
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
     * Cria o script de configuração com base nos parâmetros e opções escolhidos.
     * 
     * @param nomeArq    Representa o nome do arquivo da olt
     * @param slotLength Representa o tamanho do slot que a olt tem
     * 
     * @return true se a criação do script foi bem-sucedida, false caso contrário.
     */
    public boolean createScript(String nomeArq, int slotLength) {
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

        if (getPonVlanType() == getVlanType()[1]) {
            bridgeUplink.add(oltGpon.bridgeUplink(getVlans().get(0),
                    getInterfaceEthernet(), getBridgeInterfaceUplink()));
            bridgeProfile.add(oltGpon.bridgeProfile(getVlans().get(0)));
            bridgeProfileRouter.add(oltGpon.bridgeProfileRouter(getVlans().get(0)));

            for (int i = 0; i < getDefaultCpeType().length - 1; i++) {
                if (i <= 3) {
                    bridgeProfileBind.add(oltGpon.bridgeProfileBind(getDeviceType()[i]));
                } else {
                    bridgeProfileBindRouter.add(oltGpon.bridgeProfileBindRouter(getDeviceType()[i]));
                }
            }
            if (getDefaultCpe() == getDefaultCpeType()[0]) {
                bridgeProfileBind.add(oltGpon.bridgeProfileBind(getDeviceType()[getDeviceType().length - 1]));
            } else {
                bridgeProfileBindRouter
                        .add(oltGpon.bridgeProfileBindRouter(getDeviceType()[getDeviceType().length - 1]));
            }
        } else {
            int j = 0;
            for (String vlans : getVlans()) {
                bridgeUplink.add(oltGpon.bridgeUplink(vlans, getInterfaceEthernet(), getBridgeInterfaceUplink()));
                bridgeProfile.add(oltGpon.bridgeProfile(vlans, Integer.toString(j)));
                bridgeProfileRouter.add(oltGpon.bridgeProfileRouter(vlans, Integer.toString(j)));
                j++;
                for (int i = 0; i < getDefaultCpeType().length - 1; i++) {
                    if (i <= 3) {
                        bridgeProfileBind.add(oltGpon.bridgeProfileBind(Integer.toString(j), getDeviceType()[i],
                                getInterfaceGpon()[j]));
                    } else {
                        bridgeProfileBindRouter.add(oltGpon.bridgeProfileBindRouter(Integer.toString(j),
                                getDeviceType()[i], getInterfaceGpon()[j]));
                    }
                }
                if (getDefaultCpe() == getDefaultCpeType()[0]) {
                    bridgeProfileBind.add(oltGpon.bridgeProfileBind(Integer.toString(j),
                            getDeviceType()[getDeviceType().length - 1], getInterfaceGpon()[j]));
                } else {
                    bridgeProfileBindRouter
                            .add(oltGpon.bridgeProfileBindRouter(Integer.toString(j),
                                    getDeviceType()[getDeviceType().length - 1], getInterfaceGpon()[j]));
                }
            }
        }
        return false;

        // // Se o código chegar aqui, significa que a escrita foi bem-sucedida
        // return writeScript(newScript, accessLevel, dba, mgrInterface, vlan,
        // profileVlan, profileLineBridge,
        // profileLineRouter, autoConfig, ontAutoConfig);
    }

    @Override
    public String[] getDeviceType() {
        return super.getDeviceType();
    }

    @Override
    public List<String> getVlans() {
        return super.getVlans();
    }

    @Override
    public void setVlans(final List<String> vlans) {
        super.setVlans(vlans);
    }

    @Override
    public String getInterfaceEthernet() {
        return super.getInterfaceEthernet();
    }

    @Override
    public void setInterfaceEthernet(final String interfaceEthernet) {
        super.setInterfaceEthernet(interfaceEthernet);
    }
}
