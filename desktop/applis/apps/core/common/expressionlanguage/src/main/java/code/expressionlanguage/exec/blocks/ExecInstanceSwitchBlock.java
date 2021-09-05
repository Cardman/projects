package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.util.CustList;

public final class ExecInstanceSwitchBlock extends ExecAbstractSwitchBlock {
    public ExecInstanceSwitchBlock(String _instanceTest, String _label, int _valueOffset, CustList<ExecOperationNode> _opValue) {
        super(_instanceTest,_label, _valueOffset, _opValue);
    }

    @Override
    protected void processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack) {
        ExecResultCase found_ = innerProcess(this, getInstanceTest(),_cont, _if, _arg, _stack, 1);
        addStack(_cont, _if, _arg, _stack, found_);
    }

    public static ExecResultCase innerProcess(ExecBracedBlock _braced, String _instanceTest, ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack, int _index) {
        ExecResultCase res_ = ExecStdSwitchBlock.innerProcess(_instanceTest,_cont, _stack, _braced, _if, _arg, _index);
        if (res_ != null) {
            _if.setExecLastVisitedBlock(res_.getBlock());
        }
        return res_;
    }

}
