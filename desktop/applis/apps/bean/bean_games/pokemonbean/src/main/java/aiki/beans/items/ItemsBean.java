package aiki.beans.items;

import aiki.beans.*;
import aiki.beans.facade.dto.*;
import aiki.beans.game.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.fight.items.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ItemsBean extends WithFilterBean implements BeanRenderWithAppName{
    private IntBeanChgSubmit updateValues;
    public ItemsBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(file().getVal(MessagesDataItems.M_P_29_TITLE));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,this),MessagesPkBean.ITEMS,MessagesDataItems.M_P_29_INDEX);
        initPage();
        initLine();
        formatMessage(MessagesPkBean.ITEMS,MessagesDataItems.M_P_29_CONTENT_NAME);
        setTypedName(DifficultyBeanForm.txt(getBuilder().getGenInput(),this,getTypedName().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.ITEMS,MessagesDataItems.M_P_29_PRICE_DOT);
        setTypedPrice(DifficultyBeanForm.txt(getBuilder().getGenInput(),this,getTypedPrice().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.ITEMS,MessagesDataItems.M_P_29_CONTENT);
        setTypedClass(DifficultyBeanForm.txt(getBuilder().getGenInput(),this,getTypedClass().tryRet()));
        feedParents();
        initLine();
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.ITEMS,MessagesDataItems.M_P_29_OK));
        getUpdateValues().addEvt(new ItemsBeanSearch(this));
        feedParents();
        new BeanDisplayList<ItemLine>(new BeanDisplayItemLine()).displayGrid(this,getItems(),MessagesPkBean.ITEMS,MessagesDataItems.M_P_29_ITEMS,MessagesDataItems.M_P_29_IMAGE,MessagesDataItems.M_P_29_NAME,MessagesDataItems.M_P_29_PRICE,MessagesDataItems.M_P_29_DESCRIPTION);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,this),MessagesPkBean.ITEMS,MessagesDataItems.M_P_29_INDEX);
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.ITEMS).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        AbsMap<TranslatedKey, Item> sortedItems_ = getForms().getValItemData(CST_ITEMS_SET);
        itemInit(sortedItems_);
    }

    public String search() {
//        Integer price_;
//        if (!typedPrice.isEmpty()) {
//            price_ = NumberUtil.parseInt(typedPrice);
//        } else {
//            price_ = null;
//        }
        AbsMap<TranslatedKey, Item> sortedItems_ = sortedItems();
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
            return tryRedirect(sortedItems_.firstKey());
//            return switchItem(it_);
        }
        return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML;
    }

    public static String switchItemPage(Item _it) {
        if (_it instanceof Ball) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BALL_HTML;
        }
        if (_it instanceof Berry) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BERRY_HTML;
        }
        if (_it instanceof Boost) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BOOST_HTML;
        }
        if (_it instanceof EvolvingItem) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_EVO_ITEM_HTML;
        }
        if (_it instanceof EvolvingStone) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_EVO_STONE_HTML;
        }
        if (_it instanceof Fossil) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_FOSSIL_HTML;
        }
        if (_it instanceof HealingHpStatus) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGHPSTATUS_HTML;
        }
        if (_it instanceof HealingStatus) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGSTATUS_HTML;
        }
        if (_it instanceof HealingHp) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGHP_HTML;
        }
        if (_it instanceof HealingPp) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGPP_HTML;
        }
        if (_it instanceof HealingItem) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGITEM_HTML;
        }
        if (_it instanceof ItemForBattle) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML;
        }
        if (_it instanceof Repel) {
            return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_REPEL_HTML;
        }
        return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_SELLINGITEM_HTML;
//        if (_it instanceof SellingItem) {
//            return AikiBeansItemsStd.WEB_HTML_ITEMS_SELLINGITEM_HTML;
//        }
//        return AikiBeansItemsStd.WEB_HTML_ITEMS_ITEM_HTML;
    }

    public String clickLink(int _index) {
//        DataBase data_ = getDataBase();
        return tryRedirect(getItemsTr().get(_index));
//        getForms().put(CST_ITEM, item_);
//        Item it_ = data_.getItem(item_);
//        return switchItem(it_);
    }
    public int[][] getMiniImage(int _number) {
        String item_ = getItems().get(_number).getName().getKey();
        DataBase data_ = getDataBase();
        return data_.getMiniItem(item_);
    }
}