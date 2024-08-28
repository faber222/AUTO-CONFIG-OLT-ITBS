package engtelecom.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import engtelecom.product.OltGponAN5k;
import engtelecom.scripts.ScriptsAN5k;

public class ConfigGeneratorAN5k {
    private List<String> ponAuth;
    private List<String> capabilityProfiles;
    private List<String> capaD2;
    private List<String> capaF;
    private List<String> capaF3;
    private List<String> cpeAuth;
    private List<String> profileServMode;
    private List<String> veipCommands;
    private List<String> pppoeCommands;
    private List<String> uplinkVlanConfigs;
    private List<String> wifi2Commands;
    private List<String> wifi5Commands;
    private final File script;

    private final OltGponAN5k objAN5k;

    public ConfigGeneratorAN5k(final OltGponAN5k olt) {
        this.objAN5k = olt;
        this.script = new File(objAN5k.getNomeArq());
    }

    public boolean writeScript() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.script))) {
            for (final String lines : this.ponAuth) {
                writer.write(lines);
                writer.newLine();
            }
            writer.newLine();

            for (final String lines : this.capabilityProfiles) {
                writer.write(lines);
                writer.newLine();
            }
            writer.newLine();

            // for (final String lines : this.capaD2) {
            //     writer.write(lines);
            //     writer.newLine();
            // }
            // writer.newLine();

            // for (final String lines : this.capaF) {
            //     writer.write(lines);
            //     writer.newLine();
            // }
            // writer.newLine();

            // for (final String lines : this.capaF3) {
            //     writer.write(lines);
            //     writer.newLine();
            // }
            // writer.newLine();

            for (final String lines : this.cpeAuth) {
                writer.write(lines);
                writer.newLine();
            }
            writer.newLine();

            for (final String lines : this.profileServMode) {
                writer.write(lines);
                writer.newLine();
            }
            writer.newLine();

            for (final String lines : this.veipCommands) {
                writer.write(lines);
                writer.newLine();
            }
            writer.newLine();

            for (final String lines : this.pppoeCommands) {
                writer.write(lines);
                writer.newLine();
            }
            writer.newLine();

            for (final String lines : this.uplinkVlanConfigs) {
                writer.write(lines);
                writer.newLine();
            }
            writer.newLine();

            for (final String lines : this.wifi2Commands) {
                writer.write(lines);
                writer.newLine();
            }
            writer.newLine();

            for (final String lines : this.wifi5Commands) {
                writer.write(lines);
                writer.newLine();
            }
            writer.newLine();

            return true;
        } catch (final IOException e) {
            return false;
        }
    }

    public boolean createScript() {
        final ScriptsAN5k scriptsOltAn5k = new ScriptsAN5k();
        String vlan = objAN5k.getVlanVeip();
        this.ponAuth = scriptsOltAn5k.setPonAuth(objAN5k.getSlotPon(), objAN5k.getSlotPortaPon());
        // this.capaD2 = scriptsOltAn5k.comandoOnuCapaD2();
        // this.capaF = scriptsOltAn5k.comandoOnuCapaF();
        // this.capaF3 = scriptsOltAn5k.comandoOnuCapaF3();
        this.capabilityProfiles = new ArrayList<>();

        if (objAN5k.isOnuCapability()) {
            this.capabilityProfiles = scriptsOltAn5k.comandoOnuCapa(objAN5k.getCapaProfileName(),
                    objAN5k.getCapaPonType(), objAN5k.getCapaCpeType(), objAN5k.getCapaOneGPortNumber(),
                    objAN5k.getCapaTenGPortNumber(), objAN5k.getCapaPotsNumber(),
                    objAN5k.getCapaWifiNumber(), objAN5k.getCapaUsbNumber(), objAN5k.getCapaEquipamentId());
            this.capaD2 = new ArrayList<>();
            this.capaF = new ArrayList<>();
            this.capaF3 = new ArrayList<>();
        }

        this.cpeAuth = scriptsOltAn5k.provisionaCPE(objAN5k.getPhyIdCpe(), objAN5k.getSlotPon(),
                objAN5k.getSlotPortaPon(), objAN5k.getSlotCpe(), objAN5k.getCpeCapaProfile());

        this.profileServMode = scriptsOltAn5k.configProfileServMode();

        this.veipCommands = scriptsOltAn5k.configVeip(objAN5k.getSlotPon(), objAN5k.getSlotPortaPon(),
                objAN5k.getSlotCpe(), objAN5k.getVlanVeip());

        this.pppoeCommands = new ArrayList<>();
        this.wifi2Commands = new ArrayList<>();
        this.wifi5Commands = new ArrayList<>();

        if (objAN5k.isWanService()) {
            String wifiStandard2;
            String wifiStandard5;
            this.pppoeCommands = scriptsOltAn5k.comandoPpoe(objAN5k.getSlotPon(), objAN5k.getSlotPortaPon(),
                    objAN5k.getSlotCpe(), objAN5k.getVlanPppoe(), objAN5k.getUserPppoe(), objAN5k.getSenhaPppoe());
            vlan = objAN5k.getVlanPppoe();
            this.veipCommands = new ArrayList<>();
            this.profileServMode = new ArrayList<>();

            switch (objAN5k.getCpeCapaProfile()) {
                case "HG6145D2" -> {
                    wifiStandard2 = "802.11bgn";
                    wifiStandard5 = "802.11ac";
                }
                case "HG6145F" -> {
                    wifiStandard2 = "801.11ax";
                    wifiStandard5 = "802.11ac";
                }
                default -> {
                    wifiStandard2 = "801.11ax";
                    wifiStandard5 = "801.11ax";
                }
            }

            this.wifi2Commands = scriptsOltAn5k.comandoWifi2(objAN5k.getSlotPon(), objAN5k.getSlotPortaPon(),
                    objAN5k.getSlotCpe(), objAN5k.getSsid2(), objAN5k.getSsidPass2(), wifiStandard2);

            this.wifi5Commands = scriptsOltAn5k.comandoWifi5(objAN5k.getSlotPon(), objAN5k.getSlotPortaPon(),
                    objAN5k.getSlotCpe(), objAN5k.getSsid5(), objAN5k.getSsidPass5(), wifiStandard5);
        }
        this.uplinkVlanConfigs = scriptsOltAn5k.addVlanToUplink(vlan, objAN5k.getSlotUplink(),
                objAN5k.getSlotPortaUplink());
        return writeScript();
    }

}
