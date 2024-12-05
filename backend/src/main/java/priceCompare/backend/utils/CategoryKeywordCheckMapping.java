package priceCompare.backend.utils;

import priceCompare.backend.enums.Subcategory;

import java.util.List;
import java.util.Map;

import static priceCompare.backend.enums.Subcategory.*;

public class CategoryKeywordCheckMapping {
    public static final Map<Subcategory, List<String>> categoryMap = Map.<Subcategory, List<String>>ofEntries(
            Map.entry(KIPSPLAAT, List.of("kipsplaat")),
            Map.entry(OSB_PLAAT, List.of("OSB")),
            Map.entry(VINEER, List.of("vineer")),
            Map.entry(TSEMENTKIUDPLAAT, List.of("tsementkiudplaat", "tsementlaastplaat", "tsementplaat", "cembrit")),
            Map.entry(PUITKIUDPLAAT, List.of("puitkiudplaat", "soome papp")),
            Map.entry(PUITLAASTPLAAT, List.of("puitlaastplaat")),
            Map.entry(KIPSKIUDPLAAT, List.of("kipskiudplaat")),
            Map.entry(WEDI_PLAAT, List.of("wedi plaat")),
            Map.entry(TYCROC_PLAAT, List.of("tycroc plaat", "tycroc fxl", "tycroc twp", "tycroc uhp", "tycroc fxw", "tycrock dušialus")),

            Map.entry(POORBETOON_BAUROC, List.of("bauroc")),
            Map.entry(FIBO, List.of("fibo")),
            Map.entry(OONESPLOKK, List.of("arc", "columbia", "sarrusplokk", "õõnesplokk", "reaplokk", "betoneks")),
            Map.entry(SILIKAATPLOKK, List.of("silikaatplokk", "silrock")),
            Map.entry(LAKKAPLOKK, List.of("lakkaplokk", "lakka")),
            Map.entry(KERATERM, List.of("keraterm")),
            Map.entry(VUNDAMENDIPLOKK, List.of("vundamendiplokk")),
            Map.entry(TELLIS, List.of("tellis")),
            Map.entry(SILLUSED, List.of("sillus")),
            Map.entry(TANAVAKIVI, List.of("Nunna", "kloostri", "unikivi", "murukivi", "sillutuskivi", "kaarekivi", "talukivi", "kartanokivi", "külakivi", "mõisakivi", "rinnatisekivi")),
            Map.entry(KONNITEPLAAT, List.of("Kõnniteeplaat", "plaat", "kärgplaat")),
            Map.entry(AAREKIVID, List.of("äärekivi")),
            Map.entry(VIHMAVEERENN, List.of("veerenn", "vihmaveerenn", "veerenni", "vihmaveepüüdja", "veepüüdja")),
            Map.entry(KORSTNAD, List.of("korsten")),

            Map.entry(KIVIVILL, List.of("Kivivill", "sammumüra", "kivivillaplaat", "puistevill paroc")),
            Map.entry(KLAASVILL, List.of("klaasvill", "klaasvillaplaat", "klaasvillast matt")),



            Map.entry(TSELLUVILL, List.of("tselluvill")),
            Map.entry(EPS,  List.of("EPS", "vahtplast", "vahtpolüstürool")),
            Map.entry(PIR, List.of("PIR", "recticel", "kingspan", "FF-PIR")),
            Map.entry(XPS, List.of("XPS", "JACKOFOAM", "FINNFOAM FL", "finnfoam fl-300", "finnfoam fl300", "xps finnfoam")),
            Map.entry(TUULETOKKEPLAAT, List.of("tuuletõkkeplaat")),

            Map.entry(PRUSSID, List.of("pruss")),
            Map.entry(LAUAD, List.of("laud")),
            Map.entry(KALIBREERITUD_PRUSSID, List.of("kalibreeritud pruss")),
            Map.entry(KALIBREERITUD_LAUAD, List.of("kalibreeritud laud")),
            Map.entry(HOOVELPRUSSID, List.of("höövel pruss")),
            Map.entry(HOOVELLAUAD, List.of("höövel laud")),
            Map.entry(SERVAMATA, List.of("servamata")),
            Map.entry(LIIMPUIT, List.of("liim")),
            Map.entry(PORANDALAUAD, List.of("põranda")),
            Map.entry(TERRASSILAUAD, List.of("terrassilaud", "terrassimoodul")),
            Map.entry(SISEVOODRILAUAD, List.of("sisevood")),

            Map.entry(PAHTEL, List.of("pahtel")),
            Map.entry(KROHV, List.of(
                    "krohvisegu", "uninaks", "briko", "knauf", "lubitsementkrohv", "lubikrohv",
                    "sakret", "weber", "restaureerimiskrohv", "krohvimört", "savikrohv",
                    "kiudkrohv", "tsementkrohv", "tasanduskrohv", "kipskrohvisegu",
                    "kergaluskrohv", "kipskrohv", "käsikrohv", "viimistluskrohv", "aluskrohv",
                    "antiikkrohv", "silikoonkrohv", "peenviimistluskrohv", "CT174", "fassaadikrohv"
            )),
            Map.entry(MUURISEGU, List.of("müüri")),
            Map.entry(PLAATIMISSEGU, List.of("plaatimis", "plaadi")),
            Map.entry(VUUGISEGUD, List.of("vuugi")),
            Map.entry(AHJUSEGUD, List.of("ahju")),
            Map.entry(TSEMENT, List.of("tsement")),
            Map.entry(KUIVBETOON, List.of("betoon")),
            Map.entry(LIIM, List.of("liim")),
            Map.entry(PORANDATASANDUS, List.of("tasandus", "põranda")),
            Map.entry(SEGUD_TARVIKUD, List.of("tarvik")),
            Map.entry(SIDEAINED, List.of("liiv", "killustik", "side")),

            Map.entry(PUITVOODRILAUAD, List.of("")),
            Map.entry(TSEMENTVOODRILAUAD, List.of("")),
            Map.entry(PLASTVOODER, List.of("")),
            Map.entry(FASSAAD_PLAADID, List.of("")),
            Map.entry(PROFIILID, List.of("")),
            Map.entry(FASSAAD_TARVIKUD, List.of("")),

            Map.entry(KIVI, List.of("")),
            Map.entry(TERAS, List.of("")),
            Map.entry(ETERNIIT, List.of("")),
            Map.entry(BITUUMEN, List.of("")),
            Map.entry(PVC_PC, List.of("")),
            Map.entry(VIHMAVEESUSTEEMID, List.of("")),
            Map.entry(KATUSETARVIKUD, List.of("")),

            Map.entry(EHITUSPABER, List.of("")),
            Map.entry(KANGAD, List.of("")),
            Map.entry(KILED, List.of("")),
            Map.entry(AURUTOKE, List.of("")),
            Map.entry(GEOTEKSTIIL, List.of("")),
            Map.entry(KATUSE_ALUSKATE, List.of("")),

            Map.entry(ARMATUUR, List.of("")),
            Map.entry(PLEKKPROFIILID,List.of(""))
    );
}
