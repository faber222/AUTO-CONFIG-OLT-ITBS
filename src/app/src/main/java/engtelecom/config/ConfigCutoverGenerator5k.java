package engtelecom.config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import engtelecom.analytics.DataAnaliser5k;
import engtelecom.scripts.ScriptsCutoverAN5kto6k;
import engtelecom.swingType.cutoverFhtt.Olt5kCutoverTo6k;

public class ConfigCutoverGenerator5k {

    private final List<List<String>> configUplinkVlan;
    private final List<List<String>> configWhiteList;
    private final List<List<String>> configBandWidth;
    private final List<List<String>> configWanService;
    private final List<List<String>> configWifi;
    private final List<List<String>> configVeip;
    private final List<List<String>> configEth;
    @SuppressWarnings("unused")
    private final List<List<String>> configNgn;
    @SuppressWarnings("unused")
    private final List<List<String>> configQinQ;
    private final List<List<String>> profileServMode;

    private final DataAnaliser5k dataAnaliser5k;

    private List<String[]> onuOrigemSelecionadaOnuTable;
    private List<String[]> ponOrigemSelecionadaPonTable;
    private List<String[]> slotOrigemSelecionadaSlotTable;
    private List<String[]> uplinkDestinoSelecionado;
    private List<String[]> gponDestinoSelecionado;

    private final Olt5kCutoverTo6k olt5kCutoverTo6k;

    public ConfigCutoverGenerator5k(final DataAnaliser5k data,
            final Olt5kCutoverTo6k olt5kCutoverTo6k) {
        this.olt5kCutoverTo6k = olt5kCutoverTo6k;
        this.dataAnaliser5k = data;

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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scriptMigracao5kto6k.txt"))) {
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

            if (this.configBandWidth != null) {
                for (final List<String> list : this.configBandWidth) {
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

        // Configurar vlan de uplink
        for (final String[] uplink : this.olt5kCutoverTo6k.getUplinkDestinoSelecionado()) {
            for (final String[] config : this.dataAnaliser5k.getDataVlanUpFilter().getUplinkVlans()) {
                configUplinkVlan.add(scriptsAN6k.addVlanToUplink(uplink[0], uplink[1], config[2], config[3]));
            }
        }

        List<String[]> whiteListFiltrado = null;
        List<String[]> wanServicePPPFiltrado = null;
        List<String[]> wanServiceStaticFiltrado = null;
        List<String[]> wanServiceBridgeFiltrado = null;
        List<String[]> wifiFiltrado = null;
        List<String[]> veipCfgFiltrado = null;
        List<String[]> portEthFiltrado = null;
        List<String[]> bandWidthFiltrado = null;

        if (olt5kCutoverTo6k.isSlotSelect()) {
            // whiteList
            whiteListFiltrado = filtrarEMapearPorSlot(this.dataAnaliser5k.getDataWhitelistFilter().getWhitelist(), 1);

            // wanService pppoe
            wanServicePPPFiltrado = filtrarEMapearPorSlot(
                    this.dataAnaliser5k.getDataWanServiceFilter().getWanRouterConfigs(), 0);

            // wanService static
            wanServiceStaticFiltrado = filtrarEMapearPorSlot(
                    this.dataAnaliser5k.getDataWanServiceFilter().getWanStaticConfigs(), 0);

            // wanService bridge
            wanServiceBridgeFiltrado = filtrarEMapearPorSlot(
                    this.dataAnaliser5k.getDataWanServiceFilter().getWanBridgeConfigs(), 0);

            // wifi
            wifiFiltrado = filtrarEMapearPorSlot(this.dataAnaliser5k.getDataWanServiceWifiFilter().getWifiConfigs(), 0);

            // veip
            veipCfgFiltrado = filtrarEMapearPorSlot(this.dataAnaliser5k.getDataVeipFilter().getVeipConfigs(), 0);

            // portEth
            portEthFiltrado = filtrarEMapearPorSlot(this.dataAnaliser5k.getDataPortEthFilter().getEthConfigs(), 0);

            // bandWidth
            bandWidthFiltrado = filtrarEMapearPorSlot(
                    this.dataAnaliser5k.getDataBandwidthFilter().getBandwidthConfigs(), 0);

        } else if (olt5kCutoverTo6k.isPonSelect()) {
            // whiteList
            whiteListFiltrado = filtrarEMapearPorPon(this.dataAnaliser5k.getDataWhitelistFilter().getWhitelist(), 1, 2);

            // wanService pppoe
            wanServicePPPFiltrado = filtrarEMapearPorPon(
                    this.dataAnaliser5k.getDataWanServiceFilter().getWanRouterConfigs(), 0, 1);

            // wanService static
            wanServiceStaticFiltrado = filtrarEMapearPorPon(
                    this.dataAnaliser5k.getDataWanServiceFilter().getWanStaticConfigs(), 0, 1);

            // wanService bridge
            wanServiceBridgeFiltrado = filtrarEMapearPorPon(
                    this.dataAnaliser5k.getDataWanServiceFilter().getWanBridgeConfigs(), 0, 1);

            // wifi
            wifiFiltrado = filtrarEMapearPorPon(this.dataAnaliser5k.getDataWanServiceWifiFilter().getWifiConfigs(), 0,
                    1);

            // veip
            veipCfgFiltrado = filtrarEMapearPorPon(this.dataAnaliser5k.getDataVeipFilter().getVeipConfigs(), 0, 1);

            // portEth
            portEthFiltrado = filtrarEMapearPorPon(this.dataAnaliser5k.getDataPortEthFilter().getEthConfigs(), 0, 1);

            // bandWidth
            bandWidthFiltrado = filtrarEMapearPorPon(
                    this.dataAnaliser5k.getDataBandwidthFilter().getBandwidthConfigs(), 0, 1);

        } else if (olt5kCutoverTo6k.isOnuSelect()) {
            // whiteList
            whiteListFiltrado = filtrarEMapearPorOnu(this.dataAnaliser5k.getDataWhitelistFilter().getWhitelist(), 1, 2,
                    3);

            // wanService pppoe
            wanServicePPPFiltrado = filtrarEMapearPorOnu(
                    this.dataAnaliser5k.getDataWanServiceFilter().getWanRouterConfigs(), 0, 1, 2);

            // wanService static
            wanServiceStaticFiltrado = filtrarEMapearPorOnu(
                    this.dataAnaliser5k.getDataWanServiceFilter().getWanStaticConfigs(), 0, 1, 2);

            // wanService bridge
            wanServiceBridgeFiltrado = filtrarEMapearPorOnu(
                    this.dataAnaliser5k.getDataWanServiceFilter().getWanBridgeConfigs(), 0, 1, 2);

            // wifi
            wifiFiltrado = filtrarEMapearPorOnu(this.dataAnaliser5k.getDataWanServiceWifiFilter().getWifiConfigs(), 0,
                    1, 2);

            // veip
            veipCfgFiltrado = filtrarEMapearPorOnu(this.dataAnaliser5k.getDataVeipFilter().getVeipConfigs(), 0, 1, 2);

            // portEth
            portEthFiltrado = filtrarEMapearPorOnu(this.dataAnaliser5k.getDataPortEthFilter().getEthConfigs(), 0, 1, 2);

            // bandWidth
            bandWidthFiltrado = filtrarEMapearPorOnu(
                    this.dataAnaliser5k.getDataBandwidthFilter().getBandwidthConfigs(), 0, 1, 2);

        } else {
            throw new IllegalStateException("Nenhum tipo de seleção (slot, PON, ONU) foi ativado.");
        }

        if (whiteListFiltrado != null) {
            for (final String[] config : whiteListFiltrado) {
                configWhiteList.add(scriptsAN6k.provisionaCPE(config[0], config[1], config[2], config[3], config[4]));
            }
        }

        // Configurar o wan-service
        // Configurar o pppoe
        if (wanServicePPPFiltrado != null) {
            for (final String[] config : wanServicePPPFiltrado) {
                configWanService.add(scriptsAN6k.comandoPpoe(config[0], config[1], config[2], config[6], config[9],
                        config[10], config[3], config[4], config[5], config[7], config[8]));
            }
        }

        // Configurar o router static
        // FALTA FINALIZAR
        // if (wanServiceStaticFiltrado != null) {
        //     for (final String[] config : wanServiceStaticFiltrado) {
        //         configWanService.add(scriptsAN6k.comandoStatic(config[0], config[1], config[2], config[6], config[9],
        //                 config[10], config[3], config[4], config[5], config[7], config[8]));
        //     }
        // }

        // Configurar o bridge
        if (wanServiceBridgeFiltrado != null) {
            for (final String[] config : wanServiceBridgeFiltrado) {
                configWanService.add(scriptsAN6k.comandoBridge(config[0], config[1], config[2], config[6], config[3],
                        config[4], config[5], config[7], config[8], config[9], config[10]));
            }
        }

        // Configurar o wifi com senha
        // Configurar o wifi com radius

        if (wifiFiltrado != null) {
            for (final String[] config : wifiFiltrado) {
                if ("1".equals(config[3])) {
                    // ssid 2.4
                    if ("N/A".equals(config[13])) {

                        // sem radius
                        configWifi.add(scriptsAN6k.comandoWifi2(config[0], config[1], config[2],
                                config[6].replaceAll("[\\p{Cntrl}&&[^\\n\\t]]",
                                        "-"),
                                config[10],
                                "802.11bgn", config[3], config[4],
                                config[5], config[7], config[8].replace("_", "-"), config[9]));
                    } else {
                        // com radius
                        configWifi.add(scriptsAN6k.comandoWifi2(config[0], config[1], config[2],
                                config[6].replaceAll("[\\p{Cntrl}&&[^\\n\\t]]",
                                        "-"),
                                config[10], "802.11bgn", config[3], config[4],
                                config[5], config[7], config[8].replace("_", "-"), config[9], config[11],
                                config[12], config[13]));
                    }
                } else {
                    // ssid 5.0
                    if ("N/A".equals(config[13])) {
                        // sem radius
                        configWifi.add(scriptsAN6k.comandoWifi5(config[0], config[1], config[2],
                                config[6].replaceAll("[\\p{Cntrl}&&[^\\n\\t]]",
                                        "-"),
                                config[10],
                                "802.11ac", config[3], config[4],
                                config[5], config[7], config[8].replace("_", "-"), config[9]));
                    } else {
                        // com radius
                        configWifi.add(scriptsAN6k.comandoWifi5(config[0], config[1], config[2],
                                config[6].replaceAll("[\\p{Cntrl}&&[^\\n\\t]]",
                                        "-"),
                                config[10], "802.11ac", config[3], config[4],
                                config[5], config[7], config[8].replace("_", "-"), config[9], config[11],
                                config[12], config[13]));
                    }
                }
            }
        }

        // Configurar o veip profile
        for (final String[] config : this.dataAnaliser5k.getDataVeipFilter().getPrfMgrConfigs()) {
            profileServMode.add(scriptsAN6k.configProfileServMode(config[0], config[1], config[2]));
        }

        // Configurar o veip
        if (veipCfgFiltrado != null) {
            for (final String[] config : veipCfgFiltrado) {
                configVeip.add(scriptsAN6k.configVeip(config[0], config[1], config[2], config[5], config[6]));
            }
        }

        // Configurar o portEth
        if (portEthFiltrado != null) {
            for (final String[] config : portEthFiltrado) {
                configEth.add(scriptsAN6k.configEth(config[0], config[1], config[2],
                        config[3], config[5], config[4], config[6]));
            }
        }

        // Configurar o bandwidth
        if (bandWidthFiltrado != null) {
            for (final String[] config : bandWidthFiltrado) {
                final String fixbw = String.valueOf(Math.max(Integer.parseInt(config[4]), 64));
                final String asbw = String.valueOf(Math.max(Integer.parseInt(config[5]), 64));
                final String maxbw = String.valueOf(Math.max(Integer.parseInt(config[6]), 512));

                configBandWidth.add(scriptsAN6k.configBandwidth(config[0], config[1], config[2], config[3],
                        fixbw, asbw, maxbw));
            }
        }

        // TO-DO
        // Precisa analisar o caso de não ser informado o cvlan-id

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

    /**
     * Filtra os dados do List<String[]> baseado no valor de escolha do slot
     * 
     * @param list  List<String[]> contendo os comandos para criação
     * @param index Index do valor do slot
     * @return Retorna a List<String[]> inicial porém somente com os valores do
     *         index escolhido!
     */
    public List<String[]> filtrarEMapearPorSlot(final List<String[]> list, final int index) {
        final List<String[]> origemSlots = olt5kCutoverTo6k.getSlotOrigemSelecionadaSlotTable();
        final List<String[]> destinoSlots = olt5kCutoverTo6k.getGponDestinoSelecionado();

        final Map<String, String> mapeamentoSlot = new HashMap<>();
        for (int i = 0; i < origemSlots.size(); i++) {
            final String slotOrigem = origemSlots.get(i)[0];
            final String slotDestino = destinoSlots.get(i)[0];
            mapeamentoSlot.put(slotOrigem, slotDestino);
        }

        final List<String[]> resultado = new ArrayList<>();

        for (final String[] entrada : list) {
            final String slotAtual = entrada[index];

            if (mapeamentoSlot.containsKey(slotAtual)) {
                final String[] novaEntrada = entrada.clone();
                novaEntrada[index] = mapeamentoSlot.get(slotAtual);
                resultado.add(novaEntrada);
            }
        }

        return resultado;
    }

    /**
     * Filtra os dados do List<String[]> baseado no valor de escolha slot e pon
     * 
     * @param list      List<String[]> contendo os comandos para criação
     * @param indexSlot Index do valor do slot
     * @param indexPon  Index do valor da pon
     * @return Retorna a List<String[]> inicial porém somente com os valores do
     *         index escolhido!
     */
    public List<String[]> filtrarEMapearPorPon(final List<String[]> list, final int indexSlot,
            final int indexPon) {
        final List<String[]> origem = olt5kCutoverTo6k.getPonOrigemSelecionadaPonTable();
        final List<String[]> destino = olt5kCutoverTo6k.getGponDestinoSelecionado();

        final Map<String, String> mapaPonOrigemParaDestino = new HashMap<>();

        for (int i = 0; i < origem.size(); i++) {
            final String ponOrigem = origem.get(i)[1];
            final String ponDestino = destino.get(i)[1];
            mapaPonOrigemParaDestino.put(ponOrigem, ponDestino);
        }

        final String slotOrigem = origem.get(0)[0];
        final String slotDestino = destino.get(0)[0];

        final List<String[]> filtradosMapeados = new ArrayList<>();

        for (final String[] linha : list) {
            final String linhaSlot = linha[indexSlot];
            final String linhaPon = linha[indexPon];

            if (linhaSlot.equals(slotOrigem) && mapaPonOrigemParaDestino.containsKey(linhaPon)) {
                final String novaPon = mapaPonOrigemParaDestino.get(linhaPon);

                final String[] novaLinha = linha.clone();
                novaLinha[indexSlot] = slotDestino;
                novaLinha[indexPon] = novaPon;

                filtradosMapeados.add(novaLinha);
            }
        }

        return filtradosMapeados;
    }

    /**
     * Filtra os dados do List<String[]> baseado no valor de escolha slot, pon e onu
     * 
     * @param list      List<String[]> contendo os comandos para criação
     * @param indexSlot Index do valor do slot
     * @param indexPon  Index do valor da pon
     * @param indexOnu  Index do valor da onu
     * @return Retorna a List<String[]> inicial porém somente com os valores do
     *         index escolhido!
     */
    private List<String[]> filtrarEMapearPorOnu(final List<String[]> list, final int indexSlot,
            final int indexPon, final int indexOnu) {
        final List<String[]> resultado = new ArrayList<>();
        final List<String[]> origem = olt5kCutoverTo6k.getOnuOrigemSelecionadaOnuTable();
        final List<String[]> destino = olt5kCutoverTo6k.getGponDestinoSelecionado();

        // Confirma que o destino tem slot e pon definidos
        if (destino.isEmpty() || destino.get(0).length < 2) {
            System.err.println("Destino inválido para ONU.");
            return resultado;
        }

        final String novoSlot = destino.get(0)[0];
        final String novaPon = destino.get(0)[1];

        for (final String[] linha : list) {
            final String slot = linha[indexSlot];
            final String pon = linha[indexPon];
            final String onu = linha[indexOnu];

            for (final String[] onuSelecionada : origem) {
                final String slotOrigem = onuSelecionada[0];
                final String ponOrigem = onuSelecionada[1];
                final String onuOrigem = onuSelecionada[2];

                if (slot.equals(slotOrigem) && pon.equals(ponOrigem) && onu.equals(onuOrigem)) {
                    final String[] novaLinha = linha.clone();
                    novaLinha[indexSlot] = novoSlot;
                    novaLinha[indexPon] = novaPon;
                    resultado.add(novaLinha);
                    break;
                }
            }
        }

        return resultado;
    }

}
