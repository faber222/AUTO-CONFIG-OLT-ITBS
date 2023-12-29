package engtelecom;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
            final int result = JOptionPane.showOptionDialog(null, "Qual OLT deseja configurar?", "faber222",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, equipamentoIcon, options, options[0]);

            // Verifica a opção escolhida pelo usuário.
            switch (result) {
                case 0:
                    // Se a opção for 0, a condição é satisfeita e retorna true.
                    condition = true;

                    final OltGpon OltG16 = new OltGpon(16);
                    OltG16.start();

                    return true;
                case 1:
                    // Se a opção for 1, chama o método mostrarCriador().
                    condition = true;

                    final OltGpon OltG08 = new OltGpon(8);
                    OltG08.start();
                    return true;
                default:
                    // Se nenhuma opção válida for escolhida, chama o método saida() e encerra o
                    // programa.
                    App.saida(saidaIcon);
                    System.exit(0);
                    break;
            }

        } while (!condition);

        // Retorna false se o loop não for interrompido antes deste ponto.
        return false;
    }

    /**
     * O método principal que inicia a aplicação.
     *
     * @param args Argumentos da linha de comando (não utilizados neste momento).
     */
    public static void main(final String[] args) {
        final Object[] options = { "G16", "G08", "Cancelar" };

        // Carrega os ícones necessários para o diálogo
        final ClassLoader classLoader = App.class.getClassLoader();
        final ImageIcon equipamentoIcon = new ImageIcon(classLoader.getResource("equipamento.png"));
        final ImageIcon saidaIcon = new ImageIcon(classLoader.getResource("saida.png"));
        final ImageIcon erroIcon = new ImageIcon(classLoader.getResource("erro.png"));

        App.presentation(options, equipamentoIcon, saidaIcon, erroIcon);
    }
}
