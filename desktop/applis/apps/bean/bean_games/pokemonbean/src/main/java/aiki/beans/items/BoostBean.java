package aiki.beans.items;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Boost;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.StringMap;

public class BoostBean extends ItemBean {
    private Rate winPp;
    private int maxEv;
    private DictionaryComparator<String, Short> happiness;
    private DictionaryComparator<Statistic, Short> evs;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        DataBase data_ = getDataBase();
        maxEv = data_.getMaxEv();
        Boost item_ = (Boost) getItem();
        winPp = item_.getWinPp();
        DictionaryComparator<String, Short> happiness_;
        happiness_ = DictionaryComparatorUtil.buildItemsShort(data_,getLanguage());
        for (String i: item_.getHappiness().getKeys()) {
            happiness_.put(i, item_.getHappiness().getVal(i));
        }
        happiness = happiness_;
        DictionaryComparator<Statistic, Short> evs_;
        evs_ = DictionaryComparatorUtil.buildStatisShort(data_,getLanguage());
        for (Statistic s: item_.getEvs().getKeys()) {
            evs_.put(s, item_.getEvs().getVal(s));
        }
        evs = evs_;
    }
    public boolean isBall(int _index) {
        String item_ = happiness.getKey(_index);
        return !item_.isEmpty();
    }
    public String getTrHappiness(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        String item_ = happiness.getKey(_index);
        return translatedItems_.getVal(item_);
    }
    public String clickHappiness(int _index) {
        String item_ = happiness.getKey(_index);
        getForms().put(CST_ITEM, item_);
        return CST_BALL;
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

    public DictionaryComparator<String,Short> getHappiness() {
        return happiness;
    }

    public DictionaryComparator<Statistic,Short> getEvs() {
        return evs;
    }

    public int getMaxEv() {
        return maxEv;
    }
}