package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.util.CustList;

public abstract class ExecAbstractSwitchBlock extends ExecBracedBlock implements StackableBlock, WithNotEmptyEl {
    private final String label;
    private final String instanceTest;

    private final ExecOperationNodeListOff value;

    ExecAbstractSwitchBlock(String _instanceTest, String _label, int _valueOffset, CustList<ExecOperationNode> _opValue) {
        instanceTest = _instanceTest;
        label = _label;
        value = new ExecOperationNodeListOff(_opValue,_valueOffset);
    }

    protected static CustList<ExecBracedBlock> children(ExecBracedBlock _braced, SwitchBlockStack _if) {
        ExecBlock n_ = _braced.getFirstChild();
        CustList<ExecBracedBlock> children_;
        children_ = new CustList<ExecBracedBlock>();
        while (n_ instanceof ExecBracedBlock) {
            children_.add((ExecBracedBlock)n_);
            _if.setExecLastVisitedBlock((ExecBracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        _if.setExecBlock(_braced);
        return children_;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processSwitch(_cont, _stack, label, value, this);
    }

    protected abstract void processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack);

    protected void addStack(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack, ExecResultCase _found) {
        AbstractPageEl ip_ = _stack.getLastPage();
        _cont.getCoverage().passSwitch(this, _found, _arg, _stack);
        visit(_if, _found, ip_, this);
    }

    static void visit(SwitchBlockStack _if, ExecResultCase _found, AbstractPageEl _ip, ExecBracedBlock _bl) {
        if (_found == null) {
            _if.setCurrentVisitedBlock(_bl);
        } else {
            _ip.setBlock(_found.getBlock());
            _if.setCurrentVisitedBlock(_found.getBlock());
        }
        _ip.addBlock(_if);
    }

    public String getInstanceTest() {
        return instanceTest;
    }
}
