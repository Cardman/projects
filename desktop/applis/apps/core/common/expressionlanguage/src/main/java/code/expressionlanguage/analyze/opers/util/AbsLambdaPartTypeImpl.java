package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public abstract class AbsLambdaPartTypeImpl {

    protected static final String EMPTY_STRING = "";

    protected static final String VARARG_SUFFIX = "...";
    private final CustList<AnaResultPartType> partOffsets;
    private final String className;
    private InfoErrorDto partOffsetsErrEnd = new InfoErrorDto("");

    protected AbsLambdaPartTypeImpl(String _cl, CustList<AnaResultPartType> _parts) {
        this.partOffsets = _parts;
        className = _cl;
    }
    public MethodId resolveArguments(boolean _retRef, int _from, MethodAccessKind _static, StringList _params, AnalyzedPageEl _page){
        StringList out_ = new StringList();
        CustList<BoolVal> ref_ = new CustList<BoolVal>();
        int len_ = _params.size();
        int vararg_ = -1;
        int off_ = className.indexOf('(')+1;
        for (int i = 0; i < len_; i++) {
            if (i < _from) {
                off_ += _params.get(i).length() + 1;
                continue;
            }
            String full_ = _params.get(i);
            int loc_ = StringExpUtil.getOffset(full_);
            if (full_.trim().isEmpty()) {
                loc_--;
            }
            String arg_ = full_.trim();
            BoolVal refParam_ = BoolVal.FALSE;
            if (arg_.startsWith("~")) {
                arg_ = arg_.substring(1);
                loc_ += StringUtil.getFirstPrintableCharIndex(arg_)+1;
                arg_ = arg_.trim();
                refParam_ = BoolVal.TRUE;
            }
            boolean wrap_ = false;
            String type_;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                if (i + 1 != len_) {
                    varargError(_page, off_, full_);
                    return null;
                }
                wrap_ = true;
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length()).trim();
            } else {
                type_ = arg_;
            }
            arg_ = resolve(wrap_,off_ + loc_,type_,_page);
            off_ += _params.get(i).length() + 1;
            out_.add(arg_);
            ref_.add(refParam_);
        }
        return new MethodId(_retRef,_static, EMPTY_STRING, out_,ref_, vararg_ != -1);
    }
    public abstract String resolve(boolean _wrap, int _offset, String _in,AnalyzedPageEl _page);

    private void varargError(AnalyzedPageEl _page, int _off, String _full) {
        //last type => error
        FoundErrorInterpret varg_ = new FoundErrorInterpret();
        varg_.setFile(_page.getCurrentFile());
        int i_ = _off + _full.lastIndexOf("...");
        varg_.setIndexFile(_page, _off + _full.lastIndexOf("..."));
        //three dots
        varg_.buildError(_page.getAnalysisMessages().getUnexpectedVararg());
        _page.getLocalizer().addError(varg_);
        errPart(varg_, _page, i_);
    }

    private void errPart(FoundErrorInterpret _err, AnalyzedPageEl _page, int _begin) {
        partOffsetsErrEnd = new InfoErrorDto(_err,_page, _begin, 3);
    }

    public InfoErrorDto getPartOffsetsErrEnd() {
        return partOffsetsErrEnd;
    }

    public CustList<AnaResultPartType> getPartOffsets() {
        return partOffsets;
    }
}
