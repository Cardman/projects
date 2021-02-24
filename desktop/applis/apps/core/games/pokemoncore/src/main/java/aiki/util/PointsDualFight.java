package aiki.util;

import aiki.instances.Instances;
import aiki.map.characters.DualFight;
import code.util.CollCapacity;

public final class PointsDualFight extends Points<DualFight> {
    public PointsDualFight() {
    }
    public PointsDualFight(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected DualFight def() {
        return Instances.newDualFight();
    }
}
