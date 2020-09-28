package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.Configuration;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.util.AnalyzingDoc;

public final class AnaRendThrowing extends AnaRendLeaf {

    private final String expression;

    private int expressionOffset;

    private OperationNode root;
    AnaRendThrowing(OffsetStringInfo _expression, OffsetsBlock _offset) {
        super(_offset);
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setOffset(0);
        _page.setGlobalOffset(expressionOffset);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrValue());
        root = RenderAnalysis.getRootAnalyzedOperations(expression, 0, _anaDoc, _page);
    }

    public OperationNode getRoot() {
        return root;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }
}
