package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecThrowing;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class Throwing extends AbruptBlock {

    private final String expression;

    private int expressionOffset;

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
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        MemberCallingsBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setOffset(0);
        page_.setGlobalOffset(expressionOffset);
        CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(f_.getStaticContext()));
        ExecThrowing exec_ = new ExecThrowing(getOffset(),expression,expressionOffset,op_);
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }

}
