package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ArgumentListInstancing extends AbstractArrayInstancingOperation implements PreAnalyzableOperation {

    ArgumentListInstancing(int _index, int _indexChild,
                           MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        MethodOperation par_ = getParent();
        NamedArgumentOperation n_ = (NamedArgumentOperation) par_;
        String name_ = n_.getName();
        MethodOperation call_ = n_.getParent();
        CallDynMethodOperation f_ = (CallDynMethodOperation) call_;
        CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
        int len_ = methodInfos_.size();
        for (int i = 0; i < len_; i++) {
            int gr_ = methodInfos_.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                int ind_ = StringUtil.indexOf(methodInfo_.getParametersNames(), name_);
                StringList formattedParams_ = methodInfo_.getFormattedParams();
                if (!formattedParams_.isValidIndex(ind_)) {
                    continue;
                }
                newList_.add(methodInfo_);
            }
            methodInfos_.set(i,newList_);
        }
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

}
