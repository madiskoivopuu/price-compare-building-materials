package priceCompare.backend.stores.decora.service;

import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.enums.LocationName;

public enum DecoraStoreLocation {
    TALLINN(LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Laki põik 4")
            .build()),
    TARTU(LocationDto.builder()
            .locationName(LocationName.TARTU)
            .address("Riia 193")
            .build()),
    PARNU(LocationDto.builder()
            .locationName(LocationName.PARNU)
            .address("Suur-Jõe 57/1")
            .build()),
    VORU(LocationDto.builder()
            .locationName(LocationName.VORU)
            .address("Lepa 2")
            .build()),
    VILJANDI(LocationDto.builder()
            .locationName(LocationName.VILJANDI)
            .address("Leola 53")
            .build()),
    JOGEVA(LocationDto.builder()
            .locationName(LocationName.JOGEVA)
            .address("Puiestee 38")
            .build()),
    POLTSAMAA(LocationDto.builder()
            .locationName(LocationName.POLTSAMAA)
            .address("Laki põik 4")
            .build());

    protected final LocationDto location;

    DecoraStoreLocation(LocationDto location) {
        this.location = location;
    }
}
