package aiki.util;

import aiki.instances.Instances;
import aiki.map.pokemon.WildPk;
import code.util.CollCapacity;

public final class PointsWildPk extends Points<WildPk> {
    public PointsWildPk() {
    }
    public PointsWildPk(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected WildPk def() {
        return Instances.newWildPk();
    }
}
