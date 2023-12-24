package engtelecom.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import engtelecom.scripts.ScriptsG16;

/**
 * Classe responsável por gerar configurações e scripts com base nos parâmetros
 * fornecidos.
 * Estende a classe Config para herdar as configurações básicas.
 */
public class ConfigGenerator extends Config {
    private String ponVlanType;

    /**
     * Construtor da classe ConfigGenerator.
     *
     * @param vlans             Lista de VLANs a serem configuradas.
     * @param aimProfileVlan    Lista de perfis de VLAN.
     * @param interfaceEthernet Nome da interface Ethernet.
     * @param aimProfileLine    Lista de perfis de linhas.
     * @param produtos          Lista de produtos associados.
     */
    public ConfigGenerator(List<String> vlans, List<String> aimProfileVlan, String interfaceEthernet,
            List<String> aimProfileLine, List<String> produtos) {
        super(vlans, aimProfileVlan, interfaceEthernet, aimProfileLine, produtos);
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
     * Define o tipo de VLAN da PON.
     *
     * @param ponVlanType O tipo de VLAN da PON.
     */
    public void setPonVlanType(String ponVlanType) {
        this.ponVlanType = ponVlanType;
    }

    /**
     * Escreve o script de configuração no arquivo fornecido.
     *
     * @param script            Arquivo de script a ser escrito.
     * @param accessLevel       Lista de níveis de acesso.
     * @param dba               Lista de configurações de DBA.
     * @param mgrInterface      Interface de gerenciamento.
     * @param vlan              Configuração VLAN.
     * @param profileVlan       Lista de configurações de perfil de VLAN.
     * @param profileLineBridge Lista de configurações de perfil de linha Bridge.
     * @param profileLineRouter Lista de configurações de perfil de linha Router.
     * @return true se a escrita for bem-sucedida, false caso contrário.
     */
    public boolean writeScript(File script, List<String> accessLevel, List<String> dba, String mgrInterface,
            String vlan, List<List<String>> profileVlan, List<List<String>> profileLineBridge,
            List<List<String>> profileLineRouter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(script))) {
            // Escreve os comandos de nível de acesso
            for (String access : accessLevel) {
                writer.write(access);
                writer.newLine();
            }

            writer.newLine();

            // Escreve os comandos de configuração DBA
            for (String dbaLine : dba) {
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
            for (List<String> profileVlanLine : profileVlan) {
                for (String item : profileVlanLine) {
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
            for (List<String> profileLineBridgeLine : profileLineBridge) {
                for (String item : profileLineBridgeLine) {
                    writer.write(item);
                    writer.newLine();
                }
            }

            writer.newLine();

            // Escreve os comandos de deploy de perfil de linha Router
            for (List<String> profileLineRouterLine : profileLineRouter) {
                for (String item : profileLineRouterLine) {
                    writer.write(item);
                    writer.newLine();
                }
            }
            writer.write("exit");
            writer.newLine();
            writer.write("exit");
            writer.newLine();
            writer.newLine();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Cria o script de configuração com base nos parâmetros fornecidos.
     *
     * @return true se a criação for bem-sucedida, false caso contrário.
     */
    public boolean createScript() {
        File newScript = new File("scriptG16.txt");
        String vlan = new String();
        String mgrInterface = new String();
        ScriptsG16 g16 = new ScriptsG16();
        List<String> accessLevel = g16.enable();
        List<String> dba = g16.profileDba();
        List<List<String>> profileVlan = new ArrayList<>();
        List<List<String>> profileLineBridge = new ArrayList<>();
        List<List<String>> profileLineRouter = new ArrayList<>();

        String[] deviceType = {
                "i10-100",
                "i10-420",
                "i30-100",
                "i40-100",
                "i40-100-v2",
                "i40-201",
                "i40-211",
                "i40-421",
                "i41-100",
                "i41-201",
                "i41-211",
                "i41-421"
        };

        vlan = g16.vlan(getVlans());
        mgrInterface = g16.interfaceMgr(getInterfaceEthernet(), getVlans());

        for (int i = 0; i < getVlans().size(); i++) {
            profileVlan.add(g16.profileVlan(getVlans().get(i), getAimProfileVlan().get(i)));
            profileLineBridge.add(g16.profileLineBridge(getVlans().get(i), getAimProfileLine().get(i),
                    getAimProfileVlan().get(i), deviceType[3]));
            if (getVlans().size() == 1) {
                profileLineRouter.add(g16.profileLineRouter(getVlans().get(i), getAimProfileLine().get(i + 1),
                        getAimProfileVlan().get(i), deviceType[7]));
            } else {
                profileLineRouter.add(g16.profileLineRouter(getVlans().get(i), getAimProfileLine().get(i + 16),
                        getAimProfileVlan().get(i), deviceType[7]));
            }
        }

        // if (getPonVlanType().equals("Uma vlan por pon")) {
        // // Escreva comandos para uma VLAN por PON

        // // Adicione mais comandos conforme necessário
        // } else if (getPonVlanType().equals("Uma vlan para todas as pon")) {
        // // Escreva comandos para uma VLAN para todas as PON

        // // Adicione mais comandos conforme necessário
        // } else {
        // // Lógica para outros casos (se necessário)

        // // Adicione mais comandos conforme necessário
        // }

        // Se o código chegar aqui, significa que a escrita foi bem-sucedida
        return writeScript(newScript, accessLevel, dba, mgrInterface, vlan, profileVlan, profileLineBridge,
                profileLineRouter);
    }

    // Métodos sobrescritos da classe Config

    @Override
    public List<String> getAimProfileLine() {
        return super.getAimProfileLine();
    }

    @Override
    public List<String> getAimProfileVlan() {
        return super.getAimProfileVlan();
    }

    @Override
    public List<String> getProdutos() {
        return super.getProdutos();
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
    public void setProdutos(List<String> produtos) {
        super.setProdutos(produtos);
    }

    @Override
    public void setVlans(List<String> vlans) {
        super.setVlans(vlans);
    }

    @Override
    public String getInterfaceEthernet() {
        return super.getInterfaceEthernet();
    }

    @Override
    public void setInterfaceEthernet(String interfaceEthernet) {
        super.setInterfaceEthernet(interfaceEthernet);
    }
}
