package aiki.beans.map.elements;

import aiki.beans.*;
import aiki.facade.*;
import aiki.map.pokemon.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.StringUtil;

public final class LegendaryPokemonBean extends CommonBean implements BeanRenderWithAppName{
    private TranslatedPkElements pokemon;
    public LegendaryPokemonBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesDataMapLevel.M_P_32_TITLE_LEG),getName()));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,this),MessagesPkBean.MAP, MessagesDataMapLevel.M_P_32_INDEX);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_MAP_MAP_HTML,this),MessagesPkBean.MAP, MessagesDataMapLevel.M_P_32_MAP);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,this),MessagesPkBean.MAP, MessagesDataMapLevel.M_P_32_LEVEL);
        disTranslatedPkElements(pokemon);
    }
    public StringMap<String> file() {
        return file(MessagesPkBean.MAP).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        pokemon = new TranslatedPkElements(getFacade(),getForms().getValPk(CST_LEG_PK));
    }
    public int[][] getImage() {
        return pokemon.getImage();
//        DataBase data_ = getDataBase();
//        String name_ = pokemon.getName();
//        return data_.getMaxiPkFront().getVal(name_).getImage();
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }
    public String getName() {
        return pokemon.getName().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsPokemon_;
//        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        String name_ = pokemon.getName();
//        return translationsPokemon_.getVal(name_);
    }
    public String clickName() {
        return tryRedirect(pokemon.getName());
    }
    public long getLevel() {
        return pokemon.getLevel();
    }
    public String getGender() {
        return pokemon.getGender().getTranslation();
//        DataBase data_ = getDataBase();
//        AbsMap<Gender,String> translationsGenders_;
//        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
//        Gender gender_ = pokemon.getGender();
//        return translationsGenders_.getVal(gender_);
    }
    public String getAbility() {
        return pokemon.getAbility().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsAbilities_;
//        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        String ability_ = pokemon.getAbility();
//        return translationsAbilities_.getVal(ability_);
    }
    public String clickAbility() {
        return tryRedirect(pokemon.getAbility());
    }
    public String getItem() {
        return pokemon.getItem().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsItems_;
//        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
//        String item_ = pokemon.getItem();
//        return translationsItems_.getVal(item_);
    }
    public String clickItem() {
        return tryRedirect(pokemon.getItem());
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
    public String getMove(int _moveIndex) {
        return getMovesAtLevel().get(_moveIndex).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String move_ = getMovesAtLevel().get(_moveIndex);
//        return translationsMoves_.getVal(move_);
    }
    public String clickMove(int _moveIndex) {
        return tryRedirect(getMovesAtLevel().get(_moveIndex));
    }
    public CustList<TranslatedKey> getMovesAtLevel() {
        return pokemon.getMoves();
//        DataBase data_ = getDataBase();
//        StringList moves_ = data_.getPokemon(pokemon.getName()).getMovesAtLevel(pokemon.getLevel(), data_.getNbMaxMoves());
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
//        return moves_;
    }

    public Pokemon getPokemon() {
        return pokemon.getTrained();
    }
}