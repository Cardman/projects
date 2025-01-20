package aiki.fight.pokemon;

public final class NameLevel {

    private final String name;

    private long level;

    public NameLevel(String _name, long _level) {
        name = _name;
        level = _level;
    }

    public NameLevel(NameLevel _p) {
        this(_p.name, _p.level);
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

}
