package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.ForEachTable;

public final class ReachForEachTable extends ReachForEachAbs implements ReachBreakableBlock,ReachBuildableElMethod,ReachAbruptGroup {
    public ReachForEachTable(ForEachTable _info) {
        super(_info,_info);
    }

}
