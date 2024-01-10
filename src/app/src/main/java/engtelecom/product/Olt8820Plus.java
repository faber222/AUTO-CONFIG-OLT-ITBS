/**
 * Criado por faber222
 * Ano 2024
*/
package engtelecom.product;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import engtelecom.access.Telnet8820Plus;
import engtelecom.config.ConfigGenerator8820Plus;

public class Olt8820Plus extends Olt {

    public Olt8820Plus(final int slotLength) {
        this.slotLength = slotLength;
    }

    public boolean isValidVlanRange(final String aimVlanLineRange, final ImageIcon erroIcon,
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
                        "Range invalido. Deve conter " + range + " numeros e nao ultrapassar 4095.",
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
                        "Vlan invalida. Deve conter " + range + " numero, ser maior que 0 e nao ultrapassar 4095.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE, erroIcon);
                return false;
            }
        }
        // Se não atender a nenhum dos casos anteriores, exibe mensagem de formato
        // inválido
        JOptionPane.showMessageDialog(null, "Formato invalido",
                "Erro", JOptionPane.ERROR_MESSAGE, erroIcon);
        return false;
    }

    public String getBridgeInterfaceUplink(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon,
            final ImageIcon erroIcon, final String[] modelConfiguration) {
        String input = new String();
        final Object[] configuration = modelConfiguration;

        // Loop para garantir que o usuário forneça uma escolha válida do modelo de
        // configuração.
        do {
            // Solicita ao usuário que escolha o tipo de auto-config.
            input = (String) JOptionPane.showInputDialog(null, "Por favor, escolha o tipo de bridge uplink:",
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

    public String getBridgeInterfaceUplinkVlanMode(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon,
            final ImageIcon erroIcon, final String[] modelConfiguration) {
        String input = new String();
        final Object[] configuration = modelConfiguration;

        // Loop para garantir que o usuário forneça uma escolha válida do modelo de
        // configuração.
        do {
            // Solicita ao usuário que escolha o tipo de auto-config.
            input = (String) JOptionPane.showInputDialog(null, "Por favor, escolha o modo da bridge uplink:",
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
     * Método responsável por iniciar o processo de configuração da OLT 8820I.
     * Aqui é onde a "magia" acontece, guiando o usuário através da configuração.
     */
    public void start() {
        // Carrega os ícones necessários para o diálogo
        final ClassLoader classLoader = Olt8820Plus.class.getClassLoader();
        final ImageIcon equipamentoIcon = new ImageIcon(classLoader.getResource("equipamento.png"));
        // final ImageIcon ipIcon = new ImageIcon(classLoader.getResource("ip.png"));
        final ImageIcon saidaIcon = new ImageIcon(classLoader.getResource("saida.png"));
        final ImageIcon erroIcon = new ImageIcon(classLoader.getResource("erro.png"));
        final String nomeArq = "script8820i.txt";

        final String[] modelosInterface = {
                "eth 1",
                "eth 2",
                "eth 3",
                "eth 4",
                "eth 5",
                "eth 6",
                "eth 7",
                "eth 8",
                "xeth 1",
                "xeth 2",
        };

        final String[] bridgeInterfaceUplink = {
                "uplink",
                "intralink",
                "tls"
        };

        final String[] bridgeInterfaceUplinkVlanMode = {
                "tagged",
                "untagged"
        };

        final String[] configuracoes = {
                "Uma vlan por pon",
                "Apenas uma Vlan"
        };

        final String[] deviceType = {
                "110",
                "110b",
                "110g",
                "r1",
                "121w",
                "142ng",
                "142nw",
                "1420g",
                "120ac",
                "121ac",
                "1200r",
                "ax1800",
                "ax1800v",
                "default"
        };

        final String[] interfaceGpon = {
                "gpon 1",
                "gpon 2",
                "gpon 3",
                "gpon 4",
                "gpon 5",
                "gpon 6",
                "gpon 7",
                "gpon 8"
        };

        final String[] defaultCpeType = {
                "bridge",
                "router"
        };

        // Obtém informações do usuário para a configuração
        final String interfaceEth = this.getInterfaceEth(equipamentoIcon, saidaIcon, erroIcon,
                modelosInterface);
        final String modelConfiguration = this.getModelConfiguration(equipamentoIcon, saidaIcon, erroIcon,
                configuracoes);
        final String defaultCpe = this.getDefaultCpeType(equipamentoIcon, saidaIcon, erroIcon,
                defaultCpeType);
        final String bridgeInterfaceUplinkType = this.getBridgeInterfaceUplink(equipamentoIcon, saidaIcon,
                erroIcon, bridgeInterfaceUplink);
        final String bridgeInterfaceUplinkVlanModeType = this.getBridgeInterfaceUplinkVlanMode(
                equipamentoIcon,
                saidaIcon, erroIcon, bridgeInterfaceUplinkVlanMode);

        int rangeVlan;

        if (modelConfiguration.equals(configuracoes[1])) {
            rangeVlan = 1;
        } else {
            rangeVlan = this.getSlotLength();
        }

        // Obtém as listas necessárias para a configuração
        final List<String> arrayVlan = this.getVlanClient(equipamentoIcon, saidaIcon, erroIcon, rangeVlan);

        // Cria um objeto ConfigGenerator para gerar o script de configuração
        final ConfigGenerator8820Plus configGenerator = new ConfigGenerator8820Plus(arrayVlan, interfaceEth, deviceType,
                modelConfiguration, configuracoes, defaultCpe, interfaceGpon, defaultCpeType, bridgeInterfaceUplinkType,
                bridgeInterfaceUplinkVlanModeType);

        configGenerator.createScript(nomeArq);

        this.getIpFromUser(saidaIcon, erroIcon);
        this.getPortFromUser(saidaIcon, erroIcon);
        this.getUserAndPwd(saidaIcon, erroIcon);

        // Configuração final para definir o acesso à OLT
        final Telnet8820Plus telnetAccess = new Telnet8820Plus(getIp(), getPort(), getUser(), getPasswd());
        telnetAccess.oltAccess(nomeArq);
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public void setIp(final String ip) {
        this.ip = ip;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(final int port) {
        this.port = port;
    }

    @Override
    public String getPasswd() {
        return passwd;
    }

    @Override
    public void setPasswd(final String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(final String user) {
        this.user = user;
    }

    @Override
    public int getSlotLength() {
        return slotLength;
    }

    @Override
    public void setSlotLength(final int slotLength) {
        this.slotLength = slotLength;
    }

    @Override
    public void saida(final ImageIcon saidaIcon) {
        // Exibe uma caixa de diálogo com uma mensagem de aviso indicando que o programa
        // será encerrado.
        JOptionPane.showMessageDialog(null,
                "Voce pressionou o botao 'Cancelar'. O programa sera encerrado.",
                null, JOptionPane.WARNING_MESSAGE, saidaIcon);
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
    public void getIpFromUser(final ImageIcon saidaIcon, final ImageIcon erroIcon) {
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

    @Override
    public void getPortFromUser(final ImageIcon saidaIcon, final ImageIcon erroIcon) {
        String port;
        do {
            port = JOptionPane.showInputDialog("Digite a porta de acesso Telnet da OLT:");
        } while (!port.matches("^[1-9]\\d*$"));
        this.setPort(Integer.parseInt(port));
    }

    @Override
    public void getUserAndPwd(final ImageIcon saidaIcon, final ImageIcon erroIcon) {
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

    @Override
    public List<String> getVlanClient(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon, final ImageIcon erroIcon, final int range) {
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
                this.saida(saidaIcon);
                System.exit(0);
            }
        } while (!this.isValidVlanRange(input, erroIcon, range));

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

    @Override
    public String getDefaultCpeType(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon, final ImageIcon erroIcon,
            final String[] defaultCpeType) {
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

    @Override
    public String getModelConfiguration(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon, final ImageIcon erroIcon,
            final String[] modelConfiguration) {
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

    @Override
    public String getInterfaceEth(final ImageIcon equipamentoIcon, final ImageIcon saidaIcon, final ImageIcon erroIcon,
            final String[] interfaceEth) {
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
