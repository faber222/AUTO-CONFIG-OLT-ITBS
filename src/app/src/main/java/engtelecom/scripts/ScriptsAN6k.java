package engtelecom.scripts;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que fornece scripts relacionados à configuração de uma OLT AN5000
 */
public class ScriptsAN6k {
        /**
         * Construtor padrão
         */
        public ScriptsAN6k() {
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
                        final String slotPortaPon, final String slotCpe, final String cpeCapaProfile) {
                final List<String> scriptProvisionaCpe = new ArrayList<>();
                // final StringBuilder mgrStringBuilder = new StringBuilder();
                // scriptProvisionaCpe.add("cd onu");
                scriptProvisionaCpe.add(String.format("whitelist add phy-id %s type %s slot %s pon %s onuid %s",
                                serialNumberCpe, cpeCapaProfile, slotGpon, slotPortaPon, slotCpe));
                // scriptProvisionaCpe.add("exit");
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
                scriptComandoPpoe.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptComandoPpoe.add(String.format(
                                "onu wan-cfg %s index 1 mode inter type route %s 7 nat enable qos disable dsp pppoe pro disable %s %s null auto entries 6 fe1 fe2 fe3 fe4 ssid1 ssid5",
                                slotCpe, vlan, userPPP, passPPP));
                scriptComandoPpoe.add(String.format(
                                "onu ipv6-wan-cfg %s ind 1 ip-stack-mode both ipv6-src-type slaac prefix-src-type delegate",
                                slotCpe));
                scriptComandoPpoe.add("exit");
                return scriptComandoPpoe;
        }

        /**
         * Função usada para criar o servMode intelbras router para perfil veip
         * 
         * @return Lista de strings contendo a config servMode default intelbras
         */
        public List<String> configProfileServMode() {
                final List<String> scriptServMode = new ArrayList<>();
                scriptServMode.add(
                                "port-service-mode-profile add index 30 name ITBS_ROUTER_PROF type unicast cvlan transparent translate disable qinq disable null");
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
                scriptVeip.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptVeip.add(String.format(
                                "onu veip %s cvlan-tpid  33024 cvlan-id %s cvlan-cos 65535 trans-vlan-tpid 33024 trans-vlan-id 65535 trans-vlan-cos 65535 svlan-tpid 33024 svlan-vid 65535 svlan-cos 65535 tls 0 service-mode-profile 30 svlan-profile 65535 service-type 1",
                                slotCpe, vlan));
                scriptVeip.add("exit");
                return scriptVeip;
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
                uplink.add(String.format("port vlan %s to %s tag 1/%s %s", vlan, vlan,
                                slotUplink, slotPortaUplink));
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
                scriptComandoWifi.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptComandoWifi.add(String.format(
                                "onu wifi connection %s serv-no 1 index 1 ssid enable %s hide disable authmode wpa-psk/wpa2psk encrypt-type aes wpakey %s interval 86400 wifi-connect-num 32",
                                slotCpe, ssidName2, passName2));
                scriptComandoWifi.add(String.format(
                                "onu wifi attribute %s serv-no 1 wifi enable district brazil channel 6 standard %s txpower 20 frequency 2.4ghz freq-bandwidth 20mhz/40mhz",
                                slotCpe, wifiVersion));
                scriptComandoWifi.add("exit");
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
                scriptComandoWifi.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptComandoWifi.add(String.format(
                                "onu wifi connection %s serv-no 2 index 1 ssid enable %s hide disable authmode wpa-psk/wpa2psk encrypt-type aes wpakey %s interval 86400 wifi-connect-num 32",
                                slotCpe, ssidName5, passName5));
                scriptComandoWifi.add(String.format(
                                "onu wifi attribute %s serv-no 2 wifi enable district brazil channel 161 standard %s txpower 20 frequency 5.8ghz freq-bandwidth 80mhz",
                                slotCpe, wifiVersion));
                scriptComandoWifi.add("exit");
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
         * @param eid     Identificador da CPE na OLT
         * @return Lista de strings contendo o script completo de criação de onu
         *         capability
         */
        public List<String> comandoOnuCapa(final String name, final String ponType, final String onuCapa,
                        final String lan1g, final String lan10g, final String pots,
                        final String wifi, final String usb, final String eid) {
                final List<String> scriptOnuCapa = new ArrayList<>();
                scriptOnuCapa.add(String.format(
                                "onu caps-profile add name %s pontype %s onucapa %s lan1g %s lan10g %s pots %s",
                                name, ponType, onuCapa, lan1g, lan10g, pots));
                scriptOnuCapa.add(String.format("onu caps-profile add option wifi %s usb %s end", wifi, usb));
                scriptOnuCapa.add(String.format("onu caps-profile modify name %s eid %s", name, eid));
                return scriptOnuCapa;
        }
}
