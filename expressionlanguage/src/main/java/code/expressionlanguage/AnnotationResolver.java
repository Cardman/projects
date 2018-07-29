package code.expressionlanguage;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;


public final class AnnotationResolver {

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
    public static final int FCT_OPER_PRIO = 10;
    public static final byte UNICODE_SIZE = 4;

    private static final String EMPTY_STRING = "";
    private static final char ESCAPE_META_CHAR = '\\';
    private static final char DELIMITER_CHAR = 39;
    private static final char DELIMITER_STRING = 34;
    private static final char ARR_LEFT = '{';
    private static final char ARR_RIGHT = '}';
    private static final char PAR_LEFT = '(';
    private static final char PAR_RIGHT = ')';
    private static final char SEP_ARG = ',';
    private static final char DOT_VAR = '.';
    private static final char ANNOT = '@';
    private static final char EXTERN_CLASS = FileResolver.SUPPLEMENT_CHAR;
    private static final String STATIC_ACCESS = "static";
    private static final String BOOLEAN = "bool";
    private static final String CLASS = "class";
    private static final String NULL_REF_STRING = "null";
    private static final String TRUE_STRING = "true";
    private static final String FALSE_STRING = "false";

    private static final String ARR = "[";

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

    private AnnotationResolver() {
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
                IndexUnicodeEscape res_ = ElResolver.processStrings(_string, len_, unic_, DELIMITER_CHAR);
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
                        if (StringList.isWordChar(nextPart_.charAt(0))) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (nextPart_.charAt(0) == ARR_LEFT) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (nextPart_.charAt(0) == PAR_LEFT) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (nextPart_.charAt(0) == DOT_VAR) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (nextPart_.charAt(0) == EXTERN_CLASS) {
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
                IndexUnicodeEscape res_ = ElResolver.processStrings(_string, len_, unic_, DELIMITER_STRING);
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
                        if (StringList.isWordChar(nextPart_.charAt(0))) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (nextPart_.charAt(0) == ARR_LEFT) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (nextPart_.charAt(0) == PAR_LEFT) {
                            d_.setBadOffset(i_);
                            return d_;
                        }
                        if (nextPart_.charAt(0) == EXTERN_CLASS) {
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
            if (curChar_ == ANNOT) {
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
            if (curChar_ == EXTERN_CLASS) {
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
                    if (ElResolver.procWordFirstChar(_string, i_ + 1, CLASS)) {
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
                    if (ElResolver.procWordFirstChar(_string, i_ + 1, STATIC_ACCESS)) {
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
                    boolean foundValue_ = false;
                    for (String s: StringList.wrapStringArray(TRUE_STRING,FALSE_STRING,NULL_REF_STRING)) {
                        if (ElResolver.procWordFirstChar(_string, i_ + 1, s)) {
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
                    for (String s: StringList.wrapStringArray(BOOLEAN)) {
                        if (ElResolver.procWordFirstChar(_string, i_ + 1, s)) {
                            int index_ = ElResolver.processPredefinedMethod(_string, i_, s, len_);
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
                }
                d_.setBadOffset(i_);
                return d_;
            }
            if (StringList.isWordChar(curChar_)) {
                enabledMinus_ = true;
                if (ElResolver.isNumber(i_, len_, _string)) {
                    NumberInfosOutput res_ = ElResolver.processNb(i_, len_, _string, false);
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
                    if (!StringList.isWordChar(locChar_)) {
                        if (locChar_ != EXTERN_CLASS) {
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
                                i_=bk_;
                                if (spaces_) {
                                    boolean keep_ = false;
                                    if (StringList.isWordChar(locChar_)) {
                                        keep_ = true;
                                    } else if (locChar_ == EXTERN_CLASS) {
                                        keep_ = true;
                                    } else if (locChar_ == Templates.PREFIX_VAR_TYPE_CHAR) {
                                        keep_ = true;
                                    }
                                    if (keep_) {
                                        i_++;
                                        continue;
                                    }
                                }
                                break;
                            }
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
                if (i_ >= len_ || _string.substring(i_).trim().charAt(0) != PAR_LEFT) {
                    tolerateDot_ = true;
                    type_ = ConstType.WORD;
                    info_.setKind(type_);
                    info_.setFirstChar(beginWord_);
                    info_.setLastChar(i_);
                    info_.setName(_string.substring(beginWord_, i_));
                    d_.getVariables().add(info_);
                }
                String nextPart_ = _string.substring(i_).trim();
                if (!tolerateDot_ && !nextPart_.isEmpty() && nextPart_.charAt(0) == DOT_VAR) {
                    d_.setBadOffset(i_);
                    return d_;
                }
                if (!ElResolver.isCorrectNbEndWord(nextPart_, end_)) {
                    d_.setBadOffset(i_+1);
                    return d_;
                }
                continue;
            }
            if (curChar_ == DOT_VAR) {
                if (ElResolver.isNumber(i_ + 1, len_, _string)) {
                    NumberInfosOutput res_ = ElResolver.processNb(i_ + 1, len_, _string, true);
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
                    if (ElResolver.isNumber(0, lenLoc_, nextPart_)) {
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
            if (curChar_ == SEP_ARG) {
                if (parsBrackets_.isEmpty()) {
                    d_.setBadOffset(i_);
                    return d_;
                }
            }
            boolean pureBinaryOp_ = false;
            if (curChar_ == PLUS_CHAR || curChar_ == MINUS_CHAR) {
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
                plusMinus_ = true;
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
            if (curChar_ == ARR_LEFT) {
                idOp_ = true;
            }
            if (curChar_ == ARR_RIGHT) {
                idOp_ = true;
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
        if (_string.charAt(i_) == EXTERN_CLASS) {
            int begin_;
            int end_;
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
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.STATIC_ACCESS);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string, firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            String sub_ = _string.substring(firstPrintChar_ + 1, len_);
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
        if (_string.charAt(firstPrintChar_) == MINUS_CHAR || _string.charAt(firstPrintChar_) == PLUS_CHAR) {
            prio_ = UNARY_PRIO;
            operators_.put(firstPrintChar_, String.valueOf(_string.charAt(firstPrintChar_)));
            i_ = ElResolver.incrementUnary(_string, firstPrintChar_ + 1, lastPrintChar_, _offset, _d);
        } else if (_string.charAt(firstPrintChar_) == NEG_BOOL_CHAR) {
            if (firstPrintChar_ < lastPrintChar_ && _string.charAt(firstPrintChar_+1) != EQ_CHAR) {
                prio_ = UNARY_PRIO;
                operators_.put(firstPrintChar_, String.valueOf(NEG_BOOL_CHAR));
                i_ = ElResolver.incrementUnary(_string, firstPrintChar_ + 1, lastPrintChar_, _offset, _d);
            }
        } else if (_d.getDelCast().contains(firstPrintChar_+_offset)) {
            prio_ = UNARY_PRIO;
            int min_ = _d.getDelCast().indexOfObj(firstPrintChar_+_offset);
            int max_ = _d.getDelCast().get(min_ + 1) - _offset;
            operators_.put(firstPrintChar_, _string.substring(firstPrintChar_, max_ + 1));
            i_ = ElResolver.incrementUnary(_string, firstPrintChar_, lastPrintChar_, _offset, _d);
        } else {
            if (iVar_ > -1) {
                i_ = iVar_ - _offset;
                enPars_ = false;
                operators_.put(i_, EMPTY_STRING);
            }
        }
        boolean useFct_ = false;
        String fctName_ = EMPTY_STRING;
        boolean enabledId_ = false;
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (_d.getAllowedOperatorsIndexes().containsObj(i_+_offset)) {
                if (curChar_ == PAR_LEFT) {
                    if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO) {
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
                if (curChar_ == SEP_ARG && parsBrackets_.size() == 1 && prio_ >= FCT_OPER_PRIO && enPars_) {
                    operators_.put(i_, String.valueOf(SEP_ARG));
                }
                if (curChar_ == PAR_RIGHT) {
                    parsBrackets_.removeKey(parsBrackets_.lastKey());
                    if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO && enPars_) {
                        operators_.put(i_, String.valueOf(PAR_RIGHT));
                    }
                }
                if (curChar_ == ARR_LEFT) {
                    if (parsBrackets_.isEmpty()) {
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
                    if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO) {
                        if (!operators_.lastValue().isEmpty()) {
                            operators_.put(i_, String.valueOf(ARR_RIGHT));
                        }
                        enabledId_ = true;
                    }
                }
                if (parsBrackets_.isEmpty()) {
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
                        if (i_ + 1 < _string.length()) {
                            char nextChar_ = _string.charAt(i_ + 1);
                            if (nextChar_ != EQ_CHAR) {
                                processNextEq_ = false;
                            }
                        } else {
                            processNextEq_ = false;
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
                        } else {
                            builtOperator_.append(curChar_);
                            if (prio_ > AFF_PRIO) {
                                clearOperators_ = true;
                                prio_ = AFF_PRIO;
                                foundOperator_ = true;
                            }
                            increment_ = 1;
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

                        if (prio_ > prioOpMult_) {
                            prio_ = prioOpMult_;
                        }
                        if (prio_ == prioOpMult_) {
                            clearOperators_ = true;
                            foundOperator_ = true;
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
        op_.setDeclaring(false);
        op_.setPriority(prio_);
        op_.setOperators(operators_);
        op_.setUseFct(useFct_);
        op_.setFctName(fctName_);
        op_.setupValues(_string, false);
        op_.setDelimiter(_d);
        return op_;
    }

}
