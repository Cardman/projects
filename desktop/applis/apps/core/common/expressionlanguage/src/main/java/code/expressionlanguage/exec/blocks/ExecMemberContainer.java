package code.expressionlanguage.exec.blocks;


import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractInitPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public abstract class ExecMemberContainer {

    private CustList<ExecOperationNode> opValue;

    private final int offset;
    private final int trOffset;
    private final ExecFieldContainer container = new ExecFieldContainer();

    protected ExecMemberContainer(int _elementContent, int _trOffset) {
        this.offset = _elementContent;
        this.trOffset = _trOffset;
    }

    public void processEl(ContextEl _cont, StackCall _stack, AbstractInitPageEl _last, ExecBlock _coveredBlock) {
        firstEnter(_stack,_last);
        ExecHelperBlocks.tryToCalculate(_cont,0,_stack, opValue, trOffset, _coveredBlock, -1);
        if (_stack.stopAt(_cont)) {
            return;
        }
        _last.clearCurrentEls();
        ExecHelperBlocks.processMemberBlock(_stack,_last);
    }

    protected abstract void firstEnter(StackCall _stack, AbstractInitPageEl _last);

    public int getOffset() {
        return offset;
    }

    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

    public void setOpValue(CustList<ExecOperationNode> _opValue) {
        this.opValue = _opValue;
    }

    public ExecFieldContainer getContainer() {
        return container;
    }
}
