package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.AnonymousResult;
import code.expressionlanguage.analyze.blocks.FieldBlock;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.analyze.files.SegmentStringPart;
import code.expressionlanguage.analyze.files.SegmentStringType;
import code.expressionlanguage.analyze.opers.MethodOperation;
import code.expressionlanguage.analyze.types.AnaPartTypeUtil;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.maths.litteralcom.StrTypes;
import code.util.CharList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;


public final class ElResolver {

    public static final int CONST_PRIO = 0;
    public static final int NAME_PRIO = 1;
    public static final int DECL_PRIO = 2;
    public static final int AFF_PRIO = 3;
    public static final int TERNARY_PRIO = 4;
    public static final int NULL_SAFE_PRIO = 5;
    public static final int OR_PRIO = 6;
    public static final int AND_PRIO = 7;
    public static final int RANGE = 8;
    public static final int BIT_OR_PRIO = 9;
    public static final int BIT_XOR_PRIO = 10;
    public static final int BIT_AND_PRIO = 11;
    public static final int EQ_PRIO = 12;
    public static final int CMP_PRIO = 13;
    public static final int SHIFT_PRIO = 14;
    public static final int ADD_PRIO = 15;
    public static final int MULT_PRIO = 16;
    public static final int UNARY_PRIO = 17;
    public static final int POST_INCR_PRIO = 18;
    public static final int FCT_OPER_PRIO = 19;
    static final byte UNICODE_SIZE = 4;

    static final String EMPTY_STRING = "";
    static final char LINE_RETURN = '\n';
    static final char FORM_FEED = '\f';
    static final char BOUND = '\b';
    static final char LINE_FEED = '\r';
    static final char SPACE = ' ';
    static final char TAB = '\t';
    static final char ESCAPE_META_CHAR = '\\';
    static final char DELIMITER_CHAR = 39;
    static final char DELIMITER_STRING = 34;
    static final char ARR_LEFT = '[';
    static final char ARR_RIGHT = ']';
    static final char ANN_ARR_LEFT = '{';
    static final char ANN_ARR_RIGHT = '}';
    static final char PAR_LEFT = '(';
    static final char PAR_RIGHT = ')';
    static final char SEP_ARG = ',';
    static final char DOT_VAR = '.';
    static final char DOUBLE = 'd';

    static final char INTEGER = 'i';
    static final char NB_INTERN_SP = '_';

    static final char ANNOT = '@';

    static final char MIN_ENCODE_DIGIT = '0';

    static final String ARR = "[";
    static final String ARR_END = "]";

    static final char NEG_BOOL_CHAR = '!';

    static final char MULT_CHAR = '*';

    static final char DIV_CHAR = '/';

    static final char MOD_CHAR = '%';

    static final char PLUS_CHAR = '+';

    static final char MINUS_CHAR = '-';

    static final char LOWER_CHAR = '<';

    static final char GREATER_CHAR = '>';

    static final char EQ_CHAR = '=';

    static final char AND_CHAR = '&';

    static final char OR_CHAR = '|';
    static final char XOR_CHAR = '^';
    static final char NEG_BOOL = '~';
    static final char BEGIN_TERNARY = '?';
    static final char DELIMITER_TEXT = '`';
    static final char END_TERNARY = ':';

    private ElResolver() {
    }

    public static Delimiters checkSyntaxDelimiters(String _string, int _minIndex, AnalyzedPageEl _page) {
        Delimiters d_ = new Delimiters();
        d_.setPartOfString(true);
        FullFieldRetriever ret_ = new FullFieldRetriever(d_, _string, _page, _minIndex);
        return commonCheck(_string, _minIndex, ret_, d_, _page);
    }

    public static Delimiters checkSyntax(String _string, int _elOffest, AnalyzedPageEl _page) {
        Delimiters d_ = new Delimiters();
        FullFieldRetriever ret_ = new FullFieldRetriever(d_, _string, _page, _elOffest);
        return commonCheck(_string, _elOffest, ret_, d_, _page);
    }

    static Delimiters checkSyntaxQuick(String _string, AnalyzedPageEl _page) {
        Delimiters d_ = new Delimiters();
        QuickFieldRetriever ret_ = new QuickFieldRetriever(d_);
        return commonCheck(_string, 0, ret_, d_, _page);
    }
    private static Delimiters commonCheck(String _string, int _minIndex, FieldRetriever _ret, Delimiters _d, AnalyzedPageEl _page) {
        boolean partOfString_ = _d.isPartOfString();

        StackOperators parsBrackets_;
        parsBrackets_ = new StackOperators();
        ResultAfterOperators resOpers_ = new ResultAfterOperators();
        resOpers_.setParsBrackets(parsBrackets_);
        resOpers_.setPartOfString(partOfString_);

        int len_ = _string.length();
        int i_ = _minIndex;
        while (i_ < len_) {
            if (!StringUtil.isWhitespace(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        int beginIndex_ = i_;
        _page.getAnonymousResults().clear();
        if (i_ >= len_) {
            _d.setBadOffset(i_);
            return _d;
        }
        KeyWords keyWords_ = _page.getKeyWords();
        i_ = _minIndex;
        ResultAfterInstKeyWord resKeyWords_ = new ResultAfterInstKeyWord();
        resKeyWords_.setNextIndex(i_);
        resOpers_.setDoubleDotted(resKeyWords_);
        while (i_ < len_) {

            int until_ = i_;
            for (SegmentStringPart s: _page.getCurrentParts()) {
                if (s.getBegin() == i_) {
                    strParts(_string, _d, resOpers_, keyWords_, s);
                    until_ = s.getEnd();
                    break;
                }
            }
            if (until_ > i_) {
                i_ = until_;
                continue;
            }
            char curChar_ = _string.charAt(i_);
            resOpers_.getDoubleDotted().setNextIndex(i_);
            if (StringExpUtil.isTypeLeafChar(curChar_)) {
                procWord(resOpers_, _string, _ret, _d, _page);
            } else {
                processOperators(beginIndex_, _string, _d, _ret,resOpers_, _page);
            }
            if (_d.getBadOffset() >= 0) {
                return _d;
            }
            i_ = resOpers_.getDoubleDotted().getNextIndex();
        }
        if (resOpers_.isConstTextString()) {
            _d.setBadOffset(i_);
            return _d;
        }
        if (resOpers_.isConstTextChar()) {
            _d.setBadOffset(i_);
            return _d;
        }
        if (resOpers_.isConstString()) {
            _d.setBadOffset(i_);
            return _d;
        }
        if (resOpers_.isConstChar()) {
            _d.setBadOffset(i_);
            return _d;
        }
        if (resOpers_.isConstText()) {
            _d.setBadOffset(i_);
            return _d;
        }
        if (!parsBrackets_.isEmpty()) {
            _d.setBadOffset(i_);
            return _d;
        }
        if (!resOpers_.isPartOfString()) {
            return _d;
        }
        _d.setBadOffset(i_);
        return _d;
    }

    private static void strParts(String _string, Delimiters _d, ResultAfterOperators _resOpers, KeyWords _keyWords, SegmentStringPart _stPart) {
        int len_ = _string.length();
        int i_ = _stPart.getBegin();
        TextBlockInfo si_ = new TextBlockInfo();
        TextBlockInfo txt_ = new TextBlockInfo();
        boolean escapedMeta_ = false;
        int unicode_ = 0;
        if (_stPart.getStrType() == SegmentStringType.CHAR) {
            int end_ = _stPart.getEnd();
            int st_ = _stPart.getBegin();
            _resOpers.setConstChar(true);
            _resOpers.setNbChars(0);
            _d.getDelStringsChars().add(st_);
            st_++;
            while (st_ < end_) {
                IndexUnicodeEscape unic_ = new IndexUnicodeEscape();
                unic_.setIndex(st_);
                unic_.setEscape(escapedMeta_);
                unic_.setNbChars(_resOpers.getNbChars());
                unic_.setPart(true);
                unic_.setUnicode(unicode_);
                unic_.setStringInfo(si_);
                IndexUnicodeEscape res_ = processStrings(_keyWords, _string, len_, si_,unic_, DELIMITER_CHAR);
                int index_ = res_.getIndex();
                if (!res_.isPart()) {
                    _d.getStringInfo().add(si_);
                    _d.getDelStringsChars().add(st_);
                    si_ = new TextBlockInfo();
                    _resOpers.setConstChar(false);
                    st_++;
                    _d.setEnabledOp(true);
                    continue;
                }
                st_ = index_;
                escapedMeta_ = res_.isEscape();
                _resOpers.setNbChars(res_.getNbChars());
                unicode_ = res_.getUnicode();
            }
        }
        if (_stPart.getStrType() == SegmentStringType.STRING) {
            int end_ = _stPart.getEnd();
            _d.getDelStringsChars().add(i_);
            int st_ = _stPart.getBegin();
            _resOpers.setConstString(true);
            st_++;
            while (st_ < end_) {
                IndexUnicodeEscape unic_ = new IndexUnicodeEscape();
                unic_.setStringInfo(si_);
                unic_.setIndex(st_);
                unic_.setEscape(escapedMeta_);
                unic_.setNbChars(_resOpers.getNbChars());
                unic_.setPart(true);
                unic_.setUnicode(unicode_);
                IndexUnicodeEscape res_ = processStrings(_keyWords, _string, len_, si_, unic_, DELIMITER_STRING);
                int index_ = res_.getIndex();
                if (!res_.isPart()) {
                    _d.getStringInfo().add(si_);
                    _d.getDelStringsChars().add(st_);
                    si_ = new TextBlockInfo();
                    _resOpers.setConstString(false);
                    st_++;
                    _d.setEnabledOp(true);
                    continue;
                }
                st_ = index_;
                escapedMeta_ = res_.isEscape();
                _resOpers.setNbChars(res_.getNbChars());
                unicode_ = res_.getUnicode();
            }

        }
        if (_stPart.getStrType() == SegmentStringType.TEXT) {
            int end_ = _stPart.getEnd();
            int st_ = _stPart.getBegin();
            _resOpers.setConstText(true);
            _d.getDelStringsChars().add(i_);
            st_++;
            while (st_ < end_) {
                IndexUnicodeEscape unic_ = new IndexUnicodeEscape();
                unic_.setStringInfo(si_);
                unic_.setIndex(st_);
                unic_.setEscape(escapedMeta_);
                unic_.setNbChars(_resOpers.getNbChars());
                unic_.setPart(true);
                unic_.setUnicode(unicode_);
                IndexUnicodeEscape res_ = processStrings(_keyWords, _string, len_, si_, unic_, DELIMITER_TEXT);
                int index_ = res_.getIndex();
                if (!res_.isPart()) {
                    _d.getStringInfo().add(si_);
                    _d.getDelStringsChars().add(st_);
                    si_ = new TextBlockInfo();
                    _resOpers.setConstText(false);
                    st_++;
                    _d.setEnabledOp(true);
                    continue;
                }
                st_ = index_;
                escapedMeta_ = res_.isEscape();
                _resOpers.setNbChars(res_.getNbChars());
                unicode_ = res_.getUnicode();
            }

        }
        if (_stPart.getStrType() == SegmentStringType.TEXT_STRING) {
            int end_ = _stPart.getEnd();
            int st_ = _stPart.getBegin();
            _resOpers.setConstTextString(true);
            _d.getDelTextBlocks().add(st_);
            st_+=3;
            while (st_ < end_) {
                IndexUnicodeEscape unic_ = new IndexUnicodeEscape();
                unic_.setIndex(st_);
                unic_.setEscape(escapedMeta_);
                unic_.setNbChars(_resOpers.getNbChars());
                unic_.setPart(true);
                unic_.setUnicode(unicode_);
                unic_.setTextInfo(txt_);
                IndexUnicodeEscape res_ = processTextBlocks(_keyWords, _string, len_, txt_,unic_, DELIMITER_STRING);
                int index_ = res_.getIndex();
                if (!res_.isPart()) {
                    _d.getTextInfo().add(txt_);
                    _d.getDelTextBlocks().add(st_+2);
                    txt_ = new TextBlockInfo();
                    _resOpers.setConstTextString(false);
                    st_+=3;
                    _d.setEnabledOp(true);
                    continue;
                }
                st_ = index_;
                escapedMeta_ = res_.isEscape();
                _resOpers.setNbChars(res_.getNbChars());
                unicode_ = res_.getUnicode();
            }

        }
        if (_stPart.getStrType() == SegmentStringType.TEXT_CHAR) {
            int end_ = _stPart.getEnd();
            int st_ = _stPart.getBegin();
            _resOpers.setConstTextChar(true);
            _d.getDelTextBlocks().add(st_);
            st_+=3;
            while (st_ < end_) {
                IndexUnicodeEscape unic_ = new IndexUnicodeEscape();
                unic_.setIndex(st_);
                unic_.setEscape(escapedMeta_);
                unic_.setNbChars(_resOpers.getNbChars());
                unic_.setPart(true);
                unic_.setUnicode(unicode_);
                unic_.setTextInfo(txt_);
                IndexUnicodeEscape res_ = processTextBlocks(_keyWords, _string, len_, txt_,unic_, DELIMITER_CHAR);
                int index_ = res_.getIndex();
                if (!res_.isPart()) {
                    _d.getTextInfo().add(txt_);
                    _d.getDelTextBlocks().add(st_+2);
                    txt_ = new TextBlockInfo();
                    _resOpers.setConstTextChar(false);
                    st_+=3;
                    _d.setEnabledOp(true);
                    continue;
                }
                st_ = index_;
                escapedMeta_ = res_.isEscape();
                _resOpers.setNbChars(res_.getNbChars());
                unicode_ = res_.getUnicode();
            }
        }
    }

    private static void procWord(ResultAfterOperators _resOpers, String _string, FieldRetriever _ret, Delimiters _d, AnalyzedPageEl _page) {
        int i_ = _resOpers.getDoubleDotted().getNextIndex();
        if (_page.getCurrentBlock() instanceof InfoBlock
                && _resOpers.getParsBrackets().isEmptyStackSymChars()) {
            int len_ = _string.length();
            int bk_ = StringExpUtil.getBackPrintChar(_string, i_);
            if (bk_ < 0 || StringExpUtil.nextCharIs(_string, bk_, len_, ',')) {
                int j_ = ElResolverCommon.getWord(_string, len_, i_);
                int n_ = StringExpUtil.nextPrintChar(j_, len_, _string);
                if (n_ < 0
                        || StringExpUtil.nextCharIs(_string, n_, len_, '=') && !StringExpUtil.nextCharIs(_string, n_ + 1, len_, '=')
                        || StringExpUtil.nextCharIs(_string, n_, len_, ',')) {
                    String word_ = _string.substring(i_, j_);
                    VariableInfo info_ = new VariableInfo();
                    ConstType type_;
                    type_ = ConstType.CUST_FIELD;
                    info_.setKind(type_);
                    info_.declaringField(_resOpers.getFieldNumber(),(InfoBlock)_page.getCurrentBlock());
                    info_.setAffect(StringExpUtil.nextCharIs(_string, n_, len_, '='));
                    info_.setFirstChar(i_);
                    info_.setLastChar(j_);
                    info_.setName(word_);
                    _d.getVariables().add(info_);
                    _resOpers.setFieldNumber(_resOpers.getFieldNumber()+1);
                    _d.setEnabledOp(true);
                    _resOpers.getDoubleDotted().setNextIndex(j_);
                    return;
                }
            }
        }
        char curChar_ = _string.charAt(i_);
        ResultAfterInstKeyWord kw_ = _resOpers.getDoubleDotted();
        kw_.setNextIndex(i_);
        processAfterInstuctionKeyWordCast(_string, _d, kw_, _page);
        if (_d.getBadOffset() >= 0) {
            return;
        }
        int nextInd_ = kw_.getNextIndex();
        if (nextInd_ > i_) {
            return;
        }
        processAfterInstuctionKeyWord(_string, _d, kw_, _resOpers, _page);
        if (_d.getBadOffset() >= 0) {
            return;
        }
        nextInd_ = kw_.getNextIndex();
        if (nextInd_ > i_) {
            _d.setEnabledOp(true);
            return;
        }
        processWords(_string,curChar_, _d, _ret, kw_, _page);
    }

    private static void processAfterInstuctionKeyWordCast(String _string, Delimiters _d, ResultAfterInstKeyWord _out, AnalyzedPageEl _page) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordCast_ = keyWords_.getKeyWordCast();
        String keyWordExplicit_ = keyWords_.getKeyWordExplicit();
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
            _d.getCastParts().add(new AnaResultPartType());
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
        }
    }
    private static void processAfterInstuctionKeyWord(String _string, Delimiters _d, ResultAfterInstKeyWord _out, ResultAfterOperators _opers, AnalyzedPageEl _page) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        KeyWords keyWords_ = _page.getKeyWords();
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
        String keyWordNull_ = keyWords_.getKeyWordNull();
        String keyWordNew_ = keyWords_.getKeyWordNew();
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
        String keyWordDefault_ = keyWords_.getKeyWordDefault();
        String keyWordDefaultValue_ = keyWords_.getKeyWordDefaultValue();
        String keyWordOperator_ = keyWords_.getKeyWordOperator();
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
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordNew_)) {
            DefaultProcessKeyWord.processKeyWordNew(_string,i_,_d,_out, _page);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordInstanceof_)) {
            int next_ = i_ + keyWordInstanceof_.length();
            next_ = ElResolverCommon.incrInstanceOf(_string, len_, next_);
            _d.getAllowedOperatorsIndexes().add(i_);
            _d.getDelInstanceof().add(i_);
            _d.getDelInstanceof().add(next_);
            i_ = next_;
            _out.setNextIndex(i_);
            return;
        }
        StackDelimiters stack_ = _d.getStack();
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
                if (!StringUtil.isWhitespace(_string.charAt(afterStatic_))) {
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
                if (!StringUtil.isWhitespace(_string.charAt(afterStatic_))) {
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
                _d.getStaticAccessTypes().add(null);
                _d.getStaticParts().add(new AnaResultPartType());
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
                if (!StringUtil.isWhitespace(_string.charAt(afterSuper_))) {
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
                    stack_.getCallings().add(afterSuper_);
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
                if (StringUtil.isWhitespace(_string.charAt(afterSuper_))) {
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
                    stack_.getCallings().add(afterSuper_);
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
                if (!StringUtil.isWhitespace(_string.charAt(afterClassChoice_))) {
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
                if (!StringUtil.isWhitespace(loc_)) {
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
                stack_.getCallings().add(afterClassChoice_);
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
                if (!StringUtil.isWhitespace(_string.charAt(afterClassChoice_))) {
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
                if (!StringUtil.isWhitespace(loc_)) {
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
                stack_.getCallings().add(afterClassChoice_);
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
                if (!StringUtil.isWhitespace(_string.charAt(afterClassChoice_))) {
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
                if (!StringUtil.isWhitespace(loc_)) {
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
                if (!StringUtil.isWhitespace(loc_)) {
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
                stack_.getCallings().add(afterClassChoice_);
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
                if (!StringUtil.isWhitespace(_string.charAt(afterClassChoice_))) {
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
                if (!StringUtil.isWhitespace(loc_)) {
                    break;
                }
                afterClassChoice_++;
            }
            if (afterClassChoice_ >= len_) {
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            char loc_ = _string.charAt(afterClassChoice_);
            if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordOperator_)) {
                if (loc_ == '=' || loc_ == ':') {
                    int afterAff_ = StringExpUtil.nextPrintChar(afterClassChoice_ + 1, len_, _string);
                    int nextKey_ = afterAff_;
                    if (StringExpUtil.startsWithKeyWord(_string,afterAff_,keyWordCast_)) {
                        int impl_ = afterAff_ + keyWordCast_.length();
                        int nextLeftPar_ = StringExpUtil.nextPrintCharIs(impl_,len_,_string,PAR_LEFT);
                        if (nextLeftPar_ < 0) {
                            _d.setBadOffset(impl_+1);
                            return;
                        }
                        int nextRightPar_ = _string.indexOf(PAR_RIGHT, nextLeftPar_);
                        if (nextRightPar_ < 0) {
                            _d.setBadOffset(nextLeftPar_+1);
                            return;
                        }
                        nextKey_ = StringExpUtil.nextPrintChar(nextRightPar_ + 1, len_, _string);
                    }
                    if (StringExpUtil.startsWithKeyWord(_string,nextKey_,keyWordExplicit_)) {
                        int test_ = nextKey_ + keyWordExplicit_.length();
                        int nextLeftPar_ = StringExpUtil.nextPrintCharIs(test_,len_,_string,PAR_LEFT);
                        if (nextLeftPar_ < 0) {
                            _d.setBadOffset(test_+1);
                            return;
                        }
                        int nextRightPar_ = _string.indexOf(PAR_RIGHT, nextLeftPar_);
                        if (nextRightPar_ < 0) {
                            _d.setBadOffset(nextLeftPar_+1);
                            return;
                        }
                        nextKey_ = StringExpUtil.nextPrintChar(nextRightPar_ + 1, len_, _string);
                    }
                    if (StringExpUtil.nextPrintCharIs(nextKey_,len_,_string,PAR_LEFT) < 0) {
                        _d.setBadOffset(len_);
                        return;
                    }
                    stack_.getCallings().add(nextKey_);
                    i_ = nextKey_;
                    _out.setNextIndex(i_);
                    return;
                }
            }
            if (loc_ != PAR_LEFT) {
                _d.setBadOffset(afterClassChoice_);
                return;
            }
            stack_.getCallings().add(afterClassChoice_);
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
                if (!StringUtil.isWhitespace(_string.charAt(afterSuper_))) {
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
            if (afterSuper_ < len_ && _string.charAt(afterSuper_) == PAR_LEFT) {
                stack_.getCallings().add(afterSuper_);
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
                if (StringUtil.isWhitespace(_string.charAt(afterSuper_))) {
                    afterSuper_++;
                    continue;
                }
                if (!StringExpUtil.isTypeLeafChar(_string.charAt(afterSuper_))) {
                    break;
                }
                afterSuper_++;
            }
            if (followedByParLeft(_string, len_, afterSuper_)) {
                stack_.getCallings().add(afterSuper_);
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
            afterSuper_ = nextAfterWhite(afterSuper_,_string,len_);
            if (followedByParLeft(_string, len_, afterSuper_)) {
                stack_.getCallings().add(afterSuper_);
            }
            i_ = afterSuper_;
            _out.setNextIndex(i_);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordValueOf_)) {
            int afterSuper_ = i_ + keyWordValueOf_.length();
            afterSuper_ = nextAfterWhite(afterSuper_,_string,len_);
            if (followedByParLeft(_string, len_, afterSuper_)) {

                int indexComma_ = _string.indexOf(SEP_ARG, afterSuper_);
                int min_;
                if (indexComma_ < 0) {
                    min_ = _string.indexOf(PAR_RIGHT,afterSuper_);
                } else {
                    min_ = indexComma_;
                }
                if (min_ >= 0) {
                    stack_.getCallings().add(afterSuper_);
                    StackOperators parsBrackets_;
                    parsBrackets_ = _opers.getParsBrackets();
                    parsBrackets_.addEntry(afterSuper_, PAR_LEFT);
                    _d.getAllowedOperatorsIndexes().add(afterSuper_);
                    _out.setNextIndex(min_);
                    return;
                }
            }
            _d.setBadOffset(Math.min(afterSuper_,len_));
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordValues_)) {
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
            _d.getDelValues().add(i_);
            _d.getDelValues().add(indexParRight_);
            i_ = indexParRight_ + 1;
            _out.setNextIndex(i_);
            return;
        }
        boolean foundValue_ = false;
        for (String s: StringUtil.wrapStringArray(keyWordTrue_,keyWordFalse_,keyWordNull_,keyWordParent_)) {
            if (StringExpUtil.startsWithKeyWord(_string,i_, s)) {
                int afterSuper_ = i_ + s.length();
                while (afterSuper_ < len_) {
                    char ch_ = _string.charAt(afterSuper_);
                    if (!StringUtil.isWhitespace(ch_)) {
                        if (ch_ == PAR_LEFT) {
                            stack_.getCallings().add(afterSuper_);
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
        for (String s: StringUtil.wrapStringArray(keyWordFirstopt_,keyWordBool_,keyWordDefault_)) {
            if (StringExpUtil.startsWithKeyWord(_string,i_, s)) {
                int index_ = ElResolverCommon.processPredefinedMethod(_string, i_, s);
                if (index_ < 0) {
                    _d.setBadOffset(-index_);
                    return;
                }
                foundValue_ = true;
                stack_.getCallings().add(index_);
                i_ = index_;
                break;
            }
        }
        if (foundValue_) {
            _out.setNextIndex(i_);
            return;
        }
        DefaultProcessKeyWord.processInternKeyWord(_page,_string, i_, _d, _out);
    }

    private static boolean followedByParLeft(String _string, int _len, int _afterSuper) {
        return _afterSuper < _len && _string.charAt(_afterSuper) == PAR_LEFT;
    }

    private static int nextAfterWhite(int _i, String _string,int _len) {
        int afterSuper_ = _i;
        while (afterSuper_ < _len) {
            if (!StringUtil.isWhitespace(_string.charAt(afterSuper_))) {
                //_string.charAt(afterSuper_) != EXTERN_CLASS && !foundHat_
                break;
            }
            afterSuper_++;
        }
        return afterSuper_;
    }
    private static void processWords(String _string, char _curChar, Delimiters _d, FieldRetriever _ret, ResultAfterInstKeyWord _out, AnalyzedPageEl _page) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        KeyWords keyWords_ = _page.getKeyWords();
        if (isPossibleDigit(_string,_d) && StringExpUtil.isDigit(_curChar)) {
            NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, i_, len_, _string, false);
            int nextIndex_ = res_.getNextIndex();
            _d.getNbInfos().add(res_.getInfos());
            _d.getDelNumbers().add(i_);
            _d.getDelNumbers().add(nextIndex_);
            _out.setNextIndex(nextIndex_);
            _d.setEnabledOp(true);
            return;
        }
        int beginWord_ = i_;
        i_ = incrAfterWord(beginWord_,_string);
        String word_ = _string.substring(beginWord_, i_);
        int nextPar_ = StringExpUtil.nextPrintCharIs(i_, len_, _string, PAR_LEFT);
        if (nextPar_ > -1) {
            _d.getStack().getCallings().add(nextPar_);
            _out.setNextIndex(i_);
            _d.setEnabledOp(true);
            return;
        }
        int bk_ = StringExpUtil.getBackPrintChar(_string, beginWord_);
        if (StringExpUtil.nextCharIs(_string,bk_,len_,'.')) {
            ConstType type_;
            type_ = ConstType.WORD;
            VariableInfo info_ = new VariableInfo();
            info_.setKind(type_);
            info_.setFirstChar(beginWord_);
            info_.setLastChar(i_);
            info_.setName(word_);
            _d.getVariables().add(info_);
            _out.setNextIndex(i_);
            _d.setEnabledOp(true);
            return;
        }
        for (AnonymousResult r:_page.getCurrentAnonymousResults()) {
            if (r.getIndex() == beginWord_) {
                _page.getAnonymousResults().add(r);
                _out.setNextIndex(r.getNext());
                _d.setEnabledOp(true);
                return;
            }
        }
        int n_ = ElResolverCommon.addNamed(_string, beginWord_, i_, _d.getNamedArgs(),_page);
        if (n_ >= i_) {
            _out.setNextIndex(n_);
            return;
        }
        i_ = _ret.processFieldsStaticAccess(beginWord_,word_,i_);
        _out.setNextIndex(i_);
        _d.setEnabledOp(true);
    }

    public static int incrAfterWord(int _i, String _string) {
        int len_ = _string.length();
        int i_ = _i;
        while (i_ < len_) {
            char locChar_ = _string.charAt(i_);
            if (!StringExpUtil.isTypeLeafChar(locChar_)) {
                break;
            }
            i_++;
        }
        return i_;
    }
    private static void processOperators(int _beginIndex, String _string,
                                         Delimiters _dout, FieldRetriever _ret, ResultAfterOperators _out, AnalyzedPageEl _page) {
        StackOperators parsBrackets_;
        parsBrackets_ = _out.getParsBrackets();

        int len_ = _string.length();
        ResultAfterInstKeyWord doubleDotted_ = _out.getDoubleDotted();
        int i_ = doubleDotted_.getNextIndex();
        KeyWords keyWords_ = _page.getKeyWords();
        char curChar_ = _string.charAt(i_);
        StackDelimiters stack_ = _dout.getStack();
        if (curChar_ == ANNOT) {
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
                if (StringUtil.isWhitespace(locChar_)) {
                    j_++;
                    continue;
                }
                break;
            }
            if (StringExpUtil.nextCharIs(_string,j_,len_,PAR_LEFT)) {
                stack_.getCallings().add(j_);
            } else {
                _dout.getDelSimpleAnnotations().add(i_);
                _dout.getDelSimpleAnnotations().add(last_);
            }
            _dout.setEnabledOp(true);
            i_ = j_;
            doubleDotted_.setNextIndex(i_);
            return;
        }
        if (isPossibleDigit(_string, _dout) && curChar_ == DOT_VAR) {
            int n_ = StringExpUtil.nextPrintChar(i_ + 1, len_, _string);
            if (ElResolverCommon.isDigitOrDot(_string,n_)) {
                NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, i_ + 1, len_, _string, true);
                int nextIndex_ = res_.getNextIndex();
                _dout.getNbInfos().add(res_.getInfos());
                _dout.getDelNumbers().add(i_);
                _dout.getDelNumbers().add(nextIndex_);
                _dout.setEnabledOp(true);
                i_ = nextIndex_;
                doubleDotted_.setNextIndex(i_);
                return;
            }
        }
        if (curChar_ == PAR_LEFT) {
            int j_ = indexAfterPossibleCast(_string, i_, _dout, _ret, _page);
            if (j_ > i_) {
                int indexParRight_ = _string.indexOf(PAR_RIGHT, i_ +1);
                if (j_ >= indexParRight_ + 1) {
                    i_ = j_;
                    doubleDotted_.setNextIndex(i_);
                    return;
                }
                stack_.getCallings().add(i_);
                parsBrackets_.addEntry(i_, curChar_);
                _dout.getAllowedOperatorsIndexes().add(i_);
                i_ = j_;
                doubleDotted_.setNextIndex(i_);
                return;
            }
            for (AnonymousResult r:_page.getCurrentAnonymousResults()) {
                if (r.getIndex() == i_) {
                    _page.getAnonymousResults().add(r);
                    doubleDotted_.setNextIndex(r.getNext());
                    _dout.setEnabledOp(true);
                    return;
                }
            }
            stack_.getCallings().add(i_);
            parsBrackets_.addEntry(i_, curChar_);
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
            parsBrackets_.removeLast();
        }
        if (curChar_ == ANN_ARR_LEFT) {
            for (AnonymousResult r:_page.getCurrentAnonymousResults()) {
                if (r.getIndex() == i_) {
                    _page.getAnonymousResults().add(r);
                    doubleDotted_.setNextIndex(r.getNext());
                    _dout.setEnabledOp(true);
                    return;
                }
            }
            parsBrackets_.addEntry(i_, curChar_);
        }
        if (curChar_ == ANN_ARR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                if (_out.isPartOfString()) {
                    _out.setPartOfString(false);
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
            parsBrackets_.removeLast();
        }
        if (curChar_ == ARR_LEFT) {
            int j_ = i_ + 1;
            SkipArPart s_ = new SkipArPart();
            s_.skip(j_,_string);
            j_ = s_.getIndex();
            boolean skip_ = s_.isSkip();
            if (skip_) {
                _dout.getDimsAddonIndexes().add(i_);
                _dout.getDimsAddonIndexes().add(j_);
                i_ = j_ + 1;
                doubleDotted_.setNextIndex(i_);
                _dout.setEnabledOp(true);
                return;
            }
            parsBrackets_.addEntry(i_, curChar_);
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
            parsBrackets_.removeLast();
        }
        if (curChar_ == BEGIN_TERNARY) {
            boolean ternary_ = ElResolverCommon.isTernary(_string,len_,i_);
            if (ternary_) {
                parsBrackets_.addEntry(i_, curChar_);
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
            parsBrackets_.removeLast();
        }
        if (curChar_ == SEP_ARG && parsBrackets_.isEmptyStackSymChars() && isAcceptCommaInstr(_page)) {
            _dout.setBadOffset(i_);
            return;
        }
        boolean escapeOpers_ = false;
        boolean addOp_ = true;
        boolean andOr_ = false;
        boolean nullSafe_ = false;
        boolean ltGt_ = false;
        boolean unary_ = false;
        if (curChar_ == MULT_CHAR) {
            unary_ = true;
            escapeOpers_ = true;
        }
        if (curChar_ == MOD_CHAR) {
            escapeOpers_ = true;
        }
        if (curChar_ == DIV_CHAR) {
            escapeOpers_ = true;
        }
        if (curChar_ == PLUS_CHAR){
            unary_ = true;
            if (i_ + 1 >= len_ || _string.charAt(i_ + 1) != PLUS_CHAR) {
                escapeOpers_ = true;
            }
            if (_beginIndex == i_) {
                addOp_ = false;
            }
        }
        if (curChar_ == MINUS_CHAR){
            unary_ = true;
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
            unary_ = true;
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
            if (andOr_ && j_ < len_ && _string.charAt(j_) == curChar_) {
                if (j_+1 < len_ && _string.charAt(j_+1) == EQ_CHAR) {
                    j_++;
                } else {
                    addOp_ = false;
                }
            }
            if (nullSafe_ && StringExpUtil.nextCharIs(_string, j_, len_, DOT_VAR)) {
                int n_ = StringExpUtil.nextPrintChar(j_ + 1, len_, _string);
                if (!ElResolverCommon.isDigitOrDot(_string,n_)) {
                    j_++;
                }
            }
            if (nullSafe_ && j_ < len_ && _string.charAt(j_) == curChar_) {
                j_++;
            }
            if (nullSafe_ && j_ < len_ && _string.charAt(j_) == curChar_) {
                j_++;
            }
            if (j_ < len_ && _string.charAt(j_) == EQ_CHAR) {
                j_++;
                unary_ = false;
            }
            if (addOp_ && (_dout.isEnabledOp()||!unary_)) {
                _dout.getAllowedOperatorsIndexes().add(i_);
            }
            _dout.setEnabledOp(false);
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
            _dout.setEnabledOp(true);
            return;
        }
        if (curChar_ == MINUS_CHAR){
            if (addOp_) {
                _dout.getAllowedOperatorsIndexes().add(i_);
            }
            i_++;
            i_++;
            doubleDotted_.setNextIndex(i_);
            _dout.setEnabledOp(true);
            return;
        }
        boolean idOp_ = curChar_ == ANN_ARR_RIGHT;
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
            _dout.setEnabledOp(true);
        }
        i_++;
        doubleDotted_.setNextIndex(i_);
    }

    private static boolean noWideInternDelimiter(String _substring) {
        return noDelSt(_substring) && StringExpUtil.noDel(_substring);
    }

    private static boolean noDelSt(String _substring) {
        return _substring.indexOf(DELIMITER_CHAR) < 0 && _substring.indexOf(DELIMITER_STRING) < 0 && _substring.indexOf(DELIMITER_TEXT) < 0;
    }

    private static boolean isPossibleDigit(String _string, Delimiters _dout) {
        boolean possibleDigit_ = false;
        if (!_dout.getAllowedOperatorsIndexes().isEmpty()) {
            int lastIndex_ = _dout.getAllowedOperatorsIndexes().last();
            if (_string.charAt(lastIndex_) != DOT_VAR) {
                possibleDigit_ = true;
            }
        } else {
            possibleDigit_ = true;
        }
        return possibleDigit_;
    }

    private static boolean isAcceptCommaInstr(AnalyzedPageEl _page) {
        return !_page.isAcceptCommaInstr() && !(_page.getCurrentBlock() instanceof FieldBlock);
    }

    private static IndexUnicodeEscape processStrings(KeyWords _key, String _string, int _max, TextBlockInfo _si, IndexUnicodeEscape _infos, char _delimiter) {
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
            infos_.getStringInfo().appendChar(curChar_);
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
            infos_.getStringInfo().appendChar(curChar_);
            nbChars_++;
            infos_.setNbChars(nbChars_);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (unicode_ > 0) {
            char charToAdd_ = trStr(_key, _si, curChar_);
            infos_.getStringInfo().getBuiltUnicode()[unicode_-1] = charToAdd_;
            if (unicode_ < UNICODE_SIZE) {
                unicode_++;
            } else {
                char[] unicodes_ = infos_.getStringInfo().getBuiltUnicode();
                char builtChar_ = NumParsers.parseCharSixteen(String.valueOf(unicodes_));
                infos_.getStringInfo().appendChar(builtChar_);
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
        if (curChar_ == LINE_RETURN) {
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == _delimiter) {
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            infos_.getStringInfo().appendChar(curChar_);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        String single_ = _key.getEscKeyWord(_string, i_);
        String newLine_ = _key.getKeyWordEscLine();
        String form_ = _key.getKeyWordEscForm();
        String rfeed_ = _key.getKeyWordEscFeed();
        String space_ = _key.getKeyWordEscSpace();
        String tab_ = _key.getKeyWordEscTab();
        String bound_ = _key.getKeyWordEscBound();
        if (single_ != null) {
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            if (StringUtil.quickEq(single_, newLine_)) {
                i_+=newLine_.length();
                infos_.getStringInfo().appendChar(LINE_RETURN);
            } else if (StringUtil.quickEq(single_, form_)) {
                i_+=form_.length();
                infos_.getStringInfo().appendChar(FORM_FEED);
            } else if (StringUtil.quickEq(single_, rfeed_)) {
                i_+=rfeed_.length();
                infos_.getStringInfo().appendChar(LINE_FEED);
            } else if (StringUtil.quickEq(single_, tab_)) {
                i_+=tab_.length();
                infos_.getStringInfo().appendChar(TAB);
            } else if (StringUtil.quickEq(single_, space_)) {
                i_+=tab_.length();
                infos_.getStringInfo().appendChar(SPACE);
            } else {
                i_+=bound_.length();
                infos_.getStringInfo().appendChar(BOUND);
            }
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == ESCAPE_META_CHAR) {
            infos_.getStringInfo().appendChar(ESCAPE_META_CHAR);
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
            infos_.getStringInfo().appendChar(curChar_);
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

    private static char trStr(KeyWords _key, TextBlockInfo _si, char _curChar) {
        int index_ = index(_key, _curChar);
        if (index_ < 0) {
            _si.setKo();
            return _curChar;
        }
        return (char) index_;
    }

    private static char trTx(KeyWords _key, TextBlockInfo _si, char _curChar) {
        int index_ = index(_key, _curChar);
        if (index_ < 0) {
            _si.setKo();
            return _curChar;
        }
        return (char) index_;
    }
    private static int index(KeyWords _key, char _curChar) {
        boolean ok_ = StringExpUtil.isDigit(_curChar);
        if (ok_) {
            return _curChar;
        }
        int min_ = NumParsers.toMinCaseLetter(_curChar);
        int ind_ = _key.getKeyWordNbDig().indexOf(min_);
        if (ind_ >= 0) {
            return (char) (ind_ + 'A');
        }
        return -1;
    }

    private static IndexUnicodeEscape processTextBlocks(KeyWords _key, String _string, int _max, TextBlockInfo _si, IndexUnicodeEscape _infos, char _delimiter) {
        int i_ = _infos.getIndex();
        int nbChars_ = _infos.getNbChars();
        int unicode_ = _infos.getUnicode();
        char curChar_ = _string.charAt(i_);
        boolean escapedMeta_ = _infos.isEscape();
        IndexUnicodeEscape infos_ = new IndexUnicodeEscape();
        infos_.setTextInfo(_infos.getTextInfo());
        infos_.setEscape(escapedMeta_);
        infos_.setIndex(i_);
        infos_.setNbChars(nbChars_);
        infos_.setUnicode(unicode_);
        infos_.setPart(_infos.isPart());
        if (!StringUtil.isWhitespace(curChar_)&&!infos_.getTextInfo().isLine()) {
            infos_.getTextInfo().setKo();
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
            if (curChar_ == _delimiter
                    && StringExpUtil.nextCharIs(_string,i_+1,_string.length(),_delimiter)
                    && StringExpUtil.nextCharIs(_string,i_+2,_string.length(),_delimiter)) {
                int lastSpace_ = infos_.getTextInfo().getLastSpace();
                if (lastSpace_ > -1) {
                    int lastIndex_ = infos_.getTextInfo().length()-1;
                    for (int i = lastIndex_; i >= lastSpace_; i--) {
                        infos_.getTextInfo().remove(i);
                    }
                }
                infos_.setPart(false);
                i_+=3;
                infos_.setIndex(i_);
                return infos_;
            }
            if (curChar_ == LINE_RETURN) {
                boolean line_ = infos_.getTextInfo().isLine();
                infos_.getTextInfo().setLine(true);
                int lastSpace_ = infos_.getTextInfo().getLastSpace();
                if (lastSpace_ > -1) {
                    int lastIndex_ = infos_.getTextInfo().length()-1;
                    for (int i = lastIndex_; i >= lastSpace_; i--) {
                        infos_.getTextInfo().remove(i);
                    }
                }
                if (line_) {
                    infos_.getTextInfo().appendChar(curChar_);
                    nbChars_++;
                    infos_.setNbChars(nbChars_);
                }
                infos_.getTextInfo().setPrintable(false);
                infos_.getTextInfo().setLastSpace(-1);
            } else if (StringUtil.isWhitespace(curChar_)) {
                if (infos_.getTextInfo().isPrintable()) {
                    infos_.getTextInfo().setLastSpace(infos_.getTextInfo().length());
                    infos_.getTextInfo().appendChar(curChar_);
                    nbChars_++;
                    infos_.setNbChars(nbChars_);
                }
            } else {
                infos_.getTextInfo().setLastSpace(-1);
                infos_.getTextInfo().appendChar(curChar_);
                infos_.getTextInfo().setPrintable(true);
                nbChars_++;
                infos_.setNbChars(nbChars_);
            }
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        infos_.getTextInfo().setLastSpace(-1);
        if (unicode_ > 0) {
            char charToAdd_ = trTx(_key, _si, curChar_);
            infos_.getTextInfo().getBuiltUnicode()[unicode_-1] = charToAdd_;
            if (unicode_ < UNICODE_SIZE) {
                unicode_++;
            } else {
                char[] unicodes_ = infos_.getTextInfo().getBuiltUnicode();
                char builtChar_ = NumParsers.parseCharSixteen(String.valueOf(unicodes_));
                infos_.getTextInfo().appendChar(builtChar_);
                infos_.getTextInfo().setPrintable(true);
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
            infos_.getTextInfo().appendChar(curChar_);
            infos_.getTextInfo().setPrintable(true);
            i_++;
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == LINE_RETURN) {
            infos_.setEscape(false);
            i_++;
            infos_.setIndex(i_);
            infos_.getTextInfo().setPrintable(true);
            return infos_;
        }
        String single_ = _key.getEscKeyWord(_string, i_);
        String newLine_ = _key.getKeyWordEscLine();
        String form_ = _key.getKeyWordEscForm();
        String rfeed_ = _key.getKeyWordEscFeed();
        String space_ = _key.getKeyWordEscSpace();
        String tab_ = _key.getKeyWordEscTab();
        String bound_ = _key.getKeyWordEscBound();
        if (single_ != null) {
            nbChars_++;
            infos_.setNbChars(nbChars_);
            infos_.setEscape(false);
            if (StringUtil.quickEq(single_, newLine_)) {
                i_+=newLine_.length();
                infos_.getTextInfo().appendChar(LINE_RETURN);
            } else if (StringUtil.quickEq(single_, form_)) {
                i_+=form_.length();
                infos_.getTextInfo().appendChar(FORM_FEED);
            } else if (StringUtil.quickEq(single_, rfeed_)) {
                i_+=rfeed_.length();
                infos_.getTextInfo().appendChar(LINE_FEED);
            } else if (StringUtil.quickEq(single_, tab_)) {
                i_+=tab_.length();
                infos_.getTextInfo().appendChar(TAB);
            } else if (StringUtil.quickEq(single_, space_)) {
                i_+=tab_.length();
                infos_.getTextInfo().appendChar(SPACE);
            } else {
                i_+=bound_.length();
                infos_.getTextInfo().appendChar(BOUND);
            }
            infos_.getTextInfo().setPrintable(true);
            infos_.setIndex(i_);
            return infos_;
        }
        if (curChar_ == ESCAPE_META_CHAR) {
            infos_.getTextInfo().appendChar(ESCAPE_META_CHAR);
            infos_.getTextInfo().setPrintable(true);
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
            infos_.getTextInfo().appendChar(curChar_);
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

    public static OperationsSequence getOperationsSequence(int _offset, String _string,
                                                           Delimiters _d, AnalyzedPageEl _page, MethodOperation _meth) {
        return getOperationsSequence(_offset,_string,_d,_page,_meth,new ExpPartDelimiters(_string));
    }
    public static OperationsSequence getOperationsSequence(int _offset, String _string,
                                                           Delimiters _d, AnalyzedPageEl _page, MethodOperation _meth, ExpPartDelimiters _exp) {
        int lastPrintChar_ = _exp.getLastPrintIndex();
        if (delimits(_d.getDelSimpleAnnotations(), _offset, _exp.getFirstPrintIndex(), lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.SIMPLE_ANNOTATION);
            op_.setOperators(new StrTypes());
            op_.initValues();
            op_.setFctName(_string);
            return op_;
        }
        OperationsSequence seq_ = tryGetSequence(_exp,_offset, _string, _d, _page);
        if (seq_ != null) {
            return seq_;
        }
        int len_ = lastPrintChar_ + 1;
        AfterUnaryParts af_ = new AfterUnaryParts(_offset,_string, _exp,_d);
        int i_ = af_.getIndex();
        af_.setInstance(_offset,_string,_d, _page);
        while (i_ < len_) {
            af_.setState(_offset,_string,_d);
            i_ = af_.getIndex();
        }
        Ints laterIndexesDouble_ = af_.getLaterIndexesDouble();
        int prio_ = af_.getPrio();
        StrTypes operators_;
        operators_ = af_.getOperators();
        boolean is_ = af_.isInstOf();
        String fctName_ = af_.getFctName();
        boolean instance_ = af_.isInstance();
        OperationsSequence op_ = new OperationsSequence();
        op_.setPriority(prio_);
        op_.setOperators(operators_);
        op_.setFctName(fctName_);
        op_.setErrorDot(af_.isErrorDot());
        op_.setBlock(af_.getBlock());
        op_.setLength(af_.getLength());
        op_.setDeltaAnnot(af_.getDeltaAnnot());
        op_.setRetSwitch(af_.getRetSwitch());
        op_.setupValues(_string, is_, instance_, laterIndexesDouble_);
        String extracted_ = af_.getExtracted();
        op_.setExtractType(extracted_);
        AnaResultPartType partsOffs_ = af_.getPartsOffs();
        op_.setPartOffsets(partsOffs_);
        op_.adjust(_meth,_page);
        return op_;
    }

    private static OperationsSequence tryGetSequence(ExpPartDelimiters _exp,int _offset, String _string,
                                                     Delimiters _d, AnalyzedPageEl _page) {
        int i_ = _exp.getFirstPrintIndex();
        int len_ = _string.length();
        if (i_ >= len_) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.ERROR);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, 0);
            return op_;
        }
        int lastPrintChar_ = _exp.getLastPrintIndex();
        len_ = lastPrintChar_+1;
        for (VariableInfo v: _d.getVariables()) {
            if (v.getFirstChar() == _offset + i_) {
                int iVar_ = v.getLastChar();
                if (iVar_ != _offset + lastPrintChar_ + 1) {
                    break;
                }
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(v.getKind());
                op_.setDeclaringField(v.getDeclaringField());
                op_.setErrors(v.getErrors());
                op_.setOperators(new StrTypes());
                op_.setValue(v.getName(), i_);
                return op_;
            }
        }
        for (AnonymousResult a: _page.getAnonymousResults()) {
            if (a.getIndex() == i_ + _offset) {
                int to_ = a.getUntil() - _offset;
                if (to_ != lastPrintChar_) {
                    break;
                }
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.ANON_FCT);
                op_.setOperators(new StrTypes());
                op_.setValue(_string, i_);
                op_.setBlock(a.getType());
                op_.setResults(a.getResults());
                op_.setLength(a.getLength());
                return op_;
            }
        }
        return allDels(_offset, _string, _d, len_, _page, i_, lastPrintChar_);
    }

    public static int firstPrint(String _string) {
        int len_ = _string.length();
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < len_) {
            if (!StringUtil.isWhitespace(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        return i_;
    }

    private static OperationsSequence allDels(int _offset, String _string, Delimiters _d, int _len, AnalyzedPageEl _page, int _firstPrintChar, int _lastPrintChar) {
        KeyWords keyWords_ = _page.getKeyWords();
        int strLen_ = _string.length();
        int begStat_ = delimiter(_d.getDelKeyWordStatic(), _offset, _firstPrintChar, strLen_);
        if (begStat_ >= 0) {
            int ext_ = begStat_ / 2;
            String extracted_ = _d.getDelKeyWordStaticExtract().get(ext_);
            AnaGeneType extractedType_ = _d.getStaticAccessTypes().get(ext_);
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.STATIC_ACCESS);
            op_.setExtractType(extracted_);
            op_.setExtractStaticType(extractedType_);
            op_.setPartOffsets(_d.getStaticParts().get(ext_));
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        if (delimits(_d.getDelKeyWordStaticCall(), _offset, _firstPrintChar, strLen_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.STATIC_CALL_ACCESS);
            op_.setPartOffsets(new AnaResultPartType());
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        if (delimits(_d.getDelVararg(), _offset, _firstPrintChar, _lastPrintChar)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.VARARG);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        if (delimits(_d.getDelDefaultValue(), _offset, _firstPrintChar, _lastPrintChar)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.DEFAULT_VALUE);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        if (delimits(_d.getDelLambda(), _offset, _firstPrintChar, _lastPrintChar)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.LAMBDA);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        if (delimits(_d.getDelIds(), _offset, _firstPrintChar, _lastPrintChar)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.ID);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        if (delimits(_d.getDelClass(), _offset, _firstPrintChar, _lastPrintChar)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.CLASS_INFO);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        if (delimits(_d.getDelKeyWordSuper(), _offset, _firstPrintChar, _lastPrintChar + 1L)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.SUPER_KEYWORD);
            op_.setOperators(new StrTypes());
            int ind_ = _string.indexOf('.') + 1;
            op_.setDelta(ind_);
            op_.setValue(_string.substring(ind_, _lastPrintChar + 1), _firstPrintChar);
            return op_;
        }
        if (delimits(_d.getDelKeyWordClassChoice(), _offset, _firstPrintChar, _lastPrintChar + 1L)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.CLASSCHOICE_KEYWORD);
            op_.setOperators(new StrTypes());
            op_.setValue(_string.substring(_firstPrintChar, _lastPrintChar + 1), _firstPrintChar);
            return op_;
        }
        if (delimits(_d.getDelLoopVars(), _offset, _firstPrintChar, _lastPrintChar)) {
            int indexLeftArr_ = _string.indexOf(ARR_LEFT);
            int indexRightArr_ = _string.lastIndexOf(ARR_RIGHT);
            int delta_ = indexLeftArr_+1 - _firstPrintChar;
            String name_ = _string.substring(indexLeftArr_+1, indexRightArr_);
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.LOOP_INDEX);
            op_.setOperators(new StrTypes());
            op_.setDelta(delta_);
            op_.setValue(name_, _firstPrintChar);
            return op_;
        }
        if (delimits(_d.getDelKeyWordSuperAccess(), _offset, _firstPrintChar, _lastPrintChar +1L)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.SUPER_ACCESS_KEYWORD);
            op_.setOperators(new StrTypes());
            op_.setValue(_string.substring(_firstPrintChar, _lastPrintChar + 1), _firstPrintChar);
            return op_;
        }
        if (delimits(_d.getDelAccessIndexers(), _offset, _firstPrintChar, _lastPrintChar +1L)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.ACCESS_INDEXER);
            op_.setOperators(new StrTypes());
            op_.setValue(_string.substring(_firstPrintChar, _lastPrintChar + 1), _firstPrintChar);
            return op_;
        }
        if (delimits(_d.getDelValues(), _offset, _firstPrintChar, _lastPrintChar)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.VALUES);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        return sub(_offset, _string, _d, _len, keyWords_, _firstPrintChar, _lastPrintChar);
    }

    private static OperationsSequence sub(int _offset, String _string, Delimiters _d, int _len, KeyWords _keyWords, int _firstPrintChar, int _lastPrintChar) {
        String keyWordFalse_ = _keyWords.getKeyWordFalse();
        String keyWordNull_ = _keyWords.getKeyWordNull();
        String keyWordThis_ = _keyWords.getKeyWordThis();
        String keyWordParent_ = _keyWords.getKeyWordParent();
        String keyWordTrue_ = _keyWords.getKeyWordTrue();
        String sub_ = _string.substring(_firstPrintChar, _len);
        if (StringUtil.quickEq(sub_, keyWordThis_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.THIS_KEYWORD);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        if (StringUtil.quickEq(sub_, keyWordParent_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.PARENT_KEY_WORD);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        if (StringUtil.quickEq(sub_, keyWordNull_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.NULL_CST);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        if (StringUtil.quickEq(sub_, keyWordTrue_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.TRUE_CST);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        if (StringUtil.quickEq(sub_, keyWordFalse_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.FALSE_CST);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, _firstPrintChar);
            return op_;
        }
        return consOrNo(_offset, _string, _d, _firstPrintChar, _lastPrintChar);
    }

    private static OperationsSequence consOrNo(int _offset, String _string, Delimiters _d, int _firstPrintChar, int _lastPrintChar) {
        int beginNb_ = delimiter(_d.getDelNumbers(), _offset, _firstPrintChar, _lastPrintChar + 1L);
        if (beginNb_ >= 0) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.NUMBER);
            int indexNb_ = beginNb_/2;
            op_.setOperators(new StrTypes());
            op_.setNbInfos(_d.getNbInfos().get(indexNb_));
            op_.getNbInfos().setPositive(true);
            op_.setValue(_string, _firstPrintChar);
            op_.setLength(_d.getDelNumbers().get(beginNb_+1)-_offset-_firstPrintChar);
            return op_;
        }
        int beginStr_ = delimiter(_d.getDelStringsChars(), _offset, _firstPrintChar, _lastPrintChar);
        if (beginStr_ >= 0) {
            TextBlockInfo info_ = _d.getStringInfo().get(beginStr_/2);
            String export_ = info_.build();
            int lenStrCh_ = _d.getDelStringsChars().get(beginStr_+1)-_offset+1-_firstPrintChar;
            if (_string.charAt(_firstPrintChar) == DELIMITER_CHAR) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.CHARACTER);
                op_.setOperators(new StrTypes());
                op_.setStrInfo(info_);
                info_.setFound(_string);
                op_.setValue(export_, _firstPrintChar);
                op_.setLength(lenStrCh_);
                return op_;
            }
            if (_string.charAt(_firstPrintChar) == DELIMITER_STRING) {
                OperationsSequence op_ = new OperationsSequence();
                op_.setConstType(ConstType.STRING);
                op_.setOperators(new StrTypes());
                op_.setStrInfo(info_);
                info_.setFound(_string);
                op_.setValue(export_, _firstPrintChar);
                op_.setLength(lenStrCh_);
                return op_;
            }
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.STRING);
            op_.setOperators(new StrTypes());
            op_.setStrInfo(info_);
            info_.setFound(_string);
            op_.setValue(export_, _firstPrintChar);
            op_.setLength(lenStrCh_);
            return op_;
        }
        int beginTx_ = delimiter(_d.getDelTextBlocks(), _offset, _firstPrintChar, _lastPrintChar);
        if (beginTx_ >= 0) {
            TextBlockInfo info_ = _d.getTextInfo().get(beginTx_/2);
            String export_ = info_.build();
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.TEXT_BLOCK);
            op_.setOperators(new StrTypes());
            op_.setTextInfo(info_);
            info_.setFound(_string);
            op_.setValue(export_, _firstPrintChar);
            op_.setLength(_d.getDelTextBlocks().get(beginTx_+1)-_offset+1-_firstPrintChar);
            return op_;
        }
        return null;
    }

    public static int lastPrintChar(String _string) {
        int len_ = _string.length();
        int lastPrintChar_ = len_ - 1;
        while (lastPrintChar_ >= 0&&StringUtil.isWhitespace(_string.charAt(lastPrintChar_))) {
            lastPrintChar_--;
        }
        return lastPrintChar_;
    }

    private static boolean delimits(Ints _dels, int _offset,long _first, long _last) {
        return delimiter(_dels, _offset, _first, _last) > IndexConstants.INDEX_NOT_FOUND_ELT;
    }
    private static int delimiter(Ints _dels, int _offset,long _first, long _last) {
        int begin_ = _dels.indexOfNb(_first +_offset);
        int end_ = _dels.indexOfNb(_last +_offset);
        if (begin_ > IndexConstants.INDEX_NOT_FOUND_ELT && begin_ + 1 == end_) {
            return begin_;
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    private static int indexAfterPossibleCast(String _string, int _from, Delimiters _d, FieldRetriever _ret, AnalyzedPageEl _page) {
        int indexParRight_ = _string.indexOf(PAR_RIGHT, _from +1);
        if (indexParRight_ < 0) {
            return _from;
        }
        for (AnonymousResult r:_page.getCurrentAnonymousResults()) {
            if (r.getIndex() == _from) {
                return _from;
            }
        }
        if (_d.getStack().getCallings().containsObj(_from)) {
            return _from;
        }

        String sub_ = _string.substring(_from + 1, indexParRight_);
        int off_ = StringUtil.getFirstPrintableCharIndex(sub_);
        String subTrim_ = sub_.trim();
        if (subTrim_.startsWith(ARR) && subTrim_.endsWith(ARR_END)) {
            String candidate_ = subTrim_.substring(1,subTrim_.length()-1);
            if (noWideInternDelimiter(candidate_)) {
                _d.getDelLoopVars().add(_from);
                _d.getDelLoopVars().add(indexParRight_);
                _d.setEnabledOp(true);
                return indexParRight_ + 1;
            }
        }
        int next_ = StringExpUtil.nextPrintChar(indexParRight_+1,_string.length(),_string);
        if (next_ < 0 || StringExpUtil.startsWithKeyWord(_string, next_, _page.getKeyWords().getKeyWordInstanceof())) {
            return _from;
        }
        for (String s: StringUtil.wrapStringArray("+=","-=",
                "*=","/","%",
                "^","&","|",
                "?",":",
                "<",">",",","->",
                "!=","=",")","[","]","}")) {
            if (_string.startsWith(s,next_)) {
                return _from;
            }
        }
        if (_string.startsWith(".",next_)) {
            int n_ = StringExpUtil.nextPrintChar(next_ + 1, _string.length(), _string);
            if (!ElResolverCommon.isDigitOrDot(_string,n_)) {
                return _from;
            }
        }
        int beginWord_ = _from + 1 + off_;
        if (StringExpUtil.isDollarWordChar(_string.charAt(beginWord_))&&AnaPartTypeUtil.isCorrectType(subTrim_, new StringList())) {
            if (!isAlwaysType(_string, subTrim_, next_)) {
                int inc_ = incrAfterWord(beginWord_, _string);
                String word_ = _string.substring(beginWord_, inc_);
                int n_ = _ret.tryGetVarField(beginWord_, word_, inc_);
                if (n_ > beginWord_) {
                    _d.setEnabledOp(true);
                    return n_;
                }
            }
            AnaResultPartType resType_ = ResolvingTypes.resolveCorrectTypeWithoutErrorsExact(beginWord_, subTrim_, _page);
            if (resType_.isOk()) {
                _d.getDelCast().add(_from);
                _d.getDelCast().add(indexParRight_);
                String typeOut_ = resType_.getResult();
                _d.getDelCastExtract().add(typeOut_);
                _d.getCastParts().add(resType_);
                return indexParRight_ + 1;
            }
        }
        return _from;
    }

    private static boolean isAlwaysType(String _string, String _subTrim, int _next) {
        return _subTrim.contains("<") || _subTrim.contains("[") || isAlwaysType(_string, _next);
    }

    private static boolean isAlwaysType(String _string, int _next) {
        if (StringExpUtil.isDollarWordChar(_string.charAt(_next))) {
            return true;
        }
        for (char s: CharList.wrapCharArray('~','!',
                '(','{','`','\'','"','.','#')) {
            if (StringExpUtil.nextCharIs(_string, _next, _string.length(),s)) {
                return true;
            }
        }
        boolean nextIncr_ = false;
        for (char c: CharList.wrapCharArray('+','-')) {
            if (StringExpUtil.nextCharIs(_string, _next, _string.length(),c)&&StringExpUtil.nextCharIs(_string, _next +1, _string.length(),c)) {
                nextIncr_ = true;
                break;
            }
        }
        if (!nextIncr_) {
            return false;
        }
        int nextAfter_ = StringExpUtil.nextPrintChar(_next +2, _string.length(), _string);
        if (nextAfter_ > -1 && StringExpUtil.isDollarWordChar(_string.charAt(nextAfter_))) {
            return true;
        }
        if (_string.startsWith("!=",nextAfter_)) {
            return false;
        }
        for (char c: CharList.wrapCharArray('~','!',
                '(','{','`','\'','"','#')) {
            if (StringExpUtil.nextCharIs(_string,nextAfter_, _string.length(),c)) {
                return true;
            }
        }
        return false;
    }

}
