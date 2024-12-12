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
            .googleMapsLink("https://www.google.com/maps/place/BAUHAUS+Rocca+al+Mare/@59.4255398,24.6377067,1838m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4692943b8e4371d3:0xcfbca4abaa19cbc7!8m2!3d59.4255371!4d24.6402816!16s%2Fg%2F11c2plz1kn?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    LASNAMAE("Lasnamäe", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Tähesaju tee 8")
            .googleMapsLink("https://www.google.com/maps/place/BAUHAUS+Lasnam%C3%A4e/@59.4420615,24.8543521,879m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4692ecea77a1f955:0x4862858474e57530!8m2!3d59.4420588!4d24.856927!16s%2Fg%2F1thcq_jc?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
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

