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
        int i_ = DefaultProcessKeyWord.skipWhiteSpace(_string,_minIndex);
        int beginIndex_ = i_;
        if (i_ >= len_) {
            _d.setBadOffset(i_);
            return _d;
        }
        i_ = _minIndex;
        ResultAfterInstKeyWord resKeyWords_ = new ResultAfterInstKeyWord();
        resKeyWords_.setNextIndex(i_);
        resOpers_.setDoubleDotted(resKeyWords_);
        while (i_ < len_) {

            int until_ = next(_string,i_,resOpers_,_d,_page);
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
        return afterLoop(_d, parsBrackets_, resOpers_, i_);
    }
    private static int next(String _string, int _i, ResultAfterOperators _ret, Delimiters _d, AnalyzedPageEl _page){
        KeyWords keyWords_ = _page.getKeyWords();
        int until_ = _i;
        for (SegmentStringPart s: _page.getCurrentParts()) {
            if (s.getBegin() == _i) {
                strParts(_string, _d, _ret, keyWords_, s);
                until_ = s.getEnd();
                break;
            }
        }
        if (until_ > _i) {
            return until_;
        }
        int untilNb_ = _i;
        for (NumberInfosOutput n: _page.getCurrentNumbers()) {
            if (n.getPreviousIndex() == _i) {

                _d.getNbInfos().add(n.getInfos());
                _d.getDelNumbers().add(_i);
                _d.getDelNumbers().add(n.getNextIndex());
                _d.setEnabledOp(true);

                untilNb_ = n.getNextIndex();
                break;
            }
        }
        return Math.max(untilNb_, _i);
    }

    private static Delimiters afterLoop(Delimiters _d, StackOperators _parsBrackets, ResultAfterOperators _resOpers, int _i) {
        if (_resOpers.isConstTextString()) {
            _d.setBadOffset(_i);
            return _d;
        }
        if (_resOpers.isConstString()) {
            _d.setBadOffset(_i);
            return _d;
        }
        if (_resOpers.isConstChar()) {
            _d.setBadOffset(_i);
            return _d;
        }
        if (_resOpers.isConstText()) {
            _d.setBadOffset(_i);
            return _d;
        }
        if (!_parsBrackets.isEmpty()) {
            _d.setBadOffset(_i);
            return _d;
        }
        if (!_resOpers.isPartOfString()) {
            return _d;
        }
        _d.setBadOffset(_i);
        return _d;
    }

    private static void strParts(String _string, Delimiters _d, ResultAfterOperators _resOpers, KeyWords _keyWords, SegmentStringPart _stPart) {
        if (_stPart.getStrType() == SegmentStringType.CHAR) {
            ch(_string, _d, _resOpers, _keyWords, _stPart);
        }
        if (_stPart.getStrType() == SegmentStringType.STRING) {
            str(_string, _d, _resOpers, _keyWords, _stPart);

        }
        if (_stPart.getStrType() == SegmentStringType.TEXT) {
            tx(_string, _d, _resOpers, _stPart);

        }
        if (_stPart.getStrType() == SegmentStringType.TEXT_STRING) {
            txStr(_string, _d, _resOpers, _keyWords, _stPart);

        }
        if (_stPart.getStrType() == SegmentStringType.TEXT_CHAR) {
            txCh(_string, _d, _resOpers, _keyWords, _stPart);
        }
    }

    private static void txCh(String _string, Delimiters _d, ResultAfterOperators _resOpers, KeyWords _keyWords, SegmentStringPart _stPart) {
        txBl(_string, _d, _resOpers, _keyWords, _stPart, DELIMITER_CHAR);
    }

    private static void txBl(String _string, Delimiters _d, ResultAfterOperators _resOpers, KeyWords _keyWords, SegmentStringPart _stPart, char _delimiterChar) {
        int len_ = _string.length();
        TextBlockInfo txt_ = new TextBlockInfo();
        boolean escapedMeta_ = false;
        int unicode_ = 0;
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
            unic_.setStringInfo(txt_);
            IndexUnicodeEscape res_ = processTextBlocks(_keyWords, _string, len_, txt_,unic_, _delimiterChar);
            int index_ = res_.getIndex();
            if (!res_.isPart()) {
                _d.getStringInfo().add(txt_);
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

    private static void txStr(String _string, Delimiters _d, ResultAfterOperators _resOpers, KeyWords _keyWords, SegmentStringPart _stPart) {
        txBl(_string, _d, _resOpers, _keyWords, _stPart, DELIMITER_STRING);
    }

    private static void tx(String _string, Delimiters _d, ResultAfterOperators _resOpers, SegmentStringPart _stPart) {
        int len_ = _string.length();
        int i_ = _stPart.getBegin();
        TextBlockInfo si_ = new TextBlockInfo();
        boolean escapedMeta_ = false;
        int unicode_ = 0;
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
            IndexUnicodeEscape res_ = processStringsDelText(_string, len_, unic_);
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

    private static void str(String _string, Delimiters _d, ResultAfterOperators _resOpers, KeyWords _keyWords, SegmentStringPart _stPart) {
        int len_ = _string.length();
        int i_ = _stPart.getBegin();
        TextBlockInfo si_ = new TextBlockInfo();
        boolean escapedMeta_ = false;
        int unicode_ = 0;
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

    private static void ch(String _string, Delimiters _d, ResultAfterOperators _resOpers, KeyWords _keyWords, SegmentStringPart _stPart) {
        int len_ = _string.length();
        TextBlockInfo si_ = new TextBlockInfo();
        boolean escapedMeta_ = false;
        int unicode_ = 0;
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
            if (unic_.getNbChars() > 1) {
                si_.setKo();
            }
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
        processWords(_string, _d, _ret, kw_, _page);
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
            _out.setNextIndex(indexParRight_ + 1);
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
            _out.setNextIndex(indexParRight_ + 1);
        }
    }
    private static void processAfterInstuctionKeyWord(String _string, Delimiters _d, ResultAfterInstKeyWord _out, ResultAfterOperators _opers, AnalyzedPageEl _page) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordClass_ = keyWords_.getKeyWordClass();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordId_ = keyWords_.getKeyWordId();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        String keyWordInterfaces_ = keyWords_.getKeyWordInterfaces();
        String keyWordLambda_ = keyWords_.getKeyWordLambda();
        String keyWordNew_ = keyWords_.getKeyWordNew();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String keyWordStaticCall_ = keyWords_.getKeyWordStaticCall();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        String keyWordVararg_ = keyWords_.getKeyWordVararg();
        String keyWordDefaultValue_ = keyWords_.getKeyWordDefaultValue();
        String keyWordOperator_ = keyWords_.getKeyWordOperator();
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordVararg_)) {
            keyWordVararg(_string, _d, _out);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordDefaultValue_)) {
            keyWordDefaultValue(_string, _d, _out);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordClass_)) {
            keyWordClass(_string, _d, _out);
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
            _out.setNextIndex(next_);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordLambda_)) {
            keyWordLambda(_string, _d, _out);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordId_)) {
            keyWordId(_string, _d, _out);
            return;
        }
        boolean isKeySt_ = StringExpUtil.startsWithKeyWord(_string,i_, keyWordStatic_);
        boolean isKeyStCall_ = StringExpUtil.startsWithKeyWord(_string,i_, keyWordStaticCall_);
        if (isKeySt_||isKeyStCall_) {
            keyWordStaticStaticCall(_string, _d, _out, isKeySt_,_page);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordSuper_)) {
            keyWordSuper(_string, _d, _out, _page);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordClasschoice_)) {
            keyWordClassChoice(_string, _d, _out, _page);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordSuperaccess_)) {
            keyWordSuperAccess(_string, _d, _out, _page);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordThisaccess_)) {
            keyWordThisAccess(_string, _d, _out, _page);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordInterfaces_)|| StringExpUtil.startsWithKeyWord(_string,i_, keyWordOperator_)) {
            keyWordInterfacesOperator(_string, _d, _out, _page);
            return;
        }
        defKeyWords2(_string, _d, _out, _opers, _page);
    }

    private static void defKeyWords2(String _string, Delimiters _d, ResultAfterInstKeyWord _out, ResultAfterOperators _opers, AnalyzedPageEl _page) {
        int i_ = _out.getNextIndex();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        String keyWordValueOf_ = keyWords_.getKeyWordValueOf();
        String keyWordValues_ = keyWords_.getKeyWordValues();
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordThat_)) {
            keyWordThat(_string, _d, _out, _page);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordThis_)) {
            keyWordThis(_string, _out, _d, _page);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordValueOf_)) {
            keyWordValueOf(_string, _d, _out, _opers, _page);
            return;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordValues_)) {
            keyWordValues(_string, _d, _out);
            return;
        }
        defKeyWords(_string, _d, _out, _page);
    }

    private static void defKeyWords(String _string, Delimiters _d, ResultAfterInstKeyWord _out, AnalyzedPageEl _page) {
        int i_ = _out.getNextIndex();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordBool_ = keyWords_.getKeyWordBool();
        String keyWordFalse_ = keyWords_.getKeyWordFalse();
        String keyWordFirstopt_ = keyWords_.getKeyWordFirstopt();
        String keyWordNull_ = keyWords_.getKeyWordNull();
        String keyWordParent_ = keyWords_.getKeyWordParent();
        String keyWordTrue_ = keyWords_.getKeyWordTrue();
        String keyWordDefault_ = keyWords_.getKeyWordDefault();
        StackDelimiters stack_ = _d.getStack();
        boolean foundValue_ = false;
        for (String s: StringUtil.wrapStringArray(keyWordTrue_,keyWordFalse_,keyWordNull_,keyWordParent_)) {
            if (StringExpUtil.startsWithKeyWord(_string,i_, s)) {
                i_ = incrTrueFalseNullPar(_string,_d,i_,s);
                foundValue_ = true;
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
    private static int incrTrueFalseNullPar(String _string, Delimiters _d, int _i, String _part) {
        StackDelimiters stack_ = _d.getStack();
        int len_ = _string.length();
        int afterSuper_ = _i + _part.length();
        int pr_ = StringExpUtil.nextPrintChar(afterSuper_,len_,_string);
        if (StringExpUtil.nextCharIs(_string,pr_,len_,PAR_LEFT)) {
            stack_.getCallings().add(pr_);
            return pr_;
        }
        if (pr_ < 0){
            return len_;
        }
        return pr_;
    }

    private static void keyWordValueOf(String _string, Delimiters _d, ResultAfterInstKeyWord _out, ResultAfterOperators _opers, AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordValueOf_ = keyWords_.getKeyWordValueOf();
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        StackDelimiters stack_ = _d.getStack();
        int afterSuper_ = i_ + keyWordValueOf_.length();
        afterSuper_ = DefaultProcessKeyWord.skipWhiteSpace(_string, afterSuper_);
        if (StringExpUtil.nextCharIs(_string, afterSuper_, len_, PAR_LEFT)) {

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
        _d.setBadOffset(Math.min(afterSuper_, len_));
    }

    private static void keyWordThis(String _string, ResultAfterInstKeyWord _out, Delimiters _d, AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        StackDelimiters stack_ = _d.getStack();
        int afterSuper_ = i_ + keyWordThis_.length();
        afterSuper_ = DefaultProcessKeyWord.skipWhiteSpace(_string, afterSuper_);
        if (StringExpUtil.nextCharIs(_string, afterSuper_, len_, PAR_LEFT)) {
            stack_.getCallings().add(afterSuper_);
        }
        _out.setNextIndex(afterSuper_);
    }

    private static void keyWordValues(String _string, Delimiters _d, ResultAfterInstKeyWord _out) {
        simpleKey(_string, _out, _d, _d.getDelValues());
    }

    private static void keyWordThat(String _string, Delimiters _d, ResultAfterInstKeyWord _out, AnalyzedPageEl _page) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        int afterSuper_ = i_ + keyWordThat_.length();
        StackDelimiters stack_ = _d.getStack();
        int nextPr_ = StringExpUtil.nextPrintChar(afterSuper_,len_,_string);
        if (nextPr_ < 0) {
            _d.setBadOffset(afterSuper_);
            return;
        }
        if (_string.charAt(nextPr_) == ARR_LEFT) {
            _d.getDelAccessIndexers().add(i_);
            _d.getDelAccessIndexers().add(nextPr_);
            _out.setNextIndex(nextPr_);
            return;
        }
        if (_string.charAt(nextPr_) == PAR_LEFT) {
            stack_.getCallings().add(nextPr_);
            _out.setNextIndex(nextPr_);
            return;
        }
        if (_string.charAt(nextPr_) != DOT_VAR) {
            _d.setBadOffset(afterSuper_);
            return;
        }
        afterSuper_ = nextPr_;
        afterSuper_++;
        while (afterSuper_ < len_) {
            if (!StringUtil.isWhitespace(_string.charAt(afterSuper_)) && !StringExpUtil.isTypeLeafChar(_string.charAt(afterSuper_))) {
                break;
            }
            afterSuper_++;
        }
        if (StringExpUtil.nextCharIs(_string, afterSuper_, len_, PAR_LEFT)) {
            stack_.getCallings().add(afterSuper_);
        } else {
            _d.setBadOffset(afterSuper_);
            return;
        }
        _out.setNextIndex(afterSuper_);
    }

    private static void keyWordInterfacesOperator(String _string, Delimiters _d, ResultAfterInstKeyWord _out, AnalyzedPageEl _page) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordOperator_ = keyWords_.getKeyWordOperator();
        int afterClassChoice_ = incr(_string, i_, _page);
        int afterFirst_ = nextIndex(_string, _d, afterClassChoice_);
        if (_d.getBadOffset() >= 0) {
            return;
        }
        if (afterFirst_ >= len_) {
            _d.setBadOffset(afterFirst_);
            return;
        }
        char loc_ = _string.charAt(afterFirst_);
        if (StringExpUtil.startsWithKeyWord(_string, i_, keyWordOperator_) && (loc_ == '=' || loc_ == ':')) {
            compound(_string, _d, _out, afterFirst_,_page);
            return;
        }
        defInterfOpers(_string,_d, _out, afterFirst_);
    }

    private static void badOffset(Delimiters _d, int _len, int _index) {
        if (_index < 0) {
            _d.setBadOffset(_len - 1);
        } else {
            _d.setBadOffset(_index);
        }
    }

    private static void defInterfOpers(String _string, Delimiters _d, ResultAfterInstKeyWord _out, int _afterClassChoice) {
        StackDelimiters stack_ = _d.getStack();
        char loc_ = _string.charAt(_afterClassChoice);
        if (loc_ != PAR_LEFT) {
            _d.setBadOffset(_afterClassChoice);
            return;
        }
        stack_.getCallings().add(_afterClassChoice);
        _out.setNextIndex(_afterClassChoice);
    }

    private static int incr(String _string, int _cur, AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordInterfaces_ = keyWords_.getKeyWordInterfaces();
        String keyWordOperator_ = keyWords_.getKeyWordOperator();
        int afterClassChoice_;
        if (StringExpUtil.startsWithKeyWord(_string, _cur, keyWordInterfaces_)) {
            afterClassChoice_ = _cur + keyWordInterfaces_.length();
        } else {
            afterClassChoice_ = _cur + keyWordOperator_.length();
        }
        return afterClassChoice_;
    }

    private static void compound(String _string, Delimiters _d, ResultAfterInstKeyWord _out, int _afterClassChoice, AnalyzedPageEl _page) {
        int len_ = _string.length();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordCast_ = keyWords_.getKeyWordCast();
        String keyWordExplicit_ = keyWords_.getKeyWordExplicit();
        StackDelimiters stack_ = _d.getStack();
        int afterAff_ = StringExpUtil.nextPrintChar(_afterClassChoice + 1, len_, _string);
        int nextKey_ = afterAff_;
        if (StringExpUtil.startsWithKeyWord(_string, afterAff_, keyWordCast_)) {
            int impl_ = afterAff_ + keyWordCast_.length();
            int nextLeftPar_ = StringExpUtil.nextPrintCharIs(impl_, len_, _string, PAR_LEFT);
            if (nextLeftPar_ < 0) {
                _d.setBadOffset(impl_ + 1);
                return;
            }
            int nextRightPar_ = _string.indexOf(PAR_RIGHT, nextLeftPar_);
            if (nextRightPar_ < 0) {
                _d.setBadOffset(nextLeftPar_ + 1);
                return;
            }
            nextKey_ = StringExpUtil.nextPrintChar(nextRightPar_ + 1, len_, _string);
        }
        if (StringExpUtil.startsWithKeyWord(_string, nextKey_, keyWordExplicit_)) {
            int test_ = nextKey_ + keyWordExplicit_.length();
            int nextLeftPar_ = StringExpUtil.nextPrintCharIs(test_, len_, _string, PAR_LEFT);
            if (nextLeftPar_ < 0) {
                _d.setBadOffset(test_ + 1);
                return;
            }
            int nextRightPar_ = _string.indexOf(PAR_RIGHT, nextLeftPar_);
            if (nextRightPar_ < 0) {
                _d.setBadOffset(nextLeftPar_ + 1);
                return;
            }
            nextKey_ = StringExpUtil.nextPrintChar(nextRightPar_ + 1, len_, _string);
        }
        if (StringExpUtil.nextPrintCharIs(nextKey_, len_, _string, PAR_LEFT) < 0) {
            _d.setBadOffset(len_);
            return;
        }
        stack_.getCallings().add(nextKey_);
        _out.setNextIndex(nextKey_);
    }

    private static void keyWordThisAccess(String _string, Delimiters _d, ResultAfterInstKeyWord _out, AnalyzedPageEl _page) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        StackDelimiters stack_ = _d.getStack();
        int afterClassChoice_ = i_ + keyWordThisaccess_.length();
        int afterFirst_ = nextIndexIndexer(_string, _d, i_, afterClassChoice_);
        if (_d.getBadOffset() >= 0) {
            return;
        }
        if (StringExpUtil.nextCharIs(_string,afterFirst_,len_,ARR_LEFT)) {
            _out.setNextIndex(afterFirst_);
            return;
        }
        afterClassChoice_ = skipWord(_string,afterFirst_);
        afterClassChoice_ = DefaultProcessKeyWord.skipWhiteSpace(_string,afterClassChoice_);
        if (StringExpUtil.nextCharIs(_string,afterClassChoice_,len_,PAR_LEFT)) {
            //fct
            stack_.getCallings().add(afterClassChoice_);
            _out.setNextIndex(afterClassChoice_);
            return;
        }
        //field
        _d.setBadOffset(afterClassChoice_);
    }

    private static void keyWordSuperAccess(String _string, Delimiters _d, ResultAfterInstKeyWord _out, AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        keyWordsSuperAccessClassChoice(_string, _d, _out, keyWordSuperaccess_, _d.getDelKeyWordSuperAccess());
    }

    private static void keyWordClassChoice(String _string, Delimiters _d, ResultAfterInstKeyWord _out, AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        keyWordsSuperAccessClassChoice(_string, _d, _out, keyWordClasschoice_, _d.getDelKeyWordClassChoice());
    }

    private static void keyWordsSuperAccessClassChoice(String _string, Delimiters _d, ResultAfterInstKeyWord _out, String _keyWord, Ints _dels) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        StackDelimiters stack_ = _d.getStack();
        int afterClassChoice_ = i_ + _keyWord.length();
        int afterFirst_ = nextIndexIndexer(_string, _d, i_, afterClassChoice_);
        if (_d.getBadOffset() >= 0) {
            return;
        }
        if (StringExpUtil.nextCharIs(_string,afterFirst_,len_,ARR_LEFT)) {
            _out.setNextIndex(afterFirst_);
            return;
        }
        int afWord_ = skipWord(_string, afterFirst_);
        if (afWord_ <= afterFirst_) {
            _d.setBadOffset(afterFirst_);
            return;
        }
        int nextPrChar_ = DefaultProcessKeyWord.skipWhiteSpace(_string, afWord_);
        if (StringExpUtil.nextCharIs(_string,nextPrChar_,len_,PAR_LEFT)) {
            //fct
            stack_.getCallings().add(nextPrChar_);
            _out.setNextIndex(nextPrChar_);
            return;
        }
        //field
        _dels.add(i_);
        _dels.add(afWord_);
        _out.setNextIndex(afWord_);
    }

    private static int skipWord(String _string, int _i) {
        int len_ = _string.length();
        int afterClassChoice_ = _i;
        while (afterClassChoice_ < len_) {
            char loc_ = _string.charAt(afterClassChoice_);
            if (!StringExpUtil.isTypeLeafChar(loc_)) {
                break;
            }
            afterClassChoice_++;
        }
        return afterClassChoice_;
    }

    private static void keyWordSuper(String _string, Delimiters _d, ResultAfterInstKeyWord _out, AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        StackDelimiters stack_ = _d.getStack();
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        int afterSuper_ = i_ + keyWordSuper_.length();
        int nextPr_ = StringExpUtil.nextPrintChar(afterSuper_, len_, _string);
        if (nextPr_ < 0) {
            _d.setBadOffset(len_);
            return;
        }
        if (_string.charAt(nextPr_) == ARR_LEFT) {
            _d.getDelAccessIndexers().add(i_);
            _d.getDelAccessIndexers().add(afterSuper_);
            _out.setNextIndex(afterSuper_);
            return;
        }
        if (_string.charAt(nextPr_) == DOT_VAR) {
            supDot(_string, _d, _out, nextPr_);
        } else if (_string.charAt(nextPr_) == PAR_LEFT) {
            stack_.getCallings().add(nextPr_);
            _out.setNextIndex(nextPr_);
        } else {
            _d.setBadOffset(nextPr_);
        }
    }

    private static void supDot(String _string, Delimiters _d, ResultAfterInstKeyWord _out, int _nextPr) {
        StackDelimiters stack_ = _d.getStack();
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        int afterSup_ = _nextPr;
        afterSup_++;
        while (afterSup_ < len_) {
            if (!StringUtil.isWhitespace(_string.charAt(afterSup_)) && !StringExpUtil.isTypeLeafChar(_string.charAt(afterSup_))) {
                break;
            }
            afterSup_++;
        }
        if (afterSup_ < len_) {
            if (_string.charAt(afterSup_) != PAR_LEFT) {
                _d.getDelKeyWordSuper().add(i_);
                _d.getDelKeyWordSuper().add(afterSup_);
            } else {
                stack_.getCallings().add(afterSup_);
            }
        } else {
            _d.getDelKeyWordSuper().add(i_);
            _d.getDelKeyWordSuper().add(afterSup_);
        }
        _out.setNextIndex(afterSup_);
    }

    private static void keyWordStaticStaticCall(String _string, Delimiters _d, ResultAfterInstKeyWord _out, boolean _isKeySt, AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String keyWordStaticCall_ = keyWords_.getKeyWordStaticCall();
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        Ints indexes_;
        int afterStatic_;
        if (_isKeySt) {
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

        afterStatic_ = DefaultProcessKeyWord.skipWhiteSpace(_string,afterStatic_);
        if (afterStatic_ >= len_) {
            _d.setBadOffset(afterStatic_);
            return;
        }
        if (_string.charAt(afterStatic_) != DOT_VAR) {
            _d.setBadOffset(afterStatic_);
            return;
        }
        indexes_.add(i_);
        indexes_.add(afterStatic_);
        stKey(_d, _isKeySt);
        _out.setNextIndex(afterStatic_);
    }

    private static int nextIndexIndexer(String _string, Delimiters _d, int _i, int _current){
        int afterFirst_ = nextIndex(_string, _d, _current);
        if (_d.getBadOffset() >= 0) {
            return afterFirst_;
        }
        int len_ = _string.length();
        if (StringExpUtil.nextCharIs(_string,afterFirst_,len_,ARR_LEFT)) {
            _d.getDelAccessIndexers().add(_i);
            _d.getDelAccessIndexers().add(afterFirst_);
        }
        return afterFirst_;
    }
    private static int nextIndex(String _string, Delimiters _d, int _current){
        int len_ = _string.length();
        int ne_ = StringExpUtil.nextPrintChar(_current, len_, _string);
        if (!StringExpUtil.nextCharIs(_string,ne_,len_,PAR_LEFT) || ne_ + 1 >= len_) {
            badOffset(_d, len_, ne_);
            return _current;
        }
        int indRightPar_ = _string.indexOf(PAR_RIGHT, ne_);
        if (indRightPar_ < 0) {
            _d.setBadOffset(len_ - 1);
            return ne_;
        }
        return DefaultProcessKeyWord.skipWhiteSpace(_string,indRightPar_+1);
    }
    private static void stKey(Delimiters _d, boolean _isKeySt) {
        if (_isKeySt) {
            _d.getDelKeyWordStaticExtract().add(EMPTY_STRING);
            _d.getStaticAccessTypes().add(null);
            _d.getStaticParts().add(new AnaResultPartType());
        }
    }

    private static void keyWordId(String _string, Delimiters _d, ResultAfterInstKeyWord _out) {
        //lambda
        simpleKey(_string, _out, _d, _d.getDelIds());
    }

    private static void keyWordLambda(String _string, Delimiters _d, ResultAfterInstKeyWord _out) {
        //lambda
        simpleKey(_string, _out, _d, _d.getDelLambda());
    }

    private static void keyWordClass(String _string, Delimiters _d, ResultAfterInstKeyWord _out) {
        simpleKey(_string, _out, _d, _d.getDelClass());
    }

    private static void keyWordDefaultValue(String _string, Delimiters _d, ResultAfterInstKeyWord _out) {
        simpleKey(_string, _out, _d, _d.getDelDefaultValue());
    }

    private static void keyWordVararg(String _string, Delimiters _d, ResultAfterInstKeyWord _out) {
        simpleKey(_string, _out, _d, _d.getDelVararg());
    }

    private static void simpleKey(String _string, ResultAfterInstKeyWord _out, Delimiters _d, Ints _dels) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        int indexParLeft_ = _string.indexOf(ElResolver.PAR_LEFT, i_ +1);
        if (indexParLeft_ < 0) {
            _d.setBadOffset(len_);
            return;
        }
        int indexParRight_ = _string.indexOf(ElResolver.PAR_RIGHT,indexParLeft_+1);
        if (indexParRight_ < 0) {
            _d.setBadOffset(len_);
            return;
        }
        _dels.add(i_);
        _dels.add(indexParRight_);
        _out.setNextIndex(indexParRight_ + 1);
    }

    private static void processWords(String _string, Delimiters _d, FieldRetriever _ret, ResultAfterInstKeyWord _out, AnalyzedPageEl _page) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
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
        AnonymousResult anon_ = anon(beginWord_, _page);
        if (anon_ != null) {
            _out.setNextIndex(anon_.getNext());
            _d.setEnabledOp(true);
            return;
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

        ResultAfterInstKeyWord doubleDotted_ = _out.getDoubleDotted();
        int i_ = doubleDotted_.getNextIndex();
        char curChar_ = _string.charAt(i_);
        if (curChar_ == ANNOT) {
            annot(_string, _dout, doubleDotted_);
            return;
        }
        if (curChar_ == PAR_LEFT) {
            parLeft(_beginIndex, _string, _dout, _ret, _page,_out);
            return;
        }
        if (curChar_ == PAR_RIGHT) {
            parRight(_beginIndex, _string, _dout,_out);
            return;
        }
        if (curChar_ == ANN_ARR_LEFT) {
            annArrLeft(_beginIndex, _string, _dout, _page, _out);
            return;
        }
        if (curChar_ == ANN_ARR_RIGHT) {
            annArrRight(_beginIndex, _string, _dout, _out);
            return;
        }
        if (curChar_ == ARR_LEFT) {
            arrLeft(_beginIndex, _string, _dout, _out);
            return;
        }
        if (curChar_ == ARR_RIGHT) {
            arrRight(_beginIndex, _string, _dout, _out);
            return;
        }
        if (curChar_ == BEGIN_TERNARY&&ElResolverCommon.isTernary(_string, _string.length(), i_)) {
            ternBeg(_beginIndex, _string, _dout, _out);
            return;
        }
        if (curChar_ == END_TERNARY) {
            ternEnd(_beginIndex, _string, _dout, _out);
            return;
        }
        if (curChar_ == SEP_ARG && parsBrackets_.isEmptyStackSymChars() && isAcceptCommaInstr(_page)) {
            _dout.setBadOffset(i_);
            return;
        }
        afterOperator(_beginIndex, _string, _dout, doubleDotted_, i_);
    }

    private static void ternEnd(int _beginIndex, String _string, Delimiters _dout, ResultAfterOperators _out) {
        right(_beginIndex, _string, _dout, _out, BEGIN_TERNARY);
    }

    private static void right(int _beginIndex, String _string, Delimiters _dout, ResultAfterOperators _out, char _begin) {
        StackOperators parsBrackets_;
        parsBrackets_ = _out.getParsBrackets();

        ResultAfterInstKeyWord doubleDotted_ = _out.getDoubleDotted();
        int i_ = doubleDotted_.getNextIndex();
        if (parsBrackets_.isEmpty()) {
            _dout.setBadOffset(i_);
            return;
        }
        if (parsBrackets_.lastValue() != _begin) {
            _dout.setBadOffset(i_);
            return;
        }
        parsBrackets_.removeLast();
        afterOperator(_beginIndex, _string, _dout, doubleDotted_, i_);
    }

    private static void ternBeg(int _beginIndex, String _string, Delimiters _dout, ResultAfterOperators _out) {

        stack(_beginIndex,_string,_dout,_out);
    }

    private static void arrRight(int _beginIndex, String _string, Delimiters _dout, ResultAfterOperators _out) {
        right(_beginIndex, _string, _dout, _out, ARR_LEFT);
    }

    private static void arrLeft(int _beginIndex, String _string, Delimiters _dout, ResultAfterOperators _out) {
        ResultAfterInstKeyWord doubleDotted_ = _out.getDoubleDotted();
        int i_ = doubleDotted_.getNextIndex();
        int j_ = i_ + 1;
        SkipArPart s_ = new SkipArPart();
        s_.skip(j_, _string);
        j_ = s_.getIndex();
        boolean skip_ = s_.isSkip();
        if (skip_) {
            _dout.getDimsAddonIndexes().add(i_);
            _dout.getDimsAddonIndexes().add(j_);
            doubleDotted_.setNextIndex(j_ + 1);
            _dout.setEnabledOp(true);
            return;
        }
        stack(_beginIndex, _string, _dout, _out);
    }

    private static void annArrRight(int _beginIndex, String _string, Delimiters _dout, ResultAfterOperators _out) {
        StackOperators parsBrackets_;
        parsBrackets_ = _out.getParsBrackets();
        if (parsBrackets_.isEmpty() && _out.isPartOfString()) {
            int len_ = _string.length();
            ResultAfterInstKeyWord doubleDotted_ = _out.getDoubleDotted();
            int i_ = doubleDotted_.getNextIndex();
            _out.setPartOfString(false);
            _dout.setIndexEnd(i_ -1);
            doubleDotted_.setNextIndex(len_);
            return;
        }
        right(_beginIndex,_string,_dout,_out,ANN_ARR_LEFT);
    }

    private static void annArrLeft(int _beginIndex, String _string, Delimiters _dout, AnalyzedPageEl _page, ResultAfterOperators _out) {
        ResultAfterInstKeyWord doubleDotted_ = _out.getDoubleDotted();
        int i_ = doubleDotted_.getNextIndex();
        AnonymousResult anon_ = anon(i_, _page);
        if (anon_ != null) {
            doubleDotted_.setNextIndex(anon_.getNext());
            _dout.setEnabledOp(true);
            return;
        }
        stack(_beginIndex, _string, _dout, _out);
    }

    private static void parRight(int _beginIndex, String _string, Delimiters _dout, ResultAfterOperators _out) {
        right(_beginIndex, _string, _dout, _out, PAR_LEFT);
    }

    private static void parLeft(int _beginIndex, String _string, Delimiters _dout, FieldRetriever _ret, AnalyzedPageEl _page, ResultAfterOperators _out) {
        ResultAfterInstKeyWord doubleDotted_ = _out.getDoubleDotted();
        int i_ = doubleDotted_.getNextIndex();
        StackDelimiters stack_ = _dout.getStack();
        AnonymousResult foundAn_ = anon(i_, _page);
        if (foundAn_ != null) {
            doubleDotted_.setNextIndex(foundAn_.getNext());
            _dout.setEnabledOp(true);
            return;
        }
        int indexParRight_ = _string.indexOf(PAR_RIGHT, i_ +1);
        if (indexParRight_ < 0) {
            stack_.getCallings().add(i_);
            stack(_beginIndex, _string, _dout, _out);
            return;
        }
        if (_dout.getStack().getCallings().containsObj(i_)) {
            stack_.getCallings().add(i_);
            stack(_beginIndex, _string, _dout, _out);
            return;
        }
        int afterLeftPar_ = i_ + 1;
        if (!delStrs(afterLeftPar_, indexParRight_, _page) && indLoopVar(_string, afterLeftPar_, indexParRight_) && noWideInternDelimiter(intern(_string, afterLeftPar_, indexParRight_))) {
            _dout.getDelLoopVars().add(i_);
            _dout.getDelLoopVars().add(indexParRight_);
            _dout.setEnabledOp(true);
            doubleDotted_.setNextIndex(indexParRight_+1);
            return;
        }
        int next_ = StringExpUtil.nextPrintChar(indexParRight_+1,_string.length(),_string);
        if (noCast(_string,next_,_page)) {
            stack_.getCallings().add(i_);
            stack(_beginIndex, _string, _dout, _out);
            return;
        }
        String sub_ = _string.substring(afterLeftPar_, indexParRight_);
        String subTrim_ = sub_.trim();
        int off_ = StringUtil.getFirstPrintableCharIndex(sub_);
        int beginWord_ = afterLeftPar_ + off_;
        if (StringExpUtil.isDollarWordChar(_string.charAt(beginWord_))&&AnaPartTypeUtil.isCorrectType(subTrim_, new StringList())) {
            if (!isAlwaysType(_string, subTrim_, next_)) {
                int inc_ = incrAfterWord(beginWord_, _string);
                String word_ = _string.substring(beginWord_, inc_);
                int n_ = _ret.tryGetVarField(beginWord_, word_, inc_);
                if (n_ > beginWord_) {
                    stack_.getCallings().add(i_);
                    stack(_beginIndex, _string, _dout, _out);
                    doubleDotted_.setNextIndex(n_);
                    return;
                }
            }
            AnaResultPartType resType_ = ResolvingTypes.resolveCorrectTypeWithoutErrorsExact(beginWord_, subTrim_, _page);
            if (resType_.isOk()) {
                _dout.getDelCast().add(i_);
                _dout.getDelCast().add(indexParRight_);
                String typeOut_ = resType_.getResult();
                _dout.getDelCastExtract().add(typeOut_);
                _dout.getCastParts().add(resType_);
                doubleDotted_.setNextIndex(indexParRight_+1);
                return;
            }
        }
        stack_.getCallings().add(i_);
        stack(_beginIndex, _string, _dout, _out);
    }

    private static void stack(int _beginIndex, String _string, Delimiters _dout, ResultAfterOperators _out) {
        StackOperators parsBrackets_;
        parsBrackets_ = _out.getParsBrackets();
        ResultAfterInstKeyWord doubleDotted_ = _out.getDoubleDotted();
        int i_ = doubleDotted_.getNextIndex();
        char curChar_ = _string.charAt(i_);
        parsBrackets_.addEntry(i_, curChar_);
        afterOperator(_beginIndex, _string, _dout, doubleDotted_, i_);
    }

    private static void annot(String _string, Delimiters _dout, ResultAfterInstKeyWord _doubleDotted) {
        int len_ = _string.length();
        int i_ = _doubleDotted.getNextIndex();
        StackDelimiters stack_ = _dout.getStack();
        int j_ = i_ + 1;
        int last_ = i_;
        while (j_ < len_) {
            char locChar_ = _string.charAt(j_);
            if (StringExpUtil.isTypeLeafChar(locChar_) || locChar_ == DOT_VAR) {
                last_ = j_;
                j_++;
            } else  if (StringUtil.isWhitespace(locChar_)) {
                j_++;
            } else {
                break;
            }
        }
        if (StringExpUtil.nextCharIs(_string,j_, len_,PAR_LEFT)) {
            stack_.getCallings().add(j_);
        } else {
            _dout.getDelSimpleAnnotations().add(i_);
            _dout.getDelSimpleAnnotations().add(last_);
        }
        _dout.setEnabledOp(true);
        _doubleDotted.setNextIndex(j_);
    }

    private static void afterOperator(int _beginIndex, String _string, Delimiters _dout, ResultAfterInstKeyWord _doubleDotted, int _i) {
        IncrOperatorPart incr_ = new IncrOperatorPart(_dout.isEnabledOp());
        int nextIndex_ = incr_.tryAddOp(_beginIndex, _string, _i);
        int indexOp_ = incr_.getIndexOp();
        if (indexOp_ > -1) {
            _dout.getAllowedOperatorsIndexes().add(indexOp_);
        }
        _doubleDotted.setNextIndex(nextIndex_);
        _dout.setEnabledOp(incr_.isEnabledOp());
    }

    private static boolean noWideInternDelimiter(String _substring) {
        return StringExpUtil.noDel(_substring);
    }

    private static boolean isAcceptCommaInstr(AnalyzedPageEl _page) {
        return !_page.isAcceptCommaInstr() && !(_page.getCurrentBlock() instanceof FieldBlock);
    }

    private static IndexUnicodeEscape processStringsDelText(String _string, int _max, IndexUnicodeEscape _infos) {
        int i_ = _infos.getIndex();
        int nbChars_ = _infos.getNbChars();
        char curChar_ = _string.charAt(i_);
        IndexUnicodeEscape infos_ = buildState(_infos);
        if (curChar_ == DELIMITER_TEXT) {
            if (i_ + 1 >= _max ||_string.charAt(i_ + 1) != DELIMITER_TEXT) {
                infos_.setPart(false);
                infos_.setIndex(i_+1);
                return infos_;
            }
            infos_.setIndex(i_+2);
        } else {
            infos_.setIndex(i_+1);
        }
        infos_.getStringInfo().appendChar(curChar_);
        infos_.setNbChars(nbChars_+1);
        return infos_;
    }
    private static IndexUnicodeEscape processStrings(KeyWords _key, String _string, int _max, TextBlockInfo _si, IndexUnicodeEscape _infos, char _delimiter) {
        int i_ = _infos.getIndex();
        int nbChars_ = _infos.getNbChars();
        int unicode_ = _infos.getUnicode();
        char curChar_ = _string.charAt(i_);
        boolean escapedMeta_ = _infos.isEscape();
        IndexUnicodeEscape infos_ = buildState(_infos);
        if (!escapedMeta_) {
            if (curChar_ == ESCAPE_META_CHAR) {
                if (i_ + 1 >= _max) {
                    _si.setKo();
                    infos_.setIndex(i_+1);
                    return infos_;
                }
                infos_.setEscape(true);
                infos_.setIndex(i_+1);
                return infos_;
            }
            if (curChar_ == _delimiter) {
                infos_.setPart(false);
                infos_.setIndex(i_+1);
                return infos_;
            }
            infos_.getStringInfo().appendChar(curChar_);
            infos_.setNbChars(nbChars_+1);
            infos_.setIndex(i_+1);
            return infos_;
        }
        if (unicode_ > 0) {
            unicode(_key, _si, i_, nbChars_, unicode_, curChar_, infos_);
            return infos_;
        }
        return escOthStrings(_infos,_key, _string, _max, _si, _delimiter, infos_);
    }

    private static IndexUnicodeEscape buildState(IndexUnicodeEscape _infos) {
        IndexUnicodeEscape infos_ = new IndexUnicodeEscape();
        infos_.setStringInfo(_infos.getStringInfo());
        infos_.setEscape(_infos.isEscape());
        infos_.setIndex(_infos.getIndex());
        infos_.setNbChars(_infos.getNbChars());
        infos_.setUnicode(_infos.getUnicode());
        infos_.setPart(_infos.isPart());
        return infos_;
    }

    private static IndexUnicodeEscape escOthStrings(IndexUnicodeEscape _infos, KeyWords _key, String _string, int _max, TextBlockInfo _si, char _delimiter, IndexUnicodeEscape _out) {
        int i_ = _infos.getIndex();
        int nbChars_ = _infos.getNbChars();
        char curChar_ = _string.charAt(i_);
        if (curChar_ == LINE_RETURN) {
            _out.setEscape(false);
            _out.setIndex(i_+1);
            return _out;
        }
        TextBlockInfo textInfo_ = _out.getStringInfo();
        if (curChar_ == _delimiter) {
            _out.setNbChars(nbChars_+1);
            _out.setEscape(false);
            textInfo_.appendChar(curChar_);
            _out.setIndex(i_+1);
            return _out;
        }
        String single_ = _key.getEscKeyWord(_string, i_);
        if (single_ != null) {
            _out.setNbChars(nbChars_+1);
            _out.setEscape(false);
            _out.setIndex(appEsc(i_,_key, single_, textInfo_));
            return _out;
        }
        if (curChar_ == ESCAPE_META_CHAR) {
            textInfo_.appendChar(ESCAPE_META_CHAR);
            _out.setNbChars(nbChars_+1);
            _out.setEscape(false);
            _out.setIndex(i_+1);
            return _out;
        }
        return othEsc(_key,_infos, _string, _max, _si, _out, textInfo_);
    }

    private static IndexUnicodeEscape othEsc(KeyWords _key, IndexUnicodeEscape _infos, String _string, int _max, TextBlockInfo _si, IndexUnicodeEscape _out, TextBlockInfo _infTx) {
        int i_ = _infos.getIndex();
        int nbChars_ = _infos.getNbChars();
        char curChar_ = _string.charAt(i_);
        String unicodeStr_ = _key.getKeyWordEscUnicode();
        if (!_string.startsWith(unicodeStr_, i_) || i_ + unicodeStr_.length() + UNICODE_SIZE > _max) {
            _si.setKo();
            _infTx.appendChar(curChar_);
            _out.setNbChars(nbChars_ +1);
            _out.setEscape(false);
            _out.setIndex(i_ +1);
            return _out;
        }
        _out.setUnicode(1);
        _out.setIndex(i_+unicodeStr_.length());
        return _out;
    }

    private static int appEsc(int _i, KeyWords _key, String _single, TextBlockInfo _textInfo) {
        String newLine_ = _key.getKeyWordEscLine();
        String form_ = _key.getKeyWordEscForm();
        String rfeed_ = _key.getKeyWordEscFeed();
        String space_ = _key.getKeyWordEscSpace();
        String tab_ = _key.getKeyWordEscTab();
        String bound_ = _key.getKeyWordEscBound();
        int i_;
        if (StringUtil.quickEq(_single, newLine_)) {
            i_ = _i + newLine_.length();
            _textInfo.appendChar(LINE_RETURN);
        } else if (StringUtil.quickEq(_single, form_)) {
            i_ = _i + form_.length();
            _textInfo.appendChar(FORM_FEED);
        } else if (StringUtil.quickEq(_single, rfeed_)) {
            i_ = _i + rfeed_.length();
            _textInfo.appendChar(LINE_FEED);
        } else if (StringUtil.quickEq(_single, tab_)) {
            i_ = _i + tab_.length();
            _textInfo.appendChar(TAB);
        } else if (StringUtil.quickEq(_single, space_)) {
            i_ = _i + tab_.length();
            _textInfo.appendChar(SPACE);
        } else {
            i_ = _i + bound_.length();
            _textInfo.appendChar(BOUND);
        }
        return i_;
    }

    private static char trUnicodeDigToLetterInStr(KeyWords _key, TextBlockInfo _si, char _curChar) {
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
        infos_.setStringInfo(_infos.getStringInfo());
        infos_.setEscape(escapedMeta_);
        infos_.setIndex(i_);
        infos_.setNbChars(nbChars_);
        infos_.setUnicode(unicode_);
        infos_.setPart(_infos.isPart());
        if (!StringUtil.isWhitespace(curChar_)&&!infos_.getStringInfo().isLine()) {
            infos_.getStringInfo().setKo();
        }
        if (!escapedMeta_) {
            return prEscText(_infos,_string, _max, _si, _delimiter, infos_);
        }
        infos_.getStringInfo().setLastSpace(-1);
        if (unicode_ > 0) {
            if (unicode(_key, _si, i_, nbChars_, unicode_, curChar_, infos_)) {
                infos_.getStringInfo().setPrintable(true);
            }
            return infos_;
        }
        if (curChar_ == _delimiter) {
            infos_.setNbChars(nbChars_+1);
            infos_.setEscape(false);
            infos_.getStringInfo().appendChar(curChar_);
            infos_.getStringInfo().setPrintable(true);
            infos_.setIndex(i_+1);
            return infos_;
        }
        if (curChar_ == LINE_RETURN) {
            infos_.setEscape(false);
            infos_.setIndex(i_+1);
            infos_.getStringInfo().setPrintable(true);
            return infos_;
        }
        return procOthEsBlock(_infos,_key, _string, _max, _si, infos_);
    }

    private static boolean unicode(KeyWords _key, TextBlockInfo _si, int _i, int _nbChars, int _unicode, char _curChar, IndexUnicodeEscape _infos) {
        boolean pr_;
        char charToAdd_ = trUnicodeDigToLetterInStr(_key, _si, _curChar);
        _infos.getStringInfo().getBuiltUnicode()[_unicode -1] = charToAdd_;
        if (_unicode < UNICODE_SIZE) {
            _infos.setNbChars(_nbChars);
            _infos.setEscape(true);
            _infos.setUnicode(_unicode +1);
            pr_ = false;
        } else {
            char[] unicodes_ = _infos.getStringInfo().getBuiltUnicode();
            char builtChar_ = NumParsers.parseCharSixteen(String.valueOf(unicodes_));
            _infos.getStringInfo().appendChar(builtChar_);
            _infos.setNbChars(_nbChars +1);
            _infos.setEscape(false);
            _infos.setUnicode(0);
            pr_ = true;
        }
        _infos.setIndex(_i +1);
        return pr_;
    }

    private static IndexUnicodeEscape prEscText(IndexUnicodeEscape _infos, String _string, int _max, TextBlockInfo _si, char _delimiter, IndexUnicodeEscape _out) {
        int i_ = _infos.getIndex();
        int nbChars_ = _infos.getNbChars();
        char curChar_ = _string.charAt(i_);
        if (curChar_ == ESCAPE_META_CHAR) {
            if (i_ + 1 >= _max) {
                _si.setKo();
                _out.setIndex(i_+1);
                return _out;
            }
            _out.setEscape(true);
            _out.setIndex(i_+1);
            return _out;
        }
        if (curChar_ == _delimiter
                && StringExpUtil.nextCharIs(_string, i_ +1, _string.length(), _delimiter)
                && StringExpUtil.nextCharIs(_string, i_ +2, _string.length(), _delimiter)) {
            removeRightSpaces(_out);
            _out.setPart(false);
            _out.setIndex(i_+3);
            return _out;
        }
        if (curChar_ == LINE_RETURN) {
            boolean line_ = _out.getStringInfo().isLine();
            _out.getStringInfo().setLine(true);
            removeRightSpaces(_out);
            if (line_) {
                _out.getStringInfo().appendChar(curChar_);
                _out.setNbChars(nbChars_+1);
            }
            _out.getStringInfo().setPrintable(false);
            _out.getStringInfo().setLastSpace(-1);
        } else if (StringUtil.isWhitespace(curChar_)) {
            if (_out.getStringInfo().isPrintable()) {
                _out.getStringInfo().setLastSpace(_out.getStringInfo().length());
                _out.getStringInfo().appendChar(curChar_);
                _out.setNbChars(nbChars_+1);
            }
        } else {
            _out.getStringInfo().setLastSpace(-1);
            _out.getStringInfo().appendChar(curChar_);
            _out.getStringInfo().setPrintable(true);
            _out.setNbChars(nbChars_+1);
        }
        _out.setIndex(i_+1);
        return _out;
    }

    private static void removeRightSpaces(IndexUnicodeEscape _out) {
        int lastSpace_ = _out.getStringInfo().getLastSpace();
        if (lastSpace_ > -1) {
            int lastIndex_ = _out.getStringInfo().length()-1;
            for (int i = lastIndex_; i >= lastSpace_; i--) {
                _out.getStringInfo().remove(i);
            }
        }
    }

    private static IndexUnicodeEscape procOthEsBlock(IndexUnicodeEscape _infos, KeyWords _key, String _string, int _max, TextBlockInfo _si, IndexUnicodeEscape _out) {
        int i_ = _infos.getIndex();
        int nbChars_ = _infos.getNbChars();
        char curChar_ = _string.charAt(i_);
        TextBlockInfo textInfo_ = _out.getStringInfo();
        String single_ = _key.getEscKeyWord(_string, i_);
        if (single_ != null) {
            _out.setNbChars(nbChars_+1);
            _out.setEscape(false);
            _out.setIndex(appEsc(i_, _key, single_, textInfo_));
            textInfo_.setPrintable(true);
            return _out;
        }
        if (curChar_ == ESCAPE_META_CHAR) {
            textInfo_.appendChar(ESCAPE_META_CHAR);
            textInfo_.setPrintable(true);
            _out.setNbChars(nbChars_+1);
            _out.setEscape(false);
            _out.setIndex(i_+1);
            return _out;
        }
        return othEsc(_key,_infos, _string, _max, _si, _out, textInfo_);
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
        for (AnonymousResult a: _page.getCurrentAnonymousResults()) {
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
            TextBlockInfo info_ = _d.getStringInfo().get(beginTx_/2);
            String export_ = info_.build();
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.TEXT_BLOCK);
            op_.setOperators(new StrTypes());
            op_.setStrInfo(info_);
            info_.setFound(_string);
            op_.setValue(export_, _firstPrintChar);
            op_.setLength(_d.getDelTextBlocks().get(beginTx_+1)-_offset+1-_firstPrintChar);
            return op_;
        }
        return null;
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

    private static boolean noCast(String _string, int _next, AnalyzedPageEl _page) {
        if (_next < 0 || StringExpUtil.startsWithKeyWord(_string, _next, _page.getKeyWords().getKeyWordInstanceof())) {
            return true;
        }
        for (String s: StringUtil.wrapStringArray("+=","-=",
                "*=","/","%",
                "^","&","|",
                "?",":",
                "<",">",",","->",
                "!=","=",")","[","]","}")) {
            if (_string.startsWith(s,_next)) {
                return true;
            }
        }
        if (_string.startsWith(".",_next)) {
            int n_ = StringExpUtil.nextPrintChar(_next + 1, _string.length(), _string);
            return !ElResolverCommon.isDigitOrDot(_string, n_);
        }
        return false;
    }

    private static String intern(String _string, int _afterLeftPar, int _indexParRight) {
        String sub_ = _string.substring(_afterLeftPar, _indexParRight);
        String subTrim_ = sub_.trim();
        return subTrim_.substring(1, subTrim_.length() - 1);
    }

    private static boolean indLoopVar(String _string, int _afterLeftPar, int _indexParRight) {
        return StringExpUtil.nextCharIs(_string,StringExpUtil.nextPrintChar(_afterLeftPar,_indexParRight,_string),_indexParRight,ARR_LEFT)
        &&StringExpUtil.nextCharIs(_string,StringExpUtil.getBackPrintChar(_string,_indexParRight,_afterLeftPar),_indexParRight,ARR_RIGHT);
    }
    private static boolean delStrs(int _afterLeftPar, int _indexParRight, AnalyzedPageEl _page) {
        for (int j = _afterLeftPar; j < _indexParRight; j++) {
            for (SegmentStringPart s: _page.getCurrentParts()) {
                if (s.getBegin() == j) {
                    return true;
                }
            }
        }
        return false;
    }

    private static AnonymousResult anon(int _from, AnalyzedPageEl _page) {
        for (AnonymousResult r:_page.getCurrentAnonymousResults()) {
            if (r.getIndex() == _from) {
                return r;
            }
        }
        return null;
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
