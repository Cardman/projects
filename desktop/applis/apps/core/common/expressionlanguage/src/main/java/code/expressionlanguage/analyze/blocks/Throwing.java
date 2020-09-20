package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
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
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        MemberCallingsBlock f_ = _page.getCurrentFct();
        _page.setOffset(0);
        _page.setGlobalOffset(expressionOffset);
        CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(expression, Calculation.staticCalculation(f_.getStaticContext()), _page);
        if (!_page.getCurrentEmptyPartErr().isEmpty()) {
            getErrorsBlock().add(_page.getCurrentEmptyPartErr());
            setReachableError(true);
        }
        root = _page.getCurrentRoot();
        ExecThrowing exec_ = new ExecThrowing(getOffset(), expressionOffset,op_);
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getCoverage().putBlockOperations(exec_,this);
    }

    public OperationNode getRoot() {
        return root;
    }
}
