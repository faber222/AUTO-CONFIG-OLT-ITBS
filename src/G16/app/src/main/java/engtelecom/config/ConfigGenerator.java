package engtelecom.config;

import java.util.List;

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
