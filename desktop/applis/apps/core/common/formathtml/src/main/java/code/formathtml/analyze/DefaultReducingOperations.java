package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachMethodOperation;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.util.CustList;

public final class DefaultReducingOperations implements AbstractReducingOperations {
    @Override
    public CustList<OperationNode> reduced(CustList<OperationNode> _list, AnalyzedPageEl _page) {
        CustList<ReachMethodOperation> reach_ = ReachOperationUtil.getExecutableNodes(_list);
        ReachOperationUtil.tryCalculate(_page, reach_);
        return _list;
    }
}
