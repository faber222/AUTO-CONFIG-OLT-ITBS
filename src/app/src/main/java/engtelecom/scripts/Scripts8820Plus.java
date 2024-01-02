package engtelecom.scripts;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que fornece scripts relacionados à configuração de uma OLT G16 e G08.
 */
public class Scripts8820Plus {
    /**
     * Construtor padrão
     */
    public Scripts8820Plus() {
    }

    public String bridgeUplink(final String vlanRange, final String interfaceEth,
            final String bridgeInterfaceUplink) {
        return String.format("bridge add %s %s vlan %s tagged", interfaceEth, bridgeInterfaceUplink, vlanRange);
    }

    public String bridgeProfile(final String vlanRange, final String gponId) {
        return String.format("bridge-profile add gpon%s-default downlink vlan %s tagged eth 1", gponId, vlanRange);
    }

    public String bridgeProfileRouter(final String vlanRange, final String gponId) {
        return String.format("bridge-profile add gpon%s-default-router downlink vlan %s tagged router", gponId,
                vlanRange);
    }

    public String bridgeProfileBind(final String gponId, final String deviceType, final String interfaceGpon) {
        return String.format("bridge-profile bind add gpon%s-default device intelbras-%s %s", gponId, deviceType,
                interfaceGpon);
    }

    public String bridgeProfileBindRouter(final String gponId, final String deviceType, final String interfaceGpon) {
        return String.format("bridge-profile bind add gpon%s-default-router device intelbras-%s %s", gponId, deviceType,
                interfaceGpon);
    }

    public String bridgeProfile(final String vlanRange) {
        return String.format("bridge-profile add default downlink vlan %s tagged eth 1", vlanRange);
    }

    public String bridgeProfileRouter(final String vlanRange) {
        return String.format("bridge-profile add default-router downlink vlan %s tagged router", vlanRange);
    }

    public String bridgeProfileBind(final String deviceType) {
        return String.format("bridge-profile bind add default device intelbras-%s", deviceType);
    }

    public String bridgeProfileBindRouter(final String deviceType) {
        return String.format("bridge-profile bind add default-router device intelbras-%s", deviceType);
    }

    /**
     * Retorna o script de configuração automática de ONTs.
     *
     * @return Lista de strings com os comandos de configuração automática.
     */
    public List<String> autoConfig() {
        final List<String> autoConfig = new ArrayList<>();
        autoConfig.add("onu set auto");
        autoConfig.add("auto-service enable");
        autoConfig.add("yes");
        autoConfig.add("onu show refresh");
        return autoConfig;
    }

}
