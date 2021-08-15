package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendThrowing extends AnaRendLeaf implements AnaRendBuildEl {

    private final String expression;

    private final int expressionOffset;

    private OperationNode root;
    AnaRendThrowing(OffsetStringInfo _expression, int _offset) {
        super(_offset);
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.zeroOffset();
        _page.setGlobalOffset(expressionOffset);
        _anaDoc.setAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        root = RenderAnalysis.getRootAnalyzedOperations(expression, 0, _anaDoc, _page);
    }

    public OperationNode getRoot() {
        return root;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }
}
