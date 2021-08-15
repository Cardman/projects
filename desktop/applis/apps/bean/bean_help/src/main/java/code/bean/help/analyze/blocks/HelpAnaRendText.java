package code.bean.help.analyze.blocks;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.blocks.NatRendBuildEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.bean.help.analyze.HelpResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendLeaf;
import code.util.StringList;

public final class HelpAnaRendText extends AnaRendLeaf implements NatRendBuildEl {

    private final String expression;

    private StringList texts = new StringList();
    HelpAnaRendText(OffsetStringInfo _left, int _offset) {
        super(_offset);
        expression = _left.getInfo();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        HelpResultText res_ = new HelpResultText();
        res_.buildAna(expression);
        texts = res_.getTexts();
    }

    public StringList getTexts() {
        return texts;
    }

}
