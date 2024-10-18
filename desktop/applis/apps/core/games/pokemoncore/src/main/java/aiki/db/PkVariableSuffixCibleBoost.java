package aiki.db;

import aiki.fight.enums.Statistic;

public final class PkVariableSuffixCibleBoost extends AbsVariableSuffixImpl implements AbsVariableSuffixStatArg {
    public PkVariableSuffixCibleBoost(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(Statistic _v) {
        return getData().prefixCibleBoost(_v);
    }
}
