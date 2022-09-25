package aiki.fight.items;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;


public final class Boost extends Item {

    private static final String ITEM = "aiki.fight.items.Boost";

    private Rate winPp;
    private StringMap<Short> happiness;
    private IdMap<Statistic, Short> evs;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkPositiveOrZero(winPp,_data);
        if (!evs.isEmpty()) {
            DataInfoChecker.checkZero(winPp,_data);
        }
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBase(),evs.getKeys(),_data);
        for (EntryCust<Statistic, Short> s : evs.entryList()) {
            DataInfoChecker.checkPositiveOrZero(s.getValue(),_data);
        }
        DataInfoChecker.checkStringListContains(DataInfoChecker.itemsBall(_data).getKeys(),happiness.getKeys(),_data);
        for (EntryCust<String, Short> k : happiness.entryList()) {
            DataInfoChecker.checkPositiveOrZero(k.getValue(),_data);
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

    public IdMap<Statistic, Short> getEvs() {
        return evs;
    }

    public void setEvs(IdMap<Statistic, Short> _evs) {
        evs = _evs;
    }

}
