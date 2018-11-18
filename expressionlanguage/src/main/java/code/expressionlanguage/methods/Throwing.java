package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;

public final class Throwing extends AbruptBlock implements StackableBlock {

    private final String expression;

    private int expressionOffset;

    private CustList<OperationNode> opThrow;

    public Throwing(ContextEl _importingPage,
            BracedBlock _m, OffsetStringInfo _expression, OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public String getExpression() {
        return expression;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opThrow);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        page_.setGlobalOffset(expressionOffset);
        opThrow = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
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
}
