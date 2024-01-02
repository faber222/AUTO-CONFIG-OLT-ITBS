package engtelecom.enumCodes;

public enum ConfigurationType8820Plus {
    ONE_VLAN_PER_PON("One VLAN per PON"),
    ONLY_ONE_VLAN("Only one VLAN");

    private final String displayName;

    ConfigurationType8820Plus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
