/**
 * Interface que define os métodos padrão para interação com uma OLT (Optical Line Terminal).
 * Implementações devem fornecer funcionalidades específicas para uma OLT em particular.
 * 
 * @author faber222
 * @since 2024
 */
package engtelecom.product;

import java.util.List;

import javax.swing.ImageIcon;

public interface OltInterface {

        /**
         * Obtém o endereço IP da OLT.
         * 
         * @return O endereço IP da OLT.
         */
        public abstract String getIp();

        /**
         * Define o endereço IP da OLT.
         * 
         * @param ip O endereço IP a ser definido.
         */
        public abstract void setIp(String ip);

        /**
         * Obtém a porta de acesso Telnet da OLT.
         * 
         * @return A porta de acesso Telnet da OLT.
         */
        public abstract int getPort();

        /**
         * Define a porta de acesso Telnet da OLT.
         * 
         * @param port A porta de acesso Telnet a ser definida.
         */
        public abstract void setPort(int port);

        /**
         * Obtém a senha de acesso à OLT.
         * 
         * @return A senha de acesso à OLT.
         */
        public abstract String getPasswd();

        /**
         * Define a senha de acesso à OLT.
         * 
         * @param passwd A senha de acesso à OLT a ser definida.
         */
        public abstract void setPasswd(String passwd);

        /**
         * Obtém o nome de usuário para acesso à OLT.
         * 
         * @return O nome de usuário para acesso à OLT.
         */
        public abstract String getUser();

        /**
         * Define o nome de usuário para acesso à OLT.
         * 
         * @param user O nome de usuário a ser definido.
         */
        public abstract void setUser(String user);

        /**
         * Obtém o tamanho dos slots que a OLT possui.
         * 
         * @return O tamanho dos slots que a OLT possui.
         */
        public abstract int getSlotLength();

        /**
         * Define o tamanho dos slots que a OLT possui.
         * 
         * @param slotLength O tamanho dos slots a ser definido.
         */
        public abstract void setSlotLength(int slotLength);

        /**
         * Inicia o processo de interação/configuração da OLT.
         */
        public abstract void start();

        /**
         * Exibe uma caixa de diálogo de saída.
         * 
         * @param saidaIcon Ícone para a caixa de diálogo de saída.
         */
        public abstract void saida(ImageIcon saidaIcon);

        /**
         * Verifica se um endereço IP é válido no formato IPv4.
         * 
         * @param ipAddress O endereço IP a ser verificado.
         * @return true se o endereço IP for válido, false caso contrário.
         */
        public abstract boolean isValidIPv4Address(String ipAddress);

        /**
         * Obtém o endereço IP da OLT a partir do usuário.
         * 
         * @param saidaIcon Ícone para caixa de diálogo de saída.
         * @param erroIcon  Ícone de erro para caixa de diálogo.
         */
        public abstract void getIpFromUser(ImageIcon saidaIcon, ImageIcon erroIcon);

        /**
         * Obtém a porta de acesso Telnet da OLT a partir do usuário.
         * 
         * @param saidaIcon Ícone para caixa de diálogo de saída.
         * @param erroIcon  Ícone de erro para caixa de diálogo.
         */
        public abstract void getPortFromUser(ImageIcon saidaIcon, ImageIcon erroIcon);

        /**
         * Obtém o nome de usuário e senha da OLT a partir do usuário.
         * 
         * @param saidaIcon Ícone para caixa de diálogo de saída.
         * @param erroIcon  Ícone de erro para caixa de diálogo.
         */
        public abstract void getUserAndPwd(ImageIcon saidaIcon, ImageIcon erroIcon);

        /**
         * Obtém informações de VLAN para o cliente a partir do usuário.
         * 
         * @param equipamentoIcon Ícone do equipamento.
         * @param saidaIcon       Ícone de saída.
         * @param erroIcon        Ícone de erro.
         * @param range           O intervalo permitido para as informações de VLAN.
         * @return Uma lista de strings representando as informações de VLAN para o
         *         cliente.
         */
        public abstract List<String> getVlanClient(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
                        ImageIcon erroIcon, int range);

        /**
         * Obtém o tipo padrão de CPE (Customer Premises Equipment) a partir do usuário.
         * 
         * @param equipamentoIcon Ícone do equipamento.
         * @param saidaIcon       Ícone de saída.
         * @param erroIcon        Ícone de erro.
         * @param defaultCpeType  Array de strings representando os tipos padrão de CPE.
         * @return O tipo padrão de CPE escolhido pelo usuário.
         */
        public abstract String getDefaultCpeType(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
                        ImageIcon erroIcon, String[] defaultCpeType);

        /**
         * Obtém a configuração do modelo a partir do usuário.
         * 
         * @param equipamentoIcon    Ícone do equipamento.
         * @param saidaIcon          Ícone de saída.
         * @param erroIcon           Ícone de erro.
         * @param modelConfiguration Array de strings representando as configurações de
         *                           modelo.
         * @return A configuração do modelo escolhida pelo usuário.
         */
        public abstract String getModelConfiguration(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
                        ImageIcon erroIcon, String[] modelConfiguration);

        /**
         * Obtém a interface Ethernet Uplink da OLT a partir do usuário.
         * 
         * @param equipamentoIcon Ícone do equipamento.
         * @param saidaIcon       Ícone de saída.
         * @param erroIcon        Ícone de erro.
         * @param interfaceEth    Array de strings representando as interfaces Ethernet.
         * @return A interface Ethernet Uplink escolhida pelo usuário.
         */
        public abstract String getInterfaceEth(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
                        ImageIcon erroIcon, String[] interfaceEth);
}
