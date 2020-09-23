package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.Block;

public abstract class ReachSwitchPartBlock extends ReachBracedBlock implements ReachBuildableElMethod {
    protected ReachSwitchPartBlock(Block _info) {
        super(_info);
    }
}
