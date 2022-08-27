package aiki.util;

import aiki.instances.Instances;
import aiki.map.characters.TrainerMultiFights;

public final class PointsTrainerMultiFights extends Points<TrainerMultiFights> {

    @Override
    protected TrainerMultiFights def() {
        return Instances.newTrainerMultiFights();
    }
}
