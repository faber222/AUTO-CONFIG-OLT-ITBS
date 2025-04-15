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
public class OltCutoverOnuTable extends javax.swing.JFrame {
        private javax.swing.JButton jButtonOk;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable jTableOnu;

        // private final List<String[]> onuSelecionada;
        private OltCutoverOnuTableListener listener;

        /**
         * Construtor sem preencher dados imediatamente
         */
        public OltCutoverOnuTable() {
                // this.onuSelecionada = new ArrayList<>();
                initComponents();
        }

        public void setListener(OltCutoverOnuTableListener listener) {
                this.listener = listener;
        }

        // Método para adicionar novas linhas dinamicamente
        public void adicionarLinha(String slot, String pon, String onu) {
                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableOnu.getModel();
                model.addRow(new Object[] { slot, pon, onu, false });
        }

        public void ordenarTabela() {
                DefaultTableModel model = (DefaultTableModel) jTableOnu.getModel();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                jTableOnu.setRowSorter(sorter);

                // Definir comparadores para colunas numéricas
                sorter.setComparator(0, Comparator.comparingInt(o -> Integer.valueOf(o.toString()))); // SLOT
                sorter.setComparator(1, Comparator.comparingInt(o -> Integer.valueOf(o.toString()))); // PON
                sorter.setComparator(2, Comparator.comparingInt(o -> Integer.valueOf(o.toString()))); // ONU

                // Definir ordenação: primeiro SLOT (coluna 0), depois PON (coluna 1), e depois
                // ONU (coluna 2)
                List<RowSorter.SortKey> sortKeys = Arrays.asList(
                                new RowSorter.SortKey(0, SortOrder.ASCENDING), // Ordena SLOT em ordem crescente
                                new RowSorter.SortKey(1, SortOrder.ASCENDING), // Ordena PON em ordem crescente
                                new RowSorter.SortKey(2, SortOrder.ASCENDING) // Ordena ONU em ordem crescente
                );

                sorter.setSortKeys(sortKeys);
                sorter.sort(); // Aplica a ordenação
        }

        /**
         * Coleta os dados selecionados na tabela considerando a ordenação do sorter.
         */
        private List<String[]> getOnuSelecionada() {
                DefaultTableModel model = (DefaultTableModel) jTableOnu.getModel();
                TableRowSorter<?> sorter = (TableRowSorter<?>) jTableOnu.getRowSorter(); // Obtém o sorter
                List<String[]> onuSelecionada = new ArrayList<>();
                // onuSelecionada.clear();

                for (int i = 0; i < jTableOnu.getRowCount(); i++) {
                        int modelIndex = sorter.convertRowIndexToModel(i); // Converte índice da exibição para o modelo

                        Boolean selecionado = (Boolean) model.getValueAt(modelIndex, 3);
                        if (Boolean.TRUE.equals(selecionado)) {
                                String slotValido = String.valueOf(model.getValueAt(modelIndex, 0));
                                String ponValido = String.valueOf(model.getValueAt(modelIndex, 1));
                                String onuValido = String.valueOf(model.getValueAt(modelIndex, 2));

                                // Adiciona ao ArrayList
                                onuSelecionada.add(new String[] { slotValido, ponValido, onuValido });
                        }
                }
                return onuSelecionada;
        }

        private void initComponents() {
                jPanel1 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTableOnu = new javax.swing.JTable();
                jButtonOk = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setResizable(false);

                jTableOnu.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {}, // Inicialmente sem dados
                                new String[] { "SLOT", "PON", "ONU", "" }) {
                        Class<?>[] types = new Class[] {
                                        java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class,
                                        java.lang.Boolean.class
                        };
                        boolean[] canEdit = new boolean[] {
                                        false, false, false, true
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

                jTableOnu.getTableHeader().setReorderingAllowed(false);
                jScrollPane1.setViewportView(jTableOnu);
                jTableOnu.setAutoCreateRowSorter(true);

                jButtonOk.setText("OK");
                jButtonOk.addActionListener((java.awt.event.ActionEvent evt) -> {
                        jButtonOkActionPerformed();
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380,
                                                                Short.MAX_VALUE)
                                                .addComponent(jButtonOk, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jButtonOk)
                                                                .addGap(0, 10, Short.MAX_VALUE)));

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
        }

        private void jButtonOkActionPerformed() {
                if (listener != null) {
                        List<String[]> selecionados = getOnuSelecionada(); // Atualiza os dados selecionados;
                        if (selecionados.isEmpty()) {
                                JOptionPane.showMessageDialog(
                                                null,
                                                "Erro: Selecione alguma CPE!", "Campo vazio!",
                                                JOptionPane.ERROR_MESSAGE);
                        } else {
                                listener.onProfileCreatedOnuTable(selecionados);
                        }
                }
                this.dispose(); // Fecha o JFrame
        }

}
