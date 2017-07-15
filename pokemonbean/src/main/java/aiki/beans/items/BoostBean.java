package aiki.beans.items;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Boost;

public class BoostBean extends ItemBean {

    @Accessible
    private Rate winPp;

    @Accessible
    private int maxEv;

    @Accessible
    private TreeMap<String, Short> happiness;

    @Accessible
    private TreeMap<Statistic, Short> evs;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
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

    @Accessible
    private boolean isBall(Long _index) {
        String item_ = happiness.getKey(_index.intValue());
        return !item_.isEmpty();
    }

    @Accessible
    private String getTrHappiness(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        String item_ = happiness.getKey(_index.intValue());
        return translatedItems_.getVal(item_);
    }

    @Accessible
    private String clickHappiness(Long _index) {
        String item_ = happiness.getKey(_index.intValue());
        getForms().put(ITEM, item_);
        return BALL;
    }

    @Accessible
    private String getTrEv(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic statistic_ = evs.getKey(_index.intValue());
        return translatedStatistics_.getVal(statistic_);
    }
}
