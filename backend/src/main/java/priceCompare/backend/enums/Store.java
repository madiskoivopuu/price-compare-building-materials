package priceCompare.backend.enums;
import lombok.Getter;

@Getter
public enum Store {
    KRAUTA("krauta"),
    BAUHOF("bauhof");

    protected final String storeName;

    Store(String name) {
        this.storeName = name;
    }
}
