package code.expressionlanguage.common;

import code.maths.litteralcom.MathExpUtil;
import code.util.core.NumberUtil;

final class StringDataDirLetterUtil {
    private StringDataDirLetterUtil() {
    }

    static boolean isOtherDigit(char _ch) {
        for (int i: NumberUtil.wrapIntArray(1632,1776,1984,2406,2534,2662,
                2790,2918,3046,3174,3302,3430,3664,3792,3872,4160,4240,6112,6160,
                6470,6608,6784,6800,6992,7088,7232,7248,42528,43216,43264,43472,
                43600,44016,65296)) {
            if (rgBounds3(_ch, i, i + 9)) {
                return true;
            }
        }
        return false;
    }

    static boolean isOtherSpace(char _ch) {
        return _ch == 160 || _ch == 5760 || _ch == 6158 || _ch == 12288 || _ch == 8239 || _ch == 8287 || rgBounds3(_ch, 127, 159) || rgBounds3(_ch, 8192, 8202);
    }

    static boolean isRomanDigits(char _ch) {
        return rgBounds3(_ch, 8544, 8575);
    }

    static boolean isSensibleOtherLetter(char _ch) {
        return rgBounds3(_ch, 9398, 9449);
    }

    static boolean isOtherMathSymbol(char _string) {
        if (_string < 128) {
            return false;
        }
        for (int i: NumberUtil.wrapIntArray(8602,8654)) {
            if (rgBounds3(_string, i, i + 1)) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(1542,8314,8330)) {
            if (rgBounds3(_string, i, i + 2)) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(8512,8592)) {
            if (rgBounds3(_string, i, i + 4)) {
                return true;
            }
        }
        return _string == 172 || isOtherMathSymbol3(_string);
    }

    private static boolean isOtherMathSymbol3(char _string) {
        return _string == 177 || _string == 215 || _string == 247 || _string == 1014 || _string == 8611 || _string == 8260 || _string == 8614 || _string == 8274 || _string == 8622 || _string == 8472 || _string == 8660 || _string == 8658 || _string == 8523 || _string == 9655 || _string == 9665 || isOtherMathSymbol2(_string);
    }

    private static boolean isOtherMathSymbol2(char _string) {
        return _string == 8608 || _string == 9839 || _string == 9084 || rgBounds3(_string, 9115, 9139) || rgBounds3(_string, 9180, 9185) || rgBounds3(_string, 9720, 9727) || rgBounds3(_string, 8968, 8971) || !rgBounds3(_string, 8960, 8991) && (rgBounds3(_string, 8692, 8993) || rgBounds3(_string, 10176, 10180) || rgBounds3(_string, 10183, 10213) || rgBounds3(_string, 10224, 10239) || !rgBounds3(_string, 10627, 10648) && isOtherMathSymbol1(_string));
    }

    private static boolean isOtherMathSymbol1(char _string) {
        return rgBounds3(_string, 10716, 10747) || !rgBounds3(_string, 10712, 10749) && (rgBounds3(_string, 11056, 11076) || !rgBounds3(_string, 11008, 11078) && (rgBounds3(_string, 10496, 11084) || rgBounds3(_string, 65513, 65516) || rgBounds3(_string, 65308, 65310) || rgBounds3(_string, 65124, 65126) || _string == 64297 || _string == 65122 || _string == 65291 || _string == 65372 || _string == 65374 || _string == 65506));
    }

    static int toLowerCaseInt(char _ch) {
        if (_ch == 453 || _ch == 456) {
            return _ch + 1;
        }
        if (StringDataUndefinedUtil.isUnassigned(_ch)) {
            return _ch;
        }
        if (isOtherSpace(_ch)) {
            return _ch;
        }
        if (MathExpUtil.isDigit(_ch)) {
            return _ch;
        }
        if (isOtherDigit(_ch)) {
            return _ch;
        }
        return toLowerCheckDefFive(_ch);
    }

    private static int toLowerCheckDefFive(char _ch) {
        if (isRomanDigits(_ch)) {
            if (_ch >= 8544 + 16) {
                return _ch;
            }
            return _ch+16;
        }
        if (isSensibleOtherLetter(_ch)) {
            if (_ch >= 9398 + 26) {
                return _ch;
            }
            return _ch+26;
        }
        if (isOtherMathSymbol(_ch)) {
            return _ch;
        }
        return toLowerCheckDefFour(_ch);
    }

    private static int toLowerCheckDefFour(char _ch) {
        if (_ch == 391 || _ch == 395 || _ch == 401 || _ch == 408 || _ch == 423 || _ch == 428 || _ch == 431 || _ch == 440 || _ch == 444) {
            return _ch + 1;
        }
        return toLowerCheckDefThree(_ch);
    }

    private static int toLowerCheckDefTwo(char _ch) {
        if (_ch % 2 == 0) {
            return toLowerCheckDefTwo2(_ch);
        }
        return toLowerCheckDefTwo1(_ch);
    }

    private static int toLowerCheckDefTwo2(char _ch) {
        if (rgBounds3(_ch, 256, 302) || rgBounds3(_ch, 306, 310) || rgBounds3(_ch, 330, 374) || rgBounds3(_ch, 386, 388) || rgBounds3(_ch, 416, 420) || rgBounds3(_ch, 478, 494) || rgBounds3(_ch, 498, 500) || rgBounds3(_ch, 504, 542) || rgBounds3(_ch, 546, 562) || rgBounds3(_ch, 582, 590)) {
            return _ch + 1;
        }
        return toLowerCheckDefTwo3(_ch);
    }

    private static int toLowerCheckDefTwo3(char _ch) {
        if (rgBounds3(_ch, 880, 882) || rgBounds3(_ch, 984, 1006) || rgBounds3(_ch, 1120, 1152) || rgBounds3(_ch, 1162, 1214) || rgBounds3(_ch, 1232, 1318) || rgBounds3(_ch, 7680, 7828) || rgBounds3(_ch, 7840, 7934) || rgBounds3(_ch, 11392, 11490) || rgBounds3(_ch, 42560, 42604) || rgBounds3(_ch, 42624, 42646) || rgBounds3(_ch, 42786, 42798) || rgBounds3(_ch, 42802, 42862) || rgBounds3(_ch, 42878, 42886) || rgBounds3(_ch, 42896, 42898) || rgBounds3(_ch, 42912, 42920)) {
            return _ch + 1;
        }
        return toLowerCheckDef(_ch);
    }

    private static int toLowerCheckDefTwo1(char _ch) {
        if (rgBounds3(_ch, 313, 327) || rgBounds3(_ch, 377, 381) || rgBounds3(_ch, 435, 437) || rgBounds3(_ch, 459, 475) || rgBounds3(_ch, 1217, 1229) || rgBounds3(_ch, 11367, 11371) || rgBounds3(_ch, 11499, 11501) || rgBounds3(_ch, 42873, 42875)) {
            return _ch + 1;
        }

        return toLowerCheckDef(_ch);
    }

    static int toLowerCaseIntCheckUpp(char _ch) {
        if (isRomanDigits(_ch)) {
            return _ch+16;
        }
        if (isSensibleOtherLetter(_ch)) {
            return _ch+26;
        }
        if (isOtherMathSymbol(_ch)) {
            return _ch;
        }
        if (_ch == 391 || _ch == 395 || _ch == 401 || _ch == 408 || _ch == 423 || _ch == 428 || _ch == 431 || _ch == 440 || _ch == 444) {
            return _ch + 1;
        }
        return toLowerCaseIntCheckUpp5(_ch);
    }

    private static int toLowerCaseIntCheckUpp5(char _ch) {
        if (_ch == 571 || _ch == 577 || _ch == 886 || _ch == 1015 || _ch == 1018 || _ch == 8579 || _ch == 11360 || _ch == 11378 || _ch == 11381 || _ch == 11506) {
            return _ch + 1;
        }
        return toLowerCaseIntCheckUpp4(_ch);
    }

    private static int toLowerCaseIntCheckUpp4(char _ch) {
        if (_ch == 42891) {
            return _ch + 1;
        }
        if (_ch == 304) {
            return _ch -199;
        }
        if (_ch == 376) {
            return _ch -121;
        }
        return toLowerCaseIntCheckUpp13(_ch);
    }

    private static int toLowerCaseIntCheckUpp13(char _ch) {
        if (_ch == 385) {
            return _ch + 210;
        }
        if (_ch == 390) {
            return _ch + 206;
        }
        if (_ch == 398) {
            return _ch + 79;
        }
        if (_ch == 399) {
            return _ch + 202;
        }
        return toLowerCaseIntCheckUpp12(_ch);
    }

    private static int toLowerCaseIntCheckUpp12(char _ch) {
        if (_ch == 400) {
            return _ch + 203;
        }
        if (_ch == 403) {
            return _ch + 205;
        }
        if (_ch == 404) {
            return _ch + 207;
        }
        if (_ch == 406) {
            return _ch + 211;
        }
        return toLowerCaseIntCheckUpp11(_ch);
    }

    private static int toLowerCaseIntCheckUpp11(char _ch) {
        if (_ch == 407) {
            return _ch + 209;
        }
        if (_ch == 412) {
            return _ch + 211;
        }
        if (_ch == 413) {
            return _ch + 213;
        }
        if (_ch == 415) {
            return _ch + 214;
        }
        return toLowerCaseIntCheckUpp3(_ch);
    }

    private static int toLowerCaseIntCheckUpp3(char _ch) {
        if (_ch == 422) {
            return _ch + 218;
        }
        if (_ch == 425) {
            return _ch + 218;
        }
        if (_ch == 430) {
            return _ch + 218;
        }
        return toLowerCaseIntCheckUpp10(_ch);
    }

    private static int toLowerCaseIntCheckUpp10(char _ch) {
        if (_ch == 439) {
            return _ch + 219;
        }
        if (_ch == 452) {
            return _ch + 2;
        }
        if (_ch == 455) {
            return _ch + 2;
        }
        if (_ch == 458) {
            return _ch + 2;
        }
        return toLowerCaseIntCheckUpp9(_ch);
    }

    private static int toLowerCaseIntCheckUpp9(char _ch) {
        if (_ch == 497) {
            return _ch + 2;
        }
        if (_ch == 502) {
            return _ch -97;
        }
        if (_ch == 503) {
            return _ch -56;
        }
        if (_ch == 544) {
            return _ch -130;
        }
        return toLowerCaseIntCheckUpp8(_ch);
    }

    private static int toLowerCaseIntCheckUpp8(char _ch) {
        if (_ch == 570) {
            return _ch + 10795;
        }
        if (_ch == 573) {
            return _ch -163;
        }
        if (_ch == 574) {
            return _ch + 10792;
        }
        if (_ch == 579) {
            return _ch -195;
        }
        return toLowerCaseIntCheckUpp2(_ch);
    }

    private static int toLowerCaseIntCheckUpp2(char _ch) {
        if (_ch == 580) {
            return _ch + 69;
        }
        if (_ch == 581) {
            return _ch + 71;
        }
        if (_ch == 902) {
            return _ch + 38;
        }
        if (_ch == 908) {
            return _ch + 64;
        }
        return toLowerCaseIntCheckUpp7(_ch);
    }

    private static int toLowerCaseIntCheckUpp7(char _ch) {
        if (_ch == 975) {
            return _ch + 8;
        }
        if (_ch == 1012) {
            return _ch -60;
        }
        if (_ch == 1017) {
            return _ch -7;
        }
        if (_ch == 1216) {
            return _ch + 15;
        }
        return toLowerCaseIntCheckUpp6(_ch);
    }

    private static int toLowerCaseIntCheckUpp6(char _ch) {
        if (_ch == 4295) {
            return _ch + 7264;
        }
        if (_ch == 4301) {
            return _ch + 7264;
        }
        if (_ch == 7838) {
            return _ch -7615;
        }
        if (_ch == 8025 || _ch == 8027 || _ch == 8029 || _ch == 8031) {
            return _ch - 8;
        }
        return toLowerCaseIntCheckUpp1(_ch);
    }

    private static int toLowerCaseIntCheckUpp1(char _ch) {
        if (_ch == 8172) {
            return _ch -7;
        }
        if (_ch == 8486) {
            return _ch -7517;
        }
        if (_ch == 8490) {
            return _ch -8383;
        }
        return toLowerCaseDefaultSeven6(_ch);
    }

    private static int toLowerCaseDefaultSeven6(char _ch) {
        if (_ch == 8491) {
            return _ch -8262;
        }
        if (_ch == 8498) {
            return _ch + 28;
        }
        if (_ch == 11362) {
            return _ch -10743;
        }
        if (_ch == 11363) {
            return _ch -3814;
        }
        return toLowerCaseDefaultSeven5(_ch);
    }

    private static int toLowerCaseDefaultSeven5(char _ch) {
        if (_ch == 11364) {
            return _ch -10727;
        }
        if (_ch == 11373) {
            return _ch -10780;
        }
        if (_ch == 11374) {
            return _ch -10749;
        }
        if (_ch == 11375) {
            return _ch -10783;
        }
        return toLowerCaseDefaultSeven4(_ch);
    }

    private static int toLowerCaseDefaultSeven4(char _ch) {
        if (_ch == 11376) {
            return _ch -10782;
        }
        if (_ch == 42877) {
            return _ch -35332;
        }
        if (_ch == 42893) {
            return _ch -42280;
        }
        if (_ch == 42922) {
            return _ch -42308;
        }
        return toLowerCaseDefaultSeven(_ch);
    }

    private static int toLowerCaseDefaultSeven(char _ch) {
        if (_ch % 2 == 0) {
            return toLowerCaseDefaultSeven2(_ch);
        }
        return toLowerCaseDefaultSeven1(_ch);
    }

    private static int toLowerCaseDefaultSeven2(char _ch) {
        if (rgBounds3(_ch, 256, 302) || rgBounds3(_ch, 306, 310) || rgBounds3(_ch, 330, 374) || rgBounds3(_ch, 386, 388) || rgBounds3(_ch, 416, 420) || rgBounds3(_ch, 478, 494) || rgBounds3(_ch, 498, 500) || rgBounds3(_ch, 504, 542) || rgBounds3(_ch, 546, 562) || rgBounds3(_ch, 582, 590) || rgBounds3(_ch, 880, 882) || rgBounds3(_ch, 984, 1006) || rgBounds3(_ch, 1120, 1152) || rgBounds3(_ch, 1162, 1214) || rgBounds3(_ch, 1232, 1318)) {
            return _ch + 1;
        }
        return toLowerCaseDefaultSeven3(_ch);
    }

    private static int toLowerCaseDefaultSeven3(char _ch) {
        if (rgBounds3(_ch, 7680, 7828) || rgBounds3(_ch, 7840, 7934) || rgBounds3(_ch, 11392, 11490) || rgBounds3(_ch, 42560, 42604) || rgBounds3(_ch, 42624, 42646) || rgBounds3(_ch, 42786, 42798) || rgBounds3(_ch, 42802, 42862) || rgBounds3(_ch, 42878, 42886) || rgBounds3(_ch, 42896, 42898) || rgBounds3(_ch, 42912, 42920)) {
            return _ch + 1;
        }
        return toLowerCaseDefaultSix(_ch);
    }

    private static int toLowerCaseDefaultSeven1(char _ch) {
        if (rgBounds3(_ch, 313, 327) || rgBounds3(_ch, 377, 381) || rgBounds3(_ch, 435, 437) || rgBounds3(_ch, 459, 475) || rgBounds3(_ch, 1217, 1229) || rgBounds3(_ch, 11367, 11371) || rgBounds3(_ch, 11499, 11501) || rgBounds3(_ch, 42873, 42875)) {
            return _ch + 1;
        }

        return toLowerCaseDefaultSix(_ch);
    }

    private static int toLowerCaseDefaultSix(char _ch) {
        if (rgBounds3(_ch, 65, 90) || rgBounds3(_ch, 192, 214) || rgBounds3(_ch, 216, 222)) {
            return _ch + 32;
        }
        if (rgBounds3(_ch, 393, 394)) {
            return _ch + 205;
        }
        if (rgBounds3(_ch, 433, 434)) {
            return _ch + 217;
        }
        return toLowerCaseDefaultSix5(_ch);
    }

    private static int toLowerCaseDefaultSix5(char _ch) {
        if (rgBounds3(_ch, 904, 906)) {
            return _ch + 37;
        }
        if (rgBounds3(_ch, 910, 911)) {
            return _ch + 63;
        }
        if (rgBounds3(_ch, 913, 929)) {
            return _ch + 32;
        }
        if (rgBounds3(_ch, 931, 939)) {
            return _ch + 32;
        }
        return toLowerCaseDefaultSix4(_ch);
    }

    private static int toLowerCaseDefaultSix4(char _ch) {
        if (rgBounds3(_ch, 1021, 1023)) {
            return _ch -130;
        }
        if (rgBounds3(_ch, 1024, 1039)) {
            return _ch + 80;
        }
        if (rgBounds3(_ch, 1040, 1071)) {
            return _ch + 32;
        }
        if (rgBounds3(_ch, 1329, 1366)) {
            return _ch + 48;
        }
        return toLowerCaseDefaultSix2(_ch);
    }

    private static int toLowerCaseDefaultSix2(char _ch) {
        if (rgBounds3(_ch, 4256, 4293)) {
            return _ch + 7264;
        }
        if (rgBounds3(_ch, 7944, 7951) || rgBounds3(_ch, 7960, 7965) || rgBounds3(_ch, 7976, 7983) || rgBounds3(_ch, 7992, 7999) || rgBounds3(_ch, 8008, 8013) || rgBounds3(_ch, 8040, 8047) || rgBounds3(_ch, 8120, 8121)) {
            return _ch - 8;
        }
        return toLowerCaseDefaultSix3(_ch);
    }

    private static int toLowerCaseDefaultSix3(char _ch) {
        if (rgBounds3(_ch, 8122, 8123)) {
            return _ch -74;
        }
        if (rgBounds3(_ch, 8136, 8139)) {
            return _ch -86;
        }
        if (rgBounds3(_ch, 8152, 8153)) {
            return _ch -8;
        }
        if (rgBounds3(_ch, 8154, 8155)) {
            return _ch -100;
        }
        return toLowerCaseDefaultSix1(_ch);
    }

    private static int toLowerCaseDefaultSix1(char _ch) {
        return toLowerCheckDef4(_ch);
    }

    static int toLowerCaseIntCheck(char _ch) {
        return toLowerCheckDefFive(_ch);
    }

    private static int toLowerCheckDefThree(char _ch) {
        if (_ch == 571 || _ch == 577 || _ch == 886 || _ch == 1015 || _ch == 1018 || _ch == 8579 || _ch == 11360 || _ch == 11378 || _ch == 11381 || _ch == 11506 || _ch == 42891) {
            return _ch + 1;
        }
        return toLowerCheckDefThree5(_ch);
    }

    private static int toLowerCheckDefThree5(char _ch) {
        if (_ch == 304) {
            return _ch -199;
        }
        if (_ch == 376) {
            return _ch -121;
        }
        if (_ch == 385) {
            return _ch + 210;
        }
        return toLowerCheckDefThree17(_ch);
    }

    private static int toLowerCheckDefThree17(char _ch) {
        if (_ch == 390) {
            return _ch + 206;
        }
        if (_ch == 398) {
            return _ch + 79;
        }
        if (_ch == 399) {
            return _ch + 202;
        }
        if (_ch == 400) {
            return _ch + 203;
        }
        return toLowerCheckDefThree4(_ch);
    }

    private static int toLowerCheckDefThree4(char _ch) {
        if (_ch == 403) {
            return _ch + 205;
        }
        if (_ch == 404) {
            return _ch + 207;
        }
        if (_ch == 406) {
            return _ch + 211;
        }
        return toLowerCheckDefThree16(_ch);
    }

    private static int toLowerCheckDefThree16(char _ch) {
        if (_ch == 407) {
            return _ch + 209;
        }
        if (_ch == 412) {
            return _ch + 211;
        }
        if (_ch == 413) {
            return _ch + 213;
        }
        if (_ch == 415) {
            return _ch + 214;
        }
        return toLowerCheckDefThree15(_ch);
    }

    private static int toLowerCheckDefThree15(char _ch) {
        if (_ch == 422) {
            return _ch + 218;
        }
        if (_ch == 425) {
            return _ch + 218;
        }
        if (_ch == 430) {
            return _ch + 218;
        }
        if (_ch == 439) {
            return _ch + 219;
        }
        return toLowerCheckDefThree14(_ch);
    }

    private static int toLowerCheckDefThree14(char _ch) {
        if (_ch == 452) {
            return _ch + 2;
        }
        if (_ch == 455) {
            return _ch + 2;
        }
        if (_ch == 458) {
            return _ch + 2;
        }
        if (_ch == 497) {
            return _ch + 2;
        }
        return toLowerCheckDefThree3(_ch);
    }

    private static int toLowerCheckDefThree3(char _ch) {
        if (_ch == 502) {
            return _ch -97;
        }
        if (_ch == 503) {
            return _ch -56;
        }
        return toLowerCheckDefThree13(_ch);
    }

    private static int toLowerCheckDefThree13(char _ch) {
        if (_ch == 544) {
            return _ch -130;
        }
        if (_ch == 570) {
            return _ch + 10795;
        }
        if (_ch == 573) {
            return _ch -163;
        }
        if (_ch == 574) {
            return _ch + 10792;
        }
        return toLowerCheckDefThree12(_ch);
    }

    private static int toLowerCheckDefThree12(char _ch) {
        if (_ch == 579) {
            return _ch -195;
        }
        if (_ch == 580) {
            return _ch + 69;
        }
        if (_ch == 581) {
            return _ch + 71;
        }
        if (_ch == 902) {
            return _ch + 38;
        }
        return toLowerCheckDefThree11(_ch);
    }

    private static int toLowerCheckDefThree11(char _ch) {
        if (_ch == 908) {
            return _ch + 64;
        }
        if (_ch == 975) {
            return _ch + 8;
        }
        if (_ch == 1012) {
            return _ch -60;
        }
        if (_ch == 1017) {
            return _ch -7;
        }
        return toLowerCheckDefThree2(_ch);
    }

    private static int toLowerCheckDefThree2(char _ch) {
        if (_ch == 1216) {
            return _ch + 15;
        }
        if (_ch == 4295) {
            return _ch + 7264;
        }
        if (_ch == 4301) {
            return _ch + 7264;
        }
        if (_ch == 7838) {
            return _ch -7615;
        }
        return toLowerCheckDefThree10(_ch);
    }

    private static int toLowerCheckDefThree10(char _ch) {
        if (_ch == 8025) {
            return _ch -8;
        }
        if (_ch == 8027) {
            return _ch -8;
        }
        if (_ch == 8029) {
            return _ch -8;
        }
        if (_ch == 8031) {
            return _ch -8;
        }
        return toLowerCheckDefThree9(_ch);
    }

    private static int toLowerCheckDefThree9(char _ch) {
        if (_ch == 8124) {
            return _ch -9;
        }
        if (_ch == 8140) {
            return _ch -9;
        }
        if (_ch == 8172) {
            return _ch -7;
        }
        if (_ch == 8188) {
            return _ch -9;
        }
        return toLowerCheckDefThree1(_ch);
    }

    private static int toLowerCheckDefThree1(char _ch) {
        if (_ch == 8486) {
            return _ch -7517;
        }
        if (_ch == 8490) {
            return _ch -8383;
        }
        return toLowerCheckDefThree8(_ch);
    }

    private static int toLowerCheckDefThree8(char _ch) {
        if (_ch == 8491) {
            return _ch -8262;
        }
        if (_ch == 8498) {
            return _ch + 28;
        }
        if (_ch == 11362) {
            return _ch -10743;
        }
        if (_ch == 11363) {
            return _ch -3814;
        }
        return toLowerCheckDefThree7(_ch);
    }

    private static int toLowerCheckDefThree7(char _ch) {
        if (_ch == 11364) {
            return _ch -10727;
        }
        if (_ch == 11373) {
            return _ch -10780;
        }
        if (_ch == 11374) {
            return _ch -10749;
        }
        if (_ch == 11375) {
            return _ch -10783;
        }
        return toLowerCheckDefThree6(_ch);
    }

    private static int toLowerCheckDefThree6(char _ch) {
        if (_ch == 11376) {
            return _ch -10782;
        }
        if (_ch == 42877) {
            return _ch -35332;
        }
        if (_ch == 42893) {
            return _ch -42280;
        }
        if (_ch == 42922) {
            return _ch -42308;
        }
        return toLowerCheckDefTwo(_ch);
    }

    private static int toLowerCheckDef(char _ch) {
        if (rgBounds3(_ch, 65, 90) || rgBounds3(_ch, 192, 214) || rgBounds3(_ch, 216, 222)) {
            return _ch + 32;
        }
        if (rgBounds3(_ch, 393, 394)) {
            return _ch + 205;
        }
        if (rgBounds3(_ch, 433, 434)) {
            return _ch + 217;
        }
        if (rgBounds3(_ch, 904, 906)) {
            return _ch + 37;
        }
        return toLowerCheckDef5(_ch);
    }

    private static int toLowerCheckDef5(char _ch) {
        if (rgBounds3(_ch, 910, 911)) {
            return _ch + 63;
        }
        if (rgBounds3(_ch, 913, 929)) {
            return _ch + 32;
        }
        if (rgBounds3(_ch, 931, 939)) {
            return _ch + 32;
        }
        if (rgBounds3(_ch, 1021, 1023)) {
            return _ch -130;
        }
        return toLowerCheckDef2(_ch);
    }

    private static int toLowerCheckDef2(char _ch) {
        if (rgBounds3(_ch, 1024, 1039)) {
            return _ch + 80;
        }
        if (rgBounds3(_ch, 1040, 1071)) {
            return _ch + 32;
        }
        if (rgBounds3(_ch, 1329, 1366)) {
            return _ch + 48;
        }
        if (rgBounds3(_ch, 4256, 4293)) {
            return _ch + 7264;
        }
        if (rgBounds3(_ch, 7944, 7951) || rgBounds3(_ch, 7960, 7965) || rgBounds3(_ch, 7976, 7983) || rgBounds3(_ch, 7992, 7999) || rgBounds3(_ch, 8008, 8013) || rgBounds3(_ch, 8040, 8047)) {
            return _ch - 8;
        }
        return toLowerCheckDef1(_ch);
    }

    private static int toLowerCheckDef1(char _ch) {
        if (rgBounds3(_ch, 8072, 8079) || rgBounds3(_ch, 8088, 8095) || rgBounds3(_ch, 8104, 8111) || rgBounds3(_ch, 8120, 8121)) {
            return _ch - 8;
        }
        if (rgBounds3(_ch, 8122, 8123)) {
            return _ch -74;
        }
        if (rgBounds3(_ch, 8136, 8139)) {
            return _ch -86;
        }
        if (rgBounds3(_ch, 8152, 8153)) {
            return _ch -8;
        }
        if (rgBounds3(_ch, 8154, 8155)) {
            return _ch -100;
        }
        return toLowerCheckDef4(_ch);
    }

    private static int toLowerCheckDef4(char _ch) {
        if (rgBounds3(_ch, 8168, 8169)) {
            return _ch - 8;
        }
        if (rgBounds3(_ch, 8170, 8171)) {
            return _ch - 112;
        }
        if (rgBounds3(_ch, 8184, 8185)) {
            return _ch - 128;
        }
        return toLowerCheckDef3(_ch);
    }

    private static int toLowerCheckDef3(char _ch) {
        if (rgBounds3(_ch, 8186, 8187)) {
            return _ch - 126;
        }
        if (rgBounds3(_ch, 11264, 11310)) {
            return _ch + 48;
        }
        if (rgBounds3(_ch, 11390, 11391)) {
            return _ch - 10815;
        }
        if (rgBounds3(_ch, 65313, 65338)) {
            return _ch + 32;
        }
        return _ch;
    }

    static int toUpperCaseInt(char _ch) {
        if (StringDataUndefinedUtil.isUnassigned(_ch)) {
            return _ch;
        }
        if (isOtherSpace(_ch)) {
            return _ch;
        }
        if (MathExpUtil.isDigit(_ch)) {
            return _ch;
        }
        if (isOtherDigit(_ch)) {
            return _ch;
        }
        if (isRomanDigits(_ch)) {
            return off(_ch, 8544, 16);
        }
        if (isSensibleOtherLetter(_ch)) {
            return off(_ch, 9398, 26);
        }
        if (isOtherMathSymbol(_ch)) {
            return _ch;
        }
        return toUpperCaseInt2(_ch);
    }

    private static int toUpperCaseInt2(char _ch) {
        if (_ch == 383) {
            return 83;
        }
        if (_ch == 410) {
            return 573;
        }
        if (_ch == 457) {
            return 455;
        }
        return toUpperCaseInt4(_ch);
    }

    private static int toUpperCaseInt4(char _ch) {
        if (_ch == 454) {
            return 452;
        }
        if (_ch == 460) {
            return 458;
        }
        if (_ch == 477) {
            return 398;
        }
        if (_ch == 499) {
            return 497;
        }
        return toUpperCaseInt3(_ch);
    }

    private static int toUpperCaseInt3(char _ch) {
        if (_ch == 592) {
            return 11375;
        }
        if (_ch == 1008) {
            return 922;
        }
        if (_ch == 1231) {
            return 1216;
        }
        if (_ch == 7936) {
            return 7944;
        }
        return toUpperCaseInt1(_ch);
    }

    private static int toUpperCaseInt1(char _ch) {
        if (_ch == 392 || _ch == 396 || _ch == 402 || _ch == 409 || _ch == 424 || _ch == 429 || _ch == 432 || _ch == 441 || _ch == 445 || _ch == 453 || _ch == 456) {
            return _ch - 1;
        }
        return toUpperCaseDefThree(_ch);
    }

    private static int toUpperCaseDefThree(char _ch) {
        if (_ch == 613) {
            return _ch + 42280;
        }
        if (_ch == 614) {
            return _ch + 42308;
        }
        if (_ch == 616) {
            return _ch -209;
        }
        return toUpperCaseDefThree2(_ch);
    }

    private static int toUpperCaseDefThree2(char _ch) {
        if (_ch == 617) {
            return _ch -211;
        }
        if (_ch == 619) {
            return _ch + 10743;
        }
        if (_ch == 623) {
            return _ch -211;
        }
        if (_ch == 643) {
            return _ch -218;
        }
        return toUpperCaseDefThree1(_ch);
    }

    private static int toUpperCaseDefThree1(char _ch) {
        if (_ch == 658) {
            return _ch -219;
        }
        if (_ch == 7545) {
            return _ch + 35332;
        }
        if (_ch == 7549) {
            return _ch + 3814;
        }
        if (_ch == 459 || _ch == 498) {
            return _ch - 1;
        }
        return toUpperCaseDefFour(_ch);
    }

    static int toUpperCaseIntCheck(char _ch) {
        if (isRomanDigits(_ch)) {
            return off(_ch, 8544, 16);
        }
        if (isSensibleOtherLetter(_ch)) {
            return off(_ch, 9398, 26);
        }
        return toUpperCaseIntCheck3(_ch);
    }

    private static int off(char _ch, int _rel, int _add) {
        if (_ch >= _rel + _add) {
            return _ch - _add;
        }
        return _ch;
    }

    private static int toUpperCaseIntCheck3(char _ch) {
        if (isOtherMathSymbol(_ch)) {
            return _ch;
        }
        if (_ch == 383) {
            return 83;
        }
        if (_ch == 410) {
            return 573;
        }
        if (_ch == 457) {
            return 455;
        }
        return toUpperCaseIntCheck2(_ch);
    }

    private static int toUpperCaseIntCheck2(char _ch) {
        if (_ch == 454) {
            return 452;
        }
        if (_ch == 460) {
            return 458;
        }
        if (_ch == 477) {
            return 398;
        }
        if (_ch == 499) {
            return 497;
        }
        return toUpperCaseIntCheck1(_ch);
    }

    private static int toUpperCaseIntCheck1(char _ch) {
        if (_ch == 592) {
            return 11375;
        }
        if (_ch == 1008) {
            return 922;
        }
        if (_ch == 1231) {
            return 1216;
        }
        if (_ch == 7936) {
            return 7944;
        }
        if (_ch == 392 || _ch == 396 || _ch == 402 || _ch == 409 || _ch == 424 || _ch == 429 || _ch == 432 || _ch == 441 || _ch == 445 || _ch == 453 || _ch == 456) {
            return _ch - 1;
        }
        return toUpperCaseDefThree(_ch);
    }

    private static int toUpperCaseDefTwo(char _ch) {
        if (_ch % 2 == 0) {
            return toUpperCaseDefTwo2(_ch);
        }
        return toUpperCaseDefTwo1(_ch);
    }

    private static int toUpperCaseDefTwo2(char _ch) {
        if (rgBounds3(_ch, 314, 328) || rgBounds3(_ch, 378, 382) || rgBounds3(_ch, 436, 438) || rgBounds3(_ch, 462, 476) || rgBounds3(_ch, 1218, 1230) || rgBounds3(_ch, 11368, 11372) || rgBounds3(_ch, 11500, 11502) || rgBounds3(_ch, 42874, 42876)) {
            return _ch - 1;
        }
        return toUpperCaseDef(_ch);
    }

    private static int toUpperCaseDefTwo1(char _ch) {
        if (rgBounds3(_ch, 257, 303) || rgBounds3(_ch, 307, 311) || rgBounds3(_ch, 331, 375) || rgBounds3(_ch, 387, 389) || rgBounds3(_ch, 417, 421) || rgBounds3(_ch, 479, 495) || rgBounds3(_ch, 505, 543) || rgBounds3(_ch, 547, 563) || rgBounds3(_ch, 583, 591) || rgBounds3(_ch, 881, 883) || rgBounds3(_ch, 985, 1007) || rgBounds3(_ch, 1121, 1153) || rgBounds3(_ch, 1163, 1215)) {
            return _ch - 1;
        }
        return toUpperCaseDefTwo3(_ch);
    }

    private static int toUpperCaseDefTwo3(char _ch) {
        if (rgBounds3(_ch, 1233, 1319) || rgBounds3(_ch, 7681, 7829) || rgBounds3(_ch, 7841, 7935) || rgBounds3(_ch, 11393, 11491) || rgBounds3(_ch, 42561, 42605) || rgBounds3(_ch, 42625, 42647) || rgBounds3(_ch, 42787, 42799) || rgBounds3(_ch, 42803, 42863) || rgBounds3(_ch, 42879, 42887) || rgBounds3(_ch, 42897, 42899) || rgBounds3(_ch, 42913, 42921)) {
            return _ch - 1;
        }
        return toUpperCaseDef(_ch);
    }

    static int toUpperCaseIntCheckLow(char _ch) {
        if (isRomanDigits(_ch)) {
            return _ch-16;
        }
        if (isSensibleOtherLetter(_ch)) {
            return _ch-26;
        }
        if (isOtherMathSymbol(_ch)) {
            return _ch;
        }
        if (_ch == 383) {
            return 83;
        }
        if (_ch == 410) {
            return 573;
        }
        return toUpperCaseIntCheckLow2(_ch);
    }

    private static int toUpperCaseIntCheckLow2(char _ch) {
        if (_ch == 457) {
            return 455;
        }
        if (_ch == 454) {
            return 452;
        }
        if (_ch == 460) {
            return 458;
        }
        if (_ch == 477) {
            return 398;
        }
        return toUpperCaseIntCheckLow1(_ch);
    }

    private static int toUpperCaseIntCheckLow1(char _ch) {
        if (_ch == 499) {
            return 497;
        }
        if (_ch == 592) {
            return 11375;
        }
        return toUpperCaseDefFour18(_ch);
    }

    private static int toUpperCaseDefFour18(char _ch) {
        if (_ch == 1008) {
            return 922;
        }
        if (_ch == 1231) {
            return 1216;
        }
        if (_ch == 7936) {
            return 7944;
        }
        if (_ch == 392) {
            return _ch -1;
        }
        return toUpperCaseDefFour17(_ch);
    }

    private static int toUpperCaseDefFour17(char _ch) {
        if (_ch == 396) {
            return _ch -1;
        }
        if (_ch == 402) {
            return _ch -1;
        }
        if (_ch == 409) {
            return _ch -1;
        }
        if (_ch == 424) {
            return _ch -1;
        }
        return toUpperCaseDefFour16(_ch);
    }

    private static int toUpperCaseDefFour16(char _ch) {
        if (_ch == 429) {
            return _ch -1;
        }
        if (_ch == 432) {
            return _ch -1;
        }
        if (_ch == 441) {
            return _ch -1;
        }
        if (_ch == 445) {
            return _ch -1;
        }
        return toUpperCaseDefFour(_ch);
    }

    private static int toUpperCaseDefFour(char _ch) {
        if (_ch == 501) {
            return _ch -1;
        }
        if (_ch == 572) {
            return _ch -1;
        }
        return toUpperCaseDefFour15(_ch);
    }

    private static int toUpperCaseDefFour15(char _ch) {
        if (_ch == 578) {
            return _ch -1;
        }
        if (_ch == 887) {
            return _ch -1;
        }
        if (_ch == 1016) {
            return _ch -1;
        }
        if (_ch == 1019) {
            return _ch -1;
        }
        return toUpperCaseDefFour4(_ch);
    }

    private static int toUpperCaseDefFour4(char _ch) {
        if (_ch == 8580) {
            return _ch -1;
        }
        if (_ch == 11361) {
            return _ch -1;
        }
        return toUpperCaseDefFour14(_ch);
    }

    private static int toUpperCaseDefFour14(char _ch) {
        if (_ch == 11379) {
            return _ch -1;
        }
        if (_ch == 11382) {
            return _ch -1;
        }
        if (_ch == 11507) {
            return _ch -1;
        }
        if (_ch == 42892) {
            return _ch -1;
        }
        return toUpperCaseDefFour12(_ch);
    }

    private static int toUpperCaseDefFour12(char _ch) {
        if (_ch == 181) {
            return _ch + 743;
        }
        if (_ch == 255) {
            return _ch + 121;
        }
        if (_ch == 305) {
            return _ch -232;
        }
        if (_ch == 384) {
            return _ch + 195;
        }
        return toUpperCaseDefFour11(_ch);
    }

    private static int toUpperCaseDefFour11(char _ch) {
        if (_ch == 405) {
            return _ch + 97;
        }
        if (_ch == 414) {
            return _ch + 130;
        }
        if (_ch == 447) {
            return _ch + 56;
        }
        if (_ch == 593) {
            return _ch + 10780;
        }
        return toUpperCaseDefFour3(_ch);
    }

    private static int toUpperCaseDefFour3(char _ch) {
        if (_ch == 594) {
            return _ch + 10782;
        }
        if (_ch == 595) {
            return _ch -210;
        }
        return toUpperCaseDefFour10(_ch);
    }

    private static int toUpperCaseDefFour10(char _ch) {
        if (_ch == 596) {
            return _ch -206;
        }
        if (_ch == 601) {
            return _ch -202;
        }
        if (_ch == 603) {
            return _ch -203;
        }
        if (_ch == 608) {
            return _ch -205;
        }
        return toUpperCaseDefFour9(_ch);
    }

    private static int toUpperCaseDefFour9(char _ch) {
        if (_ch == 611) {
            return _ch -207;
        }
        if (_ch == 625) {
            return _ch + 10749;
        }
        if (_ch == 626) {
            return _ch -213;
        }
        if (_ch == 629) {
            return _ch -214;
        }
        return toUpperCaseDefFour8(_ch);
    }

    private static int toUpperCaseDefFour8(char _ch) {
        if (_ch == 637) {
            return _ch + 10727;
        }
        if (_ch == 640) {
            return _ch -218;
        }
        if (_ch == 648) {
            return _ch -218;
        }
        if (_ch == 649) {
            return _ch -69;
        }
        return toUpperCaseDefFour2(_ch);
    }

    private static int toUpperCaseDefFour2(char _ch) {
        if (_ch == 652) {
            return _ch -71;
        }
        if (_ch == 837) {
            return _ch + 84;
        }
        return toUpperCaseDefFour7(_ch);
    }

    private static int toUpperCaseDefFour7(char _ch) {
        if (_ch == 940) {
            return _ch -38;
        }
        if (_ch == 962) {
            return _ch -31;
        }
        if (_ch == 972) {
            return _ch -64;
        }
        if (_ch == 976) {
            return _ch -62;
        }
        return toUpperCaseDefFour6(_ch);
    }

    private static int toUpperCaseDefFour6(char _ch) {
        if (_ch == 977) {
            return _ch -57;
        }
        if (_ch == 981) {
            return _ch -47;
        }
        if (_ch == 982) {
            return _ch -54;
        }
        if (_ch == 983) {
            return _ch -8;
        }
        return toUpperCaseDefFour5(_ch);
    }

    private static int toUpperCaseDefFour5(char _ch) {
        if (_ch == 1009) {
            return _ch -80;
        }
        if (_ch == 1010) {
            return _ch + 7;
        }
        if (_ch == 1013) {
            return _ch -96;
        }
        if (_ch == 7835) {
            return _ch -59;
        }
        return toUpperCaseDefFour1(_ch);
    }

    private static int toUpperCaseDefFour1(char _ch) {
        if (_ch == 8017 || _ch == 8019 || _ch == 8021 || _ch == 8023) {
            return _ch + 8;
        }
        if (_ch == 8115) {
            return _ch + 9;
        }
        if (_ch == 8126) {
            return _ch -7205;
        }
        return toUpperCaseDef9(_ch);
    }

    private static int toUpperCaseDef9(char _ch) {
        if (_ch == 8131) {
            return _ch + 9;
        }
        if (_ch == 8165) {
            return _ch + 7;
        }
        if (_ch == 8179) {
            return _ch + 9;
        }
        if (_ch == 8526) {
            return _ch -28;
        }
        return toUpperCaseDef8(_ch);
    }

    private static int toUpperCaseDef8(char _ch) {
        if (_ch == 11365) {
            return _ch -10795;
        }
        if (_ch == 11366) {
            return _ch -10792;
        }
        if (_ch == 11559) {
            return _ch -7264;
        }
        if (_ch == 11565) {
            return _ch -7264;
        }
        return toUpperCaseDefTwo(_ch);
    }

    private static int toUpperCaseDef(char _ch) {
        if (rgBounds3(_ch, 97, 122) || rgBounds3(_ch, 224, 246) || rgBounds3(_ch, 248, 254)) {
            return _ch - 32;
        }
        if (rgBounds3(_ch, 575, 576)) {
            return _ch + 10815;
        }
        return toUpperCaseDef7(_ch);
    }

    private static int toUpperCaseDef7(char _ch) {
        if (rgBounds3(_ch, 598, 599)) {
            return _ch -205;
        }
        if (rgBounds3(_ch, 650, 651)) {
            return _ch -217;
        }
        if (rgBounds3(_ch, 891, 893)) {
            return _ch + 130;
        }
        return toUpperCaseDef4(_ch);
    }

    private static int toUpperCaseDef4(char _ch){
        if (rgBounds3(_ch, 941, 943)) {
            return _ch -37;
        }
        if (rgBounds3(_ch, 945, 961)) {
            return _ch -32;
        }
        if (rgBounds3(_ch, 963, 971)) {
            return _ch -32;
        }
        if (rgBounds3(_ch, 973, 974)) {
            return _ch -63;
        }
        return toUpperCaseDef6(_ch);
    }

    private static int toUpperCaseDef6(char _ch) {
        if (rgBounds3(_ch, 1072, 1103)) {
            return _ch -32;
        }
        if (rgBounds3(_ch, 1104, 1119)) {
            return _ch -80;
        }
        if (rgBounds3(_ch, 1377, 1414)) {
            return _ch -48;
        }
        return toUpperCaseDef3(_ch);
    }

    private static int toUpperCaseDef3(char _ch){
        if (rgBounds3(_ch, 7937, 7943) || rgBounds3(_ch, 7952, 7957) || rgBounds3(_ch, 7968, 7975) || rgBounds3(_ch, 7984, 7991) || rgBounds3(_ch, 8000, 8005) || rgBounds3(_ch, 8032, 8039)) {
            return _ch + 8;
        }
        if (rgBounds3(_ch, 8048, 8049)) {
            return _ch + 74;
        }
        return toUpperCaseDef2(_ch);
    }

    private static int toUpperCaseDef2(char _ch){
        if (rgBounds3(_ch, 8050, 8053)) {
            return _ch + 86;
        }
        if (rgBounds3(_ch, 8054, 8055)) {
            return _ch + 100;
        }
        if (rgBounds3(_ch, 8056, 8057)) {
            return _ch + 128;
        }
        return toUpperCaseDef5(_ch);
    }

    private static int toUpperCaseDef5(char _ch) {
        if (rgBounds3(_ch, 8058, 8059)) {
            return _ch + 112;
        }
        if (rgBounds3(_ch, 8060, 8061)) {
            return _ch + 126;
        }
        if (rgBounds3(_ch, 8064, 8071) || rgBounds3(_ch, 8080, 8087)) {
            return _ch + 8;
        }
        return toUpperCaseDef1(_ch);
    }

    private static int toUpperCaseDef1(char _ch){
        if (rgBounds3(_ch, 8096, 8103) || rgBounds3(_ch, 8112, 8113) || rgBounds3(_ch, 8144, 8145) || rgBounds3(_ch, 8160, 8161)) {
            return _ch + 8;
        }
        if (rgBounds3(_ch, 11312, 11358)) {
            return _ch -48;
        }
        if (rgBounds3(_ch, 11520, 11557)) {
            return _ch -7264;
        }
        if (rgBounds3(_ch, 65345, 65370)) {
            return _ch -32;
        }
        return _ch;
    }

    private static boolean rgBounds3(char _ch, int _mini, int _maxi) {
        return _ch >= _mini && _ch <= _maxi;
    }
}
