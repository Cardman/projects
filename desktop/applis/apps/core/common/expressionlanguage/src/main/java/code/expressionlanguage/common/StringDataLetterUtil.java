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
        if (inRange(_ch, 19968, 40909)) {
            return true;
        }
        if (inRange(_ch, 44032, 55204)) {
            return true;
        }
        if (inRange(_ch, 13312, 19894)) {
            return true;
        }
        if (inRange(_ch, 40960, 42125)) {
            return true;
        }
        if (inRange(_ch, 5121, 5741)) {
            return true;
        }
        return isOtherLett38(_ch);
    }

    private static boolean isOtherLett38(char _ch){
        if (_ch >= 63744) {
            return isGreatLetter(_ch);
        }
        if (inRange(_ch, 1015, 1154)) {
            return true;
        }
        if (inRange(_ch, 1162, 1320)) {
            return true;
        }
        if (inRange(_ch, 1649, 1748)) {
            return true;
        }
        if (inRange(_ch, 4348, 4681)) {
            return true;
        }
        if (inRange(_ch, 7680, 7958)) {
            return true;
        }
        if (inRange(_ch, 7424, 7616)) {
            return true;
        }
        return isOtherLett37(_ch);
    }

    private static boolean isOtherLett37(char _ch){
        if (inRange(_ch, 11360, 11493)) {
            return true;
        }
        if (inRange(_ch, 12353, 12439)) {
            return true;
        }
        if (inRange(_ch, 12449, 12539)) {
            return true;
        }
        if (inRange(_ch, 12593, 12687)) {
            return true;
        }
        if (inRange(_ch, 42240, 42509)) {
            return true;
        }
        if (inRange(_ch, 42786, 42889)) {
            return true;
        }
        if (inRange(_ch, 42656, 42726)) {
            return true;
        }
        return isOtherLett36(_ch);
    }

    private static boolean isOtherLett36(char _ch){
        if (inRange(_ch, 43072, 43124)) {
            return true;
        }
        if (inRange(_ch, 43138, 43188)) {
            return true;
        }
        if (inRange(_ch, 11568, 11624)) {
            return true;
        }
        if (inRange(_ch, 931, 1014)) {
            return true;
        }
        if (inRange(_ch, 1869, 1958)) {
            return true;
        }
        if (inRange(_ch, 2308, 2362)) {
            return true;
        }
        if (inRange(_ch, 4824, 4881)) {
            return true;
        }
        return isOtherLett35(_ch);
    }

    private static boolean isOtherLett35(char _ch){
        if (inRange(_ch, 4888, 4955)) {
            return true;
        }
        if (inRange(_ch, 5024, 5109)) {
            return true;
        }
        if (inRange(_ch, 5792, 5867)) {
            return true;
        }
        if (inRange(_ch, 6176, 6264)) {
            return true;
        }
        if (inRange(_ch, 6320, 6390)) {
            return true;
        }
        if (inRange(_ch, 6016, 6068)) {
            return true;
        }
        if (inRange(_ch, 6688, 6741)) {
            return true;
        }
        return isOtherLett34(_ch);
    }

    private static boolean isOtherLett34(char _ch){
        if (inRange(_ch, 8064, 8117)) {
            return true;
        }
        if (inRange(_ch, 55243, 55292)) {
            return true;
        }
        if (inRange(_ch, 55216, 55239)) {
            return true;
        }
        if (inRange(_ch, 42192, 42238)) {
            return true;
        }
        if (inRange(_ch, 42560, 42607)) {
            return true;
        }
        if (inRange(_ch, 42623, 42648)) {
            return true;
        }
        if (inRange(_ch, 43020, 43043)) {
            return true;
        }
        return isOtherLett33(_ch);
    }

    private static boolean isOtherLett33(char _ch){
        if (inRange(_ch, 43274, 43302)) {
            return true;
        }
        if (inRange(_ch, 43312, 43335)) {
            return true;
        }
        if (inRange(_ch, 43360, 43389)) {
            return true;
        }
        if (inRange(_ch, 43396, 43443)) {
            return true;
        }
        if (inRange(_ch, 43520, 43561)) {
            return true;
        }
        if (inRange(_ch, 43616, 43639)) {
            return true;
        }
        if (inRange(_ch, 43648, 43696)) {
            return true;
        }
        return isOtherLett32(_ch);
    }

    private static boolean isOtherLett32(char _ch){
        if (inRange(_ch, 43968, 44003)) {
            return true;
        }
        if (inRange(_ch, 710, 722)) {
            return true;
        }
        if (inRange(_ch, 736, 741)) {
            return true;
        }
        if (inRange(_ch, 880, 885)) {
            return true;
        }
        if (inRange(_ch, 886, 888)) {
            return true;
        }
        if (inRange(_ch, 890, 894)) {
            return true;
        }
        if (inRange(_ch, 904, 907)) {
            return true;
        }
        return isOtherLett31(_ch);
    }

    private static boolean isOtherLett31(char _ch){
        if (inRange(_ch, 1520, 1523)) {
            return true;
        }
        if (inRange(_ch, 1646, 1648)) {
            return true;
        }
        if (inRange(_ch, 1765, 1767)) {
            return true;
        }
        if (inRange(_ch, 1774, 1776)) {
            return true;
        }
        if (inRange(_ch, 1786, 1789)) {
            return true;
        }
        if (inRange(_ch, 2036, 2038)) {
            return true;
        }
        if (inRange(_ch, 2210, 2221)) {
            return true;
        }
        return isOtherLett30(_ch);
    }

    private static boolean isOtherLett30(char _ch){
        if (inRange(_ch, 2392, 2402)) {
            return true;
        }
        if (inRange(_ch, 2417, 2424)) {
            return true;
        }
        if (inRange(_ch, 2425, 2432)) {
            return true;
        }
        if (inRange(_ch, 2437, 2445)) {
            return true;
        }
        if (inRange(_ch, 2447, 2449)) {
            return true;
        }
        if (inRange(_ch, 2474, 2481)) {
            return true;
        }
        if (inRange(_ch, 2486, 2490)) {
            return true;
        }
        return isOtherLett29(_ch);
    }

    private static boolean isOtherLett29(char _ch){
        if (inRange(_ch, 2524, 2526)) {
            return true;
        }
        if (inRange(_ch, 2527, 2530)) {
            return true;
        }
        if (inRange(_ch, 2544, 2546)) {
            return true;
        }
        if (inRange(_ch, 2565, 2571)) {
            return true;
        }
        if (inRange(_ch, 2575, 2577)) {
            return true;
        }
        if (inRange(_ch, 2602, 2609)) {
            return true;
        }
        if (inRange(_ch, 2610, 2612)) {
            return true;
        }
        return isOtherLett28(_ch);
    }

    private static boolean isOtherLett28(char _ch){
        if (inRange(_ch, 2613, 2615)) {
            return true;
        }
        if (inRange(_ch, 2616, 2618)) {
            return true;
        }
        if (inRange(_ch, 2649, 2653)) {
            return true;
        }
        if (inRange(_ch, 2674, 2677)) {
            return true;
        }
        if (inRange(_ch, 2693, 2702)) {
            return true;
        }
        if (inRange(_ch, 2703, 2706)) {
            return true;
        }
        if (inRange(_ch, 2730, 2737)) {
            return true;
        }
        return isOtherLett27(_ch);
    }

    private static boolean isOtherLett27(char _ch){
        if (inRange(_ch, 2738, 2740)) {
            return true;
        }
        if (inRange(_ch, 2741, 2746)) {
            return true;
        }
        if (inRange(_ch, 2784, 2786)) {
            return true;
        }
        if (inRange(_ch, 2821, 2829)) {
            return true;
        }
        if (inRange(_ch, 2831, 2833)) {
            return true;
        }
        if (inRange(_ch, 2858, 2865)) {
            return true;
        }
        if (inRange(_ch, 2866, 2868)) {
            return true;
        }
        return isOtherLett26(_ch);
    }

    private static boolean isOtherLett26(char _ch){
        if (inRange(_ch, 2869, 2874)) {
            return true;
        }
        if (inRange(_ch, 2908, 2910)) {
            return true;
        }
        if (inRange(_ch, 2911, 2914)) {
            return true;
        }
        if (inRange(_ch, 2949, 2955)) {
            return true;
        }
        if (inRange(_ch, 2958, 2961)) {
            return true;
        }
        if (inRange(_ch, 2962, 2966)) {
            return true;
        }
        if (inRange(_ch, 2969, 2971)) {
            return true;
        }
        return isOtherLett25(_ch);
    }

    private static boolean isOtherLett25(char _ch){
        if (inRange(_ch, 2974, 2976)) {
            return true;
        }
        if (inRange(_ch, 2979, 2981)) {
            return true;
        }
        if (inRange(_ch, 2984, 2987)) {
            return true;
        }
        if (inRange(_ch, 2990, 3002)) {
            return true;
        }
        if (inRange(_ch, 3077, 3085)) {
            return true;
        }
        if (inRange(_ch, 3086, 3089)) {
            return true;
        }
        if (inRange(_ch, 3114, 3124)) {
            return true;
        }
        return isOtherLett24(_ch);
    }

    private static boolean isOtherLett24(char _ch){
        if (inRange(_ch, 3125, 3130)) {
            return true;
        }
        if (inRange(_ch, 3160, 3162)) {
            return true;
        }
        if (inRange(_ch, 3168, 3170)) {
            return true;
        }
        if (inRange(_ch, 3205, 3213)) {
            return true;
        }
        if (inRange(_ch, 3214, 3217)) {
            return true;
        }
        if (inRange(_ch, 3242, 3252)) {
            return true;
        }
        if (inRange(_ch, 3253, 3258)) {
            return true;
        }
        return isOtherLett23(_ch);
    }

    private static boolean isOtherLett23(char _ch){
        if (inRange(_ch, 3296, 3298)) {
            return true;
        }
        if (inRange(_ch, 3313, 3315)) {
            return true;
        }
        if (inRange(_ch, 3333, 3341)) {
            return true;
        }
        if (inRange(_ch, 3342, 3345)) {
            return true;
        }
        if (inRange(_ch, 3424, 3426)) {
            return true;
        }
        if (inRange(_ch, 3450, 3456)) {
            return true;
        }
        if (inRange(_ch, 3461, 3479)) {
            return true;
        }
        return isOtherLett22(_ch);
    }

    private static boolean isOtherLett22(char _ch){
        if (inRange(_ch, 3507, 3516)) {
            return true;
        }
        if (inRange(_ch, 3520, 3527)) {
            return true;
        }
        if (inRange(_ch, 3634, 3636)) {
            return true;
        }
        if (inRange(_ch, 3648, 3655)) {
            return true;
        }
        if (inRange(_ch, 3713, 3715)) {
            return true;
        }
        if (inRange(_ch, 3719, 3721)) {
            return true;
        }
        if (inRange(_ch, 3732, 3736)) {
            return true;
        }
        return isOtherLett21(_ch);
    }

    private static boolean isOtherLett21(char _ch){
        if (inRange(_ch, 3737, 3744)) {
            return true;
        }
        if (inRange(_ch, 3745, 3748)) {
            return true;
        }
        if (inRange(_ch, 3754, 3756)) {
            return true;
        }
        if (inRange(_ch, 3757, 3761)) {
            return true;
        }
        if (inRange(_ch, 3762, 3764)) {
            return true;
        }
        if (inRange(_ch, 3776, 3781)) {
            return true;
        }
        if (inRange(_ch, 3804, 3808)) {
            return true;
        }
        return isOtherLett20(_ch);
    }

    private static boolean isOtherLett20(char _ch){
        if (inRange(_ch, 3904, 3912)) {
            return true;
        }
        if (inRange(_ch, 3976, 3981)) {
            return true;
        }
        if (inRange(_ch, 4176, 4182)) {
            return true;
        }
        if (inRange(_ch, 4186, 4190)) {
            return true;
        }
        if (inRange(_ch, 4197, 4199)) {
            return true;
        }
        if (inRange(_ch, 4206, 4209)) {
            return true;
        }
        if (inRange(_ch, 4213, 4226)) {
            return true;
        }
        return isOtherLett19(_ch);
    }

    private static boolean isOtherLett19(char _ch){
        if (inRange(_ch, 4682, 4686)) {
            return true;
        }
        if (inRange(_ch, 4688, 4695)) {
            return true;
        }
        if (inRange(_ch, 4698, 4702)) {
            return true;
        }
        if (inRange(_ch, 4746, 4750)) {
            return true;
        }
        if (inRange(_ch, 4786, 4790)) {
            return true;
        }
        if (inRange(_ch, 4792, 4799)) {
            return true;
        }
        if (inRange(_ch, 4802, 4806)) {
            return true;
        }
        return isOtherLett18(_ch);
    }

    private static boolean isOtherLett18(char _ch){
        if (inRange(_ch, 4808, 4823)) {
            return true;
        }
        if (inRange(_ch, 4882, 4886)) {
            return true;
        }
        if (inRange(_ch, 4992, 5008)) {
            return true;
        }
        if (inRange(_ch, 5743, 5760)) {
            return true;
        }
        if (inRange(_ch, 5888, 5901)) {
            return true;
        }
        if (inRange(_ch, 5902, 5906)) {
            return true;
        }
        if (inRange(_ch, 5920, 5938)) {
            return true;
        }
        return isOtherLett17(_ch);
    }

    private static boolean isOtherLett17(char _ch){
        if (inRange(_ch, 5952, 5970)) {
            return true;
        }
        if (inRange(_ch, 5984, 5997)) {
            return true;
        }
        if (inRange(_ch, 5998, 6001)) {
            return true;
        }
        if (inRange(_ch, 6512, 6517)) {
            return true;
        }
        if (inRange(_ch, 6593, 6600)) {
            return true;
        }
        if (inRange(_ch, 6981, 6988)) {
            return true;
        }
        if (inRange(_ch, 7086, 7088)) {
            return true;
        }
        return isOtherLett16(_ch);
    }

    private static boolean isOtherLett16(char _ch){
        if (inRange(_ch, 7245, 7248)) {
            return true;
        }
        if (inRange(_ch, 7401, 7405)) {
            return true;
        }
        if (inRange(_ch, 7406, 7410)) {
            return true;
        }
        if (inRange(_ch, 7413, 7415)) {
            return true;
        }
        if (inRange(_ch, 7960, 7966)) {
            return true;
        }
        if (inRange(_ch, 8008, 8014)) {
            return true;
        }
        if (inRange(_ch, 8016, 8024)) {
            return true;
        }
        return isOtherLett15(_ch);
    }

    private static boolean isOtherLett15(char _ch){
        if (inRange(_ch, 8118, 8125)) {
            return true;
        }
        if (inRange(_ch, 8130, 8133)) {
            return true;
        }
        if (inRange(_ch, 8134, 8141)) {
            return true;
        }
        if (inRange(_ch, 8144, 8148)) {
            return true;
        }
        if (inRange(_ch, 8150, 8156)) {
            return true;
        }
        if (inRange(_ch, 8160, 8173)) {
            return true;
        }
        if (inRange(_ch, 8178, 8181)) {
            return true;
        }
        return isOtherLett14(_ch);
    }

    private static boolean isOtherLett14(char _ch){
        if (inRange(_ch, 8182, 8189)) {
            return true;
        }
        if (inRange(_ch, 8336, 8349)) {
            return true;
        }
        if (inRange(_ch, 8458, 8468)) {
            return true;
        }
        if (inRange(_ch, 8473, 8478)) {
            return true;
        }
        if (inRange(_ch, 8490, 8494)) {
            return true;
        }
        if (inRange(_ch, 8495, 8506)) {
            return true;
        }
        if (inRange(_ch, 8508, 8512)) {
            return true;
        }
        return isOtherLett13(_ch);
    }

    private static boolean isOtherLett13(char _ch){
        if (inRange(_ch, 8517, 8522)) {
            return true;
        }
        if (inRange(_ch, 8579, 8581)) {
            return true;
        }
        if (inRange(_ch, 11499, 11503)) {
            return true;
        }
        if (inRange(_ch, 11506, 11508)) {
            return true;
        }
        if (inRange(_ch, 11680, 11687)) {
            return true;
        }
        if (inRange(_ch, 11688, 11695)) {
            return true;
        }
        if (inRange(_ch, 11696, 11703)) {
            return true;
        }
        return isOtherLett12(_ch);
    }

    private static boolean isOtherLett12(char _ch){
        if (inRange(_ch, 11704, 11711)) {
            return true;
        }
        if (inRange(_ch, 11712, 11719)) {
            return true;
        }
        if (inRange(_ch, 11720, 11727)) {
            return true;
        }
        if (inRange(_ch, 11728, 11735)) {
            return true;
        }
        if (inRange(_ch, 11736, 11743)) {
            return true;
        }
        if (inRange(_ch, 12293, 12295)) {
            return true;
        }
        if (inRange(_ch, 12337, 12342)) {
            return true;
        }
        return isOtherLett11(_ch);
    }

    private static boolean isOtherLett11(char _ch){
        if (inRange(_ch, 12347, 12349)) {
            return true;
        }
        if (inRange(_ch, 12445, 12448)) {
            return true;
        }
        if (inRange(_ch, 12540, 12544)) {
            return true;
        }
        if (inRange(_ch, 12784, 12800)) {
            return true;
        }
        if (inRange(_ch, 910, 930)) {
            return true;
        }
        if (inRange(_ch, 1329, 1367)) {
            return true;
        }
        if (inRange(_ch, 1377, 1416)) {
            return true;
        }
        return isOtherLett10(_ch);
    }

    private static boolean isOtherLett10(char _ch){
        if (inRange(_ch, 1488, 1515)) {
            return true;
        }
        if (inRange(_ch, 1568, 1611)) {
            return true;
        }
        if (inRange(_ch, 1810, 1840)) {
            return true;
        }
        if (inRange(_ch, 1994, 2027)) {
            return true;
        }
        if (inRange(_ch, 2048, 2070)) {
            return true;
        }
        if (inRange(_ch, 2112, 2137)) {
            return true;
        }
        if (inRange(_ch, 2451, 2473)) {
            return true;
        }
        return isOtherLett9(_ch);
    }

    private static boolean isOtherLett9(char _ch){
        if (inRange(_ch, 2579, 2601)) {
            return true;
        }
        if (inRange(_ch, 2707, 2729)) {
            return true;
        }
        if (inRange(_ch, 2835, 2857)) {
            return true;
        }
        if (inRange(_ch, 3090, 3113)) {
            return true;
        }
        if (inRange(_ch, 3218, 3241)) {
            return true;
        }
        if (inRange(_ch, 3346, 3387)) {
            return true;
        }
        if (inRange(_ch, 3482, 3506)) {
            return true;
        }
        return isOtherLett8(_ch);
    }

    private static boolean isOtherLett8(char _ch){
        if (inRange(_ch, 3585, 3633)) {
            return true;
        }
        if (inRange(_ch, 3913, 3949)) {
            return true;
        }
        if (inRange(_ch, 4096, 4139)) {
            return true;
        }
        if (inRange(_ch, 4256, 4294)) {
            return true;
        }
        if (inRange(_ch, 4304, 4347)) {
            return true;
        }
        if (inRange(_ch, 4704, 4745)) {
            return true;
        }
        if (inRange(_ch, 4752, 4785)) {
            return true;
        }
        return isOtherLett7(_ch);
    }

    private static boolean isOtherLett7(char _ch){
        if (inRange(_ch, 5761, 5787)) {
            return true;
        }
        if (inRange(_ch, 6272, 6313)) {
            return true;
        }
        if (inRange(_ch, 6400, 6429)) {
            return true;
        }
        if (inRange(_ch, 6480, 6510)) {
            return true;
        }
        if (inRange(_ch, 6528, 6572)) {
            return true;
        }
        if (inRange(_ch, 6656, 6679)) {
            return true;
        }
        if (inRange(_ch, 6917, 6964)) {
            return true;
        }
        return isOtherLett6(_ch);
    }

    private static boolean isOtherLett6(char _ch){
        if (inRange(_ch, 7043, 7073)) {
            return true;
        }
        if (inRange(_ch, 7098, 7142)) {
            return true;
        }
        if (inRange(_ch, 7168, 7204)) {
            return true;
        }
        if (inRange(_ch, 7258, 7294)) {
            return true;
        }
        if (inRange(_ch, 7968, 8006)) {
            return true;
        }
        if (inRange(_ch, 8031, 8062)) {
            return true;
        }
        if (inRange(_ch, 11264, 11311)) {
            return true;
        }
        return isOtherLett5(_ch);
    }

    private static boolean isOtherLett5(char _ch){
        if (inRange(_ch, 11312, 11359)) {
            return true;
        }
        if (inRange(_ch, 11520, 11558)) {
            return true;
        }
        if (inRange(_ch, 11648, 11671)) {
            return true;
        }
        if (inRange(_ch, 12549, 12590)) {
            return true;
        }
        if (inRange(_ch, 12704, 12731)) {
            return true;
        }
        if (inRange(_ch, 42512, 42528)) {
            return true;
        }
        return isOtherLett4(_ch);
    }

    private static boolean isOtherLett4(char _ch){
        if (inRange(_ch, 42538, 42540)) {
            return true;
        }
        if (inRange(_ch, 42775, 42784)) {
            return true;
        }
        if (inRange(_ch, 42891, 42895)) {
            return true;
        }
        if (inRange(_ch, 42896, 42900)) {
            return true;
        }
        if (inRange(_ch, 42912, 42923)) {
            return true;
        }
        if (inRange(_ch, 43000, 43010)) {
            return true;
        }
        if (inRange(_ch, 43011, 43014)) {
            return true;
        }
        return isOtherLett3(_ch);
    }

    private static boolean isOtherLett3(char _ch){
        if (inRange(_ch, 43015, 43019)) {
            return true;
        }
        if (inRange(_ch, 43250, 43256)) {
            return true;
        }
        if (inRange(_ch, 43584, 43587)) {
            return true;
        }
        if (inRange(_ch, 43588, 43596)) {
            return true;
        }
        if (inRange(_ch, 43701, 43703)) {
            return true;
        }
        if (inRange(_ch, 43705, 43710)) {
            return true;
        }
        if (inRange(_ch, 43739, 43742)) {
            return true;
        }
        return isOtherLett2(_ch);
    }

    private static boolean isOtherLett2(char _ch){
        if (inRange(_ch, 43744, 43755)) {
            return true;
        }
        if (inRange(_ch, 43762, 43765)) {
            return true;
        }
        if (inRange(_ch, 43777, 43783)) {
            return true;
        }
        if (inRange(_ch, 43785, 43791)) {
            return true;
        }
        if (inRange(_ch, 43793, 43799)) {
            return true;
        }
        if (inRange(_ch, 43808, 43815)) {
            return true;
        }
        if (inRange(_ch, 43816, 43823)) {
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
        if (inRange(_ch, 64467, 64830)) {
            return true;
        }
        if (inRange(_ch, 64112, 64218)) {
            return true;
        }
        if (inRange(_ch, 64326, 64434)) {
            return true;
        }
        return isGreatLetter3(_ch);
    }

    private static boolean isGreatLetter3(char _ch){
        if (inRange(_ch, 65142, 65277)) {
            return true;
        }
        if (inRange(_ch, 65382, 65471)) {
            return true;
        }
        if (inRange(_ch, 64848, 64912)) {
            return true;
        }
        if (inRange(_ch, 64914, 64968)) {
            return true;
        }
        if (inRange(_ch, 65313, 65339)) {
            return true;
        }
        if (inRange(_ch, 65345, 65371)) {
            return true;
        }
        return isGreatLetter2(_ch);
    }

    private static boolean isGreatLetter2(char _ch){
        if (inRange(_ch, 64287, 64297)) {
            return true;
        }
        if (inRange(_ch, 64298, 64311)) {
            return true;
        }
        if (inRange(_ch, 65008, 65020)) {
            return true;
        }
        if (inRange(_ch, 64256, 64263)) {
            return true;
        }
        if (inRange(_ch, 64275, 64280)) {
            return true;
        }
        if (inRange(_ch, 64312, 64317)) {
            return true;
        }
        if (inRange(_ch, 65136, 65141)) {
            return true;
        }
        return isGreatLetter1(_ch);
    }

    private static boolean isGreatLetter1(char _ch){
        if (inRange(_ch, 65474, 65480)) {
            return true;
        }
        if (inRange(_ch, 65482, 65488)) {
            return true;
        }
        if (inRange(_ch, 65490, 65496)) {
            return true;
        }
        if (inRange(_ch, 64320, 64325)) {
            return _ch != 64322;
        }
        if (inRange(_ch, 65498, 65501)) {
            return true;
        }
        return _ch == 64285 || _ch == 64318;
    }

    private static boolean inRange(char _ch, int _mini, int _maxi) {
        return _ch >= _mini && _ch < _maxi;
    }

    static boolean isSupplLetter(char _ch) {
        if (_ch == 215) {
            return false;
        }
        return _ch != 247;
    }
}
