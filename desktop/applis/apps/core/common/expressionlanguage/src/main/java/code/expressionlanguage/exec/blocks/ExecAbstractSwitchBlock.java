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

    private final ExecOperationNodeListOff value;

    ExecAbstractSwitchBlock(String _label, int _valueOffset, CustList<ExecOperationNode> _opValue) {
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

    protected static ExecBracedBlock nullCase(CustList<ExecBracedBlock> _children) {
        ExecBracedBlock found_;
        found_ = null;
        for (ExecBracedBlock b: _children) {
            if (!(b instanceof ExecDefaultCondition)) {
                if (b instanceof ExecNullCaseCondition) {
                    found_ = b;
                    break;
                }
            } else {
                found_ = b;
            }
        }
        return found_;
    }

    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        return value.getList();
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processSwitch(_cont, _stack, label, value, this);
    }

    protected abstract void processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack);

    protected void addStack(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack, ExecBracedBlock _found) {
        AbstractPageEl ip_ = _stack.getLastPage();
        if (_found == null) {
            _cont.getCoverage().passSwitch(this, _arg, _stack);
            _if.setCurrentVisitedBlock(this);
        } else {
            _cont.getCoverage().passSwitch(this, _found, _arg, _stack);
            ip_.setBlock(_found);
            _if.setCurrentVisitedBlock(_found);
        }
        ip_.addBlock(_if);
    }

}
