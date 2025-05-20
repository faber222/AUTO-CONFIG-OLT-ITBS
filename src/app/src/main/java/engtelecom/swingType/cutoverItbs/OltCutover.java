/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package engtelecom.swingType.cutoverItbs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import engtelecom.App;
import engtelecom.access.read.SSHClient;
import engtelecom.access.read.TelnetCutover;
import engtelecom.access.write.fhtt.TelnetFhtt;
import engtelecom.analytics.DataAnaliser;
import engtelecom.config.ConfigCutoverGenerator;

/**
 *
 * @author faber222
 */
public class OltCutover extends javax.swing.JInternalFrame {

        private final SpinnerNumberModel modelChassiPon;
        private final SpinnerNumberModel modelChassiUp;
        private final SpinnerNumberModel modelPortaTelnet;
        private final SpinnerNumberModel modelPortaTelnet2;
        private final SpinnerNumberModel modelPortaUp;

        // Variables declaration - do not modify
        private javax.swing.ButtonGroup buttonGroup1;

        private javax.swing.ButtonGroup buttonGroup2;

        private javax.swing.ButtonGroup buttonGroup3;

        private javax.swing.JButton jButtonCancel;

        private javax.swing.JButton jButtonCriar;

        private javax.swing.JButton jButtonFileChooser;

        private javax.swing.JButton jButtonIniciar;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel19;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel20;
        private javax.swing.JLabel jLabel22;
        private javax.swing.JLabel jLabel23;
        private javax.swing.JLabel jLabel24;
        private javax.swing.JLabel jLabel25;
        private javax.swing.JLabel jLabel26;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JPanel jPanel7;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPanel jPanel6;
        private javax.swing.JPanel jPanel8;

        private javax.swing.JPasswordField jPasswordFieldOltPasswdDestino;
        private javax.swing.JPasswordField jPasswordFieldOltPasswdOrigem;
        private javax.swing.JRadioButton jRadioButtonAN6k;
        private javax.swing.JRadioButton jRadioButtonAn5k;
        // private javax.swing.JRadioButton jRadioButtonSSHDestino;
        private javax.swing.JRadioButton jRadioButtonSSHOrigem;
        private javax.swing.JRadioButton jRadioButtonTELNELDestino;
        private javax.swing.JRadioButton jRadioButtonTELNETOrigem;
        private javax.swing.JScrollPane jScrollPanel;
        private javax.swing.JSpinner jSpinnerPortOltDestino;
        private javax.swing.JSpinner jSpinnerPortOltOrigem;
        private javax.swing.JSpinner jSpinnerPortaUplink;
        private javax.swing.JSpinner jSpinnerSlotPON;
        private javax.swing.JSpinner jSpinnerSlotUplink;
        private javax.swing.JTextArea jTextAreaPreviewCode;
        private javax.swing.JTextField jTextFieldIpOltDestino;
        private javax.swing.JTextField jTextFieldIpOltOrigem;
        private javax.swing.JTextField jTextFieldOltUserDestino;
        private javax.swing.JTextField jTextFieldOltUserOrigem;

        private final ImageIcon ipIcon;
        private final ImageIcon portIcon;
        private final ImageIcon userIcon;
        private final ImageIcon passIcon;
        private final ImageIcon oltIcon;
        private final ImageIcon collectIcon;
        private final ImageIcon fileIcon;
        private final ImageIcon fileHoverIcon;
        private final ImageIcon criarIcon;
        private final ImageIcon enviarIcon;
        private final ImageIcon sairIcon;

        private boolean fileChooserIsSelected;
        private String filePath;
        private boolean scriptCriado;

        // Variables declaration - do not modify

        // End of variables declaration

        private javax.swing.JButton jButtonColetar;

        // End of variables declaration
        // End of variables declaration
        /**
         * Creates new form OltG16
         */
        public OltCutover(App app) {
                this.modelChassiPon = new SpinnerNumberModel(1, 0, 250, 1);
                this.modelChassiUp = new SpinnerNumberModel(1, 0, 250, 1);
                this.modelPortaTelnet = new SpinnerNumberModel(23, 0, 65535, 1);
                this.modelPortaTelnet2 = new SpinnerNumberModel(23, 0, 65535, 1);
                this.modelPortaUp = new SpinnerNumberModel(1, 0, 100, 1);
                this.ipIcon = app.getIpIcon();
                this.portIcon = app.getPortIcon();
                this.userIcon = app.getUserIcon();
                this.passIcon = app.getPassIcon();
                this.oltIcon = app.getOltIcon();
                this.collectIcon = app.getCollectIcon();
                this.fileIcon = app.getFileIcon();
                this.fileHoverIcon = app.getFileHoverIcon();
                this.criarIcon = app.getCriarIcon();
                this.enviarIcon = app.getEnviarIcon();
                this.sairIcon = app.getSairIcon();
                this.fileChooserIsSelected = false;
                this.filePath = new String();
                this.scriptCriado = false;
                initComponents();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {

                buttonGroup1 = new javax.swing.ButtonGroup();
                buttonGroup2 = new javax.swing.ButtonGroup();
                buttonGroup3 = new javax.swing.ButtonGroup();
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                jLabel10 = new javax.swing.JLabel();
                jPasswordFieldOltPasswdOrigem = new javax.swing.JPasswordField();
                jTextFieldOltUserOrigem = new javax.swing.JTextField();
                jTextFieldIpOltOrigem = new javax.swing.JTextField();
                jSpinnerPortOltOrigem = new javax.swing.JSpinner(this.modelPortaTelnet);
                jLabel11 = new javax.swing.JLabel();
                jRadioButtonTELNETOrigem = new javax.swing.JRadioButton();
                jRadioButtonSSHOrigem = new javax.swing.JRadioButton();
                jPanel4 = new javax.swing.JPanel();
                jButtonCriar = new javax.swing.JButton();
                jButtonIniciar = new javax.swing.JButton();
                jButtonCancel = new javax.swing.JButton();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                jPanel5 = new javax.swing.JPanel();
                jScrollPanel = new javax.swing.JScrollPane();
                jTextAreaPreviewCode = new javax.swing.JTextArea();
                jPanel8 = new javax.swing.JPanel();
                jLabel22 = new javax.swing.JLabel();
                jLabel23 = new javax.swing.JLabel();
                jLabel24 = new javax.swing.JLabel();
                jLabel25 = new javax.swing.JLabel();
                jPasswordFieldOltPasswdDestino = new javax.swing.JPasswordField();
                jTextFieldOltUserDestino = new javax.swing.JTextField();
                jTextFieldIpOltDestino = new javax.swing.JTextField();
                jSpinnerPortOltDestino = new javax.swing.JSpinner(this.modelPortaTelnet2);
                jRadioButtonTELNELDestino = new javax.swing.JRadioButton();
                jLabel12 = new javax.swing.JLabel();
                // jRadioButtonSSHDestino = new javax.swing.JRadioButton();
                jRadioButtonAn5k = new javax.swing.JRadioButton();
                jRadioButtonAN6k = new javax.swing.JRadioButton();
                jLabel26 = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                jLabel19 = new javax.swing.JLabel();
                jSpinnerSlotPON = new javax.swing.JSpinner(this.modelChassiPon);
                jLabel5 = new javax.swing.JLabel();
                jSpinnerSlotUplink = new javax.swing.JSpinner(this.modelChassiUp);
                jSpinnerPortaUplink = new javax.swing.JSpinner(this.modelPortaUp);
                jLabel20 = new javax.swing.JLabel();
                jPanel6 = new javax.swing.JPanel();
                jButtonFileChooser = new javax.swing.JButton();
                jPanel7 = new javax.swing.JPanel();
                jButtonColetar = new javax.swing.JButton();

                setClosable(true);
                setIconifiable(true);
                setTitle("OLT-CUTOVER");
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                setFrameIcon(null);
                setName(""); // NOI18N
                setRequestFocusEnabled(false);

                jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OLT-ORIGEM",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
                jPanel1.setForeground(java.awt.SystemColor.activeCaption);
                jPanel1.setToolTipText("OLT");
                jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jLabel1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel1.setIcon(this.ipIcon); // NOI18N
                jLabel1.setText("IP OLT");

                jLabel2.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel2.setIcon(this.userIcon); // NOI18N
                jLabel2.setText("Usuario");

                jLabel9.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel9.setIcon(this.portIcon); // NOI18N
                jLabel9.setText("Porta");

                jLabel10.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel10.setIcon(this.passIcon); // NOI18N
                jLabel10.setText("Senha");

                jLabel11.setText("Acesso:");

                buttonGroup1.add(jRadioButtonTELNETOrigem);
                jRadioButtonTELNETOrigem.setText("TELNET");
                jRadioButtonTELNETOrigem.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
                jRadioButtonTELNETOrigem.setMargin(new java.awt.Insets(0, 0, 0, 0));

                buttonGroup1.add(jRadioButtonSSHOrigem);
                jRadioButtonSSHOrigem.setSelected(true);
                jRadioButtonSSHOrigem.setText("SSH");
                jRadioButtonSSHOrigem.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
                jRadioButtonSSHOrigem.setMargin(new java.awt.Insets(0, 0, 0, 0));

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
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(jSpinnerPortOltOrigem)
                                                                                .addComponent(jTextFieldIpOltOrigem,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                100,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(12, 12, 12)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(jLabel10,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel2))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(jPasswordFieldOltPasswdOrigem)
                                                                                .addComponent(jTextFieldOltUserOrigem,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                100,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(12, 12, 12)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(12, 12, 12)
                                                                                                .addComponent(jRadioButtonSSHOrigem)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jRadioButtonTELNETOrigem))
                                                                                .addComponent(jLabel11))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel1)
                                                                                                                .addComponent(jLabel2)
                                                                                                                .addComponent(jTextFieldOltUserOrigem,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jTextFieldIpOltOrigem,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel9)
                                                                                                                .addComponent(jLabel10)
                                                                                                                .addComponent(jPasswordFieldOltPasswdOrigem,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jSpinnerPortOltOrigem,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel11)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jRadioButtonSSHOrigem)
                                                                                                                .addComponent(jRadioButtonTELNETOrigem))))
                                                                .addContainerGap()));

                jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comandos",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N
                jPanel4.setVerifyInputWhenFocusTarget(false);

                jButtonCriar.setText("Criar");
                jButtonCriar.addActionListener((final java.awt.event.ActionEvent evt) -> {
                        jButtonCriarActionPerformed(evt);
                });

                jButtonIniciar.setText("Enviar");
                jButtonIniciar.addActionListener((final java.awt.event.ActionEvent evt) -> {
                        jButtonIniciarActionPerformed(evt);
                });

                jButtonCancel.setText("Cancel");
                jButtonCancel.addActionListener((final java.awt.event.ActionEvent evt) -> {
                        jButtonCancelActionPerformed(evt);
                });

                jLabel3.setIcon(this.criarIcon); // NOI18N
                jLabel3.setText("Criar script de migração:");

                jLabel4.setIcon(this.enviarIcon); // NOI18N
                jLabel4.setText("Enviar para a OLT:");

                jLabel6.setIcon(this.sairIcon); // NOI18N
                jLabel6.setText("Sair do programa:");

                final javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel3)
                                                                                .addComponent(jLabel4)
                                                                                .addComponent(jLabel6))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                false)
                                                                                .addComponent(jButtonCancel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jButtonIniciar,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jButtonCriar,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jButtonCriar)
                                                                                .addComponent(jLabel3))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jButtonIniciar)
                                                                                .addComponent(jLabel4))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jButtonCancel)
                                                                                .addComponent(jLabel6))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

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
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel5Layout.createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addComponent(jScrollPanel)
                                                                                .addGap(6, 6, 6)));
                jPanel5Layout.setVerticalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 277,
                                                                Short.MAX_VALUE));

                jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OLT-DESTINO",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N
                jPanel8.setToolTipText("OLT");
                jPanel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jLabel22.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel22.setIcon(this.ipIcon); // NOI18N
                jLabel22.setText("IP OLT");

                jLabel23.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel23.setIcon(this.userIcon); // NOI18N
                jLabel23.setText("Usuario");

                jLabel24.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel24.setIcon(this.portIcon); // NOI18N
                jLabel24.setText("Porta");

                jLabel25.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel25.setIcon(this.passIcon); // NOI18N
                jLabel25.setText("Senha");

                buttonGroup2.add(jRadioButtonTELNELDestino);
                jRadioButtonTELNELDestino.setSelected(true);
                jRadioButtonTELNELDestino.setText("TELNET");
                jRadioButtonTELNELDestino.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
                jRadioButtonTELNELDestino.setMargin(new java.awt.Insets(0, 0, 0, 0));

                jLabel12.setText("Acesso:");

                // buttonGroup2.add(jRadioButtonSSHDestino);
                // jRadioButtonSSHDestino.setSelected(true);
                // jRadioButtonSSHDestino.setText("SSH");
                // jRadioButtonSSHDestino.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,
                // 0, 0, 0));
                // jRadioButtonSSHDestino.setMargin(new java.awt.Insets(0, 0, 0, 0));

                buttonGroup3.add(jRadioButtonAn5k);
                jRadioButtonAn5k.setSelected(true);
                jRadioButtonAn5k.setText("AN5k");
                jRadioButtonAn5k.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
                jRadioButtonAn5k.setMargin(new java.awt.Insets(0, 0, 0, 0));

                buttonGroup3.add(jRadioButtonAN6k);
                jRadioButtonAN6k.setText("AN6k");
                jRadioButtonAN6k.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
                jRadioButtonAN6k.setMargin(new java.awt.Insets(0, 0, 0, 0));

                jLabel26.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
                jLabel26.setIcon(this.oltIcon); // NOI18N
                jLabel26.setText("OLT");

                final javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel8Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(jLabel22,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel24,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel26,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(12, 12, 12)
                                                                .addGroup(jPanel8Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel8Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel8Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jSpinnerPortOltDestino,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                100,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jTextFieldIpOltDestino,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                100,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(12, 12, 12)
                                                                                                .addGroup(jPanel8Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                false)
                                                                                                                .addComponent(jLabel25,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(jLabel23))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel8Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(jTextFieldOltUserDestino)
                                                                                                                .addComponent(jPasswordFieldOltPasswdDestino,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                100,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(12, 12, 12)
                                                                                                .addGroup(jPanel8Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel8Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(12, 12, 12)
                                                                                                                                // .addComponent(jRadioButtonSSHDestino)
                                                                                                                                // .addPreferredGap(
                                                                                                                                // javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(jRadioButtonTELNELDestino))
                                                                                                                .addComponent(jLabel12)))
                                                                                .addGroup(jPanel8Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jRadioButtonAn5k)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jRadioButtonAN6k)
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addContainerGap()));
                jPanel8Layout.setVerticalGroup(
                                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addGroup(jPanel8Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel8Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel12)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel8Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                // .addComponent(jRadioButtonSSHDestino)
                                                                                                                .addComponent(jRadioButtonTELNELDestino)))
                                                                                .addGroup(jPanel8Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(5, 5, 5)
                                                                                                .addGroup(jPanel8Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel8Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGroup(jPanel8Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                                .addComponent(jLabel23)
                                                                                                                                                .addComponent(jTextFieldOltUserDestino,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addGroup(jPanel8Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                                .addComponent(jLabel25)
                                                                                                                                                .addComponent(jPasswordFieldOltPasswdDestino,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                                .addGroup(jPanel8Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGroup(jPanel8Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                                .addComponent(jLabel22)
                                                                                                                                                .addComponent(jTextFieldIpOltDestino,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addGroup(jPanel8Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                                .addComponent(jLabel24)
                                                                                                                                                .addComponent(jSpinnerPortOltDestino,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel8Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel8Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jRadioButtonAn5k)
                                                                                                .addComponent(jRadioButtonAN6k))
                                                                                .addComponent(jLabel26))
                                                                .addContainerGap(15, Short.MAX_VALUE)));

                jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PORTAS DE DESTINO",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11))); // NOI18N

                jLabel19.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
                jLabel19.setText("Chassi PON:");

                jLabel5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
                jLabel5.setText("Chassi Uplink:");

                jLabel20.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
                jLabel20.setText("Porta Uplink:");

                final javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(3, 3, 3)
                                                                .addComponent(jLabel19,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                100,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jSpinnerSlotPON,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(32, 32, 32)
                                                                .addComponent(jLabel5,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                100,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(2, 2, 2)
                                                                .addComponent(jSpinnerSlotUplink,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(32, 32, 32)
                                                                .addComponent(jLabel20)
                                                                .addGap(2, 2, 2)
                                                                .addComponent(jSpinnerPortaUplink,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel19,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                23,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jSpinnerSlotPON,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel3Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jSpinnerSlotUplink,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jLabel5,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                23,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jSpinnerPortaUplink,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jLabel20,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                23,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(2, 2, 2)));

                jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Importar script local",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N
                jPanel6.setToolTipText("OLT");
                jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jButtonFileChooser.setIcon(this.fileIcon); // NOI18N
                jButtonFileChooser.setText("File");
                jButtonFileChooser.setSelectedIcon(this.fileHoverIcon); // NOI18N
                jButtonFileChooser.addActionListener((final java.awt.event.ActionEvent evt) -> {
                        jButtonFileChooserActionPerformed(evt);
                });

                final javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jButtonFileChooser,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                jPanel6Layout.setVerticalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addComponent(jButtonFileChooser,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGap(8, 8, 8)));

                jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Importar script remoto",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N
                jPanel7.setToolTipText("OLT");
                jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jButtonColetar.setIcon(this.collectIcon); // NOI18N
                jButtonColetar.setText("Coletar");
                jButtonColetar.addActionListener((final java.awt.event.ActionEvent evt) -> {
                        jButtonColetarActionPerformed(evt);
                });

                final javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jButtonColetar,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                182, Short.MAX_VALUE)
                                                                .addContainerGap()));
                jPanel7Layout.setVerticalGroup(
                                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButtonColetar));

                final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jPanel5,
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel8,
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel1,
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel3,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                                .addComponent(jPanel7,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(jPanel6,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jPanel4,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)))
                                                                .addGap(12, 12, 12)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel8,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel5,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jPanel7,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jPanel6,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(7, 7, 7)
                                                                                                .addComponent(jPanel4,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                0,
                                                                                                                Short.MAX_VALUE)))
                                                                .addGap(12, 12, 12)));

                pack();
        }// </editor-fold>

        private void jButtonCriarActionPerformed(final java.awt.event.ActionEvent evt) {

                if (this.fileChooserIsSelected) {
                        final DataAnaliser dataAnaliser = new DataAnaliser(this.filePath);
                        dataAnaliser.start();
                        String oltType = "AN5000";
                        if (jRadioButtonAN6k.isSelected()) {
                                oltType = "AN6000";
                        }
                        final String slotChassiPon = (String) jSpinnerSlotPON.getValue().toString();
                        final String slotChassiUp = (String) jSpinnerSlotUplink.getValue().toString();
                        final String slotPortaUp = (String) jSpinnerPortaUplink.getValue().toString();

                        final ConfigCutoverGenerator cutover = new ConfigCutoverGenerator(dataAnaliser.getData(),
                                        oltType, slotChassiPon, slotChassiUp, slotPortaUp);
                        if (cutover.start()) {
                                previewText("scriptMigracao.txt");
                                scriptCriado = true;
                        }

                } else {
                        JOptionPane.showMessageDialog(null,
                                        "Nenhum arquivo selecionado.", "Error!",
                                        JOptionPane.ERROR_MESSAGE, null);
                }

        }

        private void jButtonIniciarActionPerformed(final java.awt.event.ActionEvent evt) {
                if (scriptCriado) {
                        JOptionPane.showMessageDialog(null,
                                        "Acessando a OLT remotamente....", null,
                                        JOptionPane.INFORMATION_MESSAGE, null);
                        String oltType = "AN5000";
                        if (jRadioButtonAN6k.isSelected()) {
                                oltType = "AN6000";
                        }
                        // if (jRadioButtonTELNELDestino.isSelected()) {
                        final TelnetFhtt tesTelnetFhtt = new TelnetFhtt(jTextFieldIpOltDestino.getText(),
                                        (Integer) jSpinnerPortOltDestino.getValue(),
                                        jTextFieldOltUserDestino.getText(),
                                        new String(jPasswordFieldOltPasswdDestino.getPassword()),
                                        oltType);
                        if (tesTelnetFhtt.oltAccess("scriptMigracao.txt")) {
                                JOptionPane.showMessageDialog(null,
                                                "Script aplicado com sucesso!", null,
                                                JOptionPane.INFORMATION_MESSAGE, null);
                                JOptionPane.showMessageDialog(null,
                                                "NÃO ESQUEÇA DE VALIDAR O LOG GERADO E APLICAR AS CONFIGURAÇÕES!",
                                                null,
                                                JOptionPane.INFORMATION_MESSAGE, null);
                        } else {
                                JOptionPane.showMessageDialog(null,
                                                "Não foi possível aplicar o script!", null,
                                                JOptionPane.INFORMATION_MESSAGE, null);
                        }

                        // } else {
                        // final SSHClientFhtt sshClient = new SSHClientFhtt(
                        // jTextFieldIpOltDestino.getText(),
                        // (Integer) jSpinnerPortOltDestino.getValue(),
                        // jTextFieldOltUserDestino.getText(),
                        // new String(jPasswordFieldOltPasswdDestino.getPassword()), oltType);
                        // if (sshClient.oltAccess("scriptMigracao.txt")) {
                        // JOptionPane.showMessageDialog(null,
                        // "Script aplicado com sucesso!", null,
                        // JOptionPane.INFORMATION_MESSAGE, null);
                        // JOptionPane.showMessageDialog(null,
                        // "NÃO ESQUEÇA DE VALIDAR O LOG GERADO E APLICAR AS CONFIGURAÇÕES!",
                        // null,
                        // JOptionPane.INFORMATION_MESSAGE, null);
                        // } else {
                        // JOptionPane.showMessageDialog(null,
                        // "Não foi possível aplicar o script!", null,
                        // JOptionPane.INFORMATION_MESSAGE, null);
                        // }
                        // }

                } else {
                        JOptionPane.showMessageDialog(null,
                                        "Nenhum script criado para enviar.", "Error!",
                                        JOptionPane.ERROR_MESSAGE, null);
                }

        }

        private void previewText(String path) {
                // Tente abrir e ler o arquivo
                try (BufferedReader br = new BufferedReader(new FileReader(path))) {
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

        private void jButtonCancelActionPerformed(final java.awt.event.ActionEvent evt) {
                dispose();
        }

        private void jButtonColetarActionPerformed(final java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null,
                                "Acessando a OLT remotamente....", null,
                                JOptionPane.INFORMATION_MESSAGE, null);
                boolean acessou = false;
                String arq = "dados.txt";
                jButtonColetar.setText("Coletar");
                fileChooserIsSelected = false;
                if (jRadioButtonTELNETOrigem.isSelected()) {
                        final TelnetCutover telnet = new TelnetCutover(jTextFieldIpOltOrigem.getText(),
                                        (Integer) jSpinnerPortOltOrigem.getValue(), jTextFieldOltUserOrigem.getText(),
                                        new String(jPasswordFieldOltPasswdOrigem.getPassword()), arq);
                        if (telnet.oltAccess()) {
                                this.filePath = arq;
                                previewText(this.filePath);
                                jButtonColetar.setText(this.filePath);
                                acessou = true;
                                fileChooserIsSelected = true;
                        }

                } else {
                        final SSHClient sshClient = new SSHClient(jTextFieldIpOltOrigem.getText(),
                                        (Integer) jSpinnerPortOltOrigem.getValue(), jTextFieldOltUserOrigem.getText(),
                                        new String(jPasswordFieldOltPasswdOrigem.getPassword()), arq);
                        if (sshClient.oltAccess()) {
                                this.filePath = arq;
                                previewText(this.filePath);
                                jButtonColetar.setText(this.filePath);
                                acessou = true;
                                fileChooserIsSelected = true;
                        }
                }
                if (acessou) {
                        JOptionPane.showMessageDialog(null,
                                        "Script importado com sucesso", null,
                                        JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                        JOptionPane.showMessageDialog(null,
                                        "Não foi possível importar o script", null,
                                        JOptionPane.INFORMATION_MESSAGE, null);

                }
        }

        private void jButtonFileChooserActionPerformed(final java.awt.event.ActionEvent evt) {
                final JFileChooser fileChooser = new JFileChooser();

                // Adiciona um filtro para aceitar apenas arquivos de texto e derivados
                final FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                "Arquivos de Texto", "txt", "md", "csv", "log", "java", "xml", "html", "json");
                fileChooser.setFileFilter(filter);
                // Exibe o seletor de arquivo e obtém a resposta do usuário
                final int returnValue = fileChooser.showOpenDialog(this);
                fileChooser.setDialogTitle("Selecione o arquivo:");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                // Verifica se o usuário escolheu um arquivo
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                        this.fileChooserIsSelected = true;
                        // Obtém o arquivo selecionado
                        final java.io.File selectedFile = fileChooser.getSelectedFile();
                        System.out.println("Arquivo selecionado: " + selectedFile.getAbsolutePath());
                        this.filePath = selectedFile.getAbsolutePath();
                        jButtonFileChooser.setText(selectedFile.getName());
                        previewText(this.filePath);
                } else {
                        this.fileChooserIsSelected = false;
                        JOptionPane.showMessageDialog(null,
                                        "Nenhum arquivo selecionado.", "Error!",
                                        JOptionPane.ERROR_MESSAGE, null);
                        jButtonFileChooser.setText("File");
                        jTextAreaPreviewCode.setText("");
                }

        }

}
