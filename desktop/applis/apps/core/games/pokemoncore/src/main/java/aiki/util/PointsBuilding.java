package aiki.util;

import aiki.instances.Instances;
import aiki.map.buildings.Building;
import code.util.CollCapacity;

public final class PointsBuilding extends Points<Building> {
    public PointsBuilding() {
    }
    public PointsBuilding(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Building def() {
        return Instances.newGym();
    }
}
