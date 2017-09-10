package aiki.util;
import aiki.fight.enums.Statistic;
import code.util.StringList;
import code.util.ints.Equallable;

public final class TypeStatistic implements Equallable<TypeStatistic> {

    private final String type;

    private final Statistic stat;

    public TypeStatistic(String _type, Statistic _stat) {
        type = _type;
        stat = _stat;
    }

    public String getType() {
        return type;
    }

    public Statistic getStat() {
        return stat;
    }

    @Override
    public boolean eq(TypeStatistic _g) {
        if (stat != _g.stat) {
            return false;
        }
        if (!StringList.quickEq(type, _g.type)) {
            return false;
        }
        return true;
    }

}
