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
            .googleMapsLink("https://www.google.com/maps/place/Bauhof/@59.4288862,24.8400108,298m/data=!3m1!1e3!4m6!3m5!1s0x4692eca2a8119fd1:0x9fa627cfda3c0091!8m2!3d59.4288673!4d24.8406273!16s%2Fg%2F1ttp8zc_?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    MUSTAMAE("Mustamäe Bauhof", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Mustamäe, Karjavälja tn 4")
            .address("https://www.google.com/maps/place/Bauhof/@59.3419743,24.6119238,882m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4692bfcad22e00f1:0x19b63ad78a72de18!8m2!3d59.3419716!4d24.6144987!16s%2Fg%2F11ckvntd84?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    LAAGRI("Laagri Bauhof", LocationDto.builder()
            .locationName(LocationName.LAAGRI)
            .address("Pärnu mnt 558")
            .googleMapsLink("https://www.google.com/maps/place/Bauhof/@59.3191354,24.4007465,882m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4692bc87c5e8aa75:0x987322c96d0a7cac!8m2!3d59.3191327!4d24.4033214!16s%2Fg%2F11c3s963cd?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    KEILA("Keila Bauhof", LocationDto.builder()
            .locationName(LocationName.KEILA)
            .address("Paldiski mnt 35")
            .googleMapsLink("https://www.google.com/maps/place/Bauhof/@59.42887,24.8380524,879m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4692eca2a8119fd1:0x9fa627cfda3c0091!8m2!3d59.4288673!4d24.8406273!16s%2Fg%2F1ttp8zc_?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    TARTU("Tartu Bauhof", LocationDto.builder()
            .locationName(LocationName.TARTU)
            .address("Lääneringtee 27")
            .googleMapsLink("https://www.google.com/maps/place/Bauhof+Tartu/@58.3610096,26.6743184,397m/data=!3m1!1e3!4m6!3m5!1s0x46eb374b679fbaf9:0x1ade841649a77bb0!8m2!3d58.361178!4d26.6751011!16s%2Fg%2F11cn0t8fg8?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    PÄRNU("Pärnu Bauhof", LocationDto.builder()
            .locationName(LocationName.PARNU)
            .address("Papiniidu 5a/2")
            .googleMapsLink("https://www.google.com/maps/place/Bauhof/@58.3758782,24.5512605,472m/data=!3m1!1e3!4m6!3m5!1s0x46ecfcc04a184a45:0x9e478a5a3009cdac!8m2!3d58.3760366!4d24.551996!16s%2Fg%2F11c0wf5lqs?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    VILJANDI("Viljandi Bauhof", LocationDto.builder()
            .locationName(LocationName.VILJANDI)
            .address("Reinu tee 29a")
            .googleMapsLink("https://www.google.com/maps/place/Bauhof/@58.3527778,25.570837,796m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46ec98f382c27b67:0xdf228ae0025fa6f6!8m2!3d58.352775!4d25.5734119!16s%2Fg%2F11b7tgjt7t?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    KURESSAARE("Kuressaare Bauhof", LocationDto.builder()
            .locationName(LocationName.KURESSAARE)
            .address("Põlluvahe tee 2")
            .googleMapsLink("https://www.google.com/maps/place/Bauhof/@58.2676442,22.5067103,398m/data=!3m1!1e3!4m6!3m5!1s0x46f26da962766c43:0x4d982828a48adf4b!8m2!3d58.2677068!4d22.5079501!16s%2Fg%2F11cp6bxd3t?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    JOHVI("Jõhvi Bauhof", LocationDto.builder()
            .locationName(LocationName.JOHVI)
            .address("Narva mnt 141")
            .googleMapsLink("https://www.google.com/maps/place/Bauhof/@59.3635752,27.4486477,773m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46946f775eef8c81:0xfc8f30cab3d58ae0!8m2!3d59.3635725!4d27.4512226!16s%2Fg%2F11clkw7zqc?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    RAKVERE("Rakvere Bauhof", LocationDto.builder()
            .locationName(LocationName.RAKVERE)
            .address("Ringtee 2")
            .googleMapsLink("https://www.google.com/maps/place/Bauhof/@59.3488356,26.3376091,543m/data=!3m1!1e3!4m6!3m5!1s0x469374354bbd147d:0x6b4e40105eb82a38!8m2!3d59.3491039!4d26.3372971!16s%2Fg%2F11btvl6v_t?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    HAAPSALU("Haapsalu Bauhof", LocationDto.builder()
            .locationName(LocationName.HAAPSALU)
            .address("Tallinna mnt 68")
            .googleMapsLink("https://www.google.com/maps/place/Bauhof/@58.937617,23.5729048,329m/data=!3m1!1e3!4m6!3m5!1s0x46ed587f67385637:0x58bc6840f38b37d2!8m2!3d58.9375968!4d23.5737432!16s%2Fg%2F11bxc64px2?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    POLVA("Põlva Bauhof", LocationDto.builder()
            .locationName(LocationName.POLVA)
            .address("Jaama 12a")
            .googleMapsLink("https://www.google.com/maps/place/Bauhof/@58.0632453,27.070619,401m/data=!3m1!1e3!4m6!3m5!1s0x46eade31ad9af9e5:0x5ef0d5e64d4900c6!8m2!3d58.0632717!4d27.0714695!16s%2Fg%2F1pxwt4k4q?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    VALGA("Valga Bauhof", LocationDto.builder()
            .locationName(LocationName.VALGA)
            .address("Viljandi 80a")
            .googleMapsLink("https://www.google.com/maps/place/Bauhof/@57.7917211,26.0350426,403m/data=!3m1!1e3!4m6!3m5!1s0x46eba17fda8164f1:0xb62b0f648f20623e!8m2!3d57.7918566!4d26.0355159!16s%2Fg%2F11bwqlv7xg?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
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

