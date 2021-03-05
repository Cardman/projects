package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AbsBk;

public abstract class ReachSwitchPartBlock extends ReachBracedBlock implements ReachBuildableElMethod {
    protected ReachSwitchPartBlock(AbsBk _info) {
        super(_info);
    }
}
