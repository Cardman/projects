package aiki.beans.items;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.items.HealingItem;
import code.util.StringMap;

public class HealingItemBean extends ItemBean {
    static final String HEALING_ITEM_BEAN="web/html/items/healingitem.html";
    private DictionaryComparator<String, Short> happiness;
    private boolean healingTeam;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        DataBase data_ = getDataBase();
        HealingItem item_ = (HealingItem) getItem();
        healingTeam = item_.getHealingTeam();
        DictionaryComparator<String, Short> happiness_;
        happiness_ = DictionaryComparatorUtil.buildItemsShort(data_,getLanguage());
        for (String i: item_.getHappiness().getKeys()) {
            happiness_.put(i, item_.getHappiness().getVal(i));
        }
        happiness = happiness_;
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

    public boolean getHealingTeam() {
        return healingTeam;
    }

    public DictionaryComparator<String,Short> getHappiness() {
        return happiness;
    }
}