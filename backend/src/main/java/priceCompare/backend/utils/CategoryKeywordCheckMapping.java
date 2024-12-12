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

            Map.entry(PRUSSID, List.of("pruss kuivatamata", " pruss kuivatatud", " saetud pruss", " pruss immutatud", " immutatud saematerjal", " termopuit", " saematerjal immutatud", " pruss õhkkuiv", " õhkkuiv", " pruss 50x", " pruss 100x")),
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

            Map.entry(VALISVOODRILAUAD, List.of("vooder välis", "välisvoodrilaud")),
            Map.entry(TSEMENTVOODRILAUAD, List.of("kiudtsement voodrilaud", "cedral c54")),
            Map.entry(PLASTVOODER, List.of("")),
            Map.entry(FASSAAD_PLAADID, List.of("paneel", "sokliplaat", "kivex", "")),
            Map.entry(PROFIILID, List.of("nurgaprofiil", "veeninaprofiil", "aknaliiteprofiil", "sokliprofiil", "tuulutusprofiil", "algusprofiil",
                    "paigaldusprofiil", "lõpuprofiil", "kaitseprofiil")),
            Map.entry(FASSAAD_TARVIKUD, List.of("")),

            Map.entry(KIVI, List.of("katusekivi palema", "katusekivi carisma", "reakivi", "äärekivi", "harjakivi")),
            Map.entry(TERAS, List.of("Kiviprofiil", "trapetsprofiil", "otsaplekk", "harjaplekk", "räästaplekk", "otsaplekk", "liiteplekk", "S235", "s355")),
            Map.entry(ETERNIIT, List.of("harjaplaat", "poola eterniit", "eterniit", "laineplaat", "harjakate", "ääreplaat", "servakate")),
            Map.entry(BITUUMEN, List.of("bituumensindli", "servaplaat", "viiluplaat", "harjaplaat", "bituumensindel", "laineplaat",
                    "bituumenkate", "harjaprofiil", "bituumenharjaplaat", "bituumenlaineplaat", "bituumenkatuse", "bituumen viilukate",
                    "bituumenplaat", "katuseplaat onduline", "kärgkate", "bituumenlint", "bituumenkatuseplaat", "sindelkatus", "bituumenkate",
                    "bituumensindel", "SBS", "icopal", "ruberoid")),
            Map.entry(PVC_PC, List.of("PC plaadi", "kihtplastiku", "kihtplastik", "kihtplastiku profiil", "sileplaat",
                    "polüstüreen plaat", "laineplaat", "PVC trapetsplaat", "harjaplaat", "profileeritud PVC", "kihtplastplaat", "valgusplaat")),
            Map.entry(VIHMAVEESUSTEEMID, List.of("")),
            Map.entry(KATUSETARVIKUD, List.of("")),

            Map.entry(EHITUSPABER, List.of("Ehituspapp", "ehituspaber", "kattepapp", "kattepaber", "fooliumpaber", "isolatsioonipaber", "bituumeenpaber", "alumiiniumpaber")),
            Map.entry(KANGAD, List.of("tuuletõkkekangas", "tuuletõke")),
            Map.entry(KILED, List.of("kattekile", "ehituskile", "katusekile")),
            Map.entry(AURUTOKE, List.of("aurutõkkekile", "aurutõkkepaber", "aurutõke")),
            Map.entry(GEOTEKSTIIL, List.of("geotekstiil")),
            Map.entry(KATUSE_ALUSKATE, List.of("katuse aluskate", "aluskate roofproof", "anticon", "katusekile", "aluskate")),

            Map.entry(ARMATUUR, List.of("Armatuurteras", "armatuurvarras", "armatuur", "bi-armatuur", "armatuurvõrk", "müürivõrk")),
            Map.entry(PLEKKPROFIILID,List.of("L-profiil", "J-profiil", "metallkarkassi otsaliist", "karkass raintar", "metallkarkassi nurk",
                    "mütsprofiil", "ripplae otsaliist", "otsaprofiil", "karkass", "vertikaalprofiilid", "metallkarkassi post", "laepeofiil",
                    "metallkarkassi vöö", "horisontaalprofiil", "vertikaalprofiil", "ripplae karkassikandja", "karkassipost", "U-profiil", "horistontaalprofiilid"))
    );
}
