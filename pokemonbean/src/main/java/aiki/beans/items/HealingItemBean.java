package aiki.beans.items;
import code.bean.Accessible;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.items.HealingItem;

public class HealingItemBean extends ItemBean {

    @Accessible
    private final String healingItemBean="web/html/items/healingitem.html";

    @Accessible
    private TreeMap<String, Short> happiness;

    @Accessible
    private boolean healingTeam;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        HealingItem item_ = (HealingItem) getItem();
        healingTeam = item_.getHealingTeam();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        TreeMap<String, Short> happiness_;
        happiness_ = new TreeMap<String, Short>(new ComparatorTrStrings(translatedItems_));
        for (String i: item_.getHappiness().getKeys()) {
            happiness_.put(i, item_.getHappiness().getVal(i));
        }
        happiness = happiness_;
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
}
