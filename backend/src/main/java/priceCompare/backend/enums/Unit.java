package priceCompare.backend.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@JsonIgnoreProperties({"aliases"})
@Getter
public enum Unit {
    PAKK("pakk", List.of("pk")),
    PAAR("paar", List.of()),
    PURK("purk", List.of()),
    M2("m2", List.of()),
    M("m", List.of()),
    TK("tk", List.of("tükk")),
    RULL("rull", List.of()),
    KOTT("kott", List.of()),
    KOMPL("kompl", List.of("kmpl"));

    private final String displayName;
    private final List<String> aliases;

    Unit(String displayName, List<String> aliases) {
        this.displayName = displayName;
        this.aliases = aliases;
    }
    public static Unit fromDisplayName(String displayName) {
        displayName = displayName.replaceAll("[$£€='.!?\"\\\\-]", "");

        for (Unit unit : Unit.values()) {
            if (unit.getDisplayName().equalsIgnoreCase(displayName)) {
                return unit;
            }

            // check if aliases match
            for(String alias : unit.getAliases()) {
                if (alias.equalsIgnoreCase(displayName)) {
                    return unit;
                }
            }
        }
        throw new IllegalArgumentException("Invalid unit display name: " + displayName);
    }
}