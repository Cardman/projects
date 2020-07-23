package code.expressionlanguage.instr;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.blocks.FieldBlock;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.*;


public final class ElResolver {

    public static final int CONST_PRIO = 0;
    public static final int DECL_PRIO = 1;
    public static final int AFF_PRIO = 2;
    public static final int TERNARY_PRIO = 3;
    public static final int NULL_SAFE_PRIO = 4;
    public static final int OR_PRIO = 5;
    public static final int AND_PRIO = 6;
    public static final int BIT_OR_PRIO = 7;
    public static final int BIT_XOR_PRIO = 8;
    public static final int BIT_AND_PRIO = 9;
    public static final int EQ_PRIO = 10;
    public static final int CMP_PRIO = 11;
    public static final int SHIFT_PRIO = 12;
    public static final int ADD_PRIO = 13;
    public static final int MULT_PRIO = 14;
    public static final int UNARY_PRIO = 15;
    public static final int POST_INCR_PRIO = 16;
    public static final int FCT_OPER_PRIO = 17;
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

    public static Delimiters checkSyntaxDelimiters(String _string, ContextEl _conf, int _minIndex) {
        Delimiters d_ = new Delimiters();
        d_.setPartOfString(true);
        return commonCheck(_string, _conf, _minIndex, new FullFieldRetriever(d_,_string,_conf), d_);
    }

    public static Delimiters checkSyntax(String _string, ContextEl _conf, int _elOffest) {
        Delimiters d_ = new Delimiters();
        d_.setLength(_string.length());
        return commonCheck(_string, _conf, _elOffest, new FullFieldRetriever(d_,_string,_conf), d_);
    }

    static Delimiters checkSyntaxQuick(String _string, ContextEl _conf) {
        Delimiters d_ = new Delimiters();
        d_.setLength(_string.length());
        return commonCheck(_string, _conf, 0, new QuickFieldRetriever(d_), d_);
    }
    private static Delimiters commonCheck(String _string, ContextEl _conf, int _minIndex, FieldRetriever _ret, Delimiters _d) {
        boolean partOfString_ = _d.isPartOfString();
        boolean delimiters_ = partOfString_;

        IntTreeMap<Character> parsBrackets_;
        parsBrackets_ = new IntTreeMap<Character>();
        ResultAfterOperators resOpers_ = new ResultAfterOperators();
        resOpers_.setParsBrackets(parsBrackets_);
        resOpers_.setPartOfString(partOfString_);

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
            _d.setBadOffset(i_);
            return _d;
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
                IndexUnicodeEscape res_ = processStrings(keyWords_,_string, len_, si_,unic_, DELIMITER_CHAR);
                int index_ = res_.getIndex();
                if (!res_.isPart()) {
                    _d.getStringInfo().add(si_);
                    _d.getDelStringsChars().add(i_);
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
                IndexUnicodeEscape res_ = processStrings(keyWords_, _string, len_, si_, unic_, DELIMITER_STRING);
                int index_ = res_.getIndex();
                if (!res_.isPart()) {
                    _d.getStringInfo().add(si_);
                    _d.getDelStringsChars().add(i_);
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
                IndexUnicodeEscape res_ = processStrings(keyWords_, _string, len_, si_, unic_, DELIMITER_TEXT);
                int index_ = res_.getIndex();
                if (!res_.isPart()) {
                    _d.getStringInfo().add(si_);
                    _d.getDelStringsChars().add(i_);
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
            processAfterInstuctionKeyWord(beginIndex_,_string, _d, resKeyWords_, _conf);
            if (_d.getBadOffset() >= 0) {
                return _d;
            }
            int nextInd_ = resKeyWords_.getNextIndex();
            if (nextInd_ > i_) {
                i_ = nextInd_;
                ctorCall_ = resKeyWords_.isCallCtor();
                continue;
            }
            if (StringExpUtil.isTypeLeafChar(curChar_)) {
                resWords_.setNextIndex(i_);
                resWords_.setLastDoubleDot(lastDoubleDot_);
                resWords_.setCallCtor(ctorCall_);
                processWords(_string,curChar_, _d, _ret,resWords_, _conf);
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
            processOperators(beginIndex_,_minIndex,_string, delimiters_, _d, resOpers_, _conf);
            if (_d.getBadOffset() >= 0) {
                return _d;
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
            _d.setBadOffset(i_);
            return _d;
        }
        if (constChar_) {
            _d.setBadOffset(i_);
            return _d;
        }
        if (constText_) {
            _d.setBadOffset(i_);
            return _d;
        }
        if (!parsBrackets_.isEmpty()) {
            _d.setBadOffset(i_);
            return _d;
        }
        if (!partOfString_) {
            _d.setIndexBegin(_minIndex);
            return _d;
        }
        _d.setBadOffset(i_);
        return _d;
    }
    private static void processAfterInstuctionKeyWord(int _beginIndex,String _string,Delimiters _d, ResultAfterInstKeyWord _out, ContextEl _conf) {
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
        String keyWordParent_ = keyWords_.getKeyWordParent();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        String keyWordTrue_ = keyWords_.getKeyWordTrue();
        String keyWordValueOf_ = keyWords_.getKeyWordValueOf();
        String keyWordValues_ = keyWords_.getKeyWordValues();
        String keyWordVararg_ = keyWords_.getKeyWordVararg();
        String keyWordDefault_ = keyWords_.getKeyWordDefaultValue();
        String keyWordDefaultValue_ = keyWords_.getKeyWordDefaultValue();
        String keyWordOperator_ = keyWords_.getKeyWordOperator();
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordCast_)) {
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
            _d.getDelCast().add(i_);
            _d.getDelCast().add(indexParRight_);
            _d.getDelCastExtract().add(EMPTY_STRING);
            _d.getCastParts().add(new CustList<PartOffset>());
            i_ = indexParRight_ + 1;
            _out.setNextIndex(i_);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordExplicit_)) {
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
            _d.getDelExplicit().add(i_);
            _d.getDelExplicit().add(indexParRight_);
            i_ = indexParRight_ + 1;
            _out.setNextIndex(i_);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordVararg_)) {
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordDefaultValue_)) {
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordClass_)) {
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordInstanceof_)) {
            int next_ = i_ + keyWordInstanceof_.length();
            while (next_ < len_) {
                char curLoc_ = _string.charAt(next_);
                if (StringExpUtil.isTypeLeafChar(curLoc_)) {
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
                break;
            }
            if (next_ < len_ && _string.charAt(next_) == LOWER_CHAR) {
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
                    if (nbOpened_ == 0 && exitWhenNoSpace_ && !Character.isWhitespace(curLoc_)) {
                        break;
                    }
                    next_++;
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordNew_)) {
            int j_ = i_+keyWordNew_.length();

            int count_ = 0;
            boolean foundLeftPar_ = false;
            boolean foundLeft_ = false;
            while (j_ < len_) {
                char curLoc_ = _string.charAt(j_);
                if (!Character.isWhitespace(curLoc_)) {
                    if (curLoc_ == ANN_ARR_LEFT) {
                        foundLeft_ = true;
                    }
                    if (curLoc_ == PAR_LEFT || curLoc_ == ARR_LEFT) {
                        foundLeftPar_ = true;
                    }
                    j_++;
                    break;
                }
                j_++;
            }
            if (foundLeftPar_) {
                i_ = j_-1;
                _out.setNextIndex(i_);
                return;
            }
            boolean found_ = false;
            while (j_ < len_) {
                char curLoc_ = _string.charAt(j_);
                if (!Character.isWhitespace(curLoc_)) {
                    if (curLoc_ == ANN_ARR_RIGHT) {
                        found_ = true;
                    }
                    break;
                }
                j_++;
            }
            if (foundLeft_ && !found_) {
                _d.setBadOffset(j_);
                return;
            }
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
                if (curLoc_ == ARR_LEFT && count_ == 0) {
                    break;
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordLambda_)) {
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordId_)) {
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
        boolean isKeySt_ = StringExpUtil.startsWithKeyWord(_string,i_, keyWordStatic_);
        boolean isKeyStCall_ = StringExpUtil.startsWithKeyWord(_string,i_, keyWordStaticCall_);
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordSuper_)) {
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
                if (!StringExpUtil.isTypeLeafChar(_string.charAt(afterSuper_))) {
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordClasschoice_)) {
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
            if (afterClassChoice_ < len_ && _string.charAt(afterClassChoice_) == ARR_LEFT) {
                _d.getDelAccessIndexers().add(i_);
                _d.getDelAccessIndexers().add(afterClassChoice_);
                i_ = afterClassChoice_;
                _out.setNextIndex(i_);
                return;
            }
            boolean pass_ = false;
            while (afterClassChoice_ < len_) {
                char loc_ = _string.charAt(afterClassChoice_);
                if (!StringExpUtil.isTypeLeafChar(loc_)) {
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordSuperaccess_)) {
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
            if (afterClassChoice_ < len_ && _string.charAt(afterClassChoice_) == ARR_LEFT) {
                _d.getDelAccessIndexers().add(i_);
                _d.getDelAccessIndexers().add(afterClassChoice_);
                i_ = afterClassChoice_;
                _out.setNextIndex(i_);
                return;
            }
            boolean pass_ = false;
            while (afterClassChoice_ < len_) {
                char loc_ = _string.charAt(afterClassChoice_);
                if (!StringExpUtil.isTypeLeafChar(loc_)) {
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordThisaccess_)) {
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
            if (afterClassChoice_ < len_ && _string.charAt(afterClassChoice_) == ARR_LEFT) {
                _d.getDelAccessIndexers().add(i_);
                _d.getDelAccessIndexers().add(afterClassChoice_);
                i_ = afterClassChoice_;
                _out.setNextIndex(i_);
                return;
            }
            while (afterClassChoice_ < len_) {
                char loc_ = _string.charAt(afterClassChoice_);
                if (!StringExpUtil.isTypeLeafChar(loc_)) {
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordInterfaces_)|| StringExpUtil.startsWithKeyWord(_string,i_, keyWordOperator_)) {
            int afterClassChoice_;
            if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordInterfaces_)) {
                afterClassChoice_ = i_ + keyWordInterfaces_.length();
            } else {
                afterClassChoice_ = i_ + keyWordOperator_.length();
            }
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
            if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordInterfaces_)&&i_==_beginIndex) {
                _out.setCallCtor(true);
            }
            _d.getCallings().add(afterClassChoice_);
            i_ = afterClassChoice_;
            _out.setNextIndex(i_);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordThat_)) {
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
            if (afterSuper_ < len_ && _string.charAt(afterSuper_) == ARR_LEFT) {
                _d.getDelAccessIndexers().add(i_);
                _d.getDelAccessIndexers().add(afterSuper_);
                i_ = afterSuper_;
                _out.setNextIndex(i_);
                return;
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
                if (!StringExpUtil.isTypeLeafChar(_string.charAt(afterSuper_))) {
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordThis_)) {
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
        for (String s: StringList.wrapStringArray(keyWordTrue_,keyWordFalse_,keyWordNull_,keyWordParent_)) {
            if (StringExpUtil.startsWithKeyWord(_string,i_, s)) {
                int afterSuper_ = i_ + s.length();
                while (afterSuper_ < len_) {
                    char ch_ = _string.charAt(afterSuper_);
                    if (!Character.isWhitespace(ch_)) {
                        if (ch_ == PAR_LEFT) {
                            _d.getCallings().add(afterSuper_);
                        }
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
        for (String s: StringList.wrapStringArray(keyWordFirstopt_,keyWordBool_,keyWordValueOf_,keyWordValues_,keyWordDefault_)) {
            if (StringExpUtil.startsWithKeyWord(_string,i_, s)) {
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
        _conf.getAnalyzing().getProcessKeyWord().processInternKeyWord(_string, i_, _out);
    }
    private static void processWords(String _string, char _curChar,Delimiters _d, FieldRetriever _ret, ResultAfterDoubleDotted _out,ContextEl _an) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        boolean ctorCall_ = _out.isCallCtor();
        KeyWords keyWords_ = _an.getKeyWords();
        ResultAfterInstKeyWord resTmp_ = new ResultAfterInstKeyWord();
        resTmp_.setNextIndex(i_);
        if (StringExpUtil.isDigit(_curChar)) {
            NumberInfosOutput res_ = processNb(keyWords_, i_, len_, _string, false);
            int nextIndex_ = res_.getNextIndex();
            _d.getNbInfos().add(res_.getInfos());
            _d.getDelNumbers().add(i_);
            _d.getDelNumbers().add(nextIndex_);
            _out.setNextIndex(nextIndex_);
            return;
        }
        int beginWord_ = i_;
        while (i_ < len_) {
            char locChar_ = _string.charAt(i_);
            if (!StringExpUtil.isTypeLeafChar(locChar_)) {
                break;
            }
            i_++;
        }
        String prev_ = _string.substring(0, beginWord_).trim();
        String word_ = _string.substring(beginWord_, i_);
        if (StringExpUtil.nextPrintCharIs(i_,len_,_string,PAR_LEFT) > -1) {
            int j_ = i_;
            while (_string.charAt(j_) != PAR_LEFT) {
                j_++;
            }
            _d.getCallings().add(j_);
            _out.setNextIndex(i_);
            return;
        }
        if (prev_.endsWith(String.valueOf('.'))) {
            ConstType type_;
            type_ = ConstType.WORD;
            VariableInfo info_ = new VariableInfo();
            info_.setKind(type_);
            info_.setFirstChar(beginWord_);
            info_.setLastChar(i_);
            info_.setName(word_);
            _d.getVariables().add(info_);
            _out.setNextIndex(i_);
            return;
        }
        i_ = _ret.processFieldsStaticAccess(ctorCall_,beginWord_,word_,i_);
        _out.setNextIndex(i_);
    }

    static boolean isPureAffectation(String _string, int len_) {
        int index_ = StringExpUtil.nextPrintCharIs(0, len_, _string, '=');
        return hasCharOtherThanEq(_string, len_, index_);
    }

    private static boolean hasCharOtherThanEq(String _string, int len_, int index_) {
        return index_ > -1 && !StringExpUtil.nextCharIs(_string, index_ + 1, len_, '=');
    }

    private static void processOperators(int _beginIndex, int _minIndex, String _string, boolean _delimiters,
                                         Delimiters _dout, ResultAfterOperators _out, ContextEl _conf) {
        IntTreeMap<Character> parsBrackets_;
        parsBrackets_ = _out.getParsBrackets();

        int len_ = _string.length();
        ResultAfterDoubleDotted doubleDotted_ = _out.getDoubleDotted();
        int i_ = doubleDotted_.getNextIndex();
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
        if (_conf.isAnnotAnalysis() && curChar_ == ANNOT) {
            int j_ = i_ + 1;
            int last_ = i_;
            while (j_ < len_) {
                char locChar_ = _string.charAt(j_);
                if (StringExpUtil.isTypeLeafChar(locChar_)) {
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
            if (StringExpUtil.nextCharIs(_string,j_,len_,PAR_LEFT)) {
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
            int n_ = StringExpUtil.nextPrintChar(i_ + 1, len_, _string);
            if (isDigitOrDot(_string,n_)) {
                NumberInfosOutput res_ = processNb(keyWords_, i_ + 1, len_, _string, true);
                int nextIndex_ = res_.getNextIndex();
                _dout.getNbInfos().add(res_.getInfos());
                _dout.getDelNumbers().add(i_);
                _dout.getDelNumbers().add(nextIndex_);
                i_ = nextIndex_;
                doubleDotted_.setNextIndex(i_);
                return;
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
            int j_ = indexAfterPossibleCast(_conf, _string, i_, _dout);
            if (j_ > i_) {
                i_ = j_;
                doubleDotted_.setNextIndex(i_);
                return;
            }
            _dout.getCallings().add(i_);
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
            boolean ternary_ = false;
            if (StringExpUtil.nextCharIs(_string, i_ + 1, len_, DOT_VAR)) {
                int n_ = StringExpUtil.nextPrintChar(i_ + 2, len_, _string);
                if (isDigitOrDot(_string,n_)) {
                    ternary_ = true;
                }
            } else {
                if (!StringExpUtil.nextCharIs(_string, i_ + 1, len_, BEGIN_TERNARY)
                        &&!StringExpUtil.nextCharIs(_string, i_ + 1, len_, ARR_LEFT)) {
                    ternary_ = true;
                }
            }
            if (ternary_) {
                parsBrackets_.put(i_, curChar_);
            }
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
        if (curChar_ == SEP_ARG && parsBrackets_.isEmpty() && isAcceptCommaInstr(_conf)) {
            _dout.setBadOffset(i_);
            return;
        }
        boolean escapeOpers_ = false;
        boolean addOp_ = true;
        boolean andOr_ = false;
        boolean nullSafe_ = false;
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
            nullSafe_ = true;
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
            if (nullSafe_ && StringExpUtil.nextCharIs(_string, j_, len_, DOT_VAR)) {
                int n_ = StringExpUtil.nextPrintChar(j_ + 1, len_, _string);
                if (!isDigitOrDot(_string,n_)) {
                    j_++;
                }
            }
            if (nullSafe_ && j_ < len_ && _string.charAt(j_) == curChar_) {
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
                if (StringExpUtil.startsWithKeyWord(_string,j_, keyWordCast_)) {
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
                if (StringExpUtil.startsWithKeyWord(_string,j_, keyWordExplicit_)) {
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
                    int k_ = indexAfterPossibleCast(_conf, _string, j_, _dout);
                    int after_ = _dout.getDelCastExtract().size();
                    j_ = k_;
                    if (before_ != after_) {
                        continue;
                    }
                    _dout.getCallings().add(k_);
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

    private static boolean isAcceptCommaInstr(ContextEl _conf) {
        AnalyzedPageEl ana_ = _conf.getAnalyzing();
        return !ana_.isAcceptCommaInstr() && !(ana_.getCurrentBlock() instanceof FieldBlock);
    }

    static boolean isDigitOrDot(String _string, int _n) {
        return _n > -1 && (StringExpUtil.isDigit(_string.charAt(_n)) || _string.charAt(_n) == DOT_VAR);
    }

    private static IndexUnicodeEscape processStrings(KeyWords _key, String _string, int _max, StringInfo _si, IndexUnicodeEscape _infos, char _delimiter) {
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
            _si.setKo();
        }
        if (!escapedMeta_) {
            if (curChar_ == ESCAPE_META_CHAR) {
                if (i_ + 1 >= _max) {
                    _si.setKo();
                    i_++;
                    infos_.setIndex(i_);
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
            if (StringExpUtil.isDigit(curChar_)) {
                ok_ = true;
            }
            if (curChar_ >= MIN_ENCODE_LOW_LETTER && curChar_ <= MAX_ENCODE_LOW_LETTER) {
                ok_ = true;
            }
            if (curChar_ >= MIN_ENCODE_UPP_LETTER && curChar_ <= MAX_ENCODE_UPP_LETTER) {
                ok_ = true;
            }
            if (!ok_) {
                _si.setKo();
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
            _si.setKo();
            infos_.getStringInfo().getChars().add(curChar_);
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
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

    private static NumberInfosOutput processNb(KeyWords _key, int _start, int _max, String _string, boolean _seenDot) {
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
        String hexEnd_ = _key.getKeyWordNbHexEnd();
        String binPre_ = _key.getKeyWordNbBin();
        String decExp_ = _key.getKeyWordNbExpDec();
        String binExp_ = _key.getKeyWordNbExpBin();
        if (_seenDot) {
            nbInfos_.setSuffix(DOUBLE);
            decPart_.append(startChar_);
            if (startChar_ == '.') {
                nbInfos_.setError(true);
            }
            if (Character.isWhitespace(startChar_)) {
                nbInfos_.setError(true);
            }
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
                            output_.setNextIndex(_max);
                            nbInfos_.setError(true);
                            return output_;
                        }
                        startChar_ = _string.charAt(start_);
                    } else {
                        if (sub_.startsWith(binPre_)) {
                            base_ = 2;
                            start_+=binPre_.length();
                            start_++;
                            if (start_ >= _max) {
                                output_.setNextIndex(_max);
                                nbInfos_.setError(true);
                                return output_;
                            }
                            startChar_ = _string.charAt(start_);
                        } else {
                            if (StringExpUtil.isDigit(_string.charAt(start_ + 1))){
                                base_ = 8;
                                start_++;
                                startChar_ = _string.charAt(start_);
                            }
                        }
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
            if (!StringExpUtil.isTypeLeafChar(current_)) {
                int n_ = StringExpUtil.nextPrintChar(j_ + 1, _max, _string);
                if (current_ == DOT_VAR) {
                    if (_seenDot) {
                        nbInfos_.setError(true);
                        decPart_.append(current_);
                        j_++;
                        continue;
                    }
                    if (nbInfos_.isError()) {
                        intPart_.append(current_);
                        j_++;
                        continue;
                    }
                    if (n_ == -1) {
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
                    if (isWhite(j_ + 1, _max, _string) && isDigitOrDot(_string, n_)) {
                        nbInfos_.setError(true);
                        intPart_.append(current_);
                        j_++;
                        continue;
                    }
                    if (_string.charAt(n_) == DOT_VAR) {
                        nbInfos_.setError(true);
                        intPart_.append(current_);
                        j_++;
                        continue;
                    }
                    dot_ = true;
                }
                if (isWhite(j_, _max, _string)) {
                    if (nbInfos_.isError()) {
                        append(_seenDot, intPart_, decPart_, current_);
                        j_++;
                        continue;
                    }
                    if (isDigitOrDot(_string,n_)) {
                        nbInfos_.setError(true);
                        intPart_.append(current_);
                        j_++;
                        continue;
                    }
                }
                break;
            }
            String sub_ = _string.substring(j_);
            int off_ = getExpLength(base_, decExp_, binExp_);
            if (isExp(base_, decExp_, binExp_, sub_)) {
                if (nbInfos_.isError()) {
                    append(_seenDot, intPart_, decPart_, current_);
                    j_++;
                    continue;
                }
                if (isWhite(j_ + off_,_max,_string)) {
                    nbInfos_.setError(true);
                    append(_seenDot, intPart_, decPart_, current_);
                    j_++;
                    continue;
                }
                nbInfos_.setSuffix(DOUBLE);
                iExp_ = j_ + off_ - 1;
                j_ += off_ - 1;
                exp_ = true;
                break;
            }
            if (isNonNbPart(current_, base_)) {
                j_ = incrSep(j_,base_,sub_,hexEnd_);
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
            if (isExp(base_, decExp_, binExp_, sub_)) {
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
                processDotSuffix(_key,base_,sub_,hexEnd_, _string, output_, nbInfos_,j_, _max);
                return output_;
            }
            if (j_ >= _max) {
                output_.setNextIndex(j_);
                return output_;
            }
            sub_ = _string.substring(j_);
            if (isExp(base_, decExp_, binExp_, sub_)) {
                j_+=off_-1;
                processExp(_key, j_, _max, _string, output_);
                return output_;
            }
            processDotSuffix(_key,base_,sub_,hexEnd_, _string, output_, nbInfos_, j_, _max);
            return output_;
        }
        if (exp_) {
            //_string.charAt(iExp_) == EXP
            processExp(_key, iExp_, _max, _string, output_);
            return output_;
        }
        output_.setNextIndex(j_);
        return output_;
    }
    private static int incrSep(int _j, int _base, String _sub, String _hexPre) {
        int j_ = _j;
        if (_base != 10 && _sub.startsWith(_hexPre)) {
            j_ += _hexPre.length();
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
        if (suff_ != null) {
            j_ +=suff_.length();
            char su_ = _key.getSuffixes().getVal(suff_);
            _nbInfos.setSuffix(su_);
        }
        j_ = nextIndex(j_, _max, _string, _output, _nbInfos.getDecimalPart());
        _output.setNextIndex(j_);
    }

    private static boolean processSuffix(KeyWords _key, String _string, NumberInfosOutput _output, NumberInfos _nbInfos, int _j) {
        String suff_ = _key.getNbKeyWord(_string, _j);
        if (suff_ == null) {
            return false;
        }
        char ch_ = _key.getSuffixes().getVal(suff_);
        _nbInfos.setSuffix(ch_);
        int n_ = _j + suff_.length();
        boolean ok_ = processedCorrectOrContinue(_string, n_);
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
            ok_ = StringExpUtil.isDigit(_char);
        } else if (_base == 16) {
            if (StringExpUtil.isDigit(_char)) {
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
            _output.getInfos().setError(true);
            _output.setNextIndex(_max);
            return;
        }
        StringBuilder exp_ = _output.getInfos().getExponentialPart();
        int j_ = _start;
        j_++;
        if (_string.charAt(j_) == MINUS_CHAR || _string.charAt(j_) == PLUS_CHAR) {
            if (j_ + 1 >= _max) {
                _output.getInfos().setError(true);
                _output.setNextIndex(j_);
                return;
            }
            if (!StringExpUtil.isDigit(_string.charAt(j_+1))) {
                _output.getInfos().setError(true);
                _output.setNextIndex(j_);
                return;
            }
            exp_.append(_string.charAt(j_));
            j_++;
        } else {
            if (!StringExpUtil.isDigit(_string.charAt(j_))) {
                _output.getInfos().setError(true);
                int n_ = StringExpUtil.nextPrintChar(j_+1, _max, _string);
                if (n_ < 0) {
                    _output.setNextIndex(j_);
                    return;
                }
                if (!StringExpUtil.isTypeLeafChar(_string.charAt(n_))) {
                    _output.setNextIndex(j_);
                    return;
                }
            }
        }
        while (j_ < _max) {
            if (isNonNbPart(_string.charAt(j_),10)) {
                break;
            }
            exp_.append(_string.charAt(j_));
            j_++;
        }
        int n_ = StringExpUtil.nextPrintChar(j_, _max, _string);
        if (n_ > -1 && _string.charAt(n_) == DOT_VAR) {
            _output.getInfos().setError(true);
            j_ = n_;
        }
        if (j_ < _max && Character.isLetter(_string.charAt(j_))) {
            String keyWord_ = _key.getNbKeyWord(_string, j_);
            if (keyWord_ != null) {
                char suf_ = _key.getSuffixes().getVal(keyWord_);
                _output.getInfos().setSuffix(suf_);
                j_+=keyWord_.length();
            }
        }
        j_ = nextIndex(j_, _max, _string, _output, exp_);
        _output.setNextIndex(j_);
    }
    private static int nextIndex(int _j, int _max, String _string, NumberInfosOutput _output, StringBuilder _str) {
        int j_ = _j;
        if (j_ < _max && isDotDollarWordChar(_string, j_)) {
            _output.getInfos().setError(true);
            _str.append(_string.charAt(j_));
            j_++;
            while (j_ < _max) {
                if (!isDotDollarWordChar(_string,j_)) {
                    break;
                }
                _str.append(_string.charAt(j_));
                j_++;
            }
        }
        return j_;
    }

    private static boolean isDotDollarWordChar(String _string, int j_) {
        return _string.charAt(j_) == DOT_VAR || StringList.isDollarWordChar(_string.charAt(j_));
    }

    private static boolean processedCorrectOrContinue(String _string, int _j) {
        String nextPartAf_ = _string.substring(_j).trim();
        return !isNotCorrectNbEnd(nextPartAf_);
    }

    private static boolean isNotCorrectNbEnd(String _next) {
        if (_next.isEmpty()) {
            return false;
        }
        char n_ = _next.charAt(0);
        return n_ == DOT_VAR;
    }
    public static OperationsSequence getOperationsSequence(int _offset, String _string,
            ContextEl _conf, Delimiters _d) {
        OperationsSequence seq_ = tryGetSequence(_offset, _string, _conf, _d);
        if (seq_ != null) {
            return seq_;
        }
        ExpPartDelimiters del_ = new ExpPartDelimiters(_string);
        int lastPrintChar_ = del_.getLastPrintIndex();
        int len_ = lastPrintChar_ + 1;
        AfterUnaryParts af_ = new AfterUnaryParts(_offset,_string,del_,_d);
        int i_ = af_.getIndex();
        af_.setInstance(_string,_conf);
        while (i_ < len_) {
            af_.setState(_offset,_string,_d);
            i_ = af_.getIndex();
        }
        Ints laterIndexesDouble_ = af_.getLaterIndexesDouble();
        int prio_ = af_.getPrio();
        IntTreeMap<String> operators_;
        operators_ = af_.getOperators();
        boolean leftParFirstOperator_ = af_.isLeftParFirstOperator();
        boolean is_ = af_.isInstOf();
        String fctName_ = af_.getFctName();
        boolean instance_ = af_.isInstance();
        OperationsSequence op_ = new OperationsSequence();
        op_.setPriority(prio_);
        op_.setOperators(operators_);
        op_.setLeftParFirstOperator(leftParFirstOperator_);
        op_.setFctName(fctName_);
        op_.setErrorDot(af_.isErrorDot());
        op_.setupValues(_string, is_, instance_, laterIndexesDouble_);
        String extracted_ = af_.getExtracted();
        op_.setExtractType(extracted_);
        CustList<PartOffset> partsOffs_ = af_.getPartsOffs();
        op_.setPartOffsets(partsOffs_);
        op_.setDelimiter(_d);
        return op_;
    }

    private static OperationsSequence tryGetSequence(int _offset, String _string,
            ContextEl _conf, Delimiters _d) {
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
        String keyWordParent_ = keyWords_.getKeyWordParent();
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
        if (StringList.quickEq(sub_, keyWordParent_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.PARENT_KEY_WORD);
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
                op_.setStrInfo(info_);
                info_.setFound(_string);
                op_.setValue(new String(str_), firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            if (_string.charAt(firstPrintChar_) == DELIMITER_STRING) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.STRING);
                op_.setOperators(new IntTreeMap< String>());
                op_.setStrInfo(info_);
                info_.setFound(_string);
                op_.setValue(new String(str_), firstPrintChar_);
                op_.setDelimiter(_d);
                return op_;
            }
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.STRING);
            op_.setOperators(new IntTreeMap< String>());
            op_.setStrInfo(info_);
            info_.setFound(_string);
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

    private static int indexAfterPossibleCast(ContextEl _conf, String _string, int _from, Delimiters _d) {
        int indexParRight_ = _string.indexOf(PAR_RIGHT, _from +1);
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
        int next_ = StringExpUtil.nextPrintChar(indexParRight_+1,_string.length(),_string);
        for (String s: StringList.wrapStringArray("+=","-=",
                "*=","/=","%=",
                "^=","&=","|=",
                "||","&&","?",
                "<",">",",",
                "!=","=",")","]","}")) {
            if (_string.startsWith(s,next_)) {
                return _from;
            }
        }
        if (_string.startsWith(".",next_)) {
            int n_ = StringExpUtil.nextPrintChar(next_ + 1, _string.length(), _string);
            if (!isDigitOrDot(_string,n_)) {
                return _from;
            }
        }
        CustList<PartOffset> curr_ = _conf.getAnalyzing().getCurrentParts();
        String typeOut_ = ResolvingImportTypes.resolveCorrectTypeWithoutErrors(_conf,_from + 1, sub_, true, curr_);
        if (!typeOut_.isEmpty()) {
            _d.getDelCast().add(_from);
            _d.getDelCast().add(indexParRight_);
            _d.getDelCastExtract().add(typeOut_);
            _d.getCastParts().add(new CustList<PartOffset>(curr_));
            return indexParRight_ + 1;
        }
        curr_.clear();
        return _from;
    }

}
