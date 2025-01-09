/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package engtelecom.swingType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import engtelecom.App;
import engtelecom.access.TelnetFhtt;
import engtelecom.product.OltGpon;
import engtelecom.product.OltGponFhtt;

/**
 *
 * @author faber222
 */
public class OltFhtt extends javax.swing.JInternalFrame implements CapabilityProfileListener {

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// Botoes de ação do código
	private javax.swing.JButton jButtonCancel;

	private javax.swing.JButton jButtonCriar;

	private javax.swing.JButton jButtonCriarONUCapability;

	private javax.swing.JButton jButtonEnviar;

	private javax.swing.JButton jButtonFileChooser;

	// Dados campo de acesso telnetFhtt
	private javax.swing.JTextField jTextFieldIpOlt;

	private javax.swing.JFormattedTextField jFormattedTextFieldPortOlt;

	private javax.swing.JTextField jTextFieldOltUser;

	private javax.swing.JPasswordField jPasswordFieldOltPasswd;

	// Botões de seleção de objetos e seus grupos
	private javax.swing.ButtonGroup buttonGroup1;

	private javax.swing.JRadioButton jRadioButtonWanService;

	private javax.swing.JRadioButton jRadioButtonVeip;
	private javax.swing.JCheckBox jCheckBoxFlagONUCapability;
	// Apenas textos
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;

	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;

	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;

	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	// Paineis que montam os componentes da tela
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	// Variaveis auxiliares
	private javax.swing.JScrollPane jScrollPanel;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTextArea jTextAreaPreviewCode;
	// Dados inteiros da OLT
	private javax.swing.JSpinner jSpinnerPortaUplink;

	private javax.swing.JSpinner jSpinnerSlotUplink;
	private javax.swing.JSpinner jSpinnerVlanPPPOE;
	private javax.swing.JSpinner jSpinnerVlanVeip;
	private javax.swing.JSpinner jSpinnerPortaPon;
	private javax.swing.JSpinner jSpinnerSlotCpe;
	private javax.swing.JSpinner jSpinnerSlotPON;
	// Dados de string da OLT
	private javax.swing.JTextField jTextFieldModeloONU;

	private javax.swing.JTextField jTextFieldPassPPPOE;
	private javax.swing.JTextField jTextFieldPhyIdCPE;
	private javax.swing.JTextField jTextFieldSSID2;

	private javax.swing.JTextField jTextFieldSSID5;
	private javax.swing.JTextField jTextFieldSSIDPass2;
	private javax.swing.JTextField jTextFieldSSIDPass5;
	private javax.swing.JTextField jTextFieldUserPPPOE;
	// Delimitadores dos spinners
	private final SpinnerNumberModel modelChassiPon;
	private final SpinnerNumberModel modelChassiUp;
	private final SpinnerNumberModel modelPortaPon;

	private final SpinnerNumberModel modelPortaUp;
	private final SpinnerNumberModel modelCpe;
	private final SpinnerNumberModel modelPortOlt;
	private final SpinnerNumberModel modelCvlanId;
	private final SpinnerNumberModel modelPortEth;
	// Atributos filhos do OltCapa
	private String capaCpeType;
	private String capaPonType;
	private String capaWifiNumber;
	private String capaTenGPortNumber;

	private String capaOneGPortNumber;
	private String capaEquipamentId;
	private String capaPotsNumber;
	private String capaProfileName;
	private String capaUsbNumber;
	// Dados usados para repasse de contexto
	private String[] cpeType;
	private String[] ponType;
	private String[] indexPonType;
	private String[] boxCvlanMode;

	private String[] wifiNumber;
	private boolean fileChooserIsSelected;
	private String nomeArq;
	private String fileName;
	private ImageIcon oltIcon;
	private ImageIcon errorIcon;
	private ImageIcon successIcon;
	private final String oltName;
	private final CapabilityProfile capa;
	private OltPreview preview;
	private final OltGponFhtt oltGponFhtt;

	private javax.swing.JButton jButtonPreview;

	private javax.swing.JComboBox<String> jComboBoxCvlanMode;

	private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel23;
	private javax.swing.JLabel jLabel24;
	private javax.swing.JLabel jLabel25;
	private javax.swing.JPanel jPanelEth;
	private javax.swing.JPanel jPanelVeip;
	private javax.swing.JPanel jPanelWanService;
	private javax.swing.JRadioButton jRadioButtonEth;
	private javax.swing.JSpinner jSpinnerPortEth;
	private javax.swing.JSpinner jSpinnerPortOlt;

	private javax.swing.JSpinner jSpinnerVlanEth;

	private final ImageIcon previewIcon;
	private final ImageIcon fileIcon;
	private final ImageIcon fileHoverIcon;
	private final ImageIcon createIcon;
	private final ImageIcon sendIcon;
	private final ImageIcon ipIcon;
	private final ImageIcon portIcon;
	private final ImageIcon userIcon;
	private final ImageIcon passIcon;

	// End of variables declaration
	/**
	 * Creates new form OltG16
	 */
	public OltFhtt(final String oltName, final App app) {
		this.modelChassiPon = new SpinnerNumberModel(1, 1, 100, 1);
		this.modelChassiUp = new SpinnerNumberModel(1, 1, 100, 1);
		this.modelPortaPon = new SpinnerNumberModel(1, 1, 100, 1);
		this.modelPortaUp = new SpinnerNumberModel(1, 1, 100, 1);
		this.modelCpe = new SpinnerNumberModel(1, 1, 128, 1);
		this.modelPortOlt = new SpinnerNumberModel(1, 1, 65535, 1);
		this.modelCvlanId = new SpinnerNumberModel(1, 1, 4095, 1);
		this.modelPortEth = new SpinnerNumberModel(1, 1, 10, 1);
		this.previewIcon = app.getPreviewIcon();
		this.fileIcon = app.getFileIcon();
		this.fileHoverIcon = app.getFileHoverIcon();
		this.createIcon = app.getCriarIcon();
		this.sendIcon = app.getEnviarIcon();
		this.ipIcon = app.getIpIcon();
		this.portIcon = app.getPortIcon();
		this.userIcon = app.getUserIcon();
		this.passIcon = app.getPassIcon();

		this.oltName = oltName;
		initComponents();
		oltGponFhtt = new OltGponFhtt();
		capa = new CapabilityProfile(this.cpeType, this.ponType, this.indexPonType, this.wifiNumber);
	}

	public ImageIcon getOltIcon() {
		return oltIcon;
	}

	public ImageIcon getErrorIcon() {
		return errorIcon;
	}

	public ImageIcon getSuccessIcon() {
		return successIcon;
	}

	public javax.swing.JRadioButton getjRadioButtonWanService() {
		return jRadioButtonWanService;
	}

	public javax.swing.JRadioButton getjRadioButtonVeip() {
		return jRadioButtonVeip;
	}

	public javax.swing.ButtonGroup getButtonGroup1() {
		return buttonGroup1;
	}

	public javax.swing.JCheckBox getjCheckBoxFlagONUCapability() {
		return jCheckBoxFlagONUCapability;
	}

	public javax.swing.JComboBox<String> getjComboBoxCvlanMode() {
		return jComboBoxCvlanMode;
	}

	public javax.swing.JSpinner getjSpinnerPortEth() {
		return jSpinnerPortEth;
	}

	public javax.swing.JSpinner getjSpinnerVlanEth() {
		return jSpinnerVlanEth;
	}

	public javax.swing.JRadioButton getjRadioButtonEth() {
		return jRadioButtonEth;
	}

	public javax.swing.JSpinner getjSpinnerPortaUplink() {
		return jSpinnerPortaUplink;
	}

	public javax.swing.JSpinner getjSpinnerSlotUplink() {
		return jSpinnerSlotUplink;
	}

	public javax.swing.JSpinner getjSpinnerVlanPPPOE() {
		return jSpinnerVlanPPPOE;
	}

	public javax.swing.JSpinner getjSpinnerVlanVeip() {
		return jSpinnerVlanVeip;
	}

	public javax.swing.JSpinner getjSpinnerPortaPon() {
		return jSpinnerPortaPon;
	}

	public javax.swing.JSpinner getjSpinnerSlotCpe() {
		return jSpinnerSlotCpe;
	}

	public javax.swing.JSpinner getjSpinnerSlotPON() {
		return jSpinnerSlotPON;
	}

	public javax.swing.JTextField getjTextFieldModeloONU() {
		return jTextFieldModeloONU;
	}

	public javax.swing.JTextField getjTextFieldPassPPPOE() {
		return jTextFieldPassPPPOE;
	}

	public javax.swing.JTextField getjTextFieldPhyIdCPE() {
		return jTextFieldPhyIdCPE;
	}

	public javax.swing.JTextField getjTextFieldSSID2() {
		return jTextFieldSSID2;
	}

	public javax.swing.JTextField getjTextFieldSSID5() {
		return jTextFieldSSID5;
	}

	public javax.swing.JTextField getjTextFieldSSIDPass2() {
		return jTextFieldSSIDPass2;
	}

	public javax.swing.JTextField getjTextFieldSSIDPass5() {
		return jTextFieldSSIDPass5;
	}

	public javax.swing.JTextField getjTextFieldUserPPPOE() {
		return jTextFieldUserPPPOE;
	}

	public String getNomeArq() {
		return nomeArq;
	}

	public String getOltName() {
		return oltName;
	}

	public String getCapaCpeType() {
		return capaCpeType;
	}

	public String getCapaPonType() {
		return capaPonType;
	}

	public String getCapaWifiNumber() {
		return capaWifiNumber;
	}

	public String getCapaTenGPortNumber() {
		return capaTenGPortNumber;
	}

	public String getCapaOneGPortNumber() {
		return capaOneGPortNumber;
	}

	public String getCapaEquipamentId() {
		return capaEquipamentId;
	}

	public String getCapaPotsNumber() {
		return capaPotsNumber;
	}

	public String getCapaProfileName() {
		return capaProfileName;
	}

	public String getCapaUsbNumber() {
		return capaUsbNumber;
	}

	public String[] getBoxCvlanMode() {
		return boxCvlanMode;
	}

	public void printDadosCapa() {
		System.err.println("");
		System.out.println("Dados recebidos Capability Profile:");
		System.out.println("CPE Type: " + this.capaCpeType);
		System.out.println("PON Type: " + this.capaPonType);
		System.out.println("WiFi Number: " + this.capaWifiNumber);
		System.out.println("10G Port: " + this.capaTenGPortNumber);
		System.out.println("1G Port: " + this.capaOneGPortNumber);
		System.out.println("Equipament ID: " + this.capaEquipamentId);
		System.out.println("POTS Number: " + this.capaPotsNumber);
		System.out.println("Profile Name: " + this.capaProfileName);
		System.out.println("USB Number: " + this.capaUsbNumber);
	}

	@Override
	public void onProfileCreated(final String cpeType, final String ponType, final String wifiNumber,
			final String tenGPort, final String oneGPort, final String equipamentID,
			final String potsNumber, final String profileName, final String usbNumber) {

		if (this.jCheckBoxFlagONUCapability.isSelected()) {
			// Aqui você pode atualizar os componentes ou processar os dados recebidos
			this.jTextFieldModeloONU.setText(profileName);

			this.capaCpeType = cpeType;
			this.capaPonType = ponType;
			this.capaWifiNumber = wifiNumber;
			this.capaTenGPortNumber = tenGPort;
			this.capaOneGPortNumber = oneGPort;
			this.capaEquipamentId = equipamentID;
			this.capaPotsNumber = potsNumber;
			this.capaProfileName = profileName;
			this.capaUsbNumber = usbNumber;
			printDadosCapa();
		}
	}

	private void previewText() {
		// Tente abrir e ler o arquivo
		try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
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

	private void olt() {
		// Carrega os ícones necessários para o diálogo
		final ClassLoader classLoader = OltFhtt.class.getClassLoader();
		this.errorIcon = new ImageIcon(classLoader.getResource("erro.png"));
		this.successIcon = new ImageIcon(classLoader.getResource("success.png"));
		this.oltIcon = new ImageIcon(classLoader.getResource("AN6000_15.png"));
		this.nomeArq = "script" + this.oltName + ".txt";
		this.fileName = this.nomeArq;
		this.fileChooserIsSelected = false;

		this.cpeType = new String[] {
				"SFU",
				"HGU",
				"BOX MDU",
				"CARD MDU",
				"DPU"
		};

		this.ponType = new String[] {
				"1G EPON",
				"10G EPON 10G/10G",
				"10G EPON 1G/10G",
				"1G GPON",
				"10 GPON 2.5G/10G",
				"10GPON 10G/10G",
				"GPON/XGPON/XGSPON auto",
				"EPON/10GEPON auto",
				"25G PON 25G/25G"
		};

		this.indexPonType = new String[] {
				"263",
				"807",
				"808",
				"712",
				"813",
				"650",
				"824",
				"829",
				"826"
		};

		this.wifiNumber = new String[] {
				"0",
				"1",
				"2",
				"3",
				"4"
		};

		this.boxCvlanMode = new String[] {
				"Tag", "Untag"
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

		buttonGroup1 = new javax.swing.ButtonGroup();
		jPanel1 = new javax.swing.JPanel();
		jLabel20 = new javax.swing.JLabel();
		jTextFieldIpOlt = new javax.swing.JTextField();
		jLabel21 = new javax.swing.JLabel();
		jTextFieldOltUser = new javax.swing.JTextField();
		jLabel22 = new javax.swing.JLabel();
		jSpinnerPortOlt = new javax.swing.JSpinner(modelPortOlt);
		jLabel23 = new javax.swing.JLabel();
		jPasswordFieldOltPasswd = new javax.swing.JPasswordField();
		jPanel2 = new javax.swing.JPanel();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanelVeip = new javax.swing.JPanel();
		jLabel16 = new javax.swing.JLabel();
		jSpinnerVlanVeip = new javax.swing.JSpinner(modelCvlanId);
		jPanelWanService = new javax.swing.JPanel();
		jLabel10 = new javax.swing.JLabel();
		jSpinnerVlanPPPOE = new javax.swing.JSpinner(modelCvlanId);
		jLabel9 = new javax.swing.JLabel();
		jTextFieldUserPPPOE = new javax.swing.JTextField();
		jTextFieldPassPPPOE = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jTextFieldSSID2 = new javax.swing.JTextField();
		jLabel13 = new javax.swing.JLabel();
		jTextFieldSSIDPass2 = new javax.swing.JTextField();
		jLabel14 = new javax.swing.JLabel();
		jTextFieldSSIDPass5 = new javax.swing.JTextField();
		jLabel15 = new javax.swing.JLabel();
		jTextFieldSSID5 = new javax.swing.JTextField();
		jPanelEth = new javax.swing.JPanel();
		jLabel17 = new javax.swing.JLabel();
		jSpinnerPortEth = new javax.swing.JSpinner(modelPortEth);
		jLabel18 = new javax.swing.JLabel();
		jSpinnerVlanEth = new javax.swing.JSpinner(modelCvlanId);
		jLabel19 = new javax.swing.JLabel();
		jComboBoxCvlanMode = new javax.swing.JComboBox<>();
		jLabel1 = new javax.swing.JLabel();
		jTextFieldPhyIdCPE = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jSpinnerSlotPON = new javax.swing.JSpinner(modelChassiPon);
		jSpinnerPortaPon = new javax.swing.JSpinner(modelPortaPon);
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jSpinnerSlotCpe = new javax.swing.JSpinner(modelCpe);
		jTextFieldModeloONU = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		jSpinnerSlotUplink = new javax.swing.JSpinner(modelChassiUp);
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jSpinnerPortaUplink = new javax.swing.JSpinner(modelPortaUp);
		jLabel8 = new javax.swing.JLabel();
		jRadioButtonEth = new javax.swing.JRadioButton();
		jRadioButtonVeip = new javax.swing.JRadioButton();
		jRadioButtonWanService = new javax.swing.JRadioButton();
		jCheckBoxFlagONUCapability = new javax.swing.JCheckBox();
		jButtonCriarONUCapability = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		jButtonEnviar = new javax.swing.JButton();
		jButtonCriar = new javax.swing.JButton();
		jPanel5 = new javax.swing.JPanel();
		jButtonPreview = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jButtonFileChooser = new javax.swing.JButton();
		jLabel24 = new javax.swing.JLabel();
		jLabel25 = new javax.swing.JLabel();
		jTextAreaPreviewCode = new javax.swing.JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setClosable(true);
		setForeground(java.awt.Color.darkGray);
		setIconifiable(true);
		setMaximizable(false);
		setResizable(false);
		setTitle(this.oltName);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setFrameIcon(oltIcon); //

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados de acesso da OLT"));

		jLabel20.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel20.setIcon(this.ipIcon); // NOI18N
		jLabel20.setText("IP OLT:");

		jLabel21.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel21.setIcon(this.userIcon); // NOI18N
		jLabel21.setText("Usuário:");

		jLabel22.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel22.setIcon(this.passIcon); // NOI18N
		jLabel22.setText("Senha:");

		jLabel23.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel23.setIcon(this.portIcon); // NOI18N
		jLabel23.setText("Porta:");

		final javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 110,
												Short.MAX_VALUE)
										.addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jTextFieldIpOlt)
										.addComponent(jSpinnerPortOlt, javax.swing.GroupLayout.DEFAULT_SIZE, 230,
												Short.MAX_VALUE))
								.addGap(131, 131, 131)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 104,
												Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jTextFieldOltUser)
										.addComponent(jPasswordFieldOltPasswd, javax.swing.GroupLayout.DEFAULT_SIZE,
												230, Short.MAX_VALUE))
								.addGap(16, 16, 16)));
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel20)
										.addComponent(jTextFieldIpOlt, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel21)
										.addComponent(jTextFieldOltUser, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel23)
										.addComponent(jSpinnerPortOlt, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel22)
										.addComponent(jPasswordFieldOltPasswd, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulário do Script"));

		jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jLabel16.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel16.setText("CVLAN ID(V):");

		final javax.swing.GroupLayout jPanelVeipLayout = new javax.swing.GroupLayout(jPanelVeip);
		jPanelVeip.setLayout(jPanelVeipLayout);
		jPanelVeipLayout.setHorizontalGroup(
				jPanelVeipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanelVeipLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 278,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSpinnerVlanVeip, javax.swing.GroupLayout.DEFAULT_SIZE, 257,
										Short.MAX_VALUE)
								.addContainerGap()));
		jPanelVeipLayout.setVerticalGroup(
				jPanelVeipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanelVeipLayout.createSequentialGroup()
								.addGap(50, 50, 50)
								.addGroup(
										jPanelVeipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel16)
												.addComponent(jSpinnerVlanVeip, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(59, 59, 59)));

		jTabbedPane1.addTab("Veip", jPanelVeip);

		jLabel10.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel10.setText("User PPPOE:");

		jLabel9.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel9.setText("CVLAN ID(V):");

		jLabel11.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel11.setText("Pass PPPOE:");

		jLabel12.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel12.setText("SSID 2.4Ghz:");

		jLabel13.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel13.setText("Pass 2.4Ghz:");

		jLabel14.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel14.setText("Pass 5Ghz:");

		jLabel15.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel15.setText("SSID 5Ghz:");

		final javax.swing.GroupLayout jPanelWanServiceLayout = new javax.swing.GroupLayout(jPanelWanService);
		jPanelWanService.setLayout(jPanelWanServiceLayout);
		jPanelWanServiceLayout.setHorizontalGroup(
				jPanelWanServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanelWanServiceLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(jPanelWanServiceLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 100,
												Short.MAX_VALUE)
										.addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(jPanelWanServiceLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanelWanServiceLayout.createSequentialGroup()
												.addComponent(jTextFieldPassPPPOE,
														javax.swing.GroupLayout.PREFERRED_SIZE, 150,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(jPanelWanServiceLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE,
																100, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(jPanelWanServiceLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jTextFieldSSID5,
																javax.swing.GroupLayout.PREFERRED_SIZE, 150,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jTextFieldSSIDPass5,
																javax.swing.GroupLayout.PREFERRED_SIZE, 150,
																javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGroup(jPanelWanServiceLayout.createSequentialGroup()
												.addGroup(jPanelWanServiceLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jSpinnerVlanPPPOE,
																javax.swing.GroupLayout.PREFERRED_SIZE, 150,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jTextFieldUserPPPOE,
																javax.swing.GroupLayout.PREFERRED_SIZE, 150,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(jPanelWanServiceLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE,
																100, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(jPanelWanServiceLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jTextFieldSSID2,
																javax.swing.GroupLayout.PREFERRED_SIZE, 150,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jTextFieldSSIDPass2,
																javax.swing.GroupLayout.PREFERRED_SIZE, 150,
																javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addGap(11, 11, 11)));
		jPanelWanServiceLayout.setVerticalGroup(
				jPanelWanServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanelWanServiceLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(jPanelWanServiceLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanelWanServiceLayout.createSequentialGroup()
												.addGroup(jPanelWanServiceLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel9)
														.addComponent(jSpinnerVlanPPPOE,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanelWanServiceLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel10)
														.addComponent(jTextFieldUserPPPOE,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGroup(jPanelWanServiceLayout.createSequentialGroup()
												.addGroup(jPanelWanServiceLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel12)
														.addComponent(jTextFieldSSID2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanelWanServiceLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel13)
														.addComponent(jTextFieldSSIDPass2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanelWanServiceLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanelWanServiceLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel11)
												.addComponent(jTextFieldPassPPPOE,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanelWanServiceLayout.createSequentialGroup()
												.addGroup(jPanelWanServiceLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel15)
														.addComponent(jTextFieldSSID5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanelWanServiceLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel14)
														.addComponent(jTextFieldSSIDPass5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addGap(16, 16, 16)));

		jTabbedPane1.addTab("WanService", jPanelWanService);

		jLabel17.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel17.setText("Porta eth:");

		jLabel18.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel18.setText("CVLAN ID(V):");

		jLabel19.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel19.setText("CVLAN Mode(M)");

		jComboBoxCvlanMode.setModel(new javax.swing.DefaultComboBoxModel<>(this.boxCvlanMode));

		final javax.swing.GroupLayout jPanelEthLayout = new javax.swing.GroupLayout(jPanelEth);
		jPanelEth.setLayout(jPanelEthLayout);
		jPanelEthLayout.setHorizontalGroup(
				jPanelEthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanelEthLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(jPanelEthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanelEthLayout.createSequentialGroup()
												.addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 294,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(jComboBoxCvlanMode, 0,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(jPanelEthLayout.createSequentialGroup()
												.addGroup(jPanelEthLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(jPanelEthLayout.createSequentialGroup()
																.addComponent(jLabel17,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 294,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18)
																.addComponent(jSpinnerPortEth,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 227,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(jPanelEthLayout.createSequentialGroup()
																.addComponent(jLabel18,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 294,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18)
																.addComponent(jSpinnerVlanEth,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 227,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGap(0, 2, Short.MAX_VALUE)))
								.addContainerGap()));
		jPanelEthLayout.setVerticalGroup(
				jPanelEthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanelEthLayout.createSequentialGroup()
								.addGap(15, 15, 15)
								.addGroup(
										jPanelEthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel17)
												.addComponent(jSpinnerPortEth, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanelEthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel18)
												.addComponent(jSpinnerVlanEth, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanelEthLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel19)
										.addComponent(jComboBoxCvlanMode, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(36, 36, 36)));

		jTabbedPane1.addTab("Eth", jPanelEth);

		jLabel1.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel1.setText("Phy-ID CPE:");

		jTextFieldPhyIdCPE.setText("FHTT12345678");
		jTextFieldPhyIdCPE.setToolTipText("FHTT12345678");
		jTextFieldPhyIdCPE.setName("FHTT12345678"); // NOI18N

		jLabel2.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel2.setText("Slot GPON:");

		jLabel3.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel3.setText("Porta PON:");

		jLabel4.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel4.setText("Slot CPE:");

		jLabel5.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel5.setText("Modelo CPE:");

		jTextFieldModeloONU.setText("HG6145F3");
		jTextFieldModeloONU.setToolTipText("HG6145F3");
		jTextFieldModeloONU.setName("HG6145F3"); // NOI18N

		jLabel6.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel6.setText("Slot Uplink:");

		jLabel7.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
		jLabel7.setText("Porta Uplink:");

		jLabel8.setText("CPE em:");

		buttonGroup1.add(jRadioButtonEth);
		jRadioButtonEth.setText("ETH");

		buttonGroup1.add(jRadioButtonVeip);
		jRadioButtonVeip.setSelected(true);
		jRadioButtonVeip.setText("VEIP");

		buttonGroup1.add(jRadioButtonWanService);
		jRadioButtonWanService.setText("WAN-SERVICE");

		jCheckBoxFlagONUCapability.setText("Criar ONU Capability");

		jButtonCriarONUCapability.setText("Criar");
		jButtonCriarONUCapability.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(final java.awt.event.ActionEvent evt) {
				jButtonCriarONUCapabilityActionPerformed(evt);
			}
		});

		final javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel2Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jTextFieldModeloONU)
												.addComponent(jSpinnerSlotCpe)
												.addComponent(jSpinnerPortaPon)
												.addComponent(jSpinnerSlotPON)
												.addComponent(jTextFieldPhyIdCPE,
														javax.swing.GroupLayout.PREFERRED_SIZE, 150,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jSpinnerPortaUplink, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jSpinnerSlotUplink, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(jPanel2Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addGap(6, 6, 6)
												.addComponent(jRadioButtonVeip)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jRadioButtonWanService)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jRadioButtonEth)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jCheckBoxFlagONUCapability)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButtonCriarONUCapability))
										.addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel8))
								.addContainerGap()));
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jLabel8)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jRadioButtonVeip)
														.addComponent(jRadioButtonEth)
														.addComponent(jRadioButtonWanService)
														.addComponent(jCheckBoxFlagONUCapability)
														.addComponent(jButtonCriarONUCapability)))
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addContainerGap()
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(jTextFieldPhyIdCPE,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(jSpinnerSlotPON,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(jSpinnerPortaPon,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(jSpinnerSlotCpe,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(jTextFieldModeloONU,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(jSpinnerSlotUplink,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel7)
														.addComponent(jSpinnerPortaUplink,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addContainerGap()));

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Comandos"));

		jButtonEnviar.setText("Enviar");
		jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(final java.awt.event.ActionEvent evt) {
				jButtonEnviarActionPerformed(evt);
			}
		});

		jButtonCriar.setText("Criar");
		jButtonCriar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(final java.awt.event.ActionEvent evt) {
				jButtonCriarActionPerformed(evt);
			}
		});

		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Preview"));

		jButtonPreview.setIcon(this.previewIcon); // NOI18N
		jButtonPreview.setText("Visualizar");
		jButtonPreview.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(final java.awt.event.ActionEvent evt) {
				jButtonPreviewActionPerformed(evt);
			}
		});

		final javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(
				jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jButtonPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 240,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		jPanel5Layout.setVerticalGroup(
				jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel5Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jButtonPreview)
								.addContainerGap()));

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Importar script local"));

		jButtonFileChooser.setIcon(this.fileIcon); // NOI18N
		jButtonFileChooser.setText("File");
		jButtonFileChooser.setSelectedIcon(this.fileHoverIcon); // NOI18N
		jButtonFileChooser.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(final java.awt.event.ActionEvent evt) {
				jButtonFileChooserActionPerformed(evt);
			}
		});

		final javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(
				jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel3Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jButtonFileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 240,
										Short.MAX_VALUE)
								.addContainerGap()));
		jPanel3Layout.setVerticalGroup(
				jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jButtonFileChooser)
								.addContainerGap()));

		jLabel24.setIcon(this.createIcon); // NOI18N
		jLabel24.setText("Criar script:");

		jLabel25.setIcon(this.sendIcon); // NOI18N
		jLabel25.setText("Enviar para a OLT:");

		final javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(
				jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11,
										Short.MAX_VALUE)
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(jPanel4Layout.createSequentialGroup()
												.addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 161,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(jButtonCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 113,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel4Layout.createSequentialGroup()
												.addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 161,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(jButtonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE,
														113, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		jPanel4Layout.setVerticalGroup(
				jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup()
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel4Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(jPanel4Layout.createSequentialGroup()
												.addGap(3, 3, 3)
												.addGroup(jPanel4Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel24)
														.addComponent(jButtonCriar))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(jPanel4Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButtonEnviar)
														.addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE,
																14, javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

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
			this.fileName = selectedFile.getAbsolutePath();
			jButtonFileChooser.setText(selectedFile.getName());
			previewText();
		} else {
			this.fileChooserIsSelected = false;
			JOptionPane.showMessageDialog(null,
					"Nenhum arquivo selecionado.", "Error!",
					JOptionPane.ERROR_MESSAGE, this.errorIcon);
			jButtonFileChooser.setText("File");
			jTextAreaPreviewCode.setText("");
		}

	}// GEN-LAST:event_jButtonFileChooserActionPerformed

	private void jButtonEnviarActionPerformed(final java.awt.event.ActionEvent evt) {
		if (this.fileChooserIsSelected) {
			if (this.oltGponFhtt.checkTelnet(jTextFieldIpOlt.getText(), jSpinnerPortOlt.getValue().toString(),
					jTextFieldOltUser.getText(), jPasswordFieldOltPasswd.getPassword(),
					this.errorIcon)) {
				JOptionPane.showMessageDialog(null,
						"Valores válidos!", "Sucesso!",
						JOptionPane.INFORMATION_MESSAGE, this.successIcon);

				// Cria um SwingWorker para executar a tarefa em uma thread separada
				final SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						final TelnetFhtt acessoOlt = new TelnetFhtt(jTextFieldIpOlt.getText(),
								Integer.parseInt(jSpinnerPortOlt.getValue().toString()),
								jTextFieldOltUser.getText(),
								new String(jPasswordFieldOltPasswd.getPassword()), getOltName());
						acessoOlt.oltAccess(fileName);
						return null;
					}
				};

				// Inicia a execução da tarefa em uma thread separada
				worker.execute();
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Nenhum arquivo selecionado ou criado!", "Erro",
					JOptionPane.ERROR_MESSAGE, this.errorIcon);
		}
	}

	private void jButtonCriarActionPerformed(final java.awt.event.ActionEvent evt) {
		this.fileName = this.nomeArq;

		if (this.oltGponFhtt.start(this)) {
			// Cria um objeto ConfigGenerator para gerar o script de configuração
			JOptionPane.showMessageDialog(null,
					"Script criado com sucesso!", "Sucesso!",
					JOptionPane.ERROR_MESSAGE, this.successIcon);
			this.fileChooserIsSelected = true;
			jButtonFileChooser.setText(this.fileName);
			previewText();
		} else {
			JOptionPane.showMessageDialog(null,
					"Script nao foi criado!", "Error!",
					JOptionPane.ERROR_MESSAGE, this.errorIcon);
		}
	}

	private void jButtonCriarONUCapabilityActionPerformed(final java.awt.event.ActionEvent evt) {

		// final JPanel glassPane = (JPanel) this.getRootPane().getGlassPane();
		// glassPane.setVisible(true);
		// glassPane.setOpaque(false);

		// glassPane.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(final MouseEvent e) {
		// playAlertSound();
		// }
		// });
		// glassPane.addMouseListener(new java.awt.event.MouseAdapter() {
		// });

		capa.setListener(this);
		// capa.addWindowListener(new java.awt.event.WindowAdapter() {
		// @Override
		// public void windowClosed(final java.awt.event.WindowEvent e) {
		// glassPane.setVisible(false); // Desbloqueia a interação
		// }
		// });
		capa.setVisible(true);
	}

	// private void playAlertSound() {
	// try {
	// try (AudioInputStream audioInputStream = AudioSystem
	// .getAudioInputStream(getClass().getResource("/sounds/error1.wav"))) {
	// final Clip clip = AudioSystem.getClip();
	// clip.open(audioInputStream);
	// clip.start();
	// }
	// } catch (IOException | LineUnavailableException |
	// UnsupportedAudioFileException e) {
	// e.printStackTrace();
	// }
	// }

	private void jButtonPreviewActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonPreviewActionPerformed
		preview = new OltPreview(this.oltName);
		System.out.println(jTextAreaPreviewCode.getText());
		preview.setjTextAreaPreview(jTextAreaPreviewCode);
		preview.setVisible(true);
	}// GEN-LAST:event_jButtonPreviewActionPerformed

}
