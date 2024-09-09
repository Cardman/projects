package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.common.symbol.CommonOperNullSafe;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReachNullSafeOperation extends ReachMethodOperation implements ReachCalculable {
    ReachNullSafeOperation(OperationNode _info) {
        super(_info);
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        CustList<ReachOperationNode> children_ = getChildrenNodes();
        Struct f_ = children_.first().getArgument();
        Struct s_ = children_.last().getArgument();
        if (f_ == null) {
            return;
        }
        Struct value_ = ArgumentListCall.getNull(s_);
        Struct res_ = new CommonOperNullSafe().calculateOperator(f_, value_);
        if (res_ != NullStruct.NULL_VALUE) {
            setSimpleArgumentAna(res_);
        }
    }
}
