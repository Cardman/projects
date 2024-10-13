package aiki.db;

import aiki.fight.enums.Statistic;

public final class PkVariableSuffixFighterStatis extends AbsVariableSuffixImpl implements AbsVariableSuffixStatArg {
    public PkVariableSuffixFighterStatis(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(Statistic _v) {
        return getData().prefixFighterStatis(_v.getStatName());
    }
}
