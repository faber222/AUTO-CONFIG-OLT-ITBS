/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package engtelecom.swingType;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import javax.swing.ImageIcon;

import engtelecom.product.OltGpon;

/**
 *
 * @author faber222
 */
public class OltPreview extends javax.swing.JFrame {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonCopy;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaPreview;
    // End of variables declaration//GEN-END:variables
    private ImageIcon oltIcon;
    private String oltName;

    /**
     * Creates new form OltPreview
     */
    public OltPreview(String oltName) {
        final ClassLoader classLoader = OltPreview.class.getClassLoader();
        this.oltIcon = new ImageIcon(classLoader.getResource("AN6000_15.png"));
        this.oltName = oltName;
        initComponents();
    }

    public javax.swing.JTextArea getjTextAreaPreview() {
        return jTextAreaPreview;
    }

    public void setjTextAreaPreview(final javax.swing.JTextArea jTextAreaPreview) {
        this.jTextAreaPreview.setText(jTextAreaPreview.getText());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPreview = new javax.swing.JTextArea();
        jButtonClose = new javax.swing.JButton();
        jButtonCopy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Preview" + this.oltName);
        setMinimumSize(new java.awt.Dimension(838, 480));
        this.setIconImage(this.oltIcon.getImage());

        jTextAreaPreview.setEditable(false);
        jTextAreaPreview.setColumns(20);
        jTextAreaPreview.setRows(5);
        jScrollPane1.setViewportView(jTextAreaPreview);

        jButtonClose.setText("Fechar");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jButtonCopy.setText("Copiar tudo");
        jButtonCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCopyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 826,
                                                Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel1Layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(jButtonCopy)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButtonClose)))
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonClose)
                                        .addComponent(jButtonCopy))
                                .addContainerGap(8, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCopyActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCopyActionPerformed
        // Obtém o texto do JTextArea
        final String text = jTextAreaPreview.getText();

        if (text != null && !text.isEmpty()) {
            // Cria um objeto StringSelection para a área de transferência
            final StringSelection selection = new StringSelection(text);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

            // Exibe uma mensagem de confirmação
            System.out.println("Texto copiado para a área de transferência!");
        } else {
            System.out.println("Texto vazio!");
        }
    }// GEN-LAST:event_jButtonCopyActionPerformed

    private void jButtonCloseActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCloseActionPerformed
        this.dispose();
    }// GEN-LAST:event_jButtonCloseActionPerformed
}