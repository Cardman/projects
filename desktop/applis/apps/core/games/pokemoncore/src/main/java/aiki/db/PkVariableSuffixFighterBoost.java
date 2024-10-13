package aiki.db;

import aiki.fight.enums.Statistic;

public final class PkVariableSuffixFighterBoost extends AbsVariableSuffixImpl implements AbsVariableSuffixStatArg {
    public PkVariableSuffixFighterBoost(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(Statistic _v) {
        return getData().prefixFighterBoost(_v.getStatName());
    }
}
