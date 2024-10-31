package priceCompare.backend.stores.decora.service;

import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.dto.ProductParseDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;

import static priceCompare.backend.enums.Subcategory.*;

// Mappings of ematerjal.ee categories to decora.ee categories that klevu search api accepts
public class EmaterjalToDecoraCategoryMapping {
    public static final Map<Subcategory, List<String>> subcatMap = Map.of(
            KIPSPLAAT, List.of("Kipsplaadid"),
            OSB_PLAAT, List.of("Puitlaast- ja puitkiudplaadid"),
            VINEER, List.of("Vineerid"),
            TSEMENTKIUDPLAAT, List.of("Tsementplaadid"),
            PUITKIUDPLAAT, List.of("Puitlaast- ja puitkiudplaadid"),
            PUITLAASTPLAAT, List.of("Puitlaast- ja puitkiudplaadid"),
            TUULETOKKEPLAAT, List.of("Tuuletõkkeplaadid"),
            KIPSKIUDPLAAT, List.of("---"),
            MDF_PLAAT, List.of("Melamiinplaadid"),
            WEDI_PLAAT, List.of("??"),
            TYCROC_PLAAT, List.of("??"),
            TEMPSI_PLAAT, List.of("??"),
            POORBETOON_BAUROC, List.of("Müürisegud ja betoonid"),
            FIBO, List.of("Sillused", "Ehitusplokk"),
            OONESPLOKK, List.of("Ehitusplokk"), // kas on õiged kategooriad?
            TELLIS, List.of("Tellised"),
            SILIKAATPLOKK, List.of("Tellised"), // kas on õige kategooria
            LAKKAPLOKK, List.of("---"), // ei leidnud seda kategooriat ega märksõnaga "lakka" toodet
            KERATERM, List.of("Seinaplaadid", "Dekoorplaadid ja bordüürid", "Põrandaplaadid", "Klaasplokid", "Mosaiikplaadid", "Naturaalkivid"),
            VUNDAMENDIPLOKK, List.of("---"), // ei leidnud kategooriat
            TANAVAKIVI, List.of("Tänavakivid"),
            KONNITEPLAAT, List.of("Tänavakivid"),
            AAREKIVID, List.of("Tänavakivid"),
            VIHMAVEERENN, List.of("---"),
            LOODUSKIVID, List.of("Naturaalkivid"),
            KIVIVILL, List.of("Kivivillad"),
            KLAASVILL, List.of("Klaasvillad"),
            EPS, List.of("EPS-vahtpolüstüreen"),
            XPS, List.of("XPS ja PIR soojustusplaadid"),
            PIR, List.of("XPS ja PIR soojustusplaadid"),
            PRUSSID, List.of("Saematerjal"),
            LAUAD, List.of("Höövelmaterjal", "Liimpuit", "Saematerjal", "Saunamaterjal", "Terrassilaud", "Voodrilaud"),
            HOOVELPUIT, List.of("Höövelmaterjal"),
            PAHTEL, List.of("Valmispahtlid", "Pahtlid"),
            KROHV, List.of("Krohvid ja tarvikud"),
            MUURISEGU, List.of("Müürisegud ja betoonid"), // õige kategooria? https://www.decora.ee/ehitusmaterjalid/kuivsegud/muurisegud-ja-betoonid
            PLAATIMISSEGU, List.of("Plaatimissegud"), // õige kategooria? https://www.decora.ee/ehitusmaterjalid/kuivsegud/plaatimissegud
            TSEMENT, List.of("Müürisegud ja betoonid"), // õige kategooria? https://www.decora.ee/ehitusmaterjalid/kuivsegud/muurisegud-ja-betoonid
            BETOON, List.of("Müürisegud ja betoonid"), // õige kategooria? https://www.decora.ee/ehitusmaterjalid/kuivsegud/muurisegud-ja-betoonid
            LIIM, List.of("Fassaadisegud ja tarvikud"), // õige kategooria? https://www.decora.ee/ehitusmaterjalid/kuivsegud/fassaadisegud-ja-tarvikud
            VUUGITAIDE, List.of("Vuugisegud"), // https://www.decora.ee/ehitusmaterjalid/kuivsegud/vuugisegud
            MUUD_SEGUD, List.of("---"), // mina ei tea mida vittu sellega teha
            PORANDATASANDUS, List.of("Põrandasegud ja tarvikud"), // õige kategooria? https://www.decora.ee/ehitusmaterjalid/kuivsegud/porandasegud-ja-tarvikud
            VALISVOODRILAUD, List.of("Voodrilaud"), // õige kategooria? https://www.decora.ee/ehitusmaterjalid/puitmaterjal/voodrilaud
            PLAADID, List.of("Tsementplaadid"), // õige kategooria? https://www.decora.ee/ehitusmaterjalid/ehitusplaadid/tsementplaadid
            PROFIILID, List.of("----"), // ei leidnud vajab täpsustamist
            TARVIKUD, List.of("Fassaadisegud ja tarvikud"), // https://www.decora.ee/ehitusmaterjalid/kuivsegud/fassaadisegud-ja-tarvikud
    );

    public static final Map<Subcategory, Function<ProductParseDto, Boolean>> subcatAdditionalVerification = Map.of(
            OSB_PLAAT, (ProductParseDto product) -> {
                return true;
            },
            TSEMENTKIUDPLAAT, (ProductParseDto product) -> {
                return true;
            },
            PUITKIUDPLAAT, (ProductParseDto product) -> {
                return true;
            },
            PUITLAASTPLAAT, (ProductParseDto product) -> {
                return true;
            },
            WEDI_PLAAT, (ProductParseDto product) -> { // brändi põhjal?
                return true;
            },
            TYCROC_PLAAT, (ProductParseDto product) -> { // brändi põhjal?
                return true;
            },
            TEMPSI_PLAAT, (ProductParseDto product) -> { // brändi põhjal?
                return true;
            },
            POORBETOON_BAUROC, (ProductParseDto product) -> { // brändi põhjal?
                return true;
            },
            FIBO, (ProductParseDto product) -> { // brändi põhjal?
                return true;
            },
            OONESPLOKK, (ProductParseDto product) -> { // märksõna "õõnesplokk" põhjal?
                return true;
            },
            SILIKAATPLOKK, (ProductParseDto product) -> { // märksõna "silroc" põhjal?
                return true;
            },
            KONNITEPLAAT, (ProductParseDto product) -> { // märksõna "kõnniteeplaat" põhjal?
                return true;
            },
            AAREKIVID, (ProductParseDto product) -> { // märksõna "äärekivi" põhjal?
                return true;
            },
            XPS, (ProductParseDto product) -> { // märksõna "XPS" põhjal?
                return true;
            },
            PIR, (ProductParseDto product) -> { // märksõna "PIR" põhjal?
                return true;
            },
            PRUSSID, (ProductParseDto product) -> { // märksõna "prussid" põhjal?
                return true;
            },
            KROHV, (ProductParseDto product) -> { // toote nimes märksõna "krohv" põhjal?
                return true;
            },
            MUURISEGU, (ProductParseDto product) -> { // toote nimes märksõna "müürisegu" põhjal?
                return true;
            },
            TSEMENT, (ProductParseDto product) -> { // toote nimes märksõna "tsementsegu" põhjal?
                return true;
            },
            BETOON, (ProductParseDto product) -> { // toote nimes märksõna "kuivbetoon", "betoonsegu" põhjal?
                return true;
            },
            LIIM, (ProductParseDto product) -> { // toote nimes märksõna "liim" põhjal?
                return true;
            },
            PORANDATASANDUS, (ProductParseDto product) -> { // toote nimes märksõna "liim" põhjal?
                return true;
            },
            VALISVOODRILAUD, (ProductParseDto product) -> { // toote nimes märksõna "välisvooder", "välisvoodrilaud" põhjal?
                return true;
            },
    );
}
