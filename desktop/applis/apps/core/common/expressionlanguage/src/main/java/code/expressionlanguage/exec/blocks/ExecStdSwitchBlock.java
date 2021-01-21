package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.util.CustList;

public final class ExecStdSwitchBlock extends ExecAbstractSwitchBlock {

    public ExecStdSwitchBlock(String _label, int _valueOffset, CustList<ExecOperationNode> _opValue, int _offsetTrim) {
        super(_label,_valueOffset,_opValue, _offsetTrim);
    }

    @Override
    protected void processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack) {
        ExecBracedBlock found_ = innerProcess(this, _if, _arg);
        addStack(_cont,_if,_arg,_stack, found_);
    }

    public static ExecBracedBlock innerProcess(ExecBracedBlock _braced, SwitchBlockStack _if, Argument _arg) {
        ExecBlock n_ = _braced.getFirstChild();
        CustList<ExecBracedBlock> children_;
        children_ = new CustList<ExecBracedBlock>();
        while (n_ instanceof ExecBracedBlock) {
            children_.add((ExecBracedBlock)n_);
            _if.setExecLastVisitedBlock((ExecBracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        _if.setExecBlock(_braced);
        ExecBracedBlock found_ = null;
        if (_arg.isNull()) {
            for (ExecBracedBlock b: children_) {
                if (b instanceof ExecDefaultCondition) {
                    found_ = b;
                    continue;
                }
                if (b instanceof ExecNullCaseCondition) {
                    found_ = b;
                    break;
                }
            }
        } else {
            found_ = ExecStdSwitchBlock.innerProcess(children_, _arg);
        }
        return found_;
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
