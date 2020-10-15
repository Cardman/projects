package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.util.CustList;
import code.util.core.IndexConstants;

public abstract class ExecAbstractSwitchBlock extends ExecBracedBlock implements StackableBlock, WithNotEmptyEl {
    private String label;

    private int valueOffset;

    private CustList<ExecOperationNode> opValue;

    ExecAbstractSwitchBlock(String _label, int _valueOffset, CustList<ExecOperationNode> _opValue, int _offsetTrim) {
        super(_offsetTrim);
        label = _label;
        valueOffset = _valueOffset;
        opValue = _opValue;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return getEl();
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opValue);
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opValue.last();
        opValue = ExpressionLanguage.getReducedNodes(r_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont);
            return;
        }
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        Argument arg_ =  ExpressionLanguage.tryToCalculate(_cont,el_,0);
        if (_cont.callsOrException()) {
            return;
        }
        ip_.clearCurrentEls();
        SwitchBlockStack if_ = new SwitchBlockStack();
        if_.setLabel(label);
        processCase(_cont,if_,arg_);
    }

    protected abstract void processCase(ContextEl _cont,SwitchBlockStack _if, Argument _arg);

}
