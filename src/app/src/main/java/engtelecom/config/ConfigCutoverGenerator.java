package engtelecom.config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import engtelecom.scripts.ScriptsAN5kCutover;
import engtelecom.scripts.ScriptsAN6kCutover;

public class ConfigCutoverGenerator {
    private final ArrayList<String> data;
    private List<String> vlansUplink;
    private List<List<String>> ponAuth;
    private List<String> profileServMode;

    private final List<List<String>> configVeip;
    private final List<List<String>> configEth;
    private final List<List<String>> configProv;
    private final List<List<String>> configUplinkVlan;

    private final String slotChassiGpon;
    private final String slotChassiUplink;
    private final String slotPortaUplink;
    private final String oltType;

    public ConfigCutoverGenerator(final ArrayList<String> data, final String oltType,
            final String slotChassiGpon, final String slotChassiUplink, final String slotPortaUplink) {
        // ITBS5f72cb27;0/9/1;58;1005;veip;TRUE;null;1;1005;1005
        this.data = data;
        this.oltType = oltType;
        this.slotChassiGpon = slotChassiGpon;
        this.slotChassiUplink = slotChassiUplink;
        this.slotPortaUplink = slotPortaUplink;
        this.vlansUplink = new ArrayList<>(); // Inicializa a lista vlans;
        this.ponAuth = new ArrayList<>();
        this.profileServMode = new ArrayList<>();
        this.configVeip = new ArrayList<>();
        this.configEth = new ArrayList<>();
        this.configProv = new ArrayList<>();
        this.configUplinkVlan = new ArrayList<>();
    }

    public void setVlans(final List<String> vlans) {
        this.vlansUplink = vlans;
    }

    public boolean start() {
        final HashSet<String> vlansUnicas = new HashSet<>();
        final HashSet<String> newVlanUnica = new HashSet<>();
        // System.out.println(data);
        for (final String lines : this.data) {
            final String[] splitInicial = lines.split(";");
            final String vlan = splitInicial[3]; // 1005
            final String oldVlan = splitInicial[8]; // 1005
            final String newVlan = splitInicial[9]; // 1005

            if (!vlansUnicas.contains(vlan) && !newVlanUnica.contains(vlan)) {
                vlansUnicas.add(vlan);
                vlansUplink.add(vlan);
            }
            if (!vlansUnicas.contains(oldVlan) && !newVlanUnica.contains(oldVlan)) {
                vlansUnicas.add(oldVlan);
                vlansUplink.add(oldVlan);
            }
            if (!newVlanUnica.contains(newVlan) && !vlansUnicas.contains(newVlan)) {
                newVlanUnica.add(newVlan);
                vlansUplink.add(newVlan);
            }
        }
        return createScript();
        // System.out.println(this.data);
    }

    private boolean writeScript() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scriptMigracao.txt"))) {
            if (this.ponAuth != null) {
                for (final List<String> lines : this.ponAuth) {
                    for (final String string : lines) {
                        writer.write(string);
                        writer.newLine();
                    }
                }
                writer.newLine();
            }

            if (this.profileServMode != null) {
                for (final String lines : this.profileServMode) {
                    writer.write(lines);
                    writer.newLine();
                }
                writer.newLine();
            }
            for (final List<String> list : this.configUplinkVlan) {
                for (final String lines : list) {
                    writer.write(lines);
                    writer.newLine();
                }
            }
            writer.newLine();

            for (final List<String> list : this.configProv) {
                for (final String lines : list) {
                    writer.write(lines);
                    writer.newLine();
                }
            }
            writer.newLine();

            if (this.configVeip != null) {
                for (final List<String> list : this.configVeip) {
                    for (final String lines : list) {
                        writer.write(lines);
                        writer.newLine();
                    }
                }
                writer.newLine();
            }

            if (this.configEth != null) {
                for (final List<String> list : this.configEth) {
                    for (final String lines : list) {
                        writer.write(lines);
                        writer.newLine();
                    }
                }
                writer.newLine();
            }
            writer.newLine();

            return true;
        } catch (final IOException e) {
            return false;
        }
    }

    private boolean createScript() {
        final ScriptsAN5kCutover scriptsAN5k = new ScriptsAN5kCutover();
        final ScriptsAN6kCutover scriptsAN6k = new ScriptsAN6kCutover();

        final HashSet<String> cpe = new HashSet<>();
        final Map<String, Integer> gponSnCountMap = new HashMap<>();
        boolean hasVeip = false;

        if (this.oltType.equals("AN6000")) {
            this.ponAuth = null;
            this.profileServMode = null;

            for (final String each : this.vlansUplink) {
                configUplinkVlan.add(scriptsAN6k.addVlanToUplink(each, this.slotChassiUplink, this.slotPortaUplink));
            }

            for (final String lines : this.data) {
                /*
                 * [0] "ITBS5f72cb27",
                 * [1] "0/9/1",
                 * [2] "58", INUTIL
                 * [3] "1005",
                 * [4] "veip",
                 * [5] "TRUE",
                 * [6] "NULL",
                 * [7] "1", INUTIL
                 * [8] "1005",
                 * [9] "1005"
                 */
                final String[] splitInicial = lines.split(";");
                final String gponSn = splitInicial[0]; // ITBS5f72cb27

                final String[] slots = splitInicial[1].split("/"); // 0/9/1
                final String slotPortaPon = slots[1]; // 9
                final String slotCpe = slots[2]; // 1

                final String vlan = splitInicial[3]; // 1005
                final String mode = splitInicial[4]; // veip
                final String tagging = splitInicial[5]; // TRUE
                final String port = splitInicial[6]; // NULL
                final String oldVlan = splitInicial[8]; // 1005

                // Verifica se o gponSn já existe no map de contagem
                int count = gponSnCountMap.getOrDefault(gponSn, 0);
                count++; // Incrementa a contagem para o gponSn atual
                gponSnCountMap.put(gponSn, count); // Atualiza a contagem no mapa

                // Atualiza o valor do index com base na contagem
                final String index = String.valueOf(count);
                String capability = "5506-01-A1";

                if (mode.equals("veip")) {
                    capability = "HG260";
                    hasVeip = true;
                    if (tagging.equals("TRUE")) {
                        configVeip.add(scriptsAN6k.configVeip(this.slotChassiGpon, slotPortaPon, slotCpe, vlan));
                    } else {
                        configVeip.add(
                                scriptsAN6k.configVeip(this.slotChassiGpon, slotPortaPon, slotCpe, oldVlan));
                    }
                } else {
                    final String downStreamVlan = tagging.equals("TRUE") ? "transparent" : "tag";
                    configEth.add(scriptsAN6k.configEth(
                            this.slotChassiGpon, slotPortaPon, slotCpe, port,
                            downStreamVlan, index, vlan));

                }

                if (!cpe.contains(gponSn)) {
                    configProv.add(scriptsAN6k.provisionaCPE(gponSn,
                            capability, this.slotChassiGpon, slotPortaPon, slotCpe));
                    cpe.add(gponSn);
                }
            }

            if (hasVeip) {
                this.profileServMode = scriptsAN6k.configProfileServMode();
            }
        } else {
            this.profileServMode = null;

            for (int i = 1; i <= 16; i++) {
                this.ponAuth.add(scriptsAN5k.setPonAuth(this.slotChassiGpon, i + ""));
            }

            for (final String each : this.vlansUplink) {
                configUplinkVlan.add(scriptsAN5k.addVlanToUplink(each, this.slotChassiUplink, this.slotPortaUplink));
            }

            for (final String lines : this.data) {
                final String[] splitInicial = lines.split(";");
                final String gponSn = splitInicial[0]; // ITBS5f72cb27

                final String[] slots = splitInicial[1].split("/"); // 0/9/1e
                final String slotPortaPon = slots[1]; // 9
                final String slotCpe = slots[2]; // 1

                final String vlan = splitInicial[3]; // 1005
                final String mode = splitInicial[4]; // veip
                final String tagging = splitInicial[5]; // TRUE
                final String port = splitInicial[6]; // NULL
                final String oldVlan = splitInicial[8]; // 1005

                // Verifica se o gponSn já existe no map de contagem
                int count = gponSnCountMap.getOrDefault(gponSn, 0);
                count++; // Incrementa a contagem para o gponSn atual
                gponSnCountMap.put(gponSn, count); // Atualiza a contagem no mapa

                // Atualiza o valor do index com base na contagem
                final String index = String.valueOf(count);
                String capability = "5506-01-A1";

                if (mode.equals("veip")) {
                    hasVeip = true;
                    capability = "HG260";
                    if (tagging.equals("TRUE")) {
                        configVeip.add(scriptsAN5k.configVeip(this.slotChassiGpon, slotPortaPon, slotCpe, index, vlan));
                    } else {
                        configVeip
                                .add(scriptsAN5k.configVeip(this.slotChassiGpon, slotPortaPon, slotCpe, index,
                                        oldVlan));
                    }
                } else {
                    final String downStreamVlan = tagging.equals("TRUE") ? "transparent" : "tag";
                    configEth.add(scriptsAN5k.configEth(
                            this.slotChassiGpon, slotPortaPon, slotCpe, port,
                            downStreamVlan, index, vlan));

                }

                if (!cpe.contains(gponSn)) {
                    configProv.add(scriptsAN5k.provisionaCPE(gponSn, this.slotChassiGpon, slotPortaPon, slotCpe,
                            capability));

                    cpe.add(gponSn);
                }
            }
            if (hasVeip) {
                this.profileServMode = scriptsAN5k.configProfileServMode();
            }
        }
        if (writeScript()) {
            System.out.println("Script criado com sucesso!");
            JOptionPane.showMessageDialog(null,
                    "Script de migração criado com sucesso!.", null,
                    JOptionPane.WARNING_MESSAGE, null);
            return true;
        } else {
            System.out.println("Script não foi criado, ocorreu algum erro!");
            JOptionPane.showMessageDialog(null,
                    "Script de migração não foi criado!.", null,
                    JOptionPane.ERROR_MESSAGE, null);
            return false;
        }
    }
}
