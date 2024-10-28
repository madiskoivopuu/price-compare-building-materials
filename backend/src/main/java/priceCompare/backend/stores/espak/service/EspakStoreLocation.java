package priceCompare.backend.stores.espak.service;

import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.enums.LocationName;

public enum EspakStoreLocation {
    TALLINN(LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Viadukti 42")
            .build()),
    KEILA(LocationDto.builder()
            .locationName(LocationName.KEILA)
            .address("Põhjakaare 3")
            .build()),
    TARTU(LocationDto.builder()
            .locationName(LocationName.TARTU)
            .address("Turu 24")
            .build()),
    PARNU(LocationDto.builder()
            .locationName(LocationName.PARNU)
            .address("Papiniidu 4")
            .build()),
    RAKVERE(LocationDto.builder()
            .locationName(LocationName.RAKVERE)
            .address("Rägavere 46")
            .build()),
    NARVA(LocationDto.builder()
            .locationName(LocationName.NARVA)
            .address("Rahu 36a")
            .build()),
    POLVA(LocationDto.builder()
            .locationName(LocationName.POLVA)
            .address("Jaama 16d")
            .build()),
    JOGEVA(LocationDto.builder()
            .locationName(LocationName.JOGEVA)
            .address("Tallinna mnt 2a")
            .build()),
    KURESSAARE(LocationDto.builder()
            .locationName(LocationName.KURESSAARE)
            .address("Uus-Roomassaare 35")
            .build()),
    MUHU(LocationDto.builder()
            .locationName(LocationName.MUHU)
            .address("Piiri")
            .build()),
    VILJANDI(LocationDto.builder()
            .locationName(LocationName.VILJANDI)
            .address("Reinu tee 35")
            .build()),
    JOHVI(LocationDto.builder()
            .locationName(LocationName.JOHVI)
            .address("Lille 3")
            .build()),
    RAPLA(LocationDto.builder()
            .locationName(LocationName.RAPLA)
            .address("Linda 1")
            .build()),
    PAIDE(LocationDto.builder()
            .locationName(LocationName.PAIDE)
            .address("Prääma tee 20")
            .build()),
    TURI(LocationDto.builder()
            .locationName(LocationName.TURI)
            .address("Kaare 35c")
            .build()),
    VORU(LocationDto.builder()
            .locationName(LocationName.VORU)
            .address("Kubja tee 36")
            .build()),
    RAPINA(LocationDto.builder()
            .locationName(LocationName.RAPINA)
            .address("Pargi 7a")
            .build()),
    VASTSELIINA(LocationDto.builder()
            .locationName(LocationName.VASTSELIINA)
            .address("Võidu 3a")
            .build());

    protected final LocationDto location;

    EspakStoreLocation(LocationDto location) {
        this.location = location;
    }
}
