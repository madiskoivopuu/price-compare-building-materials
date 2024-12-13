package priceCompare.backend.enums;

import static priceCompare.backend.enums.Filter.*;

import lombok.Getter;

public enum Subcategory {
    KIPSPLAAT("Kipsplaat", new Filter[]{KIPS_KLASS, KIPS_LABIMOOT, KIPS_LAIUS_PIKKUS}),
    OSB_PLAAT("OSB-plaat", new Filter[]{OSB_KLASS, OSB_LABIMOOT, OSB_LAIUS_PIKKUS}),
    VINEER("Vineer", new Filter[]{VINEER_TUUP, VINEER_LABIMOOT, VINEER_LAIUS_PIKKUS, VINEER_MATERJAL}),
    TSEMENTKIUDPLAAT("Tsementkiudplaat", new Filter[]{TSEMENTKIUDPLAAT_TUUP, TSEMENTKIUDPLAAT_LABIMOOT, TSEMENTKIUDPLAAT_LAIUS_PIKKUS}),
    PUITKIUDPLAAT("Puitkiudplaat", new Filter[]{PUITKIUDPLAAT_TUUP, PUITKIUDPLAAT_LABIMOOT, PUITKIUDPLAAT_LAIUS_PIKKUS}),
    PUITLAASTPLAAT("Puitlaastplaat", new Filter[]{PUITLAASTPLAAT_TUUP, PUITLAASTPLAAT_LABIMOOT, PUITLAASTPLAAT_LAIUS_PIKKUS}),
    KIPSKIUDPLAAT("Kipskiudplaat (Fermacell)", new Filter[]{KIPSKIUDPLAAT_LABIMOOT, KIPSKIUDPLAAT_LAIUS_PIKKUS}),
    WEDI_PLAAT("WEDI plaat", new Filter[]{WEDIPLAAT_LABIMOOT, WEDIPLAAT_LAIUS_PIKKUS}),
    TYCROC_PLAAT("TYCROC plaat", new Filter[]{TYCROC_TUUP, TYCROC_LABIMOOT, TYCROC_LAIUS_PIKKUS}),
    POORBETOON_BAUROC("Poorbetoon (bauroc)", new Filter[]{}),
    FIBO("Fibo", new Filter[]{}),
    OONESPLOKK("Õõnesplokk", new Filter[]{}),
    SILIKAATPLOKK("Silikaatplokk (silroc)", new Filter[]{}),
    LAKKAPLOKK("Lakkaplokk", new Filter[]{}),
    KERATERM("Keraterm", new Filter[]{}),
    VUNDAMENDIPLOKK("Vundamendiplokk", new Filter[]{}),
    TELLIS("Tellis", new Filter[]{}),
    SILLUSED("Sillused", new Filter[]{}),
    TANAVAKIVI("Tänavakivi, teekivi", new Filter[]{}),
    KONNITEPLAAT("Kõnniteeplaat", new Filter[]{}),
    AAREKIVID("Äärekivid", new Filter[]{}),
    VIHMAVEERENN("Vihmaveerenn", new Filter[]{}),
    KORSTNAD("Korstnad", new Filter[]{}),
    KIVIVILL("Kivivill", new Filter[]{KIVIVILL_TUUP, KIVIVILL_LABIMOOT, KIVIVILL_LAIUS_PIKKUS}),
    KLAASVILL("Klaasvill", new Filter[]{KLAASVILL_TUUP, KLAASVILL_LABIMOOT, KLAASVILL_LAIUS_PIKKUS}),
    TSELLUVILL("Tselluvill", new Filter[]{}),
    EPS("EPS", new Filter[]{EPS_TUUP, EPS_LABIMOOT, EPS_LAIUS_PIKKUS}),
    PIR("PIR", new Filter[]{PIR_TUUP, PIR_LABIMOOT, PIR_LAIUS_PIKKUS}),
    XPS("XPS", new Filter[]{XPS_TUUP, XPS_LABIMOOT, XPS_LAIUS_PIKKUS}),
    TUULETOKKEPLAAT("Tuuletõkkeplaat", new Filter[]{TUULETOKE_TUUP, TUULETOKE_LABIMOOT, TUULETOKE_LAIUS_PIKKUS}),
    PRUSSID("Prussid", new Filter[]{PRUSSID_TUUP, PRUSSID_LABIMOOT, PRUSSID_LAIUS_PIKKUS}),
    LAUAD("Lauad", new Filter[]{LAUAD_TUUP, LAUAD_LABIMOOT, LAUAD_LAIUS_PIKKUS}),
    KALIBREERITUD_PRUSSID("Kalibreeritud prussid", new Filter[]{}),
    KALIBREERITUD_LAUAD("Kalibreeritud lauad", new Filter[]{}),
    HOOVELPRUSSID("Höövelprussid", new Filter[]{HOOVELPRUSSID_TUUP, HOOVELPRUSSID_LABIMOOT, HOOVELPRUSSID_LAIUS_PIKKUS}),
    HOOVELLAUAD("Höövellauad", new Filter[]{HOOVELLAUAD_TUUP, HOOVELLAUAD_LABIMOOT, HOOVELLAUAD_LAIUS_PIKKUS}),
    SERVAMATA("Servamata", new Filter[]{}),
    LIIMPUIT("Liimpuit", new Filter[]{LIIMPUIT_TUUP, LIIMPUIT_LABIMOOT, LIIMPUIT_LAIUS_PIKKUS}),
    PORANDALAUAD("Põrandalauad", new Filter[]{PORANDALAUD_TUUP, PORANDALAUD_LABIMOOT, PORANDALAUD_LAIUS_PIKKUS}),
    TERRASSILAUAD("Terrassilauad", new Filter[]{TERRASSILAUD_TUUP, TERRASSILAUD_LABIMOOT, TERRASSILAUD_LAIUS_PIKKUS}),
    SISEVOODRILAUAD("Sisevoodrilauad", new Filter[]{SISEVOODRILAUD_TUUP, SISEVOODRILAUD_LABIMOOT, SISEVOODIRLAUD_LAIUS_PIKKUS}),
    PAHTEL("Pahtel", new Filter[]{}),
    KROHV("Krohv", new Filter[]{}),
    MUURISEGU("Müürisegud", new Filter[]{}),
    PLAATIMISSEGU("Plaatimissegud", new Filter[]{}),
    VUUGISEGUD("Vuugisegud", new Filter[]{}),
    AHJUSEGUD("Ahjusegud", new Filter[]{}),
    TSEMENT("Tsement", new Filter[]{}),
    KUIVBETOON("Kuivbetoon", new Filter[]{}),
    LIIM("Liim", new Filter[]{}),
    PORANDATASANDUS("Põrandatasandus", new Filter[]{}),
    SEGUD_TARVIKUD("Tarvikud", new Filter[]{}),
    SIDEAINED("Sideained", new Filter[]{}),
    VALISVOODRILAUAD("Välisvoodrilauad", new Filter[]{VALISVOODRILAUD_TUUP, VALISVOODRILAUD_LABIMOOT, VALISVOODRILAUD_LAIUS_PIKKUS}),
    TSEMENTVOODRILAUAD("Tsement voodrilauad", new Filter[]{}),
    PLASTVOODER("Plastvooder", new Filter[]{PLASTVOODER_TUUP}),
    FASSAAD_PLAADID("Plaadid", new Filter[]{}),
    PROFIILID("Profiilid", new Filter[]{}),
    FASSAAD_TARVIKUD("Tarvikud", new Filter[]{}),
    KIVI("Kivi", new Filter[]{}),
    TERAS("Teras", new Filter[]{}),
    ETERNIIT("Eterniit", new Filter[]{}),
    BITUUMEN("Bituumen", new Filter[]{}),
    PVC_PC("PVC/PC", new Filter[]{}),
    VIHMAVEESUSTEEMID("Vihmaveesüsteemid", new Filter[]{}),
    KATUSETARVIKUD("Tarvikud", new Filter[]{}),
    EHITUSPABER("Ehituspaber", new Filter[]{}),
    KANGAD("Kangad", new Filter[]{}),
    KILED("Kiled", new Filter[]{}),
    AURUTOKE("Aurutõke", new Filter[]{}),
    GEOTEKSTIIL("Geotekstiil", new Filter[]{}),
    KATUSE_ALUSKATE("Katuse aluskate", new Filter[]{}),
    VUNDAMENDIKATE("Vundamendikate", new Filter[]{}),
    ARMATUUR("Armatuur", new Filter[]{}),
    PLEKKPROFIILID("Plekkprofiilid", new Filter[]{});


    @Getter
    private final String displayName;
    @Getter
    protected final Filter[] filters;

    Subcategory(String displayName, Filter[] filters) {
        this.displayName = displayName;
        this.filters = filters;
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
