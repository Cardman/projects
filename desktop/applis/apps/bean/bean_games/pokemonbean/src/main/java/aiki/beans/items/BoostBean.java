package aiki.beans.items;

import aiki.beans.*;
import aiki.comparators.*;
import aiki.db.DataBase;
import aiki.facade.*;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Boost;
import code.maths.Rate;
import code.scripts.pages.aiki.*;

public final class BoostBean extends ItemBean {
    private Rate winPp;
    private long maxEv;
    private DictionaryComparator<TranslatedKey, Long> evs;

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        buildHeader();
        displayIntDef(winPp,MessagesPkBean.IT_BOOST,MessagesDataItemsBoost.M_P_18_WIN_PP);
        buildHappiness(MessagesPkBean.IT_BOOST,MessagesDataItemsBoost.M_P_18_HAPPINESS,MessagesDataItemsBoost.M_P_18_HAPPINESS_OTHER_BALL,MessagesDataItemsBoost.M_P_18_HAPPINESS_BALL,MessagesDataItemsBoost.M_P_18_HAPPINESS_BOOST);
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGrid(this,evs,MessagesPkBean.IT_BOOST,MessagesDataItemsBoost.M_P_18_EVS,MessagesDataItemsBoost.M_P_18_EVS_STAT,MessagesDataItemsBoost.M_P_18_EVS_BOOST);
    }
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