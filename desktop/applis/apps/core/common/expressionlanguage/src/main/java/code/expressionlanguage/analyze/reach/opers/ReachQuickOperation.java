package code.expressionlanguage.analyze.reach.opers;

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
        Struct f_ = children_.first().getArgument();
        Struct s_ = children_.last().getArgument();
        if (f_ == null) {
            return;
        }
        if (_abs) {
            if (BooleanStruct.isTrue(f_)) {
                _to.setSimpleArgumentAna(f_);
                return;
            }
        } else {
            if (BooleanStruct.isFalse(f_)) {
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
