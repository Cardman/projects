package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.instr.DefaultProcessKeyWord;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class ParsedFctHeader extends ParsedFctHeaderAbs{
    private static final String SEP_CALLING = ",";
    private static final String END_CALLING = ")";
    private static final String ANNOT = "@";
    private static final String ANON_RETURN_PART = ":";
    private static final String ARROW = "->";
    private final ResultParsedAnnots annotations = new ResultParsedAnnots();
    private ResultParsedAnnots annotationsSupp = new ResultParsedAnnots();
    private final CustList<ResultParsedAnnots> annotationsParams = new CustList<ResultParsedAnnots>();
    private String info = "";
    private boolean ok = true;
    private int offsetLast;
    private int nextIndex;
    private int beforeArrowIndex;
    private int arrowPlusSpaceAfter;
    private boolean afterArrowLeftBrace;
    private boolean indexerSet;
    private String keyWordValue="";
    private int typeSetterOff;
    private String typeSetter="";

    public void parse(CustList<SegmentStringPart> _parts, String _info, int _sum, KeyWords _keyWords) {
        String keyWordThat_ = _keyWords.getKeyWordThat();
        parse(_parts,_info, keyWordThat_, _sum);
    }

    public void parse(CustList<SegmentStringPart> _parts, String _info, int _sum, boolean _indexer, KeyWords _keyWords) {
        String keyWordThat_ = _keyWords.getKeyWordThat();
        indexerSet = _indexer;
        keyWordValue = _keyWords.getKeyWordValue();
        parse(_parts,_info, keyWordThat_, _sum);
    }

    public static boolean isIndexerSet(boolean _meth, String _retType, String _methName, KeyWords _keyWords, String _aliasVoid) {
        return _meth && StringUtil.quickEq(_retType, _aliasVoid) && StringUtil.quickEq(_methName, _keyWords.getKeyWordThis());
    }
    public void parse(CustList<SegmentStringPart> _parts,String _info, String _keyWordThat, int _sum) {
        offsetLast = _sum;
        if (_info.startsWith(END_CALLING)) {
            offsetLast++;
            String sub_ = _info.substring(1);
            offsetLast += StringUtil.getFirstPrintableCharIndex(sub_);
            info = sub_.trim();
            return;
        }
        int len_ = _info.length();
        int j_ = DefaultProcessKeyWord.skipWhiteSpace(_info, 0);
        while (j_ < len_) {
            int k_ = j_;
            ResultParsedAnnots annotationsParam_ = new ResultParsedAnnots();
            if (_info.startsWith(ANNOT,k_)) {
                ParsedAnnotations par_ = new ParsedAnnotations(_info.substring(k_), k_+ offsetLast);
                par_.parse(_parts);
                annotationsParam_.set(par_);
                k_ = DefaultProcessKeyWord.skipWhiteSpace(_info,par_.getIndex() - offsetLast);
            }
            BoolVal ref_ = ref(_info,k_,_keyWordThat);
            k_ = afterRef(_info,k_,_keyWordThat,ref_);
            int typeOff_ = k_;
            String paramType_ = FileResolver.getFoundType(_info.substring(k_));
            k_ = DefaultProcessKeyWord.skipWhiteSpace(_info,k_+paramType_.length());
            int implCall_ = _info.indexOf(SEP_CALLING, k_);
            int implStopRightPar_ = _info.indexOf(END_CALLING, k_);
            if (implStopRightPar_ < 0) {
                ok = false;
                return;
            }
            implCall_ = gt(implCall_,implStopRightPar_);
            int parOff_ = k_;
            String varName_ = _info.substring(k_, implCall_).trim();
            if (isNotImplParam(implStopRightPar_,implCall_,varName_)) {
                feedPar(annotationsParam_, ref_, typeOff_, paramType_, parOff_, varName_);
            } else {
                typeSetterOff = typeOff_ + offsetLast;
                typeSetter = paramType_.trim();
                annotationsSupp = annotationsParam_;
            }
            if (implStopRightPar_ == implCall_) {
                int afterRightPar_ = implStopRightPar_ + 1;
                String sub_ = _info.substring(afterRightPar_);
                int afterRightParOffset_ = afterRightPar_+ StringUtil.getFirstPrintableCharIndex(sub_);
                info = sub_.trim();
                offsetLast += afterRightParOffset_;
                break;
            }
            j_=DefaultProcessKeyWord.skipWhiteSpace(_info,implCall_+1);
        }
    }

    private void feedPar(ResultParsedAnnots _annotationsParam, BoolVal _ref, int _typeOff, String _paramType, int _parOff, String _varName) {
        feedParAnnot(_annotationsParam);
        feedParBase(_ref, _typeOff, offsetLast, _paramType.trim(), _parOff, _varName);
    }

    private void feedParAnnot(ResultParsedAnnots _annotationsParam) {
        this.annotationsParams.add(_annotationsParam);
    }

    private void feedParBase(BoolVal _ref, int _typeOff, int _offsetLast, String _paramType, int _parOff, String _varName) {
        getParametersRef().add(_ref);
        getOffestsTypes().add(_typeOff + _offsetLast);
        getParametersType().add(_paramType);
        getOffestsParams().add(_parOff + _offsetLast);
        getParametersName().add(_varName);
    }

    private boolean isNotImplParam(int _implStopRightPar,int _implCall,String _varName) {
        return _implStopRightPar != _implCall||!indexerSet || !StringUtil.quickEq(keyWordValue, _varName);
    }
    private static int gt(int _implCall, int _implStopRightPar) {
        if (_implCall < 0) {
            return _implStopRightPar;
        }
        return Math.min(_implCall,_implStopRightPar);
    }

    public void parseAnonymous(CustList<SegmentStringPart> _parts,int _indexLeftPar, String _string, int _offset, String _keyWordThat) {
        int len_ = _string.length();
        int j_ = DefaultProcessKeyWord.skipWhiteSpace(_string, _indexLeftPar +1);
        if (_string.startsWith(END_CALLING,j_)) {
            processImplicitRetType(_indexLeftPar, _string, _offset, j_);
            return;
        }
        if (_string.startsWith(ANON_RETURN_PART,j_)) {
            processExplicitRetType(_indexLeftPar, _string, _offset, _keyWordThat, j_,_parts);
            return;
        }
        while (j_ < len_) {
            int k_ = j_;
            ResultParsedAnnots annotationsParam_ = new ResultParsedAnnots();
            if (_string.startsWith(ANNOT,k_)) {
                ParsedAnnotations par_ = new ParsedAnnotations(_string.substring(k_), k_+_offset);
                par_.parse(_parts);
                annotationsParam_.set(par_);
                k_ = DefaultProcessKeyWord.skipWhiteSpace(_string,par_.getIndex() - _offset);
            }
            BoolVal ref_ = ref(_string,k_,_keyWordThat);
            k_ = afterRef(_string,k_,_keyWordThat,ref_);
            ParsedType p_ = new ParsedType();
            p_.parse(_string.substring(k_));
            int typeOff_ = k_;
            boolean okType_ = p_.isOk(new StringList());
            if (okType_) {
                k_ = DefaultProcessKeyWord.skipWhiteSpace(_string,k_+p_.getInstruction().length());
            }
            int implCall_ = _string.indexOf(SEP_CALLING, k_);
            int implStopInd_ = _string.indexOf(ANON_RETURN_PART, k_);
            int implStopRightPar_ = _string.indexOf(END_CALLING, k_);
            if (implStopRightPar_ < 0) {
                nextIndex = _indexLeftPar;
                return;
            }
            implCall_ = implCallAnon(implCall_,implStopInd_,implStopRightPar_);
            int parOff_ = k_;
            String varName_ = _string.substring(k_, implCall_).trim();
            if (!StringExpUtil.isTypeLeafPart(varName_)) {
                nextIndex = _indexLeftPar;
                return;
            }
            feedParAnnot(annotationsParam_);
            String candid_ = candid(p_, okType_);
            feedParBase(ref_, typeOff_, _offset, candid_, parOff_, varName_);
            if (implStopInd_ == implCall_) {
                processExplicitRetType(_indexLeftPar,_string,_offset,_keyWordThat,implCall_,_parts);
                return;
            }
            if (implStopRightPar_ == implCall_) {
                processImplicitRetType(_indexLeftPar,_string,_offset,implCall_);
                return;
            }
            j_=DefaultProcessKeyWord.skipWhiteSpace(_string,implCall_+1);
        }
        nextIndex = _indexLeftPar;
    }

    private String candid(ParsedType _p, boolean _okType) {
        String candid_;
        if (_okType) {
            candid_ = _p.getInstruction().toString();
        } else {
            candid_ = "";
        }
        return candid_;
    }

    private static BoolVal ref(String _string, int _current, String _keyWordThat) {
        if (StringExpUtil.startsWithKeyWord(_string,_current,_keyWordThat)) {
            return BoolVal.TRUE;
        }
        return BoolVal.FALSE;
    }
    private static int afterRef(String _string, int _current, String _keyWordThat, BoolVal _ref) {
        if (_ref == BoolVal.TRUE) {
            int k_ = _current+_keyWordThat.length();
            return DefaultProcessKeyWord.skipWhiteSpace(_string,k_);
        }
        return _current;
    }
    private static int implCallAnon(int _implCall, int _implStopInd, int _implStopRightPar) {
        int implCall_ = _implCall;
        if (implCall_ < 0) {
            if (_implStopInd >= 0) {
                implCall_ = Math.min(_implStopInd,_implStopRightPar);
            } else {
                implCall_ = _implStopRightPar;
            }
        } else {
            if (_implStopInd >= 0) {
                implCall_ = Math.min(implCall_,Math.min(_implStopRightPar, _implStopInd));
            } else {
                implCall_ = Math.min(implCall_,_implStopRightPar);
            }
        }
        return implCall_;
    }

    private void processImplicitRetType(int _indexLeftPar, String _string, int _offset, int _j) {
        int k_ = DefaultProcessKeyWord.skipWhiteSpace(_string, _j +1);
        if (!_string.startsWith(ARROW,k_)) {
            nextIndex = _indexLeftPar;
            return;
        }
        setReturnOffest(_j + _offset);
        setReturnType("");
        nextIndex = _j;
        initLeftBrace(_string, k_);
    }

    private void processExplicitRetType(int _indexLeftPar, String _string, int _offset, String _keyWordThat, int _j, CustList<SegmentStringPart> _parts) {
        int k_ = DefaultProcessKeyWord.skipWhiteSpace(_string, _j +1);
        if (_string.startsWith(ANNOT,k_)) {
            ParsedAnnotations par_ = new ParsedAnnotations(_string.substring(k_), k_+ _offset);
            par_.parse(_parts);
            annotations.set(par_);
            k_ = DefaultProcessKeyWord.skipWhiteSpace(_string,par_.getIndex()- _offset);
        }
        if (StringExpUtil.startsWithKeyWord(_string,k_, _keyWordThat)) {
            k_ += _keyWordThat.length();
            k_ = DefaultProcessKeyWord.skipWhiteSpace(_string,k_);
            setRetRef(true);
        }
        int nextRightPar_ = _string.indexOf(END_CALLING,k_);
        if (nextRightPar_ < 0) {
            nextIndex = _indexLeftPar;
            return;
        }
        int until_ = nextRightPar_;
        nextRightPar_ = DefaultProcessKeyWord.skipWhiteSpace(_string,nextRightPar_+1);
        if (!_string.startsWith(ARROW,nextRightPar_)) {
            nextIndex = _indexLeftPar;
            return;
        }
        setReturnOffest(k_ + _offset);
        nextIndex = until_;
        initLeftBrace(_string, nextRightPar_);
        setReturnType(_string.substring(k_, until_));
    }

    private void initLeftBrace(String _string, int _beforeArrow) {
        beforeArrowIndex = _beforeArrow;
        int afterArrow_ = _beforeArrow + 2;
        int sk_ = DefaultProcessKeyWord.skipWhiteSpace(_string, afterArrow_);
        afterArrowLeftBrace = StringExpUtil.nextCharIs(_string,sk_, _string.length(),'{');
        arrowPlusSpaceAfter = sk_ - _beforeArrow;
    }

    public String getInfo() {
        return info;
    }

    public boolean isOk() {
        return ok;
    }

    public ResultParsedAnnots getAnnotations() {
        return annotations;
    }

    public CustList<ResultParsedAnnots> getAnnotationsParams() {
        return annotationsParams;
    }

    public ResultParsedAnnots getAnnotationsSupp() {
        return annotationsSupp;
    }

    public int getOffsetLast() {
        return offsetLast;
    }

    public int getTypeSetterOff() {
        return typeSetterOff;
    }

    public String getTypeSetter() {
        return typeSetter;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public boolean isAfterArrowLeftBrace() {
        return afterArrowLeftBrace;
    }

    public int getBeforeArrowIndex() {
        return beforeArrowIndex;
    }

    public int getArrowPlusSpaceAfter() {
        return arrowPlusSpaceAfter;
    }

}
