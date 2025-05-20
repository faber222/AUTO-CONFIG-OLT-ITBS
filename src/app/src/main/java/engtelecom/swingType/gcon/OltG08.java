/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package engtelecom.swingType.gcon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import engtelecom.access.write.Telnet;
import engtelecom.product.OltGpon;

/**
 *
 * @author faber222
 */
public class OltG08 extends javax.swing.JInternalFrame {

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.ButtonGroup buttonGroup1;

        private javax.swing.JButton jButtonCancel;

        private javax.swing.JButton jButtonCriar;

        private javax.swing.JButton jButtonEnviar;

        private javax.swing.JButton jButtonFileChooser;

        private javax.swing.JComboBox<String> jComboBoxInterfaceUplink;

        private javax.swing.JComboBox<String> jComboBoxModoAutoConfig;

        private javax.swing.JFormattedTextField jFormattedTextFieldPortOlt;

        private javax.swing.JLabel jLabel1;

        private javax.swing.JLabel jLabel10;

        private javax.swing.JLabel jLabel15;

        private javax.swing.JLabel jLabel2;

        private javax.swing.JLabel jLabel3;

        private javax.swing.JLabel jLabel4;

        private javax.swing.JLabel jLabel5;

        private javax.swing.JLabel jLabel6;

        private javax.swing.JLabel jLabel7;

        private javax.swing.JLabel jLabel8;

        private javax.swing.JLabel jLabel9;

        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPasswordField jPasswordFieldOltPasswd;
        private javax.swing.JRadioButton jRadioButtonBridge;
        private javax.swing.JRadioButton jRadioButtonRouter;
        private javax.swing.JScrollPane jScrollPanel;
        private javax.swing.JTextArea jTextAreaPreviewCode;
        private javax.swing.JTextField jTextFieldIpOlt;
        private javax.swing.JTextField jTextFieldOltUser;
        private javax.swing.JTextField jTextFieldRangeProfileLine;
        private javax.swing.JTextField jTextFieldRangeProfileVlan;
        private javax.swing.JTextField jTextFieldRangeVlan;
        private String[] modelosInterface;
        private String[] configuracoes;
        private String[] interfaceGpon;
        private boolean fileChooserIsSelected;
        private String nomeArq;
        private final String oltName;
        private ImageIcon errorIcon;
        private ImageIcon successIcon;
        private ImageIcon saidaIcon;
        private final OltGpon oltGpon;
        private final int slotLength;

        // End of variables declaration//GEN-END:variables
        /**
         * Creates new form OltG16
         */
        public OltG08(final String oltName) {
                this.slotLength = 8;
                this.oltName = oltName;
                initComponents();
                oltGpon = new OltGpon();
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
                }
        }

        public String getOltName() {
                return oltName;
        }

        private void olt() {
                // Carrega os ícones necessários para o diálogo
                final ClassLoader classLoader = OltG08.class.getClassLoader();
                this.errorIcon = new ImageIcon(classLoader.getResource("erro.png"));
                this.successIcon = new ImageIcon(classLoader.getResource("success.png"));
                this.saidaIcon = new ImageIcon(classLoader.getResource("saida.png"));
                this.nomeArq = "scriptG08.txt";
                this.fileChooserIsSelected = false;

                this.modelosInterface = new String[] {
                                "interface ethernet 1/1",
                                "interface ethernet 1/2",
                                "interface ethernet 1/3",
                                "interface ethernet 1/4",
                                "interface ethernet 2/1",
                                "interface ethernet 2/2"
                };

                this.configuracoes = new String[] {
                                "Uma vlan por pon",
                                "Uma vlan para todas as pon",
                                "Uma vlan por pon untagged"
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

                this.interfaceGpon = interfaceGponG08;

        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
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
                jLabel7 = new javax.swing.JLabel();
                jRadioButtonBridge = new javax.swing.JRadioButton();
                jRadioButtonRouter = new javax.swing.JRadioButton();
                jComboBoxInterfaceUplink = new javax.swing.JComboBox<>();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jComboBoxModoAutoConfig = new javax.swing.JComboBox<>();
                jLabel6 = new javax.swing.JLabel();
                jLabel8 = new javax.swing.JLabel();
                jTextFieldRangeProfileVlan = new javax.swing.JTextField();
                jTextFieldRangeProfileLine = new javax.swing.JTextField();
                jLabel5 = new javax.swing.JLabel();
                jTextFieldRangeVlan = new javax.swing.JTextField();
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

                setBackground(new java.awt.Color(204, 204, 204));
                setClosable(true);
                setForeground(java.awt.Color.darkGray);
                setIconifiable(true);
                setMaximizable(true);
                setResizable(true);
                setTitle("G08");
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                setMinimumSize(new java.awt.Dimension(812, 700));
                setPreferredSize(new java.awt.Dimension(812, 700));
                setRequestFocusEnabled(false);

                jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Dados de acesso da OLT ",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N
                jPanel1.setToolTipText("OLT");
                jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jLabel1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel1.setText("IP OLT");

                jLabel2.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel2.setText("Usuario");

                jLabel9.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel9.setText("Porta");

                jLabel10.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel10.setText("Senha");

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

                jFormattedTextFieldPortOlt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
                                new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#####"))));
                jFormattedTextFieldPortOlt.setToolTipText("porta");
                jFormattedTextFieldPortOlt.setName("porta"); // NOI18N
                jFormattedTextFieldPortOlt.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jFormattedTextFieldPortOltActionPerformed(evt);
                        }
                });

                final javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(jLabel9))
                                                                .addGap(12, 12, 12)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jFormattedTextFieldPortOlt,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                181,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTextFieldIpOlt,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                181,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(180, 180, 180)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                false)
                                                                                .addComponent(jLabel10,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel2,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(12, 12, 12)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jTextFieldOltUser,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                190,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jPasswordFieldOltPasswd,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                190,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(12, 12, 12)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(jLabel2)
                                                                                .addComponent(jTextFieldOltUser,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTextFieldIpOlt,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel9)
                                                                                .addComponent(jLabel10)
                                                                                .addComponent(jPasswordFieldOltPasswd,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jFormattedTextFieldPortOlt,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(6, 6, 6)));

                jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Formulario do Script ",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N

                jLabel7.setText("CPEs de terceiros, em:");

                buttonGroup1.add(jRadioButtonBridge);
                jRadioButtonBridge.setSelected(true);
                jRadioButtonBridge.setText("BRIDGE");
                jRadioButtonBridge.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
                jRadioButtonBridge.setMargin(new java.awt.Insets(0, 0, 0, 0));
                jRadioButtonBridge.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jRadioButtonBridgeActionPerformed(evt);
                        }
                });

                buttonGroup1.add(jRadioButtonRouter);
                jRadioButtonRouter.setText("ROUTER");
                jRadioButtonRouter.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
                jRadioButtonRouter.setMargin(new java.awt.Insets(0, 0, 0, 0));
                jRadioButtonRouter.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jRadioButtonRouterActionPerformed(evt);
                        }
                });

                jComboBoxInterfaceUplink.setMaximumRowCount(6);
                jComboBoxInterfaceUplink.setModel(new javax.swing.DefaultComboBoxModel<>(this.modelosInterface));
                jComboBoxInterfaceUplink.setMinimumSize(new java.awt.Dimension(65, 23));
                jComboBoxInterfaceUplink.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jComboBoxInterfaceUplinkActionPerformed(evt);
                        }
                });

                jLabel3.setText("Interface Uplink");

                jLabel4.setText("Profile Line");

                jComboBoxModoAutoConfig.setMaximumRowCount(3);
                jComboBoxModoAutoConfig.setModel(new javax.swing.DefaultComboBoxModel<>(this.configuracoes));
                jComboBoxModoAutoConfig.setToolTipText("");
                jComboBoxModoAutoConfig.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jComboBoxModoAutoConfigActionPerformed(evt);
                        }
                });

                jLabel6.setText("Modo do Auto-Config");

                jLabel8.setText("Profile Vlan");

                jTextFieldRangeProfileVlan.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jTextFieldRangeProfileVlanActionPerformed(evt);
                        }
                });

                jTextFieldRangeProfileLine.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jTextFieldRangeProfileLineActionPerformed(evt);
                        }
                });

                jLabel5.setText("Vlan");

                jTextFieldRangeVlan.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jTextFieldRangeVlanActionPerformed(evt);
                        }
                });

                final javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                false)
                                                                                .addComponent(jLabel5,
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel3,
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                127, Short.MAX_VALUE)
                                                                                .addComponent(jLabel4,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(jTextFieldRangeVlan)
                                                                                .addComponent(jComboBoxInterfaceUplink,
                                                                                                0, 186,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jTextFieldRangeProfileLine))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel6,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                137,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel8,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                137,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jTextFieldRangeProfileVlan,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                242,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(jComboBoxModoAutoConfig,
                                                                                                                                0,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(12, 12, 12)
                                                                                                                                .addComponent(jRadioButtonBridge)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(jRadioButtonRouter))
                                                                                                                .addComponent(jLabel7))
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addContainerGap()));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                23,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jComboBoxInterfaceUplink,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel6,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                23,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jComboBoxModoAutoConfig,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(6, 6, 6)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel8,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                23,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel5,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                23,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTextFieldRangeVlan)
                                                                                .addComponent(jTextFieldRangeProfileVlan,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(6, 6, 6)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel4,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                23,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jTextFieldRangeProfileLine))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel7)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jRadioButtonBridge)
                                                                                                                .addComponent(jRadioButtonRouter))))
                                                                .addGap(12, 12, 12)));

                jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Script pronto",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N
                jPanel3.setToolTipText("OLT");
                jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jLabel15.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel15.setText("Importar Script");

                jButtonFileChooser.setText("File");
                jButtonFileChooser.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonFileChooserActionPerformed(evt);
                        }
                });

                final javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel15)
                                                                .addGap(80, 80, 80)
                                                                .addComponent(jButtonFileChooser,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                140,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(12, 12, 12)));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jButtonFileChooser)
                                                                                .addComponent(jLabel15))
                                                                .addGap(0, 0, 0)));

                jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comandos",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N
                jPanel4.setVerifyInputWhenFocusTarget(false);

                jButtonEnviar.setText("Enviar");
                jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonEnviarActionPerformed(evt);
                        }
                });

                jButtonCriar.setText("Criar");
                jButtonCriar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonCriarActionPerformed(evt);
                        }
                });

                jButtonCancel.setText("Cancel");
                jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonCancelActionPerformed(evt);
                        }
                });

                final javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel4Layout.createSequentialGroup()
                                                                                .addGap(71, 71, 71)
                                                                                .addComponent(jButtonEnviar)
                                                                                .addGap(14, 14, 14)
                                                                                .addComponent(jButtonCriar)
                                                                                .addGap(14, 14, 14)
                                                                                .addComponent(jButtonCancel)
                                                                                .addContainerGap()));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jButtonEnviar)
                                                                                .addComponent(jButtonCriar)
                                                                                .addComponent(jButtonCancel))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Preview",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N

                jTextAreaPreviewCode.setEditable(false);
                jTextAreaPreviewCode.setColumns(20);
                jTextAreaPreviewCode.setRows(5);
                jScrollPanel.setViewportView(jTextAreaPreviewCode);

                final javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(jScrollPanel)
                                                                .addGap(6, 6, 6)));
                jPanel5Layout.setVerticalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 250,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                layout.createSequentialGroup()
                                                                                                                .addComponent(jPanel3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(jPanel4,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jPanel2,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel1,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel5,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(jPanel3,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel4,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel5,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void jPasswordFieldOltPasswdActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jPasswordFieldOltPasswdActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jPasswordFieldOltPasswdActionPerformed

        private void jTextFieldIpOltActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldIpOltActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextFieldIpOltActionPerformed

        private void jFormattedTextFieldPortOltActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jFormattedTextFieldPortOltActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jFormattedTextFieldPortOltActionPerformed

        private void jRadioButtonBridgeActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonBridgeActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jRadioButtonBridgeActionPerformed

        private void jRadioButtonRouterActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonRouterActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jRadioButtonRouterActionPerformed

        private void jComboBoxInterfaceUplinkActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxInterfaceUplinkActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jComboBoxInterfaceUplinkActionPerformed

        private void jComboBoxModoAutoConfigActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxModoAutoConfigActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jComboBoxModoAutoConfigActionPerformed

        private void jTextFieldRangeProfileVlanActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldRangeProfileVlanActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextFieldRangeProfileVlanActionPerformed

        private void jTextFieldRangeProfileLineActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldRangeProfileLineActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextFieldRangeProfileLineActionPerformed

        private void jTextFieldRangeVlanActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldRangeVlanActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextFieldRangeVlanActionPerformed

        private void jButtonFileChooserActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonFileChooserActionPerformed
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
                                        "Nenhum arquivo selecionado.", "Error!",
                                        JOptionPane.ERROR_MESSAGE, this.errorIcon);
                        jButtonFileChooser.setText("File");
                        jTextAreaPreviewCode.setText("");
                }
        }// GEN-LAST:event_jButtonFileChooserActionPerformed

        private void jButtonEnviarActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonEnviarActionPerformed
                if (this.fileChooserIsSelected) {
                        if (this.oltGpon.checkTelnet(jTextFieldIpOlt.getText(), jFormattedTextFieldPortOlt.getText(),
                                        jTextFieldOltUser.getText(), jPasswordFieldOltPasswd.getPassword(),
                                        this.errorIcon)) {
                                JOptionPane.showMessageDialog(null,
                                                "Valores validos!", "Sucesso!",
                                                JOptionPane.ERROR_MESSAGE, this.successIcon);
                                final SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                                        @Override
                                        protected Void doInBackground() throws Exception {
                                                final Telnet acessoOlt = new Telnet(jTextFieldIpOlt.getText(),
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
        }// GEN-LAST:event_jButtonEnviarActionPerformed

        private void jButtonCriarActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCriarActionPerformed
                this.nomeArq = "script08.txt";

                String terceiros = "router";
                if (jRadioButtonBridge.isSelected()) {
                        terceiros = "bridge";
                }
                if (this.oltGpon.start(this.interfaceGpon, (String) jComboBoxInterfaceUplink.getSelectedItem(),
                                (String) jComboBoxModoAutoConfig.getSelectedItem(),
                                terceiros, this.slotLength, jTextFieldRangeVlan.getText(),
                                jTextFieldRangeProfileVlan.getText(),
                                jTextFieldRangeProfileLine.getText(),
                                this.configuracoes, nomeArq)) {
                        // Cria um objeto ConfigGenerator para gerar o script de configuração
                        JOptionPane.showMessageDialog(null,
                                        "Script criado com sucesso!", "Sucesso!",
                                        JOptionPane.ERROR_MESSAGE, this.successIcon);
                        this.fileChooserIsSelected = true;
                        jButtonFileChooser.setText(this.nomeArq);
                        previewText();

                } else {
                        JOptionPane.showMessageDialog(null,
                                        "Script nao foi criado!", "Error!",
                                        JOptionPane.ERROR_MESSAGE, this.errorIcon);
                }
        }// GEN-LAST:event_jButtonCriarActionPerformed

        private void jTextFieldOltUserActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldOltUserActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextFieldOltUserActionPerformed

        private void jButtonCancelActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCancelActionPerformed
                this.dispose();
        }// GEN-LAST:event_jButtonCancelActionPerformed
}
