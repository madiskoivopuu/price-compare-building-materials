package priceCompare.backend.enums;

public enum LocationName {
    TALLINN("Tallinn"),
    TARTU("Tartu"),
    NARVA("Narva"),
    PÄRNU("Pärnu"),
    KOHTLA_JARVE("Kohtla-Järve"),
    VILJANDI("Viljandi"),
    RAKVERE("Rakvere"),
    SILLAMAE("Sillamäe"),
    MAARDU("Maardu"),
    KURESSAARE("Kuressaare"),
    VORU("Võru"),
    VALGA("Valga"),
    JOHVI("Jõhvi"),
    POLVA("Põlva"),
    HAAPSALU("Haapsalu");

    private final String displayName;

    LocationName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}