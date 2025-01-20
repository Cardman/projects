package aiki.beans.items;

import aiki.beans.CommonBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.items.Ball;
import aiki.fight.items.Item;
import code.scripts.confs.PkScriptPages;
import code.util.StringMap;

public abstract class ItemBean extends CommonBean {
    static final String ITEM_BEAN= PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEM_HTML;

    private DictionaryComparator<String, Long> happiness;
    private String name;
    private String displayName;
    private long price;
    private String description;
    private int[][] itemImage;

    protected void beforeDisplayingItem() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsClasses_;
        translationsClasses_ = data_.getTranslatedClassesDescriptions().getVal(getLanguage());
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        name = getForms().getValStr(CST_ITEM);
        itemImage = data_.getMiniItem(name);
        displayName = translationsItems_.getVal(name);
        Item item_ = data_.getItem(name);
        price = item_.getPrice();
        description = translationsClasses_.getVal(item_.getItemType());
    }

    public void initHappiness(StringMap<Long> _map) {
        DataBase data_ = getDataBase();
        DictionaryComparator<String, Long> happiness_;
        happiness_ = DictionaryComparatorUtil.buildItemsShort(data_,getLanguage());
        for (String i: _map.getKeys()) {
            happiness_.put(i, _map.getVal(i));
        }
        happiness = happiness_;
    }

    public boolean isBall(int _index) {
        return getDataBase().getItem(happiness.getKey(_index)) instanceof Ball;
    }
    public String getTrHappiness(int _index) {
        return getDataBase().getTranslatedItems().getVal(getLanguage()).getVal(happiness.getKey(_index));
    }
    public String clickHappiness(int _index) {
        return tryRedirectIt(happiness.getKey(_index));
    }

    public DictionaryComparator<String,Long> getHappiness() {
        return happiness;
    }


    public String clickItems() {
        getForms().safeItems(CST_ITEMS_SET);
        return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML;
    }

    protected Item getItem() {
        DataBase data_ = getDataBase();
        return data_.getItem(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int[][] getItemImage() {
        return itemImage;
    }

    public String getDescription() {
        return description;
    }

    public long getPrice() {
        return price;
    }
}