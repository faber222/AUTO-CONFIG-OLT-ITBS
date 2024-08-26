package engtelecom.product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import engtelecom.config.ConfigGeneratorAN5k;

public class OltGponAN5k extends Olt {

    public OltGponAN5k() {

    }

    public boolean start(String nomeArq, String phyIdCpe, String portaUplink, String slotUplink, String portaPon,
            String slotCpe, String slotPon, String vlan,
            String onuModel, String userPpp, String passPpp, String ssid2, String ssid5, String pass2, String pass5) {
        // Carrega os ícones necessários para o diálogo
        final ClassLoader classLoader = OltGpon.class.getClassLoader();
        final ImageIcon erroIcon = new ImageIcon(classLoader.getResource("erro.png"));

        final ConfigGeneratorAN5k configGeneratorAN5k = new ConfigGeneratorAN5k(null, null, null);
        configGeneratorAN5k.createScript();
        return false;
    }

    public boolean checkSlotCpe() {
        return false;
    }

    public boolean checkPonPort() {
        return false;
    }

    public boolean checkSlotGpon() {
        return false;
    }

    public boolean checkPhyId() {
        return false;
    }

    public boolean checkTelnet(String ipAddress, String port, String user, char[] pwd, final ImageIcon erroIcon) {
        // Verifica se o endereço IP inserido é válido.
        if (!isValidIPv4Address(ipAddress)) {
            JOptionPane.showMessageDialog(null,
                    "Entrada invalida. Por favor, insira um endereco IP valido (0-255).", "Erro",
                    JOptionPane.ERROR_MESSAGE, erroIcon);
            return false;
        }
        if (!port.matches("^[1-9]\\d*$")) {
            JOptionPane.showMessageDialog(null,
                    "Entrada invalida. Por favor, insira uma porta valida.", "Erro",
                    JOptionPane.ERROR_MESSAGE, erroIcon);
            return false;
        }
        if (user == null || pwd == null) {
            JOptionPane.showMessageDialog(null,
                    "Entrada invalida. O campo usuario ou senha nao podem ser nulos", "Erro",
                    JOptionPane.ERROR_MESSAGE, erroIcon);
            return false;
        }
        return true;
    }

    @Override
    public void saida(ImageIcon saidaIcon) {
        // Exibe uma caixa de diálogo com uma mensagem de aviso indicando que o programa
        // será encerrado.
        JOptionPane.showMessageDialog(null,
                "Voce pressionou o botao 'Cancelar'. O programa sera encerrado.",
                null, JOptionPane.WARNING_MESSAGE, saidaIcon);
    }

    @Override
    public boolean isValidIPv4Address(String ipAddress) {
        // Expressão regular para validar um endereço IPv4
        final String regex = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        // Compila a expressão regular
        final Pattern pattern = Pattern.compile(regex);
        // Compara a string de entrada com a expressão regular
        final Matcher matcher = pattern.matcher(ipAddress);
        // Retorna true se a string corresponder à expressão regular (representando um
        // IPv4 válido)
        return matcher.matches();
    }

    @Override
    public boolean checkVlanClient(String rangeVlan, ImageIcon erroIcon, int range) {
        throw new UnsupportedOperationException("Unimplemented method 'checkVlanClient'");
    }

}
