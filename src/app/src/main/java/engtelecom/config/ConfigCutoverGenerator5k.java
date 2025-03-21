package engtelecom.config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import engtelecom.analytics.DataAnaliser5k;
import engtelecom.scripts.ScriptsCutoverAN5kto6k;

public class ConfigCutoverGenerator5k {

    private final List<List<String>> configUplinkVlan;
    private final List<List<String>> configWhiteList;
    private final List<List<String>> configBandWidth;
    private final List<List<String>> configWanService;
    private final List<List<String>> configWifi;
    private final List<List<String>> configVeip;
    private final List<List<String>> configEth;
    private final List<List<String>> configNgn;
    private final List<List<String>> configQinQ;
    private final List<List<String>> profileServMode;

    private final String slotChassiGpon;
    private final String slotChassiUplink;
    private final String slotPortaUplink;

    private DataAnaliser5k dataAnaliser5k;

    public ConfigCutoverGenerator5k(final DataAnaliser5k data,
            final String slotChassiGpon, final String slotChassiUplink, final String slotPortaUplink) {
        this.dataAnaliser5k = data;

        this.slotChassiGpon = slotChassiGpon;
        this.slotChassiUplink = slotChassiUplink;
        this.slotPortaUplink = slotPortaUplink;

        this.profileServMode = new ArrayList<>();

        this.configUplinkVlan = new ArrayList<>();
        this.configWhiteList = new ArrayList<>();
        this.configBandWidth = new ArrayList<>();

        this.configWanService = new ArrayList<>();
        this.configWifi = new ArrayList<>();
        this.configVeip = new ArrayList<>();
        this.configEth = new ArrayList<>();

        this.configNgn = new ArrayList<>();
        this.configQinQ = new ArrayList<>();
    }

    public boolean start() {
        return createScript();
    }

    private boolean writeScript() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scriptMigracao.txt"))) {
            if (this.profileServMode != null) {
                for (final List<String> list : this.profileServMode) {
                    for (final String lines : list) {
                        writer.write(lines);
                        writer.newLine();
                    }
                }
            }
            writer.newLine();

            if (this.configUplinkVlan != null) {
                for (final List<String> list : this.configUplinkVlan) {
                    for (final String lines : list) {
                        writer.write(lines);
                        writer.newLine();
                    }
                }
            }

            writer.newLine();

            if (this.configWhiteList != null) {
                for (final List<String> list : this.configWhiteList) {
                    for (final String lines : list) {
                        writer.write(lines);
                        writer.newLine();
                    }
                }
            }

            writer.newLine();

            if (this.configWanService != null) {
                for (final List<String> list : this.configWanService) {
                    for (final String lines : list) {
                        writer.write(lines);
                        writer.newLine();
                    }
                }
                writer.newLine();
            }
            writer.newLine();

            if (this.configWifi != null) {
                for (final List<String> list : this.configWifi) {
                    for (final String lines : list) {
                        writer.write(lines);
                        writer.newLine();
                    }
                }
                writer.newLine();
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
            writer.newLine();

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
        final ScriptsCutoverAN5kto6k scriptsAN6k = new ScriptsCutoverAN5kto6k();

        boolean hasVeip = false;

        // Configurar vlan de uplink
        for (final String[] config : this.dataAnaliser5k.getDataVlanUpFilter().getUplinkVlans()) {
            configUplinkVlan.add(scriptsAN6k.addVlanToUplink(config[0], config[1], config[2], config[3]));
        }

        // Configurar o whitelist
        for (final String[] config : this.dataAnaliser5k.getDataWhitelistFilter().getWhitelist()) {
            configWhiteList.add(scriptsAN6k.provisionaCPE(config[0], config[1], config[2], config[3], config[4]));
        }

        // Configurar o wan-service
        // Configurar o pppoe
        for (final String[] config : this.dataAnaliser5k.getDataWanServiceFilter().getWanConfigs()) {
            if ("r".equals(config[5])) {
                configWanService.add(scriptsAN6k.comandoPpoe(config[0], config[1], config[2], config[6], config[9],
                        config[10], config[3], config[4], config[5], config[7], config[8]));
            }
        }

        // Configurar o wifi com senha

        for (final String[] config : this.dataAnaliser5k.getDataWanServiceWifiFilter().getWifiConfigs()) {
            if ("1".equals(config[3])) {
                // ssid 2.4
                if ("N/A".equals(config[13])) {
                    // sem radius
                    configWifi.add(scriptsAN6k.comandoWifi2(config[0], config[1], config[2],
                            config[6], config[10],
                            "802.11bgn", config[3], config[4],
                            config[5], config[7], config[8], config[9]));
                } else {
                    // com radius
                    // configWifi.add(scriptsAN6k.comandoWifi2(config[], config[], config[],
                    // config[], config[], config[], config[], config[],
                    // config[], config[], config[], config[], config[],
                    // config[], config[]));
                }

            } else {
                // ssid 5.0
                if ("N/A".equals(config[13])) {
                    // sem radius
                    configWifi.add(scriptsAN6k.comandoWifi5(config[0], config[1], config[2],
                            config[6], config[10],
                            "802.11ac", config[3], config[4],
                            config[5], config[7], config[8], config[9]));
                } else {
                    // com radius
                    // configWifi.add(scriptsAN6k.comandoWifi5(config[], config[], config[],
                    // config[], config[], config[], config[], config[],
                    // config[], config[], config[], config[], config[],
                    // config[], config[]));
                }
            }

        }

        // Configurar o veip
        for (final String[] config : this.dataAnaliser5k.getDataVeipFilter().getPrfMgrConfigs()) {
            profileServMode.add(scriptsAN6k.configProfileServMode(config[0], config[1], config[2]));
        }

        for (final String[] config : this.dataAnaliser5k.getDataVeipFilter().getVeipConfigs()) {
            configVeip.add(scriptsAN6k.configVeip(config[0], config[1], config[2], config[5], config[6]));
        }

        // Configurar o portEth
        for (final String[] config : this.dataAnaliser5k.getDataPortEthFilter().getEthConfigs()) {
            configEth.add(scriptsAN6k.configEth(config[0], config[1], config[2],
                    config[3], config[5], config[4], config[6]));
        }

        // TO-DO
        // Precisa analisar o caso de não ser informado o cvlan-id

        // Configurar o wifi com radius

        // Configurar o bridge

        // Configurar o bandwidth

        // Configurar o ngn

        // Configurar o qinq

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
