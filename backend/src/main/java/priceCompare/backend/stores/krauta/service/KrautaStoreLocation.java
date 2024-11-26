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
            .build()),
    TONDI("Tondi", LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Tammsaare tee 49")
            .build()),
    RAKVERE("Rakvere", LocationDto.builder()
            .locationName(LocationName.RAKVERE)
            .address("Haljala tee 4, Põhjakeskus")
            .build()),
    TARTU("Tartu", LocationDto.builder()
            .locationName(LocationName.TARTU)
            .address("Riia mnt 140e")
            .build()),
    PARNU("Papiniidu", LocationDto.builder()
            .locationName(LocationName.PARNU)
            .address("Papiniidu 2a")
            .build()),
    KURESSAARE("Kuressaare", LocationDto.builder()
            .locationName(LocationName.KURESSAARE)
            .address("Tallinna mnt 88, Auriga keskus")
            .build()),
    VORU("Kreutzwaldi", LocationDto.builder()
            .locationName(LocationName.VORU)
            .address("Kreutzwaldi 89")
            .build()),
    VALGA("Valga", LocationDto.builder()
            .locationName(LocationName.VALGA)
            .address("Tartu 84")
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