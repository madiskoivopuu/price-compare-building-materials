package priceCompare.backend.enums;

import lombok.Getter;

@Getter
public enum Category {
    EHITUSPLAADID("Ehitusplaadid", new Subcategory[]{
            Subcategory.KIPSPLAAT,
            Subcategory.OSB_PLAAT,
            Subcategory.VINEER,
            Subcategory.TSEMENTKIUDPLAAT,
            Subcategory.PUITKIUDPLAAT,
            Subcategory.PUITLAASTPLAAT,
            Subcategory.KIPSKIUDPLAAT,
            Subcategory.WEDI_PLAAT,
            Subcategory.TYCROC_PLAAT
    }),
    PLOKID_JA_KIVID("Plokid ja kivid", new Subcategory[]{
            Subcategory.POORBETOON_BAUROC,
            Subcategory.FIBO,
            Subcategory.OONESPLOKK,
            Subcategory.SILIKAATPLOKK,
            Subcategory.LAKKAPLOKK,
            Subcategory.KERATERM,
            Subcategory.VUNDAMENDIPLOKK,
            Subcategory.TELLIS,
            Subcategory.SILLUSED,
            Subcategory.TANAVAKIVI,
            Subcategory.KONNITEPLAAT,
            Subcategory.AAREKIVID,
            Subcategory.VIHMAVEERENN,
            Subcategory.KORSTNAD,
    }),
    SOOJUSTUS("Soojustus", new Subcategory[]{
            Subcategory.KIVIVILL,
            Subcategory.KLAASVILL,
            Subcategory.TSELLUVILL,
            Subcategory.EPS,
            Subcategory.PIR,
            Subcategory.XPS,
            Subcategory.TUULETOKKEPLAAT,
    }),
    PUIT("Puit", new Subcategory[]{
            Subcategory.PRUSSID,
            Subcategory.LAUAD,
            Subcategory.KALIBREERITUD_PRUSSID,
            Subcategory.KALIBREERITUD_LAUAD,
            Subcategory.HOOVELPRUSSID,
            Subcategory.HOOVELLAUAD,
            Subcategory.SERVAMATA,
            Subcategory.LIIMPUIT,
            Subcategory.PORANDALAUAD,
            Subcategory.TERRASSILAUAD,
            Subcategory.SISEVOODRILAUAD
    }),
    SEGUD_VALMIS_KUIV("Segud valmis/kuiv", new Subcategory[]{
            Subcategory.PAHTEL,
            Subcategory.KROHV,
            Subcategory.MUURISEGU,
            Subcategory.PLAATIMISSEGU,
            Subcategory.VUUGISEGUD,
            Subcategory.AHJUSEGUD,
            Subcategory.TSEMENT,
            Subcategory.KUIVBETOON,
            Subcategory.LIIM,
            Subcategory.PORANDATASANDUS,
            Subcategory.SEGUD_TARVIKUD,
            Subcategory.SIDEAINED,
    }),
    FASSAAD("Fassaad", new Subcategory[]{
            Subcategory.VALISVOODRILAUAD,
            Subcategory.TSEMENTVOODRILAUAD,
            Subcategory.PLASTVOODER,
            Subcategory.FASSAAD_PLAADID,
            Subcategory.PROFIILID,
            Subcategory.FASSAAD_TARVIKUD,
    }),
    KATUS("Katused", new Subcategory[]{
            Subcategory.KIVI,
            Subcategory.TERAS,
            Subcategory.ETERNIIT,
            Subcategory.BITUUMEN,
            Subcategory.PVC_PC,
            Subcategory.VIHMAVEESUSTEEMID,
            Subcategory.FASSAAD_TARVIKUD,
    }),
    KATTEMATERJALID("Kattematerjalid", new Subcategory[]{
            Subcategory.EHITUSPABER,
            Subcategory.KANGAD,
            Subcategory.KILED,
            Subcategory.AURUTOKE,
            Subcategory.GEOTEKSTIIL,
            Subcategory.KATUSE_ALUSKATE,
    }),
    EHITUSMETALL("Ehitusmetall", new Subcategory[]{
            Subcategory.ARMATUUR,
            Subcategory.PLEKKPROFIILID
    });

    protected final String displayName;
    protected final Subcategory[] subcategories;

    Category(String displayName, Subcategory[] subcategories) {
        this.displayName = displayName;
        this.subcategories = subcategories;
    }

    public static Category fromDisplayName(String displayName) {
        for (Category category : Category.values()) {
            if (category.getDisplayName().equalsIgnoreCase(displayName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category display name: " + displayName);
    }


}
