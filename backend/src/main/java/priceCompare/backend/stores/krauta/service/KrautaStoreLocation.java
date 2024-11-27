package priceCompare.backend.stores.krauta.service;

import lombok.Getter;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.enums.LocationName;

import java.util.HashMap;
import java.util.Map;

public enum KrautaStoreLocation {
    HAABERSTI("Haabersti", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Paldiski mnt 108a")
            .googleMapsLink("https://www.google.com/maps/place/K-Rauta+Haabersti/@59.4219792,24.6385385,771m/data=!3m2!1e3!4b1!4m6!3m5!1s0x469294398e146a4b:0xc7293079aa4cec40!8m2!3d59.4219765!4d24.6411134!16s%2Fg%2F11cn17_stv?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    TONDI("Tondi", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Tammsaare tee 49")
            .googleMapsLink("https://www.google.com/maps/place/K-Rauta+Tondi/@59.4014499,24.7118455,772m/data=!3m2!1e3!4b1!4m6!3m5!1s0x469294e6ed7f335b:0x2f503b0b7ca65489!8m2!3d59.4014472!4d24.7144204!16s%2Fg%2F11b8c5j963?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    RAKVERE("Rakvere", LocationDto.builder()
            .locationName(LocationName.RAKVERE)
            .address("Haljala tee 4, Põhjakeskus")
            .googleMapsLink("https://www.google.com/maps/place/K-Rauta/@59.3639722,26.3387302,1095m/data=!3m1!1e3!4m6!3m5!1s0x469375bbc908300f:0xbc0dd8c7f0accf30!8m2!3d59.3648362!4d26.3382886!16s%2Fg%2F11f08w0yh9?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    TARTU("Tartu", LocationDto.builder()
            .locationName(LocationName.TARTU)
            .address("Riia mnt 140e")
            .address("https://www.google.com/maps/place/K-Rauta+Tartu+parkla/@58.3564396,26.681379,795m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46eb3722d8182bb1:0x8a3de1cf81ecbf89!8m2!3d58.3564369!4d26.6862499!16s%2Fg%2F11pf7dfdxm?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    PARNU("Papiniidu", LocationDto.builder()
            .locationName(LocationName.PARNU)
            .address("Papiniidu 2a")
            .googleMapsLink("https://www.google.com/maps/place/K-Rauta+P%C3%A4rnu/@58.3728519,24.5506304,795m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46ecfceabd19d8ab:0x2ca3389b631c9c9c!8m2!3d58.3728491!4d24.5532053!16s%2Fg%2F11c6rj9tl4?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    KURESSAARE("Kuressaare", LocationDto.builder()
            .locationName(LocationName.KURESSAARE)
            .address("Tallinna mnt 88, Auriga keskus")
            .googleMapsLink("https://www.google.com/maps/place/K-Rauta+Kuressaare/@58.2650042,22.5139995,798m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46f26da7d5e53a71:0x9ae5b88a940148ef!8m2!3d58.2650014!4d22.5165744!16s%2Fg%2F11cr_hb906?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    VORU("Kreutzwaldi", LocationDto.builder()
            .locationName(LocationName.VORU)
            .address("Kreutzwaldi 89")
            .googleMapsLink("https://www.google.com/maps/place/K-Rauta+V%C3%B5ru/@57.8336095,27.0050965,807m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46eafaeee8d1ea21:0x4c0eb816dd23f8cc!8m2!3d57.8336067!4d27.0076714!16s%2Fg%2F11bv2l_gx9?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    VALGA("Valga", LocationDto.builder()
            .locationName(LocationName.VALGA)
            .address("Tartu 84")
            .googleMapsLink("https://www.google.com/maps/place/K-Rauta+Valga/@57.7894459,26.0476634,808m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46eba17d4eca3087:0x778dba55baf2e12e!8m2!3d57.7894431!4d26.0502383!16s%2Fg%2F11bwqmtmxr?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build());

    private final String sourceName;
    @Getter
    private final LocationDto location;

    KrautaStoreLocation(String sourceName, LocationDto location) {
        this.sourceName = sourceName;
        this.location = location;
    }

    public static KrautaStoreLocation fromSourceName(String sourceName) {
        for (KrautaStoreLocation locationEnum : KrautaStoreLocation.values()) {
            if (sourceName.contains(locationEnum.sourceName)) {
                return locationEnum;
            }
        }
        return null;
    }

    public static LocationDto locationFromSourceName(String sourceName) {
        KrautaStoreLocation locationEnum = fromSourceName(sourceName);
        return locationEnum != null ? locationEnum.getLocation() : null;
    }
}