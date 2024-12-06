package priceCompare.backend.utils;

import priceCompare.backend.enums.Subcategory;

import java.util.Map;

import static priceCompare.backend.enums.Subcategory.*;
import static priceCompare.backend.enums.Subcategory.PLEKKPROFIILID;

/*
bauhof and krauta haved complex search for searching only by category
for those two stores we use a keyword related to category in search to perform search by category
 */
public class CategorySearchKeywordMapping {
    public static final Map<Subcategory, String> categoryKeywordMap = Map.<Subcategory, String>ofEntries(
            Map.entry(KIPSPLAAT, "kipsplaat"),
            Map.entry(OSB_PLAAT, "osb"),
            Map.entry(VINEER, "vineer"),
            Map.entry(TSEMENTKIUDPLAAT, "tsementkiudplaat"),
            Map.entry(PUITKIUDPLAAT, "puitkiudplaat"),
            Map.entry(PUITLAASTPLAAT, "puitlaastplaat"),
            Map.entry(KIPSKIUDPLAAT, "kipskiudplaat"),
            Map.entry(WEDI_PLAAT, "wedi"),
            Map.entry(TYCROC_PLAAT, "tycroc"),

            Map.entry(POORBETOON_BAUROC, "bauroc"),
            Map.entry(FIBO, "fibo"),
            Map.entry(OONESPLOKK, "õõnesplokk"),
            Map.entry(SILIKAATPLOKK, "silikaatplokk"),
            Map.entry(LAKKAPLOKK, "lakkaplokk"),
            Map.entry(KERATERM, "keraterm"),
            Map.entry(VUNDAMENDIPLOKK, "vundamendiplokk"),
            Map.entry(TELLIS, "tellis"),
            Map.entry(SILLUSED, "sillus"),
            Map.entry(TANAVAKIVI, "tänavakivi"),
            Map.entry(KONNITEPLAAT, "kõnniteeplaat"),
            Map.entry(AAREKIVID, "äärekivi"),
            Map.entry(VIHMAVEERENN, "vihmaveerenn"),
            Map.entry(KORSTNAD, "korsten"),

            Map.entry(KIVIVILL, "kivivill"),
            Map.entry(KLAASVILL, "klaasvill"),
            Map.entry(TSELLUVILL, "tselluvill"),
            Map.entry(EPS,  "eps"),
            Map.entry(PIR, "pir"),
            Map.entry(XPS, "xps"),
            Map.entry(TUULETOKKEPLAAT, "tuuletõke"),

            Map.entry(PRUSSID, "pruss"),
            Map.entry(LAUAD, "laud"),
            Map.entry(KALIBREERITUD_PRUSSID, "pruss"),
            Map.entry(KALIBREERITUD_LAUAD, "laud"),
            Map.entry(HOOVELPRUSSID, "höövel"),
            Map.entry(HOOVELLAUAD, "höövel"),
            Map.entry(SERVAMATA, "servamata"),
            Map.entry(LIIMPUIT, "liimpuit"),
            Map.entry(PORANDALAUAD, "põrandalaud"),
            Map.entry(TERRASSILAUAD, "terrassilaud"),
            Map.entry(SISEVOODRILAUAD, "sisevoodrilaud"),

            Map.entry(PAHTEL, "pahtel"),
            Map.entry(KROHV, "krohv"),
            Map.entry(MUURISEGU, "müürisegu"),
            Map.entry(PLAATIMISSEGU, "plaatimissegu"),
            Map.entry(VUUGISEGUD, "vuugisegu"),
            Map.entry(AHJUSEGUD, "ahjusegu"),
            Map.entry(TSEMENT, "tsement"),
            Map.entry(KUIVBETOON, "kuivbetoon"),
            Map.entry(LIIM, "liim"),
            Map.entry(PORANDATASANDUS, "tasandus"),
            Map.entry(SEGUD_TARVIKUD, "tarvik"),
            Map.entry(SIDEAINED, "side"),

            Map.entry(PUITVOODRILAUAD, "voodrilaud"),
            Map.entry(TSEMENTVOODRILAUAD, "tsement voodrilaud"),
            Map.entry(PLASTVOODER, "plastvooder"),
            Map.entry(FASSAAD_PLAADID, "plaat"),
            Map.entry(PROFIILID, "profiil"),
            Map.entry(FASSAAD_TARVIKUD, "tarvik"),

            Map.entry(KIVI, "kivi"),
            Map.entry(TERAS, "plekk"),
            Map.entry(ETERNIIT, "eterniit"),
            Map.entry(BITUUMEN, "bituumen"),
            Map.entry(PVC_PC, "pvc"),
            Map.entry(VIHMAVEESUSTEEMID, "vihmaveesüsteem"),
            Map.entry(KATUSETARVIKUD, "tarvik"),

            Map.entry(EHITUSPABER, "paber"),
            Map.entry(KANGAD, "kangas"),
            Map.entry(KILED, "kile"),
            Map.entry(AURUTOKE, "aurutõke"),
            Map.entry(GEOTEKSTIIL,"geotekstiil"),
            Map.entry(KATUSE_ALUSKATE, "aluskate"),
            Map.entry(ARMATUUR, "armatuur"),
            Map.entry(PLEKKPROFIILID, "profiil")
    );
}
