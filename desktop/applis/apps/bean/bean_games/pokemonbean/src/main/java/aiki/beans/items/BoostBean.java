package aiki.beans.items;
import aiki.beans.facade.comparators.ComparatorTrStringStatistic;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Boost;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.StringMap;
import code.util.TreeMap;

public class BoostBean extends ItemBean {
    private Rate winPp;
    private int maxEv;
    private TreeMap<String, Short> happiness;
    private TreeMap<Statistic, Short> evs;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        DataBase data_ = (DataBase) getDataBase();
        maxEv = data_.getMaxEv();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Boost item_ = (Boost) getItem();
        winPp = item_.getWinPp();
        TreeMap<String, Short> happiness_;
        happiness_ = new TreeMap<String, Short>(new ComparatorTrStrings(translatedItems_));
        for (String i: item_.getHappiness().getKeys()) {
            happiness_.put(i, item_.getHappiness().getVal(i));
        }
        happiness = happiness_;
        TreeMap<Statistic, Short> evs_;
        evs_ = new TreeMap<Statistic, Short>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: item_.getEvs().getKeys()) {
            evs_.put(s, item_.getEvs().getVal(s));
        }
        evs = evs_;
    }
    public boolean isBall(Long _index) {
        String item_ = happiness.getKey(_index.intValue());
        return !item_.isEmpty();
    }
    public String getTrHappiness(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        String item_ = happiness.getKey(_index.intValue());
        return translatedItems_.getVal(item_);
    }
    public String clickHappiness(Long _index) {
        String item_ = happiness.getKey(_index.intValue());
        getForms().put(ITEM, item_);
        return BALL;
    }
    public String getTrEv(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic statistic_ = evs.getKey(_index.intValue());
        return translatedStatistics_.getVal(statistic_);
    }

    public Rate getWinPp() {
        return winPp;
    }

    public TreeMap<String,Short> getHappiness() {
        return happiness;
    }

    public TreeMap<Statistic,Short> getEvs() {
        return evs;
    }

    public int getMaxEv() {
        return maxEv;
    }
}