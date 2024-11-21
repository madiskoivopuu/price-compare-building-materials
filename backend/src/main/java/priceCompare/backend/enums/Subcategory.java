package priceCompare.backend.enums;

public enum Subcategory {
    KIPSPLAAT("Kipsplaat"),
    OSB_PLAAT("OSB-plaat"),
    VINEER("Vineer"),
    TSEMENTKIUDPLAAT("Tsementkiudplaat"),
    PUITKIUDPLAAT("Puitkiudplaat"),
    PUITLAASTPLAAT("Puitlaastplaat"),
    KIPSKIUDPLAAT("Kipskiudplaat (Fermacell)"),
    WEDI_PLAAT("WEDI plaat"),
    TYCROC_PLAAT("TYCROC plaat"),
    POORBETOON_BAUROC("Poorbetoon (bauroc)"),
    FIBO("Fibo"),
    OONESPLOKK("Õõnesplokk"),
    SILIKAATPLOKK("Silikaatplokk (silroc)"),
    LAKKAPLOKK("Lakkaplokk"),
    KERATERM("Keraterm"),
    VUNDAMENDIPLOKK("Vundamendiplokk"),
    TELLIS("Tellis"),
    SILLUSED("Sillused"),
    TANAVAKIVI("Tänavakivi, teekivi"),
    KONNITEPLAAT("Kõnniteeplaat"),
    AAREKIVID("Äärekivid"),
    VIHMAVEERENN("Vihmaveerenn"),
    KORSTNAD("Korstnad"),
    KIVIVILL("Kivivill"),
    KLAASVILL("Klaasvill"),
    TSELLUVILL("Tselluvill"),
    EPS("EPS"),
    PIR("PIR"),
    XPS("XPS"),
    TUULETOKKEPLAAT("Tuuletõkeplaat"),
    PRUSSID("Prussid"),
    LAUAD("Lauad"),
    KALIBREERITUD_PRUSSID("Kalibreeritud prussid"),
    KALIBREERITUD_LAUAD("Kalibreeritud lauad"),
    HOOVELPRUSSID("Höövelprussid"),
    HOOVELLAUAD("Höövellauad"),
    SERVAMATA("Servamata"),
    LIIMPUIT("Liimpuit"),
    PORANDALAUAD("Põrandalauad"),
    TERRASSILAUAD("Terrassilauad"),
    SISEVOODRILAUAD("Sisevoodrilauad"),
    PAHTEL("Pahtel"),
    KROHV("Krohv"),
    MUURISEGU("Müürisegud"),
    PLAATIMISSEGU("Plaatimissegud"),
    VUUGISEGUD("Vuugisegud"),
    AHJUSEGUD("Ahjusegud"),
    TSEMENT("Tsement"),
    KUIVBETOON("Kuivbetoon"),
    LIIM("Liim"),
    PORANDATASANDUS("Põrandatasandus"),
    SEGUD_TARVIKUD("Tarvikud"),
    SIDEAINED("Sideained"),
    PUITVOODRILAUAD("Puitvoodrilauad"),
    TSEMENTVOODRILAUAD("Tsement voodrilauad"),
    PLASTVOODER("Plastvooder"),
    FASSAAD_PLAADID("Plaadid"),
    PROFIILID("Profiilid"),
    FASSAAD_TARVIKUD("Tarvikud"),
    KIVI("Kivi"),
    TERAS("Teras"),
    ETERNIIT("Eterniit"),
    BITUUMEN("Bituumen"),
    PVC_PC("PVC/PC"),
    VIHMAVEESUSTEEMID("Vihmaveesüsteemid"),
    KATUSETARVIKUD("Tarvikud"),
    EHITUSPABER("Ehituspaber"),
    KANGAD("Kangad"),
    KILED("Kiled"),
    AURUTOKE("Aurutõke"),
    GEOTEKSTIIL("Geotekstiil"),
    KATUSE_ALUSKATE("Katuse aluskate"),
    VUNDAMENDIKATE("Vundamendikate"),
    ARMATUUR("Armatuur"),
    PLEKKPROFIILID("Plekkprofiilid");


    private final String displayName;

    Subcategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Subcategory fromDisplayName(String displayName) {
        for (Subcategory subcategory : Subcategory.values()) {
            if (subcategory.getDisplayName().equalsIgnoreCase(displayName)) {
                return subcategory;
            }
        }
        throw new IllegalArgumentException("Invalid subcategory display name: " + displayName);
    }
}
