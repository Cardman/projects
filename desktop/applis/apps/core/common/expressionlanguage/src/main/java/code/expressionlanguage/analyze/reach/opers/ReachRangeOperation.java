package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReachRangeOperation extends ReachMethodOperation implements ReachCalculable {
    public ReachRangeOperation(OperationNode _info) {
        super(_info);
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        CustList<ReachOperationNode> childrenNodes_ = getChildrenNodes();
        if (childrenNodes_.size() != 2) {
            return;
        }
        Argument first_ = childrenNodes_.first().getArgument();
        checkNull(first_,_page);
        Argument second_ = childrenNodes_.last().getArgument();
        checkNull(second_,_page);
        Struct range_ = AnaApplyCoreMethodUtil.range(first_.getStruct(), second_.getStruct());
        if (range_ != null) {
            setSimpleArgumentAna(new Argument(range_));
        }
    }
}
