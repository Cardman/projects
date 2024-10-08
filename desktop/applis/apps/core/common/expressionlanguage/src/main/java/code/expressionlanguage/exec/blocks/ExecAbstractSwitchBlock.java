package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.structs.Struct;

public abstract class ExecAbstractSwitchBlock extends ExecBracedBlock implements WithEl {
    private final String label;
    private final String instanceTest;

    private final ExecOperationNodeListOff value;

    ExecAbstractSwitchBlock(String _instanceTest, String _label, ExecOperationNodeListOff _ex) {
        instanceTest = _instanceTest;
        label = _label;
        value = _ex;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processSwitch(_cont, _stack, label, value, this);
    }

    protected void processCase(String _label, Struct _arg, StackCall _stack) {
        boolean atMost_ = this instanceof ExecInstanceSwitchBlock;
        SwitchBlockStack sw_ = new SwitchBlockStack(this, atMost_);
        sw_.setValue(_arg);
        sw_.setInstanceTest(getInstanceTest());
        sw_.setLabel(_label);
        addStack(sw_, _stack);
    }

    protected void addStack(SwitchBlockStack _if, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ExecBlock f_ = visit(_if, ip_, this);
        if (f_ != null) {
            ip_.setBlock(f_);
        }
    }

    static ExecBlock visit(SwitchBlockStack _if, AbstractPageEl _ip, ExecBracedBlock _bl) {
        ExecListLastBkSw infos_ = _if.getInfos();
        ExecBlock f_ = infos_.visit();
        _if.setCurrentVisitedBlock(_bl);
        _ip.addBlock(_if);
        return f_;
    }

    public String getInstanceTest() {
        return instanceTest;
    }
}
