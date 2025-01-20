package aiki.beans.map.elements;

import aiki.beans.CommonBean;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.util.AbsMap;
import code.util.StringList;
import code.util.StringMap;

public class LegendaryPokemonBean extends CommonBean {
    private WildPk pokemon;

    @Override
    public void beforeDisplaying() {
        pokemon = getForms().getValPk(CST_LEG_PK);
    }
    public int[][] getImage() {
        DataBase data_ = getDataBase();
        String name_ = pokemon.getName();
        return data_.getMaxiPkFront().getVal(name_).getImage();
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }
    public String getName() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        String name_ = pokemon.getName();
        return translationsPokemon_.getVal(name_);
    }
    public String clickName() {
        String name_ = pokemon.getName();
        return tryRedirectPk(name_);
    }
    public long getLevel() {
        return pokemon.getLevel();
    }
    public String getGender() {
        DataBase data_ = getDataBase();
        AbsMap<Gender,String> translationsGenders_;
        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        Gender gender_ = pokemon.getGender();
        return translationsGenders_.getVal(gender_);
    }
    public String getAbility() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        String ability_ = pokemon.getAbility();
        return translationsAbilities_.getVal(ability_);
    }
    public String clickAbility() {
        String ability_ = pokemon.getAbility();
        return tryRedirectAb(ability_);
    }
    public String getItem() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        String item_ = pokemon.getItem();
        return translationsItems_.getVal(item_);
    }
    public String clickItem() {
        String item_ = pokemon.getItem();
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
    public String getMove(int _moveIndex) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String move_ = getMovesAtLevel().get(_moveIndex);
        return translationsMoves_.getVal(move_);
    }
    public String clickMove(int _moveIndex) {
        String move_ = getMovesAtLevel().get(_moveIndex);
        return tryRedirectMv(move_);
    }
    public StringList getMovesAtLevel() {
        DataBase data_ = getDataBase();
        StringList moves_ = data_.getPokemon(pokemon.getName()).getMovesAtLevel(pokemon.getLevel(), data_.getNbMaxMoves());
        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return moves_;
    }

    public WildPk getPokemon() {
        return pokemon;
    }
}