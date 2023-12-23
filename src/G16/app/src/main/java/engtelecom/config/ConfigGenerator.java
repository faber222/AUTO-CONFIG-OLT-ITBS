package engtelecom.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import engtelecom.scripts.ScriptsG16;

public class ConfigGenerator extends Config {
    private String ponVlanType;

    public ConfigGenerator(List<String> vlans, List<String> aimProfileVlan, String interfaceEthernet,
            List<String> aimProfileLine, List<String> produtos) {
        super(vlans, aimProfileVlan, interfaceEthernet, aimProfileLine, produtos);
    }

    public String getPonVlanType() {
        return ponVlanType;
    }

    public void setPonVlanType(String ponVlanType) {
        this.ponVlanType = ponVlanType;
    }

    public boolean writeScript(File script, List<String> dba, List<List<String>> mgrInterface, List<String> vlan,
            List<List<String>> profileVlan, List<List<String>> profileLineBridge,
            List<List<String>> profileLineRouter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(script))) {
            for (String dbaLine : dba) {
                writer.write(dbaLine);
                writer.newLine();
            }

            writer.newLine();
            for (String vlanLine : vlan) {
                writer.write(vlanLine);
                writer.newLine();
            }

            writer.newLine();
            for (List<String> interfaceLine : mgrInterface) {
                for (String item : interfaceLine) {
                    writer.write(item);
                    writer.newLine();
                }
            }
            writer.newLine();

            for (List<String> profileVlanLine : profileVlan) {
                for (String item : profileVlanLine) {
                    writer.write(item);
                    writer.newLine();
                }
            }

            writer.newLine();
            for (List<String> profileLineBridgeLine : profileLineBridge) {
                for (String item : profileLineBridgeLine) {
                    writer.write(item);
                    writer.newLine();
                }
            }

            writer.newLine();
            for (List<String> profileLineRouterLine : profileLineRouter) {
                for (String item : profileLineRouterLine) {
                    writer.write(item);
                    writer.newLine();
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean createScript() {
        File newScript = new File("scriptG16.txt");
        ScriptsG16 g16 = new ScriptsG16();
        List<String> dba = g16.profileDba();
        List<String> vlan = new ArrayList<>();
        List<List<String>> mgrInterface = new ArrayList<>();
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

        for (String vlans : getVlans()) {
            vlan.add(g16.vlan(vlans));
            mgrInterface.add(g16.interfaceMgr(getInterfaceEthernet(), vlans));
        }

        for (int i = 0; i < getVlans().size(); i++) {
            profileVlan.add(g16.profileVlan(getVlans().get(i), getAimProfileVlan().get(i)));
            profileLineBridge.add(g16.profileLineBridge(getVlans().get(i), getAimProfileLine().get(i),
                    getAimProfileVlan().get(i), deviceType[3]));
            profileLineRouter.add(g16.profileLineRouter(getVlans().get(i), getAimProfileLine().get(i + 16),
                    getAimProfileVlan().get(i), deviceType[7]));
        }

        writeScript(newScript, dba, mgrInterface, vlan, profileVlan, profileLineBridge, profileLineRouter);

        if (getPonVlanType().equals("Uma vlan por pon")) {
            // Escreva comandos para uma VLAN por PON

            // Adicione mais comandos conforme necessário
        } else if (getPonVlanType().equals("Uma vlan para todas as pon")) {
            // Escreva comandos para uma VLAN para todas as PON

            // Adicione mais comandos conforme necessário
        } else {
            // Lógica para outros casos (se necessário)

            // Adicione mais comandos conforme necessário
        }

        // Se o código chegar aqui, significa que a escrita foi bem-sucedida
        return true;
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
