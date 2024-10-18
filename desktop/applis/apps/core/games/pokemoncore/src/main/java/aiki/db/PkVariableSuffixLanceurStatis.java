package aiki.db;

import aiki.fight.enums.Statistic;

public final class PkVariableSuffixLanceurStatis extends AbsVariableSuffixImpl implements AbsVariableSuffixStatArg {
    public PkVariableSuffixLanceurStatis(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(Statistic _v) {
        return getData().prefixLanceurStatis(_v);
    }
}
