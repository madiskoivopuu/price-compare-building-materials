package priceCompare.backend.stores.bauhaus.service;

import lombok.Getter;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.enums.LocationName;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum BauhausStoreLocation {
    ROCCA_AL_MARE("Rocca al Mare", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Kaeravälja tn 3")
            .build()),
    LASNAMAE("Lasnamäe", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Tähesaju tee 8")
            .build());
    private static final Map<String, BauhausStoreLocation> SOURCE_NAME_MAP = new HashMap<>();

    static {
        for (BauhausStoreLocation location : BauhausStoreLocation.values()) {
            SOURCE_NAME_MAP.put(location.sourceName, location);
        }
    }

    private final String sourceName;
    private final LocationDto location;

    BauhausStoreLocation(String sourceName, LocationDto location) {
        this.sourceName = sourceName;
        this.location = location;
    }

    public static BauhausStoreLocation fromSourceName(String sourceName) {
        return SOURCE_NAME_MAP.get(sourceName);
    }

    public static LocationDto locationFromSourceName(String sourceName) {
        BauhausStoreLocation locationEnum = fromSourceName(sourceName);
        if (locationEnum != null) {
            return locationEnum.getLocation();
        }
        return null;
    }
}

