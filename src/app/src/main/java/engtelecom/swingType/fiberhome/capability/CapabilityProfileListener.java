/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package engtelecom.swingType.fiberhome.capability;

/**
 *
 * @author faber222
 */
public interface CapabilityProfileListener {
    void onProfileCreated(String cpeType, String ponType, String wifiNumber,
            String tenGPort, String oneGPort, String equipamentID,
            String potsNumber, String profileName, String usbNumber);

}
