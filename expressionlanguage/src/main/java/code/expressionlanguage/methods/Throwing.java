package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class Throwing extends AbruptBlock implements StackableBlock, WithNotEmptyEl {

    private final String expression;

    private int expressionOffset;

    private CustList<ExecOperationNode> opThrow;

    public Throwing(OffsetStringInfo _expression, OffsetsBlock _offset) {
        super(_offset);
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opThrow.last();
        opThrow = ElUtil.getReducedNodes(r_);
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opThrow);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setOffset(0);
        page_.setGlobalOffset(expressionOffset);
        opThrow = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.getStaticContext()));
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setOffset(0);
        page_.setGlobalOffset(expressionOffset);
        opThrow = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(f_.getStaticContext()));
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ip_.setOffset(0);
        ip_.setGlobalOffset(expressionOffset);
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont, this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        Argument arg_ = el_.calculateMember(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        ip_.clearCurrentEls();
        Struct o_ = arg_.getStruct();
        _cont.setException(o_);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return getEl();
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int off_ = getExpressionOffset();
        int offsetEndBlock_ = off_ + getExpression().length();
        ElUtil.buildCoverageReport(_cont,off_,this,opThrow,offsetEndBlock_,_parts);
    }
}
