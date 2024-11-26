package priceCompare.backend.enums;

import lombok.Getter;

@Getter
public enum LocationName {
    TALLINN("Tallinn"),
    TARTU("Tartu"),
    TURI("Türi"),
    NARVA("Narva"),
    PARNU("Pärnu"),
    KOHTLA_JARVE("Kohtla-Järve"),
    VILJANDI("Viljandi"),
    VORU("Võru"),
    RAKVERE("Rakvere"),
    SILLAMAE("Sillamäe"),
    MAARDU("Maardu"),
    KURESSAARE("Kuressaare"),
    VALGA("Valga"),
    VASTSELIINA("Vastseliina"),
    HAAPSALU("Haapsalu"),
    KEILA("Keila"),
    POLVA("Põlva"),
    JOGEVA("Jõgeva"),
    MUHU("Muhu"),
    JOHVI("Jõhvi"),
    RAPLA("Rapla"),
    RAPINA("Räpina"),
    PAIDE("Paide"),
    POLTSAMAA("Põltsamaa"),
    LAAGRI("Laagri"),
    LOKSA("Loksa"),
    EPOOD("Epood");

    private final String displayName;

    LocationName(String displayName) {
        this.displayName = displayName;
    }
}