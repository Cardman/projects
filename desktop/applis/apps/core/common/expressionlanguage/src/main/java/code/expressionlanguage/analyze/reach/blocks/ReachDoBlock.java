package code.expressionlanguage.analyze.reach.blocks;


import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.DoBlock;

public final class ReachDoBlock extends ReachBracedBlock implements ReachLoop {
    private final String label;
    protected ReachDoBlock(DoBlock _info) {
        super(_info);
        label = _info.getLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        //
    }
}
