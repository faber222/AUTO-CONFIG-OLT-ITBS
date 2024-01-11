package engtelecom.product;

/**
 * Classe abstrata que fornece uma implementação básica da interface
 * OltInterface.
 * Implementações concretas devem estender esta classe e fornecer a lógica
 * específica da OLT.
 * 
 * @author faber222
 * @since 2024
 */
public abstract class Olt implements OltInterface {

    /**
     * Endereço IP da OLT.
     */
    protected String ip;

    /**
     * Porta de acesso Telnet da OLT.
     */
    protected int port;

    /**
     * Senha de acesso à OLT.
     */
    protected String passwd;

    /**
     * Nome de usuário para acesso à OLT.
     */
    protected String user;

    /**
     * Tamanho dos slots que a OLT possui.
     */
    protected int slotLength;
}
