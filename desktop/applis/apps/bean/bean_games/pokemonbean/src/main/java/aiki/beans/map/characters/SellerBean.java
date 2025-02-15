package aiki.beans.map.characters;

import aiki.beans.*;
import aiki.db.*;
import aiki.map.characters.*;
import code.util.*;

public class SellerBean extends CommonBean {

    private CustList<TranslatedKey> items;
    private CustList<TranslatedKey> allTm;

    @Override
    public void beforeDisplaying() {
        Seller seller_ = (Seller) getForms().getValPers(CST_PERSON);
        items = listTrStringsIt(seller_.getItems(),getFacade());
        DataBase data_ = getDataBase();
        StringList moves_ = new StringList();
        for (Integer s: seller_.getTm()) {
            moves_.add(data_.getTm().getVal(s));
        }
        allTm = listTrStringsMv(moves_,getFacade());
    }
    public String getItem(int _index) {
        return items.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsItems_;
//        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
//        String item_ = getItems().get(_index);
//        return translationsItems_.getVal(item_);
    }
    public CustList<TranslatedKey> getItems() {
        return items;
    }
    public String clickItem(int _index) {
        return tryRedirect(items.get(_index));
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
        return allTm.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String move_ = getAllTm().get(_index);
//        return translationsMoves_.getVal(move_);
    }
    public CustList<TranslatedKey> getAllTm() {
        return allTm;
    }
//    public StringList getAllTm() {
//        DataBase data_ = getDataBase();
//        StringList moves_ = new StringList();
//        for (Integer s: seller.getTm()) {
//            moves_.add(data_.getTm().getVal(s));
//        }
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
//        return moves_;
//    }
    public String clickTm(int _index) {
        return tryRedirect(allTm.get(_index));
    }
}