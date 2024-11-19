package priceCompare.backend.stores.puumarket.service;

import lombok.Getter;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.enums.LocationName;
import priceCompare.backend.stores.bauhof.service.BauhofStoreLocation;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum PuumaterjalStoreLocation {
    TALLINN_1("Mustamäe", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Laki 3a")
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
            .locationName(LocationName.PARNU)
            .address("Lina 24")
            .build())
    ;


    private static final Map<String, PuumaterjalStoreLocation> SOURCE_NAME_MAP = new HashMap<>();

    static {
        for (PuumaterjalStoreLocation location : PuumaterjalStoreLocation.values()) {
            SOURCE_NAME_MAP.put(location.sourceName, location);
        }
    }

    private final String sourceName;
    private final LocationDto location;

    PuumaterjalStoreLocation(String sourceName, LocationDto location) {
        this.sourceName = sourceName;
        this.location = location;
    }

    public static PuumaterjalStoreLocation fromSourceName(String sourceName) {
        return SOURCE_NAME_MAP.get(sourceName);
    }

    public static LocationDto locationFromSourceName(String sourceName) {
        PuumaterjalStoreLocation locationEnum = fromSourceName(sourceName);
        if (locationEnum != null) {
            return locationEnum.getLocation();
        }
        return null;
    }
}
