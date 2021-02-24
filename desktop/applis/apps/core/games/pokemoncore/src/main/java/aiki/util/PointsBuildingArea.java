package aiki.util;

import aiki.instances.Instances;
import aiki.map.tree.BuildingArea;

public final class PointsBuildingArea extends Points<BuildingArea> {

    @Override
    protected BuildingArea def() {
        BuildingArea b_ = new BuildingArea();
        b_.initialize(Instances.newGym());
        return b_;
    }
}
