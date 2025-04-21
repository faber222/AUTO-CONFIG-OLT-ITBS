package engtelecom.scripts;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que fornece scripts relacionados à configuração de uma OLT AN5000
 */
public class ScriptsCutoverAN5kto6k {
        /**
         * Construtor padrão
         */
        public ScriptsCutoverAN5kto6k() {
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
                        final String slotPortaPon, final String slotCpe, final String cpeType) {
                final List<String> scriptProvisionaCpe = new ArrayList<>();
                scriptProvisionaCpe.add(String.format("whitelist add phy-id %s type %s slot %s pon %s onuid %s",
                                serialNumberCpe, cpeType, slotGpon, slotPortaPon, slotCpe));
                return scriptProvisionaCpe;
        }

        /**
         * Função usada para criar o script bandwidth
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde a CPE se encontra
         * @param typestr      Tipo de config de bw
         * @param fixbw        Banda fixa
         * @param asrbw        Banda assegurada
         * @param maxbw        Banda max
         * @return Lista de strings contendo todo o script para configuração de
         *         bandwidth
         */
        public List<String> configBandwidth(final String slotGpon, final String slotPortaPon,
                        final String slotCpe, final String typestr, final String fixbw, final String asrbw,
                        final String maxbw) {
                final List<String> scriptBandwidth = new ArrayList<>();
                scriptBandwidth.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptBandwidth.add(String.format(
                                "onu gpon-upstream-service-bandwidth %s type %s fix %s assure %s max %s",
                                slotCpe, typestr, fixbw, asrbw, maxbw));
                scriptBandwidth.add("exit");
                return scriptBandwidth;
        }

        /**
         * Função usada para criar o servMode intelbras router para perfil veip
         * 
         * @param index Identificador do Service mode profile
         * @param nome  Nome do Service mode profile
         * @param type  Tipo do Service(uni/transp)
         * @return Lista de strings contendo a config servMode default intelbras
         */
        public List<String> configProfileServMode(final String index, final String nome, final String type) {
                final List<String> scriptServMode = new ArrayList<>();
                scriptServMode.add(String.format(
                                "port-service-mode-profile add index %s name %s type %s cvlan transparent translate disable qinq disable null",
                                index, nome, type));
                return scriptServMode;
        }

        /**
         * Função usada para criar o script veip da CPE
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde a CPE se encontra
         * @param vlan         Vlan do VEIP
         * @param perfil       Perfil do Service Mode Profile
         * @return Lista de strings contendo todo o script para configuração de veip
         */
        public List<String> configVeip(final String slotGpon, final String slotPortaPon,
                        final String slotCpe, final String vlan, final String perfil) {
                final List<String> scriptVeip = new ArrayList<>();
                scriptVeip.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptVeip.add(String.format(
                                "onu veip %s cvlan-tpid 33024 cvlan-id %s cvlan-cos 65535 trans-vlan-tpid 33024 trans-vlan-id 65535 trans-vlan-cos 65535 svlan-tpid 33024 svlan-vid 65535 svlan-cos 65535 tls 0 service-mode-profile %s svlan-profile 65535 service-type 1",
                                slotCpe, vlan, perfil));
                scriptVeip.add("exit");
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
         * @param index        Identificador do service count
         * @param vlan         Vlan da eth
         * @return Lista de strings contendo todo o script para configuração de eth
         *         bridge
         */
        public List<String> configEth(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String port, final String mode, final String index, final String vlan) {
                final List<String> scriptVeip = new ArrayList<>();
                scriptVeip.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptVeip.add(String.format("onu port vlan %s eth %s service count %s", slotCpe, port, index));
                scriptVeip.add(String.format("onu port vlan %s eth %s service %s %s priority 0 tpid 33024 vid %s",
                                slotCpe, port, index, mode, vlan));
                scriptVeip.add("exit");
                return scriptVeip;
        }

        /**
         * Função usada para criar o script que add a vlan na uplink da olt
         * 
         * @param slotUplink      Slot da placa no chassi
         * @param slotPortaUplink Slot da porta uplink no chassi
         * @param vlanBegin       Vlan inicial da uplink
         * @param vlanEnd         Vlan final da uplink
         * @return Lista de strings contendo todo o script para add vlan na uplink
         */
        public List<String> addVlanToUplink(final String slotUplink,
                        final String slotPortaUplink, final String vlanBegin, final String vlanEnd) {
                final List<String> uplink = new ArrayList<>();
                uplink.add(String.format("port vlan %s to %s tag 1/%s %s",
                                vlanBegin, vlanEnd,
                                slotUplink, slotPortaUplink));
                uplink.add(String.format("port vlan %s to %s allslot", vlanBegin, vlanEnd));
                return uplink;
        }

        /**
         * Função usada para criar o script para configurar o pppoe da cpe
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde desejamos provisionar a CPE
         * @param vlan         Vlan do WanService
         * @param userPPP      Usuario pppoe
         * @param passPPP      Senha do pppoe
         * @param index        Index do WanService
         * @param mode         Modo do WanService (Internet, voice/Internet, etc)
         * @param type         Modo de operação da Wan (bridge/router)
         * @param nat          Nat enable/disable
         * @param dsp          Modo pppoe/null
         * 
         * @return Lista de strings contendo todo o script para configurar pppoe
         */
        public List<String> comandoPpoe(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String vlan, final String userPPP, final String passPPP, final String index,
                        final String mode, final String type, final String nat, final String dsp) {
                final List<String> scriptComandoPpoe = new ArrayList<>();
                scriptComandoPpoe.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptComandoPpoe.add(String.format(
                                "onu wan-cfg %s index %s mode %s type %s %s 7 nat %s qos disable dsp %s pro disable %s %s null auto entries 6 fe1 fe2 fe3 fe4 ssid1 ssid5",
                                slotCpe, index, mode, type, vlan, nat, dsp, userPPP, passPPP));
                scriptComandoPpoe.add(String.format(
                                "onu ipv6-wan-cfg %s ind %s ip-stack-mode both ipv6-src-type slaac prefix-src-type delegate",
                                slotCpe, index));
                scriptComandoPpoe.add("exit");
                return scriptComandoPpoe;
        }

        /**
         * Função usada para criar o script para configurar o router static da cpe
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde desejamos provisionar a CPE
         * @param vlan         Vlan do WanService
         * @param userPPP      Usuario pppoe
         * @param passPPP      Senha do pppoe
         * @param index        Index do WanService
         * @param mode         Modo do WanService (Internet, voice/Internet, etc)
         * @param type         Modo de operação da Wan (bridge/router)
         * @param nat          Nat enable/disable
         * @param dsp          Modo pppoe/null
         * 
         * @return Lista de strings contendo todo o script para configurar pppoe
         */
        public List<String> comandoStatic(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String vlan, final String userPPP, final String passPPP, final String index,
                        final String mode, final String type, final String nat, final String dsp) {
                final List<String> scriptComandoPpoe = new ArrayList<>();
                scriptComandoPpoe.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptComandoPpoe.add(String.format(
                                "onu wan-cfg %s index %s mode %s type %s %s 7 nat %s qos disable dsp %s pro disable %s %s null auto entries 6 fe1 fe2 fe3 fe4 ssid1 ssid5",
                                slotCpe, index, mode, type, vlan, nat, dsp, userPPP, passPPP));
                scriptComandoPpoe.add(String.format(
                                "onu ipv6-wan-cfg %s ind %s ip-stack-mode both ipv6-src-type slaac prefix-src-type delegate",
                                slotCpe, index));
                scriptComandoPpoe.add("exit");
                return scriptComandoPpoe;
        }


        /**
         * Função usada para criar o script para configurar o pppoe da cpe
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde desejamos provisionar a CPE
         * @param vlan         Vlan do WanService
         * @param index        Index do WanService
         * @param mode         Modo do WanService (Internet, voice/Internet, etc)
         * @param type         Modo de operação da Wan (bridge/router)
         * @param nat          Nat enable/disable
         * @param vlanMode     Modo tag/transp
         * @param tvlan        Translate vlan dis/en
         * @param tvid         Id vlan translate
         * 
         * @return Lista de strings contendo todo o script para configurar pppoe
         */
        public List<String> comandoBridge(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String vlan, final String index, final String mode, final String type, final String nat,
                        final String vlanMode, final String tvlan, final String tvid) {
                final List<String> scriptComandoPpoe = new ArrayList<>();
                scriptComandoPpoe.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptComandoPpoe.add(String.format(
                                "onu wan-cfg %s index %s mode %s type %s %s 7 nat %s qos disable vlanmode %s tvlan %s %s 0 dsp null entries 6 fe1 fe2 fe3 fe4 ssid1 ssid5",
                                slotCpe, index, mode, type, vlan, nat, vlanMode, tvlan, tvid));
                scriptComandoPpoe.add(String.format(
                                "onu ipv6-wan-cfg %s ind %s ip-stack-mode both ipv6-src-type slaac prefix-src-type delegate",
                                slotCpe, index));
                scriptComandoPpoe.add("exit");
                return scriptComandoPpoe;
        }

        /**
         * Função usada para criar o script para configurar o wifi 2.4Ghz
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde a cpe se encontra
         * @param ssidName2    Ssid da rede 2.4Ghz
         * @param passName2    Senha da rede 2.4Ghz
         * @param wifiVersion  802.11
         * @param servNo       Indica se é wifi 2.4 ou 5Ghz (1/2)
         * @param index        Index
         * @param ssidEnable   Enable/Disable
         * @param hide         SSID oculto ou não
         * @param authMode     Tipo de autenticação (WPA, WPA2, etc.)
         * @param encryptType  Tipo de criptografia
         * @return Lista de strings contendo todo o script para configurar a rede 2.4Ghz
         */
        public List<String> comandoWifi2(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String ssidName2, final String passName2, final String wifiVersion,
                        final String servNo, final String index,
                        final String ssidEnable, final String hide,
                        final String authMode, final String encryptType) {
                final List<String> scriptComandoWifi = new ArrayList<>();
                scriptComandoWifi.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptComandoWifi.add(String.format(
                                "onu wifi connection %s serv-no %s index %s ssid %s %s hide %s authmode %s encrypt-type %s wpakey %s interval 86400 wifi-connect-num 32",
                                slotCpe, servNo, index, ssidEnable, ssidName2, hide, authMode, encryptType, passName2));
                scriptComandoWifi.add(String.format(
                                "onu wifi attribute %s serv-no %s wifi %s district brazil channel 6 standard %s txpower 20 frequency 2.4ghz freq-bandwidth 20mhz/40mhz",
                                slotCpe, servNo, ssidEnable, wifiVersion));
                scriptComandoWifi.add("exit");
                return scriptComandoWifi;
        }

        /**
         * Função usada para criar o script para configurar o wifi 2.4Ghz com radius
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde a cpe se encontra
         * @param ssidName2    Ssid da rede 2.4Ghz
         * @param passName2    Senha da rede 2.4Ghz
         * @param wifiVersion  802.11
         * @param servNo       Indica se é wifi 2.4 ou 5Ghz (1/2)
         * @param index        Index
         * @param ssidEnable   Enable/Disable
         * @param hide         SSID oculto ou não
         * @param authMode     Tipo de autenticação (WPA, WPA2, etc.)
         * @param encryptType  Tipo de criptografia
         * @param radiusServ   IP do Radius
         * @param port         Porta do Radius
         * @param pswd         Senha do Radius
         * @return Lista de strings contendo todo o script para configurar a rede 2.4Ghz
         */
        public List<String> comandoWifi2(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String ssidName2, final String passName2, final String wifiVersion, final String servNo,
                        final String index, final String ssidEnable, final String hide,
                        final String authMode, final String encryptType, final String radiusServ, final String port,
                        final String pswd) {
                final List<String> scriptComandoWifi = new ArrayList<>();
                scriptComandoWifi.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptComandoWifi.add(String.format(
                                "onu wifi connection %s serv-no %s index %s ssid %s %s hide %s authmode %s encrypt-type %s wpakey %s interval 86400 radius-serv ipv4 %s port %s pswd %s wifi-connect-num 32",
                                slotCpe, servNo, index, ssidEnable, ssidName2, hide, authMode, encryptType, passName2,
                                radiusServ, port, pswd));
                scriptComandoWifi.add(String.format(
                                "onu wifi attribute %s serv-no %s wifi %s district brazil channel 6 standard %s txpower 20 frequency 2.4ghz freq-bandwidth 20mhz/40mhz",
                                slotCpe, servNo, ssidEnable, wifiVersion));
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
         * @param wifiVersion  802.11
         * @param servNo       Indica se é wifi 2.4 ou 5Ghz (1/2)
         * @param index        Index
         * @param ssidEnable   Enable/Disable
         * @param hide         SSID oculto ou não
         * @param authMode     Tipo de autenticação (WPA, WPA2, etc.)
         * @param encryptType  Tipo de criptografia
         * @return Lista de strings contendo todo o script para configurar a rede 5Ghz
         */
        public List<String> comandoWifi5(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String ssidName5, final String passName5, final String wifiVersion,
                        final String servNo, final String index,
                        final String ssidEnable, final String hide,
                        final String authMode, final String encryptType) {
                final List<String> scriptComandoWifi = new ArrayList<>();
                scriptComandoWifi.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptComandoWifi.add(String.format(
                                "onu wifi connection %s serv-no %s index %s ssid %s %s hide %s authmode %s encrypt-type %s wpakey %s interval 86400 wifi-connect-num 32",
                                slotCpe, servNo, index, ssidEnable, ssidName5, hide, authMode, encryptType, passName5));
                scriptComandoWifi.add(String.format(
                                "onu wifi attribute %s serv-no %s wifi %s district brazil channel 161 standard %s txpower 20 frequency 5.8ghz freq-bandwidth 80mhz",
                                slotCpe, servNo, ssidEnable, wifiVersion));
                scriptComandoWifi.add("exit");
                return scriptComandoWifi;
        }

        /**
         * Função usada para criar o script para configurar o wifi 5Ghz com radius
         * 
         * @param slotGpon     Slot da placa no chassi
         * @param slotPortaPon Porta pon onde a CPE se encontra
         * @param slotCpe      Slot da pon onde a cpe se encontra
         * @param ssidName5    Ssid da rede 5Ghz
         * @param passName5    Senha da rede 5Ghz
         * @param wifiVersion  802.11
         * @param servNo       Indica se é wifi 2.4 ou 5Ghz (1/2)
         * @param index        Index
         * @param ssidEnable   Enable/Disable
         * @param hide         SSID oculto ou não
         * @param authMode     Tipo de autenticação (WPA, WPA2, etc.)
         * @param encryptType  Tipo de criptografia
         * @param radiusServ   IP do Radius
         * @param port         Porta do Radius
         * @param pswd         Senha do Radius
         * @return Lista de strings contendo todo o script para configurar a rede 5Ghz
         */
        public List<String> comandoWifi5(final String slotGpon, final String slotPortaPon, final String slotCpe,
                        final String ssidName5, final String passName5, final String wifiVersion,
                        final String servNo, final String index,
                        final String ssidEnable, final String hide,
                        final String authMode, final String encryptType, final String radiusServ, final String port,
                        final String pswd) {
                final List<String> scriptComandoWifi = new ArrayList<>();
                scriptComandoWifi.add(String.format("interface pon 1/%s/%s", slotGpon, slotPortaPon));
                scriptComandoWifi.add(String.format(
                                "onu wifi connection %s serv-no %s index %s ssid %s %s hide %s authmode %s encrypt-type %s wpakey %s interval 86400 radius-serv ipv4 %s port %s pswd %s wifi-connect-num 32",
                                slotCpe, servNo, index, ssidEnable, ssidName5, hide, authMode, encryptType, passName5,
                                radiusServ, port, pswd));
                scriptComandoWifi.add(String.format(
                                "onu wifi attribute %s serv-no %s wifi %s district brazil channel 161 standard %s txpower 20 frequency 5.8ghz freq-bandwidth 80mhz",
                                slotCpe, servNo, ssidEnable, wifiVersion));
                scriptComandoWifi.add("exit");
                return scriptComandoWifi;
        }
}
