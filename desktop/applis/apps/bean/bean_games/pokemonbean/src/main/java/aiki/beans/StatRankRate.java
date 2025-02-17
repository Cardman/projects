package aiki.beans;

import aiki.fight.enums.Statistic;
import code.util.IdMap;

public final class StatRankRate {
    private final long rank;
    private final String fail;
    private final String rate;

    public StatRankRate(IdMap<Statistic,Long> _k, Statistic _c, String _l, String _e) {
        if (_k.contains(_c)) {
            this.rank = _k.getVal(_c);
        } else {
            this.rank = 0;
        }
        this.fail = _l;
        this.rate = _e;
    }

    public long getRank() {
        return rank;
    }

    public String getFail() {
        return fail;
    }

    public String getRate() {
        return rate;
    }
}
