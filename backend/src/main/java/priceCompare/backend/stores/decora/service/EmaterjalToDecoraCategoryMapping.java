package priceCompare.backend.stores.decora.service;

import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.dto.ProductParseDto;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static priceCompare.backend.enums.Subcategory.*;

// Mappings of ematerjal.ee categories to decora.ee categories that klevu search api accepts
public class EmaterjalToDecoraCategoryMapping {
    public static final Map<Subcategory, List<String>> subcatMap = Map.ofEntries(
            Map.entry(KIPSPLAAT, List.of("Kipsplaadid")),
            Map.entry(OSB_PLAAT, List.of("Puitlaast- ja puitkiudplaadid")),
            Map.entry(VINEER, List.of("Vineerid")),
            Map.entry(TSEMENTKIUDPLAAT, List.of("Tsementplaadid")),
            Map.entry(PUITKIUDPLAAT, List.of("Puitlaast- ja puitkiudplaadid")),
            Map.entry(PUITLAASTPLAAT, List.of("Puitlaast- ja puitkiudplaadid")),
            Map.entry(TUULETOKKEPLAAT, List.of("Tuuletõkkeplaadid")),
            Map.entry(KIPSKIUDPLAAT, null),
            Map.entry(MDF_PLAAT, List.of("Melamiinplaadid")),
            Map.entry(WEDI_PLAAT, List.of("??")),
            Map.entry(TYCROC_PLAAT, List.of("??")),
            Map.entry(TEMPSI_PLAAT, List.of("??")),
            Map.entry(POORBETOON_BAUROC, List.of("Müürisegud ja betoonid")),
            Map.entry(FIBO, List.of("Sillused", "Ehitusplokk")),
            Map.entry(OONESPLOKK, List.of("Ehitusplokk")),
            Map.entry(TELLIS, List.of("Tellised")),
            Map.entry(SILIKAATPLOKK, List.of("Tellised")),
            Map.entry(LAKKAPLOKK, null),
            Map.entry(KERATERM, List.of("Seinaplaadid", "Dekoorplaadid ja bordüürid", "Põrandaplaadid", "Klaasplokid", "Mosaiikplaadid", "Naturaalkivid")),
            Map.entry(VUNDAMENDIPLOKK, null),
            Map.entry(TANAVAKIVI, List.of("Tänavakivid")),
            Map.entry(KONNITEPLAAT, List.of("Tänavakivid")),
            Map.entry(AAREKIVID, List.of("Tänavakivid")),
            Map.entry(VIHMAVEERENN, null),
            Map.entry(LOODUSKIVID, List.of("Naturaalkivid")),
            Map.entry(KIVIVILL, List.of("Kivivillad")),
            Map.entry(KLAASVILL, List.of("Klaasvillad")),
            Map.entry(EPS, List.of("EPS-vahtpolüstüreen")),
            Map.entry(XPS, List.of("XPS ja PIR soojustusplaadid")),
            Map.entry(PIR, List.of("XPS ja PIR soojustusplaadid")),
            Map.entry(PRUSSID, List.of("Saematerjal")),
            Map.entry(LAUAD, List.of("Höövelmaterjal", "Liimpuit", "Saematerjal", "Saunamaterjal", "Terrassilaud", "Voodrilaud")),
            Map.entry(HOOVELPUIT, List.of("Höövelmaterjal")),
            Map.entry(PAHTEL, List.of("Valmispahtlid", "Pahtlid")),
            Map.entry(KROHV, List.of("Krohvid ja tarvikud")),
            Map.entry(MUURISEGU, List.of("Müürisegud ja betoonid")),
            Map.entry(PLAATIMISSEGU, List.of("Plaatimissegud")),
            Map.entry(TSEMENT, List.of("Müürisegud ja betoonid")),
            Map.entry(BETOON, List.of("Müürisegud ja betoonid")),
            Map.entry(LIIM, List.of("Müürisegud ja betoonid", "Fassaadisegud ja tarvikud")),
            Map.entry(VUUGITAIDE, List.of("Vuugisegud")),
            Map.entry(MUUD_SEGUD, null),
            Map.entry(PORANDATASANDUS, List.of("Põrandasegud ja tarvikud")),
            Map.entry(VALISVOODRILAUD, List.of("Voodrilaud")),
            Map.entry(FASSAAD_PLAADID, List.of("Tsementplaadid")),
            Map.entry(PROFIILID, List.of("?")),
            Map.entry(TARVIKUD, List.of("Fassaadisegud ja tarvikud")),
            Map.entry(KIVI, null),
            Map.entry(TERAS, null),
            Map.entry(ETERNIIT, List.of("Eterniitkatused")),
            Map.entry(BITUUMEN, List.of("Bituumenkatused")),
            Map.entry(PVC, List.of("PVC katusematerjalid")),
            Map.entry(PC, null),
            Map.entry(RULLMATERJAL, List.of("Katuse aluskatted")),
            Map.entry(KORSTNAD, List.of("Metallkorstnad", "Moodulkorstnad")),
            Map.entry(TARVIKUD, List.of("Eterniitkatused", "Bituumenkatused", "PVC katusematerjalid")),
            Map.entry(VARIKATUSED, List.of("Päikesevarjud ja varikatused")),
            Map.entry(PLEKK, List.of("Laekarkassid", "Seinakarkassid")),
            Map.entry(ARMATUUR, List.of("Armatuur")),
            Map.entry(KANDURID, List.of("Armatuuritoed ja -kandurid")),
            Map.entry(TALDMIKU_VORMID, List.of("Armatuur")),
            Map.entry(KILED, List.of("Kattematerjalid")),
            Map.entry(AURUTOKE, List.of("Aurutõkked")),
            Map.entry(GEOTEKSTIIL, List.of("Geotekstiilid")),
            Map.entry(KATUSE_ALUSKATE, List.of("Katuse aluskatted")),
            Map.entry(KANGAD, List.of("Tuuletõkke kangad")),
            Map.entry(KOORMAKATTED, List.of("Koormarihmad ja -katted")),
            Map.entry(EHITUSPAPP, List.of("Ehituspapid ja -paberid")),
            Map.entry(TAPEET, List.of("Tapeedid", "Tapeet tellimisel", "Fototapeedid")),
            Map.entry(NURGAD, List.of("Ehitusmetall")),
            Map.entry(EHITUSMETALL_PLAADID, List.of("Ehitusmetall")),
            Map.entry(POSTIJALAD, List.of("Ehitusmetall")),
            Map.entry(EHITUSKOBAD, List.of("Ehitusmetall")),
            Map.entry(BETOONIHARGID, List.of("Ehitusmetall")),
            Map.entry(PALGIKINGAD, List.of("Ehitusmetall")),
            Map.entry(PLEKKPROFIILID, List.of("Ehitusmetall")),
            Map.entry(SISEUKSED, List.of("Siseuksed")),
            Map.entry(VALISUKSED, List.of("Välisuksed")),
            Map.entry(SAUNAUKSED, List.of("Saunauksed")),
            Map.entry(UKSELENGID, List.of("Siseuksed")),
            Map.entry(POONINGULUUGID, List.of("Pööninguluugid")),
            Map.entry(KONTROLLLUUGID, List.of("Kontroll-luugid")),
            Map.entry(AKNAD, List.of()), // TODO
            Map.entry(KATUSEAKNAD, List.of("Katuseaknad")),
            Map.entry(AKNAPROFIILID, List.of("Fassaadisegud ja tarvikud")),
            Map.entry(AKNALAUAD, List.of("Aknalauad")),
            Map.entry(SISEVARVID, List.of("Sisevärvid")),
            Map.entry(VALISVARVID, List.of("Välisvärvid")),
            Map.entry(KRUNT, List.of("Krundid")),
            Map.entry(SISE, List.of("Lakid, õlid, vahad")),
            Map.entry(VALIS, List.of("Lakid, õlid, vahad")),
            Map.entry(LIISTUD_PORAND, List.of("Puitliistud", "Seinapaneeli liistud", "Plast põrandaliistud")),
            Map.entry(AVATAITED, null),
            Map.entry(LIISTUD_LAGI, List.of("Penoplastist liistud ja laerosetid", "Seinapaneeli liistud")),
            Map.entry(VIIMISTLUSPLAADID_PORAND, List.of("Põrandaplaadid")),
            Map.entry(VIIMISTLUSPLAADID_LAGI, List.of("Laekatted penoplastist", "MDF seina- ja laepaneelid", "Plast seina- ja laepaneelid")),
            Map.entry(SEIN, List.of("SPC seinaplaadid", "SPC digitaalprint seinapaneelid", "Seinaplaadid", "MDF seina- ja laepaneelid", "Plast seina- ja laepaneelid")),
            Map.entry(LAHUSTID, List.of("Lahustid")),
            Map.entry(MAALRITEIP, List.of("Teibid")),
            Map.entry(TUULETOKKETEIP, List.of("Teibid")),
            Map.entry(UNIVERSAAL, List.of("Teibid")),
            Map.entry(SILIKOONID, List.of("Silikoonid ja hermeetikud")),
            Map.entry(LIIM_PUIT, List.of("Liimid")),
            Map.entry(LIIM_UNIVERSAAL, List.of("Liimid")),
            Map.entry(HUDROISOLATSIOON, List.of("Hüdroisolatsioon")),
            Map.entry(HERMEETIKUD, List.of(" Silikoonid ja hermeetikud")),
            Map.entry(KANALISATSIOON, List.of("Sisekanalisatsioon", "Väliskanalisatsioon")),
            Map.entry(VEETORUD, List.of("Veetorud ja -liitmikud")),
            Map.entry(VENTILATSIOON, List.of("Ventilatsioonitorud ja liitmikud")),
            Map.entry(VOOLIKUD, List.of("Voolikud")),
            Map.entry(LISATARVIKUD, List.of()), // TODO
            Map.entry(KRUVID, List.of("Kruvid")),
            Map.entry(NAELAD, List.of("Naelad")),
            Map.entry(TUUBLID, List.of("Kinnitustarvikud")),
            Map.entry(POLDID, List.of("Poldid, mutrid, seibid")),
            Map.entry(MUTRID, List.of("Poldid, mutrid, seibid")),
            Map.entry(NEEDID, List.of("Kinnitustarvikud")),
            Map.entry(SEIBID, List.of("Poldid, mutrid, seibid", "Kinnitustarvikud")),
            Map.entry(KLAMBRID, List.of("Kinnitustarvikud", "Klambripüstolid ja klambrid", "Aiavõrgud ja piirded", "MDF seina- ja laepaneelid", "Installatsioonitarvikud")),
            Map.entry(ANKRUD, List.of("Poldid, mutrid, seibid")),
            Map.entry(KEERMELATID, List.of("Keermelatid")),
            Map.entry(MUUD, List.of("Kruvid", "Naelad", "Kinnitustarvikud", "Poldid, mutrid, seibid", "Klambripüstolid ja klambrid", "Aiavõrgud ja piirded", "MDF seina- ja laepaneelid", "Installatsioonitarvikud", "Keermelatid")),
            Map.entry(KIPSITARVIKUD, null),
            Map.entry(BAUROCI_TARVIKUD, null),
            Map.entry(FIBOTARVIKUD, null)
    );

    public static final Map<Subcategory, Function<ProductParseDto, Boolean>> subcatAdditionalVerification = Map.ofEntries(
            Map.entry(OSB_PLAAT, (ProductParseDto product) -> {
                return true;
            }),
            Map.entry(TSEMENTKIUDPLAAT, (ProductParseDto product) -> {
                return true;
            }),
            Map.entry(PUITKIUDPLAAT, (ProductParseDto product) -> {
                return true;
            }),
            Map.entry(PUITLAASTPLAAT, (ProductParseDto product) -> {
                return true;
            }),
            Map.entry(WEDI_PLAAT, (ProductParseDto product) -> { // brändi põhjal?
                return true;
            }),
            Map.entry(TYCROC_PLAAT, (ProductParseDto product) -> { // brändi põhjal?
                return true;
            }),
            Map.entry(TEMPSI_PLAAT, (ProductParseDto product) -> { // brändi põhjal?
                return true;
            }),
            Map.entry(POORBETOON_BAUROC, (ProductParseDto product) -> { // brändi põhjal?
                return true;
            }),
            Map.entry(FIBO, (ProductParseDto product) -> { // brändi põhjal?
                return true;
            }),
            Map.entry(OONESPLOKK, (ProductParseDto product) -> { // märksõna "õõnesplokk" põhjal?
                return true;
            }),
            Map.entry(SILIKAATPLOKK, (ProductParseDto product) -> { // märksõna "silroc" põhjal?
                return true;
            }),
            Map.entry(KONNITEPLAAT, (ProductParseDto product) -> { // märksõna "kõnniteeplaat" põhjal?
                return true;
            }),
            Map.entry(AAREKIVID, (ProductParseDto product) -> { // märksõna "äärekivi" põhjal?
                return true;
            }),
            Map.entry(XPS, (ProductParseDto product) -> { // märksõna "XPS" põhjal?
                return true;
            }),
            Map.entry(PIR, (ProductParseDto product) -> { // märksõna "PIR" põhjal?
                return true;
            }),
            Map.entry(PRUSSID, (ProductParseDto product) -> { // märksõna "prussid" põhjal?
                return true;
            }),
            Map.entry(KROHV, (ProductParseDto product) -> { // toote nimes märksõna "krohv" põhjal?
                return true;
            }),
            Map.entry(MUURISEGU, (ProductParseDto product) -> { // toote nimes märksõna "müürisegu" põhjal?
                return true;
            }),
            Map.entry(TSEMENT, (ProductParseDto product) -> { // toote nimes märksõna "tsementsegu" põhjal?
                return true;
            }),
            Map.entry(BETOON, (ProductParseDto product) -> { // toote nimes märksõna "kuivbetoon", "betoonsegu" põhjal?
                return true;
            }),
            Map.entry(LIIM, (ProductParseDto product) -> { // toote nimes märksõna "liim" põhjal?
                return true;
            }),
            Map.entry(PORANDATASANDUS, (ProductParseDto product) -> { // toote nimes märksõna "liim" põhjal?
                return true;
            }),
            Map.entry(VALISVOODRILAUD, (ProductParseDto product) -> { // toote nimes märksõna "välisvooder", "välisvoodrilaud" põhjal?
                return true;
            }),
            Map.entry(TARVIKUD, (ProductParseDto product) -> { // toote nimes märksõna "plaat" välistamise põhjal?
                return true;
            }),
            Map.entry(VARIKATUSED, (ProductParseDto product) -> { // toote nimes märksõna "katus" põhjal?
                return true;
            }),
            Map.entry(PLEKK, (ProductParseDto product) -> { // toote nimes märksõna "plekk" põhjal?
                return true;
            }),
            Map.entry(TALDMIKU_VORMID, (ProductParseDto product) -> { // toote nimes märksõna "vorm" põhjal?
                return true;
            }),
            Map.entry(KILED, (ProductParseDto product) -> { // toote nimes märksõna "kile" põhjal?
                return true;
            }),
            Map.entry(KOORMAKATTED, (ProductParseDto product) -> { // toote nimes märksõna "kate" põhjal?
                return true;
            }),
            Map.entry(EHITUSPAPP, (ProductParseDto product) -> { // toote nimes märksõna "papp" põhjal?
                return true;
            }),
            Map.entry(NURGAD, (ProductParseDto product) -> { // toote nimes märksõna "nurk" või "nurgik" põhjal?
                return true;
            }),
            Map.entry(EHITUSMETALL_PLAADID, (ProductParseDto product) -> { // toote nimes märksõna "latt" või "plaat" põhjal?
                return true;
            }),
            Map.entry(POSTIJALAD, (ProductParseDto product) -> { // toote nimes märksõna "post" ja ("jalg" või "jalad") põhjal?
                return true;
            }),
            Map.entry(EHITUSKOBAD, (ProductParseDto product) -> { // toote nimes märksõna "ehituskoba" põhjal?
                return true;
            }),
            Map.entry(BETOONIHARGID, (ProductParseDto product) -> { // toote nimes märksõna "betoonhark" ja "betoonhargid" põhjal?
                return true;
            }),
            Map.entry(PALGIKINGAD, (ProductParseDto product) -> { // toote nimes märksõna "palgiking" põhjal?
                return true;
            }),
            Map.entry(PLEKKPROFIILID, (ProductParseDto product) -> { // toote nimes märksõna "plekkprofiil" põhjal?
                return true;
            }),
            Map.entry(UKSELENGID, (ProductParseDto product) -> { // toote nimes märksõna "ukseleng" põhjal?
                return true;
            }),
            Map.entry(AKNAPROFIILID, (ProductParseDto product) -> { // toote nimes märksõna "aknaprofiil" põhjal?
                return true;
            }),
            Map.entry(LIISTUD_PORAND, (ProductParseDto product) -> { // toote nimes märksõna "põrand" põhjal?
                return true;
            }),
            Map.entry(LIISTUD_LAGI, (ProductParseDto product) -> { // toote nimes märksõna "lae" või "lagi" põhjal?
                return true;
            }),
            Map.entry(VIIMISTLUSPLAADID_LAGI, (ProductParseDto product) -> { // toote nimes märksõna "lae" või "lagi" põhjal?
                return true;
            }),
            Map.entry(SEIN, (ProductParseDto product) -> { // toote nimes märksõna "sein" põhjal?
                return true;
            }),
            Map.entry(MAALRITEIP, (ProductParseDto product) -> { // toote nimes märksõna "maalri" põhjal?
                return true;
            }),
            Map.entry(TUULETOKKETEIP, (ProductParseDto product) -> { // toote nimes märksõna "tuuletõkke" või "tuuletõke" põhjal?
                return true;
            }),
            Map.entry(UNIVERSAAL, (ProductParseDto product) -> { // toote nimes märksõna "universaal" põhjal?
                return true;
            }),
            Map.entry(SILIKOONID, (ProductParseDto product) -> { // toote nimes märksõna "silikoon" põhjal?
                return true;
            }),
            Map.entry(LIIM_PUIT, (ProductParseDto product) -> { // toote nimes märksõna "puit" või "puidu" põhjal?
                return true;
            }),
            Map.entry(LIIM_UNIVERSAAL, (ProductParseDto product) -> { // toote nimes märksõna "universaal" põhjal?
                return true;
            }),
            Map.entry(HERMEETIKUD, (ProductParseDto product) -> { // toote nimes märksõna "hermeetik" põhjal?
                return true;
            }),
            Map.entry(TUUBLID, (ProductParseDto product) -> { // toote nimes märksõna "tüübel" või "tüüblid" põhjal?
                return true;
            }),
            Map.entry(POLDID, (ProductParseDto product) -> { // toote nimes märksõna "polt" või "poldid" põhjal?
                return true;
            }),
            Map.entry(MUTRID, (ProductParseDto product) -> { // toote nimes märksõna "mutter" või "mutrid" põhjal?
                return true;
            }),
            Map.entry(NEEDID, (ProductParseDto product) -> { // toote nimes märksõna "neet" või "needid" põhjal?
                return true;
            }),
            Map.entry(SEIBID, (ProductParseDto product) -> { // toote nimes märksõna "seib" põhjal?
                return true;
            }),
            Map.entry(KLAMBRID, (ProductParseDto product) -> { // toote nimes märksõna "klamber" või "klambri" põhjal?
                return true;
            }),
            Map.entry(ANKRUD, (ProductParseDto product) -> { // toote nimes märksõna "ankur" või "ankrud" põhjal?
                return true;
            }),
            Map.entry(MUUD, (ProductParseDto product) -> { // ülejäägid lihtsalt?
                // programmaatiliselt keeruline, ei saa seda hashmapi selle funktsiooni sees referencida
                return true;
            })
    );
}
