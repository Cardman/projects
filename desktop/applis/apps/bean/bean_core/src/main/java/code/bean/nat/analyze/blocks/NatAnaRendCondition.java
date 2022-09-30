package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatAnalyzingDoc;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.opers.NatOperationNode;

public abstract class NatAnaRendCondition extends NatAnaRendParentBlock implements NatRendBuildEl {

    private final String condition;

    private NatOperationNode root;
    NatAnaRendCondition(String _condition) {
        super();
        condition = _condition;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        buildConditions(_anaDoc, _page);
    }

    protected void buildConditions(NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        root = NatRenderAnalysis.getRootAnalyzedOperations(condition, 0, _anaDoc, _page);
    }

    public NatOperationNode getRoot() {
        return root;
    }
}
