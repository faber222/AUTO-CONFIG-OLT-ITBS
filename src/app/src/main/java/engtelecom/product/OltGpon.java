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
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import engtelecom.access.Telnet;
import engtelecom.config.ConfigGenerator;

/**
 * Objeto que representa a OLT G08
 */
public class OltGpon {
    /**
     * Atributo que armazena o endereço IP da conexão.
     */
    private static String ip;

    /**
     * Atributo que armazena a porta da conexão (pode ser Integer ou int, dependendo
     * da necessidade).
     */
    private static Integer port;

    /**
     * Atributo que armazena a senha da conexão.
     */
    private static String passwd;

    /**
     * Atributo que armazena o nome de usuário da conexão.
     */
    private static String user;

    /**
     * Atributo que armazena o tamanho do slot gpon
     */
    private static int slotLength;

    /**
     * Exibe informações sobre o criador e um QR code contendo o link do GitHub.
     */
    public static void mostrarCriador() {
        // Informações sobre o criador
        final String nomeCriador = "Faber";
        final String githubLink = "https://github.com/faber222"; // Substitua com o link do GitHub do criador

        // Configura as opções de codificação do QR code
        final Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.MARGIN, 2);

        try {
            // Gera o QR code
            final BitMatrix bitMatrix = new QRCodeWriter().encode(githubLink, BarcodeFormat.QR_CODE, 100, 100, hints);

            // Converte a matriz de bits do QR code em uma imagem BufferedImage
            final BufferedImage bufferedImage = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                for (int y = 0; y < bitMatrix.getHeight(); y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }

            // Cria uma ImageIcon a partir do QR code
            final ImageIcon qrCodeIcon = new ImageIcon(bufferedImage);

            // Mensagem informativa com detalhes sobre o criador e o link do GitHub
            final String mensagem = "O criador deste código: " + nomeCriador + "\n\n" +
                    "Você pode encontrá-lo no GitHub em:\n" + githubLink;

            // Exibe a mensagem com a ImageIcon contendo o QR code
            JOptionPane.showMessageDialog(
                    null, mensagem, "Criador faber222 e Link do GitHub", JOptionPane.INFORMATION_MESSAGE, qrCodeIcon);
        } catch (final WriterException e) {
            // Lida com exceções relacionadas à geração do QR code
            e.printStackTrace();
        }
    }

    /**
     * Exibe uma mensagem de saída quando o usuário pressiona o botão "Cancelar".
     * 
     * @param saidaIcon Ícone a ser exibido na mensagem de saída.
     */
    public static void saida(final ImageIcon saidaIcon) {
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
    public static boolean presentation(final Object[] options, final ImageIcon equipamentoIcon,
            final ImageIcon saidaIcon, final ImageIcon erroIcon) {
        // Condição para controlar o loop.
        boolean condition = false;

        // Loop para apresentar o diálogo até que a condição seja satisfeita.
        do {
            // Exibe um diálogo de opções ao usuário.
            final int result = JOptionPane.showOptionDialog(null, "Bem Vindo ao AUTO-CONFIG!", "faber222",
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
                    OltGpon.mostrarCriador();
                    break;
                default:
                    // Se nenhuma opção válida for escolhida, chama o método saida() e encerra o
                    // programa.
                    OltGpon.saida(saidaIcon);
                    System.exit(0);
                    break;
            }

        } while (!condition);

        // Retorna false se o loop não for interrompido antes deste ponto.
        return false;
    }

    /**
     * Construtor padrão
     * 
     * @param slotLength Tamanho dos slots que a olt possui, pode ser 8 ou 16
     */
    public OltGpon(final int slotLength) {
        OltGpon.setSlotLength(slotLength);
    }

    /**
     * Obtém o endereço IP da OLT a partir do usuário.
     * 
     * @param saidaIcon Ícone de saída.
     * @param erroIcon  Ícone de erro.
     */
    public static void getIpFromUser(final ImageIcon saidaIcon, final ImageIcon erroIcon) {
        // Loop para garantir que o usuário forneça um endereço IP válido.
        do {
            // Solicita ao usuário que insira o endereço IP da OLT.
            OltGpon.setIp(JOptionPane.showInputDialog("Digite o IP da OLT:"));

            // Verifica se o usuário cancelou a operação.
            if (OltGpon.getIp() == null) {
                saida(saidaIcon);
                System.exit(0);
            }

            // Verifica se o endereço IP inserido é válido.
            if (!isValidIPv4Address(OltGpon.getIp())) {
                JOptionPane.showMessageDialog(null,
                        "Entrada inválida. Por favor, insira um endereço IP válido (0-255).", "Erro",
                        JOptionPane.ERROR_MESSAGE, erroIcon);
            }
        } while (!isValidIPv4Address(OltGpon.getIp()));
    }

    /**
     * Obtém a porta de acesso da OLT.
     * 
     * @param saidaIcon Ícone de saída.
     * @param erroIcon  Ícone de erro.
     */
    public static void getPortFromUser(final ImageIcon saidaIcon, final ImageIcon erroIcon) {
        String port;
        do {
            port = JOptionPane.showInputDialog("Digite a porta de acesso Telnet da OLT:");
        } while (!port.matches("^[1-9]\\d*$"));
        OltGpon.setPort(Integer.parseInt(port));
    }

    /**
     * Obtém o nome de usuário e senha do usuário para a OLT.
     * 
     * @param saidaIcon Ícone de saída.
     * @param erroIcon  Ícone de erro.
     */
    public static void getUserAndPwd(final ImageIcon saidaIcon, final ImageIcon erroIcon) {
        // Loop para garantir que o usuário forneça tanto o nome de usuário quanto a
        // senha.
        do {
            // Solicita ao usuário que insira o nome de usuário da OLT.
            OltGpon.setUser(JOptionPane.showInputDialog("Digite o usuário da OLT:"));

            // Verifica se o usuário cancelou a operação.
            if (OltGpon.getUser() == null) {
                saida(saidaIcon);
                System.exit(0);
            }

            // Solicita ao usuário que insira a senha da OLT.
            OltGpon.setPasswd(JOptionPane.showInputDialog("Digite a senha da OLT:"));

            // Verifica se o usuário cancelou a operação.
            if (OltGpon.getPasswd() == null) {
                saida(saidaIcon);
                System.exit(0);
            }

        } while (OltGpon.getPasswd() == null && OltGpon.getUser() == null);
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
    public static boolean isValidAimVlanLineRange(final String aimVlanLineRange, final ImageIcon erroIcon,
            final int range) {
        // Validando o formato do range
        if (aimVlanLineRange.matches("^[1-9]\\d*-\\d*[1-9]\\d*$") && range >= 2) {
            // Se o formato for válido e o range for maior ou igual a 2
            final String[] partes = aimVlanLineRange.split("-");
            final int inicio = Integer.parseInt(partes[0]);
            final int fim = Integer.parseInt(partes[1]);

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
        } else if (range == 1 && aimVlanLineRange.matches("^[1-9]\\d*$")) {
            // Se o range for 1 e o formato for um inteiro
            final int inicio = Integer.parseInt(aimVlanLineRange);

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
        JOptionPane.showMessageDialog(null, "Formato inválido",
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
    public static List<String> getVlanClient(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon,
            final ImageIcon erroIcon, final int range) {
        // Lista que armazenará as informações de VLAN para o cliente.

        final List<String> vlan = new ArrayList<String>();
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
                OltGpon.saida(saidaIcon);
                System.exit(0);
            }
        } while (!OltGpon.isValidAimVlanLineRange(input, erroIcon, range));

        // Processa a entrada do usuário com base no intervalo especificado.
        if (range != 1) {
            // Se o intervalo não for 1, divide a entrada e adiciona os valores à lista.
            final String[] partes = input.split("-");
            final int inicio = Integer.parseInt(partes[0]);
            final int fim = Integer.parseInt(partes[1]);

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
    public static List<String> getAimProfileVlan(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon,
            final ImageIcon erroIcon, final int range) {
        // Lista que armazenará o perfil de VLAN de objetivo.
        final List<String> aimProfileVlan = new ArrayList<String>();
        // String para armazenar a entrada do usuário.
        String input = new String();
        // Mensagem a ser exibida com base no intervalo especificado.
        String message;

        // Determina a mensagem com base no intervalo.
        if (range == 1) {
            message = "Qual o profile vlan?:";
        } else {
            message = "Qual o range do profile vlan?: Use o formato: inicio-fim";
        }

        // Loop para garantir que a entrada do usuário seja válida.
        do {
            input = (String) JOptionPane.showInputDialog(message);

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                OltGpon.saida(saidaIcon);
                System.exit(0);
            }
        } while (!OltGpon.isValidAimVlanLineRange(input, erroIcon, range));

        // Processa a entrada do usuário com base no intervalo especificado.
        if (range != 1) {
            // Se o intervalo não for 1, divide a entrada e adiciona os valores à lista.
            final String[] partes = input.split("-");
            final int inicio = Integer.parseInt(partes[0]);
            final int fim = Integer.parseInt(partes[1]);

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
    public static List<String> getAimProfileLine(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon,
            final ImageIcon erroIcon, final int range) {
        // Lista que armazenará o perfil de linha de objetivo.
        final List<String> aimProfileLine = new ArrayList<String>();
        // String para armazenar a entrada do usuário.
        String input = new String();

        // Loop para garantir que a entrada do usuário seja válida.
        do {
            input = (String) JOptionPane.showInputDialog("Qual o range do profile Line?: Use o formato: inicio-fim");

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                OltGpon.saida(saidaIcon);
                System.exit(0);
            }
        } while (!OltGpon.isValidAimVlanLineRange(input, erroIcon, range));

        // Processa a entrada do usuário com base no intervalo especificado.
        if (range == OltGpon.getSlotLength() * 2) {
            // Se o intervalo for 16 ou 32, divide a entrada e adiciona os valores à lista.
            final String[] partes = input.split("-");
            final int inicio = Integer.parseInt(partes[0]);
            final int fim = Integer.parseInt(partes[1]);

            for (int j = inicio; j <= fim; j++) {
                aimProfileLine.add(String.valueOf(j));
            }
        } else {
            // Se o intervalo não for 16 ou 32, simplesmente divide a entrada e adiciona os
            // valores à lista.
            final String[] partes = input.split("-");
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
    public static String getInterfaceEth(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon,
            final ImageIcon erroIcon,
            final String[] interfaceEth) {
        String input = new String();
        final Object[] interfaces = interfaceEth;

        // Loop para garantir que o usuário forneça uma escolha válida da interface
        // Ethernet.
        do {
            // Solicita ao usuário que escolha a interface Ethernet Uplink da OLT.
            input = (String) JOptionPane.showInputDialog(null, "Por favor, escolha a interface Uplink da OLT:",
                    "faber222",
                    JOptionPane.QUESTION_MESSAGE, equipamentoIcon, interfaces, interfaces[0]);

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                OltGpon.saida(saidaIcon);
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
    public static String getModelConfiguration(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon,
            final ImageIcon erroIcon, final String[] modelConfiguration) {
        String input = new String();
        final Object[] configuration = modelConfiguration;

        // Loop para garantir que o usuário forneça uma escolha válida do modelo de
        // configuração.
        do {
            // Solicita ao usuário que escolha o tipo de auto-config.
            input = (String) JOptionPane.showInputDialog(null, "Por favor, escolha o tipo de auto-config:",
                    "faber222",
                    JOptionPane.QUESTION_MESSAGE, equipamentoIcon, configuration, configuration[0]);

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                OltGpon.saida(saidaIcon);
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
     * Obtém o tipo padrão do equipamento CPE (Customer Premises Equipment).
     * 
     * @param equipamentoIcon Ícone para exibição do diálogo.
     * @param saidaIcon       Ícone para a mensagem de saída.
     * @param erroIcon        Ícone para a mensagem de erro.
     * @param defaultCpeType  Tipos padrão disponíveis para escolha.
     * @return O tipo padrão do equipamento CPE escolhido pelo usuário.
     *         {@code null} se a operação for cancelada.
     */
    public static String getDefaultCpeType(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon,
            final ImageIcon erroIcon, final String[] defaultCpeType) {
        String input = new String();
        final Object[] configuration = defaultCpeType;

        // Loop para garantir que o usuário forneça uma escolha válida do modelo de
        // configuração.
        do {
            // Solicita ao usuário que escolha o tipo de operação da ont de terceiros.
            input = (String) JOptionPane.showInputDialog(null, "CPEs de terceiros, em:",
                    "faber222",
                    JOptionPane.QUESTION_MESSAGE, equipamentoIcon, configuration, configuration[0]);

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                OltGpon.saida(saidaIcon);
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
     * Método responsável por iniciar o processo de configuração da OLT G16.
     * Aqui é onde a "magia" acontece, guiando o usuário através da configuração.
     */
    public void start() {
        final Object[] options = { "Avancar", "Autor", "Cancelar" };

        // Carrega os ícones necessários para o diálogo
        final ClassLoader classLoader = OltGpon.class.getClassLoader();
        final ImageIcon equipamentoIcon = new ImageIcon(classLoader.getResource("equipamento.png"));
        // final ImageIcon ipIcon = new ImageIcon(classLoader.getResource("ip.png"));
        final ImageIcon saidaIcon = new ImageIcon(classLoader.getResource("saida.png"));
        final ImageIcon erroIcon = new ImageIcon(classLoader.getResource("erro.png"));
        String nomeArq = "scriptG08.txt";

        if (OltGpon.getSlotLength() == 16) {
            nomeArq = "scriptG16.txt";
        }

        // Apresenta o diálogo inicial
        OltGpon.presentation(options, equipamentoIcon, saidaIcon, erroIcon);

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

        final String[] deviceType = {
                "i10-100",
                "i10-420",
                "i30-100",
                "i40-100",
                "i40-100-v2",
                "i40-201",
                "i40-211",
                "i40-421",
                "i41-100",
                "i41-201",
                "i41-211",
                "i41-421"
        };

        final String[] interfaceGponG08 = {
                "0/1",
                "0/2",
                "0/3",
                "0/4",
                "0/5",
                "0/6",
                "0/7",
                "0/8"
        };

        final String[] interfaceGponG16 = {
                "0/1",
                "0/2",
                "0/3",
                "0/4",
                "0/5",
                "0/6",
                "0/7",
                "0/8",
                "0/9",
                "0/10",
                "0/11",
                "0/12",
                "0/13",
                "0/14",
                "0/15",
                "0/16"
        };

        String[] interfaceGpon = interfaceGponG08;

        if (OltGpon.getSlotLength() == 16) {
            interfaceGpon = interfaceGponG16;
        }

        final String[] defaultCpeType = {
                "bridge",
                "router"
        };

        final String regex = "^[1-9]\\d*-\\d*[1-9]\\d*$";

        // Obtém informações do usuário para a configuração
        final String interfaceEth = OltGpon.getInterfaceEth(equipamentoIcon, saidaIcon, erroIcon, modelosInterface);
        final String modelConfiguration = OltGpon.getModelConfiguration(equipamentoIcon, saidaIcon, erroIcon,
                configuracoes);
        final String defaultCpe = OltGpon.getDefaultCpeType(equipamentoIcon, saidaIcon, erroIcon, defaultCpeType);

        int rangeVlan;
        int rangeAimVlan;
        int rangeAimLine;

        if (modelConfiguration.equals(configuracoes[1])) {
            rangeVlan = rangeAimVlan = 1;
            rangeAimLine = 2;
        } else {
            rangeVlan = rangeAimVlan = OltGpon.getSlotLength();
            rangeAimLine = OltGpon.getSlotLength() * 2;
        }

        // Obtém as listas necessárias para a configuração
        final List<String> arrayVlan = OltGpon.getVlanClient(equipamentoIcon, saidaIcon, erroIcon, rangeVlan);
        final List<String> arrayAimVlan = OltGpon.getAimProfileVlan(equipamentoIcon, saidaIcon, erroIcon, rangeAimVlan);
        final List<String> arrayAimLine = OltGpon.getAimProfileLine(equipamentoIcon, saidaIcon, erroIcon, rangeAimLine);

        // Cria um objeto ConfigGenerator para gerar o script de configuração
        final ConfigGenerator configGenerator = new ConfigGenerator(arrayVlan, arrayAimVlan, interfaceEth,
                arrayAimLine, deviceType, modelConfiguration, configuracoes,
                defaultCpe, interfaceGpon, defaultCpeType);

        configGenerator.createScript(nomeArq, OltGpon.getSlotLength());

        OltGpon.getIpFromUser(saidaIcon, erroIcon);
        OltGpon.getPortFromUser(saidaIcon, erroIcon);
        OltGpon.getUserAndPwd(saidaIcon, erroIcon);

        // Configuração final para definir o acesso à OLT
        final Telnet telnetAccess = new Telnet(getIp(), getPort(), getUser(), getPasswd());
        telnetAccess.oltAccess(nomeArq);
    }

    /**
     * Retorna o endereço IP configurado para conexão com a OLT.
     *
     * @return O endereço IP configurado.
     */
    public static String getIp() {
        return ip;
    }

    /**
     * Define o endereço IP para a conexão com a OLT.
     *
     * @param ip O endereço IP a ser configurado.
     */
    public static void setIp(final String ip) {
        OltGpon.ip = ip;
    }

    /**
     * Retorna a porta configurada para conexão com a OLT.
     *
     * @return A porta configurada.
     */
    public static Integer getPort() {
        return port;
    }

    /**
     * Define a porta para a conexão com a OLT.
     *
     * @param port A porta a ser configurada.
     */
    public static void setPort(final Integer port) {
        OltGpon.port = port;
    }

    /**
     * Retorna a senha configurada para a conexão com a OLT.
     *
     * @return A senha configurada.
     */
    public static String getPasswd() {
        return passwd;
    }

    /**
     * Define a senha para a conexão com a OLT.
     *
     * @param passwd A senha a ser configurada.
     */
    public static void setPasswd(final String passwd) {
        OltGpon.passwd = passwd;
    }

    /**
     * Retorna o nome de usuário configurado para a conexão com a OLT.
     *
     * @return O nome de usuário configurado.
     */
    public static String getUser() {
        return user;
    }

    /**
     * Define o nome de usuário para a conexão com a OLT.
     *
     * @param user O nome de usuário a ser configurado.
     */
    public static void setUser(final String user) {
        OltGpon.user = user;
    }

    /**
     * Retorna o valor do slotLength
     * 
     * @return Inteiro contendo quantos slots gpon tem
     */
    public static int getSlotLength() {
        return slotLength;
    }

    /**
     * Define o valor do slot
     * 
     * @param slotLength Valor inteiro, deve ser 8 ou 16
     */
    public static void setSlotLength(final int slotLength) {
        OltGpon.slotLength = slotLength;
    }

}