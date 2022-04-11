package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.DefaultProcessKeyWord;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class ParsedFctHeader extends ParsedFctHeaderAbs{
    private static final String SEP_CALLING = ",";
    private static final String END_CALLING = ")";
    private static final String ANNOT = "@";
    private static final String ANON_RETURN_PART = ":";
    private static final String ARROW = "->";
    private final Ints annotationsIndexes = new Ints();
    private final StringList annotations = new StringList();
    private Ints annotationsIndexesSupp = new Ints();
    private StringList annotationsSupp = new StringList();
    private final CustList<Ints> annotationsIndexesParams = new CustList<Ints>();
    private final CustList<StringList> annotationsParams = new CustList<StringList>();
    private String info = "";
    private boolean ok = true;
    private int offsetLast;
    private int nextIndex;
    private String afterArrow="";
    private boolean indexerSet;
    private String keyWordValue="";
    private int typeSetterOff;
    private String typeSetter="";

    public void parse(String _info, AnalyzedPageEl _parse, int _sum) {
        KeyWords keyWords_ = _parse.getKeyWords();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        parse(_info, keyWordThat_, _sum);
    }

    public void parse(boolean _meth,String _retType, String _methName, String _info, AnalyzedPageEl _parse, int _sum) {
        KeyWords keyWords_ = _parse.getKeyWords();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        indexerSet = isIndexerSet(_meth,_retType,_methName, _parse);
        keyWordValue = keyWords_.getKeyWordValue();
        parse(_info, keyWordThat_, _sum);
    }

    private static boolean isIndexerSet(boolean _meth, String _retType, String _methName, AnalyzedPageEl _parse) {
        KeyWords keyWords_ = _parse.getKeyWords();
        return _meth && StringUtil.quickEq(_retType,_parse.getAliasVoid()) && StringUtil.quickEq(_methName,keyWords_.getKeyWordThis());
    }
    public void parse(String _info, String _keyWordThat, int _sum) {
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
            Ints annotationsIndexesParam_ = new Ints();
            StringList annotationsParam_ = new StringList();
            if (_info.startsWith(ANNOT,k_)) {
                ParsedAnnotations par_ = new ParsedAnnotations(_info.substring(k_), k_+ _sum);
                par_.parse();
                annotationsIndexesParam_ = par_.getAnnotationsIndexes();
                annotationsParam_ = par_.getAnnotations();
                k_ = DefaultProcessKeyWord.skipWhiteSpace(_info,par_.getIndex() - _sum);
            }
            BoolVal ref_;
            if (StringExpUtil.startsWithKeyWord(_info,k_,_keyWordThat)) {
                k_ += _keyWordThat.length();
                k_ = DefaultProcessKeyWord.skipWhiteSpace(_info,k_);
                ref_ = BoolVal.TRUE;
            } else {
                ref_ = BoolVal.FALSE;
            }
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
            if (implStopRightPar_ == implCall_) {
                if (isNotImplParam(varName_)) {
                    feedPar(annotationsIndexesParam_, annotationsParam_, ref_, typeOff_, paramType_, parOff_, varName_);
                } else {
                    typeSetterOff = typeOff_ + _sum;
                    typeSetter = paramType_.trim();
                    annotationsIndexesSupp = annotationsIndexesParam_;
                    annotationsSupp = annotationsParam_;
                }
                int afterRightPar_ = implStopRightPar_ + 1;
                String sub_ = _info.substring(afterRightPar_);
                int afterRightParOffset_ = afterRightPar_+ StringUtil.getFirstPrintableCharIndex(sub_);
                info = sub_.trim();
                offsetLast = afterRightParOffset_+ _sum;
                break;
            }
            feedPar(annotationsIndexesParam_, annotationsParam_, ref_, typeOff_, paramType_, parOff_, varName_);
            j_=DefaultProcessKeyWord.skipWhiteSpace(_info,implCall_+1);
        }
    }

    private void feedPar(Ints annotationsIndexesParam_, StringList annotationsParam_, BoolVal ref_, int typeOff_, String paramType_, int parOff_, String varName_) {
        feedParAnnot(annotationsIndexesParam_, annotationsParam_);
        feedParBase(ref_, typeOff_, offsetLast, paramType_.trim(), parOff_, varName_);
    }

    private void feedParAnnot(Ints annotationsIndexesParam_, StringList annotationsParam_) {
        this.annotationsIndexesParams.add(annotationsIndexesParam_);
        this.annotationsParams.add(annotationsParam_);
    }

    private void feedParBase(BoolVal ref_, int typeOff_, int offsetLast, String paramType_, int parOff_, String varName_) {
        getParametersRef().add(ref_);
        getOffestsTypes().add(typeOff_ + offsetLast);
        getParametersType().add(paramType_);
        getOffestsParams().add(parOff_ + offsetLast);
        getParametersName().add(varName_);
    }

    private boolean isNotImplParam(String _par) {
        return !indexerSet || !StringUtil.quickEq(keyWordValue, _par);
    }
    private static int gt(int implCall_, int implStopRightPar_) {
        if (implCall_ < 0) {
            return implStopRightPar_;
        }
        return Math.min(implCall_,implStopRightPar_);
    }

    public void parseAnonymous(int _indexLeftPar, String _string, int _offset, String _keyWordThat) {
        int len_ = _string.length();
        int j_ = DefaultProcessKeyWord.skipWhiteSpace(_string, _indexLeftPar +1);
        if (_string.startsWith(END_CALLING,j_)) {
            processImplicitRetType(_indexLeftPar, _string, _offset, j_);
            return;
        }
        if (_string.startsWith(ANON_RETURN_PART,j_)) {
            processExplicitRetType(_indexLeftPar, _string, _offset, _keyWordThat, j_);
            return;
        }
        while (j_ < len_) {
            int k_ = j_;
            Ints annotationsIndexesParam_ = new Ints();
            StringList annotationsParam_ = new StringList();
            if (_string.startsWith(ANNOT,k_)) {
                ParsedAnnotations par_ = new ParsedAnnotations(_string.substring(k_), k_+_offset);
                par_.parse();
                annotationsIndexesParam_ = par_.getAnnotationsIndexes();
                annotationsParam_ = par_.getAnnotations();
                k_ = DefaultProcessKeyWord.skipWhiteSpace(_string,par_.getIndex() - _offset);
            }
            BoolVal ref_;
            if (StringExpUtil.startsWithKeyWord(_string,k_,_keyWordThat)) {
                k_ += _keyWordThat.length();
                k_ = DefaultProcessKeyWord.skipWhiteSpace(_string,k_);
                ref_ = BoolVal.TRUE;
            } else {
                ref_ = BoolVal.FALSE;
            }
            ParsedType p_ = new ParsedType();
            p_.parse(_string.substring(k_));
            String candid_;
            int typeOff_ = k_;
            if (p_.isOk(new StringList())) {
                candid_ = p_.getInstruction().toString();
                k_ = DefaultProcessKeyWord.skipWhiteSpace(_string,k_+candid_.length());
            } else {
                candid_ = "";
            }
            int implCall_ = _string.indexOf(SEP_CALLING, k_);
            int implStopInd_ = _string.indexOf(ANON_RETURN_PART, k_);
            int implStopRightPar_ = _string.indexOf(END_CALLING, k_);
            if (implStopRightPar_ < 0) {
                nextIndex = _indexLeftPar;
                return;
            }
            if (implCall_ < 0) {
                if (implStopInd_ >= 0) {
                    implCall_ = Math.min(implStopInd_,implStopRightPar_);
                } else {
                    implCall_ = implStopRightPar_;
                }
            } else {
                if (implStopInd_ >= 0) {
                    implCall_ = Math.min(implCall_,Math.min(implStopRightPar_, implStopInd_));
                } else {
                    implCall_ = Math.min(implCall_,implStopRightPar_);
                }
            }

            int parOff_ = k_;
            String varName_ = _string.substring(k_, implCall_).trim();
            if (!StringExpUtil.isTypeLeafPart(varName_)) {
                nextIndex = _indexLeftPar;
                return;
            }
            feedParAnnot(annotationsIndexesParam_, annotationsParam_);
            feedParBase(ref_, typeOff_, _offset, candid_, parOff_, varName_);
            if (implStopInd_ == implCall_) {
                processExplicitRetType(_indexLeftPar,_string,_offset,_keyWordThat,implCall_);
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

    private void processImplicitRetType(int _indexLeftPar, String _string, int _offset, int _j) {
        int k_ = DefaultProcessKeyWord.skipWhiteSpace(_string, _j +1);
        if (!_string.startsWith(ARROW,k_)) {
            nextIndex = _indexLeftPar;
            return;
        }
        setReturnOffest(_j + _offset);
        setReturnType("");
        nextIndex = _j;
        afterArrow = _string.substring(k_+2);
    }

    private void processExplicitRetType(int _indexLeftPar, String _string, int _offset, String _keyWordThat, int _j) {
        int k_ = DefaultProcessKeyWord.skipWhiteSpace(_string, _j +1);
        if (_string.startsWith(ANNOT,k_)) {
            ParsedAnnotations par_ = new ParsedAnnotations(_string.substring(k_), k_+ _offset);
            par_.parse();
            annotationsIndexes.addAllElts(par_.getAnnotationsIndexes());
            annotations.addAllElts(par_.getAnnotations());
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
        afterArrow = _string.substring(nextRightPar_+2);
        setReturnType(_string.substring(k_, until_));
    }

    public String getInfo() {
        return info;
    }

    public boolean isOk() {
        return ok;
    }

    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }

    public StringList getAnnotations() {
        return annotations;
    }

    public CustList<Ints> getAnnotationsIndexesParams() {
        return annotationsIndexesParams;
    }

    public CustList<StringList> getAnnotationsParams() {
        return annotationsParams;
    }

    public Ints getAnnotationsIndexesSupp() {
        return annotationsIndexesSupp;
    }

    public StringList getAnnotationsSupp() {
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

    public String getAfterArrow() {
        return afterArrow;
    }

}
