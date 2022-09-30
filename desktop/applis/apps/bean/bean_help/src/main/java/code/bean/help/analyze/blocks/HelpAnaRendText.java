package code.bean.help.analyze.blocks;

import code.bean.help.analyze.HelpResultText;
import code.bean.nat.analyze.NatAnalyzingDoc;
import code.bean.nat.analyze.blocks.NatAnaRendDocumentBlock;
import code.bean.nat.analyze.blocks.NatAnaRendLeaf;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.blocks.NatRendBuildEl;
import code.util.StringList;

public final class HelpAnaRendText extends NatAnaRendLeaf implements NatRendBuildEl {

    private final String expression;

    private StringList texts = new StringList();
    HelpAnaRendText(String _left) {
        expression = _left;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        HelpResultText res_ = new HelpResultText();
        res_.buildAna(expression);
        texts = res_.getTexts();
    }

    public StringList getTexts() {
        return texts;
    }

}
