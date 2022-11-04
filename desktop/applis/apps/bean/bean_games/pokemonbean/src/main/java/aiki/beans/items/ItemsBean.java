package aiki.beans.items;

import aiki.beans.WithFilterBean;
import aiki.db.DataBase;
import aiki.fight.items.*;
import code.images.BaseSixtyFourUtil;
import code.util.AbsMap;

public class ItemsBean extends WithFilterBean {

    @Override
    public void beforeDisplaying() {
        AbsMap<String, Item> sortedItems_ = getForms().getValItemData(CST_ITEMS_SET);
        itemInit(sortedItems_);
    }

    public String search() {
//        Integer price_;
//        if (!typedPrice.isEmpty()) {
//            price_ = NumberUtil.parseInt(typedPrice);
//        } else {
//            price_ = null;
//        }
        AbsMap<String,Item> sortedItems_ = sortedItems(getDataBase());
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsItems_;
//        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
//        StringMap<String> translationsClasses_;
//        translationsClasses_ = data_.getTranslatedClassesDescriptions().getVal(getLanguage());
//        if (price_ == null) {
//            for (String i: data_.getItems().getKeys()) {
//                String display_ = translationsItems_.getVal(i);
//                if (!StringUtil.match(display_, typedName)) {
//                    continue;
//                }
//                Item i_ = data_.getItem(i);
////                String class_ = translationsClasses_.getVal(i_.getClass().getName());
//                String class_ = translationsClasses_.getVal(i_.getItemType());
//                if (StringUtil.match(class_, typedClass)) {
//                    sortedItems_.add(i);
//                }
//            }
//        } else {
//            int int_ = price_;
//            for (String i: data_.getItems().getKeys()) {
//                String display_ = translationsItems_.getVal(i);
//                if (!StringUtil.match(display_, typedName)) {
//                    continue;
//                }
//                Item i_ = data_.getItem(i);
//                if (i_.getPrice() != int_) {
//                    continue;
//                }
////                String class_ = translationsClasses_.getVal(i_.getClass().getName());
//                String class_ = translationsClasses_.getVal(i_.getItemType());
//                if (!StringUtil.match(class_, typedClass)) {
//                    continue;
//                }
//                sortedItems_.add(i);
//            }
//        }
//        sortedItems_.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        getForms().putItems(CST_ITEMS_SET, sortedItems_);
        if (sortedItems_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
//            getForms().put(CST_ITEM, sortedItems_.firstKey());
//            Item it_ = sortedItems_.firstValue();
            return tryRedirectIt(sortedItems_.firstKey());
//            return switchItem(it_);
        }
        return AikiBeansItemsStd.WEB_HTML_ITEMS_ITEMS_HTML;
    }

    public static String switchItem(Item _it) {
        if (_it instanceof Ball) {
            return CST_BALL;
        }
        if (_it instanceof Berry) {
            return CST_BERRY;
        }
        if (_it instanceof Boost) {
            return CST_BOOST;
        }
        if (_it instanceof EvolvingItem) {
            return CST_EVOLVINGITEM;
        }
        if (_it instanceof EvolvingStone) {
            return CST_EVOLVINGSTONE;
        }
        if (_it instanceof Fossil) {
            return CST_FOSSIL;
        }
        if (_it instanceof HealingHpStatus) {
            return CST_HEALINGHPSTATUS;
        }
        if (_it instanceof HealingStatus) {
            return CST_HEALINGSTATUS;
        }
        if (_it instanceof HealingHp) {
            return CST_HEALINGHP;
        }
        if (_it instanceof HealingPp) {
            return CST_HEALINGPP;
        }
        if (_it instanceof HealingItem) {
            return CST_HEALINGITEM;
        }
        if (_it instanceof ItemForBattle) {
            return CST_ITEMFORBATTLE;
        }
        if (_it instanceof Repel) {
            return CST_REPEL;
        }
        if (_it instanceof SellingItem) {
            return CST_SELLINGITEM;
        }
        return CST_ITEM;
    }

    public static String switchItemPage(Item _it) {
        if (_it instanceof Ball) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_BALL_HTML;
        }
        if (_it instanceof Berry) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_BERRY_HTML;
        }
        if (_it instanceof Boost) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_BOOST_HTML;
        }
        if (_it instanceof EvolvingItem) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_EVO_ITEM_HTML;
        }
        if (_it instanceof EvolvingStone) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_EVO_STONE_HTML;
        }
        if (_it instanceof Fossil) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_FOSSIL_HTML;
        }
        if (_it instanceof HealingHpStatus) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_HEALINGHPSTATUS_HTML;
        }
        if (_it instanceof HealingStatus) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_HEALINGSTATUS_HTML;
        }
        if (_it instanceof HealingHp) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_HEALINGHP_HTML;
        }
        if (_it instanceof HealingPp) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_HEALINGPP_HTML;
        }
        if (_it instanceof HealingItem) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_HEALINGITEM_HTML;
        }
        if (_it instanceof ItemForBattle) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_ITEMFORBATTLE_HTML;
        }
        if (_it instanceof Repel) {
            return AikiBeansItemsStd.WEB_HTML_ITEMS_REPEL_HTML;
        }
        return AikiBeansItemsStd.WEB_HTML_ITEMS_SELLINGITEM_HTML;
//        if (_it instanceof SellingItem) {
//            return AikiBeansItemsStd.WEB_HTML_ITEMS_SELLINGITEM_HTML;
//        }
//        return AikiBeansItemsStd.WEB_HTML_ITEMS_ITEM_HTML;
    }

    public String clickLink(int _index) {
//        DataBase data_ = getDataBase();
        String item_ = getItems().get(_index).getName();
        return tryRedirectIt(item_);
//        getForms().put(CST_ITEM, item_);
//        Item it_ = data_.getItem(item_);
//        return switchItem(it_);
    }
    public String getMiniImage(int _number) {
        String item_ = getItems().get(_number).getName();
        DataBase data_ = getDataBase();
        return BaseSixtyFourUtil.getStringByImage(data_.getMiniItems().getVal(item_));
    }
}