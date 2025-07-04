package priceCompare.backend.stores.bauhof.service;

import static priceCompare.backend.enums.Subcategory.*;

import priceCompare.backend.enums.Subcategory;
import java.util.List;
import java.util.Map;

public class EmaterjalToBauhofCategoryMapping {
    public static final Map<Subcategory, List<String>> categoryMap = Map.<Subcategory, List<String>>ofEntries(
            Map.entry(KIPSPLAAT, List.of("Kipsplaadid")),
            Map.entry(OSB_PLAAT, List.of("Puitlaastplaadid")),
            Map.entry(VINEER, List.of("Vineerid")),
            Map.entry(TSEMENTKIUDPLAAT, List.of("Eriotstarbelised plaadid", "Fassaadiplaadid")),
            Map.entry(PUITKIUDPLAAT, List.of("Puitkiudplaadid")),
            Map.entry(PUITLAASTPLAAT, List.of("Puitlaastplaadid")),
            Map.entry(KIPSKIUDPLAAT, List.of()),
            Map.entry(WEDI_PLAAT, List.of()),
            Map.entry(TYCROC_PLAAT, List.of("Eriotstarbelised plaadid")),
            Map.entry(POORBETOON_BAUROC, List.of("Plokid, kivid ja tarvikud")),
            Map.entry(FIBO, List.of("Plokid, kivid ja tarvikud")),
            Map.entry(OONESPLOKK, List.of("Plokid, kivid ja tarvikud")),
            Map.entry(SILIKAATPLOKK, List.of("Plokid, kivid ja tarvikud")),
            Map.entry(LAKKAPLOKK, List.of()),
            Map.entry(KERATERM, List.of()),
            Map.entry(VUNDAMENDIPLOKK, List.of()),
            Map.entry(TELLIS, List.of("Tellised")),
            Map.entry(SILLUSED, List.of("Sillused")),
            Map.entry(TANAVAKIVI, List.of("Tänavakivid")),
            Map.entry(KONNITEPLAAT, List.of("Tänavakivid")),
            Map.entry(AAREKIVID, List.of("Tänavakivid")),
            Map.entry(VIHMAVEERENN, List.of("Tänavakivid")),
            Map.entry(KORSTNAD, List.of("Moodulkorstnad")),
            Map.entry(KIVIVILL, List.of("Kivivillad")),
            Map.entry(KLAASVILL, List.of("Klaasvillad")),
            Map.entry(TSELLUVILL, List.of("Tselluvillad")),
            Map.entry(EPS, List.of("Vahtplastid")),
            Map.entry(PIR, List.of("Vahtplastid")),
            Map.entry(XPS, List.of()),
            Map.entry(TUULETOKKEPLAAT, List.of("Puitkiudplaadid")),
            Map.entry(PRUSSID, List.of("Puit", "Saematerjal")),
            Map.entry(LAUAD, List.of("Saematerjal", "Immutataud puit")),
            Map.entry(KALIBREERITUD_PRUSSID, List.of()),
            Map.entry(KALIBREERITUD_LAUAD, List.of()),
            Map.entry(HOOVELPRUSSID, List.of("Hööveldatud puit")),
            Map.entry(HOOVELLAUAD, List.of()),
            Map.entry(SERVAMATA, List.of()),
            Map.entry(LIIMPUIT, List.of()),
            Map.entry(PORANDALAUAD, List.of("Hööveldatud puit")),
            Map.entry(TERRASSILAUAD, List.of("Terrassimaterjal")),
            Map.entry(SISEVOODRILAUAD, List.of("Hööveldatud puit")),
            Map.entry(PAHTEL, List.of("Pahtlid")),
            Map.entry(KROHV, List.of("Krohvid", "Põrandasegud")),
            Map.entry(MUURISEGU, List.of("Ehitussegud", "Põrandasegud")),
            Map.entry(PLAATIMISSEGU, List.of()),
            Map.entry(VUUGISEGUD, List.of()),
            Map.entry(AHJUSEGUD, List.of("Põrandasegud")),
            Map.entry(TSEMENT, List.of("Ehitussegud", "Põrandasegud")),
            Map.entry(KUIVBETOON, List.of("Ehitussegud")),
            Map.entry(LIIM, List.of("Ehitussegud")),
            Map.entry(PORANDATASANDUS, List.of("Põrandasegud")),
            Map.entry(SEGUD_TARVIKUD, List.of("Krohvi lisatarvikud")),
            Map.entry(SIDEAINED, List.of("Killustik", "Ehitussegud")),
            Map.entry(VALISVOODRILAUAD, List.of("Hööveldatud puit")),
            Map.entry(TSEMENTVOODRILAUAD, List.of("Fassaadiplaadid")),
            Map.entry(PLASTVOODER, List.of()),
            Map.entry(FASSAAD_PLAADID, List.of("Fassaadiplaadid")),
            Map.entry(PROFIILID, List.of()),
            Map.entry(FASSAAD_TARVIKUD, List.of("Fassaadiplaatide lisatarvikud")),
            Map.entry(KIVI, List.of("Kivikatused")),
            Map.entry(TERAS, List.of("Plekk-katused")),
            Map.entry(ETERNIIT, List.of("Eterniitkatused")),
            Map.entry(BITUUMEN, List.of("Bituumenkatused")),
            Map.entry(PVC_PC, List.of("Plastikkatused")),
            Map.entry(VIHMAVEESUSTEEMID, List.of("Vihmaveesüsteemid")),
            Map.entry(KATUSETARVIKUD, List.of("Katusekatted ja lisatarvikud")),
            Map.entry(EHITUSPABER, List.of("Ehituspaberid ja kiled")),
            Map.entry(KANGAD, List.of("Ehituspaberid ja kiled")),
            Map.entry(KILED, List.of("Ehituspaberid ja kiled")),
            Map.entry(AURUTOKE, List.of("Ehituspaberid ja kiled")),
            Map.entry(GEOTEKSTIIL, List.of("Ehituspaberid ja kiled")),
            Map.entry(KATUSE_ALUSKATE, List.of("Ehituspaberid ja kiled")),
            Map.entry(ARMATUUR, List.of("Armatuurid")),
            Map.entry(PLEKKPROFIILID, List.of("Karkassid"))
    );

}
