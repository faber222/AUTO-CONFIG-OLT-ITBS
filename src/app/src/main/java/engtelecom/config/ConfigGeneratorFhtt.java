package engtelecom.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import engtelecom.product.OltGponFhtt;
import engtelecom.scripts.ScriptsAN5k;
import engtelecom.scripts.ScriptsAN6k;

public class ConfigGeneratorFhtt {
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

    private final OltGponFhtt objFhtt;

    public ConfigGeneratorFhtt(final OltGponFhtt olt) {
        this.objFhtt = olt;
        this.script = new File(objFhtt.getNomeArq());
    }

    public List<String> getPonAuth() {
        return ponAuth;
    }

    public void setPonAuth(final List<String> ponAuth) {
        this.ponAuth = ponAuth;
    }

    public List<String> getCapabilityProfiles() {
        return capabilityProfiles;
    }

    public void setCapabilityProfiles(final List<String> capabilityProfiles) {
        this.capabilityProfiles = capabilityProfiles;
    }

    public List<String> getCpeAuth() {
        return cpeAuth;
    }

    public void setCpeAuth(final List<String> cpeAuth) {
        this.cpeAuth = cpeAuth;
    }

    public List<String> getProfileServMode() {
        return profileServMode;
    }

    public void setProfileServMode(final List<String> profileServMode) {
        this.profileServMode = profileServMode;
    }

    public List<String> getVeipCommands() {
        return veipCommands;
    }

    public void setVeipCommands(final List<String> veipCommands) {
        this.veipCommands = veipCommands;
    }

    public List<String> getPppoeCommands() {
        return pppoeCommands;
    }

    public void setPppoeCommands(final List<String> pppoeCommands) {
        this.pppoeCommands = pppoeCommands;
    }

    public List<String> getUplinkVlanConfigs() {
        return uplinkVlanConfigs;
    }

    public void setUplinkVlanConfigs(final List<String> uplinkVlanConfigs) {
        this.uplinkVlanConfigs = uplinkVlanConfigs;
    }

    public List<String> getWifi2Commands() {
        return wifi2Commands;
    }

    public void setWifi2Commands(final List<String> wifi2Commands) {
        this.wifi2Commands = wifi2Commands;
    }

    public List<String> getWifi5Commands() {
        return wifi5Commands;
    }

    public void setWifi5Commands(final List<String> wifi5Commands) {
        this.wifi5Commands = wifi5Commands;
    }

    public boolean writeScript() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.script))) {
            if (this.ponAuth != null) {
                for (final String lines : this.ponAuth) {
                    writer.write(lines);
                    writer.newLine();
                }
                writer.newLine();
            }

            if (this.capabilityProfiles != null) {
                for (final String lines : this.capabilityProfiles) {
                    writer.write(lines);
                    writer.newLine();
                }
                writer.newLine();
            }

            // for (final String lines : this.capaD2) {
            // writer.write(lines);
            // writer.newLine();
            // }
            // writer.newLine();

            // for (final String lines : this.capaF) {
            // writer.write(lines);
            // writer.newLine();
            // }
            // writer.newLine();

            // for (final String lines : this.capaF3) {
            // writer.write(lines);
            // writer.newLine();
            // }
            // writer.newLine();

            for (final String lines : this.cpeAuth) {
                writer.write(lines);
                writer.newLine();
            }
            writer.newLine();

            if (this.profileServMode != null) {
                for (final String lines : this.profileServMode) {
                    writer.write(lines);
                    writer.newLine();
                }
                writer.newLine();
            }

            for (final String lines : this.uplinkVlanConfigs) {
                writer.write(lines);
                writer.newLine();
            }
            writer.newLine();

            if (this.veipCommands != null) {
                for (final String lines : this.veipCommands) {
                    writer.write(lines);
                    writer.newLine();
                }
                writer.newLine();
            }

            if (this.pppoeCommands != null) {
                for (final String lines : this.pppoeCommands) {
                    writer.write(lines);
                    writer.newLine();
                }
                writer.newLine();
            }

            if (this.wifi2Commands != null | this.wifi5Commands != null) {
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
            }

            return true;
        } catch (final IOException e) {
            return false;
        }
    }

    public boolean createScript() {
        if (("AN5000").matches(objFhtt.getOltType())) {
            final ScriptsAN5k scriptsOltAn5k = new ScriptsAN5k();
            String vlan = objFhtt.getVlanVeip();
            setPonAuth(scriptsOltAn5k.setPonAuth(objFhtt.getSlotPon(), objFhtt.getSlotPortaPon()));
            // this.capaD2 = scriptsOltAn5k.comandoOnuCapaD2();
            // this.capaF = scriptsOltAn5k.comandoOnuCapaF();
            // this.capaF3 = scriptsOltAn5k.comandoOnuCapaF3();

            setCapabilityProfiles(null);
            if (objFhtt.isOnuCapability()) {
                setCapabilityProfiles(scriptsOltAn5k.comandoOnuCapa(objFhtt.getCapaProfileName(),
                        objFhtt.getCapaPonType(), objFhtt.getCapaCpeType(), objFhtt.getCapaOneGPortNumber(),
                        objFhtt.getCapaTenGPortNumber(), objFhtt.getCapaPotsNumber(),
                        objFhtt.getCapaWifiNumber(), objFhtt.getCapaUsbNumber(), objFhtt.getCapaEquipamentId()));
                // this.capaD2 = null;
                // this.capaF = null;
                // this.capaF3 = null;
            }

            setCpeAuth(scriptsOltAn5k.provisionaCPE(objFhtt.getPhyIdCpe(), objFhtt.getSlotPon(),
                    objFhtt.getSlotPortaPon(), objFhtt.getSlotCpe(), objFhtt.getCpeCapaProfile()));

            setProfileServMode(scriptsOltAn5k.configProfileServMode());

            setVeipCommands(scriptsOltAn5k.configVeip(objFhtt.getSlotPon(), objFhtt.getSlotPortaPon(),
                    objFhtt.getSlotCpe(), objFhtt.getVlanVeip()));

            setPppoeCommands(null);
            setWifi2Commands(null);
            setWifi5Commands(null);

            if (objFhtt.isWanService()) {
                String wifiStandard2;
                String wifiStandard5;
                setPppoeCommands(scriptsOltAn5k.comandoPpoe(objFhtt.getSlotPon(), objFhtt.getSlotPortaPon(),
                        objFhtt.getSlotCpe(), objFhtt.getVlanPppoe(), objFhtt.getUserPppoe(), objFhtt.getSenhaPppoe()));
                vlan = objFhtt.getVlanPppoe();
                setVeipCommands(null);
                setProfileServMode(null);

                switch (objFhtt.getCpeCapaProfile()) {
                    case "HG6145D2":
                        wifiStandard2 = "802.11bgn";
                        wifiStandard5 = "802.11ac";
                        break;
                    case "HG6145F":
                        wifiStandard2 = "802.11b/g/n/ax";
                        wifiStandard5 = "802.11ac";
                        break;

                    default:
                        wifiStandard2 = "802.11b/g/n/ax";
                        wifiStandard5 = "802.11a/n/ac/ax";
                        break;
                }

                setWifi2Commands(scriptsOltAn5k.comandoWifi2(objFhtt.getSlotPon(), objFhtt.getSlotPortaPon(),
                        objFhtt.getSlotCpe(), objFhtt.getSsid2(), objFhtt.getSsidPass2(), wifiStandard2));

                setWifi5Commands(scriptsOltAn5k.comandoWifi5(objFhtt.getSlotPon(), objFhtt.getSlotPortaPon(),
                        objFhtt.getSlotCpe(), objFhtt.getSsid5(), objFhtt.getSsidPass5(), wifiStandard5));
            }
            setUplinkVlanConfigs(scriptsOltAn5k.addVlanToUplink(vlan, objFhtt.getSlotUplink(),
                    objFhtt.getSlotPortaUplink()));
        } else {
            final ScriptsAN6k ScriptsAN6k = new ScriptsAN6k();
            String vlan = objFhtt.getVlanVeip();
            setPonAuth(null);
            // this.capaD2 = ScriptsAN6k.comandoOnuCapaD2();
            // this.capaF = ScriptsAN6k.comandoOnuCapaF();
            // this.capaF3 = ScriptsAN6k.comandoOnuCapaF3();
            setCapabilityProfiles(null);

            if (objFhtt.isOnuCapability()) {
                setCapabilityProfiles(ScriptsAN6k.comandoOnuCapa(objFhtt.getCapaProfileName(),
                        objFhtt.getCapaPonType(), objFhtt.getCapaCpeType(), objFhtt.getCapaOneGPortNumber(),
                        objFhtt.getCapaTenGPortNumber(), objFhtt.getCapaPotsNumber(),
                        objFhtt.getCapaWifiNumber(), objFhtt.getCapaUsbNumber(), objFhtt.getCapaEquipamentId()));
                // this.capaD2 = null;
                // this.capaF = null;
                // this.capaF3 = null;
            }

            setCpeAuth(ScriptsAN6k.provisionaCPE(objFhtt.getPhyIdCpe(), objFhtt.getSlotPon(),
                    objFhtt.getSlotPortaPon(), objFhtt.getSlotCpe(), objFhtt.getCpeCapaProfile()));

            setProfileServMode(ScriptsAN6k.configProfileServMode());

            setVeipCommands(ScriptsAN6k.configVeip(objFhtt.getSlotPon(), objFhtt.getSlotPortaPon(),
                    objFhtt.getSlotCpe(), objFhtt.getVlanVeip()));

            setPppoeCommands(null);
            setWifi2Commands(null);
            setWifi5Commands(null);

            if (objFhtt.isWanService()) {
                String wifiStandard2;
                String wifiStandard5;
                setPppoeCommands(ScriptsAN6k.comandoPpoe(objFhtt.getSlotPon(), objFhtt.getSlotPortaPon(),
                        objFhtt.getSlotCpe(), objFhtt.getVlanPppoe(), objFhtt.getUserPppoe(), objFhtt.getSenhaPppoe()));
                vlan = objFhtt.getVlanPppoe();
                setVeipCommands(null);
                setProfileServMode(null);

                switch (objFhtt.getCpeCapaProfile()) {
                    case "HG6145D2":
                        wifiStandard2 = "802.11bgn";
                        wifiStandard5 = "802.11ac";
                        break;
                    case "HG6145F":
                        wifiStandard2 = "802.11b/g/n/ax";
                        wifiStandard5 = "802.11ac";
                        break;

                    default:
                        wifiStandard2 = "802.11b/g/n/ax";
                        wifiStandard5 = "802.11a/n/ac/ax";
                        break;
                }
                setWifi2Commands(ScriptsAN6k.comandoWifi2(objFhtt.getSlotPon(), objFhtt.getSlotPortaPon(),
                        objFhtt.getSlotCpe(), objFhtt.getSsid2(), objFhtt.getSsidPass2(), wifiStandard2));

                setWifi5Commands(ScriptsAN6k.comandoWifi5(objFhtt.getSlotPon(), objFhtt.getSlotPortaPon(),
                        objFhtt.getSlotCpe(), objFhtt.getSsid5(), objFhtt.getSsidPass5(), wifiStandard5));
            }
            setUplinkVlanConfigs(ScriptsAN6k.addVlanToUplink(vlan, objFhtt.getSlotUplink(),
                    objFhtt.getSlotPortaUplink()));
        }
        return writeScript();
    }

}
