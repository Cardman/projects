package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.CustList;
import code.util.StringList;

public final class AnaRendText extends AnaRendLeaf implements AnaRendBuildEl {

    private final String expression;

    private final int expressionOffset;

    private CustList<OperationNode> roots;

    private StringList texts = new StringList();
    private final ResultText res;
    AnaRendText(OffsetStringInfo _left, int _offset) {
        super(_offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
        res  = new ResultText();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setSumOffset(res.getResultExpression().getSumOffset());
        _page.zeroOffset();
        res.buildIdAna(expressionOffset, _anaDoc, _page);
        roots = res.getOpExpRoot();
        texts = res.getTexts();
    }

    public ResultText getRes() {
        return res;
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public StringList getTexts() {
        return texts;
    }

    public String getExpression() {
        return expression;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }
}
