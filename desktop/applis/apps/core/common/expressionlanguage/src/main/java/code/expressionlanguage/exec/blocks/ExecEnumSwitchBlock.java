package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ExecEnumSwitchBlock extends ExecEnumValueSwitchBlock {

    public ExecEnumSwitchBlock(String _label, int _valueOffset, CustList<ExecOperationNode> _opValue, int _offsetTrim) {
        super(_label,_valueOffset,_opValue, _offsetTrim);
    }

    @Override
    protected ExecBracedBlock process(CustList<ExecBracedBlock> _children, Argument _arg) {
        String name_ = NumParsers.getNameOfEnum(_arg.getStruct());
        ExecBracedBlock def_ = null;
        ExecBracedBlock found_ = null;
        for (ExecBracedBlock b: _children) {
            if (b instanceof ExecDefaultCondition) {
                def_ = b;
                continue;
            }
            if (b instanceof ExecEnumCaseCondition) {
                ExecEnumCaseCondition c_ = (ExecEnumCaseCondition) b;
                if (StringUtil.quickEq(c_.getValue(), name_)) {
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
