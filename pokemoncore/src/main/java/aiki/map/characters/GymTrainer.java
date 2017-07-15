package aiki.map.characters;
import code.datacheck.CheckedData;

@CheckedData
public class GymTrainer extends TrainerOneFight implements Fightable {

    @Override
    public int getMult() {
        return getMultiplicityFight();
    }

}
