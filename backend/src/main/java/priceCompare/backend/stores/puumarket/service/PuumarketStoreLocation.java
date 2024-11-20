package priceCompare.backend.stores.puumarket.service;

import lombok.Getter;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.enums.LocationName;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum PuumarketStoreLocation {
    TALLINN_1("Mustamäe", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Laki 3a")
            .build()),
    TALLINN_1_VALISLADU("Välisladu", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Laki 3a (välisladu)")
            .build()),
    TALLINN_2("Männiku", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Väike-Männiku 11")
            .build()),
    RAKVERE("Rakvere", LocationDto.builder()
            .locationName(LocationName.RAKVERE)
            .address("Ringtee 3")
            .build()),
    PARNU("Pärnu", LocationDto.builder()
            .locationName(LocationName.PARNU)
            .address("Lina 24")
            .build()),
    TARTU("Tartu", LocationDto.builder()
            .locationName(LocationName.TARTU)
            .address("Ringtee 37b")
            .build())
    ;


    private static final Map<String, PuumarketStoreLocation> SOURCE_NAME_MAP = new HashMap<>();

    static {
        for (PuumarketStoreLocation location : PuumarketStoreLocation.values()) {
            SOURCE_NAME_MAP.put(location.sourceName, location);
        }
    }

    private final String sourceName; // location name as shown on the website
    private final LocationDto location;

    PuumarketStoreLocation(String sourceName, LocationDto location) {
        this.sourceName = sourceName;
        this.location = location;
    }

    public static PuumarketStoreLocation fromSourceName(String sourceName) {
        return SOURCE_NAME_MAP.get(sourceName);
    }

    public static LocationDto locationFromSourceName(String sourceName) {
        PuumarketStoreLocation locationEnum = fromSourceName(sourceName);
        if (locationEnum != null) {
            return locationEnum.getLocation();
        }
        return null;
    }
}
