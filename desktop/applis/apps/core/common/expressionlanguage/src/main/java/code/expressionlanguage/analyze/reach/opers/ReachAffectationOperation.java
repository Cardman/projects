package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.analyze.opers.DeclaredFieldOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class ReachAffectationOperation extends ReachMethodOperation implements ReachCalculable {
    private final OperationNode settable;
    ReachAffectationOperation(AffectationOperation _info) {
        super(_info);
        settable = _info.getSettableOp();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        if (!(settable instanceof DeclaredFieldOperation)) {
            return;
        }
        DeclaredFieldOperation fieldRef_ = (DeclaredFieldOperation) settable;
        ReachOperationNode lastChild_ = getChildrenNodes().last();
        Struct value_ = lastChild_.getArgument();
        ClassField id_ = fieldRef_.getFieldIdReadOnly();
        NumParsers.getStaticFieldMap(id_.getClassName(), _page.getStaticFields()).set(id_.getFieldName(), value_);
        setSimpleArgument(value_);
    }
}
