package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.ForEachLoop;

public final class ReachForEachLoop extends ReachForEachAbs implements ReachBreakableBlock,ReachBuildableElMethod,ReachAbruptGroup {
    public ReachForEachLoop(ForEachLoop _info) {
        super(_info,_info);
    }

}
