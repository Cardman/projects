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
        return StringDataLetterUtil.isLetter(_ch)|| StringDataDirLetterUtil.isOtherDigit(_ch);
    }

    public static boolean isLowerCase(char _string) {
        if (StringDataUndefinedUtil.isUnassigned(_string) || StringDataDirLetterUtil.isOtherSpace(_string) || MathExpUtil.isDigit(_string) || StringDataDirLetterUtil.isOtherDigit(_string) || _string == 453 || _string == 456 || _string == 459 || _string == 498) {
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
        return _string != 660 && (inRangeBounds(_string, 653, 696) || _string == 890 || _string == 912 || _string == 944 || _string == 1011 || _string == 1020 || _string == 1415 || StringDataDirLetterUtil.toLowerCaseIntCheck(_string) == _string && StringDataDirLetterUtil.toUpperCaseIntCheckLow(_string) != _string);
    }

    public static boolean isUpperCase(char _string) {
        if (StringDataUndefinedUtil.isUnassigned(_string)|| StringDataDirLetterUtil.isOtherSpace(_string)||MathExpUtil.isDigit(_string)|| StringDataDirLetterUtil.isOtherDigit(_string)) {
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
        return _string != 8124 && _string != 8140 && _string != 8188 && (_string == 8450 || _string == 8484 || _string == 8455 || _string == 8488 || _string == 8469 || _string == 8499 || _string == 8517 || inRangeBounds(_string, 8473, 8477) || StringDataDirLetterUtil.toUpperCaseIntCheck(_string) == _string && StringDataDirLetterUtil.toLowerCaseIntCheckUpp(_string) != _string);
    }

    public static char toLowerCase(char _string) {
        return (char) StringDataDirLetterUtil.toLowerCaseInt(_string);
    }

    public static char toUpperCase(char _string) {
        return (char) StringDataDirLetterUtil.toUpperCaseInt(_string);
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
        if (StringDataDirLetterUtil.isOtherDigit(_ch)) {
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
        if (StringDataDirLetterUtil.isSensibleOtherLetter(_ch)) {
            return SENS_OTHER_LETTER;
        }
        if (StringDataDirLetterUtil.isRomanDigits(_ch)) {
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
        if (StringDataDirLetterUtil.isOtherMathSymbol(_ch)) {
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
        if (StringDataSymbolUtil.isOtherSymbol(_ch)) {
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
        if (StringDataDirLetterUtil.isOtherSpace(_ch)) {
            return SPACE_OTHER;
        }
        if (!StringDataUndefinedUtil.isUnassigned(_ch)) {
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
        return generalDirOther3(_ch);
    }

    private static int generalDirOther3(char _ch) {
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
        if (_type == QUOTES || _type == ESCAPE) {
            return 13;
        }
        if (_type == MATH) {
            return StringDataSymbolUtil.dirOtherPrintSix(_ch);
        }
        if (_type == MODIFIER) {
            return StringDataSymbolUtil.dirOtherPrintFive(_ch);
        }
        if (_type == SYMBOL_MODIFIER) {
            return StringDataSymbolUtil.dirOtherPrintFour(_ch);
        }
        if (_type == SYMBOL_OTHER) {
            return StringDataSymbolUtil.dirOtherPrintTwo(_ch);
        }
        if (_type == PUNCTUATION_CONNECTOR) {
            return StringDataSymbolUtil.dirOtherPrintThree(_ch);
        }
        if (_type == PUNCTUATION_BOUND || _type == PUNCTUATION_QUOTE) {
            return 13;
        }
        if (_type == PUNCTUATION_OTHER) {
            return StringDataSymbolUtil.dirOtherPrint(_ch);
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

    private static int dirSeparator(char _ch) {
        if (_ch == 8206){
            return 0;
        }
        if (_ch == 1757){
            return 6;
        }
        return dirSeparator3(_ch);
    }

    private static int dirSeparator3(char _ch) {
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

    static int dirLettersDigitsOther(char _ch) {
        if (_ch == 11517) {
            return 13;
        }

        if (_ch <= 3075) {
            return 0;
        }
        if (_ch <= 3198) {
            return 13;
        }
        return dirLettersDigitsOther4(_ch);
    }

    private static int dirLettersDigitsOther4(char _ch) {
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
        return dirLettersDigitsOther3(_ch);
    }

    private static int dirLettersDigitsOther3(char _ch) {
        if (_ch <= 9351) {
            return 13;
        }
        if (_ch <= 9371) {
            return 3;
        }
        if (_ch <= 10131) {
            return 13;
        }
        return dirLettersDigitsOther2(_ch);
    }

    private static int dirLettersDigitsOther2(char _ch) {
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
        return processPunct1(_ch);
    }

    private static int processPunct1(char _ch) {
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
        return processOperators1(_ch);
    }

    private static int processOperators1(char _ch) {
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
        return processLetter4(_ch);
    }

    private static int processLetter4(char _ch) {
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
        return processLetter3(_ch);
    }

    private static int processLetter3(char _ch) {
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
        return processLetter2(_ch);
    }

    private static int processLetter2(char _ch) {
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
        return processOther16(_ch);
    }

    private static int processOther16(char _ch) {
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
        return processOther15(_ch);
    }

    private static int processOther15(char _ch) {
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
        return processOther14(_ch);
    }

    private static int processOther14(char _ch) {
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
        return processOther13(_ch);
    }

    private static int processOther13(char _ch) {
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
        return processOther12(_ch);
    }

    private static int processOther12(char _ch) {
        if (inRangeBounds(_ch, 7042, 43755)) {
            return 8;
        }
        if (inRangeBounds(_ch, 7019, 43757)) {
            return 6;
        }
        if (inRangeBounds(_ch, 6979, 43765)) {
            return 8;
        }
        if (inRangeBounds(_ch, 6757, 6764) || inRangeBounds(_ch, 6966, 6970) || inRangeBounds(_ch, 4141, 4144) || inRangeBounds(_ch, 4146, 4151) || inRangeBounds(_ch, 4153, 4154) || inRangeBounds(_ch, 4157, 4158) || inRangeBounds(_ch, 4184, 4192) || inRangeBounds(_ch, 4209, 4226) || inRangeBounds(_ch, 4229, 4230) || inRangeBounds(_ch, 4237, 6086) || inRangeBounds(_ch, 6439, 6440) || _ch == 6450 || inRangeBounds(_ch, 6457, 6459)) {
            return 6;
        }
        return processOther3(_ch);
    }

    private static int processOther3(char _ch) {
        if (inRangeBounds(_ch, 3544, 6601)) {
            return 8;
        }
        if (inRangeBounds(_ch, 3538, 6680)) {
            return 6;
        }
        return processOther11(_ch);
    }

    private static int processOther11(char _ch) {
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
        return processOther10(_ch);
    }

    private static int processOther10(char _ch) {
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
        return processOther9(_ch);
    }

    private static int processOther9(char _ch) {
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
        return processOther8(_ch);
    }

    private static int processOther8(char _ch) {
        if (inRangeBounds(_ch, 3271, 3459) || inRangeBounds(_ch, 2887, 2892)) {
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
        return processOther7(_ch);
    }

    private static int processOther7(char _ch) {
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

    static boolean inRangeBounds(char _ch, int _mini, int _maxi) {
        return _ch >= _mini && _ch <= _maxi;
    }

}
