package priceCompare.backend.enums;

public enum Unit {
    M2("m2"),
    TK("tk");

    private final String displayName;

    Unit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}