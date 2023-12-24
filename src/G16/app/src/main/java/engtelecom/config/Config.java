package engtelecom.config;

import java.util.List;

/**
 * Representa uma configuração que pode ser aplicada a um dispositivo de rede.
 */
public class Config {
    /**
     * Lista de VLANs associadas à configuração.
     */
    private List<String> vlans;

    /**
     * Lista de perfis AIM VLAN associados à configuração.
     */
    private List<String> aimProfileVlan;

    /**
     * Interface Ethernet associada à configuração.
     */
    private String interfaceEthernet;

    /**
     * Lista de perfis AIM Line associados à configuração.
     */
    private List<String> aimProfileLine;

    /**
     * Lista de produtos associados à configuração.
     */
    private List<String> produtos;

    /**
     * Construtor que inicializa os atributos da classe.
     * 
     * @param vlans             Lista de VLANs.
     * @param aimProfileVlan    Lista de perfis AIM VLAN.
     * @param interfaceEthernet Interface Ethernet.
     * @param aimProfileLine    Lista de perfis AIM Line.
     * @param produtos          Lista de produtos.
     */
    public Config(final List<String> vlans, final List<String> aimProfileVlan, final String interfaceEthernet,
            final List<String> aimProfileLine, final List<String> produtos) {
        this.vlans = vlans;
        this.aimProfileVlan = aimProfileVlan;
        this.interfaceEthernet = interfaceEthernet;
        this.aimProfileLine = aimProfileLine;
        this.produtos = produtos;
    }

    /**
     * Obtém a lista de VLANs.
     * 
     * @return A lista de VLANs.
     */
    public List<String> getVlans() {
        return vlans;
    }

    /**
     * Define a lista de VLANs.
     * 
     * @param vlans A nova lista de VLANs a ser definida.
     */
    public void setVlans(final List<String> vlans) {
        this.vlans = vlans;
    }

    /**
     * Obtém a lista de perfis AIM VLAN.
     * 
     * @return A lista de perfis AIM VLAN.
     */
    public List<String> getAimProfileVlan() {
        return aimProfileVlan;
    }

    /**
     * Define a lista de perfis AIM VLAN.
     * 
     * @param aimProfileVlan A nova lista de perfis AIM VLAN a ser definida.
     */
    public void setAimProfileVlan(final List<String> aimProfileVlan) {
        this.aimProfileVlan = aimProfileVlan;
    }

    /**
     * Obtém a interface Ethernet.
     * 
     * @return A interface Ethernet.
     */
    public String getInterfaceEthernet() {
        return interfaceEthernet;
    }

    /**
     * Define a interface Ethernet.
     * 
     * @param interfaceEthernet A nova interface Ethernet a ser definida.
     */
    public void setInterfaceEthernet(String interfaceEthernet) {
        this.interfaceEthernet = interfaceEthernet;
    }

    /**
     * Obtém a lista de perfis AIM Line.
     * 
     * @return A lista de perfis AIM Line.
     */
    public List<String> getAimProfileLine() {
        return aimProfileLine;
    }

    /**
     * Define a lista de perfis AIM Line.
     * 
     * @param aimProfileLine A nova lista de perfis AIM Line a ser definida.
     */
    public void setAimProfileLine(final List<String> aimProfileLine) {
        this.aimProfileLine = aimProfileLine;
    }

    /**
     * Obtém a lista de produtos.
     * 
     * @return A lista de produtos.
     */
    public List<String> getProdutos() {
        return produtos;
    }

    /**
     * Define a lista de produtos.
     * 
     * @param produtos A nova lista de produtos a ser definida.
     */
    public void setProdutos(final List<String> produtos) {
        this.produtos = produtos;
    }

}
