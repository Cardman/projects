package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.CustList;
import code.util.StringList;

public final class NatAnaRendText extends NatAnaRendLeaf implements NatRendBuildEl {

    private final String expression;

    private CustList<NatOperationNode> roots;

    private StringList texts = new StringList();
    NatAnaRendText(String _left) {
        expression = _left;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
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
