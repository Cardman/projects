package code.expressionlanguage.common;

import code.maths.litteralcom.MathExpUtil;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class StringDataUtil {
    public static final int LETTER_SENS_LOWER_CASE = 1;
    public static final int LETTER_SENS_UPPER_CASE = 2;
    public static final int LETTER_INSENS_LOWER_CASE = 3;
    public static final int LETTER_INSENS_UPPER_CASE = 4;
    public static final int LETTER_SENS_NO_CASE = 5;
    public static final int LETTER_SEMI_SENS_NO_CASE = 6;
    public static final int LETTER_INSENS_NO_CASE_DEF_DIR = 7;
    public static final int LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS = 8;
    public static final int LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT = 9;
    public static final int DIGIT_BASE = 10;
    public static final int DIGIT_OTHER = 11;
    public static final int ID_SEP= 12;
    public static final int CURRENCY= 13;
    public static final int SENS_OTHER_LETTER= 14;
    public static final int ROMAN_DIGIT = 15;
    public static final int DEL_LEFT = 16;
    public static final int DEL_RIGHT = 17;
    public static final int OPERATOR_LANGUAGE = 18;
    public static final int OPERATOR_SPEC = 19;
    public static final int PUNCTUATION = 20;
    public static final int QUOTES = 21;
    public static final int ESCAPE = 22;
    public static final int MATH = 23;
    public static final int MODIFIER = 24;
    public static final int SYMBOL_MODIFIER = 25;
    public static final int SYMBOL_OTHER = 26;
    public static final int PUNCTUATION_CONNECTOR = 27;
    public static final int PUNCTUATION_BOUND = 28;
    public static final int PUNCTUATION_QUOTE = 29;
    public static final int PUNCTUATION_OTHER = 30;
    public static final int LETTERS_DIGITS_OTHER = 31;
    public static final int SEPARATOR = 32;
    public static final int SPACE = 33;
    public static final int SPACE_OTHER = 34;
    public static final int OTHER = 35;
    public static final int UNASSIGNED = 36;

    private StringDataUtil() {
    }

    public static String toLowerCase(String _string) {
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder(len_);
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            str_.append(toLowerCase(_string.charAt(i)));
        }
        return str_.toString();
    }

    public static String toUpperCase(String _string) {
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder(len_);
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            str_.append(toUpperCase(_string.charAt(i)));
        }
        return str_.toString();
    }

    public static int digit(char _ch, int _radix) {
        if (_radix < 2) {
            return -1;
        }
        if (_radix > 36) {
            return -1;
        }
        int digit_ = -1;
        if (MathExpUtil.isDigit(_ch)) {
            digit_ = _ch - '0';
        }
        if (inRangeBounds(_ch, 'a', 'z')) {
            digit_ = _ch - 'a' + 10;
        }
        if (inRangeBounds(_ch, 'A', 'Z')) {
            digit_ = _ch - 'A' + 10;
        }
        if (digit_ < _radix) {
            return digit_;
        }
        return -1;
    }

    public static char forDigit(int _digit, int _radix) {
        if (_radix < 2) {
            return 0;
        }
        if (_radix > 36) {
            return 0;
        }
        if (_digit < 0) {
            return 0;
        }
        if (_digit >= _radix) {
            return 0;
        }
        if (_digit < 10) {
            return (char) ('0'+_digit);
        }
        return (char)('a'+_digit-10);
    }

    public static boolean isLetterOrDigit(char _ch) {
        if (_ch < '0') {
            return false;
        }
        if (_ch <= '9') {
            return true;
        }
        return StringDataLetterUtil.isLetter(_ch)||isOtherDigit(_ch);
    }

    public static boolean isLowerCase(char _string) {
        if (isUnassigned(_string) || isOtherSpace(_string) || MathExpUtil.isDigit(_string) || isOtherDigit(_string) || _string == 453 || _string == 456 || _string == 459 || _string == 498) {
            return false;
        }
        for (int i: NumberUtil.wrapIntArray(426,627,609,638,704,42800,8150,8166,
                8182,8462,8508,8134,8146,8118,7836)) {
            if (inRangeBounds(_string, i, i + 1)) {
                return true;
            }
        }
        return isLowerCase7(_string);
    }
    private static boolean isLowerCase7(char _string){
        for (int i: NumberUtil.wrapIntArray(620,8162,43000)) {
            if (inRangeBounds(_string, i, i + 2)) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(604,8518)) {
            if (inRangeBounds(_string, i, i + 3)) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(736,7830,64275)) {
            if (inRangeBounds(_string, i, i + 4)) {
                return true;
            }
        }
        return isLowerCase6(_string);
    }
    private static boolean isLowerCase6(char _string){
        for (int i: NumberUtil.wrapIntArray(42864)) {
            if (inRangeBounds(_string, i, i + 8)) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(564)) {
            if (inRangeBounds(_string, i, i + 5)) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(630,641,11383,64256)) {
            if (inRangeBounds(_string, i, i + 6)) {
                return true;
            }
        }
        return isLowerCase5(_string);
    }
    private static boolean isLowerCase5(char _string){
        for (int i: NumberUtil.wrapIntArray(8336)) {
            if (inRangeBounds(_string, i, i + 12)) {
                return true;
            }
        }
        return _string == 42894 || _string == 11492 || _string == 11377 || _string == 11380 || _string == 170 || _string == 186 || _string == 8305 || isLowerCase4(_string);
    }
    private static boolean isLowerCase4(char _string){
        return _string == 8500 || _string == 8505 || _string == 8495 || _string == 8467 || _string == 8458 || _string == 8319 || _string == 8180 || _string == 8178 || _string == 8132 || _string == 8114 || _string == 8116 || _string == 8130 || isLowerCase3(_string);
    }
    private static boolean isLowerCase3(char _string){
        return _string == 8022 || _string == 8020 || _string == 8018 || _string == 8016 || _string == 7839 || _string == 223 || _string == 312 || isLowerCase2(_string);
    }
    private static boolean isLowerCase2(char _string){
        return _string == 329 || _string == 397 || inRangeBounds(_string, 7424, 7615) || _string == 411 || _string == 442 || _string == 446 || _string == 496 || _string == 545 || _string == 597 || _string == 600 || _string == 602 || inRangeBounds(_string, 612, 624) || isLowerCase1(_string);
    }
    private static boolean isLowerCase1(char _string){
        return _string != 660 && (inRangeBounds(_string, 653, 696) || _string == 890 || _string == 912 || _string == 944 || _string == 1011 || _string == 1020 || _string == 1415 || toLowerCaseIntCheck(_string) == _string && toUpperCaseIntCheckLow(_string) != _string);
    }

    public static boolean isUpperCase(char _string) {
        if (isUnassigned(_string)||isOtherSpace(_string)||MathExpUtil.isDigit(_string)||isOtherDigit(_string)) {
            return false;
        }
        for (int i: NumberUtil.wrapIntArray(8072,8088,8104)) {
            if (inRangeBounds(_string, i, i + 7)) {
                return false;
            }
        }
        for (int i: NumberUtil.wrapIntArray(978,8459,8464)) {
            if (inRangeBounds(_string, i, i + 2)) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(8492,8496,8510)) {
            if (inRangeBounds(_string, i, i + 1)) {
                return true;
            }
        }
        return isUpperCase1(_string);
    }
    private static boolean isUpperCase1(char _string) {
        return _string != 8124 && _string != 8140 && _string != 8188 && (_string == 8450 || _string == 8484 || _string == 8455 || _string == 8488 || _string == 8469 || _string == 8499 || _string == 8517 || inRangeBounds(_string, 8473, 8477) || toUpperCaseIntCheck(_string) == _string && toLowerCaseIntCheckUpp(_string) != _string);
    }

    public static char toLowerCase(char _string) {
        return (char) toLowerCaseInt(_string);
    }

    public static char toUpperCase(char _string) {
        return (char) toUpperCaseInt(_string);
    }

    public static byte getDirectionality(char _ch) {
        int type_ = getCustomType(_ch);
        return (byte)generalDir(_ch, type_);
    }

    public static byte getType(char _ch) {
        int type_ = getCustomType(_ch);
        return (byte)procGene(_ch, type_);
    }

    public static int getCustomType(char _ch) {
        if (StringDataLetterUtil.isLetter(_ch)) {
            return getCustomType6(_ch);
        }
        if (MathExpUtil.isDigit(_ch)) {
            return DIGIT_BASE;
        }
        if (isOtherDigit(_ch)) {
            return DIGIT_OTHER;
        }
        return getCustomType5(_ch);
    }
    private static int getCustomType6(char _ch){
        if (isLowerCase(_ch)) {
            if (toUpperCase(_ch) != _ch) {
                return LETTER_SENS_LOWER_CASE;
            }
            return LETTER_INSENS_LOWER_CASE;
        }
        if (isUpperCase(_ch)) {
            if (toLowerCase(_ch) != _ch) {
                return LETTER_SENS_UPPER_CASE;
            }
            return LETTER_INSENS_UPPER_CASE;
        }
        return getCustomType9(_ch);
    }
    private static int getCustomType9(char _ch){
        if (_ch == 453 || _ch == 456 || _ch == 459 || _ch == 498) {
            return LETTER_SENS_NO_CASE;
        }
        if (inRangeBounds(_ch, 8072, 8079)) {
            return LETTER_SEMI_SENS_NO_CASE;
        }
        if (inRangeBounds(_ch, 8088, 8095)) {
            return LETTER_SEMI_SENS_NO_CASE;
        }
        return getCustomType8(_ch);
    }
    private static int getCustomType8(char _ch){
        if (inRangeBounds(_ch, 8104, 8111)) {
            return LETTER_SEMI_SENS_NO_CASE;
        }
        if (_ch == 8124 || _ch == 8140 || _ch == 8188) {
            return LETTER_SEMI_SENS_NO_CASE;
        }
        if (_ch == 6103) {
            return LETTER_INSENS_NO_CASE_DEF_DIR;
        }
        if (_ch == 43259) {
            return LETTER_INSENS_NO_CASE_DEF_DIR;
        }
        if (inRangeBounds(_ch, 1488, 2220)) {
            return LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT;
        }
        if (inRangeBounds(_ch, 64285, 65276)) {
            return LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT;
        }
        return getCustomType7(_ch);
    }
    private static int getCustomType7(char _ch){
        if (inRangeBounds(_ch, 699, 703)) {
            return LETTER_INSENS_NO_CASE_DEF_DIR;
        }
        if (inRangeBounds(_ch, 697, 719)) {
            return LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS;
        }
        if (_ch == 11823 || _ch == 884|| _ch == 748) {
            return LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS;
        }
        if (inRangeBounds(_ch, 42656, 42725)) {
            return LETTER_INSENS_NO_CASE_DEF_DIR;
        }
        if (inRangeBounds(_ch, 42623, 42888)) {
            return LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS;
        }
        return LETTER_INSENS_NO_CASE_DEF_DIR;
    }
    private static int getCustomType5(char _ch){
        if (_ch == '_' || _ch == 160) {
            return ID_SEP;
        }
        if (isCurrencyChar(_ch)) {
            return CURRENCY;
        }
        if (isSensibleOtherLetter(_ch)) {
            return SENS_OTHER_LETTER;
        }
        if (isRomanDigits(_ch)) {
            return ROMAN_DIGIT;
        }
        if (_ch == '(' || _ch == '[' || _ch == '{') {
            return DEL_LEFT;
        }
        if (_ch == ')' || _ch == ']' || _ch == '}') {
            return DEL_RIGHT;
        }
        if (isPunc1(_ch)) {
            return OPERATOR_LANGUAGE;
        }
        return getCustomType4(_ch);
    }
    private static boolean isPunc1(char _ch){
        return _ch == '!' || _ch == '+' || _ch == '-' ||
                _ch == '*' || _ch == '%' || _ch == '/'||
                _ch == '#' || _ch == '&' || _ch == '|'||
                _ch == '^' || _ch == '?' || _ch == ':'||
                _ch == '<' || _ch == '=' || _ch == '>'||
                _ch == '~' || _ch == '@';
    }
    private static int getCustomType4(char _ch){
        if (_ch == ',' || _ch == '.') {
            return OPERATOR_SPEC;
        }
        if (_ch == ';') {
            return PUNCTUATION;
        }
        if (_ch == '\'' || _ch == '`' || _ch == '"') {
            return QUOTES;
        }
        if (_ch == '\\') {
            return ESCAPE;
        }
        if (isOtherMathSymbol(_ch)) {
            return MATH;
        }
        if (_ch == 166) {
            return MODIFIER;
        }
        return getCustomType3(_ch);
    }
    private static int getCustomType3(char _ch){
        for (int i: NumberUtil.wrapIntArray(168,184)) {
            if (inRangeBounds(_ch, i, i + 1)) {
                return MODIFIER;
            }
        }
        for (int i: NumberUtil.wrapIntArray(173)) {
            if (inRangeBounds(_ch, i, i + 3)) {
                return MODIFIER;
            }
        }
        for (int i: NumberUtil.wrapIntArray(178,188)) {
            if (inRangeBounds(_ch, i, i + 2)) {
                return MODIFIER;
            }
        }
        return getCustomType2(_ch);
    }
    private static int getCustomType2(char _ch){
        if (isOtherSymbol(_ch)) {
            if (isModifierSymbol(_ch)) {
                return SYMBOL_MODIFIER;
            }
            return SYMBOL_OTHER;
        }
        if (isOtherPonctuation(_ch)) {
            if (isConnector(_ch)) {
                return PUNCTUATION_CONNECTOR;
            }
            if (isBoundPunct(_ch)) {
                return PUNCTUATION_BOUND;
            }
            if (isQuotePunct(_ch)) {
                return PUNCTUATION_QUOTE;
            }
            return PUNCTUATION_OTHER;
        }
        return getCustomType1(_ch);
    }
    private static int getCustomType1(char _ch){
        if (isOtherLettersDigits(_ch)) {
            return LETTERS_DIGITS_OTHER;
        }
        if (isOtherModifier(_ch)) {
            return SEPARATOR;
        }
        if (_ch <= ' ') {
            if (_ch == ' ') {
                return SPACE;
            }
            if (_ch == '\n') {
                return SPACE;
            }
            if (_ch == '\t') {
                return SPACE;
            }
            return SPACE_OTHER;
        }
        if (isOtherSpace(_ch)) {
            return SPACE_OTHER;
        }
        if (!isUnassigned(_ch)) {
            return OTHER;
        }
        return UNASSIGNED;
    }

    private static int generalDir(char _ch, int _type) {
        if (_type <= LETTER_INSENS_NO_CASE_DEF_DIR) {
            return 0;
        }
        if (_type == LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS) {
            return 13;
        }
        if (_type == LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT) {
            if (_ch <= 1522) {
                return 1;
            }
            if (inRangeBounds(_ch, 1994, 2136)) {
                return 1;
            }
            if (inRangeBounds(_ch, 64285, 64335)) {
                return 1;
            }
            return 2;
        }
        return generalDir2(_ch,_type);
    }
    private static int generalDir2(char _ch, int _type) {
        if (_type == DIGIT_BASE) {
            return 3;
        }
        if (_type == DIGIT_OTHER) {
            if (_ch <= 1641) {
                return 6;
            }
            if (_ch <= 1785) {
                return 3;
            }
            if (_ch <= 1993) {
                return 1;
            }
            if (_ch >= 65296) {
                return 3;
            }
            return 0;
        }
        return generalDir1(_ch,_type);
    }
    private static int generalDir1(char _ch, int _type) {
        if (_type == ID_SEP) {
            if (_ch == 160) {
                return 7;
            }
            return 13;
        }
        if (_type == CURRENCY) {
            if (_ch == 1547) {
                return 2;
            }
            if (_ch == 65020) {
                return 2;
            }
            return 5;
        }
        if (_type == SENS_OTHER_LETTER) {
            return 0;
        }
        if (_type == ROMAN_DIGIT) {
            return 0;
        }
        if (_type == DEL_LEFT) {
            return 13;
        }
        if (_type == DEL_RIGHT) {
            return 13;
        }
        return generalDirOther(_ch, _type);
    }

    private static int generalDirOther(char _ch, int _type) {
        if (_type == OPERATOR_LANGUAGE) {
            return generalDirOther2(_ch);
        }
        if (_type == OPERATOR_SPEC) {
            return 7;
        }
        if (_type == PUNCTUATION) {
            return 13;
        }
        return generalDirOther1(_ch,_type);
    }

    private static int generalDirOther2(char _ch) {
        if (_ch == 33) {
            return 13;
        }
        if (_ch <= 37) {
            return 5;
        }
        if (_ch <= 42) {
            return 13;
        }
        if (_ch <= 45) {
            return 4;
        }
        if (_ch <= 58) {
            return 7;
        }
        return 13;
    }

    private static int generalDirOther1(char _ch, int _type) {
        if (_type == QUOTES) {
            return 13;
        }
        if (_type == ESCAPE) {
            return 13;
        }
        if (_type == MATH) {
            return dirOtherPrintSix(_ch);
        }
        if (_type == MODIFIER) {
            return dirOtherPrintFive(_ch);
        }
        if (_type == SYMBOL_MODIFIER) {
            return dirOtherPrintFour(_ch);
        }
        if (_type == SYMBOL_OTHER) {
            return dirOtherPrintTwo(_ch);
        }
        if (_type == PUNCTUATION_CONNECTOR) {
            return dirOtherPrintThree(_ch);
        }
        if (_type == PUNCTUATION_BOUND || _type == PUNCTUATION_QUOTE) {
            return 13;
        }
        if (_type == PUNCTUATION_OTHER) {
            return dirOtherPrint(_ch);
        }
        if (_type == LETTERS_DIGITS_OTHER) {
            return dirLettersDigitsOther(_ch);
        }
        if (_type == SEPARATOR) {
            return dirSeparator(_ch);
        }
        if (_type == SPACE) {
            return dirSpace(_ch);
        }
        if (_type == SPACE_OTHER) {
            return dirOtherSpace(_ch);
        }
        if (_type == OTHER) {
            return dirOther(_ch);
        }
        return -1;
    }

    private static int dirSpace(char _ch) {
        if (_ch == 32) {
            return 12;
        }
        if (_ch == 10) {
            return 10;
        }
        return 11;
    }

    private static int dirOtherSpace(char _ch) {
        if (_ch == 11) {
            return 11;
        }
        if (_ch == 12) {
            return 12;
        }
        if (_ch == 13) {
            return 10;
        }
        if (_ch == 31) {
            return 11;
        }
        return dirOtherSpace1(_ch);
    }

    private static int dirOtherSpace1(char _ch) {
        if (_ch == 133) {
            return 10;
        }
        if (_ch == 8239) {
            return 7;
        }
        if (_ch <= 27) {
            return 9;
        }
        return dirOtherSpace2(_ch);
    }

    private static int dirOtherSpace2(char _ch) {
        if (_ch <= 30) {
            return 10;
        }
        if (_ch <= 159) {
            return 9;
        }
        return 12;
    }

    private static int dirOtherPrintSix(char _ch) {
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

    private static int dirOtherPrintFive(char _ch) {
        if (_ch == 173) {
            return 9;
        }
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

    private static int dirOtherPrintFour(char _ch) {
        if (inRangeBounds(_ch, 42889, 42890)) {
            return 0;
        }
        if (inRangeBounds(_ch, 64434, 64449)) {
            return 2;
        }
        return 13;
    }

    private static int dirOtherPrintThree(char _ch) {
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

    private static int dirOtherPrintTwo(char _ch) {
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
        if (inRangeBounds(_ch, 2554, 2928)) {
            return 0;
        }
        if (inRangeBounds(_ch, 3059, 3066)) {
            return 13;
        }
        if (inRangeBounds(_ch, 3199, 4255)) {
            return 0;
        }
        if (inRangeBounds(_ch, 5008, 6655)) {
            return 13;
        }
        if (inRangeBounds(_ch, 7009, 7036)) {
            return 0;
        }
        if (inRangeBounds(_ch, 8448, 9013)) {
            return 13;
        }
        return dirOtherPrintTwo3(_ch);
    }
    private static int dirOtherPrintTwo3(char _ch) {
        if (inRangeBounds(_ch, 9014, 9082)) {
            return 0;
        }
        if (inRangeBounds(_ch, 9083, 9290)) {
            return 13;
        }
        if (inRangeBounds(_ch, 9372, 9397)) {
            return 0;
        }
        if (inRangeBounds(_ch, 9472, 10175)) {
            return 13;
        }
        if (inRangeBounds(_ch, 10240, 10495)) {
            return 0;
        }
        if (inRangeBounds(_ch, 11008, 12351)) {
            return 13;
        }
        if (inRangeBounds(_ch, 12688, 12703)) {
            return 0;
        }
        return dirOtherPrintTwo2(_ch);
    }
    private static int dirOtherPrintTwo2(char _ch) {
        if (inRangeBounds(_ch, 12736, 12771)) {
            return 13;
        }
        if (inRangeBounds(_ch, 12800, 12828)) {
            return 0;
        }
        if (inRangeBounds(_ch, 12829, 12830)) {
            return 13;
        }
        if (inRangeBounds(_ch, 12842, 12923)) {
            return 0;
        }
        if (inRangeBounds(_ch, 12924, 12926)) {
            return 13;
        }
        if (inRangeBounds(_ch, 12927, 13003)) {
            return 0;
        }
        if (inRangeBounds(_ch, 13004, 13007)) {
            return 13;
        }
        return dirOtherPrintTwo1(_ch);
    }
    private static int dirOtherPrintTwo1(char _ch) {
        if (inRangeBounds(_ch, 13008, 13174)) {
            return 0;
        }
        if (inRangeBounds(_ch, 13175, 13178)) {
            return 13;
        }
        if (inRangeBounds(_ch, 13179, 13277)) {
            return 0;
        }
        if (inRangeBounds(_ch, 13278, 13279)) {
            return 13;
        }
        if (inRangeBounds(_ch, 13280, 13310)) {
            return 0;
        }
        if (inRangeBounds(_ch, 13311, 43051)) {
            return 13;
        }
        if (inRangeBounds(_ch, 43062, 43641)) {
            return 0;
        }
        return 13;
    }

    private static int dirOtherPrint(char _ch) {
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

    private static int dirSeparator(char _ch) {
        if (_ch == 8206){
            return 0;
        }
        if (_ch == 1757){
            return 6;
        }
        if (_ch == 1807){
            return 2;
        }
        if (_ch == 8207){
            return 1;
        }
        if (_ch == 8234){
            return 14;
        }
        if (_ch == 8235){
            return 16;
        }
        return dirSeparator1(_ch);
    }
    private static int dirSeparator1(char _ch) {
        if (_ch == 8236){
            return 18;
        }
        if (_ch == 8237){
            return 15;
        }
        if (_ch == 8238){
            return 17;
        }
        return dirSeparator2(_ch);
    }

    private static int dirSeparator2(char _ch) {
        if (_ch == 65279){
            return 9;
        }
        if (_ch <= 1540) {
            return 6;
        }
        if (_ch <= 8303) {
            return 9;
        }
        return 13;
    }

    private static int dirLettersDigitsOther(char _ch) {
        if (_ch == 11517) {
            return 13;
        }

        if (_ch <= 3075) {
            return 0;
        }
        if (_ch <= 3198) {
            return 13;
        }
        if (_ch <= 5872) {
            return 0;
        }
        if (_ch <= 6137) {
            return 13;
        }
        if (_ch <= 6618) {
            return 0;
        }
        if (_ch <= 8329) {
            return 3;
        }
        return dirLettersDigitsOther1(_ch);
    }
    private static int dirLettersDigitsOther1(char _ch) {
        if (_ch <= 8543) {
            return 13;
        }
        if (_ch <= 8584) {
            return 0;
        }
        if (_ch <= 9351) {
            return 13;
        }
        if (_ch <= 9371) {
            return 3;
        }
        if (_ch <= 10131) {
            return 13;
        }
        if (_ch <= 12879) {
            return 0;
        }
        if (_ch <= 12895) {
            return 13;
        }
        if (_ch <= 12937) {
            return 0;
        }
        if (_ch <= 12991) {
            return 13;
        }
        return 0;
    }

    private static int dirOther(char _ch) {
        if (_ch == 2307 || _ch == 2363 || _ch == 2519 || _ch == 2563 || _ch == 2691 || _ch == 2878 || _ch == 2880 || _ch == 2903 || _ch == 3415 || _ch == 3967 || _ch == 4145 || _ch == 4152 || _ch == 6070 || _ch == 6743 || _ch == 6753 || _ch == 6916 || _ch == 6965 || _ch == 6971 || _ch == 7082 || _ch == 7143 || _ch == 7150 || _ch == 7393 || _ch == 43395 || _ch == 43755) {
            return 0;
        }
        return dirOther12(_ch);
    }
    private static int dirOther12(char _ch) {
        if (_ch == 2362 || _ch == 2364 || _ch == 2381 || _ch == 2492 || _ch == 2509 || _ch == 2620 || _ch == 2748 || _ch == 2876 || _ch == 2879 || _ch == 2946 || _ch == 3008 || _ch == 3021 || _ch == 3260 || _ch == 3405 || _ch == 3530 || _ch == 4237 || _ch == 6086 || _ch == 6450 || _ch == 6742 || _ch == 6754 || _ch == 6964 || _ch == 6972 || _ch == 6978 || _ch == 7083 || _ch == 7142 || _ch == 7149 || _ch == 43443 || _ch == 43452 || _ch == 43766 || _ch == 44005 || _ch == 44008 || _ch == 44013) {
            return 8;
        }
        return dirOther11(_ch);
    }
    private static int dirOther11(char _ch) {
        if (_ch == 8232){
            return 12;
        }
        if (_ch == 8233){
            return 10;
        }
        if (dirBool(_ch)) {
            return 8;
        }
        return 0;
    }

    private static boolean dirBool(char _ch) {
        return _ch <= 2306 || dirOther10(_ch);
    }
    private static boolean dirOther10(char _ch) {
        return _ch > 2368 && (_ch <= 2376 || _ch > 2383 && (_ch <= 2433 || dirOther13(_ch)));
    }

    private static boolean dirOther13(char _ch) {
        return _ch > 2496 && (_ch <= 2500 || _ch > 2508 && (_ch <= 2562 || _ch > 2624 && dirOther9(_ch)));
    }

    private static boolean dirOther9(char _ch) {
        return _ch <= 2690 || _ch > 2752 && (_ch <= 2760 || _ch > 2764 && dirOther14(_ch));
    }

    private static boolean dirOther14(char _ch) {
        return _ch <= 2817 || _ch > 2819 && (_ch <= 2884 || _ch > 2892 && dirOther17(_ch));
    }

    private static boolean dirOther17(char _ch) {
        return _ch <= 2915 || _ch > 3075 && dirOther8(_ch);
    }

    private static boolean dirOther8(char _ch) {
        return _ch <= 3136 || _ch > 3140 && (_ch <= 3171 || _ch > 3275 && dirOther15(_ch));
    }

    private static boolean dirOther15(char _ch) {
        return _ch <= 3277 || _ch > 3286 && (_ch <= 3299 || dirOther16(_ch));
    }

    private static boolean dirOther16(char _ch) {
        return _ch > 3392 && (_ch <= 3396 || _ch > 3404 && dirOther7(_ch));
    }

    private static boolean dirOther7(char _ch) {
        return _ch <= 3427 || _ch > 3537 && (_ch <= 3542 || _ch > 3571 && (_ch <= 3897 || _ch > 3903 && (_ch <= 4038 || dirOther18(_ch))));
    }

    private static boolean dirOther18(char _ch) {
        return _ch > 4140 && (_ch <= 4154 || _ch > 4156 && dirOther6(_ch));
    }

    private static boolean dirOther6(char _ch) {
        return _ch <= 4158 || _ch > 4183 && (_ch <= 4192 || _ch > 4205 && dirOther19(_ch));
    }

    private static boolean dirOther19(char _ch) {
        return _ch <= 4226 || _ch > 4228 && (_ch <= 4230 || _ch > 4252 && (_ch <= 6077 || _ch > 6085 && dirOther5(_ch)));
    }

    private static boolean dirOther5(char _ch) {
        return _ch > 6088 && (_ch <= 6434 || _ch > 6438 && (_ch <= 6440 || _ch > 6456 && dirOther20(_ch)));
    }

    private static boolean dirOther20(char _ch) {
        return _ch <= 6459 || _ch > 6618 && (_ch <= 6680 || _ch > 6741 && (_ch <= 6752 || dirOther4(_ch)));
    }

    private static boolean dirOther4(char _ch) {
        return _ch > 6756 && (_ch <= 6764 || _ch > 6770 && (_ch <= 6970 || dirOther21(_ch)));
    }

    private static boolean dirOther21(char _ch) {
        return _ch > 6980 && (_ch <= 7041 || _ch > 7073 && (_ch <= 7077 || dirOther3(_ch)));
    }

    private static boolean dirOther3(char _ch) {
        return _ch > 7079 && (_ch <= 7081 || _ch > 7085 && (_ch <= 7145 || _ch > 7148 && dirOther22(_ch)));
    }

    private static boolean dirOther22(char _ch) {
        return _ch <= 7153 || _ch > 7211 && (_ch <= 7219 || _ch > 7221 && (_ch <= 7405 || _ch > 7411 && dirOther2(_ch)));
    }

    private static boolean dirOther2(char _ch) {
        return _ch <= 12333 || _ch > 12346 && (_ch <= 43019 || _ch > 43044 && (_ch <= 43046 || _ch > 43203 && (_ch <= 43345 || dirOther23(_ch))));
    }

    private static boolean dirOther23(char _ch) {
        return _ch > 43347 && (_ch <= 43394 || _ch > 43445 && (_ch <= 43449 || dirOther1(_ch)));
    }

    private static boolean dirOther1(char _ch) {
        return _ch > 43456 && (_ch <= 43566 || _ch > 43568 && (_ch <= 43570 || _ch > 43572 && dirOther24(_ch)));
    }

    private static boolean dirOther24(char _ch) {
        return _ch <= 43596 || _ch > 43643 && (_ch <= 43757 || _ch > 63743);
    }
    private static boolean isOtherDigit(char _ch) {
        for (int i: NumberUtil.wrapIntArray(1632,1776,1984,2406,2534,2662,
                2790,2918,3046,3174,3302,3430,3664,3792,3872,4160,4240,6112,6160,
                6470,6608,6784,6800,6992,7088,7232,7248,42528,43216,43264,43472,
                43600,44016,65296)) {
            if (inRangeBounds(_ch, i, i + 9)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOtherPonctuation(char _ch) {
        return _ch == 161 || _ch == 167 || _ch == 171 || _ch == 187 || _ch == 191 || _ch == 894 || _ch == 903 || inRangeBounds(_ch, 1370, 1375) || inRangeBounds(_ch, 1642, 1645) || inRangeBounds(_ch, 1792, 1805) || isOtherPonctuation7(_ch);
    }
    private static boolean isOtherPonctuation7(char _ch) {
        return inRangeBounds(_ch, 2039, 2041) || inRangeBounds(_ch, 2096, 2110) || inRangeBounds(_ch, 3844, 3858) || inRangeBounds(_ch, 3898, 3901) || inRangeBounds(_ch, 4048, 4052) || inRangeBounds(_ch, 4170, 4175) || inRangeBounds(_ch, 4960, 4968) || isOtherPonctuation6(_ch);
    }
    private static boolean isOtherPonctuation6(char _ch) {
        return inRangeBounds(_ch, 5867, 5869) || inRangeBounds(_ch, 6100, 6106) || inRangeBounds(_ch, 6144, 6154) || inRangeBounds(_ch, 6818, 6822) || inRangeBounds(_ch, 6824, 6829) || inRangeBounds(_ch, 7002, 7008) || inRangeBounds(_ch, 7164, 7167) || isOtherPonctuation5(_ch);
    }
    private static boolean isOtherPonctuation5(char _ch) {
        return inRangeBounds(_ch, 7227, 7231) || inRangeBounds(_ch, 7360, 7367) || inRangeBounds(_ch, 8208, 8231) || inRangeBounds(_ch, 8240, 8259) || inRangeBounds(_ch, 8261, 8273) || inRangeBounds(_ch, 8275, 8286) || inRangeBounds(_ch, 10088, 10101) || isOtherPonctuation4(_ch);
    }
    private static boolean isOtherPonctuation4(char _ch) {
        return inRangeBounds(_ch, 10214, 10223) || inRangeBounds(_ch, 10627, 10648) || inRangeBounds(_ch, 10712, 10715) || inRangeBounds(_ch, 11513, 11516) || inRangeBounds(_ch, 11776, 11822) || inRangeBounds(_ch, 11824, 11835) || inRangeBounds(_ch, 12289, 12291) || isOtherPonctuation3(_ch);
    }
    private static boolean isOtherPonctuation3(char _ch) {
        return inRangeBounds(_ch, 12296, 12305) || inRangeBounds(_ch, 12308, 12319) || inRangeBounds(_ch, 42738, 42743) || inRangeBounds(_ch, 43124, 43127) || inRangeBounds(_ch, 43457, 43469) || inRangeBounds(_ch, 43612, 43615) || inRangeBounds(_ch, 65040, 65049) || isOtherPonctuation2(_ch);
    }
    private static boolean isOtherPonctuation2(char _ch) {
        return inRangeBounds(_ch, 65072, 65106) || inRangeBounds(_ch, 65108, 65123) || inRangeBounds(_ch, 65281, 65283) || inRangeBounds(_ch, 65285, 65290) || inRangeBounds(_ch, 65292, 65295) || inRangeBounds(_ch, 65339, 65341) || inRangeBounds(_ch, 65375, 65381) || isOtherPonctuation1(_ch);
    }
    private static boolean isOtherPonctuation1(char _ch) {
        for (int i: NumberUtil.wrapIntArray(182,1417,1523,1545,
                1548,1566,2404,3674,4057,5741,5787,5941,6468,6686,6816,7294,8317,8333,9001,10181,
                10748,11518,42238,42510,43214,43256,43310,43486,43258,43742,43760,64830,65130,65306,65311)) {
            if (inRangeBounds(_ch, i, i + 1)) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(1470,1472,1475,1478,1563,1748,
                2142,2416,2800,3572,3663,3860,3973,4347,
                5120,7379,11632,12336,12349,12448,12539,
                42509,42611,42622,43359,44011,65128,65343,65371,65373)) {
            if (_ch == i) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOtherSymbol(char _ch) {
        return inRangeBounds(_ch, 706, 709) || inRangeBounds(_ch, 722, 735) || inRangeBounds(_ch, 741, 747) || inRangeBounds(_ch, 751, 767) || inRangeBounds(_ch, 3059, 3064) || inRangeBounds(_ch, 3841, 3843) || isOtherSymbol9(_ch);
    }
    private static boolean isOtherSymbol9(char _ch) {
        return inRangeBounds(_ch, 3861, 3863) || inRangeBounds(_ch, 3866, 3871) || inRangeBounds(_ch, 4030, 4037) || inRangeBounds(_ch, 4039, 4044) || inRangeBounds(_ch, 4053, 4056) || inRangeBounds(_ch, 5008, 5017) || inRangeBounds(_ch, 6622, 6655) || isOtherSymbol8(_ch);
    }
    private static boolean isOtherSymbol8(char _ch) {
        return inRangeBounds(_ch, 7009, 7018) || inRangeBounds(_ch, 7028, 7036) || inRangeBounds(_ch, 8127, 8129) || inRangeBounds(_ch, 8141, 8143) || inRangeBounds(_ch, 8157, 8159) || inRangeBounds(_ch, 8173, 8175) || inRangeBounds(_ch, 8451, 8454) || isOtherSymbol7(_ch);
    }
    private static boolean isOtherSymbol7(char _ch) {
        return inRangeBounds(_ch, 8478, 8483) || inRangeBounds(_ch, 8598, 8601) || inRangeBounds(_ch, 9140, 9179) || inRangeBounds(_ch, 9186, 9203) || inRangeBounds(_ch, 9216, 9254) || inRangeBounds(_ch, 9280, 9290) || inRangeBounds(_ch, 8604, 8607) || isOtherSymbol6(_ch);
    }
    private static boolean isOtherSymbol6(char _ch) {
        return inRangeBounds(_ch, 8615, 8621) || inRangeBounds(_ch, 8623, 8653) || inRangeBounds(_ch, 8661, 8691) || inRangeBounds(_ch, 8960, 8967) || inRangeBounds(_ch, 8972, 8991) || inRangeBounds(_ch, 8994, 9000) || inRangeBounds(_ch, 9003, 9083) || isOtherSymbol5(_ch);
    }
    private static boolean isOtherSymbol5(char _ch) {
        if (inRangeBounds(_ch, 9085, 9114) || inRangeBounds(_ch, 9372, 9449)) {
            return true;
        }
        for (int i: NumberUtil.wrapIntArray(9655,9665,9839,9984)) {
            if (_ch == i) {
                return false;
            }
        }
        if (inRangeBounds(_ch, 12246, 12271)) {
            return false;
        }
        return inRangeBounds(_ch, 9472, 10087) || inRangeBounds(_ch, 10132, 10175) || isOtherSymbol4(_ch);
    }
    private static boolean isOtherSymbol4(char _ch) {
        return inRangeBounds(_ch, 10240, 10495) || inRangeBounds(_ch, 11008, 11055) || inRangeBounds(_ch, 11088, 11097) || inRangeBounds(_ch, 11493, 11498) || inRangeBounds(_ch, 11904, 11929) || inRangeBounds(_ch, 11931, 12019) || inRangeBounds(_ch, 12032, 12283) || isOtherSymbol3(_ch);
    }
    private static boolean isOtherSymbol3(char _ch) {
        return inRangeBounds(_ch, 12694, 12703) || inRangeBounds(_ch, 12736, 12771) || inRangeBounds(_ch, 12800, 12830) || inRangeBounds(_ch, 12842, 12871) || inRangeBounds(_ch, 12896, 12927) || inRangeBounds(_ch, 12938, 12976) || inRangeBounds(_ch, 12992, 13054) || isOtherSymbol2(_ch);
    }
    private static boolean isOtherSymbol2(char _ch) {
        return inRangeBounds(_ch, 13056, 13311) || inRangeBounds(_ch, 19904, 19967) || inRangeBounds(_ch, 42128, 42182) || inRangeBounds(_ch, 42752, 42774) || inRangeBounds(_ch, 43048, 43051) || inRangeBounds(_ch, 43639, 43641) || inRangeBounds(_ch, 64434, 64449) || isOtherSymbol1(_ch);
    }
    private static boolean isOtherSymbol1(char _ch) {
        for (int i: NumberUtil.wrapIntArray(900,1550,1789,
                4046,4254,8189,8448,8470,8456,8506,8597,8524,8609,8612,8656,
                11077,12306,12342,12350,12443,12688,42784,42889,43062,
                65507,65517,65532)) {
            if (inRangeBounds(_ch, i, i + 1)) {
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

    private static boolean isCurrencyChar(char _ch) {
        if (inRangeBounds(_ch, 162, 165) || inRangeBounds(_ch, 8352, 8378)) {
            return true;
        }
        for (int i: NumberUtil.wrapIntArray(2546,65504,65509)) {
            if (inRangeBounds(_ch, i, i + 1)) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(36,1423,1547,2555,2801,
                3065,3647,6107,
                43064,65020,65129,65284)) {
            if (_ch == i) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOtherLettersDigits(char _ch) {
        return inRangeBounds(_ch, 12690, 12730) || inRangeBounds(_ch, 12832, 12991) || inRangeBounds(_ch, 43056, 43061) || isOtherLettersDigits3(_ch);
    }
    private static boolean isOtherLettersDigits3(char _ch) {
        return _ch == 8585 || inRangeBounds(_ch, 9312, 9983) || inRangeBounds(_ch, 9985, 10131) || inRangeBounds(_ch, 2548, 2553) || inRangeBounds(_ch, 2930, 2935) || inRangeBounds(_ch, 3056, 3058) || inRangeBounds(_ch, 3192, 3198) || isOtherLettersDigits2(_ch);
    }
    private static boolean isOtherLettersDigits2(char _ch) {
        return inRangeBounds(_ch, 3440, 3445) || inRangeBounds(_ch, 3882, 3891) || inRangeBounds(_ch, 4969, 4988) || _ch == 11517 || inRangeBounds(_ch, 8528, 8543) || inRangeBounds(_ch, 8304, 8305) || inRangeBounds(_ch, 8308, 8329) || isOtherLettersDigits1(_ch);
    }
    private static boolean isOtherLettersDigits1(char _ch) {
        return inRangeBounds(_ch, 6128, 6137) || _ch == 6618 || inRangeBounds(_ch, 8576, 8584) || inRangeBounds(_ch, 12295, 12329) || inRangeBounds(_ch, 42726, 42735) || inRangeBounds(_ch, 12344, 12346) || inRangeBounds(_ch, 5870, 5872);
    }

    private static boolean isOtherModifier(char _ch) {
        return _ch == 1757 || _ch == 1807 || inRangeBounds(_ch, 1536, 1540) || inRangeBounds(_ch, 8203, 8207) || inRangeBounds(_ch, 8234, 8238) || inRangeBounds(_ch, 8288, 8292) || inRangeBounds(_ch, 8298, 8303) || inRangeBounds(_ch, 65529, 65531) || _ch == 65279;
    }

    private static boolean isOtherSpace(char _ch) {
        return _ch == 160 || _ch == 5760 || _ch == 6158 || _ch == 12288 || _ch == 8239 || _ch == 8287 || inRangeBounds(_ch, 127, 159) || inRangeBounds(_ch, 8192, 8202);
    }

    private static boolean isUnassigned(char _ch) {
        return inRangeBounds(_ch, 888, 889) || inRangeBounds(_ch, 895, 899) || _ch == 907 || _ch == 909 || _ch == 930 || inRangeBounds(_ch, 1320, 1328) || inRangeBounds(_ch, 1367, 1368) || _ch == 1376 || _ch == 1416 || isUnassigned47(_ch);
    }
    private static boolean isUnassigned47(char _ch) {
        return inRangeBounds(_ch, 1419, 1422) || _ch == 1424 || inRangeBounds(_ch, 1480, 1487) || inRangeBounds(_ch, 1515, 1519) || inRangeBounds(_ch, 1525, 1535) || _ch == 1541 || inRangeBounds(_ch, 1564, 1565) || isUnassigned46(_ch);
    }
    private static boolean isUnassigned46(char _ch) {
        return _ch == 1806 || inRangeBounds(_ch, 1867, 1868) || inRangeBounds(_ch, 1970, 1983) || inRangeBounds(_ch, 2043, 2047) || inRangeBounds(_ch, 2094, 2095) || _ch == 2111 || inRangeBounds(_ch, 2140, 2141) || isUnassigned45(_ch);
    }
    private static boolean isUnassigned45(char _ch) {
        return inRangeBounds(_ch, 2143, 2207) || _ch == 2209 || inRangeBounds(_ch, 2221, 2275) || _ch == 2303 || _ch == 2424 || _ch == 2432 || _ch == 2436 || inRangeBounds(_ch, 2445, 2446) || inRangeBounds(_ch, 2449, 2450) || _ch == 2473 || _ch == 2481 || isUnassigned44(_ch);
    }
    private static boolean isUnassigned44(char _ch) {
        return inRangeBounds(_ch, 2483, 2485) || inRangeBounds(_ch, 2490, 2491) || inRangeBounds(_ch, 2501, 2502) || inRangeBounds(_ch, 2505, 2506) || inRangeBounds(_ch, 2511, 2518) || inRangeBounds(_ch, 2520, 2523) || _ch == 2526 || isUnassigned43(_ch);
    }
    private static boolean isUnassigned43(char _ch) {
        return inRangeBounds(_ch, 2532, 2533) || inRangeBounds(_ch, 2556, 2560) || _ch == 2564 || inRangeBounds(_ch, 2571, 2574) || inRangeBounds(_ch, 2577, 2578) || _ch == 2601 || _ch == 2609 || _ch == 2612 || isUnassigned42(_ch);
    }
    private static boolean isUnassigned42(char _ch) {
        return _ch == 2615 || inRangeBounds(_ch, 2618, 2619) || _ch == 2621 || inRangeBounds(_ch, 2627, 2630) || inRangeBounds(_ch, 2633, 2634) || inRangeBounds(_ch, 2638, 2640) || inRangeBounds(_ch, 2642, 2648) || isUnassigned41(_ch);
    }
    private static boolean isUnassigned41(char _ch) {
        return _ch == 2653 || inRangeBounds(_ch, 2655, 2661) || inRangeBounds(_ch, 2678, 2688) || _ch == 2692 || _ch == 2702 || _ch == 2706 || _ch == 2729 || _ch == 2737 || _ch == 2740 || isUnassigned40(_ch);
    }
    private static boolean isUnassigned40(char _ch) {
        return inRangeBounds(_ch, 2746, 2747) || _ch == 2758 || _ch == 2762 || inRangeBounds(_ch, 2766, 2767) || inRangeBounds(_ch, 2769, 2783) || inRangeBounds(_ch, 2788, 2789) || inRangeBounds(_ch, 2802, 2816) || isUnassigned39(_ch);
    }
    private static boolean isUnassigned39(char _ch) {
        return _ch == 2820 || inRangeBounds(_ch, 2829, 2830) || inRangeBounds(_ch, 2833, 2834) || _ch == 2857 || _ch == 2865 || _ch == 2868 || inRangeBounds(_ch, 2874, 2875) || inRangeBounds(_ch, 2885, 2886) || isUnassigned38(_ch);
    }
    private static boolean isUnassigned38(char _ch) {
        return inRangeBounds(_ch, 2889, 2890) || inRangeBounds(_ch, 2894, 2901) || inRangeBounds(_ch, 2904, 2907) || _ch == 2910 || inRangeBounds(_ch, 2916, 2917) || inRangeBounds(_ch, 2936, 2945) || _ch == 2948 || isUnassigned37(_ch);
    }
    private static boolean isUnassigned37(char _ch) {
        return inRangeBounds(_ch, 2955, 2957) || _ch == 2961 || inRangeBounds(_ch, 2966, 2968) || _ch == 2971 || _ch == 2973 || inRangeBounds(_ch, 2976, 2978) || inRangeBounds(_ch, 2981, 2983) || inRangeBounds(_ch, 2987, 2989) || isUnassigned36(_ch);
    }
    private static boolean isUnassigned36(char _ch) {
        return inRangeBounds(_ch, 3002, 3005) || inRangeBounds(_ch, 3011, 3013) || _ch == 3017 || inRangeBounds(_ch, 3022, 3023) || inRangeBounds(_ch, 3025, 3030) || inRangeBounds(_ch, 3032, 3045) || inRangeBounds(_ch, 3067, 3072) || isUnassigned35(_ch);
    }
    private static boolean isUnassigned35(char _ch) {
        return _ch == 3076 || _ch == 3085 || _ch == 3089 || _ch == 3113 || _ch == 3124 || inRangeBounds(_ch, 3130, 3132) || _ch == 3141 || _ch == 3145 || inRangeBounds(_ch, 3150, 3156) || _ch == 3159 || isUnassigned34(_ch);
    }
    private static boolean isUnassigned34(char _ch) {
        return inRangeBounds(_ch, 3162, 3167) || inRangeBounds(_ch, 3172, 3173) || inRangeBounds(_ch, 3184, 3191) || inRangeBounds(_ch, 3200, 3201) || _ch == 3204 || _ch == 3213 || _ch == 3217 || isUnassigned33(_ch);
    }
    private static boolean isUnassigned33(char _ch) {
        return _ch == 3241 || _ch == 3252 || inRangeBounds(_ch, 3258, 3259) || _ch == 3269 || _ch == 3273 || inRangeBounds(_ch, 3278, 3284) || inRangeBounds(_ch, 3287, 3293) || _ch == 3295 || inRangeBounds(_ch, 3300, 3301) || isUnassigned32(_ch);
    }
    private static boolean isUnassigned32(char _ch) {
        return _ch == 3312 || inRangeBounds(_ch, 3315, 3329) || _ch == 3332 || _ch == 3341 || _ch == 3345 || inRangeBounds(_ch, 3387, 3388) || _ch == 3397 || _ch == 3401 || inRangeBounds(_ch, 3407, 3414) || inRangeBounds(_ch, 3416, 3423) || isUnassigned31(_ch);
    }
    private static boolean isUnassigned31(char _ch) {
        return inRangeBounds(_ch, 3428, 3429) || inRangeBounds(_ch, 3446, 3448) || inRangeBounds(_ch, 3456, 3457) || _ch == 3460 || inRangeBounds(_ch, 3479, 3481) || _ch == 3506 || _ch == 3516 || isUnassigned30(_ch);
    }
    private static boolean isUnassigned30(char _ch) {
        return inRangeBounds(_ch, 3518, 3519) || inRangeBounds(_ch, 3527, 3529) || inRangeBounds(_ch, 3531, 3534) || _ch == 3541 || _ch == 3543 || inRangeBounds(_ch, 3552, 3569) || inRangeBounds(_ch, 3573, 3584) || isUnassigned29(_ch);
    }
    private static boolean isUnassigned29(char _ch) {
        return inRangeBounds(_ch, 3643, 3646) || inRangeBounds(_ch, 3676, 3712) || _ch == 3715 || inRangeBounds(_ch, 3717, 3718) || _ch == 3721 || inRangeBounds(_ch, 3723, 3724) || inRangeBounds(_ch, 3726, 3731) || isUnassigned28(_ch);
    }
    private static boolean isUnassigned28(char _ch) {
        return _ch == 3736 || _ch == 3744 || _ch == 3748 || _ch == 3750 || inRangeBounds(_ch, 3752, 3753) || _ch == 3756 || _ch == 3770 || inRangeBounds(_ch, 3774, 3775) || _ch == 3781 || _ch == 3783 || isUnassigned27(_ch);
    }
    private static boolean isUnassigned27(char _ch) {
        return inRangeBounds(_ch, 3790, 3791) || inRangeBounds(_ch, 3802, 3803) || inRangeBounds(_ch, 3808, 3839) || _ch == 3912 || inRangeBounds(_ch, 3949, 3952) || _ch == 3992 || _ch == 4029 || isUnassigned26(_ch);
    }
    private static boolean isUnassigned26(char _ch) {
        return _ch == 4045 || inRangeBounds(_ch, 4059, 4095) || _ch == 4294 || inRangeBounds(_ch, 4296, 4300) || inRangeBounds(_ch, 4302, 4303) || _ch == 4681 || inRangeBounds(_ch, 4686, 4687) || isUnassigned25(_ch);
    }
    private static boolean isUnassigned25(char _ch) {
        return _ch == 4695 || _ch == 4697 || inRangeBounds(_ch, 4702, 4703) || _ch == 4745 || inRangeBounds(_ch, 4750, 4751) || _ch == 4785 || inRangeBounds(_ch, 4790, 4791) || _ch == 4799 || _ch == 4801 || isUnassigned24(_ch);
    }
    private static boolean isUnassigned24(char _ch) {
        return inRangeBounds(_ch, 4806, 4807) || _ch == 4823 || _ch == 4881 || inRangeBounds(_ch, 4886, 4887) || inRangeBounds(_ch, 4955, 4956) || inRangeBounds(_ch, 4989, 4991) || inRangeBounds(_ch, 5018, 5023) || isUnassigned23(_ch);
    }
    private static boolean isUnassigned23(char _ch) {
        return inRangeBounds(_ch, 5109, 5119) || inRangeBounds(_ch, 5789, 5791) || inRangeBounds(_ch, 5873, 5887) || _ch == 5901 || inRangeBounds(_ch, 5909, 5919) || inRangeBounds(_ch, 5943, 5951) || inRangeBounds(_ch, 5972, 5983) || isUnassigned22(_ch);
    }
    private static boolean isUnassigned22(char _ch) {
        return _ch == 5997 || _ch == 6001 || inRangeBounds(_ch, 6004, 6015) || inRangeBounds(_ch, 6110, 6111) || inRangeBounds(_ch, 6122, 6127) || inRangeBounds(_ch, 6138, 6143) || _ch == 6159 || isUnassigned21(_ch);
    }
    private static boolean isUnassigned21(char _ch) {
        return inRangeBounds(_ch, 6170, 6175) || inRangeBounds(_ch, 6264, 6271) || inRangeBounds(_ch, 6315, 6319) || inRangeBounds(_ch, 6390, 6399) || inRangeBounds(_ch, 6429, 6431) || inRangeBounds(_ch, 6444, 6447) || inRangeBounds(_ch, 6460, 6463) || isUnassigned20(_ch);
    }
    private static boolean isUnassigned20(char _ch) {
        return inRangeBounds(_ch, 6465, 6467) || inRangeBounds(_ch, 6510, 6511) || inRangeBounds(_ch, 6517, 6527) || inRangeBounds(_ch, 6572, 6575) || inRangeBounds(_ch, 6602, 6607) || inRangeBounds(_ch, 6619, 6621) || inRangeBounds(_ch, 6684, 6685) || isUnassigned19(_ch);
    }
    private static boolean isUnassigned19(char _ch) {
        return _ch == 6751 || inRangeBounds(_ch, 6781, 6782) || inRangeBounds(_ch, 6794, 6799) || inRangeBounds(_ch, 6810, 6815) || inRangeBounds(_ch, 6830, 6911) || inRangeBounds(_ch, 6988, 6991) || inRangeBounds(_ch, 7037, 7039) || isUnassigned18(_ch);
    }
    private static boolean isUnassigned18(char _ch) {
        return inRangeBounds(_ch, 7156, 7163) || inRangeBounds(_ch, 7224, 7226) || inRangeBounds(_ch, 7242, 7244) || inRangeBounds(_ch, 7296, 7359) || inRangeBounds(_ch, 7368, 7375) || inRangeBounds(_ch, 7415, 7423) || inRangeBounds(_ch, 7655, 7675) || isUnassigned17(_ch);
    }
    private static boolean isUnassigned17(char _ch) {
        return inRangeBounds(_ch, 7958, 7959) || inRangeBounds(_ch, 7966, 7967) || inRangeBounds(_ch, 8006, 8007) || inRangeBounds(_ch, 8014, 8015) || _ch == 8024 || _ch == 8026 || _ch == 8028 || _ch == 8030 || isUnassigned16(_ch);
    }
    private static boolean isUnassigned16(char _ch) {
        return inRangeBounds(_ch, 8062, 8063) || _ch == 8117 || _ch == 8133 || inRangeBounds(_ch, 8148, 8149) || _ch == 8156 || inRangeBounds(_ch, 8176, 8177) || _ch == 8181 || _ch == 8191 || inRangeBounds(_ch, 8293, 8297) || isUnassigned15(_ch);
    }
    private static boolean isUnassigned15(char _ch) {
        return inRangeBounds(_ch, 8306, 8307) || _ch == 8335 || inRangeBounds(_ch, 8349, 8351) || inRangeBounds(_ch, 8379, 8399) || inRangeBounds(_ch, 8433, 8447) || inRangeBounds(_ch, 8586, 8591) || inRangeBounds(_ch, 9204, 9215) || isUnassigned14(_ch);
    }
    private static boolean isUnassigned14(char _ch) {
        return inRangeBounds(_ch, 9255, 9279) || inRangeBounds(_ch, 9291, 9311) || _ch == 9984 || inRangeBounds(_ch, 11085, 11087) || inRangeBounds(_ch, 11098, 11263) || _ch == 11311 || _ch == 11359 || isUnassigned13(_ch);
    }
    private static boolean isUnassigned13(char _ch) {
        return inRangeBounds(_ch, 11508, 11512) || _ch == 11558 || inRangeBounds(_ch, 11560, 11564) || inRangeBounds(_ch, 11566, 11567) || inRangeBounds(_ch, 11624, 11630) || inRangeBounds(_ch, 11633, 11646) || inRangeBounds(_ch, 11671, 11679) || isUnassigned12(_ch);
    }
    private static boolean isUnassigned12(char _ch) {
        return _ch == 11687 || _ch == 11695 || _ch == 11703 || _ch == 11711 || _ch == 11719 || _ch == 11727 || _ch == 11735 || _ch == 11743 || inRangeBounds(_ch, 11836, 11903) || _ch == 11930 || inRangeBounds(_ch, 12020, 12031) || isUnassigned11(_ch);
    }
    private static boolean isUnassigned11(char _ch) {
        return inRangeBounds(_ch, 12246, 12271) || inRangeBounds(_ch, 12284, 12287) || _ch == 12352 || inRangeBounds(_ch, 12439, 12440) || inRangeBounds(_ch, 12544, 12548) || inRangeBounds(_ch, 12590, 12592) || _ch == 12687 || isUnassigned10(_ch);
    }
    private static boolean isUnassigned10(char _ch) {
        return inRangeBounds(_ch, 12731, 12735) || inRangeBounds(_ch, 12772, 12783) || _ch == 12831 || _ch == 13055 || inRangeBounds(_ch, 19894, 19903) || inRangeBounds(_ch, 40909, 40959) || inRangeBounds(_ch, 42125, 42127) || isUnassigned9(_ch);
    }
    private static boolean isUnassigned9(char _ch) {
        return inRangeBounds(_ch, 42183, 42191) || inRangeBounds(_ch, 42540, 42559) || inRangeBounds(_ch, 42648, 42654) || inRangeBounds(_ch, 42744, 42751) || _ch == 42895 || inRangeBounds(_ch, 42900, 42911) || inRangeBounds(_ch, 42923, 42999) || isUnassigned8(_ch);
    }
    private static boolean isUnassigned8(char _ch) {
        return inRangeBounds(_ch, 43052, 43055) || inRangeBounds(_ch, 43066, 43071) || inRangeBounds(_ch, 43128, 43135) || inRangeBounds(_ch, 43205, 43213) || inRangeBounds(_ch, 43226, 43231) || inRangeBounds(_ch, 43260, 43263) || inRangeBounds(_ch, 43348, 43358) || isUnassigned7(_ch);
    }
    private static boolean isUnassigned7(char _ch) {
        return inRangeBounds(_ch, 43389, 43391) || _ch == 43470 || inRangeBounds(_ch, 43482, 43485) || inRangeBounds(_ch, 43488, 43519) || inRangeBounds(_ch, 43575, 43583) || inRangeBounds(_ch, 43598, 43599) || inRangeBounds(_ch, 43610, 43611) || isUnassigned6(_ch);
    }
    private static boolean isUnassigned6(char _ch) {
        return inRangeBounds(_ch, 43644, 43647) || inRangeBounds(_ch, 43715, 43738) || inRangeBounds(_ch, 43767, 43776) || inRangeBounds(_ch, 43783, 43784) || inRangeBounds(_ch, 43791, 43792) || inRangeBounds(_ch, 43799, 43807) || _ch == 43815 || isUnassigned5(_ch);
    }
    private static boolean isUnassigned5(char _ch) {
        return inRangeBounds(_ch, 43823, 43967) || inRangeBounds(_ch, 44014, 44015) || inRangeBounds(_ch, 44026, 44031) || inRangeBounds(_ch, 55204, 55215) || inRangeBounds(_ch, 55239, 55242) || inRangeBounds(_ch, 55292, 55295) || inRangeBounds(_ch, 64110, 64111) || isUnassigned4(_ch);
    }
    private static boolean isUnassigned4(char _ch) {
        return inRangeBounds(_ch, 64218, 64255) || inRangeBounds(_ch, 64263, 64274) || inRangeBounds(_ch, 64280, 64284) || _ch == 64311 || _ch == 64317 || _ch == 64319 || _ch == 64322 || isUnassigned3(_ch);
    }
    private static boolean isUnassigned3(char _ch) {
        return _ch == 64325 || inRangeBounds(_ch, 64450, 64466) || inRangeBounds(_ch, 64832, 64847) || inRangeBounds(_ch, 64912, 64913) || inRangeBounds(_ch, 64968, 65007) || inRangeBounds(_ch, 65022, 65023) || inRangeBounds(_ch, 65050, 65055) || isUnassigned2(_ch);
    }
    private static boolean isUnassigned2(char _ch) {
        return inRangeBounds(_ch, 65063, 65071) || _ch == 65107 || _ch == 65127 || inRangeBounds(_ch, 65132, 65135) || _ch == 65141 || inRangeBounds(_ch, 65277, 65278) || _ch == 65280 || isUnassigned1(_ch);
    }
    private static boolean isUnassigned1(char _ch) {
        return inRangeBounds(_ch, 65471, 65473) || inRangeBounds(_ch, 65480, 65481) || inRangeBounds(_ch, 65488, 65489) || inRangeBounds(_ch, 65496, 65497) || inRangeBounds(_ch, 65501, 65503) || _ch == 65511 || inRangeBounds(_ch, 65519, 65528) || _ch >= 65534;
    }

    private static boolean isRomanDigits(char _ch) {
        return inRangeBounds(_ch, 8544, 8575);
    }

    private static boolean isSensibleOtherLetter(char _ch) {
        return inRangeBounds(_ch, 9398, 9449);
    }

    private static boolean isQuotePunct(char _ch) {
        return inRangeBounds(_ch, 8216, 8217) || inRangeBounds(_ch, 8219, 8221) || _ch == 8223 || _ch == 171 || _ch == 187 || inRangeBounds(_ch, 8249, 8250) || isQuotePunct1(_ch);
    }
    private static boolean isQuotePunct1(char _ch) {
        return inRangeBounds(_ch, 11778, 11781) || inRangeBounds(_ch, 11785, 11786) || inRangeBounds(_ch, 11788, 11789) || inRangeBounds(_ch, 11804, 11805) || inRangeBounds(_ch, 11808, 11809);
    }

    private static boolean isBoundPunct(char _ch) {
        return inRangeBounds(_ch, 3898, 3901) || inRangeBounds(_ch, 5787, 5788) || _ch == 8218 || _ch == 8222 || isBoundPunct2(_ch);
    }
    private static boolean isBoundPunct2(char _ch) {
        return inRangeBounds(_ch, 8261, 8262) || inRangeBounds(_ch, 8287, 9002) || inRangeBounds(_ch, 10088, 10749) || inRangeBounds(_ch, 11810, 11817) || inRangeBounds(_ch, 12296, 12319) || inRangeBounds(_ch, 64830, 65039) || isBoundPunct1(_ch);
    }
    private static boolean isBoundPunct1(char _ch) {
        return inRangeBounds(_ch, 65047, 65048) || inRangeBounds(_ch, 65073, 65092) || inRangeBounds(_ch, 65095, 65096) || inRangeBounds(_ch, 65113, 65118) || inRangeBounds(_ch, 65288, 65289) || inRangeBounds(_ch, 65313, 65339) || inRangeBounds(_ch, 65341, 65376) || inRangeBounds(_ch, 65378, 65379);
    }

    private static boolean isConnector(char _ch) {
        return inRangeBounds(_ch, 1418, 1471) || _ch == 5120 || _ch == 6150 || _ch == 8276
                || _ch == 11802 || _ch == 12316 || _ch == 12448 || _ch == 12336
                || _ch == 65123 || _ch == 65112 || _ch == 65293 || _ch == 65343 || isConnector1(_ch);
    }
    private static boolean isConnector1(char _ch) {
        return inRangeBounds(_ch, 8208, 8213) || inRangeBounds(_ch, 8255, 8256) || _ch == 11799 || inRangeBounds(_ch, 11834, 11835) || inRangeBounds(_ch, 65073, 65076) || inRangeBounds(_ch, 65101, 65103);
    }

    private static boolean isModifierSymbol(char _ch) {
        return _ch <= 901 || inRangeBounds(_ch, 8125, 8190) || inRangeBounds(_ch, 12443, 12444) || inRangeBounds(_ch, 42752, 42890) || inRangeBounds(_ch, 64434, 65020) || inRangeBounds(_ch, 65022, 65507);
    }

    private static int procGene(char _ch, int _type) {
        if (_type == LETTER_SENS_LOWER_CASE) {
            return 2;
        }
        if (_type == LETTER_SENS_UPPER_CASE) {
            return 1;
        }
        if (_type == LETTER_INSENS_LOWER_CASE) {
            return processLetter(_ch);
        }
        if (_type == LETTER_INSENS_UPPER_CASE) {
            return 1;
        }
        if (_type == LETTER_SENS_NO_CASE || _type == LETTER_SEMI_SENS_NO_CASE) {
            return 3;
        }
        if (_type == LETTER_INSENS_NO_CASE_DEF_DIR) {
            return processOtherLetter(_ch);
        }
        if (_type == LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS) {
            return 4;
        }
        if (_type == LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT) {
            return processOtherLetterTwo(_ch);
        }
        if (_type == DIGIT_BASE || _type == DIGIT_OTHER) {
            return 9;
        }
        if (_type == ID_SEP) {
            if (_ch == 160) {
                return 12;
            }
            return 23;
        }
        return processGeneralSymbol(_ch, _type);
    }

    private static int processGeneralSymbol(char _ch, int _type) {
        if (_type == CURRENCY) {
            return 26;
        }
        if (_type == SENS_OTHER_LETTER) {
            return 28;
        }
        if (_type == ROMAN_DIGIT) {
            return 10;
        }
        if (_type == DEL_LEFT) {
            return 21;
        }
        if (_type == DEL_RIGHT) {
            return 22;
        }
        if (_type == OPERATOR_LANGUAGE) {
            return processOperators(_ch);
        }
        if (_type == OPERATOR_SPEC || _type == PUNCTUATION) {
            return 24;
        }
        if (_type == QUOTES) {
            return processQuote(_ch);
        }
        if (_type == ESCAPE) {
            return 24;
        }
        return processGeneralSymbol1(_ch,_type);
    }
    private static int processGeneralSymbol1(char _ch, int _type) {
        if (_type == MATH) {
            return 25;
        }
        if (_type == MODIFIER) {
            return processModifier(_ch);
        }
        if (_type == SYMBOL_MODIFIER) {
            return 27;
        }
        if (_type == SYMBOL_OTHER) {
            return 28;
        }
        if (_type == PUNCTUATION_CONNECTOR) {
            return processPunct(_ch);
        }
        if (_type == PUNCTUATION_BOUND) {
            return processPunctTwo(_ch);
        }
        if (_type == PUNCTUATION_QUOTE) {
            return processPunctThree(_ch);
        }
        if (_type == PUNCTUATION_OTHER) {
            return 24;
        }
        if (_type == LETTERS_DIGITS_OTHER) {
            return processLettersDigitsOther(_ch);
        }
        if (_type == SEPARATOR) {
            return 16;
        }
        return processDefault(_ch, _type);
    }

    private static int processDefault(char _ch, int _type) {
        if (_type == SPACE) {
            if (_ch <= 10) {
                return 15;
            }
            return 12;
        }
        if (_type == SPACE_OTHER) {
            if (_ch <= 159) {
                return 15;
            }
            return 12;
        }
        if (_type == OTHER) {
            return processOther(_ch);
        }
        return 0;
    }

    private static int processPunctThree(char _ch) {
        if (_ch == 171 || _ch == 8223) {
            return 29;
        }
        if (_ch == 187 || _ch == 8221) {
            return 30;
        }
        if (_ch <= 8217) {
            return processPunctPair(_ch);
        }
        if (_ch <= 8220) {
            return 29;
        }
        return processPunctThree1(_ch);
    }
    private static int processPunctThree1(char _ch) {
        if (_ch <= 11777) {
            return processPunctImpair(_ch);
        }
        if (_ch <= 11781) {
            return processPunctPair(_ch);
        }
        if (_ch <= 11786) {
            return processPunctImpair(_ch);
        }
        return processPunctPair(_ch);
    }

    private static int processPunctPair(char _ch) {
        if (_ch % 2 != 0) {
            return 30;
        }
        return 29;
    }

    private static int processPunctImpair(char _ch) {
        if (_ch % 2 != 0) {
            return 29;
        }
        return 30;
    }

    private static int processPunctTwo(char _ch) {
        if (_ch <= 4000) {
            return pair(_ch);
        }
        if (_ch <= 6000) {
            return impair(_ch);
        }
        if (_ch <= 8261) {
            return 21;
        }
        if (_ch <= 10000) {
            return impair(_ch);
        }
        return processPunctTwo3(_ch);
    }
    private static int processPunctTwo3(char _ch) {
        if (_ch <= 10180) {
            return pair(_ch);
        }
        if (_ch <= 10182) {
            return impair(_ch);
        }
        if (_ch <= 10223) {
            return pair(_ch);
        }
        if (_ch <= 10711) {
            return impair(_ch);
        }
        return processPunctTwo2(_ch);
    }
    private static int processPunctTwo2(char _ch) {
        if (_ch <= 12315) {
            return pair(_ch);
        }
        if (_ch <= 12318) {
            return impair(_ch);
        }
        if (_ch <= 12319) {
            return 22;
        }
        if (_ch <= 64831) {
            return pair(_ch);
        }
        return processPunctTwo1(_ch);
    }

    private static int impair(char _ch) {
        if (_ch % 2 != 0) {
            return 21;
        }
        return 22;
    }

    private static int processPunctTwo1(char _ch) {
        if (_ch <= 65118) {
            return impair(_ch);
        }
        if (_ch <= 65289) {
            return pair(_ch);
        }
        if (_ch == 65339 || _ch == 65371 || _ch == 65375 || _ch == 65378) {
            return 21;
        }
        return 22;
    }

    private static int pair(char _ch) {
        if (_ch % 2 != 0) {
            return 22;
        }
        return 21;
    }

    private static int processPunct(char _ch) {
        if (_ch <= 8213) {
            return 20;
        }
        if (_ch <= 8276) {
            return 23;
        }
        if (_ch <= 65074) {
            return 20;
        }
        if (_ch <= 65103) {
            return 23;
        }
        if (_ch <= 65293) {
            return 20;
        }
        return 23;
    }

    private static int processModifier(char _ch) {
        if (_ch == 173) {
            return 16;
        }
        if (_ch == 166) {
            return 28;
        }
        if (_ch == 168 || _ch == 175 || _ch == 180 || _ch == 184) {
            return 27;
        }
        if (_ch <= 176) {
            return 28;
        }
        return 11;
    }

    private static int processQuote(char _ch) {
        if (_ch <= 64) {
            return 24;
        }
        return 27;
    }

    private static int processOperators(char _ch) {
        if (_ch == 45) {
            return 20;
        }
        if (_ch == 94) {
            return 27;
        }
        if (_ch == 43) {
            return 25;
        }
        if (_ch <= 58) {
            return 24;
        }
        if (_ch <= 62) {
            return 25;
        }
        if (_ch <= 64) {
            return 24;
        }
        return 25;
    }

    private static int processOtherLetterTwo(char _ch) {
        if (_ch == 1600 || _ch == 1765 || _ch == 1766 || _ch == 2036 || _ch == 2037 || _ch == 2042 || _ch == 2074 || _ch == 2084 || _ch == 2088) {
            return 4;
        }
        return 5;
    }

    private static int processOtherLetter(char _ch) {
        if (_ch == 1369 || _ch == 2417 || _ch == 3654 || _ch == 3782 || _ch == 4348 || _ch == 6103 || _ch == 6211 || _ch == 6823 || _ch == 11631 || _ch == 12293 || _ch == 40981 || _ch == 42508 || _ch == 43741 || _ch == 43471 || _ch == 43632 || _ch == 43763 || _ch == 43764 || _ch == 65392 || _ch == 65438 || _ch == 65439 || inRangeBounds(_ch, 7288, 7293) || inRangeBounds(_ch, 12337, 12347) || inRangeBounds(_ch, 12445, 12446) || inRangeBounds(_ch, 12539, 12542) || inRangeBounds(_ch, 42232, 42239) || inRangeBounds(_ch, 699, 750)) {
            return 4;
        }
        return 5;
    }

    private static int processLetter(char _ch) {
        if (_ch <= 186) {
            return 5;
        }
        if (_ch <= 687) {
            return 2;
        }
        if (_ch <= 890) {
            return 4;
        }
        if (_ch <= 7467) {
            return 2;
        }
        if (_ch <= 7530) {
            return 4;
        }
        if (_ch <= 7543) {
            return 2;
        }
        if (_ch <= 7544) {
            return 4;
        }
        if (_ch <= 7578) {
            return 2;
        }
        return processLetter1(_ch);
    }
    private static int processLetter1(char _ch) {
        if (_ch <= 7615) {
            return 4;
        }
        if (_ch <= 8183) {
            return 2;
        }
        if (_ch <= 8348) {
            return 4;
        }
        if (_ch <= 11387) {
            return 2;
        }
        if (_ch <= 11389) {
            return 4;
        }
        if (_ch <= 42801) {
            return 2;
        }
        if (_ch <= 42864) {
            return 4;
        }
        if (_ch <= 42894) {
            return 2;
        }
        if (_ch <= 43001) {
            return 4;
        }
        return 2;
    }

    private static int processLettersDigitsOther(char _ch) {
        if (inRangeBounds(_ch, 12690, 12991) || _ch >= 43056 || inRangeBounds(_ch, 8585, 10131) || _ch <= 4988 || _ch == 11517 || inRangeBounds(_ch, 8528, 8543) || inRangeBounds(_ch, 8304, 8329) || inRangeBounds(_ch, 6128, 6137) || _ch == 6618) {
            return 11;
        }
        return 10;
    }

    private static int processOther(char _ch) {
        if (_ch == 8232) {
            return 13;
        }
        if (_ch == 8233) {
            return 14;
        }
        if (inRangeBounds(_ch, 57344, 63743)) {
            return 18;
        }
        if (inRangeBounds(_ch, 55296, 57343)) {
            return 19;
        }
        if (_ch >= 44013) {
            return 6;
        }
        if (inRangeBounds(_ch, 8413, 8416) || inRangeBounds(_ch, 8418, 8420) || inRangeBounds(_ch, 42608, 42610)) {
            return 7;
        }
        if (_ch <= 1159) {
            return 6;
        }
        return processOther6(_ch);
    }
    private static int processOther6(char _ch) {
        if (_ch <= 1161) {
            return 7;
        }
        if (_ch <= 2306 || inRangeBounds(_ch, 3633, 3897) || inRangeBounds(_ch, 3953, 3966) || inRangeBounds(_ch, 43204, 43345) || inRangeBounds(_ch, 7412, 12333) || inRangeBounds(_ch, 12441, 43019) || inRangeBounds(_ch, 7222, 7392) || inRangeBounds(_ch, 7394, 7405) || inRangeBounds(_ch, 3968, 4038) || inRangeBounds(_ch, 4253, 6069) || inRangeBounds(_ch, 6071, 6077) || inRangeBounds(_ch, 6089, 6434) || inRangeBounds(_ch, 2369, 2376) || inRangeBounds(_ch, 2385, 2433)) {
            return 6;
        }
        return processOther5(_ch);
    }
    private static int processOther5(char _ch) {
        if (inRangeBounds(_ch, 2625, 2690) || inRangeBounds(_ch, 3142, 3171) || inRangeBounds(_ch, 6744, 6752) || inRangeBounds(_ch, 6771, 6915)) {
            return 6;
        }
        if (inRangeBounds(_ch, 43047, 43347) || inRangeBounds(_ch, 4239, 6085) || inRangeBounds(_ch, 2366, 2380) || inRangeBounds(_ch, 7220, 43044)) {
            return 8;
        }
        if (inRangeBounds(_ch, 7212, 43394)) {
            return 6;
        }
        if (inRangeBounds(_ch, 7154, 43395)) {
            return 8;
        }
        if (inRangeBounds(_ch, 7151, 43443)) {
            return 6;
        }
        if (inRangeBounds(_ch, 7150, 43445)) {
            return 8;
        }
        if (inRangeBounds(_ch, 7149, 43449)) {
            return 6;
        }
        if (inRangeBounds(_ch, 7146, 43451)) {
            return 8;
        }
        if (inRangeBounds(_ch, 7144, 43452)) {
            return 6;
        }
        return processOther4(_ch);
    }
    private static int processOther4(char _ch) {
        if (inRangeBounds(_ch, 7143, 43456)) {
            return 8;
        }
        if (inRangeBounds(_ch, 7142, 43566)) {
            return 6;
        }
        if (inRangeBounds(_ch, 7084, 43568)) {
            return 8;
        }
        if (inRangeBounds(_ch, 7083, 43570)) {
            return 6;
        }
        if (inRangeBounds(_ch, 7082, 43572)) {
            return 8;
        }
        if (inRangeBounds(_ch, 7080, 43596)) {
            return 6;
        }
        if (inRangeBounds(_ch, 7078, 43643)) {
            return 8;
        }
        if (inRangeBounds(_ch, 7074, 43713)) {
            return 6;
        }
        if (inRangeBounds(_ch, 7042, 43755)) {
            return 8;
        }
        if (inRangeBounds(_ch, 7019, 43757)) {
            return 6;
        }
        if (inRangeBounds(_ch, 6979, 43765)) {
            return 8;
        }
        if (inRangeBounds(_ch, 6757, 6764) || inRangeBounds(_ch, 6966, 6970) || inRangeBounds(_ch, 4141, 4144) || inRangeBounds(_ch, 4146, 4151)) {
            return 6;
        }
        return processOther3(_ch);
    }
    private static int processOther3(char _ch) {
        if (inRangeBounds(_ch, 4153, 4154) || inRangeBounds(_ch, 4157, 4158) || inRangeBounds(_ch, 4184, 4192) || inRangeBounds(_ch, 4209, 4226) || inRangeBounds(_ch, 4229, 4230) || inRangeBounds(_ch, 4237, 6086) || inRangeBounds(_ch, 6439, 6440) || _ch == 6450 || inRangeBounds(_ch, 6457, 6459)) {
            return 6;
        }
        if (inRangeBounds(_ch, 3544, 6601)) {
            return 8;
        }
        if (inRangeBounds(_ch, 3538, 6680)) {
            return 6;
        }
        if (inRangeBounds(_ch, 3535, 6741)) {
            return 8;
        }
        if (inRangeBounds(_ch, 3393, 3396)) {
            return 6;
        }
        if (inRangeBounds(_ch, 3330, 3404)) {
            return 8;
        }
        if (inRangeBounds(_ch, 2753, 2760)) {
            return 6;
        }
        return processOther2(_ch);
    }
    private static int processOther2(char _ch) {
        if (inRangeBounds(_ch, 2750, 2764)) {
            return 8;
        }
        if (inRangeBounds(_ch, 2748, 2817)) {
            return 6;
        }
        if (inRangeBounds(_ch, 2622, 2819)) {
            return 8;
        }
        if (inRangeBounds(_ch, 2530, 2562)) {
            return 6;
        }
        if (inRangeBounds(_ch, 2519, 2563)) {
            return 8;
        }
        if (inRangeBounds(_ch, 2509, 2876)) {
            return 6;
        }
        if (inRangeBounds(_ch, 2503, 2878)) {
            return 8;
        }
        if (inRangeBounds(_ch, 3298, 3405)) {
            return 6;
        }
        if (inRangeBounds(_ch, 3285, 3415)) {
            return 8;
        }
        if (inRangeBounds(_ch, 3276, 3427)) {
            return 6;
        }
        if (inRangeBounds(_ch, 3271, 3459)) {
            return 8;
        }
        if (inRangeBounds(_ch, 2887, 2892)) {
            return 8;
        }
        if (inRangeBounds(_ch, 2881, 2902)) {
            return 6;
        }
        if (inRangeBounds(_ch, 2880, 2903)) {
            return 8;
        }
        if (inRangeBounds(_ch, 2497, 2946)) {
            return 6;
        }
        return processOther1(_ch);
    }
    private static int processOther1(char _ch) {
        if (inRangeBounds(_ch, 6755, 6916)) {
            return 8;
        }
        if (inRangeBounds(_ch, 6754, 6964) || _ch == 2362 || _ch == 2492 || _ch == 3008 || _ch == 3021) {
            return 6;
        }
        if (inRangeBounds(_ch, 2382, 3075)) {
            return 8;
        }
        if (inRangeBounds(_ch, 2364, 3136) || inRangeBounds(_ch, 3270, 6742)) {
            return 6;
        }
        if (inRangeBounds(_ch, 3264, 6971)) {
            return 8;
        }
        if (_ch == 3260 || _ch == 44008 || _ch == 44005) {
            return 6;
        }
        if (inRangeBounds(_ch, 6973, 6977)) {
            return 8;
        }
        if (inRangeBounds(_ch, 3263, 43766)) {
            return 6;
        }
        return 8;
    }

    private static boolean isOtherMathSymbol(char _string) {
        if (_string < 128) {
            return false;
        }
        for (int i: NumberUtil.wrapIntArray(8602,8654)) {
            if (inRangeBounds(_string, i, i + 1)) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(1542,8314,8330)) {
            if (inRangeBounds(_string, i, i + 2)) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(8512,8592)) {
            if (inRangeBounds(_string, i, i + 4)) {
                return true;
            }
        }
        return _string == 172 || isOtherMathSymbol3(_string);
    }
    private static boolean isOtherMathSymbol3(char _string) {
        return _string == 177 || _string == 215 || _string == 247 || _string == 1014 || _string == 8611 || _string == 8260 || _string == 8614 || _string == 8274 || _string == 8622 || _string == 8472 || _string == 8660 || _string == 8658 || _string == 8523 || _string == 9655 || _string == 9665 || isOtherMathSymbol2(_string);
    }
    private static boolean isOtherMathSymbol2(char _string) {
        return _string == 8608 || _string == 9839 || _string == 9084 || inRangeBounds(_string, 9115, 9139) || inRangeBounds(_string, 9180, 9185) || inRangeBounds(_string, 9720, 9727) || inRangeBounds(_string, 8968, 8971) || !inRangeBounds(_string, 8960, 8991) && (inRangeBounds(_string, 8692, 8993) || inRangeBounds(_string, 10176, 10180) || inRangeBounds(_string, 10183, 10213) || inRangeBounds(_string, 10224, 10239) || !inRangeBounds(_string, 10627, 10648) && isOtherMathSymbol1(_string));
    }
    private static boolean isOtherMathSymbol1(char _string) {
        return inRangeBounds(_string, 10716, 10747) || !inRangeBounds(_string, 10712, 10749) && (inRangeBounds(_string, 11056, 11076) || !inRangeBounds(_string, 11008, 11078) && (inRangeBounds(_string, 10496, 11084) || inRangeBounds(_string, 65513, 65516) || inRangeBounds(_string, 65308, 65310) || inRangeBounds(_string, 65124, 65126) || _string == 64297 || _string == 65122 || _string == 65291 || _string == 65372 || _string == 65374 || _string == 65506));
    }

    private static int toLowerCaseInt(char _ch) {
        if (_ch == 453 || _ch == 456) {
            return _ch + 1;
        }
        if (isUnassigned(_ch)) {
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
        if (inRangeBounds(_ch, 256, 302) || inRangeBounds(_ch, 306, 310) || inRangeBounds(_ch, 330, 374) || inRangeBounds(_ch, 386, 388) || inRangeBounds(_ch, 416, 420) || inRangeBounds(_ch, 478, 494) || inRangeBounds(_ch, 498, 500) || inRangeBounds(_ch, 504, 542) || inRangeBounds(_ch, 546, 562) || inRangeBounds(_ch, 582, 590)) {
            return _ch + 1;
        }
        return toLowerCheckDefTwo3(_ch);
    }
    private static int toLowerCheckDefTwo3(char _ch) {
        if (inRangeBounds(_ch, 880, 882) || inRangeBounds(_ch, 984, 1006) || inRangeBounds(_ch, 1120, 1152) || inRangeBounds(_ch, 1162, 1214) || inRangeBounds(_ch, 1232, 1318) || inRangeBounds(_ch, 7680, 7828) || inRangeBounds(_ch, 7840, 7934) || inRangeBounds(_ch, 11392, 11490) || inRangeBounds(_ch, 42560, 42604) || inRangeBounds(_ch, 42624, 42646) || inRangeBounds(_ch, 42786, 42798) || inRangeBounds(_ch, 42802, 42862) || inRangeBounds(_ch, 42878, 42886) || inRangeBounds(_ch, 42896, 42898) || inRangeBounds(_ch, 42912, 42920)) {
            return _ch + 1;
        }
        return toLowerCheckDef(_ch);
    }

    private static int toLowerCheckDefTwo1(char _ch) {
        if (inRangeBounds(_ch, 313, 327) || inRangeBounds(_ch, 377, 381) || inRangeBounds(_ch, 435, 437) || inRangeBounds(_ch, 459, 475) || inRangeBounds(_ch, 1217, 1229) || inRangeBounds(_ch, 11367, 11371) || inRangeBounds(_ch, 11499, 11501) || inRangeBounds(_ch, 42873, 42875)) {
            return _ch + 1;
        }

        return toLowerCheckDef(_ch);
    }

    private static int toLowerCaseIntCheckUpp(char _ch) {
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
        if (inRangeBounds(_ch, 256, 302) || inRangeBounds(_ch, 306, 310) || inRangeBounds(_ch, 330, 374) || inRangeBounds(_ch, 386, 388) || inRangeBounds(_ch, 416, 420) || inRangeBounds(_ch, 478, 494) || inRangeBounds(_ch, 498, 500) || inRangeBounds(_ch, 504, 542) || inRangeBounds(_ch, 546, 562) || inRangeBounds(_ch, 582, 590) || inRangeBounds(_ch, 880, 882) || inRangeBounds(_ch, 984, 1006) || inRangeBounds(_ch, 1120, 1152) || inRangeBounds(_ch, 1162, 1214) || inRangeBounds(_ch, 1232, 1318)) {
            return _ch + 1;
        }
        return toLowerCaseDefaultSeven3(_ch);
    }
    private static int toLowerCaseDefaultSeven3(char _ch) {
        if (inRangeBounds(_ch, 7680, 7828) || inRangeBounds(_ch, 7840, 7934) || inRangeBounds(_ch, 11392, 11490) || inRangeBounds(_ch, 42560, 42604) || inRangeBounds(_ch, 42624, 42646) || inRangeBounds(_ch, 42786, 42798) || inRangeBounds(_ch, 42802, 42862) || inRangeBounds(_ch, 42878, 42886) || inRangeBounds(_ch, 42896, 42898) || inRangeBounds(_ch, 42912, 42920)) {
            return _ch + 1;
        }
        return toLowerCaseDefaultSix(_ch);
    }

    private static int toLowerCaseDefaultSeven1(char _ch) {
        if (inRangeBounds(_ch, 313, 327) || inRangeBounds(_ch, 377, 381) || inRangeBounds(_ch, 435, 437) || inRangeBounds(_ch, 459, 475) || inRangeBounds(_ch, 1217, 1229) || inRangeBounds(_ch, 11367, 11371) || inRangeBounds(_ch, 11499, 11501) || inRangeBounds(_ch, 42873, 42875)) {
            return _ch + 1;
        }

        return toLowerCaseDefaultSix(_ch);
    }

    private static int toLowerCaseDefaultSix(char _ch) {
        if (inRangeBounds(_ch, 65, 90) || inRangeBounds(_ch, 192, 214) || inRangeBounds(_ch, 216, 222)) {
            return _ch + 32;
        }
        if (inRangeBounds(_ch, 393, 394)) {
            return _ch + 205;
        }
        if (inRangeBounds(_ch, 433, 434)) {
            return _ch + 217;
        }
        if (inRangeBounds(_ch, 904, 906)) {
            return _ch + 37;
        }
        if (inRangeBounds(_ch, 910, 911)) {
            return _ch + 63;
        }
        if (inRangeBounds(_ch, 913, 929)) {
            return _ch + 32;
        }
        if (inRangeBounds(_ch, 931, 939)) {
            return _ch + 32;
        }
        if (inRangeBounds(_ch, 1021, 1023)) {
            return _ch -130;
        }
        if (inRangeBounds(_ch, 1024, 1039)) {
            return _ch + 80;
        }
        if (inRangeBounds(_ch, 1040, 1071)) {
            return _ch + 32;
        }
        if (inRangeBounds(_ch, 1329, 1366)) {
            return _ch + 48;
        }
        return toLowerCaseDefaultSix2(_ch);
    }
    private static int toLowerCaseDefaultSix2(char _ch) {
        if (inRangeBounds(_ch, 4256, 4293)) {
            return _ch + 7264;
        }
        if (inRangeBounds(_ch, 7944, 7951) || inRangeBounds(_ch, 7960, 7965) || inRangeBounds(_ch, 7976, 7983) || inRangeBounds(_ch, 7992, 7999) || inRangeBounds(_ch, 8008, 8013) || inRangeBounds(_ch, 8040, 8047) || inRangeBounds(_ch, 8120, 8121)) {
            return _ch - 8;
        }
        if (inRangeBounds(_ch, 8122, 8123)) {
            return _ch -74;
        }
        if (inRangeBounds(_ch, 8136, 8139)) {
            return _ch -86;
        }
        if (inRangeBounds(_ch, 8152, 8153)) {
            return _ch -8;
        }
        if (inRangeBounds(_ch, 8154, 8155)) {
            return _ch -100;
        }
        return toLowerCaseDefaultSix1(_ch);
    }
    private static int toLowerCaseDefaultSix1(char _ch) {
        if (inRangeBounds(_ch, 8168, 8169)) {
            return _ch -8;
        }
        if (inRangeBounds(_ch, 8170, 8171)) {
            return _ch -112;
        }
        if (inRangeBounds(_ch, 8184, 8185)) {
            return _ch -128;
        }
        if (inRangeBounds(_ch, 8186, 8187)) {
            return _ch -126;
        }
        if (inRangeBounds(_ch, 11264, 11310)) {
            return _ch + 48;
        }
        if (inRangeBounds(_ch, 11390, 11391)) {
            return _ch -10815;
        }
        if (inRangeBounds(_ch, 65313, 65338)) {
            return _ch + 32;
        }
        return _ch;
    }

    private static int toLowerCaseIntCheck(char _ch) {
        return toLowerCheckDefFive(_ch);
    }

    private static int toLowerCheckDefThree(char _ch) {
        if (_ch == 571 || _ch == 577 || _ch == 886 || _ch == 1015 || _ch == 1018 || _ch == 8579 || _ch == 11360) {
            return _ch + 1;
        }
        return toLowerCheckDefThree5(_ch);
    }
    private static int toLowerCheckDefThree5(char _ch) {
        if (_ch == 11378 || _ch == 11381 || _ch == 11506 || _ch == 42891) {
            return _ch + 1;
        }
        if (_ch == 304) {
            return _ch -199;
        }
        if (_ch == 376) {
            return _ch -121;
        }
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
        if (inRangeBounds(_ch, 65, 90) || inRangeBounds(_ch, 192, 214) || inRangeBounds(_ch, 216, 222)) {
            return _ch + 32;
        }
        if (inRangeBounds(_ch, 393, 394)) {
            return _ch + 205;
        }
        if (inRangeBounds(_ch, 433, 434)) {
            return _ch + 217;
        }
        if (inRangeBounds(_ch, 904, 906)) {
            return _ch + 37;
        }
        if (inRangeBounds(_ch, 910, 911)) {
            return _ch + 63;
        }
        if (inRangeBounds(_ch, 913, 929)) {
            return _ch + 32;
        }
        if (inRangeBounds(_ch, 931, 939)) {
            return _ch + 32;
        }
        if (inRangeBounds(_ch, 1021, 1023)) {
            return _ch -130;
        }
        return toLowerCheckDef2(_ch);
    }
    private static int toLowerCheckDef2(char _ch) {
        if (inRangeBounds(_ch, 1024, 1039)) {
            return _ch + 80;
        }
        if (inRangeBounds(_ch, 1040, 1071)) {
            return _ch + 32;
        }
        if (inRangeBounds(_ch, 1329, 1366)) {
            return _ch + 48;
        }
        if (inRangeBounds(_ch, 4256, 4293)) {
            return _ch + 7264;
        }
        if (inRangeBounds(_ch, 7944, 7951) || inRangeBounds(_ch, 7960, 7965) || inRangeBounds(_ch, 7976, 7983) || inRangeBounds(_ch, 7992, 7999) || inRangeBounds(_ch, 8008, 8013) || inRangeBounds(_ch, 8040, 8047)) {
            return _ch - 8;
        }
        return toLowerCheckDef1(_ch);
    }
    private static int toLowerCheckDef1(char _ch) {
        if (inRangeBounds(_ch, 8072, 8079) || inRangeBounds(_ch, 8088, 8095) || inRangeBounds(_ch, 8104, 8111) || inRangeBounds(_ch, 8120, 8121)) {
            return _ch - 8;
        }
        if (inRangeBounds(_ch, 8122, 8123)) {
            return _ch -74;
        }
        if (inRangeBounds(_ch, 8136, 8139)) {
            return _ch -86;
        }
        if (inRangeBounds(_ch, 8152, 8153)) {
            return _ch -8;
        }
        if (inRangeBounds(_ch, 8154, 8155)) {
            return _ch -100;
        }
        if (inRangeBounds(_ch, 8168, 8169)) {
            return _ch -8;
        }
        if (inRangeBounds(_ch, 8170, 8171)) {
            return _ch -112;
        }
        if (inRangeBounds(_ch, 8184, 8185)) {
            return _ch -128;
        }
        if (inRangeBounds(_ch, 8186, 8187)) {
            return _ch -126;
        }
        if (inRangeBounds(_ch, 11264, 11310)) {
            return _ch + 48;
        }
        if (inRangeBounds(_ch, 11390, 11391)) {
            return _ch -10815;
        }
        if (inRangeBounds(_ch, 65313, 65338)) {
            return _ch + 32;
        }
        return _ch;
    }

    private static int toUpperCaseInt(char _ch) {
        if (isUnassigned(_ch)) {
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
            if (_ch >= 8544 + 16) {
                return _ch-16;
            }
            return _ch;
        }
        if (isSensibleOtherLetter(_ch)) {
            if (_ch >= 9398 + 26) {
                return _ch-26;
            }
            return _ch;
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
        if (_ch == 658) {
            return _ch -219;
        }
        if (_ch == 7545) {
            return _ch + 35332;
        }
        if (_ch == 7549) {
            return _ch + 3814;
        }
        if (_ch == 459) {
            return _ch -1;
        }
        if (_ch == 498) {
            return _ch -1;
        }
        return toUpperCaseDefFour(_ch);
    }

    private static int toUpperCaseIntCheck(char _ch) {
        if (isRomanDigits(_ch)) {
            if (_ch >= 8544 + 16) {
                return _ch-16;
            }
            return _ch;
        }
        if (isSensibleOtherLetter(_ch)) {
            if (_ch >= 9398 + 26) {
                return _ch-26;
            }
            return _ch;
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
        if (inRangeBounds(_ch, 314, 328) || inRangeBounds(_ch, 378, 382) || inRangeBounds(_ch, 436, 438) || inRangeBounds(_ch, 462, 476) || inRangeBounds(_ch, 1218, 1230) || inRangeBounds(_ch, 11368, 11372) || inRangeBounds(_ch, 11500, 11502) || inRangeBounds(_ch, 42874, 42876)) {
            return _ch - 1;
        }
        return toUpperCaseDef(_ch);
    }

    private static int toUpperCaseDefTwo1(char _ch) {
        if (inRangeBounds(_ch, 257, 303) || inRangeBounds(_ch, 307, 311) || inRangeBounds(_ch, 331, 375) || inRangeBounds(_ch, 387, 389) || inRangeBounds(_ch, 417, 421) || inRangeBounds(_ch, 479, 495) || inRangeBounds(_ch, 505, 543) || inRangeBounds(_ch, 547, 563) || inRangeBounds(_ch, 583, 591) || inRangeBounds(_ch, 881, 883) || inRangeBounds(_ch, 985, 1007) || inRangeBounds(_ch, 1121, 1153) || inRangeBounds(_ch, 1163, 1215)) {
            return _ch - 1;
        }
        return toUpperCaseDefTwo3(_ch);
    }
    private static int toUpperCaseDefTwo3(char _ch) {
        if (inRangeBounds(_ch, 1233, 1319) || inRangeBounds(_ch, 7681, 7829) || inRangeBounds(_ch, 7841, 7935) || inRangeBounds(_ch, 11393, 11491) || inRangeBounds(_ch, 42561, 42605) || inRangeBounds(_ch, 42625, 42647) || inRangeBounds(_ch, 42787, 42799) || inRangeBounds(_ch, 42803, 42863) || inRangeBounds(_ch, 42879, 42887) || inRangeBounds(_ch, 42897, 42899) || inRangeBounds(_ch, 42913, 42921)) {
            return _ch - 1;
        }
        return toUpperCaseDef(_ch);
    }

    private static int toUpperCaseIntCheckLow(char _ch) {
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
        if (inRangeBounds(_ch, 97, 122) || inRangeBounds(_ch, 224, 246) || inRangeBounds(_ch, 248, 254)) {
            return _ch - 32;
        }
        if (inRangeBounds(_ch, 575, 576)) {
            return _ch + 10815;
        }
        if (inRangeBounds(_ch, 598, 599)) {
            return _ch -205;
        }
        if (inRangeBounds(_ch, 650, 651)) {
            return _ch -217;
        }
        if (inRangeBounds(_ch, 891, 893)) {
            return _ch + 130;
        }
        return toUpperCaseDef4(_ch);
    }
    private static int toUpperCaseDef4(char _ch){
        if (inRangeBounds(_ch, 941, 943)) {
            return _ch -37;
        }
        if (inRangeBounds(_ch, 945, 961)) {
            return _ch -32;
        }
        if (inRangeBounds(_ch, 963, 971)) {
            return _ch -32;
        }
        if (inRangeBounds(_ch, 973, 974)) {
            return _ch -63;
        }
        if (inRangeBounds(_ch, 1072, 1103)) {
            return _ch -32;
        }
        if (inRangeBounds(_ch, 1104, 1119)) {
            return _ch -80;
        }
        if (inRangeBounds(_ch, 1377, 1414)) {
            return _ch -48;
        }
        return toUpperCaseDef3(_ch);
    }
    private static int toUpperCaseDef3(char _ch){
        if (inRangeBounds(_ch, 7937, 7943) || inRangeBounds(_ch, 7952, 7957) || inRangeBounds(_ch, 7968, 7975) || inRangeBounds(_ch, 7984, 7991) || inRangeBounds(_ch, 8000, 8005) || inRangeBounds(_ch, 8032, 8039)) {
            return _ch + 8;
        }
        if (inRangeBounds(_ch, 8048, 8049)) {
            return _ch + 74;
        }
        return toUpperCaseDef2(_ch);
    }
    private static int toUpperCaseDef2(char _ch){
        if (inRangeBounds(_ch, 8050, 8053)) {
            return _ch + 86;
        }
        if (inRangeBounds(_ch, 8054, 8055)) {
            return _ch + 100;
        }
        if (inRangeBounds(_ch, 8056, 8057)) {
            return _ch + 128;
        }
        if (inRangeBounds(_ch, 8058, 8059)) {
            return _ch + 112;
        }
        if (inRangeBounds(_ch, 8060, 8061)) {
            return _ch + 126;
        }
        if (inRangeBounds(_ch, 8064, 8071) || inRangeBounds(_ch, 8080, 8087)) {
            return _ch + 8;
        }
        return toUpperCaseDef1(_ch);
    }
    private static int toUpperCaseDef1(char _ch){
        if (inRangeBounds(_ch, 8096, 8103) || inRangeBounds(_ch, 8112, 8113) || inRangeBounds(_ch, 8144, 8145) || inRangeBounds(_ch, 8160, 8161)) {
            return _ch + 8;
        }
        if (inRangeBounds(_ch, 11312, 11358)) {
            return _ch -48;
        }
        if (inRangeBounds(_ch, 11520, 11557)) {
            return _ch -7264;
        }
        if (inRangeBounds(_ch, 65345, 65370)) {
            return _ch -32;
        }
        return _ch;
    }

    private static boolean inRangeBounds(char _ch, int _mini, int _maxi) {
        return _ch >= _mini && _ch <= _maxi;
    }

}
