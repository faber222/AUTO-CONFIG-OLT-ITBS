/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package engtelecom;

import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import engtelecom.swingType.InfoFrame;
import engtelecom.swingType.anm2Unm.Anm2Unm;
import engtelecom.swingType.cutoverFhtt.Olt5kCutoverTo6k;
import engtelecom.swingType.cutoverItbs.OltCutover;
import engtelecom.swingType.fiberhome.OltFhtt;
import engtelecom.swingType.gcon.OltG08;
import engtelecom.swingType.gcon.OltG16;
import engtelecom.swingType.zhone.Olt8820;

/**
 *
 * @author faber222
 */
public class App extends javax.swing.JFrame {
    private static boolean darkMode = true; // Tema padrão: Dark

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        final Preferences prefs = Preferences.userNodeForPackage(App.class);
        darkMode = prefs.getBoolean("DARK_MODE", true); // Default: Dark
        // Aplica o tema inicial
        atualizarTemaGlobal(darkMode);
        java.awt.EventQueue.invokeLater(() -> {
            new App().setVisible(true);
        });
    }

    public static void atualizarTemaGlobal(final boolean isDarkMode) {
        try {
            darkMode = isDarkMode;
            final Preferences prefs = Preferences.userNodeForPackage(App.class);
            prefs.putBoolean("DARK_MODE", darkMode);
            if (isDarkMode) {
                UIManager.setLookAndFeel(new FlatMacDarkLaf());
            } else {
                UIManager.setLookAndFeel(new FlatMacLightLaf());

            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    // Getter para o tema atual
    public static boolean isDarkMode() {
        return darkMode;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;

    private javax.swing.JMenuItem jMenuItem2;

    private javax.swing.JMenuItem jMenuItem3;

    private javax.swing.JMenuItem jMenu8820i;

    private javax.swing.JMenuItem jMenuAN5k;

    private javax.swing.JMenuItem jMenuAN6k;

    private javax.swing.JMenuItem jMenuG08;

    private javax.swing.JMenuItem jMenuG16;

    private javax.swing.JMenuItem jMenuCutover;
    
    private javax.swing.JMenuItem jMenuTema;

    private javax.swing.JMenuItem jMenuCutover5kTo6k;
    private javax.swing.JMenuItem jMenuAnm2Unm;
    private ImageIcon infoIcon;
    private ImageIcon terminalIcon;
    private ImageIcon terminalOsxIcon;

    private ImageIcon databaseRefresh;
    private ImageIcon databaseDisconnect;
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
    private ImageIcon temaIcon;
    private final String oltAn5k;
    private final String oltAn6k;
    private final String oltG16;
    private final String oltG08;
    private final String olt8820;
    
    private final String oltCutover;

    // private final String oltCutover5k;
    private final String oltCutover5kTo6k;

    private final String migracaoAnm2Unm;

    // End of variables declaration//GEN-END:variables
    /**
     * Creates new form AppNew
     */
    public App() {
        this.oltAn5k = "AN5000";
        this.oltAn6k = "AN6000";
        this.olt8820 = "8820i";
        this.oltG16 = "G16";
        this.oltG08 = "G08";
        this.oltCutover = "OLT-CUTOVER";
        // this.oltCutover5k = "OLT-CUTOVER-5k";
        this.oltCutover5kTo6k = "OLT-CUTOVER-5k-TO-6K";
        this.migracaoAnm2Unm = "ANM-2-UNM";
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
        this.terminalOsxIcon = new ImageIcon(ClassLoader.getSystemResource("icons/application_osx_terminal.png"));
        this.databaseRefresh = new ImageIcon(ClassLoader.getSystemResource("icons/database_refresh.png"));
        this.databaseDisconnect = new ImageIcon(ClassLoader.getSystemResource("icons/disconnect.png"));
        this.infoIcon = new ImageIcon(classLoader.getResource("icons/information.png"));
        this.mainIcon = new ImageIcon(classLoader.getResource("provedor.png"));
        this.oltCutoverIcon = new ImageIcon(classLoader.getResource("script.png"));
        this.temaIcon = new ImageIcon(classLoader.getResource("icons/contrast_high.png"));

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
    @SuppressWarnings("Convert2Lambda")
    private void initComponents() {

        olt();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuAN5k = new javax.swing.JMenuItem();
        jMenuAN6k = new javax.swing.JMenuItem();
        jMenuG16 = new javax.swing.JMenuItem();
        jMenuG08 = new javax.swing.JMenuItem();
        jMenu8820i = new javax.swing.JMenuItem();
        jMenuCutover = new javax.swing.JMenuItem();
        jMenuTema = new javax.swing.JMenuItem();
        jMenuCutover5kTo6k = new javax.swing.JMenuItem();
        jMenuAnm2Unm = new javax.swing.JMenuItem();

        jMenuItem1 = new javax.swing.JMenuItem();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HefForge OLT - Script tools");
        setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N

        final javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
                jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1165, Short.MAX_VALUE));
        jDesktopPane1Layout.setVerticalGroup(
                jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 700, Short.MAX_VALUE));

        jMenu1.setIcon(terminalOsxIcon); // NOI18N
        jMenu1.setText("Intelbras");
        jMenuBar1.add(jMenu1);

        jMenuG16.setIcon(oltG16Icon); // NOI18N
        jMenuG16.setText(this.oltG16);
        jMenuG16.addActionListener((final java.awt.event.ActionEvent evt) -> {
            jMenuG16ActionPerformed();
        });

        jMenu1.add(jMenuG16);

        jMenuG08.setIcon(oltG08Icon); // NOI18N
        if (jMenuG08.getIcon().equals(oltG08Icon)) {

        }

        jMenuG08.setText(this.oltG08);
        jMenuG08.addActionListener((final java.awt.event.ActionEvent evt) -> {
            jMenuG08ActionPerformed();
        });
        jMenu1.add(jMenuG08);

        jMenu8820i.setIcon(olt8820Icon); // NOI18N
        jMenu8820i.setText(this.olt8820);
        jMenu8820i.addActionListener((final java.awt.event.ActionEvent evt) -> {
            jMenu8820iActionPerformed();
        });
        jMenu1.add(jMenu8820i);

        jMenu3.setIcon(terminalIcon); // NOI18N
        jMenu3.setText("Fiberhome");
        jMenuBar1.add(jMenu3);

        jMenuAN5k.setIcon(oltAN6KIcon); // NOI18N
        jMenuAN5k.setText(this.oltAn5k);
        jMenuAN5k.addActionListener((final java.awt.event.ActionEvent evt) -> {
            jMenuAN5kActionPerformed();
        });

        jMenu3.add(jMenuAN5k);

        jMenuAN6k.setIcon(oltAN6KIcon); // NOI18N
        jMenuAN6k.setText(this.oltAn6k);
        jMenuAN6k.addActionListener((final java.awt.event.ActionEvent evt) -> {
            jMenuAN6kActionPerformed();
        });

        jMenu3.add(jMenuAN6k);

        jMenu4.setIcon(databaseRefresh); // NOI18N
        jMenu4.setText("Cutover");
        jMenuBar1.add(jMenu4);

        jMenuCutover.setIcon(oltCutoverIcon);
        jMenuCutover.setText(this.oltCutover);
        jMenuCutover.addActionListener((final java.awt.event.ActionEvent evt) -> {
            jMenuCutoverActionPerformed();
        });

        jMenu4.add(jMenuCutover);

        // jMenu4.add();

        jMenuCutover5kTo6k.setIcon(oltCutoverIcon);
        jMenuCutover5kTo6k.setText(this.oltCutover5kTo6k);
        jMenuCutover5kTo6k.addActionListener((final java.awt.event.ActionEvent evt) -> {
            jMenuCutover5kTo6kActionPerformed();
        });

        jMenu4.add(jMenuCutover5kTo6k);
        
        jMenuAnm2Unm.setIcon(oltCutoverIcon);
        jMenuAnm2Unm.setText(this.migracaoAnm2Unm);
        jMenuAnm2Unm.addActionListener((final java.awt.event.ActionEvent evt) -> {
            jMenuAnm2UnmActionPerformed();
        });

        jMenu4.add(jMenuAnm2Unm);

        jMenu5.setIcon(infoIcon); // NOI18N
        jMenu5.setText("Sobre");

        jMenuItem3.setIcon(infoIcon); // NOI18N
        jMenuItem3.setText("Sobre");
        jMenuItem3.addActionListener((final java.awt.event.ActionEvent evt) -> {
            jMenuInfoActionPerformed();
        });
        jMenu5.add(jMenuItem3);

        jMenuTema.setIcon(temaIcon);
        jMenuTema.setText("Tema");
        jMenuTema.addActionListener((final java.awt.event.ActionEvent evt) -> {
            jMenuTemaActionPerformed();
        });
        jMenu5.add(jMenuTema);
        
        jMenuItem2.setIcon(databaseDisconnect); // NOI18N
        jMenuItem2.setText("Sair");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed();
            }
        });
        jMenu5.add(jMenuItem2);


        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDesktopPane1));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDesktopPane1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuG16ActionPerformed() {// GEN-FIRST:event_jMenuG16ActionPerformed
        final OltG16 telaG16 = new OltG16(this.oltG16);
        jDesktopPane1.add(telaG16);
        telaG16.setVisible(true);
    }// GEN-LAST:event_jMenuG16ActionPerformed

    private void jMenuG08ActionPerformed() {// GEN-FIRST:event_jMenuG08ActionPerformed
        final OltG08 telaG08 = new OltG08(this.oltG08);
        jDesktopPane1.add(telaG08);
        telaG08.setVisible(true);
    }// GEN-LAST:event_jMenuG08ActionPerformed

    private void jMenu8820iActionPerformed() {// GEN-FIRST:event_jMenu8820iActionPerformed
        final Olt8820 tela8820 = new Olt8820(this.olt8820);
        jDesktopPane1.add(tela8820);
        tela8820.setVisible(true);
    }// GEN-LAST:event_jMenu8820iActionPerformed

    private void jMenuCutoverActionPerformed() {// GEN-FIRST:event_jMenu8820iActionPerformed
        final OltCutover telaCutover = new OltCutover(this);
        jDesktopPane1.add(telaCutover);
        telaCutover.setVisible(true);
    }// GEN-LAST:event_jMenu8820iActionPerformed
    
    private void jMenuTemaActionPerformed() {//
        App.atualizarTemaGlobal(!App.isDarkMode());
        atualizarUI();
    }// GEN-LAST:event_jMenu8820iActionPerformed

    private void jMenuCutover5kTo6kActionPerformed() {// GEN-FIRST:event_jMenu8820iActionPerformed
        final Olt5kCutoverTo6k olt5kCutoverTo6k = new Olt5kCutoverTo6k();
        jDesktopPane1.add(olt5kCutoverTo6k);
        olt5kCutoverTo6k.setVisible(true);
    }// GEN-LAST:event_jMenu8820iActionPerformed

    private void jMenuAnm2UnmActionPerformed() {// GEN-FIRST:event_jMenu8820iActionPerformed
        final Anm2Unm migracaoAnm2Unm = new Anm2Unm();
        jDesktopPane1.add(migracaoAnm2Unm);
        migracaoAnm2Unm.setVisible(true);
    }// GEN-LAST:event_jMenu8820iActionPerformed

    private void jMenuAN5kActionPerformed() {
        final OltFhtt telaAN5000 = new OltFhtt(this.oltAn5k, this);
        jDesktopPane1.add(telaAN5000);
        telaAN5000.setVisible(true);
    }

    private void jMenuAN6kActionPerformed() {
        final OltFhtt telaAN6000 = new OltFhtt(this.oltAn6k, this);
        jDesktopPane1.add(telaAN6000);
        telaAN6000.setVisible(true);
    }

    private void jMenuInfoActionPerformed() {
        new InfoFrame().setVisible(true);
    }

    private void jMenuItem2ActionPerformed() {// GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }// GEN-LAST:event_jMenuItem2ActionPerformed

    private void atualizarUI() {
        SwingUtilities.updateComponentTreeUI(this); // Atualiza o JFrame principal
    }
}
