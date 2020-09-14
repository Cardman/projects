package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecThrowing;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.CustList;

public final class Throwing extends AbruptBlock {

    private final String expression;

    private OperationNode root;
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
        if (!page_.getCurrentEmptyPartErr().isEmpty()) {
            getErrorsBlock().add(page_.getCurrentEmptyPartErr());
            setReachableError(true);
        }
        root = page_.getCurrentRoot();
        ExecThrowing exec_ = new ExecThrowing(getOffset(), expressionOffset,op_);
        exec_.setFile(page_.getBlockToWrite().getFile());
        page_.getBlockToWrite().appendChild(exec_);
        page_.getCoverage().putBlockOperations(exec_,this);
    }

    public OperationNode getRoot() {
        return root;
    }
}
