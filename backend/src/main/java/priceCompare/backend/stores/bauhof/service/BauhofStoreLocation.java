package priceCompare.backend.stores.bauhof.service;

import lombok.Getter;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.enums.LocationName;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum BauhofStoreLocation {
    LASNAMAE("Lasnamäe Bauhof", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Lasnamäe, J.Smuuli tee 41")
            .build()),
    MUSTAMAE("Mustamäe Bauhof", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Mustamäe, Karjavälja tn 4")
            .build()),
    LAAGRI("Laagri Bauhof", LocationDto.builder()
            .locationName(LocationName.LAAGRI)
            .address("Pärnu mnt 558")
            .build()),
    KEILA("Keila Bauhof", LocationDto.builder()
            .locationName(LocationName.KEILA)
            .address("Paldiski mnt 35")
            .build()),
    TARTU("Tartu Bauhof", LocationDto.builder()
            .locationName(LocationName.TARTU)
            .address("Lääneringtee 27")
            .build()),
    PÄRNU("Pärnu Bauhof", LocationDto.builder()
            .locationName(LocationName.PARNU)
            .address("Papiniidu 5a/2")
            .build()),
    VILJANDI("Viljandi Bauhof", LocationDto.builder()
            .locationName(LocationName.VILJANDI)
            .address("Reinu tee 29a")
            .build()),
    KURESSAARE("Kuressaare Bauhof", LocationDto.builder()
            .locationName(LocationName.KURESSAARE)
            .address("Põlluvahe tee 2")
            .build()),
    JOHVI("Jõhvi Bauhof", LocationDto.builder()
            .locationName(LocationName.JOHVI)
            .address("Narva mnt 141")
            .build()),
    RAKVERE("Rakvere Bauhof", LocationDto.builder()
            .locationName(LocationName.RAKVERE)
            .address("Ringtee 2")
            .build()),
    HAAPSALU("Haapsalu Bauhof", LocationDto.builder()
            .locationName(LocationName.HAAPSALU)
            .address("Tallinna mnt 68")
            .build()),
    POLVA("Põlva Bauhof", LocationDto.builder()
            .locationName(LocationName.POLVA)
            .address("Jaama 12a")
            .build()),
    VALGA("Valga Bauhof", LocationDto.builder()
            .locationName(LocationName.VALGA)
            .address("Viljandi 80a")
            .build());

    private static final Map<String, BauhofStoreLocation> SOURCE_NAME_MAP = new HashMap<>();

    static {
        for (BauhofStoreLocation location : BauhofStoreLocation.values()) {
            SOURCE_NAME_MAP.put(location.sourceName, location);
        }
    }

    private final String sourceName;
    private final LocationDto location;

    BauhofStoreLocation(String sourceName, LocationDto location) {
        this.sourceName = sourceName;
        this.location = location;
    }

    public static BauhofStoreLocation fromSourceName(String sourceName) {
        return SOURCE_NAME_MAP.get(sourceName);
    }

    public static LocationDto locationFromSourceName(String sourceName) {
        BauhofStoreLocation locationEnum = fromSourceName(sourceName);
        if (locationEnum != null) {
            return locationEnum.getLocation();
        }
        return null;
    }
}

