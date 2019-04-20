package aiki.map.characters;


public final class TrainerLeague extends TrainerOneFight implements Fightable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    @Override
    public int getMult() {
        return getMultiplicityFight();
    }
}
