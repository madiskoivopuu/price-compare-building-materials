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
            .googleMapsLink("https://www.google.ee/maps/place/Puumarket+Mustam%C3%A4e/@59.4211205,24.6849093,193m/data=!3m1!1e3!4m15!1m8!3m7!1s0x4692945e5b8d2c51:0xcf4c2129b3ec0a05!2sForelli+10a,+10621+Tallinn!3b1!8m2!3d59.4203343!4d24.6875692!16s%2Fg%2F11b8z0md1d!3m5!1s0x4692945e58b4fbdd:0x31bce157aa080c8a!8m2!3d59.4211253!4d24.6854243!16s%2Fg%2F1tykt0vg?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    TALLINN_1_VALISLADU("Välisladu", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Laki 3a (välisladu)")
            .googleMapsLink("https://www.google.ee/maps/place/Puumarket+Mustam%C3%A4e/@59.4211205,24.6849093,193m/data=!3m1!1e3!4m15!1m8!3m7!1s0x4692945e5b8d2c51:0xcf4c2129b3ec0a05!2sForelli+10a,+10621+Tallinn!3b1!8m2!3d59.4203343!4d24.6875692!16s%2Fg%2F11b8z0md1d!3m5!1s0x4692945e58b4fbdd:0x31bce157aa080c8a!8m2!3d59.4211253!4d24.6854243!16s%2Fg%2F1tykt0vg?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    TALLINN_2("Männiku", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Väike-Männiku 11")
            .googleMapsLink("https://www.google.ee/maps/place/V%C3%A4ike-M%C3%A4nniku+tn+11,+11216+Tallinn/@59.3675772,24.7183856,287m/data=!3m1!1e3!4m6!3m5!1s0x4692953602d82631:0xe4c395e12c9a28bb!8m2!3d59.3677966!4d24.7188497!16s%2Fg%2F11b8v596vv?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    RAKVERE("Rakvere", LocationDto.builder()
            .locationName(LocationName.RAKVERE)
            .address("Ringtee 3")
            .googleMapsLink("https://www.google.ee/maps/place/Ringtee+3,+Rakvere,+44314+L%C3%A4%C3%A4ne-Viru+maakond/@59.3504304,26.339294,336m/data=!3m1!1e3!4m6!3m5!1s0x469375caea839569:0x963d1532761d214a!8m2!3d59.3506017!4d26.3393119!16s%2Fg%2F11cpckrb4q?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    PARNU("Pärnu", LocationDto.builder()
            .locationName(LocationName.PARNU)
            .address("Lina 24")
            .googleMapsLink("https://www.google.ee/maps/place/Lina+tn+24,+P%C3%A4rnu,+80010+P%C3%A4rnu+maakond/@58.3994543,24.525302,562m/data=!3m1!1e3!4m6!3m5!1s0x46ecfd2c6bc90b0d:0xbe5bc40dfcc510eb!8m2!3d58.3995391!4d24.5252612!16s%2Fg%2F11cp8m0r_2?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    TARTU("Tartu", LocationDto.builder()
            .locationName(LocationName.TARTU)
            .address("Ringtee 37b")
            .googleMapsLink("https://www.google.ee/maps/place/Puumarket+Tartu/@58.3423821,26.7361455,474m/data=!3m1!1e3!4m15!1m8!3m7!1s0x46eb30b468c74bf1:0x396634cc1b924356!2sRingtee+37c,+50105+Tartu!3b1!8m2!3d58.3414991!4d26.7363897!16s%2Fg%2F11bw4ck81p!3m5!1s0x46eb30b468c714b7:0xb7d757f49534e9eb!8m2!3d58.3425099!4d26.7360492!16s%2Fg%2F1ptzjlx9g?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
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
