package engtelecom.swingType;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import engtelecom.access.Telnet8820Plus;
import engtelecom.product.Olt8820Plus;
import engtelecom.product.OltGpon;

/**
 *
 * @author faber222
 */
public class OltGpon8820PlusMenu extends JFrame {

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private ButtonGroup buttonGroup1;
        private JButton jButtonCancel;
        private JButton jButtonCriar;
        private JButton jButtonEnviar;
        private JButton jButtonFileChooser;
        private JComboBox<String> jComboBoxBridgeUplink;
        private JComboBox<String> jComboBoxInterfaceUplink;
        private JComboBox<String> jComboBoxModoAutoConfig;
        private JComboBox<String> jComboBoxModoVlan;
        private JLabel jLabel1;
        private JLabel jLabel10;
        private JLabel jLabel12;
        private JLabel jLabel13;
        private JLabel jLabel15;
        private JLabel jLabel2;
        private JLabel jLabel3;
        private JLabel jLabel4;
        private JLabel jLabel6;
        private JLabel jLabel7;
        private JLabel jLabel9;
        private JPanel jPanel1;
        private JPanel jPanel2;
        private JPanel jPanel3;
        private JPasswordField jPasswordFieldOltPasswd;
        private JRadioButton jRadioButtonBridge;
        private JRadioButton jRadioButtonRouter;
        private JTextField jTextFieldIpOlt;
        private JTextField jTextFieldOltUser;
        private JTextField jTextFieldPortOlt;
        private JTextField jTextFieldRangeVlan;

        private String[] modelosInterface;
        private String[] configuracoes;
        private String[] bridgeInterfaceUplink;
        private String[] bridgeInterfaceUplinkVlanMode;
        private String nomeArq;
        private boolean fileChooserIsSelected;
        private ImageIcon errorIcon;
        private ImageIcon successIcon;
        private ImageIcon saidaIcon;
        private Olt8820Plus olt8820Plus;
        private int slotLength;

        /**
         * Creates new form OltGponMenu
         */
        public OltGpon8820PlusMenu(int slotLength) {
                this.slotLength = slotLength;
                initComponents();
                setLocationRelativeTo(null);
                olt8820Plus = new Olt8820Plus();
        }

        /**
         * @param args the command line arguments
         */
        public void start() {
                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new OltGpon8820PlusMenu(slotLength).setVisible(true);
                        }
                });

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
                final ClassLoader classLoader = OltGpon.class.getClassLoader();
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
                                "intralink",
                                "tls"
                };

                this.bridgeInterfaceUplinkVlanMode = new String[] {
                                "tagged",
                                "untagged"
                };

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

                buttonGroup1 = new ButtonGroup();
                jButtonCancel = new JButton();
                jButtonCriar = new JButton();
                jPanel1 = new JPanel();
                jLabel1 = new JLabel();
                jLabel2 = new JLabel();
                jTextFieldIpOlt = new JTextField();
                jLabel9 = new JLabel();
                jLabel10 = new JLabel();
                jPasswordFieldOltPasswd = new JPasswordField();
                jTextFieldOltUser = new JTextField();
                jTextFieldPortOlt = new JTextField();
                jPanel2 = new JPanel();
                jLabel7 = new JLabel();
                jRadioButtonBridge = new JRadioButton();
                jRadioButtonRouter = new JRadioButton();
                jComboBoxInterfaceUplink = new JComboBox<>();
                jLabel3 = new JLabel();
                jLabel4 = new JLabel();
                jComboBoxModoAutoConfig = new JComboBox<>();
                jLabel6 = new JLabel();
                jTextFieldRangeVlan = new JTextField();
                jComboBoxModoVlan = new JComboBox<>();
                jLabel12 = new JLabel();
                jComboBoxBridgeUplink = new JComboBox<>();
                jLabel13 = new JLabel();
                jButtonEnviar = new JButton();
                jPanel3 = new JPanel();
                jLabel15 = new JLabel();
                jButtonFileChooser = new JButton();

                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setTitle("OLT-AUTO-CONFIG");
                setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
                setResizable(false);

                jButtonCancel.setText("Cancel");
                jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButtonCancelActionPerformed(evt);
                        }
                });

                jButtonCriar.setText("Criar");
                jButtonCriar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButtonCriarActionPerformed(evt);
                        }
                });

                jPanel1.setBorder(BorderFactory.createTitledBorder(null, " Dados de acesso da OLT ",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N
                jPanel1.setToolTipText("OLT");
                jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jLabel1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel1.setText("IP OLT");

                jLabel2.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel2.setText("Usuario");

                // jTextFieldIpOlt.setText("John");
                jTextFieldIpOlt.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jTextFieldIpOltActionPerformed(evt);
                        }
                });

                jLabel9.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel9.setText("Porta");

                jLabel10.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel10.setText("Senha");

                // jPasswordFieldOltPasswd.setText("jPasswordField1");
                jPasswordFieldOltPasswd.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jPasswordFieldOltPasswdActionPerformed(evt);
                        }
                });

                // jTextFieldOltUser.setText("John");

                // jTextFieldPortOlt.setText("John");
                jTextFieldPortOlt.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jTextFieldPortOltActionPerformed(evt);
                        }
                });

                GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(jLabel9))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jTextFieldPortOlt,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                328,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jTextFieldIpOlt))
                                                                .addGap(35, 35, 35)
                                                                .addGroup(jPanel1Layout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                false)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel2,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jTextFieldOltUser,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                357,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel10,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGap(26, 26, 26)
                                                                                                .addComponent(jPasswordFieldOltPasswd,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                357,
                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap()));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(jTextFieldIpOlt,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel2)
                                                                                .addComponent(jTextFieldOltUser,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel9)
                                                                                .addComponent(jLabel10)
                                                                                .addComponent(jTextFieldPortOlt)
                                                                                .addComponent(jPasswordFieldOltPasswd))
                                                                .addContainerGap()));

                jPanel2.setBorder(BorderFactory.createTitledBorder(null, " Formulario do Script ",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N

                jLabel7.setText("CPEs de terceiros, em:");

                jRadioButtonBridge.setSelected(true);
                buttonGroup1.add(jRadioButtonBridge);
                jRadioButtonBridge.setText("BRIDGE");
                jRadioButtonBridge.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                jRadioButtonBridge.setMargin(new java.awt.Insets(0, 0, 0, 0));
                jRadioButtonBridge.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jRadioButtonBridgeActionPerformed(evt);
                        }
                });

                buttonGroup1.add(jRadioButtonRouter);
                jRadioButtonRouter.setText("ROUTER");
                jRadioButtonRouter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                jRadioButtonRouter.setMargin(new java.awt.Insets(0, 0, 0, 0));
                jRadioButtonRouter.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jRadioButtonRouterActionPerformed(evt);
                        }
                });

                jLabel3.setText("Interface Uplink");
                jComboBoxInterfaceUplink.setMaximumRowCount(6);
                jComboBoxInterfaceUplink.setModel(
                                new DefaultComboBoxModel<>(modelosInterface));
                jComboBoxInterfaceUplink.setMinimumSize(new java.awt.Dimension(65, 23));
                jComboBoxInterfaceUplink.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jComboBoxInterfaceUplinkActionPerformed(evt);
                        }
                });

                jLabel6.setText("Modo do Auto-Config");
                jComboBoxModoAutoConfig.setMaximumRowCount(3);
                jComboBoxModoAutoConfig.setModel(
                                new DefaultComboBoxModel<>(configuracoes));
                jComboBoxModoAutoConfig.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jComboBoxModoAutoConfigActionPerformed(evt);
                        }
                });

                jLabel4.setText("Range Vlan");
                // jTextFieldRangeVlan.setText("jTextField2");
                jTextFieldRangeVlan.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jTextFieldRangeVlanActionPerformed(evt);
                        }
                });

                jLabel12.setText("Modo Vlan");
                jComboBoxModoVlan.setMaximumRowCount(3);
                jComboBoxModoVlan.setModel(
                                new DefaultComboBoxModel<>(bridgeInterfaceUplinkVlanMode));
                jComboBoxModoVlan.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jComboBoxModoVlanActionPerformed(evt);
                        }
                });

                jLabel13.setText("Bridge Uplink");
                jComboBoxBridgeUplink.setMaximumRowCount(6);
                jComboBoxBridgeUplink.setModel(
                                new DefaultComboBoxModel<>(bridgeInterfaceUplink));
                jComboBoxBridgeUplink.setMinimumSize(new java.awt.Dimension(65, 23));
                jComboBoxBridgeUplink.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jComboBoxBridgeUplinkActionPerformed(evt);
                        }
                });

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel4,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                137,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                                .addComponent(jLabel3,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                137,
                                                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                                                .addGap(11, 11, 11)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(jComboBoxInterfaceUplink,
                                                                                                                                0,
                                                                                                                                231,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(jTextFieldRangeVlan)))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel13,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                137,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(11, 11, 11)
                                                                                                .addComponent(jComboBoxBridgeUplink,
                                                                                                                0,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)))
                                                                .addGap(38, 38, 38)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel6,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                137,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jComboBoxModoAutoConfig,
                                                                                                                0, 269,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(12, 12, 12)
                                                                                                                                .addComponent(jRadioButtonBridge)
                                                                                                                                .addPreferredGap(
                                                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(jRadioButtonRouter))
                                                                                                                .addComponent(jLabel7))
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel12,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                137,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jComboBoxModoVlan,
                                                                                                                0,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)))
                                                                .addContainerGap()));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jComboBoxModoAutoConfig)
                                                                                                .addComponent(jLabel6,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                23,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel3,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                23,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jComboBoxInterfaceUplink,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jComboBoxModoVlan)
                                                                                .addComponent(jLabel12,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                23,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel13,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                23,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jComboBoxBridgeUplink,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel4,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                23,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jTextFieldRangeVlan,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel7)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jRadioButtonBridge)
                                                                                                                .addComponent(jRadioButtonRouter))))
                                                                .addGap(12, 12, 12)));

                jButtonEnviar.setText("Enviar");
                jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButtonEnviarActionPerformed(evt);
                        }
                });

                jPanel3.setBorder(BorderFactory.createTitledBorder(null, "Script pronto",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N
                jPanel3.setToolTipText("OLT");
                jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jLabel15.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel15.setText("Importar Script");

                jButtonFileChooser.setText("File");
                jButtonFileChooser.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButtonFileChooserActionPerformed(evt);
                        }
                });

                GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel15)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                                                41,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jButtonFileChooser,
                                                                                GroupLayout.PREFERRED_SIZE, 218,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout
                                                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel15)
                                                                .addComponent(jButtonFileChooser)));

                GroupLayout layout = new GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jPanel1,
                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel2,
                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(GroupLayout.Alignment.LEADING,
                                                                                                layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jPanel3,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(jButtonEnviar)
                                                                                                                .addGap(10, 10, 10)
                                                                                                                .addComponent(jButtonCriar)
                                                                                                                .addGap(10, 10, 10)
                                                                                                                .addComponent(jButtonCancel)))
                                                                .addGap(21, 21, 21)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jPanel3,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(layout.createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jButtonCancel)
                                                                                                .addComponent(jButtonCriar)
                                                                                                .addComponent(jButtonEnviar)))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                jPanel1.getAccessibleContext().setAccessibleName("OLT");

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void jComboBoxInterfaceUplinkActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxInterfaceUplinkActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jComboBoxInterfaceUplinkActionPerformed

        private void jRadioButtonRouterActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonRouterActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jRadioButtonRouterActionPerformed

        private void jRadioButtonBridgeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonBridgeActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jRadioButtonBridgeActionPerformed

        private void jTextFieldRangeVlanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldRangeVlanActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextFieldRangeVlanActionPerformed

        private void jTextFieldPortOltActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldPortOltActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextFieldPortOltActionPerformed

        private void jPasswordFieldOltPasswdActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jPasswordFieldOltPasswdActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jPasswordFieldOltPasswdActionPerformed

        private void jComboBoxModoAutoConfigActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxModoAutoConfigActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jComboBoxModoAutoConfigActionPerformed

        private void jTextFieldIpOltActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldIpOltActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextFieldIpOltActionPerformed

        private void jComboBoxModoVlanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxModoVlanActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jComboBoxModoVlanActionPerformed

        private void jComboBoxBridgeUplinkActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxBridgeUplinkActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jComboBoxBridgeUplinkActionPerformed

        private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxModoAutoConfigActionPerformed
                saida();
                dispose();
                System.exit(0);
        }// GEN-LAST:event_jComboBoxModoAutoConfigActionPerformed

        private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonEnviarActionPerformed
                if (this.fileChooserIsSelected) {
                        if (this.olt8820Plus.checkTelnet(jTextFieldIpOlt.getText(),
                                        jTextFieldPortOlt.getText(),
                                        jTextFieldOltUser.getText(), jPasswordFieldOltPasswd.getPassword(),
                                        this.errorIcon)) {
                                JOptionPane.showMessageDialog(null,
                                                "Valores validos!", "Sucesso",
                                                JOptionPane.ERROR_MESSAGE, this.successIcon);

                                Telnet8820Plus acessoOlt = new Telnet8820Plus(jTextFieldIpOlt.getText(),
                                                Integer.parseInt(jTextFieldPortOlt.getText()),
                                                jTextFieldOltUser.getText(),
                                                new String(jPasswordFieldOltPasswd.getPassword()));
                                acessoOlt.oltAccess(this.nomeArq);
                        }
                } else {
                        JOptionPane.showMessageDialog(null,
                                        "Nenhum arquivo selecionado ou criado!", "Error",
                                        JOptionPane.ERROR_MESSAGE, this.errorIcon);
                }

        }// GEN-LAST:event_jButtonEnviarActionPerformed

        private void jButtonCriarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCriarActionPerformed
                this.nomeArq = "script8820I.txt";

                String terceiros = "router";
                if (jRadioButtonBridge.isSelected()) {
                        terceiros = "bridge";
                }
                if (this.olt8820Plus.start((String) this.jComboBoxInterfaceUplink.getSelectedItem(), this.configuracoes,
                                terceiros, (String) this.jComboBoxModoAutoConfig.getSelectedItem(),
                                this.jTextFieldRangeVlan.getText(), (String) this.jComboBoxModoVlan.getSelectedItem(),
                                (String) this.jComboBoxBridgeUplink.getSelectedItem(), this.slotLength, this.nomeArq)) {
                        // Cria um objeto ConfigGenerator para gerar o script de configuração
                        JOptionPane.showMessageDialog(null,
                                        "Script criado com sucesso!", "Sucesso",
                                        JOptionPane.ERROR_MESSAGE, this.successIcon);
                        this.fileChooserIsSelected = true;
                        jButtonFileChooser.setText(this.nomeArq);
                } else {

                        JOptionPane.showMessageDialog(null,
                                        "Script nao foi criado!", "Error",
                                        JOptionPane.ERROR_MESSAGE, this.errorIcon);
                }

        }// GEN-LAST:event_jButtonCriarActionPerformed

        private void jButtonFileChooserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonFileChooserActionPerformed

                JFileChooser fileChooser = new JFileChooser();

                // Exibe o seletor de arquivo e obtém a resposta do usuário
                int returnValue = fileChooser.showOpenDialog(null);

                // Verifica se o usuário escolheu um arquivo
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                        this.fileChooserIsSelected = true;
                        // Obtém o arquivo selecionado
                        java.io.File selectedFile = fileChooser.getSelectedFile();
                        System.out.println("Arquivo selecionado: " + selectedFile.getAbsolutePath());
                        this.nomeArq = selectedFile.getAbsolutePath();
                        jButtonFileChooser.setText(selectedFile.getName());
                } else {
                        this.fileChooserIsSelected = false;
                        JOptionPane.showMessageDialog(null,
                                        "Nenhum arquivo selecionado.", "Error",
                                        JOptionPane.ERROR_MESSAGE, this.errorIcon);
                        jButtonFileChooser.setText("File");
                }
        }// GEN-LAST:event_jButtonFileChooserActionPerformed

        // End of variables declaration//GEN-END:variables
}
