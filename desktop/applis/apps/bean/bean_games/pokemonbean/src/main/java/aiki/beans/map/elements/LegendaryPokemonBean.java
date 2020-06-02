package aiki.beans.map.elements;
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
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.enums.Gender;
import code.images.BaseSixtyFourUtil;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;

public class LegendaryPokemonBean extends CommonBean {
    private Pokemon pokemon;

    @Override
    public void beforeDisplaying() {
        pokemon = (Pokemon) getForms().getVal(LEG_PK);
    }
    public String getImage() {
        DataBase data_ = (DataBase) getDataBase();
        String name_ = pokemon.getName();
        return BaseSixtyFourUtil.getStringByImage(data_.getMaxiPkFront().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }
    public String getName() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        String name_ = pokemon.getName();
        return translationsPokemon_.getVal(name_);
    }
    public String clickName() {
        String name_ = pokemon.getName();
        getForms().put(PK, name_);
        return POKEMON;
    }
    public short getLevel() {
        return pokemon.getLevel();
    }
    public String getGender() {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Gender,String> translationsGenders_;
        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        Gender gender_ = pokemon.getGender();
        return translationsGenders_.getVal(gender_);
    }
    public String getAbility() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        String ability_ = pokemon.getAbility();
        return translationsAbilities_.getVal(ability_);
    }
    public String clickAbility() {
        String ability_ = pokemon.getAbility();
        getForms().put(ABILITY, ability_);
        return ABILITY;
    }
    public String getItem() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        String item_ = pokemon.getItem();
        return translationsItems_.getVal(item_);
    }
    public String clickItem() {
        DataBase data_ = (DataBase) getDataBase();
        String item_ = pokemon.getItem();
        getForms().put(ITEM, item_);
        Item it_ = data_.getItem(item_);
        if (it_ instanceof Ball) {
            return BALL;
        }
        if (it_ instanceof Berry) {
            return BERRY;
        }
        if (it_ instanceof Boost) {
            return BOOST;
        }
        if (it_ instanceof EvolvingItem) {
            return EVOLVINGITEM;
        }
        if (it_ instanceof EvolvingStone) {
            return EVOLVINGSTONE;
        }
        if (it_ instanceof Fossil) {
            return FOSSIL;
        }
        if (it_ instanceof HealingHpStatus) {
            return HEALINGHPSTATUS;
        }
        if (it_ instanceof HealingStatus) {
            return HEALINGSTATUS;
        }
        if (it_ instanceof HealingHp) {
            return HEALINGHP;
        }
        if (it_ instanceof HealingPp) {
            return HEALINGPP;
        }
        if (it_ instanceof HealingItem) {
            return HEALINGITEM;
        }
        if (it_ instanceof ItemForBattle) {
            return ITEMFORBATTLE;
        }
        if (it_ instanceof Repel) {
            return REPEL;
        }
        if (it_ instanceof SellingItem) {
            return SELLINGITEM;
        }
        return ITEM;
    }
    public String getMove(Long _moveIndex) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String move_ = getMovesAtLevel().get(_moveIndex.intValue());
        return translationsMoves_.getVal(move_);
    }
    public String clickMove(Long _moveIndex) {
        String move_ = getMovesAtLevel().get(_moveIndex.intValue());
        getForms().put(MOVE, move_);
        return MOVE;
    }
    public StringList getMovesAtLevel() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_ = data_.getPokemon(pokemon.getName()).getMovesAtLevel(pokemon.getLevel(), data_.getNbMaxMoves());
        moves_.sortElts(new ComparatorTrStrings(translationsMoves_));
        return moves_;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
}