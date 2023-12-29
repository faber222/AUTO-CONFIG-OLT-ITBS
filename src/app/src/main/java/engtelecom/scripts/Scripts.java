package engtelecom.scripts;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que fornece scripts relacionados à configuração de uma OLT G16 e G08.
*/
public class Scripts {
    /**
     * Construtor padrão
     */
    public Scripts() {
    }

    /**
     * Retorna o script de acesso conf t
     * 
     * @return Lista de strings com o enable e conf t
     */
    public List<String> enable() {
        final List<String> accessLevel = new ArrayList<>();
        accessLevel.add("enable");
        accessLevel.add("configure terminal");
        return accessLevel;
    }

    /**
     * Retorna o script do profile DBA
     * 
     * @return Lista de strings do profile DBA
     */
    public List<String> profileDba() {
        final List<String> dba = new ArrayList<>();
        dba.add("deploy profile dba");
        dba.add("aim 1 name DBA-DEFAULT");
        dba.add("type 4 max 1200000");
        dba.add("active");
        dba.add("exit");
        dba.add("exit");
        return dba;
    }

    /**
     * Converte uma lista de VLANs em uma string formatada para comandos de
     * configuração VLAN.
     *
     * @param vlanList Lista de VLANs a serem convertidas.
     * @return Uma string formatada para comandos VLAN, por exemplo, "vlan
     *         10,20,30".
     */
    public String vlan(final List<String> vlanList) {
        final StringBuilder vlanString = new StringBuilder("vlan ");
        final String joinedVlans = String.join(",", vlanList);
        vlanString.append(joinedVlans);
        return vlanString.toString();
    }

    /**
     * Retorna uma lista de comandos para a configuração da interface hybrid tagged.
     *
     * @param ethMgr    Nome da interface eth.
     * @param vlanRange Lista de números de VLANs a serem marcadas (tagged).
     * @return Uma string contendo comandos formatados, por exemplo:
     * 
     *         <pre>
     *     ethMgr
     *     switchport mode hybrid
     *     switchport hybrid tagged vlan 10,20,30
     *     exit
     *         </pre>
     */
    public String interfaceMgr(final String ethMgr, final List<String> vlanRange) {
        final StringBuilder mgrStringBuilder = new StringBuilder();
        final String joinedVlans = String.join(",", vlanRange);
        mgrStringBuilder.append(ethMgr).append("\n");
        mgrStringBuilder.append("switchport mode hybrid").append("\n");
        mgrStringBuilder.append(String.format("switchport hybrid tagged vlan %s", joinedVlans)).append("\n");
        mgrStringBuilder.append("exit");
        return mgrStringBuilder.toString();
    }

    /**
     * Retorna o script do deploy profile vlan
     * 
     * @param vlanRange Numero da vlan
     * @param idProfile Id do profile vlan
     * @return Lista de strings contendo o script do profile vlan
     */
    public List<String> profileVlanTagged(final String vlanRange, final String idProfile) {
        final List<String> vlan = new ArrayList<>();
        vlan.add(String.format("aim %s name %s", idProfile, vlanRange));
        vlan.add(String.format("translate old-vlan %s new-vlan %s", vlanRange, vlanRange));
        vlan.add("active");
        return vlan;
    }

    /**
     * Retorna o script do deploy profile vlan
     * 
     * @param vlanRange Numero da vlan
     * @param idProfile Id do profile vlan
     * @return Lista de strings contendo o script do profile vlan
     */
    public List<String> profileVlanUntagged(final String vlanRange, final String idProfile) {
        final List<String> vlan = new ArrayList<>();
        vlan.add(String.format("aim %s name %s", idProfile, vlanRange));
        vlan.add(String.format("translate old-vlan 1 new-vlan %s", vlanRange));
        vlan.add("active");
        return vlan;
    }

    /**
     * Retorna o script do deploy profile line BRIDGE
     * 
     * @param vlanRange     id da vlan
     * @param idProfileLine id do profile line
     * @param idProfileVlan id do profile vlan da vlan escolhida
     * @param deviceType    device type da ont em questão
     * @return Lista de strings contendo o script do profile line
     */
    public List<String> profileLineBridgeTagged(final String vlanRange, final String idProfileLine, final String idProfileVlan,
            final String deviceType) {
        final List<String> line = new ArrayList<>();
        line.add(String.format("aim %s", idProfileLine));
        line.add(String.format("device type %s", deviceType));
        line.add("tcont 1 profile dba 1");
        line.add(String.format("gemport 1 tcont 1 vlan-profile %s", idProfileVlan));
        line.add("mapping mode port-vlan");
        line.add(String.format("mapping 1 port eth 1 vlan %s gemport 1", vlanRange));
        line.add(String.format("flow 1 port eth 1 default vlan %s", vlanRange));
        line.add("active");
        return line;
    }

    /**
     * Retorna o script do deploy profile line BRIDGE
     * 
     * @param idProfileLine id do profile line
     * @param idProfileVlan id do profile vlan da vlan escolhida
     * @param deviceType    device type da ont em questão
     * @return Lista de strings contendo o script do profile line
     */
    public List<String> profileLineBridgeUntagged(final String idProfileLine, final String idProfileVlan, final String deviceType) {
        final List<String> line = new ArrayList<>();
        line.add(String.format("aim %s", idProfileLine));
        line.add(String.format("device type %s", deviceType));
        line.add("tcont 1 profile dba 1");
        line.add(String.format("gemport 1 tcont 1 vlan-profile %s", idProfileVlan));
        line.add("mapping mode port-vlan");
        line.add("mapping 1 port eth 1 vlan 1 gemport 1");
        line.add("flow 1 port eth 1 default vlan 1");
        line.add("active");
        return line;
    }

    /**
     * Retorna o script do deploy profile line ROUTER
     * 
     * @param vlanRange     id da vlan
     * @param idProfileLine id do profile line
     * @param idProfileVlan id do profile vlan da vlan escolhida
     * @param deviceType    device type da ont em questão
     * @return Lista de strings contendo o script do profile line
     */
    public List<String> profileLineRouterTagged(final String vlanRange, final String idProfileLine, final String idProfileVlan,
            final String deviceType) {
        final List<String> line = new ArrayList<>();
        line.add(String.format("aim %s", idProfileLine));
        line.add(String.format("device type %s", deviceType));
        line.add("tcont 1 profile dba 1");
        line.add(String.format("gemport 1 tcont 1 vlan-profile %s", idProfileVlan));
        line.add("mapping mode port-vlan");
        line.add(String.format("mapping 1 port veip vlan %s gemport 1", vlanRange));
        line.add(String.format("flow 1 port veip vlan %s keep", vlanRange));
        line.add("active");
        return line;
    }

    /**
     * Retorna o script do deploy profile line ROUTER
     * 
     * @param idProfileLine id do profile line
     * @param idProfileVlan id do profile vlan da vlan escolhida
     * @param deviceType    device type da ont em questão
     * @return Lista de strings contendo o script do profile line
     */
    public List<String> profileLineRouterUnTagged(final String idProfileLine, final String idProfileVlan, final String deviceType) {
        final List<String> line = new ArrayList<>();
        line.add(String.format("aim %s", idProfileLine));
        line.add(String.format("device type %s", deviceType));
        line.add("tcont 1 profile dba 1");
        line.add(String.format("gemport 1 tcont 1 vlan-profile %s", idProfileVlan));
        line.add("mapping mode port-vlan");
        line.add("mapping 1 port veip vlan 1 gemport 1");
        line.add("flow 1 port veip default vlan 1");
        line.add("active");
        return line;
    }

    /**
     * Retorna o comando para a configuração automática de uma ONT com uma VLAN por
     * PON.
     *
     * @param idProfileLine id do profile line
     * @param vlanRange     id da vlan
     * @param deviceType    device type da ont em questão
     * @param interfaceGpon interface GPON da ONT
     * @return Comando para a configuração automática.
     */
    public String ontAutoConfigUmaVlanPorPon(final String idProfileLine, final String vlanRange, final String deviceType,
            final String interfaceGpon) {
        return String.format("ont auto-config name %s-VLAN-%s device-type %s line %s interface gpon %s",
                deviceType, vlanRange, deviceType, idProfileLine, interfaceGpon);
    }

    /**
     * Retorna o comando para a configuração automática de uma ONT com uma VLAN para
     * todas as PON.
     *
     * @param idProfileLine id do profile line
     * @param deviceType    device type da ont em questão
     * @return Comando para a configuração automática.
     */
    public String ontAutoConfigUmaVlanPon(final String idProfileLine, final String deviceType) {
        return String.format("ont auto-config name CPE_%s device-type %s line %s", deviceType, deviceType,
                idProfileLine);
    }

    /**
     * Retorna o comando para a configuração automática padrão de uma ONT com uma
     * VLAN por PON.
     *
     * @param idProfileLine id do profile line
     * @param interfaceGpon interface GPON da ONT
     * @return Comando para a configuração automática padrão.
     */
    public String ontAutoConfigDefaultUmaVlanPorPon(final String idProfileLine, final String interfaceGpon) {
        return String.format("ont auto-config name TERCEIROS-%s all-ont line %s interface gpon %s",
                idProfileLine, idProfileLine, interfaceGpon);
    }

    /**
     * Retorna o comando para a configuração automática padrão de uma ONT com uma
     * VLAN para todas as PON.
     *
     * @param idProfileLine id do profile line
     * @return Comando para a configuração automática padrão.
     */
    public String ontAutoConfigDefaultUmaVlanPon(final String idProfileLine) {
        return String.format("ont auto-config name DEFAULT all-ont line %s", idProfileLine);
    }

    /**
     * Retorna o script de configuração automática de ONTs.
     *
     * @return Lista de strings com os comandos de configuração automática.
     */
    public List<String> autoConfig() {
        final List<String> autoConfig = new ArrayList<>();
        autoConfig.add("ont auto-config");
        autoConfig.add("ont-find interface gpon all");
        return autoConfig;
    }

}
