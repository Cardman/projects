package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.Throwing;
import code.expressionlanguage.methods.WithNotEmptyEl;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ExecThrowing extends ExecLeaf implements WithNotEmptyEl {

    private final String expression;

    private int expressionOffset;

    private CustList<ExecOperationNode> opThrow;
    public ExecThrowing(OffsetsBlock _offset, String _expression, int _expressionOffset, CustList<ExecOperationNode> _opThrow) {
        super(_offset);
        expression = _expression;
        expressionOffset = _expressionOffset;
        opThrow = _opThrow;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int off_ = getExpressionOffset();
        int offsetEndBlock_ = off_ + getExpression().length();
        ElUtil.buildCoverageReport(_cont,off_,this,opThrow,offsetEndBlock_,_parts);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return getEl();
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opThrow.last();
        opThrow = ElUtil.getReducedNodes(r_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ip_.setOffset(0);
        ip_.setGlobalOffset(expressionOffset);
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont, this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        Argument arg_ = ElUtil.tryToCalculate(_cont,el_,0);
        if (_cont.callsOrException()) {
            return;
        }
        ip_.clearCurrentEls();
        Struct o_ = arg_.getStruct();
        _cont.setException(o_);
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opThrow);
    }

    public CustList<ExecOperationNode> getOpThrow() {
        return opThrow;
    }
}
