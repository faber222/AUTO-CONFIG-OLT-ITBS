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

/**
 * Objeto que representa a OLT G16
 */
public class OltG16 {
    /**
     * Variável que armazena o endereço IP da conexão.
     */
    private String ip;
    /**
     * Variável que armazena a porta da conexão (pode ser Integer ou int, dependendo
     * da necessidade).
     */
    private Integer port;
    /**
     * Variável que armazena a senha da conexão.
     */
    private String passwd;
    /**
     * Variável que armazena o nome de usuário da conexão.
     */
    private String user;

    /**
     * Exibe informações sobre o criador e um QR code contendo o link do GitHub.
     */
    public static void mostrarCriador() {
        // Informações sobre o criador
        String nomeCriador = "Faber";
        String githubLink = "https://github.com/faber222"; // Substitua com o link do GitHub do criador

        // Configura as opções de codificação do QR code
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.MARGIN, 2);

        try {
            // Gera o QR code
            BitMatrix bitMatrix = new QRCodeWriter().encode(githubLink, BarcodeFormat.QR_CODE, 100, 100, hints);

            // Converte a matriz de bits do QR code em uma imagem BufferedImage
            BufferedImage bufferedImage = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                for (int y = 0; y < bitMatrix.getHeight(); y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }

            // Cria uma ImageIcon a partir do QR code
            ImageIcon qrCodeIcon = new ImageIcon(bufferedImage);

            // Mensagem informativa com detalhes sobre o criador e o link do GitHub
            String mensagem = "O criador deste código: " + nomeCriador + "\n\n" +
                    "Você pode encontrá-lo no GitHub em:\n" + githubLink;

            // Exibe a mensagem com a ImageIcon contendo o QR code
            JOptionPane.showMessageDialog(
                    null, mensagem, "Criador faber222 e Link do GitHub", JOptionPane.INFORMATION_MESSAGE, qrCodeIcon);
        } catch (WriterException e) {
            // Lida com exceções relacionadas à geração do QR code
            e.printStackTrace();
        }
    }

    /**
     * Exibe uma mensagem de saída quando o usuário pressiona o botão "Cancelar".
     * 
     * @param saidaIcon Ícone a ser exibido na mensagem de saída.
     */
    public static void saida(ImageIcon saidaIcon) {
        // Exibe uma caixa de diálogo com uma mensagem de aviso indicando que o programa
        // será encerrado.
        JOptionPane.showMessageDialog(null,
                "Você pressionou o botão 'Cancelar'. O programa será encerrado.",
                null, JOptionPane.WARNING_MESSAGE, saidaIcon);
    }

    /**
     * Verifica se a string fornecida representa um endereço IPv4 válido.
     * 
     * @param ipAddress A string contendo o endereço IPv4 a ser validado.
     * @return true se a string for um endereço IPv4 válido, false caso contrário.
     */
    public static boolean isValidIPv4Address(final String ipAddress) {
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

    /**
     * Apresenta um diálogo de boas-vindas e opções ao usuário.
     * 
     * @param options         Opções a serem exibidas no diálogo.
     * @param equipamentoIcon Ícone do equipamento.
     * @param saidaIcon       Ícone de saída.
     * @param erroIcon        Ícone de erro.
     * @return true se a condição for satisfeita, false caso contrário.
     */
    public static boolean presentation(Object[] options, ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon) {
        // Condição para controlar o loop.
        boolean condition = false;

        // Loop para apresentar o diálogo até que a condição seja satisfeita.
        do {
            // Exibe um diálogo de opções ao usuário.
            int result = JOptionPane.showOptionDialog(null, "Bem Vindo ao AUTO-CONFIG-G16!", "faber222",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, equipamentoIcon, options, options[0]);

            // Verifica a opção escolhida pelo usuário.
            switch (result) {
                case 0:
                    // Se a opção for 0, a condição é satisfeita e retorna true.
                    condition = true;
                    return true;
                case 1:
                    // Se a opção for 1, chama o método mostrarCriador().
                    OltG16.mostrarCriador();
                    break;
                default:
                    // Se nenhuma opção válida for escolhida, chama o método saida() e encerra o
                    // programa.
                    OltG16.saida(saidaIcon);
                    System.exit(0);
                    break;
            }

        } while (!condition);

        // Retorna false se o loop não for interrompido antes deste ponto.
        return false;
    }

    public OltG16() {
    }

    /**
     * Obtém o endereço IP da OLT a partir do usuário.
     * 
     * @param oltClient Instância da classe OltG16 para a qual o endereço IP será
     *                  definido.
     * @param saidaIcon Ícone de saída.
     * @param erroIcon  Ícone de erro.
     */
    public void getIpFromUser(OltG16 oltClient, ImageIcon saidaIcon, ImageIcon erroIcon) {
        // Loop para garantir que o usuário forneça um endereço IP válido.
        do {
            // Solicita ao usuário que insira o endereço IP da OLT.
            oltClient.setIp(JOptionPane.showInputDialog("Digite o IP da OLT:"));

            // Verifica se o usuário cancelou a operação.
            if (oltClient.getIp() == null) {
                saida(saidaIcon);
                System.exit(0);
            }

            // Verifica se o endereço IP inserido é válido.
            if (!isValidIPv4Address(oltClient.getIp())) {
                JOptionPane.showMessageDialog(null,
                        "Entrada inválida. Por favor, insira um endereço IP válido (0-255).", "Erro",
                        JOptionPane.ERROR_MESSAGE, erroIcon);
            }

        } while (!isValidIPv4Address(oltClient.getIp()));
    }

    /**
     * Obtém o nome de usuário e senha do usuário para a OLT.
     * 
     * @param oltClient Instância da classe OltG16 para a qual o usuário e a senha
     *                  serão definidos.
     * @param saidaIcon Ícone de saída.
     * @param erroIcon  Ícone de erro.
     */
    public void getUserAndPwd(OltG16 oltClient, ImageIcon saidaIcon, ImageIcon erroIcon) {
        // Loop para garantir que o usuário forneça tanto o nome de usuário quanto a
        // senha.
        do {
            // Solicita ao usuário que insira o nome de usuário da OLT.
            oltClient.setUser(JOptionPane.showInputDialog("Digite o usuário da OLT:"));

            // Verifica se o usuário cancelou a operação.
            if (oltClient.getUser() == null) {
                saida(saidaIcon);
                System.exit(0);
            }

            // Solicita ao usuário que insira a senha da OLT.
            oltClient.setPasswd(JOptionPane.showInputDialog("Digite a senha da OLT:"));

            // Verifica se o usuário cancelou a operação.
            if (oltClient.getPasswd() == null) {
                saida(saidaIcon);
                System.exit(0);
            }

        } while (oltClient.getPasswd() == null && oltClient.getUser() == null);
    }

    /**
     * Verifica se o range de VLAN é válido com base no formato e nos limites
     * permitidos.
     * 
     * @param aimVlanLineRange A string que representa o range de VLAN.
     * @param erroIcon         Ícone de erro.
     * @param range            O intervalo permitido para a VLAN.
     * @return true se o range for válido, false caso contrário.
     */
    public static boolean isValidAimVlanLineRange(String aimVlanLineRange, ImageIcon erroIcon, int range) {
        // Validando o formato do range
        if (aimVlanLineRange.matches("\\d+-\\d+") && range >= 2) {
            // Se o formato for válido e o range for maior ou igual a 2
            String[] partes = aimVlanLineRange.split("-");
            int inicio = Integer.parseInt(partes[0]);
            int fim = Integer.parseInt(partes[1]);

            // Validando se são "range" números e se não ultrapassam 4095
            if (fim - inicio + 1 == range && fim <= 4095) {
                return true;
            } else {
                // Exibe mensagem de erro se o range não atender às condições
                JOptionPane.showMessageDialog(null,
                        "Range inválido. Deve conter " + range + " números e não ultrapassar 4095.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE, erroIcon);
                return false;
            }
        } else if (range == 1) {
            // Se o range for 1
            int inicio = Integer.parseInt(aimVlanLineRange);

            // Validando se é um número válido e se não ultrapassa 4095
            if (inicio >= 1 && inicio <= 4095) {
                return true;
            } else {
                // Exibe mensagem de erro se a VLAN não atender às condições
                JOptionPane.showMessageDialog(null,
                        "Vlan inválida. Deve conter " + range + " número, ser maior que 0 e não ultrapassar 4095.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE, erroIcon);
                return false;
            }
        }
        // Se não atender a nenhum dos casos anteriores, exibe mensagem de formato
        // inválido
        JOptionPane.showMessageDialog(null, "Formato inválido. Use o formato: vlan",
                "Erro", JOptionPane.ERROR_MESSAGE, erroIcon);
        return false;
    }

    /**
     * Obtém informações de VLAN para um cliente a partir de uma entrada do usuário.
     * 
     * @param equipamentoIcon Ícone do equipamento.
     * @param saidaIcon       Ícone de saída.
     * @param erroIcon        Ícone de erro.
     * @param range           O intervalo permitido para a VLAN.
     * @return Uma lista de strings representando a VLAN para o cliente.
     */
    public static List<String> getVlanClient(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon, int range) {
        // Lista que armazenará as informações de VLAN para o cliente.
        List<String> vlan = new ArrayList<String>();
        // String para armazenar a entrada do usuário.
        String input = new String();
        // Mensagem a ser exibida com base no intervalo especificado.
        String message;

        // Determina a mensagem com base no intervalo.
        if (range == 1) {
            message = "Qual a vlan?:";
        } else {
            message = "Qual o range de vlan?: Use o formato: inicio-fim";
        }

        // Loop para garantir que a entrada do usuário seja válida.
        do {
            input = (String) JOptionPane.showInputDialog(message);

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                OltG16.saida(saidaIcon);
                System.exit(0);
            }
        } while (!OltG16.isValidAimVlanLineRange(input, erroIcon, range));

        // Processa a entrada do usuário com base no intervalo especificado.
        if (range != 1) {
            // Se o intervalo não for 1, divide a entrada e adiciona os valores à lista.
            String[] partes = input.split("-");
            int inicio = Integer.parseInt(partes[0]);
            int fim = Integer.parseInt(partes[1]);

            for (int j = inicio; j <= fim; j++) {
                vlan.add(String.valueOf(j));
            }
        } else {
            // Se o intervalo for 1, adiciona a entrada diretamente à lista.
            vlan.add(input);
        }

        // Retorna a lista contendo as informações de VLAN para o cliente.
        return vlan;
    }

    /**
     * Obtém um perfil de VLAN de objetivo (aim VLAN profile) a partir de uma
     * entrada do usuário.
     * 
     * @param equipamentoIcon Ícone do equipamento.
     * @param saidaIcon       Ícone de saída.
     * @param erroIcon        Ícone de erro.
     * @param range           O intervalo permitido para o perfil de VLAN de
     *                        objetivo.
     * @return Uma lista de strings representando o perfil de VLAN de objetivo.
     */
    public static List<String> getAimProfileVlan(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon, int range) {
        // Lista que armazenará o perfil de VLAN de objetivo.
        List<String> aimProfileVlan = new ArrayList<String>();
        // String para armazenar a entrada do usuário.
        String input = new String();

        // Loop para garantir que a entrada do usuário seja válida.
        do {
            input = (String) JOptionPane.showInputDialog("Qual o range do profile vlan?: Use o formato: inicio-fim");

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                OltG16.saida(saidaIcon);
                System.exit(0);
            }
        } while (!OltG16.isValidAimVlanLineRange(input, erroIcon, range));

        // Processa a entrada do usuário com base no intervalo especificado.
        if (range != 1) {
            // Se o intervalo não for 1, divide a entrada e adiciona os valores à lista.
            String[] partes = input.split("-");
            int inicio = Integer.parseInt(partes[0]);
            int fim = Integer.parseInt(partes[1]);

            for (int j = inicio; j <= fim; j++) {
                aimProfileVlan.add(String.valueOf(j));
            }
        } else {
            // Se o intervalo for 1, adiciona a entrada diretamente à lista.
            aimProfileVlan.add(input);
        }

        // Retorna a lista contendo o perfil de VLAN de objetivo.
        return aimProfileVlan;
    }

    /**
     * Obtém um perfil de linha de objetivo (aim profile line) a partir de uma
     * entrada do usuário.
     * 
     * @param equipamentoIcon Ícone do equipamento.
     * @param saidaIcon       Ícone de saída.
     * @param erroIcon        Ícone de erro.
     * @param range           O intervalo permitido para o perfil de linha de
     *                        objetivo.
     * @return Uma lista de strings representando o perfil de linha de objetivo.
     */
    public static List<String> getAimProfileLine(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon, int range) {
        // Lista que armazenará o perfil de linha de objetivo.
        List<String> aimProfileLine = new ArrayList<String>();
        // String para armazenar a entrada do usuário.
        String input = new String();

        // Loop para garantir que a entrada do usuário seja válida.
        do {
            input = (String) JOptionPane.showInputDialog("Qual o range do profile Line?: Use o formato: inicio-fim");

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                OltG16.saida(saidaIcon);
                System.exit(0);
            }
        } while (!OltG16.isValidAimVlanLineRange(input, erroIcon, range));

        // Processa a entrada do usuário com base no intervalo especificado.
        if (range == 32) {
            // Se o intervalo for 32, divide a entrada e adiciona os valores à lista.
            String[] partes = input.split("-");
            int inicio = Integer.parseInt(partes[0]);
            int fim = Integer.parseInt(partes[1]);

            for (int j = inicio; j <= fim; j++) {
                aimProfileLine.add(String.valueOf(j));
            }
        } else {
            // Se o intervalo não for 32, simplesmente divide a entrada e adiciona os
            // valores à lista.
            String[] partes = input.split("-");
            aimProfileLine.add(partes[0]);
            aimProfileLine.add(partes[1]);
        }

        // Retorna a lista contendo o perfil de linha de objetivo.
        return aimProfileLine;
    }

    /**
     * Obtém a interface Ethernet (Uplink) da OLT a partir do usuário.
     * 
     * @param equipamentoIcon Ícone do equipamento.
     * @param saidaIcon       Ícone de saída.
     * @param erroIcon        Ícone de erro.
     * @param interfaceEth    Um array contendo opções de interfaces Ethernet.
     * @return A interface Ethernet escolhida pelo usuário.
     */
    public static String getInterfaceEth(ImageIcon equipamentoIcon, ImageIcon saidaIcon, ImageIcon erroIcon,
            String[] interfaceEth) {
        String input = new String();
        Object[] interfaces = interfaceEth;

        // Loop para garantir que o usuário forneça uma escolha válida da interface
        // Ethernet.
        do {
            // Solicita ao usuário que escolha a interface Ethernet Uplink da OLT.
            input = (String) JOptionPane.showInputDialog(null, "Por favor, escolha a interface Uplink da OLT:",
                    "faber222",
                    JOptionPane.QUESTION_MESSAGE, equipamentoIcon, interfaces, interfaces[0]);

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                OltG16.saida(saidaIcon);
                System.exit(0);
            }

            try {
                // Retorna a escolha do usuário se for válida.
                return input;
            } catch (final NumberFormatException e) {
                // Lida com uma exceção (isso não parece ser relevante nesta lógica).
                return null;
            }
        } while (input == null);
    }

    /**
     * Obtém o modelo de configuração (tipo de auto-config) da OLT a partir do
     * usuário.
     * 
     * @param equipamentoIcon    Ícone do equipamento.
     * @param saidaIcon          Ícone de saída.
     * @param erroIcon           Ícone de erro.
     * @param modelConfiguration Um array contendo opções de modelos de
     *                           configuração.
     * @return O modelo de configuração escolhido pelo usuário.
     */
    public static String getModelConfiguration(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon, String[] modelConfiguration) {
        String input = new String();
        Object[] configuration = modelConfiguration;

        // Loop para garantir que o usuário forneça uma escolha válida do modelo de
        // configuração.
        do {
            // Solicita ao usuário que escolha o tipo de auto-config.
            input = (String) JOptionPane.showInputDialog(null, "Por favor, escolha o tipo de auto-config:",
                    "faber222",
                    JOptionPane.QUESTION_MESSAGE, equipamentoIcon, configuration, configuration[0]);

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                OltG16.saida(saidaIcon);
                System.exit(0);
            }

            try {
                // Retorna a escolha do usuário se for válida.
                return input;
            } catch (final NumberFormatException e) {
                // Lida com uma exceção (isso não parece ser relevante nesta lógica).
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

        OltG16.presentation(options, equipamentoIcon, saidaIcon, erroIcon);

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
        configGeneratorG16.createScript();

        // configuração final para definir o acesso a olt

        Telnet telnetAccess = new Telnet();
        telnetAccess.oltAccess();
    }

    /**
     * Obtém o endereço IP da instância.
     * 
     * @return O endereço IP.
     */
    public String getIp() {
        return ip;
    }

    /**
     * Define o endereço IP da instância.
     * 
     * @param ip O novo endereço IP a ser definido.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Obtém a porta da instância.
     * 
     * @return A porta.
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Define a porta da instância.
     * 
     * @param port A nova porta a ser definida.
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * Obtém a senha da instância.
     * 
     * @return A senha.
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * Define a senha da instância.
     * 
     * @param passwd A nova senha a ser definida.
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * Obtém o nome de usuário da instância.
     * 
     * @return O nome de usuário.
     */
    public String getUser() {
        return user;
    }

    /**
     * Define o nome de usuário da instância.
     * 
     * @param user O novo nome de usuário a ser definido.
     */
    public void setUser(String user) {
        this.user = user;
    }

}
