/**
 * @author faber222
 * @since 2024
*/
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
     * Interface Ethernet associada à configuração.
     */
    private String interfaceEthernet;

    /**
     * Lista de produtos associados à configuração.
     */
    private final String[] deviceType;

    /**
     * Construtor que inicializa os atributos da classe.
     * 
     * @param vlans             Lista de VLANs.
     * @param interfaceEthernet Interface Ethernet.
     * @param produtos          Lista de produtos.
     */
    public Config(final List<String> vlans, final String interfaceEthernet,
            final String[] produtos) {
        this.vlans = vlans;
        this.interfaceEthernet = interfaceEthernet;
        this.deviceType = produtos;
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
    public void setInterfaceEthernet(final String interfaceEthernet) {
        this.interfaceEthernet = interfaceEthernet;
    }

    /**
     * Obtém a lista de produtos.
     * 
     * @return A lista de produtos.
     */
    public String[] getDeviceType() {
        return deviceType;
    }

}
