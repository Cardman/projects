package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.CustList;

public final class FirstOptOperation extends AbstractUnaryOperation implements PreAnalyzableOperation {

    private final int offset;

    public FirstOptOperation(int _index, int _indexChild, MethodOperation _m,
                             OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        offset = _op.getOffset();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _page);
        MethodOperation m_ = getParent();
        if (m_ instanceof RetrieveMethod) {
            RetrieveMethod f_ = (RetrieveMethod) m_;
            methods(f_);
        }
        if (m_ instanceof RetrieveConstructor) {
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            ctors(f_);
        }
    }

    private void ctors(RetrieveConstructor _f) {
        OperationNode firstChild_ = _f.getFirstChild();
        int deltaCount_ = InvokingOperation.getDeltaCount(firstChild_);
        int indexChild_ = getIndexChild()-deltaCount_;
        CustList<ConstructorInfo> methodInfos_ = _f.getCtors();
        int len_ = methodInfos_.size();
        CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
        for (int i = 0; i < len_; i++) {
            ConstructorInfo methodInfo_ = methodInfos_.get(i);
            if (methodInfo_.getGeneFormatted().getParametersTypesLength() != indexChild_+1) {
                continue;
            }
            newList_.add(methodInfo_);
        }
        methodInfos_.clear();
        methodInfos_.addAllElts(newList_);
    }

    private void methods(RetrieveMethod _f) {
        OperationNode firstChild_ = _f.getFirstChild();
        int deltaCount_ = InvokingOperation.getDeltaCount(firstChild_);
        int indexChild_ = getIndexChild()-deltaCount_;
        CustList<CustList<MethodInfo>> methodInfos_ = _f.getMethodInfos();
        int len_ = methodInfos_.size();
        for (int i = 0; i < len_; i++) {
            int gr_ = methodInfos_.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                if (methodInfo_.getGeneFormatted().getParametersTypesLength() != indexChild_+1) {
                    continue;
                }
                newList_.add(methodInfo_);
            }
            methodInfos_.set(i,newList_);
        }
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _page);
        MethodOperation m_ = getParent();
        if (isNotChildOfCallDyn(m_)||isFirstChildInParent()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page);
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordFirstopt());
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        OperationNode child_ = getFirstChild();
        setResultClass(AnaClassArgumentMatching.copy(child_.getResultClass(), _page.getPrimitiveTypes()));
    }

    public int getOffset() {
        return offset;
    }

}
