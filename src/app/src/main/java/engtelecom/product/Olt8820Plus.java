/**
 * Olt8820Plus - Classe que representa a configuração específica da OLT 8820I.
 * @author faber222
 * @since 2024
 *
 * Esta classe guia o usuário através do processo de configuração da OLT 8820I,
 * interagindo com o usuário por meio de caixas de diálogo.
 */
package engtelecom.product;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import engtelecom.config.ConfigGenerator8820Plus;

public class Olt8820Plus extends Olt {

    public Olt8820Plus() {
    }

    public boolean isValidVlanRange(final String vlanRange, final ImageIcon erroIcon,
            final int range) {
        // Verifica se é um range no formato inicio-fim
        if (range == 1) {
            if (vlanRange.matches("^[1-9]\\d*$")) {
                // Se o range for 1 e o formato for um inteiro
                final int inicio = Integer.parseInt(vlanRange);

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
            if (vlanRange.matches("^[1-9]\\d*-\\d*[1-9]\\d*$")) {
                final String[] partes = vlanRange.split("-");

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
            } else if (vlanRange.matches("^([1-9]\\d*,)+[1-9]\\d*$")) {
                // Verifica se é uma lista de valores separados por vírgula
                final String[] valores = vlanRange.split(",");

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
     * Método responsável por iniciar o processo de configuração da OLT 8820I.
     * Aqui é onde a "magia" acontece, guiando o usuário através da configuração.
     * @return 
     */
    public boolean start(String interfaceEth, String[] configuracoes,
            String defaultCpe, String modelConfiguration, String rangeVlan, String bridgeInterfaceUplinkVlanMode, String bridgeInterfaceUplink,
            int slotLength, String nomeArq) {
        // Carrega os ícones necessários para o diálogo
        final ClassLoader classLoader = Olt8820Plus.class.getClassLoader();
        final ImageIcon erroIcon = new ImageIcon(classLoader.getResource("erro.png"));

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

        int rangeNumVlan = 1;

        if (!modelConfiguration.equals(configuracoes[1])) {
            rangeNumVlan = slotLength;
        }
        
        List<String> arrayVlan = new ArrayList<>();

        if (checkVlanClient(rangeVlan, erroIcon, rangeNumVlan)) {
            // Processa a entrada do usuário com base no intervalo especificado.
            if (rangeNumVlan != 1) {
                final String[] partes;
                // Se o intervalo não for 1, divide a entrada e adiciona os valores à lista.
                if (rangeVlan.matches("^([1-9]\\d*,)+[1-9]\\d*$")) {
                    partes = rangeVlan.split(",");
                    for (String valor : partes) {
                        arrayVlan.add(valor);
                    }
                } else {
                    partes = rangeVlan.split("-");
                    final int inicio = Integer.parseInt(partes[0]);
                    final int fim = Integer.parseInt(partes[1]);

                    for (int j = inicio; j <= fim; j++) {
                        arrayVlan.add(String.valueOf(j));
                    }
                }
            } else {
                // Se o intervalo for 1, adiciona a entrada diretamente à lista.
                arrayVlan.add(rangeVlan);
            }
        } else {
            return false;
        }

        // Cria um objeto ConfigGenerator para gerar o script de configuração
        final ConfigGenerator8820Plus configGenerator = new ConfigGenerator8820Plus(arrayVlan, interfaceEth, deviceType,
                modelConfiguration, configuracoes, defaultCpe, interfaceGpon, defaultCpeType,
                bridgeInterfaceUplink, bridgeInterfaceUplinkVlanMode);

        configGenerator.createScript(nomeArq);

        // this.getIpFromUser(saidaIcon, erroIcon);
        // this.getPortFromUser(saidaIcon, erroIcon);
        // this.getUserAndPwd(saidaIcon, erroIcon);

        // // Configuração final para definir o acesso à OLT
        // final Telnet8820Plus telnetAccess = new Telnet8820Plus(getIp(), getPort(), getUser(), getPasswd());
        // telnetAccess.oltAccess(nomeArq);
        return true;
    }

    public boolean checkTelnet(String ipAddress, String port, String user, char[] pwd, final ImageIcon erroIcon) {
        // Verifica se o endereço IP inserido é válido.
        if (!isValidIPv4Address(ipAddress)) {
            JOptionPane.showMessageDialog(null,
                    "Entrada invalida. Por favor, insira um endereço IP valido (0-255).", "Erro",
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
                    "Entrada invalida. O campo usuario ou senha não podem ser nulos", "Erro",
                    JOptionPane.ERROR_MESSAGE, erroIcon);
            return false;
        }
        return true;
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
    public boolean checkVlanClient(String rangeVlan, final ImageIcon erroIcon, final int range) {
        if (this.isValidVlanRange(rangeVlan, erroIcon, range)) {
            return true;
        }
        return false;
    }

}
