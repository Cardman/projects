package aiki.map.characters;
import code.serialize.CheckedData;

@CheckedData
public final class GymTrainer extends TrainerOneFight implements Fightable {

    @Override
    public int getMult() {
        return getMultiplicityFight();
    }

}
