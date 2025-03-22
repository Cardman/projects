package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.dto.ItemLine;
import aiki.db.DataBase;
import aiki.facade.*;
import aiki.fight.items.Item;
import code.scripts.pages.aiki.*;
import code.util.AbsMap;
import code.util.StringMap;

public final class SelectItemBean extends WithFilterBean {

    private boolean player;
    private IntBeanChgSubmit updateValues;

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_SELECT_ITEM));
        formatMessageAnc(new SelectItemBeanCancel(this),MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_CANCEL);
        formatMessageAnc(new SelectItemBeanCancelItem(this),MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_CANCEL_ITEM);
        initFormIt();
        initLine();
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_SEARCH));
        getUpdateValues().addEvt(new SelectItemBeanSearch(this));
        feedParents();
        new BeanDisplayListGrid<ItemLine>(new BeanDisplayItemLineSimu(this)).displayGrid(this,getItems(),MessagesPkBean.ITEMS,MessagesDataItems.M_P_29_ITEMS,MessagesDataItems.M_P_29_IMAGE,MessagesDataItems.M_P_29_NAME,MessagesDataItems.M_P_29_PRICE,MessagesDataItems.M_P_29_DESCRIPTION);
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }
    public StringMap<String> file() {
        return file(MessagesPkBean.SIMU_LEVEL).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        player = getForms().getValBool(SIMU_CST_IS_POKEMON_PLAYER_MOVES);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsItems_;
//        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        AbsMap<TranslatedKey, Item> sortedItems_ = getForms().getValItemData(SIMU_CST_ITEMS_SET_EDIT);
        itemInit(sortedItems_);
//        getItems().clear();
//        StringMap<String> translationsClasses_;
//        translationsClasses_ = data_.getTranslatedClassesDescriptions().getVal(getLanguage());
//        for (String i: sortedItems_) {
//            Item i_ = data_.getItem(i);
////            String class_ = translationsClasses_.getVal(i_.getClass().getName());
//            String class_ = translationsClasses_.getVal(i_.getItemType());
////            class_ = XmlParser.transformSpecialChars(class_);
//            ItemLine item_ = new ItemLine();
//            item_.setName(i);
//            item_.setDisplayName(translationsItems_.getVal(i));
//            item_.setPrice(i_.getPrice());
//            item_.setDescriptionClass(class_);
//            getItems().add(item_);
//        }
//        escapeInputs();
    }
    public String cancel() {
        return redirect();
    }
    public String cancelItem() {
        getForms().put(SIMU_CST_ITEM_EDIT, DataBase.EMPTY_STRING);
        return redirect();
    }
    public String search() {
        AbsMap<TranslatedKey, Item> sortedItems_ = sortedItems();
        getForms().putItems(SIMU_CST_ITEMS_SET_EDIT, sortedItems_);
        if (sortedItems_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            return putName(sortedItems_.firstKey());
        }
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SELECTITEM_HTML;
    }

//    public String clickLink(int _index) {
//        return putName(getItems().get(_index).getName());
//    }

    public String putName(TranslatedKey _tk) {
        getForms().put(SIMU_CST_ITEM_EDIT, _tk.getKey());
        return redirect();
    }
    private String redirect() {
        if (player) {
            return CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML;
        }
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }
    public int[][] getMiniImage(int _number) {
        String item_ = getItems().get(_number).getName().getKey();
        DataBase data_ = getDataBase();
        return data_.getMiniItem(item_);
    }
}