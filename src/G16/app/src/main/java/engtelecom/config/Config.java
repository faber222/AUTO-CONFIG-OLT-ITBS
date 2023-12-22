package engtelecom.config;

import java.util.List;

public class Config {
    private List<String> vlans;
    private List<String> aimProfileVlan;
    private String interfaceEthernet;
    private List<String> aimProfileLine;
    private List<String> produtos;

    public Config(final List<String> vlans, final List<String> aimProfileVlan, final String interfaceEthernet,
            final List<String> aimProfileLine, final List<String> produtos) {
        this.vlans = vlans;
        this.aimProfileVlan = aimProfileVlan;
        this.interfaceEthernet = interfaceEthernet;
        this.aimProfileLine = aimProfileLine;
        this.produtos = produtos;
    }

    public List<String> getVlans() {
        return vlans;
    }

    public void setVlans(final List<String> vlans) {
        this.vlans = vlans;
    }

    public List<String> getAimProfileVlan() {
        return aimProfileVlan;
    }

    public void setAimProfileVlan(final List<String> aimProfileVlan) {
        this.aimProfileVlan = aimProfileVlan;
    }

    public List<String> getAimProfileLine() {
        return aimProfileLine;
    }

    public void setAimProfileLine(final List<String> aimProfileLine) {
        this.aimProfileLine = aimProfileLine;
    }

    public List<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(final List<String> produtos) {
        this.produtos = produtos;
    }

    public String getInterfaceEthernet() {
        return interfaceEthernet;
    }

    public void setInterfaceEthernet(String interfaceEthernet) {
        this.interfaceEthernet = interfaceEthernet;
    }

}
