package aiki.game.fight;


public class PseudoFighter {

    private short level;

    private String name;

    public PseudoFighter(String _name, short _level) {
        level = _level;
        name = _name;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }
}
