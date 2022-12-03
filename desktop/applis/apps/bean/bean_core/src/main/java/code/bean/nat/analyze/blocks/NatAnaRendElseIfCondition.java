package code.bean.nat.analyze.blocks;

import code.sml.NatAnalyzingDoc;

public final class NatAnaRendElseIfCondition extends NatAnaRendCondition {
    NatAnaRendElseIfCondition(String _condition) {
        super(_condition);
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        buildConditions(_anaDoc, _page);
    }

}
