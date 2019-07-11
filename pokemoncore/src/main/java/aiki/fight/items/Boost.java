package aiki.fight.items;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import code.maths.Rate;
import code.util.EntryCust;
import code.util.EnumMap;
import code.util.StringMap;


public final class Boost extends Item {

    private static final String ITEM = "aiki.fight.items.Boost";

    private Rate winPp;
    private StringMap<Short> happiness;
    private EnumMap<Statistic, Short> evs;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!winPp.isZeroOrGt()) {
            _data.setError(true);

        }
        if (!evs.isEmpty()) {
            if (!winPp.isZero()) {
                _data.setError(true);

            }
        }
        for (EntryCust<Statistic, Short> s : evs.entryList()) {
            if (!s.getKey().isWithBaseStatistic()) {
                _data.setError(true);
            }
            if (s.getValue() < 0) {
                _data.setError(true);
            }
        }
        for (String k : happiness.getKeys()) {
            if (happiness.getVal(k) < 0) {
                _data.setError(true);
            }
            Item obj_ = _data.getItem(k);
            if (!(obj_ instanceof Ball)) {
                _data.setError(true);
            }
        }
    }

    public Rate getWinPp() {
        return winPp;
    }

    public void setWinPp(Rate _winPp) {
        winPp = _winPp;
    }

    public StringMap<Short> getHappiness() {
        return happiness;
    }

    public void setHappiness(StringMap<Short> _happiness) {
        happiness = _happiness;
    }

    public EnumMap<Statistic, Short> getEvs() {
        return evs;
    }

    public void setEvs(EnumMap<Statistic, Short> _evs) {
        evs = _evs;
    }

}
