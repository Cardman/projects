package aiki.beans.simulation;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.dto.ItemLine;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.items.Item;
import code.images.ConverterBufferedImage;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public class SelectItemBean extends CommonBean {
    private String item = DataBase.EMPTY_STRING;
    private CustList<ItemLine> items = new CustList<ItemLine>();
    private StringList sortedItems = new StringList();
    private String typedName = DataBase.EMPTY_STRING;
    private String typedPrice = DataBase.EMPTY_STRING;

    private Integer price;
    private String typedClass = DataBase.EMPTY_STRING;
    private boolean player;

    @Override
    public void beforeDisplaying() {
        player = (Boolean) getForms().getVal(IS_POKEMON_PLAYER_MOVES);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        sortedItems = (StringList) getForms().getVal(ITEMS_SET_EDIT);
        items.clear();
        StringMap<String> translationsClasses_;
        translationsClasses_ = data_.getTranslatedClassesDescriptions().getVal(getLanguage());
        for (String i: sortedItems) {
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
            return EDIT_POKEMON_PLAYER;
        }
        return POKEMON_EDIT;
    }
    public String cancelItem() {
        getForms().put(ITEM_EDIT, DataBase.EMPTY_STRING);
        if (player) {
            return EDIT_POKEMON_PLAYER;
        }
        return POKEMON_EDIT;
    }
    public String search() {
        if (!typedPrice.isEmpty()) {
            price = Integer.parseInt(typedPrice);
        } else {
            price = null;
        }
        StringList sortedItems_ = new StringList();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translationsClasses_;
        translationsClasses_ = data_.getTranslatedClassesDescriptions().getVal(getLanguage());
        if (price == null) {
            for (String i: data_.getItems().getKeys()) {
                String display_ = translationsItems_.getVal(i);
                if (!StringList.match(display_, typedName)) {
                    continue;
                }
                Item i_ = data_.getItem(i);
//                String class_ = translationsClasses_.getVal(i_.getClass().getName());
                String class_ = translationsClasses_.getVal(i_.getItemType());
                if (!StringList.match(class_, typedClass)) {
                    continue;
                }
                sortedItems_.add(i);
            }
        } else {
            int int_ = price.intValue();
            for (String i: data_.getItems().getKeys()) {
                String display_ = translationsItems_.getVal(i);
                if (!StringList.match(display_, typedName)) {
                    continue;
                }
                Item i_ = data_.getItem(i);
                if (i_.getPrice() != int_) {
                    continue;
                }
//                String class_ = translationsClasses_.getVal(i_.getClass().getName());
                String class_ = translationsClasses_.getVal(i_.getItemType());
                if (!StringList.match(class_, typedClass)) {
                    continue;
                }
                sortedItems_.add(i);
            }
        }
        sortedItems_.sortElts(new ComparatorTrStrings(translationsItems_));
        getForms().put(ITEMS_SET_EDIT, sortedItems_);
        if (sortedItems_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            item = sortedItems_.first();
            getForms().put(ITEM_EDIT, item);
            if (player) {
                return EDIT_POKEMON_PLAYER;
            }
            return POKEMON_EDIT;
        }
        return DataBase.EMPTY_STRING;
    }
    public String clickLink(Long _index) {
        item = items.get(_index.intValue()).getName();
        getForms().put(ITEM_EDIT, item);
        if (player) {
            return EDIT_POKEMON_PLAYER;
        }
        return POKEMON_EDIT;
    }
    public String getMiniImage(Long _number) {
        String item_ = items.get(_number.intValue()).getName();
        DataBase data_ = (DataBase) getDataBase();
        return ConverterBufferedImage.surroundImage(data_.getMiniItems().getVal(item_));
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