package aiki.fight.pokemon;

public final class NameLevel {

    private final String name;

    private int level;

    public NameLevel(String _name, int _level) {
        name = _name;
        level = _level;
    }

    public NameLevel(NameLevel _p) {
        this(_p.name, _p.level);
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

}
