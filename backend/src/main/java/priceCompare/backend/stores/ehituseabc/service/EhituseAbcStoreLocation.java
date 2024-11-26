package priceCompare.backend.stores.ehituseabc.service;

import lombok.Getter;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.enums.LocationName;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum EhituseAbcStoreLocation {
    JARVE("Tallinn, Pärnu mnt 186", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Pärnu mnt 186")
            .build()),
    LASNAMAE("Tallinn, Peterburi tee 71", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Peterburi tee 71")
            .build()),
    MUSTAMAE("Tallinn, Kadaka tee 65", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Kadaka tee 65")
            .build()),
    TARTU("Tartu, Kalda tee 3", LocationDto.builder()
            .locationName(LocationName.TARTU)
            .address("Kalda tee 3")
            .build()),
    PARNU("Pärnu, Riia mnt 108b", LocationDto.builder()
            .locationName(LocationName.PARNU)
            .address("Riia mnt 108b")
            .build()),
    RAKVERE("Rakvere, Võidu 93", LocationDto.builder()
            .locationName(LocationName.RAKVERE)
            .address("Papiniidu 5a/2")
            .build()),
    JOHVI("Jõhvi, Tartu mnt 14", LocationDto.builder()
            .locationName(LocationName.JOHVI)
            .address("Reinu tee 29a")
            .build()),
    NARVA("Narva, Rahu 4", LocationDto.builder()
            .locationName(LocationName.NARVA)
            .address("Rahu 4")
            .build()),
    NARVA_FAMA("Narva, Fama keskus", LocationDto.builder()
            .locationName(LocationName.NARVA)
            .address("Fama keskus")
            .build()),
    KOHTLA_JARVE("Kohtla-Järve, Järveküla tee 50", LocationDto.builder()
            .locationName(LocationName.KOHTLA_JARVE)
            .address("Järveküla tee 50")
            .build()),
    HAAPSALU("Haapsalu, Tallinna mnt 81", LocationDto.builder()
            .locationName(LocationName.HAAPSALU)
            .address("Tallinna mnt 81")
            .build()),
    LOKSA("Loksa, Tallinna 40", LocationDto.builder()
            .locationName(LocationName.LOKSA)
            .address("Tallinna 40")
            .build());

    private static final Map<String, EhituseAbcStoreLocation> SOURCE_NAME_MAP = new HashMap<>();

    static {
        for (EhituseAbcStoreLocation location : EhituseAbcStoreLocation.values()) {
            SOURCE_NAME_MAP.put(location.sourceName, location);
        }
    }

    private final String sourceName;
    private final LocationDto location;

    EhituseAbcStoreLocation(String sourceName, LocationDto location) {
        this.sourceName = sourceName;
        this.location = location;
    }

    public static EhituseAbcStoreLocation fromSourceName(String sourceName) {
        return SOURCE_NAME_MAP.get(sourceName);
    }

    public static LocationDto locationFromSourceName(String sourceName) {
        EhituseAbcStoreLocation locationEnum = fromSourceName(sourceName);
        if (locationEnum != null) {
            return locationEnum.getLocation();
        }
        return null;
    }
}

