package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class ExecThrowing extends ExecLeaf implements WithNotEmptyEl {


    private int expressionOffset;

    private CustList<ExecOperationNode> opThrow;
    public ExecThrowing(int _expressionOffset, CustList<ExecOperationNode> _opThrow, int _offsetTrim) {
        super(_offsetTrim);
        expressionOffset = _expressionOffset;
        opThrow = _opThrow;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return new ExpressionLanguage(opThrow);
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opThrow.last();
        opThrow = ExpressionLanguage.getReducedNodes(r_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ip_.setOffset(0);
        ip_.setGlobalOffset(expressionOffset);
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont, this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_cont,el_,0);
        if (_cont.callsOrException()) {
            return;
        }
        ip_.clearCurrentEls();
        Struct o_ = arg_.getStruct();
        _cont.setCallingState(o_);
    }

}
