package engtelecom.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import engtelecom.scripts.Scripts;

/**
 * Classe responsável por gerar configurações e scripts com base nos parâmetros
 * fornecidos.
 * Estende a classe Config para herdar as configurações básicas.
 */
public class ConfigGeneratorG08 extends Config {

    private final String ponVlanType;
    private final String[] vlanType;
    private final String defaultCpe;
    private final String[] interfaceGpon;
    private final String[] defaultCpeType;

    /**
     * Construtor da classe ConfigGenerator.
     *
     * @param vlans             Lista de VLANs a serem configuradas.
     * @param aimProfileVlan    Lista de perfis de VLAN.
     * @param interfaceEthernet Nome da interface Ethernet.
     * @param aimProfileLine    Lista de perfis de linhas.
     * @param deviceType        Array que contem os devices de ont
     * @param ponVlanType       Palavra escolhida que define o tipo de config.
     * @param vlanType          Array de comparacao do tipo de config.
     * @param defaultCpe        String que define se é Router ou Bridge
     * @param interfaceGpon     Array com as interfaces gpon que a olt tem
     * @param defaultCpeType    Array contendo o default bridge ou router
     */
    public ConfigGeneratorG08(final List<String> vlans, final List<String> aimProfileVlan,
            final String interfaceEthernet,
            final List<String> aimProfileLine, final String[] deviceType, final String ponVlanType,
            final String[] vlanType, final String defaultCpe, final String[] interfaceGpon,
            final String[] defaultCpeType) {
        super(vlans, aimProfileVlan, interfaceEthernet, aimProfileLine, deviceType);
        this.ponVlanType = ponVlanType;
        this.vlanType = vlanType;
        this.defaultCpe = defaultCpe;
        this.interfaceGpon = interfaceGpon;
        this.defaultCpeType = defaultCpeType;
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
     * @param nomeArq Representa o nome do arquivo da olt
     * @return true se a criação do script foi bem-sucedida, false caso contrário.
     */
    public boolean createScript(String nomeArq) {
        // Caminho do novo script
        final File newScript = new File(nomeArq);

        // Inicialização de variáveis
        String vlan = new String();
        String mgrInterface = new String();

        // Instância da classe Scripts para obter scripts pré-definidos
        final Scripts g08 = new Scripts();

        // Obtenção de listas de scripts pré-definidos
        final List<String> accessLevel = g08.enable();
        final List<String> dba = g08.profileDba();
        final List<String> autoConfig = g08.autoConfig();

        // Listas para armazenar scripts específicos
        final List<List<String>> profileVlan = new ArrayList<>();
        final List<List<String>> profileLineBridge = new ArrayList<>();
        final List<List<String>> profileLineRouter = new ArrayList<>();
        final List<String> ontAutoConfig = new ArrayList<>();

        // Construção das strings para VLAN e interfaces de gerenciamento
        vlan = g08.vlan(getVlans());
        mgrInterface = g08.interfaceMgr(getInterfaceEthernet(), getVlans());

        // Construção dos scripts para VLAN, Profile Line Bridge e Profile Line Router
        if (getPonVlanType() == getVlanType()[2]) {
            for (int i = 0; i < getVlans().size(); i++) {
                profileVlan.add(g08.profileVlanUntagged(getVlans().get(i), getAimProfileVlan().get(i)));
                profileLineBridge.add(g08.profileLineBridgeUntagged(getAimProfileLine().get(i),
                        getAimProfileVlan().get(i), getDeviceType()[3]));

                // Verificação do tamanho da lista para evitar índices fora dos limites
                if (getVlans().size() == 1) {
                    profileLineRouter.add(g08.profileLineRouterUnTagged(getAimProfileLine().get(i + 1),
                            getAimProfileVlan().get(i), getDeviceType()[7]));
                } else {
                    profileLineRouter.add(g08.profileLineRouterUnTagged(getAimProfileLine().get(i + 8),
                            getAimProfileVlan().get(i), getDeviceType()[7]));
                }
            }
        } else {
            for (int i = 0; i < getVlans().size(); i++) {
                profileVlan.add(g08.profileVlanTagged(getVlans().get(i), getAimProfileVlan().get(i)));
                profileLineBridge.add(g08.profileLineBridgeTagged(getVlans().get(i), getAimProfileLine().get(i),
                        getAimProfileVlan().get(i), getDeviceType()[3]));

                // Verificação do tamanho da lista para evitar índices fora dos limites
                if (getVlans().size() == 1) {
                    profileLineRouter.add(g08.profileLineRouterTagged(getVlans().get(i), getAimProfileLine().get(i + 1),
                            getAimProfileVlan().get(i), getDeviceType()[7]));
                } else {
                    profileLineRouter.add(g08.profileLineRouterTagged(getVlans().get(i),
                            getAimProfileLine().get(i + 8), getAimProfileVlan().get(i), getDeviceType()[7]));
                }
            }
        }

        // Configuração específica para "Uma vlan para todas as pon"
        if (getPonVlanType() == getVlanType()[1]) {
            for (int i = 0; i < getDeviceType().length; i++) {
                if (i < 5) {
                    ontAutoConfig.add(g08.ontAutoConfigUmaVlanPon(getAimProfileLine().get(0), getDeviceType()[i]));
                } else {
                    ontAutoConfig.add(g08.ontAutoConfigUmaVlanPon(getAimProfileLine().get(1), getDeviceType()[i]));
                }
            }

            // Definindo se é default bridge
            if (getDefaultCpe() == getDefaultCpeType()[0]) {
                ontAutoConfig.add(g08.ontAutoConfigDefaultUmaVlanPon(getAimProfileLine().get(0)));
            } else {
                ontAutoConfig.add(g08.ontAutoConfigDefaultUmaVlanPon(getAimProfileLine().get(1)));
            }
        } else {
            // Configuração específica para "Uma vlan por pon"
            for (int i = 0; i < getDeviceType().length; i++) {
                for (int j = 0; j < getAimProfileVlan().size(); j++) {
                    if (i < 5) {
                        ontAutoConfig.add(g08.ontAutoConfigUmaVlanPorPon(getAimProfileLine().get(j), getVlans().get(j),
                                getDeviceType()[i], getInterfaceGpon()[j]));
                    } else {
                        ontAutoConfig
                                .add(g08.ontAutoConfigUmaVlanPorPon(getAimProfileLine().get(j + 8), getVlans().get(j),
                                        getDeviceType()[i], getInterfaceGpon()[j]));
                    }
                }
            }

            for (int i = 0; i < getAimProfileVlan().size(); i++) {
                // Definindo se é default bridge
                if (getDefaultCpe() == getDefaultCpeType()[0]) {
                    ontAutoConfig.add(
                            g08.ontAutoConfigDefaultUmaVlanPorPon(getAimProfileLine().get(i), getInterfaceGpon()[i]));
                } else {
                    ontAutoConfig.add(g08.ontAutoConfigDefaultUmaVlanPorPon(getAimProfileLine().get(i + 8),
                            getInterfaceGpon()[i]));
                }
            }
        }

        // Se o código chegar aqui, significa que a escrita foi bem-sucedida
        return writeScript(newScript, accessLevel, dba, mgrInterface, vlan, profileVlan, profileLineBridge,
                profileLineRouter, autoConfig, ontAutoConfig);
    }

    @Override
    public List<String> getAimProfileLine() {
        return super.getAimProfileLine();
    }

    @Override
    public List<String> getAimProfileVlan() {
        return super.getAimProfileVlan();
    }

    @Override
    public String[] getDeviceType() {
        return super.getDeviceType();
    }

    @Override
    public String getInterfaceEthernet() {
        return super.getInterfaceEthernet();
    }

    @Override
    public List<String> getVlans() {
        return super.getVlans();
    }

    @Override
    public void setAimProfileLine(List<String> aimProfileLine) {
        super.setAimProfileLine(aimProfileLine);
    }

    @Override
    public void setAimProfileVlan(List<String> aimProfileVlan) {
        super.setAimProfileVlan(aimProfileVlan);
    }

    @Override
    public void setInterfaceEthernet(String interfaceEthernet) {
        super.setInterfaceEthernet(interfaceEthernet);
    }

    @Override
    public void setVlans(List<String> vlans) {
        super.setVlans(vlans);
    }

}
