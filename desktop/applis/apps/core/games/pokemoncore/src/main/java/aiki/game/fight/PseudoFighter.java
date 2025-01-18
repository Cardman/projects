package aiki.game.fight;


public class PseudoFighter {

    private int level;

    private String name;

    public PseudoFighter(String _name, int _level) {
        level = _level;
        name = _name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int _level) {
        level = _level;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }
}
