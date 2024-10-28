package priceCompare.backend.enums;
import lombok.Getter;
import priceCompare.backend.dto.LocationsDto;

@Getter
public enum Store {
    KRAUTA("krauta"),
    BAUHOF("bauhof"),
    ESPAK("espak");

    protected final String storeName;

    Store(String name) {
        this.storeName = name;
    }
}
