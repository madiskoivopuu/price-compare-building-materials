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
            Subcategory.TUULETOKKEPLAAT,
            Subcategory.KIPSKIUDPLAAT,
            Subcategory.MDF_PLAAT,
            Subcategory.WEDI_PLAAT,
            Subcategory.TYCROC_PLAAT,
            Subcategory.TEMPSI_PLAAT
    }),
    PLOKID_JA_KIVID("Plokid ja kivid", new Subcategory[]{
            Subcategory.POORBETOON_BAUROC,
            Subcategory.FIBO,
            Subcategory.OONESPLOKK,
            Subcategory.TELLIS,
            Subcategory.SILIKAATPLOKK,
            Subcategory.LAKKAPLOKK,
            Subcategory.KERATERM,
            Subcategory.VUNDAMENDIPLOKK,
            Subcategory.TANAVAKIVI,
            Subcategory.KONNITEPLAAT,
            Subcategory.AAREKIVID,
            Subcategory.VIHMAVEERENN,
            Subcategory.LOODUSKIVID
    }),
    SOOJUSTUS("Soojustus", new Subcategory[]{
            Subcategory.KIVIVILL,
            Subcategory.KLAASVILL,
            Subcategory.EPS,
            Subcategory.PIR,
            Subcategory.XPS,
            Subcategory.POORBETOON_BAUROC
    }),
    PUIT("Puit", new Subcategory[]{
            Subcategory.PRUSSID,
            Subcategory.LAUAD,
            Subcategory.HOOVELPUIT
    }),
    SEGUD_VALMIS_KUIV("Segud valmis/kuiv", new Subcategory[]{
            Subcategory.PAHTEL,
            Subcategory.KROHV,
            Subcategory.MUURISEGU,
            Subcategory.PLAATIMISSEGU,
            Subcategory.TSEMENT,
            Subcategory.BETOON,
            Subcategory.LIIM,
            Subcategory.VUUGITAIDE,
            Subcategory.MUUD_SEGUD,
            Subcategory.PORANDATASANDUS
    }),
    FASSAAD("Fassaad", new Subcategory[]{
            Subcategory.VALISVOODRILAUD,
            Subcategory.FASSAAD_PLAADID,
            Subcategory.PROFIILID,
            Subcategory.TARVIKUD
    }),
    KATUS("Katused", new Subcategory[]{
            Subcategory.KIVI,
            Subcategory.TERAS,
            Subcategory.ETERNIIT,
            Subcategory.BITUUMEN,
            Subcategory.PVC,
            Subcategory.PC,
            Subcategory.RULLMATERJAL,
            Subcategory.KORSTNAD,
            Subcategory.TARVIKUD,
            Subcategory.VARIKATUSED
    }),
    KARKASS_JA_ARMATUUR("Karkass ja armatuur", new Subcategory[]{
            Subcategory.PLEKK,
            Subcategory.ARMATUUR,
            Subcategory.KANDURID,
            Subcategory.TALDMIKU_VORMID
    }),
    KATTEMATERJALID("Kattematerjalid", new Subcategory[]{
            Subcategory.KILED,
            Subcategory.AURUTOKE,
            Subcategory.GEOTEKSTIIL,
            Subcategory.KATUSE_ALUSKATE,
            Subcategory.KANGAD,
            Subcategory.KOORMAKATTED,
            Subcategory.EHITUSPAPP,
            Subcategory.TAPEET
    }),
    EHITUSMETALL("Ehitusmetall", new Subcategory[]{
            Subcategory.NURGAD,
            Subcategory.EHITUSMETALL_PLAADID,
            Subcategory.POSTIJALAD,
            Subcategory.EHITUSKOBAD,
            Subcategory.BETOONIHARGID,
            Subcategory.PALGIKINGAD,
            Subcategory.PLEKKPROFIILID
    }),
    AKNAD_JA_UKSED("Aknad ja uksed", new Subcategory[]{
            Subcategory.SISEUKSED,
            Subcategory.VALISUKSED,
            Subcategory.SAUNAUKSED,
            Subcategory.UKSELENGID,
            Subcategory.POONINGULUUGID,
            Subcategory.KONTROLLLUUGID,
            Subcategory.AKNAD,
            Subcategory.KATUSEAKNAD,
            Subcategory.AKNAPROFIILID,
            Subcategory.AKNALAUAD
    }),
    VARVID("Värvid", new Subcategory[]{
            Subcategory.SISEVARVID,
            Subcategory.VALISVARVID,
            Subcategory.KRUNT
    }),
    LAKID_OILID_VAHAD("Lakid, õlid, vahad", new Subcategory[]{
            Subcategory.SISE,
            Subcategory.VALIS
    }),
    LIISTUD("Liistud", new Subcategory[]{
            Subcategory.LIISTUD_PORAND,
            Subcategory.AVATAITED,
            Subcategory.LIISTUD_LAGI,
    }),
    VIIMISTLUSPLAADID("Viimistlusplaadid", new Subcategory[]{
            Subcategory.LIISTUD_PORAND,
            Subcategory.LIISTUD_LAGI,
            Subcategory.SEIN
    }),
    LAHUSTID("Lahustid", new Subcategory[]{
            Subcategory.LAHUSTID
    }),
    TEIBID("Tebid", new Subcategory[]{
            Subcategory.MAALRITEIP,
            Subcategory.TUULETOKKETEIP,
            Subcategory.UNIVERSAAL
    }),
    SILIKOONID("Silikoonid", new Subcategory[]{
            Subcategory.UNIVERSAAL
    }),
    LIIMID("Liimid", new Subcategory[]{
            Subcategory.LIIM_PUIT,
            Subcategory.LIIM_UNIVERSAAL,
    }),
    HUDROISOLATSIOON("Hüdroisolatsioon", new Subcategory[]{
            Subcategory.HUDROISOLATSIOON
    }),
    HERMEETIKUD("Hermeetikud", new Subcategory[]{
            Subcategory.HERMEETIKUD
    }),
    TORUD("Torud", new Subcategory[]{
            Subcategory.KANALISATSIOON,
            Subcategory.VEETORUD,
            Subcategory.VENTILATSIOON,
            Subcategory.VOOLIKUD,
            Subcategory.LISATARVIKUD
    }),
    TAITEMATERJALID("Täitematerjalid", new Subcategory[]{
            Subcategory.LIIV,
            Subcategory.KRUUS,
            Subcategory.KILLUSTIK
    }),
    KINNITUSVAHENDID("Kinnitusvahendid", new Subcategory[]{
            Subcategory.KRUVID,
            Subcategory.NAELAD,
            Subcategory.TUUBLID,
            Subcategory.POLDID,
            Subcategory.MUTRID,
            Subcategory.NEEDID,
            Subcategory.SEIBID,
            Subcategory.KLAMBRID,
            Subcategory.ANKRUD,
            Subcategory.KEERMELATID,
            Subcategory.MUUD,
    }),
    TARVIKUD("Tarvikud", new Subcategory[]{
            Subcategory.KIPSITARVIKUD,
            Subcategory.BAUROCI_TARVIKUD,
            Subcategory.FIBOTARVIKUD
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
