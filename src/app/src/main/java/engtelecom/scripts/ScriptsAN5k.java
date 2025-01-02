package engtelecom.scripts;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que fornece scripts relacionados à configuração de uma OLT AN5000
 */
public class ScriptsAN5k {
        /**
         * Construtor padrão
         */
        public ScriptsAN5k() {
        }

        /**
         * Função usada para selecionar o modo de autorização das portas Pon
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a cpe se encontra
         * @return Lista de Strings contendo todo o script de autorização das portas
         */
        public List<String> setPonAuth(final String slotGpon, final String slotPortaPon) {
                final List<String> ponAuth = new ArrayList<>();
                ponAuth.add("cd card");
                ponAuth.add(String.format("set pon_auth slot %s pon %s mode phy_id", slotGpon, slotPortaPon));
                ponAuth.add("cd ..");
                return ponAuth;
        }

        /**
         * Função usada para criar o script para provisionamento de CPE
         * 
         * @param serialNumberCpe Serial number da CPE, seguir esse padrao FHTT12345678
         * @param slotGpon        Slot da placa no chassi
         * @param slotPortaPon    Porta pon onde a CPE se encontra
         * @param slotCpe         Slot da pon onde desejamos provisionar a CPE
         * @param cpeType         Capability da CPE
         * @return Lista de strings contendo todo o script de provisionamento de cpes
         */
        public List<String> provisionaCPE(final String serialNumberCpe, final String slotGpon,
                        final String slotPortaPon,
                        final String slotCpe, final String cpeCapaProfile) {
                final List<String> scriptProvisionaCpe = new ArrayList<>();
                // final StringBuilder mgrStringBuilder = new StringBuilder();
                scriptProvisionaCpe.add("cd onu");
                scriptProvisionaCpe.add(String.format(
                                "set whitelist phy_addr address %s password null action add slot %s pon %s onu %s type %s",
                                serialNumberCpe, slotGpon, slotPortaPon, slotCpe, cpeCapaProfile));
                scriptProvisionaCpe.add("cd ..");
                return scriptProvisionaCpe;
        }

        /**
         * Função usada para criar o script para configurar o pppoe da cpe
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde desejamos provisionar a CPE
         * @param vlan         Vlan do pppoe
         * @param userPPP      Usuario pppoe
         * @param passPPP      Senha do pppoe
         * @return Lista de strings contendo todo o script para configurar pppoe
         */
        public List<String> comandoPpoe(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String vlan,
                        final String userPPP,
                        final String passPPP) {
                final List<String> scriptComandoPpoe = new ArrayList<>();
                scriptComandoPpoe.add("cd onu");
                scriptComandoPpoe.add("cd lan");
                scriptComandoPpoe.add(String.format(
                                "set wancfg slot %s %s %s index 1 mode internet type route %s 0 nat enable qos disable dsp pppoe proxy disable %s %s null auto entries 6 fe1 fe2 fe3 fe4 ssid1 ssid5",
                                slotGpon, slotPortaPon, slotCpe, vlan, userPPP, passPPP));
                scriptComandoPpoe.add(String.format("apply wancfg slot %s %s %s", slotGpon, slotPortaPon, slotCpe));
                scriptComandoPpoe.add(String.format(
                                "set wancfg slot %s %s %s index 1 ip-stack-mode both ipv6-src-type slaac prefix-src-type delegate",
                                slotGpon, slotPortaPon, slotCpe));
                scriptComandoPpoe.add(String.format("apply wancfg slot %s %s %s", slotGpon, slotPortaPon, slotCpe));
                scriptComandoPpoe.add("cd ..");
                scriptComandoPpoe.add("cd ..");
                return scriptComandoPpoe;
        }

        /**
         * Função usada para criar o servMode intelbras router para perfil veip
         * 
         * @return Lista de strings contendo a config servMode default intelbras
         */
        public List<String> configProfileServMode() {
                final List<String> scriptServMode = new ArrayList<>();
                scriptServMode.add("cd profile");
                scriptServMode.add(
                                "add servmode profile index 30 name ITBS_ROUTER_PROF type unicast cvlan transparent translate disable qinq disable null");
                scriptServMode.add("cd ..");
                return scriptServMode;
        }

        /**
         * Função usada para criar o script veip da CPE
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde a CPE se encontra
         * @param vlan         Vlan do VEIP
         * @return Lista de strings contendo todo o script para configuração de veip
         */
        public List<String> configVeip(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String vlan) {
                final List<String> scriptVeip = new ArrayList<>();
                scriptVeip.add("cd onu");
                scriptVeip.add("cd lan");
                scriptVeip.add(String.format(
                                "set epon slot %s pon %s onu %s port 1 onuveip 1 33024 %s 65535 33024 65535 65535 33024 65535 65535 0 30 65535",
                                slotGpon, slotPortaPon, slotCpe, vlan));
                scriptVeip.add("cd ..");
                scriptVeip.add("cd ..");
                return scriptVeip;
        }

        /**
         * Função usada para criar o script eth bridge da CPE
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde a CPE se encontra
         * @param port         Numero da porta eth mapeada
         * @param mode         Modo "tag" tira a vlan, "transparent" mantem a vlan
         * @param vlan         Vlan da eth
         * @return Lista de strings contendo todo o script para configuração de eth
         *         bridge
         */
        public List<String> configEth(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String port, final String mode, final String vlan) {
                final List<String> scriptEth = new ArrayList<>();
                scriptEth.add("cd onu");
                scriptEth.add("cd lan");
                scriptEth.add(String.format("set slot %s pon %s onu %s port %s service number 1", slotGpon,
                                slotPortaPon, slotCpe, port));
                scriptEth.add(String.format("set slot %s pon %s onu %s port %s service 1 vlan_mode %s 0 33024 %s",
                                slotGpon, slotPortaPon, slotCpe, port, mode, vlan));
                scriptEth.add(String.format("apply onu %s %s %s vlan", slotGpon, slotPortaPon, slotCpe));
                scriptEth.add("cd ..");
                scriptEth.add("cd ..");
                return scriptEth;
        }

        /**
         * Função usada para criar o script que add a vlan na uplink da olt
         * 
         * @param vlan            Vlan da uplink
         * @param slotUplink      Slot da placa no chassi
         * @param slotPortaUplink Slot da porta uplink no chassi
         * @return Lista de strings contendo todo o script para add vlan na uplink
         */
        public List<String> addVlanToUplink(final String vlan, final String slotUplink, final String slotPortaUplink) {
                final List<String> uplink = new ArrayList<>();
                uplink.add("cd vlan");
                uplink.add(String.format("add vlan vlan_begin %s vlan_end %s tag uplink slot %s port %s", vlan, vlan,
                                slotUplink, slotPortaUplink));
                uplink.add(String.format("add vlan vlan_begin %s vlan_end %s tag allslot 0", vlan, vlan));
                uplink.add("cd ..");
                return uplink;
        }

        /**
         * Função usada para criar o script para configurar o wifi 2.4Ghz
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde a cpe se encontra
         * @param ssidName2    Ssid da rede 2.4Ghz
         * @param passName2    Senha da rede 2.4Ghz
         * @return Lista de strings contendo todo o script para configurar a rede 2.4Ghz
         */
        public List<String> comandoWifi2(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String ssidName2,
                        final String passName2, final String wifiVersion) {
                final List<String> scriptComandoWifi = new ArrayList<>();
                scriptComandoWifi.add("cd onu");
                scriptComandoWifi.add("cd lan");
                scriptComandoWifi.add(String.format(
                                "set wifi_serv_wlan slot %s pon %s onu %s serv_no 1 index 1 ssid enable %s hide disable authmode wpa2psk encrypt_type aes wpakey %s interval 86400 wifi_connect_num 32",
                                slotGpon, slotPortaPon, slotCpe, ssidName2, passName2));
                scriptComandoWifi.add(String.format(
                                "set wifi_serv_cfg slot %s pon %s onu %s serv_no 1 wifi enable district brazil channel 6 standard %s txpower 20 frequency 2.4ghz freq_bandwidth 20mhz/40mhz",
                                slotGpon, slotPortaPon, slotCpe, wifiVersion));
                scriptComandoWifi.add("cd ..");
                scriptComandoWifi.add("cd ..");
                return scriptComandoWifi;
        }

        /**
         * Função usada para criar o script para configurar o wifi 5Ghz
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde a cpe se encontra
         * @param ssidName5    Ssid da rede 5Ghz
         * @param passName5    Senha da rede 5Ghz
         * @param wifiVersion  Versão do IEEE da 5Ghz
         * @return Lista de strings contendo todo o script para configurar a rede 5Ghz
         */
        public List<String> comandoWifi5(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String ssidName5,
                        final String passName5, final String wifiVersion) {
                final List<String> scriptComandoWifi = new ArrayList<>();
                scriptComandoWifi.add("cd onu");
                scriptComandoWifi.add("cd lan");
                scriptComandoWifi.add(String.format(
                                "set wifi_serv_wlan slot %s pon %s onu %s serv_no 2 index 1 ssid enable %s hide disable authmode wpa2psk encrypt_type aes wpakey %s interval 86400 wifi_connect_num 32",
                                slotGpon, slotPortaPon, slotCpe, ssidName5, passName5));
                scriptComandoWifi.add(String.format(
                                "set wifi_serv_cfg slot %s pon %s onu %s serv_no 2 wifi enable district brazil channel 161 standard %s txpower 20 frequency 5.8ghz freq_bandwidth 80mhz",
                                slotGpon, slotPortaPon, slotCpe, wifiVersion));
                scriptComandoWifi.add("cd ..");
                scriptComandoWifi.add("cd ..");
                return scriptComandoWifi;
        }

        /**
         * Função usada para criar o perfil capability da cpe de forma genérica
         * 
         * @param name    Nome do modelo da CPE, ex: AX1800V
         * @param ponType Tipo de CPE, padrão 712
         * @param lan1g   Quantidade de portas 1gb
         * @param lan10g  Quantidade de portas 10gb
         * @param pots    Quantidade de portas de telefonia
         * @param wifi    Quantidade de frequencias Wireless
         * @param usb     Quantidade de portas usb
         * @return Lista de strings contendo o script completo de criação de onu
         *         capability
         */
        public List<String> comandoOnuCapa(final String name, final String ponType, final String onuCapa,
                        final String lan1g, final String lan10g, final String pots,
                        final String wifi, final String usb, final String eid) {
                final List<String> scriptOnuCapa = new ArrayList<>();
                scriptOnuCapa.add(String.format(
                                "add cs onu profile name %s pontype %s onucapa %s lan1g %s lan10g %s pots %s",
                                name, ponType, onuCapa, lan1g, lan10g, pots));
                scriptOnuCapa.add(String.format("add cs onu profile option wifi %s usb %s end", wifi, usb));
                scriptOnuCapa.add(String.format("modify cs onu profile name %s eid %s end", name, eid));
                return scriptOnuCapa;
        }

        // public List<String> comandoOnuCapaF3() {
        // final List<String> scriptOnuCapa = new ArrayList<>();
        // scriptOnuCapa.add("add cs onu profile name HG6145F3 pontype 712 onucapa 0
        // lan1g 4 lan10g 0 pots 1");
        // scriptOnuCapa.add("add cs onu profile option wifi 2 end");
        // scriptOnuCapa.add("modify cs onu profile name HG6145F3 eid HG6145F3 end");
        // return scriptOnuCapa;
        // }

        // public List<String> comandoOnuCapaF() {
        // final List<String> scriptOnuCapa = new ArrayList<>();
        // scriptOnuCapa.add("add cs onu profile name HG6145F pontype 712 onucapa 0
        // lan1g 4 lan10g 0 pots 1");
        // scriptOnuCapa.add("add cs onu profile option wifi 2 usb 2 end");
        // scriptOnuCapa.add("modify cs onu profile name HG6145F eid HG6145F end");
        // return scriptOnuCapa;
        // }

        // public List<String> comandoOnuCapaD2() {
        // final List<String> scriptOnuCapa = new ArrayList<>();
        // scriptOnuCapa.add("add cs onu profile name HG6145D2 pontype 712 onucapa 0
        // lan1g 4 lan10g 0 pots 1");
        // scriptOnuCapa.add("add cs onu profile option wifi 2 end");
        // scriptOnuCapa.add("modify cs onu profile name HG6145D2 eid HG6145D2 end");
        // return scriptOnuCapa;
        // }

}
