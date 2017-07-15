package aiki.beans.items;
import code.bean.Accessible;
import code.images.ConverterBufferedImage;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.fight.items.Item;

public class ItemBean extends CommonBean {

    @Accessible
    private final String itemBean="web/html/items/item.html";

    private Item item;

    @Accessible
    private String name;

    @Accessible
    private String displayName;

    @Accessible
    private int price;

    @Accessible
    private String description;

    @Accessible
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
        itemImage = ConverterBufferedImage.surroundImage(data_.getMiniItems().getVal(name));
        displayName = translationsItems_.getVal(name);
        item = data_.getItem(name);
        price = item.getPrice();
//        description = translationsClasses_.getVal(item.getClass().getName());
        description = translationsClasses_.getVal(item.getItemType());
    }

    @Accessible
    private String clickItems() {
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
}
