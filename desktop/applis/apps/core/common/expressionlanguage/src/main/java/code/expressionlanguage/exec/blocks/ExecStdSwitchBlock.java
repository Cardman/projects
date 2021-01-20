package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecStdSwitchBlock extends ExecEnumValueSwitchBlock {

    public ExecStdSwitchBlock(String _label, int _valueOffset, CustList<ExecOperationNode> _opValue, int _offsetTrim) {
        super(_label,_valueOffset,_opValue, _offsetTrim);
    }

    @Override
    protected ExecBracedBlock process(CustList<ExecBracedBlock> _children, Argument _arg) {
        return innerProcess(_children, _arg);
    }

    public static ExecBracedBlock innerProcess(CustList<ExecBracedBlock> _children, Argument _arg) {
        ExecBracedBlock def_ = null;
        ExecBracedBlock found_ = null;
        for (ExecBracedBlock b: _children) {
            if (b instanceof ExecDefaultCondition) {
                def_ = b;
                continue;
            }
            if (b instanceof ExecStdCaseCondition) {
                ExecStdCaseCondition c_ = (ExecStdCaseCondition) b;
                if (c_.getArg().getStruct().sameReference(_arg.getStruct())) {
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
