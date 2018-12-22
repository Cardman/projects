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
import aiki.map.characters.Ally;
import aiki.map.pokemon.PkTrainer;
import code.images.BaseSixtyFourUtil;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public class AllyBean extends CommonBean {
    private Ally ally;
    private CustList<PkTrainer> team;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        CustList<PkTrainer> team_;
        team_ = new CustList<PkTrainer>();
        CustList<PkTrainer> list_ = ally.getTeam();
        for (PkTrainer p: list_) {
            PkTrainer pk_;
            pk_ = new PkTrainer();
            pk_.setName(p.getName());
            pk_.setGender(p.getGender());
            pk_.setAbility(p.getAbility());
            pk_.setItem(p.getItem());
            pk_.setLevel(p.getLevel());
            pk_.setMoves(new StringList());
            pk_.getMoves().addAllElts(p.getMoves());
            pk_.getMoves().sortElts(new ComparatorTrStrings(translationsMoves_));
            team_.add(pk_);
        }
        team = team_;
    }
    public String getImage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String name_ = pk_.getName();
        return BaseSixtyFourUtil.getStringByImage(data_.getMaxiPkFront().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }
    public String getName(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String name_ = pk_.getName();
        return translationsPokemon_.getVal(name_);
    }
    public String clickName(Long _index) {
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String name_ = pk_.getName();
        getForms().put(PK, name_);
        return POKEMON;
    }
    public String getAbility(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String ability_ = pk_.getAbility();
        return translationsAbilities_.getVal(ability_);
    }
    public String clickAbility(Long _index) {
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String ability_ = pk_.getAbility();
        getForms().put(ABILITY, ability_);
        return ABILITY;
    }
    public String getItem(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String item_ = pk_.getItem();
        return translationsItems_.getVal(item_);
    }
    public String clickItem(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String item_ = pk_.getItem();
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
    public String getMove(Long _index, Long _moveIndex) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String move_ = pk_.getMoves().get(_moveIndex.intValue());
        return translationsMoves_.getVal(move_);
    }
    public String clickMove(Long _index, Long _moveIndex) {
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String move_ = pk_.getMoves().get(_moveIndex.intValue());
        getForms().put(MOVE, move_);
        return MOVE;
    }

    public CustList<PkTrainer> getTeam() {
        return team;
    }

    public void setAlly(Ally _ally) {
        ally = _ally;
    }
}