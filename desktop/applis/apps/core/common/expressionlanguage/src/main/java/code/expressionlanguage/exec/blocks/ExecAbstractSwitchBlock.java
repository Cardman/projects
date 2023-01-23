package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.util.CustList;

public abstract class ExecAbstractSwitchBlock extends ExecBracedBlock implements WithEl {
    private final String label;
    private final String instanceTest;

    private final ExecOperationNodeListOff value;

    ExecAbstractSwitchBlock(String _instanceTest, String _label, int _valueOffset, CustList<ExecOperationNode> _opValue) {
        instanceTest = _instanceTest;
        label = _label;
        value = new ExecOperationNodeListOff(_opValue,_valueOffset);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processSwitch(_cont, _stack, label, value, this);
    }

    protected void processCase(String _label, Argument _arg, StackCall _stack) {
        boolean atMost_ = this instanceof ExecInstanceSwitchBlock;
        SwitchBlockStack sw_ = new SwitchBlockStack(this, atMost_);
        sw_.setValue(ArgumentListCall.toStr(_arg));
        sw_.setInstanceTest(getInstanceTest());
        sw_.setLabel(_label);
        addStack(sw_, _stack);
    }

    protected void addStack(SwitchBlockStack _if, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.clearCurrentEls();
        visit(_if, ip_, this);
    }

    static void visit(SwitchBlockStack _if, AbstractPageEl _ip, ExecBracedBlock _bl) {
        ExecListLastBkSw infos_ = _if.getInfos();
        ExecBlock f_ = infos_.visit();
        if (f_ != null) {
            _ip.setBlock(f_);
        }
        _if.setCurrentVisitedBlock(_bl);
        _ip.addBlock(_if);
    }

    public String getInstanceTest() {
        return instanceTest;
    }
}
