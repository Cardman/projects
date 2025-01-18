package aiki.beans.map.characters;

import aiki.beans.CommonBean;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.map.characters.Seller;
import code.util.StringList;
import code.util.StringMap;

public class SellerBean extends CommonBean {

    private Seller seller;

    @Override
    public void beforeDisplaying() {
        seller = (Seller) getForms().getValPers(CST_PERSON);
    }
    public String getItem(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        String item_ = getItems().get(_index);
        return translationsItems_.getVal(item_);
    }
    public StringList getItems() {
        DataBase data_ = getDataBase();
        StringList items_ = new StringList(seller.getItems());
        items_.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        return items_;
    }
    public String clickItem(int _index) {
        String item_ = getItems().get(_index);
        return tryRedirectIt(item_);
//        if (it_ instanceof Ball) {
//            return CST_BALL;
//        }
//        if (it_ instanceof Berry) {
//            return CST_BERRY;
//        }
//        if (it_ instanceof Boost) {
//            return CST_BOOST;
//        }
//        if (it_ instanceof EvolvingItem) {
//            return CST_EVOLVINGITEM;
//        }
//        if (it_ instanceof EvolvingStone) {
//            return CST_EVOLVINGSTONE;
//        }
//        if (it_ instanceof Fossil) {
//            return CST_FOSSIL;
//        }
//        if (it_ instanceof HealingHpStatus) {
//            return CST_HEALINGHPSTATUS;
//        }
//        if (it_ instanceof HealingStatus) {
//            return CST_HEALINGSTATUS;
//        }
//        if (it_ instanceof HealingHp) {
//            return CST_HEALINGHP;
//        }
//        if (it_ instanceof HealingPp) {
//            return CST_HEALINGPP;
//        }
//        if (it_ instanceof HealingItem) {
//            return CST_HEALINGITEM;
//        }
//        if (it_ instanceof ItemForBattle) {
//            return CST_ITEMFORBATTLE;
//        }
//        if (it_ instanceof Repel) {
//            return CST_REPEL;
//        }
//        if (it_ instanceof SellingItem) {
//            return CST_SELLINGITEM;
//        }
//        return CST_ITEM;
    }
    public String getTm(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String move_ = getAllTm().get(_index);
        return translationsMoves_.getVal(move_);
    }
    public StringList getAllTm() {
        DataBase data_ = getDataBase();
        StringList moves_ = new StringList();
        for (Integer s: seller.getTm()) {
            moves_.add(data_.getTm().getVal(s));
        }
        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return moves_;
    }
    public String clickTm(int _index) {
        String move_ = getAllTm().get(_index);
        return tryRedirectMv(move_);
    }
}