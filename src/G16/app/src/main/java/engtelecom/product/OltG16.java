package engtelecom.product;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
// import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import engtelecom.access.Telnet;
import engtelecom.config.ConfigGenerator;

public class OltG16 {
    private String ip;
    private Integer port;
    private String passwd;
    private String user;

    public static void mostrarCriador() {
        String nomeCriador = "Faber";
        String githubLink = "https://github.com/faber222"; // Substitua com o link do GitHub do criador

        // Configura as opções de codificação do QR code
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.MARGIN, 2);

        // Gera o QR code
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(githubLink, BarcodeFormat.QR_CODE, 100, 100, hints);

            // Cria uma ImageIcon a partir do QR code
            BufferedImage bufferedImage = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                for (int y = 0; y < bitMatrix.getHeight(); y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }
            ImageIcon qrCodeIcon = new ImageIcon(bufferedImage);

            String mensagem = "O criador deste codigo: " + nomeCriador + "\n\n" +
                    "Voce pode encontra-lo no GitHub:";

            JOptionPane.showMessageDialog(
                    null, mensagem, "Criador faber222 e Link do GitHub", JOptionPane.INFORMATION_MESSAGE, qrCodeIcon);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static void saida(ImageIcon saidaIcon) {
        JOptionPane.showMessageDialog(null,
                "Voce pressionou o botao 'Cancelar'. O programa sera encerrado.",
                null, JOptionPane.WARNING_MESSAGE, saidaIcon);
    }

    public static boolean isValidIPv4Address(final String ipAddress) {
        // Expressão regular para validar um endereço IPv4
        final String regex = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        // Compila a expressão regular
        final Pattern pattern = Pattern.compile(regex);
        // Compara a string de entrada com a expressão regular
        final Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    public static boolean apresentation(Object[] options, ImageIcon equipamentoIcon,
            ImageIcon saidaIcon,
            ImageIcon erroIcon) {
        boolean condition = false;
        do {
            int result = JOptionPane.showOptionDialog(null, "Bem Vindo ao AUTO-CONFIG-G16!", "faber222",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, equipamentoIcon, options, options[0]);
            switch (result) {
                case 0:
                    condition = true;
                    return true;
                case 1:
                    OltG16.mostrarCriador();
                    break;
                default:
                    OltG16.saida(saidaIcon);
                    System.exit(0);
                    break;
            }

        } while (!condition);
        return false;

    }

    public OltG16() {
    }

    public void getIpFromUser(OltG16 oltClient, ImageIcon saidaIcon, ImageIcon erroIcon) {
        do {
            // Solicita ao usuário que insira o IP do AP
            oltClient.setIp(JOptionPane.showInputDialog("Digite o IP da Olt:"));
            if (oltClient.getIp() == null) {
                saida(saidaIcon);
                System.exit(0);
            }
            if (!isValidIPv4Address(oltClient.getIp())) {
                JOptionPane.showMessageDialog(null,
                        "Entrada invalida. Por favor, insira um ip valido 0-255", "Erro",
                        JOptionPane.ERROR_MESSAGE, erroIcon);
            }
        } while (!isValidIPv4Address(oltClient.getIp()));
    }

    public void getUserAndPwd(OltG16 oltClient, ImageIcon saidaIcon, ImageIcon erroIcon) {
        do {
            // Solicita ao usuário que insira o IP do AP
            oltClient.setUser(JOptionPane.showInputDialog("Digite o usuario da Olt:"));
            if (oltClient.getUser() == null) {
                saida(saidaIcon);
                System.exit(0);
            }
            oltClient.setPasswd(JOptionPane.showInputDialog("Digite a senha da Olt:"));
            if (oltClient.getPasswd() == null) {
                saida(saidaIcon);
                System.exit(0);
            }
        } while (oltClient.getPasswd() == null && oltClient.getUser() == null);
    }

    public static boolean isValidAimVlanLineRange(String aimVlanLineRange, ImageIcon erroIcon, int range) {
        // Validando o formato do range
        if (aimVlanLineRange.matches("\\d+-\\d+") && range > 2) {
            String[] partes = aimVlanLineRange.split("-");
            int inicio = Integer.parseInt(partes[0]);
            int fim = Integer.parseInt(partes[1]);

            // Validando se são 16 números e se não ultrapassam 4095
            if (fim - inicio + 1 == range && fim <= 4095) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Range inválido. Deve conter " + range + " números e não ultrapassar 4095.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE, erroIcon);
                return false;
            }
        } else if (range == 1 || range == 2) {
            int inicio = Integer.parseInt(aimVlanLineRange);

            // Validando se são 16 números e se não ultrapassam 4095
            if (inicio >= 1 && inicio <= 4095) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Vlan inválida. Deve conter " + range + " número, ser maior que 0 e não ultrapassar 4095.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE, erroIcon);
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "Formato inválido. Use o formato: vlan",
                "Erro", JOptionPane.ERROR_MESSAGE, erroIcon);
        return false;
    }

    public static List<String> getVlanClient(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon, int range) {
        List<String> vlan = new ArrayList<String>();
        String input = new String();
        String message;
        if (range == 1) {
            message = "Qual a vlan?:";
        } else {
            message = "Qual o range de vlan?: Use o formato: inicio-fim";
        }

        do {
            input = (String) JOptionPane.showInputDialog(message);
            if (input == null) {
                OltG16.saida(saidaIcon);
                System.exit(0);
            }
        } while (!OltG16.isValidAimVlanLineRange(input, erroIcon, range));

        if (range != 1) {
            String[] partes = input.split("-");
            int inicio = Integer.parseInt(partes[0]);
            int fim = Integer.parseInt(partes[1]);

            for (int j = inicio; j <= fim; j++) {
                vlan.add(String.valueOf(j));
            }
        } else {
            vlan.add(input);
        }

        return vlan;
    }

    public static List<String> getAimProfileVlan(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon, int range) {
        List<String> aimProfileVlan = new ArrayList<String>();
        String input = new String();
        do {
            input = (String) JOptionPane.showInputDialog("Qual o range do profile vlan?: Use o formato: inicio-fim");
            if (input == null) {
                OltG16.saida(saidaIcon);
                System.exit(0);
            }
        } while (!OltG16.isValidAimVlanLineRange(input, erroIcon, range));

        if (range != 1) {
            String[] partes = input.split("-");
            int inicio = Integer.parseInt(partes[0]);
            int fim = Integer.parseInt(partes[1]);

            for (int j = inicio; j <= fim; j++) {
                aimProfileVlan.add(String.valueOf(j));
            }
        } else {
            aimProfileVlan.add(input);
        }

        return aimProfileVlan;
    }

    public static List<String> getAimProfileLine(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon, int range) {
        List<String> aimProfileLine = new ArrayList<String>();
        String input = new String();
        do {
            input = (String) JOptionPane.showInputDialog("Qual o range do profile Line?: Use o formato: inicio-fim");
            if (input == null) {
                OltG16.saida(saidaIcon);
                System.exit(0);
            }
        } while (!OltG16.isValidAimVlanLineRange(input, erroIcon, range));

        if (range != 2) {
            String[] partes = input.split("-");
            int inicio = Integer.parseInt(partes[0]);
            int fim = Integer.parseInt(partes[1]);

            for (int j = inicio; j <= fim; j++) {
                aimProfileLine.add(String.valueOf(j));
            }
        } else {
            String[] partes = input.split("-");

            aimProfileLine.add(partes[0]);
            aimProfileLine.add(partes[1]);
        }
        return aimProfileLine;
    }

    public static String getInterfaceEth(ImageIcon equipamentoIcon, ImageIcon saidaIcon, ImageIcon erroIcon,
            String[] interfaceEth) {
        String input = new String();
        Object[] interfaces = interfaceEth;
        do {
            // Solicita ao usuário que escolha a interface eth
            input = (String) JOptionPane.showInputDialog(null, "Por favor, escolha a interface Uplink da olt:",
                    "faber222",
                    JOptionPane.QUESTION_MESSAGE, equipamentoIcon, interfaces, interfaces[0]);
            if (input == null) {
                OltG16.saida(saidaIcon);
                System.exit(0);
            }
            try {
                return input;
            } catch (final NumberFormatException e) {
                return null;
            }
        } while (input == null);
    }

    public static String getModelConfiguration(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon, String[] modelConfiguration) {
        String input = new String();
        Object[] configuration = modelConfiguration;
        do {
            // Solicita ao usuário que escolha o modo do auto-config
            input = (String) JOptionPane.showInputDialog(null, "Por favor, escolha o tipo de auto-config:",
                    "faber222",
                    JOptionPane.QUESTION_MESSAGE, equipamentoIcon, configuration, configuration[0]);
            if (input == null) {
                OltG16.saida(saidaIcon);
                System.exit(0);
            }
            try {
                return input;
            } catch (final NumberFormatException e) {
                return null;
            }
        } while (input == null);
    }

    public void start() {
        OltG16 newOltG16 = new OltG16();
        Object[] options = { "Avancar", "Autor", "Cancelar" };

        ClassLoader classLoader = OltG16.class.getClassLoader();
        ImageIcon equipamentoIcon = new ImageIcon(classLoader.getResource("equipamento.png"));
        ImageIcon ipIcon = new ImageIcon(classLoader.getResource("ip.png"));
        ImageIcon saidaIcon = new ImageIcon(classLoader.getResource("saida.png"));
        ImageIcon erroIcon = new ImageIcon(classLoader.getResource("erro.png"));

        OltG16.apresentation(options, equipamentoIcon, saidaIcon, erroIcon);

        final String[] modelosInterface = {
                "interface ethernet 1/1",
                "interface ethernet 1/2",
                "interface ethernet 1/3",
                "interface ethernet 1/4",
                "interface ethernet 2/1",
                "interface ethernet 2/2"
        };

        final String[] configuracoes = {
                "Uma vlan por pon",
                "Uma vlan para todas as pon",
                "Uma vlan por pon untagged"
        };

        String interfaceEth = OltG16.getInterfaceEth(equipamentoIcon, saidaIcon, erroIcon, modelosInterface);
        String modelConfiguration = OltG16.getModelConfiguration(equipamentoIcon, saidaIcon, erroIcon, configuracoes);
        int rangeVlan;
        int rangeAimVlan;
        int rangeAimLine;
        
        if (modelConfiguration == configuracoes[1]) {
            rangeVlan = rangeAimVlan = 1;
            rangeAimLine = 2;
        } else {
            rangeVlan = rangeAimVlan = 16;
            rangeAimLine = 32;
        }
        List<String> arrayVlan = OltG16.getVlanClient(equipamentoIcon, saidaIcon, erroIcon, rangeVlan);
        List<String> arrayAimVlan = OltG16.getAimProfileVlan(equipamentoIcon, saidaIcon, erroIcon, rangeAimVlan);
        List<String> arrayAimLine = OltG16.getAimProfileLine(equipamentoIcon, saidaIcon, erroIcon, rangeAimLine);

        ConfigGenerator configGeneratorG16 = new ConfigGenerator(arrayVlan, arrayAimVlan, interfaceEth, arrayAimLine,
                null);
        // configuração final para definir o acesso a olt

        Telnet telnetAccess = new Telnet(newOltG16.getIp(), newOltG16.getPort(), newOltG16.getUser(),
                newOltG16.getPasswd(), null);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
