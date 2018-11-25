package code.expressionlanguage;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.FieldBlock;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.util.BooleanList;
import code.util.CharList;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;


public final class ElResolver {

    public static final int BAD_PRIO = -1;
    public static final int CONST_PRIO = 0;
    public static final int AFF_PRIO = 1;
    public static final int TERNARY_PRIO = 2;
    public static final int OR_PRIO = 3;
    public static final int AND_PRIO = 4;
    public static final int BIT_OR_PRIO = 5;
    public static final int BIT_XOR_PRIO = 6;
    public static final int BIT_AND_PRIO = 7;
    public static final int EQ_PRIO = 8;
    public static final int CMP_PRIO = 9;
    public static final int SHIFT_PRIO = 10;
    public static final int ADD_PRIO = 11;
    public static final int MULT_PRIO = 12;
    public static final int UNARY_PRIO = 13;
    public static final int POST_INCR_PRIO = 14;
    public static final int FCT_OPER_PRIO = 15;
    public static final byte UNICODE_SIZE = 4;

    private static final String EMPTY_STRING = "";
    private static final char LINE_RETURN = '\n';
    private static final char FORM_FEED = '\f';
    private static final char BOUND = '\b';
    private static final char LINE_FEED = '\r';
    private static final char TAB = '\t';
    private static final char ESCAPE_META_CHAR = '\\';
    private static final char DELIMITER_CHAR = 39;
    private static final char DELIMITER_STRING = 34;
    private static final char ARR_LEFT = '[';
    private static final char ARR_RIGHT = ']';
    private static final char ANN_ARR_LEFT = '{';
    private static final char ANN_ARR_RIGHT = '}';
    private static final char PAR_LEFT = '(';
    private static final char PAR_RIGHT = ')';
    private static final char SEP_ARG = ',';
    private static final char DOT_VAR = '.';
    private static final char DOUBLE = 'd';
    private static final char LONG = 'L';
    private static final char INTEGER = 'i';
    private static final char NB_INTERN_SP = '_';
    private static final String GET_INDEX = ";;";
    private static final String GET_CATCH_VAR = ";..";
    private static final String GET_LOC_VAR = ";.";
    private static final String GET_ATTRIBUTE = ";";
    private static final String SIMPLE_SIFFIX = ";";
    private static final String GET_PARAM = ";.;";
    private static final String GET_FIELD = ";;;";
    private static final char ANNOT = '@';

    private static final char MIN_ENCODE_DIGIT = '0';
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
    private static final char XOR_CHAR = '^';
    private static final char NEG_BOOL = '~';
    private static final char BEGIN_TERNARY = '?';
    private static final char DELIMITER_TEXT = '`';

    private ElResolver() {
    }

    public static Delimiters checkSyntaxDelimiters(String _string, Analyzable _conf, int _minIndex, char _begin, char _end) {
        Delimiters d_ = new Delimiters();
        d_.setBegin(_begin);
        d_.setEnd(_end);
        d_.setPartOfString(true);
        return commonCheck(_string, _conf, _minIndex, true, d_);
    }

    public static Delimiters checkSyntax(String _string, Analyzable _conf, int _elOffest) {
        return commonCheck(_string, _conf, _elOffest, false,new Delimiters());
    }
    static Delimiters commonCheck(String _string, Analyzable _conf, int _minIndex, boolean _delimiters, Delimiters _d) {
        char begin_ = _d.getBegin();
        char end_ = _d.getEnd();
        boolean partOfString_ = _d.isPartOfString();

        Delimiters d_ = new Delimiters();
        NatTreeMap<Integer,Character> parsBrackets_;
        parsBrackets_ = new NatTreeMap<Integer,Character>();

        boolean constString_ = false;
        boolean constChar_ = false;
        boolean constText_ = false;
        boolean escapedMeta_ = false;
        int unicode_ = 0;
        int len_ = _string.length();
        int i_ = _minIndex;
        int lastDoubleDot_ = i_;
        boolean beginOrEnd_ = false;
        boolean aff_ = false;
        boolean ctorCall_ = false;
        while (i_ < len_) {
            if (!Character.isWhitespace(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        int beginIndex_ = i_;
        if (i_ >= len_) {
            d_.setBadOffset(i_);
            return d_;
        }
        Options opt_ = _conf.getOptions();
        KeyWords keyWords_ = _conf.getKeyWords();
        char suffix_ = opt_.getSuffix();
        StringInfo si_ = new StringInfo();
        i_ = _minIndex;
        int nbChars_ = 0;
        String keyWordBool_ = keyWords_.getKeyWordBool();
        String keyWordCast_ = keyWords_.getKeyWordCast();
        String keyWordClass_ = keyWords_.getKeyWordClass();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordFalse_ = keyWords_.getKeyWordFalse();
        String keyWordFirstopt_ = keyWords_.getKeyWordFirstopt();
        String keyWordId_ = keyWords_.getKeyWordId();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        String keyWordInterfaces_ = keyWords_.getKeyWordInterfaces();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        String keyWordLambda_ = keyWords_.getKeyWordLambda();
        String keyWordNew_ = keyWords_.getKeyWordNew();
        String keyWordNull_ = keyWords_.getKeyWordNull();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        String keyWordTrue_ = keyWords_.getKeyWordTrue();
        String keyWordValueOf_ = keyWords_.getKeyWordValueOf();
        String keyWordValues_ = keyWords_.getKeyWordValues();
        String keyWordVararg_ = keyWords_.getKeyWordVararg();
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (constChar_) {
                IndexUnicodeEscape unic_ = new IndexUnicodeEscape();
                unic_.setIndex(i_);
                unic_.setEscape(escapedMeta_);
                unic_.setNbChars(nbChars_);
                unic_.setPart(true);
                unic_.setUnicode(unicode_);
                unic_.setStringInfo(si_);
                IndexUnicodeEscape res_ = processStrings(keyWords_,_string, len_, unic_, DELIMITER_CHAR);
                int index_ = res_.getIndex();
                if (index_ < 0) {
                    d_.setBadOffset(-index_);
                    return d_;
                }
                if (!res_.isPart()) {
                    d_.getStringInfo().add(si_);
                    d_.getDelStringsChars().add(i_);
                    si_ = new StringInfo();
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
                unic_.setStringInfo(si_);
                unic_.setIndex(i_);
                unic_.setEscape(escapedMeta_);
                unic_.setNbChars(nbChars_);
                unic_.setPart(true);
                unic_.setUnicode(unicode_);
                IndexUnicodeEscape res_ = processStrings(keyWords_, _string, len_, unic_, DELIMITER_STRING);
                int index_ = res_.getIndex();
                if (index_ < 0) {
                    d_.setBadOffset(-index_);
                    return d_;
                }
                if (!res_.isPart()) {
                    d_.getStringInfo().add(si_);
                    d_.getDelStringsChars().add(i_);
                    si_ = new StringInfo();
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
            if (constText_) {
                IndexUnicodeEscape unic_ = new IndexUnicodeEscape();
                unic_.setStringInfo(si_);
                unic_.setIndex(i_);
                unic_.setEscape(escapedMeta_);
                unic_.setNbChars(nbChars_);
                unic_.setPart(true);
                unic_.setUnicode(unicode_);
                IndexUnicodeEscape res_ = processStrings(keyWords_, _string, len_, unic_, DELIMITER_TEXT);
                int index_ = res_.getIndex();
                if (index_ < 0) {
                    d_.setBadOffset(-index_);
                    return d_;
                }
                if (!res_.isPart()) {
                    d_.getStringInfo().add(si_);
                    d_.getDelStringsChars().add(i_);
                    si_ = new StringInfo();
                    constText_ = false;
                    i_++;
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
                int last_ = i_;
                while (j_ < len_) {
                    char locChar_ = _string.charAt(j_);
                    if (StringList.isDollarWordChar(locChar_)) {
                        last_ = j_;
                        j_++;
                        continue;
                    }
                    if (locChar_ == DOT_VAR) {
                        last_ = j_;
                        j_++;
                        continue;
                    }
                    if (Character.isWhitespace(locChar_)) {
                        j_++;
                        continue;
                    }
                    break;
                }
                if (j_ < len_ && _string.charAt(j_) == PAR_LEFT) {
                    d_.getCallings().add(j_);
                } else {
                    d_.getDelSimpleAnnotations().add(i_);
                    d_.getDelSimpleAnnotations().add(last_);
                }
                i_ = j_;
                continue;
            }
            String sub_ = _string.substring(i_);
            if (ContextEl.startsWithKeyWord(sub_, keyWordCast_)) {
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
                d_.getDelCast().add(i_);
                d_.getDelCast().add(indexParRight_);
                d_.getDelCastExtract().add(EMPTY_STRING);
                i_ = indexParRight_ + 1;
                continue;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordVararg_)) {
                int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
                int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
                if (indexParRight_ < 0) {
                    d_.setBadOffset(len_);
                    return d_;
                }
                d_.getDelVararg().add(i_);
                d_.getDelVararg().add(indexParRight_);
                i_ = indexParRight_ + 1;
                continue;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordClass_)) {
                int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
                int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
                if (indexParRight_ < 0) {
                    d_.setBadOffset(len_);
                    return d_;
                }
                d_.getDelClass().add(i_);
                d_.getDelClass().add(indexParRight_);
                i_ = indexParRight_ + 1;
                continue;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordInstanceof_)) {
                int next_ = i_ + keyWordInstanceof_.length();
                if (Character.isWhitespace(_string.charAt(next_))) {
                    //instanceof
                    if (i_ <= 0 || StringList.isDollarWordChar(_string.charAt(i_-1))) {
                        //error
                        d_.setBadOffset(i_-1);
                        return d_;
                    }
                    while (next_ < len_) {
                        char curLoc_ = _string.charAt(next_);
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
                    if (next_ < len_) {
                        if (_string.charAt(next_) == LOWER_CHAR) {
                            int nbOpened_ = 1;
                            next_++;
                            boolean exitWhenNoSpace_ = false;
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
                                    if (nbOpened_ == 0 && !_string.substring(next_).trim().startsWith(".")) {
                                        exitWhenNoSpace_ = true;
                                    }
                                    continue;
                                }
                                if (nbOpened_ == 0) {
                                    if (exitWhenNoSpace_ && !Character.isWhitespace(curLoc_)) {
                                        break;
                                    }
                                }
                                next_++;
                                continue;
                            }
                            if (nbOpened_ > 0) {
                                d_.setBadOffset(next_);
                                return d_;
                            }
                        }
                    }
                    if (next_ < len_) {
                        char curLoc_ = _string.charAt(next_);
                        if (curLoc_ == ARR_LEFT) {
                            boolean ok_ = true;
                            while (next_ < len_) {
                                curLoc_ = _string.charAt(next_);
                                if (Character.isWhitespace(curLoc_)) {
                                    next_++;
                                    continue;
                                }
                                if (curLoc_ == ARR_LEFT) {
                                    if (!ok_) {
                                        break;
                                    }
                                    ok_ = false;
                                    next_++;
                                    continue;
                                }
                                if (curLoc_ == ARR_RIGHT) {
                                    if (ok_) {
                                        ok_ = false;
                                        break;
                                    }
                                    ok_ = true;
                                    next_++;
                                    continue;
                                }
                                break;
                            }
                        }
                    }
                    d_.getAllowedOperatorsIndexes().add(i_);
                    d_.getDelInstanceof().add(i_);
                    d_.getDelInstanceof().add(next_);
                    i_ = next_;
                    continue;
                }
                d_.setBadOffset(next_);
                return d_;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordNew_)) {
                int j_ = i_;

                int count_ = 0;
                while (j_ < len_) {
                    char curLoc_ = _string.charAt(j_);
                    if (curLoc_ == Templates.LT) {
                        count_++;
                        j_++;
                        continue;
                    }
                    if (curLoc_ == Templates.GT) {
                        count_--;
                        j_++;
                        continue;
                    }
                    if (curLoc_ == PAR_LEFT) {
                        d_.getCallings().add(j_);
                        break;
                    }
                    if (curLoc_ == ARR_LEFT) {
                        if (count_ == 0) {
                            break;
                        }
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
            if (ContextEl.startsWithKeyWord(sub_, keyWordLambda_)) {
                //lambda
                int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
                int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
                if (indexParRight_ < 0) {
                    d_.setBadOffset(len_);
                    return d_;
                }
                d_.getDelLambda().add(i_);
                d_.getDelLambda().add(indexParRight_);
                i_ = indexParRight_ + 1;
                continue;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordId_)) {
                //lambda
                int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
                int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
                if (indexParRight_ < 0) {
                    d_.setBadOffset(len_);
                    return d_;
                }
                d_.getDelIds().add(i_);
                d_.getDelIds().add(indexParRight_);
                i_ = indexParRight_ + 1;
                continue;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordStatic_)) {
                int afterStatic_ = i_ + keyWordStatic_.length();
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
            if (ContextEl.startsWithKeyWord(sub_, keyWordSuper_)) {
                int afterSuper_ = i_ + keyWordSuper_.length();
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
                        ctorCall_ = true;
                        d_.getCallings().add(afterSuper_);
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
                if (afterSuper_ < len_) {
                    if (_string.charAt(afterSuper_) != PAR_LEFT) {
                        d_.getDelKeyWordSuper().add(i_);
                        d_.getDelKeyWordSuper().add(afterSuper_);
                    } else {
                        d_.getCallings().add(afterSuper_);
                    }
                } else {
                    d_.getDelKeyWordSuper().add(i_);
                    d_.getDelKeyWordSuper().add(afterSuper_);
                }
                i_ = afterSuper_;
                continue;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordClasschoice_)) {
                int afterClassChoice_ = i_ + keyWordClasschoice_.length();
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
                    d_.getCallings().add(afterClassChoice_);
                    i_ = afterClassChoice_;
                    continue;
                }
                //field
                d_.getDelKeyWordClassChoice().add(i_);
                d_.getDelKeyWordClassChoice().add(afterClassChoice_);
                i_ = afterClassChoice_;
                continue;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordSuperaccess_)) {
                int afterClassChoice_ = i_ + keyWordSuperaccess_.length();
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
                if (!pass_) {
                    d_.setBadOffset(afterClassChoice_);
                    return d_;
                }
                if (afterClassChoice_ >= len_) {
                    //field
                    d_.getDelKeyWordSuperAccess().add(i_);
                    d_.getDelKeyWordSuperAccess().add(afterClassChoice_);
                    i_ = afterClassChoice_;
                    continue;
                }
                if (_string.charAt(afterClassChoice_) == PAR_LEFT) {
                    //fct
                    d_.getCallings().add(afterClassChoice_);
                    i_ = afterClassChoice_;
                    continue;
                }
                //field
                d_.getDelKeyWordSuperAccess().add(i_);
                d_.getDelKeyWordSuperAccess().add(afterClassChoice_);
                i_ = afterClassChoice_;
                continue;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordThisaccess_)) {
                int afterClassChoice_ = i_ + keyWordThisaccess_.length();
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
                if (!pass_) {
                    d_.setBadOffset(afterClassChoice_);
                    return d_;
                }
                if (afterClassChoice_ >= len_) {
                    d_.setBadOffset(afterClassChoice_);
                    return d_;
                }
                if (_string.charAt(afterClassChoice_) == PAR_LEFT) {
                    //fct
                    d_.getCallings().add(afterClassChoice_);
                    i_ = afterClassChoice_;
                    continue;
                }
                //field
                d_.setBadOffset(afterClassChoice_);
                return d_;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordInterfaces_)) {
                int afterClassChoice_ = i_ + keyWordInterfaces_.length();
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
                ctorCall_ = true;
                d_.getCallings().add(afterClassChoice_);
                i_ = afterClassChoice_;
                continue;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordThat_)) {
                int afterSuper_ = i_ + keyWordThat_.length();
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
                        break;
                    }
                    afterSuper_++;
                }
                if (afterSuper_ < len_ && _string.charAt(afterSuper_) == PAR_LEFT) {
                    d_.getCallings().add(afterSuper_);
                } else {
                    d_.setBadOffset(afterSuper_);
                    return d_;
                }
                i_ = afterSuper_;
                continue;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordThis_)) {
                int afterSuper_ = i_ + keyWordThis_.length();
                while (afterSuper_ < len_) {
                    if (!Character.isWhitespace(_string.charAt(afterSuper_))) {
                        //_string.charAt(afterSuper_) != EXTERN_CLASS && !foundHat_
                        break;
                    }
                    afterSuper_++;
                }
                if (afterSuper_ < len_ && _string.charAt(afterSuper_) == PAR_LEFT) {
                    ctorCall_ = true;
                    d_.getCallings().add(afterSuper_);
                }
                i_ = afterSuper_;
                continue;
            }
            boolean foundValue_ = false;
            for (String s: StringList.wrapStringArray(keyWordTrue_,keyWordFalse_,keyWordNull_)) {
                if (ContextEl.startsWithKeyWord(sub_, s)) {
                    int afterSuper_ = i_ + s.length();
                    while (afterSuper_ < len_) {
                        if (!Character.isWhitespace(_string.charAt(afterSuper_))) {
                            break;
                        }
                        afterSuper_++;
                    }
                    foundValue_ = true;
                    i_ = afterSuper_;
                    break;
                }
            }
            if (foundValue_) {
                continue;
            }
            for (String s: StringList.wrapStringArray(keyWordFirstopt_,keyWordBool_,keyWordValueOf_,keyWordValues_)) {
                if (ContextEl.startsWithKeyWord(sub_, s)) {
                    int index_ = processPredefinedMethod(_string, i_, s, len_);
                    if (index_ < 0) {
                        d_.setBadOffset(-index_);
                        return d_;
                    }
                    foundValue_ = true;
                    d_.getCallings().add(index_);
                    i_ = index_;
                    break;
                }
            }
            if (foundValue_) {
                continue;
            }
            if (ContextEl.startsWithKeyWord(sub_, keyWordIntern_)) {
                int index_ = processPredefinedMethod(_string, i_, keyWordIntern_, len_);
                if (index_ >= 0) {
                    d_.getCallings().add(index_);
                    i_ = index_;
                    continue;
                }
                if (_conf.isInternGlobal()) {
                    int afterSuper_ = i_ + keyWordIntern_.length();
                    String trim_ = _string.substring(afterSuper_).trim();
                    if (trim_.startsWith(String.valueOf(DOT_VAR))) {
                        while (true) {
                            if (_string.charAt(afterSuper_) == DOT_VAR) {
                                //_string.charAt(afterSuper_) != EXTERN_CLASS && !foundHat_
                                break;
                            }
                            afterSuper_++;
                        }
                        i_ = afterSuper_;
                        continue;
                    }
                }
            }
            if (StringList.isDollarWordChar(curChar_)) {
                if (isNumber(i_, len_, _string, opt_)) {
                    NumberInfosOutput res_ = processNb(keyWords_, i_, len_, _string, false, opt_);
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
                            if (ContextEl.startsWithKeyWord(_string.substring(i_), keyWordInstanceof_)) {
                                i_=bk_;
                                break;
                            }
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
                            break;
                        }
                    }
                    i_++;
                }
                ConstType type_ = ConstType.NOTHING;
                boolean tolerateDot_ = false;
                VariableInfo info_ = new VariableInfo();
                boolean other_ = false;
                String prev_ = _string.substring(0, beginWord_).trim();
                String word_ = _string.substring(beginWord_, i_);
                if (opt_.getSuffixVar() == VariableSuffix.DISTINCT) {
                    if (i_ < len_ && _string.charAt(i_) == suffix_) {
                        if (i_ + 1 < len_ && _string.charAt(i_ + 1) == suffix_) {
                            if (i_ + 2 < len_ && _string.charAt(i_ + 2) == suffix_) {
                                type_ = ConstType.CUST_FIELD;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_+GET_FIELD.length());
                                info_.setName(word_);
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
                                info_.setName(word_);
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
                            } else if (i_ + 2 < len_ && _string.charAt(i_ + 2) == suffix_) {
                                type_ = ConstType.PARAM;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_+GET_PARAM.length());
                                info_.setName(word_);
                                i_ += GET_PARAM.length();
                                d_.getVariables().add(info_);
                            } else {
                                type_ = ConstType.LOC_VAR;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_+GET_LOC_VAR.length());
                                info_.setName(word_);
                                i_ += GET_LOC_VAR.length();
                                d_.getVariables().add(info_);
                            }
                        } else {
                            type_ = ConstType.LOOP_VAR;
                            info_.setKind(type_);
                            info_.setFirstChar(beginWord_);
                            info_.setLastChar(i_ + GET_ATTRIBUTE.length());
                            info_.setName(word_);
                            i_ += GET_ATTRIBUTE.length();
                            d_.getVariables().add(info_);
                        }
                    } else {
                        other_ = true;
                    }
                } else if (opt_.getSuffixVar() == VariableSuffix.FIELDS) {
                    if (i_ < len_ && _string.charAt(i_) == suffix_) {
                        if (i_ + 1 < len_ && _string.charAt(i_ + 1) == suffix_) {
                            if (i_ + 2 < len_ && _string.charAt(i_ + 2) == suffix_) {
                                type_ = ConstType.CUST_FIELD;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_+GET_FIELD.length());
                                info_.setName(word_);
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
                                info_.setName(word_);
                                i_ += GET_INDEX.length();
                                d_.getVariables().add(info_);
                            }
                        } else {
                            if (prev_.endsWith(String.valueOf(DOT_VAR))) {
                                if (i_ >= len_ || _string.substring(i_).trim().charAt(0) != PAR_LEFT) {
                                    type_ = ConstType.WORD;
                                    info_.setKind(type_);
                                    info_.setFirstChar(beginWord_);
                                    info_.setLastChar(i_+SIMPLE_SIFFIX.length());
                                    info_.setName(word_);
                                    i_ += SIMPLE_SIFFIX.length();
                                    d_.getVariables().add(info_);
                                    tolerateDot_ = true;
                                } else {
                                    other_ = true;
                                }
                            } else if (_conf.getParameters().contains(word_)) {
                                type_ = ConstType.PARAM;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_+SIMPLE_SIFFIX.length());
                                info_.setName(word_);
                                i_ += SIMPLE_SIFFIX.length();
                                d_.getVariables().add(info_);
                            } else if (_conf.getAnalyzing().containsCatchVar(word_)) {
                                type_ = ConstType.CATCH_VAR;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_+SIMPLE_SIFFIX.length());
                                info_.setName(word_);
                                i_ += SIMPLE_SIFFIX.length();
                                d_.getVariables().add(info_);
                            } else if (_conf.containsMutableLoopVar(word_)) {
                                type_ = ConstType.LOOP_VAR;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_ + SIMPLE_SIFFIX.length());
                                info_.setName(word_);
                                i_ += SIMPLE_SIFFIX.length();
                                d_.getVariables().add(info_);
                            } else if (_conf.getAnalyzing().containsVar(word_)) {
                                type_ = ConstType.LOOP_VAR;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_ + SIMPLE_SIFFIX.length());
                                info_.setName(word_);
                                i_ += SIMPLE_SIFFIX.length();
                                d_.getVariables().add(info_);
                            } else {
                                type_ = ConstType.LOC_VAR;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_ + SIMPLE_SIFFIX.length());
                                info_.setName(word_);
                                i_ += SIMPLE_SIFFIX.length();
                                d_.getVariables().add(info_);
                            }
                        }
                    } else {
                        other_ = true;
                    }
                } else if (opt_.getSuffixVar() == VariableSuffix.MERGED) {
                    if (i_ < len_ && _string.charAt(i_) == suffix_) {
                        if (i_ + 1 < len_ && _string.charAt(i_ + 1) == suffix_) {
                            type_ = ConstType.LOOP_INDEX;
                            info_.setKind(type_);
                            info_.setFirstChar(beginWord_);
                            info_.setLastChar(i_+GET_INDEX.length());
                            info_.setName(word_);
                            i_ += GET_INDEX.length();
                            d_.getVariables().add(info_);
                            tolerateDot_ = true;
                        } else {
                            if (prev_.endsWith(String.valueOf(DOT_VAR))) {
                                if (i_ >= len_ || _string.substring(i_).trim().charAt(0) != PAR_LEFT) {
                                    type_ = ConstType.WORD;
                                    info_.setKind(type_);
                                    info_.setFirstChar(beginWord_);
                                    info_.setLastChar(i_+SIMPLE_SIFFIX.length());
                                    info_.setName(word_);
                                    i_ += SIMPLE_SIFFIX.length();
                                    d_.getVariables().add(info_);
                                    tolerateDot_ = true;
                                } else {
                                     other_ = true;
                                }
                            } else if (_conf.getParameters().contains(word_)) {
                                type_ = ConstType.PARAM;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_+SIMPLE_SIFFIX.length());
                                info_.setName(word_);
                                i_ += SIMPLE_SIFFIX.length();
                                d_.getVariables().add(info_);
                            } else if (_conf.getAnalyzing().containsCatchVar(word_)) {
                                type_ = ConstType.CATCH_VAR;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_+SIMPLE_SIFFIX.length());
                                info_.setName(word_);
                                i_ += SIMPLE_SIFFIX.length();
                                d_.getVariables().add(info_);
                            } else if (_conf.containsMutableLoopVar(word_)) {
                                type_ = ConstType.LOOP_VAR;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_ + SIMPLE_SIFFIX.length());
                                info_.setName(word_);
                                i_ += SIMPLE_SIFFIX.length();
                                d_.getVariables().add(info_);
                            } else if (_conf.getAnalyzing().containsVar(word_)) {
                                type_ = ConstType.LOOP_VAR;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_ + SIMPLE_SIFFIX.length());
                                info_.setName(word_);
                                i_ += SIMPLE_SIFFIX.length();
                                d_.getVariables().add(info_);
                            } else if (_conf.getAnalyzing().containsLocalVar(word_)) {
                                type_ = ConstType.LOC_VAR;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_ + SIMPLE_SIFFIX.length());
                                info_.setName(word_);
                                i_ += SIMPLE_SIFFIX.length();
                                d_.getVariables().add(info_);
                            } else {
                                type_ = ConstType.CUST_FIELD;
                                info_.setKind(type_);
                                info_.setFirstChar(beginWord_);
                                info_.setLastChar(i_+SIMPLE_SIFFIX.length());
                                info_.setName(word_);
                                i_ += SIMPLE_SIFFIX.length();
                                d_.getVariables().add(info_);
                            }
                        }
                    } else {
                        other_ = true;
                    }
                } else {
                    tolerateDot_ = true;
                    boolean fct_ = i_ < len_ && _string.substring(i_).trim().charAt(0) == PAR_LEFT;
                    if (prev_.endsWith(String.valueOf(DOT_VAR)) || fct_) {
                        if (!fct_) {
                            type_ = ConstType.WORD;
                            info_.setKind(type_);
                            info_.setFirstChar(beginWord_);
                            info_.setLastChar(i_);
                            info_.setName(word_);
                            d_.getVariables().add(info_);
                        } else {
                            other_ = true;
                        }
                    } else if (_conf.getParameters().contains(word_)) {
                        type_ = ConstType.PARAM;
                        info_.setKind(type_);
                        info_.setFirstChar(beginWord_);
                        info_.setLastChar(i_);
                        info_.setName(word_);
                        d_.getVariables().add(info_);
                    } else if (_conf.getAnalyzing().containsCatchVar(word_)) {
                        type_ = ConstType.CATCH_VAR;
                        info_.setKind(type_);
                        info_.setFirstChar(beginWord_);
                        info_.setLastChar(i_);
                        info_.setName(word_);
                        d_.getVariables().add(info_);
                    } else if (_conf.containsMutableLoopVar(word_)) {
                        type_ = ConstType.LOOP_VAR;
                        info_.setKind(type_);
                        info_.setFirstChar(beginWord_);
                        info_.setLastChar(i_);
                        info_.setName(word_);
                        d_.getVariables().add(info_);
                    } else if (_conf.getAnalyzing().containsVar(word_)) {
                        type_ = ConstType.LOOP_VAR;
                        info_.setKind(type_);
                        info_.setFirstChar(beginWord_);
                        info_.setLastChar(i_);
                        info_.setName(word_);
                        d_.getVariables().add(info_);
                    } else if (_conf.getAnalyzing().containsLocalVar(word_)) {
                        type_ = ConstType.LOC_VAR;
                        info_.setKind(type_);
                        info_.setFirstChar(beginWord_);
                        info_.setLastChar(i_);
                        info_.setName(word_);
                        d_.getVariables().add(info_);
                    } else {
                        other_ = true;
                    }
                }
                if (other_) {
                    if (i_ >= len_ || _string.substring(i_).trim().charAt(0) != PAR_LEFT) {
                        tolerateDot_ = true;
                        type_ = ConstType.WORD;
                        info_.setKind(type_);
                        info_.setFirstChar(beginWord_);
                        info_.setLastChar(i_);
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
                            if (prev_.endsWith(StringList.concat(dot_,dot_))) {
                                int j_ = beginWord_;
                                StringList parts_ = new StringList();
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
                                        parts_.add(part_.toString());
                                        part_.delete(0, part_.length());
                                        if (j_ + 1 < len_ && _string.charAt(j_ + 1) == DOT_VAR) {
                                            j_++;
                                            j_++;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                StringBuilder allparts_ = new StringBuilder(StringList.concat(dot_,dot_));
                                int partLen_ = parts_.size();
                                for (int i = 0; i < partLen_; i++) {
                                    allparts_.append(parts_.get(i));
                                    if (i + 1 < partLen_) {
                                        allparts_.append(DOT_VAR);
                                        allparts_.append(DOT_VAR);
                                    }
                                }
                                String id_ = allparts_.toString();
                                String typeRes_ = _conf.resolveCorrectTypeWithoutErrors(id_, false);
                                if (!typeRes_.isEmpty()) {
                                    d_.getDelKeyWordStatic().add(lastDoubleDot_);
                                    int next_;
                                    next_ = j_;
                                    d_.getDelKeyWordStatic().add(next_);
                                    d_.getDelKeyWordStaticExtract().add(typeRes_);
                                    i_ = next_;
                                } else {
                                    info_.setName(word_);
                                    d_.getVariables().add(info_);
                                }
                            } else if (!prev_.endsWith(dot_) && i_ < len_ &&_string.charAt(i_) == DOT_VAR) {
                                i_ = processFieldsStaticAccess(_conf, ctorCall_, _string, beginWord_, d_, aff_);
                            } else {
                                info_.setName(word_);
                                d_.getVariables().add(info_);
                            }
                        }
                    } else {
                        int j_ = i_;
                        while (_string.charAt(j_) != PAR_LEFT) {
                            j_++;
                        }
                        d_.getCallings().add(j_);
                    }
                }
                String nextPart_ = _string.substring(i_).trim();
                if (!tolerateDot_ && !nextPart_.isEmpty() && (nextPart_.charAt(0) == DOT_VAR || nextPart_.charAt(0) == suffix_)) {
                    d_.setBadOffset(i_);
                    return d_;
                }
                continue;
            }
            if (curChar_ == DOT_VAR) {
                if (i_ + 1 < len_ && _string.charAt(i_ + 1) == DOT_VAR) {
                    lastDoubleDot_ = i_;
                    i_++;
                    i_++;
                    continue;
                }
                if (isNumber(i_ + 1, len_, _string, opt_)) {
                    NumberInfosOutput res_ = processNb(keyWords_, i_ + 1, len_, _string, true, opt_);
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
                    if (isNumber(0, lenLoc_, nextPart_, opt_)) {
                        d_.setBadOffset(-i_-1);
                        return d_;
                    }
                }
            }
            if (curChar_ == ESCAPE_META_CHAR) {
                if (_delimiters) {
                    if (i_ + 1 < len_ && _string.substring(i_+1).trim().startsWith(String.valueOf(begin_))) {
                        d_.getEscapings().add(i_);
                        int j_ = i_ + 1;
                        while (_string.charAt(j_) != begin_) {
                            j_++;
                        }
                        beginOrEnd_ = true;
                        i_ = j_;
                        continue;
                    }
                    if (i_ + 1 < len_ && _string.substring(i_+1).trim().startsWith(String.valueOf(end_))) {
                        d_.getEscapings().add(i_);
                        int j_ = i_ + 1;
                        while (_string.charAt(j_) != end_) {
                            j_++;
                        }
                        beginOrEnd_ = true;
                        i_ = j_;
                        continue;
                    }
                }
                d_.setBadOffset(i_);
                return d_;
            }
            if (curChar_ == DELIMITER_CHAR) {
                constChar_ = true;
                nbChars_ = 0;
                d_.getDelStringsChars().add(i_);
                i_++;
                continue;
            }
            if (curChar_ == DELIMITER_STRING) {
                constString_ = true;
                d_.getDelStringsChars().add(i_);
                i_++;
                continue;
            }
            if (curChar_ == DELIMITER_TEXT){
                constText_ = true;
                d_.getDelStringsChars().add(i_);
                i_++;
                continue;
            }
            if (partOfString_ && curChar_ == end_) {
                if (!beginOrEnd_) {
                    partOfString_ = false;
                    break;
                }
                beginOrEnd_ = false;
            }
            if (partOfString_ && curChar_ == begin_) {
                if (!beginOrEnd_) {
                    d_.setBadOffset(i_);
                    return d_;
                }
                beginOrEnd_ = false;
            }
            if (curChar_ == PAR_LEFT) {
                int j_ = indexAfterPossibleCast(_conf, ctorCall_, _string, i_, len_, d_);
                if (j_ > i_) {
                    i_ = j_;
                    continue;
                }
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
            if (curChar_ == ARR_LEFT) {
                int j_ = i_ + 1;
                boolean skip_ = false;
                while (j_ < len_) {
                    char nextChar_ = _string.charAt(j_);
                    if (Character.isWhitespace(nextChar_)) {
                        j_++;
                        continue;
                    }
                    if (nextChar_ == ARR_RIGHT) {
                        skip_ = true;
                    }
                    break;
                }
                if (skip_) {
                    d_.getDimsAddonIndexes().add(i_);
                    d_.getDimsAddonIndexes().add(j_);
                    i_ = j_ + 1;
                    continue;
                }
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
            if (opt_.getSuffixVar() == VariableSuffix.NONE) {
                if (curChar_ == BEGIN_TERNARY) {
                    parsBrackets_.put(i_, curChar_);
                }
                if (curChar_ == opt_.getSuffix()) {
                    if (parsBrackets_.isEmpty()) {
                        d_.setBadOffset(i_);
                        return d_;
                    }
                    if (parsBrackets_.lastValue() != BEGIN_TERNARY) {
                        d_.setBadOffset(i_);
                        return d_;
                    }
                    parsBrackets_.removeKey(parsBrackets_.lastKey());
                }
            }
            if (curChar_ == SEP_ARG) {
                if (parsBrackets_.isEmpty() && !_conf.isMerged() && !(_conf.getCurrentBlock() instanceof FieldBlock)) {
                    d_.setBadOffset(i_);
                    return d_;
                }
            }
            boolean escapeOpers_ = false;
            boolean addOp_ = true;
            boolean andOr_ = false;
            boolean ltGt_ = false;
            if (curChar_ == MULT_CHAR) {
                escapeOpers_ = true;
            }
            if (curChar_ == MOD_CHAR) {
                escapeOpers_ = true;
            }
            if (curChar_ == DIV_CHAR) {
                escapeOpers_ = true;
            }
            if (curChar_ == PLUS_CHAR){
                if (i_ + 1 >= len_ || _string.charAt(i_ + 1) != PLUS_CHAR) {
                    escapeOpers_ = true;
                }
                if (beginIndex_ == i_) {
                    addOp_ = false;
                }
            }
            if (curChar_ == MINUS_CHAR){
                if (i_ + 1 >= len_ || _string.charAt(i_ + 1) != MINUS_CHAR) {
                    escapeOpers_ = true;
                }
                if (beginIndex_ == i_) {
                    addOp_ = false;
                }
            }
            if (curChar_ == AND_CHAR) {
                andOr_ = true;
                escapeOpers_ = true;
            }
            if (curChar_ == OR_CHAR) {
                andOr_ = true;
                escapeOpers_ = true;
            }
            if (curChar_ == LOWER_CHAR) {
                escapeOpers_ = true;
                ltGt_ = true;
            }
            if (curChar_ == XOR_CHAR) {
                escapeOpers_ = true;
            }
            if (opt_.getSuffixVar() == VariableSuffix.NONE) {
                if (curChar_ == BEGIN_TERNARY) {
                    escapeOpers_ = true;
                }
                if (curChar_ == opt_.getSuffix()) {
                    escapeOpers_ = true;
                }
            }
            if (curChar_ == GREATER_CHAR) {
                escapeOpers_ = true;
                ltGt_ = true;
            }
            if (curChar_ == EQ_CHAR) {
                if (parsBrackets_.isEmpty()) {
                    if (i_ + 1 < len_ && _string.charAt(i_ + 1) != EQ_CHAR) {
                        aff_ = true;
                    }
                }
                escapeOpers_ = true;
            }
            if (curChar_ == NEG_BOOL_CHAR) {
                escapeOpers_ = true;
                if (beginIndex_ == i_) {
                    addOp_ = false;
                }
            }
            if (curChar_ == ANN_ARR_LEFT) {
                escapeOpers_ = true;
            }
            if (curChar_ == ARR_LEFT) {
                escapeOpers_ = true;
            }
            if (curChar_ == PAR_LEFT) {
                escapeOpers_ = true;
            }
            if (curChar_ == SEP_ARG) {
                escapeOpers_ = true;
            }
            if (escapeOpers_) {
                int j_ = i_ + 1;
                if (ltGt_ && j_ < len_ && _string.charAt(j_) == curChar_) {
                    j_++;
                }
                if (ltGt_ && j_ < len_ && _string.charAt(j_) == curChar_) {
                    j_++;
                }
                if (ltGt_ && j_ < len_ && _string.charAt(j_) == curChar_) {
                    j_++;
                }
                if (andOr_ && j_ < len_ && _string.charAt(j_) == curChar_) {
                    j_++;
                } else if (j_ < len_ && _string.charAt(j_) == EQ_CHAR) {
                    j_++;
                }
                while (j_ < len_) {
                    char curLoc_ = _string.charAt(j_);
                    if (Character.isWhitespace(curLoc_)) {
                        j_++;
                        continue;
                    }
                    if (curLoc_ == PLUS_CHAR) {
                        j_++;
                        if (j_ < len_ && _string.charAt(j_) == PLUS_CHAR) {
                            j_++;
                        }
                        continue;
                    }
                    if (curLoc_ == MINUS_CHAR) {
                        j_++;
                        if (j_ < len_ && _string.charAt(j_) == MINUS_CHAR) {
                            j_++;
                        }
                        continue;
                    }
                    if (curLoc_ == NEG_BOOL_CHAR) {
                        j_++;
                        continue;
                    }
                    if (curLoc_ == NEG_BOOL) {
                        j_++;
                        continue;
                    }
                    String subAfter_ = _string.substring(j_);
                    if (ContextEl.startsWithKeyWord(subAfter_, keyWordCast_)) {
                        int indexParLeft_ = _string.indexOf(PAR_LEFT,j_+1);
                        int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
                        if (indexParRight_ < 0) {
                            d_.setBadOffset(len_);
                            return d_;
                        }
                        if (indexParRight_ + 1 >= len_) {
                            d_.setBadOffset(len_);
                            return d_;
                        }
                        d_.getDelCast().add(j_);
                        d_.getDelCast().add(indexParRight_);
                        d_.getDelCastExtract().add(EMPTY_STRING);
                        j_ = indexParRight_ + 1;
                        continue;
                    }
                    if (curLoc_ == PAR_LEFT) {
                        int before_ = d_.getDelCastExtract().size();
                        int k_ = indexAfterPossibleCast(_conf, ctorCall_, _string, j_, len_, d_);
                        int after_ = d_.getDelCastExtract().size();
                        j_ = k_;
                        if (before_ != after_) {
                            continue;
                        }
                    }
                    break;
                }
                if (addOp_) {
                    d_.getAllowedOperatorsIndexes().add(i_);
                }
                i_ = j_;
                continue;
            }
            if (curChar_ == PLUS_CHAR){
                if (addOp_) {
                    d_.getAllowedOperatorsIndexes().add(i_);
                }
                i_++;
                i_++;
                continue;
            }
            if (curChar_ == MINUS_CHAR){
                if (addOp_) {
                    d_.getAllowedOperatorsIndexes().add(i_);
                }
                i_++;
                i_++;
                continue;
            }
            boolean idOp_ = false;
            if (curChar_ == ANN_ARR_RIGHT) {
                idOp_ = true;
            }
            if (curChar_ == ARR_RIGHT) {
                idOp_ = true;
            }
            if (curChar_ == PAR_RIGHT) {
                idOp_ = true;
            }
            if (curChar_ == DOT_VAR) {
                idOp_ = true;
            }
            if (idOp_) {
                d_.getAllowedOperatorsIndexes().add(i_);
            }
            i_++;
        }
        if (constString_) {
            d_.setBadOffset(i_);
            return d_;
        }
        if (constChar_) {
            d_.setBadOffset(i_);
            return d_;
        }
        if (constText_) {
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
    static int processFieldsStaticAccess(Analyzable _conf, boolean _ctor,String _string, int _from, Delimiters _d, boolean _aff) {
        Delimiters d_ = _d;
        int i_ = _from;
        int len_ = _string.length();
        Numbers<Integer> indexes_ = new Numbers<Integer>();
        StringList parts_ = new StringList();
        StringList partsFields_ = new StringList();
        Numbers<Integer> begins_ = new Numbers<Integer>();
        Numbers<Integer> ends_ = new Numbers<Integer>();
        int fChar_ = -1;
        int lChar_ = -1;
        BooleanList doubleDotted_ = new BooleanList();
        StringBuilder part_ = new StringBuilder();
        boolean foundThis_ = false;
        boolean addLast_ = true;
        int j_ = i_;
        int k_ = i_;
        boolean aff_ = false;
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordNew_ = keyWords_.getKeyWordNew();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        while (j_ < len_) {
            char locChar_ = _string.charAt(j_);
            if (StringList.isDollarWordChar(locChar_)) {
                if (fChar_ == -1) {
                    fChar_ = j_;
                }
                if (lChar_ != -1 && lChar_ + 1 != j_) {
                    String tr_ = part_.toString().trim();
                    foundThis_ = StringList.quickEq(keyWordThis_, tr_);
                    addLast_ = !foundThis_;
                    if (!addLast_) {
                        indexes_.removeLast();
                    }
                    break;
                }
                lChar_ = j_;
                part_.append(locChar_);
                j_++;
                continue;
            }
            String tr_ = part_.toString().trim();
            if (Character.isWhitespace(locChar_)) {
                if (StringList.quickEq(keyWordNew_, tr_)) {
                    indexes_.removeLast();
                    addLast_ = false;
                    break;
                }
                part_.append(locChar_);
                j_++;
                continue;
            }
            if (StringList.quickEq(keyWordThis_, tr_)) {
                indexes_.removeLast();
                addLast_ = false;
                foundThis_ = true;
                break;
            }
            if (StringList.quickEq(keyWordSuper_, tr_)) {
                indexes_.removeLast();
                addLast_ = false;
                break;
            }
            if (StringList.quickEq(keyWordThat_, tr_)) {
                indexes_.removeLast();
                addLast_ = false;
                break;
            }
            if (StringList.quickEq(keyWordClasschoice_, tr_)) {
                indexes_.removeLast();
                addLast_ = false;
                break;
            }
            if (locChar_ == PAR_LEFT) {
                indexes_.removeLast();
                addLast_ = false;
                break;
            }
            if (locChar_ == DOT_VAR) {
                k_ = j_;
                indexes_.add(j_);
                parts_.add(part_.toString());
                partsFields_.add(part_.toString());
                begins_.add(fChar_);
                ends_.add(lChar_);
                part_.delete(0, part_.length());
                fChar_ = -1;
                lChar_ = -1;
                if (j_ + 1 < len_ && _string.charAt(j_ + 1) == DOT_VAR) {
                    doubleDotted_.add(true);
                    j_++;
                } else {
                    if (!doubleDotted_.isEmpty() && doubleDotted_.last()) {
                        addLast_ = false;
                        foundThis_ = true;
                        break;
                    }
                    doubleDotted_.add(false);
                }
                j_++;
                continue;
            }
            if (locChar_ == EQ_CHAR) {
                if (j_ + 1 < len_ && _string.charAt(j_ + 1) != EQ_CHAR) {
                    aff_ = true;
                }
            }
            break;
        }
        if (addLast_ && !begins_.containsObj(-1) && fChar_ > -1) {
            k_ = j_;
            partsFields_.add(part_.toString());
            begins_.add(fChar_);
            ends_.add(lChar_);
        }
        String glClass_ = _conf.getGlobalClass();
        boolean field_ = false;
        if (glClass_ != null && !foundThis_ && !partsFields_.isEmpty()) {
            field_ = true;
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(glClass_);
            String word_ = partsFields_.first().trim();
            boolean realAff_ = aff_ && partsFields_.size() == 1;
            FieldResult fr_ = OperationNode.resolveDeclaredCustField(_conf, _conf.isStaticContext() || _ctor, clArg_, true, true, word_, _conf.getCurrentBlock() != null, realAff_);
            if (fr_.getStatus() != SearchingMemberStatus.UNIQ || fr_.getId().getType() == null) {
                field_ = false;
            }
        } else {
            field_ = false;
        }
        if (field_ || _conf.isEnabledInternVars()) {
            ConstType type_ = ConstType.WORD;


            int lenFields_ = partsFields_.size();
            for (int i = 0; i < lenFields_; i++) {
                VariableInfo infoLoc_ = new VariableInfo();
                infoLoc_.setKind(type_);
                infoLoc_.setFirstChar(begins_.get(i));
                infoLoc_.setLastChar(ends_.get(i)+1);
                infoLoc_.setName(partsFields_.get(i).trim());
                d_.getVariables().add(infoLoc_);
            }
            d_.getAllowedOperatorsIndexes().addAllElts(indexes_);
            return k_;
        }
        StringBuilder allparts_ = new StringBuilder();
        int partLen_ = partsFields_.size();
        for (int i = 0; i < partLen_; i++) {
            allparts_.append(partsFields_.get(i));
            if (i + 1 < partLen_) {
                allparts_.append(DOT_VAR);
                if (doubleDotted_.get(i)) {
                    allparts_.append(DOT_VAR);
                }
            }
        }
        String id_ = allparts_.toString();
        String dot_ = String.valueOf(DOT_VAR);
        StringList candidates_ = new StringList();
        Options opt_ = _conf.getOptions();
        if (!opt_.isSingleInnerParts() && id_.indexOf(StringList.concat(dot_,dot_)) == -1) {
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
                String tr_ = ContextEl.removeDottedSpaces(c);
                if (_conf.getClassBody(tr_) != null) {
                    int n_ = indexes_.get(index_);
                    d_.getDelKeyWordStatic().add(i_);
                    d_.getDelKeyWordStatic().add(n_);
                    d_.getDelKeyWordStaticExtract().add(tr_);
                    i_ = n_;
                    found_ = true;
                    break;
                }
                index_++;
            }
            if (found_) {
                return i_;
            }
        }
        if (opt_.isSingleInnerParts() && !foundThis_) {
            StringList inners_ = Templates.getAllInnerTypesSingleDotted(id_, _conf);
            String start_ = inners_.first();
            int nb_=0;
            for (char c: start_.toCharArray()) {
                if (c == '.') {
                    nb_++;
                }
            }
            Block bl_ = _conf.getCurrentBlock();
            AccessingImportingBlock r_ = bl_.getImporting();
            String imp_ = _conf.lookupSingleImportType(start_, r_);
            if (!imp_.isEmpty()) {
                start_ = imp_;
            }
            for (String i: inners_.mid(1)) {
                String innerName_ = i.trim();
                StringList res_ = TypeUtil.getInners(true, glClass_, start_, innerName_, false, _conf);
                if (res_.size() == 1) {
                    start_ = res_.first();
                    nb_++;
                    continue;
                }
                int n_ = indexes_.get(nb_);
                d_.getDelKeyWordStatic().add(i_);
                d_.getDelKeyWordStatic().add(n_);
                d_.getDelKeyWordStaticExtract().add(start_);
                return n_;
            }
            d_.getDelKeyWordStatic().add(i_);
            d_.getDelKeyWordStatic().add(k_);
            d_.getDelKeyWordStaticExtract().add(start_);
            return k_;
        }
        String tr_ = ContextEl.removeDottedSpaces(id_);
        if (_conf.getClassBody(tr_) != null) {
            d_.getDelKeyWordStatic().add(i_);
            d_.getDelKeyWordStatic().add(k_);
            d_.getDelKeyWordStaticExtract().add(tr_);
            return k_;
        }
        String typeRes_ = _conf.resolveCorrectTypeWithoutErrors(id_, false);
        if (!typeRes_.isEmpty()) {
            d_.getDelKeyWordStatic().add(i_);
            i_ = k_;
            d_.getDelKeyWordStatic().add(i_);
            d_.getDelKeyWordStaticExtract().add(typeRes_);
            return i_;
        }
        int index_ = 0;
        for (String c: candidates_) {
            tr_ = _conf.resolveCorrectTypeWithoutErrors(c,false);
            if (!tr_.isEmpty()) {
                int n_ = indexes_.get(index_);
                d_.getDelKeyWordStatic().add(i_);
                d_.getDelKeyWordStatic().add(n_);
                d_.getDelKeyWordStaticExtract().add(tr_);
                return n_;
            }
            index_++;
        }
        return j_;
    }
    static IndexUnicodeEscape processStrings(KeyWords _key, String _string, int _max, IndexUnicodeEscape _infos, char _delimiter) {
        int i_ = _infos.getIndex();
        int nbChars_ = _infos.getNbChars();
        int unicode_ = _infos.getUnicode();
        int len_ = _max;
        char curChar_ = _string.charAt(i_);
        boolean escapedMeta_ = _infos.isEscape();
        IndexUnicodeEscape infos_ = new IndexUnicodeEscape();
        infos_.setStringInfo(_infos.getStringInfo());
        infos_.setEscape(escapedMeta_);
        infos_.setIndex(i_);
        infos_.setNbChars(nbChars_);
        infos_.setUnicode(unicode_);
        infos_.setPart(_infos.isPart());
        if (_delimiter == DELIMITER_TEXT) {
            if (curChar_ == DELIMITER_TEXT) {
                if (i_ + 1 >= len_ ||_string.charAt(i_ + 1) != DELIMITER_TEXT) {
                    infos_.setPart(false);
                    i_++;
                    infos_.setIndex(i_);
                    return infos_;
                }
                i_++;
            }
            infos_.getStringInfo().getChars().add(curChar_);
            nbChars_++;
            infos_.setNbChars(nbChars_);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
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
            infos_.getStringInfo().getChars().add(curChar_);
            nbChars_++;
            infos_.setNbChars(nbChars_);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (unicode_ > 0) {
            boolean ok_ = false;
            if (ContextEl.isDigit(curChar_)) {
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
            infos_.getStringInfo().getBuiltUnicode()[unicode_-1] = curChar_;
            if (unicode_ < UNICODE_SIZE) {
                unicode_++;
            } else {
                char[] unicodes_ = infos_.getStringInfo().getBuiltUnicode();
                char builtChar_ = LgNames.parseCharSixteen(new String(unicodes_));
                infos_.getStringInfo().getChars().add(builtChar_);
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
            infos_.getStringInfo().getChars().add(curChar_);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        String single_ = _key.getEscKeyWord(_string, i_);
        String newLine_ = _key.getKeyWordEscLine();
        String form_ = _key.getKeyWordEscForm();
        String rfeed_ = _key.getKeyWordEscFeed();
        String tab_ = _key.getKeyWordEscTab();
        String bound_ = _key.getKeyWordEscBound();
        if (single_ != null) {
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            if (StringList.quickEq(single_, newLine_)) {
                i_+=newLine_.length();
                infos_.getStringInfo().getChars().add(LINE_RETURN);
            } else if (StringList.quickEq(single_, form_)) {
                i_+=form_.length();
                infos_.getStringInfo().getChars().add(FORM_FEED);
            } else if (StringList.quickEq(single_, rfeed_)) {
                i_+=rfeed_.length();
                infos_.getStringInfo().getChars().add(LINE_FEED);
            } else if (StringList.quickEq(single_, tab_)) {
                i_+=tab_.length();
                infos_.getStringInfo().getChars().add(TAB);
            } else {
                i_+=bound_.length();
                infos_.getStringInfo().getChars().add(BOUND);
            }
            infos_.setIndex(i_);
            return infos_;
        }
//        if (curChar_ == IND_LINE) {
//            nbChars_++;
//            infos_.setNbChars(nbChars_);
//            infos_.setEscape(false);
//            i_++;
//            infos_.setIndex(i_);
//            return infos_;
//        }
//        if (curChar_ == IND_FORM) {
//            nbChars_++;
//            infos_.setNbChars(nbChars_);
//            infos_.setEscape(false);
//            i_++;
//            infos_.setIndex(i_);
//            return infos_;
//        }
//        if (curChar_ == IND_BOUND) {
//            nbChars_++;
//            infos_.setNbChars(nbChars_);
//            infos_.setEscape(false);
//            i_++;
//            infos_.setIndex(i_);
//            return infos_;
//        }
//        if (curChar_ == IND_LINE_FEED) {
//            nbChars_++;
//            infos_.setNbChars(nbChars_);
//            infos_.setEscape(false);
//            i_++;
//            infos_.setIndex(i_);
//            return infos_;
//        }
//        if (curChar_ == IND_TAB) {
//            nbChars_++;
//            infos_.setNbChars(nbChars_);
//            infos_.setEscape(false);
//            i_++;
//            infos_.setIndex(i_);
//            return infos_;
//        }
        if (curChar_ == ESCAPE_META_CHAR) {
            infos_.getStringInfo().getChars().add(ESCAPE_META_CHAR);
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        String unicodeStr_ = _key.getKeyWordEscUnicode();
        if (!_string.substring(i_).startsWith(unicodeStr_) || i_ + unicodeStr_.length() + UNICODE_SIZE > len_) {
            infos_.setIndex(-i_);
            return infos_;
        }
        unicode_ = 1;
        infos_.setUnicode(unicode_);
        i_+=unicodeStr_.length();
        infos_.setIndex(i_);
        return infos_;
    }
    static int processPredefinedMethod(String _string, int _i, String _name, int _max) {
        int afterSuper_ = _i + _name.length();
        int index_ = _string.indexOf(PAR_LEFT,afterSuper_);
        if (index_ < 0) {
            return -afterSuper_;
        }
        if (!_string.substring(afterSuper_, index_).trim().isEmpty()) {
            return -afterSuper_;
        }
        return index_;
    }

    static boolean isNumber(int _start, int _max, String _string, Options _opt) {
        if (_start >= _string.length()) {
            return false;
        }
        char first_ = _string.charAt(_start);
        int k_ = _start;
        boolean var_ = false;
        while (k_ < _max) {
            if (!StringList.isWordChar(_string.charAt(k_))) {
                if (_opt.getSuffixVar() != VariableSuffix.NONE && _string.charAt(k_) == _opt.getSuffix()) {
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
        return ContextEl.isDigit(first_) && !var_;
    }

    static NumberInfosOutput processNb(KeyWords _key, int _start, int _max, String _string, boolean _seenDot, Options _opt) {
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
        int start_ = _start;
        char startChar_ = _string.charAt(start_);
        int base_ = 10;
        String hexPre_ = _key.getKeyWordNbHex();
        String binPre_ = _key.getKeyWordNbBin();
        String decExp_ = _key.getKeyWordNbExpDec();
        String binExp_ = _key.getKeyWordNbExpBin();
        if (_seenDot) {
            nbInfos_.setDotted(true);
            nbInfos_.setSuffix(DOUBLE);
            decPart_.append(startChar_);
        } else {
            if (startChar_ == '0') {
                String sub_ = _string.substring(start_ + 1);
                if (start_ + 1 < _max && sub_.startsWith(hexPre_)) {
                    base_ = 16;
                    start_+=hexPre_.length();
                    start_++;
                    if (start_ >= _max) {
                        output_.setNextIndex(-start_);
                        return output_;
                    }
                    startChar_ = _string.charAt(start_);
                    intPart_.append(startChar_);
                } else if (start_ + 1 < _max && sub_.startsWith(binPre_)) {
                    base_ = 2;
                    start_+=binPre_.length();
                    start_++;
                    if (start_ >= _max) {
                        output_.setNextIndex(-start_);
                        return output_;
                    }
                    startChar_ = _string.charAt(start_);
                    intPart_.append(startChar_);
                } else if (start_ + 1 < _max && ContextEl.isDigit(_string.charAt(start_ + 1))){
                    base_ = 8;
                    start_++;
                    if (start_ >= _max) {
                        output_.setNextIndex(-start_);
                        return output_;
                    }
                    startChar_ = _string.charAt(start_);
                    intPart_.append(startChar_);
                } else {
                    intPart_.append(startChar_);
                }
            } else {
                intPart_.append(startChar_);
            }
        }
        nbInfos_.setBase(base_);
        int len_ = _max;
        int j_ = start_ + 1;
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
                            if (base_ == 10) {
                                nbInfos_.setDotted(true);
                                nbInfos_.setSuffix(DOUBLE);
                                output_.setNextIndex(j_ + 1);
                                return output_;
                            }
                            output_.setNextIndex(-(j_ + 1));
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
            String sub_ = _string.substring(j_);
            boolean isExp_;
            int off_;
            if (base_ == 10) {
                off_ = decExp_.length();
                isExp_ = sub_.startsWith(decExp_);
            } else {
                off_ = binExp_.length();
                isExp_ = sub_.startsWith(binExp_);
            }
            if (isExp_) {
                if (j_ + off_ < len_ && Character.isWhitespace(_string.charAt(j_ + off_))) {
                    output_.setNextIndex(-(j_ + off_));
                    return output_;
                }
                nbInfos_.setSuffix(DOUBLE);
                iExp_ = j_ + off_ - 1;
                j_ += off_ - 1;
                exp_ = true;
                break;
            }
            if (base_ == 10) {
                if (Character.isLetter(current_)) {
                    String suff_ = _key.getNbKeyWord(_string, j_);
                    if (suff_ == null) {
                        if (StringList.isDollarWordChar(_string.charAt(j_))) {
                            output_.setNextIndex(-j_);
                            return output_;
                        }
                        output_.setNextIndex(j_);
                    } else {
                        char ch_ = _key.getSuffixes().getVal(suff_);
                        nbInfos_.setSuffix(ch_);
                        output_.setNextIndex(j_ + suff_.length());
                    }
                    int n_ = output_.getNextIndex();
                    String next_ = _string.substring(n_).trim();
                    if (!isCorrectNbEnd(next_)) {
                        output_.setNextIndex(-(j_ + 1));
                        return output_;
                    }
                    return output_;
                }
            } else {
                if (!isDigit(current_, base_) && Character.isLetter(current_)) {
                    if (sub_.startsWith(hexPre_)) {
                        if (j_ + 1 < len_) {
                            current_ = _string.charAt(j_ + 1);
                            j_+=hexPre_.length();
                        }
                    }
                    String suff_ = _key.getNbKeyWord(_string, j_);
                    if (suff_ == null) {
                        if (StringList.isDollarWordChar(_string.charAt(j_))) {
                            output_.setNextIndex(-j_);
                            return output_;
                        }
                        output_.setNextIndex(j_);
                    } else {
                        char ch_ = _key.getSuffixes().getVal(suff_);
                        nbInfos_.setSuffix(ch_);
                        output_.setNextIndex(j_ + suff_.length());
                    }
                    int n_ = output_.getNextIndex();
                    String next_ = _string.substring(n_).trim();
                    if (!isCorrectNbEnd(next_)) {
                        output_.setNextIndex(-(j_ + 1));
                        return output_;
                    }
                    return output_;
                }
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
            nbInfos_.setSuffix(DOUBLE);
            output_.setNextIndex(j_ + 1);
            return output_;
        }
        if (dot_) {
            nbInfos_.setSuffix(DOUBLE);
            nbInfos_.setDotted(true);
            char next_ = _string.charAt(j_ + 1);
            String sub_ = _string.substring(j_ + 1);
            int off_;
            if (base_ == 10) {
                off_ = decExp_.length();
                exp_ = sub_.startsWith(decExp_);
            } else {
                off_ = binExp_.length();
                exp_ = sub_.startsWith(binExp_);
            }
            if (!isDigit(next_, base_) && next_ != NB_INTERN_SP && !exp_) {
                String suff_ = _key.getNbKeyWord(_string, j_ + 1);
                if (suff_ == null) {
                    if (StringList.isDollarWordChar(_string.charAt(j_ + 1))) {
                        output_.setNextIndex(-(j_ + 1));
                        return output_;
                    }
                } else {
                    j_+=suff_.length();
                    char su_ = _key.getSuffixes().getVal(suff_);
                    nbInfos_.setSuffix(su_);
                }
                String nextPart_ = _string.substring(j_+1).trim();
                if (!isCorrectNbEnd(nextPart_)) {
                    output_.setNextIndex(-(j_ + 1));
                    return output_;
                }
                output_.setNextIndex(j_ + 1);
                return output_;
            }
            if (exp_) {
                j_+=off_;
                //_string.charAt(j_) == EXP
                processExp(_key, _opt, j_, len_, _string, output_);
                return output_;
            }
            j_++;
            while (j_ < len_) {
                char curChar_ = _string.charAt(j_);
                if (!isDigit(curChar_, base_) && curChar_ != NB_INTERN_SP) {
                    break;
                }
                decPart_.append(curChar_);
                j_++;
            }
            if (j_ >= len_) {
                output_.setNextIndex(j_);
                return output_;
            }
            next_ = _string.charAt(j_);
            sub_ = _string.substring(j_);
            if (base_ == 10) {
                off_ = decExp_.length();
                exp_ = sub_.startsWith(decExp_);
            } else {
                off_ = binExp_.length();
                exp_ = sub_.startsWith(binExp_);
            }
            if (exp_) {
                processExp(_key, _opt, j_, len_, _string, output_);
                return output_;
            }
            if (base_ != 10) {
                if (sub_.startsWith(hexPre_)) {
                    j_+=hexPre_.length();
                }
            }
            String suff_ = _key.getNbKeyWord(_string, j_);
            if (suff_ == null) {
                if (StringList.isDollarWordChar(_string.charAt(j_))) {
                    output_.setNextIndex(-j_);
                    return output_;
                }
            } else {
                char su_ = _key.getSuffixes().getVal(suff_);
                nbInfos_.setSuffix(su_);
                j_ += suff_.length();
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
            processExp(_key, _opt, iExp_, len_, _string, output_);
            return output_;
        }
        if (!_seenDot) {
            if (_opt.isUpperLong()) {
                nbInfos_.setSuffix(LONG);
            } else {
                nbInfos_.setSuffix(INTEGER);
            }
        }
        String nextPart_ = _string.substring(j_).trim();
        if (!isCorrectNbEnd(nextPart_)) {
            output_.setNextIndex(-j_);
            return output_;
        }
        output_.setNextIndex(j_);
        return output_;
    }
    static boolean isDigit(char _char, int _base) {
        boolean ok_ = false;
        if (_base == 10) {
            ok_ = ContextEl.isDigit(_char);
        } else if (_base == 16) {
            if (ContextEl.isDigit(_char)) {
                ok_ = true;
            }
            if (_char >= MIN_ENCODE_LOW_LETTER && _char <= MAX_ENCODE_LOW_LETTER) {
                ok_ = true;
            }
            if (_char >= MIN_ENCODE_UPP_LETTER && _char <= MAX_ENCODE_UPP_LETTER) {
                ok_ = true;
            }
        } else if (_base == 2) {
            if (_char == '0' || _char == '1') {
                ok_ = true;
            }
        } else {
            if (_char >= MIN_ENCODE_DIGIT && _char <= '7') {
                ok_ = true;
            }
        }
        return ok_;
    }
    static void processExp(KeyWords _key, Options _opt,int _start, int _max, String _string, NumberInfosOutput _output) {
        StringBuilder exp_ = _output.getInfos().getExponentialPart();
        int len_ = _max;
        int j_ = _start;
        j_++;
        if (_string.charAt(j_) == MINUS_CHAR) {
            exp_.append(_string.charAt(j_));
            j_++;
        }
        if (!ContextEl.isDigit(_string.charAt(j_))) {
            _output.setNextIndex(-j_);
            return;
        }
        while (j_ < len_) {
            if (!ContextEl.isDigit(_string.charAt(j_)) && _string.charAt(j_) != NB_INTERN_SP) {
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
            String keyWord_ = _key.getNbKeyWord(_string, j_);
            if (keyWord_ == null) {
                if (StringList.isDollarWordChar(_string.charAt(j_))) {
                    _output.setNextIndex(-j_);
                    return;
                }
            } else {
                char suf_ = _key.getSuffixes().getVal(keyWord_);
                _output.getInfos().setSuffix(suf_);
                j_+=keyWord_.length();
            }
        }
        nextPart_ = _string.substring(j_).trim();
        if (!isCorrectNbEnd(nextPart_)) {
            _output.setNextIndex(-j_);
            return;
        }
        _output.setNextIndex(j_);
    }
    private static boolean isCorrectNbEnd(String _next) {
        if (_next.isEmpty()) {
            return true;
        }
        char n_ = _next.charAt(0);
        if (ContextEl.isDigit(n_)) {
            return false;
        }
        if (n_ == DOT_VAR) {
            return false;
        }
        return true;
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
            op_.setConstType(ConstType.ERROR);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string, 0);
            op_.setDelimiter(_d);
            return op_;
        }
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordFalse_ = keyWords_.getKeyWordFalse();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        String keyWordNew_ = keyWords_.getKeyWordNew();
        String keyWordNull_ = keyWords_.getKeyWordNull();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        String keyWordTrue_ = keyWords_.getKeyWordTrue();
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
        int begin_;
        int end_;
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
        begin_ = _d.getDelSimpleAnnotations().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelSimpleAnnotations().indexOfObj(_offset + lastPrintChar_);
        if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.SIMPLE_ANNOTATION);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setFctName(_string);
            op_.setDelimiter(_d);
            return op_;
        }
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
        begin_ = _d.getDelIds().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelIds().indexOfObj(_offset + lastPrintChar_);
        if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.ID);
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
        begin_ = _d.getDelKeyWordSuper().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelKeyWordSuper().indexOfObj(_offset + lastPrintChar_ + 1);
        if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.SUPER_KEYWORD);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string.substring(firstPrintChar_+keyWordSuper_.length() + 1, lastPrintChar_ + 1), firstPrintChar_);
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
        begin_ = _d.getDelLoopVars().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelLoopVars().indexOfObj(_offset + lastPrintChar_);
        if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
            String name_ = StringList.getWordsSeparators(_string.substring(firstPrintChar_+1, lastPrintChar_)).get(1);
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.LOOP_INDEX);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(name_, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelKeyWordSuperAccess().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelKeyWordSuperAccess().indexOfObj(_offset + lastPrintChar_ + 1);
        if (begin_ > CustList.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.SUPER_ACCESS_KEYWORD);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string.substring(firstPrintChar_, lastPrintChar_ + 1),firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        String sub_ = _string.substring(firstPrintChar_, len_);
        if (StringList.quickEq(sub_, keyWordThis_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.THIS_KEYWORD);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (_conf.isInternGlobal()) {
            if (StringList.quickEq(sub_, keyWordIntern_)) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.WORD);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(_string, firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
        }
        if (StringList.quickEq(sub_, keyWordNull_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.NULL_CST);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (StringList.quickEq(sub_, keyWordTrue_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.TRUE_CST);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (StringList.quickEq(sub_, keyWordFalse_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.FALSE_CST);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        int firstNbChar_ = firstPrintChar_;
        int beginNb_ = _d.getDelNumbers().indexOfObj(_offset + firstNbChar_);
        int endNb_ = _d.getDelNumbers().indexOfObj(_offset + lastPrintChar_ + 1);
        if (beginNb_ > CustList.INDEX_NOT_FOUND_ELT && beginNb_ + 1 == endNb_) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.NUMBER);
            int indexNb_ = beginNb_/2;
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setNbInfos(_d.getNbInfos().get(indexNb_));
            op_.getNbInfos().setPositive(true);
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
            StringInfo info_ = _d.getStringInfo().get(before_/2);
            CharList list_ = info_.getChars();
            int lenStr_ = list_.size();
            char[] str_ = new char[lenStr_];
            for (int i = 0; i < lenStr_; i++) {
                str_[i] = list_.get(i);
            }
            if (_string.charAt(firstPrintChar_) == DELIMITER_CHAR) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.CHARACTER);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(new String(str_), firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            if (_string.charAt(firstPrintChar_) == DELIMITER_STRING) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.STRING);
                op_.setOperators(new NatTreeMap<Integer, String>());
                op_.setValue(new String(str_), firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.STRING);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setValue(new String(str_), firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        boolean preIncr_ = false;
        String extracted_ = EMPTY_STRING;
        if (_string.charAt(firstPrintChar_) == MINUS_CHAR || _string.charAt(firstPrintChar_) == PLUS_CHAR) {
            if (firstPrintChar_ + 1 < _string.length()) {
                if (_string.charAt(firstPrintChar_) == _string.charAt(firstPrintChar_ + 1)) {
                    preIncr_ = true;
                }
            }
        }
        if (preIncr_) {
            prio_ = UNARY_PRIO;
            String ch_ = String.valueOf(_string.charAt(firstPrintChar_));
            operators_.put(firstPrintChar_, StringList.concat(EMPTY_STRING,ch_,ch_));
            i_ = incrementUnary(_string, firstPrintChar_ + 2, lastPrintChar_, _offset, _d);
        } else if (_string.charAt(firstPrintChar_) == MINUS_CHAR || _string.charAt(firstPrintChar_) == PLUS_CHAR) {
            prio_ = UNARY_PRIO;
            operators_.put(firstPrintChar_, String.valueOf(_string.charAt(firstPrintChar_)));
            i_ = incrementUnary(_string, firstPrintChar_ + 1, lastPrintChar_, _offset, _d);
        } else if (_string.charAt(firstPrintChar_) == NEG_BOOL_CHAR || _string.charAt(firstPrintChar_) == NEG_BOOL) {
            prio_ = UNARY_PRIO;
            operators_.put(firstPrintChar_, String.valueOf(_string.charAt(firstPrintChar_)));
            i_ = incrementUnary(_string, firstPrintChar_ + 1, lastPrintChar_, _offset, _d);
        } else if (_d.getDelCast().contains(firstPrintChar_+_offset)) {
            prio_ = UNARY_PRIO;
            int min_ = _d.getDelCast().indexOfObj(firstPrintChar_+_offset);
            int max_ = _d.getDelCast().get(min_ + 1) - _offset;
            operators_.put(firstPrintChar_, _string.substring(firstPrintChar_, max_ + 1));
            int ext_ = min_ / 2;
            extracted_ = _d.getDelCastExtract().get(ext_);
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
        boolean instance_ = false;
        if (ContextEl.startsWithKeyWord(_string.substring(firstPrintChar_), keyWordNew_)) {
            instance_ = true;
        }
        if (_string.charAt(firstPrintChar_) == ANN_ARR_LEFT) {
            instance_ = true;
        }
        Options opt_ = _conf.getOptions();
        Numbers<Integer> laterIndexesDouble_ = new Numbers<Integer>();
        Numbers<Integer> escapings_ = new Numbers<Integer>();
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (_d.getDimsAddonIndexes().containsObj(i_+_offset)) {
                laterIndexesDouble_.add(i_);
            }
            if (_d.getEscapings().containsObj(i_+_offset)) {
                escapings_.add(i_);
            }
            if (!_d.getAllowedOperatorsIndexes().containsObj(i_+_offset)) {
                i_++;
                continue;
            }
            if (curChar_ == PAR_LEFT) {
                if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO && !declaring_) {
                    if (enPars_) {
                        useFct_ = true;
                        fctName_ = _string.substring(CustList.FIRST_INDEX, i_);
                        operators_.put(i_, String.valueOf(PAR_LEFT));
                    } else if (enabledId_) {
                        instance_ = false;
                        operators_.clear();
                        operators_.put(i_, EMPTY_STRING);
                    }
                }
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == SEP_ARG) {
                if (parsBrackets_.size() == 0) {
                    if (!declaring_) {
                        operators_.clear();
                    }
                    instance_ = false;
                    enabledId_ = false;
                    enPars_ = false;
                    operators_.put(i_, String.valueOf(SEP_ARG));
                    declaring_ = true;
                } else if (parsBrackets_.size() == 1 && prio_ >= FCT_OPER_PRIO && enPars_){
                    operators_.put(i_, String.valueOf(SEP_ARG));
                }
            }
            if (curChar_ == PAR_RIGHT) {
                parsBrackets_.removeKey(parsBrackets_.lastKey());
                if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO && enPars_) {
                    if (!operators_.lastValue().isEmpty()) {
                        operators_.put(i_, String.valueOf(PAR_RIGHT));
                    }
                    enPars_ = false;
                    enabledId_ = true;
                }
                i_++;
                continue;
            }
            if (curChar_ == ANN_ARR_LEFT) {
                if (parsBrackets_.isEmpty() && !declaring_) {
                    if (FCT_OPER_PRIO <= prio_) {
                        useFct_ = false;
                        if (instance_) {
                            fctName_ = _string.substring(firstPrintChar_, i_);
                            operators_.put(i_, ANN_ARR);
                        } else {
                            fctName_ = EMPTY_STRING;
                            instance_ = false;
                            operators_.clear();
                            if (firstPrintChar_ == i_) {
                                operators_.put(i_, ANN_ARR);
                            } else {
                                operators_.put(i_, EMPTY_STRING);
                            }
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
                    enPars_ = false;
                    enabledId_ = true;
                }
                i_++;
                continue;
            }
            if (curChar_ == ARR_LEFT) {
                if (parsBrackets_.isEmpty() && !declaring_) {
                    if (FCT_OPER_PRIO <= prio_) {
                        useFct_ = false;

                        if (instance_) {
                            if (operators_.isEmpty()) {
                                fctName_ = _string.substring(firstPrintChar_, i_);
                                operators_.put(i_, ARR);
                            } else {
                                String op_ = operators_.firstValue();
                                if (!op_.isEmpty() && op_.charAt(0) == ARR_LEFT) {
                                    operators_.put(i_, ARR);
                                } else {
                                    fctName_ = EMPTY_STRING;
                                    instance_ = false;
                                    operators_.clear();
                                    operators_.put(i_, EMPTY_STRING);
                                }
                            }
                        } else {
                            fctName_ = EMPTY_STRING;
                            instance_ = false;
                            operators_.clear();
                            if (firstPrintChar_ == i_) {
                                operators_.put(i_, ARR);
                            } else {
                                operators_.put(i_, EMPTY_STRING);
                            }
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
                    enPars_ = false;
                    enabledId_ = true;
                }
                i_++;
                continue;
            }
            if (curChar_ == BEGIN_TERNARY) {
                if (parsBrackets_.isEmpty() && prio_ > TERNARY_PRIO && !declaring_) {
                    operators_.clear();
                    useFct_ = false;
                    operators_.put(i_, String.valueOf(curChar_));
                }
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == opt_.getSuffix()) {
                parsBrackets_.removeKey(parsBrackets_.lastKey());
                if (parsBrackets_.isEmpty() && prio_ > TERNARY_PRIO && !declaring_) {
                    operators_.put(i_, String.valueOf(curChar_));
                    enPars_ = false;
                    enabledId_ = true;
                    prio_ = TERNARY_PRIO;
                }
                i_++;
                continue;
            }
            if (!parsBrackets_.isEmpty()) {
                i_++;
                continue;
            }
            if (declaring_) {
                i_++;
                continue;
            }
            StringBuilder builtOperator_ = new StringBuilder();
            boolean clearOperators_ = false;
            boolean foundOperator_ = false;
            int increment_ = 1;
            if (curChar_ == DOT_VAR) {
                builtOperator_.append(DOT_VAR);
                if (prio_ == FCT_OPER_PRIO) {
                    clearOperators_ = true;
                    foundOperator_ = true;
                }
            }
            if (curChar_ == NEG_BOOL_CHAR || curChar_ == EQ_CHAR) {
                boolean processNextEq_ = true;
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
                    builtOperator_.append(EQ_CHAR);
                    if (prio_ > EQ_PRIO) {
                        prio_ = EQ_PRIO;
                    }
                    if (prio_ == EQ_PRIO) {
                        clearOperators_ = true;
                        foundOperator_ = true;
                    }
                    increment_ = 2;
                } else {
                    builtOperator_.append(curChar_);
                    if (prio_ > AFF_PRIO && prio_ != TERNARY_PRIO) {
                        clearOperators_ = true;
                        prio_ = AFF_PRIO;
                        foundOperator_ = true;
                    }
                    increment_ = 1;
                }
            }
            int prioOpMult_ = 0;
            boolean andOr_ = false;
            if (curChar_ == LOWER_CHAR || curChar_ == GREATER_CHAR) {
                if (i_ + 1 < len_) {
                    char nextChar_ = _string.charAt(i_ + 1);
                    if (nextChar_ == curChar_) {
                        prioOpMult_ = SHIFT_PRIO;
                    }
                }
            }
            if (curChar_ == MINUS_CHAR || curChar_ == PLUS_CHAR) {
                prioOpMult_ = ADD_PRIO;
            } else if (curChar_ == MULT_CHAR || curChar_ == DIV_CHAR || curChar_ == MOD_CHAR) {
                prioOpMult_ = MULT_PRIO;
            } else if (curChar_ == AND_CHAR) {
                prioOpMult_ = AND_PRIO;
                andOr_ = true;
            } else if (curChar_ == OR_CHAR) {
                prioOpMult_ = OR_PRIO;
                andOr_ = true;
            } else if (curChar_ == XOR_CHAR) {
                prioOpMult_ = BIT_XOR_PRIO;
            }
            if (prioOpMult_ > 0) {
                builtOperator_.append(curChar_);
                if (i_ + 1 < len_) {
                    char nextChar_ = _string.charAt(i_ + 1);
                    int delta_ = 0;
                    if (prioOpMult_ == SHIFT_PRIO) {
                        builtOperator_.append(nextChar_);
                        if (i_ + 2 < len_ && _string.charAt(i_ + 2) == nextChar_) {
                            delta_++;
                            builtOperator_.append(nextChar_);
                            if (i_ + 3 < len_ && _string.charAt(i_ + 3) == nextChar_) {
                                delta_++;
                                builtOperator_.append(nextChar_);
                            }
                        }
                    }
                    boolean aff_ = false;
                    if (nextChar_ == EQ_CHAR) {
                        aff_ = true;
                    } else if (prioOpMult_ == SHIFT_PRIO && delta_+i_ + 2 < len_ && _string.charAt(delta_+i_ + 2) == EQ_CHAR) {
                        aff_ = true;
                    }
                    if (prioOpMult_ == ADD_PRIO && nextChar_ == curChar_) {
                        if (prio_ > POST_INCR_PRIO) {
                            clearOperators_ = true;
                            prio_ = POST_INCR_PRIO;
                            foundOperator_ = true;
                        }
                        increment_ = 2;
                        builtOperator_.append(nextChar_);
                    } else if (andOr_ && nextChar_ == curChar_) {
                        builtOperator_.append(curChar_);
                        if (prio_ > prioOpMult_) {
                            prio_ = prioOpMult_;
                        }
                        if (prio_ == prioOpMult_) {
                            clearOperators_ = true;
                            foundOperator_ = true;
                        }
                        increment_ = 2;
                    } else if (aff_) {
                        increment_ = 2;
                        if (prioOpMult_ == SHIFT_PRIO) {
                            increment_++;
                        }
                        if (prio_ > AFF_PRIO && prio_ != TERNARY_PRIO) {
                            clearOperators_ = true;
                            prio_ = AFF_PRIO;
                            foundOperator_ = true;
                        }
                        builtOperator_.append(EQ_CHAR);
                    } else {
                        if (curChar_ == AND_CHAR) {
                            prioOpMult_ = BIT_AND_PRIO;
                        } else if (curChar_ == OR_CHAR) {
                            prioOpMult_ = BIT_OR_PRIO;
                        }
                        if (prioOpMult_ == SHIFT_PRIO) {
                            increment_++;
                            increment_ += delta_;
                        }
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
            int min_ = _d.getDelInstanceof().indexOfObj(i_+_offset);
            if (min_ >= 0 && min_ % 2 == 0) {
                int next_ = _d.getDelInstanceof().get(min_+1) - _offset;
                is_ = true;
                if (prio_ > CMP_PRIO) {
                    clearOperators_ = true;
                    prio_ = CMP_PRIO;
                }
                if (prio_ == CMP_PRIO) {
                    foundOperator_ = true;
                }
                String op_ = _string.substring(i_, next_);
                builtOperator_.append(op_);
                increment_ = op_.length();
            }
            if ((curChar_ == LOWER_CHAR || curChar_ == GREATER_CHAR) && prioOpMult_ != SHIFT_PRIO) {
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
                    operators_.clear();
                }
                useFct_ = false;
                fctName_ = EMPTY_STRING;
                instance_ = false;
                enPars_ = false;
                enabledId_ = false;
                operators_.put(i_,builtOperator_.toString());
            }
            i_ += increment_;
        }
        OperationsSequence op_ = new OperationsSequence();
        op_.setDeclaring(declaring_);
        op_.setPriority(prio_);
        op_.setOperators(operators_);
        op_.setUseFct(useFct_);
        op_.setFctName(fctName_);
        op_.setupValues(_string, is_, true, instance_, laterIndexesDouble_, escapings_);
        op_.setExtractType(extracted_);
        op_.setDelimiter(_d);
        return op_;
    }

    static int incrementUnary(String _string, int _from, int _to, int _offset, Delimiters _d) {
        int j_ = _from;
        while (j_ <= _to) {
            char ch_ = _string.charAt(j_);
            if (ch_ != MINUS_CHAR) {
                if (ch_ != NEG_BOOL) {
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
            }
            j_++;
        }
        return j_;
    }

    static int indexAfterPossibleCast(Analyzable _conf, boolean _ctor,String _string, int _from, int _max, Delimiters _d) {
        int i_ = _from;
        int len_ = _max;
        int indexParRight_ = _string.indexOf(PAR_RIGHT, i_+1);
        Delimiters d_ = _d;
        Options opt_ = _conf.getOptions();
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        if (indexParRight_ < 0) {
            return i_;
        }
        if (d_.getCallings().containsObj(i_)) {
            return i_;
        }

        String sub_ = _string.substring(i_ + 1, indexParRight_);
        String subTrim_ = sub_.trim();
        int arrRight_ = subTrim_.indexOf(ARR_RIGHT);
        if (subTrim_.startsWith(ARR) && arrRight_ > -1) {
            _d.getDelLoopVars().add(i_);
            _d.getDelLoopVars().add(indexParRight_);
            return indexParRight_ + 1;
        }
        int j_ = i_ + 1;
        while (true) {
            char locChar_ = _string.charAt(j_);
            if (!Character.isWhitespace(locChar_)) {
                break;
            }
            j_++;
        }
        boolean cast_ = true;
        boolean type_ = false;
        boolean doubleDot_ = false;
        if (isNumber(j_, indexParRight_, _string, opt_)) {
            cast_ = false;
        }
        if (j_ >= indexParRight_) {
            cast_ = false;
        }
        boolean strType_ = false;
        if (cast_) {
            char locCar_ = _string.charAt(j_);
            if (!StringList.isDollarWordChar(locCar_)) {
                if (locCar_ == Templates.PREFIX_VAR_TYPE_CHAR) {
                    type_ = true;
                } else if (locCar_ == DOT_VAR) {
                    if (_string.charAt(j_ + 1) == DOT_VAR) {
                        doubleDot_ = true;
                        type_ = true;
                        strType_ = true;
                        j_++;
                    } else {
                        cast_ = false;
                    }
                } else {
                    cast_ = false;
                }
            }
            int k_ = indexParRight_+1;
            while (k_ < len_) {
                char locChar_ = _string.charAt(k_);
                if (Character.isWhitespace(locChar_)) {
                    k_++;
                    continue;
                }
                if (StringList.isDollarWordChar(locChar_)) {
                    type_ = true;
                }
                if (locChar_ == PAR_LEFT) {
                    type_ = true;
                }
                break;
            }
        }
        int count_ = 0;
        boolean foundLtGt_ = false;
        Numbers<Integer> indexes_ = new Numbers<Integer>();
        StringBuilder part_ = new StringBuilder();
        StringList partsFields_ = new StringList();
        Numbers<Integer> begins_ = new Numbers<Integer>();
        Numbers<Integer> ends_ = new Numbers<Integer>();
        int fChar_ = -1;
        int lChar_ = -1;
        if (cast_ && !strType_) {
            while (j_ < indexParRight_) {
                char locCar_ = _string.charAt(j_);
                if (locCar_ == Templates.GT) {
                    if (count_ == 0) {
                        cast_ = false;
                        break;
                    }
                    count_--;
                    j_++;
                    continue;
                }
                if (locCar_ == Templates.LT) {
                    foundLtGt_ = true;
                    count_++;
                    j_++;
                    continue;
                }
                if (locCar_ == Templates.PREFIX_VAR_TYPE_CHAR) {
                    type_ = true;
                    j_++;
                    continue;
                }
                if (locCar_ == Templates.SUB_TYPE_CHAR) {
                    if (count_ > 0) {
                        type_ = true;
                        j_++;
                        continue;
                    }
                }
                if (locCar_ == Templates.SUP_TYPE_CHAR) {
                    if (count_ > 0) {
                        type_ = true;
                        j_++;
                        continue;
                    }
                }
                if (locCar_ == Templates.COMMA) {
                    if (count_ > 0) {
                        type_ = true;
                        j_++;
                        continue;
                    }
                }
                if (locCar_ == ARR_LEFT) {
                    if (count_ > 0) {
                        type_ = true;
                        j_++;
                        continue;
                    }
                    int next_ = j_ + 1;
                    while (next_ < indexParRight_) {
                        char nChar_ = _string.charAt(next_);
                        if (Character.isWhitespace(nChar_)) {
                            next_++;
                            continue;
                        }
                        if (nChar_ == ARR_RIGHT) {
                            type_ = true;
                        } else {
                            cast_ = false;
                        }
                        break;
                    }
                    break;
                }
                if (locCar_ == ARR_RIGHT) {
                    if (count_ > 0) {
                        type_ = true;
                        j_++;
                        continue;
                    }
                }
                if (StringList.isDollarWordChar(locCar_)) {
                    if (fChar_ == -1) {
                        fChar_ = j_;
                    }
                    if (lChar_ != -1 && lChar_ + 1 != j_ && count_ == 0) {
                        cast_ = false;
                        break;
                    }
                    lChar_ = j_;
                    part_.append(locCar_);
                    j_++;
                    continue;
                }
                if (Character.isWhitespace(locCar_)) {
                    part_.append(locCar_);
                    j_++;
                    continue;
                }
                String tr_ = part_.toString().trim();
                if (StringList.quickEq(keyWordThis_, tr_)) {
                    cast_ = false;
                    break;
                }
                if (StringList.quickEq(keyWordSuper_, tr_)) {
                    cast_ = false;
                    break;
                }
                if (StringList.quickEq(keyWordThat_, tr_)) {
                    cast_ = false;
                    break;
                }
                if (StringList.quickEq(keyWordClasschoice_, tr_)) {
                    cast_ = false;
                    break;
                }
                if (locCar_ == DOT_VAR) {
                    indexes_.add(j_);
                    partsFields_.add(part_.toString());
                    begins_.add(fChar_);
                    ends_.add(lChar_);
                    part_.delete(0, part_.length());
                    fChar_ = -1;
                    lChar_ = -1;
                    if (_string.charAt(j_ + 1) == DOT_VAR) {
                        doubleDot_ = true;
                        type_ = true;
                        j_++;
                    } else if (doubleDot_) {
                        if (count_ == 0) {
                            cast_ = false;
                            break;
                        }
                    }
                    j_++;
                    continue;
                }
                cast_ = false;
                break;
            }
            if (count_ != 0) {
                cast_ = false;
            } else if (foundLtGt_) {
                type_ = true;
            }
        }
        if (cast_) {
            if (type_) {
                String typeRes_ = _conf.resolveCorrectTypeWithoutErrors(sub_, true);
                if (!typeRes_.isEmpty()) {
                    d_.getDelCast().add(i_);
                    d_.getDelCast().add(indexParRight_);
                    d_.getDelCastExtract().add(typeRes_);
                    return indexParRight_ + 1;
                }
                cast_ = false;
            }
        }
        if (cast_) {
            partsFields_.add(part_.toString());
            begins_.add(fChar_);
            ends_.add(lChar_);
            boolean field_ = true;
            String glClass_ = _conf.getGlobalClass();
            if (glClass_ != null) {
                ClassArgumentMatching clArg_ = new ClassArgumentMatching(glClass_);
                String word_ = partsFields_.first();
                FieldResult fr_ = OperationNode.resolveDeclaredCustField(_conf, _conf.isStaticContext() || _ctor, clArg_, true, true, word_, _conf.getCurrentBlock() != null, false);
                if (fr_.getStatus() != SearchingMemberStatus.UNIQ || fr_.getId().getType() == null) {
                    field_ = false;
                }
            } else {
                field_ = false;
            }
            if (field_) {
                ConstType w_ = ConstType.WORD;
                int lenFields_ = partsFields_.size();
                for (int i = 0; i < lenFields_; i++) {
                    VariableInfo infoLoc_ = new VariableInfo();
                    infoLoc_.setKind(w_);
                    infoLoc_.setFirstChar(begins_.get(i));
                    infoLoc_.setLastChar(ends_.get(i)+1);
                    infoLoc_.setName(partsFields_.get(i).trim());
                    d_.getVariables().add(infoLoc_);
                }
                d_.getAllowedOperatorsIndexes().add(i_);
                d_.getAllowedOperatorsIndexes().addAllElts(indexes_);
                d_.getAllowedOperatorsIndexes().add(indexParRight_);
                return indexParRight_+1;
            }
            String typeRes_ = _conf.resolveCorrectTypeWithoutErrors(sub_, true);
            if (!typeRes_.isEmpty()) {
                d_.getDelCast().add(i_);
                d_.getDelCast().add(indexParRight_);
                d_.getDelCastExtract().add(typeRes_);
                return indexParRight_ + 1;
            }
        }
        return i_;
    }
}
