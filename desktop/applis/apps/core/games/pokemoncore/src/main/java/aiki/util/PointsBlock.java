package aiki.util;

import aiki.instances.Instances;
import aiki.map.levels.Block;
import code.util.CollCapacity;

public final class PointsBlock extends Points<Block> {
    public PointsBlock() {
    }
    public PointsBlock(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Block def() {
        return Instances.newBlock();
    }
}
