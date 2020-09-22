package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReachUnaryBooleanOperation extends ReachMethodOperation implements ReachCalculable {
    ReachUnaryBooleanOperation(OperationNode _info) {
        super(_info);
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        CustList<ReachOperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Struct value_ = arg_.getStruct();
        if (!(value_ instanceof BooleanStruct)) {
            return;
        }
        BooleanStruct o_ = (BooleanStruct) value_;
        Argument a_ = new Argument(o_.neg());
        setSimpleArgumentAna(a_);
    }

}
