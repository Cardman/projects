package aiki.db;

import aiki.fight.enums.Statistic;

public final class PkVariableSuffixCibleStatis extends AbsVariableSuffixImpl implements AbsVariableSuffixStatArg {
    public PkVariableSuffixCibleStatis(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(Statistic _v) {
        return getData().prefixCibleStatis(_v.getStatName());
    }
}
