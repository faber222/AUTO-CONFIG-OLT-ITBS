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
import engtelecom.access.read.itbs.SshRead;
import engtelecom.access.read.itbs.TelnetRead;
import engtelecom.access.write.fhtt.TelnetWriteFhtt;
import engtelecom.analytics.DataAnaliser;
import engtelecom.config.ConfigCutoverGenerator;
import engtelecom.swingType.cutoverFhtt.destino.Olt5kCutoverDestinoAcesso;
import engtelecom.swingType.cutoverFhtt.destino.Olt5kCutoverDestinoAcessoListener;
import engtelecom.swingType.cutoverFhtt.origem.Olt5kCutoverOrigemAcesso;
import engtelecom.swingType.cutoverFhtt.origem.Olt5kCutoverOrigemAcessoListener;

/**
 *
 * @author faber222
 */
public class OltCutover extends javax.swing.JInternalFrame
                implements Olt5kCutoverOrigemAcessoListener, Olt5kCutoverDestinoAcessoListener {

        private final SpinnerNumberModel modelChassiPon;
        private final SpinnerNumberModel modelChassiUp;
        private final SpinnerNumberModel modelPortaUp;

        // Variáveis de UI
        private javax.swing.JButton jButtonCancel;
        private javax.swing.JButton jButtonColetar;
        private javax.swing.JButton jButtonCriar;
        private javax.swing.JButton jButtonFileChooser;
        private javax.swing.JButton jButtonIniciar;
        private javax.swing.JButton jButtonDadosOltOrigem;
        private javax.swing.JButton jButtonDadosOltDestino;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel19;
        private javax.swing.JLabel jLabel20;
        private javax.swing.JLabel jLabelDadosOltOrigem;
        private javax.swing.JLabel jLabelDadosOltDestino;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPanel jPanel6;
        private javax.swing.JPanel jPanel7;
        private javax.swing.JPanel jPanelDadosDestino;
        private javax.swing.JPanel jPanelDadosOrigem;
        private javax.swing.JScrollPane jScrollPanel;
        private javax.swing.JScrollPane jScrollPaneOrigem;
        private javax.swing.JScrollPane jScrollPaneDestino;
        private javax.swing.JSpinner jSpinnerPortaUplink;
        private javax.swing.JSpinner jSpinnerSlotPON;
        private javax.swing.JSpinner jSpinnerSlotUplink;
        private javax.swing.JTextArea jTextAreaPreviewCode;
        private javax.swing.JTextPane jTextPaneDadosOltOrigem;
        private javax.swing.JTextPane jTextPaneDadosOltDestino;
        private javax.swing.JComboBox<String> jComboBoxOltType;

        // Ícones
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

        // Dialogos de acesso
        private final Olt5kCutoverOrigemAcesso olt5kCutoverOrigemAcesso;
        private final Olt5kCutoverDestinoAcesso olt5kCutoverDestinoAcesso;

        // Variáveis de estado e dados da OLT de Origem
        private String ipOltOrigem;
        private String userOltOrigem;
        private String passOltOrigem;
        private String portOltOrigem;
        private boolean isTelnetOltOrigem;
        private boolean dadosOrigemPreenchidos;

        // Variáveis de estado e dados da OLT de Destino
        private String ipOltDestino;
        private String userOltDestino;
        private String passOltDestino;
        private String portOltDestino;
        private boolean dadosDestinoPreenchidos;

        private boolean fileChooserIsSelected;
        private String filePath;
        private boolean scriptCriado;

        /**
         * Creates new form OltG16
         */
        public OltCutover(App app) {
                this.modelChassiPon = new SpinnerNumberModel(1, 0, 250, 1);
                this.modelChassiUp = new SpinnerNumberModel(1, 0, 250, 1);
                this.modelPortaUp = new SpinnerNumberModel(1, 0, 100, 1);

                // Ícones
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

                // Estado inicial
                this.fileChooserIsSelected = false;
                this.filePath = "";
                this.scriptCriado = false;
                this.dadosOrigemPreenchidos = false;
                this.dadosDestinoPreenchidos = false;

                // Instancia os dialogs
                this.olt5kCutoverOrigemAcesso = new Olt5kCutoverOrigemAcesso();
                this.olt5kCutoverDestinoAcesso = new Olt5kCutoverDestinoAcesso();

                // Define os listeners
                this.olt5kCutoverOrigemAcesso.setListener(this);
                this.olt5kCutoverDestinoAcesso.setListener(this);

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
                jPanelDadosOrigem = new javax.swing.JPanel();
                jLabelDadosOltOrigem = new javax.swing.JLabel();
                jScrollPaneOrigem = new javax.swing.JScrollPane();
                jTextPaneDadosOltOrigem = new javax.swing.JTextPane();
                jButtonDadosOltOrigem = new javax.swing.JButton();
                jPanelDadosDestino = new javax.swing.JPanel();
                jLabelDadosOltDestino = new javax.swing.JLabel();
                jScrollPaneDestino = new javax.swing.JScrollPane();
                jTextPaneDadosOltDestino = new javax.swing.JTextPane();
                jButtonDadosOltDestino = new javax.swing.JButton();

                setClosable(true);
                setIconifiable(true);
                setTitle("OLT-CUTOVER");
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                setFrameIcon(null);
                setName(""); // NOI18N
                setRequestFocusEnabled(false);

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

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
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
                                                                                42, Short.MAX_VALUE)
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

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
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

                jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DADOS DE DESTINO",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11))); // NOI18N

                jLabel19.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
                jLabel19.setText("Chassi PON:");

                jLabel5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
                jLabel5.setText("Chassi Uplink:");

                jLabel20.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
                jLabel20.setText("Porta Uplink:");

                // --- Início da Modificação ---
                final javax.swing.JLabel jLabelOltType = new javax.swing.JLabel();
                jLabelOltType.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
                jLabelOltType.setText("OLT Destino:");

                jComboBoxOltType = new javax.swing.JComboBox<>();
                jComboBoxOltType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AN6000", "AN5000" }));

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(3, 3, 3)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
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
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabelOltType)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jComboBoxOltType,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                120,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel19,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                23,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jSpinnerSlotPON,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabelOltType)
                                                                                .addComponent(jComboBoxOltType,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))                                                              );
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

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
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

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
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

                jPanelDadosOrigem.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados OLT Origem"));

                jLabelDadosOltOrigem.setText("Dados de acesso:");

                jTextPaneDadosOltOrigem.setEditable(false);
                jScrollPaneOrigem.setViewportView(jTextPaneDadosOltOrigem);

                jButtonDadosOltOrigem.setText("...");
                jButtonDadosOltOrigem.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButtonDadosOltOrigemActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanelDadosOrigemLayout = new javax.swing.GroupLayout(jPanelDadosOrigem);
                jPanelDadosOrigem.setLayout(jPanelDadosOrigemLayout);
                jPanelDadosOrigemLayout.setHorizontalGroup(
                                jPanelDadosOrigemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanelDadosOrigemLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabelDadosOltOrigem)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPaneOrigem)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jButtonDadosOltOrigem,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                48,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
                jPanelDadosOrigemLayout.setVerticalGroup(
                                jPanelDadosOrigemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanelDadosOrigemLayout.createSequentialGroup()
                                                                .addGroup(jPanelDadosOrigemLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanelDadosOrigemLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                .addComponent(jScrollPaneOrigem)
                                                                                                .addComponent(jButtonDadosOltOrigem,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addComponent(jLabelDadosOltOrigem))
                                                                .addGap(0, 6, Short.MAX_VALUE)));

                jPanelDadosDestino.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados OLT Destino"));

                jLabelDadosOltDestino.setText("Dados de acesso:");

                jTextPaneDadosOltDestino.setEditable(false);
                jScrollPaneDestino.setViewportView(jTextPaneDadosOltDestino);

                jButtonDadosOltDestino.setText("...");
                jButtonDadosOltDestino.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButtonDadosOltDestinoActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanelDadosDestinoLayout = new javax.swing.GroupLayout(jPanelDadosDestino);
                jPanelDadosDestino.setLayout(jPanelDadosDestinoLayout);
                jPanelDadosDestinoLayout.setHorizontalGroup(
                                jPanelDadosDestinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanelDadosDestinoLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabelDadosOltDestino)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPaneDestino)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jButtonDadosOltDestino,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                48,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
                jPanelDadosDestinoLayout.setVerticalGroup(
                                jPanelDadosDestinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanelDadosDestinoLayout.createSequentialGroup()
                                                                .addGroup(jPanelDadosDestinoLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabelDadosOltDestino)
                                                                                .addComponent(jScrollPaneDestino,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jButtonDadosOltDestino))
                                                                .addGap(0, 6, Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
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
                                                                                .addComponent(jPanel3,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                layout.createSequentialGroup()
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
                                                                                                                                Short.MAX_VALUE))
                                                                                .addComponent(jPanelDadosOrigem,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanelDadosDestino,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(12, 12, 12)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jPanelDadosOrigem,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanelDadosDestino,
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
                        final String oltType = (String) jComboBoxOltType.getSelectedItem();
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
                if (!dadosDestinoPreenchidos) {
                        JOptionPane.showMessageDialog(null, "Dados da OLT de destino não preenchidos.", "Erro!",
                                        JOptionPane.ERROR_MESSAGE, null);
                        return;
                }
                if (scriptCriado) {
                        JOptionPane.showMessageDialog(null,
                                        "Acessando a OLT remotamente....", null,
                                        JOptionPane.INFORMATION_MESSAGE, null);
                        String oltType = "AN6000"; // Hardcoded

                        final TelnetWriteFhtt tesTelnetFhtt = new TelnetWriteFhtt(this.ipOltDestino,
                                        Integer.parseInt(this.portOltDestino),
                                        this.userOltDestino,
                                        this.passOltDestino,
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

                } else {
                        JOptionPane.showMessageDialog(null,
                                        "Nenhum script criado para enviar.", "Error!",
                                        JOptionPane.ERROR_MESSAGE, null);
                }
        }

        private void previewText(String path) {
                try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                        final StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                                content.append(line).append("\n");
                        }
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
                if (!dadosOrigemPreenchidos) {
                        JOptionPane.showMessageDialog(null, "Dados da OLT de origem não preenchidos.", "Erro!",
                                        JOptionPane.ERROR_MESSAGE, null);
                        return;
                }

                JOptionPane.showMessageDialog(null,
                                "Acessando a OLT remotamente....", null,
                                JOptionPane.INFORMATION_MESSAGE, null);
                boolean acessou = false;
                String arq = "dados.txt";
                jButtonColetar.setText("Coletar");
                fileChooserIsSelected = false;

                if (this.isTelnetOltOrigem) {
                        final TelnetRead telnet = new TelnetRead(this.ipOltOrigem,
                                        Integer.parseInt(this.portOltOrigem), this.userOltOrigem,
                                        this.passOltOrigem, arq);
                        if (telnet.oltAccess()) {
                                acessou = true;
                        }
                } else {
                        final SshRead sshClient = new SshRead(this.ipOltOrigem,
                                        Integer.parseInt(this.portOltOrigem), this.userOltOrigem,
                                        this.passOltOrigem, arq);
                        if (sshClient.oltAccess()) {
                                acessou = true;
                        }
                }

                if (acessou) {
                        this.filePath = arq;
                        previewText(this.filePath);
                        jButtonColetar.setText(this.filePath);
                        fileChooserIsSelected = true;
                        JOptionPane.showMessageDialog(null,
                                        "Script importado com sucesso", null,
                                        JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                        JOptionPane.showMessageDialog(null,
                                        "Não foi possível importar o script", null,
                                        JOptionPane.ERROR_MESSAGE, null);
                }
        }

        private void jButtonFileChooserActionPerformed(final java.awt.event.ActionEvent evt) {
                final JFileChooser fileChooser = new JFileChooser();
                final FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                "Arquivos de Texto", "txt", "md", "csv", "log", "java", "xml", "html", "json");
                fileChooser.setFileFilter(filter);
                fileChooser.setDialogTitle("Selecione o arquivo:");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                final int returnValue = fileChooser.showOpenDialog(this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                        this.fileChooserIsSelected = true;
                        final java.io.File selectedFile = fileChooser.getSelectedFile();
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

        private void jButtonDadosOltOrigemActionPerformed(java.awt.event.ActionEvent evt) {
                olt5kCutoverOrigemAcesso.setVisible(true);
        }

        private void jButtonDadosOltDestinoActionPerformed(java.awt.event.ActionEvent evt) {
                olt5kCutoverDestinoAcesso.setVisible(true);
        }

        @Override
        public void onProfileDadosOrigemCreated(final String ip, final String user, final String passwd,
                        final String port, final boolean isTelnet) {
                this.ipOltOrigem = ip;
                this.userOltOrigem = user;
                this.passOltOrigem = passwd;
                this.portOltOrigem = port;
                this.isTelnetOltOrigem = isTelnet;
                this.dadosOrigemPreenchidos = true;

                String acesso = isTelnet ? "TELNET" : "SSH";
                jTextPaneDadosOltOrigem.setText(String.format("IP: %s, Porta: %s, User: %s, Acesso: %s",
                                ip, port, user, acesso));
        }

        @Override
        public void onProfileDadosDestinoCreated(final String ip, final String user, final String passwd,
                        final String port) {
                this.ipOltDestino = ip;
                this.userOltDestino = user;
                this.passOltDestino = passwd;
                this.portOltDestino = port;
                this.dadosDestinoPreenchidos = true;

                jTextPaneDadosOltDestino.setText(String.format("IP: %s, Porta: %s, User: %s, Acesso: TELNET",
                                ip, port, user));
        }

}