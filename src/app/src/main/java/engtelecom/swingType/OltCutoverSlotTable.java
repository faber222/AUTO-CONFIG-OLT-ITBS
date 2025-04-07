/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package engtelecom.swingType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author faber222
 */
public class OltCutoverSlotTable extends javax.swing.JFrame {

        private javax.swing.JButton jButtonOk;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable jTableSlot;

        private OltCutoverSlotTableListener listener;

        public OltCutoverSlotTable() {
                initComponents();
        }

        public void setListener(OltCutoverSlotTableListener listener) {
                this.listener = listener;
        }

        // Método para adicionar novas linhas dinamicamente
        public void adicionarLinha(String slot) {
                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableSlot.getModel();
                model.addRow(new Object[] { slot, false });
        }

        /**
         * Coleta os dados selecionados na tabela considerando a ordenação do sorter.
         */
        private List<String[]> getSlotSelecionado() {
                DefaultTableModel model = (DefaultTableModel) jTableSlot.getModel();
                TableRowSorter<?> sorter = (TableRowSorter<?>) jTableSlot.getRowSorter(); // Obtém o sorter
                List<String[]> slotSelecionado = new ArrayList<>();
                // slotSelecionado.clear();

                for (int i = 0; i < jTableSlot.getRowCount(); i++) {
                        int modelIndex = sorter.convertRowIndexToModel(i); // Converte índice da exibição para o modelo

                        Boolean selecionado = (Boolean) model.getValueAt(modelIndex, 1);
                        if (Boolean.TRUE.equals(selecionado)) {
                                String slotValido = String.valueOf(model.getValueAt(modelIndex, 0));

                                // Adiciona ao ArrayList
                                slotSelecionado.add(new String[] { slotValido });
                        }
                }
                return slotSelecionado;
        }

        public void ordenarTabela() {
                DefaultTableModel model = (DefaultTableModel) jTableSlot.getModel();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                jTableSlot.setRowSorter(sorter);

                // Definir comparadores para colunas numéricas
                sorter.setComparator(0, Comparator.comparingInt(o -> Integer.valueOf(o.toString()))); // SLOT

                // Definir ordenação: primeiro SLOT (coluna 0), depois PON (coluna 1), e depois
                // ONU (coluna 2)
                List<RowSorter.SortKey> sortKeys = Arrays.asList(
                                new RowSorter.SortKey(0, SortOrder.ASCENDING) // Ordena SLOT em ordem crescente
                );

                sorter.setSortKeys(sortKeys);
                sorter.sort(); // Aplica a ordenação
        }

        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTableSlot = new javax.swing.JTable();
                jButtonOk = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setMaximumSize(null);
                setMinimumSize(null);
                setResizable(false);

                jTableSlot.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {}, // Inicialmente sem dados
                                new String[] {
                                                "SLOT", ""
                                }) {
                        Class[] types = new Class[] {
                                        java.lang.Object.class, java.lang.Boolean.class
                        };
                        boolean[] canEdit = new boolean[] {
                                        false, true
                        };

                        @Override
                        public Class<?> getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        @Override
                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                jTableSlot.setToolTipText("Objetos");
                jScrollPane1.setViewportView(jTableSlot);
                jTableSlot.getTableHeader().setReorderingAllowed(false);
                jTableSlot.setAutoCreateRowSorter(true);

                jButtonOk.setText("OK");
                jButtonOk.setToolTipText("");
                jButtonOk.addActionListener((java.awt.event.ActionEvent evt) -> {
                        jButtonOkActionPerformed();
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 115, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                false)
                                                                                                .addComponent(jButtonOk,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jScrollPane1,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                140,
                                                                                                                Short.MAX_VALUE))
                                                                                .addContainerGap(6, Short.MAX_VALUE))));
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
        }

        private void jButtonOkActionPerformed() {
                if (listener != null) {
                        List<String[]> selecionados = getSlotSelecionado(); // Atualiza os dados selecionados;
                        listener.onProfileCreatedSlotTable(selecionados);
                }
                this.dispose(); // Fecha o JFrame
        }
}
