package aiki.fight.pokemon;

public final class NameLevel {

    private final String name;

    private short level;

    public NameLevel(String _name, int _level) {
        name = _name;
        level = (short) _level;
    }

    public NameLevel(NameLevel _p) {
        this(_p.name, _p.level);
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

}
