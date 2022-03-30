package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;

public abstract class NatAnaRendCondition extends AnaRendParentBlock implements NatRendBuildEl {

    private final String condition;

    private final int conditionOffset;

    private NatOperationNode root;
    NatAnaRendCondition(OffsetStringInfo _condition, int _offset) {
        super(_offset);
        condition = _condition.getInfo();
        conditionOffset = _condition.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        buildConditions(_anaDoc, _page);
    }

    protected void buildConditions(AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        root = NatRenderAnalysis.getRootAnalyzedOperations(condition, 0, _anaDoc, _page);
    }

    public int getConditionOffset() {
        return conditionOffset;
    }

    public NatOperationNode getRoot() {
        return root;
    }
}
