package code.expressionlanguage;
import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;


public final class ElResolver {

    public static final int BAD_PRIO = -1;
    public static final int CONST_PRIO = 0;
    public static final int OR_PRIO = 1;
    public static final int AND_PRIO = 2;
    public static final int EQ_PRIO = 3;
    public static final int CMP_PRIO = 4;
    public static final int ADD_PRIO = 5;
    public static final int MULT_PRIO = 6;
    public static final int UNARY_PRIO = 7;
    public static final int DOT_PRIO = 8;
    public static final int ARR_OPER_PRIO = 9;
    public static final int FCT_OPER_PRIO = 10;
    public static final byte UNICODE_SIZE = 4;

    private static final String RETURN_LINE = "\n";
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
    private static final char EXTERN_CLASS = '$';
    private static final String CLASS_CHOICE = "classchoice";
    private static final String INSTANCE = "new";
    private static final String SUPER = "super";
    private static final String STATIC_ACCESS = "static";
    private static final String VAR_ARG = "vararg";
    private static final String FIRST_OPT = "firstopt";
    private static final String CLASS = "class";
    private static final String BOOLEAN = "bool";
    private static final String INSTANCEOF = "instanceof";
    private static final String THIS = "this";
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

    static Delimiters checkSyntaxDelimiters(String _string, ContextEl _conf, int _minIndex, char _begin, char _end) {
        Delimiters d_ = new Delimiters();
        d_.setBegin(_begin);
        d_.setEnd(_end);
        d_.setPartOfString(true);
        return commonCheck(_string, _conf, _minIndex, d_);
    }

    static Delimiters checkSyntax(String _string, ContextEl _conf, int _elOffest) {
        return commonCheck(_string, _conf, _elOffest, new Delimiters());
    }
    static Delimiters commonCheck(String _string, ContextEl _conf, int _minIndex, Delimiters _d) {
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
            _conf.getLastPage().setOffset(i_);
            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
        }
        int firstPrintableWordChar_ = i_;
        while (i_ < len_) {
            if (!StringList.isWordChar(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        if (i_ < len_ && _string.charAt(i_) == GET_VAR) {
            i_++;
            boolean spaces_ = false;
            while (i_ < len_) {
                if (_string.charAt(i_) == GET_VAR) {
                    i_++;
                    continue;
                }
                if (_string.charAt(i_) == DOT_VAR) {
                    i_++;
                    continue;
                }
                if (Character.isWhitespace(_string.charAt(i_))) {
                    spaces_ = true;
                }
                break;
            }
            if (spaces_) {
                int j_ = i_+1;
                while (j_ < len_) {
                    if (Character.isWhitespace(_string.charAt(j_))) {
                        j_++;
                        continue;
                    }
                    if (_string.charAt(j_) == GET_VAR) {
                        _conf.getLastPage().setOffset(i_+1);
                        throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                    }
                    if (_string.charAt(j_) == DOT_VAR) {
                        _conf.getLastPage().setOffset(i_+1);
                        throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                    }
                    break;
                }
            }
            while (i_ < len_) {
                if (!Character.isWhitespace(_string.charAt(i_))) {
                    break;
                }
                i_++;
            }
        } else {
            i_ = firstPrintableWordChar_;
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
                    _conf.getLastPage().setOffset(-index_);
                    throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                }
                if (!res_.isPart()) {
                    d_.getDelStringsChars().add(i_);
                    constChar_ = false;
                    enabledMinus_ = true;
                    i_++;
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
                    _conf.getLastPage().setOffset(-index_);
                    throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                }
                if (!res_.isPart()) {
                    d_.getDelStringsChars().add(i_);
                    constString_ = false;
                    enabledMinus_ = true;
                    i_++;
                    continue;
                }
                i_ = index_;
                escapedMeta_ = res_.isEscape();
                nbChars_ = res_.getNbChars();
                unicode_ = res_.getUnicode();
                continue;
            }
            if (curChar_ == EXTERN_CLASS) {
                enabledMinus_ = true;
                hatMethod_ = true;
                if (i_ + 1 < len_) {
                    char nextChar_ = _string.charAt(i_ + 1);
                    if (Character.isWhitespace(nextChar_)) {
                        _conf.getLastPage().setOffset(i_+1);
                        throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                    }
                    if (procWordFirstChar(_string, i_ + 1, INSTANCE, len_)) {
                        int j_ = i_ + 1;
                        while (j_ < len_) {
                            if (_string.charAt(j_) == PAR_LEFT) {
                                hatMethod_ = false;
                                break;
                            }
                            j_++;
                        }
                        if (j_ >= len_) {
                            _conf.getLastPage().setOffset(len_ - 1);
                            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                        }
                        i_ = j_;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, STATIC_ACCESS, len_)) {
                        int afterStatic_ = i_ + 1 + STATIC_ACCESS.length();
                        if (afterStatic_ < len_) {
                            if (_string.charAt(afterStatic_) != EXTERN_CLASS) {
                                _conf.getLastPage().setOffset(afterStatic_);
                                throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                            }
                        } else {
                            _conf.getLastPage().setOffset(len_ - 1);
                            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                        }
                        int j_ = i_ + 1;
                        while (j_ < len_) {
                            if (_string.charAt(j_) == DOT_VAR) {
                                hatMethod_ = false;
                                break;
                            }
                            j_++;
                        }
                        if (j_ >= len_) {
                            _conf.getLastPage().setOffset(len_ - 1);
                            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                        }
                        d_.getDelKeyWordStatic().add(i_);
                        d_.getDelKeyWordStatic().add(j_);
                        i_ = j_;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, SUPER, len_)) {
                        int afterSuper_ = i_ + 1 + SUPER.length();
                        boolean foundHat_ = false;
                        while (afterSuper_ < len_) {
                            if (_string.charAt(afterSuper_) == EXTERN_CLASS) {
                                foundHat_ = true;
                                break;
                            }
                            if (!Character.isWhitespace(_string.charAt(afterSuper_))) {
                                if (_string.charAt(afterSuper_) != PAR_LEFT) {
                                    _conf.getLastPage().setOffset(afterSuper_);
                                    throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                                }
                                hatMethod_ = false;
                                break;
                            }
                            afterSuper_++;
                        }
                        if (afterSuper_ + 1 >= len_) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
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
                            if (!StringList.isWordChar(_string.charAt(afterSuper_))) {
                                if (_string.charAt(afterSuper_) == EXTERN_CLASS) {
                                    _conf.getLastPage().setOffset(afterSuper_);
                                    throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                                }
                                hatMethod_ = false;
                                break;
                            }
                            afterSuper_++;
                        }
                        while (afterSuper_ < len_) {
                            if (_string.charAt(afterSuper_) != GET_VAR) {
                                break;
                            }
                            afterSuper_++;
                        }
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
                    if (procWordFirstChar(_string, i_ + 1, CLASS_CHOICE, len_)) {
                        int afterClassChoice_ = i_ + 1 + CLASS_CHOICE.length();
                        boolean foundHat_ = false;
                        while (afterClassChoice_ < len_) {
                            if (_string.charAt(afterClassChoice_) == EXTERN_CLASS) {
                                foundHat_ = true;
                                break;
                            }
                            if (!Character.isWhitespace(_string.charAt(afterClassChoice_))) {
                                _conf.getLastPage().setOffset(afterClassChoice_);
                                throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                            }
                            afterClassChoice_++;
                        }
                        if (!foundHat_) {
                            _conf.getLastPage().setOffset(len_ - 1);
                            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                        }
                        if (afterClassChoice_ + 1 >= len_) {
                            _conf.getLastPage().setOffset(afterClassChoice_);
                            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                        }
                        boolean foundHats_ = false;
                        afterClassChoice_++;
                        while (afterClassChoice_ < len_) {
                            if (_string.charAt(afterClassChoice_) == EXTERN_CLASS) {
                                if (afterClassChoice_ + 1 >= len_) {
                                    _conf.getLastPage().setOffset(afterClassChoice_);
                                    throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                                }
                                if (_string.charAt(afterClassChoice_ + 1) == EXTERN_CLASS) {
                                    hatMethod_ = false;
                                    foundHats_ = true;
                                    //afterClassChoice_ + 1 < _string.length()
                                    break;
                                }
                            }
                            afterClassChoice_++;
                        }
                        if (!foundHats_) {
                            _conf.getLastPage().setOffset(len_ - 1);
                            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                        }
                        if (afterClassChoice_ + 2 >= len_) {
                            _conf.getLastPage().setOffset(afterClassChoice_);
                            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                        }
                        //afterClassChoice_ + 2 < len_
                        if (_string.charAt(afterClassChoice_ + 2) == EXTERN_CLASS) {
                            _conf.getLastPage().setOffset(afterClassChoice_);
                            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                        }
                        afterClassChoice_++;
                        afterClassChoice_++;
                        //afterClassChoice_ < len_
                        while (afterClassChoice_ < len_) {
                            if (!StringList.isWordChar(_string.charAt(afterClassChoice_))) {
                                break;
                            }
                            afterClassChoice_++;
                        }
                        if (afterClassChoice_ >= len_) {
                            _conf.getLastPage().setOffset(afterClassChoice_ - 1);
                            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                        }
                        int nbSuffix_ = 0;
                        while (afterClassChoice_ < len_) {
                            if (_string.charAt(afterClassChoice_) != GET_VAR) {
                                break;
                            }
                            nbSuffix_++;
                            afterClassChoice_++;
                        }
                        if (afterClassChoice_ >= len_ || _string.charAt(afterClassChoice_) != PAR_LEFT) {
                            if (nbSuffix_ != GET_FIELD.length()) {
                                _conf.getLastPage().setOffset(afterClassChoice_ - 1);
                                throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                            }
                        }
                        if (afterClassChoice_ < len_) {
                            if (_string.charAt(afterClassChoice_) != PAR_LEFT) {
                                d_.getDelKeyWordClassChoice().add(i_);
                                d_.getDelKeyWordClassChoice().add(afterClassChoice_);
                            }
                        } else {
                            d_.getDelKeyWordClassChoice().add(i_);
                            d_.getDelKeyWordClassChoice().add(afterClassChoice_);
                        }
                        i_ = afterClassChoice_;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, THIS, len_)) {
                        int afterSuper_ = i_ + 1 + THIS.length();
                        boolean foundHat_ = false;
                        while (afterSuper_ < len_) {
                            if (_string.charAt(afterSuper_) == EXTERN_CLASS) {
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
                            hatMethod_ = false;
                            i_ = afterSuper_;
                            continue;
                        }
                        if (afterSuper_ + 1 >= len_) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                        }
                        afterSuper_++;
                        while (afterSuper_ < len_) {
                            if (Character.isWhitespace(_string.charAt(afterSuper_))) {
                                afterSuper_++;
                                continue;
                            }
                            if (!StringList.isWordChar(_string.charAt(afterSuper_))) {
                                if (_string.charAt(afterSuper_) == EXTERN_CLASS) {
                                    _conf.getLastPage().setOffset(afterSuper_);
                                    throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                                }
                                hatMethod_ = false;
                                break;
                            }
                            afterSuper_++;
                        }
                        i_ = afterSuper_;
                        continue;
                    }
                    boolean foundValue_ = false;
                    for (String s: StringList.wrapStringArray(TRUE_STRING,FALSE_STRING,NULL_REF_STRING)) {
                        if (procWordFirstChar(_string, i_ + 1, s, len_)) {
                            int afterSuper_ = i_ + 1 + s.length();
                            while (afterSuper_ < len_) {
                                if (_string.charAt(afterSuper_) == EXTERN_CLASS) {
                                    _conf.getLastPage().setOffset(afterSuper_);
                                    throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                                }
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
                    for (String s: StringList.wrapStringArray(VAR_ARG,FIRST_OPT,CLASS,INSTANCEOF,BOOLEAN)) {
                        if (procWordFirstChar(_string, i_ + 1, s, len_)) {
                            int index_ = processPredefinedMethod(_string, i_, s, len_);
                            if (index_ < 0) {
                                _conf.getLastPage().setOffset(-index_);
                                throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
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
                _conf.getLastPage().setOffset(i_);
                throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
            }
            if (StringList.isWordChar(curChar_)) {
                enabledMinus_ = true;
                if (i_ + 1 < len_) {
                    if (Character.isWhitespace(_string.charAt(i_ + 1))) {
                        int j_ = i_ + 2;
                        while (j_ < len_) {
                            if (Character.isWhitespace(_string.charAt(j_))) {
                                j_++;
                                continue;
                            }
                            if (StringList.isWordChar(_string.charAt(j_))) {
                                _conf.getLastPage().setOffset(i_+1);
                                throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                            }
                            if (_string.charAt(j_) == GET_VAR) {
                                _conf.getLastPage().setOffset(i_+1);
                                throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                            }
                            break;
                        }
                    }
                }
                if (isNumber(i_, len_, _string)) {
                    int res_ = processNb(i_, len_, firstPrintableWordChar_, _string, false);
                    if (res_ < 0) {
                        _conf.getLastPage().setOffset(-res_);
                        throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                    }
                    d_.getDelNumbers().add(i_);
                    d_.getDelNumbers().add(res_);
                    i_ = res_;
                    continue;
                }
                while (i_ < len_) {
                    if (!StringList.isWordChar(_string.charAt(i_))) {
                        break;
                    }
                    i_++;
                }
                continue;
            }
            if (curChar_ == DOT_VAR) {
                if (isNumber(i_ + 1, len_, _string)) {
                    int res_ = processNb(i_ + 1, len_, firstPrintableWordChar_, _string, true);
                    if (res_ < 0) {
                        _conf.getLastPage().setOffset(-res_);
                        throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                    }
                    d_.getDelNumbers().add(i_);
                    d_.getDelNumbers().add(res_);
                    i_ = res_;
                    continue;
                }
                if (i_ + 1 < len_ && Character.isWhitespace(_string.charAt(i_ + 1))) {
                    String nextPart_ = _string.substring(i_ + 1).trim();
                    int lenLoc_ = nextPart_.length();
                    if (isNumber(0, lenLoc_, nextPart_)) {
                        _conf.getLastPage().setOffset(-i_-1);
                        throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                    }
                }
                if (parsBrackets_.isEmpty()) {
                    if (i_ + 1 >= len_) {
                        if (!foundSemiColumn_) {
                            _conf.getLastPage().setOffset(i_);
                            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                        }
                    }
                }
            }
            if (curChar_ == ESCAPE_META_CHAR) {
                _conf.getLastPage().setOffset(i_);
                throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
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
            if (curChar_ == NEG_BOOL_CHAR || curChar_ == LOWER_CHAR || curChar_ == GREATER_CHAR) {
                int j_ = i_ + 1;
                boolean exist_ = false;
                while (j_ < len_) {
                    if (Character.isWhitespace(_string.charAt(j_))) {
                        exist_ = true;
                        j_++;
                        continue;
                    }
                    if (_string.charAt(j_) == EQ_CHAR && exist_) {
                        _conf.getLastPage().setOffset(i_+1);
                        throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                    }
                    break;
                }
            }
            if (curChar_ == PAR_LEFT) {
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == PAR_RIGHT) {
                if (parsBrackets_.isEmpty()) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                }
                if (parsBrackets_.lastValue() != PAR_LEFT) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                }
                parsBrackets_.removeKey(parsBrackets_.lastKey());
            }
            if (curChar_ == ARR_LEFT) {
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == ARR_RIGHT) {
                if (parsBrackets_.isEmpty()) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                }
                if (parsBrackets_.lastValue() != ARR_LEFT) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                }
                parsBrackets_.removeKey(parsBrackets_.lastKey());
            }
            if (curChar_ == SEP_ARG) {
                if (parsBrackets_.isEmpty()) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
                }
            }
            boolean pureBinaryOp_ = false;
            if (curChar_ == PLUS_CHAR) {
                pureBinaryOp_ = true;
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
            if (pureBinaryOp_) {
                enabledMinus_ = false;
            } else if (!Character.isWhitespace(curChar_) && curChar_ != MINUS_CHAR){
                enabledMinus_ = true;
            }
            if (!enabledMinus_ && curChar_ == MINUS_CHAR) {
                i_++;
                continue;
            }
            d_.getAllowedOperatorsIndexes().add(i_);
            if (curChar_ == MINUS_CHAR) {
                enabledMinus_ = false;
            }
            if (partOfString_ && curChar_ == end_) {
                partOfString_ = false;
                break;
            }
            if (partOfString_ && curChar_ == begin_) {
                _conf.getLastPage().setOffset(i_);
                throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
            }
            i_++;
        }
        if (hatMethod_) {
            _conf.getLastPage().setOffset(i_);
            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
        }
        if (constString_) {
            _conf.getLastPage().setOffset(i_);
            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
        }
        if (constChar_) {
            _conf.getLastPage().setOffset(i_);
            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
        }
        if (!parsBrackets_.isEmpty()) {
            _conf.getLastPage().setOffset(i_);
            throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
        }
        if (!partOfString_) {
            d_.setIndexBegin(_minIndex);
            d_.setIndexEnd(i_-1);
            return d_;
        }
        _conf.getLastPage().setOffset(i_);
        throw new BadExpressionLanguageException(StringList.concat(_string,RETURN_LINE,_conf.joinPages()));
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
            nbChars_ ++;
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
                nbChars_ ++;
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
            nbChars_ ++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == IND_LINE) {
            nbChars_ ++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == IND_FORM) {
            nbChars_ ++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == IND_BOUND) {
            nbChars_ ++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == IND_LINE_FEED) {
            nbChars_ ++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == IND_TAB) {
            nbChars_ ++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == ESCAPE_META_CHAR) {
            nbChars_ ++;
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
        if (afterSuper_ >= _max) {
            return -afterSuper_;
        }
        if (_string.charAt(afterSuper_) != PAR_LEFT) {
            return -afterSuper_;
        }
        return afterSuper_;
    }

    private static boolean isNumber(int _start, int _max, String _string) {
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

    private static int processNb(int _start, int _max, int _firstPrint, String _string, boolean _seenDot) {
        //_string.charAt(_start) is digit
        int len_ = _max;
        int j_ = _start + 1;
        boolean dot_ = false;
        boolean exp_ = false;
        int iExp_ = j_;
        while (j_ < len_) {
            if (!StringList.isWordChar(_string.charAt(j_))) {
                if (_string.charAt(j_) == DOT_VAR) {
                    if (_seenDot) {
                        return -j_;
                    }
                    if (j_ + 1 < len_ && Character.isWhitespace(_string.charAt(j_ + 1))) {
                        String nextPart_ = _string.substring(j_ + 1).trim();
                        if (nextPart_.isEmpty()) {
                            return j_ + 1;
                        }
                        if (StringList.isWordChar(nextPart_.charAt(0)) || nextPart_.charAt(0) == DOT_VAR) {
                            return -(j_ + 1);
                        }
                    }
                    if (j_ + 1 < len_ && _string.charAt(j_ + 1) == DOT_VAR) {
                        return -(j_ + 1);
                    }
                    dot_ = true;
                }
                break;
            }
            if (Character.toLowerCase(_string.charAt(j_)) == EXP) {
                if (exp_) {
                    return -j_;
                }
                if (j_ + 1 < len_ && Character.isWhitespace(_string.charAt(j_ + 1))) {
                    return -(j_ + 1);
                }
                iExp_ = j_;
                exp_ = true;
            } else if (Character.isLetter(_string.charAt(j_))) {
                if (!isNbSuffix(_string.charAt(j_))) {
                    return -j_;
                }
                String nextPart_ = _string.substring(j_ + 1).trim();
                if (nextPart_.isEmpty()) {
                    return j_ + 1;
                }
                if (StringList.isWordChar(nextPart_.charAt(0)) || nextPart_.charAt(0) == DOT_VAR) {
                    return -j_;
                }
                return j_ + 1;
            }
            j_++;
        }
        if (dot_ && exp_) {
            return -j_;
        }
        if (j_ + 1 >= len_ && dot_) {
            return j_ + 1;
        }
        if (dot_) {
            char next_ = _string.charAt(j_ + 1);
            if (Character.toLowerCase(next_) == EXP) {
                exp_ = true;
            }
            if (!Character.isDigit(next_) && next_ != NB_INTERN_SP && !exp_) {
                if (Character.isLetter(next_)) {
                    if (!isNbSuffix(next_)) {
                        return -(j_ + 1);
                    }
                    String nextPart_ = _string.substring(j_ + 2).trim();
                    if (nextPart_.isEmpty()) {
                        return j_ + 2;
                    }
                    char afterNext_ = nextPart_.charAt(0);
                    if (afterNext_ == DOT_VAR || StringList.isWordChar(afterNext_)) {
                        return -(j_ + 2);
                    }
                    return j_ + 2;
                }
                return j_ + 1;
            }
            j_++;
            if (exp_) {
                //_string.charAt(j_) == EXP
                return processExp(j_, len_, _string);
            }
            while (j_ < len_) {
                if (!Character.isDigit(_string.charAt(j_)) && _string.charAt(j_) != NB_INTERN_SP) {
                    break;
                }
                j_++;
            }
            if (j_ >= len_) {
                return j_;
            }
            if (Character.toLowerCase(_string.charAt(j_)) == EXP) {
                return processExp(j_, len_, _string);
            }
            if (Character.isLetter(_string.charAt(j_)) && !isNbSuffix(_string.charAt(j_))) {
                return -j_;
            }
            if (Character.isLetter(_string.charAt(j_))) {
                // => isNbSuffix(_string.charAt(j_))
                j_++;
            }
            String nextPart_ = _string.substring(j_).trim();
            if (!nextPart_.isEmpty() && nextPart_.charAt(0) == DOT_VAR) {
                return -j_;
            }
            if (!nextPart_.isEmpty() && StringList.isWordChar(nextPart_.charAt(0))) {
                return -j_;
            }
            return j_;
        }
        if (iExp_ + 1 >= len_ && exp_) {
            return -j_;
        }
        if (exp_) {
            //_string.charAt(iExp_) == EXP
            return processExp(iExp_, len_, _string);
        }
        if (j_ < len_ && Character.isWhitespace(_string.charAt(j_))) {
            String next_ = _string.substring(j_ + 1).trim();
            if (!next_.isEmpty() && next_.charAt(0) == DOT_VAR) {
                return -j_;
            }
            if (!next_.isEmpty() && StringList.isWordChar(next_.charAt(0))) {
                return -j_;
            }
        }
        return j_;
    }
    private static boolean isNbSuffix(char _char) {
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

    private static int processExp(int _start, int _max, String _string) {
        int len_ = _max;
        int j_ = _start;
        j_++;
        if (_string.charAt(j_) == MINUS_CHAR) {
            j_++;
        }
        if (!Character.isDigit(_string.charAt(j_))) {
            return -j_;
        }
        while (j_ < len_) {
            if (!Character.isDigit(_string.charAt(j_)) && _string.charAt(j_) != NB_INTERN_SP) {
                break;
            }
            j_++;
        }
        String nextPart_ = _string.substring(j_).trim();
        if (!nextPart_.isEmpty() && nextPart_.charAt(0) == DOT_VAR) {
            return -j_;
        }
        if (j_ < len_ && Character.isLetter(_string.charAt(j_))) {
            if (!isNbSuffix(_string.charAt(j_))) {
                return -j_;
            }
            j_++;
        }
        nextPart_ = _string.substring(j_).trim();
        if (!nextPart_.isEmpty() && StringList.isWordChar(nextPart_.charAt(0))) {
            return -j_;
        }
        if (!nextPart_.isEmpty() &&  nextPart_.charAt(0) == DOT_VAR) {
            return -j_;
        }
        return j_;
    }
    public static OperationsSequence getOperationsSequence(int _offset, String _string,
            ContextEl _conf, Delimiters _d) {
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
            op_.setupValues(_string);
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
        len_ = lastPrintChar_+1;
        if (_string.charAt(i_) == EXTERN_CLASS) {
            int begin_;
            int end_;
            begin_ = _d.getDelKeyWordStatic().indexOfObj(_offset + firstPrintChar_);
            end_ = _d.getDelKeyWordStatic().indexOfObj(_offset + lastPrintChar_ + 1);
            if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.STATIC_ACCESS);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setupValues(_string);
                op_.setDelimiter(_d);
                return op_;
            }
            String sub_ = _string.substring(firstPrintChar_ + 1, len_);
            if (StringList.quickEq(sub_, THIS)) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.THIS_KEYWORD);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setupValues(_string);
                op_.setDelimiter(_d);
                return op_;
            }
            if (StringList.quickEq(sub_, NULL_REF_STRING)) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.NULL_CST);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setupValues(_string);
                op_.setDelimiter(_d);
                return op_;
            }
            if (StringList.quickEq(sub_, TRUE_STRING)) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.TRUE_CST);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setupValues(_string);
                op_.setDelimiter(_d);
                return op_;
            }
            if (StringList.quickEq(sub_, FALSE_STRING)) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.FALSE_CST);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setupValues(_string);
                op_.setDelimiter(_d);
                return op_;
            }
            begin_ = _d.getDelKeyWordSuper().indexOfObj(_offset + firstPrintChar_);
            end_ = _d.getDelKeyWordSuper().indexOfObj(_offset + lastPrintChar_ + 1);
            if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.SUPER_KEYWORD);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string.substring(firstPrintChar_+SUPER.length() + 2, lastPrintChar_ - GET_FIELD.length() + 1), firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            begin_ = _d.getDelKeyWordClassChoice().indexOfObj(_offset + firstPrintChar_);
            end_ = _d.getDelKeyWordClassChoice().indexOfObj(_offset + lastPrintChar_ + 1);
            if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.CLASSCHOICE_KEYWORD);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string.substring(firstPrintChar_, lastPrintChar_ - GET_FIELD.length() + 1),firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
        }
        int firstNbChar_ = firstPrintChar_;
        if (_string.charAt(firstPrintChar_) == MINUS_CHAR) {
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
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isCustomField(_string, firstPrintChar_, lastPrintChar_, GET_FIELD)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.CUST_FIELD);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string.substring(firstPrintChar_, lastPrintChar_ - GET_FIELD.length() + 1),firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isCustomField(_string, firstPrintChar_, lastPrintChar_, GET_PARAM)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.PARAM);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string.substring(firstPrintChar_, lastPrintChar_ - GET_PARAM.length() + 1),firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isCustomField(_string, firstPrintChar_, lastPrintChar_, GET_CATCH_VAR)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.CATCH_VAR);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string.substring(firstPrintChar_, lastPrintChar_ - GET_CATCH_VAR.length() + 1),firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isCustomField(_string, firstPrintChar_, lastPrintChar_, GET_LOC_VAR)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.LOC_VAR);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string.substring(firstPrintChar_, lastPrintChar_ - GET_LOC_VAR.length() + 1),firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isCustomField(_string, firstPrintChar_, lastPrintChar_, GET_INDEX)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.LOOP_INDEX);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string.substring(firstPrintChar_, lastPrintChar_ - GET_INDEX.length() + 1),firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isCustomField(_string, firstPrintChar_, lastPrintChar_, GET_ATTRIBUTE)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.LOOP_VAR);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string.substring(firstPrintChar_, lastPrintChar_ - GET_ATTRIBUTE.length() + 1),firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isWord(_string, firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.WORD);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
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
                op_.setupValues(_string);
                op_.setDelimiter(_d);
                return op_;
            }
            if (_string.charAt(firstPrintChar_) == DELIMITER_STRING) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.STRING);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setupValues(_string);
                op_.setDelimiter(_d);
                return op_;
            }
        }
        if (_string.charAt(firstPrintChar_) == MINUS_CHAR) {
            prio_ = UNARY_PRIO;
            operators_.put(firstPrintChar_, String.valueOf(MINUS_CHAR));
            i_ += getIncrement(_string, firstPrintChar_ + 1, lastPrintChar_);
        } else if (_string.charAt(firstPrintChar_) == NEG_BOOL_CHAR) {
            if (firstPrintChar_ < lastPrintChar_ && _string.charAt(firstPrintChar_+1) != EQ_CHAR) {
                prio_ = UNARY_PRIO;
                operators_.put(firstPrintChar_, String.valueOf(NEG_BOOL_CHAR));
                i_ += getIncrement(_string, firstPrintChar_ + 1, lastPrintChar_);
            }
        } else {
            while (true) {
                if (!StringList.isWordChar(_string.charAt(i_))) {
                    break;
                }
                i_++;
            }
            //i_ < len_
            if (_string.charAt(i_) == GET_VAR) {
                i_++;
                while (true) {
                    if (_string.charAt(i_) == GET_VAR) {
                        i_++;
                        continue;
                    }
                    if (_string.charAt(i_) == DOT_VAR) {
                        i_++;
                        continue;
                    }
                    break;
                }
                //i_ < len_
                int j_ = i_;
                while (true) {
                    if (!Character.isWhitespace(_string.charAt(j_))) {
                        break;
                    }
                    j_++;
                }
                //j_ < len_
                if (_string.charAt(j_) != ARR_LEFT) {
                    prio_ = DOT_PRIO;
                    operators_.put(i_, EMPTY_STRING);
                }
            }
        }
        boolean useFct_ = false;
        String fctName_ = EMPTY_STRING;
        int quoted_ = 0;
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (_d.getDelStringsChars().containsObj(i_+_offset)) {
                quoted_++;
                i_++;
                continue;
            }
            if (quoted_ % 2 == 1) {
                i_++;
                continue;
            }
            if (StringList.isWordChar(curChar_)) {
                while (i_ < len_) {
                    if (!StringList.isWordChar(_string.charAt(i_))) {
                        break;
                    }
                    i_++;
                }
                continue;
            }
            if (_d.getAllowedOperatorsIndexes().containsObj(i_+_offset)) {
                if (curChar_ == PAR_LEFT) {
                    if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO) {
                        useFct_ = true;
                        fctName_ = _string.substring(CustList.FIRST_INDEX, i_);
                        operators_.put(i_, String.valueOf(PAR_LEFT));
                    }
                    parsBrackets_.put(i_, curChar_);
                }
                if (curChar_ == SEP_ARG && parsBrackets_.size() == 1 && prio_ == FCT_OPER_PRIO) {
                    operators_.put(i_, String.valueOf(SEP_ARG));
                }
                if (curChar_ == PAR_RIGHT) {
                    parsBrackets_.removeKey(parsBrackets_.lastKey());
                    if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO) {
                        operators_.put(i_, String.valueOf(PAR_RIGHT));
                    }
                }
                if (curChar_ == ARR_LEFT) {
                    if (parsBrackets_.isEmpty()) {
                        if (ARR_OPER_PRIO < prio_) {
                            prio_ = ARR_OPER_PRIO;
                            useFct_ = false;
                            fctName_ = EMPTY_STRING;
                        }
                        if (ARR_OPER_PRIO <= prio_) {
                            operators_.clear();
                            operators_.put(i_, ARR);
                        }
                    }
                    parsBrackets_.put(i_, curChar_);
                }
                if (curChar_ == ARR_RIGHT) {
                    parsBrackets_.removeKey(parsBrackets_.lastKey());
                    if (parsBrackets_.isEmpty() && prio_ == ARR_OPER_PRIO) {
                        operators_.put(i_, String.valueOf(ARR_RIGHT));
                    }
                }
                if (parsBrackets_.isEmpty()) {
                    StringBuilder builtOperator_ = new StringBuilder();
                    boolean clearOperators_ = false;
                    boolean foundOperator_ = false;
                    int increment_ = 1;
                    if (curChar_ == DOT_VAR) {
                        builtOperator_.append(DOT_VAR);
                        if (prio_ > DOT_PRIO) {
                            prio_ = DOT_PRIO;
                        }
                        if (prio_ == DOT_PRIO) {
                            clearOperators_ = true;
                            foundOperator_ = true;
                        }
                    }
                    if (curChar_ == NEG_BOOL_CHAR) {
                        builtOperator_.append(NEG_BOOL_CHAR);
                        if (i_ + 1 < _string.length()) {
                            char nextChar_ = _string.charAt(i_ + 1);
                            if (nextChar_ == EQ_CHAR) {
                                if (prio_ > EQ_PRIO) {
                                    clearOperators_ = true;
                                    prio_ = EQ_PRIO;
                                }
                                if (prio_ == EQ_PRIO) {
                                    foundOperator_ = true;
                                    builtOperator_.append(EQ_CHAR);
                                }
                                increment_ = 2;
                            } else {
                                if (prio_ > EQ_PRIO) {
                                    prio_ = EQ_PRIO;
                                    clearOperators_ = true;
                                    foundOperator_ = true;
                                }
                            }
                        } else {
                            prio_ = EQ_PRIO;
                            foundOperator_ = true;
                        }
                    }
                    if (curChar_ == EQ_CHAR) {
                        builtOperator_.append(EQ_CHAR);
                        if (prio_ > EQ_PRIO) {
                            clearOperators_ = true;
                            prio_ = EQ_PRIO;
                        }
                        if (prio_ == EQ_PRIO) {
                            foundOperator_ = true;
                        }
                        increment_ = 1;
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
                        if (foundOperator_) {
                            if (i_ + 1 < _string.length()) {
                                char nextChar_ = _string.charAt(i_ + 1);
                                if (nextChar_ == EQ_CHAR) {
                                    builtOperator_.append(nextChar_);
                                    increment_++;
                                }
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
        op_.setPriority(prio_);
        op_.setOperators(operators_);
        op_.setUseFct(useFct_);
        op_.setFctName(fctName_);
        op_.setupValues(_string);
        op_.setDelimiter(_d);
        return op_;
    }

    static boolean isCustomField(String _string, int _from, int _to, String _suffix) {
        int i_ = _from;
        while (i_ <= _to) {
            if (!StringList.isWordChar(_string.charAt(i_))) {
                return StringList.quickEq(_string.substring(i_, _to +1),_suffix);
            }
            i_++;
        }
        return false;
    }
    static boolean isWord(String _string, int _from, int _to) {
        int i_ = _from;
        while (i_ <= _to) {
            if (!StringList.isWordChar(_string.charAt(i_))) {
                return false;
            }
            i_++;
        }
        return true;
    }

    static int getIncrement(String _string, int _from, int _to) {
        int increment_ = 1;
        int j_ = _from;
        while (j_ <= _to) {
            char ch_ = _string.charAt(j_);
            if (ch_ != MINUS_CHAR) {
                if (ch_ != NEG_BOOL_CHAR) {
                    if (!Character.isWhitespace(ch_)) {
                        break;
                    }
                }
            }
            increment_++;
            j_++;
        }
        return increment_;
    }

    static boolean procWordFirstChar(String _string, int _i, String _word, int _max) {
        int len_ = _max;
        int wordLength_ = _word.length();
        if (_i + wordLength_ <= len_) {
            boolean process_ = true;
            if (_i + wordLength_ < len_) {
                if (StringList.isWordChar(_string.charAt(_i + wordLength_))) {
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
}
