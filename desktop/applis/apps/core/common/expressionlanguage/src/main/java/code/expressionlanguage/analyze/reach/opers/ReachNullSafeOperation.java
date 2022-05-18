package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.common.symbol.CommonOperNullSafe;
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
        Argument f_ = children_.first().getArgument();
        Argument s_ = children_.last().getArgument();
        if (f_ == null) {
            return;
        }
        Struct value_ = Argument.getNull(Argument.getNullable(s_));
        Struct v_ = f_.getStruct();
        Struct res_ = new CommonOperNullSafe().calculateOperator(v_, value_, (byte) 0);
        if (res_ != NullStruct.NULL_VALUE) {
            setSimpleArgumentAna(new Argument(res_));
        }
    }
}
