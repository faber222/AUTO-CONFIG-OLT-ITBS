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

import engtelecom.config.ConfigGenerator;

/**
 * Objeto que representa a OLT G08
 */
public class OltGpon extends Olt {

    /**
     * Construtor padrão
     * 
     * @param slotLength Tamanho dos slots que a olt possui, pode ser 8 ou 16
     */
    public OltGpon() {

    }

    public boolean isValidAimVlanLineRange(final String aimVlanLineRange, final ImageIcon erroIcon, final int range,
            String errorName) {
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
                            "Valor invalido para " + errorName + ". Deve conter " + range
                                    + " numero, ser maior que 0 e nao ultrapassar 4095.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE, erroIcon);
                    return false;
                }
            } else {
                // Exibe mensagem de erro se a VLAN não atender às condições
                JOptionPane.showMessageDialog(null,
                        "Valor invalido para " + errorName
                                + ". Deve conter apenas numero(s) inteiro(s) nao nulo, maior que 0 e menor que 4095.",
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
                            "Range invalido para " + errorName + ". Deve conter " + range
                                    + " numeros e nao ultrapassar 4095.",
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
                                    "Valor " + valor + " invalido para " + errorName
                                            + ". Deve ser um numero inteiro nao nulo.",
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
                                    + " valores separados por virgula para " + errorName + ".",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE, erroIcon);
                    return false;
                }
            }
        }
        // Exibe mensagem de erro se o range não atender às condições
        JOptionPane.showMessageDialog(null,
                "Valor invalido para " + errorName + ". Deve conter " + range + " numeros e nao ultrapassar 4095.",
                "Erro",
                JOptionPane.ERROR_MESSAGE, erroIcon);
        return false;
    }

    public boolean checkAimProfileVlan(String rangeProfileVlan,
            final ImageIcon erroIcon, final int range) {
        if (this.isValidAimVlanLineRange(rangeProfileVlan, erroIcon, range, "Profile Vlan")) {
            return true;
        }
        return false;
    }

    public boolean checkAimProfileLine(String rangeProfielLine, final ImageIcon erroIcon, final int range) {

        if (this.isValidAimVlanLineRange(rangeProfielLine, erroIcon, range, "Profile Line")) {
            return true;
        }

        return false;
    }

    /**
     * Método responsável por iniciar o processo de configuração da OLT G16.
     * Aqui é onde a "magia" acontece, guiando o usuário através da configuração.
     */
    public boolean start(String[] interfaceGpon, String interfaceEth, String configuracaoAutoConfig,
            String defaultCpe, int slotLength, String rangeVlan, String rangeProfileVlan, String rangeProfileLine,
            String[] configuracoes, String nomeArq) {

        // Carrega os ícones necessários para o diálogo
        final ClassLoader classLoader = OltGpon.class.getClassLoader();
        final ImageIcon erroIcon = new ImageIcon(classLoader.getResource("erro.png"));

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

        final String[] defaultCpeType = {
                "bridge",
                "router"
        };

        int rangeNumVlan;
        int rangeAimVlan;
        int rangeAimLine;

        if (configuracaoAutoConfig.equals(configuracoes[1])) {
            rangeNumVlan = rangeAimVlan = 1;
            rangeAimLine = 2;
        } else {
            rangeNumVlan = rangeAimVlan = slotLength;
            rangeAimLine = slotLength * 2;
        }

        List<String> arrayVlan = new ArrayList<>();
        List<String> arrayAimVlan = new ArrayList<>();
        List<String> arrayAimLine = new ArrayList<>();

        System.out.println("Valor slotLenghth: " + slotLength);
        System.out.println("Range vlan: " + rangeNumVlan);
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

        System.out.println("Range Aim Vlan: " + rangeAimVlan);
        if (checkAimProfileVlan(rangeProfileVlan, erroIcon, rangeAimVlan)) {
            // Processa a entrada do usuário com base no intervalo especificado.
            if (rangeAimVlan != 1) {
                // Se o intervalo não for 1, divide a entrada e adiciona os valores à lista.
                final String[] partes;
                // Se o intervalo não for 1, divide a entrada e adiciona os valores à lista.
                if (rangeProfileVlan.matches("^([1-9]\\d*,)+[1-9]\\d*$")) {
                    partes = rangeProfileVlan.split(",");
                    for (String valor : partes) {
                        arrayAimVlan.add(valor);
                    }
                } else {
                    partes = rangeProfileVlan.split("-");
                    final int inicio = Integer.parseInt(partes[0]);
                    final int fim = Integer.parseInt(partes[1]);

                    for (int j = inicio; j <= fim; j++) {
                        arrayAimVlan.add(String.valueOf(j));
                    }
                }
            } else {
                // Se o intervalo for 1, adiciona a entrada diretamente à lista.
                arrayAimVlan.add(rangeProfileVlan);
            }
        } else {
            return false;
        }

        System.out.println("Range Line: " + rangeAimLine);
        if (checkAimProfileLine(rangeProfileLine, erroIcon, rangeAimLine)) {
            final String[] partes;
            if (rangeProfileLine.matches("^([1-9]\\d*,)+[1-9]\\d*$")) {
                partes = rangeProfileLine.split(",");
                for (String valor : partes) {
                    arrayAimLine.add(valor);
                }
            } else {
                partes = rangeProfileLine.split("-");
                final int inicio = Integer.parseInt(partes[0]);
                final int fim = Integer.parseInt(partes[1]);

                for (int j = inicio; j <= fim; j++) {
                    arrayAimLine.add(String.valueOf(j));
                }
            }
        } else {
            return false;
        }

        // Cria um objeto ConfigGenerator para gerar o script de configuração
        final ConfigGenerator configGenerator = new ConfigGenerator(arrayVlan,
                arrayAimVlan, interfaceEth, arrayAimLine, deviceType,
                configuracaoAutoConfig, configuracoes,
                defaultCpe, interfaceGpon, defaultCpeType);

        configGenerator.createScript(nomeArq, slotLength);
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
    public boolean checkVlanClient(String rangeVlan,
            ImageIcon erroIcon, int range) {

        if (this.isValidAimVlanLineRange(rangeVlan, erroIcon, range, "Range Vlan")) {
            return true;
        }
        return false;
    }

    @Override
    public String getInterfaceEth(ImageIcon equipamentoIcon, ImageIcon saidaIcon, ImageIcon erroIcon,
            String[] interfaceEth) {
        throw new UnsupportedOperationException("Unimplemented method 'getInterfaceEth'");
    }

    @Override
    public String getDefaultCpeType(ImageIcon equipamentoIcon, ImageIcon saidaIcon, ImageIcon erroIcon,
            String[] defaultCpeType) {
        throw new UnsupportedOperationException("Unimplemented method 'getDefaultCpeType'");
    }

    @Override
    public String getModelConfiguration(ImageIcon equipamentoIcon, ImageIcon saidaIcon, ImageIcon erroIcon,
            String[] modelConfiguration) {
        throw new UnsupportedOperationException("Unimplemented method 'getModelConfiguration'");
    }

    @Override
    public int getSlotLength() {
        throw new UnsupportedOperationException("Unimplemented method 'getSlotLength'");
    }

    @Override
    public void setSlotLength(int slotLength) {
        throw new UnsupportedOperationException("Unimplemented method 'setSlotLength'");
    }

    @Override
    public void getIpFromUser(ImageIcon saidaIcon, ImageIcon erroIcon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIpFromUser'");
    }

    @Override
    public void getPortFromUser(ImageIcon saidaIcon, ImageIcon erroIcon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPortFromUser'");
    }

    @Override
    public void getUserAndPwd(ImageIcon saidaIcon, ImageIcon erroIcon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserAndPwd'");
    }

}
