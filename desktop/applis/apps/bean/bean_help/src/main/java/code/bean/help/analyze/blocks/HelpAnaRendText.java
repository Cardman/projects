package code.bean.help.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.bean.help.analyze.HelpResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendLeaf;
import code.util.StringList;

public final class HelpAnaRendText extends AnaRendLeaf {

    private final String expression;

    private final int expressionOffset;

    private StringList texts = new StringList();
    HelpAnaRendText(OffsetStringInfo _left, int _offset) {
        super(_offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(expressionOffset);
        _page.zeroOffset();
        HelpResultText res_ = new HelpResultText();
        res_.buildAna(expression);
        texts = res_.getTexts();
    }

    public StringList getTexts() {
        return texts;
    }

}
