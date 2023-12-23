package engtelecom.scripts;

import java.util.ArrayList;
import java.util.List;

public class ScriptsG16 {
    public ScriptsG16() {
    }

    /**
     * Retorna o script do profile DBA
     * 
     * @return Listra de strings do profile DBA
     */
    public List<String> profileDba() {
        List<String> dba = new ArrayList<>();
        dba.add("deploy profile dba");
        dba.add("aim 1 name DBA-DEFAULT");
        dba.add("type 4 max 1200000");
        dba.add("active");
        dba.add("exit");
        return dba;
    }

    /**
     * Retorna o formato para a criação de vlans na olt
     * 
     * @param vlanRange numero da vlan a ser criada
     * @return String formatada no padrão do codigo da olt
     */
    public String vlan(String vlanRange) {
        return String.format("vlan %s", vlanRange);
    }

    /**
     * Retorna uma lista contendo a sequencia de criação do interface hybrid tagged
     * 
     * @param ethMgr nome da interface eth 
     * @param vlanRange numero da vlan tagged
     * @return retorna a lista de strings formatada 
     */
    public List<String> interfaceMgr(String ethMgr, String vlanRange) {
        List<String> mgrInterface = new ArrayList<>();
        mgrInterface.add(ethMgr);
        mgrInterface.add("switchport mode hybrid");
        mgrInterface.add(String.format("switchport hybrid tagged vlan %s", vlanRange));
        mgrInterface.add("exit");
        // mgrInterface.add("exit");
        return mgrInterface;
    }

    /**
     * Retorna o script do deploy profile vlan
     * 
     * @param vlanRange Nmero da vlan
     * @param idProfile Id do profile vlan
     * @return Lista de strings contendo o script do profile vlan
     */
    public List<String> profileVlan(String vlanRange, String idProfile) {
        List<String> vlan = new ArrayList<>();
        vlan.add("deploy profile vlan");
        vlan.add(String.format("aim %s name %s", idProfile, vlanRange));
        vlan.add(String.format("translate old-vlan %s new-vlan %s", vlanRange, vlanRange));
        vlan.add("active");
        vlan.add("exit");
        return vlan;
    }

    /**
     * Retorna o script do deploy profile line BRIDGE
     * 
     * @param vlanRange id da vlan
     * @param idProfileLine id do profile line 
     * @param idProfileVlan id do profile vlan da vlan escolhida
     * @param deviceType device type da ont em questão
     * @return Lista de strings contendo o script do profile line
     */
    public List<String> profileLineBridge(String vlanRange, String idProfileLine, String idProfileVlan,
            String deviceType) {
        List<String> line = new ArrayList<>();
        line.add("deploy profile line");
        line.add(String.format("aim %s", idProfileLine));
        line.add(String.format("device type %s", deviceType));
        line.add("tcont 1 profile dba 1");
        line.add(String.format("gemport 1 tcont 1 vlan-profile %s", idProfileVlan));
        line.add("mapping mode port-vlan");
        line.add(String.format("mapping 1 port eth 1 vlan %s gemport 1", vlanRange));
        line.add(String.format("flow 1 port eth 1 default vlan %s", vlanRange));
        line.add("active");
        line.add("exit");
        return line;
    }

    /**
     * Retorna o script do deploy profile line ROUTER
     * 
     * @param vlanRange id da vlan
     * @param idProfileLine id do profile line
     * @param idProfileVlan id do profile vlan da vlan escolhida
     * @param deviceType device type da ont em questão
     * @return Lista de strings contendo o script do profile line
     */
    public List<String> profileLineRouter(String vlanRange, String idProfileLine, String idProfileVlan,
            String deviceType) {
        List<String> line = new ArrayList<>();
        line.add("deploy profile line");
        line.add(String.format("aim %s", idProfileLine));
        line.add(String.format("device type %s", deviceType));
        line.add("tcont 1 profile dba 1");
        line.add(String.format("gemport 1 tcont 1 vlan-profile %s", idProfileVlan));
        line.add("mapping mode port-vlan");
        line.add(String.format("mapping 1 port veip vlan %s gemport 1", vlanRange));
        line.add(String.format("flow 1 port veip vlan %s keep", vlanRange));
        line.add("active");
        line.add("exit");
        return line;
    }

}
