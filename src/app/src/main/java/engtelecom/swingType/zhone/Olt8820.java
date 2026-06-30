/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package engtelecom.swingType.zhone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

import engtelecom.access.write.itbs.TelnetWrite8820Plus;
import engtelecom.product.Olt8820Plus;

/**
 *
 * @author faber222
 */
public class Olt8820 extends javax.swing.JInternalFrame {

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.ButtonGroup buttonGroup1;

        private javax.swing.JButton jButtonCancel;

        private javax.swing.JButton jButtonCriar;

        private javax.swing.JButton jButtonEnviar;

        private javax.swing.JButton jButtonFileChooser;

        private javax.swing.JComboBox<String> jComboBoxBridgeUplink;

        private javax.swing.JComboBox<String> jComboBoxInterfaceUplink1;

        private javax.swing.JComboBox<String> jComboBoxModoAutoConfig1;

        private javax.swing.JComboBox<String> jComboBoxModoVlan;

        private javax.swing.JFormattedTextField jFormattedTextFieldPortOlt;

        private javax.swing.JLabel jLabel1;

        private javax.swing.JLabel jLabel10;

        private javax.swing.JLabel jLabel11;

        private javax.swing.JLabel jLabel12;

        private javax.swing.JLabel jLabel13;

        private javax.swing.JLabel jLabel14;

        private javax.swing.JLabel jLabel15;

        private javax.swing.JLabel jLabel16;

        private javax.swing.JLabel jLabel17;

        private javax.swing.JLabel jLabel2;

        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPasswordField jPasswordFieldOltPasswd;
        private javax.swing.JRadioButton jRadioButtonBridge1;
        private javax.swing.JRadioButton jRadioButtonRouter1;
        private javax.swing.JScrollPane jScrollPanel;
        private javax.swing.JTextArea jTextAreaPreviewCode;
        private javax.swing.JTextField jTextFieldIpOlt;
        private javax.swing.JTextField jTextFieldOltUser;
        private javax.swing.JTextField jTextFieldRangeVlan1;
        private String[] modelosInterface;
        private String[] configuracoes;
        private String[] bridgeInterfaceUplink;
        private String[] bridgeInterfaceUplinkVlanMode;
        private String nomeArq;
        private final String oltName;
        private boolean fileChooserIsSelected;
        private ImageIcon errorIcon;
        private ImageIcon successIcon;
        private ImageIcon saidaIcon;
        private final Olt8820Plus olt8820Plus;
        private final int slotLength;

        // End of variables declaration//GEN-END:variables
        /**
         * Creates new form OltG16
         */
        public Olt8820(final String oltName) {
                this.slotLength = 8;
                this.oltName = oltName;
                this.olt8820Plus = new Olt8820Plus();
                initComponents();
        }

        public void previewText() {
                // Tente abrir e ler o arquivo
                try (BufferedReader br = new BufferedReader(new FileReader(this.nomeArq))) {
                        final StringBuilder content = new StringBuilder();
                        String line;

                        // Lê linha por linha até o final do arquivo
                        while ((line = br.readLine()) != null) {
                                content.append(line).append("\n");
                        }

                        // Define o texto do JTextArea com o conteúdo lido
                        jTextAreaPreviewCode.setText(content.toString());
                        br.close();
                } catch (final IOException e) {
                        e.printStackTrace();
                        jTextAreaPreviewCode.setText(null);
                }
        }

        public String getOltName() {
                return oltName;
        }

        public void saida() {
                // Exibe uma caixa de diálogo com uma mensagem de aviso indicando que o programa
                // será encerrado.
                JOptionPane.showMessageDialog(null,
                                "Voce pressionou o botao 'Cancelar'. O programa sera encerrado.",
                                null, JOptionPane.WARNING_MESSAGE, this.saidaIcon);
        }

        private void olt() {
                // Carrega os ícones necessários para o diálogo
                final ClassLoader classLoader = Olt8820.class.getClassLoader();
                this.errorIcon = new ImageIcon(classLoader.getResource("erro.png"));
                this.successIcon = new ImageIcon(classLoader.getResource("success.png"));
                this.saidaIcon = new ImageIcon(classLoader.getResource("saida.png"));
                this.nomeArq = "script8820I.txt";
                this.fileChooserIsSelected = false;

                this.modelosInterface = new String[] {
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

                this.configuracoes = new String[] {
                                "Uma vlan por pon",
                                "Apenas uma Vlan"
                };

                this.bridgeInterfaceUplink = new String[] {
                                "uplink",
                                "tls"
                };

                this.bridgeInterfaceUplinkVlanMode = new String[] {
                                "tagged",
                                "untagged"
                };

        }

        // ===================================================================
        // Helpers de estilo (FlatLaf) - apenas visual, nao alteram o backend
        // ===================================================================

        /** Carrega um icone do classpath; retorna null se nao encontrado. */
        private ImageIcon loadIcon(final String path) {
                final java.net.URL url = getClass().getClassLoader().getResource(path);
                return url != null ? new ImageIcon(url) : null;
        }

        /** Titulo de secao em negrito com cor de destaque do tema. */
        private JLabel sectionHeader(final String text, final String iconRes) {
                final JLabel label = new JLabel(text);
                label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
                final ImageIcon icon = loadIcon(iconRes);
                if (icon != null) {
                        label.setIcon(icon);
                        label.setIconTextGap(8);
                }
                final Color accent = UIManager.getColor("Component.accentColor");
                if (accent != null) {
                        label.setForeground(accent);
                }
                return label;
        }

        /** Painel "card" com cantos arredondados e fundo derivado do tema. */
        private void styleCard(final JPanel panel) {
                panel.putClientProperty(FlatClientProperties.STYLE,
                                "arc:18;"
                                                + "[light]background:darken($Panel.background,3%);"
                                                + "[dark]background:lighten($Panel.background,4%)");
        }

        /** Campo de texto arredondado com placeholder e icone a esquerda. */
        private void styleField(final JComponent field, final String iconRes, final String placeholder) {
                field.putClientProperty(FlatClientProperties.STYLE, "arc:10");
                if (placeholder != null) {
                        field.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, placeholder);
                }
                final ImageIcon icon = loadIcon(iconRes);
                if (icon != null) {
                        field.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, icon);
                }
        }

        /** Botao principal: pill arredondado com cor de destaque. */
        private void stylePrimaryButton(final JButton button, final String iconRes) {
                button.putClientProperty(FlatClientProperties.STYLE, "arc:999; focusWidth:0; borderWidth:0");
                Color accent = UIManager.getColor("Component.accentColor");
                if (accent == null) {
                        accent = new Color(0x2D7FF9);
                }
                button.setBackground(accent);
                button.setForeground(Color.WHITE);
                final ImageIcon icon = loadIcon(iconRes);
                if (icon != null) {
                        button.setIcon(icon);
                }
        }

        /** Botao secundario: pill arredondado, estilo padrao do tema. */
        private void styleSecondaryButton(final JButton button, final String iconRes) {
                button.putClientProperty(FlatClientProperties.STYLE, "arc:999");
                final ImageIcon icon = loadIcon(iconRes);
                if (icon != null) {
                        button.setIcon(icon);
                }
        }

        /**
         * This method is called from within the constructor to initialize the form.
         */
        @SuppressWarnings("unchecked")
        private void initComponents() {
                olt();
                buttonGroup1 = new javax.swing.ButtonGroup();
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                jLabel10 = new javax.swing.JLabel();
                jPasswordFieldOltPasswd = new javax.swing.JPasswordField();
                jTextFieldOltUser = new javax.swing.JTextField();
                jTextFieldIpOlt = new javax.swing.JTextField();
                jFormattedTextFieldPortOlt = new javax.swing.JFormattedTextField();
                jPanel2 = new javax.swing.JPanel();
                jLabel11 = new javax.swing.JLabel();
                jRadioButtonBridge1 = new javax.swing.JRadioButton();
                jRadioButtonRouter1 = new javax.swing.JRadioButton();
                jComboBoxInterfaceUplink1 = new javax.swing.JComboBox<>();
                jLabel12 = new javax.swing.JLabel();
                jLabel13 = new javax.swing.JLabel();
                jComboBoxModoAutoConfig1 = new javax.swing.JComboBox<>();
                jLabel14 = new javax.swing.JLabel();
                jTextFieldRangeVlan1 = new javax.swing.JTextField();
                jComboBoxModoVlan = new javax.swing.JComboBox<>();
                jLabel16 = new javax.swing.JLabel();
                jComboBoxBridgeUplink = new javax.swing.JComboBox<>();
                jLabel17 = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                jLabel15 = new javax.swing.JLabel();
                jButtonFileChooser = new javax.swing.JButton();
                jPanel4 = new javax.swing.JPanel();
                jButtonEnviar = new javax.swing.JButton();
                jButtonCriar = new javax.swing.JButton();
                jButtonCancel = new javax.swing.JButton();
                jPanel5 = new javax.swing.JPanel();
                jScrollPanel = new javax.swing.JScrollPane();
                jTextAreaPreviewCode = new javax.swing.JTextArea();

                // ----- Frame -----
                setClosable(true);
                setIconifiable(true);
                setMaximizable(true);
                setResizable(true);
                setTitle("8820i");
                setFrameIcon(loadIcon("OLT_8820.png"));
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                setMinimumSize(new Dimension(900, 650));
                setPreferredSize(new Dimension(980, 700));

                // ----- Card 1: Acesso da OLT -----
                styleCard(jPanel1);
                jLabel1.setText("IP OLT");
                jLabel9.setText("Porta");
                jLabel2.setText("Usuario");
                jLabel10.setText("Senha");

                styleField(jTextFieldIpOlt, "icons/server.png", "192.168.0.1");

                jFormattedTextFieldPortOlt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
                                new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#####"))));
                jFormattedTextFieldPortOlt.setToolTipText("porta");
                jFormattedTextFieldPortOlt.setName("porta"); // NOI18N
                styleField(jFormattedTextFieldPortOlt, "icons/sitemap.png", "23");

                styleField(jTextFieldOltUser, "icons/user.png", "admin");
                styleField(jPasswordFieldOltPasswd, "icons/key.png", "senha");

                jPanel1.setLayout(new MigLayout("insets 16, gap 12 10, fillx",
                                "[80!][grow,fill]", "[][][][]"));
                jPanel1.add(sectionHeader("Acesso da OLT", "icons/server.png"), "span 2, wrap, gapbottom 6");
                jPanel1.add(jLabel1);
                jPanel1.add(jTextFieldIpOlt, "growx, wrap");
                jPanel1.add(jLabel9);
                jPanel1.add(jFormattedTextFieldPortOlt, "growx, wrap");
                jPanel1.add(jLabel2);
                jPanel1.add(jTextFieldOltUser, "growx, wrap");
                jPanel1.add(jLabel10);
                jPanel1.add(jPasswordFieldOltPasswd, "growx");

                // ----- Card 2: Formulario do Script -----
                styleCard(jPanel2);
                jLabel12.setText("Interface Uplink");
                jLabel17.setText("Bridge Uplink");
                jLabel14.setText("Modo do Auto-Config");
                jLabel16.setText("Modo Vlan");
                jLabel13.setText("Range Vlan");
                jLabel11.setText("CPEs de terceiros, em:");

                jComboBoxInterfaceUplink1.setMaximumRowCount(6);
                jComboBoxInterfaceUplink1.setModel(new javax.swing.DefaultComboBoxModel<>(modelosInterface));
                jComboBoxBridgeUplink.setMaximumRowCount(6);
                jComboBoxBridgeUplink.setModel(new javax.swing.DefaultComboBoxModel<>(bridgeInterfaceUplink));
                jComboBoxModoAutoConfig1.setMaximumRowCount(3);
                jComboBoxModoAutoConfig1.setModel(new javax.swing.DefaultComboBoxModel<>(configuracoes));
                jComboBoxModoVlan.setMaximumRowCount(3);
                jComboBoxModoVlan.setModel(new javax.swing.DefaultComboBoxModel<>(bridgeInterfaceUplinkVlanMode));
                styleField(jTextFieldRangeVlan1, "icons/sitemap.png", "100-107");

                buttonGroup1.add(jRadioButtonBridge1);
                jRadioButtonBridge1.setSelected(true);
                jRadioButtonBridge1.setText("BRIDGE");
                buttonGroup1.add(jRadioButtonRouter1);
                jRadioButtonRouter1.setText("ROUTER");

                final JPanel cpePanel = new JPanel(new MigLayout("insets 0, gap 18", "[][]"));
                cpePanel.setOpaque(false);
                cpePanel.add(jRadioButtonBridge1);
                cpePanel.add(jRadioButtonRouter1);

                jPanel2.setLayout(new MigLayout("insets 16, gap 12 10, fillx",
                                "[140!][grow,fill]", "[][][][][][][]"));
                jPanel2.add(sectionHeader("Formulario do Script", "icons/script_code.png"),
                                "span 2, wrap, gapbottom 6");
                jPanel2.add(jLabel12);
                jPanel2.add(jComboBoxInterfaceUplink1, "growx, wrap");
                jPanel2.add(jLabel17);
                jPanel2.add(jComboBoxBridgeUplink, "growx, wrap");
                jPanel2.add(jLabel14);
                jPanel2.add(jComboBoxModoAutoConfig1, "growx, wrap");
                jPanel2.add(jLabel16);
                jPanel2.add(jComboBoxModoVlan, "growx, wrap");
                jPanel2.add(jLabel13);
                jPanel2.add(jTextFieldRangeVlan1, "growx, wrap");
                jPanel2.add(jLabel11, "span 2, wrap, gaptop 4");
                jPanel2.add(cpePanel, "span 2, growx");

                // ----- Card 5: Preview -----
                styleCard(jPanel5);
                jTextAreaPreviewCode.setEditable(false);
                jTextAreaPreviewCode.setColumns(20);
                jTextAreaPreviewCode.setRows(5);
                jTextAreaPreviewCode.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
                jScrollPanel.setViewportView(jTextAreaPreviewCode);
                jScrollPanel.putClientProperty(FlatClientProperties.STYLE, "arc:12");
                jScrollPanel.setPreferredSize(new Dimension(420, 380));

                jPanel5.setLayout(new MigLayout("insets 16, fill", "[grow,fill]", "[][grow,fill]"));
                jPanel5.add(sectionHeader("Preview", "icons/eye.png"), "wrap, gapbottom 6");
                jPanel5.add(jScrollPanel, "grow");

                // ----- Footer (jPanel4): arquivo + botoes de acao -----
                jPanel4.setOpaque(false);
                jLabel15.setText("Importar Script");
                final ImageIcon importIcon = loadIcon("icons/folder.png");
                if (importIcon != null) {
                        jLabel15.setIcon(importIcon);
                        jLabel15.setIconTextGap(6);
                }

                jButtonFileChooser.setText("File");
                styleSecondaryButton(jButtonFileChooser, "icons/folder.png");

                jButtonCancel.setText("Cancel");
                styleSecondaryButton(jButtonCancel, "icons/cross.png");
                jButtonCriar.setText("Criar");
                stylePrimaryButton(jButtonCriar, "icons/script_code.png");
                jButtonEnviar.setText("Enviar");
                stylePrimaryButton(jButtonEnviar, "icons/server_connect.png");

                jPanel4.setLayout(new MigLayout("insets 8 4 0 4, fillx",
                                "[][][grow][][][]", "[]"));
                jPanel4.add(jLabel15, "gapright 6");
                jPanel4.add(jButtonFileChooser);
                jPanel4.add(new JLabel(), "growx");
                jPanel4.add(jButtonCancel);
                jPanel4.add(jButtonCriar);
                jPanel4.add(jButtonEnviar);

                // jPanel3 permanece instanciado (compatibilidade) mas nao e exibido nesta versao.

                // ----- Cabecalho -----
                final JLabel headerLabel = new JLabel("Configurador 8820i");
                headerLabel.setFont(headerLabel.getFont().deriveFont(Font.BOLD, 18f));
                final ImageIcon headerIcon = loadIcon("icons/script_code.png");
                if (headerIcon != null) {
                        headerLabel.setIcon(headerIcon);
                        headerLabel.setIconTextGap(8);
                }

                // ----- Coluna esquerda (dois cards empilhados) -----
                final JPanel leftColumn = new JPanel(new MigLayout("insets 0, gap 14, flowy, fillx",
                                "[grow,fill]", "[][]"));
                leftColumn.setOpaque(false);
                leftColumn.add(jPanel1, "growx");
                leftColumn.add(jPanel2, "growx");

                // ----- Corpo (2 colunas) -----
                final JPanel body = new JPanel(new MigLayout("insets 0, gap 14, fill",
                                "[380!,fill][grow,fill]", "[grow,fill]"));
                body.setOpaque(false);
                body.add(leftColumn, "growy");
                body.add(jPanel5, "grow");

                // ----- Monta o content pane -----
                getContentPane().setLayout(new MigLayout("insets 16, gap 12, fill",
                                "[grow,fill]", "[][grow,fill][]"));
                getContentPane().add(headerLabel, "wrap");
                getContentPane().add(body, "grow, wrap");
                getContentPane().add(jPanel4, "growx");

                // ----- Listeners (comportamento inalterado) -----
                jPasswordFieldOltPasswd.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jPasswordFieldOltPasswdActionPerformed(evt);
                        }
                });
                jTextFieldOltUser.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jTextFieldOltUserActionPerformed(evt);
                        }
                });
                jTextFieldIpOlt.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jTextFieldIpOltActionPerformed(evt);
                        }
                });
                jFormattedTextFieldPortOlt.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jFormattedTextFieldPortOltActionPerformed(evt);
                        }
                });
                jRadioButtonBridge1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jRadioButtonBridge1ActionPerformed(evt);
                        }
                });
                jRadioButtonRouter1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jRadioButtonRouter1ActionPerformed(evt);
                        }
                });
                jComboBoxInterfaceUplink1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jComboBoxInterfaceUplink1ActionPerformed(evt);
                        }
                });
                jComboBoxModoAutoConfig1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jComboBoxModoAutoConfig1ActionPerformed(evt);
                        }
                });
                jTextFieldRangeVlan1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jTextFieldRangeVlan1ActionPerformed(evt);
                        }
                });
                jComboBoxModoVlan.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jComboBoxModoVlanActionPerformed(evt);
                        }
                });
                jComboBoxBridgeUplink.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jComboBoxBridgeUplinkActionPerformed(evt);
                        }
                });
                jButtonFileChooser.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonFileChooserActionPerformed(evt);
                        }
                });
                jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonEnviarActionPerformed(evt);
                        }
                });
                jButtonCriar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonCriarActionPerformed(evt);
                        }
                });
                jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonCancelActionPerformed(evt);
                        }
                });

                pack();
        }

        private void jPasswordFieldOltPasswdActionPerformed(final java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void jTextFieldIpOltActionPerformed(final java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void jFormattedTextFieldPortOltActionPerformed(final java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void jButtonFileChooserActionPerformed(final java.awt.event.ActionEvent evt) {
                final JFileChooser fileChooser = new JFileChooser();

                // Adiciona um filtro para aceitar apenas arquivos de texto e derivados
                final FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                "Arquivos de Texto", "txt", "md", "csv", "log", "java", "xml", "html", "json");
                fileChooser.setFileFilter(filter);

                // Exibe o seletor de arquivo e obtém a resposta do usuário
                final int returnValue = fileChooser.showOpenDialog(null);

                // Verifica se o usuário escolheu um arquivo
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                        this.fileChooserIsSelected = true;
                        // Obtém o arquivo selecionado
                        final java.io.File selectedFile = fileChooser.getSelectedFile();
                        System.out.println("Arquivo selecionado: " + selectedFile.getAbsolutePath());
                        this.nomeArq = selectedFile.getAbsolutePath();
                        jButtonFileChooser.setText(selectedFile.getName());
                        previewText();
                } else {
                        this.fileChooserIsSelected = false;
                        JOptionPane.showMessageDialog(null,
                                        "Nenhum arquivo selecionado.", "Error",
                                        JOptionPane.ERROR_MESSAGE, this.errorIcon);
                        jButtonFileChooser.setText("File");
                        jTextAreaPreviewCode.setText("");
                }
        }

        private void jButtonEnviarActionPerformed(final java.awt.event.ActionEvent evt) {
                if (this.fileChooserIsSelected) {
                        if (this.olt8820Plus.checkTelnet(jTextFieldIpOlt.getText(),
                                        jFormattedTextFieldPortOlt.getText(),
                                        jTextFieldOltUser.getText(), jPasswordFieldOltPasswd.getPassword(),
                                        this.errorIcon)) {
                                JOptionPane.showMessageDialog(null,
                                                "Valores validos!", "Sucesso",
                                                JOptionPane.ERROR_MESSAGE, this.successIcon);
                                final SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                                        @Override
                                        protected Void doInBackground() throws Exception {
                                                final TelnetWrite8820Plus acessoOlt = new TelnetWrite8820Plus(
                                                                jTextFieldIpOlt.getText(),
                                                                Integer.parseInt(jFormattedTextFieldPortOlt.getText()),
                                                                jTextFieldOltUser.getText(),
                                                                new String(jPasswordFieldOltPasswd.getPassword()), getOltName());

                                                acessoOlt.oltAccess(nomeArq);
                                                return null;
                                        }

                                };
                                worker.execute();
                        }
                } else {
                        JOptionPane.showMessageDialog(null,
                                        "Nenhum arquivo selecionado ou criado!", "Error",
                                        JOptionPane.ERROR_MESSAGE, this.errorIcon);
                }
        }

        private void jButtonCriarActionPerformed(final java.awt.event.ActionEvent evt) {
                this.nomeArq = "script8820I.txt";

                String terceiros = "router";
                if (jRadioButtonBridge1.isSelected()) {
                        terceiros = "bridge";
                }
                if (this.olt8820Plus.start((String) this.jComboBoxInterfaceUplink1.getSelectedItem(),
                                this.configuracoes,
                                terceiros, (String) this.jComboBoxModoAutoConfig1.getSelectedItem(),
                                this.jTextFieldRangeVlan1.getText(), (String) this.jComboBoxModoVlan.getSelectedItem(),
                                (String) this.jComboBoxBridgeUplink.getSelectedItem(), this.slotLength, this.nomeArq)) {
                        // Cria um objeto ConfigGenerator para gerar o script de configuração
                        JOptionPane.showMessageDialog(null,
                                        "Script criado com sucesso!", "Sucesso",
                                        JOptionPane.ERROR_MESSAGE, this.successIcon);
                        this.fileChooserIsSelected = true;
                        jButtonFileChooser.setText(this.nomeArq);
                        previewText();
                } else {

                        JOptionPane.showMessageDialog(null,
                                        "Script nao foi criado!", "Error",
                                        JOptionPane.ERROR_MESSAGE, this.errorIcon);
                }
        }

        private void jTextFieldOltUserActionPerformed(final java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void jButtonCancelActionPerformed(final java.awt.event.ActionEvent evt) {
                this.dispose();
        }

        private void jRadioButtonBridge1ActionPerformed(final java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void jRadioButtonRouter1ActionPerformed(final java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void jComboBoxInterfaceUplink1ActionPerformed(final java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void jComboBoxModoAutoConfig1ActionPerformed(final java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void jTextFieldRangeVlan1ActionPerformed(final java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void jComboBoxModoVlanActionPerformed(final java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void jComboBoxBridgeUplinkActionPerformed(final java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }
}
