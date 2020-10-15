package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ParsedFctHeader {
    private static final char SEP_CALLING = ',';
    private static final char END_CALLING = ')';
    private static final char ANNOT = '@';
    private Ints offestsTypes = new Ints();
    private Ints offestsParams = new Ints();
    private StringList parametersType = new StringList();
    private StringList parametersName = new StringList();
    private CustList<Ints> annotationsIndexesParams = new CustList<Ints>();
    private CustList<StringList> annotationsParams = new CustList<StringList>();
    private String info = "";
    private String returnType = "";
    private boolean ok = true;
    private int paramOffest;
    private int returnOffest;
    private int offsetLast;
    public void parse(int _paramOffest, String _info, int _offset, AnalyzedPageEl _parse) {
        KeyWords keyWords_ = _parse.getKeyWords();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        parse(_paramOffest, _info, _offset, keyWordIntern_);
    }

    public void parse(int _paramOffest, String _info, int _offset, String _keyWordIntern) {
        String info_ = _info;
        paramOffest = _paramOffest;
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
                ParsedAnnotations par_ = new ParsedAnnotations(info_, paramOffest+_offset);
                par_.parse();
                annotationsIndexesParam_ = par_.getAnnotationsIndexes();
                annotationsParam_ = par_.getAnnotations();
                info_ = par_.getAfter();
                paramOffest = par_.getIndex()-_offset;
                paramOffest += StringUtil.getFirstPrintableCharIndex(info_);
            }
            annotationsIndexesParams.add(annotationsIndexesParam_);
            annotationsParams.add(annotationsParam_);
            offestsTypes.add(paramOffest+_offset);
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
            offestsParams.add(paramOffest + paramType_.length() + off_+_offset);
            offsetLast = paramOffest + paramType_.length() + off_;
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
            paramOffest += paramType_.length();
            paramOffest += StringUtil.getFirstPrintableCharIndex(afterParamType_);
            paramOffest += call_ + 1;
            paramOffest += StringUtil.getFirstPrintableCharIndex(afterParamName_);
        }
        info = info_;
    }

    public void parseAnonymous(int _paramOffest, String _info, int _offset, char _sepRet) {
        String info_ = _info;
        paramOffest = _paramOffest;
        offsetLast = _paramOffest;
        while (true) {
            if (info_.indexOf(_sepRet) == 0||info_.indexOf(END_CALLING) == 0) {
                info_ = info_.substring(1);
                offsetLast++;
                offsetLast +=  StringUtil.getFirstPrintableCharIndex(info_);
                break;
            }
            int lenInfo_ = info_.length();
            Ints annotationsIndexesParam_ = new Ints();
            StringList annotationsParam_ = new StringList();
            annotationsIndexesParams.add(annotationsIndexesParam_);
            annotationsParams.add(annotationsParam_);
            offestsTypes.add(paramOffest+_offset);
            int implCall_ = info_.indexOf(SEP_CALLING);
            int implStopInd_ = info_.indexOf(_sepRet);
            int implStopRightPar_ = info_.indexOf(END_CALLING);
            if (implCall_ < 0) {
                if (implStopInd_ >= 0) {
                    implCall_ = implStopInd_;
                } else {
                    implCall_ = implStopRightPar_;
                }
            } else {
                if (implStopInd_ >= 0) {
                    implCall_ = Math.min(implCall_,implStopInd_);
                } else {
                    implCall_ = Math.min(implCall_,implStopRightPar_);
                }
            }
            String paramType_;
            if (implCall_ >= 0) {
                String parName_ = info_.substring(0, implCall_).trim();
                if (StringExpUtil.isTypeLeafPart(parName_)) {
                    paramType_ = "";
                } else {
                    paramType_ = FileResolver.getFoundType(info_);
                }
            } else {
                paramType_ = FileResolver.getFoundType(info_);
            }
            parametersType.add(paramType_.trim());
            String afterParamType_ = info_.substring(paramType_.length());
            info_ = afterParamType_.trim();
            int call_ = info_.indexOf(SEP_CALLING);
            int stopInd_ = info_.indexOf(_sepRet);
            int stopRightPar_ = info_.indexOf(END_CALLING);
            if (call_ < 0) {
                if (stopInd_ >= 0) {
                    call_ = stopInd_;
                } else {
                    call_ = stopRightPar_;
                }
            } else {
                if (stopInd_ >= 0) {
                    call_ = Math.min(call_,stopInd_);
                } else {
                    call_ = Math.min(call_,stopRightPar_);
                }
            }
            int off_ = StringUtil.getFirstPrintableCharIndex(afterParamType_);
            offestsParams.add(paramOffest + paramType_.length() + off_+_offset);
            offsetLast = paramOffest + paramType_.length() + off_;
            if (call_ >= 0) {
                String paramName_ = info_.substring(0, call_);
                offsetLast += call_+1;
                parametersName.add(paramName_.trim());
            } else {
                ok = false;
                parametersName.add(info_.trim());
                info_ = "";
            }
            String afterParamName_ = info_.substring(call_ + 1);
            info_ = afterParamName_.trim();
            paramOffest += paramType_.length();
            paramOffest += StringUtil.getFirstPrintableCharIndex(afterParamType_);
            paramOffest += call_ + 1;
            paramOffest += StringUtil.getFirstPrintableCharIndex(afterParamName_);
            if (stopInd_ > -1 && call_ + 1 >= stopInd_) {
                break;
            }
            if (noAddedInfo(info_, lenInfo_)) {
                ok = false;
                break;
            }
        }
        int index_ = info_.indexOf(END_CALLING);
        returnOffest = paramOffest + _offset;
        returnType = info_.substring(0, Math.max(index_,0));
        info = info_;
    }

    private boolean noAddedInfo(String _info, int _lenInfo) {
        return _info.isEmpty()||_info.length()>=_lenInfo;
    }
    public String getReturnType() {
        return returnType;
    }

    public String getInfo() {
        return info;
    }

    public boolean isOk() {
        return ok;
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
}
