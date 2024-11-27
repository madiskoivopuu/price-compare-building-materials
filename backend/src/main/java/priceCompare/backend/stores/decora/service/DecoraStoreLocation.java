package priceCompare.backend.stores.decora.service;

import lombok.Getter;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.enums.LocationName;

@Getter
public enum DecoraStoreLocation {
    TALLINN(LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Laki põik 4")
            .googleMapsLink("https://www.google.com/maps/place/Decora/@59.4109764,24.6727027,772m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46929450dc750d6f:0xbfe155dd1805a172!8m2!3d59.4109764!4d24.6727027!16s%2Fg%2F11cn2mg6bn?hl=en-EE&entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    TARTU(LocationDto.builder()
            .locationName(LocationName.TARTU)
            .address("Riia 193")
            .googleMapsLink("https://www.google.com/maps/place/Decora/@58.354155,26.6766652,796m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46eb3713a449ca63:0x2623eb800d188e81!8m2!3d58.354155!4d26.6766652!16s%2Fg%2F1vc80hmh?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    PARNU(LocationDto.builder()
            .locationName(LocationName.PARNU)
            .address("Suur-Jõe 57/1")
            .googleMapsLink("https://www.google.com/maps/place/Decora/@58.378637,24.5334425,795m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46ecfd2336198995:0x699b32c97e91aa5!8m2!3d58.378637!4d24.5334425!16s%2Fg%2F11g_vrn8p?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    VORU(LocationDto.builder()
            .locationName(LocationName.VORU)
            .address("Lepa 2")
            .googleMapsLink("https://www.google.com/maps/place/Decora/@57.8404777,27.0043741,807m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46eafae7b9141c79:0x89db8189c53b8e70!8m2!3d57.8404777!4d27.0043741!16s%2Fg%2F11g_xj2fn?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    VILJANDI(LocationDto.builder()
            .locationName(LocationName.VILJANDI)
            .address("Leola 53")
            .googleMapsLink("https://www.google.ee/maps/place/Decora/@58.3773271,25.5924493,17z/data=!3m1!4b1!4m5!3m4!1s0x46ec9881f1c0ebe5:0x859a9d4b61178409!8m2!3d58.3773243!4d25.594638?shorturl=1")
            .build()),
    JOGEVA(LocationDto.builder()
            .locationName(LocationName.JOGEVA)
            .address("Puiestee 38")
            .googleMapsLink("https://www.google.com/maps/place//data=!4m2!3m1!1s0x4694a8dfd7b72db7:0x7bce843b30ef15a8?source=g.page.share")
            .build()),
    POLTSAMAA(LocationDto.builder()
            .locationName(LocationName.POLTSAMAA)
            .address("Jõgeva mnt 23a")
            .googleMapsLink("https://www.google.com/maps/place/J%C3%B5geva+mnt+23,+P%C3%B5ltsamaa,+48105+J%C3%B5geva+maakond/@58.6623256,25.990006,234m/data=!3m1!1e3!4m6!3m5!1s0x46ecab5f73342aff:0x3988d3428730308c!8m2!3d58.6624935!4d25.9901731!16s%2Fg%2F11c0xy67g_?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build());

    protected final LocationDto location;

    DecoraStoreLocation(LocationDto location) {
        this.location = location;
    }
}
