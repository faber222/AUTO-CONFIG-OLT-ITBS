/**
 * @author faber222
 * @since 2024
*/
package engtelecom;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import engtelecom.product.Olt8820Plus;
import engtelecom.product.OltGpon;

/**
 * Classe principal da aplicação que inicia a OLT G16.
 */
public class App {

    /**
     * Construtor padrão da classe App.
     */
    public App() {
        // Construtor padrão, não há ações específicas aqui no momento.
    }

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
            final String mensagem = "O criador deste codigo: " + nomeCriador + "\n\n" +
                    "Voce pode encontra-lo no GitHub em:\n" + githubLink;

            // Exibe a mensagem com a ImageIcon contendo o QR code
            JOptionPane.showMessageDialog(
                    null, mensagem, "Criador faber222 e Link do GitHub", JOptionPane.INFORMATION_MESSAGE, qrCodeIcon);
        } catch (final WriterException e) {
            // Lida com exceções relacionadas à geração do QR code
            e.printStackTrace();
        }
    }

    /**
     * Apresenta um diálogo de boas-vindas e opções ao usuário.
     * 
     * @param options         Opções a serem exibidas no diálogo.
     * @param equipamentoIcon Ícone do equipamento.
     * @param saidaIcon       Ícone de saída.
     * @param erroIcon        Ícone de erro.
     */
    public static void presentation(final Object[] options, final ImageIcon equipamentoIcon,
            final ImageIcon saidaIcon, final ImageIcon erroIcon) {
        // Condição para controlar o loop.
        boolean condition = false;

        final Object[] oltOptions = { "G16", "G08", "8820i", "Cancelar" };

        // Loop para apresentar o diálogo até que a condição seja satisfeita.
        do {
            // Exibe um diálogo de opções ao usuário.
            final int result = JOptionPane.showOptionDialog(null, "Bem Vindo ao AUTO-CONFIG!", "OLT-AUTO-CONFIG",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, equipamentoIcon, options, options[0]);

            // Verifica a opção escolhida pelo usuário.
            switch (result) {
                case 0:
                    // Se a opção for 0, a condição é satisfeita e retorna true.
                    condition = true;
                    App.oltOption(oltOptions, equipamentoIcon, saidaIcon, erroIcon);
                    break;
                // case 1:
                //     // Se a opção for 1, chama o método mostrarCriador().
                //     App.mostrarCriador();
                //     break;
                default:
                    // Se nenhuma opção válida for escolhida, chama o método saida() e encerra o
                    // programa.
                    App.saida(saidaIcon);
                    System.exit(0);
                    break;
            }

        } while (!condition);
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
                "Voce pressionou o botao 'Cancelar'. O programa sera encerrado.",
                null, JOptionPane.WARNING_MESSAGE, saidaIcon);
    }

    /**
     * Apresenta um diálogo de boas-vindas e opções ao usuário.
     * 
     * @param options         Opções a serem exibidas no diálogo.
     * @param equipamentoIcon Ícone do equipamento.
     * @param saidaIcon       Ícone de saída.
     * @param erroIcon        Ícone de erro.
     */
    public static void oltOption(final Object[] options, final ImageIcon equipamentoIcon,
            final ImageIcon saidaIcon, final ImageIcon erroIcon) {
        // Condição para controlar o loop.
        boolean condition = false;

        // Loop para apresentar o diálogo até que a condição seja satisfeita.
        do {
            // Exibe um diálogo de opções ao usuário.
            final int result = JOptionPane.showOptionDialog(null, "Qual OLT deseja configurar?", "OLT-AUTO-CONFIG",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, equipamentoIcon, options, options[0]);

            // Verifica a opção escolhida pelo usuário.
            switch (result) {
                case 0:
                    // Se a opção for 0, a condição é satisfeita e retorna true.
                    condition = true;

                    final OltGpon OltG16 = new OltGpon(16);
                    OltG16.start();
                    break;
                case 1:
                    // Se a opção for 1, chama o método mostrarCriador().
                    condition = true;

                    final OltGpon OltG08 = new OltGpon(8);
                    OltG08.start();
                    break;
                case 2:
                    condition = true;

                    final Olt8820Plus Olt8820Plus = new Olt8820Plus(8);
                    Olt8820Plus.start();
                    break;
                default:
                    // Se nenhuma opção válida for escolhida, chama o método saida() e encerra o
                    // programa.
                    App.saida(saidaIcon);
                    System.exit(0);
                    break;
            }

        } while (!condition);
    }

    /**
     * O método principal que inicia a aplicação.
     *
     * @param args Argumentos da linha de comando (não utilizados neste momento).
     */
    public static void main(final String[] args) {
        final Object[] options = { "Avancar", "Cancelar" };

        // Carrega os ícones necessários para o diálogo
        final ClassLoader classLoader = App.class.getClassLoader();
        final ImageIcon equipamentoIcon = new ImageIcon(classLoader.getResource("equipamento.png"));
        final ImageIcon saidaIcon = new ImageIcon(classLoader.getResource("saida.png"));
        final ImageIcon erroIcon = new ImageIcon(classLoader.getResource("erro.png"));

        App.presentation(options, equipamentoIcon, saidaIcon, erroIcon);
    }
}
