/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package engtelecom.swingType.cutoverFhtt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import engtelecom.access.write.fhtt.TelnetFhtt;
import engtelecom.analytics.DataAnaliser5k;
import engtelecom.config.ConfigCutoverGenerator5k;
import engtelecom.swingType.OltPreview;
import engtelecom.swingType.cutoverFhtt.destino.Olt5kCutoverDestinoAcesso;
import engtelecom.swingType.cutoverFhtt.destino.Olt5kCutoverDestinoAcessoListener;
import engtelecom.swingType.cutoverFhtt.destino.OltCutoverFormDestino;
import engtelecom.swingType.cutoverFhtt.destino.OltCutoverFormDestinoListener;
import engtelecom.swingType.cutoverFhtt.origem.Olt5kCutoverOrigemAcesso;
import engtelecom.swingType.cutoverFhtt.origem.Olt5kCutoverOrigemAcessoListener;
import engtelecom.swingType.cutoverFhtt.table.OltCutoverOnuTable;
import engtelecom.swingType.cutoverFhtt.table.OltCutoverOnuTableListener;
import engtelecom.swingType.cutoverFhtt.table.OltCutoverPonTable;
import engtelecom.swingType.cutoverFhtt.table.OltCutoverPonTableListener;
import engtelecom.swingType.cutoverFhtt.table.OltCutoverSlotTable;
import engtelecom.swingType.cutoverFhtt.table.OltCutoverSlotTableListener;

/**
 *
 * @author faber222
 */
public class Olt5kCutoverTo6k extends javax.swing.JInternalFrame
                implements OltCutoverOnuTableListener, OltCutoverPonTableListener, OltCutoverSlotTableListener,
                Olt5kCutoverOrigemAcessoListener, Olt5kCutoverDestinoAcessoListener, OltCutoverFormDestinoListener {

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.ButtonGroup buttonGroup1;

        private javax.swing.JButton jButtonColetar;
        private javax.swing.JButton jButtonPreview;

        private javax.swing.JButton jButtonCriar;

        private javax.swing.JButton jButtonDadosImportOltDestino;

        private javax.swing.JButton jButtonDadosImportOltOrigem;

        private javax.swing.JButton jButtonDadosOltDestino;

        private javax.swing.JButton jButtonDadosOltOrigem;

        private javax.swing.JButton jButtonEnviar;

        private javax.swing.JButton jButtonFileChooser;

        private javax.swing.JButton jButtonSair;

        private javax.swing.JLabel jLabel1;

        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JRadioButton jRadioButtonOnu;
        private javax.swing.JRadioButton jRadioButtonPon;
        private javax.swing.JRadioButton jRadioButtonSlot;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane3;
        private javax.swing.JTextPane jTextPaneDadosOltDestino;
        private javax.swing.JTextPane jTextPaneDadosOltOrigem;
        private javax.swing.JTextArea jTextAreaPreviewCode;

        private OltCutoverOnuTable oltCutoverOnuTable;
        private OltCutoverPonTable oltCutoverPonTable;
        private OltCutoverSlotTable oltCutoverSlotTable;
        private final Olt5kCutoverOrigemAcesso olt5kCutoverOrigemAcesso;
        private final Olt5kCutoverDestinoAcesso olt5kCutoverDestinoAcesso;
        private OltCutoverFormDestino oltCutoverFormDestino;

        private List<String[]> onuOrigemSelecionadaOnuTable;
        private List<String[]> ponOrigemSelecionadaPonTable;
        private List<String[]> slotOrigemSelecionadaSlotTable;
        private List<String[]> uplinkDestinoSelecionado;
        private List<String[]> gponDestinoSelecionado;

        private DataAnaliser5k dataAnaliser5k;

        private OltPreview preview;
        private boolean fileChooserIsSelected;
        private String filePath;
        private boolean scriptCriado;
        private boolean origemSelecionada;
        private boolean destinoSelecionado;

        private String ipOltOrigem;
        private String userOltOrigem;
        private String portOltOrigem;
        private String passOltOrigem;
        private boolean isTelnetOltOrigem;
        private boolean dadosOrigemPreenchidos;
        private boolean dadosDestinoPreenchidos;

        private boolean isSlotSelect;
        private boolean isPonSelect;
        private boolean isOnuSelect;

        private String ipOltDestino;
        private String userOltDestino;
        private String portOltDestino;
        private String passOltDestino;

        private int slotValue;
        private int ponValue;

        /**
         * Creates new form Olt5kCutover
         */
        public Olt5kCutoverTo6k() {
                initComponents();

                this.olt5kCutoverOrigemAcesso = new Olt5kCutoverOrigemAcesso();
                this.olt5kCutoverDestinoAcesso = new Olt5kCutoverDestinoAcesso();

                this.dadosOrigemPreenchidos = false;
                this.dadosDestinoPreenchidos = false;
                this.fileChooserIsSelected = false;
                this.filePath = new String();
                this.scriptCriado = false;
                this.origemSelecionada = false;
                this.destinoSelecionado = false;
                this.isSlotSelect = false;
                this.isPonSelect = false;
                this.isOnuSelect = false;
        }

        public boolean isSlotSelect() {
                return isSlotSelect;
        }

        public boolean isPonSelect() {
                return isPonSelect;
        }

        public boolean isOnuSelect() {
                return isOnuSelect;
        }

        /**
         * Retorna uma lista de string contendo a info do slot e porta uplink de destino
         * 
         * [0] - slot
         * 
         * [1] - porta
         * 
         * @return List<String[]>
         */
        public List<String[]> getUplinkDestinoSelecionado() {
                return uplinkDestinoSelecionado;
        }

        /**
         * Retorna uma lista de string contendo a info do slot ou pon serviço de
         * destino.
         * 
         * [0] - slot
         * 
         * [1] - pon
         * 
         * @return List<String[]> array size == 1 || array size == 2
         */
        public List<String[]> getGponDestinoSelecionado() {
                return gponDestinoSelecionado;
        }

        /**
         * Retorna uma lista de string contendo as onus selecionadas para serem migradas
         * 
         * [0] - slot
         * 
         * [1] - pon
         * 
         * [2] - onu
         * 
         * @return List<String[]>
         */
        public List<String[]> getOnuOrigemSelecionadaOnuTable() {
                return onuOrigemSelecionadaOnuTable;
        }

        /**
         * Retorna uma lista de string contendo as pons selecionadas para serem migradas
         * 
         * [0] - slot
         * 
         * [1] - pon
         * 
         * @return List<String[]>
         */
        public List<String[]> getPonOrigemSelecionadaPonTable() {
                return ponOrigemSelecionadaPonTable;
        }

        /**
         * Retorna uma lista de string contendo os slots selecionados para serem
         * migrados
         * 
         * [0] - slot
         * 
         * @return List<String[]>
         */
        public List<String[]> getSlotOrigemSelecionadaSlotTable() {
                return slotOrigemSelecionadaSlotTable;
        }

        /**
         * Método interface, herdado da tabela de onu, usado para selecionar as onus de
         * origem.
         * 
         */
        @Override
        public void onProfileCreatedOnuTable(final List<String[]> onuSelecionada) {

                final String slotBase = onuSelecionada.get(0)[0];
                for (final String[] onu : onuSelecionada) {
                        if (!onu[0].equals(slotBase)) {
                                JOptionPane.showMessageDialog(
                                                null,
                                                "Erro: Todas as ONUs selecionadas devem estar no mesmo SLOT.",
                                                "SLOT Diferente",
                                                JOptionPane.ERROR_MESSAGE);
                                jTextPaneDadosOltOrigem.setText("");
                                this.isOnuSelect = false;
                                return;
                        }
                }

                this.onuOrigemSelecionadaOnuTable = onuSelecionada;

                // Debug
                System.out.println("ONU Selecionada na classe principal:");
                for (final String[] onu : this.onuOrigemSelecionadaOnuTable) {
                        System.out.println(Arrays.toString(onu));
                }

                // Conta PONs únicas
                final Set<String> ponsUnicas = new HashSet<>();
                for (final String[] onu : this.onuOrigemSelecionadaOnuTable) {
                        if (onu.length > 1) {
                                ponsUnicas.add(onu[1]); // Índice 1 = valor da PON
                        }
                }
                this.ponValue = ponsUnicas.size();

                // Resumo formatado
                final StringBuilder resumo = new StringBuilder();
                for (final String[] onu : this.onuOrigemSelecionadaOnuTable) {
                        resumo.append(onu[0]).append("-").append(onu[1]).append("-").append(onu[2]).append(", ");
                }

                if (resumo.length() > 0) {
                        resumo.setLength(resumo.length() - 2); // Remove vírgula e espaço final
                }

                final int maxLength = 66;
                String textoFinal = resumo.toString();
                if (textoFinal.length() > maxLength) {
                        textoFinal = textoFinal.substring(0, maxLength - 3) + "...";
                }

                jTextPaneDadosOltOrigem.setText(textoFinal);
                this.isOnuSelect = true;
        }

        /**
         * Método interface, herdado da tabela de pon, usado para selecionar as pons de
         * origem.
         */
        @Override
        public void onProfileCreatedPonTable(final List<String[]> ponSelecionada) {
                final String slotBase = ponSelecionada.get(0)[0];
                for (final String[] pon : ponSelecionada) {
                        if (!pon[0].equals(slotBase)) {
                                JOptionPane.showMessageDialog(
                                                null,
                                                "Erro: Todas as PONs selecionadas devem estar no mesmo SLOT.",
                                                "SLOT Diferente",
                                                JOptionPane.ERROR_MESSAGE);
                                jTextPaneDadosOltOrigem.setText("");
                                this.isPonSelect = false;
                                return;
                        }
                }

                this.ponOrigemSelecionadaPonTable = ponSelecionada;

                // Debug
                System.out.println("PON Selecionada na classe principal:");
                for (final String[] pon : this.ponOrigemSelecionadaPonTable) {
                        System.out.println(Arrays.toString(pon));
                }

                // Conta PONs únicas
                final Set<String> ponsUnicas = new HashSet<>();
                for (final String[] pon : this.ponOrigemSelecionadaPonTable) {
                        if (pon.length > 1) {
                                ponsUnicas.add(pon[1]); // Índice 1 = valor da PON
                        }
                }

                this.ponValue = ponsUnicas.size();

                final StringBuilder resumo = new StringBuilder();
                for (final String[] pon : this.ponOrigemSelecionadaPonTable) {
                        resumo.append(pon[0]).append("-").append(pon[1]).append(", ");
                }

                if (resumo.length() > 0) {
                        resumo.setLength(resumo.length() - 2);
                }

                final int maxLength = 66;
                String textoFinal = resumo.toString();
                if (textoFinal.length() > maxLength) {
                        textoFinal = textoFinal.substring(0, maxLength - 3) + "...";
                }

                jTextPaneDadosOltOrigem.setText(textoFinal);
                this.isPonSelect = true;
        }

        /**
         * Método interface, herdado da tabela de slot, usado para selecionar os slots
         * de origem.
         */
        @Override
        public void onProfileCreatedSlotTable(final List<String[]> slotSelecionada) {
                this.slotOrigemSelecionadaSlotTable = slotSelecionada; // Copia os dados corretamente

                // Debug: imprimir para garantir que os dados foram armazenados corretamente
                System.out.println("SLOT Selecionado na classe principal:");
                for (final String[] slot : this.slotOrigemSelecionadaSlotTable) {
                        System.out.println(Arrays.toString(slot));
                }

                // Conta SLOTs únicos
                final Set<String> slotsUnicos = new HashSet<>();
                for (final String[] slot : this.slotOrigemSelecionadaSlotTable) {
                        if (slot.length > 0) {
                                slotsUnicos.add(slot[0]); // Índice 0 = valor do SLOT
                        }
                }

                this.slotValue = slotsUnicos.size();

                // Cria um resumo em uma única linha
                final StringBuilder resumo = new StringBuilder();
                for (final String[] slot : this.slotOrigemSelecionadaSlotTable) {
                        resumo.append(slot[0]).append(", ");
                }

                if (resumo.length() > 0) {
                        resumo.setLength(resumo.length() - 2); // Remove vírgula e espaço final
                }

                final int maxLength = 66;
                String textoFinal = resumo.toString();
                if (textoFinal.length() > maxLength) {
                        textoFinal = textoFinal.substring(0, maxLength - 3) + "...";
                }

                jTextPaneDadosOltOrigem.setText(textoFinal);
                this.isSlotSelect = true;
        }

        /**
         * Método herdado da interface que faz a coleta dos dados da olt de origem para
         * coleta via ssh/telnet
         */
        @Override
        public void onProfileDadosOrigemCreated(final String ip, final String user, final String passwd,
                        final String port, final boolean isTelnet) {
                System.out.println("Ip: " + ip);
                System.out.println("Usuário: " + user);
                System.out.println("Senha: " + passwd);
                System.out.println("Porta: " + port);
                if (isTelnet) {
                        System.out.println("Acesso por telnet");
                } else {
                        System.out.println("Acesso por ssh");
                }

                this.ipOltOrigem = ip;
                this.userOltOrigem = user;
                this.passOltOrigem = passwd;
                this.portOltOrigem = port;
                this.isTelnetOltOrigem = isTelnet;
                dadosOrigemPreenchidos = true;
        }

        /**
         * Método herdado da interface que faz a coleta dos dados da olt de destino para
         * envio de dados via telnet
         */
        @Override
        public void onProfileDadosDestinoCreated(final String ip, final String user, final String passwd,
                        final String port) {
                System.out.println("Ip: " + ip);
                System.out.println("Usuário: " + user);
                System.out.println("Senha: " + passwd);
                System.out.println("Porta: " + port);

                this.ipOltDestino = ip;
                this.userOltDestino = user;
                this.passOltDestino = passwd;
                this.portOltDestino = port;
                dadosDestinoPreenchidos = true;
        }

        /**
         * Método herdado da interface que faz a coleta dos nodos UPLINK e SERVIÇO,
         * importante para a seleção correta para a migração dos dados.
         * 
         */
        @Override
        public void onProfileFormDestinoCreated(final ArrayList<String[]> oltNodos,
                        final ArrayList<String[]> uplinkNodos, int slotValue, int ponValue) {
                // Determina se os dados são de PONs (2 campos) ou SLOTs (1 campo)
                boolean isPon = oltNodos.stream().allMatch(dados -> dados.length == 2);
                boolean isSlot = oltNodos.stream().allMatch(dados -> dados.length == 1);

                if (!isPon && !isSlot) {
                        JOptionPane.showMessageDialog(
                                        null,
                                        "Erro: Os dados da OLT estão misturados ou mal formatados.",
                                        "Validação de destino",
                                        JOptionPane.ERROR_MESSAGE);
                        return;
                }

                if (isPon && oltNodos.size() != ponValue) {
                        JOptionPane.showMessageDialog(
                                        null,
                                        "Erro: Você deve selecionar exatamente " + ponValue + " PON(s).",
                                        "Quantidade incorreta de PONs",
                                        JOptionPane.ERROR_MESSAGE);
                        return;
                }

                if (isSlot && oltNodos.size() != slotValue) {
                        JOptionPane.showMessageDialog(
                                        null,
                                        "Erro: Você deve selecionar exatamente " + slotValue + " SLOT(s).",
                                        "Quantidade incorreta de SLOTs",
                                        JOptionPane.ERROR_MESSAGE);
                        return;
                }

                System.out.println("OLT's:");
                this.gponDestinoSelecionado = oltNodos;

                for (final String[] dados : oltNodos) {
                        System.out.println(Arrays.toString(dados));
                }

                System.out.println("UPLINK's:");
                this.uplinkDestinoSelecionado = uplinkNodos;
                for (final String[] dados : uplinkNodos) {
                        System.out.println(Arrays.toString(dados));
                }

                this.destinoSelecionado = true;

                // Após validar e armazenar os dados:
                final StringBuilder resumo = new StringBuilder();

                // Adiciona os dados de destino (GPON/SLOT)
                resumo.append("Serviço: ");
                for (final String[] dados : gponDestinoSelecionado) {
                        resumo.append(String.join("-", dados)).append(", ");
                }
                if (!gponDestinoSelecionado.isEmpty()) {
                        resumo.setLength(resumo.length() - 2); // remove última vírgula e espaço
                }
                resumo.append(" ");

                // Adiciona os dados de uplink
                resumo.append("Uplink: ");
                for (final String[] dados : uplinkDestinoSelecionado) {
                        resumo.append(String.join("-", dados)).append(", ");
                }
                if (!uplinkDestinoSelecionado.isEmpty()) {
                        resumo.setLength(resumo.length() - 2);
                }

                // Limita o tamanho do texto para evitar quebra de layout
                final int maxLength = 66;
                String textoFinal = resumo.toString();
                if (textoFinal.length() > maxLength) {
                        textoFinal = textoFinal.substring(0, maxLength - 3) + "...";
                }

                // Exibe no JTextPane do destino
                jTextPaneDadosOltDestino.setText(textoFinal);
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings({ "Convert2Lambda" })
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                buttonGroup1 = new javax.swing.ButtonGroup();
                jPanel1 = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                jButtonDadosOltDestino = new javax.swing.JButton();
                jScrollPane3 = new javax.swing.JScrollPane();
                jTextPaneDadosOltDestino = new javax.swing.JTextPane();
                jLabel3 = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                jButtonDadosOltOrigem = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTextPaneDadosOltOrigem = new javax.swing.JTextPane();
                jLabel1 = new javax.swing.JLabel();
                jButtonSair = new javax.swing.JButton();
                jButtonEnviar = new javax.swing.JButton();
                jPanel4 = new javax.swing.JPanel();
                jRadioButtonSlot = new javax.swing.JRadioButton();
                jRadioButtonPon = new javax.swing.JRadioButton();
                jRadioButtonOnu = new javax.swing.JRadioButton();
                jLabel5 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                jButtonDadosImportOltOrigem = new javax.swing.JButton();
                jLabel8 = new javax.swing.JLabel();
                jButtonFileChooser = new javax.swing.JButton();
                jLabel7 = new javax.swing.JLabel();
                jButtonDadosImportOltDestino = new javax.swing.JButton();
                jButtonCriar = new javax.swing.JButton();
                jButtonColetar = new javax.swing.JButton();
                jButtonPreview = new javax.swing.JButton();
                jTextAreaPreviewCode = new javax.swing.JTextArea();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("Cutover 5k");
                setResizable(false);
                setClosable(true);
                setIconifiable(true);

                jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OLT DESTINO",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("sansserif", 1, 13))); // NOI18N

                jButtonDadosOltDestino.setText("...");
                jButtonDadosOltDestino.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonDadosOltDestinoActionPerformed(evt);
                        }
                });

                jTextPaneDadosOltDestino.setEditable(false);
                jScrollPane3.setViewportView(jTextPaneDadosOltDestino);

                jLabel3.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
                jLabel3.setIcon(new javax.swing.ImageIcon(
                                getClass().getResource("/icons/application_osx_terminal.png"))); // NOI18N
                jLabel3.setText("Destino");
                jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                final javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(25, 25, 25)
                                                                .addComponent(jLabel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                104,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jScrollPane3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                432,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jButtonDadosOltDestino,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGap(7, 7, 7)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jButtonDadosOltDestino)
                                                                                .addComponent(jScrollPane3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel3))
                                                                .addGap(6, 6, 6)));

                jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OLT ORIGEM",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("sansserif", 1, 13))); // NOI18N

                jButtonDadosOltOrigem.setText("...");
                jButtonDadosOltOrigem.setToolTipText("");
                jButtonDadosOltOrigem.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonDadosOltOrigemActionPerformed(evt);
                        }
                });

                jTextPaneDadosOltOrigem.setEditable(false);
                jScrollPane1.setViewportView(jTextPaneDadosOltOrigem);

                jLabel1.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
                jLabel1.setIcon(new javax.swing.ImageIcon(
                                getClass().getResource("/icons/application_osx_terminal.png"))); // NOI18N
                jLabel1.setText("Origem");
                jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                final javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(25, 25, 25)
                                                                .addComponent(jLabel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                104,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                432,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jButtonDadosOltOrigem)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jButtonDadosOltOrigem)
                                                                                .addComponent(jScrollPane1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel1))
                                                                .addGap(6, 6, 6)));

                jButtonSair.setText("Close");
                jButtonSair.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonSairActionPerformed(evt);
                        }
                });

                jButtonEnviar.setText("Enviar");
                jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonEnviarActionPerformed(evt);
                        }
                });

                buttonGroup1.add(jRadioButtonSlot);
                jRadioButtonSlot.setSelected(true);
                jRadioButtonSlot.setText("SLOT");

                buttonGroup1.add(jRadioButtonPon);
                jRadioButtonPon.setText("PON");

                buttonGroup1.add(jRadioButtonOnu);
                jRadioButtonOnu.setText("ONU");

                jLabel5.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
                jLabel5.setText("Migrar de");

                jLabel6.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
                jLabel6.setText("DADOS OLT ORIGEM");

                jButtonDadosImportOltOrigem.setText("...");
                jButtonDadosImportOltOrigem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButtonDadosImportOltOrigem.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonDadosImportOltOrigemActionPerformed();
                        }
                });

                jLabel8.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
                jLabel8.setText("IMPORTAR BKP LOCAL");

                jButtonFileChooser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/folder.png"))); // NOI18N
                jButtonFileChooser.setText("File");
                jButtonFileChooser.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonFileChooserActionPerformed();
                        }
                });

                jLabel7.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
                jLabel7.setText("DADOS OLT DESTINO");

                jButtonDadosImportOltDestino.setText("...");
                jButtonDadosImportOltDestino.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonDadosImportOltDestinoActionPerformed();
                        }
                });

                final javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(14, 14, 14)
                                                                .addGroup(jPanel4Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                false)
                                                                                .addGroup(jPanel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel6,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                167,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jButtonDadosImportOltOrigem,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                80,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel5,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                69,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jRadioButtonSlot)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jRadioButtonPon)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jRadioButtonOnu)))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel4Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jLabel8,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel7,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                143,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(21, 21, 21)
                                                                .addGroup(jPanel4Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jButtonFileChooser,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jButtonDadosImportOltDestino,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                80,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(12, 12, 12)));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel4Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel6)
                                                                                                .addComponent(jButtonDadosImportOltOrigem))
                                                                                .addGroup(jPanel4Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jButtonDadosImportOltDestino)
                                                                                                .addComponent(jLabel7)))
                                                                .addGap(12, 12, 12)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel4Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jRadioButtonSlot)
                                                                                                .addComponent(jRadioButtonPon)
                                                                                                .addComponent(jRadioButtonOnu)
                                                                                                .addComponent(jLabel5))
                                                                                .addGroup(jPanel4Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel8)
                                                                                                .addComponent(jButtonFileChooser)))
                                                                .addGap(6, 6, 6)));

                jButtonCriar.setText("Criar");
                jButtonCriar.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonCriarActionPerformed(evt);
                        }
                });

                jButtonColetar.setText("Coletar bkp remoto");
                jButtonColetar.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonColetarActionPerformed(evt);
                        }
                });

                jButtonPreview.setText("Preview");
                jButtonPreview.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(final java.awt.event.ActionEvent evt) {
                                jButtonPreviewActionPerformed(evt);
                        }
                });

                final javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jButtonPreview)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jButtonColetar)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jButtonCriar)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jButtonEnviar,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                72,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jButtonSair))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                false)
                                                                                                .addComponent(jPanel2,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jPanel3,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jPanel4,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jPanel4,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(6, 6, 6)
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(6, 6, 6)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jButtonSair)
                                                                                .addComponent(jButtonEnviar)
                                                                                .addComponent(jButtonCriar)
                                                                                .addComponent(jButtonColetar)
                                                                                .addComponent(jButtonPreview))
                                                                .addGap(12, 12, 12)));

                final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        /**
         * Método usado para fazer a coleta dos dados da olt de origem, sendo eles os
         * valores de slot, pon ou onu.
         * 
         * Ele ao mesmo tempo reconhece se foi selecionado coleta por slot, pon ou onu e
         * abre sua respectiva coleta ordenada.
         * 
         * @param evt NÃO USADO!
         */
        private void jButtonDadosOltOrigemActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonDadosOltOrigemActionPerformed
                this.isPonSelect = false;
                this.isOnuSelect = false;
                this.isSlotSelect = false;
                if (this.fileChooserIsSelected) {

                        this.dataAnaliser5k = new DataAnaliser5k(this.filePath);
                        this.dataAnaliser5k.start();
                        origemSelecionada = true;
                        final Set<String> conjuntoUnico = new HashSet<>();
                        if (jRadioButtonSlot.isSelected()) {
                                this.oltCutoverSlotTable = new OltCutoverSlotTable();
                                for (final String[] linha : this.dataAnaliser5k.getDataWhitelistFilter()
                                                .getWhitelist()) {
                                        if (conjuntoUnico.add(linha[1])) { // Só adiciona se ainda não existir no
                                                                           // conjunto
                                                oltCutoverSlotTable.adicionarLinha(linha[1]);
                                        }
                                }
                                oltCutoverSlotTable.ordenarTabela();
                                oltCutoverSlotTable.setListener(this);
                                oltCutoverSlotTable.setVisible(true);
                        } else if (jRadioButtonPon.isSelected()) {
                                this.oltCutoverPonTable = new OltCutoverPonTable();
                                for (final String[] linha : this.dataAnaliser5k.getDataWhitelistFilter()
                                                .getWhitelist()) {
                                        final String chave = linha[1] + ";" + linha[2]; // Cria uma chave única

                                        if (conjuntoUnico.add(chave)) { // Só adiciona se ainda não existir no conjunto
                                                oltCutoverPonTable.adicionarLinha(linha[1], linha[2]);
                                        }
                                }
                                oltCutoverPonTable.ordenarTabela();
                                oltCutoverPonTable.setListener(this);
                                oltCutoverPonTable.setVisible(true);
                        } else {
                                this.oltCutoverOnuTable = new OltCutoverOnuTable();
                                for (final String[] linha : this.dataAnaliser5k.getDataWhitelistFilter()
                                                .getWhitelist()) {
                                        oltCutoverOnuTable.adicionarLinha(linha[0],linha[1], linha[2], linha[3]);
                                }
                                oltCutoverOnuTable.ordenarTabela();
                                oltCutoverOnuTable.setListener(this);
                                oltCutoverOnuTable.setVisible(true);
                        }
                } else {
                        origemSelecionada = false;

                        JOptionPane.showMessageDialog(null,
                                        "Nenhum arquivo importado localmente ou remotamente.", "Error!",
                                        JOptionPane.ERROR_MESSAGE, null);
                }
        }// GEN-LAST:event_jButtonDadosOltOrigemActionPerformed

        private void jButtonSairActionPerformed(final java.awt.event.ActionEvent evt) {
                this.dispose();
        }

        /**
         * Método usado para chamar a interface formulário usada para coleta dos dados
         * da OLT de destino, sendo placa de serviço e uplink usar.
         * 
         * @param evt NÃO USADO!
         */
        private void jButtonDadosOltDestinoActionPerformed(final java.awt.event.ActionEvent evt) {
                if (this.origemSelecionada) {
                        this.oltCutoverFormDestino = new OltCutoverFormDestino(jRadioButtonSlot.isSelected(),
                                        this.slotValue, this.ponValue);
                        oltCutoverFormDestino.setListener(this);
                        oltCutoverFormDestino.setVisible(true);
                } else {
                        JOptionPane.showMessageDialog(null,
                                        "Nenhuma origem selecionada.", "Error!",
                                        JOptionPane.ERROR_MESSAGE, null);
                }
        }

        private void jButtonPreviewActionPerformed(final java.awt.event.ActionEvent evt) {
                preview = new OltPreview("Cutover 5k");
                preview.setjTextAreaPreview(jTextAreaPreviewCode);
                preview.setVisible(true);
        }

        /**
         * Método usado para coletar remotamente via telnet ou ssh os dados da olt
         * fiberhome de origem.
         * 
         * @param evt NÃO USADO!
         */
        private void jButtonColetarActionPerformed(final java.awt.event.ActionEvent evt) {

                // PRECISA CRIAR UM COLETADOR DE DADOS DA OLT 5K

                // if (dadosOrigemPreenchidos) {
                // JOptionPane.showMessageDialog(null,
                // "Acessando a OLT remotamente....", null,
                // JOptionPane.INFORMATION_MESSAGE, null);
                // boolean acessou = false;
                // final String arq = "dados.txt";
                // jButtonColetar.setText("Coletar bkp remoto");
                // fileChooserIsSelected = false;
                // if (this.isTelnetOltOrigem) {
                // final TelnetCutover telnet = new TelnetCutover(this.ipOltOrigem,
                // Integer.parseInt(this.portOltOrigem),
                // this.userOltOrigem,
                // this.passOltOrigem, arq);
                // if (telnet.oltAccess()) {
                // this.filePath = arq;
                // previewText(this.filePath);
                // jButtonColetar.setText(this.filePath);
                // acessou = true;
                // fileChooserIsSelected = true;
                // }

                // } else {
                // final SSHClient sshClient = new SSHClient(
                // this.ipOltOrigem,
                // Integer.parseInt(this.portOltOrigem),
                // this.userOltOrigem,
                // this.passOltOrigem, arq);
                // if (sshClient.oltAccess()) {
                // this.filePath = arq;
                // previewText(this.filePath);
                // jButtonColetar.setText(this.filePath);
                // acessou = true;
                // fileChooserIsSelected = true;
                // }
                // }
                // if (acessou) {
                // JOptionPane.showMessageDialog(null,
                // "Script importado com sucesso", null,
                // JOptionPane.INFORMATION_MESSAGE, null);
                // } else {
                // JOptionPane.showMessageDialog(null,
                // "Não foi possível importar o script", null,
                // JOptionPane.INFORMATION_MESSAGE, null);
                // }
                // } else {
                // JOptionPane.showMessageDialog(null,
                // "Para importar o script remotamente, é importante que seja preenchido os
                // dados de acesso da OLT,\n"
                // +
                // "sem isso não será possível fazer a coleta remota, somente via script backup
                // importado localmente!",
                // null,
                // JOptionPane.INFORMATION_MESSAGE, null);
                // }

        }

        /**
         * Método usado para criar o script de migração.
         * 
         * @param evt NÃO USADO!
         */
        private void jButtonCriarActionPerformed(final java.awt.event.ActionEvent evt) {
                // Falta agora finalizar a possibilidade de criar o script com limitação de
                // slot, pon e onu
                if (this.origemSelecionada && this.destinoSelecionado) {
                        // this.dataAnaliser5k = new DataAnaliser5k(this.filePath);
                        // this.dataAnaliser5k.start();

                        final ConfigCutoverGenerator5k cutover = new ConfigCutoverGenerator5k(dataAnaliser5k, this);
                        if (cutover.start()) {
                                previewText("scriptMigracao5kto6k.txt");
                                scriptCriado = true;
                        }

                } else {
                        JOptionPane.showMessageDialog(null,
                                        "Nenhuma origem selecionada.", "Error!",
                                        JOptionPane.ERROR_MESSAGE, null);
                }
        }

        /**
         * Método usado para enviar todas as configurações via telnet.
         * Funcional apenas para olt fiberhome.
         * 
         * @param evt NÃO USADO!
         */
        private void jButtonEnviarActionPerformed(final java.awt.event.ActionEvent evt) {
                if (dadosDestinoPreenchidos) {
                        if (scriptCriado) {
                                JOptionPane.showMessageDialog(null,
                                                "Acessando a OLT remotamente....", null,
                                                JOptionPane.INFORMATION_MESSAGE, null);

                                final TelnetFhtt tesTelnetFhtt = new TelnetFhtt(this.ipOltDestino,
                                                Integer.parseInt(this.portOltDestino),
                                                this.userOltDestino,
                                                this.passOltDestino,
                                                "AN6000");
                                if (tesTelnetFhtt.oltAccess("scriptMigracao5kto6k.txt")) {
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
                } else {
                        JOptionPane.showMessageDialog(null,
                                        "Para enviar o script remotamente, é importante que seja preenchido os dados de acesso da OLT,\n"
                                                        +
                                                        "sem isso não será possível fazer o envio remoto, somente localmente!",
                                        null,
                                        JOptionPane.INFORMATION_MESSAGE, null);
                }
        }

        /**
         * Método que chama a interface formulário dos dados da olt de origem!
         */
        private void jButtonDadosImportOltOrigemActionPerformed() {
                olt5kCutoverOrigemAcesso.setListener(this);
                olt5kCutoverOrigemAcesso.setVisible(true);
        }

        /**
         * Método que chama a interface formulário dos dados da olt de destino
         */
        private void jButtonDadosImportOltDestinoActionPerformed() {
                olt5kCutoverDestinoAcesso.setListener(this);
                olt5kCutoverDestinoAcesso.setVisible(true);
        }

        @SuppressWarnings("CallToPrintStackTrace")
        private void previewText(final String path) {
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

        private void jButtonFileChooserActionPerformed() {
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
                        // jButtonFileChooser.setText(selectedFile.getName());
                        String fileName = selectedFile.getName();
                        final int maxLength = 5; // Defina o número máximo de caracteres visíveis

                        if (fileName.length() > maxLength) {
                                fileName = fileName.substring(0, maxLength - 3) + "..."; // Corta e adiciona "..."
                        }

                        jButtonFileChooser.setText(fileName);
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
