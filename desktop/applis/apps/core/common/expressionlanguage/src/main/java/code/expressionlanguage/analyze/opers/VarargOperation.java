package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.Parametrable;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.core.StringUtil;

public final class VarargOperation extends LeafOperation implements FunctFilterOperation {

    private String className;

    private final CustList<AnaResultPartTypeDtoInt> partOffsets = new CustList<AnaResultPartTypeDtoInt>();
    private InfoErrorDto partOffsetsErr = new InfoErrorDto("");
    public VarargOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        className = _op.getValues().firstValue();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        MethodOperation m_ = getParent();
        if (isNotChildOfCallDyn(m_)||!isFirstChildInParent()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page);
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordVararg());
            _page.getLocalizer().addError(varg_);
            partOffsetsErr = new InfoErrorDto(varg_,_page,_page.getKeyWords().getKeyWordVararg().length());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            setSimpleArgument(NullStruct.NULL_VALUE);
            return;
        }
        int afterLeftPar_ = className.indexOf(PAR_LEFT) + 1;
        String str_ = className.substring(afterLeftPar_, className.lastIndexOf(PAR_RIGHT));
        int off_ = StringUtil.getFirstPrintableCharIndex(str_);
        AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(afterLeftPar_ + off_, str_.trim(), _page);
        str_ = result_.getResult(_page);
        partOffsets.add(new AnaResultPartTypeDirectDto(result_));
        setResultClass(new AnaClassArgumentMatching(str_));
        className = str_;
        if (m_ instanceof RetrieveMethod) {
            RetrieveMethod f_ = (RetrieveMethod) m_;
            methods(f_);
        }
        if (m_ instanceof RetrieveConstructor) {
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            ctors(f_);
        }
        setSimpleArgument(NullStruct.NULL_VALUE);
    }

    private void ctors(RetrieveConstructor _f) {
        CustList<ConstructorInfo> methodInfos_ = _f.getCtors();
        int len_ = methodInfos_.size();
        CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
        for (int i = 0; i < len_; i++) {
            ConstructorInfo methodInfo_ = methodInfos_.get(i);
            if (exclude(methodInfo_)) {
                continue;
            }
            newList_.add(methodInfo_);
        }
        methodInfos_.clear();
        methodInfos_.addAllElts(newList_);
    }

    private void methods(RetrieveMethod _f) {
        CustList<CustList<MethodInfo>> methodInfos_ = _f.getMethodInfos();
        int len_ = methodInfos_.size();
        for (int i = 0; i < len_; i++) {
            int gr_ = methodInfos_.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                if (exclude(methodInfo_)) {
                    continue;
                }
                newList_.add(methodInfo_);
            }
            methodInfos_.set(i,newList_);
        }
    }

    private boolean exclude(Parametrable _methodInfo) {
        Identifiable geneFormatted_ = _methodInfo.getGeneFormatted();
        if (!_methodInfo.isVararg()) {
            return true;
        }
        String wc_ = geneFormatted_.getParametersType(geneFormatted_.getParametersTypesLength() - 1);
        return !StringUtil.quickEq(wc_, className);
    }

    public String getClassName() {
        return className;
    }

    @Override
    public CustList<AnaResultPartTypeDtoInt> getPartOffsets() {
        return partOffsets;
    }

    @Override
    public InfoErrorDto getPartOffsetsErr() {
        return partOffsetsErr;
    }
}
