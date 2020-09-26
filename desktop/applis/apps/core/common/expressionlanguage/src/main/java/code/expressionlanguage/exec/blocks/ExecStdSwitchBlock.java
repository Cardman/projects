package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecStdSwitchBlock extends ExecEnumValueSwitchBlock {

    public ExecStdSwitchBlock(String _label, int _valueOffset, CustList<ExecOperationNode> _opValue, int _offsetTrim) {
        super(_label,_valueOffset,_opValue, _offsetTrim);
    }

    @Override
    protected ExecBracedBlock process(CustList<ExecBracedBlock> children_, Argument arg_) {
        ExecBracedBlock def_ = null;
        ExecBracedBlock found_ = null;
        for (ExecBracedBlock b: children_) {
            if (b instanceof ExecDefaultCondition) {
                def_ = b;
                continue;
            }
            if (b instanceof ExecStdCaseCondition) {
                ExecStdCaseCondition c_ = (ExecStdCaseCondition) b;
                if (c_.getArg().getStruct().sameReference(arg_.getStruct())) {
                    found_ = c_;
                    break;
                }
            }
        }
        if (found_ != null) {
            return found_;
        }
        return def_;
    }
}
