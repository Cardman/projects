package aiki.beans.items;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.fight.items.Item;
import code.util.StringList;
import code.util.StringMap;
import code.util.opers.BaseSixtyFourUtil;

public class ItemBean extends CommonBean {
    private final String itemBean="web/html/items/item.html";

    private Item item;
    private String name;
    private String displayName;
    private int price;
    private String description;
    private String itemImage;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsClasses_;
        translationsClasses_ = data_.getTranslatedClassesDescriptions().getVal(getLanguage());
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        String name_ = (String) getForms().getVal(ITEM);
        if (name_ != null) {
            name = name_;
        }
        itemImage = BaseSixtyFourUtil.getStringByImage(data_.getMiniItems().getVal(name));
        displayName = translationsItems_.getVal(name);
        item = data_.getItem(name);
        price = item.getPrice();
//        description = translationsClasses_.getVal(item.getClass().getName());
        description = translationsClasses_.getVal(item.getItemType());
    }
    public String clickItems() {
        if (!getForms().contains(ITEMS_SET)) {
            getForms().put(ITEMS_SET, new StringList());
        }
        return ITEMS;
    }

    protected Item getItem() {
        DataBase data_ = (DataBase) getDataBase();
        String name_ = (String) getForms().getVal(ITEM);
        if (name_ != null) {
            return data_.getItem(name_);
        }
        return data_.getItem(name);
    }

    protected String getName() {
        return name;
    }

    protected void setName(String _name) {
        name = _name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getItemBean() {
        return itemBean;
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