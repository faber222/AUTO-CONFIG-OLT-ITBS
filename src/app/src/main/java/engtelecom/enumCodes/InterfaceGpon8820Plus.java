package engtelecom.enumCodes;

public enum InterfaceGpon8820Plus {
    GPON_1("gpon 1"),
    GPON_2("gpon 2"),
    GPON_3("gpon 3"),
    GPON_4("gpon 4"),
    GPON_5("gpon 5"),
    GPON_6("gpon 6"),
    GPON_7("gpon 7"),
    GPON_8("gpon 8");

    private final String displayName;

    InterfaceGpon8820Plus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
