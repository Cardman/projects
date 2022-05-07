package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendThrowing extends AnaRendLeaf implements AnaRendBuildEl {

    private final String expression;

    private final int expressionOffset;
    private final ResultExpression resultExpression = new ResultExpression();

    private OperationNode root;
    AnaRendThrowing(OffsetStringInfo _expression, int _offset) {
        super(_offset);
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.zeroOffset();
        _page.setSumOffset(resultExpression.getSumOffset());
        root = RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,resultExpression);
    }

    public OperationNode getRoot() {
        return root;
    }

    public String getExpression() {
        return expression;
    }

    public ResultExpression getRes() {
        return resultExpression;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }
}
