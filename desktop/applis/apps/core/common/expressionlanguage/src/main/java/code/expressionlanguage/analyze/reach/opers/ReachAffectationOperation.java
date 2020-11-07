package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.StandardFieldOperation;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class ReachAffectationOperation extends ReachMethodOperation implements ReachCalculable {
    private OperationNode settable;
    ReachAffectationOperation(AffectationOperation _info) {
        super(_info);
        settable = _info.getSettableOp();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        if (!ElUtil.isDeclaringField(settable, _page)) {
            return;
        }
        StandardFieldOperation fieldRef_ = (StandardFieldOperation) settable;
        ReachOperationNode lastChild_ = getChildrenNodes().last();
        Argument value_ = lastChild_.getArgument();
        ClassField id_ = fieldRef_.getFieldIdReadOnly();
        Struct str_ = value_.getStruct();
        NumParsers.getStaticFieldMap(id_.getClassName(), _page.getStaticFields()).set(id_.getFieldName(), str_);
        setSimpleArgument(value_);
    }
}
