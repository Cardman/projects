package code.expressionlanguage.common;

import code.util.core.NumberUtil;

final class StringDataSymbolUtil {
    private StringDataSymbolUtil() {
    }

    static int dirOtherPrintSix(char _ch) {
        if (_ch == 172) {
            return 13;
        }
        if (_ch == 177) {
            return 5;
        }
        return dirOtherPrintSix2(_ch);
    }

    private static int dirOtherPrintSix2(char _ch) {
        if (_ch == 1544) {
            return 2;
        }
        return dirOtherPrintSix4(_ch);
    }

    private static int dirOtherPrintSix4(char _ch) {
        if (_ch == 8260) {
            return 7;
        }
        if (_ch == 8274) {
            return 13;
        }
        if (_ch == 8316) {
            return 13;
        }
        if (_ch == 8722) {
            return 4;
        }
        return dirOtherPrintSix1(_ch);
    }

    private static int dirOtherPrintSix1(char _ch) {
        if (_ch == 8723) {
            return 5;
        }
        if (_ch == 65291) {
            return 4;
        }
        return dirOtherPrintSix3(_ch);
    }

    private static int dirOtherPrintSix3(char _ch) {
        if (_ch <= 1543) {
            return 13;
        }
        if (_ch <= 8331) {
            return 4;
        }
        if (_ch <= 11084) {
            return 13;
        }
        if (_ch <= 65122) {
            return 4;
        }
        return 13;
    }

    static int dirOtherPrintFive(char _ch) {
        if (_ch == 173) {
            return 9;
        }
        return dirOtherPrintFive1(_ch);
    }

    private static int dirOtherPrintFive1(char _ch) {
        if (_ch == 176) {
            return 5;
        }
        if (_ch == 185) {
            return 3;
        }
        if (_ch <= 175) {
            return 13;
        }
        if (_ch <= 179) {
            return 3;
        }
        return 13;
    }

    static int dirOtherPrintFour(char _ch) {
        if (rgBounds2(_ch, 42889, 42890)) {
            return 0;
        }
        if (rgBounds2(_ch, 64434, 64449)) {
            return 2;
        }
        return 13;
    }

    static int dirOtherPrintThree(char _ch) {
        if (_ch == 1470) {
            return 1;
        }
        if (_ch <= 65112) {
            return 13;
        }
        if (_ch <= 65293) {
            return 4;
        }
        return 13;
    }

    static int dirOtherPrintTwo(char _ch) {
        if (_ch == 1154 || _ch == 8527 || _ch == 9900 || _ch == 9109) {
            return 0;
        }
        if (_ch == 12880) {
            return 13;
        }
        if (_ch == 8494 || _ch == 43065) {
            return 5;
        }
        if (_ch <= 1769) {
            return 13;
        }
        if (_ch <= 1790) {
            return 2;
        }
        return dirOtherPrintTwo4(_ch);
    }

    private static int dirOtherPrintTwo4(char _ch) {
        if (rgBounds2(_ch, 2554, 2928)) {
            return 0;
        }
        if (rgBounds2(_ch, 3059, 3066)) {
            return 13;
        }
        return dirOtherPrintTwo8(_ch);
    }

    private static int dirOtherPrintTwo8(char _ch) {
        if (rgBounds2(_ch, 3199, 4255)) {
            return 0;
        }
        if (rgBounds2(_ch, 5008, 6655)) {
            return 13;
        }
        if (rgBounds2(_ch, 7009, 7036)) {
            return 0;
        }
        if (rgBounds2(_ch, 8448, 9013)) {
            return 13;
        }
        return dirOtherPrintTwo3(_ch);
    }

    private static int dirOtherPrintTwo3(char _ch) {
        if (rgBounds2(_ch, 9014, 9082)) {
            return 0;
        }
        if (rgBounds2(_ch, 9083, 9290)) {
            return 13;
        }
        if (rgBounds2(_ch, 9372, 9397)) {
            return 0;
        }
        return dirOtherPrintTwo7(_ch);
    }

    private static int dirOtherPrintTwo7(char _ch) {
        if (rgBounds2(_ch, 9472, 10175)) {
            return 13;
        }
        if (rgBounds2(_ch, 10240, 10495)) {
            return 0;
        }
        if (rgBounds2(_ch, 11008, 12351)) {
            return 13;
        }
        if (rgBounds2(_ch, 12688, 12703)) {
            return 0;
        }
        return dirOtherPrintTwo2(_ch);
    }

    private static int dirOtherPrintTwo2(char _ch) {
        if (rgBounds2(_ch, 12736, 12771)) {
            return 13;
        }
        if (rgBounds2(_ch, 12800, 12828)) {
            return 0;
        }
        if (rgBounds2(_ch, 12829, 12830)) {
            return 13;
        }
        return dirOtherPrintTwo6(_ch);
    }

    private static int dirOtherPrintTwo6(char _ch) {
        if (rgBounds2(_ch, 12842, 12923)) {
            return 0;
        }
        if (rgBounds2(_ch, 12924, 12926)) {
            return 13;
        }
        if (rgBounds2(_ch, 12927, 13003)) {
            return 0;
        }
        if (rgBounds2(_ch, 13004, 13007)) {
            return 13;
        }
        return dirOtherPrintTwo1(_ch);
    }

    private static int dirOtherPrintTwo1(char _ch) {
        if (rgBounds2(_ch, 13008, 13174)) {
            return 0;
        }
        if (rgBounds2(_ch, 13175, 13178)) {
            return 13;
        }
        if (rgBounds2(_ch, 13179, 13277)) {
            return 0;
        }
        return dirOtherPrintTwo5(_ch);
    }

    private static int dirOtherPrintTwo5(char _ch) {
        if (rgBounds2(_ch, 13278, 13279)) {
            return 13;
        }
        if (rgBounds2(_ch, 13280, 13310)) {
            return 0;
        }
        if (rgBounds2(_ch, 13311, 43051)) {
            return 13;
        }
        if (rgBounds2(_ch, 43062, 43641)) {
            return 0;
        }
        return 13;
    }

    static int dirOtherPrint(char _ch) {
        if (_ch == 1642) {
            return 5;
        }
        if (_ch == 1645 || _ch == 1748) {
            return 2;
        }
        if (_ch == 11632) {
            return 0;
        }
        return dirOtherPrint4(_ch);
    }

    private static int dirOtherPrint4(char _ch) {
        if (_ch == 1548 || _ch == 65104 || _ch == 65106 || _ch == 65109) {
            return 7;
        }
        if (_ch == 65105 || _ch == 65108) {
            return 13;
        }
        return dirOtherPrint3(_ch);
    }

    private static int dirOtherPrint3(char _ch) {
        if (_ch == 65119 || _ch == 65130) {
            return 5;
        }
        if (_ch <= 903) {
            return 13;
        }
        if (_ch <= 1417) {
            return 0;
        }
        if (_ch <= 1524) {
            return 1;
        }
        return dirOtherPrint5(_ch);
    }

    private static int dirOtherPrint5(char _ch) {
        if (_ch <= 1546) {
            return 5;
        }
        if (_ch <= 1567) {
            return 2;
        }
        if (_ch <= 1644) {
            return 6;
        }
        if (_ch <= 1805) {
            return 2;
        }
        return dirOtherPrint2(_ch);
    }

    private static int dirOtherPrint2(char _ch) {
        if (_ch <= 2041) {
            return 13;
        }
        if (_ch <= 2142) {
            return 1;
        }
        if (_ch <= 6106) {
            return 0;
        }
        if (_ch <= 6469) {
            return 13;
        }
        return dirOtherPrint6(_ch);
    }

    private static int dirOtherPrint6(char _ch) {
        if (_ch <= 7379) {
            return 0;
        }
        if (_ch <= 8231) {
            return 13;
        }
        if (_ch <= 8244) {
            return 5;
        }
        if (_ch <= 12539) {
            return 13;
        }
        return dirOtherPrint1(_ch);
    }

    private static int dirOtherPrint1(char _ch) {
        if (_ch <= 42239) {
            return 0;
        }
        return dirOtherPrint8(_ch);
    }

    private static int dirOtherPrint8(char _ch) {
        if (_ch <= 42622) {
            return 13;
        }
        if (_ch <= 42743) {
            return 0;
        }
        if (_ch <= 43127) {
            return 13;
        }
        if (_ch <= 44011) {
            return 0;
        }
        return dirOtherPrint7(_ch);
    }

    private static int dirOtherPrint7(char _ch) {
        if (_ch <= 65282) {
            return 13;
        }
        if (_ch <= 65285) {
            return 5;
        }
        if (_ch <= 65290) {
            return 13;
        }
        if (_ch <= 65306) {
            return 7;
        }
        return 13;
    }

    static boolean isOtherSymbol(char _ch) {
        return rgBounds2(_ch, 706, 709) || rgBounds2(_ch, 722, 735) || rgBounds2(_ch, 741, 747) || rgBounds2(_ch, 751, 767) || rgBounds2(_ch, 3059, 3064) || rgBounds2(_ch, 3841, 3843) || isOtherSymbol9(_ch);
    }

    private static boolean isOtherSymbol9(char _ch) {
        return rgBounds2(_ch, 3861, 3863) || rgBounds2(_ch, 3866, 3871) || rgBounds2(_ch, 4030, 4037) || rgBounds2(_ch, 4039, 4044) || rgBounds2(_ch, 4053, 4056) || rgBounds2(_ch, 5008, 5017) || rgBounds2(_ch, 6622, 6655) || isOtherSymbol8(_ch);
    }

    private static boolean isOtherSymbol8(char _ch) {
        return rgBounds2(_ch, 7009, 7018) || rgBounds2(_ch, 7028, 7036) || rgBounds2(_ch, 8127, 8129) || rgBounds2(_ch, 8141, 8143) || rgBounds2(_ch, 8157, 8159) || rgBounds2(_ch, 8173, 8175) || rgBounds2(_ch, 8451, 8454) || isOtherSymbol7(_ch);
    }

    private static boolean isOtherSymbol7(char _ch) {
        return rgBounds2(_ch, 8478, 8483) || rgBounds2(_ch, 8598, 8601) || rgBounds2(_ch, 9140, 9179) || rgBounds2(_ch, 9186, 9203) || rgBounds2(_ch, 9216, 9254) || rgBounds2(_ch, 9280, 9290) || rgBounds2(_ch, 8604, 8607) || isOtherSymbol6(_ch);
    }

    private static boolean isOtherSymbol6(char _ch) {
        return rgBounds2(_ch, 8615, 8621) || rgBounds2(_ch, 8623, 8653) || rgBounds2(_ch, 8661, 8691) || rgBounds2(_ch, 8960, 8967) || rgBounds2(_ch, 8972, 8991) || rgBounds2(_ch, 8994, 9000) || rgBounds2(_ch, 9003, 9083) || isOtherSymbol5(_ch);
    }

    private static boolean isOtherSymbol5(char _ch) {
        if (rgBounds2(_ch, 9085, 9114) || rgBounds2(_ch, 9372, 9449)) {
            return true;
        }
        for (int i: NumberUtil.wrapIntArray(9655,9665,9839,9984)) {
            if (_ch == i) {
                return false;
            }
        }
        if (rgBounds2(_ch, 12246, 12271)) {
            return false;
        }
        return rgBounds2(_ch, 9472, 10087) || rgBounds2(_ch, 10132, 10175) || isOtherSymbol4(_ch);
    }

    private static boolean isOtherSymbol4(char _ch) {
        return rgBounds2(_ch, 10240, 10495) || rgBounds2(_ch, 11008, 11055) || rgBounds2(_ch, 11088, 11097) || rgBounds2(_ch, 11493, 11498) || rgBounds2(_ch, 11904, 11929) || rgBounds2(_ch, 11931, 12019) || rgBounds2(_ch, 12032, 12283) || isOtherSymbol3(_ch);
    }

    private static boolean isOtherSymbol3(char _ch) {
        return rgBounds2(_ch, 12694, 12703) || rgBounds2(_ch, 12736, 12771) || rgBounds2(_ch, 12800, 12830) || rgBounds2(_ch, 12842, 12871) || rgBounds2(_ch, 12896, 12927) || rgBounds2(_ch, 12938, 12976) || rgBounds2(_ch, 12992, 13054) || isOtherSymbol2(_ch);
    }

    private static boolean isOtherSymbol2(char _ch) {
        return rgBounds2(_ch, 13056, 13311) || rgBounds2(_ch, 19904, 19967) || rgBounds2(_ch, 42128, 42182) || rgBounds2(_ch, 42752, 42774) || rgBounds2(_ch, 43048, 43051) || rgBounds2(_ch, 43639, 43641) || rgBounds2(_ch, 64434, 64449) || isOtherSymbol1(_ch);
    }

    private static boolean isOtherSymbol1(char _ch) {
        for (int i: NumberUtil.wrapIntArray(900,1550,1789,
                4046,4254,8189,8448,8470,8456,8506,8597,8524,8609,8612,8656,
                11077,12306,12342,12350,12443,12688,42784,42889,43062,
                65507,65517,65532)) {
            if (rgBounds2(_ch, i, i + 1)) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(749,885,1154,1758,1769,
                2038,2554,2928,3066,3199,3449,3859,3892,
                3894,3896,6464,8125,8468,8485,8487,8489,
                8494,8522,8527,8659,12292,12320,12880,43065,65021,65342,
                65344,65512)) {
            if (_ch == i) {
                return true;
            }
        }
        return false;
    }

    private static boolean rgBounds2(char _ch, int _mini, int _maxi) {
        return _ch >= _mini && _ch <= _maxi;
    }
}
