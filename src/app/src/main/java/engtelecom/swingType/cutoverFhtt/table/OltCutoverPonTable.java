/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package engtelecom.swingType.cutoverFhtt.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author faber222
 */
public class OltCutoverPonTable extends javax.swing.JFrame {

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton jButtonOk;

        private javax.swing.JPanel jPanel1;

        private javax.swing.JScrollPane jScrollPane1;

        private javax.swing.JTable jTablePon;

        private OltCutoverPonTableListener listener;

        // End of variables declaration//GEN-END:variables
        /**
         * Creates new form OltCutoverTable
         */
        public OltCutoverPonTable() {
                initComponents();
        }

        public void setListener(OltCutoverPonTableListener listener) {
                this.listener = listener;
        }

        // Método para adicionar novas linhas dinamicamente
        public void adicionarLinha(String slot, String pon) {
                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTablePon.getModel();
                model.addRow(new Object[] { slot, pon, false });
        }

        public void ordenarTabela() {
                DefaultTableModel model = (DefaultTableModel) jTablePon.getModel();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                jTablePon.setRowSorter(sorter);

                // Definir comparadores para colunas numéricas
                sorter.setComparator(0, Comparator.comparingInt(o -> Integer.valueOf(o.toString()))); // SLOT
                sorter.setComparator(1, Comparator.comparingInt(o -> Integer.valueOf(o.toString()))); // PON

                // Definir ordenação: primeiro SLOT (coluna 0) e depois PON (coluna 1)
                List<RowSorter.SortKey> sortKeys = Arrays.asList(
                                new RowSorter.SortKey(0, SortOrder.ASCENDING), // Ordena SLOT em ordem crescente
                                new RowSorter.SortKey(1, SortOrder.ASCENDING) // Ordena PON em ordem crescente
                );

                sorter.setSortKeys(sortKeys);
                sorter.sort(); // Aplica a ordenação
        }

        /**
         * Coleta os dados selecionados na tabela considerando a ordenação do sorter.
         */
        private List<String[]> getPonSelecionada() {
                DefaultTableModel model = (DefaultTableModel) jTablePon.getModel();
                TableRowSorter<?> sorter = (TableRowSorter<?>) jTablePon.getRowSorter(); // Obtém o sorter
                List<String[]> ponSelecionada = new ArrayList<>();
                // onuSelecionada.clear();

                for (int i = 0; i < jTablePon.getRowCount(); i++) {
                        int modelIndex = sorter.convertRowIndexToModel(i); // Converte índice da exibição para o modelo

                        Boolean selecionado = (Boolean) model.getValueAt(modelIndex, 2);
                        if (Boolean.TRUE.equals(selecionado)) {
                                String slotValido = String.valueOf(model.getValueAt(modelIndex, 0));
                                String ponValido = String.valueOf(model.getValueAt(modelIndex, 1));

                                // Adiciona ao ArrayList
                                ponSelecionada.add(new String[] { slotValido, ponValido });
                        }
                }
                return ponSelecionada;
        }

        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTablePon = new javax.swing.JTable();
                jButtonOk = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setMinimumSize(null);
                setResizable(false);

                jTablePon.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {}, // Inicialmente sem dados
                                new String[] {
                                                "SLOT", "PON", ""
                                }) {
                        @SuppressWarnings("rawtypes")
                        Class[] types = new Class[] {
                                        java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class
                        };
                        boolean[] canEdit = new boolean[] {
                                        true, false, true
                        };

                        @SuppressWarnings({ "rawtypes", "unchecked" })
                        @Override
                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        @Override
                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                jTablePon.setToolTipText("Objetos");
                jTablePon.setAutoCreateRowSorter(true);
                jTablePon.getTableHeader().setReorderingAllowed(false);
                jScrollPane1.setViewportView(jTablePon);
                // if (jTablePon.getColumnModel().getColumnCount() > 0) {
                // jTablePon.getColumnModel().getColumn(1).setResizable(false);
                // jTablePon.getColumnModel().getColumn(2).setResizable(false);
                // }

                jButtonOk.setText("OK");
                jButtonOk.setToolTipText("");
                jButtonOk.addActionListener((java.awt.event.ActionEvent evt) -> {
                        jButtonOkActionPerformed();
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 170, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                .addComponent(jScrollPane1,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                168,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jButtonOk,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                158,
                                                                                                                Short.MAX_VALUE))
                                                                                .addContainerGap(
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 193, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addComponent(jScrollPane1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                152,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jButtonOk)
                                                                                .addContainerGap(
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(6, 6, 6)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

        private void jButtonOkActionPerformed() {
                if (listener != null) {
                        List<String[]> selecionados = getPonSelecionada(); // Atualiza os dados selecionados;
                        if (selecionados.isEmpty()) {
                                JOptionPane.showMessageDialog(
                                                null,
                                                "Erro: Selecione alguma PON!", "Campo vazio!",
                                                JOptionPane.ERROR_MESSAGE);
                        } else {
                                listener.onProfileCreatedPonTable(selecionados);
                        }
                }
                this.dispose(); // Fecha o JFrame
        }
}
