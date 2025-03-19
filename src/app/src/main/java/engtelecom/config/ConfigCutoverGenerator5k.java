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
    // private List<String> vlansUplink;

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

        // this.vlansUplink = new ArrayList<>(); // Inicializa a lista vlans;
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

    // public void setVlans(final List<String> vlans) {
    // this.vlansUplink = vlans;
    // }

    public boolean start() {
        // for (final String[] config :
        // this.dataAnaliser5k.getDataBandwidthFilter().getBandwidthConfigs()) {
        // System.out.println(Arrays.toString(config));
        // }

        return createScript();
        // return true;
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
        final ScriptsCutoverAN5kto6k scriptsAN6k = new ScriptsCutoverAN5kto6k();

        boolean hasVeip = false;

        // Configurar vlan de uplink
        for (final String[] config : this.dataAnaliser5k.getDataVlanUpFilter().getUplinkVlans()) {
            configUplinkVlan.add(scriptsAN6k.addVlanToUplink(config[0], config[1], config[2], config[3]));
        }

        // TO-DO
        // Configurar o whitelist

        // Configurar o bandwidth

        // Configurar o wan-service

        // Configurar o pppoe

        // Configurar o wifi com senha

        // Configurar o wifi com radius

        // Configurar o veip

        // Configurar o portEth

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
