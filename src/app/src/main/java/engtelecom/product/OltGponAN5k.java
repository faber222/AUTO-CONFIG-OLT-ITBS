package engtelecom.product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import engtelecom.config.ConfigGeneratorAN5k;
import engtelecom.swingType.OltAN5k;

public class OltGponAN5k extends Olt {
    private String portaUplink;
    private String slotUplink;
    private String vlanPppoe;
    private String vlanVeip;
    private String slotPon;
    private String portaPon;
    private String slotCpe;

    private String phyIdCpe;
    private String modeloCpe;
    private String userPppoe;
    private String senhaPppoe;
    private String ssid2;
    private String ssid5;
    private String ssidPass2;
    private String ssidPass5;

    private String capaCpeType;
    private String capaPonType;
    private String capaWifiNumber;
    private String capaTenGPortNumber;
    private String capaOneGPortNumber;
    private String capaEquipamentId;
    private String capaPotsNumber;
    private String capaProfileName;
    private String capaUsbNumber;

    private boolean isWanService;
    private boolean isOnuCapability;

    private String nomeArq;

    public OltGponAN5k() {

    }

    public String getSlotPortaUplink() {
        return portaUplink;
    }

    public String getSlotUplink() {
        return slotUplink;
    }

    public String getVlanPppoe() {
        return vlanPppoe;
    }

    public String getVlanVeip() {
        return vlanVeip;
    }

    public String getSlotPon() {
        return slotPon;
    }

    public String getSlotPortaPon() {
        return portaPon;
    }

    public String getSlotCpe() {
        return slotCpe;
    }

    public String getPhyIdCpe() {
        return phyIdCpe;
    }

    public String getCpeCapaProfile() {
        return modeloCpe;
    }

    public String getUserPppoe() {
        return userPppoe;
    }

    public String getSenhaPppoe() {
        return senhaPppoe;
    }

    public String getSsid2() {
        return ssid2;
    }

    public String getSsid5() {
        return ssid5;
    }

    public String getSsidPass2() {
        return ssidPass2;
    }

    public String getSsidPass5() {
        return ssidPass5;
    }

    public String getCapaCpeType() {
        return capaCpeType;
    }

    public String getCapaPonType() {
        return capaPonType;
    }

    public String getCapaWifiNumber() {
        return capaWifiNumber;
    }

    public String getCapaTenGPortNumber() {
        return capaTenGPortNumber;
    }

    public String getCapaOneGPortNumber() {
        return capaOneGPortNumber;
    }

    public String getCapaEquipamentId() {
        return capaEquipamentId;
    }

    public String getCapaPotsNumber() {
        return capaPotsNumber;
    }

    public String getCapaProfileName() {
        return capaProfileName;
    }

    public String getCapaUsbNumber() {
        return capaUsbNumber;
    }

    public String getNomeArq() {
        return nomeArq;
    }

    public boolean isWanService() {
        return isWanService;
    }

    public boolean isOnuCapability() {
        return isOnuCapability;
    }

    public boolean start(final OltAN5k oltData) {
        // Valores inteiros
        this.portaUplink = (String) oltData.getjSpinnerPortaUplink().getValue().toString();
        this.slotUplink = (String) oltData.getjSpinnerSlotUplink().getValue().toString();
        this.slotPon = (String) oltData.getjSpinnerSlotPON().getValue().toString();
        this.portaPon = (String) oltData.getjSpinnerPortaPon().getValue().toString();
        this.slotCpe = (String) oltData.getjSpinnerSlotCpe().getValue().toString();
        this.vlanPppoe = null;
        this.vlanVeip = null;

        // Valores String
        this.modeloCpe = oltData.getjTextFieldModeloONU().getText();
        this.phyIdCpe = oltData.getjTextFieldPhyIdCPE().getText();
        this.nomeArq = oltData.getNomeArq();
        this.userPppoe = null;
        this.senhaPppoe = null;
        this.ssid2 = null;
        this.ssid5 = null;
        this.ssidPass2 = null;
        this.ssidPass5 = null;

        // Valores Strings Capability
        this.capaCpeType = null;
        this.capaPonType = null;
        this.capaWifiNumber = null;
        this.capaTenGPortNumber = null;
        this.capaOneGPortNumber = null;
        this.capaEquipamentId = null;
        this.capaPotsNumber = null;
        this.capaProfileName = null;
        this.capaUsbNumber = null;

        // Valores booleanos
        this.isOnuCapability = oltData.getjCheckBoxFlagONUCapability().isSelected();
        this.isWanService = oltData.getjRadioButtonWanService().isSelected();

        if (!checkPhyId(this.phyIdCpe)) {
            JOptionPane.showMessageDialog(null,
                    "PhyId invalido. Por favor, insira um phy-id valido (FHTT12345678).", "Erro",
                    JOptionPane.ERROR_MESSAGE, oltData.getErrorIcon());
            return false;
        }

        printUplinkPonCpeValues();

        if (isOnuCapability) {
            this.capaCpeType = oltData.getCapaCpeType();
            this.capaPonType = oltData.getCapaPonType();
            this.capaWifiNumber = oltData.getCapaWifiNumber();
            this.capaTenGPortNumber = oltData.getCapaTenGPortNumber();
            this.capaOneGPortNumber = oltData.getCapaOneGPortNumber();
            this.capaEquipamentId = oltData.getCapaEquipamentId();
            this.capaPotsNumber = oltData.getCapaPotsNumber();
            this.capaProfileName = oltData.getCapaProfileName();
            this.capaUsbNumber = oltData.getCapaUsbNumber();
            printCapabilityValues();
        }
        if (isWanService) {
            this.vlanPppoe = (String) oltData.getjSpinnerVlanPPPOE().getValue().toString();
            this.userPppoe = oltData.getjTextFieldUserPPPOE().getText();
            this.senhaPppoe = oltData.getjTextFieldPassPPPOE().getText();
            this.ssid2 = oltData.getjTextFieldSSID2().getText();
            this.ssid5 = oltData.getjTextFieldSSID5().getText();
            this.ssidPass2 = oltData.getjTextFieldSSIDPass2().getText();
            this.ssidPass5 = oltData.getjTextFieldSSIDPass5().getText();
            printPppoeAndSsidValues();
        } else {
            this.vlanVeip = (String) oltData.getjSpinnerVlanVeip().getValue().toString();
            printVeip();
        }

        final ConfigGeneratorAN5k configGeneratorAN5k = new ConfigGeneratorAN5k(this);
        configGeneratorAN5k.createScript();
        return false;
    }

    public void printUplinkPonCpeValues() {
        System.err.println("");
        System.err.println("Dados Uplink e PON");
        System.out.println("PHY IdCpe: " + this.phyIdCpe);
        System.out.println("MODELO Cpe: " + this.modeloCpe);
        System.out.println("PORTA Uplink: " + this.portaUplink);
        System.out.println("SLOT Uplink: " + this.slotUplink);
        System.out.println("SLOT Pon: " + this.slotPon);
        System.out.println("PORT Pon: " + this.portaPon);
        System.out.println("SLOT Cpe: " + this.slotCpe);
    }

    public void printCapabilityValues() {
        System.err.println("");
        System.err.println("Dados Recursivos Capability Profile");
        System.out.println("CPE Type: " + this.capaCpeType);
        System.out.println("PON Type: " + this.capaPonType);
        System.out.println("WIFI Number: " + this.capaWifiNumber);
        System.out.println("TEN GPort Number: " + this.capaTenGPortNumber);
        System.out.println("ONE GPort Number: " + this.capaOneGPortNumber);
        System.out.println("EQUIPAMENT Id: " + this.capaEquipamentId);
        System.out.println("POTS Number: " + this.capaPotsNumber);
        System.out.println("PROFILE Name: " + this.capaProfileName);
        System.out.println("USB Number: " + this.capaUsbNumber);
    }

    public void printPppoeAndSsidValues() {
        System.err.println("");
        System.err.println("Dados WanService");
        System.out.println("VLAN Pppoe: " + this.vlanPppoe);
        System.out.println("USER Pppoe: " + this.userPppoe);
        System.out.println("SENHA Pppoe: " + this.senhaPppoe);
        System.out.println("SSID2: " + this.ssid2);
        System.out.println("SSID5: " + this.ssid5);
        System.out.println("SSID Pass2: " + this.ssidPass2);
        System.out.println("SSID Pass5: " + this.ssidPass5);
    }

    public void printVeip() {
        System.err.println("");
        System.err.println("Dados Veip");
        System.out.println("VLAN Veip: " + this.vlanVeip);
    }

    public boolean checkPhyId(final String phyId) {
        return (phyId.matches("^[A-Za-z0-9]{4}[A-Fa-f0-9]{8}$"));
    }

    public boolean checkTelnet(final String ipAddress, final String port, final String user, final char[] pwd, final ImageIcon erroIcon) {
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
    public void saida(final ImageIcon saidaIcon) {
        throw new UnsupportedOperationException("Unimplemented method 'saida'");
    }

    @Override
    public boolean isValidIPv4Address(final String ipAddress) {
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
    public boolean checkVlanClient(final String rangeVlan, final ImageIcon erroIcon, final int range) {
        throw new UnsupportedOperationException("Unimplemented method 'checkVlanClient'");
    }

}
