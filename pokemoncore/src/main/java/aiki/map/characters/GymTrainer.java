package aiki.map.characters;

public final class GymTrainer extends TrainerOneFight implements Fightable {

    @Override
    public int getMult() {
        return getMultiplicityFight();
    }

}
