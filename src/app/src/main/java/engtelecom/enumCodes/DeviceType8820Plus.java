package engtelecom.enumCodes;

public enum DeviceType8820Plus {
    TYPE_110("110"),
    TYPE_110B("110b"),
    TYPE_110G("110g"),
    TYPE_R1("r1"),
    TYPE_121W("121w"),
    TYPE_142NG("142ng"),
    TYPE_142NW("142nw"),
    TYPE_1420G("1420g"),
    TYPE_120AC("120ac"),
    TYPE_121AC("121ac"),
    TYPE_1200R("1200r"),
    TYPE_AX1800("ax1800"),
    TYPE_AX1800V("ax1800v"),
    TYPE_DEFAULT("default");

    private final String displayName;

    DeviceType8820Plus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
