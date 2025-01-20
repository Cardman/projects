package aiki.db;

import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.status.StatusSimple;
import aiki.fight.util.*;
import aiki.instances.*;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.*;
import code.util.*;
import org.junit.Test;

public final class DataBaseModifEntitiesTest extends DataBaseValidationCommon {
    @Test
    public void renameItem0() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newBall());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedItems().addEntry(NULL_REF, tr_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,tr_.firstKey());
    }
    @Test
    public void renameItem1() {
        Ball b_ = Instances.newBall();
        b_.setCatchingRate(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedItems().addEntry(NULL_REF, tr_);
        db_.renameItem(POKE_BALL,POKE_BALL);
        assertEq(1,db_.getItems().size());
        assertEq(POKE_BALL,db_.getItems().firstKey());
    }
    @Test
    public void renameItem2() {
        Ball b_ = Instances.newBall();
        b_.setCatchingRate(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
    }
    @Test
    public void renameItem3() {
        Boost b_ = Instances.newBoost();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,b_.getHappiness().firstKey());
    }
    @Test
    public void renameItem4() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,b_.getHappiness().firstKey());
    }
    @Test
    public void renameItem5() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        MoveData m_ = Instances.newDamagingMoveData();
        m_.getTypesByOwnedItem().addEntry(POKE_BALL,NULL_REF);
        db_.completeMembers(TREMPETTE,m_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,m_.getTypesByOwnedItem().firstKey());
    }
    @Test
    public void renameItem6() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        PokemonData m_ = Instances.newPokemonData();
        EvolutionItem evo_ = Instances.newEvolutionItem();
        evo_.setItem(POKE_BALL);
        m_.getEvolutions().addEntry(TREMPETTE, evo_);
        db_.completeMembers(TREMPETTE,m_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,evo_.getItem());
    }
    @Test
    public void renameItem7() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        PokemonData m_ = Instances.newPokemonData();
        EvolutionItem evo_ = Instances.newEvolutionItem();
        evo_.setItem(POKE_BALL_2);
        m_.getEvolutions().addEntry(TREMPETTE, evo_);
        db_.completeMembers(TREMPETTE,m_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(POKE_BALL_2,evo_.getItem());
    }
    @Test
    public void renameItem8() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        PokemonData m_ = Instances.newPokemonData();
        EvolutionStone evo_ = Instances.newEvolutionStoneSimple();
        evo_.setStone(POKE_BALL);
        m_.getEvolutions().addEntry(TREMPETTE, evo_);
        db_.completeMembers(TREMPETTE,m_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,evo_.getStone());
    }
    @Test
    public void renameItem9() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        Road r_ = Instances.newRoad();
        AreaApparition a_ = Instances.newAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setItem(POKE_BALL);
        a_.getWildPokemon().add(w_);
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        db_.getMap().getPlaces().add(r_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,w_.getItem());
    }
    @Test
    public void renameItem10() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        Road r_ = Instances.newRoad();
        MultAreaApparition a_ = new MultAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setItem(POKE_BALL);
        a_.getWildPokemonList().add(new CustList<WildPk>(w_));
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        db_.getMap().getPlaces().add(r_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,w_.getItem());
    }
    @Test
    public void renameItem11() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        Road r_ = Instances.newRoad();
        TrainerMultiFights t_ = Instances.newTrainerMultiFights();
        PokemonTeam te_ = Instances.newPokemonTeam();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.setItem(POKE_BALL);
        te_.getTeam().add(p_);
        t_.getTeamsRewards().add(te_);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,0), t_);
        db_.getMap().getPlaces().add(r_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,p_.getItem());
    }
    @Test
    public void renameItem12() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        Road r_ = Instances.newRoad();
        DealerItem d_ = Instances.newDealerItem();
        d_.setItems(new StringList(POKE_BALL));
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,1), d_);
        db_.getMap().getPlaces().add(r_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,d_.getItems().first());
    }
    @Test
    public void renameItem13() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        City r_ = Instances.newCity();
        PokemonCenter c_ = Instances.newPokemonCenter();
        c_.getIndoor().getGerants().addEntry(newPoint(0,0),Instances.newGerantPokemon());
        Seller s_ = Instances.newSeller();
        s_.setItems(new StringList(POKE_BALL));
        c_.getIndoor().getGerants().addEntry(newPoint(0,1), s_);
        r_.getBuildings().addEntry(newPoint(0,0), c_);
        db_.getMap().getPlaces().add(r_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,s_.getItems().first());
    }
    @Test
    public void renameItem14() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        City r_ = Instances.newCity();
        GymTrainer t_ = Instances.newGymTrainer();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.setItem(POKE_BALL);
        t_.getTeam().add(p_);
        Gym g_ = Instances.newGym();
        g_.getIndoor().getGymTrainers().addEntry(newPoint(0,0),t_);
        r_.getBuildings().addEntry(newPoint(0,0), g_);
        db_.getMap().getPlaces().add(r_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,p_.getItem());
    }
    @Test
    public void renameItem15() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        League r_ = Instances.newLeague();
        LevelLeague lev_ = Instances.newLevelLeague();
        r_.getRooms().add(lev_);
        TrainerLeague t_ = Instances.newTrainerLeague();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.setItem(POKE_BALL);
        t_.getTeam().add(p_);
        Gym g_ = Instances.newGym();
        lev_.setTrainer(t_);
        db_.getMap().getPlaces().add(r_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,p_.getItem());
    }
    @Test
    public void renameItem16() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        Road r_ = Instances.newRoad();
        DualFight t_ = Instances.newDualFight();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.setItem(POKE_BALL);
        t_.getFoeTrainer().getTeam().add(p_);
        r_.getLevelRoad().getDualFights().addEntry(newPoint(0,0), t_);
        db_.getMap().getPlaces().add(r_);
        db_.renameItem(POKE_BALL,PIKACHU);
        assertEq(1,db_.getItems().size());
        assertEq(PIKACHU,db_.getItems().firstKey());
        assertEq(PIKACHU,p_.getItem());
    }
    @Test
    public void deleteItem0() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newBall());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedItems().addEntry(NULL_REF, tr_);
        db_.deleteItem(POKE_BALL);
        assertEq(0,db_.getItems().size());
        assertEq(0,tr_.size());
    }
    @Test
    public void deleteItem1() {
        Ball b_ = Instances.newBall();
        Ball c_ = Instances.newBall();
        b_.setCatchingRate(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+POKE_BALL+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL_2,b_);
        db_.completeMembers(POKE_BALL,c_);
        db_.deleteItem(POKE_BALL);
        assertEq(2,db_.getItems().size());
    }
    @Test
    public void deleteItem2() {
        Boost b_ = Instances.newBoost();
        Ball c_ = Instances.newBall();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL_2,b_);
        db_.completeMembers(POKE_BALL,c_);
        db_.deleteItem(POKE_BALL);
        assertEq(2,db_.getItems().size());
    }
    @Test
    public void deleteItem3() {
        Boost b_ = Instances.newBoost();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL_2,b_);
        db_.completeMembers(POKE_BALL,b_);
        db_.deleteItem(POKE_BALL);
        assertEq(2,db_.getItems().size());
    }
    @Test
    public void deleteItem4() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL_2,b_);
        db_.completeMembers(POKE_BALL,b_);
        db_.deleteItem(POKE_BALL);
        assertEq(2,db_.getItems().size());
    }
    @Test
    public void deleteItem5() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        MoveData m_ = Instances.newDamagingMoveData();
        m_.getTypesByOwnedItem().addEntry(POKE_BALL,NULL_REF);
        db_.completeMembers(TREMPETTE,m_);
        db_.deleteItem(POKE_BALL);
        assertEq(1,db_.getItems().size());
    }
    @Test
    public void deleteItem6() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        PokemonData m_ = Instances.newPokemonData();
        EvolutionItem evo_ = Instances.newEvolutionItem();
        evo_.setItem(POKE_BALL);
        m_.getEvolutions().addEntry(TREMPETTE, evo_);
        db_.completeMembers(TREMPETTE,m_);
        db_.deleteItem(POKE_BALL);
        assertEq(1,db_.getItems().size());
    }
    @Test
    public void deleteItem7() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        PokemonData m_ = Instances.newPokemonData();
        EvolutionItem evo_ = Instances.newEvolutionItem();
        evo_.setItem(POKE_BALL_2);
        m_.getEvolutions().addEntry(TREMPETTE, evo_);
        db_.completeMembers(TREMPETTE,m_);
        db_.deleteItem(POKE_BALL);
        assertEq(0,db_.getItems().size());
    }
    @Test
    public void deleteItem8() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        PokemonData m_ = Instances.newPokemonData();
        EvolutionStone evo_ = Instances.newEvolutionStoneSimple();
        evo_.setStone(POKE_BALL);
        m_.getEvolutions().addEntry(TREMPETTE, evo_);
        db_.completeMembers(TREMPETTE,m_);
        db_.deleteItem(POKE_BALL);
        assertEq(1,db_.getItems().size());
    }
    @Test
    public void deleteItem9() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        Road r_ = Instances.newRoad();
        AreaApparition a_ = Instances.newAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setItem(POKE_BALL);
        a_.getWildPokemon().add(w_);
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        db_.getMap().getPlaces().add(r_);
        db_.deleteItem(POKE_BALL);
        assertEq(1,db_.getItems().size());
    }
    @Test
    public void deleteItem10() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        Road r_ = Instances.newRoad();
        MultAreaApparition a_ = new MultAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setItem(POKE_BALL);
        a_.getWildPokemonList().add(new CustList<WildPk>(w_));
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        db_.getMap().getPlaces().add(r_);
        db_.deleteItem(POKE_BALL);
        assertEq(1,db_.getItems().size());
    }
    @Test
    public void deleteItem11() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        Road r_ = Instances.newRoad();
        TrainerMultiFights t_ = Instances.newTrainerMultiFights();
        PokemonTeam te_ = Instances.newPokemonTeam();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.setItem(POKE_BALL);
        te_.getTeam().add(p_);
        t_.getTeamsRewards().add(te_);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,0), t_);
        db_.getMap().getPlaces().add(r_);
        db_.deleteItem(POKE_BALL);
        assertEq(1,db_.getItems().size());
    }
    @Test
    public void deleteItem12() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        Road r_ = Instances.newRoad();
        DealerItem d_ = Instances.newDealerItem();
        d_.setItems(new StringList(POKE_BALL));
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,1), d_);
        db_.getMap().getPlaces().add(r_);
        db_.deleteItem(POKE_BALL);
        assertEq(1,db_.getItems().size());
    }
    @Test
    public void deleteItem13() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        City r_ = Instances.newCity();
        PokemonCenter c_ = Instances.newPokemonCenter();
        c_.getIndoor().getGerants().addEntry(newPoint(0,0),Instances.newGerantPokemon());
        Seller s_ = Instances.newSeller();
        s_.setItems(new StringList(POKE_BALL));
        c_.getIndoor().getGerants().addEntry(newPoint(0,1), s_);
        r_.getBuildings().addEntry(newPoint(0,0), c_);
        db_.getMap().getPlaces().add(r_);
        db_.deleteItem(POKE_BALL);
        assertEq(1,db_.getItems().size());
    }
    @Test
    public void deleteItem14() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        City r_ = Instances.newCity();
        GymTrainer t_ = Instances.newGymTrainer();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.setItem(POKE_BALL);
        t_.getTeam().add(p_);
        Gym g_ = Instances.newGym();
        g_.getIndoor().getGymTrainers().addEntry(newPoint(0,0),t_);
        r_.getBuildings().addEntry(newPoint(0,0), g_);
        db_.getMap().getPlaces().add(r_);
        db_.deleteItem(POKE_BALL);
        assertEq(1,db_.getItems().size());
    }
    @Test
    public void deleteItem15() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        League r_ = Instances.newLeague();
        LevelLeague lev_ = Instances.newLevelLeague();
        r_.getRooms().add(lev_);
        TrainerLeague t_ = Instances.newTrainerLeague();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.setItem(POKE_BALL);
        t_.getTeam().add(p_);
        Gym g_ = Instances.newGym();
        lev_.setTrainer(t_);
        db_.getMap().getPlaces().add(r_);
        db_.deleteItem(POKE_BALL);
        assertEq(1,db_.getItems().size());
    }
    @Test
    public void deleteItem16() {
        HealingPp b_ = Instances.newHealingPp();
        b_.getHappiness().addEntry(POKE_BALL,1L);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        Road r_ = Instances.newRoad();
        DualFight t_ = Instances.newDualFight();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.setItem(POKE_BALL);
        t_.getFoeTrainer().getTeam().add(p_);
        r_.getLevelRoad().getDualFights().addEntry(newPoint(0,0), t_);
        db_.getMap().getPlaces().add(r_);
        db_.deleteItem(POKE_BALL);
        assertEq(1,db_.getItems().size());
    }
    @Test
    public void renamePokemon0() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newPokemonData());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedPokemon().addEntry(NULL_REF, tr_);
        db_.renamePokemon(POKE_BALL,PIKACHU);
        assertEq(1,db_.getPokedex().size());
        assertEq(PIKACHU,db_.getPokedex().firstKey());
        assertEq(PIKACHU,tr_.firstKey());
    }
    @Test
    public void renamePokemon1() {
        PokemonData b_ = Instances.newPokemonData();
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedPokemon().addEntry(NULL_REF, tr_);
        db_.renamePokemon(POKE_BALL,POKE_BALL);
        assertEq(1,db_.getPokedex().size());
        assertEq(POKE_BALL,db_.getPokedex().firstKey());
    }
    @Test
    public void renamePokemon2() {
        PokemonData b_ = Instances.newPokemonData();
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        Fossil f_ = Instances.newFossil();
        f_.setPokemon(POKE_BALL);
        db_.completeMembers(TREMPETTE, f_);
        db_.completeMembers(POKE_BALL_2,Instances.newBall());
        db_.renamePokemon(POKE_BALL,PIKACHU);
        assertEq(1,db_.getPokedex().size());
        assertEq(PIKACHU,db_.getPokedex().firstKey());
        assertEq(PIKACHU,f_.getPokemon());
    }
    @Test
    public void renamePokemon3() {
        PokemonData b_ = Instances.newPokemonData();
        b_.setBaseEvo(POKE_BALL);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.renamePokemon(POKE_BALL,PIKACHU);
        assertEq(1,db_.getPokedex().size());
        assertEq(PIKACHU,db_.getPokedex().firstKey());
        assertEq(PIKACHU,b_.getBaseEvo());
    }
    @Test
    public void renamePokemon4() {
        PokemonData b_ = Instances.newPokemonData();
        b_.getEvolutions().addEntry(POKE_BALL,Instances.newEvolutionLevelSimple());
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.renamePokemon(POKE_BALL,PIKACHU);
        assertEq(1,db_.getPokedex().size());
        assertEq(PIKACHU,db_.getPokedex().firstKey());
        assertEq(PIKACHU,b_.getEvolutions().firstKey());
    }
    @Test
    public void renamePokemon5() {
        DataBase db_ = newData();
        PokemonData m_ = Instances.newPokemonData();
        EvolutionStone evo_ = Instances.newEvolutionStoneSimple();
        evo_.setStone(POKE_BALL);
        m_.getEvolutions().addEntry(TREMPETTE, evo_);
        db_.completeMembers(POKE_BALL,m_);
        Road r_ = Instances.newRoad();
        AreaApparition a_ = Instances.newAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setName(POKE_BALL);
        a_.getWildPokemon().add(w_);
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        db_.getMap().getPlaces().add(r_);
        db_.renamePokemon(POKE_BALL,PIKACHU);
        assertEq(1,db_.getPokedex().size());
        assertEq(PIKACHU,db_.getPokedex().firstKey());
        assertEq(PIKACHU,w_.getName());
    }
    @Test
    public void renamePokemon6() {
        DataBase db_ = newData();
        PokemonData m_ = Instances.newPokemonData();
        EvolutionStone evo_ = Instances.newEvolutionStoneSimple();
        evo_.setStone(POKE_BALL);
        m_.getEvolutions().addEntry(TREMPETTE, evo_);
        db_.completeMembers(POKE_BALL,m_);
        Road r_ = Instances.newRoad();
        MultAreaApparition a_ = new MultAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setName(POKE_BALL);
        a_.getWildPokemonList().add(new CustList<WildPk>(w_));
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        db_.getMap().getPlaces().add(r_);
        db_.renamePokemon(POKE_BALL,PIKACHU);
        assertEq(1,db_.getPokedex().size());
        assertEq(PIKACHU,db_.getPokedex().firstKey());
        assertEq(PIKACHU,w_.getName());
    }
    @Test
    public void renamePokemon7() {
        DataBase db_ = newData();
        PokemonData m_ = Instances.newPokemonData();
        EvolutionStone evo_ = Instances.newEvolutionStoneSimple();
        evo_.setStone(POKE_BALL);
        m_.getEvolutions().addEntry(TREMPETTE, evo_);
        db_.completeMembers(POKE_BALL,m_);
        Road r_ = Instances.newRoad();
        TrainerMultiFights t_ = Instances.newTrainerMultiFights();
        PokemonTeam te_ = Instances.newPokemonTeam();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.setName(POKE_BALL);
        te_.getTeam().add(p_);
        t_.getTeamsRewards().add(te_);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,0), t_);
        db_.getMap().getPlaces().add(r_);
        db_.renamePokemon(POKE_BALL,PIKACHU);
        assertEq(1,db_.getPokedex().size());
        assertEq(PIKACHU,db_.getPokedex().firstKey());
        assertEq(PIKACHU,p_.getName());
    }
    @Test
    public void renamePokemon8() {
        PokemonData b_ = Instances.newPokemonData();
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        ItemForBattle f_ = Instances.newItemForBattle();
        f_.getMultStatPokemonRank().addEntry(new StatisticPokemon(Statistic.SPEED, POKE_BALL), 1L);
        db_.completeMembers(TREMPETTE, f_);
        db_.completeMembers(POKE_BALL_2,Instances.newBall());
        db_.renamePokemon(POKE_BALL,PIKACHU);
        assertEq(1,db_.getPokedex().size());
        assertEq(PIKACHU,db_.getPokedex().firstKey());
        assertEq(PIKACHU,f_.getMultStatPokemonRank().getKey(0).getPokemon());
    }
    @Test
    public void deletePokemon0() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newPokemonData());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedPokemon().addEntry(NULL_REF, tr_);
        db_.deletePokemon(POKE_BALL);
        assertEq(0,db_.getPokedex().size());
        assertEq(0,tr_.size());
    }
    @Test
    public void deletePokemon1() {
        Ball b_ = Instances.newBall();
        b_.setCatchingRate(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+POKE_BALL+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL_2,b_);
        db_.completeMembers(POKE_BALL,Instances.newPokemonData());
        db_.deletePokemon(POKE_BALL);
        assertEq(1,db_.getPokedex().size());
    }
    @Test
    public void deletePokemon2() {
        PokemonData b_ = Instances.newPokemonData();
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        Fossil f_ = Instances.newFossil();
        f_.setPokemon(POKE_BALL);
        db_.completeMembers(TREMPETTE, f_);
        db_.completeMembers(POKE_BALL_2,Instances.newBall());
        db_.deletePokemon(POKE_BALL);
        assertEq(1,db_.getPokedex().size());
    }
    @Test
    public void deletePokemon3() {
        DataBase db_ = newData();
        PokemonData b_ = Instances.newPokemonData();
        b_.setBaseEvo(POKE_BALL);
        db_.completeMembers(POKE_BALL,b_);
        db_.deletePokemon(POKE_BALL);
        assertEq(0,db_.getPokedex().size());
    }
    @Test
    public void deletePokemon4() {
        DataBase db_ = newData();
        PokemonData b_ = Instances.newPokemonData();
        b_.getEvolutions().addEntry(POKE_BALL,Instances.newEvolutionLevelSimple());
        db_.completeMembers(POKE_BALL,b_);
        db_.deletePokemon(POKE_BALL);
        assertEq(0,db_.getPokedex().size());
    }
    @Test
    public void deletePokemon5() {
        DataBase db_ = newData();
        PokemonData m_ = Instances.newPokemonData();
        EvolutionStone evo_ = Instances.newEvolutionStoneSimple();
        evo_.setStone(POKE_BALL);
        m_.getEvolutions().addEntry(TREMPETTE, evo_);
        db_.completeMembers(POKE_BALL,m_);
        Road r_ = Instances.newRoad();
        AreaApparition a_ = Instances.newAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setName(POKE_BALL);
        a_.getWildPokemon().add(w_);
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        db_.getMap().getPlaces().add(r_);
        db_.deletePokemon(POKE_BALL);
        assertEq(1,db_.getPokedex().size());
    }
    @Test
    public void deletePokemon6() {
        DataBase db_ = newData();
        PokemonData m_ = Instances.newPokemonData();
        EvolutionStone evo_ = Instances.newEvolutionStoneSimple();
        evo_.setStone(POKE_BALL);
        m_.getEvolutions().addEntry(TREMPETTE, evo_);
        db_.completeMembers(POKE_BALL,m_);
        Road r_ = Instances.newRoad();
        MultAreaApparition a_ = new MultAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setName(POKE_BALL);
        a_.getWildPokemonList().add(new CustList<WildPk>(w_));
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        db_.getMap().getPlaces().add(r_);
        db_.deletePokemon(POKE_BALL);
        assertEq(1,db_.getPokedex().size());
    }
    @Test
    public void deletePokemon7() {
        DataBase db_ = newData();
        PokemonData m_ = Instances.newPokemonData();
        EvolutionStone evo_ = Instances.newEvolutionStoneSimple();
        evo_.setStone(POKE_BALL);
        m_.getEvolutions().addEntry(TREMPETTE, evo_);
        db_.completeMembers(POKE_BALL,m_);
        Road r_ = Instances.newRoad();
        TrainerMultiFights t_ = Instances.newTrainerMultiFights();
        PokemonTeam te_ = Instances.newPokemonTeam();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.setName(POKE_BALL);
        te_.getTeam().add(p_);
        t_.getTeamsRewards().add(te_);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,0), t_);
        db_.getMap().getPlaces().add(r_);
        db_.deletePokemon(POKE_BALL);
        assertEq(1,db_.getPokedex().size());
    }
    @Test
    public void deletePokemon8() {
        DataBase db_ = newData();
        PokemonData b_ = Instances.newPokemonData();
        b_.setBaseEvo(POKE_BALL);
        db_.completeMembers(POKE_BALL,b_);
        PokemonData d_ = Instances.newPokemonData();
        d_.setBaseEvo(POKE_BALL);
        db_.completeMembers(POKE_BALL_2,d_);
        db_.deletePokemon(POKE_BALL);
        assertEq(2,db_.getPokedex().size());
    }
    @Test
    public void deletePokemon9() {
        PokemonData b_ = Instances.newPokemonData();
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        ItemForBattle f_ = Instances.newItemForBattle();
        f_.getMultStatPokemonRank().addEntry(new StatisticPokemon(Statistic.SPEED, POKE_BALL), 1L);
        db_.completeMembers(TREMPETTE, f_);
        db_.completeMembers(POKE_BALL_2,Instances.newBall());
        db_.deletePokemon(POKE_BALL);
        assertEq(1,db_.getPokedex().size());
        assertEq(POKE_BALL,db_.getPokedex().firstKey());
    }
    @Test
    public void renameMove0() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedMoves().addEntry(NULL_REF, tr_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,tr_.firstKey());
    }
    @Test
    public void renameMove1() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedMoves().addEntry(NULL_REF, tr_);
        db_.renameMove(POKE_BALL,POKE_BALL);
        assertEq(1,db_.getMoves().size());
        assertEq(POKE_BALL,db_.getMoves().firstKey());
    }
    @Test
    public void renameMove2() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        PokemonData d_ = Instances.newPokemonData();
        EvolutionMove e_ = Instances.newEvolutionMove();
        e_.setMove(POKE_BALL);
        d_.getEvolutions().addEntry(POKE_BALL_2, e_);
        d_.getEvolutions().addEntry(TREMPETTE, Instances.newEvolutionTeam());
        db_.completeMembers(TREMPETTE2, d_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,e_.getMove());
    }
    @Test
    public void renameMove3() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        PokemonData d_ = Instances.newPokemonData();
        d_.getLevMoves().add(new LevelMove(1,POKE_BALL));
        db_.completeMembers(TREMPETTE2, d_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,d_.getLevMoves().first().getMove());
    }
    @Test
    public void renameMove4() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        AbilityData d_ = Instances.newAbilityData();
        d_.getImmuMove().add(POKE_BALL);
        db_.completeMembers(TREMPETTE2, d_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,d_.getImmuMove().first());
    }
    @Test
    public void renameMove5() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        AbilityData d_ = Instances.newAbilityData();
        d_.getHealHpByTypeIfWeather().addEntry(new WeatherType(POKE_BALL,NULL_REF), Rate.one());
        db_.completeMembers(TREMPETTE2, d_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,d_.getHealHpByTypeIfWeather().firstKey().getWeather());
    }
    @Test
    public void renameMove6() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        AbilityData d_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        e_.setEnabledWeather(POKE_BALL);
        d_.getEffectSending().add(e_);
        db_.completeMembers(TREMPETTE2, d_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,e_.getEnabledWeather());
    }
    @Test
    public void renameMove7() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        ItemForBattle d_ = Instances.newItemForBattle();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        e_.setEnabledWeather(POKE_BALL);
        d_.getEffectSending().add(e_);
        db_.completeMembers(TREMPETTE2, d_);
        db_.completeMembers(TREMPETTE, Instances.newBall());
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,e_.getEnabledWeather());
    }
    @Test
    public void renameMove8() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        EffectCombo ec_ = Instances.newEffectCombo();
        EffectTeam eff_ = Instances.newEffectTeam();
        eff_.getUnusableMoves().add(POKE_BALL);
        ec_.getTeamMove().add(eff_);
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(TREMPETTE), ec_));
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,eff_.getUnusableMoves().first());
        assertEq(TREMPETTE,db_.getCombos().getEffects().first().getList().first());
    }
    @Test
    public void renameMove9() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        EffectCombo ec_ = Instances.newEffectCombo();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL), ec_));
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,db_.getCombos().getEffects().first().getList().first());
    }
    @Test
    public void renameMove10() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectUnprotectFromTypes eff_ = Instances.newEffectUnprotectFromTypes();
        eff_.getDisableImmuFromMoves().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL, move_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,eff_.getDisableImmuFromMoves().first());
    }
    @Test
    public void renameMove11() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectCopyMove eff_ = Instances.newEffectCopyMove();
        eff_.getMovesNotToBeCopied().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL, move_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,eff_.getMovesNotToBeCopied().first());
    }
    @Test
    public void renameMove12() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectTeam eff_ = Instances.newEffectTeam();
        eff_.getUnusableMoves().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL, move_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,eff_.getUnusableMoves().first());
    }
    @Test
    public void renameMove13() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectGlobal eff_ = Instances.newEffectGlobal();
        eff_.getUnusableMoves().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL, move_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,eff_.getUnusableMoves().first());
    }
    @Test
    public void renameMove14() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectInvoke eff_ = Instances.newEffectInvoke();
        eff_.getMovesNotToBeInvoked().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL, move_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,eff_.getMovesNotToBeInvoked().first());
    }
    @Test
    public void renameMove15() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        Road r_ = Instances.newRoad();
        TrainerMultiFights t_ = Instances.newTrainerMultiFights();
        PokemonTeam te_ = Instances.newPokemonTeam();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.getMoves().add(POKE_BALL);
        te_.getTeam().add(p_);
        t_.getTeamsRewards().add(te_);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,0), t_);
        db_.getMap().getPlaces().add(r_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,p_.getMoves().first());
    }
    @Test
    public void renameMove16() {
        DataBase db_ = newData();
        DamagingMoveData mv_ = Instances.newDamagingMoveData();
        mv_.setAccuracy(VAR_PREFIX+db_.fighterPp()+SE+POKE_BALL);
        db_.completeMembers(POKE_BALL, mv_);
        db_.completeVariables();
        Road r_ = Instances.newRoad();
        TrainerMultiFights t_ = Instances.newTrainerMultiFights();
        PokemonTeam te_ = Instances.newPokemonTeam();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.getMoves().add(POKE_BALL);
        te_.getTeam().add(p_);
        t_.getTeamsRewards().add(te_);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,0), t_);
        db_.getMap().getPlaces().add(r_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,p_.getMoves().first());
    }
    @Test
    public void renameMove17() {
        DataBase db_ = newData();
        DamagingMoveData mv_ = Instances.newDamagingMoveData();
        mv_.setAccuracy(VAR_PREFIX+db_.immuTypeAttCible()+SE+POKE_BALL);
        db_.completeMembers(POKE_BALL, mv_);
        db_.completeVariables();
        Road r_ = Instances.newRoad();
        TrainerMultiFights t_ = Instances.newTrainerMultiFights();
        PokemonTeam te_ = Instances.newPokemonTeam();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.getMoves().add(POKE_BALL);
        te_.getTeam().add(p_);
        t_.getTeamsRewards().add(te_);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,0), t_);
        db_.getMap().getPlaces().add(r_);
        db_.renameMove(POKE_BALL,PIKACHU);
        assertEq(1,db_.getMoves().size());
        assertEq(PIKACHU,db_.getMoves().firstKey());
        assertEq(PIKACHU,p_.getMoves().first());
    }
//    @Test
//    public void editMove() {
//        DataBase db_ = newData();
//        DamagingMoveData da_ = Instances.newDamagingMoveData();
//        da_.setCategory(TREMPETTE);
//        db_.completeMembers(POKE_BALL, da_);
//        da_.setCategory(PIKACHU);
//        assertEq(1,db_.getAllCategories().size());
//        assertEq(1,db_.getCategories().size());
//        assertEq(TREMPETTE,db_.getAllCategories().first());
//        assertEq(TREMPETTE,db_.getCategories().first());
//        db_.editMove(POKE_BALL,db_.getMove(POKE_BALL));
//        assertEq(1,db_.getAllCategories().size());
//        assertEq(1,db_.getCategories().size());
//        assertEq(PIKACHU,db_.getAllCategories().first());
//        assertEq(PIKACHU,db_.getCategories().first());
//    }
    @Test
    public void deleteMove0() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedMoves().addEntry(NULL_REF, tr_);
        db_.deleteMove(POKE_BALL);
        assertEq(0,db_.getMoves().size());
        assertEq(0,tr_.size());
    }
    @Test
    public void deleteMove1() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        db_.deleteMove(POKE_BALL);
        assertEq(0,db_.getMoves().size());
    }
    @Test
    public void deleteMove2() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        PokemonData d_ = Instances.newPokemonData();
        EvolutionMove e_ = Instances.newEvolutionMove();
        e_.setMove(POKE_BALL);
        d_.getEvolutions().addEntry(POKE_BALL_2, e_);
        d_.getEvolutions().addEntry(TREMPETTE, Instances.newEvolutionTeam());
        db_.completeMembers(TREMPETTE2, d_);
        db_.deleteMove(POKE_BALL);
        assertEq(1,db_.getMoves().size());
    }
    @Test
    public void deleteMove3() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        PokemonData d_ = Instances.newPokemonData();
        d_.getLevMoves().add(new LevelMove(1,POKE_BALL));
        db_.completeMembers(TREMPETTE2, d_);
        db_.deleteMove(POKE_BALL);
        assertEq(1,db_.getMoves().size());
    }
    @Test
    public void deleteMove4() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        AbilityData d_ = Instances.newAbilityData();
        d_.getImmuMove().add(POKE_BALL);
        db_.completeMembers(TREMPETTE2, d_);
        db_.deleteMove(POKE_BALL);
        assertEq(1,db_.getMoves().size());
    }
    @Test
    public void deleteMove5() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        AbilityData d_ = Instances.newAbilityData();
        d_.getHealHpByTypeIfWeather().addEntry(new WeatherType(POKE_BALL,NULL_REF), Rate.one());
        db_.completeMembers(TREMPETTE2, d_);
        db_.deleteMove(POKE_BALL);
        assertEq(1,db_.getMoves().size());
    }
    @Test
    public void deleteMove6() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        AbilityData d_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        e_.setEnabledWeather(POKE_BALL);
        d_.getEffectSending().add(e_);
        db_.completeMembers(TREMPETTE2, d_);
        db_.deleteMove(POKE_BALL);
        assertEq(1,db_.getMoves().size());
    }
    @Test
    public void deleteMove7() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        ItemForBattle d_ = Instances.newItemForBattle();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        e_.setEnabledWeather(POKE_BALL);
        d_.getEffectSending().add(e_);
        db_.completeMembers(TREMPETTE2, d_);
        db_.completeMembers(TREMPETTE, Instances.newBall());
        db_.deleteMove(POKE_BALL);
        assertEq(1,db_.getMoves().size());
    }
    @Test
    public void deleteMove8() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        EffectCombo ec_ = Instances.newEffectCombo();
        EffectTeam eff_ = Instances.newEffectTeam();
        eff_.getUnusableMoves().add(POKE_BALL);
        ec_.getTeamMove().add(eff_);
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(TREMPETTE), ec_));
        db_.deleteMove(POKE_BALL);
        assertEq(1,db_.getMoves().size());
    }
    @Test
    public void deleteMove9() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        EffectCombo ec_ = Instances.newEffectCombo();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL), ec_));
        db_.deleteMove(POKE_BALL);
        assertEq(1,db_.getMoves().size());
    }
    @Test
    public void deleteMove10() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectUnprotectFromTypes eff_ = Instances.newEffectUnprotectFromTypes();
        eff_.getDisableImmuFromMoves().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL, move_);
        db_.deleteMove(POKE_BALL);
        assertEq(2,db_.getMoves().size());
    }
    @Test
    public void deleteMove11() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectCopyMove eff_ = Instances.newEffectCopyMove();
        eff_.getMovesNotToBeCopied().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL, move_);
        db_.deleteMove(POKE_BALL);
        assertEq(2,db_.getMoves().size());
    }
    @Test
    public void deleteMove12() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectTeam eff_ = Instances.newEffectTeam();
        eff_.getUnusableMoves().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL, move_);
        db_.deleteMove(POKE_BALL);
        assertEq(2,db_.getMoves().size());
    }
    @Test
    public void deleteMove13() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectGlobal eff_ = Instances.newEffectGlobal();
        eff_.getUnusableMoves().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL, move_);
        db_.deleteMove(POKE_BALL);
        assertEq(2,db_.getMoves().size());
    }
    @Test
    public void deleteMove14() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectInvoke eff_ = Instances.newEffectInvoke();
        eff_.getMovesNotToBeInvoked().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL, move_);
        db_.deleteMove(POKE_BALL);
        assertEq(2,db_.getMoves().size());
    }
    @Test
    public void deleteMove15() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        Road r_ = Instances.newRoad();
        TrainerMultiFights t_ = Instances.newTrainerMultiFights();
        PokemonTeam te_ = Instances.newPokemonTeam();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.getMoves().add(POKE_BALL);
        te_.getTeam().add(p_);
        t_.getTeamsRewards().add(te_);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,0), t_);
        db_.getMap().getPlaces().add(r_);
        db_.deleteMove(POKE_BALL);
        assertEq(1,db_.getMoves().size());
    }
    @Test
    public void deleteMove16() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectInvoke eff_ = Instances.newEffectInvoke();
        eff_.getMovesNotToBeInvoked().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL, move_);
        db_.deleteMove(POKE_BALL);
        assertEq(2,db_.getMoves().size());
    }
    @Test
    public void deleteMove17() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectSwitchAbilities eff_ = Instances.newEffectSwitchAbilities();
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+POKE_BALL+RB+RP);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newDamagingMoveData());
        db_.deleteMove(POKE_BALL);
        assertEq(2,db_.getMoves().size());
    }
    @Test
    public void deleteMove18() {
        DataBase db_ = newData();
        DamagingMoveData mv_ = Instances.newDamagingMoveData();
        mv_.setAccuracy(VAR_PREFIX+db_.fighterPp()+SE+POKE_BALL);
        db_.completeMembers(POKE_BALL, mv_);
        db_.completeVariables();
        db_.deleteMove(POKE_BALL);
        assertEq(0,db_.getMoves().size());
    }
    @Test
    public void deleteMove19() {
        DataBase db_ = newData();
        DamagingMoveData mv_ = Instances.newDamagingMoveData();
        mv_.setAccuracy(VAR_PREFIX+db_.immuTypeAttCible()+SE+POKE_BALL);
        db_.completeMembers(POKE_BALL, mv_);
        db_.completeVariables();
        db_.deleteMove(POKE_BALL);
        assertEq(0,db_.getMoves().size());
    }
    @Test
    public void renameAbility0() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedAbilities().addEntry(NULL_REF, tr_);
        db_.renameAbility(POKE_BALL,PIKACHU);
        assertEq(1,db_.getAbilities().size());
        assertEq(PIKACHU,db_.getAbilities().firstKey());
        assertEq(PIKACHU,tr_.firstKey());
    }
    @Test
    public void renameAbility1() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedAbilities().addEntry(NULL_REF, tr_);
        db_.renameAbility(POKE_BALL,POKE_BALL);
        assertEq(1,db_.getAbilities().size());
        assertEq(POKE_BALL,db_.getAbilities().firstKey());
    }
    @Test
    public void renameAbility2() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectSwitchAbilities eff_ = Instances.newEffectSwitchAbilities();
        eff_.setConstAbility(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        db_.renameAbility(POKE_BALL,PIKACHU);
        assertEq(1,db_.getAbilities().size());
        assertEq(PIKACHU,db_.getAbilities().firstKey());
        assertEq(PIKACHU,eff_.getConstAbility());
    }
    @Test
    public void renameAbility3() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectGlobal eff_ = Instances.newEffectGlobal();
        eff_.getCancelProtectingAbilities().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        db_.renameAbility(POKE_BALL,PIKACHU);
        assertEq(1,db_.getAbilities().size());
        assertEq(PIKACHU,db_.getAbilities().firstKey());
        assertEq(PIKACHU,eff_.getCancelProtectingAbilities().first());
    }
    @Test
    public void renameAbility4() {
        DataBase db_ = newData();
        PokemonData move_ = Instances.newPokemonData();
        move_.getAbilities().add(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        db_.renameAbility(POKE_BALL,PIKACHU);
        assertEq(1,db_.getAbilities().size());
        assertEq(PIKACHU,db_.getAbilities().firstKey());
        assertEq(PIKACHU,move_.getAbilities().first());
    }
    @Test
    public void renameAbility5() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        Road r_ = Instances.newRoad();
        AreaApparition a_ = Instances.newAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setAbility(POKE_BALL);
        a_.getWildPokemon().add(w_);
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        db_.getMap().getPlaces().add(r_);
        db_.renameAbility(POKE_BALL,PIKACHU);
        assertEq(1,db_.getAbilities().size());
        assertEq(PIKACHU,db_.getAbilities().firstKey());
        assertEq(PIKACHU,w_.getAbility());
    }
    @Test
    public void renameAbility6() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        Road r_ = Instances.newRoad();
        MultAreaApparition a_ = new MultAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setAbility(POKE_BALL);
        a_.getWildPokemonList().add(new CustList<WildPk>(w_));
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        db_.getMap().getPlaces().add(r_);
        db_.renameAbility(POKE_BALL,PIKACHU);
        assertEq(1,db_.getAbilities().size());
        assertEq(PIKACHU,db_.getAbilities().firstKey());
        assertEq(PIKACHU,w_.getAbility());
    }
    @Test
    public void renameAbility7() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        Road r_ = Instances.newRoad();
        TrainerMultiFights t_ = Instances.newTrainerMultiFights();
        PokemonTeam te_ = Instances.newPokemonTeam();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.setAbility(POKE_BALL);
        te_.getTeam().add(p_);
        t_.getTeamsRewards().add(te_);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,0), t_);
        db_.getMap().getPlaces().add(r_);
        db_.renameAbility(POKE_BALL,PIKACHU);
        assertEq(1,db_.getAbilities().size());
        assertEq(PIKACHU,db_.getAbilities().firstKey());
        assertEq(PIKACHU,p_.getAbility());
    }
    @Test
    public void deleteAbility0() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedAbilities().addEntry(NULL_REF, tr_);
        db_.deleteAbility(POKE_BALL);
        assertEq(0,db_.getAbilities().size());
        assertEq(0,tr_.size());
    }
    @Test
    public void deleteAbility1() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        db_.deleteAbility(POKE_BALL);
        assertEq(0,db_.getAbilities().size());
    }
    @Test
    public void deleteAbility2() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectSwitchAbilities eff_ = Instances.newEffectSwitchAbilities();
        eff_.setConstAbility(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        db_.deleteAbility(POKE_BALL);
        assertEq(1,db_.getAbilities().size());
    }
    @Test
    public void deleteAbility3() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectGlobal eff_ = Instances.newEffectGlobal();
        eff_.getCancelProtectingAbilities().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        db_.deleteAbility(POKE_BALL);
        assertEq(1,db_.getAbilities().size());
    }
    @Test
    public void deleteAbility4() {
        DataBase db_ = newData();
        PokemonData move_ = Instances.newPokemonData();
        move_.getAbilities().add(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        db_.deleteAbility(POKE_BALL);
        assertEq(1,db_.getAbilities().size());
    }
    @Test
    public void deleteAbility5() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        Road r_ = Instances.newRoad();
        AreaApparition a_ = Instances.newAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setAbility(POKE_BALL);
        a_.getWildPokemon().add(w_);
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        db_.getMap().getPlaces().add(r_);
        db_.deleteAbility(POKE_BALL);
        assertEq(1,db_.getAbilities().size());
    }
    @Test
    public void deleteAbility6() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        Road r_ = Instances.newRoad();
        MultAreaApparition a_ = new MultAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setAbility(POKE_BALL);
        a_.getWildPokemonList().add(new CustList<WildPk>(w_));
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        db_.getMap().getPlaces().add(r_);
        db_.deleteAbility(POKE_BALL);
        assertEq(1,db_.getAbilities().size());
    }
    @Test
    public void deleteAbility7() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        Road r_ = Instances.newRoad();
        TrainerMultiFights t_ = Instances.newTrainerMultiFights();
        PokemonTeam te_ = Instances.newPokemonTeam();
        PkTrainer p_ = Instances.newPkTrainer();
        p_.setAbility(POKE_BALL);
        te_.getTeam().add(p_);
        t_.getTeamsRewards().add(te_);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,0), t_);
        db_.getMap().getPlaces().add(r_);
        db_.deleteAbility(POKE_BALL);
        assertEq(1,db_.getAbilities().size());
    }
    @Test
    public void deleteAbility8() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.getImmuAbility().add(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        db_.deleteAbility(POKE_BALL);
        assertEq(2,db_.getAbilities().size());
    }
    @Test
    public void deleteAbility9() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectSwitchAbilities eff_ = Instances.newEffectSwitchAbilities();
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+POKE_BALL+RB+RP);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newAbilityData());
        db_.deleteAbility(POKE_BALL);
        assertEq(1,db_.getAbilities().size());
    }
    @Test
    public void renameStatus0() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedStatus().addEntry(NULL_REF, tr_);
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,tr_.firstKey());
    }
    @Test
    public void renameStatus1() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedStatus().addEntry(NULL_REF, tr_);
        db_.renameStatus(POKE_BALL,POKE_BALL);
        assertEq(1,db_.getStatus().size());
        assertEq(POKE_BALL,db_.getStatus().firstKey());
    }
    @Test
    public void renameStatus2() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectEndRoundIndividual eff_ = Instances.newEffectEndRoundIndividual();
        eff_.setUserStatusEndRound(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,eff_.getUserStatusEndRound());
    }
    @Test
    public void renameStatus3() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectEndRoundMultiRelation eff_ = Instances.newEffectEndRoundMultiRelation();
        eff_.getDamageByStatus().addEntry(POKE_BALL,Rate.one());
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,eff_.getDamageByStatus().firstKey());
    }
    @Test
    public void renameStatus4() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectTeam eff_ = Instances.newEffectTeam();
        eff_.getProtectAgainstStatus().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,eff_.getProtectAgainstStatus().first());
    }
    @Test
    public void renameStatus5() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectTeamWhileSendFoe eff_ = Instances.newEffectTeamWhileSendFoe();
        eff_.getStatusByNbUses().addEntry(1L,POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,eff_.getStatusByNbUses().firstValue());
    }
    @Test
    public void renameStatus6() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectGlobal eff_ = Instances.newEffectGlobal();
        eff_.getPreventStatus().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,eff_.getPreventStatus().first());
    }
    @Test
    public void renameStatus7() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectStatus eff_ = Instances.newEffectStatus();
        eff_.getLawStatus().getLaw().addEntry(POKE_BALL, LgInt.one());
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,eff_.getLawStatus().getLaw().firstKey());
    }
    @Test
    public void renameStatus8() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectStatus eff_ = Instances.newEffectStatus();
        eff_.getLawStatus().getLaw().addEntry(POKE_BALL_2, LgInt.one());
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(POKE_BALL_2,eff_.getLawStatus().getLaw().firstKey());
    }
    @Test
    public void renameStatus9() {
        DataBase db_ = newData();
        Berry move_ = Instances.newBerry();
        move_.getHealStatus().add(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,move_.getHealStatus().first());
    }
    @Test
    public void renameStatus10() {
        DataBase db_ = newData();
        ItemForBattle move_ = Instances.newItemForBattle();
        EffectEndRoundIndividual eff_ = Instances.newEffectEndRoundIndividual();
        eff_.setUserStatusEndRound(POKE_BALL);
        move_.getEffectEndRound().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,eff_.getUserStatusEndRound());
    }
    @Test
    public void renameStatus11() {
        DataBase db_ = newData();
        ItemForBattle move_ = Instances.newItemForBattle();
        move_.getImmuStatus().add(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,move_.getImmuStatus().first());
    }
    @Test
    public void renameStatus12() {
        DataBase db_ = newData();
        HealingStatus move_ = Instances.newHealingHpStatus();
        move_.getStatus().add(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,move_.getStatus().first());
    }
    @Test
    public void renameStatus13() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        EffectEndRoundIndividual eff_ = Instances.newEffectEndRoundIndividual();
        eff_.setUserStatusEndRound(POKE_BALL);
        move_.getEffectEndRound().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,eff_.getUserStatusEndRound());
    }
    @Test
    public void renameStatus14() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        EffectCombo ec_ = Instances.newEffectCombo();
        EffectTeam eff_ = Instances.newEffectTeam();
        eff_.getProtectAgainstStatus().add(POKE_BALL);
        ec_.getTeamMove().add(eff_);
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(TREMPETTE), ec_));
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,eff_.getProtectAgainstStatus().first());
    }
    @Test
    public void renameStatus15() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.getForwardStatus().addEntry(NULL_REF,POKE_BALL_2);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(POKE_BALL_2,move_.getForwardStatus().firstValue());
    }
    @Test
    public void renameStatus16() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.SPEED,POKE_BALL));
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,move_.getImmuLowStatIfStatus().first().getStatus());
    }
    @Test
    public void renameStatus17() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.getMultStatIfStatutRank().addEntry(new StatisticStatus(Statistic.SPEED,POKE_BALL),1L);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.renameStatus(POKE_BALL,PIKACHU);
        assertEq(1,db_.getStatus().size());
        assertEq(PIKACHU,db_.getStatus().firstKey());
        assertEq(PIKACHU,move_.getMultStatIfStatutRank().firstKey().getStatus());
    }
    @Test
    public void deleteStatus0() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedStatus().addEntry(NULL_REF, tr_);
        db_.deleteStatus(POKE_BALL);
        assertEq(0,db_.getStatus().size());
        assertEq(0,tr_.size());
    }
    @Test
    public void deleteStatus1() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(0,db_.getStatus().size());
    }
    @Test
    public void deleteStatus2() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectEndRoundIndividual eff_ = Instances.newEffectEndRoundIndividual();
        eff_.setUserStatusEndRound(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus3() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectEndRoundMultiRelation eff_ = Instances.newEffectEndRoundMultiRelation();
        eff_.getDamageByStatus().addEntry(POKE_BALL,Rate.one());
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus4() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectTeam eff_ = Instances.newEffectTeam();
        eff_.getProtectAgainstStatus().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus5() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectTeamWhileSendFoe eff_ = Instances.newEffectTeamWhileSendFoe();
        eff_.getStatusByNbUses().addEntry(1L,POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus6() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectGlobal eff_ = Instances.newEffectGlobal();
        eff_.getPreventStatus().add(POKE_BALL);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus7() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectStatus eff_ = Instances.newEffectStatus();
        eff_.getLawStatus().getLaw().addEntry(POKE_BALL, LgInt.one());
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus8() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectStatus eff_ = Instances.newEffectStatus();
        eff_.getLawStatus().getLaw().addEntry(POKE_BALL_2, LgInt.one());
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(0,db_.getStatus().size());
    }
    @Test
    public void deleteStatus9() {
        DataBase db_ = newData();
        Berry move_ = Instances.newBerry();
        move_.getHealStatus().add(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus10() {
        DataBase db_ = newData();
        ItemForBattle move_ = Instances.newItemForBattle();
        EffectEndRoundIndividual eff_ = Instances.newEffectEndRoundIndividual();
        eff_.setUserStatusEndRound(POKE_BALL);
        move_.getEffectEndRound().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus11() {
        DataBase db_ = newData();
        ItemForBattle move_ = Instances.newItemForBattle();
        move_.getImmuStatus().add(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus12() {
        DataBase db_ = newData();
        HealingStatus move_ = Instances.newHealingHpStatus();
        move_.getStatus().add(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus13() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        EffectEndRoundIndividual eff_ = Instances.newEffectEndRoundIndividual();
        eff_.setUserStatusEndRound(POKE_BALL);
        move_.getEffectEndRound().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus14() {
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        EffectCombo ec_ = Instances.newEffectCombo();
        EffectTeam eff_ = Instances.newEffectTeam();
        eff_.getProtectAgainstStatus().add(POKE_BALL);
        ec_.getTeamMove().add(eff_);
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(TREMPETTE), ec_));
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus15() {
        DataBase db_ = newData();
        StatusSimple move_ = Instances.newStatusSimple();
        EffectEndRoundSingleStatus eff_ = Instances.newEffectEndRoundSingleStatus();
        eff_.setFailEndRound(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+POKE_BALL+RB+RP);
        move_.getEffectEndRound().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(2,db_.getStatus().size());
    }
    @Test
    public void deleteStatus16() {
        DataBase db_ = newData();
        StatusSimple move_ = Instances.newStatusSimple();
        EffectEndRoundSingleStatus eff_ = Instances.newEffectEndRoundSingleStatus();
        eff_.setFailEndRound(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+POKE_BALL+RB+RP);
        move_.getEffectEndRound().add(eff_);
        db_.completeMembers(POKE_BALL, move_);
        db_.deleteStatus(POKE_BALL);
        assertEq(0,db_.getStatus().size());
    }
    @Test
    public void deleteStatus17() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.getForwardStatus().addEntry(NULL_REF,POKE_BALL_2);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(0,db_.getStatus().size());
        assertEq(POKE_BALL_2,move_.getForwardStatus().firstValue());
    }
    @Test
    public void deleteStatus18() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectSwitchAbilities eff_ = Instances.newEffectSwitchAbilities();
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+POKE_BALL+RB+RP);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void deleteStatus19() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.SPEED,POKE_BALL_2));
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(0,db_.getStatus().size());
        assertEq(POKE_BALL_2,move_.getImmuLowStatIfStatus().first().getStatus());
    }
    @Test
    public void deleteStatus20() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.SPEED,POKE_BALL));
        db_.completeMembers(POKE_BALL_2, move_);
        db_.completeMembers(POKE_BALL,Instances.newStatusSimple());
        db_.deleteStatus(POKE_BALL);
        assertEq(1,db_.getStatus().size());
    }
    @Test
    public void renameType0() {
        DataBase db_ = newData();
        StringMap<String> tr_ = defTrType(db_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(1,tr_.size());
        assertEq(PIKACHU,tr_.firstKey());
    }
    @Test
    public void renameType1() {
        DataBase db_ = newData();
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        db_.getTranslatedTypes().addEntry(NULL_REF, tr_);
        db_.renameType(POKE_BALL,POKE_BALL);
        assertEq(1,tr_.size());
        assertEq(POKE_BALL,tr_.firstKey());
    }
    @Test
    public void renameType2() {
        DataBase db_ = newData();
        PokemonData move_ = Instances.newPokemonData();
        move_.getTypes().add(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,move_.getTypes().first());
    }
    @Test
    public void renameType3() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectUnprotectFromTypes e_ = Instances.newEffectUnprotectFromTypes();
        e_.getTypes().add(new TypesDuo(POKE_BALL,POKE_BALL));
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,e_.getTypes().first().getPokemonType());
        assertEq(PIKACHU,e_.getTypes().first().getDamageType());
    }
    @Test
    public void renameType4() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectSwitchTypes e_ = Instances.newEffectSwitchTypes();
        e_.getConstTypes().add(POKE_BALL);
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,e_.getConstTypes().first());
    }
    @Test
    public void renameType5() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectTeamWhileSendFoe e_ = Instances.newEffectTeamWhileSendFoe();
        e_.getDeletedByFoeTypes().add(POKE_BALL);
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,e_.getDeletedByFoeTypes().first());
    }
    @Test
    public void renameType6() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectGlobal e_ = Instances.newEffectGlobal();
        e_.getEfficiencyMoves().addEntry(new TypesDuo(POKE_BALL,POKE_BALL),Rate.one());
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,e_.getEfficiencyMoves().getKeys().first().getPokemonType());
        assertEq(PIKACHU,e_.getEfficiencyMoves().getKeys().first().getDamageType());
    }
    @Test
    public void renameType7() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectGlobal e_ = Instances.newEffectGlobal();
        e_.getMultStatIfContainsType().addEntry(new StatisticType(Statistic.SPEED,POKE_BALL),Rate.one());
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,e_.getMultStatIfContainsType().getKeys().first().getType());
    }
    @Test
    public void renameType8() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectProtectFromTypes e_ = Instances.newEffectProtectFromTypes();
        e_.getImmuAgainstTypes().add(POKE_BALL);
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,e_.getImmuAgainstTypes().first());
    }
    @Test
    public void renameType9() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectMultUsedMovePower e_ = Instances.newEffectMultUsedMovePower();
        e_.getMultMovePowerFctType().addEntry(POKE_BALL,Rate.one());
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,e_.getMultMovePowerFctType().firstKey());
    }
    @Test
    public void renameType10() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectMultSufferedMovePower e_ = Instances.newEffectMultSufferedMovePower();
        e_.getMultMovePowerFctType().addEntry(POKE_BALL,Rate.one());
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,e_.getMultMovePowerFctType().firstKey());
    }
    @Test
    public void renameType11() {
        DataBase db_ = newData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectEndRoundIndividual e_ = Instances.newEffectEndRoundIndividual();
        e_.getHealHpByOwnerTypes().addEntry(POKE_BALL,Rate.one());
        move_.getEffects().add(e_);
        move_.getEffects().add(Instances.newEffectEndRoundFoe());
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,e_.getHealHpByOwnerTypes().firstKey());
    }
    @Test
    public void renameType12() {
        DataBase db_ = newData();
        ItemForBattle move_ = Instances.newItemForBattle();
        EffectEndRoundIndividual e_ = Instances.newEffectEndRoundIndividual();
        e_.getHealHpByOwnerTypes().addEntry(POKE_BALL,Rate.one());
        move_.getEffectEndRound().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,e_.getHealHpByOwnerTypes().firstKey());
    }
    @Test
    public void renameType13() {
        DataBase db_ = newData();
        Berry move_ = Instances.newBerry();
        move_.getMultFoesDamage().addEntry(POKE_BALL,new EfficiencyRate(Rate.one(),Rate.one()));
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,move_.getMultFoesDamage().firstKey());
    }
    @Test
    public void renameType14() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.getMultStatIfDamgeType().addEntry(new StatisticType(Statistic.SPEED,POKE_BALL),0L);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,move_.getMultStatIfDamgeType().getKeys().first().getType());
    }
    @Test
    public void renameType15() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.getBreakFoeImmune().add(new TypesDuo(POKE_BALL,POKE_BALL));
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,move_.getBreakFoeImmune().first().getPokemonType());
        assertEq(PIKACHU,move_.getBreakFoeImmune().first().getDamageType());
    }
    @Test
    public void renameType16() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.getHealHpByTypeIfWeather().addEntry(new WeatherType(POKE_BALL,POKE_BALL),Rate.one());
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,move_.getHealHpByTypeIfWeather().firstKey().getType());
    }
    @Test
    public void renameType17() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.getImmuMoveTypesByWeather().addEntry(NULL_REF,new StringList(POKE_BALL));
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,move_.getImmuMoveTypesByWeather().firstValue().first());
    }
    @Test
    public void renameType18() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.setTypeForMoves(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,move_.getTypeForMoves());
    }
    @Test
    public void renameType19() {
        DataBase db_ = newData();
        db_.getTableTypes().addEntry(new TypesDuo(POKE_BALL,POKE_BALL),Rate.one());
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,db_.getTableTypes().firstKey().getDamageType());
        assertEq(PIKACHU,db_.getTableTypes().firstKey().getPokemonType());
    }
    @Test
    public void renameType20() {
        DataBase db_ = newData();
        AbilityData move_ = Instances.newAbilityData();
        move_.getChangingBoostTypes().addEntry(POKE_BALL_2,new TypeDamageBoost(POKE_BALL,Rate.one()));
        db_.completeMembers(POKE_BALL_2, move_);
        db_.renameType(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,move_.getChangingBoostTypes().firstValue().getType());
    }
    @Test
    public void deleteType0() {
        DataBase db_ = newData();
        defTrType(db_);
        assertFalse(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType1() {
        DataBase db_ = newData();
        defTrType(db_);
        assertFalse(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType2() {
        DataBase db_ = newData();
        defTrType(db_);
        PokemonData move_ = Instances.newPokemonData();
        move_.getTypes().add(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType3() {
        DataBase db_ = newData();
        defTrType(db_);
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectUnprotectFromTypes e_ = Instances.newEffectUnprotectFromTypes();
        e_.getTypes().add(new TypesDuo(POKE_BALL,POKE_BALL));
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType4() {
        DataBase db_ = newData();
        defTrType(db_);
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectSwitchTypes e_ = Instances.newEffectSwitchTypes();
        e_.getConstTypes().add(POKE_BALL);
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType5() {
        DataBase db_ = newData();
        defTrType(db_);
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectTeamWhileSendFoe e_ = Instances.newEffectTeamWhileSendFoe();
        e_.getDeletedByFoeTypes().add(POKE_BALL);
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType6() {
        DataBase db_ = newData();
        defTrType(db_);
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectGlobal e_ = Instances.newEffectGlobal();
        e_.getEfficiencyMoves().addEntry(new TypesDuo(POKE_BALL,POKE_BALL),Rate.one());
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType7() {
        DataBase db_ = newData();
        defTrType(db_);
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectGlobal e_ = Instances.newEffectGlobal();
        e_.getMultStatIfContainsType().addEntry(new StatisticType(Statistic.SPEED,POKE_BALL),Rate.one());
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType8() {
        DataBase db_ = newData();
        defTrType(db_);
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectProtectFromTypes e_ = Instances.newEffectProtectFromTypes();
        e_.getImmuAgainstTypes().add(POKE_BALL);
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType9() {
        DataBase db_ = newData();
        defTrType(db_);
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectMultUsedMovePower e_ = Instances.newEffectMultUsedMovePower();
        e_.getMultMovePowerFctType().addEntry(POKE_BALL,Rate.one());
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType10() {
        DataBase db_ = newData();
        defTrType(db_);
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectMultSufferedMovePower e_ = Instances.newEffectMultSufferedMovePower();
        e_.getMultMovePowerFctType().addEntry(POKE_BALL,Rate.one());
        move_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType11() {
        DataBase db_ = newData();
        defTrType(db_);
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectEndRoundIndividual e_ = Instances.newEffectEndRoundIndividual();
        e_.getHealHpByOwnerTypes().addEntry(POKE_BALL,Rate.one());
        move_.getEffects().add(e_);
        move_.getEffects().add(Instances.newEffectEndRoundFoe());
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType12() {
        DataBase db_ = newData();
        defTrType(db_);
        ItemForBattle move_ = Instances.newItemForBattle();
        EffectEndRoundIndividual e_ = Instances.newEffectEndRoundIndividual();
        e_.getHealHpByOwnerTypes().addEntry(POKE_BALL,Rate.one());
        move_.getEffectEndRound().add(e_);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType13() {
        DataBase db_ = newData();
        defTrType(db_);
        Berry move_ = Instances.newBerry();
        move_.getMultFoesDamage().addEntry(POKE_BALL,new EfficiencyRate(Rate.one(),Rate.one()));
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType14() {
        DataBase db_ = newData();
        defTrType(db_);
        AbilityData move_ = Instances.newAbilityData();
        move_.getMultStatIfDamgeType().addEntry(new StatisticType(Statistic.SPEED,POKE_BALL),0L);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType15() {
        DataBase db_ = newData();
        defTrType(db_);
        AbilityData move_ = Instances.newAbilityData();
        move_.getBreakFoeImmune().add(new TypesDuo(POKE_BALL,POKE_BALL));
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType16() {
        DataBase db_ = newData();
        defTrType(db_);
        AbilityData move_ = Instances.newAbilityData();
        move_.getHealHpByTypeIfWeather().addEntry(new WeatherType(POKE_BALL,POKE_BALL),Rate.one());
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType17() {
        DataBase db_ = newData();
        defTrType(db_);
        AbilityData move_ = Instances.newAbilityData();
        move_.getImmuMoveTypesByWeather().addEntry(NULL_REF,new StringList(POKE_BALL));
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType18() {
        DataBase db_ = newData();
        defTrType(db_);
        AbilityData move_ = Instances.newAbilityData();
        move_.setTypeForMoves(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void deleteType19() {
        DataBase db_ = newData();
        defTrType(db_);
        db_.getTableTypes().addEntry(new TypesDuo(POKE_BALL,POKE_BALL),Rate.one());
        assertTrue(db_.usedType(POKE_BALL));
        assertEq(1,db_.getTableTypes().size());
    }
    @Test
    public void deleteType20() {
        DataBase db_ = newData();
        defTrType(db_);
        db_.getTableTypes().addEntry(new TypesDuo(POKE_BALL,POKE_BALL),Rate.one());
        db_.getTableTypes().addEntry(new TypesDuo(POKE_BALL,POKE_BALL_2),Rate.one());
        db_.getTableTypes().addEntry(new TypesDuo(POKE_BALL_2,POKE_BALL),Rate.one());
        db_.getTableTypes().addEntry(new TypesDuo(POKE_BALL_2,POKE_BALL_2),Rate.one());
        assertTrue(db_.usedType(POKE_BALL));
        assertEq(4,db_.getTableTypes().size());
    }
    @Test
    public void deleteType21() {
        DataBase db_ = newData();
        defTrType(db_);
        db_.getTableTypes().addEntry(new TypesDuo(POKE_BALL,POKE_BALL),Rate.one());
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectSwitchAbilities eff_ = Instances.newEffectSwitchAbilities();
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+POKE_BALL+RB+RP);
        move_.getEffects().add(eff_);
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
        assertEq(1,db_.getTableTypes().size());
    }
    @Test
    public void deleteType22() {
        DataBase db_ = newData();
        defTrType(db_);
        db_.getTableTypes().addEntry(new TypesDuo(POKE_BALL,POKE_BALL),Rate.one());
        AbilityData move_ = Instances.newAbilityData();
        move_.getChangingBoostTypes().addEntry(POKE_BALL_2,new TypeDamageBoost(POKE_BALL,Rate.one()));
        db_.completeMembers(POKE_BALL_2, move_);
        assertTrue(db_.usedType(POKE_BALL));
    }
    @Test
    public void renameCategory0() {
        DataBase db_ = newData();
        StringMap<String> tr_ = defTrCat(db_);
        db_.renameCategory(POKE_BALL,PIKACHU);
        assertEq(1,tr_.size());
        assertEq(PIKACHU,tr_.firstKey());
    }
    @Test
    public void renameCategory1() {
        DataBase db_ = newData();
        StringMap<String> tr_ = defTrCat(db_);
        db_.renameCategory(POKE_BALL,POKE_BALL);
        assertEq(1,tr_.size());
        assertEq(POKE_BALL,tr_.firstKey());
    }
    @Test
    public void renameCategory2() {
        DataBase db_ = newData();
        defTrCat(db_);
        AbilityData ab_ = Instances.newAbilityData();
        ab_.getMultStatIfDamageCat().addEntry(new StatisticCategory(Statistic.SPEED,POKE_BALL),1L);
        db_.completeMembers(POKE_BALL_2, ab_);
        db_.renameCategory(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,ab_.getMultStatIfDamageCat().getKeys().first().getCategory());
    }
    @Test
    public void renameCategory3() {
        DataBase db_ = newData();
        defTrCat(db_);
        AbilityData ab_ = Instances.newAbilityData();
        ab_.getMultStatIfCat().addEntry(new StatisticCategory(Statistic.SPEED,POKE_BALL),Rate.one());
        db_.completeMembers(POKE_BALL_2, ab_);
        db_.renameCategory(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,ab_.getMultStatIfCat().getKeys().first().getCategory());
    }
    @Test
    public void renameCategory4() {
        DataBase db_ = newData();
        defTrCat(db_);
        DamagingMoveData ab_ = Instances.newDamagingMoveData();
        ab_.setCategory(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, ab_);
        db_.renameCategory(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,ab_.getCategory());
    }
    @Test
    public void renameCategory5() {
        DataBase db_ = newData();
        defTrCat(db_);
        StatusMoveData ab_ = Instances.newStatusMoveData();
        EffectTeam e_ = Instances.newEffectTeam();
        e_.getMultDamage().addEntry(new CategoryMult(POKE_BALL, 1),Rate.one());
        ab_.getEffects().add(e_);
        ab_.getEffects().add(Instances.newEffectEndRoundFoe());
        db_.completeMembers(POKE_BALL_2, ab_);
        db_.renameCategory(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,e_.getMultDamage().firstKey().getCategory());
    }
    @Test
    public void renameCategory6() {
        DataBase db_ = newData();
        defTrCat(db_);
        Berry ab_ = Instances.newBerry();
        ab_.getDamageRateRecoilFoe().addEntry(POKE_BALL,Rate.one());
        db_.completeMembers(POKE_BALL_2, ab_);
        db_.completeMembers(TREMPETTE, Instances.newItemForBattle());
        db_.renameCategory(POKE_BALL,PIKACHU);
        assertEq(PIKACHU,ab_.getDamageRateRecoilFoe().firstKey());
    }
    @Test
    public void renameCategory7() {
        DataBase db_ = newData();
        StringMap<String> tr_ = defTrCat(db_);
        Berry ab_ = Instances.newBerry();
        ab_.getDamageRateRecoilFoe().addEntry(POKE_BALL,Rate.one());
        db_.completeMembers(POKE_BALL_2, ab_);
        db_.completeMembers(TREMPETTE, Instances.newItemForBattle());
        db_.renameCategory(db_.getDefCategory(),PIKACHU);
        assertEq(1,tr_.size());
        assertTrue(tr_.contains(POKE_BALL));
    }
    @Test
    public void renameCategory8() {
        DataBase db_ = newData();
        StringMap<String> tr_ = defTrCat(db_);
        Berry ab_ = Instances.newBerry();
        ab_.getDamageRateRecoilFoe().addEntry(POKE_BALL,Rate.one());
        db_.completeMembers(POKE_BALL_2, ab_);
        db_.completeMembers(TREMPETTE, Instances.newItemForBattle());
        db_.renameCategory(PIKACHU,db_.getDefCategory());
        assertEq(1,tr_.size());
        assertTrue(tr_.contains(POKE_BALL));
    }
    @Test
    public void deleteCategory0() {
        DataBase db_ = newData();
        defTrCat(db_);
        assertFalse(db_.usedCategory(POKE_BALL));
    }
    @Test
    public void deleteCategory1() {
        DataBase db_ = newData();
        defTrCat(db_);
        assertFalse(db_.usedCategory(POKE_BALL));
    }
    @Test
    public void deleteCategory2() {
        DataBase db_ = newData();
        defTrCat(db_);
        AbilityData ab_ = Instances.newAbilityData();
        ab_.getMultStatIfDamageCat().addEntry(new StatisticCategory(Statistic.SPEED,POKE_BALL),1L);
        db_.completeMembers(POKE_BALL_2, ab_);
        assertTrue(db_.usedCategory(POKE_BALL));
    }
    @Test
    public void deleteCategory3() {
        DataBase db_ = newData();
        defTrCat(db_);
        AbilityData ab_ = Instances.newAbilityData();
        ab_.getMultStatIfCat().addEntry(new StatisticCategory(Statistic.SPEED,POKE_BALL),Rate.one());
        db_.completeMembers(POKE_BALL_2, ab_);
        assertTrue(db_.usedCategory(POKE_BALL));
    }
    @Test
    public void deleteCategory4() {
        DataBase db_ = newData();
        defTrCat(db_);
        DamagingMoveData ab_ = Instances.newDamagingMoveData();
        ab_.setCategory(POKE_BALL);
        db_.completeMembers(POKE_BALL_2, ab_);
        assertTrue(db_.usedCategory(POKE_BALL));
    }
    @Test
    public void deleteCategory5() {
        DataBase db_ = newData();
        defTrCat(db_);
        StatusMoveData ab_ = Instances.newStatusMoveData();
        EffectTeam e_ = Instances.newEffectTeam();
        e_.getMultDamage().addEntry(new CategoryMult(POKE_BALL, 1),Rate.one());
        ab_.getEffects().add(e_);
        ab_.getEffects().add(Instances.newEffectEndRoundFoe());
        db_.completeMembers(POKE_BALL_2, ab_);
        assertTrue(db_.usedCategory(POKE_BALL));
    }
    @Test
    public void deleteCategory6() {
        DataBase db_ = newData();
        defTrCat(db_);
        Berry ab_ = Instances.newBerry();
        ab_.getDamageRateRecoilFoe().addEntry(POKE_BALL,Rate.one());
        db_.completeMembers(POKE_BALL_2, ab_);
        db_.completeMembers(TREMPETTE, Instances.newItemForBattle());
        assertTrue(db_.usedCategory(POKE_BALL));
    }
    @Test
    public void deleteCategory7() {
        DataBase db_ = newData();
        defTrCat(db_);
        StatusMoveData ab_ = Instances.newStatusMoveData();
        EffectTeam e_ = Instances.newEffectTeam();
        e_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+POKE_BALL+RB+RP);
        ab_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, ab_);
        assertTrue(db_.usedCategory(POKE_BALL));
    }
    @Test
    public void deleteCategory8() {
        DataBase db_ = newData();
        db_.initValue(DataBase.DEF_CAT,POKE_BALL);
        defTrCat(db_);
        StatusMoveData ab_ = Instances.newStatusMoveData();
        EffectTeam e_ = Instances.newEffectTeam();
        e_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+POKE_BALL+RB+RP);
        ab_.getEffects().add(e_);
        db_.completeMembers(POKE_BALL_2, ab_);
        assertTrue(db_.usedCategory(db_.getDefCategory()));
    }
    @Test
    public void renameTm1() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        db_.renameTm(1,1);
        assertEq(1,db_.getTm().size());
        assertEq(1,db_.getTm().firstKey());
    }
    @Test
    public void renameTm2() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getTechnicalMoves().add( 1);
        db_.completeMembers(TREMPETTE, pk_);
        db_.renameTm(1,2);
        assertEq(1,db_.getTm().size());
        assertEq(2,db_.getTm().firstKey());
        assertEq(2,pk_.getTechnicalMoves().first());
    }
    @Test
    public void renameTm3() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getTechnicalMoves().add( 3);
        db_.completeMembers(TREMPETTE, pk_);
        db_.renameTm(1,2);
        assertEq(1,db_.getTm().size());
        assertEq(2,db_.getTm().firstKey());
        assertEq(3,pk_.getTechnicalMoves().first());
    }
    @Test
    public void renameTm4() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        Road r_ = Instances.newRoad();
        r_.getLevelRoad().getTm().addEntry(newPoint(0,0),  1);
        db_.getMap().getPlaces().add(r_);
        db_.renameTm(1,2);
        assertEq(1,db_.getTm().size());
        assertEq(2,db_.getTm().firstKey());
        assertEq(2,r_.getLevelRoad().getTm().firstValue());
    }
    @Test
    public void renameTm5() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        Road r_ = Instances.newRoad();
        r_.getLevelRoad().getTm().addEntry(newPoint(0,0),  3);
        db_.getMap().getPlaces().add(r_);
        db_.renameTm(1,2);
        assertEq(1,db_.getTm().size());
        assertEq(2,db_.getTm().firstKey());
        assertEq(3,r_.getLevelRoad().getTm().firstValue());
    }
    @Test
    public void renameTm6() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        Road r_ = Instances.newRoad();
        DealerItem d_ = Instances.newDealerItem();
        d_.getTechnicalMoves().add( 1);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,0), d_);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,1),Instances.newTrainerMultiFights());
        db_.getMap().getPlaces().add(r_);
        db_.renameTm(1,2);
        assertEq(1,db_.getTm().size());
        assertEq(2,db_.getTm().firstKey());
        assertEq(2,d_.getTechnicalMoves().first());
    }
    @Test
    public void renameTm7() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        City r_ = Instances.newCity();
        Gym g_ = Instances.newGym();
        g_.getIndoor().getGymLeader().setTm( 1);
        r_.getBuildings().addEntry(newPoint(0,0), g_);
        r_.getBuildings().addEntry(newPoint(0,1),Instances.newPokemonCenter());
        db_.getMap().getPlaces().add(r_);
        db_.renameTm(1,2);
        assertEq(1,db_.getTm().size());
        assertEq(2,db_.getTm().firstKey());
        assertEq(2,g_.getIndoor().getGymLeader().getTm());
    }
    @Test
    public void renameTm8() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        City r_ = Instances.newCity();
        Gym g_ = Instances.newGym();
        g_.getIndoor().getGymLeader().setTm( 3);
        r_.getBuildings().addEntry(newPoint(0,0), g_);
        r_.getBuildings().addEntry(newPoint(0,1),Instances.newPokemonCenter());
        db_.getMap().getPlaces().add(r_);
        db_.renameTm(1,2);
        assertEq(1,db_.getTm().size());
        assertEq(2,db_.getTm().firstKey());
        assertEq(3,g_.getIndoor().getGymLeader().getTm());
    }
    @Test
    public void deleteTm1() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        db_.deleteTm(1);
        assertEq(0,db_.getTm().size());
    }
    @Test
    public void deleteTm2() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getTechnicalMoves().add( 1);
        db_.completeMembers(TREMPETTE, pk_);
        db_.deleteTm(1);
        assertEq(1,db_.getTm().size());
    }
    @Test
    public void deleteTm3() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getTechnicalMoves().add( 3);
        db_.completeMembers(TREMPETTE, pk_);
        db_.deleteTm(1);
        assertEq(0,db_.getTm().size());
    }
    @Test
    public void deleteTm4() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        Road r_ = Instances.newRoad();
        r_.getLevelRoad().getTm().addEntry(newPoint(0,0),  1);
        db_.getMap().getPlaces().add(r_);
        db_.deleteTm(1);
        assertEq(1,db_.getTm().size());
    }
    @Test
    public void deleteTm5() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        Road r_ = Instances.newRoad();
        r_.getLevelRoad().getTm().addEntry(newPoint(0,0),  3);
        db_.getMap().getPlaces().add(r_);
        db_.deleteTm(1);
        assertEq(0,db_.getTm().size());
    }
    @Test
    public void deleteTm6() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        Road r_ = Instances.newRoad();
        DealerItem d_ = Instances.newDealerItem();
        d_.getTechnicalMoves().add( 1);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,0), d_);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(0,1),Instances.newTrainerMultiFights());
        db_.getMap().getPlaces().add(r_);
        db_.deleteTm(1);
        assertEq(1,db_.getTm().size());
    }
    @Test
    public void deleteTm7() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        City r_ = Instances.newCity();
        Gym g_ = Instances.newGym();
        g_.getIndoor().getGymLeader().setTm( 1);
        r_.getBuildings().addEntry(newPoint(0,0), g_);
        r_.getBuildings().addEntry(newPoint(0,1),Instances.newPokemonCenter());
        db_.getMap().getPlaces().add(r_);
        db_.deleteTm(1);
        assertEq(1,db_.getTm().size());
    }
    @Test
    public void deleteTm8() {
        DataBase db_ = newData();
        db_.getTm().addEntry(1,POKE_BALL);
        City r_ = Instances.newCity();
        Gym g_ = Instances.newGym();
        g_.getIndoor().getGymLeader().setTm( 3);
        r_.getBuildings().addEntry(newPoint(0,0), g_);
        r_.getBuildings().addEntry(newPoint(0,1),Instances.newPokemonCenter());
        db_.getMap().getPlaces().add(r_);
        db_.deleteTm(1);
        assertEq(0,db_.getTm().size());
    }
    @Test
    public void renameHm1() {
        DataBase db_ = newData();
        db_.getHm().addEntry(1,POKE_BALL);
        db_.renameHm(1,1);
        assertEq(1,db_.getHm().size());
        assertEq(1,db_.getHm().firstKey());
    }
    @Test
    public void renameHm2() {
        DataBase db_ = newData();
        db_.getHm().addEntry(1,POKE_BALL);
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getHiddenMoves().add( 1);
        db_.completeMembers(TREMPETTE, pk_);
        db_.renameHm(1,2);
        assertEq(1,db_.getHm().size());
        assertEq(2,db_.getHm().firstKey());
        assertEq(2,pk_.getHiddenMoves().first());
    }
    @Test
    public void renameHm3() {
        DataBase db_ = newData();
        db_.getHm().addEntry(1,POKE_BALL);
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getHiddenMoves().add( 3);
        db_.completeMembers(TREMPETTE, pk_);
        City r_ = Instances.newCity();
        Gym g_ = Instances.newGym();
        g_.getIndoor().getGymLeader().setTm( 1);
        r_.getBuildings().addEntry(newPoint(0,0), g_);
        r_.getBuildings().addEntry(newPoint(0,1),Instances.newPokemonCenter());
        db_.getMap().getPlaces().add(r_);
        db_.renameHm(1,2);
        assertEq(1,db_.getHm().size());
        assertEq(2,db_.getHm().firstKey());
        assertEq(3,pk_.getHiddenMoves().first());
    }
    @Test
    public void renameHm4() {
        DataBase db_ = newData();
        db_.getHm().addEntry(1,POKE_BALL);
        Road r_ = Instances.newRoad();
        r_.getLevelRoad().getHm().addEntry(newPoint(0,0),  1);
        db_.getMap().getPlaces().add(r_);
        db_.renameHm(1,2);
        assertEq(1,db_.getHm().size());
        assertEq(2,db_.getHm().firstKey());
        assertEq(2,r_.getLevelRoad().getHm().firstValue());
    }
    @Test
    public void renameHm5() {
        DataBase db_ = newData();
        db_.getHm().addEntry(1,POKE_BALL);
        Road r_ = Instances.newRoad();
        r_.getLevelRoad().getHm().addEntry(newPoint(0,0),  3);
        db_.getMap().getPlaces().add(r_);
        db_.renameHm(1,2);
        assertEq(1,db_.getHm().size());
        assertEq(2,db_.getHm().firstKey());
        assertEq(3,r_.getLevelRoad().getHm().firstValue());
    }
    @Test
    public void deleteHm1() {
        DataBase db_ = newData();
        db_.getHm().addEntry(1,POKE_BALL);
        db_.deleteHm(1);
        assertEq(0,db_.getHm().size());
    }
    @Test
    public void deleteHm2() {
        DataBase db_ = newData();
        db_.getHm().addEntry(1,POKE_BALL);
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getHiddenMoves().add( 1);
        db_.completeMembers(TREMPETTE, pk_);
        db_.deleteHm(1);
        assertEq(1,db_.getHm().size());
    }
    @Test
    public void deleteHm3() {
        DataBase db_ = newData();
        db_.getHm().addEntry(1,POKE_BALL);
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getHiddenMoves().add( 3);
        db_.completeMembers(TREMPETTE, pk_);
        City r_ = Instances.newCity();
        Gym g_ = Instances.newGym();
        g_.getIndoor().getGymLeader().setTm( 1);
        r_.getBuildings().addEntry(newPoint(0,0), g_);
        r_.getBuildings().addEntry(newPoint(0,1),Instances.newPokemonCenter());
        db_.getMap().getPlaces().add(r_);
        db_.deleteHm(1);
        assertEq(0,db_.getHm().size());
    }
    @Test
    public void deleteHm4() {
        DataBase db_ = newData();
        db_.getHm().addEntry(1,POKE_BALL);
        Road r_ = Instances.newRoad();
        r_.getLevelRoad().getHm().addEntry(newPoint(0,0),  1);
        db_.getMap().getPlaces().add(r_);
        db_.deleteHm(1);
        assertEq(1,db_.getHm().size());
    }
    @Test
    public void deleteHm5() {
        DataBase db_ = newData();
        db_.getHm().addEntry(1,POKE_BALL);
        Road r_ = Instances.newRoad();
        r_.getLevelRoad().getHm().addEntry(newPoint(0,0),  3);
        db_.getMap().getPlaces().add(r_);
        db_.deleteHm(1);
        assertEq(0,db_.getHm().size());
    }

    private StringMap<String> defTrType(DataBase _db) {
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        _db.getTranslatedTypes().addEntry(NULL_REF, tr_);
        return tr_;
    }

    private StringMap<String> defTrCat(DataBase _db) {
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(POKE_BALL,NULL_REF);
        _db.getTranslatedCategories().addEntry(NULL_REF, tr_);
        return tr_;
    }
    private static DataBase newData() {
        DataBase db_ = new DataBase(DefaultGenerator.oneElt());
        db_.setVarParamsMove(new StringMap<StringList>());
        db_.initializeMembers();
        db_.defValues();
        db_.setCombos(Instances.newCombos());
        db_.setMap(Instances.newDataMap());
        return db_;
    }
}
