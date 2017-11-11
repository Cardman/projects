package code.expressionlanguage;
import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;


public final class ElResolver {

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
    private static final char EXTERN_CLASS = '^';
    private static final String CLASS_CHOICE = "classchoice";
    private static final String STATIC_CALL = "^^";
    private static final String INSTANCE = "new";
    private static final String SUPER = "super";
    private static final String STATIC_ACCESS = "static";
    private static final String VAR_ARG = "vararg";
    private static final String FIRST_OPT = "firstopt";
    private static final String CLASS = "class";
    private static final String BOOLEAN = "boolean";
    private static final String INSTANCEOF = "instanceof";
    private static final String THIS = "this";

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
            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
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
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                    }
                    if (_string.charAt(j_) == DOT_VAR) {
                        _conf.getLastPage().setOffset(i_+1);
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
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
        i_ = _minIndex;
        int nbChars_ = 0;
        int beginCharString_ = 0;
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
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                if (!res_.isPart()) {
                    d_.getDelimitersStringsChars().put(beginCharString_-_minIndex, i_-_minIndex);
                    constChar_ = false;
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
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                if (!res_.isPart()) {
                    d_.getDelimitersStringsChars().put(beginCharString_-_minIndex, i_-_minIndex);
                    constString_ = false;
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
                hatMethod_ = true;
                if (i_ + 1 < len_) {
                    char nextChar_ = _string.charAt(i_ + 1);
                    if (Character.isWhitespace(nextChar_)) {
                        _conf.getLastPage().setOffset(i_+1);
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
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
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        i_ = j_;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, STATIC_ACCESS, len_)) {
                        int afterStatic_ = i_ + 1 + STATIC_ACCESS.length();
                        boolean foundHat_ = false;
                        while (afterStatic_ < len_) {
                            if (_string.charAt(afterStatic_) == EXTERN_CLASS) {
                                foundHat_ = true;
                                break;
                            }
                            if (!Character.isWhitespace(_string.charAt(afterStatic_))) {
                                _conf.getLastPage().setOffset(afterStatic_);
                                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                            }
                            afterStatic_++;
                        }
                        if (!foundHat_) {
                            _conf.getLastPage().setOffset(len_ - 1);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
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
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
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
                                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                                }
                                hatMethod_ = false;
                                break;
                            }
                            afterSuper_++;
                        }
                        if (!foundHat_) {
                            i_ = afterSuper_;
                            continue;
                        }
                        if (afterSuper_ + 1 >= len_) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
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
                                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                                }
                                hatMethod_ = false;
                                break;
                            }
                            afterSuper_++;
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
                                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                            }
                            afterClassChoice_++;
                        }
                        if (!foundHat_) {
                            _conf.getLastPage().setOffset(len_ - 1);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        if (afterClassChoice_ + 1 >= len_) {
                            _conf.getLastPage().setOffset(afterClassChoice_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        boolean foundHats_ = false;
                        afterClassChoice_++;
                        while (afterClassChoice_ < len_) {
                            if (_string.charAt(afterClassChoice_) == EXTERN_CLASS) {
                                if (afterClassChoice_ + 1 >= len_) {
                                    _conf.getLastPage().setOffset(afterClassChoice_);
                                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                                }
                                if (_string.charAt(afterClassChoice_ + 1) == EXTERN_CLASS) {
                                    hatMethod_ = false;
                                    foundHats_ = true;
                                    break;
                                }
                            }
                            afterClassChoice_++;
                        }
                        if (!foundHats_) {
                            _conf.getLastPage().setOffset(len_ - 1);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        if (afterClassChoice_ + 1 >= len_) {
                            _conf.getLastPage().setOffset(afterClassChoice_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        i_ = afterClassChoice_ + 2;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, THIS, len_)) {
                        int afterSuper_ = i_ + 1 + THIS.length();
                        i_ = afterSuper_;
                        hatMethod_ = false;
                        continue;
                    }
                    for (String s: new String[]{VAR_ARG,FIRST_OPT,CLASS,INSTANCEOF,BOOLEAN}) {
                        if (procWordFirstChar(_string, i_ + 1, s, len_)) {
                            int index_ = processPredefinedMethod(_string, i_, s, len_);
                            if (index_ < 0) {
                                _conf.getLastPage().setOffset(-index_);
                                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
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
                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
            }
            if (StringList.isWordChar(curChar_)) {
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
                                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                            }
                            if (_string.charAt(j_) == GET_VAR) {
                                _conf.getLastPage().setOffset(i_+1);
                                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                            }
                            break;
                        }
                    }
                }
                if (isNumber(i_, len_, _string)) {
                    int res_ = processNb(i_, len_, firstPrintableWordChar_, _string, false);
                    if (res_ < 0) {
                        _conf.getLastPage().setOffset(-res_);
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                    }
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
            if (curChar_ == ESCAPE_META_CHAR) {
                _conf.getLastPage().setOffset(i_);
                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
            }
            if (curChar_ == DELIMITER_CHAR) {
                constChar_ = true;
                nbChars_ = 0;
                beginCharString_ = i_;
            }
            if (curChar_ == DELIMITER_STRING) {
                constString_ = true;
                beginCharString_ = i_;
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
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
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
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                if (parsBrackets_.lastValue() != PAR_LEFT) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                d_.getCallings().put(parsBrackets_.lastKey()-_minIndex, i_-_minIndex);
                parsBrackets_.removeKey(parsBrackets_.lastKey());
            }
            if (curChar_ == ARR_LEFT) {
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == ARR_RIGHT) {
                if (parsBrackets_.isEmpty()) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                if (parsBrackets_.lastValue() != ARR_LEFT) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                d_.getCallings().put(parsBrackets_.lastKey()-_minIndex, i_-_minIndex);
                parsBrackets_.removeKey(parsBrackets_.lastKey());
            }
            if (curChar_ == SEP_ARG) {
                if (parsBrackets_.isEmpty()) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
            }
            if (curChar_ == DOT_VAR) {
                if (i_ < len_) {
                    if (isNumber(i_ + 1, len_, _string)) {
                        int res_ = processNb(i_ + 1, len_, firstPrintableWordChar_, _string, true);
                        if (res_ < 0) {
                            _conf.getLastPage().setOffset(-res_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        i_ = res_;
                        continue;
                    }
                }
                if (parsBrackets_.isEmpty()) {
                    if (i_ + 1 >= len_) {
                        if (!foundSemiColumn_) {
                            _conf.getLastPage().setOffset(i_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                    }
                }
            }
            if (partOfString_ && _string.charAt(i_) == end_) {
                partOfString_ = false;
                break;
            }
            if (partOfString_ && _string.charAt(i_) == begin_) {
                _conf.getLastPage().setOffset(i_);
                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
            }
            i_++;
        }
        if (hatMethod_) {
            _conf.getLastPage().setOffset(i_);
            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
        }
        if (constString_) {
            _conf.getLastPage().setOffset(i_);
            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
        }
        if (constChar_) {
            _conf.getLastPage().setOffset(i_);
            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
        }
        if (!parsBrackets_.isEmpty()) {
            _conf.getLastPage().setOffset(i_);
            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
        }
        if (!partOfString_) {
            d_.setIndexBegin(_minIndex);
            d_.setIndexEnd(i_-1);
            return d_;
        }
        _conf.getLastPage().setOffset(i_);
        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
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
        int i_ = _start;
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
                    dot_ = true;
                }
                break;
            }
            if (Character.toLowerCase(_string.charAt(j_)) == EXP) {
                iExp_ = j_;
                exp_ = true;
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
            if (!Character.isDigit(next_) && !exp_) {
                return j_ + 1;
            }
            j_++;
            if (exp_) {
                //_string.charAt(j_) == EXP
                return processExp(j_, len_, _string);
            }
            while (j_ < len_) {
                if (!Character.isDigit(_string.charAt(j_))) {
                    break;
                }
                j_++;
            }
            if (j_ >= len_) {
                return j_;
            }
            if (_string.charAt(j_) == EXP) {
                exp_ = true;
            }
            if (exp_) {
                //_string.charAt(j_) == EXP
                return processExp(j_, len_, _string);
            }
            while (j_ < len_) {
                if (!StringList.isWordChar(_string.charAt(j_))) {
                    break;
                }
                j_++;
            }
            if (j_ + 1 < len_ && _string.charAt(j_) == DOT_VAR) {
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
            return -j_;
        }
        if (onlySpacesFrom(_string, _firstPrint, i_, DOT_VAR) && _string.charAt(i_ - 1) != DOT_VAR) {
            return 1-i_;
        }
        while (i_ < len_) {
            if (!StringList.isWordChar(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        return i_;
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
            if (!Character.isDigit(_string.charAt(j_))) {
                break;
            }
            j_++;
        }
        if (j_ < len_ && _string.charAt(j_) == DOT_VAR) {
            return -j_;
        }
        if (j_ < len_ && Character.isLetter(_string.charAt(j_))) {
            j_++;
        }
        return j_;
    }
    private static int processValidNb(int _start, int _max, int _firstPrint, String _string) {
        int i_ = _start;
        int len_ = _max;
        int j_ = _start + 1;
        boolean dot_ = false;
        boolean exp_ = false;
        int iExp_ = j_;
        while (j_ < len_) {
            if (!StringList.isWordChar(_string.charAt(j_))) {
                if (_string.charAt(j_) == DOT_VAR) {
                    dot_ = true;
                }
                break;
            }
            if (Character.toLowerCase(_string.charAt(j_)) == EXP) {
                iExp_ = j_;
                exp_ = true;
            }
            j_++;
        }
        if (j_ + 1 >= len_ && dot_) {
            return j_ + 1;
        }
        if (dot_) {
            char next_ = _string.charAt(j_ + 1);
            if (Character.toLowerCase(next_) == EXP) {
                exp_ = true;
            }
            if (!Character.isDigit(next_) && !exp_) {
                return j_ + 1;
            }
            j_++;
            if (exp_) {
                //_string.charAt(j_) == EXP
                return processValidExp(j_, len_, _string);
            }
            while (j_ < len_) {
                if (!Character.isDigit(_string.charAt(j_))) {
                    break;
                }
                j_++;
            }
            if (j_ >= len_) {
                return j_;
            }
            if (_string.charAt(j_) == EXP) {
                exp_ = true;
            }
            if (exp_) {
                //_string.charAt(j_) == EXP
                return processValidExp(j_, len_, _string);
            }
            while (j_ < len_) {
                if (!StringList.isWordChar(_string.charAt(j_))) {
                    break;
                }
                j_++;
            }
            return j_;
        }
        if (exp_) {
            //_string.charAt(iExp_) == EXP
            return processValidExp(iExp_, len_, _string);
        }
        while (i_ < len_) {
            if (!StringList.isWordChar(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        return i_;
    }
    private static int processValidExp(int _start, int _max, String _string) {
        int len_ = _max;
        int j_ = _start;
        j_++;
        if (_string.charAt(j_) == MINUS_CHAR) {
            j_++;
        }
        while (j_ < len_) {
            if (!Character.isDigit(_string.charAt(j_))) {
                break;
            }
            j_++;
        }
        if (j_ < len_ && Character.isLetter(_string.charAt(j_))) {
            j_++;
        }
        return j_;
    }
    private static boolean onlySpacesTo(String _string, int _index, int _length, char _end) {
        int i_ = _index;
        int len_ = _length;
        if (i_ + 1 < len_) {
            int j_ = i_ + 1;
            while (j_ < len_) {
                if (!Character.isWhitespace(_string.charAt(j_))) {
                    break;
                }
                j_++;
            }
            if (j_ < len_) {
                if (_string.charAt(j_) == _end) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean onlySpacesFrom(String _string, int _min, int _index, char _begin) {
        int i_ = _index;
        if (i_ > _min) {
            int j_ = i_ - 1;
            while (j_ >= _min) {
                if (!Character.isWhitespace(_string.charAt(j_))) {
                    break;
                }
                j_--;
            }
            if (j_ >= _min) {
                if (_string.charAt(j_) == _begin) {
                    return true;
                }
            }
        }
        return false;
    }
    public static OperationsSequence getOperationsSequence(int _offset, String _string,
            ContextEl _conf, Delimiters _d) {
        NatTreeMap<Integer,String> operators_;
        operators_ = new NatTreeMap<Integer,String>();
        NatTreeMap<Integer,Character> parsBrackets_;
        parsBrackets_ = new NatTreeMap<Integer,Character>();
        char usedCaller_ = 0;
        char usedEnder_ = 0;
        boolean constString_ = false;
        boolean constChar_ = false;
        boolean escapedMeta_ = false;
        int prioMax_ = FCT_OPER_PRIO;
        int prio_ = prioMax_;
        int len_ = _string.length();
        int i_ = CustList.FIRST_INDEX;
        int minIndexDot_ = CustList.FIRST_INDEX;
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
            if (procWordFirstChar(_string, firstPrintChar_ + 1, STATIC_ACCESS, len_)) {
                int j_ = firstPrintChar_ +1 + STATIC_ACCESS.length();
                boolean staticAccess_ = true;
                while (j_ <= lastPrintChar_) {
                    if (_string.charAt(j_) != DOT_VAR) {
                        j_++;
                        continue;
                    }
                    staticAccess_ = false;
                    break;
                }
                if (staticAccess_) {
                    OperationsSequence op_ = new OperationsSequence();
                    op_.setOperators(new NatTreeMap<Integer, String>());
                    op_.setupValues(_string);
                    op_.setDelimiter(_d);
                    return op_;
                }
            }
        }
        if (isFloatingNumber(_string, firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isIntegerNumber(_string, firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isVariableOrWord(_string, firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isConstant(_string, firstPrintChar_, lastPrintChar_, DELIMITER_CHAR)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isConstant(_string, firstPrintChar_, lastPrintChar_, DELIMITER_STRING)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.setDelimiter(_d);
            return op_;
        }
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
            minIndexDot_ = i_;
            if (!onlySpacesTo(_string, minIndexDot_-1, len_, ARR_LEFT)) {
                prio_ = DOT_PRIO;
                operators_.put(i_, EMPTY_STRING);
            }
        }
        i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (constChar_) {
                if (!escapedMeta_) {
                    if (curChar_ == ESCAPE_META_CHAR) {
                        escapedMeta_ = true;
                        i_++;
                        continue;
                    }
                    if (curChar_ == DELIMITER_CHAR) {
                        constChar_ = false;
                        i_++;
                        continue;
                    }
                    i_++;
                    continue;
                }
                escapedMeta_ = false;
                i_++;
                continue;
            }
            if (constString_) {
                if (!escapedMeta_) {
                    if (curChar_ == ESCAPE_META_CHAR) {
                        escapedMeta_ = true;
                        i_++;
                        continue;
                    }
                    if (curChar_ == DELIMITER_STRING) {
                        constString_ = false;
                        i_++;
                        continue;
                    }
                    i_++;
                    continue;
                }
                escapedMeta_ = false;
                i_++;
                continue;
            }
            if (curChar_ == DELIMITER_CHAR) {
                constChar_ = true;
            }
            if (curChar_ == DELIMITER_STRING) {
                constString_ = true;
            }
            if (curChar_ == EXTERN_CLASS) {
                if (procWordFirstChar(_string, i_ + 1, INSTANCE, len_)) {
                    i_ = _string.indexOf(PAR_LEFT, i_ + 1);
                    continue;
                }
                if (procWordFirstChar(_string, i_ + 1, STATIC_ACCESS, len_)) {
                    i_ = _string.indexOf(DOT_VAR, i_ + 1);
                    continue;
                }
                if (procWordFirstChar(_string, i_ + 1, SUPER, len_)) {
                    int next_ = _string.indexOf(EXTERN_CLASS, i_ + 1);
                    if (next_ > CustList.INDEX_NOT_FOUND_ELT) {
                        i_ = next_ + 1;
                        continue;
                    }
                }
                if (procWordFirstChar(_string, i_ + 1, CLASS_CHOICE, len_)) {
                    i_ = _string.indexOf(STATIC_CALL, i_ + 1) + STATIC_CALL.length();
                    continue;
                }
            }
            if (isNumber(i_, len_, _string)) {
                i_ = processValidNb(i_, len_, firstPrintChar_, _string);
                continue;
            }
            if (curChar_ == DOT_VAR) {
                if (isNumber(i_ + 1, len_, _string)) {
                    i_ = processValidNb(i_ + 1, len_, firstPrintChar_, _string);
                    continue;
                }
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
            if (curChar_ == PAR_LEFT) {
                parsBrackets_.put(i_, curChar_);
                usedCaller_ = curChar_;
            }
            if (curChar_ == PAR_RIGHT) {
                usedEnder_ = curChar_;
                parsBrackets_.removeKey(parsBrackets_.lastKey());
            }
            if (curChar_ == ARR_LEFT) {
                if (parsBrackets_.isEmpty()) {
                    if (ARR_OPER_PRIO < prio_) {
                        prio_ = ARR_OPER_PRIO;
                        operators_.clear();
                    }
                    if (ARR_OPER_PRIO <= prio_) {
                        operators_.put(i_, ARR);
                    }
                }
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == ARR_RIGHT) {
                usedEnder_ = curChar_;
                parsBrackets_.removeKey(parsBrackets_.lastKey());
                if (parsBrackets_.isEmpty() && prio_ == ARR_OPER_PRIO) {
                    operators_.put(i_, String.valueOf(ARR_RIGHT));
                }
            }
            if (parsBrackets_.isEmpty() && i_ + 2 <= len_) {
                String builtOperator_ = EMPTY_STRING;
                boolean clearOperators_ = false;
                boolean foundOperator_ = false;
                char nextChar_ = _string.charAt(i_ + 1);
                int increment_ = 1;
                if (curChar_ == DOT_VAR) {
                    builtOperator_ += DOT_VAR;
                    if (i_ > minIndexDot_) {
                        if (prio_ > DOT_PRIO) {
                            clearOperators_ = true;
                            prio_ = DOT_PRIO;
                        }
                        if (prio_ == DOT_PRIO) {
                            foundOperator_ = true;
                        }
                    }
                }
                if (curChar_ == NEG_BOOL_CHAR) {
                    builtOperator_ += NEG_BOOL_CHAR;
                    if (i_ == firstPrintChar_) {
                        foundOperator_ = true;
                        prio_ = UNARY_PRIO;
                    } else {
                        if (prio_ > EQ_PRIO) {
                            clearOperators_ = true;
                            prio_ = EQ_PRIO;
                            increment_++;
                            builtOperator_ += EQ_CHAR;
                        }
                        if (prio_ == EQ_PRIO) {
                            foundOperator_ = true;
                        }
                    }
                    if (foundOperator_) {
                        increment_ = getIncrement(_string, nextChar_ == EQ_CHAR, i_+1, lastPrintChar_);
                    }
                }
                int prioOpMult_ = 0;
                if (curChar_ == MINUS_CHAR || curChar_ == PLUS_CHAR) {
                    prioOpMult_ = ADD_PRIO;
                } else if (curChar_ == MULT_CHAR || curChar_ == DIV_CHAR || curChar_ == MOD_CHAR) {
                    prioOpMult_ = MULT_PRIO;
                } else if (curChar_ == AND_CHAR) {
                    prioOpMult_ = AND_PRIO;
                } else if (curChar_ == EQ_CHAR) {
                    prioOpMult_ = EQ_PRIO;
                } else if (curChar_ == OR_CHAR) {
                    prioOpMult_ = OR_PRIO;
                }
                if (prioOpMult_ > 0) {
                    builtOperator_ += curChar_;
                    if (i_ == firstPrintChar_) {
                        foundOperator_ = true;
                        prio_ = UNARY_PRIO;
                    } else {
                        if (prio_ > prioOpMult_) {
                            clearOperators_ = true;
                            prio_ = prioOpMult_;
                        }
                        if (prio_ == prioOpMult_) {
                            foundOperator_ = true;
                        }
                    }
                    increment_ = getIncrement(_string, false, i_+1, lastPrintChar_);
                }
                if (curChar_ == LOWER_CHAR || curChar_ == GREATER_CHAR) {
                    builtOperator_ += curChar_;
                    if (prio_ > CMP_PRIO) {
                        clearOperators_ = true;
                        prio_ = CMP_PRIO;
                    }
                    if (prio_ == CMP_PRIO) {
                        foundOperator_ = true;
                    }
                    if (foundOperator_) {
                        if (nextChar_ == EQ_CHAR) {
                            builtOperator_ += nextChar_;
                        }
                        increment_ = getIncrement(_string, nextChar_ == EQ_CHAR, i_+1, lastPrintChar_);
                    }
                }
                if (foundOperator_) {
                    if (clearOperators_) {
                        operators_.clear();
                    }
                    operators_.put(i_,builtOperator_);
                }
                i_ += increment_;
                continue;
            }
            i_++;
        }
        OperationsSequence op_ = new OperationsSequence();
        op_.setPriority(prio_);
        op_.setOperators(operators_);
        if (prioMax_ == prio_) {
            if (usedCaller_ != 0) {
                int indexUsedCaller_ = _string.indexOf(usedCaller_);
                int index_ = indexUsedCaller_ + 1;
                int end_ = _string.lastIndexOf(usedEnder_);
                NatTreeMap<Integer,String> newOperators_;
                newOperators_ = new NatTreeMap<Integer,String>();
                newOperators_.put(indexUsedCaller_, String.valueOf(usedCaller_));
                for (int i = index_; i < end_; i++) {
                    char curChar_ = _string.charAt(i);
                    if (constChar_) {
                        if (!escapedMeta_) {
                            if (curChar_ == ESCAPE_META_CHAR) {
                                escapedMeta_ = true;
                                continue;
                            }
                            if (curChar_ == DELIMITER_CHAR) {
                                constChar_ = false;
                                continue;
                            }
                            continue;
                        }
                        escapedMeta_ = false;
                        continue;
                    }
                    if (constString_) {
                        if (!escapedMeta_) {
                            if (curChar_ == ESCAPE_META_CHAR) {
                                escapedMeta_ = true;
                                continue;
                            }
                            if (curChar_ == DELIMITER_STRING) {
                                constString_ = false;
                                continue;
                            }
                            continue;
                        }
                        escapedMeta_ = false;
                        continue;
                    }
                    if (curChar_ == DELIMITER_CHAR) {
                        constChar_ = true;
                    }
                    if (curChar_ == DELIMITER_STRING) {
                        constString_ = true;
                    }
                    if (curChar_ == PAR_LEFT) {
                        parsBrackets_.put(i, curChar_);
                    }
                    if (curChar_ == PAR_RIGHT) {
                        parsBrackets_.removeKey(parsBrackets_.lastKey());
                    }
                    if (curChar_ == ARR_LEFT) {
                        parsBrackets_.put(i, curChar_);
                    }
                    if (curChar_ == ARR_RIGHT) {
                        parsBrackets_.removeKey(parsBrackets_.lastKey());
                    }
                    if (curChar_ == SEP_ARG) {
                        if (parsBrackets_.isEmpty()) {
                            newOperators_.put(i, String.valueOf(SEP_ARG));
                        }
                    }
                }
                newOperators_.put(end_, String.valueOf(usedEnder_));
                String fctName_ = _string.substring(CustList.FIRST_INDEX, _string.indexOf(usedCaller_));
                op_.setFctName(fctName_);
                op_.setUseFct(true);
                op_.setOperators(newOperators_);
            }
        }
        op_.setupValues(_string);
        op_.setDelimiter(_d);
        return op_;
    }

    static boolean isIntegerNumber(String _string, int _from, int _to) {
        int i_ = _from;
        if (!Character.isDigit(_string.charAt(i_))) {
            if (_string.charAt(i_) != MINUS_CHAR) {
                return false;
            }
            i_++;
            if (i_ <= _to) {
                if (!Character.isDigit(_string.charAt(i_))) {
                    return false;
                }
            }
        }
        while (i_ <= _to) {
            if (!Character.isDigit(_string.charAt(i_))) {
                if (Character.isLetter(_string.charAt(i_))) {
                    i_++;
                    continue;
                }
                return false;
            }
            i_++;
        }
        return true;
    }

    static boolean isFloatingNumber(String _string, int _from, int _to) {
        int i_ = _from;
        if (!Character.isDigit(_string.charAt(i_))) {
            if (_string.charAt(i_) != MINUS_CHAR) {
                if (_string.charAt(i_) != DOT_VAR) {
                    return false;
                }
            }
            i_++;
            if (_string.charAt(i_) == DOT_VAR) {
                i_++;
            }
        }
        if (i_ <= _to) {
            if (!Character.isDigit(_string.charAt(i_))) {
                return false;
            }
        }
        int nbDots_ = 0;
        boolean exp_ = false;
        while (i_ <= _to) {
            char cur_ = _string.charAt(i_);
            if (!Character.isDigit(cur_)) {
                if (Character.isLetter(cur_)) {
                    if (Character.toLowerCase(cur_) == EXP) {
                        exp_ = true;
                        i_++;
                        continue;
                    }
                    if (nbDots_ == 0 && !exp_) {
                        return false;
                    }
                    i_++;
                    continue;
                }
                if (cur_ == MINUS_CHAR && exp_) {
                    i_++;
                    continue;
                }
                if (cur_ != DOT_VAR || nbDots_ > 0) {
                    return false;
                }
                nbDots_++;
                i_++;
                continue;
            }
            i_++;
        }
        return true;
    }
    static boolean isVariableOrWord(String _string, int _from, int _to) {
        int i_ = _from;
        while (i_ <= _to) {
            if (!StringList.isWordChar(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        if (i_ <= _to && _string.charAt(i_) != GET_VAR) {
            return false;
        }
        while (i_ <= _to) {
            if (_string.charAt(i_) != GET_VAR) {
                if (_string.charAt(i_) != DOT_VAR) {
                    return false;
                }
            }
            i_++;
        }
        return true;
    }
    static boolean isConstant(String _string, int _from, int _to, char _delimiter) {
        int i_ = _from;
        if (_string.charAt(i_) != _delimiter) {
            return false;
        }
        i_++;
        boolean escaped_ = false;
        while (i_ < _to) {
            if (escaped_) {
                i_++;
                escaped_ = false;
                continue;
            }
            if (_string.charAt(i_) == ESCAPE_META_CHAR) {
                escaped_ = true;
            }
            if (_string.charAt(i_) == _delimiter) {
                return false;
            }
            i_++;
        }
        if (_string.charAt(_to) != _delimiter) {
            return false;
        }
        return true;
    }
    static int getIncrement(String _string, boolean _preIncrement, int _from, int _to) {
        int increment_ = 1;
        int j_ = _from;
        if (_preIncrement) {
            j_++;
            increment_++;
        }
        while (j_ <= _to) {
            if (_string.charAt(j_) != MINUS_CHAR) {
                if (_string.charAt(j_) != NEG_BOOL_CHAR) {
                    if (_string.charAt(j_) != PLUS_CHAR) {
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
