package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;

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
        root = ElUtil.getRootAnalyzedOperationsReadOnly(expression, Calculation.staticCalculation(f_.getStaticContext()), _page);
        if (!_page.getCurrentEmptyPartErr().isEmpty()) {
            addErrorBlock(_page.getCurrentEmptyPartErr());
        }
    }

    public OperationNode getRoot() {
        return root;
    }
}
