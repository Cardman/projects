package aiki.beans.items;
import aiki.beans.CommonBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.items.Item;
import code.images.BaseSixtyFourUtil;
import code.util.StringList;
import code.util.StringMap;

public abstract class ItemBean extends CommonBean {
    static final String ITEM_BEAN="web/html/items/item.html";

    private DictionaryComparator<String, Short> happiness;
    private String name;
    private String displayName;
    private int price;
    private String description;
    private String itemImage;

    protected void beforeDisplayingItem() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsClasses_;
        translationsClasses_ = data_.getTranslatedClassesDescriptions().getVal(getLanguage());
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        String name_ = getForms().getValStr(CST_ITEM);
        if (name_ != null) {
            name = name_;
        }
        itemImage = BaseSixtyFourUtil.getStringByImage(data_.getMiniItems().getVal(name));
        displayName = translationsItems_.getVal(name);
        Item item_ = data_.getItem(name);
        price = item_.getPrice();
        description = translationsClasses_.getVal(item_.getItemType());
    }

    public void initHappiness(StringMap<Short> _map) {
        DataBase data_ = getDataBase();
        DictionaryComparator<String, Short> happiness_;
        happiness_ = DictionaryComparatorUtil.buildItemsShort(data_,getLanguage());
        for (String i: _map.getKeys()) {
            happiness_.put(i, _map.getVal(i));
        }
        happiness = happiness_;
    }

    public boolean isBall(int _index) {
        return !happiness.getKey(_index).isEmpty();
    }
    public String getTrHappiness(int _index) {
        return getDataBase().getTranslatedItems().getVal(getLanguage()).getVal(happiness.getKey(_index));
    }
    public String clickHappiness(int _index) {
        getForms().put(CST_ITEM, happiness.getKey(_index));
        return CST_BALL;
    }

    public DictionaryComparator<String,Short> getHappiness() {
        return happiness;
    }


    public String clickItems() {
        if (!getForms().contains(CST_ITEMS_SET)) {
            getForms().put(CST_ITEMS_SET, new StringList());
        }
        return CST_ITEMS;
    }

    protected Item getItem() {
        DataBase data_ = getDataBase();
        String name_ = getForms().getValStr(CST_ITEM);
        if (name_ != null) {
            return data_.getItem(name_);
        }
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

    public String getItemImage() {
        return itemImage;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}