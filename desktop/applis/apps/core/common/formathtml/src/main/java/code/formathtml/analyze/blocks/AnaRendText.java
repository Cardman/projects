package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.Configuration;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.CustList;
import code.util.StringList;

public final class AnaRendText extends AnaRendLeaf {

    private final String expression;

    private int expressionOffset;

    private CustList<OperationNode> roots;

    private StringList texts = new StringList();
    AnaRendText(OffsetStringInfo _left, OffsetsBlock _offset) {
        super(_offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setOffset(expressionOffset);
        ResultText res_ = new ResultText();
        res_.buildAna(expression, expressionOffset, _anaDoc, _page);
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public StringList getTexts() {
        return texts;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }
}
