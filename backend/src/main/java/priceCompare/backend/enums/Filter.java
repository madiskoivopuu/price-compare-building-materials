package priceCompare.backend.enums;

import static priceCompare.backend.enums.Keyword.*;

import lombok.Getter;
import java.util.List;

public enum Filter {

    KIPS_KLASS("Klass", List.of(
            STANDARD,
            NIISKUSKINDEL,
            TUULETOKE,
            ERIKOVA,
            TULEKINDEL,
            PAINDETUGEV,
            PINNATUGEV,
            REMONDI,
            PÕRAND
    )),

    KIPS_LABIMOOT("Läbimõõt", List.of(
            L6_5,
            L9,
            L9_5,
            L12_5,
            L15,
            L15_5
    )),

    OSB_KLASS("Klass", List.of(
            OSB,
            OSB3
    )),

    OSB_TUUP("Tüüp", List.of(
            OSB_TAVALINE,
            OSB_TG4
    )),

    KIPS_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D900x1300,
            D900x2000,
            D900x2400,
            D1200x2000,
            D1200x2200,
            D1200x2400,
            D1200x2500,
            D1200x2600,
            D1200x2700,
            D1200x2800,
            D1200x3000,
            D1200x3300,
            D1200x3600
    )),

    OSB_LABIMOOT("Läbimõõt", List.of(
            L6,
            L8,
            L9,
            L10,
            L11,
            L12,
            L15,
            L18,
            L22,
            L25
    )),

    OSB_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D620x1250,
            D625x2500,
            D1250x2500
    )),

    VINEER_TUUP("Tüüp", List.of(
            VINEER_NIISKUSKINDEL,
            VINEER_VEEKINDEL,
            VINEER_FILMI
    )),

    VINEER_MATERJAL("Materjal", List.of(
            VINEER_KASK,
            VINEER_KUUSK,
            VINEER_EUKALUPT,
            VINEER_MAND,
            VINEER_PAPLI,
            VINEER_HAAVA
    )),

    VINEER_LABIMOOT("Läbimõõt", List.of(
            L4,
            L6_5,
            L9,
            L12,
            L15,
            L18,
            L21,
            L24,
            L27,
            L30
    )),

    VINEER_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D625x2500,
            D1220x2440,
            D1250x2500,
            D1500x3000,
            D1525x3050,
            D2150x3850
    )),

    TSEMENTKIUDPLAAT_TUUP("Tüüp", List.of(
            TSEMENTKIUDPLAAT_TAVALINE,
            TSEMENTKIUDPLAAT_SAUNA
    )),

    TSEMENTKIUDPLAAT_LABIMOOT("Läbimõõt", List.of(
            L3_5,
            L6,
            L8,
            L9,
            L10,
            L12,
            L14,
            L16,
            L18,
            L20,
            L24
    )),

    TSEMENTKIUDPLAAT_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D600x1200,
            D625x1250,
            D630x1200,
            D630x1250,
            D625x2500,
            D800x1250,
            D900x1200,
            D900x2400,
            D1200x1275,
            D1200x2400,
            D1200x2550,
            D1200x2600,
            D1200x3000,
            D1250x2600,
            D1250x2600,
            D1250x2700,
            D1250x3000,
            D1250x3050,
            D1250x3350
    )),

    TSEMENTLAASTPLAAT_LABIMOOT("Läbimõõt", List.of(
            L8
    )),

    TSEMENTLAASTPLAAT_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D1250x2600
    )),

    PUITKIUDPLAAT_TUUP("Tüüp", List.of(
            PUITKIUDPLAAT_TAVALINE,
            PUITKIUDPLAAT_PEHME,
            PUITKIUDPLAAT_KOVA,
            PUITKIUDPLAAT_PORAND
    )),

    PUITKIUDPLAAT_LABIMOOT("Läbimõõt", List.of(
            L3,
            L3_2,
            L6,
            L10,
            L12,
            L16,
            L18,
            L25
    )),

    PUITKIUDPLAAT_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D610x1220,
            D946x2070,
            D1200x2700,
            D1220x1220,
            D1220x2440,
            D1700x2745,
            D2070x2800
    )),

    PUITLAASTPLAAT_TUUP("Tüüp", List.of(
            PUITLAASTPLAAT_TAVALINE,
            PUITLAASTPLAAT_KAHE_SOONEGA,
            PUITLAASTPLAAT_NELJA_SOONEGA
    )),

    PUITLAASTPLAAT_LABIMOOT("Läbimõõt", List.of(
            L9,
            L12,
            L15,
            L16,
            L18,
            L19,
            L22,
            L25
    )),

    PUITLAASTPLAAT_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D600x2400,
            D675x2500,
            D1200x2500,
            D1200x2600,
            D1250x2500,
            D2070x2650,
            D2070x2800
    )),

    KIPSKIUDPLAAT_LABIMOOT("Läbimõõt", List.of(
            L10,
            L12_5,
            L15,
            L18,
            L22
    )),

    KIPSKIUDPLAAT_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D1000x1500,
            D1200x2400,
            D1200x2500,
            D1200x2600,
            D1200x2800,
            D1200x3000
    )),

    WEDIPLAAT_LABIMOOT("Läbimõõt", List.of(
            L6,
            L12_5,
            L20,
            L30,
            L40,
            L50
    )),

    WEDIPLAAT_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D600x1250,
            D600x2500
    )),

    TYCROC_TUUP("Tüüp", List.of(
            TYCROC_VEEKINDEL_FXW,
            TYCROC_VEEKINDEL_TWP,
            TYCROC_VEEKINDEL_FXL,
            TYCROC_PORAND,
            TYCROC_DUSIALUS
    )),

    TYCROC_LABIMOOT("Läbimõõt", List.of(
            L12,
            L20,
            L25,
            L30,
            L50
    )),

    TYCROC_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D600x1200,
            D600x2500,
            D900x900,
            D1000x1000,
            D1000x1500,
            D1250x2500
    )),

    KIVIVILL_TUUP("Tüüp", List.of(
            KIVIVILL_PLAAT,
            KIVIVILL_RULL,
            KIVIVILL_KROHVITAV,
            KIVIVILL_SAMMUMÜRA
    )),

    KIVIVILL_LABIMOOT("Läbimõõt", List.of(
            L20,
            L30,
            L40,
            L42,
            L50,
            L66,
            L75,
            L100,
            L125,
            L150,
            L200,
            L250,
            L300
    )),

    KIVIVILL_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D565x1000,
            D565x1220,
            D600x1000,
            D600x1200,
            D610x1000,
            D610x1220
    )),

    KLAASVILL_TUUP("Tüüp", List.of(
            KLAASVILL_PLAAT,
            KLAASVILL_RULL
    )),

    KLAASVILL_LABIMOOT("Läbimõõt", List.of(
            L20,
            L30,
            L40,
            L42,
            L50,
            L66,
            L75,
            L100,
            L125,
            L150
    )),

    KLAASVILL_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D560x870,
            D565x870,
            D565x1250,
            D610x1250,
            D610x1310
    )),

    EPS_TUUP("Tüüp", List.of(
            EPS60,
            EPS80_100,
            EPS120,
            EPS_L_ELEMENT
    )),

    EPS_LABIMOOT("Läbimõõt", List.of(
            L10,
            L25,
            L50,
            L100,
            L150
    )),

    EPS_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D600x1000,
            D600x1200,
            D1000x1200
    )),

    XPS_TUUP("Tüüp", List.of(
            XPS_PLAAT,
            XPS_L_ELEMENT
    )),

    XPS_LABIMOOT("Läbimõõt", List.of(
            L20,
            L30,
            L50,
            L100,
            L150
    )),

    XPS_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D585x1185,
            D600x1200,
            D1000x1200
    )),

    PIR_TUUP("Tüüp", List.of(
            PIR_FOOLIUMIGA,
            PIR_KROHVITAV,
            PIR_TULEKINDLAM,
            PIR_KIPSIGA
    )),

    PIR_LABIMOOT("Läbimõõt", List.of(
            L20,
            L30,
            L50,
            L70,
            L100,
            L120,
            L150,
            L200
    )),

    PIR_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D590x1200,
            D600x1200,
            D600x2400,
            D1200x2400
    )),

    TUULETOKE_TUUP("Tüüp", List.of(
            TUULETOKE_KLAASVILL,
            TUULETOKE_KIVIVILL,
            TUULETOKE_PUITKIUD
    )),

    TUULETOKE_LABIMOOT("Läbimõõt", List.of(
            L12,
            L13,
            L16,
            L18,
            L20,
            L22,
            L25,
            L30,
            L35,
            L50,
            L75,
            L100,
            L120,
            L180,
            L205
    )),

    TUULETOKE_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D600x1200,
            D600x1500,
            D600x2230,
            D600x2420,
            D800x2400,
            D1200x1800,
            D1200x1875,
            D1200x2700,
            D1200x3000,
            D1250x2500
    )),

    PRUSSID_TUUP("Tüüp", List.of(
            PUIT_KUIV,
            PUIT_MARG,
            PUIT_IMMUTATUD
    )),

    PRUSSID_LABIMOOT("Läbimõõt", List.of(
            L50,
            L75,
            L100,
            L150,
            L200
    )),

    PRUSSID_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D50x2400,
            D50x3000,
            D50x3600,
            D50x4200,
            D50x4800,
            D75x4800,
            D100x3000,
            D100x3600,
            D100x4200,
            D100x4800,
            D100x6000,
            D150x3000,
            D150x3600,
            D150x4200,
            D150x4800,
            D150x6000,
            D200x3600,
            D200x4200,
            D200x4800,
            D200x6000
    )),

    LAUAD_TUUP("Tüüp", List.of(
            PUIT_KUIV,
            PUIT_MARG,
            PUIT_IMMUTATUD
    )),

    LAUAD_LABIMOOT("Läbimõõt", List.of(
            L22,
            L32,
            L37
    )),

    LAUAD_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D50x3000,
            D50x3600,
            D50x3900,
            D50x4200,
            D50x4500,
            D50x4800,
            D50x5100,
            D50x6000,
            D75x3000,
            D75x3600,
            D75x4200,
            D75x4800,
            D100x3000,
            D100x3300,
            D100x3600,
            D100x3900,
            D100x4200,
            D100x4500,
            D100x4800,
            D100x5100,
            D100x5400,
            D100x5700,
            D100x6000,
            D125x3000,
            D125x4800,
            D125x5400,
            D125x6000,
            D150x3000,
            D150x3600,
            D150x4200,
            D150x4800,
            D150x5100,
            D150x6000,
            D200x4200,
            D200x4800,
            D200x5400,
            D200x6000
    )),

    HOOVELPRUSSID_TUUP("Tüüp", List.of(
            IMMUTAMATA,
            IMMUTATUD,
            TUGEVUSSORT
    )),

    HOOVELPRUSSID_LABIMOOT("Läbimõõt", List.of(
            L28,
            L32,
            L45,
            L70,
            L95
    )),

    HOOVELPRUSSID_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D45x2400,
            D45x2700,
            D45x3000,
            D50x3600,
            D45x4200,
            D45x4800,
            D45x5400,
            D45x6000,
            D70x2400,
            D70x2700,
            D70x3000,
            D70x4200,
            D70x4800,
            D70x5400,
            D70x6000,
            D95x3000,
            D95x4800,
            D95x5100,
            D95x5400,
            D95x6000,
            D120x4800,
            D120x5400,
            D120x6000,
            D145x3000,
            D145x3600,
            D145x4200,
            D145x4800,
            D145x5400,
            D145x6000,
            D195x3000,
            D195x4200,
            D195x4800,
            D195x5400,
            D195x6000,
            D195x7200,
            D220x5100,
            D220x5400,
            D245x4800,
            D245x5100,
            D245x5400,
            D245x6000
    )),

    HOOVELLAUAD_TUUP("Tüüp", List.of(
            IMMUTAMATA,
            IMMUTATUD,
            TUGEVUSSORT
    )),

    HOOVELLAUAD_LABIMOOT("Läbimõõt", List.of(
            L28,
            L32,
            L45,
            L70,
            L95
    )),

    HOOVELLAUAD_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D45x2700,
            D45x3000,
            D45x4800,
            D45x5400,
            D45x6000,
            D70x2700,
            D70x3000,
            D95x3000,
            D95x3600,
            D95x4200,
            D95x4800,
            D95x5100,
            D95x5400,
            D95x6000,
            D120x3000,
            D120x4200,
            D120x4800,
            D120x5100,
            D120x5400,
            D120x6000,
            D145x3000,
            D145x4200,
            D145x4800,
            D145x5400,
            D145x6000,
            D195x4800
    )),

    LIIMPUIT_TUUP("Tüüp", List.of(
            TALAD,
            RISTKIHTPLAAT,
            LVL
    )),

    LIIMPUIT_LABIMOOT("Läbimõõt", List.of(
            L19,
            L39,
            L45,
            L51,
            L90,
            L100,
            L115,
            L120,
            L140,
            L200
    )),

    LIIMPUIT_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D45x2700,
            D45x3000,
            D45x4800,
            D45x5400,
            D45x6000,
            D70x2700,
            D70x3000,
            D95x3000,
            D95x3600,
            D95x4200,
            D95x4800,
            D95x5100,
            D95x5400,
            D95x6000,
            D120x3000,
            D120x4200,
            D120x4800,
            D120x5100,
            D120x5400,
            D120x6000,
            D145x3000,
            D145x4200,
            D145x4800,
            D145x5400,
            D145x6000,
            D195x4800
    )),

    PORANDALAUD_TUUP("Tüüp", List.of(
            PORANDALAUD_NATURAALSED,
            PORANDALAUD_VIIMISTLETUD
    )),

    PORANDALAUD_LABIMOOT("Läbimõõt", List.of(
            L18,
            L22,
            L33,
            L40
    )),

    PORANDALAUD_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D120x4200,
            D120x4500,
            D120x4800,
            D120x5100,
            D120x5400,
            D145x2050,
            D145x2550,
            D145x2700,
            D145x3600,
            D145x3900,
            D145x4200,
            D145x4500,
            D145x4800,
            D145x5100,
            D195x4500,
            D195x4800,
            D195x5100,
            D195x5400,
            D195x6000,
            D245x4500,
            D245x4800,
            D245x5100
    )),

    TERRASSILAUD_TUUP("Tüüp", List.of(
            TERRASSILAUD_IMMUTATUD,
            TERRASSILAUD_NATURAALSED,
            TERRASILAUD_VIIMISTLETUD,
            TERRASILAUD_TERMO,
            TERRASILAUD_KOMPOSIIT
    )),

    TERRASSILAUD_LABIMOOT("Läbimõõt", List.of(
            L15,
            L20,
            L21,
            L25,
            L26,
            L28
    )),

    TERRASSILAUD_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D80x4200,
            D95x3000,
            D95x3600,
            D95x3900,
            D95x4200,
            D95x4500,
            D95x4800,
            D95x5100,
            D95x5400,
            D95x5700,
            D95x6000,
            D115x3000,
            D115x4200,
            D115x4800,
            D115x5100,
            D117x4500,
            D118x2700,
            D118x3000,
            D118x4200,
            D118x4800,
            D119x1850,
            D120x3000,
            D120x3300,
            D120x3600,
            D120x3900,
            D120x4000,
            D120x4200,
            D120x4500,
            D120x4800,
            D120x5100,
            D120x5400,
            D120x5700,
            D120x6000,
            D140x2700,
            D140x2900,
            D140x3000,
            D140x3300,
            D140x3900,
            D140x4200,
            D140x4500,
            D140x4800,
            D140x5100,
            D145x3000,
            D145x3600,
            D145x3900,
            D145x4000,
            D145x4200,
            D145x4500,
            D145x4800,
            D145x5100,
            D145x5400,
            D145x5700,
            D145x6000,
            D148x4200,
            D150x4200
    )),

    SISEVOODRILAUD_TUUP("Tüüp", List.of(
            SISEVOODRILAUD_NATURAALSED,
            SISEVOODRILAUD_VIIMISTLETUD,
            SISEVOODRILAUD_TERMO
    )),

    SISEVOODRILAUD_LABIMOOT("Läbimõõt", List.of(
            L12,
            L14,
            L15,
            L18,
            L20,
            L28
    )),

    SISEVOODIRLAUD_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D95x2100,
            D95x2400,
            D95x2700,
            D95x3000,
            D95x4200,
            D95x4800,
            D95x5100,
            D95x5400,
            D120x2100,
            D120x2400,
            D120x2700,
            D120x3000,
            D120x3300,
            D120x4200,
            D120x4800,
            D120x5100,
            D120x5400,
            D145x2700,
            D145x3000,
            D145x3300,
            D145x3900,
            D145x4200,
            D145x4800,
            D145x5100,
            D145x5400
    )),

    VALISVOODRILAUD_TUUP("Tüüp", List.of(
            VALISVOODRILAUD_NATURAALSED,
            VALISVOODRILAUD_KRUNDITUD,
            VALISVOODRILAUD_VARVITUD,
            VALISVOODRILAUD_TERMO
    )),

    VALISVOODRILAUD_LABIMOOT("Läbimõõt", List.of(
            L18,
            L19,
            L20,
            L21,
            L22
    )),

    VALISVOODRILAUD_LAIUS_PIKKUS("Laius x pikkus", List.of(
            D95x4000,
            D95x4800,
            D95x5100,
            D95x5400,
            D95x6000,
            D120x3000,
            D120x4800,
            D120x5100,
            D120x5400,
            D120x6000,
            D145x4200,
            D145x4800,
            D145x5100,
            D145x5400,
            D145x6000,
            D146x5400,
            D146x6000,
            D195x4800,
            D195x5400,
            D195x6000
    )),

    PLASTVOODER_TUUP("Tüüp", List.of(
            PLASTVOODER_FASSAAD,
            PLASTVOODER_PLAAT,
            PLASTVOODER_TUULEKAST
    )),
    ;

    @Getter
    private final String displayName;
    @Getter
    private final List<Keyword> keywords;

    Filter(String displayName, List<Keyword> keywords) {
        this.displayName = displayName;
        this.keywords = keywords;
    }
}
