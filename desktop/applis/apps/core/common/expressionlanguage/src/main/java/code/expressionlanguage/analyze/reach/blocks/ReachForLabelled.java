package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AbsBk;

public abstract class ReachForLabelled extends ReachBracedBlock implements ReachBreakableBlock,ReachBuildableElMethod,ReachAbruptGroup{
    private final String label;
    protected ReachForLabelled(AbsBk _info, String _lab) {
        super(_info);
        label = _lab;
    }

    @Override
    public String getRealLabel() {
        return getLabel();
    }

    public String getLabel() {
        return label;
    }
}
