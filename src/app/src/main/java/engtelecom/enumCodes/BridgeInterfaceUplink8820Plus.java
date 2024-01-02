package engtelecom.enumCodes;

public enum BridgeInterfaceUplink8820Plus {
    UPLINK("uplink"),
    INTRALINK("intralink"),
    TLS("tls");

    private final String displayName;

    BridgeInterfaceUplink8820Plus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
