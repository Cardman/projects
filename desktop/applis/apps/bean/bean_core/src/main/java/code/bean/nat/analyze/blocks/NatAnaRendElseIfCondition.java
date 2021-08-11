package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;

public final class NatAnaRendElseIfCondition extends NatAnaRendCondition {
    NatAnaRendElseIfCondition(OffsetStringInfo _condition, int _offset) {
        super(_condition, _offset);
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        buildConditions(_anaDoc, _page);
    }

}
