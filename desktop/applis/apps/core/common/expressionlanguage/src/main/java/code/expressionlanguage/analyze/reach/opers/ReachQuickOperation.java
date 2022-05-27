package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public abstract class ReachQuickOperation extends ReachMethodOperation implements ReachCalculable {
    ReachQuickOperation(OperationNode _info) {
        super(_info);
    }

    static void tryGetResult(ReachMethodOperation _to, boolean _abs) {
        CustList<ReachOperationNode> children_ = _to.getChildrenNodes();
        Argument f_ = children_.first().getArgument();
        Argument s_ = children_.last().getArgument();
        if (f_ == null) {
            return;
        }
        Struct v_ = f_.getStruct();
        if (_abs) {
            if (BooleanStruct.isTrue(v_)) {
                _to.setSimpleArgumentAna(f_);
                return;
            }
        } else {
            if (BooleanStruct.isFalse(v_)) {
                _to.setSimpleArgumentAna(f_);
                return;
            }
        }
        if (s_ == null) {
            return;
        }
        _to.setSimpleArgumentAna(s_);
    }

}
