package priceCompare.backend.enums;

import lombok.Getter;
import priceCompare.backend.utils.CombiationGen;
import priceCompare.backend.utils.MetricUnit;
import priceCompare.backend.utils.Tuple;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum Keyword {
    STANDARD("Standard (A, GN13)", List.of("niiskuskindel", "H1", "H2", "H3", "GKBI", "tuuletõke", "tuuletõkke", "EH1", "EH2", "EH3", "GTS", "GTS9", "KTS", "erikova", " D ", "GEK", "GEK13", "tulekindel", "tulekindlam", "tule", " F ", "GFL", "GKF", "paindetugev", " R ", "pinnatugev", " I ", "remondi", "remont", "põrand", "erikõva", "eritugev"), true),
    NIISKUSKINDEL("Niiskuskindlam (H1, H2, H3, GKBI)", List.of("niiskuskindel", "H1", "H2", "H3", "GKBI")),
    TUULETOKE("Tuuletõke (EH1, EH2, EH3, GTS, GTS9, KTS)", List.of("tuuletõke", "tuuletõkke", "tuule", "EH1", "EH2", "EH3", "GTS", "GTS9", "KTS")),
    ERIKOVA("Erikõva (D, GEK, GEK13)", List.of("erikova", " D ", "GEK", "GEK13")),
    TULEKINDEL("Tulekindel (F, GFL, GKF)", List.of("tulekindel", "tulekindlam", "tule", " F ", "GFL", "GKF")),
    PAINDETUGEV("Paindetugev (R)", List.of("paindetugev", " R ")),
    PINNATUGEV("Pinnatugev (I)", List.of("pinnatugev", " I ")),
    REMONDI("Remondi", List.of("remondi", "remont")),
    PÕRAND("Põrand", List.of("põrand")),

    L3("3.5", List.of(" 3 ", " 3x", "x3 ", " 3*", "*3 ")),
    L3_2("3.2", List.of(" 3,2 ", " 3.2 ", " 3.2x", "x3.2 ", " 3.2*", "*3.2 ")),
    L3_5("3.5", List.of(" 3,5 ", " 3.5 ", " 3.5x", "x3.5 ", " 3.5*", "*3.5 ")),
    L4("4", List.of(" 4 ", " 4x", "x4 ", " 4*", "*4 ")),
    L6("6", List.of(" 6 ", " 6x", "x6 ", " 6*", "*6 ")),
    L6_5("6.5", List.of(" 6,5 ", " 6.5 ", " 6.5x", "x6.5 ", " 6.5*", "*6.5 ")),
    L8("8", List.of(" 8 ", " 8x", "x8 ", " 8*", "*8 ")),
    L9("9", List.of(" 9 ", " 9x", "x9 ", " 9*", "*9 ")),
    L9_5("9.5", List.of(" 9,5 ", " 9.5 ", " 9.5x", "x9.5 ", " 9.5*", "*9.5 ")),
    L10("10", List.of(" 10 ", " 10x", "x10 ", " 10*", "*10 ")),
    L11("11", List.of(" 11 ", " 11x", "x11 ", " 11*", "*11 ")),
    L12("12", List.of(" 12 ", " 12x", "x12 ", " 12*", "*12 ")),
    L12_5("12.5", List.of(" 12,5 ", " 12.5 ", " 12.5x", "x12.5 ", " 12.5*", "*12.5 ")),
    L13("13", List.of(" 13 ", " 13x", "x13 ", " 13*", "*13 ")),
    L14("14", List.of(" 14 ", " 14x", "x14 ", " 14*", "*14 ")),
    L15("15", List.of(" 15 ", " 15x", "x15 ", " 15*", "*15 ")),
    L15_5("15.5", List.of(" 15,5 ", " 15.5 ", " 15.5x", "x15.5 ", " 15.5*", "*15.5 ")),
    L16("16", List.of(" 16 ", " 16x", "x16 ", " 16*", "*16 ")),
    L18("18", List.of(" 18 ", " 18x", "x18 ", " 18*", "*18 ")),
    L19("19", List.of(" 19 ", " 19x", "x19 ", " 19*", "*19 ")),
    L20("20", List.of(" 20 ", " 20x", "x20 ", " 20*", "*20 ")),
    L21("21", List.of(" 21 ", " 21x", "x21 ", " 21*", "*21 ")),
    L22("22", List.of(" 22 ", " 22x", "x22 ", " 22*", "*22 ")),
    L24("24", List.of(" 24 ", " 24x", "x24 ", " 24*", "*24 ")),
    L25("25", List.of(" 25 ", " 25x", "x25 ", " 25*", "*25 ")),
    L26("26", List.of(" 26 ", " 26x", "x26 ", " 26*", "*26 ")),
    L27("27", List.of(" 27 ", " 27x", "x27 ", " 27*", "*27 ")),
    L28("28", List.of(" 28 ", " 28x", "x28 ", " 28*", "*28 ")),
    L30("30", List.of(" 30 ", " 30x", "x30 ", " 30*", "*30 ")),
    L32("32", List.of(" 32 ", " 32x", "x32 ", " 32*", "*32 ")),
    L33("33", List.of(" 33 ", " 33x", "x33 ", " 33*", "*33 ")),
    L35("35", List.of(" 35 ", " 35x", "x35 ", " 35*", "*35 ")),
    L37("37", List.of(" 37 ", " 37x", "x37 ", " 37*", "*37 ")),
    L39("39", List.of(" 39 ", " 39x", "x39 ", " 39*", "*39 ")),
    L40("40", List.of(" 40 ", " 40x", "x40 ", " 40*", "*40 ")),
    L42("42", List.of(" 42 ", " 42x", "x42 ", " 42*", "*42 ")),
    L45("45", List.of(" 45 ", " 45x", "x45 ", " 45*", "*45 ")),
    L50("50", List.of(" 50 ", " 50x", "x50 ", " 50*", "*50 ")),
    L51("51", List.of(" 51 ", " 51x", "x51 ", " 51*", "*51 ")),
    L66("66", List.of(" 66 ", " 66x", "x66 ", " 66*", "*66 ")),
    L70("70", List.of(" 70 ", " 70x", "x70 ", " 70*", "*70 ")),
    L75("75", List.of(" 75 ", " 75x", "x75 ", " 75*", "*75 ")),
    L90("90", List.of(" 90 ", " 90x", "x90 ", " 90*", "*90 ")),
    L95("95", List.of(" 95 ", " 95x", "x95 ", " 95*", "*95 ")),
    L100("100", List.of(" 100 ", " 100x", "x100 ", " 100*", "*100 ")),
    L115("115", List.of(" 115 ", " 115x", "x115 ", " 115*", "*115 ")),
    L120("120", List.of(" 120 ", " 120x", "x120 ", " 120*", "*120 ")),
    L125("125", List.of(" 125 ", " 125x", "x125 ", " 125*", "*125 ")),
    L140("140", List.of(" 140 ", " 140x", "x140 ", " 140*", "*140 ")),
    L150("150", List.of(" 150 ", " 150x", "x150 ", " 150*", "*150 ")),
    L180("180", List.of(" 180 ", " 180x", "x180 ", " 180*", "*180 ")),
    L200("200", List.of(" 200 ", " 200x", "x200 ", " 200*", "*200 ")),
    L205("205", List.of(" 205 ", " 205x", "x205 ", " 205*", "*205 ")),
    L250("250", List.of(" 250 ", " 250x", "x250 ", " 250*", "*250 ")),
    L300("300", List.of(" 300 ", " 300x", "x300 ", " 300*", "*300 ")),




    D45x2400("45x2400", generateTwoUnitCombinations(45, 2400)),
    D45x2700("45x2700", generateTwoUnitCombinations(45, 2700)),
    D45x3000("45x3000", generateTwoUnitCombinations(45, 3000)),
    D45x4200("45x4200", generateTwoUnitCombinations(45, 4200)),
    D45x4800("45x4800", generateTwoUnitCombinations(45, 4800)),
    D45x5400("45x5400", generateTwoUnitCombinations(45, 5400)),
    D45x6000("45x6000", generateTwoUnitCombinations(45, 6000)),
    D50x2400("50x2400", generateTwoUnitCombinations(50, 2400)),
    D50x3000("50x3000", generateTwoUnitCombinations(50, 3000)),
    D50x3600("50x3600", generateTwoUnitCombinations(50, 3600)),
    D50x3900("50x3900", generateTwoUnitCombinations(50, 3900)),
    D50x4200("50x4200", generateTwoUnitCombinations(50, 4200)),
    D50x4500("50x4500", generateTwoUnitCombinations(50, 4500)),
    D50x4800("50x4800", generateTwoUnitCombinations(50, 4800)),
    D50x5100("50x5100", generateTwoUnitCombinations(50, 5100)),
    D50x6000("50x6000", generateTwoUnitCombinations(50, 6000)),
    D70x2400("70x2400", generateTwoUnitCombinations(70, 2400)),
    D70x2700("70x2700", generateTwoUnitCombinations(70, 2700)),
    D70x3000("70x3000", generateTwoUnitCombinations(70, 3000)),
    D70x4200("70x4200", generateTwoUnitCombinations(70, 4200)),
    D70x4800("70x4800", generateTwoUnitCombinations(70, 4800)),
    D70x5400("70x5400", generateTwoUnitCombinations(70, 5400)),
    D70x6000("70x6000", generateTwoUnitCombinations(70, 6000)),
    D75x3000("75x3000", generateTwoUnitCombinations(75, 3000)),
    D75x3600("75x3600", generateTwoUnitCombinations(75, 3600)),
    D75x4200("75x4200", generateTwoUnitCombinations(75, 4200)),
    D75x4800("75x4800", generateTwoUnitCombinations(75, 4800)),
    D95x3000("95x3000", generateTwoUnitCombinations(95, 3000)),
    D95x3600("95x3600", generateTwoUnitCombinations(95, 3600)),
    D95x4800("95x4800", generateTwoUnitCombinations(95, 4800)),
    D95x4200("95x4200", generateTwoUnitCombinations(95, 4200)),
    D95x5100("95x5100", generateTwoUnitCombinations(95, 5100)),
    D95x5400("95x5400", generateTwoUnitCombinations(95, 5400)),
    D95x6000("95x6000", generateTwoUnitCombinations(95, 6000)),
    D100x3000("100x3000", generateTwoUnitCombinations(100, 3000)),
    D100x3300("100x3300", generateTwoUnitCombinations(100, 3300)),
    D100x3600("100x3600", generateTwoUnitCombinations(100, 3600)),
    D100x3900("100x3900", generateTwoUnitCombinations(100, 3900)),
    D100x4200("100x4200", generateTwoUnitCombinations(100, 4200)),
    D100x4500("100x4500", generateTwoUnitCombinations(100, 4500)),
    D100x4800("100x4800", generateTwoUnitCombinations(100, 4800)),
    D100x5100("100x5100", generateTwoUnitCombinations(100, 5100)),
    D100x5400("100x5400", generateTwoUnitCombinations(100, 5400)),
    D100x5700("100x5700", generateTwoUnitCombinations(100, 5700)),
    D100x6000("100x6000", generateTwoUnitCombinations(100, 6000)),
    D125x3000("125x3000", generateTwoUnitCombinations(125, 3000)),
    D120x4800("120x4800", generateTwoUnitCombinations(120, 4800)),
    D120x3000("120x3000", generateTwoUnitCombinations(120, 3000)),
    D120x4200("120x4200", generateTwoUnitCombinations(120, 4200)),
    D120x5100("120x5100", generateTwoUnitCombinations(120, 5100)),
    D120x5400("120x5400", generateTwoUnitCombinations(120, 5400)),
    D120x6000("120x6000", generateTwoUnitCombinations(120, 6000)),
    D125x4800("125x4800", generateTwoUnitCombinations(125, 4800)),
    D125x5400("125x5400", generateTwoUnitCombinations(125, 5400)),
    D125x6000("125x6000", generateTwoUnitCombinations(125, 6000)),
    D145x3000("145x3000", generateTwoUnitCombinations(145, 3000)),
    D145x3600("145x3600", generateTwoUnitCombinations(145, 3600)),
    D145x4200("145x4200", generateTwoUnitCombinations(145, 4200)),
    D145x4800("145x4800", generateTwoUnitCombinations(145, 4800)),
    D145x5400("145x5400", generateTwoUnitCombinations(145, 5400)),
    D145x6000("145x6000", generateTwoUnitCombinations(145, 6000)),
    D150x3000("150x3000", generateTwoUnitCombinations(150, 3000)),
    D150x3600("150x3600", generateTwoUnitCombinations(150, 3600)),
    D150x4200("150x4200", generateTwoUnitCombinations(150, 4200)),
    D150x4800("150x4800", generateTwoUnitCombinations(150, 4800)),
    D150x5100("150x5100", generateTwoUnitCombinations(150, 5100)),
    D150x6000("150x6000", generateTwoUnitCombinations(150, 6000)),
    D195x3000("195x3000", generateTwoUnitCombinations(195, 3000)),
    D195x4200("195x4200", generateTwoUnitCombinations(195, 4200)),
    D195x4800("195x4800", generateTwoUnitCombinations(195, 4800)),
    D195x5400("195x5400", generateTwoUnitCombinations(195, 5400)),
    D195x6000("195x6000", generateTwoUnitCombinations(195, 6000)),
    D195x7200("195x7200", generateTwoUnitCombinations(195, 7200)),
    D200x4200("200x4200", generateTwoUnitCombinations(200, 4200)),
    D200x4800("200x4800", generateTwoUnitCombinations(200, 4800)),
    D200x5400("200x5400", generateTwoUnitCombinations(200, 5400)),
    D200x6000("200x6000", generateTwoUnitCombinations(200, 6000)),
    D200x3600("200x3600", generateTwoUnitCombinations(200, 3600)),
    D220x5100("220x5100", generateTwoUnitCombinations(220, 5100)),
    D220x5400("220x5400", generateTwoUnitCombinations(220, 5400)),
    D245x4800("245x4800", generateTwoUnitCombinations(245, 4800)),
    D245x5100("245x5100", generateTwoUnitCombinations(245, 5100)),
    D245x5400("245x5400", generateTwoUnitCombinations(245, 5400)),
    D245x6000("245x6000", generateTwoUnitCombinations(245, 6000)),
    D560x870("560x870", generateTwoUnitCombinations(560, 870)),
    D565x870("565x870", generateTwoUnitCombinations(565, 870)),
    D565x1250("565x1250", generateTwoUnitCombinations(565, 1250)),
    D565x1000("565x1000", generateTwoUnitCombinations(565, 1000)),
    D565x1220("565x1220", generateTwoUnitCombinations(565, 1220)),
    D585x1185("585x1185", generateTwoUnitCombinations(585, 1185)),
    D585x1235("585x1235", generateTwoUnitCombinations(585, 1235)),
    D585x2485("585x2485", generateTwoUnitCombinations(585, 2485)),
    D590x1200("590x1200", generateTwoUnitCombinations(590, 1200)),
    D600x1000("600x1000", generateTwoUnitCombinations(600, 1000)),
    D600x1200("600x1200", generateTwoUnitCombinations(600, 1200)),
    D600x1250("600x1250", generateTwoUnitCombinations(600, 1250)),
    D600x1500("600x1500", generateTwoUnitCombinations(600, 1500)),
    D600x2230("600x2230", generateTwoUnitCombinations(600, 2230)),
    D600x2400("600x2400", generateTwoUnitCombinations(600, 2400)),
    D600x2420("600x2420", generateTwoUnitCombinations(600, 2420)),
    D600x2500("600x2500", generateTwoUnitCombinations(600, 2500)),
    D610x1000("610x1000", generateTwoUnitCombinations(610, 1000)),
    D610x1220("610x1220", generateTwoUnitCombinations(610, 1220)),
    D610x1250("610x1250", generateTwoUnitCombinations(610, 1250)),
    D610x1310("610x1310", generateTwoUnitCombinations(610, 1310)),
    D620x1250("620x1250", generateTwoUnitCombinations(620, 1250)),
    D625x1250("625x1250", generateTwoUnitCombinations(625, 1250)),
    D625x2500("625x2500", generateTwoUnitCombinations(625, 2500)),
    D630x1200("630x1200", generateTwoUnitCombinations(630, 1200)),
    D630x1250("630x1250", generateTwoUnitCombinations(630, 1250)),
    D675x2500("675x2500", generateTwoUnitCombinations(675, 2500)),
    D800x1250("800x1250", generateTwoUnitCombinations(800, 1250)),
    D800x2400("800x2400", generateTwoUnitCombinations(800, 2400)),
    D900x900("900x900", generateTwoUnitCombinations(900, 900)),
    D900x1200("900x1200", generateTwoUnitCombinations(900, 1200)),
    D900x1300("900x1300", generateTwoUnitCombinations(900, 1300)),
    D900x2000("900x2000", generateTwoUnitCombinations(900, 2000)),
    D900x2400("900x2400", generateTwoUnitCombinations(900, 2400)),
    D946x2070("946x2070", generateTwoUnitCombinations(946, 2070)),
    D1000x1000("1000x1000", generateTwoUnitCombinations(1000, 1000)),
    D1000x1200("1000x1200", generateTwoUnitCombinations(1000, 1200)),
    D1000x1500("1000x1500", generateTwoUnitCombinations(1000, 1500)),
    D1200x1275("1200x1275", generateTwoUnitCombinations(1200, 1275)),
    D1200x1800("1200x1800", generateTwoUnitCombinations(1200, 1800)),
    D1200x1875("1200x1875", generateTwoUnitCombinations(1200, 1875)),
    D1200x2000("1200x2000", generateTwoUnitCombinations(1200, 2000)),
    D1200x2200("1200x2200", generateTwoUnitCombinations(1200, 2200)),
    D1200x2400("1200x2400", generateTwoUnitCombinations(1200, 2400)),
    D1200x2500("1200x2500", generateTwoUnitCombinations(1200, 2500)),
    D1200x2550("1200x2550", generateTwoUnitCombinations(1200, 2550)),
    D1200x2600("1200x2600", generateTwoUnitCombinations(1200, 2600)),
    D1200x2700("1200x2700", generateTwoUnitCombinations(1200, 2700)),
    D1200x2800("1200x2800", generateTwoUnitCombinations(1200, 2800)),
    D1200x3000("1200x3000", generateTwoUnitCombinations(1200, 3000)),
    D1200x3300("1200x3300", generateTwoUnitCombinations(1200, 3300)),
    D1200x3600("1200x3600", generateTwoUnitCombinations(1200, 3600)),
    D1220x1220("1220x1220", generateTwoUnitCombinations(1220, 1220)),
    D1220x2440("1220x2440", generateTwoUnitCombinations(1220, 2440)),
    D1250x2500("1250x2500", generateTwoUnitCombinations(1250, 2500)),
    D1250x2600("1250x2600", generateTwoUnitCombinations(1250, 2600)),
    D1250x2700("1250x2700", generateTwoUnitCombinations(1250, 2700)),
    D1250x3000("1250x3000", generateTwoUnitCombinations(1250, 3000)),
    D1250x3050("1250x3050", generateTwoUnitCombinations(1250, 3050)),
    D1250x3350("1250x3350", generateTwoUnitCombinations(1250, 3350)),
    D1500x3000("1500x3000", generateTwoUnitCombinations(1500, 3000)),
    D1525x3050("1525x3050", generateTwoUnitCombinations(1525, 3050)),
    D1700x2745("1700x2745", generateTwoUnitCombinations(1700, 2745)),
    D2150x3850("2150x3850", generateTwoUnitCombinations(2150, 3850)),
    D2070x2650("2070x2650", generateTwoUnitCombinations(2070, 2650)),
    D2070x2800("2070x2800", generateTwoUnitCombinations(2070, 2800)),
    D66x2550("66x2550", generateTwoUnitCombinations(66, 2550)),
    D66x2700("66x2700", generateTwoUnitCombinations(66, 2700)),
    D66x3000("66x3000", generateTwoUnitCombinations(66, 3000)),
    D66x4000("66x4000", generateTwoUnitCombinations(66, 4000)),
    D66x6000("66x6000", generateTwoUnitCombinations(66, 6000)),
    D90x3000("90x3000", generateTwoUnitCombinations(90, 3000)),
    D92x3000("92x3000", generateTwoUnitCombinations(92, 3000)),
    D92x6000("92x6000", generateTwoUnitCombinations(92, 6000)),
    D115x3000("115x3000", generateTwoUnitCombinations(115, 3000)),
    D115x6000("115x6000", generateTwoUnitCombinations(115, 6000)),
    D140x3000("140x3000", generateTwoUnitCombinations(140, 3000)),
    D140x4000("140x4000", generateTwoUnitCombinations(140, 4000)),
    D140x6000("140x6000", generateTwoUnitCombinations(140, 6000)),
    D200x8000("200x8000", generateTwoUnitCombinations(200, 8000)),
    D200x12000("200x12000", generateTwoUnitCombinations(200, 12000)),
    D200x13500("200x13500", generateTwoUnitCombinations(200, 13500)),
    D225x4600("225x4600", generateTwoUnitCombinations(225, 4600)),
    D225x6000("225x6000", generateTwoUnitCombinations(225, 6000)),
    D225x8000("225x8000", generateTwoUnitCombinations(225, 8000)),
    D225x13500("225x13500", generateTwoUnitCombinations(225, 13500)),
    D270x6000("270x6000", generateTwoUnitCombinations(270, 6000)),
    D270x8000("270x8000", generateTwoUnitCombinations(270, 8000)),
    D270x13500("270x13500", generateTwoUnitCombinations(270, 13500)),
    D300x8000("300x8000", generateTwoUnitCombinations(300, 8000)),
    D300x10000("300x10000", generateTwoUnitCombinations(300, 10000)),
    D300x12000("300x12000", generateTwoUnitCombinations(300, 12000)),
    D315x8000("315x8000", generateTwoUnitCombinations(315, 8000)),
    D315x13500("315x13500", generateTwoUnitCombinations(315, 13500)),
    D360x8000("360x8000", generateTwoUnitCombinations(360, 8000)),
    D360x10000("360x10000", generateTwoUnitCombinations(360, 10000)),
    D360x12000("360x12000", generateTwoUnitCombinations(360, 12000)),
    D360x13500("360x13500", generateTwoUnitCombinations(360, 13500)),
    D120x4500("120x4500", generateTwoUnitCombinations(120, 4500)),
    D145x2050("145x2050", generateTwoUnitCombinations(145, 2050)),
    D145x2550("145x2550", generateTwoUnitCombinations(145, 2550)),
    D145x2700("145x2700", generateTwoUnitCombinations(145, 2700)),
    D145x3900("145x3900", generateTwoUnitCombinations(145, 3900)),
    D145x4500("145x4500", generateTwoUnitCombinations(145, 4500)),
    D145x5100("145x5100", generateTwoUnitCombinations(145, 5100)),
    D195x4500("195x4500", generateTwoUnitCombinations(195, 4500)),
    D195x5100("195x5100", generateTwoUnitCombinations(195, 5100)),
    D245x4500("245x4500", generateTwoUnitCombinations(245, 4500)),
    D80x4200("80x4200", generateTwoUnitCombinations(80, 4200)),
    D95x3900("95x3900", generateTwoUnitCombinations(95, 3900)),
    D95x4500("95x4500", generateTwoUnitCombinations(95, 4500)),
    D95x5700("95x5700", generateTwoUnitCombinations(95, 5700)),
    D115x4200("115x4200", generateTwoUnitCombinations(115, 4200)),
    D115x4800("115x4800", generateTwoUnitCombinations(115, 4800)),
    D115x5100("115x5100", generateTwoUnitCombinations(115, 5100)),
    D117x4500("117x4500", generateTwoUnitCombinations(117, 4500)),
    D118x2700("118x2700", generateTwoUnitCombinations(118, 2700)),
    D118x3000("118x3000", generateTwoUnitCombinations(118, 3000)),
    D118x4200("118x4200", generateTwoUnitCombinations(118, 4200)),
    D118x4800("118x4800", generateTwoUnitCombinations(118, 4800)),
    D119x1850("119x1850", generateTwoUnitCombinations(119, 1850)),
    D120x3300("120x3300", generateTwoUnitCombinations(120, 3300)),
    D120x3600("120x3600", generateTwoUnitCombinations(120, 3600)),
    D120x3900("120x3900", generateTwoUnitCombinations(120, 3900)),
    D120x4000("120x4000", generateTwoUnitCombinations(120, 4000)),
    D120x5700("120x5700", generateTwoUnitCombinations(120, 5700)),
    D140x2700("140x2700", generateTwoUnitCombinations(140, 2700)),
    D140x2900("140x2900", generateTwoUnitCombinations(140, 2900)),
    D140x3300("140x3300", generateTwoUnitCombinations(140, 3300)),
    D140x3900("140x3900", generateTwoUnitCombinations(140, 3900)),
    D140x4200("140x4200", generateTwoUnitCombinations(140, 4200)),
    D140x4500("140x4500", generateTwoUnitCombinations(140, 4500)),
    D140x4800("140x4800", generateTwoUnitCombinations(140, 4800)),
    D140x5100("140x5100", generateTwoUnitCombinations(140, 5100)),
    D145x4000("145x4000", generateTwoUnitCombinations(145, 4000)),
    D145x5700("145x5700", generateTwoUnitCombinations(145, 5700)),
    D148x4200("148x4200", generateTwoUnitCombinations(148, 4200)),
    D95x2100("95x2100", generateTwoUnitCombinations(95, 2100)),
    D95x2400("95x2400", generateTwoUnitCombinations(95, 2400)),
    D95x2700("95x2700", generateTwoUnitCombinations(95, 2700)),
    D120x2100("120x2100", generateTwoUnitCombinations(120, 2100)),
    D120x2400("120x2400", generateTwoUnitCombinations(120, 2400)),
    D120x2700("120x2700", generateTwoUnitCombinations(120, 2700)),
    D145x3300("145x3300", generateTwoUnitCombinations(145, 3300)),
    D95x4000("95x4000", generateTwoUnitCombinations(95, 4000)),
    D146x5400("146x5400", generateTwoUnitCombinations(146, 5400)),
    D146x6000("146x6000", generateTwoUnitCombinations(146, 6000)),


    OSB("OSB", List.of(" OSB ")),
    OSB3("OSB-3", List.of("OSB3", "OSB-3")),

    OSB_TAVALINE("tavaline OSB", List.of("punn", "faas"), true),
    OSB_TG4("PUNN; TG4; 4-FAAS", List.of("TG4", "punn", "faas")),

    VINEER_NIISKUSKINDEL("Niiskuskindel", List.of("niiskus")),
    VINEER_VEEKINDEL("Veekindel", List.of("vee")),
    VINEER_FILMI("Filmi", List.of("filmi")),

    VINEER_KASK("Kask", List.of("kask", "kase")),
    VINEER_KUUSK("Kuusk", List.of("kuusk", "kuuse")),
    VINEER_EUKALUPT("Eukalüpt", List.of("eukalüpt")),
    VINEER_MAND("Mänd", List.of("mänd", "männi")),
    VINEER_PAPLI("Papli", List.of("papli", "pappel")),
    VINEER_HAAVA("Haava", List.of("haava", "haab")),

    B_BB("B/BB", List.of("B/BB")),
    B_BB_S_BB("B/BB, S/BB", List.of("B/BB, S/BB")),
    BB_BB("BB/BB", List.of("BB/BB")),
    BB_CP_BB_WG("BB/CP, BB/WG", List.of("BB/CP", "BB/WG")),
    BB_C("BB/C", List.of("BB/C")),
    CP_CP("CPCP", List.of("CPCP")),
    WG_WG("WG/WG", List.of("WG/WG")),
    CP_C("CP/C", List.of("CP/C")),
    C_C("C/C", List.of("C/C")),
    C_PLUS_C("C+/C", List.of("C+/C")),
    F_F("F/F", List.of("F/F")),
    F_W("F/W", List.of("F/W")),
    F_WT("F/WT", List.of("F/WT")),
    G_III("G/III", List.of("G/III")),
    III_III("III/III", List.of("III/III")),

    TSEMENTKIUDPLAAT_TAVALINE("Tavaline tsemenkiudplaat", List.of("saun"), true),
    TSEMENTKIUDPLAAT_SAUNA("Sauna", List.of("saun")),

    PUITKIUDPLAAT_TAVALINE("Tavaline (soome papp)", List.of("pehme","MDF","kõva","HDF","põrand"),true),
    PUITKIUDPLAAT_PEHME("Pehme (MDF)", List.of("pehme", "MDF")),
    PUITKIUDPLAAT_KOVA("Kõva (HDF)", List.of("kõva", "HDF")),
    PUITKIUDPLAAT_PORAND("Põrand", List.of("põrand")),

    PUITLAASTPLAAT_TAVALINE("Tavaline puitlaastplaat", List.of("soon", "punn"), true),
    PUITLAASTPLAAT_KAHE_SOONEGA("2-soonega/punniga", List.of("2-soonega", "2soonega", "2 soont")),
    PUITLAASTPLAAT_NELJA_SOONEGA("4-soonega/punniga", List.of("4-soonega", "4soonega", "4 soont")),

    TYCROC_VEEKINDEL_FXW("Veekindel (FXW)", List.of("FXW")),
    TYCROC_VEEKINDEL_TWP("Veekindel (TWP)", List.of("TWP")),
    TYCROC_VEEKINDEL_FXL("Veekindel (FXL)", List.of("FXL")),
    TYCROC_PORAND("Põrand", List.of("Põrand", "porand")),
    TYCROC_DUSIALUS("Dušialus", List.of("Dušialus", "duš", "dusialus")),

    KIVIVILL_PLAAT("Plaat (kivivill)", List.of("plaat")),
    KIVIVILL_RULL("Rull (kivivill)", List.of("rull")),
    KIVIVILL_KROHVITAV("Krohvitav (kivivill)", List.of("krohvitav")),
    KIVIVILL_SAMMUMÜRA("Sammumüra (kivivill)", List.of("sammu", "müra", "mürasummutus", "sammumüra")),

    KLAASVILL_PLAAT("Plaat (klaasvill)", List.of("plaat")),
    KLAASVILL_RULL("Rull (klaasvill)", List.of("rull")),

    EPS60("Fassaad (EPS60)", List.of("EPS60", "EPS 60", "fassaad", "EPS-60")),
    EPS80_100("Põrand (EPS80,100)", List.of("EPS80", "EPS100", "EPS 80,", "EPS 100,", "Põrand", "EPS-80", "EPS-100")),
    EPS120("Perimeeter (EPS120)", List.of("EPS120", "EPS-120", "EPS 120,", "perimeeter")),
    EPS_L_ELEMENT("L-element (EPS)", List.of("L-plokk")),

    XPS_PLAAT("", List.of("L-plokk")),
    XPS_L_ELEMENT("L-element (XPS)", List.of("L-plokk")),

    PIR_FOOLIUMIGA("Fooliumiga", List.of("sauna-satu", "sauna", "fooliumiga")),
    PIR_KROHVITAV("Rull (PIR)", List.of("rull")),
    PIR_TULEKINDLAM("Krohvitav (PIR)", List.of("krohvitav")),
    PIR_KIPSIGA("Kipsiga", List.of("kipsiga")),

    TUULETOKE_KLAASVILL("Klaasvill", List.of("klaasvill")),
    TUULETOKE_KIVIVILL("Kivivill", List.of("kivivill")),
    TUULETOKE_PUITKIUD("Puitkiud", List.of("puitkiud")),

    PUIT_KUIV("Kuivatatud 18%", List.of("kuivatatud", "õhukuiv", "õhkkuiv", "kuiv")),
    PUIT_MARG("Kuivatamata", List.of("kuivatamata", "märg")),
    PUIT_IMMUTATUD("Immutatud", List.of("immutatud")),

    IMMUTAMATA("Immutamata", List.of("immutatud"),true),
    IMMUTATUD("Immutatatud", List.of("immutatud")),
    TUGEVUSSORT("Tugevussort C24", List.of("tugevussorteeritud", "tugevussort", "c24")),

    TALAD("Talad", List.of("tala", "talad")),
    RISTKIHTPLAAT("Ristkihtplaat", List.of("ristkihtplaat", "ristkiht")),
    LVL("LVL", List.of("lvl")),

    PORANDALAUD_NATURAALSED("Naturaalsne põrandalaud", List.of("viimistletud", "õlitatud", "vahatatud"), true),
    PORANDALAUD_VIIMISTLETUD("Viimistletud põrandalaud", List.of("viimistletud", "õlitatud", "vahatatud")),

    TERRASSILAUD_IMMUTATUD("Immutatatud terrassilaud", List.of("immutatud")),
    TERRASSILAUD_NATURAALSED("Naturaalsed terrassilaud", List.of("viimistletud","õlitatud","termo","komposiit"),true),
    TERRASILAUD_VIIMISTLETUD("Viimistletud terrassilaud", List.of("viimistletud", "õlitatud")),
    TERRASILAUD_TERMO("Termo terrassilaud", List.of("termo")),
    TERRASILAUD_KOMPOSIIT("Komposiit terrassilaud", List.of("komposiit")),

    SISEVOODRILAUD_NATURAALSED("Naturaalsed sisevoodrilaud", List.of("viimistletud","termo","õlitatud"),true),
    SISEVOODRILAUD_VIIMISTLETUD("Viimistletud sisevoodrilaud", List.of("viimistletud", "õlitatud")),
    SISEVOODRILAUD_TERMO("Termo sisevoodrilaud", List.of("termo")),

    VALISVOODRILAUD_NATURAALSED("Naturaalne välisvoodrilaud", List.of("krunditud","krunt","värvitud","termo"), true),
    VALISVOODRILAUD_KRUNDITUD("Krunditud välisvoodrilaud", List.of("krunditud")),
    VALISVOODRILAUD_VARVITUD("Värvitud välisvoodrilaud", List.of("värvitud")),
    VALISVOODRILAUD_TERMO("Termo välisvoodrilaud", List.of("termo")),

    PLASTVOODER_FASSAAD("Fassaad", List.of("fassaad")),
    PLASTVOODER_PLAAT("Plaat", List.of("plaat")),
    PLASTVOODER_TUULEKAST("Tuulekast", List.of("tuulekast"));


    @Getter
    final String displayName;
    final List<String> aliases;
    @Getter
    final boolean checkThatDoesNotContainAnyKeywordsInList;


    Keyword(String displayName, List<String> aliases) {
        this.displayName = displayName;
        this.aliases = aliases;
        this.checkThatDoesNotContainAnyKeywordsInList = false;
    }

    Keyword(String displayName, List<String> aliases, boolean checkThatDoesNotContainAnyKeywordsInList) {
        this.displayName = displayName;
        this.aliases = aliases;
        this.checkThatDoesNotContainAnyKeywordsInList = checkThatDoesNotContainAnyKeywordsInList;
    }

    public static Keyword fromDisplayName(String displayName) {
        for (Keyword filter : Keyword.values()) {
            if (filter.getDisplayName().equalsIgnoreCase(displayName)) {
                System.out.println(displayName);
                return filter;
            }
        }
        throw new IllegalArgumentException("Invalid filter display name: " + displayName);
    }

    private static List<MetricUnit> generateNumberAndUnitDisplay(float valueInMm) {
        final List<String> units = List.of("mm", "cm", "m");
        final List<Boolean> whitespaceInclusion = List.of(false, true);
        List<MetricUnit> generatedDisplays = new ArrayList<>();

        generatedDisplays.add(
                MetricUnit.builder()
                        .numericValue(valueInMm)
                        .unit("")
                        .toInt(true)
                        .withWhitespace(false)
                        .commaValue(",")
                        .build()
        );

        int currUnitIdx = 0;
        float currentValue = valueInMm;
        do {
            for(Boolean useWhitespace : whitespaceInclusion) {
                if(currentValue >= 1) {
                    generatedDisplays.add(
                            MetricUnit.builder()
                                    .numericValue(currentValue)
                                    .unit(units.get(currUnitIdx))
                                    .toInt(true)
                                    .withWhitespace(useWhitespace)
                                    .build()
                    );
                }
                generatedDisplays.add(
                        MetricUnit.builder()
                                .numericValue(currentValue)
                                .unit(units.get(currUnitIdx))
                                .toInt(false)
                                .withWhitespace(useWhitespace)
                                .commaValue(",")
                                .build()
                );
            }

            currentValue /= 100;
            currUnitIdx++;
        } while(currUnitIdx < units.size() && currentValue >= 0.1);

        return generatedDisplays;
    }

    public static List<String> generateTwoUnitCombinations(float valMm1, float valMm2) {
        final List<String> separators = List.of("x", " x ", "*", " * ", ", ", ",");
        List<String> results = new ArrayList<>();
        for (String separator : separators) {
            List<Tuple<MetricUnit, MetricUnit>> lengthWidthCombinations = CombiationGen.create(
                    generateNumberAndUnitDisplay(valMm1),
                    generateNumberAndUnitDisplay(valMm2)
            );

            for(Tuple<MetricUnit, MetricUnit> combination : lengthWidthCombinations) {
                // websites probably wont show numbers with different units & whitespaces, like 3m x 15mm (makes no sense?)

                // very human code, very readable xD
                String unit1 = combination.getFirst().getUnit();
                String unit2 = combination.getSecond().getUnit();
                boolean bothHaveSameSetting = combination.getFirst().isWithWhitespace() == combination.getSecond().isWithWhitespace();
                if(!unit1.equals(unit2) || !bothHaveSameSetting)
                    continue;

                results.add(String.format("%s%s%s", combination.getFirst().getHumanReadableValue(), separator, combination.getSecond().getHumanReadableValue()));
            }
        }

        return results;
    }
}
