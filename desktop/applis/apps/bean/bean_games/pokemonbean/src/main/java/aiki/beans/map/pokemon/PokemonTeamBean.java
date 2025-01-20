package aiki.beans.map.pokemon;

import aiki.beans.AbsPkTeamBean;
import aiki.map.characters.Trainer;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.characters.TrainerOneFight;
import aiki.map.pokemon.PkTrainer;
import code.util.CustList;

public class PokemonTeamBean extends AbsPkTeamBean {
    private int noFight;
    private Trainer trainer;
//    private CustList<PkTrainer> team;
    private long reward;
    private int multiplicity;

    @Override
    public void beforeDisplaying() {
//        DataBase data_ = getDataBase();
//        CustList<PkTrainer> team_;
//        team_ = new CustList<PkTrainer>();
        CustList<PkTrainer> list_;
        if (trainer instanceof TrainerOneFight) {
            list_ = ((TrainerOneFight)trainer).getTeam();
            reward = ((TrainerOneFight)trainer).getReward();
        } else {
            list_ = ((TrainerMultiFights)trainer).getTeamsRewards().get(noFight).getTeam();
            reward = ((TrainerMultiFights)trainer).getTeamsRewards().get(noFight).getReward();
        }
        multiplicity = trainer.getMultiplicityFight();
        initTeam(list_);
//        for (PkTrainer p: list_) {
//            PkTrainer pk_;
//            pk_ = new PkTrainer();
//            pk_.setName(p.getName());
//            pk_.setGender(p.getGender());
//            pk_.setAbility(p.getAbility());
//            pk_.setItem(p.getItem());
//            pk_.setLevel(p.getLevel());
//            pk_.setMoves(new StringList());
//            pk_.getMoves().addAllElts(p.getMoves());
//            pk_.getMoves().sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
//            team_.add(pk_);
//        }
//        team = team_;
    }
//    public String getName(int _index) {
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsPokemon_;
//        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        PkTrainer pk_;
//        pk_ = team.get(_index);
//        String name_ = pk_.getName();
//        return translationsPokemon_.getVal(name_);
//    }
    public String clickName(int _noFight,int _index) {
        CustList<PkTrainer> list_ = list(_noFight);
        return clickName(list_,_index);
//        PkTrainer pk_;
//        pk_ = list_.get(_index);
//        String name_ = pk_.getName();
//        getForms().put(CST_PK, name_);
//        return CST_POKEMON;
    }

    private CustList<PkTrainer> list(int _noFight) {
        CustList<PkTrainer> list_;
        if (trainer instanceof TrainerOneFight) {
            list_ = ((TrainerOneFight)trainer).getTeam();
        } else {
            list_ = ((TrainerMultiFights)trainer).getTeamsRewards().get(_noFight).getTeam();
        }
        return list_;
    }

    public int[][] getImage(int _index) {
//        DataBase data_ = getDataBase();
        CustList<PkTrainer> list_ = list(noFight);
        return getImage(list_,_index);
//        PkTrainer pk_;
//        pk_ = list_.get(_index);
//        String name_ = pk_.getName();
//        return BaseSixtyFourUtil.getStringByImage(data_.getMaxiPkFront().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }
//    public String getAbility(int _index) {
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsAbilities_;
//        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        PkTrainer pk_;
//        pk_ = team.get(_index);
//        String ability_ = pk_.getAbility();
//        return translationsAbilities_.getVal(ability_);
//    }
    public String clickAbility(int _noFight,int _index) {
        CustList<PkTrainer> list_ = list(_noFight);
        return clickAbility(list_,_index);
//        PkTrainer pk_;
//        pk_ = list_.get(_index);
//        String ability_ = pk_.getAbility();
//        getForms().put(CST_ABILITY, ability_);
//        return CST_ABILITY;
    }
//    public String getItem(int _index) {
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsItems_;
//        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
//        PkTrainer pk_;
//        pk_ = team.get(_index);
//        String item_ = pk_.getItem();
//        return translationsItems_.getVal(item_);
//    }
    public String clickItem(int _noFight,int _index) {
//        DataBase data_ = getDataBase();
        CustList<PkTrainer> list_ = list(_noFight);
        return clickItem(list_,_index);
//        PkTrainer pk_;
//        pk_ = list_.get(_index);
//        String item_ = pk_.getItem();
//        getForms().put(CST_ITEM, item_);
//        Item it_ = data_.getItem(item_);
//        return ItemsBean.switchItem(it_);
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
//    public String getMove(int _index, int _moveIndex) {
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        PkTrainer pk_;
//        pk_ = team.get(_index);
//        String move_ = pk_.getMoves().get(_moveIndex);
//        return translationsMoves_.getVal(move_);
//    }
    public String clickMove(int _noFight,int _index, int _moveIndex) {
//        DataBase data_ = getDataBase();
        CustList<PkTrainer> list_ = list(_noFight);
        return clickMove(list_,_index,_moveIndex);
//        PkTrainer pk_;
//        pk_ = list_.get(_index);
//        StringList moves_ = new StringList(pk_.getMoves());
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
//        String move_ = moves_.get(_moveIndex);
//        getForms().put(CST_MOVE, move_);
//        return CST_MOVE;
    }

    public void setTrainer(Trainer _trainer) {
        trainer = _trainer;
    }

    public long getReward() {
        return reward;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

//    public CustList<PkTrainer> getTeam() {
//        return team;
//    }

    public int getNoFight() {
        return noFight;
    }

    public void setNoFight(int _noFight) {
        noFight = _noFight;
    }
}