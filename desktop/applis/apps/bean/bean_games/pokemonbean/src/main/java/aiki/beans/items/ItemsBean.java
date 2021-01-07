package aiki.beans.items;
import aiki.beans.CommonBean;
import aiki.beans.facade.dto.ItemLine;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.Boost;
import aiki.fight.items.EvolvingItem;
import aiki.fight.items.EvolvingStone;
import aiki.fight.items.Fossil;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingStatus;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.items.Repel;
import aiki.fight.items.SellingItem;
import code.images.BaseSixtyFourUtil;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class ItemsBean extends CommonBean {
    private final CustList<ItemLine> items = new CustList<ItemLine>();
    private StringList sortedItems = new StringList();
    private String typedName = DataBase.EMPTY_STRING;
    private String typedPrice = DataBase.EMPTY_STRING;

    private Integer price;
    private String typedClass = DataBase.EMPTY_STRING;

    @Override
    public void beforeDisplaying() {
        sortedItems = (StringList) getForms().getVal(ITEMS_SET);
        DataBase data_ = (DataBase) getDataBase();
        items.clear();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
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
    public String search() {
        if (!typedPrice.isEmpty()) {
            price = NumberUtil.parseInt(typedPrice);
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
                if (!StringUtil.match(display_, typedName)) {
                    continue;
                }
                Item i_ = data_.getItem(i);
//                String class_ = translationsClasses_.getVal(i_.getClass().getName());
                String class_ = translationsClasses_.getVal(i_.getItemType());
                if (!StringUtil.match(class_, typedClass)) {
                    continue;
                }
                sortedItems_.add(i);
            }
        } else {
            int int_ = price;
            for (String i: data_.getItems().getKeys()) {
                String display_ = translationsItems_.getVal(i);
                if (!StringUtil.match(display_, typedName)) {
                    continue;
                }
                Item i_ = data_.getItem(i);
                if (i_.getPrice() != int_) {
                    continue;
                }
//                String class_ = translationsClasses_.getVal(i_.getClass().getName());
                String class_ = translationsClasses_.getVal(i_.getItemType());
                if (!StringUtil.match(class_, typedClass)) {
                    continue;
                }
                sortedItems_.add(i);
            }
        }
        sortedItems_.sortElts(new ComparatorTrStrings(translationsItems_));
        getForms().put(ITEMS_SET, sortedItems_);
        if (sortedItems_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(ITEM, sortedItems_.first());
            Item it_ = data_.getItem(sortedItems_.first());
            if (it_ instanceof Ball) {
                return BALL;
            }
            if (it_ instanceof Berry) {
                return BERRY;
            }
            if (it_ instanceof Boost) {
                return BOOST;
            }
            if (it_ instanceof EvolvingItem) {
                return EVOLVINGITEM;
            }
            if (it_ instanceof EvolvingStone) {
                return EVOLVINGSTONE;
            }
            if (it_ instanceof Fossil) {
                return FOSSIL;
            }
            if (it_ instanceof HealingHpStatus) {
                return HEALINGHPSTATUS;
            }
            if (it_ instanceof HealingStatus) {
                return HEALINGSTATUS;
            }
            if (it_ instanceof HealingHp) {
                return HEALINGHP;
            }
            if (it_ instanceof HealingPp) {
                return HEALINGPP;
            }
            if (it_ instanceof HealingItem) {
                return HEALINGITEM;
            }
            if (it_ instanceof ItemForBattle) {
                return ITEMFORBATTLE;
            }
            if (it_ instanceof Repel) {
                return REPEL;
            }
            if (it_ instanceof SellingItem) {
                return SELLINGITEM;
            }
            return ITEM;
        }
        return ITEMS;
    }
    public String clickLink(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        String item_ = items.get(_index).getName();
        getForms().put(ITEM, item_);
        Item it_ = data_.getItem(item_);
        if (it_ instanceof Ball) {
            return BALL;
        }
        if (it_ instanceof Berry) {
            return BERRY;
        }
        if (it_ instanceof Boost) {
            return BOOST;
        }
        if (it_ instanceof EvolvingItem) {
            return EVOLVINGITEM;
        }
        if (it_ instanceof EvolvingStone) {
            return EVOLVINGSTONE;
        }
        if (it_ instanceof Fossil) {
            return FOSSIL;
        }
        if (it_ instanceof HealingHpStatus) {
            return HEALINGHPSTATUS;
        }
        if (it_ instanceof HealingStatus) {
            return HEALINGSTATUS;
        }
        if (it_ instanceof HealingHp) {
            return HEALINGHP;
        }
        if (it_ instanceof HealingPp) {
            return HEALINGPP;
        }
        if (it_ instanceof HealingItem) {
            return HEALINGITEM;
        }
        if (it_ instanceof ItemForBattle) {
            return ITEMFORBATTLE;
        }
        if (it_ instanceof Repel) {
            return REPEL;
        }
        if (it_ instanceof SellingItem) {
            return SELLINGITEM;
        }
        return ITEM;
    }
    public String getMiniImage(int _number) {
        String item_ = items.get(_number).getName();
        DataBase data_ = (DataBase) getDataBase();
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