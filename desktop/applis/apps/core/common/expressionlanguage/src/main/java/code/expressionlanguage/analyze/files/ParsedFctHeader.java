package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.DefaultProcessKeyWord;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.util.BooleanList;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ParsedFctHeader {
    private static final char SEP_CALLING = ',';
    private static final char END_CALLING = ')';
    private static final char ANNOT = '@';
    private final Ints offestsTypes = new Ints();
    private final Ints offestsParams = new Ints();
    private final BooleanList parametersRef = new BooleanList();
    private final StringList parametersType = new StringList();
    private final StringList parametersName = new StringList();
    private final Ints annotationsIndexes = new Ints();
    private final StringList annotations = new StringList();
    private final CustList<Ints> annotationsIndexesParams = new CustList<Ints>();
    private final CustList<StringList> annotationsParams = new CustList<StringList>();
    private String info = "";
    private String returnType = "";
    private boolean ok = true;
    private boolean retRef;
    private int returnOffest;
    private int offsetLast;
    private int nextIndex;
    private String afterArrow="";
    public void parse(int _paramOffest, String _info, int _offset, AnalyzedPageEl _parse) {
        KeyWords keyWords_ = _parse.getKeyWords();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        parse(_paramOffest, _info, _offset, keyWordIntern_, keyWordThat_);
    }

    public void parse(int _paramOffest, String _info, int _offset, String _keyWordIntern, String _keyWordThat) {
        String info_ = _info;
        int paramOffest_ = _paramOffest;
        offsetLast = _paramOffest;
        while (true) {
            if (info_.indexOf(END_CALLING) == 0) {
                info_ = info_.substring(1);
                offsetLast++;
                offsetLast +=  StringUtil.getFirstPrintableCharIndex(info_);
                break;
            }
            int lenInfo_ = info_.length();
            Ints annotationsIndexesParam_ = new Ints();
            StringList annotationsParam_ = new StringList();
            String trimBefAnn_ = info_.trim();
            if (StringExpUtil.nextCharIs(trimBefAnn_,0,trimBefAnn_.length(), ANNOT)) {
                ParsedAnnotations par_ = new ParsedAnnotations(trimBefAnn_, paramOffest_ +_offset);
                par_.parse();
                annotationsIndexesParam_ = par_.getAnnotationsIndexes();
                annotationsParam_ = par_.getAnnotations();
                info_ = par_.getAfter();
                paramOffest_ = par_.getIndex()-_offset;
            }
            paramOffest_ += StringUtil.getFirstPrintableCharIndex(info_);
            info_ = info_.trim();
            annotationsIndexesParams.add(annotationsIndexesParam_);
            annotationsParams.add(annotationsParam_);
            if (StringExpUtil.startsWithKeyWord(info_,_keyWordThat)) {
                info_ = info_.substring(_keyWordThat.length());
                paramOffest_ += _keyWordThat.length() + StringExpUtil.getOffset(info_);
                info_ = info_.trim();
                parametersRef.add(true);
            } else {
                parametersRef.add(false);
            }
            offestsTypes.add(paramOffest_ +_offset);
            String paramType_ = FileResolver.getFoundType(info_);
            parametersType.add(paramType_.trim());
            String afterParamType_ = info_.substring(paramType_.length());
            info_ = afterParamType_.trim();
            int call_ = info_.indexOf(SEP_CALLING);
            int stopInd_ = info_.indexOf(END_CALLING);
            if (call_ < 0) {
                call_ = stopInd_;
            } else {
                call_ = Math.min(call_,stopInd_);
            }
            int off_ = StringUtil.getFirstPrintableCharIndex(afterParamType_);
            offestsParams.add(paramOffest_ + paramType_.length() + off_+_offset);
            offsetLast = paramOffest_ + paramType_.length() + off_;
            if (call_ >= 0) {
                String paramName_ = info_.substring(0, call_);
                offsetLast += call_+1;
                parametersName.add(paramName_.trim());
            } else {
                ok = false;
                parametersName.add(info_.trim());
            }
            String afterParamName_ = info_.substring(call_ + 1);
            boolean exist_ = info_.startsWith(Character.toString(END_CALLING),call_);
            info_ = afterParamName_.trim();
            if (exist_) {
                if (StringExpUtil.startsWithKeyWord(info_, _keyWordIntern)) {
                    offsetLast +=  StringUtil.getFirstPrintableCharIndex(afterParamName_);
                    break;
                }
            }
            if (noAddedInfo(info_, lenInfo_)) {
                break;
            }
            paramOffest_ += paramType_.length();
            paramOffest_ += StringUtil.getFirstPrintableCharIndex(afterParamType_);
            paramOffest_ += call_ + 1;
            paramOffest_ += StringUtil.getFirstPrintableCharIndex(afterParamName_);
        }
        info = info_;
    }

    public void parseAnonymous(int _indexLeftPar, String _string, int _offset, String _keyWordThat) {
        int len_ = _string.length();
        int j_ = DefaultProcessKeyWord.skipWhiteSpace(_string, _indexLeftPar +1);
        if (_string.startsWith(")",j_)) {
            processImplicitRetType(_indexLeftPar, _string, _offset, j_);
            return;
        }
        if (_string.startsWith(":",j_)) {
            processExplicitRetType(_indexLeftPar, _string, _offset, _keyWordThat, j_);
            return;
        }
        while (j_ < len_) {
            int k_ = j_;
            Ints annotationsIndexesParam_ = new Ints();
            StringList annotationsParam_ = new StringList();
            if (_string.startsWith("@",k_)) {
                ParsedAnnotations par_ = new ParsedAnnotations(_string.substring(k_), k_+_offset);
                par_.parse();
                annotationsIndexesParam_ = par_.getAnnotationsIndexes();
                annotationsParam_ = par_.getAnnotations();
                k_ = DefaultProcessKeyWord.skipWhiteSpace(_string,par_.getIndex() - _offset);
            }
            annotationsIndexesParams.add(annotationsIndexesParam_);
            annotationsParams.add(annotationsParam_);
            if (StringExpUtil.startsWithKeyWord(_string,k_,_keyWordThat)) {
                k_ += _keyWordThat.length();
                k_ = DefaultProcessKeyWord.skipWhiteSpace(_string,k_);
                parametersRef.add(true);
            } else {
                parametersRef.add(false);
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
            int implStopInd_ = _string.indexOf(':', k_);
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
            offestsTypes.add(typeOff_+_offset);
            parametersType.add(candid_);
            offestsParams.add(parOff_ +_offset);
            parametersName.add(varName_);
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
        if (!_string.startsWith("->",k_)) {
            nextIndex = _indexLeftPar;
            return;
        }
        returnOffest = _j + _offset;
        returnType = "";
        nextIndex = _j;
        afterArrow = _string.substring(k_+2);
    }

    private void processExplicitRetType(int _indexLeftPar, String _string, int _offset, String _keyWordThat, int _j) {
        int k_ = DefaultProcessKeyWord.skipWhiteSpace(_string, _j +1);
        if (_string.startsWith("@",k_)) {
            ParsedAnnotations par_ = new ParsedAnnotations(_string.substring(k_), k_+ _offset);
            par_.parse();
            annotationsIndexes.addAllElts(par_.getAnnotationsIndexes());
            annotations.addAllElts(par_.getAnnotations());
            k_ = DefaultProcessKeyWord.skipWhiteSpace(_string,par_.getIndex()- _offset);
        }
        if (StringExpUtil.startsWithKeyWord(_string,k_, _keyWordThat)) {
            k_ += _keyWordThat.length();
            k_ = DefaultProcessKeyWord.skipWhiteSpace(_string,k_);
            retRef = true;
        }
        int nextRightPar_ = _string.indexOf(")",k_);
        if (nextRightPar_ < 0) {
            nextIndex = _indexLeftPar;
            return;
        }
        int until_ = nextRightPar_;
        nextRightPar_ = DefaultProcessKeyWord.skipWhiteSpace(_string,nextRightPar_+1);
        if (!_string.startsWith("->",nextRightPar_)) {
            nextIndex = _indexLeftPar;
            return;
        }
        returnOffest = k_+ _offset;
        nextIndex = until_;
        afterArrow = _string.substring(nextRightPar_+2);
        returnType = _string.substring(k_,until_);
    }

    private static boolean noAddedInfo(String _info, int _lenInfo) {
        return _info.isEmpty()||_info.length()>=_lenInfo;
    }
    public String getReturnType() {
        return returnType;
    }

    public String getInfo() {
        return info;
    }

    public boolean isRetRef() {
        return retRef;
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

    public int getOffsetLast() {
        return offsetLast;
    }

    public int getReturnOffest() {
        return returnOffest;
    }

    public Ints getOffestsParams() {
        return offestsParams;
    }

    public Ints getOffestsTypes() {
        return offestsTypes;
    }

    public StringList getParametersName() {
        return parametersName;
    }

    public StringList getParametersType() {
        return parametersType;
    }

    public BooleanList getParametersRef() {
        return parametersRef;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public String getAfterArrow() {
        return afterArrow;
    }

}
