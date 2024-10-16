package priceCompare.backend.enums;

import lombok.Getter;

@Getter
public enum Unit {
    PAKK("pakk"),
    PAAR("paar"),
    PURK("purk"),
    M2("m2"),
    M("m"),
    TK("tk"),
    RULL("rull"),
    KOMPL("kompl.");

    private final String displayName;

    Unit(String displayName) {
        this.displayName = displayName;
    }
    public static Unit fromDisplayName(String displayName) {
        for (Unit unit : Unit.values()) {
            if (unit.getDisplayName().equalsIgnoreCase(displayName)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid unit display name: " + displayName);
    }
}