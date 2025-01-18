package aiki.game.fight;

import aiki.db.DataBase;
import aiki.fight.items.ItemForBattle;
import aiki.util.TeamPositionList;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.util.NextUsers;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.player.Player;
import aiki.map.characters.Ally;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymLeader;
import aiki.map.characters.TempTrainer;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class FightOrderTest extends InitializationDataBase {

    @Test
    public void fighters1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data_);
        TeamPositionList fighters_ = FightOrder.fighters(fight_);
        assertEq(4, fighters_.size());
        assertTrue(fighters_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(fighters_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        assertTrue(fighters_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(fighters_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void fighters2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data_);
        TeamPositionList fighters_ = FightOrder.fighters(fight_,Fight.CST_PLAYER);
        assertEq(2, fighters_.size());
        assertTrue(fighters_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(fighters_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void frontFighters1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data_);
        TeamPositionList fighters_ = FightOrder.frontFighters(fight_);
        assertEq(2, fighters_.size());
        assertTrue(fighters_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(fighters_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
    }

    private static Fight lastToUseMove(int _mult, DataBase _data) {
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(GRELOT_ZEN);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight(_mult);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data);
        return fight_;
    }

    @Test
    public void lastToUseMove1Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 1, data_);
        FightRound.initRound(fight_);
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), data_));
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), data_));
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_));
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), data_));
    }

    @Test
    public void lastToUseMove2Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 2, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_));
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), data_));
    }

    @Test
    public void lastToUseMove3Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 2, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_));
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), data_));
    }

    @Test
    public void lastToUseMove4Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 2, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(APRES_VOUS, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_));
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), data_));
    }

    @Test
    public void lastToUseMove5Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 2, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(A_LA_QUEUE, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightOrder.lastToUseMove(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_));
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), data_));
    }

    @Test
    public void lastToUseMove6Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 2, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(CHARGE, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_));
        assertTrue(!FightOrder.lastToUseMove(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), data_));
    }

    @Test
    public void fightersHavingToAct1Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 1, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(A_LA_QUEUE, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        TeamPositionList list_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.fightersHavingToAct(fight_,true, data_);
        assertEq(0, list_.size());
    }

    @Test
    public void fightersHavingToAct2Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 1, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(A_LA_QUEUE, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setActed(true);
        TeamPositionList list_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.fightersHavingToAct(fight_,true, data_);
        assertEq(0, list_.size());
    }

    @Test
    public void fightersHavingToAct3Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 1, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(A_LA_QUEUE, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setActed(true);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.zero());
        TeamPositionList list_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        assertEq(0, list_.size());
        list_ = FightOrder.fightersHavingToAct(fight_,true, data_);
        assertEq(0, list_.size());
    }

    @Test
    public void fightersHavingToAct4Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 2, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(A_LA_QUEUE, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setChosenHealingObject(CENDRESACREE, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).successUsingMove();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setActed(true);
        TeamPositionList list_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        list_ = FightOrder.fightersHavingToAct(fight_,true, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void fightersHavingToAct5Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 1, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setSubstitute( 1);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        TeamPositionList list_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.fightersHavingToAct(fight_,true, data_);
        assertEq(0, list_.size());
    }

    @Test
    public void fightersHavingToAct6Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 1, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setSubstitute( 1);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.zero());
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        TeamPositionList list_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.fightersHavingToAct(fight_,true, data_);
        assertEq(0, list_.size());
    }

    @Test
    public void reverseSpeed1Test() {
        DataBase data_ = initDb();
        Fight fight_ = lastToUseMove( 1, data_);
        assertTrue(!FightOrder.reverseSpeed(fight_,data_));
        fight_.enableGlobalMove(BROUHAHA);
        assertTrue(!FightOrder.reverseSpeed(fight_,data_));
        fight_.enableGlobalMove(DISTORSION);
        assertTrue(FightOrder.reverseSpeed(fight_,data_));
        fight_.enableGlobalMove(GRAVITE);
        assertTrue(FightOrder.reverseSpeed(fight_,data_));
    }

    @Test
    public void speed1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightRound.initRound(fight_);
        assertEq(new Rate("1893/25"), FightOrder.speed(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), data_));
    }

    private static Fight sortFightersHavingToActAmongList(DataBase _data) {
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void sortFightersUsingMoveAmongList1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersUsingMoveAmongList2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(APRES_VOUS, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersUsingMoveAmongList3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(RELAIS);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(FARCEUR);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersUsingMoveAmongList4Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(ENCENS_PLEIN);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersUsingMoveAmongList5Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(ENCENS_PLEIN);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(ENCENS_PLEIN);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersUsingMoveAmongList6Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setCurrentAbility(FREIN);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersUsingMoveAmongList7Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setCurrentAbility(FREIN);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(FREIN);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersUsingMoveAmongList8Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersUsingMoveAmongList9Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setLevel( 5);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatisBase().getVal(Statistic.SPEED).affect(new Rate("25"));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().put(Statistic.SPEED,  0);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getIv().put(Statistic.SPEED,  0);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setLevel( 5);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatisBase().getVal(Statistic.SPEED).affect(new Rate("25"));
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = new TeamPositionList();
        fighters_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        fighters_.add(tp(KEY_FOE, POKEMON_FIGHTER_ZERO));
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersUsingMoveAmongList10Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(AILES_BOURRASQUE);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersSwitchingAmongList1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersSwitchingAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersSwitchingAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersSwitchingAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersSwitchingAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersSwitchingAmongList2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setLevel( 5);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatisBase().getVal(Statistic.SPEED).affect(new Rate("25"));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().put(Statistic.SPEED,  0);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getIv().put(Statistic.SPEED,  0);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setLevel( 5);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatisBase().getVal(Statistic.SPEED).affect(new Rate("25"));
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = new TeamPositionList();
        fighters_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        fighters_.add(tp(KEY_FOE, POKEMON_FIGHTER_ZERO));
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersSwitchingAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersSwitchingAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersSwitchingAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersSwitchingAmongList(fight_, data_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersBeingHealedAmongList1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setChosenHealingObject(EAU_FRAICHE, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setChosenHealingObject(CENDRESACREE, data_);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersBeingHealedAmongList(fight_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersBeingHealedAmongList(fight_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersBeingHealedAmongList(fight_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersBeingHealedAmongList(fight_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
    }

    @Test
    public void sortFightersBeingHealedAmongList2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setChosenHealingObject(EAU_FRAICHE, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setChosenHealingObject(CENDRESACREE, data_);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersBeingHealedAmongList(fight_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersBeingHealedAmongList(fight_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersBeingHealedAmongList(fight_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersBeingHealedAmongList(fight_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), orderedFighters_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), orderedFighters_.get(1));
    }

    @Test
    public void sortFightersBeingHealedAmongList3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersHavingToActAmongList(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setChosenHealingObject(CENDRESACREE, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setChosenHealingObject(CENDRESACREE, data_);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersBeingHealedAmongList(fight_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        TeamPosition element_;
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersBeingHealedAmongList(fight_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        fight_.enableGlobalMove(DISTORSION);
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersBeingHealedAmongList(fight_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
        element_ = fighters_.get(0);
        fighters_.set(0, fighters_.get(1));
        fighters_.set(1, element_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersBeingHealedAmongList(fight_);
        orderedFighters_ = fight_.getTemp().getOrderedFighters();
        assertEq(2, orderedFighters_.size());
    }

    private static Fight sortFightersByWornBerry(DataBase _data) {
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void containsCbt1Test() {
        CustList<TeamPosition> list_ = new CustList<TeamPosition>();
        FightOrder.addIfPossible(list_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        assertEq(1,list_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), list_.get(0));
    }

    @Test
    public void containsCbt2Test() {
        CustList<TeamPosition> list_ = new CustList<TeamPosition>();
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        FightOrder.addIfPossible(list_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        assertEq(1,list_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), list_.get(0));
    }

    @Test
    public void containsCbt3Test() {
        CustList<TeamPosition> list_ = new CustList<TeamPosition>();
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        FightOrder.addIfPossible(list_,tp(KEY_PLAYER, POKEMON_FIGHTER_ONE));
        assertEq(2,list_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), list_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), list_.get(1));
    }
    @Test
    public void sortFightersByWornBerry1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersByWornBerry(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fighters_ = FightOrder.fightersUsingMove(fight_, fighters_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        NextUsers lists_ = FightOrder.sortFightersByWornBerry(fight_,orderedFighters_, data_);
        assertEq(2, lists_.getNextFighters().size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), lists_.getNextFighters().get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), lists_.getNextFighters().get(1));
        assertEq(1, lists_.getItemUsers().size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), lists_.getItemUsers().get(0));
    }

    @Test
    public void sortFightersByWornBerry2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersByWornBerry(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(BAIE_CHERIM);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fighters_ = FightOrder.fightersUsingMove(fight_, fighters_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        NextUsers lists_ = FightOrder.sortFightersByWornBerry(fight_,orderedFighters_, data_);
        assertEq(2, lists_.getNextFighters().size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), lists_.getNextFighters().get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), lists_.getNextFighters().get(1));
        assertEq(0, lists_.getItemUsers().size());
    }

    @Test
    public void sortFightersByWornBerry3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersByWornBerry(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(BAIE_CHERIM);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fighters_ = FightOrder.fightersUsingMove(fight_, fighters_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        NextUsers lists_ = FightOrder.sortFightersByWornBerry(fight_,orderedFighters_, data_);
        assertEq(2, lists_.getNextFighters().size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), lists_.getNextFighters().get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), lists_.getNextFighters().get(1));
        assertEq(0, lists_.getItemUsers().size());
    }

    @Test
    public void sortFightersByWornBerry4Test() {
        DataBase data_ = initDb();
        Fight fight_ = sortFightersByWornBerry(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersHavingToAct(fight_,false, data_);
        fighters_ = FightOrder.fightersUsingMove(fight_, fighters_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(fighters_);
        FightOrder.sortFightersUsingMoveAmongList(fight_, data_);
        TeamPositionList orderedFighters_ = fight_.getTemp().getOrderedFighters();
        NextUsers lists_ = FightOrder.sortFightersByWornBerry(fight_,orderedFighters_, data_);
        assertEq(2, lists_.getNextFighters().size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), lists_.getNextFighters().get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), lists_.getNextFighters().get(1));
        assertEq(0, lists_.getItemUsers().size());
    }

    @Test
    public void fightersUsingMoveWithBerry1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersUsingMoveWithBerry(fight_,FightOrder.frontFighters(fight_), data_);
        assertEq(1,fighters_.size());
        assertTrue(fighters_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void fightersUsingMoveWithBerry2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setItem(BAIE_MEPO);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersUsingMoveWithBerry(fight_,FightOrder.frontFighters(fight_), data_);
        assertEq(0,fighters_.size());
    }

    @Test
    public void fightersUsingMove1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersUsingMove(fight_,FightOrder.frontFighters(fight_));
        assertEq(2,fighters_.size());
        assertTrue(fighters_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(fighters_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void fightersSwitching1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersSwitching(fight_,FightOrder.frontFighters(fight_));
        assertEq(1,fighters_.size());
        assertTrue(fighters_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void fightersBeingHealed1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setChosenHealingObject(EAU_FRAICHE, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList fighters_ = FightOrder.fightersBeingHealed(fight_,FightOrder.frontFighters(fight_));
        assertEq(1,fighters_.size());
        assertTrue(fighters_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void indexOfRemoving1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setItem(VIVE_GRIFFE);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(VIVE_GRIFFE_TRUE);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(VIVE_GRIFFE);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(HYPER_BALL);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList list_ = new TeamPositionList();
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE));
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO));
        list_.add(tp(KEY_FOE, POKEMON_FIGHTER_ZERO));
        list_.add(tp(KEY_FOE, POKEMON_FIGHTER_ONE));
        assertEq(1, FightOrder.indexOfRemovingItem(fight_,list_, data_).getIndiceTirage());
        list_.removeObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE));
        assertEq(2, FightOrder.indexOfRemovingItem(fight_,list_, data_).getIndiceTirage());
        list_.removeObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO));
        assertEq(-1, FightOrder.indexOfRemovingItem(fight_,list_, data_).getIndiceTirage());
    }

    @Test
    public void randomFigtherHavingToAct1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(VIVE_GRIFFE_FALSE);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(VIVE_GRIFFE_TRUE);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(HYPER_BALL);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList list_ = new TeamPositionList();
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE));
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO));
        TeamPositionList next_ = randomFigtherHavingToAct(2, data_, fight_, list_);
        assertEq(1, next_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), next_.get(0));
        assertTrue(fight_.getTemp().getAcceptableChoices());
        list_.removeObj(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO));
        next_ = randomFigtherHavingToAct(1, data_, fight_, list_);
        assertEq(1, next_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), next_.get(0));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void randomFigtherHavingToAct2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(HYPER_BALL);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList list_ = new TeamPositionList();
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE));
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO));
        TeamPositionList next_;
        next_ = FightOrder.randomFigtherHavingToAct(fight_,list_, data_);
        assertEq(1, next_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), next_.get(0));
        list_.removeObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE));
        next_ = FightOrder.randomFigtherHavingToAct(fight_,list_, data_);
        assertEq(1, next_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), next_.get(0));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void randomFigtherHavingToAct3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(VIVE_GRIFFE_TRUE_FALSE);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(VIVE_GRIFFE_TRUE);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(HYPER_BALL);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList list_ = new TeamPositionList();
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE));
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO));
        TeamPositionList next_;
        next_ = FightOrder.randomFigtherHavingToAct(fight_,list_, data_);
        assertEq(1, next_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), next_.get(0));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void randomFigtherHavingToAct1FailTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(HYPER_BALL);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList next_ = FightOrder.randomFigtherHavingToAct(fight_,new TeamPositionList(), data_);
        assertEq(0, next_.size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void randomFigtherHavingToAct1FailSimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(VIVE_GRIFFE_TRUE_FALSE);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(VIVE_GRIFFE_TRUE);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(HYPER_BALL);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.getTemp().setSimulation(true);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList list_ = new TeamPositionList();
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE));
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO));
        TeamPositionList next_ = FightOrder.randomFigtherHavingToAct(fight_,list_, data_);
        assertEq(0, next_.size());
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void randomFigtherHavingToAct2FailSimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(VIVE_GRIFFE);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(VIVE_GRIFFE);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(HYPER_BALL);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.getTemp().setSimulation(true);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        FightRound.initRound(fight_);
        TeamPositionList list_ = new TeamPositionList();
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE));
        list_.add(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO));
        TeamPositionList next_ = randomFigtherHavingToAct(2, data_, fight_, list_);
        assertEq(0, next_.size());
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void fightersWearingExpObject1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setItem(MULTI_EXP);
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setRemainingHp(Rate.zero());
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setItem(HYPER_BALL);
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setItem(BRAC_MACHO);
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(HYPER_BALL);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        TeamPositionList fighters_ = FightOrder.fightersBelongingToUser(fight_,true);
        Ints fightersExpObject_ = FightOrder.fightersWearingExpObject(fight_, fighters_, data_);
        assertEq(1, fightersExpObject_.size());
        assertTrue(fightersExpObject_.containsObj(0));
    }

    @Test
    public void fightersBelongingToUser1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setItem(MULTI_EXP);
        player_.getTeam().add(pokemonUser_);
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPk_ = new PkTrainer();
        allyPk_.setName(PIKACHU);
        allyPk_.setItem(NULL_REF);
        allyPk_.setAbility(METEO);
        allyPk_.setGender(Gender.NO_GENDER);
        allyPk_.setLevel( 32);
        allyPk_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        allyTeam_.add(allyPk_);
        ally_.setTeam(allyTeam_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        DualFight dual_ = new DualFight();
        dual_.setAlly(ally_);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        TeamPositionList list_ = FightOrder.fightersBelongingToUser(fight_,true);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.fightersBelongingToUser(fight_,false);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
    }
    @Test
    public void fightersBelongingToUserHavingBeaten1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setItem(MULTI_EXP);
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setRemainingHp(Rate.zero());
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        Ints list_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj( 0));
    }

    @Test
    public void fightersBelongingToUserHavingBeaten2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(SEISME,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setItem(MULTI_EXP);
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setRemainingHp(Rate.zero());
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        Ints list_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj( 0));
    }

    @Test
    public void closestFightersAmongList1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = closestFightersAmongList(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), Ints.newList());
        assertEq(0,list_.size());
        list_ = closestFightersAmongList(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), Ints.newList());
        assertEq(0,list_.size());
    }

    @Test
    public void closestFightersAmongList2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = closestFightersAmongList(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), Ints.newList( 0));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = closestFightersAmongList(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), Ints.newList( 0));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void closestFightersAmongList3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = closestFightersAmongList(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), Ints.newList(0,1));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = closestFightersAmongList(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), Ints.newList(0,1));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        fight_.getTemp().setMustFront(false);
        list_ = closestFightersAmongList(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), Ints.newList(0,1));
        assertEq(2,list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        list_ = closestFightersAmongList(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), Ints.newList(0,1));
        assertEq(2,list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void closestFightersAmongList4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = closestFightersAmongList(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), Ints.newList(0,1));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = closestFightersAmongList(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), Ints.newList(0,1));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void closestFightersAmongList5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = closestFightersAmongList(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), Ints.newList(0,1,2));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        list_ = closestFightersAmongList(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), Ints.newList(0,1,2));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        list_ = closestFightersAmongList(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), Ints.newList(2,1,0));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        list_ = closestFightersAmongList(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), Ints.newList(2,1,0));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        list_ = closestFightersAmongList(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), Ints.newList(2,0,1));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        list_ = closestFightersAmongList(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), Ints.newList(2,0,1));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        list_ = closestFightersAmongList(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), Ints.newList(0,2,1));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        list_ = closestFightersAmongList(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), Ints.newList(0,2,1));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        list_ = closestFightersAmongList(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), Ints.newList(0,2));
        assertEq(2,list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_TWO)));
        list_ = closestFightersAmongList(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), Ints.newList(0,2));
        assertEq(2,list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO)));
    }

    @Test
    public void closestFightersAmongList6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 4);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = closestFightersAmongList(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), Ints.newList(0,1,2,3));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = closestFightersAmongList(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), Ints.newList(3,2,1,0));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = closestFightersAmongList(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), Ints.newList( 0));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void closestFightersAmongList7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = closestFightersAmongList(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), Ints.newList());
        assertEq(0,list_.size());
    }

    @Test
    public void closestFoeFighter1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 4);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = FightOrder.closestFoeFighter(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        assertEq(1,list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void closestFigthersTeam1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(SECHERESSE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = closestFigthersTeam(diff_, 0, Fight.CST_PLAYER, fight_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        list_ = closestFigthersTeam(diff_, 0, Fight.CST_FOE, fight_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        fight_.getTemp().setMustFront(false);
        list_ = closestFigthersTeam(diff_, 0, Fight.CST_PLAYER, fight_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        list_ = closestFigthersTeam(diff_, 0, Fight.CST_FOE, fight_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = closestFigthersTeam(diff_, 1, Fight.CST_PLAYER, fight_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        list_ = closestFigthersTeam(diff_, 1, Fight.CST_FOE, fight_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void closestFigthersTeam2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(SECHERESSE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = closestFigthersTeam(diff_, 0, Fight.CST_PLAYER, fight_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        list_ = closestFigthersTeam(diff_, 0, Fight.CST_FOE, fight_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void closestFigthersTeam3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = closestFigthersTeam(diff_, 0, Fight.CST_PLAYER, fight_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        list_ = closestFigthersTeam(diff_, 0, Fight.CST_FOE, fight_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        list_ = closestFigthersTeam(diff_, 1, Fight.CST_PLAYER, fight_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO)));
        list_ = closestFigthersTeam(diff_, 1, Fight.CST_FOE, fight_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_TWO)));
        fight_.getTemp().setMustFront(false);
        list_ = closestFigthersTeam(diff_, 0, Fight.CST_PLAYER, fight_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO)));
        list_ = closestFigthersTeam(diff_, 0, Fight.CST_FOE, fight_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_TWO)));
        list_ = closestFigthersTeam(diff_, 1, Fight.CST_PLAYER, fight_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO)));
        list_ = closestFigthersTeam(diff_, 1, Fight.CST_FOE, fight_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_TWO)));
        fight_.getTemp().setMustFront(true);
        diff_.setEnabledClosing(false);
        list_ = closestFigthersTeam(diff_, 0, Fight.CST_PLAYER, fight_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO)));
        list_ = closestFigthersTeam(diff_, 0, Fight.CST_FOE, fight_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_TWO)));
        list_ = closestFigthersTeam(diff_, 0, Fight.CST_PLAYER, fight_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO)));
        list_ = closestFigthersTeam(diff_, 0, Fight.CST_FOE, fight_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_TWO)));
    }

    @Test
    public void closestFigthersSameTeam1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = FightOrder.closestFigthersSameTeam(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        list_ = FightOrder.closestFigthersSameTeam(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void closestFigthersFoeTeam1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = FightOrder.closestFigthersFoeTeam(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        list_ = FightOrder.closestFigthersFoeTeam(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void closestFigthersFoeTeam2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = FightOrder.closestFigthersFoeTeam(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), diff_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void closestFigthers1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        TeamPositionList list_ = FightOrder.closestFigthers(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        list_ = FightOrder.closestFigthers(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void getPointViewChangementType1Test() {
        DataBase data_ = initDb();
        PointViewChangementType pt_ = FightOrder.getPointViewChangementType(OEIL_MIRACLE,data_);
        assertEq(PointViewChangementType.NOTHING, pt_);
        pt_ = FightOrder.getPointViewChangementType(PAR_ICI,data_);
        assertEq(PointViewChangementType.ATTRACT_DAMAGES_MOVES, pt_);
        pt_ = FightOrder.getPointViewChangementType(SAISIE,data_);
        assertEq(PointViewChangementType.THIEF_BONUSES, pt_);
    }

    private static Fight chosenTargets(int _mult, Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME,_diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SEISME,  10);
        moves_.put(SOIN,  10);
        moves_.put(COUD_KRANE,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_, _data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_, _data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_, _data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PAR_ICI));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight(_mult);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data);
        return fight_;
    }

    @Test
    public void chosenTargets1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 1, diff_, data_);
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.NOTHING, diff_, data_);
        assertEq(0, list_.size());
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.LANCEUR, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ALLIES, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.TOUS_ADV, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.PSEUDO_GLOBALE, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ADJ_ADV, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ADJ_MULT, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.GLOBALE, diff_, data_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO));
//        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ANY_FOE, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.AUTRE_UNIQ, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.UNIQUE_IMPORTE, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), TargetChoice.AUTRE_UNIQ, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(0, list_.size());
    }

    @Test
    public void chosenTargets2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 2, diff_, data_);
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.NOTHING, diff_, data_);
        assertEq(0, list_.size());
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.LANCEUR, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ALLIES, diff_, data_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.TOUS_ADV, diff_, data_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.PSEUDO_GLOBALE, diff_, data_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ADJ_ADV, diff_, data_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ADJ_MULT, diff_, data_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.GLOBALE, diff_, data_);
        assertEq(4, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
//        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(SOIN,tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SOIN);
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ALLIE, diff_, data_);
        assertEq(0, list_.size());
//        assertEq(1, list_.size());
//        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.UNIQUE_IMPORTE, diff_, data_);
        assertEq(0, list_.size());
//        assertEq(1, list_.size());
//        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setGroundPlace(Fighter.BACK);
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ALLIE, diff_, data_);
        assertEq(0, list_.size());
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.UNIQUE_IMPORTE, diff_, data_);
        assertEq(0, list_.size());
//        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(SEISME,tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ANY_FOE, diff_, data_);
        assertEq(0, list_.size());
//        assertEq(1, list_.size());
//        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.AUTRE_UNIQ, diff_, data_);
        assertEq(0, list_.size());
//        assertEq(1, list_.size());
//        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.UNIQUE_IMPORTE, diff_, data_);
        assertEq(0, list_.size());
//        assertEq(1, list_.size());
//        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
//        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(PAR_ICI,tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(PAR_ICI);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).choisirAttaqueFin();
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSuccessfulMove(true);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO));
//        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ANY_FOE, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.AUTRE_UNIQ, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.UNIQUE_IMPORTE, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ONE));
//        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ANY_FOE, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.AUTRE_UNIQ, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.UNIQUE_IMPORTE, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setGroundPlace(Fighter.BACK);
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ANY_FOE, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.AUTRE_UNIQ, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.UNIQUE_IMPORTE, diff_, data_);
//        assertEq(0, list_.size());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void chosenTargets3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 3, diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).setFirstChosenMoveTarget(JACKPOT,tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).setFirstChosenMoveTarget(JACKPOT,tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
//        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(PAR_ICI,tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(PAR_ICI);
//        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).setFirstChosenMoveTarget(BROUHAHA,tc(KEY_FOE, POKEMON_TARGET_TWO));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).successUsingMove();
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).successUsingMove();
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).successUsingMove();
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), TargetChoice.AUTRE_UNIQ, diff_, data_);
        assertEq(0, list_.size());
//        assertEq(1, list_.size());
//        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void chosenTargets4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 3, diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).setFirstChosenMoveTarget(JACKPOT,tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
//        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).setFirstChosenMoveTarget(PAR_ICI,tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).setFirstChosenMove(PAR_ICI);
//        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(PAR_ICI,tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(PAR_ICI);
//        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).setFirstChosenMoveTarget(BROUHAHA,tc(KEY_FOE, POKEMON_TARGET_TWO));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).successUsingMove();
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).successUsingMove();
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).successUsingMove();
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), TargetChoice.AUTRE_UNIQ, diff_, data_);
        assertEq(0, list_.size());
//        assertEq(1, list_.size());
//        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_TWO)));
    }

    @Test
    public void chosenTargets5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 3, diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).setFirstChosenMoveTarget(JACKPOT,tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
//        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).setFirstChosenMoveTarget(PAR_ICI,tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).setFirstChosenMove(PAR_ICI);
//        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(PAR_ICI,tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(PAR_ICI);
//        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMoveTarget(BROUHAHA,tc(KEY_FOE, POKEMON_TARGET_ONE));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).successUsingMove();
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).successUsingMove();
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).successUsingMove();
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), TargetChoice.AUTRE_UNIQ, diff_, data_);
        assertEq(0, list_.size());
//        assertEq(2, list_.size());
//        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
//        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_TWO)));
    }

    @Test
    public void chosenTargets6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 2, diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ABIME,tc(KEY_FOE, POKEMON_TARGET_ONE));
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ANY_FOE, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void chosenTargets7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 2, diff_, data_);
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ALLIE, diff_, data_);
        assertEq(0, list_.size());
        list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.AUTRE_UNIQ, diff_, data_);
        assertEq(0, list_.size());
    }

    @Test
    public void chosenTargets8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 1, diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ABIME,tc(KEY_FOE, POKEMON_TARGET_ONE));
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.AUTRE_UNIQ, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void chosenTargets9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 2, diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(INTERVERSION,tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ALLIE, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void chosenTargets10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 1, diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(INTERVERSION,tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.ALLIE, diff_, data_);
        assertEq(0, list_.size());
    }

    @Test
    public void chosenTargets11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 2, diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ACUPRESSION,tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.UNIQUE_IMPORTE, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void chosenTargets12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 1, diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ACUPRESSION,tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.UNIQUE_IMPORTE, diff_, data_);
        assertEq(0, list_.size());
    }

    @Test
    public void chosenTargets13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 2, diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ONE));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SAISIE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).choisirAttaqueFin();
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSuccessfulMove(true);
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.AUTRE_UNIQ, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
    }

    @Test
    public void chosenTargets14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = chosenTargets( 2, diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ONE));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(PAR_ICI);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).choisirAttaqueFin();
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSuccessfulMove(true);
        TeamPositionList list_ = FightOrder.chosenTargets(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TargetChoice.AUTRE_UNIQ, diff_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void targetsEffect() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        StringMap<Integer> movs_ = new StringMap<Integer>();
        movs_.addEntry(PISTOLET_A_O,5);
        Fight fight_ = calculateCatchingRate1(data_,diff_, movs_,PIKACHU,1);
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setCaught(true);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(BATAILLE,tc(KEY_FOE, POKEMON_TARGET_ZERO));
        assertEq(0, FightOrder.targetsEffect(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), data_.getMove(BATAILLE).getEffet(0), diff_, data_).size());
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(BATAILLE,tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        assertEq(1, FightOrder.fightersHavingToAct(fight_,false, data_).size());
    }

    @Test
    public void targetsEffect1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SEISME,  10);
        moves_.put(SOIN,  10);
        moves_.put(COUD_KRANE,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PAR_ICI));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        String finalMove_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getFinalChosenMove();
        MoveData move_ = data_.getMove(finalMove_);
        TeamPositionList list_ = FightOrder.targetsEffect(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), move_.getEffet(move_.indexOfPrimaryEffect()), diff_, data_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertTrue(list_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_TWO)));
    }

    @Test
    public void nbBackPartners1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SEISME,  10);
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setRemainingHp(Rate.zero());
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(A_LA_QUEUE, APRES_VOUS, SEISME, BROUHAHA));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        assertEq(1, FightOrder.nbBackPartners(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void nbFrontPartners1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SEISME,  10);
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setRemainingHp(Rate.zero());
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(A_LA_QUEUE, APRES_VOUS, SEISME, BROUHAHA));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        assertEq(1, FightOrder.nbFrontPartners(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void notKoFrontFightersBelongingToUser1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SEISME,  10);
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(A_LA_QUEUE, APRES_VOUS, SEISME, BROUHAHA));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        TeamPositionList list_ = FightOrder.notKoFrontFightersBelongingToUser(fight_, true);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void notKoBackFightersBelongingToUser1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SEISME,  10);
        moves_.put(A_LA_QUEUE,  10);
        moves_.put(APRES_VOUS,  10);
        moves_.put(BROUHAHA,  10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(A_LA_QUEUE, APRES_VOUS, SEISME, BROUHAHA));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        TeamPositionList list_ = FightOrder.notKoBackFightersBelongingToUser(fight_, true);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO)));
    }

    @Test
    public void sortedFightersAmongListEndRound1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SIPHON,  10);
        moves_.put(DEMI_TOUR,  10);
        Fight fight_ = sortedFightersAmongListEndRound1(data_, diff_, moves_);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        TeamPositionList sorted_ = FightOrder.sortedFightersAmongListEndRound(fight_, false, data_);
        assertEq(3, sorted_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), sorted_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), sorted_.get(1));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), sorted_.get(2));
        //893/100
        //1013/100
        //46/5
    }

    @Test
    public void sortedFightersAmongListEndRound2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SIPHON,  10);
        moves_.put(DEMI_TOUR,  10);
        Fight fight_ = sortedFightersAmongListEndRound1(data_, diff_, moves_);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        TeamPositionList sorted_ = FightOrder.sortedFightersAmongListEndRound(fight_, true, data_);
        assertEq(3, sorted_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), sorted_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), sorted_.get(1));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), sorted_.get(2));
        //893/100
        //1013/100
        //46/5
    }

    @Test
    public void sortedFightersAmongListEndRound3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SIPHON,  10);
        moves_.put(DEMI_TOUR,  10);
        Fight fight_ = sortedFightersAmongListEndRound1(data_, diff_, moves_);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.enableGlobalMove(DISTORSION);
        TeamPositionList sorted_ = FightOrder.sortedFightersAmongListEndRound(fight_, false, data_);
        assertEq(3, sorted_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), sorted_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), sorted_.get(1));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), sorted_.get(2));
        //893/100
        //1013/100
        //46/5
    }

    @Test
    public void sortedFightersAmongListEndRound4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SIPHON,  10);
        moves_.put(DEMI_TOUR,  10);
        Fight fight_ = sortedFightersAmongListEndRound1(data_, diff_, moves_);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.enableGlobalMove(DISTORSION);
        TeamPositionList sorted_ = FightOrder.sortedFightersAmongListEndRound(fight_, true, data_);
        assertEq(3, sorted_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), sorted_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), sorted_.get(1));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), sorted_.get(2));
        //893/100
        //1013/100
        //46/5
    }

    @Test
    public void sortedFightersAmongListEndRound5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SIPHON,  10);
        moves_.put(DEMI_TOUR,  10);
        Fight fight_ = sortedFightersAmongListEndRound1(data_, diff_, moves_);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(FREIN);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(ENCENS_PLEIN);
        fighter_.setCurrentAbility(NULL_REF);
        TeamPositionList sorted_ = FightOrder.sortedFightersAmongListEndRound(fight_, false, data_);
        assertEq(3, sorted_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), sorted_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), sorted_.get(1));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), sorted_.get(2));
        //893/100
        //1013/100
        //46/5
    }

    @Test
    public void sortedFightersAmongListEndRound6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SIPHON,  10);
        moves_.put(DEMI_TOUR,  10);
        Fight fight_ = sortedFightersAmongListEndRound1(data_, diff_, moves_);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(FREIN);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(ENCENS_PLEIN);
        fighter_.setCurrentAbility(FREIN);
        TeamPositionList sorted_ = FightOrder.sortedFightersAmongListEndRound(fight_, false, data_);
        assertEq(3, sorted_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), sorted_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), sorted_.get(1));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), sorted_.get(2));
        //893/100
        //1013/100
        //46/5
    }

    @Test
    public void sortedFightersAmongListEndRound7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SIPHON,  10);
        moves_.put(DEMI_TOUR,  10);
        Fight fight_ = sortedFightersAmongListEndRound1(data_, diff_, moves_);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.backUpObject(ENCENS_PLEIN);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(ENCENS_PLEIN);
        fighter_.setCurrentAbility(FREIN);
        TeamPositionList sorted_ = FightOrder.sortedFightersAmongListEndRound(fight_, false, data_);
        assertEq(3, sorted_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), sorted_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), sorted_.get(1));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), sorted_.get(2));
        //893/100
        //1013/100
        //46/5
    }

    @Test
    public void sortedFightersAmongListEndRound8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SIPHON,  10);
        moves_.put(DEMI_TOUR,  10);
        Fight fight_ = sortedFightersAmongListEndRound1(data_, diff_, moves_);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.backUpObject(ENCENS_PLEIN);
        fighter_.setCurrentAbility(FREIN);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(FREIN);
        TeamPositionList sorted_ = FightOrder.sortedFightersAmongListEndRound(fight_, false, data_);
        assertEq(3, sorted_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), sorted_.get(0));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), sorted_.get(1));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), sorted_.get(2));
        //893/100
        //1013/100
        //46/5
    }

    @Test
    public void sortedFightersAmongListEndRound9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SIPHON,  10);
        moves_.put(DEMI_TOUR,  10);
        Fight fight_ = sortedFightersAmongListEndRound1(data_, diff_, moves_);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.backUpObject(ENCENS_PLEIN);
        fighter_.setCurrentAbility(FREIN);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(ENCENS_PLEIN);
        fighter_.setCurrentAbility(FREIN);
        TeamPositionList sorted_ = FightOrder.sortedFightersAmongListEndRound(fight_, false, data_);
        assertEq(3, sorted_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), sorted_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), sorted_.get(1));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), sorted_.get(2));
        //893/100
        //1013/100
        //46/5
    }

    @Test
    public void sortedFightersAmongListEndRound10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SIPHON,  10);
        moves_.put(DEMI_TOUR,  10);
        Fight fight_ = sortedFightersAmongListEndRound1(data_, diff_, moves_);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_.setCurrentAbility(FREIN);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_.setCurrentAbility(FREIN);
        TeamPositionList sorted_ = FightOrder.sortedFightersAmongListEndRound(fight_, false, data_);
        assertEq(3, sorted_.size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), sorted_.get(0));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), sorted_.get(1));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), sorted_.get(2));
        //893/100
        //1013/100
        //46/5
    }

    @Test
    public void sortedFightersAmongListEndRound11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(SIPHON,  10);
        moves_.put(DEMI_TOUR,  10);
        Fight fight_ = sortedFightersAmongListEndRound2(data_, diff_, moves_);
        fight_.enableGlobalMove(TEMPETESABLE);
        TeamPosition player_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition ally_ = tp(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        TeamPosition foe_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_;
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(DRAGON);
        fighter_.setRemainedHp(new Rate("1"));
        foe_ = tp(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(DRAGON);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(player_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(ROCHE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(DRAGON);
        TeamPositionList sorted_ = FightOrder.sortedFightersAmongListEndRound(fight_, false, data_);
        assertEq(4, sorted_.size());
        assertTrue(sorted_.containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO)));
        assertTrue(sorted_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertTrue(sorted_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), sorted_.get(3));
    }

    private Fight sortedFightersAmongListEndRound2(DataBase _data, Difficulty _diff, StringMap<Integer> _moves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data,  3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data,  4, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer( 3, new StringList(JACKPOT)));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer( 3, new StringList(DETECTION)));
        foeTeam_.add(pkTrainer( 3, new StringList(DETECTION)));
        return sortedFightersAmongListEndRoundInit(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight sortedFightersAmongListEndRound1(DataBase _data, Difficulty _diff, StringMap<Integer> _moves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data,  3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data,  4, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer( 3, new StringList(JACKPOT)));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer( 3, new StringList(DETECTION)));
        return sortedFightersAmongListEndRoundInit(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private static Fight sortedFightersAmongListEndRoundInit(Difficulty _diff, DataBase _data, Player _player, CustList<PkTrainer> _allyTeam, CustList<PkTrainer> _foeTeam) {
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        ally_.setTeam(_allyTeam);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(_foeTeam);
        trainer_.setReward( 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, _player, _diff, dual_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    private static PokemonPlayer pkPlayer(Difficulty _diff, DataBase _data, int _level, StringMap<Integer> _moves) {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel(_level);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_, _data, _moves);
        lasPk_.setUsedBallCatching(SUPER_BALL);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data);
        return lasPk_;
    }

    private static PkTrainer pkTrainer(int _level, StringList _moves) {
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        foePokemon_.setMoves(_moves);
        return foePokemon_;
    }

    private TeamPositionList closestFigthersTeam(Difficulty _diff, int _i, int _cst, Fight _fight) {
        Team team_ = _fight.getTeams().getVal(_cst);
        return FightOrder.closestFigthersTeam(_fight, team_.getMembers().getVal( _i), _cst, team_, _diff);
    }

    private TeamPositionList closestFightersAmongList(Fight _fight, TeamPosition _tp, Ints _ls) {
        return FightOrder.closestFoeFightersAmongList(_fight,Fight.foe(_tp.getTeam()), FightOrder.fighters(_fight,Fight.foe(_tp.getTeam()),_ls), _fight.getFighter(_tp));
    }

    private TeamPositionList randomFigtherHavingToAct(int _ind, DataBase _data, Fight _fight, TeamPositionList _list) {
        return FightOrder.randomFigtherHavingToAct(_fight, _list, new UsedItemForBattle((ItemForBattle) _fight.getFighter(_list.get(_ind)).ficheObjet(_data),_ind,_list.get(_ind)), _data);
    }

    private Fight calculateCatchingRate1(DataBase _data, Difficulty _diff, StringMap<Integer> _moves, String _name, int _level) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data,  3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data,  4, _moves));
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( _level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, foePokemon_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }
}
