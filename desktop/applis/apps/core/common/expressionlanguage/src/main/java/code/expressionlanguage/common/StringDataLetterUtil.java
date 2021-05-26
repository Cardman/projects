package code.expressionlanguage.common;

import code.util.core.NumberUtil;

public final class StringDataLetterUtil {
    private StringDataLetterUtil() {
    }

    public static boolean isLetter(char _ch) {
        if (_ch < 'A') {
            return false;
        }
        if (_ch <= 'Z') {
            return true;
        }
        if (_ch < 'a') {
            return false;
        }
        if (_ch <= 'z') {
            return true;
        }
        if (_ch < 170) {
            return false;
        }
        if (_ch < 192) {
            return _ch == 170 || _ch == 181 || _ch == 186;
        }
        if (_ch < 706) {
            return isSupplLetter(_ch);
        }
        return isOtherLett(_ch);
    }

    static boolean isOtherLett(char _ch) {
        if (_ch >= 19968 && _ch < 40909) {
            return true;
        }
        if (_ch >= 44032 && _ch < 55204) {
            return true;
        }
        if (_ch >= 13312 && _ch < 19894) {
            return true;
        }
        if (_ch >= 40960 && _ch < 42125) {
            return true;
        }
        if (_ch >= 5121 && _ch < 5741) {
            return true;
        }
        return isOtherLett38(_ch);
    }

    private static boolean isOtherLett38(char _ch){
        if (_ch >= 63744) {
            return isGreatLetter(_ch);
        }
        if (_ch >= 1015 && _ch < 1154) {
            return true;
        }
        if (_ch >= 1162 && _ch < 1320) {
            return true;
        }
        if (_ch >= 1649 && _ch < 1748) {
            return true;
        }
        if (_ch >= 4348 && _ch < 4681) {
            return true;
        }
        if (_ch >= 7680 && _ch < 7958) {
            return true;
        }
        if (_ch >= 7424 && _ch < 7616) {
            return true;
        }
        return isOtherLett37(_ch);
    }

    private static boolean isOtherLett37(char _ch){
        if (_ch >= 11360 && _ch < 11493) {
            return true;
        }
        if (_ch >= 12353 && _ch < 12439) {
            return true;
        }
        if (_ch >= 12449 && _ch < 12539) {
            return true;
        }
        if (_ch >= 12593 && _ch < 12687) {
            return true;
        }
        if (_ch >= 42240 && _ch < 42509) {
            return true;
        }
        if (_ch >= 42786 && _ch < 42889) {
            return true;
        }
        if (_ch >= 42656 && _ch < 42726) {
            return true;
        }
        return isOtherLett36(_ch);
    }

    private static boolean isOtherLett36(char _ch){
        if (_ch >= 43072 && _ch < 43124) {
            return true;
        }
        if (_ch >= 43138 && _ch < 43188) {
            return true;
        }
        if (_ch >= 11568 && _ch < 11624) {
            return true;
        }
        if (_ch >= 931 && _ch < 1014) {
            return true;
        }
        if (_ch >= 1869 && _ch < 1958) {
            return true;
        }
        if (_ch >= 2308 && _ch < 2362) {
            return true;
        }
        if (_ch >= 4824 && _ch < 4881) {
            return true;
        }
        return isOtherLett35(_ch);
    }

    private static boolean isOtherLett35(char _ch){
        if (_ch >= 4888 && _ch < 4955) {
            return true;
        }
        if (_ch >= 5024 && _ch < 5109) {
            return true;
        }
        if (_ch >= 5792 && _ch < 5867) {
            return true;
        }
        if (_ch >= 6176 && _ch < 6264) {
            return true;
        }
        if (_ch >= 6320 && _ch < 6390) {
            return true;
        }
        if (_ch >= 6016 && _ch < 6068) {
            return true;
        }
        if (_ch >= 6688 && _ch < 6741) {
            return true;
        }
        return isOtherLett34(_ch);
    }

    private static boolean isOtherLett34(char _ch){
        if (_ch >= 8064 && _ch < 8117) {
            return true;
        }
        if (_ch >= 55243 && _ch < 55292) {
            return true;
        }
        if (_ch >= 55216 && _ch < 55239) {
            return true;
        }
        if (_ch >= 42192 && _ch < 42238) {
            return true;
        }
        if (_ch >= 42560 && _ch < 42607) {
            return true;
        }
        if (_ch >= 42623 && _ch < 42648) {
            return true;
        }
        if (_ch >= 43020 && _ch < 43043) {
            return true;
        }
        return isOtherLett33(_ch);
    }

    private static boolean isOtherLett33(char _ch){
        if (_ch >= 43274 && _ch < 43302) {
            return true;
        }
        if (_ch >= 43312 && _ch < 43335) {
            return true;
        }
        if (_ch >= 43360 && _ch < 43389) {
            return true;
        }
        if (_ch >= 43396 && _ch < 43443) {
            return true;
        }
        if (_ch >= 43520 && _ch < 43561) {
            return true;
        }
        if (_ch >= 43616 && _ch < 43639) {
            return true;
        }
        if (_ch >= 43648 && _ch < 43696) {
            return true;
        }
        return isOtherLett32(_ch);
    }

    private static boolean isOtherLett32(char _ch){
        if (_ch >= 43968 && _ch < 44003) {
            return true;
        }
        if (_ch >= 710 && _ch < 722) {
            return true;
        }
        if (_ch >= 736 && _ch < 741) {
            return true;
        }
        if (_ch >= 880 && _ch < 885) {
            return true;
        }
        if (_ch >= 886 && _ch < 888) {
            return true;
        }
        if (_ch >= 890 && _ch < 894) {
            return true;
        }
        if (_ch >= 904 && _ch < 907) {
            return true;
        }
        return isOtherLett31(_ch);
    }

    private static boolean isOtherLett31(char _ch){
        if (_ch >= 1520 && _ch < 1523) {
            return true;
        }
        if (_ch >= 1646 && _ch < 1648) {
            return true;
        }
        if (_ch >= 1765 && _ch < 1767) {
            return true;
        }
        if (_ch >= 1774 && _ch < 1776) {
            return true;
        }
        if (_ch >= 1786 && _ch < 1789) {
            return true;
        }
        if (_ch >= 2036 && _ch < 2038) {
            return true;
        }
        if (_ch >= 2210 && _ch < 2221) {
            return true;
        }
        return isOtherLett30(_ch);
    }

    private static boolean isOtherLett30(char _ch){
        if (_ch >= 2392 && _ch < 2402) {
            return true;
        }
        if (_ch >= 2417 && _ch < 2424) {
            return true;
        }
        if (_ch >= 2425 && _ch < 2432) {
            return true;
        }
        if (_ch >= 2437 && _ch < 2445) {
            return true;
        }
        if (_ch >= 2447 && _ch < 2449) {
            return true;
        }
        if (_ch >= 2474 && _ch < 2481) {
            return true;
        }
        if (_ch >= 2486 && _ch < 2490) {
            return true;
        }
        return isOtherLett29(_ch);
    }

    private static boolean isOtherLett29(char _ch){
        if (_ch >= 2524 && _ch < 2526) {
            return true;
        }
        if (_ch >= 2527 && _ch < 2530) {
            return true;
        }
        if (_ch >= 2544 && _ch < 2546) {
            return true;
        }
        if (_ch >= 2565 && _ch < 2571) {
            return true;
        }
        if (_ch >= 2575 && _ch < 2577) {
            return true;
        }
        if (_ch >= 2602 && _ch < 2609) {
            return true;
        }
        if (_ch >= 2610 && _ch < 2612) {
            return true;
        }
        return isOtherLett28(_ch);
    }

    private static boolean isOtherLett28(char _ch){
        if (_ch >= 2613 && _ch < 2615) {
            return true;
        }
        if (_ch >= 2616 && _ch < 2618) {
            return true;
        }
        if (_ch >= 2649 && _ch < 2653) {
            return true;
        }
        if (_ch >= 2674 && _ch < 2677) {
            return true;
        }
        if (_ch >= 2693 && _ch < 2702) {
            return true;
        }
        if (_ch >= 2703 && _ch < 2706) {
            return true;
        }
        if (_ch >= 2730 && _ch < 2737) {
            return true;
        }
        return isOtherLett27(_ch);
    }

    private static boolean isOtherLett27(char _ch){
        if (_ch >= 2738 && _ch < 2740) {
            return true;
        }
        if (_ch >= 2741 && _ch < 2746) {
            return true;
        }
        if (_ch >= 2784 && _ch < 2786) {
            return true;
        }
        if (_ch >= 2821 && _ch < 2829) {
            return true;
        }
        if (_ch >= 2831 && _ch < 2833) {
            return true;
        }
        if (_ch >= 2858 && _ch < 2865) {
            return true;
        }
        if (_ch >= 2866 && _ch < 2868) {
            return true;
        }
        return isOtherLett26(_ch);
    }

    private static boolean isOtherLett26(char _ch){
        if (_ch >= 2869 && _ch < 2874) {
            return true;
        }
        if (_ch >= 2908 && _ch < 2910) {
            return true;
        }
        if (_ch >= 2911 && _ch < 2914) {
            return true;
        }
        if (_ch >= 2949 && _ch < 2955) {
            return true;
        }
        if (_ch >= 2958 && _ch < 2961) {
            return true;
        }
        if (_ch >= 2962 && _ch < 2966) {
            return true;
        }
        if (_ch >= 2969 && _ch < 2971) {
            return true;
        }
        return isOtherLett25(_ch);
    }

    private static boolean isOtherLett25(char _ch){
        if (_ch >= 2974 && _ch < 2976) {
            return true;
        }
        if (_ch >= 2979 && _ch < 2981) {
            return true;
        }
        if (_ch >= 2984 && _ch < 2987) {
            return true;
        }
        if (_ch >= 2990 && _ch < 3002) {
            return true;
        }
        if (_ch >= 3077 && _ch < 3085) {
            return true;
        }
        if (_ch >= 3086 && _ch < 3089) {
            return true;
        }
        if (_ch >= 3114 && _ch < 3124) {
            return true;
        }
        return isOtherLett24(_ch);
    }

    private static boolean isOtherLett24(char _ch){
        if (_ch >= 3125 && _ch < 3130) {
            return true;
        }
        if (_ch >= 3160 && _ch < 3162) {
            return true;
        }
        if (_ch >= 3168 && _ch < 3170) {
            return true;
        }
        if (_ch >= 3205 && _ch < 3213) {
            return true;
        }
        if (_ch >= 3214 && _ch < 3217) {
            return true;
        }
        if (_ch >= 3242 && _ch < 3252) {
            return true;
        }
        if (_ch >= 3253 && _ch < 3258) {
            return true;
        }
        return isOtherLett23(_ch);
    }

    private static boolean isOtherLett23(char _ch){
        if (_ch >= 3296 && _ch < 3298) {
            return true;
        }
        if (_ch >= 3313 && _ch < 3315) {
            return true;
        }
        if (_ch >= 3333 && _ch < 3341) {
            return true;
        }
        if (_ch >= 3342 && _ch < 3345) {
            return true;
        }
        if (_ch >= 3424 && _ch < 3426) {
            return true;
        }
        if (_ch >= 3450 && _ch < 3456) {
            return true;
        }
        if (_ch >= 3461 && _ch < 3479) {
            return true;
        }
        return isOtherLett22(_ch);
    }

    private static boolean isOtherLett22(char _ch){
        if (_ch >= 3507 && _ch < 3516) {
            return true;
        }
        if (_ch >= 3520 && _ch < 3527) {
            return true;
        }
        if (_ch >= 3634 && _ch < 3636) {
            return true;
        }
        if (_ch >= 3648 && _ch < 3655) {
            return true;
        }
        if (_ch >= 3713 && _ch < 3715) {
            return true;
        }
        if (_ch >= 3719 && _ch < 3721) {
            return true;
        }
        if (_ch >= 3732 && _ch < 3736) {
            return true;
        }
        return isOtherLett21(_ch);
    }

    private static boolean isOtherLett21(char _ch){
        if (_ch >= 3737 && _ch < 3744) {
            return true;
        }
        if (_ch >= 3745 && _ch < 3748) {
            return true;
        }
        if (_ch >= 3754 && _ch < 3756) {
            return true;
        }
        if (_ch >= 3757 && _ch < 3761) {
            return true;
        }
        if (_ch >= 3762 && _ch < 3764) {
            return true;
        }
        if (_ch >= 3776 && _ch < 3781) {
            return true;
        }
        if (_ch >= 3804 && _ch < 3808) {
            return true;
        }
        return isOtherLett20(_ch);
    }

    private static boolean isOtherLett20(char _ch){
        if (_ch >= 3904 && _ch < 3912) {
            return true;
        }
        if (_ch >= 3976 && _ch < 3981) {
            return true;
        }
        if (_ch >= 4176 && _ch < 4182) {
            return true;
        }
        if (_ch >= 4186 && _ch < 4190) {
            return true;
        }
        if (_ch >= 4197 && _ch < 4199) {
            return true;
        }
        if (_ch >= 4206 && _ch < 4209) {
            return true;
        }
        if (_ch >= 4213 && _ch < 4226) {
            return true;
        }
        return isOtherLett19(_ch);
    }

    private static boolean isOtherLett19(char _ch){
        if (_ch >= 4682 && _ch < 4686) {
            return true;
        }
        if (_ch >= 4688 && _ch < 4695) {
            return true;
        }
        if (_ch >= 4698 && _ch < 4702) {
            return true;
        }
        if (_ch >= 4746 && _ch < 4750) {
            return true;
        }
        if (_ch >= 4786 && _ch < 4790) {
            return true;
        }
        if (_ch >= 4792 && _ch < 4799) {
            return true;
        }
        if (_ch >= 4802 && _ch < 4806) {
            return true;
        }
        return isOtherLett18(_ch);
    }

    private static boolean isOtherLett18(char _ch){
        if (_ch >= 4808 && _ch < 4823) {
            return true;
        }
        if (_ch >= 4882 && _ch < 4886) {
            return true;
        }
        if (_ch >= 4992 && _ch < 5008) {
            return true;
        }
        if (_ch >= 5743 && _ch < 5760) {
            return true;
        }
        if (_ch >= 5888 && _ch < 5901) {
            return true;
        }
        if (_ch >= 5902 && _ch < 5906) {
            return true;
        }
        if (_ch >= 5920 && _ch < 5938) {
            return true;
        }
        return isOtherLett17(_ch);
    }

    private static boolean isOtherLett17(char _ch){
        if (_ch >= 5952 && _ch < 5970) {
            return true;
        }
        if (_ch >= 5984 && _ch < 5997) {
            return true;
        }
        if (_ch >= 5998 && _ch < 6001) {
            return true;
        }
        if (_ch >= 6512 && _ch < 6517) {
            return true;
        }
        if (_ch >= 6593 && _ch < 6600) {
            return true;
        }
        if (_ch >= 6981 && _ch < 6988) {
            return true;
        }
        if (_ch >= 7086 && _ch < 7088) {
            return true;
        }
        return isOtherLett16(_ch);
    }

    private static boolean isOtherLett16(char _ch){
        if (_ch >= 7245 && _ch < 7248) {
            return true;
        }
        if (_ch >= 7401 && _ch < 7405) {
            return true;
        }
        if (_ch >= 7406 && _ch < 7410) {
            return true;
        }
        if (_ch >= 7413 && _ch < 7415) {
            return true;
        }
        if (_ch >= 7960 && _ch < 7966) {
            return true;
        }
        if (_ch >= 8008 && _ch < 8014) {
            return true;
        }
        if (_ch >= 8016 && _ch < 8024) {
            return true;
        }
        return isOtherLett15(_ch);
    }

    private static boolean isOtherLett15(char _ch){
        if (_ch >= 8118 && _ch < 8125) {
            return true;
        }
        if (_ch >= 8130 && _ch < 8133) {
            return true;
        }
        if (_ch >= 8134 && _ch < 8141) {
            return true;
        }
        if (_ch >= 8144 && _ch < 8148) {
            return true;
        }
        if (_ch >= 8150 && _ch < 8156) {
            return true;
        }
        if (_ch >= 8160 && _ch < 8173) {
            return true;
        }
        if (_ch >= 8178 && _ch < 8181) {
            return true;
        }
        return isOtherLett14(_ch);
    }

    private static boolean isOtherLett14(char _ch){
        if (_ch >= 8182 && _ch < 8189) {
            return true;
        }
        if (_ch >= 8336 && _ch < 8349) {
            return true;
        }
        if (_ch >= 8458 && _ch < 8468) {
            return true;
        }
        if (_ch >= 8473 && _ch < 8478) {
            return true;
        }
        if (_ch >= 8490 && _ch < 8494) {
            return true;
        }
        if (_ch >= 8495 && _ch < 8506) {
            return true;
        }
        if (_ch >= 8508 && _ch < 8512) {
            return true;
        }
        return isOtherLett13(_ch);
    }

    private static boolean isOtherLett13(char _ch){
        if (_ch >= 8517 && _ch < 8522) {
            return true;
        }
        if (_ch >= 8579 && _ch < 8581) {
            return true;
        }
        if (_ch >= 11499 && _ch < 11503) {
            return true;
        }
        if (_ch >= 11506 && _ch < 11508) {
            return true;
        }
        if (_ch >= 11680 && _ch < 11687) {
            return true;
        }
        if (_ch >= 11688 && _ch < 11695) {
            return true;
        }
        if (_ch >= 11696 && _ch < 11703) {
            return true;
        }
        return isOtherLett12(_ch);
    }

    private static boolean isOtherLett12(char _ch){
        if (_ch >= 11704 && _ch < 11711) {
            return true;
        }
        if (_ch >= 11712 && _ch < 11719) {
            return true;
        }
        if (_ch >= 11720 && _ch < 11727) {
            return true;
        }
        if (_ch >= 11728 && _ch < 11735) {
            return true;
        }
        if (_ch >= 11736 && _ch < 11743) {
            return true;
        }
        if (_ch >= 12293 && _ch < 12295) {
            return true;
        }
        if (_ch >= 12337 && _ch < 12342) {
            return true;
        }
        return isOtherLett11(_ch);
    }

    private static boolean isOtherLett11(char _ch){
        if (_ch >= 12347 && _ch < 12349) {
            return true;
        }
        if (_ch >= 12445 && _ch < 12448) {
            return true;
        }
        if (_ch >= 12540 && _ch < 12544) {
            return true;
        }
        if (_ch >= 12784 && _ch < 12800) {
            return true;
        }
        if (_ch >= 910 && _ch < 930) {
            return true;
        }
        if (_ch >= 1329 && _ch < 1367) {
            return true;
        }
        if (_ch >= 1377 && _ch < 1416) {
            return true;
        }
        return isOtherLett10(_ch);
    }

    private static boolean isOtherLett10(char _ch){
        if (_ch >= 1488 && _ch < 1515) {
            return true;
        }
        if (_ch >= 1568 && _ch < 1611) {
            return true;
        }
        if (_ch >= 1810 && _ch < 1840) {
            return true;
        }
        if (_ch >= 1994 && _ch < 2027) {
            return true;
        }
        if (_ch >= 2048 && _ch < 2070) {
            return true;
        }
        if (_ch >= 2112 && _ch < 2137) {
            return true;
        }
        if (_ch >= 2451 && _ch < 2473) {
            return true;
        }
        return isOtherLett9(_ch);
    }

    private static boolean isOtherLett9(char _ch){
        if (_ch >= 2579 && _ch < 2601) {
            return true;
        }
        if (_ch >= 2707 && _ch < 2729) {
            return true;
        }
        if (_ch >= 2835 && _ch < 2857) {
            return true;
        }
        if (_ch >= 3090 && _ch < 3113) {
            return true;
        }
        if (_ch >= 3218 && _ch < 3241) {
            return true;
        }
        if (_ch >= 3346 && _ch < 3387) {
            return true;
        }
        if (_ch >= 3482 && _ch < 3506) {
            return true;
        }
        return isOtherLett8(_ch);
    }

    private static boolean isOtherLett8(char _ch){
        if (_ch >= 3585 && _ch < 3633) {
            return true;
        }
        if (_ch >= 3913 && _ch < 3949) {
            return true;
        }
        if (_ch >= 4096 && _ch < 4139) {
            return true;
        }
        if (_ch >= 4256 && _ch < 4294) {
            return true;
        }
        if (_ch >= 4304 && _ch < 4347) {
            return true;
        }
        if (_ch >= 4704 && _ch < 4745) {
            return true;
        }
        if (_ch >= 4752 && _ch < 4785) {
            return true;
        }
        return isOtherLett7(_ch);
    }

    private static boolean isOtherLett7(char _ch){
        if (_ch >= 5761 && _ch < 5787) {
            return true;
        }
        if (_ch >= 6272 && _ch < 6313) {
            return true;
        }
        if (_ch >= 6400 && _ch < 6429) {
            return true;
        }
        if (_ch >= 6480 && _ch < 6510) {
            return true;
        }
        if (_ch >= 6528 && _ch < 6572) {
            return true;
        }
        if (_ch >= 6656 && _ch < 6679) {
            return true;
        }
        if (_ch >= 6917 && _ch < 6964) {
            return true;
        }
        return isOtherLett6(_ch);
    }

    private static boolean isOtherLett6(char _ch){
        if (_ch >= 7043 && _ch < 7073) {
            return true;
        }
        if (_ch >= 7098 && _ch < 7142) {
            return true;
        }
        if (_ch >= 7168 && _ch < 7204) {
            return true;
        }
        if (_ch >= 7258 && _ch < 7294) {
            return true;
        }
        if (_ch >= 7968 && _ch < 8006) {
            return true;
        }
        if (_ch >= 8031 && _ch < 8062) {
            return true;
        }
        if (_ch >= 11264 && _ch < 11311) {
            return true;
        }
        return isOtherLett5(_ch);
    }

    private static boolean isOtherLett5(char _ch){
        if (_ch >= 11312 && _ch < 11359) {
            return true;
        }
        if (_ch >= 11520 && _ch < 11558) {
            return true;
        }
        if (_ch >= 11648 && _ch < 11671) {
            return true;
        }
        if (_ch >= 12549 && _ch < 12590) {
            return true;
        }
        if (_ch >= 12704 && _ch < 12731) {
            return true;
        }
        if (_ch >= 42512 && _ch < 42528) {
            return true;
        }
        return isOtherLett4(_ch);
    }

    private static boolean isOtherLett4(char _ch){
        if (_ch >= 42538 && _ch < 42540) {
            return true;
        }
        if (_ch >= 42775 && _ch < 42784) {
            return true;
        }
        if (_ch >= 42891 && _ch < 42895) {
            return true;
        }
        if (_ch >= 42896 && _ch < 42900) {
            return true;
        }
        if (_ch >= 42912 && _ch < 42923) {
            return true;
        }
        if (_ch >= 43000 && _ch < 43010) {
            return true;
        }
        if (_ch >= 43011 && _ch < 43014) {
            return true;
        }
        return isOtherLett3(_ch);
    }

    private static boolean isOtherLett3(char _ch){
        if (_ch >= 43015 && _ch < 43019) {
            return true;
        }
        if (_ch >= 43250 && _ch < 43256) {
            return true;
        }
        if (_ch >= 43584 && _ch < 43587) {
            return true;
        }
        if (_ch >= 43588 && _ch < 43596) {
            return true;
        }
        if (_ch >= 43701 && _ch < 43703) {
            return true;
        }
        if (_ch >= 43705 && _ch < 43710) {
            return true;
        }
        if (_ch >= 43739 && _ch < 43742) {
            return true;
        }
        return isOtherLett2(_ch);
    }

    private static boolean isOtherLett2(char _ch){
        if (_ch >= 43744 && _ch < 43755) {
            return true;
        }
        if (_ch >= 43762 && _ch < 43765) {
            return true;
        }
        if (_ch >= 43777 && _ch < 43783) {
            return true;
        }
        if (_ch >= 43785 && _ch < 43791) {
            return true;
        }
        if (_ch >= 43793 && _ch < 43799) {
            return true;
        }
        if (_ch >= 43808 && _ch < 43815) {
            return true;
        }
        if (_ch >= 43816 && _ch < 43823) {
            return true;
        }
        return isOtherLett1(_ch);
    }

    private static boolean isOtherLett1(char _ch){
        for (int v: NumberUtil.wrapIntArray(
                748,
                750,
                902,
                908,
                1369,
                1749,
                1791,
                1808,
                1969,
                2042,
                2074,
                2084,
                2088,
                2208,
                2365,
                2384,
                2482,
                2493,
                2510,
                2654,
                2749,
                2768,
                2877,
                2929,
                2947,
                2972,
                3024,
                3133,
                3261,
                3294,
                3389,
                3406,
                3517,
                3716,
                3722,
                3725,
                3749,
                3751,
                3773,
                3782,
                3840,
                4159,
                4193,
                4238,
                4295,
                4301,
                4696,
                4800,
                6103,
                6108,
                6314,
                6823,
                8025,
                8027,
                8029,
                8126,
                8305,
                8319,
                8450,
                8455,
                8469,
                8484,
                8486,
                8488,
                8526,
                11559,
                11565,
                11631,
                11823,
                43259,
                43471,
                43642,
                43697,
                43712,
                43714
        )) {
            if (v == _ch) {
                return true;
            }
        }
        return false;
    }

    private static boolean isGreatLetter(char _ch) {
        if (_ch < 64110) {
            return true;
        }
        if (_ch >= 64467 && _ch < 64830) {
            return true;
        }
        if (_ch >= 64112 && _ch < 64218) {
            return true;
        }
        if (_ch >= 64326 && _ch < 64434) {
            return true;
        }
        return isGreatLetter3(_ch);
    }

    private static boolean isGreatLetter3(char _ch){
        if (_ch >= 65142 && _ch < 65277) {
            return true;
        }
        if (_ch >= 65382 && _ch < 65471) {
            return true;
        }
        if (_ch >= 64848 && _ch < 64912) {
            return true;
        }
        if (_ch >= 64914 && _ch < 64968) {
            return true;
        }
        if (_ch >= 65313 && _ch < 65339) {
            return true;
        }
        if (_ch >= 65345 && _ch < 65371) {
            return true;
        }
        return isGreatLetter2(_ch);
    }

    private static boolean isGreatLetter2(char _ch){
        if (_ch >= 64287 && _ch < 64297) {
            return true;
        }
        if (_ch >= 64298 && _ch < 64311) {
            return true;
        }
        if (_ch >= 65008 && _ch < 65020) {
            return true;
        }
        if (_ch >= 64256 && _ch < 64263) {
            return true;
        }
        if (_ch >= 64275 && _ch < 64280) {
            return true;
        }
        if (_ch >= 64312 && _ch < 64317) {
            return true;
        }
        if (_ch >= 65136 && _ch < 65141) {
            return true;
        }
        return isGreatLetter1(_ch);
    }

    private static boolean isGreatLetter1(char _ch){
        if (_ch >= 65474 && _ch < 65480) {
            return true;
        }
        if (_ch >= 65482 && _ch < 65488) {
            return true;
        }
        if (_ch >= 65490 && _ch < 65496) {
            return true;
        }
        if (_ch >= 64320 && _ch < 64325) {
            return _ch != 64322;
        }
        if (_ch >= 65498 && _ch < 65501) {
            return true;
        }
        return _ch == 64285 || _ch == 64318;
    }

    static boolean isSupplLetter(char _ch) {
        if (_ch == 215) {
            return false;
        }
        return _ch != 247;
    }
}
