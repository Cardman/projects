package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ExecEnumSwitchBlock extends ExecAbstractSwitchBlock {

    public ExecEnumSwitchBlock(String _label, int _valueOffset, CustList<ExecOperationNode> _opValue) {
        super(_label,_valueOffset,_opValue);
    }

    @Override
    protected void processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack) {
        ExecBracedBlock found_ = innerProcessEnum(this, _if, _arg);
        addStack(_cont,_if,_arg,_stack, found_);
    }

    public static ExecBracedBlock innerProcessEnum(ExecBracedBlock _braced, SwitchBlockStack _if, Argument _arg) {
        CustList<ExecBracedBlock> children_ = children(_braced, _if);
        ExecBracedBlock found_;
        if (_arg.isNull()) {
            found_ = nullCase(children_);
        } else {
            found_ = innerProcessEnum(children_, _arg);
        }
        return found_;
    }

    public static ExecBracedBlock innerProcessEnum(CustList<ExecBracedBlock> _children, Argument _arg) {
        String name_ = NumParsers.getNameOfEnum(_arg.getStruct());
        ExecBracedBlock def_ = null;
        ExecBracedBlock found_ = null;
        for (ExecBracedBlock b: _children) {
            if (!(b instanceof ExecDefaultCondition)) {
                if (b instanceof ExecEnumCaseCondition) {
                    ExecEnumCaseCondition c_ = (ExecEnumCaseCondition) b;
                    if (StringUtil.quickEq(c_.getValue(), name_)) {
                        found_ = c_;
                        break;
                    }
                }
            } else {
                def_ = b;
            }
        }
        if (found_ != null) {
            return found_;
        }
        return def_;
    }
}
