package code.bean.nat.analyze.blocks;

import code.formathtml.analyze.AnalyzingDoc;

public final class NatAnaRendElseIfCondition extends NatAnaRendCondition {
    NatAnaRendElseIfCondition(String _condition) {
        super(_condition);
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        buildConditions(_anaDoc, _page);
    }

}
