package priceCompare.backend.enums;
import lombok.Getter;

@Getter
public enum Store {
    KRAUTA("krauta"),
    BAUHOF("bauhof"),
    ESPAK("espak"),
    DECORA("decora"),
    PUUMARKET("puumarket"),
    EHITUSEABC("ehituseabc"),
    EHOMER("ehomer"),
    BAUHAUS("bauhaus");

    protected final String storeName;

    Store(String name) {
        this.storeName = name;
    }
}
