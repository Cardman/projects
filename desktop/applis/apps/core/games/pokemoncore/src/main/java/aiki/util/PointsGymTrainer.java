package aiki.util;

import aiki.instances.Instances;
import aiki.map.characters.GymTrainer;
import code.util.CollCapacity;

public final class PointsGymTrainer extends Points<GymTrainer> {
    public PointsGymTrainer() {
    }
    public PointsGymTrainer(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected GymTrainer def() {
        return Instances.newGymTrainer();
    }
}
