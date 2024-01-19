/**
 * ARQUIVO BASE, CRIADO POR CHATGPT
 */
package engtelecom.swingType;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FormularioSwing extends JFrame {

    private JTextField textField;
    private JPasswordField passwordField;
    private JTextArea textArea;
    private JRadioButton radioButton;
    private JCheckBox checkBox;
    private JButton button;
    private JComboBox<String> comboBox;

    public FormularioSwing() {
        // Configurações básicas do JFrame
        setTitle("Formulário Swing");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializa os componentes
        initComponents();

        // Adiciona os componentes ao painel
        addComponentsToPanel();

        // Configura o layout do painel
        setLayout(new GridLayout(8, 1));

        // Define a ação do botão
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirDados();
            }
        });
    }

    private void initComponents() {
        // Inicializa os componentes
        textField = new JTextField(20);
        passwordField = new JPasswordField(20);
        textArea = new JTextArea(20, 30);
        textArea.setLineWrap(true); // Quebra de linha automática
        textArea.setWrapStyleWord(true); // Quebra de palavra automática
        textArea.setRows(20);
        radioButton = new JRadioButton("Radio Button");
        checkBox = new JCheckBox("Check Box");
        button = new JButton("Enviar");
        comboBox = new JComboBox<>(new String[] { "Opção 1", "Opção 2", "Opção 3" });
    }

    private void addComponentsToPanel() {
        // Adiciona os componentes ao painel
        add(new JLabel("Caixa de Texto: "));
        add(textField);
        add(new JLabel("Senha: "));
        add(passwordField);
        add(new JLabel("Área de Texto: "));
        add(textArea);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
        add(new JLabel("Radio Button: "));
        add(radioButton);
        add(new JLabel("Check Box: "));
        add(checkBox);
        add(new JLabel("Lista Suspensa: "));
        add(comboBox);
        add(new JLabel("Botão: "));
        add(button);
    }

    private void exibirDados() {
        // Obtém os dados dos componentes
        String texto = textField.getText();
        char[] senha = passwordField.getPassword();
        String areaTexto = textArea.getText();
        boolean radioSelecionado = radioButton.isSelected();
        boolean checkSelecionado = checkBox.isSelected();
        String itemSelecionado = comboBox.getSelectedItem().toString();

        // Exibe os dados em uma caixa de diálogo
        String mensagem = "Texto: " + texto + "\nSenha: " + new String(senha)
                + "\nÁrea de Texto: " + areaTexto + "\nRadio Button Selecionado: "
                + radioSelecionado + "\nCheck Box Selecionado: " + checkSelecionado
                + "\nItem Selecionado na Lista: " + itemSelecionado;
        JOptionPane.showMessageDialog(this, mensagem);
    }

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OltGponMenu.class.getName()).log(
                    java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OltGponMenu.class.getName()).log(
                    java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OltGponMenu.class.getName()).log(
                    java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OltGponMenu.class.getName()).log(
                    java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // Cria e exibe o formulário
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioSwing().setVisible(true);
            }
        });
    }
}
