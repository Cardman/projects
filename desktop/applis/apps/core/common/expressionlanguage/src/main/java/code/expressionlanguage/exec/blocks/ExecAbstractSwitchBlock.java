package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.util.CustList;
import code.util.core.IndexConstants;

public abstract class ExecAbstractSwitchBlock extends ExecBracedBlock implements StackableBlock, WithNotEmptyEl {
    private final String label;

    private final int valueOffset;

    private final CustList<ExecOperationNode> opValue;

    ExecAbstractSwitchBlock(String _label, int _valueOffset, CustList<ExecOperationNode> _opValue, int _offsetTrim) {
        super(_offsetTrim);
        label = _label;
        valueOffset = _valueOffset;
        opValue = _opValue;
    }

    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        return opValue;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont, _stack);
            return;
        }
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        Argument arg_ =  ExpressionLanguage.tryToCalculate(_cont,el_,0, _stack);
        if (_cont.callsOrException(_stack)) {
            return;
        }
        ip_.clearCurrentEls();
        SwitchBlockStack if_ = new SwitchBlockStack();
        if_.setLabel(label);
        processCase(_cont,if_,arg_, _stack);
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
