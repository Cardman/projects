package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendLeaf;
import code.util.CustList;
import code.util.StringList;

public final class NatAnaRendText extends AnaRendLeaf implements NatRendBuildEl {

    private final String expression;

    private CustList<NatOperationNode> roots;

    private StringList texts = new StringList();
    NatAnaRendText(OffsetStringInfo _left, int _offset) {
        super(_offset);
        expression = _left.getInfo();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
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
