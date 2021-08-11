package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.analyze.NatResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendLeaf;
import code.util.CustList;
import code.util.StringList;

public final class NatAnaRendText extends AnaRendLeaf {

    private final String expression;

    private final int expressionOffset;

    private CustList<NatOperationNode> roots;

    private StringList texts = new StringList();
    NatAnaRendText(OffsetStringInfo _left, int _offset) {
        super(_offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(expressionOffset);
        _page.zeroOffset();
        NatResultText res_ = new NatResultText();
        res_.buildAna(expression, _anaDoc, _page);
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
    }

    public CustList<NatOperationNode> getRoots() {
        return roots;
    }

    public StringList getTexts() {
        return texts;
    }

}
