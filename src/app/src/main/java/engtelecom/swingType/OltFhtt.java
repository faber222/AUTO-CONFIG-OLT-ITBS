/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package engtelecom.swingType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import engtelecom.access.Telnet;
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

	// Dados campo de acesso telnet
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
	private final SpinnerNumberModel modelVlanVeip;
	private final SpinnerNumberModel modelVlanWan;
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

	private String[] wifiNumber;
	private boolean fileChooserIsSelected;
	private String nomeArq;
	private String fileName;
	private ImageIcon oltIcon;
	private ImageIcon errorIcon;
	private ImageIcon successIcon;
	private final String oltName;
	private final CapabilityProfile capa;
	private final OltGponFhtt oltGponFhtt;

	// End of variables declaration
	/**
	 * Creates new form OltG16
	 */
	public OltFhtt(String oltName) {
		this.modelChassiPon = new SpinnerNumberModel(1, 1, 100, 1);
		this.modelChassiUp = new SpinnerNumberModel(1, 1, 100, 1);
		this.modelPortaPon = new SpinnerNumberModel(1, 1, 100, 1);
		this.modelPortaUp = new SpinnerNumberModel(1, 1, 100, 1);
		this.modelCpe = new SpinnerNumberModel(1, 1, 128, 1);
		this.modelVlanVeip = new SpinnerNumberModel(1, 1, 4095, 1);
		this.modelVlanWan = new SpinnerNumberModel(1, 1, 4095, 1);
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

	public javax.swing.JCheckBox getjCheckBoxFlagONUCapability() {
		return jCheckBoxFlagONUCapability;
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
		final ClassLoader classLoader = OltGpon.class.getClassLoader();
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

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		olt();

		buttonGroup1 = new javax.swing.ButtonGroup();
		jScrollPanel = new javax.swing.JScrollPane();
		jTextAreaPreviewCode = new javax.swing.JTextArea();
		jPasswordFieldOltPasswd = new javax.swing.JPasswordField();
		jFormattedTextFieldPortOlt = new javax.swing.JFormattedTextField();
		jCheckBoxFlagONUCapability = new javax.swing.JCheckBox();
		jTabbedPane1 = new javax.swing.JTabbedPane();

		jTextFieldIpOlt = new javax.swing.JTextField();
		jTextFieldPhyIdCPE = new javax.swing.JTextField();
		jTextFieldModeloONU = new javax.swing.JTextField();
		jTextFieldOltUser = new javax.swing.JTextField();
		jTextFieldSSID5 = new javax.swing.JTextField();
		jTextFieldPassPPPOE = new javax.swing.JTextField();
		jTextFieldUserPPPOE = new javax.swing.JTextField();
		jTextFieldSSIDPass5 = new javax.swing.JTextField();
		jTextFieldSSIDPass2 = new javax.swing.JTextField();
		jTextFieldSSID2 = new javax.swing.JTextField();

		jSpinnerVlanPPPOE = new javax.swing.JSpinner(modelVlanWan);
		jSpinnerVlanVeip = new javax.swing.JSpinner(modelVlanVeip);
		jSpinnerSlotPON = new javax.swing.JSpinner(this.modelChassiPon);
		jSpinnerPortaPon = new javax.swing.JSpinner(this.modelPortaPon);
		jSpinnerSlotUplink = new javax.swing.JSpinner(this.modelChassiUp);
		jSpinnerPortaUplink = new javax.swing.JSpinner(this.modelPortaUp);
		jSpinnerSlotCpe = new javax.swing.JSpinner(this.modelCpe);

		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jPanel6 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		jLabel17 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		jLabel19 = new javax.swing.JLabel();
		jLabel20 = new javax.swing.JLabel();
		jLabel21 = new javax.swing.JLabel();

		jRadioButtonWanService = new javax.swing.JRadioButton();
		jRadioButtonVeip = new javax.swing.JRadioButton();

		jButtonCriarONUCapability = new javax.swing.JButton();
		jButtonFileChooser = new javax.swing.JButton();
		jButtonEnviar = new javax.swing.JButton();
		jButtonCriar = new javax.swing.JButton();
		jButtonCancel = new javax.swing.JButton();

		setBackground(new java.awt.Color(204, 204, 204));
		setClosable(true);
		setForeground(java.awt.Color.darkGray);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle(this.oltName);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setFrameIcon(oltIcon); //
		setMinimumSize(new java.awt.Dimension(932, 812));
		setPreferredSize(new java.awt.Dimension(932, 812));
		setRequestFocusEnabled(false);

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Dados de acesso da OLT ",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N
		jPanel1.setToolTipText("OLT");
		jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		jLabel1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
		jLabel1.setText("IP OLT");

		jLabel2.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
		jLabel2.setText("Usuario");

		jLabel9.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
		jLabel9.setText("Porta");

		jLabel10.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
		jLabel10.setText("Senha");

		jFormattedTextFieldPortOlt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
				new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#####"))));
		jFormattedTextFieldPortOlt.setToolTipText("porta");
		jFormattedTextFieldPortOlt.setName("porta"); // NOI18N

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel1)
										.addComponent(jLabel9))
								.addGap(12, 12, 12)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jTextFieldIpOlt, javax.swing.GroupLayout.DEFAULT_SIZE, 285,
												Short.MAX_VALUE)
										.addComponent(jFormattedTextFieldPortOlt))
								.addGap(153, 153, 153)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel2))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jTextFieldOltUser)
										.addComponent(jPasswordFieldOltPasswd, javax.swing.GroupLayout.DEFAULT_SIZE,
												285, Short.MAX_VALUE))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel1)
										.addComponent(jLabel2)
										.addComponent(jTextFieldOltUser, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jTextFieldIpOlt, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel9)
										.addComponent(jLabel10)
										.addComponent(jPasswordFieldOltPasswd, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jFormattedTextFieldPortOlt,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(6, 6, 6)));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Formulario do Script ",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N

		jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel3.setText("Phy-ID CPE:");

		jLabel4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel4.setText("Porta PON:");

		jLabel5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel5.setText("Slot Uplink:");

		jTextFieldPhyIdCPE.setText("FHTT12345678");
		jTextFieldPhyIdCPE.setToolTipText("FHTT12345678");
		jTextFieldPhyIdCPE.setName("FHTT12345678"); // NOI18N

		jLabel7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel7.setText("Slot CPE:");

		jLabel6.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel6.setText("Modelo CPE:");

		jTextFieldModeloONU.setText("HG6145F3");
		jTextFieldModeloONU.setToolTipText("FHTT12345678");
		jTextFieldModeloONU.setName("FHTT12345678"); // NOI18N

		jCheckBoxFlagONUCapability.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jCheckBoxFlagONUCapability.setText("Criar ONU Capability");

		jButtonCriarONUCapability.setText("Criar");
		jButtonCriarONUCapability.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCriarONUCapabilityActionPerformed(evt);
			}
		});

		jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		jTabbedPane1.setToolTipText("WanService");

		jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

		jLabel16.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel16.setText("SSID 2.4Ghz:");

		jLabel14.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel14.setText("SSID 5Ghz:");

		jLabel13.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel13.setText("Senha PPPOE:");

		jLabel12.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel12.setText("User PPPOE:");

		jLabel11.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel11.setText("Vlan PPPOE:");

		jLabel18.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel18.setText("Senha 5Ghz:");

		jLabel17.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel17.setText("Senha 2.4Ghz:");

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(
				jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel6Layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(jPanel6Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 100,
												Short.MAX_VALUE)
										.addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel6Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jTextFieldUserPPPOE, javax.swing.GroupLayout.DEFAULT_SIZE, 127,
												Short.MAX_VALUE)
										.addComponent(jTextFieldPassPPPOE)
										.addComponent(jSpinnerVlanPPPOE, javax.swing.GroupLayout.Alignment.TRAILING))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel6Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel17))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel6Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(jTextFieldSSID5, javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jTextFieldSSIDPass2, javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jTextFieldSSID2, javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jTextFieldSSIDPass5, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE))));
		jPanel6Layout.setVerticalGroup(
				jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel6Layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jSpinnerVlanPPPOE, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(7, 7, 7)
								.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jTextFieldUserPPPOE))
								.addGap(6, 6, 6)
								.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jTextFieldPassPPPOE)))
						.addGroup(jPanel6Layout.createSequentialGroup()
								.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jTextFieldSSID2, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jTextFieldSSIDPass2, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(6, 6, 6)
								.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jTextFieldSSID5, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jTextFieldSSIDPass5, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(6, 6, 6)));

		jTabbedPane1.addTab("WanService", jPanel6);

		jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

		jLabel21.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel21.setText("Vlan PPPOE:");

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout.setHorizontalGroup(
				jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 578, Short.MAX_VALUE)
						.addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel7Layout.createSequentialGroup()
										.addGap(169, 169, 169)
										.addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jSpinnerVlanVeip)
										.addGap(170, 170, 170))));
		jPanel7Layout.setVerticalGroup(
				jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 130, Short.MAX_VALUE)
						.addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel7Layout.createSequentialGroup()
										.addGap(53, 53, 53)
										.addGroup(jPanel7Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jSpinnerVlanVeip, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(54, Short.MAX_VALUE))));

		jTabbedPane1.addTab("Veip", jPanel7);

		buttonGroup1.add(jRadioButtonWanService);
		jRadioButtonWanService.setText("WAN-SERVICE");
		jRadioButtonWanService.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		jRadioButtonWanService.setMargin(new java.awt.Insets(0, 0, 0, 0));

		jLabel8.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel8.setText("CPE em:");

		buttonGroup1.add(jRadioButtonVeip);
		jRadioButtonVeip.setSelected(true);
		jRadioButtonVeip.setText("VEIP");
		jRadioButtonVeip.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		jRadioButtonVeip.setMargin(new java.awt.Insets(0, 0, 0, 0));

		jLabel19.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel19.setText("Slot GPON:");

		jLabel20.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
		jLabel20.setText("Porta Uplink:");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addGap(6, 6, 6)
								.addGroup(jPanel2Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 110,
												Short.MAX_VALUE))
								.addGap(30, 30, 30)
								.addGroup(jPanel2Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jSpinnerPortaUplink)
										.addComponent(jTextFieldModeloONU)
										.addComponent(jTextFieldPhyIdCPE, javax.swing.GroupLayout.DEFAULT_SIZE, 116,
												Short.MAX_VALUE)
										.addComponent(jSpinnerSlotPON)
										.addComponent(jSpinnerPortaPon)
										.addComponent(jSpinnerSlotCpe)
										.addComponent(jSpinnerSlotUplink, javax.swing.GroupLayout.Alignment.TRAILING))
								.addGap(6, 6, 6)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel8)
														.addGroup(jPanel2Layout.createSequentialGroup()
																.addGap(12, 12, 12)
																.addComponent(jRadioButtonVeip)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(jRadioButtonWanService)))
												.addGap(212, 212, 212)
												.addComponent(jCheckBoxFlagONUCapability)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jButtonCriarONUCapability)
												.addGap(6, 6, 6))
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(jTabbedPane1)
												.addContainerGap()))));
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
								.addGap(1, 1, 1)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE,
																23, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jTextFieldPhyIdCPE,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(7, 7, 7)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jSpinnerSlotPON,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE,
																23, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(6, 6, 6)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE,
																23, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jSpinnerPortaPon,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE,
																23, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jSpinnerSlotCpe,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(4, 4, 4)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE,
																23, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jTextFieldModeloONU,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE,
																23, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jSpinnerSlotUplink,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addGap(1, 1, 1)
												.addComponent(jLabel8)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jRadioButtonVeip)
														.addComponent(jRadioButtonWanService)
														.addComponent(jButtonCriarONUCapability)
														.addComponent(jCheckBoxFlagONUCapability)))
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE,
																23, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jSpinnerPortaUplink,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addGap(2, 2, 2)));

		jTextFieldPhyIdCPE.getAccessibleContext().setAccessibleName("FHTT12345678");
		jTextFieldPhyIdCPE.getAccessibleContext().setAccessibleParent(jLabel3);
		jTextFieldModeloONU.getAccessibleContext().setAccessibleName("HG6145F3");

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Script pronto",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N
		jPanel3.setToolTipText("OLT");
		jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		jLabel15.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
		jLabel15.setText("Importar Script");

		jButtonFileChooser.setText("File");
		jButtonFileChooser.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonFileChooserActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(
				jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel3Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel15)
								.addGap(80, 80, 80)
								.addComponent(jButtonFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 140,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(
				jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel3Layout.createSequentialGroup()
								.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jButtonFileChooser)
										.addComponent(jLabel15))
								.addGap(0, 0, 0)));

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comandos",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N
		jPanel4.setVerifyInputWhenFocusTarget(false);

		jButtonEnviar.setText("Enviar");
		jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonEnviarActionPerformed(evt);
			}
		});

		jButtonCriar.setText("Criar");
		jButtonCriar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCriarActionPerformed(evt);
			}
		});

		jButtonCancel.setText("Cancel");
		jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCancelActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(
				jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
								.addGap(71, 71, 71)
								.addComponent(jButtonEnviar)
								.addGap(14, 14, 14)
								.addComponent(jButtonCriar)
								.addGap(14, 14, 14)
								.addComponent(jButtonCancel)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(
				jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup()
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jButtonEnviar)
										.addComponent(jButtonCriar)
										.addComponent(jButtonCancel))
								.addGap(0, 0, Short.MAX_VALUE)));

		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Preview",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("JetBrains Mono ExtraBold", 0, 12))); // NOI18N

		jTextAreaPreviewCode.setEditable(false);
		jTextAreaPreviewCode.setColumns(20);
		jTextAreaPreviewCode.setRows(5);
		jScrollPanel.setViewportView(jTextAreaPreviewCode);

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(
				jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jScrollPanel)
								.addGap(12, 12, 12)));
		jPanel5Layout.setVerticalGroup(
				jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 250,
								javax.swing.GroupLayout.PREFERRED_SIZE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 322,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(12, 12, 12)
								.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
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

	private void jButtonEnviarActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonEnviarActionPerformed
		if (this.fileChooserIsSelected) {
			if (this.oltGponFhtt.checkTelnet(jTextFieldIpOlt.getText(), jFormattedTextFieldPortOlt.getText(),
					jTextFieldOltUser.getText(), jPasswordFieldOltPasswd.getPassword(),
					this.errorIcon)) {
				JOptionPane.showMessageDialog(null,
						"Valores validos!", "Sucesso!",
						JOptionPane.ERROR_MESSAGE, this.successIcon);

				final Telnet acessoOlt = new Telnet(jTextFieldIpOlt.getText(),
						Integer.parseInt(jFormattedTextFieldPortOlt.getText()),
						jTextFieldOltUser.getText(),
						new String(jPasswordFieldOltPasswd.getPassword()));
				acessoOlt.oltAccess(this.fileName);
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Nenhum arquivo selecionado ou criado!", "Error",
					JOptionPane.ERROR_MESSAGE, this.errorIcon);
		}
	}// GEN-LAST:event_jButtonEnviarActionPerformed

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

	private void jButtonCancelActionPerformed(final java.awt.event.ActionEvent evt) {
		dispose();
		capa.dispose();
	}

	private void jButtonCriarONUCapabilityActionPerformed(final java.awt.event.ActionEvent evt) {

		final JPanel glassPane = (JPanel) this.getRootPane().getGlassPane();
		glassPane.setVisible(true);
		glassPane.setOpaque(false);

		glassPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				playAlertSound();
			}
		});
		glassPane.addMouseListener(new java.awt.event.MouseAdapter() {
		});

		capa.setListener(this);
		capa.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(final java.awt.event.WindowEvent e) {
				glassPane.setVisible(false); // Desbloqueia a interação
			}
		});
		capa.setVisible(true);
	}

	private void playAlertSound() {
		try {
			try (AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(getClass().getResource("/sounds/error1.wav"))) {
				final Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			}
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
}
