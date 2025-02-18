package aiki.beans.items;

import aiki.beans.TranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Boost;
import code.maths.Rate;

public class BoostBean extends ItemBean {
    private Rate winPp;
    private long maxEv;
    private DictionaryComparator<TranslatedKey, Long> evs;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        DataBase data_ = getDataBase();
        maxEv = data_.getMaxEv();
        Boost item_ = (Boost) getItem();
        winPp = item_.getWinPp();
        initHappiness(item_.getHappiness());
        DictionaryComparator<TranslatedKey, Long> evs_;
        evs_ = DictionaryComparatorUtil.buildStatisShort();
        for (Statistic s: item_.getEvs().getKeys()) {
            evs_.put(buildSi(getFacade(),s), item_.getEvs().getVal(s));
        }
        evs = evs_;
    }
    public String getTrEv(int _index) {
        return evs.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        Statistic statistic_ = evs.getKey(_index);
//        return translatedStatistics_.getVal(statistic_);
    }

    public Rate getWinPp() {
        return winPp;
    }

    public DictionaryComparator<TranslatedKey,Long> getEvs() {
        return evs;
    }

    public long getMaxEv() {
        return maxEv;
    }
}