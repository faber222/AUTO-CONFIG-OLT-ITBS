/**
 * @author faber222
 * @since 2024
*/

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

    /**
     * Gera o script para adicionar uma uplink para uma bridge.
     *
     * @param vlanRange             Números de VLANs a serem configuradas.
     * @param interfaceEth          Nome da interface Ethernet.
     * @param bridgeInterfaceUplink Nome da interface de uplink da bridge.
     * @return O script para adicionar uma uplink para uma bridge.
     */
    public String bridgeUplink(final String vlanRange, final String interfaceEth,
            final String bridgeInterfaceUplink) {
        return String.format("bridge add %s %s vlan %s tagged", interfaceEth, bridgeInterfaceUplink, vlanRange);
    }

    /**
     * Gera o script para adicionar um perfil de bridge padrão.
     *
     * @param vlanRange  Números de VLANs a serem configuradas.
     * @param bridgeType Downlink, TLS ou Intralink
     * @return O script para adicionar um perfil de bridge padrão.
     */
    public String bridgeProfile(final String vlanRange, final String bridgeType) {
        return String.format("bridge-profile add default %s vlan %s tagged eth 1", bridgeType, vlanRange);
    }

    /**
     * Gera o script para adicionar um perfil de bridge.
     *
     * @param vlanRange  Números de VLANs a serem configuradas.
     * @param gponId     Identificador da porta GPON.
     * @param bridgeType Downlink, TLS ou Intralink
     * @return O script para adicionar um perfil de bridge.
     */
    public String bridgeProfile(final String vlanRange, final String gponId, final String bridgeType) {
        return String.format("bridge-profile add gpon%s-default %s vlan %s tagged eth 1", gponId,
                bridgeType, vlanRange);
    }

    /**
     * Gera o script para adicionar um perfil de bridge padrão para roteador.
     *
     * @param vlanRange  Números de VLANs a serem configuradas.
     * @param bridgeType Downlink, TLS ou Intralink
     * @return O script para adicionar um perfil de bridge padrão para roteador.
     */
    public String bridgeProfileRouter(final String vlanRange, final String bridgeType) {
        return String.format("bridge-profile add default-router %s vlan %s tagged router", bridgeType, vlanRange);
    }

    /**
     * Gera o script para adicionar um perfil de bridge para roteador.
     *
     * @param vlanRange  Números de VLANs a serem configuradas.
     * @param gponId     Identificador da porta GPON.
     * @param bridgeType Downlink, TLS ou Intralink
     * @return O script para adicionar um perfil de bridge para roteador.
     */
    public String bridgeProfileRouter(final String vlanRange, final String gponId, final String bridgeType) {
        return String.format("bridge-profile add gpon%s-default-router %s vlan %s tagged router", gponId,
                bridgeType, vlanRange);
    }

    /**
     * Gera o script para vincular um perfil de bridge.
     *
     * @param gponId        Identificador da porta GPON.
     * @param deviceType    Tipo de dispositivo.
     * @param interfaceGpon Nome da interface GPON.
     * @return O script para vincular um perfil de bridge.
     */
    public String bridgeProfileBind(final String gponId, final String deviceType, final String interfaceGpon) {
        return String.format("bridge-profile bind add gpon%s-default device intelbras-%s %s", gponId, deviceType,
                interfaceGpon);
    }

    /**
     * Gera o script para vincular um perfil de bridge para roteador.
     *
     * @param gponId        Identificador da porta GPON.
     * @param deviceType    Tipo de dispositivo.
     * @param interfaceGpon Nome da interface GPON.
     * @return O script para vincular um perfil de bridge para roteador.
     */
    public String bridgeProfileBindRouter(final String gponId, final String deviceType, final String interfaceGpon) {
        return String.format("bridge-profile bind add gpon%s-default-router device intelbras-%s %s", gponId, deviceType,
                interfaceGpon);
    }

    /**
     * Gera o script para vincular um perfil de bridge padrão.
     *
     * @param deviceType Tipo de dispositivo.
     * @return O script para vincular um perfil de bridge padrão.
     */
    public String bridgeProfileBind(final String deviceType) {
        return String.format("bridge-profile bind add default device intelbras-%s", deviceType);
    }

    /**
     * Gera o script para vincular um perfil de bridge padrão para roteador.
     *
     * @param deviceType Tipo de dispositivo.
     * @return O script para vincular um perfil de bridge padrão para roteador.
     */
    public String bridgeProfileBindRouter(final String deviceType) {
        return String.format("bridge-profile bind add default-router device intelbras-%s", deviceType);
    }

    /**
     * Gera uma lista de comandos para configuração automática.
     *
     * @return Uma lista de comandos para configuração automática.
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
