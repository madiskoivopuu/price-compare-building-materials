package priceCompare.backend.stores.espak.service;

import priceCompare.backend.enums.Subcategory;

import java.util.List;
import java.util.Map;

import static priceCompare.backend.enums.Subcategory.*;

public class EmaterjalToEspakCategoryMapping {
    // list of product_cat-* CSS classes that represent the category in the product HTML
    public static final Map<Subcategory, List<String>> subcatMap = Map.<Subcategory, List<String>>ofEntries(
            Map.entry(KIPSPLAAT, List.of("kipsplaat")),
            Map.entry(OSB_PLAAT, List.of("osb-plaadid")),
            Map.entry(VINEER, List.of("vineer")),
            Map.entry(TSEMENTKIUDPLAAT, List.of("tsementkiudplaadid")),
            Map.entry(PUITKIUDPLAAT, List.of()),
            Map.entry(PUITLAASTPLAAT, List.of()),
            Map.entry(KIPSKIUDPLAAT, List.of("kova-puitkiudplaat")),
            Map.entry(WEDI_PLAAT, List.of()),
            Map.entry(TYCROC_PLAAT, List.of("tsementkiudplaadid")),
            Map.entry(POORBETOON_BAUROC, List.of("gaasbetoonplokid")),
            Map.entry(FIBO, List.of("keramsiitplokid", "keramsiitplokid-fibo")),
            Map.entry(OONESPLOKK, List.of()),
            Map.entry(TELLIS, List.of("tellised")),
            Map.entry(SILLUSED, List.of("sillused")),
            Map.entry(SILIKAATPLOKK, List.of()),
            Map.entry(LAKKAPLOKK, List.of()),
            Map.entry(KERATERM, List.of()),
            Map.entry(VUNDAMENDIPLOKK, List.of()),
            Map.entry(TANAVAKIVI, List.of("sillutiskivid")),
            Map.entry(KONNITEPLAAT, List.of("sillutiskivid")),
            Map.entry(AAREKIVID, List.of("aarekivi")),
            Map.entry(VIHMAVEERENN, List.of()),
            Map.entry(KORSTNAD, List.of()),
            Map.entry(LOODUSKIVID, List.of()),
            Map.entry(KIVIVILL, List.of("kivivill")),
            Map.entry(KLAASVILL, List.of("klaasvill")),
            Map.entry(TSELLUVILL, List.of()),
            Map.entry(EPS, List.of("vahtpolustureen")),
            Map.entry(XPS, List.of("xps")),
            Map.entry(PIR, List.of("xps")),
            Map.entry(TUULETOKKEPLAAT, List.of("tuuletokkeplaadid")),
            Map.entry(PRUSSID, List.of()),
            Map.entry(LAUAD, List.of()),
            Map.entry(KALIBREERITUD_PRUSSID, List.of()),
            Map.entry(KALIBREERITUD_LAUAD, List.of()),
            Map.entry(HOOVELLAUAD, List.of()),
            Map.entry(LIIMPUIT, List.of()),
            Map.entry(PORANDALAUAD, List.of()),
            Map.entry(TERRASSILAUAD, List.of()),
            Map.entry(SISEVOODRILAUAD, List.of()),
            Map.entry(PAHTEL, List.of("seinatasandussegud", "sisepahtlid", "fassaadi-ja-universaalpahtlid")),
            Map.entry(KROHV, List.of("aluspahtlid")),
            Map.entry(MUURISEGU, List.of("muurisegud")),
            Map.entry(PLAATIMISSEGU, List.of("plaatimissegud")),
            Map.entry(VUUGISEGUD, List.of("plaatimissegud")),
            Map.entry(AHJUSEGUD, List.of("ahjusegud")),
            Map.entry(TSEMENT, List.of("tsement", "remontsegud")),
            Map.entry(BETOON, List.of("betoon", "remontsegud")),
            Map.entry(LIIM, List.of()),
            Map.entry(PORANDATASANDUS, List.of("porandasegud")),
            Map.entry(SEGUD_TARVIKUD, List.of("porandasegud")),
            Map.entry(SIDEAINED, List.of("sideained")),
            Map.entry(MUUD_SEGUD, List.of()),
            Map.entry(PUITVOODRILAUAD, List.of()),
            Map.entry(TSEMENTVOODRILAUAD, List.of()),
            Map.entry(PLASTVOODER, List.of()),
            Map.entry(FASSAAD_PLAADID, List.of()),
            Map.entry(PROFIILID, List.of()),
            Map.entry(LISATARVIKUD, List.of("fassaadi-lisatarvikud"))
    );
}
