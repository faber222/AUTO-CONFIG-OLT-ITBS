/**
 * Interface que define os métodos padrão para interação com uma OLT (Optical Line Terminal).
 * Implementações devem fornecer funcionalidades específicas para uma OLT em particular.
 * 
 * @author faber222
 * @since 2024
 */
package engtelecom.product;

import javax.swing.ImageIcon;

public interface OltInterface {

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
         * Obtém informações de VLAN para o cliente a partir do usuário.
         * 
         * @param equipamentoIcon Ícone do equipamento.
         * @param saidaIcon       Ícone de saída.
         * @param erroIcon        Ícone de erro.
         * @param range           O intervalo permitido para as informações de VLAN.
         * @return Uma lista de strings representando as informações de VLAN para o
         *         cliente.
         */
        public abstract boolean checkVlanClient(String rangeVlan, ImageIcon erroIcon, int range);

}
