package engtelecom.enumCodes;

public enum BridgeInterfaceUplinkVlanMode {
    TAGGED("tagged"),
    UNTAGGED("untagged");

    private final String displayName;

    BridgeInterfaceUplinkVlanMode(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}