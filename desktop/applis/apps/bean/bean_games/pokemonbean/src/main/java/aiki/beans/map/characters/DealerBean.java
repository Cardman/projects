package aiki.beans.map.characters;

import aiki.beans.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.map.characters.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class DealerBean extends CommonBean implements BeanRenderWithAppName {

    private CustList<TranslatedKey> itemsDealer;
    private CustList<TranslatedKey> allTmDealer;
    public DealerBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(file().getVal(MessagesDataMapLevel.M_P_32_TITLE_DEALER));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML), MessagesPkBean.MAP, MessagesDataMapLevel.M_P_32_INDEX);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_MAP_MAP_HTML),MessagesPkBean.MAP, MessagesDataMapLevel.M_P_32_MAP);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML),MessagesPkBean.MAP, MessagesDataMapLevel.M_P_32_LEVEL);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,itemsDealer);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,allTmDealer);
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.MAP).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        DealerItem dealer_ = (DealerItem) getForms().getValPers(CST_PERSON);
        itemsDealer = listTrStringsIt(dealer_.getItems(),getFacade());
        DataBase data_ = getDataBase();
        StringList moves_ = new StringList();
        for (Integer s: dealer_.getTechnicalMoves()) {
            moves_.add(data_.getTm().getVal(s));
        }
        allTmDealer = listTrStringsMv(moves_,getFacade());
    }
    public String getItem(int _index) {
        return itemsDealer.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsItems_;
//        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
//        String item_ = getItemsDealer().get(_index);
//        return translationsItems_.getVal(item_);
    }
    public CustList<TranslatedKey> getItemsDealer() {
        return itemsDealer;
    }
    public String clickItem(int _index) {
        return tryRedirect(itemsDealer.get(_index));
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
    public String getTmDealer(int _index) {
        return allTmDealer.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String move_ = getAllTmDealer().get(_index);
//        return translationsMoves_.getVal(move_);
    }

    public CustList<TranslatedKey> getAllTmDealer() {
        return allTmDealer;
    }

    //    public StringList getAllTmDealer() {
//        DataBase data_ = getDataBase();
//        StringList moves_ = new StringList();
//        for (Integer s: dealer.getTechnicalMoves()) {
//            moves_.add(data_.getTm().getVal(s));
//        }
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
//        return moves_;
//    }
    public String clickTm(int _index) {
        return tryRedirect(allTmDealer.get(_index));
    }
}