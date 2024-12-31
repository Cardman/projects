package aiki.beans.simulation;

import aiki.beans.WithFilterBean;
import aiki.db.DataBase;
import aiki.fight.items.Item;
import code.scripts.confs.PkScriptPages;
import code.util.AbsMap;

public class SelectItemBean extends WithFilterBean {
    private String item = DataBase.EMPTY_STRING;

    private boolean player;

    @Override
    public void beforeDisplaying() {
        player = getForms().getValBool(CST_IS_POKEMON_PLAYER_MOVES);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsItems_;
//        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        AbsMap<String, Item> sortedItems_ = getForms().getValItemData(CST_ITEMS_SET_EDIT);
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
        getForms().put(CST_ITEM_EDIT, DataBase.EMPTY_STRING);
        return redirect();
    }
    public String search() {
        AbsMap<String, Item> sortedItems_ = sortedItems(getDataBase());
        getForms().putItems(CST_ITEMS_SET_EDIT, sortedItems_);
        if (sortedItems_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            item = sortedItems_.firstKey();
            getForms().put(CST_ITEM_EDIT, item);
            return redirect();
        }
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTITEM_HTML;
    }

    public String clickLink(int _index) {
        item = getItems().get(_index).getName();
        getForms().put(CST_ITEM_EDIT, item);
        return redirect();
    }
    private String redirect() {
        if (player) {
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML;
        }
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }
    public int[][] getMiniImage(int _number) {
        String item_ = getItems().get(_number).getName();
        DataBase data_ = getDataBase();
        return data_.getMiniItem(item_);
    }
}