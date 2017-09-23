package code.expressionlanguage;
import code.expressionlanguage.exceptions.BadComparisonException;
import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.expressionlanguage.exceptions.BadNumberArgumentException;
import code.expressionlanguage.exceptions.EmptyPartException;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
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
    private static final char FIRST_VAR_ARG = '?';
    private static final char GET_VAR = ';';
    private static final char DOT_VAR = '.';
    private static final char EXTERN_CLASS = '^';
    private static final char INTERN_CLASS = '$';
    private static final String CLASS_CHOICE = "classchoice";
    private static final String STATIC_CALL = "^^";
    private static final String INSTANCE = "new";
    private static final String SUPER = "super";
    private static final String STATIC_ACCESS = "static";
    private static final String VAR_ARG = "vararg";
    private static final String FIRST_OPT = "firstopt";

    private static final char MIN_ENCODE_DIGIT = '0';
    private static final char MAX_ENCODE_DIGIT = '9';
    private static final char MIN_ENCODE_LOW_LETTER = 'a';
    private static final char MAX_ENCODE_LOW_LETTER = 'f';
    private static final char MIN_ENCODE_UPP_LETTER = 'A';
    private static final char MAX_ENCODE_UPP_LETTER = 'F';
    private static final String FCT = "(";
    private static final OperationPriority FCT_OPER = new OperationPriority(FCT, FCT_OPER_PRIO);
    private static final String ARR = "[";
    private static final OperationPriority ARR_OPER = new OperationPriority(ARR, ARR_OPER_PRIO);

    private static final String DOT = ".";
    private static final OperationPriority DOT_OPER = new OperationPriority(DOT, DOT_PRIO);

    private static final char NEG_BOOL_CHAR = '!';
    private static final String NEG_BOOL = "!";
    private static final OperationPriority NEG_BOOL_OPER = new OperationPriority(NEG_BOOL, UNARY_PRIO);

    private static final String UNARY_PLUS = "+";
    private static final OperationPriority UNARY_PLUS_OPER = new OperationPriority(UNARY_PLUS, UNARY_PRIO);

    private static final String UNARY_MINUS = "-";
    private static final OperationPriority UNARY_MINUS_OPER = new OperationPriority(UNARY_MINUS, UNARY_PRIO);

    private static final String MULT = "*";
    private static final OperationPriority MULT_OPER = new OperationPriority(MULT, MULT_PRIO);

    private static final String DIV = "/";
    private static final OperationPriority DIV_OPER = new OperationPriority(DIV, MULT_PRIO);

    private static final String MOD = "%";
    private static final OperationPriority MOD_OPER = new OperationPriority(MOD, MULT_PRIO);

    private static final String PLUS = "+";
    private static final OperationPriority PLUS_OPER = new OperationPriority(PLUS, ADD_PRIO);

    private static final char MINUS_CHAR = '-';
    private static final String MINUS = "-";
    private static final OperationPriority MINUS_OPER = new OperationPriority(MINUS, ADD_PRIO);

    private static final String LOWER_EQ = "<=";
    private static final OperationPriority LOWER_EQ_OPER = new OperationPriority(LOWER_EQ, CMP_PRIO);

    private static final char LOWER_CHAR = '<';
    private static final String LOWER = "<";
    private static final OperationPriority LOWER_OPER = new OperationPriority(LOWER, CMP_PRIO);

    private static final String GREATER_EQ = ">=";
    private static final OperationPriority GREATER_EQ_OPER = new OperationPriority(GREATER_EQ, CMP_PRIO);

    private static final char GREATER_CHAR = '>';
    private static final String GREATER = ">";
    private static final OperationPriority GREATER_OPER = new OperationPriority(GREATER, CMP_PRIO);

    private static final char EQ_CHAR = '=';
    private static final String EQ = "=";
    private static final OperationPriority EQ_OPER = new OperationPriority(EQ, EQ_PRIO);

    private static final String DIFF = "!=";
    private static final OperationPriority DIFF_OPER = new OperationPriority(DIFF, EQ_PRIO);

    private static final String AND = "&";
    private static final OperationPriority AND_OPER = new OperationPriority(AND, AND_PRIO);

    private static final String OR = "|";
    private static final OperationPriority OR_OPER = new OperationPriority(OR, OR_PRIO);
    private static final char[] OPERATORS_CHARS = new char[]{'!','+','-','*','/','%','>','=','<','&','|',',','(',')','[',']','.',';'};

    private ElResolver() {
    }

    static CustList<OperationPriority> getOperations() {
        CustList<OperationPriority> prios_ = new CustList<OperationPriority>();
        prios_.add(ARR_OPER);
        prios_.add(FCT_OPER);
        prios_.add(DOT_OPER);
        prios_.add(NEG_BOOL_OPER);
        prios_.add(UNARY_PLUS_OPER);
        prios_.add(UNARY_MINUS_OPER);
        prios_.add(MULT_OPER);
        prios_.add(DIV_OPER);
        prios_.add(MOD_OPER);
        prios_.add(PLUS_OPER);
        prios_.add(MINUS_OPER);
        prios_.add(LOWER_EQ_OPER);
        prios_.add(GREATER_EQ_OPER);
        prios_.add(LOWER_OPER);
        prios_.add(GREATER_OPER);
        prios_.add(EQ_OPER);
        prios_.add(DIFF_OPER);
        prios_.add(AND_OPER);
        prios_.add(OR_OPER);
        return prios_;
    }

    static CustList<OperationPriority> getOperationsByPriority(int _prio) {
        CustList<OperationPriority> prios_ = new CustList<OperationPriority>();
        for (OperationPriority o: getOperations()) {
            if (o.getPriority() == _prio) {
                prios_.add(o);
            }
        }
        return prios_;
    }

    static CustList<OperationPriority> getOperationsByLowerPriority(int _prio) {
        CustList<OperationPriority> prios_ = new CustList<OperationPriority>();
        for (OperationPriority o: getOperations()) {
            if (o.getPriority() <= _prio) {
                prios_.add(o);
            }
        }
        return prios_;
    }

    static int getMaxPriority() {
        Numbers<Integer> prios_ = new Numbers<Integer>();
        for (OperationPriority o: getOperations()) {
            prios_.add(o.getPriority());
        }
        return prios_.getMaximum();
    }

    static Delimiters checkSyntaxDelimiters(String _string, ContextEl _conf, int _minIndex, char _begin, char _end) {
        Delimiters d_ = new Delimiters();
        NatTreeMap<Integer,Character> parsBrackets_;
        parsBrackets_ = new NatTreeMap<Integer,Character>();

        boolean constString_ = false;
        boolean foundSemiColumn_ = false;
        boolean constChar_ = false;
        boolean escapedMeta_ = false;
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
                if (nbChars_ > 1) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                if (!escapedMeta_) {
                    if (curChar_ == ESCAPE_META_CHAR) {
                        if (i_ + 1 >= len_) {
                            _conf.getLastPage().setOffset(i_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        escapedMeta_ = true;
                        i_++;
                        continue;
                    }
                    if (curChar_ == DELIMITER_CHAR) {
                        d_.getDelimitersStringsChars().put(beginCharString_-_minIndex, i_-_minIndex);
                        constChar_ = false;
                        i_++;
                        continue;
                    }
                    nbChars_ ++;
                    i_++;
                    continue;
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
                        _conf.getLastPage().setOffset(i_);
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                    }
                    if (unicode_ < UNICODE_SIZE) {
                        unicode_++;
                    } else {
                        unicode_ = 0;
                        nbChars_ ++;
                        escapedMeta_ = false;
                    }
                    i_++;
                    continue;
                }
                if (curChar_ == DELIMITER_CHAR) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_LINE) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_FORM) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_BOUND) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_LINE_FEED) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_TAB) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == ESCAPE_META_CHAR) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ != UNICODE || i_ + UNICODE_SIZE > len_) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                unicode_ = 1;
                i_++;
                continue;
            }
            if (constString_) {
                if (!escapedMeta_) {
                    if (curChar_ == ESCAPE_META_CHAR) {
                        if (i_ + 1 >= len_) {
                            _conf.getLastPage().setOffset(i_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        escapedMeta_ = true;
                        i_++;
                        continue;
                    }
                    if (curChar_ == DELIMITER_STRING) {
                        constString_ = false;
                        d_.getDelimitersStringsChars().put(beginCharString_-_minIndex, i_-_minIndex);
                        i_++;
                        continue;
                    }
                    i_++;
                    continue;
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
                        _conf.getLastPage().setOffset(i_);
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                    }
                    if (unicode_ < UNICODE_SIZE) {
                        unicode_++;
                    } else {
                        unicode_ = 0;
                        escapedMeta_ = false;
                    }
                    i_++;
                    continue;
                }
                if (curChar_ == DELIMITER_STRING) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_LINE) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_FORM) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_BOUND) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_LINE_FEED) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_TAB) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == ESCAPE_META_CHAR) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ != UNICODE || i_ + UNICODE_SIZE > len_) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                unicode_ = 1;
                i_++;
                continue;
            }
            if (curChar_ == EXTERN_CLASS) {
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
                                break;
                            }
                            if (_string.charAt(j_) == _end) {
                                _conf.getLastPage().setOffset(j_);
                                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
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
                            if (_string.charAt(afterStatic_) == _end) {
                                _conf.getLastPage().setOffset(afterStatic_);
                                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                            }
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
                                break;
                            }
                            if (_string.charAt(j_) == _end) {
                                _conf.getLastPage().setOffset(j_);
                                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
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
                            if (_string.charAt(afterSuper_) == _end) {
                                _conf.getLastPage().setOffset(afterSuper_);
                                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                            }
                            if (_string.charAt(afterSuper_) == EXTERN_CLASS) {
                                foundHat_ = true;
                                break;
                            }
                            if (!Character.isWhitespace(_string.charAt(afterSuper_))) {
                                if (_string.charAt(afterSuper_) != PAR_LEFT) {
                                    _conf.getLastPage().setOffset(afterSuper_);
                                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                                }
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
                        i_ = afterSuper_ + 1;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, CLASS_CHOICE, len_)) {
                        int afterClassChoice_ = i_ + 1 + CLASS_CHOICE.length();
                        boolean foundHat_ = false;
                        while (afterClassChoice_ < len_) {
                            if (_string.charAt(afterClassChoice_) == _end) {
                                _conf.getLastPage().setOffset(afterClassChoice_);
                                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                            }
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
                            if (_string.charAt(afterClassChoice_) == _end) {
                                _conf.getLastPage().setOffset(afterClassChoice_);
                                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                            }
                            if (_string.charAt(afterClassChoice_) == EXTERN_CLASS) {
                                if (afterClassChoice_ + 1 >= len_) {
                                    _conf.getLastPage().setOffset(afterClassChoice_);
                                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                                }
                                if (_string.charAt(afterClassChoice_ + 1) == EXTERN_CLASS) {
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
                        i_ = afterClassChoice_ + 1;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, VAR_ARG, len_)) {
                        int afterSuper_ = i_ + 1 + VAR_ARG.length();
                        if (afterSuper_ >= len_) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        if (_string.charAt(afterSuper_) == _end) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        if (_string.charAt(afterSuper_) != PAR_LEFT) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        i_ = afterSuper_;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, FIRST_OPT, len_)) {
                        int afterSuper_ = i_ + 1 + FIRST_OPT.length();
                        if (afterSuper_ >= len_) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        if (_string.charAt(afterSuper_) == _end) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        if (_string.charAt(afterSuper_) != PAR_LEFT) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        i_ = afterSuper_;
                        continue;
                    }
                }
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
                if (Character.isDigit(curChar_)) {
                    int j_ = i_ + 1;
                    while (j_ < len_) {
                        if (!StringList.isWordChar(_string.charAt(j_))) {
                            break;
                        }
                        j_++;
                    }
                    if (j_ < len_ && Character.isWhitespace(_string.charAt(j_))) {
                        while (j_ < len_) {
                            if (!Character.isWhitespace(_string.charAt(j_))) {
                                break;
                            }
                            j_++;
                        }
                        if (j_ < len_ && _string.charAt(j_) == DOT_VAR) {
                            _conf.getLastPage().setOffset(j_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                    }
                    if (onlySpacesFrom(_string, firstPrintableWordChar_, i_, DOT_VAR) && _string.charAt(i_ - 1) != DOT_VAR) {
                        _conf.getLastPage().setOffset(i_-1);
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                    }
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
            if (curChar_ == NEG_BOOL.charAt(0) || curChar_ == LOWER.charAt(0) || curChar_ == GREATER.charAt(0)) {
                int j_ = i_ + 1;
                boolean exist_ = false;
                while (j_ < len_) {
                    if (Character.isWhitespace(_string.charAt(j_))) {
                        exist_ = true;
                        j_++;
                        continue;
                    }
                    if (_string.charAt(j_) == EQ.charAt(0) && exist_) {
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
                if (parsBrackets_.getValue(parsBrackets_.size() - 1) != PAR_LEFT) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                d_.getCallings().put(parsBrackets_.getKey(parsBrackets_.size() - 1)-_minIndex, i_-_minIndex);
                parsBrackets_.removeKey(parsBrackets_.getKey(parsBrackets_.size() - 1));
            }
            if (curChar_ == ARR_LEFT) {
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == ARR_RIGHT) {
                if (parsBrackets_.isEmpty()) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                if (parsBrackets_.getValue(parsBrackets_.size() - 1) != ARR_LEFT) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                d_.getCallings().put(parsBrackets_.getKey(parsBrackets_.size() - 1)-_minIndex, i_-_minIndex);
                parsBrackets_.removeKey(parsBrackets_.getKey(parsBrackets_.size() - 1));
            }
            if (curChar_ == SEP_ARG) {
                if (parsBrackets_.isEmpty()) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
            }
            if (parsBrackets_.isEmpty()) {
                if (_string.substring(i_, i_ + 1).startsWith(DOT)) {
                    if (i_ + 1 >= len_) {
                        if (!foundSemiColumn_) {
                            _conf.getLastPage().setOffset(i_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                    }
                }
            }
            if (_string.charAt(i_) == _end) {
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
                d_.setIndexBegin(_minIndex);
                d_.setIndexEnd(i_-1);
                return d_;
            }
            if (_string.charAt(i_) == _begin) {
                _conf.getLastPage().setOffset(i_);
                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
            }
            i_++;
        }
        _conf.getLastPage().setOffset(i_);
        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
    }

    static Delimiters checkSyntax(String _string, ContextEl _conf, int _elOffest) {
        Delimiters d_ = new Delimiters();
        NatTreeMap<Integer,Character> parsBrackets_;
        parsBrackets_ = new NatTreeMap<Integer,Character>();
        boolean constString_ = false;
        boolean foundSemiColumn_ = false;
        boolean constChar_ = false;
        boolean escapedMeta_ = false;
        int unicode_ = 0;
        int len_ = _string.length();
        int i_ = _elOffest;
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
        i_ = _elOffest;
        int nbChars_ = 0;
        int beginCharString_ = 0;
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (constChar_) {
                if (nbChars_ > 1) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                if (!escapedMeta_) {
                    if (curChar_ == ESCAPE_META_CHAR) {
                        if (i_ + 1 >= len_) {
                            _conf.getLastPage().setOffset(i_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        escapedMeta_ = true;
                        i_++;
                        continue;
                    }
                    if (curChar_ == DELIMITER_CHAR) {
                        d_.getDelimitersStringsChars().put(beginCharString_, i_);
                        constChar_ = false;
                        i_++;
                        continue;
                    }
                    nbChars_ ++;
                    i_++;
                    continue;
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
                        _conf.getLastPage().setOffset(i_);
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                    }
                    if (unicode_ < UNICODE_SIZE) {
                        unicode_++;
                    } else {
                        unicode_ = 0;
                        nbChars_ ++;
                        escapedMeta_ = false;
                    }
                    i_++;
                    continue;
                }
                if (curChar_ == DELIMITER_CHAR) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_FORM) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_LINE) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_BOUND) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_LINE_FEED) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_TAB) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == ESCAPE_META_CHAR) {
                    nbChars_ ++;
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ != UNICODE || i_ + UNICODE_SIZE >= len_) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                unicode_ = 1;
                i_++;
                continue;
            }
            if (constString_) {
                if (!escapedMeta_) {
                    if (curChar_ == ESCAPE_META_CHAR) {
                        if (i_ + 1 >= len_) {
                            _conf.getLastPage().setOffset(i_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        escapedMeta_ = true;
                        i_++;
                        continue;
                    }
                    if (curChar_ == DELIMITER_STRING) {
                        constString_ = false;
                        d_.getDelimitersStringsChars().put(beginCharString_, i_);
                        i_++;
                        continue;
                    }
                    i_++;
                    continue;
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
                        _conf.getLastPage().setOffset(i_);
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                    }
                    if (unicode_ < UNICODE_SIZE) {
                        unicode_++;
                    } else {
                        unicode_ = 0;
                        escapedMeta_ = false;
                    }
                    i_++;
                    continue;
                }
                if (curChar_ == DELIMITER_STRING) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_LINE) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_FORM) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_BOUND) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_LINE_FEED) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == IND_TAB) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == ESCAPE_META_CHAR) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ != UNICODE || i_ + UNICODE_SIZE >= len_) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                unicode_ = 1;
                i_++;
                continue;
            }
            if (curChar_ == EXTERN_CLASS) {
                if (i_ + 1 < len_) {
                    char nextChar_ = _string.charAt(i_ + 1);
                    if (Character.isWhitespace(nextChar_)) {
                        _conf.getLastPage().setOffset(i_+1);
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                    }
                    if (procWordFirstChar(_string, i_ + 1, INSTANCE, len_)) {
                        int indexLeftPar_ = _string.indexOf(PAR_LEFT, i_ + 1);
                        if (indexLeftPar_ == CustList.INDEX_NOT_FOUND_ELT) {
                            _conf.getLastPage().setOffset(len_ - 1);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        i_ = indexLeftPar_;
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
                        int indexDot_ = _string.indexOf(DOT_VAR, afterStatic_);
                        if (indexDot_ == CustList.INDEX_NOT_FOUND_ELT) {
                            _conf.getLastPage().setOffset(len_ - 1);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        i_ = indexDot_;
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
                        i_ = afterSuper_ + 1;
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
                        i_ = afterClassChoice_ + 1;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, VAR_ARG, len_)) {
                        int afterSuper_ = i_ + 1 + VAR_ARG.length();
                        if (afterSuper_ >= len_) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        if (_string.charAt(afterSuper_) != PAR_LEFT) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        i_ = afterSuper_;
                        continue;
                    }
                    if (procWordFirstChar(_string, i_ + 1, FIRST_OPT, len_)) {
                        int afterSuper_ = i_ + 1 + FIRST_OPT.length();
                        if (afterSuper_ >= len_) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        if (_string.charAt(afterSuper_) != PAR_LEFT) {
                            _conf.getLastPage().setOffset(afterSuper_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                        i_ = afterSuper_;
                        continue;
                    }
                }
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
                if (Character.isDigit(curChar_)) {
                    int j_ = i_ + 1;
                    while (j_ < len_) {
                        if (!StringList.isWordChar(_string.charAt(j_))) {
                            break;
                        }
                        j_++;
                    }
                    if (j_ < len_ && Character.isWhitespace(_string.charAt(j_))) {
                        while (j_ < len_) {
                            if (!Character.isWhitespace(_string.charAt(j_))) {
                                break;
                            }
                            j_++;
                        }
                        if (j_ < len_ && _string.charAt(j_) == DOT_VAR) {
                            _conf.getLastPage().setOffset(j_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                    }
                    if (onlySpacesFrom(_string, firstPrintableWordChar_, i_, DOT_VAR) && _string.charAt(i_ - 1) != DOT_VAR) {
                        _conf.getLastPage().setOffset(i_-1);
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                    }
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
            if (curChar_ == NEG_BOOL.charAt(0) || curChar_ == LOWER.charAt(0) || curChar_ == GREATER.charAt(0)) {
                int j_ = i_ + 1;
                boolean exist_ = false;
                while (j_ < len_) {
                    if (Character.isWhitespace(_string.charAt(j_))) {
                        exist_ = true;
                        j_++;
                        continue;
                    }
                    if (_string.charAt(j_) == EQ.charAt(0) && exist_) {
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
                if (parsBrackets_.getValue(parsBrackets_.size() - 1) != PAR_LEFT) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                d_.getCallings().put(parsBrackets_.getKey(parsBrackets_.size() - 1), i_);
                parsBrackets_.removeKey(parsBrackets_.getKey(parsBrackets_.size() - 1));
            }
            if (curChar_ == ARR_LEFT) {
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == ARR_RIGHT) {
                if (parsBrackets_.isEmpty()) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                if (parsBrackets_.getValue(parsBrackets_.size() - 1) != ARR_LEFT) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                d_.getCallings().put(parsBrackets_.getKey(parsBrackets_.size() - 1), i_);
                parsBrackets_.removeKey(parsBrackets_.getKey(parsBrackets_.size() - 1));
            }
            if (curChar_ == SEP_ARG) {
                if (parsBrackets_.isEmpty()) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
            }
            if (parsBrackets_.isEmpty()) {
                if (_string.substring(i_, i_ + 1).startsWith(DOT)) {
                    if (i_ + 1 >= len_) {
                        if (!foundSemiColumn_) {
                            _conf.getLastPage().setOffset(i_);
                            throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                        }
                    }
                }
            }
            i_++;
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
        d_.setIndexBegin(_elOffest);
        d_.setIndexEnd(i_-1);
        return d_;
    }
    static void secondCheckSyntax(String _string, ContextEl _conf, Delimiters _d) {
        int len_ = _string.length();
        int i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            if (!Character.isWhitespace(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        int firstPrintableWordChar_ = i_;
        i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            if (_d.inStringOrCharConst(i_)) {
                i_++;
                continue;
            }
            char curChar_ = _string.charAt(i_);
            boolean contained_ = false;
            for (char c: OPERATORS_CHARS) {
                if (c == curChar_) {
                    contained_ = true;
                    break;
                }
            }
            if (!contained_) {
                if (curChar_ == FIRST_VAR_ARG) {
                    if (onlySpacesTo(_string, i_, len_, SEP_ARG)) {
                        i_++;
                        continue;
                    }
                    if (onlySpacesTo(_string, i_, len_, PAR_RIGHT)) {
                        i_++;
                        continue;
                    }
                    if (onlySpacesTo(_string, i_, len_, ARR_LEFT)) {
                        if (onlySpacesFrom(_string, firstPrintableWordChar_, i_, PAR_LEFT)) {
                            i_++;
                            continue;
                        }
                        _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                    }
                    if (onlySpacesFrom(_string, firstPrintableWordChar_, i_, PAR_LEFT)) {
                        for (char c: OPERATORS_CHARS) {
                            if (onlySpacesTo(_string, i_, len_, c)) {
                                _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                            }
                        }
                        i_++;
                        continue;
                    }
                    _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                i_++;
                continue;
            }
            if (onlySpacesTo(_string, i_, len_, PLUS.charAt(0))) {
                i_++;
                continue;
            }
            if (onlySpacesTo(_string, i_, len_, MINUS.charAt(0))) {
                i_++;
                continue;
            }
            if (curChar_ == ARR_LEFT) {
                for (char c: OPERATORS_CHARS) {
                    if (c == ARR_LEFT) {
                        continue;
                    }
                    if (c == ARR_RIGHT) {
                        continue;
                    }
                    if (c == PAR_LEFT) {
                        continue;
                    }
                    if (onlySpacesTo(_string, i_, len_, c)) {
                        _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                    }
                }
                i_++;
                continue;
            }
            if (curChar_ == PAR_LEFT || curChar_ == SEP_ARG || curChar_ == AND.charAt(0) || curChar_ == OR.charAt(0)) {
                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
                    i_++;
                    continue;
                }
            }
            if (curChar_ == ARR_RIGHT) {
                if (onlySpacesTo(_string, i_, len_, GET_VAR)) {
                    _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                i_++;
                continue;
            }
            if (onlySpacesTo(_string, i_, len_, PAR_LEFT)) {
                if (curChar_ == PAR_RIGHT) {
                    _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                i_++;
                continue;
            }
            if (onlySpacesTo(_string, i_, len_, PAR_RIGHT)) {
                if (curChar_ == PAR_LEFT || curChar_ == PAR_RIGHT) {
                    i_++;
                    continue;
                }
                if (curChar_ == DOT_VAR || curChar_ == GET_VAR) {
                    i_++;
                    continue;
                }
                if (curChar_ == ARR_RIGHT) {
                    i_++;
                    continue;
                }
                _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
            }
            if (onlySpacesTo(_string, i_, len_, ARR_LEFT)) {
                if (curChar_ == PAR_RIGHT) {
                    i_++;
                    continue;
                }
                if (curChar_ == DOT_VAR || curChar_ == GET_VAR) {
                    i_++;
                    continue;
                }
                _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
            }
            if (onlySpacesTo(_string, i_, len_, ARR_RIGHT)) {
                if (curChar_ == ARR_RIGHT || curChar_ == PAR_RIGHT) {
                    i_++;
                    continue;
                }
                if (curChar_ == DOT_VAR || curChar_ == GET_VAR) {
                    i_++;
                    continue;
                }
                _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
            }
            if (curChar_ == PAR_RIGHT) {
                if (onlySpacesTo(_string, i_, len_, GET_VAR)) {
                    _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                i_++;
                continue;
            }
            if (curChar_ == GET_VAR) {
                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
                    _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                i_++;
                continue;
            }
            if (curChar_ == DOT_VAR) {
                if (onlySpacesTo(_string, i_, len_, DOT_VAR)) {
                    int j_ = i_ - 1;
                    while (j_ >= CustList.FIRST_INDEX) {
                        if (Character.isWhitespace(_string.charAt(j_))) {
                            j_--;
                            continue;
                        }
                        if (_string.charAt(j_) == DOT_VAR) {
                            j_--;
                            continue;
                        }
                        if (_string.charAt(j_) == GET_VAR) {
                            break;
                        }
                        throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                    }
                    i_++;
                    continue;
                }
                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
                    _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
                i_++;
                continue;
            }
            if (curChar_ == NEG_BOOL_CHAR) {
                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
                    i_++;
                    continue;
                }
                if (onlySpacesTo(_string, i_, len_, EQ_CHAR)) {
                    i_++;
                    continue;
                }
            }
            if (curChar_ == GREATER_CHAR || curChar_ == LOWER_CHAR) {
                if (onlySpacesTo(_string, i_, len_, EQ_CHAR)) {
                    i_++;
                    continue;
                }
            }
            if (curChar_ == EQ_CHAR) {
                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
                    i_++;
                    continue;
                }
            }
            for (char c: OPERATORS_CHARS) {
                if (onlySpacesTo(_string, i_, len_, c)) {
                    _conf.getLastPage().setOffset(_d.getIndexBegin()+i_);
                    throw new BadExpressionLanguageException(_string+RETURN_LINE+_conf.joinPages());
                }
            }
            i_++;
        }
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
        Character usedCaller_ = null;
        Character usedEnder_ = null;
//        boolean instance_ = false;
        boolean constString_ = false;
        boolean constChar_ = false;
        boolean escapedMeta_ = false;
        int prioMax_ = getMaxPriority();
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
            _conf.getLastPage().setOffset(_d.getIndexBegin()+_offset+i_);
            throw new EmptyPartException(_string+RETURN_LINE+_conf.joinPages());
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
                while (Character.isWhitespace(_string.charAt(j_))) {
                    j_++;
                }
                if (_string.charAt(j_) == EXTERN_CLASS) {
                    boolean staticAccess_ = true;
                    while (j_ <= lastPrintChar_) {
                        if (_string.charAt(j_) == EXTERN_CLASS) {
                            j_++;
                            continue;
                        }
                        if (_string.charAt(j_) == INTERN_CLASS) {
                            j_++;
                            continue;
                        }
                        if (Character.isWhitespace(_string.charAt(j_))) {
                            j_++;
                            continue;
                        }
                        if (StringList.isWordChar(_string.charAt(j_))) {
                            j_++;
                            continue;
                        }
                        if (_string.charAt(j_) == ARR_LEFT) {
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
                        op_.addOffset(_offset);
                        op_.setDelimiter(_d);
                        return op_;
                    }
                }
            }
        }
        if (isFloatingNumber(_string, firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isIntegerNumber(_string, firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isVariableOrWord(_string, firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isConstant(_string, firstPrintChar_, lastPrintChar_, DELIMITER_CHAR)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isConstant(_string, firstPrintChar_, lastPrintChar_, DELIMITER_STRING)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        while (true) {
            if (!StringList.isWordChar(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        StringBuilder suffixVar_ = new StringBuilder();
        //i_ < len_
        if (_string.charAt(i_) == GET_VAR) {
            suffixVar_.append(_string.charAt(i_));
            i_++;
            while (true) {
                if (_string.charAt(i_) == GET_VAR) {
                    suffixVar_.append(_string.charAt(i_));
                    i_++;
                    continue;
                }
                if (_string.charAt(i_) == DOT_VAR) {
                    suffixVar_.append(_string.charAt(i_));
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
                if (FCT_OPER_PRIO <= prio_) {
                    operators_.put(i_, FCT);
                }
                parsBrackets_.put(i_, curChar_);
                usedCaller_ = curChar_;
            }
            if (curChar_ == PAR_RIGHT) {
                usedEnder_ = curChar_;
                parsBrackets_.removeKey(parsBrackets_.lastKey());
                if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO) {
                    operators_.put(i_, String.valueOf(PAR_RIGHT));
                }
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
                for (OperationPriority op_: getOperationsByLowerPriority(prio_)) {
                    if (_string.substring(i_, i_ + 2).startsWith(op_.getOperation())) {
                        if (op_ == DOT_OPER) {
                            if (i_ <= minIndexDot_) {
                                continue;
                            }
                            if (Character.isDigit(_string.charAt(i_ + 1))) {
                                continue;
                            }
                        }
                        if (op_ == UNARY_PLUS_OPER || op_ == UNARY_MINUS_OPER || op_ == NEG_BOOL_OPER) {
                            if (i_ > firstPrintChar_) {
                                continue;
                            }
                        }
                        if (op_ == MINUS_OPER || op_ == PLUS_OPER) {
                            if (isUnary(_string, firstPrintChar_, i_)) {
                                continue;
                            }
                        }
                        if (op_ == EQ_OPER) {
                            if (i_ > 0) {
                                if (_string.charAt(i_ - 1) == NEG_BOOL_CHAR) {
                                    continue;
                                }
                                if (_string.charAt(i_ - 1) == LOWER_CHAR) {
                                    continue;
                                }
                                if (_string.charAt(i_ - 1) == GREATER_CHAR) {
                                    continue;
                                }
                            }
                        }
                        if (op_.getPriority() == prio_) {
                            operators_.put(i_, op_.getOperation());
                        } else {
                            operators_.clear();
                            operators_.put(i_, op_.getOperation());
                        }
                        prio_ = op_.getPriority();
                        break;
                    }
                }
            }
            i_++;
        }
        if (prio_ == CMP_PRIO) {
            if (operators_.size() != CustList.ONE_ELEMENT) {
                if (operators_.size() > CustList.ONE_ELEMENT) {
                    _conf.getLastPage().setOffset(_d.getIndexBegin()+_offset+operators_.getKey(CustList.SECOND_INDEX));
                }
                throw new BadComparisonException(_string+RETURN_LINE+_conf.joinPages());
            }
        }
        if (prio_ == EQ_PRIO) {
            if (operators_.size() != CustList.ONE_ELEMENT) {
                if (operators_.size() > CustList.ONE_ELEMENT) {
                    _conf.getLastPage().setOffset(_d.getIndexBegin()+_offset+operators_.getKey(CustList.SECOND_INDEX));
                }
                throw new BadComparisonException(_string+RETURN_LINE+_conf.joinPages());
            }
        }
        if (!suffixVar_.toString().isEmpty()) {
            if (prio_ == DOT_PRIO) {
                if (!onlySpacesTo(_string, minIndexDot_-1, len_, ARR_LEFT)) {
                    operators_.put(minIndexDot_, EMPTY_STRING);
                }
            } else if (prio_ == FCT_OPER_PRIO) {
                prio_ = DOT_PRIO;
                operators_.clear();
                if (!onlySpacesTo(_string, minIndexDot_-1, len_, ARR_LEFT)) {
                    operators_.put(minIndexDot_, EMPTY_STRING);
                }
            }
        }
        OperationsSequence op_ = new OperationsSequence();
        op_.setPriority(prio_);
        op_.setOperators(operators_);
        if (prioMax_ == prio_) {
            if (usedCaller_ != null) {
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
                        parsBrackets_.removeKey(parsBrackets_.getKey(parsBrackets_.size() - 1));
                    }
                    if (curChar_ == ARR_LEFT) {
                        parsBrackets_.put(i, curChar_);
                    }
                    if (curChar_ == ARR_RIGHT) {
                        parsBrackets_.removeKey(parsBrackets_.getKey(parsBrackets_.size() - 1));
                    }
                    if (curChar_ == SEP_ARG) {
                        if (parsBrackets_.isEmpty()) {
                            newOperators_.put(i, String.valueOf(SEP_ARG));
                        }
                    }
                }
                newOperators_.put(end_, String.valueOf(usedEnder_));
                String fctName_ = _string.substring(CustList.FIRST_INDEX, _string.indexOf(usedCaller_));
                if (fctName_.trim().isEmpty() && newOperators_.size() > 2) {
                    _conf.getLastPage().setOffset(_d.getIndexBegin()+_offset+newOperators_.getKey(CustList.SECOND_INDEX));
                    throw new BadNumberArgumentException(_string+RETURN_LINE+_conf.joinPages());
                }
                op_.setFctName(fctName_);
                op_.setUseFct(true);
                op_.setOperators(newOperators_);
            }
        }
        op_.setupValues(_string);
        op_.addOffset(_offset);
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
                return false;
            }
            i_++;
            if (i_ <= _to) {
                if (!Character.isDigit(_string.charAt(i_))) {
                    return false;
                }
            }
        }
        int nbDots_ = 0;
        while (i_ <= _to) {
            if (!Character.isDigit(_string.charAt(i_))) {
                if (Character.isLetter(_string.charAt(i_))) {
                    if (nbDots_ == 0) {
                        return false;
                    }
                    i_++;
                    continue;
                }
                if (_string.charAt(i_) != DOT_VAR || nbDots_ > 0) {
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

    static boolean isUnary(String _string, int _min, int _i) {
        int i_ = _i;
        if (i_ > _min) {
            if (StringList.isWordChar(_string.charAt(i_ - 1))) {
                return false;
            }
            if (_string.charAt(i_ - 1) == PAR_RIGHT) {
                return false;
            }
            if (_string.charAt(i_ - 1) == ARR_RIGHT) {
                return false;
            }
            if (_string.charAt(i_ - 1) == DOT_VAR) {
                return false;
            }
            if (_string.charAt(i_ - 1) == GET_VAR) {
                return false;
            }
            if (_string.charAt(i_ - 1) == DELIMITER_CHAR) {
                return false;
            }
            if (_string.charAt(i_ - 1) == DELIMITER_STRING) {
                return false;
            }
        }
        return true;
    }
    static boolean procWordFirstChar(String _string, int _i, String _word, int _max) {
        int len_ = _max;
        if (_i + _word.length() <= len_) {
            boolean process_ = true;
            if (_i + _word.length() < len_) {
                if (StringList.isWordChar(_string.charAt(_i + _word.length()))) {
                    process_ = false;
                }
            }
            if (!_string.substring(_i, _i + _word.length()).startsWith(_word)) {
                process_ = false;
            }
            return process_;
        }
        return false;
    }
}
