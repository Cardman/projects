package code.expressionlanguage.exec.blocks;


import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractInitPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.core.IndexConstants;

public abstract class ExecMemberContainer implements BuildingEl {

    private CustList<ExecOperationNode> opValue;

    private final int offset;
    private final int trOffset;
    private final ExecFieldContainer container = new ExecFieldContainer();

    protected ExecMemberContainer(int _elementContent, int _trOffset) {
        this.offset = _elementContent;
        this.trOffset = _trOffset;
    }

    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        return opValue;
    }

    public void processEl(ContextEl _cont, StackCall _stack, AbstractInitPageEl _last) {
        _last.globalOffset(offset);
        ExpressionLanguage el_ = _last.getCurrentEl(_cont, this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        ExpressionLanguage.tryToCalculate(_cont,el_, trOffset, _stack);
        if (_cont.callsOrException(_stack)) {
            return;
        }
        _last.clearCurrentEls();
        ExecHelperBlocks.processMemberBlock(_last);
    }
    public void setOpValue(CustList<ExecOperationNode> _opValue) {
        this.opValue = _opValue;
    }

    public ExecFieldContainer getContainer() {
        return container;
    }
}
