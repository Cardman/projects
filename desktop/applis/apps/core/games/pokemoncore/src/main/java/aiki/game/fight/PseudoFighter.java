package aiki.game.fight;


public class PseudoFighter {

    private long level;

    private String name;

    public PseudoFighter(String _name, long _level) {
        level = _level;
        name = _name;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long _level) {
        level = _level;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }
}
