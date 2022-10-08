package aiki.beans.simulation;
import aiki.beans.CommonBean;
import aiki.beans.facade.dto.ItemLine;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.items.Item;
import code.images.BaseSixtyFourUtil;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class SelectItemBean extends CommonBean {
    private String item = DataBase.EMPTY_STRING;
    private final CustList<ItemLine> items = new CustList<ItemLine>();
    private String typedName = DataBase.EMPTY_STRING;
    private String typedPrice = DataBase.EMPTY_STRING;

    private String typedClass = DataBase.EMPTY_STRING;
    private boolean player;

    @Override
    public void beforeDisplaying() {
        player = getForms().getValBool(CST_IS_POKEMON_PLAYER_MOVES);
        DataBase data_ = getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringList sortedItems_ = getForms().getValList(CST_ITEMS_SET_EDIT);
        items.clear();
        StringMap<String> translationsClasses_;
        translationsClasses_ = data_.getTranslatedClassesDescriptions().getVal(getLanguage());
        for (String i: sortedItems_) {
            Item i_ = data_.getItem(i);
//            String class_ = translationsClasses_.getVal(i_.getClass().getName());
            String class_ = translationsClasses_.getVal(i_.getItemType());
//            class_ = XmlParser.transformSpecialChars(class_);
            ItemLine item_ = new ItemLine();
            item_.setName(i);
            item_.setDisplayName(translationsItems_.getVal(i));
            item_.setPrice(i_.getPrice());
            item_.setDescriptionClass(class_);
            items.add(item_);
        }
        typedPrice = escapedStringQuote(typedPrice);
        typedName = escapedStringQuote(typedName);
        typedClass = escapedStringQuote(typedClass);
    }
    public String cancel() {
        if (player) {
            return CST_EDIT_POKEMON_PLAYER;
        }
        return CST_POKEMON_EDIT;
    }
    public String cancelItem() {
        getForms().put(CST_ITEM_EDIT, DataBase.EMPTY_STRING);
        if (player) {
            return CST_EDIT_POKEMON_PLAYER;
        }
        return CST_POKEMON_EDIT;
    }
    public String search() {
        StringList sortedItems_ = sortedItems(getDataBase(), typedPrice, typedName, typedClass, getLanguage());
        getForms().put(CST_ITEMS_SET_EDIT, sortedItems_);
        if (sortedItems_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            item = sortedItems_.first();
            getForms().put(CST_ITEM_EDIT, item);
            if (player) {
                return CST_EDIT_POKEMON_PLAYER;
            }
            return CST_POKEMON_EDIT;
        }
        return DataBase.EMPTY_STRING;
    }

    public static StringList sortedItems(DataBase _data, String _typedPrice, String _typedName, String _typedClass, String _language) {
        StringList sortedItems_ = new StringList();
        StringMap<String> translationsItems_;
        translationsItems_ = _data.getTranslatedItems().getVal(_language);
        StringMap<String> translationsClasses_;
        translationsClasses_ = _data.getTranslatedClassesDescriptions().getVal(_language);
        if (_typedPrice.isEmpty()) {
            for (EntryCust<String, Item> i: _data.getItems().entryList()) {
                String display_ = translationsItems_.getVal(i.getKey());
                //                String class_ = translationsClasses_.getVal(i_.getClass().getName());
                if (StringUtil.match(display_, _typedName) && StringUtil.match(translationsClasses_.getVal(i.getValue().getItemType()), _typedClass)) {
                    sortedItems_.add(i.getKey());
                }
            }
        } else {
            int int_ = NumberUtil.parseInt(_typedPrice);
            for (EntryCust<String, Item> i: _data.getItems().entryList()) {
                String display_ = translationsItems_.getVal(i.getKey());
                //                String class_ = translationsClasses_.getVal(i_.getClass().getName());
                if (StringUtil.match(display_, _typedName) && i.getValue().getPrice() == int_ && StringUtil.match(translationsClasses_.getVal(i.getValue().getItemType()), _typedClass)) {
                    sortedItems_.add(i.getKey());
                }
            }
        }
        sortedItems_.sortElts(DictionaryComparatorUtil.cmpItems(_data, _language));
        return sortedItems_;
    }

    public String clickLink(int _index) {
        item = items.get(_index).getName();
        getForms().put(CST_ITEM_EDIT, item);
        if (player) {
            return CST_EDIT_POKEMON_PLAYER;
        }
        return CST_POKEMON_EDIT;
    }
    public String getMiniImage(int _number) {
        String item_ = items.get(_number).getName();
        DataBase data_ = getDataBase();
        return BaseSixtyFourUtil.getStringByImage(data_.getMiniItems().getVal(item_));
    }

    public void setTypedName(String _typedName) {
        typedName = _typedName;
    }

    public String getTypedName() {
        return typedName;
    }

    public void setTypedPrice(String _typedPrice) {
        typedPrice = _typedPrice;
    }

    public String getTypedPrice() {
        return typedPrice;
    }

    public void setTypedClass(String _typedClass) {
        typedClass = _typedClass;
    }

    public String getTypedClass() {
        return typedClass;
    }

    public CustList<ItemLine> getItems() {
        return items;
    }
}