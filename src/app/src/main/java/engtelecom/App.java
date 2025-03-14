
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package engtelecom;

import javax.swing.ImageIcon;

import engtelecom.swingType.InfoFrame;
import engtelecom.swingType.Olt8820;
import engtelecom.swingType.OltCutover;
import engtelecom.swingType.OltCutover5k;
import engtelecom.swingType.OltFhtt;
import engtelecom.swingType.OltG08;
import engtelecom.swingType.OltG16;

/**
 *
 * @author faber222
 */
public class App extends javax.swing.JFrame {

    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) {
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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // FlatDarkLaf.setup();
        // FlatMacDarkLaf.setup();
        // FlatMacLightLaf.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new App().setVisible(true);
        });
    }

    private javax.swing.JDesktopPane jDesktopPane;

    private javax.swing.JMenu jMenu1;

    private javax.swing.JMenu jMenu2;

    private javax.swing.JMenuItem jMenu8820i;

    private javax.swing.JMenuItem jMenuAN5k;

    private javax.swing.JMenuItem jMenuAN6k;

    private javax.swing.JMenuBar jMenuBar1;

    private javax.swing.JMenuItem jMenuG08;

    private javax.swing.JMenuItem jMenuG16;

    private javax.swing.JMenuItem jMenuCutover;

    private javax.swing.JMenuItem jMenuCutover5k;

    private javax.swing.JMenuItem jMenuInfo;

    private ImageIcon infoIcon;
    private ImageIcon infoMainIcon;
    private ImageIcon terminalIcon;
    private ImageIcon olt8820Icon;
    private ImageIcon oltG16Icon;
    private ImageIcon oltG08Icon;
    private ImageIcon oltAN6KIcon;
    private ImageIcon mainIcon;
    private ImageIcon oltCutoverIcon;

    private ImageIcon ipIcon;
    private ImageIcon portIcon;
    private ImageIcon userIcon;
    private ImageIcon passIcon;
    private ImageIcon oltIcon;
    private ImageIcon collectIcon;
    private ImageIcon fileIcon;
    private ImageIcon fileHoverIcon;
    private ImageIcon criarIcon;
    private ImageIcon enviarIcon;
    private ImageIcon sairIcon;
    private ImageIcon previewIcon;

    private String oltAn5k;
    private String oltAn6k;
    private String oltG16;
    private String oltG08;
    private String olt8820;
    private String oltCutover;
    private String oltCutover5k;

    /**
     * Creates new form OltGponMenu
     */
    public App() {
        this.oltAn5k = "AN5000";
        this.oltAn6k = "AN6000";
        this.olt8820 = "8820i";
        this.oltG16 = "G16";
        this.oltG08 = "G08";
        this.oltCutover = "OLT-CUTOVER";
        this.oltCutover5k = "OLT-CUTOVER-5k";
        initComponents();
        this.setIconImage(this.mainIcon.getImage());
    }

    public ImageIcon getIpIcon() {
        return ipIcon;
    }

    public ImageIcon getPortIcon() {
        return portIcon;
    }

    public ImageIcon getUserIcon() {
        return userIcon;
    }

    public ImageIcon getPassIcon() {
        return passIcon;
    }

    public ImageIcon getOltIcon() {
        return oltIcon;
    }

    public ImageIcon getCollectIcon() {
        return collectIcon;
    }

    public ImageIcon getFileIcon() {
        return fileIcon;
    }

    public ImageIcon getFileHoverIcon() {
        return fileHoverIcon;
    }

    public ImageIcon getCriarIcon() {
        return criarIcon;
    }

    public ImageIcon getEnviarIcon() {
        return enviarIcon;
    }

    public ImageIcon getSairIcon() {
        return sairIcon;
    }

    public ImageIcon getPreviewIcon() {
        return previewIcon;
    }

    private void olt() {
        // Carrega os ícones necessários para o diálogo
        final ClassLoader classLoader = App.class.getClassLoader();
        this.olt8820Icon = new ImageIcon(classLoader.getResource("OLT_8820.png"));
        this.oltG16Icon = new ImageIcon(classLoader.getResource("OLT_G08.png"));
        this.oltG08Icon = new ImageIcon(classLoader.getResource("OLT_G08.png"));
        this.oltAN6KIcon = new ImageIcon(classLoader.getResource("OLT_AN6000.png"));
        this.terminalIcon = new ImageIcon(classLoader.getResource("icons/application_xp_terminal.png"));
        this.infoIcon = new ImageIcon(classLoader.getResource("icons/information.png"));
        this.infoMainIcon = new ImageIcon(classLoader.getResource("icons/help.png"));
        this.mainIcon = new ImageIcon(classLoader.getResource("provedor.png"));
        this.oltCutoverIcon = new ImageIcon(classLoader.getResource("script.png"));

        this.ipIcon = new ImageIcon(classLoader.getResource("icons/server.png"));
        this.portIcon = new ImageIcon(classLoader.getResource("icons/sitemap.png"));
        this.userIcon = new ImageIcon(classLoader.getResource("icons/user.png"));
        this.passIcon = new ImageIcon(classLoader.getResource("icons/key.png"));
        this.oltIcon = new ImageIcon(classLoader.getResource("icons/application_osx_terminal.png"));
        this.collectIcon = new ImageIcon(classLoader.getResource("icons/server_link.png"));
        this.fileIcon = new ImageIcon(classLoader.getResource("icons/folder.png"));
        this.fileHoverIcon = new ImageIcon(classLoader.getResource("icons/folder_explore.png"));
        this.criarIcon = new ImageIcon(classLoader.getResource("icons/script_code.png"));
        this.enviarIcon = new ImageIcon(classLoader.getResource("icons/server_connect.png"));
        this.sairIcon = new ImageIcon(classLoader.getResource("icons/cross.png"));
        this.previewIcon = new ImageIcon(classLoader.getResource("icons/eye.png"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        olt();
        
        jDesktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuAN5k = new javax.swing.JMenuItem();
        jMenuAN6k = new javax.swing.JMenuItem();
        jMenuG16 = new javax.swing.JMenuItem();
        jMenuG08 = new javax.swing.JMenuItem();
        jMenu8820i = new javax.swing.JMenuItem();
        jMenuCutover = new javax.swing.JMenuItem();
        jMenuCutover5k = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuInfo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HefForge OLT - Script tools");
        setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        
//        jDesktopPane.addComponentListener(new java.awt.event.ComponentAdapter() {
//            @Override
//            public void componentResized(java.awt.event.ComponentEvent evt) {
//                // Redimensionar os JInternalFrames proporcionalmente ao tamanho do JDesktopPane
//                for (javax.swing.JInternalFrame frame : jDesktopPane.getAllFrames()) {
//                    frame.setSize((int) (jDesktopPane.getWidth() * 0.8), (int) (jDesktopPane.getHeight() * 0.8));
//                    frame.setLocation((jDesktopPane.getWidth() - frame.getWidth()) / 2, 
//                                      (jDesktopPane.getHeight() - frame.getHeight()) / 2);
//                }
//            }
//        });

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
                jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1165, Short.MAX_VALUE));
        jDesktopPaneLayout.setVerticalGroup(
                jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 696, Short.MAX_VALUE));

        jMenu1.setIcon(terminalIcon); // NOI18N
        jMenu1.setText("OLT");

        jMenuAN5k.setIcon(oltAN6KIcon); // NOI18N
        jMenuAN5k.setText(this.oltAn5k);
        jMenuAN5k.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuAN5kActionPerformed(evt);
        });
        jMenu1.add(jMenuAN5k);

        jMenuAN6k.setIcon(oltAN6KIcon); // NOI18N
        jMenuAN6k.setText(this.oltAn6k);
        jMenuAN6k.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuAN6kActionPerformed(evt);
        });
        jMenu1.add(jMenuAN6k);

        jMenuG16.setIcon(oltG16Icon); // NOI18N
        jMenuG16.setText(this.oltG16);
        jMenuG16.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuG16ActionPerformed(evt);
        });
        jMenu1.add(jMenuG16);

        jMenuG08.setIcon(oltG08Icon); // NOI18N
        if (jMenuG08.getIcon().equals(oltG08Icon)) {

        }

        jMenuG08.setText(this.oltG08);
        jMenuG08.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuG08ActionPerformed(evt);
        });
        jMenu1.add(jMenuG08);

        jMenu8820i.setIcon(olt8820Icon); // NOI18N
        jMenu8820i.setText(this.olt8820);
        jMenu8820i.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenu8820iActionPerformed(evt);
        });
        jMenu1.add(jMenu8820i);

        jMenuCutover.setIcon(oltCutoverIcon);
        jMenuCutover.setText(this.oltCutover);
        jMenuCutover.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuCutoverActionPerformed(evt);
        });
        jMenu1.add(jMenuCutover);

        jMenuCutover5k.setIcon(oltCutoverIcon);
        jMenuCutover5k.setText(this.oltCutover5k);
        jMenuCutover5k.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuCutover5kActionPerformed(evt);
        });
        jMenu1.add(jMenuCutover5k);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(infoIcon); // NOI18N
        jMenu2.setText("Sobre");
        jMenu2.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenu2ActionPerformed(evt);
        });
        jMenuInfo.setIcon(
                infoMainIcon); // NOI18N
        jMenuInfo.setText("Info");
        jMenuInfo.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuInfoActionPerformed(evt);
        });
        jMenu2.add(jMenuInfo);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDesktopPane));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDesktopPane));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuG16ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuG16ActionPerformed
        OltG16 telaG16 = new OltG16(this.oltG16);
        jDesktopPane.add(telaG16);
        telaG16.setVisible(true);
    }// GEN-LAST:event_jMenuG16ActionPerformed

    private void jMenuG08ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuG08ActionPerformed
        OltG08 telaG08 = new OltG08(this.oltG08);
        jDesktopPane.add(telaG08);
        telaG08.setVisible(true);
    }// GEN-LAST:event_jMenuG08ActionPerformed

    private void jMenu8820iActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenu8820iActionPerformed
        Olt8820 tela8820 = new Olt8820(this.olt8820);
        jDesktopPane.add(tela8820);
        tela8820.setVisible(true);
    }// GEN-LAST:event_jMenu8820iActionPerformed

    private void jMenuCutoverActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenu8820iActionPerformed
        OltCutover telaCutover = new OltCutover(this);
        jDesktopPane.add(telaCutover);
        telaCutover.setVisible(true);
    }// GEN-LAST:event_jMenu8820iActionPerformed

    private void jMenuCutover5kActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenu8820iActionPerformed
        OltCutover5k telaCutover2 = new OltCutover5k(this);
        jDesktopPane.add(telaCutover2);
        telaCutover2.setVisible(true);
    }// GEN-LAST:event_jMenu8820iActionPerformed

    private void jMenuAN5kActionPerformed(java.awt.event.ActionEvent evt) {
        OltFhtt telaAN5000 = new OltFhtt(this.oltAn5k, this);
        jDesktopPane.add(telaAN5000);
        telaAN5000.setVisible(true);
    }

    private void jMenuAN6kActionPerformed(java.awt.event.ActionEvent evt) {
        OltFhtt telaAN6000 = new OltFhtt(this.oltAn6k, this);
        jDesktopPane.add(telaAN6000);
        telaAN6000.setVisible(true);
    }

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {
        InfoFrame info = new InfoFrame();
        info.setVisible(true);
    }

    private void jMenuInfoActionPerformed(java.awt.event.ActionEvent evt) {
        new InfoFrame().setVisible(true);
    }

    // End of variables declaration//GEN-END:variables
}
