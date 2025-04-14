package engtelecom.swingType.cutoverFhtt.destino;

import java.util.ArrayList;

public interface OltCutoverFormDestinoListener {
    void onProfileFormDestinoCreated(ArrayList<String[]> oltNodos, ArrayList<String[]> uplinkNodos);
}
