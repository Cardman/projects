package aiki.beans.map.pokemon;
import code.bean.Accessible;
import code.images.ConverterBufferedImage;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStrings;
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
import aiki.map.characters.Trainer;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.characters.TrainerOneFight;
import aiki.map.pokemon.PkTrainer;

public class PokemonTeamBean extends CommonBean {

    @Accessible
    private int noFight;

    @Accessible
    private Trainer trainer;

    @Accessible
    private CustList<PkTrainer> team;

    @Accessible
    private short reward;

    @Accessible
    private short multiplicity;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        CustList<PkTrainer> team_;
        team_ = new CustList<PkTrainer>();
        CustList<PkTrainer> list_;
        if (trainer instanceof TrainerOneFight) {
            list_ = ((TrainerOneFight)trainer).getTeam();
            reward = ((TrainerOneFight)trainer).getReward();
            multiplicity = ((TrainerOneFight)trainer).getMultiplicityFight();
        } else {
            list_ = ((TrainerMultiFights)trainer).getTeamsRewards().get(noFight).getTeam();
            reward = ((TrainerMultiFights)trainer).getTeamsRewards().get(noFight).getReward();
            multiplicity = ((TrainerMultiFights)trainer).getMultiplicityFight();
        }
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

    @Accessible
    private String getName(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String name_ = pk_.getName();
        return translationsPokemon_.getVal(name_);
    }

    @Accessible
    private String clickName(Long _noFight,Long _index) {
        CustList<PkTrainer> list_;
        if (trainer instanceof TrainerOneFight) {
            list_ = ((TrainerOneFight)trainer).getTeam();
        } else {
            list_ = ((TrainerMultiFights)trainer).getTeamsRewards().get(_noFight.intValue()).getTeam();
        }
        PkTrainer pk_;
        pk_ = list_.get(_index.intValue());
        String name_ = pk_.getName();
        getForms().put(PK, name_);
        return POKEMON;
    }

    @Accessible
    private String getImage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        CustList<PkTrainer> list_;
        if (trainer instanceof TrainerOneFight) {
            list_ = ((TrainerOneFight)trainer).getTeam();
        } else {
            list_ = ((TrainerMultiFights)trainer).getTeamsRewards().get(noFight).getTeam();
        }
        PkTrainer pk_;
        pk_ = list_.get(_index.intValue());
        String name_ = pk_.getName();
        return ConverterBufferedImage.surroundImage(data_.getMaxiPkFront().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }

    @Accessible
    private String getAbility(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String ability_ = pk_.getAbility();
        return translationsAbilities_.getVal(ability_);
    }

    @Accessible
    private String clickAbility(Long _noFight,Long _index) {
        CustList<PkTrainer> list_;
        if (trainer instanceof TrainerOneFight) {
            list_ = ((TrainerOneFight)trainer).getTeam();
        } else {
            list_ = ((TrainerMultiFights)trainer).getTeamsRewards().get(_noFight.intValue()).getTeam();
        }
        PkTrainer pk_;
        pk_ = list_.get(_index.intValue());
        String ability_ = pk_.getAbility();
        getForms().put(ABILITY, ability_);
        return ABILITY;
    }

    @Accessible
    private String getItem(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String item_ = pk_.getItem();
        return translationsItems_.getVal(item_);
    }

    @Accessible
    private String clickItem(Long _noFight,Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        CustList<PkTrainer> list_;
        if (trainer instanceof TrainerOneFight) {
            list_ = ((TrainerOneFight)trainer).getTeam();
        } else {
            list_ = ((TrainerMultiFights)trainer).getTeamsRewards().get(_noFight.intValue()).getTeam();
        }
        PkTrainer pk_;
        pk_ = list_.get(_index.intValue());
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

    @Accessible
    private String getMove(Long _index, Long _moveIndex) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        PkTrainer pk_;
        pk_ = team.get(_index.intValue());
        String move_ = pk_.getMoves().get(_moveIndex.intValue());
        return translationsMoves_.getVal(move_);
    }

    @Accessible
    private String clickMove(Long _noFight,Long _index, Long _moveIndex) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        CustList<PkTrainer> list_;
        if (trainer instanceof TrainerOneFight) {
            list_ = ((TrainerOneFight)trainer).getTeam();
        } else {
            list_ = ((TrainerMultiFights)trainer).getTeamsRewards().get(_noFight.intValue()).getTeam();
        }
        PkTrainer pk_;
        pk_ = list_.get(_index.intValue());
        StringList moves_ = new StringList(pk_.getMoves());
        moves_.sortElts(new ComparatorTrStrings(translationsMoves_));
        String move_ = moves_.get(_moveIndex.intValue());
        getForms().put(MOVE, move_);
        return MOVE;
    }
}
