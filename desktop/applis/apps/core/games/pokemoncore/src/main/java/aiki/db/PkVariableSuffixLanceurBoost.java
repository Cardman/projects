package aiki.db;

import aiki.fight.enums.Statistic;

public final class PkVariableSuffixLanceurBoost extends AbsVariableSuffixImpl implements AbsVariableSuffixStatArg {
    public PkVariableSuffixLanceurBoost(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(Statistic _v) {
        return getData().prefixLanceurBoost(_v.getStatName());
    }
}
