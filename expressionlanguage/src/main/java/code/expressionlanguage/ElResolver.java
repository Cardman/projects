package code.expressionlanguage;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.util.BooleanList;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;


public final class ElResolver {

    public static final int BAD_PRIO = -1;
    public static final int CONST_PRIO = 0;
    public static final int AFF_PRIO = 1;
    public static final int OR_PRIO = 2;
    public static final int AND_PRIO = 3;
    public static final int EQ_PRIO = 4;
    public static final int CMP_PRIO = 5;
    public static final int ADD_PRIO = 6;
    public static final int MULT_PRIO = 7;
    public static final int UNARY_PRIO = 8;
    public static final int POST_INCR_PRIO = 9;
    public static final int FCT_OPER_PRIO = 10;
    public static final byte UNICODE_SIZE = 4;

    private static final String EMPTY_STRING = "";
    private static final char LINE_RETURN = '\n';
    private static final char LINE_FEED = '\r';
    private static final char TAB = '\t';
    private static final char ESCAPE_META_CHAR = '\\';
    private static final char DELIMITER_CHAR = 39;
    private static final char DELIMITER_STRING = 34;
    private static final char UNICODE = 'u';
    private static final char IND_FORM = 'f';
    private static final char IND_LINE = 'n';
    private static final char IND_LINE_FEED = 'r';
    private static final char IND_TAB = 't';
    private static final char IND_BOUND = 'b';
    private static final char ARR_LEFT = '[';
    private static final char ARR_RIGHT = ']';
    private static final char ANN_ARR_LEFT = '{';
    private static final char ANN_ARR_RIGHT = '}';
    private static final char PAR_LEFT = '(';
    private static final char PAR_RIGHT = ')';
    private static final char SEP_ARG = ',';
    private static final char GET_VAR = ';';
    private static final char DOT_VAR = '.';
    private static final char EXP = 'e';
    private static final char DOUBLE = 'd';
    private static final char FLOAT = 'f';
    private static final char LONG = 'l';
    private static final char INTEGER = 'i';
    private static final char CHARACTER = 'c';
    private static final char SHORT = 's';
    private static final char BYTE = 'b';
    private static final char NB_INTERN_SP = '_';
    private static final String GET_INDEX = ";;";
    private static final String GET_CATCH_VAR = ";..";
    private static final String GET_LOC_VAR = ";.";
    private static final String GET_ATTRIBUTE = ";";
    private static final String GET_PARAM = ";.;";
    private static final String GET_FIELD = ";;;";
    private static final char ANNOT = '@';
    private static final char EXTERN_CLASS = FileResolver.SUPPLEMENT_CHAR;
    private static final String CLASS_CHOICE = "classchoice";
    private static final String INTERFACES = "interfaces";
    private static final String INTERN_BEAN = "intern";
    private static final String INSTANCE = "new";
    private static final String SUPER = "super";
    private static final String STATIC_ACCESS = "static";
    private static final String VAR_ARG = "vararg";
    private static final String FIRST_OPT = "firstopt";
    private static final String BOOLEAN = "bool";
    private static final String INSTANCEOF = "instanceof";
    private static final String VALUE_OF = "valueOf";
    private static final String VALUES = "values";
    private static final String THIS = "this";
    private static final String THAT = "that";
    private static final String CLASS = "class";
    private static final String LAMBDA = "lambda";
    private static final String NULL_REF_STRING = "null";
    private static final String TRUE_STRING = "true";
    private static final String FALSE_STRING = "false";

    private static final char MIN_ENCODE_DIGIT = '0';
    private static final char MAX_ENCODE_DIGIT = '9';
    private static final char MIN_ENCODE_LOW_LETTER = 'a';
    private static final char MAX_ENCODE_LOW_LETTER = 'f';
    private static final char MIN_ENCODE_UPP_LETTER = 'A';
    private static final char MAX_ENCODE_UPP_LETTER = 'F';

    private static final String ARR = "[";
    private static final String ANN_ARR = "{";

    private static final char NEG_BOOL_CHAR = '!';

    private static final char MULT_CHAR = '*';

    private static final char DIV_CHAR = '/';
 
    private static final char MOD_CHAR = '%';

    private static final char PLUS_CHAR = '+';

    private static final char MINUS_CHAR = '-';
 
    private static final char LOWER_CHAR = '<';

    private static final char GREATER_CHAR = '>';

    private static final char EQ_CHAR = '=';

    private static final char AND_CHAR = '&';

    private static final char OR_CHAR = '|';

    private ElResolver() {
    }

    public static Delimiters checkSyntaxDelimiters(String _string, Analyzable _conf, int _minIndex, char _begin, char _end) {
        Delimiters d_ = new Delimiters();
        d_.setBegin(_begin);
        d_.setEnd(_end);
        d_.setPartOfString(true);
        return commonCheck(_string, _conf, _minIndex, d_);
    }

    public static Delimiters checkSyntax(String _string, Analyzable _conf, int _elOffest) {
        return commonCheck(_string, _conf, _elOffest, new Delimiters());
    }
    static Delimiters commonCheck(String _string, Analyzable _conf, int _minIndex, Delimiters _d) {
        char begin_ = _d.getBegin();
        char end_ = _d.getEnd();
        boolean partOfString_ = _d.isPartOfString();

        Delimiters d_ = new Delimiters();
        NatTreeMap<Integer,Character> parsBrackets_;
        parsBrackets_ = new NatTreeMap<Integer,Character>();

        boolean constString_ = false;
        boolean foundSemiColumn_ = false;
        boolean constChar_ = false;
        boolean escapedMeta_ = false;
        boolean hatMethod_ = false;
        int unicode_ = 0;
        int len_ = _string.length();
        int i_ = _minIndex;
        while (i_ < len_) {
            if (!Character.isWhitespace(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        if (i_ >= len_) {
            d_.setBadOffset(i_);
            return d_;
        }
        boolean enabledMinus_ = true;
        i_ = _minIndex;
        int nbChars_ = 0;
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (constChar_) {
                IndexUnicodeEscape unic_ = new IndexUnicodeEscape();
                unic_.setIndex(i_);
                unic_.setEscape(escapedMeta_);
                unic_.setNbChars(nbChars_);
                unic_.setPart(true);
                unic_.setUnicode(unicode_);
                IndexUnicodeEscape res_ = processStrings(_string, len_, unic_, DELIMITER_CHAR);
                int index_ = res_.getIndex();
                if (index_ < 0) {
                    d_.setBadOffset(-index_);
                    return d_;
                }
                if (!res_.isPart()) {
                    d_.getDelStringsChars().add(i_);
                    constChar_ = false;
                    enabledMinus_ = true;
                    i_++;
                    if (i_ < len_) {
                        String nextPart_ = _string.substring(i_).trim();
                        if (nextPart_.charAt(0) == end_) {
                            continue;
                        }
                        if (nextPart_.charAt(0) == DELIMITER_CHAR) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (nextPart_.charAt(0) == DELIMITER_STRING) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (_conf.isAnnotAnalysis()) {
                            if (nextPart_.charAt(0) == ANN_ARR_LEFT) {
                                d_.setBadOffset(i_);
                                return d_;
                            }
                        } else {
                            if (nextPart_.charAt(0) == ARR_LEFT) {
                                d_.setBadOffset(i_);
                                return d_;
                            }
                        }
                        if (nextPart_.charAt(0) == PAR_LEFT) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (nextPart_.charAt(0) == DOT_VAR) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (StringList.isDollarWordChar(nextPart_.charAt(0))) {
                            String is_ = StringList.concat(String.valueOf(EXTERN_CLASS),INSTANCEOF);
                            if (procWordFirstChar(nextPart_, 0, is_)) {
                                continue;
                            }
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (nextPart_.charAt(0) == Templates.PREFIX_VAR_TYPE_CHAR) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                    }
                    continue;
                }
                i_ = index_;
                escapedMeta_ = res_.isEscape();
                nbChars_ = res_.getNbChars();
                unicode_ = res_.getUnicode();
                continue;
            }
            if (constString_) {
                IndexUnicodeEscape unic_ = new IndexUnicodeEscape();
                unic_.setIndex(i_);
                unic_.setEscape(escapedMeta_);
                unic_.setNbChars(nbChars_);
                unic_.setPart(true);
                unic_.setUnicode(unicode_);
                IndexUnicodeEscape res_ = processStrings(_string, len_, unic_, DELIMITER_STRING);
                int index_ = res_.getIndex();
                if (index_ < 0) {
                    d_.setBadOffset(-index_);
                    return d_;
                }
                if (!res_.isPart()) {
                    d_.getDelStringsChars().add(i_);
                    constString_ = false;
                    enabledMinus_ = true;
                    i_++;
                    if (i_ < len_) {
                        String nextPart_ = _string.substring(i_).trim();
                        if (nextPart_.charAt(0) == end_) {
                            continue;
                        }
                        if (nextPart_.charAt(0) == DELIMITER_CHAR) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (nextPart_.charAt(0) == DELIMITER_STRING) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (_conf.isAnnotAnalysis()) {
                            if (nextPart_.charAt(0) == ANN_ARR_LEFT) {
                                d_.setBadOffset(i_);
                                return d_;
                            }
                        } else {
                            if (nextPart_.charAt(0) == ARR_LEFT) {
                                d_.setBadOffset(i_);
                                return d_;
                            }
                        }
                        if (nextPart_.charAt(0) == PAR_LEFT) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (StringList.isDollarWordChar(nextPart_.charAt(0))) {
                            String is_ = StringList.concat(String.valueOf(EXTERN_CLASS),INSTANCEOF);
                            if (procWordFirstChar(nextPart_, 0, is_)) {
                                continue;
                            }
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (nextPart_.charAt(0) == Templates.PREFIX_VAR_TYPE_CHAR) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                    }
                    continue;
                }
                i_ = index_;
                escapedMeta_ = res_.isEscape();
                nbChars_ = res_.getNbChars();
                unicode_ = res_.getUnicode();
                continue;
            }
            if (curChar_ == ANNOT && _conf.isAnnotAnalysis()) {
                int j_ = i_ + 1;
                while (j_ < len_) {
                    char locChar_ = _string.charAt(j_);
                    if (StringList.isDollarWordChar(locChar_)) {
                        j_++;
                        continue;
                    }
                    if (locChar_ == DOT_VAR) {
                        j_++;
                        continue;
                    }
                    if (Character.isWhitespace(locChar_)) {
                        j_++;
                        continue;
                    }
                    hatMethod_ = false;
                    break;
                }
                i_ = j_;
                continue;
            }
            if (curChar_ == EXTERN_CLASS) {
                StringList parts_ = new StringList();
                StringBuilder part_ = new StringBuilder();
                BooleanList doubleDotted_ = new BooleanList();
                int k_ = i_;
                int lastDot_ = i_;
                while (k_ < len_) {
                    char locCar_ = _string.charAt(k_);
                    if (StringList.isDollarWordChar(locCar_)) {
                        part_.append(locCar_);
                        k_++;
                        continue;
                    }
                    if (Character.isWhitespace(locCar_)) {
                        part_.append(locCar_);
                        k_++;
                        continue;
                    }
                    if (locCar_ == DOT_VAR) {
                        lastDot_ = k_;
                        parts_.add(part_.toString());
                        part_.delete(0, part_.length());
                        if (k_ + 1 < len_ && _string.charAt(k_ + 1) == DOT_VAR) {
                            doubleDotted_.add(true);
                            k_++;
                        } else {
                            doubleDotted_.add(false);
                        }
                        k_++;
                        continue;
                    }
                    break;
                }
                StringBuilder allparts_ = new StringBuilder();
                int partLen_ = parts_.size();
                for (int i = 0; i < partLen_; i++) {
                    allparts_.append(parts_.get(i));
                    if (i + 1 < partLen_) {
                        allparts_.append(DOT_VAR);
                        if (doubleDotted_.get(i)) {
                            allparts_.append(DOT_VAR);
                        }
                    }
                }
                if (_conf.getClassBody(allparts_.toString()) != null) {
                    if (k_ >= len_) {
                        d_.setBadOffset(len_);
                        return d_;
                    }
                    d_.getDelKeyWordStatic().add(i_);
                    d_.getDelKeyWordStatic().add(lastDot_);
                    d_.getDelKeyWordStaticExtract().add(allparts_.toString());
                    i_ = lastDot_;
                    continue;
                }
                enabledMinus_ = true;
                hatMethod_ = true;
                if (i_ + 1 < len_) {
                    char nextChar_ = _string.charAt(i_ + 1);
                    if (_string.substring(i_ + 1).trim().indexOf(PAR_LEFT) == CustList.FIRST_INDEX) {
                        //cast
                        int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
                        int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
                        if (indexParRight_ < 0) {
                            d_.setBadOffset(len_);
                            return d_;
                        }
                        if (indexParRight_ + 1 >= len_) {
                            d_.setBadOffset(len_);
                            return d_;
                        }
                        enabledMinus_ = false;
                        hatMethod_ = false;
                        d_.getDelCast().add(i_);
                        d_.getDelCast().add(indexParRight_);
                        i_ = indexParRight_ + 1;
                        continue;
                    }
                    if (Character.isWhitespace(nextChar_)) {
                        d_.setBadOffset(i_+1);
                        return d_;
                    }
                    if (procWordFirstChar(_string, i_ + 1, VAR_ARG)) {
                        //vararg
                        int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
                        int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
                        if (indexParRight_ < 0) {
                            d_.setBadOffset(len_);
                            return d_;
                        }
                        hatMethod_ = false;
                        d_.getDelVararg().add(i_);
                        d_.getDelVararg().add(indexParRight_);
                        i_ = indexParRight_ + 1;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, CLASS)) {
                        int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
                        int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
                        if (indexParRight_ < 0) {
                            d_.setBadOffset(len_);
                            return d_;
                        }
                        hatMethod_ = false;
                        d_.getDelClass().add(i_);
                        d_.getDelClass().add(indexParRight_);
                        i_ = indexParRight_ + 1;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, INSTANCEOF)) {
                        int next_ = i_ + 1 + INSTANCEOF.length();
                        if (Character.isWhitespace(_string.charAt(next_))) {
                            //instanceof
                            if (i_ <= 0 || StringList.isDollarWordChar(_string.charAt(i_-1))) {
                                //error
                                d_.setBadOffset(i_-1);
                                return d_;
                            }
                            while (next_ < len_) {
                                char curLoc_ = _string.charAt(next_);
                                if (curLoc_ == ARR_LEFT) {
                                    next_++;
                                    continue;
                                }
                                if (StringList.isDollarWordChar(curLoc_)) {
                                    next_++;
                                    continue;
                                }
                                if (curLoc_ == DOT_VAR) {
                                    next_++;
                                    continue;
                                }
                                if (Character.isWhitespace(curLoc_)) {
                                    next_++;
                                    continue;
                                }
                                if (curLoc_ == Templates.PREFIX_VAR_TYPE_CHAR) {
                                    next_++;
                                    continue;
                                }
                                break;
                            }
                            if (next_ >= len_) {
                                d_.getAllowedOperatorsIndexes().add(i_);
                                d_.getDelInstanceof().add(i_);
                                d_.getDelInstanceof().add(next_);
                                i_ = next_;
                                hatMethod_ = false;
                                continue;
                            }
                            if (_string.charAt(next_) == LOWER_CHAR) {
                                int nbOpened_ = 1;
                                next_++;
                                while (next_ < len_) {
                                    char curLoc_ = _string.charAt(next_);
                                    if (curLoc_ == LOWER_CHAR) {
                                        nbOpened_++;
                                        next_++;
                                        continue;
                                    }
                                    if (curLoc_ == GREATER_CHAR) {
                                        nbOpened_--;
                                        next_++;
                                        if (nbOpened_ == 0 && !_string.substring(next_).trim().startsWith("..")) {
                                            break;
                                        }
                                        continue;
                                    }
                                    next_++;
                                    continue;
                                }
                                if (nbOpened_ > 0) {
                                    d_.setBadOffset(next_);
                                    return d_;
                                }
                            }
                            d_.getAllowedOperatorsIndexes().add(i_);
                            d_.getDelInstanceof().add(i_);
                            d_.getDelInstanceof().add(next_);
                            i_ = next_;
                            hatMethod_ = false;
                            continue;
                        }
                        d_.setBadOffset(next_);
                        return d_;
                    }
                    if (procWordFirstChar(_string, i_ + 1, INSTANCE)) {
                        int j_ = i_ + 1;
                        while (j_ < len_) {
                            if (_string.charAt(j_) == PAR_LEFT) {
                                hatMethod_ = false;
                                break;
                            }
                            j_++;
                        }
                        if (j_ >= len_) {
                            d_.setBadOffset(len_ - 1);
                            return d_;
                        }
                        i_ = j_;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, LAMBDA)) {
                        //lambda
                        int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
                        int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
                        if (indexParRight_ < 0) {
                            d_.setBadOffset(len_);
                            return d_;
                        }
                        hatMethod_ = false;
                        d_.getDelLambda().add(i_);
                        d_.getDelLambda().add(indexParRight_);
                        i_ = indexParRight_ + 1;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, STATIC_ACCESS)) {
                        int afterStatic_ = i_ + 1 + STATIC_ACCESS.length();
                        boolean foundHat_ = false;
                        while (afterStatic_ < len_) {
                            if (_string.charAt(afterStatic_) == PAR_LEFT) {
                                foundHat_ = true;
                                break;
                            }
                            if (!Character.isWhitespace(_string.charAt(afterStatic_))) {
                                d_.setBadOffset(afterStatic_);
                                return d_;
                            }
                            afterStatic_++;
                        }
                        if (!foundHat_) {
                            d_.setBadOffset(len_ - 1);
                            return d_;
                        }
                        if (afterStatic_ + 1 >= len_) {
                            d_.setBadOffset(afterStatic_);
                            return d_;
                        }
                        while (afterStatic_ < len_) {
                            if (_string.charAt(afterStatic_) == PAR_RIGHT) {
                                break;
                            }
                            afterStatic_++;
                        }
                        afterStatic_++;
                        if (afterStatic_ + 1 >= len_) {
                            d_.setBadOffset(afterStatic_);
                            return d_;
                        }
                        while (afterStatic_ < len_) {
                            if (!Character.isWhitespace(_string.charAt(afterStatic_))) {
                                if (_string.charAt(afterStatic_) == DOT_VAR) {
                                    d_.getDelKeyWordStatic().add(i_);
                                    d_.getDelKeyWordStatic().add(afterStatic_);
                                    d_.getDelKeyWordStaticExtract().add(EMPTY_STRING);
                                    hatMethod_ = false;
                                    i_ = afterStatic_;
                                    break;
                                }
                                d_.setBadOffset(afterStatic_);
                                return d_;
                            }
                            afterStatic_++;
                        }
                        if (afterStatic_ >= len_) {
                            d_.setBadOffset(afterStatic_);
                            return d_;
                        }
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, SUPER)) {
                        int afterSuper_ = i_ + 1 + SUPER.length();
                        boolean foundHat_ = false;
                        while (afterSuper_ < len_) {
                            if (_string.charAt(afterSuper_) == DOT_VAR) {
                                foundHat_ = true;
                                break;
                            }
                            if (!Character.isWhitespace(_string.charAt(afterSuper_))) {
                                if (_string.charAt(afterSuper_) != PAR_LEFT) {
                                    d_.setBadOffset(afterSuper_);
                                    return d_;
                                }
                                hatMethod_ = false;
                                break;
                            }
                            afterSuper_++;
                        }
                        if (afterSuper_ + 1 >= len_) {
                            d_.setBadOffset(afterSuper_);
                            return d_;
                        }
                        if (!foundHat_) {
                            i_ = afterSuper_;
                            continue;
                        }
                        afterSuper_++;
                        while (afterSuper_ < len_) {
                            if (Character.isWhitespace(_string.charAt(afterSuper_))) {
                                afterSuper_++;
                                continue;
                            }
                            if (!StringList.isDollarWordChar(_string.charAt(afterSuper_))) {
                                break;
                            }
                            afterSuper_++;
                        }
                        hatMethod_ = false;
                        if (afterSuper_ < len_) {
                            if (_string.charAt(afterSuper_) != PAR_LEFT) {
                                d_.getDelKeyWordSuper().add(i_);
                                d_.getDelKeyWordSuper().add(afterSuper_);
                            }
                        } else {
                            d_.getDelKeyWordSuper().add(i_);
                            d_.getDelKeyWordSuper().add(afterSuper_);
                        }
                        i_ = afterSuper_;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, CLASS_CHOICE)) {
                        int afterClassChoice_ = i_ + 1 + CLASS_CHOICE.length();
                        boolean foundHat_ = false;
                        while (afterClassChoice_ < len_) {
                            if (_string.charAt(afterClassChoice_) == PAR_LEFT) {
                                foundHat_ = true;
                                break;
                            }
                            if (!Character.isWhitespace(_string.charAt(afterClassChoice_))) {
                                d_.setBadOffset(afterClassChoice_);
                                return d_;
                            }
                            afterClassChoice_++;
                        }
                        if (!foundHat_) {
                            d_.setBadOffset(len_ - 1);
                            return d_;
                        }
                        if (afterClassChoice_ + 1 >= len_) {
                            d_.setBadOffset(afterClassChoice_);
                            return d_;
                        }
                        while (afterClassChoice_ < len_) {
                            if (_string.charAt(afterClassChoice_) == PAR_RIGHT) {
                                break;
                            }
                            afterClassChoice_++;
                        }
                        if (afterClassChoice_ + 1 >= len_) {
                            d_.setBadOffset(afterClassChoice_);
                            return d_;
                        }
                        afterClassChoice_++;
                        while (afterClassChoice_ < len_) {
                            char loc_ = _string.charAt(afterClassChoice_);
                            if (!Character.isWhitespace(loc_)) {
                                break;
                            }
                            afterClassChoice_++;
                        }
                        boolean pass_ = false;
                        while (afterClassChoice_ < len_) {
                            char loc_ = _string.charAt(afterClassChoice_);
                            if (!StringList.isDollarWordChar(loc_)) {
                                break;
                            }
                            pass_ = true;
                            afterClassChoice_++;
                        }
                        hatMethod_ = false;
                        if (!pass_) {
                            d_.setBadOffset(afterClassChoice_);
                            return d_;
                        }
                        if (afterClassChoice_ >= len_) {
                            //field
                            d_.getDelKeyWordClassChoice().add(i_);
                            d_.getDelKeyWordClassChoice().add(afterClassChoice_);
                            i_ = afterClassChoice_;
                            continue;
                        }
                        if (_string.charAt(afterClassChoice_) == PAR_LEFT) {
                            //fct
                            i_ = afterClassChoice_;
                            continue;
                        }
                        //field
                        d_.getDelKeyWordClassChoice().add(i_);
                        d_.getDelKeyWordClassChoice().add(afterClassChoice_);
                        i_ = afterClassChoice_;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, INTERFACES)) {
                        int afterClassChoice_ = i_ + 1 + INTERFACES.length();
                        boolean foundHat_ = false;
                        while (afterClassChoice_ < len_) {
                            if (_string.charAt(afterClassChoice_) == PAR_LEFT) {
                                foundHat_ = true;
                                break;
                            }
                            if (!Character.isWhitespace(_string.charAt(afterClassChoice_))) {
                                d_.setBadOffset(afterClassChoice_);
                                return d_;
                            }
                            afterClassChoice_++;
                        }
                        if (!foundHat_) {
                            d_.setBadOffset(len_ - 1);
                            return d_;
                        }
                        if (afterClassChoice_ + 1 >= len_) {
                            d_.setBadOffset(afterClassChoice_);
                            return d_;
                        }
                        while (afterClassChoice_ < len_) {
                            if (_string.charAt(afterClassChoice_) == PAR_RIGHT) {
                                break;
                            }
                            afterClassChoice_++;
                        }
                        if (afterClassChoice_ + 1 >= len_) {
                            d_.setBadOffset(afterClassChoice_);
                            return d_;
                        }
                        afterClassChoice_++;
                        while (afterClassChoice_ < len_) {
                            char loc_ = _string.charAt(afterClassChoice_);
                            if (!Character.isWhitespace(loc_)) {
                                break;
                            }
                            afterClassChoice_++;
                        }
                        if (afterClassChoice_ >= len_) {
                            d_.setBadOffset(afterClassChoice_);
                            return d_;
                        }
                        char loc_ = _string.charAt(afterClassChoice_);
                        if (loc_ != PAR_LEFT) {
                            d_.setBadOffset(afterClassChoice_);
                            return d_;
                        }
                        hatMethod_ = false;
                        i_ = afterClassChoice_;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, THAT)) {
                        int afterSuper_ = i_ + 1 + THAT.length();
                        boolean foundHat_ = false;
                        while (afterSuper_ < len_) {
                            if (_string.charAt(afterSuper_) == DOT_VAR) {
                                foundHat_ = true;
                                break;
                            }
                            if (!Character.isWhitespace(_string.charAt(afterSuper_))) {
                                //_string.charAt(afterSuper_) != EXTERN_CLASS && !foundHat_
                                break;
                            }
                            afterSuper_++;
                        }
                        if (!foundHat_) {
                            d_.setBadOffset(afterSuper_);
                            return d_;
                        }
                        if (afterSuper_ + 1 >= len_) {
                            d_.setBadOffset(afterSuper_);
                            return d_;
                        }
                        afterSuper_++;
                        while (afterSuper_ < len_) {
                            if (Character.isWhitespace(_string.charAt(afterSuper_))) {
                                afterSuper_++;
                                continue;
                            }
                            if (!StringList.isDollarWordChar(_string.charAt(afterSuper_))) {
                                hatMethod_ = false;
                                break;
                            }
                            afterSuper_++;
                        }
                        i_ = afterSuper_;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, THIS)) {
                        int afterSuper_ = i_ + 1 + THIS.length();
                        while (afterSuper_ < len_) {
                            if (!Character.isWhitespace(_string.charAt(afterSuper_))) {
                                //_string.charAt(afterSuper_) != EXTERN_CLASS && !foundHat_
                                break;
                            }
                            afterSuper_++;
                        }
                        hatMethod_ = false;
                        i_ = afterSuper_;
                        continue;
                    }
                    boolean foundValue_ = false;
                    for (String s: StringList.wrapStringArray(TRUE_STRING,FALSE_STRING,NULL_REF_STRING)) {
                        if (procWordFirstChar(_string, i_ + 1, s)) {
                            int afterSuper_ = i_ + 1 + s.length();
                            while (afterSuper_ < len_) {
                                if (!Character.isWhitespace(_string.charAt(afterSuper_))) {
                                    break;
                                }
                                afterSuper_++;
                            }
                            hatMethod_ = false;
                            foundValue_ = true;
                            i_ = afterSuper_;
                            break;
                        }
                    }
                    if (foundValue_) {
                        continue;
                    }
                    for (String s: StringList.wrapStringArray(FIRST_OPT,BOOLEAN,VALUE_OF,VALUES)) {
                        if (procWordFirstChar(_string, i_ + 1, s)) {
                            int index_ = processPredefinedMethod(_string, i_, s, len_);
                            if (index_ < 0) {
                                d_.setBadOffset(-index_);
                                return d_;
                            }
                            hatMethod_ = false;
                            i_ = index_;
                            break;
                        }
                    }
                    if (!hatMethod_) {
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, INTERN_BEAN)) {
                        int index_ = processPredefinedMethod(_string, i_, INTERN_BEAN, len_);
                        if (index_ >= 0) {
                            hatMethod_ = false;
                            i_ = index_;
                            continue;
                        }
                        if (_conf.isInternGlobal()) {
                            int afterSuper_ = i_ + 1 + INTERN_BEAN.length();
                            String trim_ = _string.substring(afterSuper_).trim();
                            if (trim_.startsWith(String.valueOf(DOT_VAR))) {
                                while (true) {
                                    if (_string.charAt(afterSuper_) == DOT_VAR) {
                                        //_string.charAt(afterSuper_) != EXTERN_CLASS && !foundHat_
                                        break;
                                    }
                                    afterSuper_++;
                                }
                                hatMethod_ = false;
                                i_ = afterSuper_;
                                continue;
                            }
                        }
                    }
                }
                d_.setBadOffset(i_);
                return d_;
            }
            if (StringList.isWordChar(curChar_)) {
                enabledMinus_ = true;
                if (isNumber(i_, len_, _string)) {
                    NumberInfosOutput res_ = processNb(i_, len_, _string, false);
                    int nextIndex_ = res_.getNextIndex();
                    if (nextIndex_ < 0) {
                        d_.setBadOffset(-nextIndex_);
                        return d_;
                    }
                    d_.getNbInfos().add(res_.getInfos());
                    d_.getDelNumbers().add(i_);
                    d_.getDelNumbers().add(nextIndex_);
                    i_ = nextIndex_;
                    continue;
                }
                int beginWord_ = i_;
                boolean keyWord_ = false;
                while (i_ < len_) {
                    char locChar_ = _string.charAt(i_);
                    if (!StringList.isDollarWordChar(locChar_)) {
                        if (locChar_ != Templates.PREFIX_VAR_TYPE_CHAR) {
                            if (locChar_ == DELIMITER_CHAR) {
                                d_.setBadOffset(i_);
                                return d_;
                            }
                            if (locChar_ == DELIMITER_STRING) {
                                d_.setBadOffset(i_);
                                return d_;
                            }
                            int bk_ = i_;
                            boolean spaces_ = false;
                            while (Character.isWhitespace(locChar_)) {
                                i_++;
                                spaces_ = true;
                                locChar_ = _string.charAt(i_);
                            }
                            String is_ = StringList.concat(String.valueOf(EXTERN_CLASS),INSTANCEOF);
                            if (procWordFirstChar(_string, i_, is_)) {
                                keyWord_ = true;
                            } else {
                                i_=bk_;
                                if (spaces_) {
                                    boolean keep_ = false;
                                    if (StringList.isDollarWordChar(locChar_)) {
                                        keep_ = true;
                                    } else if (locChar_ == Templates.PREFIX_VAR_TYPE_CHAR) {
                                        keep_ = true;
                                    }
                                    if (keep_) {
                                        i_++;
                                        continue;
                                    }
                                }
                            }
                            break;
                        }
                    }
                    i_++;
                }
                if (keyWord_) {
                    continue;
                }
                ConstType type_ = ConstType.NOTHING;
                boolean tolerateDot_ = false;
                VariableInfo info_ = new VariableInfo();
                if (i_ < len_ && _string.charAt(i_) == GET_VAR) {
                    if (i_ + 1 < len_ && _string.charAt(i_ + 1) == GET_VAR) {
                        if (i_ + 2 < len_ && _string.charAt(i_ + 2) == GET_VAR) {
                            type_ = ConstType.CUST_FIELD;
                            info_.setKind(type_);
                            info_.setFirstChar(beginWord_);
                            info_.setLastChar(i_+GET_FIELD.length());
                            info_.setName(_string.substring(beginWord_, i_));
                            i_ += GET_FIELD.length();
                            d_.getVariables().add(info_);
                            tolerateDot_ = true;
                        } else if (i_ + 2 < len_ && _string.charAt(i_ + 2) == DOT_VAR) {
                            d_.setBadOffset(i_+2);
                            return d_;
                        } else {
                            type_ = ConstType.LOOP_INDEX;
                            info_.setKind(type_);
                            info_.setFirstChar(beginWord_);
                            info_.setLastChar(i_+GET_INDEX.length());
                            info_.setName(_string.substring(beginWord_, i_));
                            i_ += GET_INDEX.length();
                            d_.getVariables().add(info_);
                        }
                    } else if (i_ + 1 < len_ && _string.charAt(i_ + 1) == DOT_VAR) {
                        if (i_ + 2 < len_ && _string.charAt(i_ + 2) == DOT_VAR) {
                            type_ = ConstType.CATCH_VAR;
                            info_.setKind(type_);
                            info_.setFirstChar(beginWord_);
                            info_.setLastChar(i_+GET_CATCH_VAR.length());
                            info_.setName(_string.substring(beginWord_, i_));
                            i_ += GET_CATCH_VAR.length();
                            d_.getVariables().add(info_);
                        } else if (i_ + 2 < len_ && _string.charAt(i_ + 2) == GET_VAR) {
                            type_ = ConstType.PARAM;
                            info_.setKind(type_);
                            info_.setFirstChar(beginWord_);
                            info_.setLastChar(i_+GET_PARAM.length());
                            info_.setName(_string.substring(beginWord_, i_));
                            i_ += GET_PARAM.length();
                            d_.getVariables().add(info_);
                        } else {
                            type_ = ConstType.LOC_VAR;
                            info_.setKind(type_);
                            info_.setFirstChar(beginWord_);
                            info_.setLastChar(i_+GET_LOC_VAR.length());
                            info_.setName(_string.substring(beginWord_, i_));
                            i_ += GET_LOC_VAR.length();
                            d_.getVariables().add(info_);
                        }
                    } else {
                        type_ = ConstType.LOOP_VAR;
                        info_.setKind(type_);
                        info_.setFirstChar(beginWord_);
                        info_.setLastChar(i_ + GET_ATTRIBUTE.length());
                        info_.setName(_string.substring(beginWord_, i_));
                        i_ += GET_ATTRIBUTE.length();
                        d_.getVariables().add(info_);
                    }
                } else if (i_ >= len_ || _string.substring(i_).trim().charAt(0) != PAR_LEFT) {
                    tolerateDot_ = true;
                    type_ = ConstType.WORD;
                    info_.setKind(type_);
                    info_.setFirstChar(beginWord_);
                    info_.setLastChar(i_);
                    String word_ = _string.substring(beginWord_, i_);
                    String dot_ = String.valueOf(DOT_VAR);
                    String look_ = _conf.getLookLocalClass();
                    int prChar_ = beginWord_ - 1;
                    while (prChar_ >= 0) {
                        char pr_ = _string.charAt(prChar_);
                        if (Character.isWhitespace(pr_)) {
                            prChar_--;
                            continue;
                        }
                        break;
                    }
                    int prIndex_ = -1;
                    if (!d_.getVariables().isEmpty()) {
                        VariableInfo vi_ = d_.getVariables().last();
                        if (vi_.getKind() == ConstType.WORD) {
                            Numbers<Integer> indexes_ = d_.getAllowedOperatorsIndexes();
                            if (!indexes_.isEmpty()) {
                                int lastOp_ = indexes_.last();
                                if (lastOp_ == prChar_ && _string.charAt(lastOp_) == DOT_VAR) {
                                    prIndex_ = prChar_;
                                }
                            }
                        } else {
                            prIndex_ = prChar_ + 1;
                        }
                    }
                    if (!d_.getVariables().isEmpty() && d_.getVariables().last().getLastChar() == prIndex_) {
                        info_.setName(word_);
                        d_.getVariables().add(info_);
                    } else if (!look_.isEmpty()) {
                        info_.setName(word_);
                        d_.getVariables().add(info_);
                    } else {
                        //if the field exist then look for an imported type (without templates) then a complete type
                        String prev_ = _string.substring(0, beginWord_).trim();
                        if (prev_.endsWith(StringList.concat(dot_,dot_))) {
                            int j_ = i_ + 1;
                            int k_ = j_;
                            StringList parts_ = new StringList();
                            BooleanList doubleDotted_ = new BooleanList();
                            StringBuilder part_ = new StringBuilder();
                            boolean dotSeen_ = false;
                            while (j_ < len_) {
                                char locChar_ = _string.charAt(j_);
                                if (StringList.isDollarWordChar(locChar_)) {
                                    part_.append(locChar_);
                                    j_++;
                                    continue;
                                }
                                if (Character.isWhitespace(locChar_)) {
                                    part_.append(locChar_);
                                    j_++;
                                    continue;
                                }
                                if (locChar_ == DOT_VAR) {
                                    dotSeen_ = true;
                                    k_ = j_;
                                    parts_.add(part_.toString());
                                    part_.delete(0, part_.length());
                                    if (j_ + 1 < len_ && _string.charAt(j_ + 1) == DOT_VAR) {
                                        doubleDotted_.add(true);
                                        j_++;
                                    } else {
                                        doubleDotted_.add(false);
                                    }
                                    j_++;
                                    continue;
                                }
                                break;
                            }
                            StringBuilder allparts_ = new StringBuilder(StringList.concat(dot_,dot_));
                            allparts_.append(word_);
                            if (!parts_.isEmpty()) {
                                allparts_.append(DOT_VAR);
                            }
                            int partLen_ = parts_.size();
                            for (int i = 0; i < partLen_; i++) {
                                allparts_.append(parts_.get(i));
                                if (i + 1 < partLen_) {
                                    allparts_.append(DOT_VAR);
                                    if (doubleDotted_.get(i)) {
                                        allparts_.append(DOT_VAR);
                                    }
                                }
                            }
                            Block bl_ = _conf.getCurrentBlock();
                            if (bl_ != null) {
                                AccessingImportingBlock r_ = bl_.getImporting();
                                if (r_ instanceof RootBlock) {
                                    boolean found_ = false;
                                    while (r_ != null) {
                                        String typeRes_ = _conf.lookupImportsIndirect(allparts_.toString(), r_);
                                        if (!typeRes_.isEmpty()) {
                                            found_ = true;
                                            d_.getDelKeyWordStatic().add(beginWord_);
                                            int next_;
                                            if (dotSeen_) {
                                                next_ = k_;
                                            } else {
                                                next_ = i_;
                                            }
                                            d_.getDelKeyWordStatic().add(next_);
                                            d_.getDelKeyWordStaticExtract().add(typeRes_);
                                            if (dotSeen_) {
                                                i_ = k_;
                                            }
                                            break;
                                        }
                                        r_ = ((RootBlock)r_).getParentType();
                                    }
                                    if (found_) {
                                        continue;
                                    }
                                    info_.setName(word_);
                                    d_.getVariables().add(info_);
                                    continue;
                                }
                                String typeRes_ = _conf.lookupImportsIndirect(allparts_.toString(), r_);
                                if (!typeRes_.isEmpty()) {
                                    d_.getDelKeyWordStatic().add(beginWord_);
                                    int next_;
                                    if (dotSeen_) {
                                        next_ = k_;
                                    } else {
                                        next_ = i_;
                                    }
                                    d_.getDelKeyWordStatic().add(next_);
                                    d_.getDelKeyWordStaticExtract().add(typeRes_);
                                    if (dotSeen_) {
                                        i_ = k_;
                                    }
                                } else {
                                    info_.setName(word_);
                                    d_.getVariables().add(info_);
                                }
                            } else {
                                info_.setName(word_);
                                d_.getVariables().add(info_);
                            }
                        } else if (!prev_.endsWith(dot_) && i_ < len_ &&_string.charAt(i_) == DOT_VAR) {
                            String glClass_ = _conf.getGlobalClass();
                            if (glClass_ != null) {
                                int j_ = i_ + 1;
                                int k_ = j_;
                                Numbers<Integer> indexes_ = new Numbers<Integer>();
                                StringList parts_ = new StringList();
                                StringList partsFields_ = new StringList();
                                Numbers<Integer> begins_ = new Numbers<Integer>();
                                int fChar_ = -1;
                                BooleanList doubleDotted_ = new BooleanList();
                                StringBuilder part_ = new StringBuilder();
                                boolean foundThis_ = false;
                                boolean addLast_ = true;
                                boolean field_ = true;
                                while (j_ < len_) {
                                    char locChar_ = _string.charAt(j_);
                                    if (StringList.isDollarWordChar(locChar_)) {
                                        if (fChar_ == -1) {
                                            fChar_ = j_;
                                        }
                                        part_.append(locChar_);
                                        j_++;
                                        continue;
                                    }
                                    if (Character.isWhitespace(locChar_)) {
                                        part_.append(locChar_);
                                        j_++;
                                        continue;
                                    }
                                    if (StringList.quickEq(prefix(THIS), part_.toString().trim())) {
                                        addLast_ = false;
                                        foundThis_ = true;
                                        break;
                                    }
                                    if (StringList.quickEq(prefix(SUPER), part_.toString().trim())) {
                                        addLast_ = false;
                                        break;
                                    }
                                    if (StringList.quickEq(prefix(THAT), part_.toString().trim())) {
                                        addLast_ = false;
                                        break;
                                    }
                                    if (StringList.quickEq(prefix(CLASS_CHOICE), part_.toString().trim())) {
                                        addLast_ = false;
                                        break;
                                    }
                                    if (locChar_ == PAR_LEFT) {
                                        addLast_ = false;
                                        break;
                                    }
                                    if (locChar_ == DOT_VAR) {
                                        k_ = j_;
                                        indexes_.add(j_);
                                        parts_.add(part_.toString());
                                        partsFields_.add(part_.toString());
                                        begins_.add(fChar_);
                                        part_.delete(0, part_.length());
                                        fChar_ = -1;
                                        if (j_ + 1 < len_ && _string.charAt(j_ + 1) == DOT_VAR) {
                                            doubleDotted_.add(true);
                                            j_++;
                                        } else {
                                            doubleDotted_.add(false);
                                        }
                                        j_++;
                                        continue;
                                    }
                                    break;
                                }
                                if (addLast_ && !begins_.containsObj(-1) && fChar_ > -1) {
                                    partsFields_.add(part_.toString());
                                    begins_.add(fChar_);
                                }
                                if (!foundThis_) {
                                    ClassArgumentMatching clArg_ = new ClassArgumentMatching(glClass_);
                                    FieldResult fr_ = OperationNode.resolveDeclaredCustField(_conf, _conf.isStaticContext(), clArg_, true, true, word_, _conf.getCurrentBlock() != null);
                                    if (fr_.getStatus() != SearchingMemberStatus.UNIQ) {
                                        field_ = false;
                                    } else {
                                        String o_ = fr_.getId().getType();
                                        for (String p: partsFields_) {
                                            ClassArgumentMatching out_ = new ClassArgumentMatching(o_);
                                            FieldResult n_ = OperationNode.resolveDeclaredCustField(_conf, false, out_, true, true, p.trim(), false);
                                            if (n_.getStatus() != SearchingMemberStatus.UNIQ) {
                                                field_ = false;
                                                break;
                                            }
                                            o_ = n_.getId().getType();
                                        }
                                    }
                                } else {
                                    field_ = false;
                                }
                                if (field_) {
                                    info_.setName(word_);
                                    d_.getVariables().add(info_);
                                    int lenFields_ = begins_.size();
                                    for (int i = 0; i < lenFields_; i++) {
                                        VariableInfo infoLoc_ = new VariableInfo();
                                        infoLoc_.setKind(type_);
                                        infoLoc_.setFirstChar(begins_.get(i));
                                        if (indexes_.isValidIndex(i)) {
                                            infoLoc_.setLastChar(indexes_.get(i));
                                        } else {
                                            infoLoc_.setLastChar(j_);
                                        }
                                        infoLoc_.setName(partsFields_.get(i).trim());
                                        d_.getVariables().add(infoLoc_);
                                    }
                                    d_.getAllowedOperatorsIndexes().add(i_);
                                    d_.getAllowedOperatorsIndexes().addAllElts(indexes_);
                                } else {
                                    StringBuilder allparts_ = new StringBuilder(word_);
                                    if (!parts_.isEmpty()) {
                                        allparts_.append(DOT_VAR);
                                    }
                                    int partLen_ = parts_.size();
                                    for (int i = 0; i < partLen_; i++) {
                                        allparts_.append(parts_.get(i));
                                        if (i + 1 < partLen_) {
                                            allparts_.append(DOT_VAR);
                                            if (doubleDotted_.get(i)) {
                                                allparts_.append(DOT_VAR);
                                            }
                                        }
                                    }
                                    String id_ = allparts_.toString();
                                    if (id_.indexOf(StringList.concat(dot_,dot_)) == -1) {
                                        StringList candidates_ = new StringList();
                                        int idLen_ = id_.length();
                                        for (int i = 0; i < idLen_; i++) {
                                            char sep_ = id_.charAt(i);
                                            if (sep_ == DOT_VAR) {
                                                candidates_.add(id_.substring(0, i));
                                            }
                                        }
                                        boolean found_ = false;
                                        int index_ = 0;
                                        for (String c: candidates_) {
                                            if (_conf.getClassBody(c) != null) {
                                                int n_ = indexes_.get(index_);
                                                d_.getDelKeyWordStatic().add(beginWord_);
                                                d_.getDelKeyWordStatic().add(n_);
                                                d_.getDelKeyWordStaticExtract().add(c);
                                                i_ = n_;
                                                found_ = true;
                                                break;
                                            }
                                            index_++;
                                        }
                                        if (found_) {
                                            continue;
                                        }
                                    }
                                    if (_conf.getClassBody(id_) != null) {
                                        d_.getDelKeyWordStatic().add(beginWord_);
                                        d_.getDelKeyWordStatic().add(k_);
                                        d_.getDelKeyWordStaticExtract().add(allparts_.toString());
                                        i_ = k_;
                                    } else {
                                        Block bl_ = _conf.getCurrentBlock();
                                        if (bl_ != null) {
                                            AccessingImportingBlock r_ = bl_.getImporting();
                                            String typeRes_ = _conf.lookupImportsIndirect(id_, r_);
                                            if (!typeRes_.isEmpty()) {
                                                d_.getDelKeyWordStatic().add(beginWord_);
                                                if (id_.contains(StringList.concat(dot_,dot_))) {
                                                    i_ = Math.max(i_, k_);
                                                }
                                                d_.getDelKeyWordStatic().add(i_);
                                                d_.getDelKeyWordStaticExtract().add(typeRes_);
                                            } else {
                                                info_.setName(word_);
                                                d_.getVariables().add(info_);
                                            }
                                        } else {
                                            info_.setName(word_);
                                            d_.getVariables().add(info_);
                                        }
                                    }
                                }
                            } else {
                                int j_ = i_ + 1;
                                int k_ = j_;
                                StringList parts_ = new StringList();
                                BooleanList doubleDotted_ = new BooleanList();
                                StringBuilder part_ = new StringBuilder();
                                while (j_ < len_) {
                                    char locChar_ = _string.charAt(j_);
                                    if (StringList.isDollarWordChar(locChar_)) {
                                        part_.append(locChar_);
                                        j_++;
                                        continue;
                                    }
                                    if (Character.isWhitespace(locChar_)) {
                                        part_.append(locChar_);
                                        j_++;
                                        continue;
                                    }
                                    if (locChar_ == DOT_VAR) {
                                        k_ = j_;
                                        parts_.add(part_.toString());
                                        part_.delete(0, part_.length());
                                        if (j_ + 1 < len_ && _string.charAt(j_ + 1) == DOT_VAR) {
                                            doubleDotted_.add(true);
                                            j_++;
                                        } else {
                                            doubleDotted_.add(false);
                                        }
                                        j_++;
                                        continue;
                                    }
                                    break;
                                }
                                StringBuilder allparts_ = new StringBuilder(word_);
                                if (!parts_.isEmpty()) {
                                    allparts_.append(DOT_VAR);
                                }
                                int partLen_ = parts_.size();
                                for (int i = 0; i < partLen_; i++) {
                                    allparts_.append(parts_.get(i));
                                    if (i + 1 < partLen_) {
                                        allparts_.append(DOT_VAR);
                                        if (doubleDotted_.get(i)) {
                                            allparts_.append(DOT_VAR);
                                        }
                                    }
                                }
                                if (_conf.getClassBody(allparts_.toString()) != null) {
                                    d_.getDelKeyWordStatic().add(beginWord_);
                                    d_.getDelKeyWordStatic().add(k_);
                                    d_.getDelKeyWordStaticExtract().add(allparts_.toString());
                                    i_ = k_;
                                } else {
                                    info_.setName(word_);
                                    d_.getVariables().add(info_);
                                }
                            }
                        } else {
                            info_.setName(word_);
                            d_.getVariables().add(info_);
                        }
                    }
                }
                String nextPart_ = _string.substring(i_).trim();
                if (!tolerateDot_ && !nextPart_.isEmpty() && (nextPart_.charAt(0) == DOT_VAR || nextPart_.charAt(0) == GET_VAR)) {
                    d_.setBadOffset(i_);
                    return d_;
                }
                if (_conf.isAnnotAnalysis()) {
                    if (nextPart_.isEmpty()) {
                        continue;
                    }
                    char nextChar_ = nextPart_.charAt(0);
                    if (nextChar_ == ANN_ARR_RIGHT) {
                        continue;
                    }
                }
                if (!isCorrectNbEndWord(nextPart_, end_)) {
                    d_.setBadOffset(i_+1);
                    return d_;
                }
                continue;
            }
            if (curChar_ == DOT_VAR) {
                if (i_ + 1 < len_ && _string.charAt(i_ + 1) == DOT_VAR) {
                    i_++;
                    i_++;
                    continue;
                }
                if (isNumber(i_ + 1, len_, _string)) {
                    enabledMinus_ = true;
                    NumberInfosOutput res_ = processNb(i_ + 1, len_, _string, true);
                    int nextIndex_ = res_.getNextIndex();
                    if (nextIndex_ < 0) {
                        d_.setBadOffset(-nextIndex_);
                        return d_;
                    }
                    d_.getNbInfos().add(res_.getInfos());
                    d_.getDelNumbers().add(i_);
                    d_.getDelNumbers().add(nextIndex_);
                    i_ = nextIndex_;
                    continue;
                }
                if (i_ + 1 < len_ && Character.isWhitespace(_string.charAt(i_ + 1))) {
                    String nextPart_ = _string.substring(i_ + 1).trim();
                    int lenLoc_ = nextPart_.length();
                    if (isNumber(0, lenLoc_, nextPart_)) {
                        d_.setBadOffset(-i_-1);
                        return d_;
                    }
                }
                if (parsBrackets_.isEmpty()) {
                    if (i_ + 1 >= len_) {
                        if (!foundSemiColumn_) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                    }
                }
            }
            if (curChar_ == ESCAPE_META_CHAR) {
                d_.setBadOffset(i_);
                return d_;
            }
            if (curChar_ == DELIMITER_CHAR) {
                constChar_ = true;
                nbChars_ = 0;
                d_.getDelStringsChars().add(i_);
            }
            if (curChar_ == DELIMITER_STRING) {
                constString_ = true;
                d_.getDelStringsChars().add(i_);
            }
            if (curChar_ == GET_VAR && parsBrackets_.isEmpty()) {
                foundSemiColumn_ = true;
            } 
            if (curChar_ == NEG_BOOL_CHAR || curChar_ == LOWER_CHAR || curChar_ == GREATER_CHAR  || curChar_ == EQ_CHAR) {
                int j_ = i_ + 1;
                boolean exist_ = false;
                while (j_ < len_) {
                    if (Character.isWhitespace(_string.charAt(j_))) {
                        exist_ = true;
                        j_++;
                        continue;
                    }
                    if (_string.charAt(j_) == EQ_CHAR && exist_) {
                        d_.setBadOffset(i_+1);
                        return d_;
                    }
                    break;
                }
            }
            if (curChar_ == PAR_LEFT) {
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == PAR_RIGHT) {
                if (parsBrackets_.isEmpty()) {
                    d_.setBadOffset(i_);
                    return d_;
                }
                if (parsBrackets_.lastValue() != PAR_LEFT) {
                    d_.setBadOffset(i_);
                    return d_;
                }
                parsBrackets_.removeKey(parsBrackets_.lastKey());
            }
            if (_conf.isAnnotAnalysis()) {
                if (curChar_ == ANN_ARR_LEFT) {
                    parsBrackets_.put(i_, curChar_);
                }
                if (curChar_ == ANN_ARR_RIGHT) {
                    if (parsBrackets_.isEmpty()) {
                        d_.setBadOffset(i_);
                        return d_;
                    }
                    if (parsBrackets_.lastValue() != ANN_ARR_LEFT) {
                        d_.setBadOffset(i_);
                        return d_;
                    }
                    parsBrackets_.removeKey(parsBrackets_.lastKey());
                }
            } else {
                if (curChar_ == ARR_LEFT) {
                    parsBrackets_.put(i_, curChar_);
                }
                if (curChar_ == ARR_RIGHT) {
                    if (parsBrackets_.isEmpty()) {
                        d_.setBadOffset(i_);
                        return d_;
                    }
                    if (parsBrackets_.lastValue() != ARR_LEFT) {
                        d_.setBadOffset(i_);
                        return d_;
                    }
                    parsBrackets_.removeKey(parsBrackets_.lastKey());
                }
            }
            if (curChar_ == SEP_ARG) {
                if (parsBrackets_.isEmpty() && !_conf.isMerged()) {
                    d_.setBadOffset(i_);
                    return d_;
                }
            }
            boolean pureBinaryOp_ = false;
            if (curChar_ == PLUS_CHAR && _conf.getOptions().applyEqPlus()) {
                pureBinaryOp_ = true;
            }
            if ((curChar_ == PLUS_CHAR || curChar_ == MINUS_CHAR) && applyMultipleAffectations(_conf)) {
                pureBinaryOp_ = false;
            }
            if (curChar_ == MULT_CHAR) {
                pureBinaryOp_ = true;
            }
            if (curChar_ == MOD_CHAR) {
                pureBinaryOp_ = true;
            }
            if (curChar_ == DIV_CHAR) {
                pureBinaryOp_ = true;
            }
            boolean plusMinus_ = false;
            if (curChar_ == MINUS_CHAR || curChar_ == PLUS_CHAR) {
                if (curChar_ == MINUS_CHAR || !_conf.getOptions().applyEqPlus()) {
                    plusMinus_ = true;
                }
            }
            if (pureBinaryOp_) {
                enabledMinus_ = false;
            } else if (!Character.isWhitespace(curChar_) && !plusMinus_){
                enabledMinus_ = true;
            }
            if (!enabledMinus_ && plusMinus_) {
                i_++;
                continue;
            }
            boolean idOp_ = false;
            if (curChar_ == AND_CHAR) {
                idOp_ = true;
            }
            if (curChar_ == OR_CHAR) {
                idOp_ = true;
            }
            if (curChar_ == LOWER_CHAR) {
                idOp_ = true;
            }
            if (curChar_ == GREATER_CHAR) {
                idOp_ = true;
            }
            if (curChar_ == EQ_CHAR) {
                idOp_ = true;
            }
            if (curChar_ == NEG_BOOL_CHAR) {
                idOp_ = true;
            }
            if (curChar_ == PLUS_CHAR) {
                idOp_ = true;
            }
            if (curChar_ == MINUS_CHAR) {
                idOp_ = true;
            }
            if (curChar_ == MULT_CHAR) {
                idOp_ = true;
            }
            if (curChar_ == DIV_CHAR) {
                idOp_ = true;
            }
            if (curChar_ == MOD_CHAR) {
                idOp_ = true;
            }
            if (_conf.isAnnotAnalysis()) {
                if (curChar_ == ANN_ARR_LEFT) {
                    idOp_ = true;
                }
                if (curChar_ == ANN_ARR_RIGHT) {
                    idOp_ = true;
                }
            } else {
                if (curChar_ == ARR_LEFT) {
                    idOp_ = true;
                }
                if (curChar_ == ARR_RIGHT) {
                    idOp_ = true;
                }
            }
            if (curChar_ == PAR_LEFT) {
                idOp_ = true;
            }
            if (curChar_ == PAR_RIGHT) {
                idOp_ = true;
            }
            if (curChar_ == SEP_ARG) {
                idOp_ = true;
            }
            if (curChar_ == DOT_VAR) {
                idOp_ = true;
            }
            if (idOp_) {
                d_.getAllowedOperatorsIndexes().add(i_);
            }
            if (plusMinus_) {
                enabledMinus_ = false;
            }
            if (partOfString_ && curChar_ == end_) {
                partOfString_ = false;
                break;
            }
            if (partOfString_ && curChar_ == begin_) {
                d_.setBadOffset(i_);
                return d_;
            }
            if (_conf.isRootAffect() || applyMultipleAffectations(_conf)) {
                boolean compound_ = false;
                if (curChar_ == PLUS_CHAR) {
                    compound_ = true;
                } else if (curChar_ == MINUS_CHAR) {
                    compound_ = true;
                } else if (curChar_ == MULT_CHAR) {
                    compound_ = true;
                } else if (curChar_ == DIV_CHAR) {
                    compound_ = true;
                } else if (curChar_ == MOD_CHAR) {
                    compound_ = true;
                } else if (curChar_ == AND_CHAR) {
                    compound_ = true;
                } else if (curChar_ == OR_CHAR) {
                    compound_ = true;
                } else if (curChar_ == LOWER_CHAR) {
                    compound_ = true;
                } else if (curChar_ == GREATER_CHAR) {
                    compound_ = true;
                } else if (curChar_ == NEG_BOOL_CHAR) {
                    compound_ = true;
                } else if (curChar_ == EQ_CHAR && applyMultipleAffectations(_conf)) {
                    compound_ = true;
                }
                if (compound_) {
                    if (i_ + 1 < len_) {
                        char next_ = _string.charAt(i_ + 1);
                        if (next_ == EQ_CHAR) {
                            i_++;
                        }
                        if (curChar_ == PLUS_CHAR || curChar_ == MINUS_CHAR) {
                            if (curChar_ == next_) {
                                i_++;
                                enabledMinus_ = true;
                            }
                        }
                    }
                } else if (curChar_ == EQ_CHAR && _conf.getOptions().applyEqPlus()) {
                    if (i_ + 1 < len_) {
                        char next_ = _string.charAt(i_ + 1);
                        if (next_ == PLUS_CHAR) {
                            i_++;
                        }
                    }
                }
            }
            i_++;
        }
        if (hatMethod_) {
            d_.setBadOffset(i_);
            return d_;
        }
        if (constString_) {
            d_.setBadOffset(i_);
            return d_;
        }
        if (constChar_) {
            d_.setBadOffset(i_);
            return d_;
        }
        if (!parsBrackets_.isEmpty()) {
            d_.setBadOffset(i_);
            return d_;
        }
        if (!partOfString_) {
            d_.setIndexBegin(_minIndex);
            d_.setIndexEnd(i_-1);
            return d_;
        }
        d_.setBadOffset(i_);
        return d_;
    }
    static IndexUnicodeEscape processStrings(String _string, int _max, IndexUnicodeEscape _infos, char _delimiter) {
        int i_ = _infos.getIndex();
        int nbChars_ = _infos.getNbChars();
        int unicode_ = _infos.getUnicode();
        int len_ = _max;
        char curChar_ = _string.charAt(i_);
        boolean escapedMeta_ = _infos.isEscape();
        IndexUnicodeEscape infos_ = new IndexUnicodeEscape();
        infos_.setEscape(escapedMeta_);
        infos_.setIndex(i_);
        infos_.setNbChars(nbChars_);
        infos_.setUnicode(unicode_);
        infos_.setPart(_infos.isPart());
        if (curChar_ == LINE_RETURN) {
            infos_.setIndex(-i_);
            return infos_;
        }
        if (curChar_ == TAB) {
            infos_.setIndex(-i_);
            return infos_;
        }
        if (curChar_ == LINE_FEED) {
            infos_.setIndex(-i_);
            return infos_;
        }
        if (nbChars_ > 1 && _delimiter == DELIMITER_CHAR) {
            infos_.setIndex(-i_);
            return infos_;
        }
        if (!escapedMeta_) {
            if (curChar_ == ESCAPE_META_CHAR) {
                if (i_ + 1 >= len_) {
                    infos_.setIndex(-i_);
                    return infos_;
                }
                infos_.setEscape(true);
                i_++;
                infos_.setIndex(i_);
                return infos_;
            }
            if (curChar_ == _delimiter) {
                infos_.setPart(false);
                i_++;
                infos_.setIndex(i_);
                return infos_;
            }
            nbChars_++;
            infos_.setNbChars(nbChars_);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (unicode_ > 0) {
            boolean ok_ = false;
            if (curChar_ >= MIN_ENCODE_DIGIT && curChar_ <= MAX_ENCODE_DIGIT) {
                ok_ = true;
            }
            if (curChar_ >= MIN_ENCODE_LOW_LETTER && curChar_ <= MAX_ENCODE_LOW_LETTER) {
                ok_ = true;
            }
            if (curChar_ >= MIN_ENCODE_UPP_LETTER && curChar_ <= MAX_ENCODE_UPP_LETTER) {
                ok_ = true;
            }
            if (!ok_) {
                infos_.setIndex(-i_);
                return infos_;
            }
            if (unicode_ < UNICODE_SIZE) {
                unicode_++;
            } else {
                unicode_ = 0;
                nbChars_++;
                escapedMeta_ = false;
            }
            infos_.setNbChars(nbChars_);
            i_++;
            infos_.setIndex(i_);
            infos_.setEscape(escapedMeta_);
            infos_.setUnicode(unicode_);
            return infos_;
        }
        if (curChar_ == _delimiter) {
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == IND_LINE) {
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == IND_FORM) {
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == IND_BOUND) {
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == IND_LINE_FEED) {
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == IND_TAB) {
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == ESCAPE_META_CHAR) {
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ != UNICODE || i_ + UNICODE_SIZE > len_) {
            infos_.setIndex(-i_);
            return infos_;
        }
        unicode_ = 1;
        infos_.setUnicode(unicode_);
        i_++;
        infos_.setIndex(i_);
        return infos_;
    }
    static int processPredefinedMethod(String _string, int _i, String _name, int _max) {
        int afterSuper_ = _i + 1 + _name.length();
        int index_ = _string.indexOf(PAR_LEFT,afterSuper_);
        if (index_ < 0) {
            return -afterSuper_;
        }
        if (!_string.substring(afterSuper_, index_).trim().isEmpty()) {
            return -afterSuper_;
        }
        return index_;
    }

    static boolean isNumber(int _start, int _max, String _string) {
        if (_start >= _string.length()) {
            return false;
        }
        char first_ = _string.charAt(_start);
        int k_ = _start;
        boolean var_ = false;
        while (k_ < _max) {
            if (!StringList.isWordChar(_string.charAt(k_))) {
                if (_string.charAt(k_) == GET_VAR) {
                    var_ = true;
                }
                break;
            }
            k_++;
        }
        if (var_) {
            return false;
        }
        while (k_ < _max) {
            if (!Character.isWhitespace(_string.charAt(k_))) {
                if (_string.charAt(k_) == PAR_LEFT) {
                    var_ = true;
                }
                break;
            }
            k_++;
        }
        return Character.isDigit(first_) && !var_;
    }

    static NumberInfosOutput processNb(int _start, int _max, String _string, boolean _seenDot) {
        //_string.charAt(_start) is digit
        NumberInfosOutput output_ = new NumberInfosOutput();
        NumberInfos nbInfos_ = new NumberInfos();
        output_.setInfos(nbInfos_);
        StringBuilder intPart_ = new StringBuilder();
        StringBuilder decPart_ = new StringBuilder();
        StringBuilder expPart_ = new StringBuilder();
        nbInfos_.setIntPart(intPart_);
        nbInfos_.setDecimalPart(decPart_);
        nbInfos_.setExponentialPart(expPart_);
        if (_seenDot) {
            nbInfos_.setDotted(true);
            nbInfos_.setSuffix(Character.toUpperCase(DOUBLE));
            decPart_.append(_string.charAt(_start));
        } else {
            intPart_.append(_string.charAt(_start));
        }
        int len_ = _max;
        int j_ = _start + 1;
        boolean dot_ = false;
        boolean exp_ = false;
        int iExp_ = j_;
        while (j_ < len_) {
            char current_ = _string.charAt(j_);
            if (!StringList.isWordChar(current_)) {
                if (current_ == DOT_VAR) {
                    if (_seenDot) {
                        output_.setNextIndex(-j_);
                        return output_;
                    }
                    if (j_ + 1 < len_ && Character.isWhitespace(_string.charAt(j_ + 1))) {
                        String nextPart_ = _string.substring(j_ + 1).trim();
                        if (nextPart_.isEmpty()) {
                            nbInfos_.setDotted(true);
                            nbInfos_.setSuffix(Character.toUpperCase(DOUBLE));
                            output_.setNextIndex(j_ + 1);
                            return output_;
                        }
                        if (!isCorrectNbEnd(nextPart_)) {
                            output_.setNextIndex(-(j_ + 1));
                            return output_;
                        }
                    }
                    if (j_ + 1 < len_ && _string.charAt(j_ + 1) == DOT_VAR) {
                        output_.setNextIndex(-(j_ + 1));
                        return output_;
                    }
                    dot_ = true;
                }
                break;
            }
            if (Character.toLowerCase(current_) == EXP) {
                if (j_ + 1 < len_ && Character.isWhitespace(_string.charAt(j_ + 1))) {
                    output_.setNextIndex(-(j_ + 1));
                    return output_;
                }
                nbInfos_.setSuffix(Character.toUpperCase(DOUBLE));
                iExp_ = j_;
                exp_ = true;
                break;
            }
            if (Character.isLetter(current_)) {
                if (!isNbSuffix(current_)) {
                    output_.setNextIndex(-j_);
                    return output_;
                }
                nbInfos_.setSuffix(current_);
                String nextPart_ = _string.substring(j_ + 1).trim();
                if (!isCorrectNbEnd(nextPart_)) {
                    output_.setNextIndex(-j_);
                    return output_;
                }
                output_.setNextIndex(j_ + 1);
                return output_;
            }
            if (_seenDot) {
                decPart_.append(current_);
            } else {
                intPart_.append(current_);
            }
            j_++;
        }
        if (j_ + 1 >= len_ && dot_) {
            nbInfos_.setDotted(true);
            nbInfos_.setSuffix(Character.toUpperCase(DOUBLE));
            output_.setNextIndex(j_ + 1);
            return output_;
        }
        if (dot_) {
            nbInfos_.setSuffix(Character.toUpperCase(DOUBLE));
            nbInfos_.setDotted(true);
            char next_ = _string.charAt(j_ + 1);
            if (Character.toLowerCase(next_) == EXP) {
                exp_ = true;
            }
            if (!Character.isDigit(next_) && next_ != NB_INTERN_SP && !exp_) {
                if (isNbSuffix(next_)) {
                    j_++;
                }
                String nextPart_ = _string.substring(j_ + 1).trim();
                if (!isCorrectNbEnd(nextPart_)) {
                    output_.setNextIndex(-(j_ + 1));
                    return output_;
                }
                output_.setNextIndex(j_ + 1);
                return output_;
            }
            j_++;
            if (exp_) {
                //_string.charAt(j_) == EXP
                processExp(j_, len_, _string, output_);
                return output_;
            }
            while (j_ < len_) {
                if (!Character.isDigit(_string.charAt(j_)) && _string.charAt(j_) != NB_INTERN_SP) {
                    break;
                }
                decPart_.append(_string.charAt(j_));
                j_++;
            }
            if (j_ >= len_) {
                output_.setNextIndex(j_);
                return output_;
            }
            if (Character.toLowerCase(_string.charAt(j_)) == EXP) {
                processExp(j_, len_, _string, output_);
                return output_;
            }
            if (Character.isLetter(_string.charAt(j_)) && !isNbSuffix(_string.charAt(j_))) {
                output_.setNextIndex(-j_);
                return output_;
            }
            if (Character.isLetter(_string.charAt(j_))) {
                // => isNbSuffix(_string.charAt(j_))
                nbInfos_.setSuffix(_string.charAt(j_));
                j_++;
            }
            String nextPart_ = _string.substring(j_).trim();
            if (!isCorrectNbEnd(nextPart_)) {
                output_.setNextIndex(-j_);
                return output_;
            }
            output_.setNextIndex(j_);
            return output_;
        }
        if (iExp_ + 1 >= len_ && exp_) {
            output_.setNextIndex(-j_);
            return output_;
        }
        if (exp_) {
            //_string.charAt(iExp_) == EXP
            processExp(iExp_, len_, _string, output_);
            return output_;
        }
        String next_ = _string.substring(j_).trim();
        if (!isCorrectNbEnd(next_)) {
            output_.setNextIndex(-j_);
            return output_;
        }
        if (!_seenDot) {
            nbInfos_.setSuffix(Character.toUpperCase(LONG));
        }
        output_.setNextIndex(j_);
        return output_;
    }
    static boolean isNbSuffix(char _char) {
        char lower_ = Character.toLowerCase(_char);
        if (lower_ == DOUBLE) {
            return true;
        }
        if (lower_ == FLOAT) {
            return true;
        }
        if (lower_ == LONG) {
            return true;
        }
        if (lower_ == INTEGER) {
            return true;
        }
        if (lower_ == CHARACTER) {
            return true;
        }
        if (lower_ == SHORT) {
            return true;
        }
        if (lower_ == BYTE) {
            return true;
        }
        return false;
    }

    static boolean isCorrectNbEnd(String _string) {
        if (_string.isEmpty()) {
            return true;
        }
        char char_ = _string.charAt(0);
        if (char_ == PAR_LEFT) {
            return false;
        }
        if (char_ == ANN_ARR_LEFT) {
            return false;
        }
        if (char_ == ARR_LEFT) {
            return false;
        }
        if (char_ == DOT_VAR) {
            return false;
        }
        if (char_ == GET_VAR) {
            return false;
        }
        if (char_ == EXTERN_CLASS) {
            String is_ = StringList.concat(String.valueOf(EXTERN_CLASS),INSTANCEOF);
            if (procWordFirstChar(_string.trim(), 0, is_)) {
                return true;
            }
            return false;
        }
        if (char_ == DELIMITER_CHAR) {
            return false;
        }
        if (char_ == DELIMITER_STRING) {
            return false;
        }
        if (StringList.isWordChar(char_)) {
            return false;
        }
        return true;
    }

    static boolean isCorrectNbEndWord(String _string, char _end) {
        if (_string.isEmpty()) {
            return true;
        }
        char char_ = _string.charAt(0);
        if (char_ == _end) {
            return true;
        }
        if (char_ == Templates.PREFIX_VAR_TYPE_CHAR) {
            return false;
        }
        if (char_ == DELIMITER_CHAR) {
            return false;
        }
        if (char_ == DELIMITER_STRING) {
            return false;
        }
        if (char_ == ':') {
            return false;
        }
        if (char_ == '{') {
            return false;
        }
        if (char_ == '}') {
            return false;
        }
        if (char_ == '?') {
            return false;
        }
        return true;
    }
    static void processExp(int _start, int _max, String _string, NumberInfosOutput _output) {
        StringBuilder exp_ = _output.getInfos().getExponentialPart();
        int len_ = _max;
        int j_ = _start;
        j_++;
        if (_string.charAt(j_) == MINUS_CHAR) {
            exp_.append(_string.charAt(j_));
            j_++;
        }
        if (!Character.isDigit(_string.charAt(j_))) {
            _output.setNextIndex(-j_);
            return;
        }
        while (j_ < len_) {
            if (!Character.isDigit(_string.charAt(j_)) && _string.charAt(j_) != NB_INTERN_SP) {
                break;
            }
            exp_.append(_string.charAt(j_));
            j_++;
        }
        String nextPart_ = _string.substring(j_).trim();
        if (!nextPart_.isEmpty() && nextPart_.charAt(0) == DOT_VAR) {
            _output.setNextIndex(-j_);
            return;
        }
        if (j_ < len_ && Character.isLetter(_string.charAt(j_))) {
            if (!isNbSuffix(_string.charAt(j_))) {
                _output.setNextIndex(-j_);
                return;
            }
            _output.getInfos().setSuffix(_string.charAt(j_));
            j_++;
        }
        nextPart_ = _string.substring(j_).trim();
        if (!isCorrectNbEnd(nextPart_)) {
            _output.setNextIndex(-j_);
            return;
        }
        _output.setNextIndex(j_);
    }
    public static OperationsSequence getOperationsSequence(int _offset, String _string,
            Analyzable _conf, Delimiters _d) {
        NatTreeMap<Integer,String> operators_;
        operators_ = new NatTreeMap<Integer,String>();
        NatTreeMap<Integer,Character> parsBrackets_;
        parsBrackets_ = new NatTreeMap<Integer,Character>();
        int prio_ = FCT_OPER_PRIO;
        int len_ = _string.length();
        int i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            if (!Character.isWhitespace(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        if (i_ >= len_) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string, 0);
            op_.setDelimiter(_d);
            return op_;
        }
        int firstPrintChar_ = i_;
        int lastPrintChar_ = len_ - 1;
        while (lastPrintChar_ >= 0) {
            if (!Character.isWhitespace(_string.charAt(lastPrintChar_))) {
                break;
            }
            lastPrintChar_--;
        }
        int strLen_ = _string.length();
        len_ = lastPrintChar_+1;
        int beginSt_;
        int endSt_;
        beginSt_ = _d.getDelKeyWordStatic().indexOfObj(_offset + firstPrintChar_);
        endSt_ = _d.getDelKeyWordStatic().indexOfObj(_offset + strLen_);
        if (beginSt_ > CustList.INDEX_NOT_FOUND_ELT && beginSt_ + 1 == endSt_) {
            int ext_ = beginSt_ / 2;
            String extracted_ = _d.getDelKeyWordStaticExtract().get(ext_);
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.STATIC_ACCESS);
            op_.setExtractType(extracted_);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (_string.charAt(i_) == EXTERN_CLASS) {
            int begin_;
            int end_;
            begin_ = _d.getDelVararg().indexOfObj(_offset + firstPrintChar_);
            end_ = _d.getDelVararg().indexOfObj(_offset + lastPrintChar_);
            if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.VARARG);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string, firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            begin_ = _d.getDelLambda().indexOfObj(_offset + firstPrintChar_);
            end_ = _d.getDelLambda().indexOfObj(_offset + lastPrintChar_);
            if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.LAMBDA);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string, firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            begin_ = _d.getDelClass().indexOfObj(_offset + firstPrintChar_);
            end_ = _d.getDelClass().indexOfObj(_offset + lastPrintChar_);
            if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.CLASS_INFO);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string, firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            begin_ = _d.getDelKeyWordStatic().indexOfObj(_offset + firstPrintChar_);
            end_ = _d.getDelKeyWordStatic().indexOfObj(_offset + strLen_);
            if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
                int ext_ = begin_ / 2;
                String extracted_ = _d.getDelKeyWordStaticExtract().get(ext_);
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.STATIC_ACCESS);
                op_.setExtractType(extracted_);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string, firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            String sub_ = _string.substring(firstPrintChar_ + 1, len_);
            if (StringList.quickEq(sub_, THIS)) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.THIS_KEYWORD);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string, firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            if (_conf.isInternGlobal()) {
                if (StringList.quickEq(sub_, INTERN_BEAN)) {
                    OperationsSequence op_ = new OperationsSequence();
                    op_.setConstType(ConstType.WORD);
                    op_.setOperators(new NatTreeMap<Integer, String>());
                    op_.setValue(_string, firstPrintChar_);
                    op_.setDelimiter(_d);
                    return op_;
                }
            }
            if (StringList.quickEq(sub_, NULL_REF_STRING)) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.NULL_CST);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string, firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            if (StringList.quickEq(sub_, TRUE_STRING)) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.TRUE_CST);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string, firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            if (StringList.quickEq(sub_, FALSE_STRING)) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.FALSE_CST);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string, firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            begin_ = _d.getDelKeyWordSuper().indexOfObj(_offset + firstPrintChar_);
            end_ = _d.getDelKeyWordSuper().indexOfObj(_offset + lastPrintChar_ + 1);
            if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.SUPER_KEYWORD);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string.substring(firstPrintChar_+SUPER.length() + 2, lastPrintChar_ + 1), firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            begin_ = _d.getDelKeyWordClassChoice().indexOfObj(_offset + firstPrintChar_);
            end_ = _d.getDelKeyWordClassChoice().indexOfObj(_offset + lastPrintChar_ + 1);
            if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.CLASSCHOICE_KEYWORD);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string.substring(firstPrintChar_, lastPrintChar_ + 1),firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
        }
        int firstNbChar_ = firstPrintChar_;
        boolean positive_ = true;
        if (_string.charAt(firstPrintChar_) == MINUS_CHAR) {
            positive_ = false;
            int secondPrintChar_ = firstPrintChar_ + 1;
            while (secondPrintChar_ <= lastPrintChar_) {
                if (!Character.isWhitespace(_string.charAt(secondPrintChar_))) {
                    firstNbChar_ = secondPrintChar_;
                    break;
                }
                secondPrintChar_++;
            }
        }
        int beginNb_ = _d.getDelNumbers().indexOfObj(_offset + firstNbChar_);
        int endNb_ = _d.getDelNumbers().indexOfObj(_offset + lastPrintChar_ + 1);
        if (beginNb_ > CustList.INDEX_NOT_FOUND_ELT && beginNb_ + 1 == endNb_) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.NUMBER);
            int indexNb_ = beginNb_/2;
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setNbInfos(_d.getNbInfos().get(indexNb_));
            op_.getNbInfos().setPositive(positive_);
            op_.getNbInfos().setFirstPrintable(firstPrintChar_);
            op_.getNbInfos().setFirstDigit(firstNbChar_);
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        boolean enPars_ = true;
        int iVar_ = -1;
        for (VariableInfo v: _d.getVariables()) {
            if (v.getFirstChar() != _offset + firstPrintChar_) {
                continue;
            }
            iVar_ = v.getLastChar();
            if (iVar_ != _offset + lastPrintChar_ + 1) {
                break;
            }
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(v.getKind());
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(v.getName(), firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        int before_ = _d.getDelStringsChars().indexOfObj(firstPrintChar_+_offset);
        int after_ = _d.getDelStringsChars().indexOfObj(lastPrintChar_+_offset);
        if (before_ + 1 == after_) {
            if (_string.charAt(firstPrintChar_) == DELIMITER_CHAR) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.CHARACTER);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string, firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            if (_string.charAt(firstPrintChar_) == DELIMITER_STRING) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.STRING);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string, firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
        }
        boolean preIncr_ = false;
        if (_string.charAt(firstPrintChar_) == MINUS_CHAR || _string.charAt(firstPrintChar_) == PLUS_CHAR) {
            if (firstPrintChar_ + 1 < _string.length()) {
                if (_string.charAt(firstPrintChar_) == _string.charAt(firstPrintChar_ + 1)) {
                    preIncr_ = true;
                }
            }
        }
        if (preIncr_ && applyMultipleAffectations(_conf)) {
            prio_ = UNARY_PRIO;
            String ch_ = String.valueOf(_string.charAt(firstPrintChar_));
            operators_.put(firstPrintChar_, StringList.concat(EMPTY_STRING,ch_,ch_));
            i_ = incrementUnary(_string, firstPrintChar_ + 2, lastPrintChar_, _offset, _d);
        } else if (_string.charAt(firstPrintChar_) == MINUS_CHAR || _string.charAt(firstPrintChar_) == PLUS_CHAR && !_conf.getOptions().applyEqPlus()) {
            prio_ = UNARY_PRIO;
            operators_.put(firstPrintChar_, String.valueOf(_string.charAt(firstPrintChar_)));
            i_ = incrementUnary(_string, firstPrintChar_ + 1, lastPrintChar_, _offset, _d);
        } else if (_string.charAt(firstPrintChar_) == NEG_BOOL_CHAR) {
            if (firstPrintChar_ < lastPrintChar_ && _string.charAt(firstPrintChar_+1) != EQ_CHAR) {
                prio_ = UNARY_PRIO;
                operators_.put(firstPrintChar_, String.valueOf(NEG_BOOL_CHAR));
                i_ = incrementUnary(_string, firstPrintChar_ + 1, lastPrintChar_, _offset, _d);
            }
        } else if (_d.getDelCast().contains(firstPrintChar_+_offset)) {
            prio_ = UNARY_PRIO;
            int min_ = _d.getDelCast().indexOfObj(firstPrintChar_+_offset);
            int max_ = _d.getDelCast().get(min_ + 1) - _offset;
            operators_.put(firstPrintChar_, _string.substring(firstPrintChar_, max_ + 1));
            i_ = incrementUnary(_string, firstPrintChar_, lastPrintChar_, _offset, _d);
        } else {
            if (iVar_ > -1) {
                i_ = iVar_ - _offset;
                enPars_ = false;
                operators_.put(i_, EMPTY_STRING);
            }
        }
        boolean useFct_ = false;
        boolean is_ = false;
        String fctName_ = EMPTY_STRING;
        boolean enabledId_ = false;
        boolean declaring_ = false;
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (_d.getAllowedOperatorsIndexes().containsObj(i_+_offset)) {
                if (curChar_ == PAR_LEFT) {
                    if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO && !declaring_) {
                        if (enPars_) {
                            operators_.clear();
                            useFct_ = true;
                            fctName_ = _string.substring(CustList.FIRST_INDEX, i_);
                            operators_.put(i_, String.valueOf(PAR_LEFT));
                        } else if (enabledId_) {
                            operators_.clear();
                            operators_.put(i_, String.valueOf(EMPTY_STRING));
                        }
                    }
                    parsBrackets_.put(i_, curChar_);
                }
                if (StringList.quickEq(fctName_, StringList.concat(String.valueOf(EXTERN_CLASS),VALUE_OF))) {
                    if (curChar_ == SEP_ARG && parsBrackets_.size() == 1 && enPars_) {
                        int first_ = parsBrackets_.firstKey();
                        operators_.clear();
                        operators_.put(first_, String.valueOf(PAR_LEFT));
                        operators_.put(i_, String.valueOf(SEP_ARG));
                    }
                } else if (!StringList.quickEq(fctName_, StringList.concat(String.valueOf(EXTERN_CLASS),VALUES))) {
                    if (curChar_ == SEP_ARG && parsBrackets_.size() == 0) {
                        if (!declaring_) {
                            operators_.clear();
                        }
                        operators_.put(i_, String.valueOf(SEP_ARG));
                        declaring_ = true;
                    } else if (curChar_ == SEP_ARG && parsBrackets_.size() == 1 && prio_ >= FCT_OPER_PRIO && enPars_ && !declaring_) {
                        operators_.put(i_, String.valueOf(SEP_ARG));
                    }
                }
                if (curChar_ == PAR_RIGHT) {
                    parsBrackets_.removeKey(parsBrackets_.lastKey());
                    if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO && enPars_ && !declaring_) {
                        operators_.put(i_, String.valueOf(PAR_RIGHT));
                    }
                }
                if (_conf.isAnnotAnalysis()) {
                    if (curChar_ == ANN_ARR_LEFT) {
                        if (parsBrackets_.isEmpty() && !declaring_) {
                            if (FCT_OPER_PRIO <= prio_) {
                                useFct_ = false;
                                fctName_ = EMPTY_STRING;
                                operators_.clear();
                                if (firstPrintChar_ == i_) {
                                    operators_.put(i_, ANN_ARR);
                                } else {
                                    operators_.put(i_, EMPTY_STRING);
                                }
                            }
                        }
                        parsBrackets_.put(i_, curChar_);
                    }
                    if (curChar_ == ANN_ARR_RIGHT) {
                        parsBrackets_.removeKey(parsBrackets_.lastKey());
                        if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO && !declaring_) {
                            if (!operators_.lastValue().isEmpty()) {
                                operators_.put(i_, String.valueOf(ANN_ARR_RIGHT));
                            }
                            enabledId_ = true;
                        }
                    }
                } else {
                    if (curChar_ == ARR_LEFT) {
                        if (parsBrackets_.isEmpty() && !declaring_) {
                            if (FCT_OPER_PRIO <= prio_) {
                                useFct_ = false;
                                fctName_ = EMPTY_STRING;
                                operators_.clear();
                                if (firstPrintChar_ == i_) {
                                    operators_.put(i_, ARR);
                                } else {
                                    operators_.put(i_, EMPTY_STRING);
                                }
                            }
                        }
                        parsBrackets_.put(i_, curChar_);
                    }
                    if (curChar_ == ARR_RIGHT) {
                        parsBrackets_.removeKey(parsBrackets_.lastKey());
                        if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO && !declaring_) {
                            if (!operators_.lastValue().isEmpty()) {
                                operators_.put(i_, String.valueOf(ARR_RIGHT));
                            }
                            enabledId_ = true;
                        }
                    }
                }
                if (parsBrackets_.isEmpty() && !declaring_) {
                    StringBuilder builtOperator_ = new StringBuilder();
                    boolean clearOperators_ = false;
                    boolean foundOperator_ = false;
                    int increment_ = 1;
                    if (curChar_ == DOT_VAR) {
                        builtOperator_.append(DOT_VAR);
                        if (prio_ == FCT_OPER_PRIO) {
                            clearOperators_ = true;
                            foundOperator_ = true;
                            enPars_ = false;
                            enabledId_ = false;
                        }
                    }
                    if (curChar_ == NEG_BOOL_CHAR || curChar_ == EQ_CHAR) {
                        boolean processNextEq_ = false;
                        if (curChar_ == NEG_BOOL_CHAR) {
                            processNextEq_ = true;
                        } else if (applyMultipleAffectations(_conf)) {
                            processNextEq_ = true;
                        }
                        if (processNextEq_) {
                            if (i_ + 1 < _string.length()) {
                                char nextChar_ = _string.charAt(i_ + 1);
                                if (nextChar_ != EQ_CHAR) {
                                    processNextEq_ = false;
                                }
                            } else {
                                processNextEq_ = false;
                            }
                        }
                        if (processNextEq_) {
                            builtOperator_.append(curChar_);
                            if (prio_ > EQ_PRIO) {
                                prio_ = EQ_PRIO;
                            }
                            if (prio_ == EQ_PRIO) {
                                clearOperators_ = true;
                                foundOperator_ = true;
                                builtOperator_.append(EQ_CHAR);
                            }
                            increment_ = 2;
                        } else if (curChar_ == NEG_BOOL_CHAR) {
                            builtOperator_.append(curChar_);
                            if (i_ + 1 < _string.length()) {
                                if (prio_ > EQ_PRIO) {
                                    prio_ = EQ_PRIO;
                                    clearOperators_ = true;
                                    foundOperator_ = true;
                                }
                            } else {
                                prio_ = EQ_PRIO;
                                foundOperator_ = true;
                            }
                        } else if (!applyMultipleAffectations(_conf)) {
                            builtOperator_.append(EQ_CHAR);
                            if (_conf.isAnalyzingRoot() && _conf.isRootAffect()) {
                                if (i_ + 1 < _string.length()) {
                                    char nextChar_ = _string.charAt(i_ + 1);
                                    if (prio_ > AFF_PRIO) {
                                        clearOperators_ = true;
                                        prio_ = AFF_PRIO;
                                        foundOperator_ = true;
                                    }
                                    increment_ = 1;
                                    if (nextChar_ == PLUS_CHAR) {
                                        increment_ = 2;
                                        builtOperator_.append(PLUS_CHAR);
                                    }
                                } else {
                                    if (prio_ > EQ_PRIO) {
                                        prio_ = EQ_PRIO;
                                    }
                                    if (prio_ == EQ_PRIO) {
                                        clearOperators_ = true;
                                        foundOperator_ = true;
                                    }
                                    increment_ = 1;
                                }
                            } else {
                                if (prio_ > EQ_PRIO) {
                                    prio_ = EQ_PRIO;
                                }
                                if (prio_ == EQ_PRIO) {
                                    clearOperators_ = true;
                                    foundOperator_ = true;
                                }
                                increment_ = 1;
                            }
                        } else {
                            builtOperator_.append(curChar_);
                            if (prio_ > AFF_PRIO) {
                                clearOperators_ = true;
                                prio_ = AFF_PRIO;
                                foundOperator_ = true;
                            }
                            increment_ = 1;
                            if (i_ + 1 < _string.length() && _conf.getOptions().applyEqPlus()) {
                                char nextChar_ = _string.charAt(i_ + 1);
                                if (nextChar_ == PLUS_CHAR) {
                                    increment_ = 2;
                                    builtOperator_.append(PLUS_CHAR);
                                }
                            }
                        }
                    }
                    int prioOpMult_ = 0;
                    if (curChar_ == MINUS_CHAR || curChar_ == PLUS_CHAR) {
                        prioOpMult_ = ADD_PRIO;
                    } else if (curChar_ == MULT_CHAR || curChar_ == DIV_CHAR || curChar_ == MOD_CHAR) {
                        prioOpMult_ = MULT_PRIO;
                    } else if (curChar_ == AND_CHAR) {
                        prioOpMult_ = AND_PRIO;
                    } else if (curChar_ == OR_CHAR) {
                        prioOpMult_ = OR_PRIO;
                    }
                    if (prioOpMult_ > 0) {
                        builtOperator_.append(curChar_);
                        if (_conf.isAnalyzingRoot() && _conf.isRootAffect() || applyMultipleAffectations(_conf)) {
                            if (i_ + 1 < len_) {
                                char nextChar_ = _string.charAt(i_ + 1);
                                if (prioOpMult_ == ADD_PRIO && nextChar_ == curChar_ && (i_ + 2 == len_ || applyMultipleAffectations(_conf))) {
                                    if (prio_ > POST_INCR_PRIO) {
                                        clearOperators_ = true;
                                        prio_ = POST_INCR_PRIO;
                                        foundOperator_ = true;
                                    }
                                    increment_ = 2;
                                    builtOperator_.append(nextChar_);
                                } else if (nextChar_ == EQ_CHAR) {
                                    if (prio_ > AFF_PRIO) {
                                        clearOperators_ = true;
                                        prio_ = AFF_PRIO;
                                        foundOperator_ = true;
                                    }
                                    increment_ = 2;
                                    builtOperator_.append(EQ_CHAR);
                                } else {
                                    if (prio_ > prioOpMult_) {
                                        prio_ = prioOpMult_;
                                    }
                                    if (prio_ == prioOpMult_) {
                                        clearOperators_ = true;
                                        foundOperator_ = true;
                                    }
                                }
                            } else {
                                if (prio_ > prioOpMult_) {
                                    prio_ = prioOpMult_;
                                }
                                if (prio_ == prioOpMult_) {
                                    clearOperators_ = true;
                                    foundOperator_ = true;
                                }
                            }
                        } else {
                            if (prio_ > prioOpMult_) {
                                prio_ = prioOpMult_;
                            }
                            if (prio_ == prioOpMult_) {
                                clearOperators_ = true;
                                foundOperator_ = true;
                            }
                        }
                    }
                    if (curChar_ == EXTERN_CLASS && procWordFirstChar(_string, i_ + 1, INSTANCEOF)) {
                        if (prio_ > CMP_PRIO) {
                            int min_ = _d.getDelInstanceof().indexOfObj(i_+_offset);
                            int next_ = _d.getDelInstanceof().get(min_+1) - _offset - 1;
                            if (next_ == lastPrintChar_) {
                                is_ = true;
                                clearOperators_ = true;
                                prio_ = CMP_PRIO;
                                foundOperator_ = true;
                                String op_ = _string.substring(i_, next_+1);
                                builtOperator_.append(op_);
                                increment_ = op_.length();
                            }
                        }
                    }
                    if (curChar_ == LOWER_CHAR || curChar_ == GREATER_CHAR) {
                        builtOperator_.append(curChar_);
                        if (prio_ > CMP_PRIO) {
                            clearOperators_ = true;
                            prio_ = CMP_PRIO;
                        }
                        if (prio_ == CMP_PRIO) {
                            foundOperator_ = true;
                        }
                        if (i_ + 1 < _string.length()) {
                            char nextChar_ = _string.charAt(i_ + 1);
                            if (nextChar_ == EQ_CHAR) {
                                builtOperator_.append(nextChar_);
                                increment_++;
                            }
                        }
                    }
                    if (foundOperator_) {
                        if (clearOperators_) {
                            useFct_ = false;
                            fctName_ = EMPTY_STRING;
                            operators_.clear();
                        }
                        operators_.put(i_,builtOperator_.toString());
                    }
                    i_ += increment_;
                    continue;
                }
            }
            i_++;
        }
        OperationsSequence op_ = new OperationsSequence();
        op_.setDeclaring(declaring_);
        op_.setPriority(prio_);
        op_.setOperators(operators_);
        op_.setUseFct(useFct_);
        op_.setFctName(fctName_);
        boolean annotation_ = _conf.isAnnotAnalysis();
        op_.setupValues(_string, is_, annotation_);
        op_.setDelimiter(_d);
        return op_;
    }

    static boolean applyMultipleAffectations(Analyzable _an) {
        if (_an.getOptions().isMultipleAffectations()) {
            return true;
        }
        return _an.isAnnotAnalysis();
    }
    static int incrementUnary(String _string, int _from, int _to, int _offset, Delimiters _d) {
        int j_ = _from;
        while (j_ <= _to) {
            char ch_ = _string.charAt(j_);
            if (ch_ != MINUS_CHAR) {
                if (ch_ != PLUS_CHAR) {
                    if (ch_ != NEG_BOOL_CHAR) {
                        if (!Character.isWhitespace(ch_)) {
                            int sum_ = _offset + j_;
                            int indexCast_ = _d.getDelCast().indexOfObj(sum_);
                            if (indexCast_ > -1) {
                                int next_ = _d.getDelCast().get(indexCast_ + 1);
                                next_ -= _offset;
                                j_ = next_ + 1;
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
            j_++;
        }
        return j_;
    }

    public static boolean procWordFirstChar(String _string, int _i, String _word) {
        int len_ = _string.length();
        int wordLength_ = _word.length();
        if (_i + wordLength_ <= len_) {
            boolean process_ = true;
            if (_i + wordLength_ < len_) {
                char ch_ = _string.charAt(_i + wordLength_);
                if (StringList.isDollarWordChar(ch_)) {
                    process_ = false;
                }
            }
            if (!_string.substring(_i, _i + wordLength_).startsWith(_word)) {
                process_ = false;
            }
            return process_;
        }
        return false;
    }
    private static String prefix(String _keyWord) {
        return StringList.concat(String.valueOf(EXTERN_CLASS),_keyWord);
    }
}
