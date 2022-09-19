package aiki.util;

import aiki.instances.Instances;
import aiki.map.characters.Person;
import code.util.CollCapacity;

public final class PointsPerson extends Points<Person> {
    public PointsPerson() {
    }
    public PointsPerson(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Person def() {
        return Instances.newGymTrainer();
    }
}
