package aiki.beans.map.characters;
import aiki.beans.CommonBean;
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
import aiki.map.characters.Seller;
import code.util.StringList;
import code.util.StringMap;

public class SellerBean extends CommonBean {

    private Seller seller;

    @Override
    public void beforeDisplaying() {
        seller = (Seller) getForms().getVal(CST_SELLER);
    }
    public String getItem(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        String item_ = getItems().get(_index);
        return translationsItems_.getVal(item_);
    }
    public StringList getItems() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringList items_ = new StringList(seller.getItems());
        items_.sortElts(new ComparatorTrStrings(translationsItems_));
        return items_;
    }
    public String clickItem(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        String item_ = getItems().get(_index);
        getForms().put(CST_ITEM, item_);
        Item it_ = data_.getItem(item_);
        if (it_ instanceof Ball) {
            return CST_BALL;
        }
        if (it_ instanceof Berry) {
            return CST_BERRY;
        }
        if (it_ instanceof Boost) {
            return CST_BOOST;
        }
        if (it_ instanceof EvolvingItem) {
            return CST_EVOLVINGITEM;
        }
        if (it_ instanceof EvolvingStone) {
            return CST_EVOLVINGSTONE;
        }
        if (it_ instanceof Fossil) {
            return CST_FOSSIL;
        }
        if (it_ instanceof HealingHpStatus) {
            return CST_HEALINGHPSTATUS;
        }
        if (it_ instanceof HealingStatus) {
            return CST_HEALINGSTATUS;
        }
        if (it_ instanceof HealingHp) {
            return CST_HEALINGHP;
        }
        if (it_ instanceof HealingPp) {
            return CST_HEALINGPP;
        }
        if (it_ instanceof HealingItem) {
            return CST_HEALINGITEM;
        }
        if (it_ instanceof ItemForBattle) {
            return CST_ITEMFORBATTLE;
        }
        if (it_ instanceof Repel) {
            return CST_REPEL;
        }
        if (it_ instanceof SellingItem) {
            return CST_SELLINGITEM;
        }
        return CST_ITEM;
    }
    public String getTm(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String move_ = getAllTm().get(_index);
        return translationsMoves_.getVal(move_);
    }
    public StringList getAllTm() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_ = new StringList();
        for (Short s: seller.getTm()) {
            moves_.add(data_.getTm().getVal(s));
        }
        moves_.sortElts(new ComparatorTrStrings(translationsMoves_));
        return moves_;
    }
    public String clickTm(int _index) {
        String move_ = getAllTm().get(_index);
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
    }
}