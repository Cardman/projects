package aiki.beans.items;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Boost;
import code.maths.Rate;
import code.util.AbsMap;

public class BoostBean extends ItemBean {
    private Rate winPp;
    private long maxEv;
    private DictionaryComparator<Statistic, Long> evs;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        DataBase data_ = getDataBase();
        maxEv = data_.getMaxEv();
        Boost item_ = (Boost) getItem();
        winPp = item_.getWinPp();
        initHappiness(item_.getHappiness());
        DictionaryComparator<Statistic, Long> evs_;
        evs_ = DictionaryComparatorUtil.buildStatisShort(data_,getLanguage());
        for (Statistic s: item_.getEvs().getKeys()) {
            evs_.put(s, item_.getEvs().getVal(s));
        }
        evs = evs_;
    }
    public String getTrEv(int _index) {
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic statistic_ = evs.getKey(_index);
        return translatedStatistics_.getVal(statistic_);
    }

    public Rate getWinPp() {
        return winPp;
    }

    public DictionaryComparator<Statistic,Long> getEvs() {
        return evs;
    }

    public long getMaxEv() {
        return maxEv;
    }
}