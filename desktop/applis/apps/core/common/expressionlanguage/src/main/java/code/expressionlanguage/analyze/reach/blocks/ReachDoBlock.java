package code.expressionlanguage.analyze.reach.blocks;


import code.expressionlanguage.analyze.blocks.DoBlock;

public final class ReachDoBlock extends ReachBracedBlock implements ReachBreakableBlock {
    private final String label;
    public ReachDoBlock(DoBlock _info) {
        super(_info);
        label = _info.getLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

}
