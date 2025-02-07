package aiki.beans.map.elements;

import aiki.beans.*;
import aiki.map.levels.*;
import aiki.map.pokemon.*;
import code.util.*;

public class AreaBean extends CommonBean {
    private AbsAreaApparition area;
    private CustList<TranslatedPkElements> walk;
    private CustList<TranslatedPkElements> fish;

    @Override
    public void beforeDisplaying() {
        area = getForms().getValArea(CST_AREA);
        walk = pks(area.getWildPokemon());
        fish = pks(area.getWildPokemonFishing());
    }
    private CustList<TranslatedPkElements> pks(CustList<WildPk> _in) {
        CustList<TranslatedPkElements> out_ = new CustList<TranslatedPkElements>();
        for (WildPk w: _in) {
            out_.add(new TranslatedPkElements(getDataBase(),w,getLanguage()));
        }
        return out_;
    }
    public int[][] getImage(int _index) {
        return walk.get(_index).getImage();
//        DataBase data_ = getDataBase();
//        Pokemon pk_;
//        pk_ = area.getWildPokemon(_index);
//        String name_ = pk_.getName();
//        return data_.getMaxiPkFront().getVal(name_).getImage();
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }
    public String getName(int _index) {
        return walk.get(_index).getName().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsPokemon_;
//        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        Pokemon pk_;
//        pk_ = area.getWildPokemon(_index);
//        String name_ = pk_.getName();
//        return translationsPokemon_.getVal(name_);
    }
    public String clickName(int _index) {
        return tryRedirect(walk.get(_index).getName());
    }
    public String getGender(int _index) {
        return walk.get(_index).getGender().getTranslation();
//        DataBase data_ = getDataBase();
//        AbsMap<Gender,String> translationsGenders_;
//        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
//        Pokemon pk_;
//        pk_ = area.getWildPokemon(_index);
//        Gender gender_ = pk_.getGender();
//        return translationsGenders_.getVal(gender_);
    }
    public String getAbility(int _index) {
        return walk.get(_index).getAbility().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsAbilities_;
//        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        Pokemon pk_;
//        pk_ = area.getWildPokemon(_index);
//        String ability_ = pk_.getAbility();
//        return translationsAbilities_.getVal(ability_);
    }
    public String clickAbility(int _index) {
        return tryRedirect(walk.get(_index).getAbility());
    }
    public String getItem(int _index) {
        return walk.get(_index).getItem().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsItems_;
//        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
//        Pokemon pk_;
//        pk_ = area.getWildPokemon(_index);
//        String item_ = pk_.getItem();
//        return translationsItems_.getVal(item_);
    }
    public String clickItem(int _index) {
        return tryRedirect(walk.get(_index).getItem());
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
    public String getMove(int _index, int _moveIndex) {
        return getMovesAtLevel(_index).get(_moveIndex).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String move_ = getMovesAtLevel(_index).get(_moveIndex);
//        return translationsMoves_.getVal(move_);
    }
    public String clickMove(int _index, int _moveIndex) {
        return tryRedirect(getMovesAtLevel(_index).get(_moveIndex));
    }
    public CustList<TranslatedKey> getMovesAtLevel(int _index) {
        return walk.get(_index).getMoves();
//        DataBase data_ = getDataBase();
//        Pokemon pk_;
//        pk_ = area.getWildPokemon(_index);
//        StringList moves_ = data_.getPokemon(pk_.getName()).getMovesAtLevel(pk_.getLevel(), data_.getNbMaxMoves());
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
//        return moves_;
    }
    public int[][] getImageFishing(int _index) {
        return fish.get(_index).getImage();
//        DataBase data_ = getDataBase();
//        Pokemon pk_;
//        pk_ = area.getPokemonFishing(_index);
//        String name_ = pk_.getName();
//        return data_.getMaxiPkFront().getVal(name_).getImage();
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }
    public String getNameFishing(int _index) {
        return fish.get(_index).getName().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsPokemon_;
//        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        Pokemon pk_;
//        pk_ = area.getPokemonFishing(_index);
//        String name_ = pk_.getName();
//        return translationsPokemon_.getVal(name_);
    }
    public String clickNameFishing(int _index) {
        return tryRedirect(fish.get(_index).getName());
    }
    public String getGenderFishing(int _index) {
        return fish.get(_index).getGender().getTranslation();
//        DataBase data_ = getDataBase();
//        AbsMap<Gender,String> translationsGenders_;
//        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
//        Pokemon pk_;
//        pk_ = area.getPokemonFishing(_index);
//        Gender gender_ = pk_.getGender();
//        return translationsGenders_.getVal(gender_);
    }
    public String getAbilityFishing(int _index) {
        return fish.get(_index).getAbility().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsAbilities_;
//        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        Pokemon pk_;
//        pk_ = area.getPokemonFishing(_index);
//        String ability_ = pk_.getAbility();
//        return translationsAbilities_.getVal(ability_);
    }
    public String clickAbilityFishing(int _index) {
        return tryRedirect(fish.get(_index).getAbility());
    }
    public String getItemFishing(int _index) {
        return fish.get(_index).getItem().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsItems_;
//        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
//        Pokemon pk_;
//        pk_ = area.getPokemonFishing(_index);
//        String item_ = pk_.getItem();
//        return translationsItems_.getVal(item_);
    }
    public String clickItemFishing(int _index) {
        return tryRedirect(fish.get(_index).getItem());
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
    public String getMoveFishing(int _index, int _moveIndex) {
        return getMovesAtLevelFishing(_index).get(_moveIndex).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String move_ = getMovesAtLevelFishing(_index).get(_moveIndex);
//        return translationsMoves_.getVal(move_);
    }
    public String clickMoveFishing(int _index, int _moveIndex) {
        return tryRedirect(getMovesAtLevelFishing(_index).get(_moveIndex));
    }
    public CustList<TranslatedKey> getMovesAtLevelFishing(int _index) {
        return fish.get(_index).getMoves();
//        DataBase data_ = getDataBase();
//        Pokemon pk_;
//        pk_ = area.getPokemonFishing(_index);
//        StringList moves_ = data_.getPokemon(pk_.getName()).getMovesAtLevel(pk_.getLevel(), data_.getNbMaxMoves());
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
//        return moves_;
    }

    public AbsAreaApparition getArea() {
        return area;
    }
}