package code.expressionlanguage.instr;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.methods.FieldBlock;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.NumParsers;
import code.util.*;


public final class ElResolver {

    public static final int CONST_PRIO = 0;
    public static final int DECL_PRIO = 1;
    public static final int AFF_PRIO = 2;
    public static final int TERNARY_PRIO = 3;
    public static final int OR_PRIO = 4;
    public static final int AND_PRIO = 5;
    public static final int BIT_OR_PRIO = 6;
    public static final int BIT_XOR_PRIO = 7;
    public static final int BIT_AND_PRIO = 8;
    public static final int EQ_PRIO = 9;
    public static final int CMP_PRIO = 10;
    public static final int SHIFT_PRIO = 11;
    public static final int ADD_PRIO = 12;
    public static final int MULT_PRIO = 13;
    public static final int UNARY_PRIO = 14;
    public static final int POST_INCR_PRIO = 15;
    public static final int FCT_OPER_PRIO = 16;
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

    private static final char INTEGER = 'i';
    private static final char NB_INTERN_SP = '_';

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
    private static final char END_TERNARY = ':';

    private ElResolver() {
    }

    public static Delimiters checkSyntaxDelimiters(String _string, Analyzable _conf, int _minIndex) {
        Delimiters d_ = new Delimiters();
        d_.setPartOfString(true);
        return commonCheck(_string, _conf, _minIndex, true, d_);
    }

    public static Delimiters checkSyntax(String _string, Analyzable _conf, int _elOffest) {
        return commonCheck(_string, _conf, _elOffest, false,new Delimiters());
    }
    private static Delimiters commonCheck(String _string, Analyzable _conf, int _minIndex, boolean _delimiters, Delimiters _d) {
        boolean partOfString_ = _d.isPartOfString();

        Delimiters d_ = new Delimiters();
        IntTreeMap<Character> parsBrackets_;
        parsBrackets_ = new IntTreeMap<Character>();
        ResultAfterOperators resOpers_ = new ResultAfterOperators();
        resOpers_.setParsBrackets(parsBrackets_);
        resOpers_.setPartOfString(partOfString_);
        StringList declaring_ = new StringList();

        boolean constString_ = false;
        boolean constChar_ = false;
        boolean constText_ = false;
        boolean escapedMeta_ = false;
        int unicode_ = 0;
        int len_ = _string.length();
        int i_ = _minIndex;
        int lastDoubleDot_ = i_;
        boolean beginOrEnd_ = false;
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
        KeyWords keyWords_ = _conf.getKeyWords();
        StringInfo si_ = new StringInfo();
        i_ = _minIndex;
        int nbChars_ = 0;
        ResultAfterInstKeyWord resKeyWords_ = new ResultAfterInstKeyWord();
        resKeyWords_.setNextIndex(i_);
        ResultAfterDoubleDotted resWords_ = new ResultAfterDoubleDotted();
        resWords_.setNextIndex(i_);
        resWords_.setLastDoubleDot(i_);
        resWords_.setCallCtor(false);
        resWords_.setDeclaring(declaring_);
        resWords_.setStack(parsBrackets_);
        resOpers_.setDoubleDotted(resWords_);
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
            resKeyWords_.setNextIndex(i_);
            resKeyWords_.setCallCtor(ctorCall_);
            processAfterInstuctionKeyWord(_string, d_, resKeyWords_, _conf);
            if (d_.getBadOffset() >= 0) {
                return d_;
            }
            int nextInd_ = resKeyWords_.getNextIndex();
            if (nextInd_ > i_) {
                i_ = nextInd_;
                ctorCall_ = resKeyWords_.isCallCtor();
                continue;
            }
            if (StringList.isDollarWordChar(curChar_)) {
                resWords_.setNextIndex(i_);
                resWords_.setLastDoubleDot(lastDoubleDot_);
                resWords_.setCallCtor(ctorCall_);
                processWords(_string, d_, resWords_, _conf);
                if (d_.getBadOffset() >= 0) {
                    return d_;
                }
                nextInd_ = resWords_.getNextIndex();
                lastDoubleDot_ = resWords_.getLastDoubleDot();
            }
            if (nextInd_ > i_) {
                i_ = nextInd_;
                continue;
            }
            resOpers_.setPartOfString(partOfString_);
            resOpers_.setBeginOrEnd(beginOrEnd_);
            resOpers_.setConstChar(false);
            resOpers_.setConstString(false);
            resOpers_.setConstText(false);
            resOpers_.setNbChars(nbChars_);
            resOpers_.getDoubleDotted().setNextIndex(i_);
            resOpers_.getDoubleDotted().setLastDoubleDot(lastDoubleDot_);
            resOpers_.getDoubleDotted().setCallCtor(ctorCall_);
            processOperators(beginIndex_,_minIndex,_string, _d, _delimiters, ctorCall_, d_, resOpers_, _conf);
            if (d_.getBadOffset() >= 0) {
                return d_;
            }
            beginOrEnd_ = resOpers_.isBeginOrEnd();
            constChar_ = resOpers_.isConstChar();
            constString_ = resOpers_.isConstString();
            constText_ = resOpers_.isConstText();
            nbChars_ = resOpers_.getNbChars();
            partOfString_ = resOpers_.isPartOfString();
            
            i_ = resOpers_.getDoubleDotted().getNextIndex();
            lastDoubleDot_ = resOpers_.getDoubleDotted().getLastDoubleDot();
            ctorCall_ = resOpers_.getDoubleDotted().isCallCtor();
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
            return d_;
        }
        d_.setBadOffset(i_);
        return d_;
    }
    private static void processAfterInstuctionKeyWord(String _string,Delimiters _d, ResultAfterInstKeyWord _out, Analyzable _conf) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordBool_ = keyWords_.getKeyWordBool();
        String keyWordCast_ = keyWords_.getKeyWordCast();
        String keyWordExplicit_ = keyWords_.getKeyWordExplicit();
        String keyWordClass_ = keyWords_.getKeyWordClass();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordFalse_ = keyWords_.getKeyWordFalse();
        String keyWordFirstopt_ = keyWords_.getKeyWordFirstopt();
        String keyWordId_ = keyWords_.getKeyWordId();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        String keyWordInterfaces_ = keyWords_.getKeyWordInterfaces();
        String keyWordLambda_ = keyWords_.getKeyWordLambda();
        String keyWordNew_ = keyWords_.getKeyWordNew();
        String keyWordNull_ = keyWords_.getKeyWordNull();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String keyWordStaticCall_ = keyWords_.getKeyWordStaticCall();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        String keyWordTrue_ = keyWords_.getKeyWordTrue();
        String keyWordValueOf_ = keyWords_.getKeyWordValueOf();
        String keyWordValues_ = keyWords_.getKeyWordValues();
        String keyWordVararg_ = keyWords_.getKeyWordVararg();
        String keyWordDefaultValue_ = keyWords_.getKeyWordDefaultValue();
        String sub_ = _string.substring(i_);
        if (ContextEl.startsWithKeyWord(sub_, keyWordCast_)) {
            int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
            if (indexParLeft_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
            if (indexParRight_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            if (indexParRight_ + 1 >= len_) {
                _d.setBadOffset(len_);
                return;
            }
            _d.getDelCast().add(i_);
            _d.getDelCast().add(indexParRight_);
            _d.getDelCastExtract().add(EMPTY_STRING);
            _d.getCastParts().add(new CustList<PartOffset>());
            i_ = indexParRight_ + 1;
            _out.setNextIndex(i_);
            return;
        }
        if (ContextEl.startsWithKeyWord(sub_, keyWordExplicit_)) {
            int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
            if (indexParLeft_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
            if (indexParRight_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            if (indexParRight_ + 1 >= len_) {
                _d.setBadOffset(len_);
                return;
            }
            _d.getDelExplicit().add(i_);
            _d.getDelExplicit().add(indexParRight_);
            i_ = indexParRight_ + 1;
            _out.setNextIndex(i_);
            return;
        }
        if (ContextEl.startsWithKeyWord(sub_, keyWordVararg_)) {
            int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
            if (indexParLeft_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
            if (indexParRight_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            _d.getDelVararg().add(i_);
            _d.getDelVararg().add(indexParRight_);
            i_ = indexParRight_ + 1;
            _out.setNextIndex(i_);
            return;
        }
        if (ContextEl.startsWithKeyWord(sub_, keyWordDefaultValue_)) {
            int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
            if (indexParLeft_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
            if (indexParRight_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            _d.getDelDefaultValue().add(i_);
            _d.getDelDefaultValue().add(indexParRight_);
            i_ = indexParRight_ + 1;
            _out.setNextIndex(i_);
            return;
        }
        if (ContextEl.startsWithKeyWord(sub_, keyWordClass_)) {
            int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
            if (indexParLeft_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
            if (indexParRight_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            _d.getDelClass().add(i_);
            _d.getDelClass().add(indexParRight_);
            i_ = indexParRight_ + 1;
            _out.setNextIndex(i_);
            return;
        }
        if (ContextEl.startsWithKeyWord(sub_, keyWordInstanceof_)) {
            int next_ = i_ + keyWordInstanceof_.length();
            if (Character.isWhitespace(_string.charAt(next_))) {
                //instanceof
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
                        }
                    }
                }
                if (next_ < len_) {
                    char curLoc_ = _string.charAt(next_);
                    if (curLoc_ == ARR_LEFT) {
                        while (next_ < len_) {
                            curLoc_ = _string.charAt(next_);
                            if (Character.isWhitespace(curLoc_)) {
                                next_++;
                                continue;
                            }
                            if (curLoc_ == ARR_LEFT) {
                                next_++;
                                continue;
                            }
                            if (curLoc_ == ARR_RIGHT) {
                                next_++;
                                continue;
                            }
                            break;
                        }
                    }
                }
                _d.getAllowedOperatorsIndexes().add(i_);
                _d.getDelInstanceof().add(i_);
                _d.getDelInstanceof().add(next_);
                i_ = next_;
                _out.setNextIndex(i_);
                return;
            }
            _d.setBadOffset(next_);
            return;
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
                    _d.getCallings().add(j_);
                    break;
                }
                if (curLoc_ == ANN_ARR_LEFT) {
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
                _d.setBadOffset(len_ - 1);
                return;
            }
            i_ = j_;
            _out.setNextIndex(i_);
            return;
        }
        if (ContextEl.startsWithKeyWord(sub_, keyWordLambda_)) {
            //lambda
            int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
            if (indexParLeft_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
            if (indexParRight_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            _d.getDelLambda().add(i_);
            _d.getDelLambda().add(indexParRight_);
            i_ = indexParRight_ + 1;
            _out.setNextIndex(i_);
            return;
        }
        if (ContextEl.startsWithKeyWord(sub_, keyWordId_)) {
            //lambda
            int indexParLeft_ = _string.indexOf(PAR_LEFT,i_+1);
            if (indexParLeft_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
            if (indexParRight_ < 0) {
                _d.setBadOffset(len_);
                return;
            }
            _d.getDelIds().add(i_);
            _d.getDelIds().add(indexParRight_);
            i_ = indexParRight_ + 1;
            _out.setNextIndex(i_);
            return;
        }
        boolean isKeySt_ = ContextEl.startsWithKeyWord(sub_, keyWordStatic_);
        boolean isKeyStCall_ = ContextEl.startsWithKeyWord(sub_, keyWordStaticCall_);
        if (isKeySt_||isKeyStCall_) {
            Ints indexes_;
            int afterStatic_;
            if (isKeySt_) {
                indexes_ = _d.getDelKeyWordStatic();
                afterStatic_ = i_ + keyWordStatic_.length();
            } else {
                indexes_ = _d.getDelKeyWordStaticCall();
                afterStatic_ = i_ + keyWordStaticCall_.length();
            }
            boolean foundHat_ = false;
            while (afterStatic_ < len_) {
                if (_string.charAt(afterStatic_) == PAR_LEFT) {
                    foundHat_ = true;
                    break;
                }
                if (!Character.isWhitespace(_string.charAt(afterStatic_))) {
                    _d.setBadOffset(afterStatic_);
                    return;
                }
                afterStatic_++;
            }
            if (!foundHat_) {
                _d.setBadOffset(len_ - 1);
                return;
            }
            if (afterStatic_ + 1 >= len_) {
                _d.setBadOffset(afterStatic_);
                return;
            }
            while (afterStatic_ < len_) {
                if (_string.charAt(afterStatic_) == PAR_RIGHT) {
                    break;
                }
                afterStatic_++;
            }
            afterStatic_++;
            if (afterStatic_ + 1 >= len_) {
                _d.setBadOffset(afterStatic_);
                return;
            }
            while (afterStatic_ < len_) {
                if (!Character.isWhitespace(_string.charAt(afterStatic_))) {
                    if (_string.charAt(afterStatic_) == DOT_VAR) {
                        indexes_.add(i_);
                        indexes_.add(afterStatic_);
                        i_ = afterStatic_;
                        break;
                    }
                    _d.setBadOffset(afterStatic_);
                    return;
                }
                afterStatic_++;
            }
            if (afterStatic_ >= len_) {
                _d.setBadOffset(afterStatic_);
                return;
            }
            if (isKeySt_) {
                _d.getDelKeyWordStaticExtract().add(EMPTY_STRING);
                _d.getStaticParts().add(new CustList<PartOffset>());
            }
            _out.setNextIndex(i_);
            return;
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
                        if (_string.charAt(afterSuper_) == ARR_LEFT) {
                            _d.getDelAccessIndexers().add(i_);
                            _d.getDelAccessIndexers().add(afterSuper_);
                            i_ = afterSuper_;
                            _out.setNextIndex(i_);
                            return;
                        }
                        _d.setBadOffset(afterSuper_);
                        return;
                    }
                    _out.setCallCtor(true);
                    _d.getCallings().add(afterSuper_);
                    break;
                }
                afterSuper_++;
            }
            if (afterSuper_ >= len_) {
                _d.setBadOffset(afterSuper_);
                return;
            }
            if (!foundHat_) {
                i_ = afterSuper_;
                _out.setNextIndex(i_);
                return;
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
                    _d.getDelKeyWordSuper().add(i_);
                    _d.getDelKeyWordSuper().add(afterSuper_);
                } else {
                    _d.getCallings().add(afterSuper_);
                }
            } else {
                _d.getDelKeyWordSuper().add(i_);
                _d.getDelKeyWordSuper().add(afterSuper_);
            }
            i_ = afterSuper_;
            _out.setNextIndex(i_);
            return;
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
                    _d.setBadOffset(afterClassChoice_);
                    return;
                }
                afterClassChoice_++;
            }
            if (!foundHat_) {
                _d.setBadOffset(len_ - 1);
                return;
            }
            if (afterClassChoice_ + 1 >= len_) {
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            while (afterClassChoice_ < len_) {
                if (_string.charAt(afterClassChoice_) == PAR_RIGHT) {
                    break;
                }
                afterClassChoice_++;
            }
            if (afterClassChoice_ + 1 >= len_) {
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            afterClassChoice_++;
            while (afterClassChoice_ < len_) {
                char loc_ = _string.charAt(afterClassChoice_);
                if (!Character.isWhitespace(loc_)) {
                    break;
                }
                afterClassChoice_++;
            }
            if (afterClassChoice_ < len_) {
                if (_string.charAt(afterClassChoice_) == ARR_LEFT) {
                    _d.getDelAccessIndexers().add(i_);
                    _d.getDelAccessIndexers().add(afterClassChoice_);
                    i_ = afterClassChoice_;
                    _out.setNextIndex(i_);
                    return;
                }
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
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            if (afterClassChoice_ >= len_) {
                //field
                _d.getDelKeyWordClassChoice().add(i_);
                _d.getDelKeyWordClassChoice().add(afterClassChoice_);
                i_ = afterClassChoice_;
                _out.setNextIndex(i_);
                return;
            }
            if (_string.charAt(afterClassChoice_) == PAR_LEFT) {
                //fct
                _d.getCallings().add(afterClassChoice_);
                i_ = afterClassChoice_;
                _out.setNextIndex(i_);
                return;
            }
            //field
            _d.getDelKeyWordClassChoice().add(i_);
            _d.getDelKeyWordClassChoice().add(afterClassChoice_);
            i_ = afterClassChoice_;
            _out.setNextIndex(i_);
            return;
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
                    _d.setBadOffset(afterClassChoice_);
                    return;
                }
                afterClassChoice_++;
            }
            if (!foundHat_) {
                _d.setBadOffset(len_ - 1);
                return;
            }
            if (afterClassChoice_ + 1 >= len_) {
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            while (afterClassChoice_ < len_) {
                if (_string.charAt(afterClassChoice_) == PAR_RIGHT) {
                    break;
                }
                afterClassChoice_++;
            }
            if (afterClassChoice_ + 1 >= len_) {
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            afterClassChoice_++;
            while (afterClassChoice_ < len_) {
                char loc_ = _string.charAt(afterClassChoice_);
                if (!Character.isWhitespace(loc_)) {
                    break;
                }
                afterClassChoice_++;
            }
            if (afterClassChoice_ < len_) {
                if (_string.charAt(afterClassChoice_) == ARR_LEFT) {
                    _d.getDelAccessIndexers().add(i_);
                    _d.getDelAccessIndexers().add(afterClassChoice_);
                    i_ = afterClassChoice_;
                    _out.setNextIndex(i_);
                    return;
                }
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
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            if (afterClassChoice_ >= len_) {
                //field
                _d.getDelKeyWordSuperAccess().add(i_);
                _d.getDelKeyWordSuperAccess().add(afterClassChoice_);
                i_ = afterClassChoice_;
                _out.setNextIndex(i_);
                return;
            }
            if (_string.charAt(afterClassChoice_) == PAR_LEFT) {
                //fct
                _d.getCallings().add(afterClassChoice_);
                i_ = afterClassChoice_;
                _out.setNextIndex(i_);
                return;
            }
            //field
            _d.getDelKeyWordSuperAccess().add(i_);
            _d.getDelKeyWordSuperAccess().add(afterClassChoice_);
            i_ = afterClassChoice_;
            _out.setNextIndex(i_);
            return;
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
                    _d.setBadOffset(afterClassChoice_);
                    return;
                }
                afterClassChoice_++;
            }
            if (!foundHat_) {
                _d.setBadOffset(len_ - 1);
                return;
            }
            if (afterClassChoice_ + 1 >= len_) {
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            while (afterClassChoice_ < len_) {
                if (_string.charAt(afterClassChoice_) == PAR_RIGHT) {
                    break;
                }
                afterClassChoice_++;
            }
            if (afterClassChoice_ + 1 >= len_) {
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            afterClassChoice_++;
            while (afterClassChoice_ < len_) {
                char loc_ = _string.charAt(afterClassChoice_);
                if (!Character.isWhitespace(loc_)) {
                    break;
                }
                afterClassChoice_++;
            }
            if (afterClassChoice_ < len_) {
                if (_string.charAt(afterClassChoice_) == ARR_LEFT) {
                    _d.getDelAccessIndexers().add(i_);
                    _d.getDelAccessIndexers().add(afterClassChoice_);
                    i_ = afterClassChoice_;
                    _out.setNextIndex(i_);
                    return;
                }
            }
            while (afterClassChoice_ < len_) {
                char loc_ = _string.charAt(afterClassChoice_);
                if (!StringList.isDollarWordChar(loc_)) {
                    break;
                }
                afterClassChoice_++;
            }
            while (afterClassChoice_ < len_) {
                char loc_ = _string.charAt(afterClassChoice_);
                if (!Character.isWhitespace(loc_)) {
                    break;
                }
                afterClassChoice_++;
            }
            if (afterClassChoice_ >= len_) {
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            if (_string.charAt(afterClassChoice_) == PAR_LEFT) {
                //fct
                _d.getCallings().add(afterClassChoice_);
                i_ = afterClassChoice_;
                _out.setNextIndex(i_);
                return;
            }
            //field
            _d.setBadOffset(afterClassChoice_);
            return;
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
                    _d.setBadOffset(afterClassChoice_);
                    return;
                }
                afterClassChoice_++;
            }
            if (!foundHat_) {
                _d.setBadOffset(len_ - 1);
                return;
            }
            if (afterClassChoice_ + 1 >= len_) {
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            while (afterClassChoice_ < len_) {
                if (_string.charAt(afterClassChoice_) == PAR_RIGHT) {
                    break;
                }
                afterClassChoice_++;
            }
            if (afterClassChoice_ + 1 >= len_) {
                _d.setBadOffset(afterClassChoice_);
                return;
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
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            char loc_ = _string.charAt(afterClassChoice_);
            if (loc_ != PAR_LEFT) {
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            _out.setCallCtor(true);
            _d.getCallings().add(afterClassChoice_);
            i_ = afterClassChoice_;
            _out.setNextIndex(i_);
            return;
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
            if (afterSuper_ < len_) {
                if (_string.charAt(afterSuper_) == ARR_LEFT) {
                    _d.getDelAccessIndexers().add(i_);
                    _d.getDelAccessIndexers().add(afterSuper_);
                    i_ = afterSuper_;
                    _out.setNextIndex(i_);
                    return;
                }
            }
            if (!foundHat_) {
                _d.setBadOffset(afterSuper_);
                return;
            }
            if (afterSuper_ + 1 >= len_) {
                _d.setBadOffset(afterSuper_);
                return;
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
                _d.getCallings().add(afterSuper_);
            } else {
                _d.setBadOffset(afterSuper_);
                return;
            }
            i_ = afterSuper_;
            _out.setNextIndex(i_);
            return;
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
                _out.setCallCtor(true);
                _d.getCallings().add(afterSuper_);
            }
            i_ = afterSuper_;
            _out.setNextIndex(i_);
            return;
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
            _out.setNextIndex(i_);
            return;
        }
        for (String s: StringList.wrapStringArray(keyWordFirstopt_,keyWordBool_,keyWordValueOf_,keyWordValues_)) {
            if (ContextEl.startsWithKeyWord(sub_, s)) {
                int index_ = processPredefinedMethod(_string, i_, s);
                if (index_ < 0) {
                    _d.setBadOffset(-index_);
                    return;
                }
                foundValue_ = true;
                _d.getCallings().add(index_);
                i_ = index_;
                break;
            }
        }
        if (foundValue_) {
            _out.setNextIndex(i_);
            return;
        }
        _conf.processInternKeyWord(_string, i_, _out);
    }
    private static void processWords(String _string,Delimiters _d, ResultAfterDoubleDotted _out,Analyzable _an) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        boolean ctorCall_ = _out.isCallCtor();
        Options opt_ = _an.getOptions();
        KeyWords keyWords_ = _an.getKeyWords();
        ResultAfterInstKeyWord resTmp_ = new ResultAfterInstKeyWord();
        resTmp_.setNextIndex(i_);
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        if (isNumber(i_, _string)) {
            NumberInfosOutput res_ = processNb(keyWords_, i_, len_, _string, false, opt_);
            int nextIndex_ = res_.getNextIndex();
            if (nextIndex_ < 0) {
                _d.setBadOffset(-nextIndex_);
                return;
            }
            _d.getNbInfos().add(res_.getInfos());
            _d.getDelNumbers().add(i_);
            _d.getDelNumbers().add(nextIndex_);
            _out.setNextIndex(nextIndex_);
            return;
        }
        int beginWord_ = i_;
        while (i_ < len_) {
            char locChar_ = _string.charAt(i_);
            if (!StringList.isDollarWordChar(locChar_)) {
                if (locChar_ != Templates.PREFIX_VAR_TYPE_CHAR) {
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
        ConstType type_;
        VariableInfo info_ = new VariableInfo();
        String prev_ = _string.substring(0, beginWord_).trim();
        String word_ = _string.substring(beginWord_, i_);
        if (i_ < len_ && _string.substring(i_).trim().charAt(0) == PAR_LEFT) {
            int j_ = i_;
            while (_string.charAt(j_) != PAR_LEFT) {
                j_++;
            }
            _d.getCallings().add(j_);
            _out.setNextIndex(i_);
            return;
        }
        if (prev_.endsWith(String.valueOf('.'))) {
            type_ = ConstType.WORD;
            info_.setKind(type_);
            info_.setFirstChar(beginWord_);
            info_.setLastChar(i_);
            info_.setName(word_);
            _d.getVariables().add(info_);
            _out.setNextIndex(i_);
            return;
        }
        if (_an.getParameters().contains(word_)) {
            type_ = ConstType.PARAM;
            info_.setKind(type_);
            info_.setFirstChar(beginWord_);
            info_.setLastChar(i_);
            info_.setName(word_);
            _d.getVariables().add(info_);
            _out.setNextIndex(i_);
            return;
        }
        if (_an.getAnalyzing().containsCatchVar(word_)) {
            type_ = ConstType.CATCH_VAR;
            info_.setKind(type_);
            info_.setFirstChar(beginWord_);
            info_.setLastChar(i_);
            info_.setName(word_);
            _d.getVariables().add(info_);
            _out.setNextIndex(i_);
            return;
        }
        if (_an.containsMutableLoopVar(word_)) {
            type_ = ConstType.LOOP_VAR;
            info_.setKind(type_);
            info_.setFirstChar(beginWord_);
            info_.setLastChar(i_);
            info_.setName(word_);
            _d.getVariables().add(info_);
            _out.setNextIndex(i_);
            return;
        }
        if (_an.getAnalyzing().containsVar(word_)) {
            type_ = ConstType.LOOP_VAR;
            info_.setKind(type_);
            info_.setFirstChar(beginWord_);
            info_.setLastChar(i_);
            info_.setName(word_);
            _d.getVariables().add(info_);
            _out.setNextIndex(i_);
            return;
        }
        if (_an.getAnalyzing().containsLocalVar(word_)) {
            type_ = ConstType.LOC_VAR;
            info_.setKind(type_);
            info_.setFirstChar(beginWord_);
            info_.setLastChar(i_);
            info_.setName(word_);
            _d.getVariables().add(info_);
            _out.setNextIndex(i_);
            return;
        }
        String nextPart_ = "";
        if (i_ < len_) {
            nextPart_ = _string.substring(i_).trim();
        }
        int nextPartLen_ = nextPart_.length();
        type_ = ConstType.WORD;
        info_.setKind(type_);
        info_.setFirstChar(beginWord_);
        info_.setLastChar(i_);
        info_.setName(word_);
        if (_out.getStack().isEmpty() && nextCharIs(nextPart_,0,nextPartLen_,'=') && !nextCharIs(nextPart_,1,nextPartLen_,'=')) {
            if (prev_.endsWith(",") || prev_.isEmpty()) {
                if (ElUtil.isDeclaringVariable(_an)) {
                    _out.getDeclaring().add(word_);
                } else if (ElUtil.isDeclaringLoopVariable(_an)) {
                    _out.getDeclaring().add(word_);
                }
            }
        }
        String look_ = _an.getLookLocalClass();
        if (!look_.isEmpty()) {
            _d.getVariables().add(info_);
        } else {
            int j_ = i_;
            while (j_ < len_) {
                if (!Character.isWhitespace(_string.charAt(j_))) {
                    break;
                }
                j_++;
            }
            if (j_ < len_ && _string.charAt(j_) == DOT_VAR) {
                i_ = processFieldsStaticAccess(_an, ctorCall_, _string, beginWord_, word_, i_, _d);
            } else {
                _d.getVariables().add(info_);
            }
        }
        _out.setNextIndex(i_);
    }

    private static void processOperators(int _beginIndex, int _minIndex, String _string,Delimiters _din, boolean _delimiters, boolean _ctorCall,
            Delimiters _dout, ResultAfterOperators _out,Analyzable _conf) {
        IntTreeMap<Character> parsBrackets_;
        parsBrackets_ = _out.getParsBrackets();

        int len_ = _string.length();
        ResultAfterDoubleDotted doubleDotted_ = _out.getDoubleDotted();
        int i_ = doubleDotted_.getNextIndex();
        Options opt_ = _conf.getOptions();
        KeyWords keyWords_ = _conf.getKeyWords();
        int nbChars_;
        ResultAfterInstKeyWord resKeyWords_ = new ResultAfterInstKeyWord();
        resKeyWords_.setNextIndex(i_);
        ResultAfterDoubleDotted resWords_ = new ResultAfterDoubleDotted();
        resWords_.setNextIndex(i_);
        resWords_.setLastDoubleDot(i_);
        String keyWordCast_ = keyWords_.getKeyWordCast();
        String keyWordExplicit_ = keyWords_.getKeyWordExplicit();
        char curChar_ = _string.charAt(i_);
        if (_conf.getContextEl().isAnnotAnalysis() && curChar_ == ANNOT) {
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
            if (nextCharIs(_string,j_,len_,PAR_LEFT)) {
                _dout.getCallings().add(j_);
            } else {
                _dout.getDelSimpleAnnotations().add(i_);
                _dout.getDelSimpleAnnotations().add(last_);
            }
            i_ = j_;
            doubleDotted_.setNextIndex(i_);
            return;
        }
        if (curChar_ == DOT_VAR) {
            if (isNumber(i_ + 1, _string)) {
                NumberInfosOutput res_ = processNb(keyWords_, i_ + 1, len_, _string, true, opt_);
                int nextIndex_ = res_.getNextIndex();
                if (nextIndex_ < 0) {
                    _dout.setBadOffset(-nextIndex_);
                    return;
                }
                _dout.getNbInfos().add(res_.getInfos());
                _dout.getDelNumbers().add(i_);
                _dout.getDelNumbers().add(nextIndex_);
                i_ = nextIndex_;
                doubleDotted_.setNextIndex(i_);
                return;
            }
            if (i_ + 1 < len_ && Character.isWhitespace(_string.charAt(i_ + 1))) {
                String nextPart_ = _string.substring(i_ + 1).trim();
                if (isNumber(0, nextPart_)) {
                    _dout.setBadOffset(-i_-1);
                    return;
                }
            }
        }
        if (curChar_ == DELIMITER_CHAR) {
            nbChars_ = 0;
            _out.setConstChar(true);
            _out.setNbChars(nbChars_);
            _dout.getDelStringsChars().add(i_);
            i_++;
            doubleDotted_.setNextIndex(i_);
            return;
        }
        if (curChar_ == DELIMITER_STRING) {
            _out.setConstString(true);
            _dout.getDelStringsChars().add(i_);
            i_++;
            doubleDotted_.setNextIndex(i_);
            return;
        }
        if (curChar_ == DELIMITER_TEXT){
            _out.setConstText(true);
            _dout.getDelStringsChars().add(i_);
            i_++;
            doubleDotted_.setNextIndex(i_);
            return;
        }
        if (curChar_ == PAR_LEFT) {
            int j_ = indexAfterPossibleCast(_conf,doubleDotted_, _ctorCall, _string, i_, len_, _dout);
            if (_dout.getBadOffset() >= 0) {
                return;
            }
            if (j_ > i_) {
                i_ = j_;
                doubleDotted_.setNextIndex(i_);
                return;
            }
            parsBrackets_.put(i_, curChar_);
        }
        if (curChar_ == PAR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                _dout.setBadOffset(i_);
                return;
            }
            if (parsBrackets_.lastValue() != PAR_LEFT) {
                _dout.setBadOffset(i_);
                return;
            }
            parsBrackets_.removeKey(parsBrackets_.lastKey());
        }
        if (curChar_ == ANN_ARR_LEFT) {
            parsBrackets_.put(i_, curChar_);
        }
        if (curChar_ == ANN_ARR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                if (_delimiters) {
                    _out.setPartOfString(false);
                    _dout.setIndexBegin(_minIndex);
                    _dout.setIndexEnd(i_-1);
                    doubleDotted_.setNextIndex(len_);
                    return;
                }
                _dout.setBadOffset(i_);
                return;
            }
            if (parsBrackets_.lastValue() != ANN_ARR_LEFT) {
                _dout.setBadOffset(i_);
                return;
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
                _dout.getDimsAddonIndexes().add(i_);
                _dout.getDimsAddonIndexes().add(j_);
                i_ = j_ + 1;
                doubleDotted_.setNextIndex(i_);
                return;
            }
            parsBrackets_.put(i_, curChar_);
        }
        if (curChar_ == ARR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                _dout.setBadOffset(i_);
                return;
            }
            if (parsBrackets_.lastValue() != ARR_LEFT) {
                _dout.setBadOffset(i_);
                return;
            }
            parsBrackets_.removeKey(parsBrackets_.lastKey());
        }
        if (curChar_ == BEGIN_TERNARY) {
            parsBrackets_.put(i_, curChar_);
        }
        if (curChar_ == END_TERNARY) {
            if (parsBrackets_.isEmpty()) {
                _dout.setBadOffset(i_);
                return;
            }
            if (parsBrackets_.lastValue() != BEGIN_TERNARY) {
                _dout.setBadOffset(i_);
                return;
            }
            parsBrackets_.removeKey(parsBrackets_.lastKey());
        }
        if (curChar_ == SEP_ARG) {
            if (parsBrackets_.isEmpty() && !_conf.isAcceptCommaInstr() && !(_conf.getCurrentBlock() instanceof FieldBlock)) {
                _dout.setBadOffset(i_);
                return;
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
            if (_beginIndex == i_) {
                addOp_ = false;
            }
        }
        if (curChar_ == MINUS_CHAR){
            if (i_ + 1 >= len_ || _string.charAt(i_ + 1) != MINUS_CHAR) {
                escapeOpers_ = true;
            }
            if (_beginIndex == i_) {
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
        if (curChar_ == BEGIN_TERNARY) {
            escapeOpers_ = true;
        }
        if (curChar_ == END_TERNARY) {
            escapeOpers_ = true;
        }
        if (curChar_ == GREATER_CHAR) {
            escapeOpers_ = true;
            ltGt_ = true;
        }
        if (curChar_ == EQ_CHAR) {
            escapeOpers_ = true;
        }
        if (curChar_ == NEG_BOOL_CHAR) {
            escapeOpers_ = true;
            if (_beginIndex == i_) {
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
            }
            if (j_ < len_ && _string.charAt(j_) == EQ_CHAR) {
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
                    if (indexParLeft_ < 0) {
                        _dout.setBadOffset(len_);
                        return;
                    }
                    int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
                    if (indexParRight_ < 0) {
                        _dout.setBadOffset(len_);
                        return;
                    }
                    if (indexParRight_ + 1 >= len_) {
                        _dout.setBadOffset(len_);
                        return;
                    }
                    _dout.getDelCast().add(j_);
                    _dout.getDelCast().add(indexParRight_);
                    _dout.getDelCastExtract().add(EMPTY_STRING);
                    _dout.getCastParts().add(new CustList<PartOffset>());
                    j_ = indexParRight_ + 1;
                    continue;
                }
                if (ContextEl.startsWithKeyWord(subAfter_, keyWordExplicit_)) {
                    int indexParLeft_ = _string.indexOf(PAR_LEFT,j_+1);
                    if (indexParLeft_ < 0) {
                        _dout.setBadOffset(len_);
                        return;
                    }
                    int indexParRight_ = _string.indexOf(PAR_RIGHT,indexParLeft_+1);
                    if (indexParRight_ < 0) {
                        _dout.setBadOffset(len_);
                        return;
                    }
                    if (indexParRight_ + 1 >= len_) {
                        _dout.setBadOffset(len_);
                        return;
                    }
                    _dout.getDelExplicit().add(j_);
                    _dout.getDelExplicit().add(indexParRight_);
                    j_ = indexParRight_ + 1;
                    continue;
                }
                if (curLoc_ == PAR_LEFT) {
                    int before_ = _dout.getDelCastExtract().size();
                    int k_ = indexAfterPossibleCast(_conf,doubleDotted_, _ctorCall, _string, j_, len_, _dout);
                    if (_dout.getBadOffset() >= 0) {
                        return;
                    }
                    int after_ = _dout.getDelCastExtract().size();
                    j_ = k_;
                    if (before_ != after_) {
                        continue;
                    }
                }
                break;
            }
            if (addOp_) {
                _dout.getAllowedOperatorsIndexes().add(i_);
            }
            i_ = j_;
            doubleDotted_.setNextIndex(i_);
            return;
        }
        if (curChar_ == PLUS_CHAR){
            if (addOp_) {
                _dout.getAllowedOperatorsIndexes().add(i_);
            }
            i_++;
            i_++;
            doubleDotted_.setNextIndex(i_);
            return;
        }
        if (curChar_ == MINUS_CHAR){
            if (addOp_) {
                _dout.getAllowedOperatorsIndexes().add(i_);
            }
            i_++;
            i_++;
            doubleDotted_.setNextIndex(i_);
            return;
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
            _dout.getAllowedOperatorsIndexes().add(i_);
        }
        i_++;
        doubleDotted_.setNextIndex(i_);
    }
    private static int processFieldsStaticAccess(Analyzable _conf, boolean _ctor, String _string, int _from, String _word, int _to, Delimiters _d) {
        int len_ = _string.length();
        String glClass_ = _conf.getGlobalClass();
        boolean field_ = isField(_conf, glClass_, _ctor, _word);
        if (field_ || _conf.isEnabledInternVars()) {
            ConstType type_ = ConstType.WORD;
            VariableInfo infoLoc_ = new VariableInfo();
            infoLoc_.setKind(type_);
            infoLoc_.setFirstChar(_from);
            infoLoc_.setLastChar(_to);
            infoLoc_.setName(_word);
            _d.getVariables().add(infoLoc_);
            return _to;
        }
        Ints indexes_ = new Ints();
        StringList partsFields_ = new StringList();
        StringBuilder part_ = new StringBuilder();
        int j_ = _from;
        int k_ = _from;
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
            String partStr_ = part_.toString();
            if (locChar_ == DOT_VAR) {
                indexes_.add(j_);
                partsFields_.add(partStr_);
                part_.delete(0,part_.length());
                j_++;
                continue;
            }
            if (locChar_ != PAR_LEFT) {
                k_ = j_;
                partsFields_.add(partStr_);
            }
            break;
        }
        StringList partsFieldsBisFields_ = new StringList();
        for (String p: partsFields_) {
            int f_ = StringList.getFirstPrintableCharIndex(p);
            int l_= StringList.getLastPrintableCharIndex(p);
            int index_ = -1;
            while (f_ < l_) {
                char loc_ = p.charAt(f_);
                if (Character.isWhitespace(loc_)) {
                    index_ = f_;
                    break;
                }
                f_++;
            }
            if (index_ > -1) {
                partsFieldsBisFields_.add(p.substring(0,index_));
                break;
            }
            partsFieldsBisFields_.add(p);
        }
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        String join_ = StringList.join(partsFieldsBisFields_, ".");
        StringList inns_ = Templates.getAllInnerTypes(join_, _conf);
        String trim_ = inns_.first().trim();
        int nextOff_ = _from;
        int nb_ = 0;
        String start_;
        if (_conf.getClassBody(trim_) != null) {
            for (char c: trim_.toCharArray()) {
                if (c == '.') {
                    nb_++;
                }
            }
            start_ = trim_;
            _conf.appendParts(_from, _from +inns_.first().length(),trim_,partOffsets_);
            nextOff_ += inns_.first().length() + 1;
        } else {
            start_ = _conf.resolveCorrectTypeWithoutErrors(_from,inns_.first(), false);
            partOffsets_.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
            nb_ = 0;
            nextOff_ += inns_.first().length() + 1;
        }
        int iPart_ = 1;
        int lenPart_ = inns_.size();
        while (iPart_ < lenPart_) {
            String fullPart_ = inns_.get(iPart_);
            int locOff_ = StringList.getFirstPrintableCharIndex(fullPart_);
            String p_ = fullPart_.trim();
            boolean fieldLoc_ = isField(_conf, start_,_ctor, p_);
            if (fieldLoc_) {
                break;
            }
            StringList res_ = TypeUtil.getInners(start_, p_, _conf);
            if (!res_.onlyOneElt()) {
                break;
            }
            start_ = res_.first();
            _conf.appendParts(nextOff_+locOff_,nextOff_+locOff_+p_.length(),start_,partOffsets_);
            nextOff_ += fullPart_.length() + 1;
            nb_++;
            iPart_++;
        }
        int n_;
        if (indexes_.isValidIndex(nb_)) {
            n_ = indexes_.get(nb_);
        } else {
            n_ = k_;
        }
        if (_conf.getClassBody(start_) == null) {
            ConstType type_ = ConstType.WORD;
            VariableInfo infoLoc_ = new VariableInfo();
            infoLoc_.setKind(type_);
            infoLoc_.setFirstChar(_from);
            infoLoc_.setLastChar(_to);
            infoLoc_.setName(_word);
            _d.getVariables().add(infoLoc_);
            return _to;
        }
        if (_string.substring(n_).trim().indexOf('.') != 0) {
            return n_;
        }
        _d.getDelKeyWordStatic().add(_from);
        _d.getDelKeyWordStatic().add(n_);
        _d.getDelKeyWordStaticExtract().add(start_);
        _d.getStaticParts().add(partOffsets_);
        return n_;
    }
    private static IndexUnicodeEscape processStrings(KeyWords _key, String _string, int _max, IndexUnicodeEscape _infos, char _delimiter) {
        int i_ = _infos.getIndex();
        int nbChars_ = _infos.getNbChars();
        int unicode_ = _infos.getUnicode();
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
                if (i_ + 1 >= _max ||_string.charAt(i_ + 1) != DELIMITER_TEXT) {
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
        if (nbChars_ > 1 && _delimiter == DELIMITER_CHAR) {
            infos_.setIndex(-i_);
            return infos_;
        }
        if (!escapedMeta_) {
            if (curChar_ == ESCAPE_META_CHAR) {
                if (i_ + 1 >= _max) {
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
                char builtChar_ = NumParsers.parseCharSixteen(new String(unicodes_));
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
        if (!_string.startsWith(unicodeStr_,i_) || i_ + unicodeStr_.length() + UNICODE_SIZE > _max) {
            infos_.setIndex(-i_);
            return infos_;
        }
        unicode_ = 1;
        infos_.setUnicode(unicode_);
        i_+=unicodeStr_.length();
        infos_.setIndex(i_);
        return infos_;
    }
    private static int processPredefinedMethod(String _string, int _i, String _name) {
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

    private static boolean isNumber(int _start, String _string) {
        if (_start >= _string.length()) {
            return false;
        }
        char first_ = _string.charAt(_start);
        return ContextEl.isDigit(first_);
    }

    private static NumberInfosOutput processNb(KeyWords _key, int _start, int _max, String _string, boolean _seenDot, Options _opt) {
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
            nbInfos_.setSuffix(DOUBLE);
            decPart_.append(startChar_);
        } else {
            nbInfos_.setSuffix(INTEGER);
            if (startChar_ == '0') {
                String sub_ = _string.substring(start_ + 1);
                if (start_ + 1 < _max) {
                    String suff_ = _key.getNbKeyWord(sub_, 0);
                    if (suff_ != null) {
                        char ch_ = _key.getSuffixes().getVal(suff_);
                        nbInfos_.setSuffix(ch_);
                        intPart_.append(startChar_);
                        output_.setNextIndex(start_ + 1 + suff_.length());
                        return output_;
                    }
                    if (sub_.startsWith(hexPre_)) {
                        base_ = 16;
                        start_+=hexPre_.length();
                        start_++;
                        if (start_ >= _max) {
                            output_.setNextIndex(-start_);
                            return output_;
                        }
                        startChar_ = _string.charAt(start_);
                    } else if (sub_.startsWith(binPre_)) {
                        base_ = 2;
                        start_+=binPre_.length();
                        start_++;
                        if (start_ >= _max) {
                            output_.setNextIndex(-start_);
                            return output_;
                        }
                        startChar_ = _string.charAt(start_);
                    } else if (ContextEl.isDigit(_string.charAt(start_ + 1))){
                        base_ = 8;
                        start_++;
                        startChar_ = _string.charAt(start_);
                    }
                }
            }
            intPart_.append(startChar_);
        }
        nbInfos_.setBase(base_);
        int j_ = start_ + 1;
        boolean dot_ = false;
        boolean exp_ = false;
        int iExp_ = j_;
        while (j_ < _max) {
            char current_ = _string.charAt(j_);
            if (!StringList.isDollarWordChar(current_)) {
                if (current_ == DOT_VAR) {
                    if (_seenDot) {
                        nbInfos_.setError(true);
                        decPart_.append(current_);
                        j_++;
                        continue;
                    }
                    String nextPart_ = _string.substring(j_ + 1).trim();
                    if (nextPart_.isEmpty()) {
                        if (base_ == 10) {
                            nbInfos_.setSuffix(DOUBLE);
                            output_.setNextIndex(j_ + 1);
                            return output_;
                        }
                        nbInfos_.setError(true);
                        intPart_.append(current_);
                        j_++;
                        continue;
                    }
                    if (nextCharIs(_string,j_ + 1,_max,DOT_VAR)) {
                        nbInfos_.setError(true);
                        intPart_.append(current_);
                        j_++;
                        continue;
                    }
                    dot_ = true;
                }
                break;
            }
            String sub_ = _string.substring(j_);
            int off_ = getExpLength(base_, decExp_, binExp_);
            exp_ = isExp(base_, decExp_, binExp_, sub_);
            if (exp_) {
                if (isWhite(j_ + off_,_max,_string)) {
                    nbInfos_.setError(true);
                    append(_seenDot, intPart_, decPart_, current_);
                    j_++;
                    continue;
                }
                nbInfos_.setSuffix(DOUBLE);
                iExp_ = j_ + off_ - 1;
                j_ += off_ - 1;
                break;
            }
            if (isNonNbPart(current_, base_) && Character.isLetter(current_)) {
                j_ = incrSep(j_,base_,sub_,hexPre_);
                boolean ok_ = processSuffix(_key, _string, output_, nbInfos_, j_);
                if (ok_) {
                    return output_;
                }
                nbInfos_.setError(true);
                append(_seenDot, intPart_, decPart_, current_);
                j_++;
                continue;
            }
            //current_ is digit or expected letter
            append(_seenDot, intPart_, decPart_, current_);
            j_++;
        }
        if (dot_) {
            nbInfos_.setSuffix(DOUBLE);
            String sub_ = _string.substring(j_ + 1);
            int off_ = getExpLength(base_, decExp_, binExp_);
            exp_ = isExp(base_, decExp_, binExp_, sub_);
            if (exp_) {
                j_+=off_;
                //_string.charAt(j_) == EXP
                processExp(_key, j_, _max, _string, output_);
                return output_;
            }
            j_++;
            boolean added_ = false;
            while (j_ < _max) {
                char curChar_ = _string.charAt(j_);
                if (isNonNbPart(curChar_, base_)) {
                    break;
                }
                decPart_.append(curChar_);
                added_ = true;
                j_++;
            }
            if (!added_) {
                processDotSuffix(_key,base_,sub_,hexPre_, _string, output_, nbInfos_,j_, _max);
                return output_;
            }
            if (j_ >= _max) {
                output_.setNextIndex(j_);
                return output_;
            }
            sub_ = _string.substring(j_);
            exp_ = isExp(base_, decExp_, binExp_, sub_);
            if (exp_) {
                processExp(_key, j_, _max, _string, output_);
                return output_;
            }
            processDotSuffix(_key,base_,sub_,hexPre_, _string, output_, nbInfos_, j_, _max);
            return output_;
        }
        if (exp_) {
            //_string.charAt(iExp_) == EXP
            processExp(_key, iExp_, _max, _string, output_);
            return output_;
        }
        if (nbInfos_.isError()) {
            output_.setNextIndex(j_);
            return output_;
        }
        processCorrect(_string, output_, j_,_max);
        return output_;
    }
    private static int incrSep(int _j, int _base, String _sub, String _hexPre) {
        int j_ = _j;
        if (_base != 10) {
            if (_sub.startsWith(_hexPre)) {
                j_+=_hexPre.length();
            }
        }
        return j_;
    }

    private static boolean isWhite(int _current, int _max, String _str) {
        return _current < _max && Character.isWhitespace(_str.charAt(_current));
    }

    private static void append(boolean _seenDot, StringBuilder _intPart, StringBuilder _decPart, char _current) {
        if (_seenDot) {
            _decPart.append(_current);
        } else {
            _intPart.append(_current);
        }
    }

    private static boolean isExp(int _base, String _decExp, String _binExp, String _sub) {
        boolean exp_;
        if (_base == 10) {
            exp_ = _sub.startsWith(_decExp);
        } else {
            exp_ = _sub.startsWith(_binExp);
        }
        return exp_;
    }

    private static int getExpLength(int _base, String _decExp, String _binExp) {
        int off_;
        if (_base == 10) {
            off_ = _decExp.length();
        } else {
            off_ = _binExp.length();
        }
        return off_;
    }

    private static void processDotSuffix(KeyWords _key, int _base, String _sub, String _hexPre, String _string,
                                         NumberInfosOutput _output, NumberInfos _nbInfos, int _j, int _max) {
        int j_ = incrSep(_j,_base,_sub,_hexPre);
        String suff_ = _key.getNbKeyWord(_string, j_);
        if (suff_ == null) {
            if (StringList.isDollarWordChar(_string.charAt(j_))) {
                _nbInfos.getDecimalPart().append(_string.charAt(j_));
                _nbInfos.setError(true);
                j_++;
                while (j_ < _max) {
                    char curChar_ = _string.charAt(j_);
                    if (!StringList.isDollarWordChar(curChar_)) {
                        break;
                    }
                    _nbInfos.getDecimalPart().append(_string.charAt(j_));
                    j_++;
                }
                _output.setNextIndex(j_);
                return;
            }
        } else {
            j_ +=suff_.length();
            char su_ = _key.getSuffixes().getVal(suff_);
            _nbInfos.setSuffix(su_);
        }
        processCorrect(_string, _output, j_,_max);
    }

    private static boolean processSuffix(KeyWords _key, String _string, NumberInfosOutput _output, NumberInfos _nbInfos, int _j) {
        String suff_ = _key.getNbKeyWord(_string, _j);
        if (suff_ == null) {
            return false;
        }
        char ch_ = _key.getSuffixes().getVal(suff_);
        _nbInfos.setSuffix(ch_);
        int n_ = _j + suff_.length();
        boolean ok_ = processedCorrectOrContinue(_string, _output, n_);
        if (ok_) {
            _output.setNextIndex(n_);
        }
        return ok_;
    }

    private static boolean isNonNbPart(char _char, int _base) {
        return isNonDigit(_char,_base) && _char != NB_INTERN_SP;
    }
    private static boolean isNonDigit(char _char, int _base) {
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
        return !ok_;
    }
    private static void processExp(KeyWords _key, int _start, int _max, String _string, NumberInfosOutput _output) {
        if (_start + 1 >= _max) {
            _output.setNextIndex(-_start);
            return;
        }
        StringBuilder exp_ = _output.getInfos().getExponentialPart();
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
        while (j_ < _max) {
            if (isNonNbPart(_string.charAt(j_),10)) {
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
        if (j_ < _max && Character.isLetter(_string.charAt(j_))) {
            String keyWord_ = _key.getNbKeyWord(_string, j_);
            if (keyWord_ == null) {
                _output.setNextIndex(-j_);
                return;
            }
            char suf_ = _key.getSuffixes().getVal(keyWord_);
            _output.getInfos().setSuffix(suf_);
            j_+=keyWord_.length();
        }
        processCorrect(_string, _output, j_,_max);
    }

    private static void processCorrect(String _string, NumberInfosOutput _output, int _j, int _max) {
        int j_ = Math.min(_max,_j);
        String nextPartAf_ = _string.substring(j_).trim();
        if (isNotCorrectNbEnd(nextPartAf_)) {
            _output.setNextIndex(-_j);
            return;
        }
        _output.setNextIndex(_j);
    }

    private static boolean processedCorrectOrContinue(String _string, NumberInfosOutput _output, int _j) {
        String nextPartAf_ = _string.substring(_j).trim();
        return !isNotCorrectNbEnd(nextPartAf_);
    }

    private static boolean isNotCorrectNbEnd(String _next) {
        if (_next.isEmpty()) {
            return false;
        }
        char n_ = _next.charAt(0);
        if (ContextEl.isDigit(n_)) {
            return true;
        }
        return n_ == DOT_VAR;
    }
    public static OperationsSequence getOperationsSequence(int _offset, String _string,
            Analyzable _conf, Delimiters _d) {
        OperationsSequence seq_ = tryGetSequence(_offset, _string, _conf, _d);
        if (seq_ != null) {
            return seq_;
        }
        IntTreeMap<String> operators_;
        operators_ = new IntTreeMap<String>();
        IntTreeMap<Character> parsBrackets_;
        parsBrackets_ = new IntTreeMap<Character>();
        int prio_ = FCT_OPER_PRIO;
        int len_ = _string.length();
        int i_ = CustList.FIRST_INDEX;
        while (Character.isWhitespace(_string.charAt(i_))) {
            i_++;
        }
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordNew_ = keyWords_.getKeyWordNew();
        int firstPrintChar_ = i_;
        int lastPrintChar_ = len_ - 1;
        while (Character.isWhitespace(_string.charAt(lastPrintChar_))) {
            lastPrintChar_--;
        }
        len_ = lastPrintChar_+1;
        boolean enPars_ = true;
        boolean preIncr_ = false;
        String extracted_ = EMPTY_STRING;
        CustList<PartOffset> partsOffs_ = new CustList<PartOffset>();
        if (_string.charAt(firstPrintChar_) == MINUS_CHAR || _string.charAt(firstPrintChar_) == PLUS_CHAR) {
            if (firstPrintChar_ + 1 < _string.length()) {
                if (_string.charAt(firstPrintChar_) == _string.charAt(firstPrintChar_ + 1)) {
                    preIncr_ = true;
                }
            }
        }
        Options opt_ = _conf.getOptions();
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
            partsOffs_ = _d.getCastParts().get(ext_);
            i_ = incrementUnary(_string, firstPrintChar_, lastPrintChar_, _offset, _d);
        } else if (_d.getDelExplicit().contains(firstPrintChar_+_offset)) {
            prio_ = UNARY_PRIO;
            int min_ = _d.getDelExplicit().indexOfObj(firstPrintChar_+_offset);
            int max_ = _d.getDelExplicit().get(min_ + 1) - _offset;
            operators_.put(firstPrintChar_, _string.substring(firstPrintChar_, max_ + 1));
            i_ = incrementUnary(_string, firstPrintChar_, lastPrintChar_, _offset, _d);
        }
        boolean leftParFirstOperator_ = false;
        boolean is_ = false;
        String fctName_ = EMPTY_STRING;
        boolean enabledId_ = false;
        boolean instance_ = false;
        if (ContextEl.startsWithKeyWord(_string.substring(firstPrintChar_), keyWordNew_)) {
            instance_ = true;
        }
        if (_string.charAt(firstPrintChar_) == ANN_ARR_LEFT) {
            instance_ = true;
        }
        Ints laterIndexesDouble_ = new Ints();
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (_d.getDimsAddonIndexes().containsObj(i_+_offset)) {
                laterIndexesDouble_.add(i_);
            }
            if (!_d.getAllowedOperatorsIndexes().containsObj(i_+_offset)) {
                i_++;
                continue;
            }
            if (curChar_ == PAR_LEFT) {
                if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO) {
                    if (enPars_) {
                        leftParFirstOperator_ = true;
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
                    if (prio_ > DECL_PRIO) {
                        operators_.clear();
                    }
                    instance_ = false;
                    enabledId_ = false;
                    enPars_ = false;
                    operators_.put(i_, String.valueOf(SEP_ARG));
                    prio_ = DECL_PRIO;
                } else if (parsBrackets_.size() == 1 && prio_ == FCT_OPER_PRIO && enPars_){
                    addCommaOperIfNotEmpty(operators_, i_,PAR_LEFT,ARR_LEFT,ANN_ARR_LEFT);
                }
            }
            if (curChar_ == PAR_RIGHT) {
                parsBrackets_.removeKey(parsBrackets_.lastKey());
                if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO && enPars_) {
                    addOperIfNotEmpty(operators_, i_, PAR_LEFT,PAR_RIGHT);
                    enPars_ = false;
                    enabledId_ = true;
                }
                i_++;
                continue;
            }
            if (curChar_ == ANN_ARR_LEFT) {
                if (parsBrackets_.isEmpty()) {
                    if (FCT_OPER_PRIO == prio_) {
                        leftParFirstOperator_ = false;
                        if (instance_) {
                            fctName_ = _string.substring(firstPrintChar_, i_);
                            operators_.put(i_, ANN_ARR);
                        } else {
                            fctName_ = EMPTY_STRING;
                            operators_.clear();
                            addOperIfBegin(operators_, i_, firstPrintChar_, ANN_ARR);
                        }
                    }
                }
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == ANN_ARR_RIGHT) {
                parsBrackets_.removeKey(parsBrackets_.lastKey());
                if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO) {
                    addOperIfNotEmpty(operators_, i_, ANN_ARR_LEFT,ANN_ARR_RIGHT);
                    enPars_ = false;
                    enabledId_ = true;
                }
                i_++;
                continue;
            }
            if (curChar_ == ARR_LEFT) {
                if (parsBrackets_.isEmpty()) {
                    if (FCT_OPER_PRIO == prio_) {
                        leftParFirstOperator_ = false;

                        if (instance_) {
                            if (operators_.isEmpty()) {
                                fctName_ = _string.substring(firstPrintChar_, i_);
                                operators_.put(i_, ARR);
                            } else {
                                String op_ = operators_.firstValue();
                                if (StringList.quickEq(op_,ARR)) {
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
                            operators_.clear();
                            addOperIfBegin(operators_, i_, firstPrintChar_, ARR);
                        }
                    }
                }
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == ARR_RIGHT) {
                parsBrackets_.removeKey(parsBrackets_.lastKey());
                if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO) {
                    addOperIfNotEmpty(operators_, i_, ARR_LEFT,ARR_RIGHT);
                    enPars_ = false;
                    enabledId_ = true;
                }
                i_++;
                continue;
            }
            if (curChar_ == BEGIN_TERNARY) {
                if (parsBrackets_.isEmpty() && prio_ > TERNARY_PRIO) {
                    operators_.clear();
                    leftParFirstOperator_ = false;
                    operators_.put(i_, String.valueOf(curChar_));
                }
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == END_TERNARY) {
                parsBrackets_.removeKey(parsBrackets_.lastKey());
                if (parsBrackets_.isEmpty() && prio_ > TERNARY_PRIO) {
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
            if (prio_ == DECL_PRIO) {
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
                boolean nextIs_ = nextCharIs(_string, i_ + 1, len_, EQ_CHAR);
                if (curChar_ == NEG_BOOL_CHAR || nextIs_) {
                    builtOperator_.append(curChar_);
                    if (nextIs_) {
                        builtOperator_.append(EQ_CHAR);
                    }
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
                    if (isGreaterThanAff(prio_)) {
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
                if (nextCharIs(_string, i_ + 1, len_, curChar_)) {
                    prioOpMult_ = SHIFT_PRIO;
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
                boolean processDefaultOp_ = true;
                if (i_ + 1 < len_) {
                    char nextChar_ = _string.charAt(i_ + 1);
                    int delta_ = 0;
                    if (prioOpMult_ == SHIFT_PRIO) {
                        builtOperator_.append(nextChar_);
                        if (nextCharIs(_string, i_ + 2, len_, nextChar_)) {
                            delta_++;
                            builtOperator_.append(nextChar_);
                            if (nextCharIs(_string, i_ + 3, len_, nextChar_)) {
                                delta_++;
                                builtOperator_.append(nextChar_);
                            }
                        }
                    }
                    if (isIncrementOperator(curChar_, prioOpMult_, nextChar_)) {
                        if (prio_ > POST_INCR_PRIO) {
                            clearOperators_ = true;
                            prio_ = POST_INCR_PRIO;
                            foundOperator_ = true;
                        }
                        increment_ = 2;
                        builtOperator_.append(nextChar_);
                        processDefaultOp_ = false;
                    } else if (isLogicAndOr(curChar_, andOr_, nextChar_)) {
                        builtOperator_.append(curChar_);
                        if (nextCharIs(_string,i_+2,len_,EQ_CHAR)) {
                            increment_ = 3;
                            if (isGreaterThanAff(prio_)) {
                                clearOperators_ = true;
                                prio_ = AFF_PRIO;
                                foundOperator_ = true;
                            }
                            builtOperator_.append(EQ_CHAR);
                        } else {
                            if (prio_ > prioOpMult_) {
                                prio_ = prioOpMult_;
                            }
                            if (prio_ == prioOpMult_) {
                                clearOperators_ = true;
                                foundOperator_ = true;
                            }
                            increment_ = 2;
                        }
                        processDefaultOp_ = false;
                    } else if (isAffectation(nextChar_, prioOpMult_, _string, delta_+i_ + 2, len_)) {
                        increment_ = 2;
                        if (prioOpMult_ == SHIFT_PRIO) {
                            increment_++;
                        }
                        if (isGreaterThanAff(prio_)) {
                            clearOperators_ = true;
                            prio_ = AFF_PRIO;
                            foundOperator_ = true;
                        }
                        builtOperator_.append(EQ_CHAR);
                        processDefaultOp_ = false;
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
                    }
                }
                if (processDefaultOp_) {
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
                    //Event if there is a previous operator, add the current operator
                    //Sample: 1<2<3 produces an error
                    foundOperator_ = true;
                }
                if (nextCharIs(_string, i_ + 1, len_, EQ_CHAR)) {
                    char nextChar_ = _string.charAt(i_ + 1);
                    builtOperator_.append(nextChar_);
                    increment_++;
                }
            }
            if (foundOperator_) {
                if (clearOperators_) {
                    operators_.clear();
                }
                leftParFirstOperator_ = false;
                fctName_ = EMPTY_STRING;
                instance_ = false;
                enPars_ = false;
                enabledId_ = false;
                operators_.put(i_,builtOperator_.toString());
            }
            i_ += increment_;
        }
        OperationsSequence op_ = new OperationsSequence();
        op_.setPriority(prio_);
        op_.setOperators(operators_);
        op_.setLeftParFirstOperator(leftParFirstOperator_);
        op_.setFctName(fctName_);
        op_.setupValues(_string, is_, instance_, laterIndexesDouble_);
        op_.setExtractType(extracted_);
        op_.setPartOffsets(partsOffs_);
        op_.setDelimiter(_d);
        return op_;
    }

    static boolean isGreaterThanAff(int _prio) {
        return _prio > AFF_PRIO && _prio != TERNARY_PRIO;
    }

    private static void addOperIfBegin(IntTreeMap< String> _operators, int _i, int _first, String _arr) {
        if (_first == _i) {
            _operators.put(_i, _arr);
        } else {
            _operators.put(_i, EMPTY_STRING);
        }
    }

    private static void addCommaOperIfNotEmpty(IntTreeMap<String> _operators, int _i, char... _open) {
        String oper_ = _operators.firstValue();
        int len_ = oper_.length();
        for (char c: _open) {
            if (nextCharIs(oper_,0, len_,c)) {
                _operators.put(_i, String.valueOf(SEP_ARG));
                break;
            }
        }
    }

    private static void addOperIfNotEmpty(IntTreeMap< String> _operators, int _i, char _open, char _op) {
        String oper_ = _operators.firstValue();
        if (nextCharIs(oper_,0, oper_.length(),_open)) {
            _operators.put(_i, String.valueOf(_op));
        }
    }

    private static boolean isAffectation(char _nextChar, int _prioOpMult, String _string, int _lastCharIndex,int _len) {
        boolean aff_ = false;
        if (_nextChar == EQ_CHAR) {
            aff_ = true;
        } else if (_prioOpMult == SHIFT_PRIO && nextCharIs(_string, _lastCharIndex, _len, EQ_CHAR)) {
            aff_ = true;
        }
        return aff_;
    }
    private static boolean isIncrementOperator(char _curChar, int _prioOpMult,
            char _nextChar) {
        return _prioOpMult == ADD_PRIO && _nextChar == _curChar;
    }

    private static boolean isLogicAndOr(char _curChar, boolean _andOr,
            char _nextChar) {
        return _andOr && _nextChar == _curChar;
    }

    private static boolean nextCharIs(String _str, int _i, int _len, char _value) {
        if (_i >= _len) {
            return false;
        }
        return _str.charAt(_i) == _value;
    }
    private static OperationsSequence tryGetSequence(int _offset, String _string,
            Analyzable _conf, Delimiters _d) {
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
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, 0);
            op_.setDelimiter(_d);
            return op_;
        }
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordFalse_ = keyWords_.getKeyWordFalse();
        String keyWordNull_ = keyWords_.getKeyWordNull();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        String keyWordTrue_ = keyWords_.getKeyWordTrue();
        int firstPrintChar_ = i_;
        int lastPrintChar_ = len_ - 1;
        while (Character.isWhitespace(_string.charAt(lastPrintChar_))) {
            lastPrintChar_--;
        }
        int strLen_ = _string.length();
        len_ = lastPrintChar_+1;
        int begin_;
        int end_;
        begin_ = _d.getDelKeyWordStatic().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelKeyWordStatic().indexOfObj(_offset + strLen_);
        if (delimits(begin_, end_)) {
            int ext_ = begin_ / 2;
            String extracted_ = _d.getDelKeyWordStaticExtract().get(ext_);
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.STATIC_ACCESS);
            op_.setExtractType(extracted_);
            op_.setPartOffsets(_d.getStaticParts().get(ext_));
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelKeyWordStaticCall().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelKeyWordStaticCall().indexOfObj(_offset + strLen_);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.STATIC_CALL_ACCESS);
            op_.setPartOffsets(new CustList<PartOffset>());
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelSimpleAnnotations().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelSimpleAnnotations().indexOfObj(_offset + lastPrintChar_);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.SIMPLE_ANNOTATION);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setFctName(_string);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelVararg().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelVararg().indexOfObj(_offset + lastPrintChar_);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.VARARG);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelDefaultValue().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelDefaultValue().indexOfObj(_offset + lastPrintChar_);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.DEFAULT_VALUE);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelLambda().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelLambda().indexOfObj(_offset + lastPrintChar_);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.LAMBDA);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelIds().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelIds().indexOfObj(_offset + lastPrintChar_);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.ID);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelClass().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelClass().indexOfObj(_offset + lastPrintChar_);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.CLASS_INFO);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelKeyWordSuper().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelKeyWordSuper().indexOfObj(_offset + lastPrintChar_ + 1);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.SUPER_KEYWORD);
            op_.setOperators(new IntTreeMap< String>());
            int ind_ = _string.indexOf('.') + 1;
            op_.setDelta(ind_);
            op_.setValue(_string.substring(ind_, lastPrintChar_ + 1), firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelKeyWordClassChoice().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelKeyWordClassChoice().indexOfObj(_offset + lastPrintChar_ + 1);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.CLASSCHOICE_KEYWORD);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string.substring(firstPrintChar_, lastPrintChar_ + 1),firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelLoopVars().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelLoopVars().indexOfObj(_offset + lastPrintChar_);
        if (delimits(begin_, end_)) {
            StringList parts_ = StringList.getDollarWordSeparators(_string.substring(firstPrintChar_, lastPrintChar_));
            String name_ = parts_.get(1);
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.LOOP_INDEX);
            op_.setOperators(new IntTreeMap< String>());
            op_.setDelta(parts_.get(0).length());
            op_.setValue(name_, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelKeyWordSuperAccess().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelKeyWordSuperAccess().indexOfObj(_offset + lastPrintChar_ + 1);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.SUPER_ACCESS_KEYWORD);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string.substring(firstPrintChar_, lastPrintChar_ + 1),firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelAccessIndexers().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelAccessIndexers().indexOfObj(_offset + lastPrintChar_+1);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.ACCESS_INDEXER);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string.substring(firstPrintChar_, lastPrintChar_ + 1),firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        String sub_ = _string.substring(firstPrintChar_, len_);
        if (StringList.quickEq(sub_, keyWordThis_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.THIS_KEYWORD);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (StringList.quickEq(sub_, keyWordNull_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.NULL_CST);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (StringList.quickEq(sub_, keyWordTrue_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.TRUE_CST);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        if (StringList.quickEq(sub_, keyWordFalse_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.FALSE_CST);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelNumbers().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelNumbers().indexOfObj(_offset + lastPrintChar_ + 1);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.NUMBER);
            int indexNb_ = begin_/2;
            op_.setOperators(new IntTreeMap< String>());
            op_.setNbInfos(_d.getNbInfos().get(indexNb_));
            op_.getNbInfos().setPositive(true);
            op_.setValue(_string, firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelStringsChars().indexOfObj(firstPrintChar_+_offset);
        end_ = _d.getDelStringsChars().indexOfObj(lastPrintChar_+_offset);
        if (delimits(begin_, end_)) {
            StringInfo info_ = _d.getStringInfo().get(begin_/2);
            CharList list_ = info_.getChars();
            int lenStr_ = list_.size();
            char[] str_ = new char[lenStr_];
            for (int i = 0; i < lenStr_; i++) {
                str_[i] = list_.get(i);
            }
            if (_string.charAt(firstPrintChar_) == DELIMITER_CHAR) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.CHARACTER);
                op_.setOperators(new IntTreeMap< String>());
                op_.setValue(new String(str_), firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            if (_string.charAt(firstPrintChar_) == DELIMITER_STRING) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.STRING);
                op_.setOperators(new IntTreeMap< String>());
                op_.setValue(new String(str_), firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.STRING);
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(new String(str_), firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        for (VariableInfo v: _d.getVariables()) {
            if (v.getFirstChar() != _offset + firstPrintChar_) {
                continue;
            }
            int iVar_ = v.getLastChar();
            if (iVar_ != _offset + lastPrintChar_ + 1) {
                break;
            }
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(v.getKind());
            op_.setOperators(new IntTreeMap< String>());
            op_.setValue(v.getName(), firstPrintChar_);
            op_.setDelimiter(_d);
            return op_;
        }
        return null;
    }

    private static boolean delimits(int _begin, int _end) {
        return _begin > CustList.INDEX_NOT_FOUND_ELT && _begin + 1 == _end;
    }
    private static int incrementUnary(String _string, int _from, int _to, int _offset, Delimiters _d) {
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
                                indexCast_ = _d.getDelExplicit().indexOfObj(sum_);
                                if (indexCast_ > -1) {
                                    int next_ = _d.getDelExplicit().get(indexCast_ + 1);
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

    private static int indexAfterPossibleCast(Analyzable _conf,  ResultAfterDoubleDotted _out,boolean _ctor,String _string, int _from, int _len, Delimiters _d) {
        int indexParRight_ = _string.indexOf(PAR_RIGHT, _from +1);
        Options opt_ = _conf.getOptions();
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        if (indexParRight_ < 0) {
            return _from;
        }
        if (_d.getCallings().containsObj(_from)) {
            return _from;
        }

        String sub_ = _string.substring(_from + 1, indexParRight_);
        String subTrim_ = sub_.trim();
        int arrRight_ = subTrim_.indexOf(ARR_RIGHT);
        if (subTrim_.startsWith(ARR) && arrRight_ > -1) {
            _d.getDelLoopVars().add(_from);
            _d.getDelLoopVars().add(indexParRight_);
            return indexParRight_ + 1;
        }
        int j_ = _from + 1;
        while (true) {
            char locChar_ = _string.charAt(j_);
            if (!Character.isWhitespace(locChar_)) {
                break;
            }
            j_++;
        }
        boolean cast_ = true;
        boolean type_ = false;
        if (isNumber(j_, _string)) {
            cast_ = false;
        }
        if (j_ >= indexParRight_) {
            cast_ = false;
        }
        if (cast_) {
            char locCar_ = _string.charAt(j_);
            if (!StringList.isDollarWordChar(locCar_)) {
                if (locCar_ == Templates.PREFIX_VAR_TYPE_CHAR) {
                    type_ = true;
                } else {
                    cast_ = false;
                }
            }
            int k_ = indexParRight_+1;
            while (k_ < _len) {
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
        Ints indexes_ = new Ints();
        StringBuilder part_ = new StringBuilder();
        StringList partsFields_ = new StringList();
        int lChar_ = -1;
        if (cast_) {
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
                if (locCar_ == Templates.SUB_TYPE_CHAR
                        || locCar_ == Templates.SUP_TYPE_CHAR
                        || locCar_ == Templates.COMMA
                        || locCar_ == ARR_RIGHT
                        || locCar_ == ARR_LEFT) {
                    if (count_ > 0) {
                        type_ = true;
                        j_++;
                        continue;
                    }
                }
                if (locCar_ == ARR_LEFT) {
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
                    if (next_ >= indexParRight_) {
                        _d.setBadOffset(indexParRight_);
                        return _from;
                    }
                    break;
                }
                if (StringList.isDollarWordChar(locCar_)) {
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
                    String strPart_ = part_.toString();
                    if (isFieldOrVariable(_conf, _out,_ctor, partsFields_, strPart_)) {
                       cast_ = false;
                        break;
                    }
                    partsFields_.add(strPart_);
                    part_.delete(0, part_.length());
                    lChar_ = -1;
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
                String typeRes_ = _conf.resolveCorrectTypeWithoutErrors(_from+1,sub_, true);
                if (!typeRes_.isEmpty()) {
                    _d.getDelCast().add(_from);
                    _d.getDelCast().add(indexParRight_);
                    _d.getDelCastExtract().add(typeRes_);
                    _d.getCastParts().add(new CustList<PartOffset>(_conf.getContextEl().getCoverage().getCurrentParts()));
                    return indexParRight_ + 1;
                }
                cast_ = false;
            }
        }
        if (cast_) {
           String strPart_ = part_.toString();
           if (isFieldOrVariable(_conf, _out,_ctor, partsFields_, strPart_)) {
               cast_ = false;
            }
        }
        if (cast_) {
            String typeRes_ = _conf.resolveCorrectTypeWithoutErrors(_from+1,sub_, true);
            if (!typeRes_.isEmpty()) {
                _d.getDelCast().add(_from);
                _d.getDelCast().add(indexParRight_);
                _d.getDelCastExtract().add(typeRes_);
                _d.getCastParts().add(new CustList<PartOffset>(_conf.getContextEl().getCoverage().getCurrentParts()));
                return indexParRight_ + 1;
            }
        }
        return _from;
    }
    private static boolean isFieldOrVariable(Analyzable _conf,  ResultAfterDoubleDotted _out,boolean _ctor, StringList _parts, String _partToAdd) {
       if (!_parts.isEmpty()) {
          return false;
       }
       String word_ = _partToAdd.trim();
       if (_out.isDeclared(word_)) {
           return true;
       }
       if (_conf.getParameters().contains(word_)) {
          return true;
       }
       if (_conf.getAnalyzing().containsCatchVar(word_)) {
          return true;
       }
       if (_conf.containsMutableLoopVar(word_)) {
          return true;
       }
       if (_conf.getAnalyzing().containsVar(word_)) {
          return true;
       }
       if (_conf.getAnalyzing().containsLocalVar(word_)) {
          return true;
       }
       String gl_ = _conf.getGlobalClass();
      return isField(_conf, gl_, _ctor, word_);
    }
    private static boolean isField(Analyzable _conf, String _fromClass, boolean _ctor, String _word) {
        boolean field_;
        boolean stCtx_ = _conf.isStaticContext() || _ctor;
        field_ = true;
        ClassArgumentMatching clArg_ = new ClassArgumentMatching(_fromClass);
        FieldResult fr_ = OperationNode.resolveDeclaredCustField(_conf, stCtx_, clArg_, true, true, _word, _conf.getCurrentBlock() != null, false);
        if (fr_.getStatus() != SearchingMemberStatus.UNIQ) {
            field_ = false;
        }
        return field_;
    }
}
