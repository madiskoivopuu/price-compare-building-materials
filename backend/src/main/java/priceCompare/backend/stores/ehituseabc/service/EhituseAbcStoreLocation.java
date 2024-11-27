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
            .googleMapsLink("https://www.google.com/maps/place/Ehituse+ABC+P%C3%A4rnu+mnt/@59.3979569,24.7226809,772m/data=!3m2!1e3!4b1!4m6!3m5!1s0x469295e24465bf49:0x97d714531fe10f2b!8m2!3d59.3979542!4d24.7252558!16s%2Fg%2F11ll_f1t93?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    LASNAMAE("Tallinn, Peterburi tee 71", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Peterburi tee 71")
            .googleMapsLink("https://www.google.com/maps/place/Ehituse+ABC+Tallinn,+Peterburi+tee+71/@59.4309885,24.8360418,771m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4692ec56cbfa8971:0xbf0888bc915a6db3!8m2!3d59.4309858!4d24.8386167!16s%2Fg%2F1tdflybk?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    MUSTAMAE("Tallinn, Kadaka tee 65", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Kadaka tee 65")
            .googleMapsLink("https://www.google.com/maps/place/Ehituse+ABC+Tallinn,+Kadaka+tee+65/@59.4144027,24.6697134,772m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4692945a6c9d81a1:0x88df9cc0f8ad9117!8m2!3d59.4144001!4d24.6745843!16s%2Fg%2F11bytsk17s?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    TARTU("Tartu, Kalda tee 3", LocationDto.builder()
            .locationName(LocationName.TARTU)
            .address("Kalda tee 3")
            .googleMapsLink("https://www.google.com/maps/place/Ehituse+ABC+Tartu/@58.3722045,26.7524944,795m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46eb36d412b33839:0x1d9ac4931c36ba00!8m2!3d58.3722017!4d26.7550693!16s%2Fg%2F11bwqfxwcb?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    PARNU("Pärnu, Riia mnt 108b", LocationDto.builder()
            .locationName(LocationName.PARNU)
            .address("Riia mnt 108b")
            .googleMapsLink("https://www.google.com/maps/place/Ehituse+ABC+P%C3%A4rnu,+Riia+mnt+108b/@58.3689815,24.5497806,795m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46ecfc94b3f94403:0x29c770bfba881b11!8m2!3d58.3689787!4d24.5523555!16s%2Fg%2F11cmt5m08k?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    RAKVERE("Rakvere, Võidu 93", LocationDto.builder()
            .locationName(LocationName.RAKVERE)
            .address("Võidu 93")
            .googleMapsLink("https://www.google.com/maps/place/Ehituse+ABC+Rakvere,+V%C3%B5idu+93/@59.3445061,26.373024,773m/data=!3m2!1e3!4b1!4m6!3m5!1s0x469375d581c8206d:0xaf6f4d056c689131!8m2!3d59.3445034!4d26.3755989!16s%2Fg%2F11gd_d892d?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    JOHVI("Jõhvi, Tartu mnt 14", LocationDto.builder()
            .locationName(LocationName.JOHVI)
            .address("Tartu mnt 14")
            .googleMapsLink("https://www.google.com/maps/place/Ehituse+ABC+J%C3%B5hvi,+Tartu+mnt+14/@59.354044,27.4067993,773m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4694657c9f76f509:0xd5d043d63a0257d0!8m2!3d59.3540413!4d27.4093742!16s%2Fg%2F11bxc7rjs2?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    NARVA("Narva, Rahu 4", LocationDto.builder()
            .locationName(LocationName.NARVA)
            .address("Rahu 4")
            .googleMapsLink("https://www.google.com/maps/place/Ehituse+ABC+Narva,+Rahu+4/@59.375099,28.1529872,772m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4694481009486275:0x7e0e67bf4bb8c30d!8m2!3d59.3750963!4d28.1555621!16s%2Fg%2F11g93h7dxf?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    NARVA_FAMA("Narva, Fama keskus", LocationDto.builder()
            .locationName(LocationName.NARVA)
            .address("Fama keskus")
            .googleMapsLink("https://www.google.com/maps/place/Ehituse+ABC+Narva,+Fama+keskus/@59.3805216,28.1851368,772m/data=!3m2!1e3!4b1!4m6!3m5!1s0x469449ad3967b98f:0x2d3933a084d6e2ae!8m2!3d59.3805189!4d28.1877117!16s%2Fg%2F11g0h1yn7w?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    KOHTLA_JARVE("Kohtla-Järve, Järveküla tee 50", LocationDto.builder()
            .locationName(LocationName.KOHTLA_JARVE)
            .address("Järveküla tee 50")
            .googleMapsLink("https://www.google.com/maps/place/Ehituse+ABC+Kohtla-J%C3%A4rve,+J%C3%A4rvek%C3%BCla+tee+50/@59.3971524,27.2836356,772m/data=!3m2!1e3!4b1!4m6!3m5!1s0x469471931da6e493:0xb26457573298ff8d!8m2!3d59.3971497!4d27.2862105!16s%2Fg%2F11h_2cjns8?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    HAAPSALU("Haapsalu, Tallinna mnt 81", LocationDto.builder()
            .locationName(LocationName.HAAPSALU)
            .address("Tallinna mnt 81")
            .googleMapsLink("https://www.google.com/maps/place/Ehituse+ABC/@58.9391657,23.5729774,782m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46ed5878ae6958fd:0x5a3f8936cff07340!8m2!3d58.939163!4d23.5755523!16s%2Fg%2F11c54hdw5n?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    LOKSA("Loksa, Tallinna 40", LocationDto.builder()
            .locationName(LocationName.LOKSA)
            .address("Tallinna 40")
            .googleMapsLink("https://www.google.com/maps/place/Ehituse+ABC+Loksa/@59.5789915,25.7197257,768m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4693aad8059d7193:0x2141dc75c84b7f0b!8m2!3d59.5789889!4d25.7245966!16s%2Fg%2F11d_td5kx9?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
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

