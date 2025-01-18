package aiki.fight.items;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;


public final class Boost extends Item {

    private Rate winPp;
    private StringMap<Integer> happiness;
    private IdMap<Statistic, Integer> evs;

    @Override
    public String getItemType() {
        return BOOST;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkPositiveOrZero(winPp,_data);
        if (!evs.isEmpty()) {
            DataInfoChecker.checkZero(winPp,_data);
        }
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBase(),evs.getKeys(),_data);
        for (EntryCust<Statistic, Integer> s : evs.entryList()) {
            DataInfoChecker.checkPositiveOrZero(s.getValue(),_data);
        }
        DataInfoChecker.checkStringListContains(DataInfoChecker.itemsBall(_data).getKeys(),happiness.getKeys(),_data);
        for (EntryCust<String, Integer> k : happiness.entryList()) {
            DataInfoChecker.checkPositiveOrZero(k.getValue(),_data);
        }
    }

    public Rate getWinPp() {
        return winPp;
    }

    public void setWinPp(Rate _winPp) {
        winPp = _winPp;
    }

    public StringMap<Integer> getHappiness() {
        return happiness;
    }

    public void setHappiness(StringMap<Integer> _happiness) {
        happiness = _happiness;
    }

    public IdMap<Statistic, Integer> getEvs() {
        return evs;
    }

    public void setEvs(IdMap<Statistic, Integer> _evs) {
        evs = _evs;
    }

}
