/**
 * @author faber222
 * @since 2024
*/
package engtelecom.product;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import engtelecom.access.Telnet;
import engtelecom.config.ConfigGenerator;
import engtelecom.swingType.OltGponMenu;

/**
 * Objeto que representa a OLT G08
 */
public class OltGpon extends Olt {

    /**
     * Construtor padrão
     * 
     * @param slotLength Tamanho dos slots que a olt possui, pode ser 8 ou 16
     */
    public OltGpon(final int slotLength) {
        this.setSlotLength(slotLength);
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
    public boolean isValidAimVlanLineRange(final String aimVlanLineRange, final ImageIcon erroIcon, final int range) {
        // Verifica se é um range no formato inicio-fim
        if (range == 1) {
            if (aimVlanLineRange.matches("^[1-9]\\d*$")) {
                // Se o range for 1 e o formato for um inteiro
                final int inicio = Integer.parseInt(aimVlanLineRange);

                // Validando se é um número válido e se não ultrapassa 4095
                if (inicio >= 1 && inicio <= 4095) {
                    return true;
                } else {
                    // Exibe mensagem de erro se a VLAN não atender às condições
                    JOptionPane.showMessageDialog(null,
                            "Valor invalido. Deve conter " + range + " numero, ser maior que 0 e nao ultrapassar 4095.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE, erroIcon);
                    return false;
                }
            } else {
                // Exibe mensagem de erro se a VLAN não atender às condições
                JOptionPane.showMessageDialog(null,
                        "Valor invalido. Deve conter apenas numero(s) inteiro(s) nao nulo, maior que 0 e menor que 4095.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE, erroIcon);
                return false;
            }
        } else {
            if (aimVlanLineRange.matches("^[1-9]\\d*-\\d*[1-9]\\d*$")) {
                final String[] partes = aimVlanLineRange.split("-");

                final int inicio = Integer.parseInt(partes[0]);
                final int fim = Integer.parseInt(partes[1]);

                // Validando se são "range" números e se não ultrapassam 4095
                if (fim - inicio + 1 == range && fim <= 4095) {
                    return true;
                } else {
                    // Exibe mensagem de erro se o range não atender às condições
                    JOptionPane.showMessageDialog(null,
                            "Range invalido. Deve conter " + range + " numeros e nao ultrapassar 4095.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE, erroIcon);
                    return false;
                }
            } else if (aimVlanLineRange.matches("^([1-9]\\d*,)+[1-9]\\d*$")) {
                // Verifica se é uma lista de valores separados por vírgula
                final String[] valores = aimVlanLineRange.split(",");

                if (valores.length == range) {
                    // Verifica se há exatamente o número correto de valores
                    for (String valor : valores) {
                        int num = Integer.parseInt(valor);
                        if (num <= 0) {
                            // Exibe mensagem de erro se algum valor não for um número inteiro não nulo
                            JOptionPane.showMessageDialog(null,
                                    "Valor " + valor + " invalido. Deve ser um numero inteiro nao nulo.",
                                    "Erro",
                                    JOptionPane.ERROR_MESSAGE, erroIcon);
                            return false;
                        }
                    }
                    return true;
                } else {
                    // Exibe mensagem de erro se o número de valores não for igual ao range
                    JOptionPane.showMessageDialog(null,
                            "Deve conter exatamente " + range
                                    + " valores separados por virgula.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE, erroIcon);
                    return false;
                }
            }
        }
        // Exibe mensagem de erro se o range não atender às condições
        JOptionPane.showMessageDialog(null,
                "Valor invalido. Deve conter " + range + " numeros e nao ultrapassar 4095.",
                "Erro",
                JOptionPane.ERROR_MESSAGE, erroIcon);
        return false;
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
    public List<String> getAimProfileVlan(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon,
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
            message = "Qual o range do profile vlan?: Use o formato: inicio-fim ou digite {1,2,3,...,n}";
        }

        // Loop para garantir que a entrada do usuário seja válida.
        do {
            input = (String) JOptionPane.showInputDialog(message);

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                this.saida(saidaIcon);
                System.exit(0);
            }
        } while (!this.isValidAimVlanLineRange(input, erroIcon, range));

        // Processa a entrada do usuário com base no intervalo especificado.
        if (range != 1) {
            // Se o intervalo não for 1, divide a entrada e adiciona os valores à lista.
            final String[] partes;
            // Se o intervalo não for 1, divide a entrada e adiciona os valores à lista.
            if (input.matches("^([1-9]\\d*,)+[1-9]\\d*$")) {
                partes = input.split(",");
                for (String valor : partes) {
                    aimProfileVlan.add(valor);
                }
            } else {
                partes = input.split("-");
                final int inicio = Integer.parseInt(partes[0]);
                final int fim = Integer.parseInt(partes[1]);

                for (int j = inicio; j <= fim; j++) {
                    aimProfileVlan.add(String.valueOf(j));
                }
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
    public List<String> getAimProfileLine(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon,
            final ImageIcon erroIcon, final int range) {
        // Lista que armazenará o perfil de linha de objetivo.
        final List<String> aimProfileLine = new ArrayList<String>();
        // String para armazenar a entrada do usuário.
        String input = new String();

        // Loop para garantir que a entrada do usuário seja válida.
        do {
            input = (String) JOptionPane.showInputDialog(
                    "Qual o range do profile Line?: Use o formato: inicio-fim ou digite {1,2,3,...,n}");

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                this.saida(saidaIcon);
                System.exit(0);
            }
        } while (!this.isValidAimVlanLineRange(input, erroIcon, range));

        // Processa a entrada do usuário com base no intervalo especificado.
        final String[] partes;
        
        if (input.matches("^([1-9]\\d*,)+[1-9]\\d*$")) {
            partes = input.split(",");
            for (String valor : partes) {
                aimProfileLine.add(valor);
            }
        } else {
            partes = input.split("-");
            final int inicio = Integer.parseInt(partes[0]);
            final int fim = Integer.parseInt(partes[1]);

            for (int j = inicio; j <= fim; j++) {
                aimProfileLine.add(String.valueOf(j));
            }
        }

        // Retorna a lista contendo o perfil de linha de objetivo.
        return aimProfileLine;
    }

    /**
     * Método responsável por iniciar o processo de configuração da OLT G16.
     * Aqui é onde a "magia" acontece, guiando o usuário através da configuração.
     */
    public void start() {

        // Carrega os ícones necessários para o diálogo
        final ClassLoader classLoader = OltGpon.class.getClassLoader();
        final ImageIcon equipamentoIcon = new ImageIcon(classLoader.getResource("equipamento.png"));
        // final ImageIcon ipIcon = new ImageIcon(classLoader.getResource("ip.png"));
        final ImageIcon saidaIcon = new ImageIcon(classLoader.getResource("saida.png"));
        final ImageIcon erroIcon = new ImageIcon(classLoader.getResource("erro.png"));
        String nomeArq = "scriptG08.txt";

        if (this.getSlotLength() == 16) {
            nomeArq = "scriptG16.txt";
        }

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

        if (this.getSlotLength() == 16) {
            interfaceGpon = interfaceGponG16;
        }

        final String[] defaultCpeType = {
                "bridge",
                "router"
        };

        final String regex = "^[1-9]\\d*-\\d*[1-9]\\d*$";
        OltGponMenu OltGponMenu = new OltGponMenu();
        OltGponMenu.start();
        

        // Obtém informações do usuário para a configuração
        final String interfaceEth = this.getInterfaceEth(equipamentoIcon, saidaIcon, erroIcon, modelosInterface);
        final String modelConfiguration = this.getModelConfiguration(equipamentoIcon, saidaIcon, erroIcon,
                configuracoes);
        final String defaultCpe = this.getDefaultCpeType(equipamentoIcon, saidaIcon, erroIcon, defaultCpeType);

        int rangeVlan;
        int rangeAimVlan;
        int rangeAimLine;

        if (modelConfiguration.equals(configuracoes[1])) {
            rangeVlan = rangeAimVlan = 1;
            rangeAimLine = 2;
        } else {
            rangeVlan = rangeAimVlan = this.getSlotLength();
            rangeAimLine = this.getSlotLength() * 2;
        }

        // Obtém as listas necessárias para a configuração
        final List<String> arrayVlan = this.getVlanClient(equipamentoIcon, saidaIcon, erroIcon, rangeVlan);
        final List<String> arrayAimVlan = this.getAimProfileVlan(equipamentoIcon, saidaIcon, erroIcon,
                rangeAimVlan);
        final List<String> arrayAimLine = this.getAimProfileLine(equipamentoIcon, saidaIcon, erroIcon,
                rangeAimLine);

        // Cria um objeto ConfigGenerator para gerar o script de configuração
        final ConfigGenerator configGenerator = new ConfigGenerator(arrayVlan, arrayAimVlan, interfaceEth,
                arrayAimLine, deviceType, modelConfiguration, configuracoes,
                defaultCpe, interfaceGpon, defaultCpeType);

        configGenerator.createScript(nomeArq, this.getSlotLength());

        this.getIpFromUser(saidaIcon, erroIcon);
        this.getPortFromUser(saidaIcon, erroIcon);
        this.getUserAndPwd(saidaIcon, erroIcon);

        // Configuração final para definir o acesso à OLT
        final Telnet telnetAccess = new Telnet(getIp(), getPort(), getUser(), getPasswd());
        telnetAccess.oltAccess(nomeArq);
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String getPasswd() {
        return passwd;
    }

    @Override
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public int getSlotLength() {
        return slotLength;
    }

    @Override
    public void setSlotLength(int slotLength) {
        this.slotLength = slotLength;
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

    /**
     * Obtém o endereço IP da OLT a partir do usuário.
     * 
     * @param saidaIcon Ícone para caixa de diálogo de saída.
     * @param erroIcon  Ícone de erro para caixa de diálogo.
     */
    @Override
    public void getIpFromUser(ImageIcon saidaIcon, ImageIcon erroIcon) {
        // Loop para garantir que o usuário forneça um endereço IP válido.
        do {
            // Solicita ao usuário que insira o endereço IP da OLT.
            this.setIp(JOptionPane.showInputDialog("Digite o IP da OLT:"));

            // Verifica se o usuário cancelou a operação.
            if (this.getIp() == null) {
                saida(saidaIcon);
                System.exit(0);
            }

            // Verifica se o endereço IP inserido é válido.
            if (!isValidIPv4Address(this.getIp())) {
                JOptionPane.showMessageDialog(null,
                        "Entrada invalida. Por favor, insira um endereço IP valido (0-255).", "Erro",
                        JOptionPane.ERROR_MESSAGE, erroIcon);
            }
        } while (!isValidIPv4Address(this.getIp()));
    }

    /**
     * Obtém a porta de acesso Telnet da OLT a partir do usuário.
     * 
     * @param saidaIcon Ícone para caixa de diálogo de saída.
     * @param erroIcon  Ícone de erro para caixa de diálogo.
     */
    @Override
    public void getPortFromUser(ImageIcon saidaIcon, ImageIcon erroIcon) {
        String port;
        do {
            port = JOptionPane.showInputDialog("Digite a porta de acesso Telnet da OLT:");
        } while (!port.matches("^[1-9]\\d*$"));
        this.setPort(Integer.parseInt(port));
    }

    /**
     * Obtém o nome de usuário e senha da OLT a partir do usuário.
     * 
     * @param saidaIcon Ícone para caixa de diálogo de saída.
     * @param erroIcon  Ícone de erro para caixa de diálogo.
     */
    @Override
    public void getUserAndPwd(ImageIcon saidaIcon, ImageIcon erroIcon) {
        // Loop para garantir que o usuário forneça tanto o nome de usuário quanto a
        // senha.
        do {
            // Solicita ao usuário que insira o nome de usuário da OLT.
            this.setUser(JOptionPane.showInputDialog("Digite o usuario da OLT:"));

            // Verifica se o usuário cancelou a operação.
            if (this.getUser() == null) {
                saida(saidaIcon);
                System.exit(0);
            }

            // Solicita ao usuário que insira a senha da OLT.
            this.setPasswd(JOptionPane.showInputDialog("Digite a senha da OLT:"));

            // Verifica se o usuário cancelou a operação.
            if (this.getPasswd() == null) {
                saida(saidaIcon);
                System.exit(0);
            }

        } while (this.getPasswd() == null && this.getUser() == null);
    }

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
    @Override
    public List<String> getVlanClient(ImageIcon equipamentoIcon, ImageIcon saidaIcon, ImageIcon erroIcon, int range) {
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
            message = "Qual o range de vlan?: Use o formato: inicio-fim ou digite {1,2,3,...,n}";
        }

        // Loop para garantir que a entrada do usuário seja válida.
        do {
            input = (String) JOptionPane.showInputDialog(message);

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                this.saida(saidaIcon);
                System.exit(0);
            }
        } while (!this.isValidAimVlanLineRange(input, erroIcon, range));

        // Processa a entrada do usuário com base no intervalo especificado.
        if (range != 1) {
            final String[] partes;
            // Se o intervalo não for 1, divide a entrada e adiciona os valores à lista.
            if (input.matches("^([1-9]\\d*,)+[1-9]\\d*$")) {
                partes = input.split(",");
                for (String valor : partes) {
                    vlan.add(valor);
                }
            } else {
                partes = input.split("-");
                final int inicio = Integer.parseInt(partes[0]);
                final int fim = Integer.parseInt(partes[1]);

                for (int j = inicio; j <= fim; j++) {
                    vlan.add(String.valueOf(j));
                }
            }
        } else {
            // Se o intervalo for 1, adiciona a entrada diretamente à lista.
            vlan.add(input);
        }

        // Retorna a lista contendo as informações de VLAN para o cliente.
        return vlan;
    }

    /**
     * Obtém o tipo padrão de CPE (Customer Premises Equipment) a partir do usuário.
     * 
     * @param equipamentoIcon Ícone do equipamento.
     * @param saidaIcon       Ícone de saída.
     * @param erroIcon        Ícone de erro.
     * @param defaultCpeType  Array de strings representando os tipos padrão de CPE.
     * @return O tipo padrão de CPE escolhido pelo usuário.
     */
    @Override
    public String getDefaultCpeType(ImageIcon equipamentoIcon, ImageIcon saidaIcon, ImageIcon erroIcon,
            String[] defaultCpeType) {
        String input = new String();
        final Object[] configuration = defaultCpeType;

        // Loop para garantir que o usuário forneça uma escolha válida do modelo de
        // configuração.
        do {
            // Solicita ao usuário que escolha o tipo de operação da ont de terceiros.
            input = (String) JOptionPane.showInputDialog(null, "CPEs de terceiros, em:",
                    "OLT-AUTO-CONFIG",
                    JOptionPane.QUESTION_MESSAGE, equipamentoIcon, configuration, configuration[0]);

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                this.saida(saidaIcon);
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
     * Obtém a configuração do modelo a partir do usuário.
     * 
     * @param equipamentoIcon    Ícone do equipamento.
     * @param saidaIcon          Ícone de saída.
     * @param erroIcon           Ícone de erro.
     * @param modelConfiguration Array de strings representando as configurações de
     *                           modelo.
     * @return A configuração do modelo escolhida pelo usuário.
     */
    @Override
    public String getModelConfiguration(ImageIcon equipamentoIcon, ImageIcon saidaIcon, ImageIcon erroIcon,
            String[] modelConfiguration) {
        String input = new String();
        final Object[] configuration = modelConfiguration;

        // Loop para garantir que o usuário forneça uma escolha válida do modelo de
        // configuração.
        do {
            // Solicita ao usuário que escolha o tipo de auto-config.
            input = (String) JOptionPane.showInputDialog(null, "Por favor, escolha o tipo de auto-config:",
                    "OLT-AUTO-CONFIG",
                    JOptionPane.QUESTION_MESSAGE, equipamentoIcon, configuration, configuration[0]);

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                this.saida(saidaIcon);
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
     * Obtém a interface Ethernet Uplink da OLT a partir do usuário.
     * 
     * @param equipamentoIcon Ícone do equipamento.
     * @param saidaIcon       Ícone de saída.
     * @param erroIcon        Ícone de erro.
     * @param interfaceEth    Array de strings representando as interfaces Ethernet.
     * @return A interface Ethernet Uplink escolhida pelo usuário.
     */
    @Override
    public String getInterfaceEth(ImageIcon equipamentoIcon, ImageIcon saidaIcon, ImageIcon erroIcon,
            String[] interfaceEth) {
        String input = new String();
        final Object[] interfaces = interfaceEth;

        // Loop para garantir que o usuário forneça uma escolha válida da interface
        // Ethernet.
        do {
            // Solicita ao usuário que escolha a interface Ethernet Uplink da OLT.
            input = (String) JOptionPane.showInputDialog(null, "Por favor, escolha a interface Uplink da OLT:",
                    "OLT-AUTO-CONFIG",
                    JOptionPane.QUESTION_MESSAGE, equipamentoIcon, interfaces, interfaces[0]);

            // Verifica se o usuário cancelou a operação.
            if (input == null) {
                this.saida(saidaIcon);
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

}
