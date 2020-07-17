package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.CustList;
import code.util.StringList;

public final class ExecEnumSwitchBlock extends ExecEnumValueSwitchBlock {

    public ExecEnumSwitchBlock(OffsetsBlock _offset, String _label, int _valueOffset, CustList<ExecOperationNode> _opValue) {
        super(_offset,_label,_valueOffset,_opValue);
    }

    @Override
    protected ExecBracedBlock process(CustList<ExecBracedBlock> children_, Argument arg_) {
        String name_ = NumParsers.getNameOfEnum(arg_.getStruct());
        ExecBracedBlock def_ = null;
        ExecBracedBlock found_ = null;
        for (ExecBracedBlock b: children_) {
            if (b instanceof ExecDefaultCondition) {
                def_ = b;
                continue;
            }
            if (b instanceof ExecEnumCaseCondition) {
                ExecEnumCaseCondition c_ = (ExecEnumCaseCondition) b;
                if (StringList.quickEq(c_.getValue(), name_)) {
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
