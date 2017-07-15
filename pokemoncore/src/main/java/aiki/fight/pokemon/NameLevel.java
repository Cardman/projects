package aiki.fight.pokemon;
import code.util.StringList;
import code.util.ints.Equallable;

public final class NameLevel implements Equallable<NameLevel> {

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

    @Override
    public boolean eq(NameLevel _g) {
        if (level != _g.level) {
            return false;
        }
        if (!StringList.quickEq(name, _g.name)) {
            return false;
        }
        return true;
    }
}
