package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import org.junit.Before;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.game.fight.actions.AbstractAction;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.animations.AnimationHealing;
import aiki.game.fight.animations.AnimationSwitch;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.util.MoveTarget;
import aiki.game.fight.util.NbEffectFighterCoords;
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
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.util.CustList;
import code.util.EqList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class FightRoundTest extends InitializationDataBase {

    private static final String PIKA = "PIKA";

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void initRound1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(GRELOT_ZEN);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        Team userTeam_ = fight_.getUserTeam();
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(POURSUITE, POKEMON_FOE_TARGET_ZERO);
        Team trainerTeam_ = fight_.getFoeTeam();
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        //assertTrue(fight_.getBeginRound());
        FightRound.initRound(fight_);
        assertEq(0, userTeam_.getSuccessfulMovesRound().size());
        assertEq(0, userTeam_.getNbUsesMovesRound().getVal(AIRE_DE_FEU));
        assertEq(0, userTeam_.getNbUsesMovesRound().getVal(AIRE_D_EAU));
        assertEq(0, userTeam_.getNbUsesMovesRound().getVal(AIRE_D_HERBE));
        assertEq(0, userTeam_.getNbKoPreviousRound());
        assertEq(0, userTeam_.getNbKoRound());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(!fighter_.isActed());
        assertEq(POURSUITE, fighter_.getFinalChosenMove());
        assertEq(0, trainerTeam_.getSuccessfulMovesRound().size());
        assertEq(0, trainerTeam_.getNbUsesMovesRound().getVal(AIRE_DE_FEU));
        assertEq(0, trainerTeam_.getNbUsesMovesRound().getVal(AIRE_D_EAU));
        assertEq(0, trainerTeam_.getNbUsesMovesRound().getVal(AIRE_D_HERBE));
        assertEq(0, trainerTeam_.getNbKoPreviousRound());
        assertEq(0, trainerTeam_.getNbKoRound());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(JACKPOT, fighter_.getFinalChosenMove());
        assertTrue(!fighter_.isActed());
        //assertTrue(!fight_.getBeginRound());
    }

    private Fight autoDamage(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(A_LA_QUEUE, (short) 10);
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(SEISME, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void autoDamage1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_);
        FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        assertEq(new Rate("12724889/136625"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void autoDamage2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_);
        FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void autoDamage3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void autoDamage4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void autoDamage5Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(POUDRE_ATTAQUE);
        FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void autoDamage1SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_);
        fight_.setSimulation(true);
        FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight disableRandomlyStatus(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(A_LA_QUEUE, (short) 10);
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(SEISME, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void disableRandomlyStatus1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = disableRandomlyStatus(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(POISON_GRAVE);
        MonteCarloBoolean law_ = new MonteCarloBoolean();
        law_.addQuickEvent(true, LgInt.one());
        FightRound.disableRandomlyStatus(fight_,law_, POKEMON_PLAYER_FIGHTER_ZERO, POISON_GRAVE, true, data);
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
        FightRound.disableRandomlyStatus(fight_,law_, POKEMON_PLAYER_FIGHTER_ZERO, POISON_GRAVE, false, data);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
    }

    @Test
    public void disableRandomlyStatus2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = disableRandomlyStatus(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(POISON_GRAVE);
        MonteCarloBoolean law_ = new MonteCarloBoolean();
        law_.addQuickEvent(false, LgInt.one());
        FightRound.disableRandomlyStatus(fight_,law_, POKEMON_PLAYER_FIGHTER_ZERO, POISON_GRAVE, true, data);
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
        FightRound.disableRandomlyStatus(fight_,law_, POKEMON_PLAYER_FIGHTER_ZERO, POISON_GRAVE, false, data);
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
    }

    @Test
    public void disableRandomlyStatus3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = disableRandomlyStatus(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(POISON_GRAVE);
        MonteCarloBoolean law_ = new MonteCarloBoolean();
        FightRound.disableRandomlyStatus(fight_,law_, POKEMON_PLAYER_FIGHTER_ZERO, POISON_GRAVE, true, data);
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
    }

    @Test
    public void disableRandomlyStatus1SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = disableRandomlyStatus(diff_);
        fight_.setSimulation(true);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(POISON_GRAVE);
        MonteCarloBoolean law_ = new MonteCarloBoolean();
        law_.addQuickEvent(true, LgInt.one());
        law_.addQuickEvent(false, LgInt.one());
        FightRound.disableRandomlyStatus(fight_,law_, POKEMON_PLAYER_FIGHTER_ZERO, POISON_GRAVE, false, data);
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
    }

    @Test
    public void disableRandomlyStatus2SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = disableRandomlyStatus(diff_);
        fight_.setSimulation(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(POISON_GRAVE);
        MonteCarloBoolean law_ = new MonteCarloBoolean();
        law_.addQuickEvent(true, LgInt.one());
        law_.addQuickEvent(false, LgInt.one());
        FightRound.disableRandomlyStatus(fight_,law_, POKEMON_FOE_FIGHTER_ZERO, POISON_GRAVE, false, data);
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
    }

    private Fight effectBeginRoundAttack(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ROUE_DE_FEU, (short) 10);
        moves_.put(RONFLEMENT, (short) 10);
        moves_.put(BLABLA_DODO, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectBeginRoundAttack1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(TROUILLE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, TROUILLE, diff_, data);
        assertTrue(!fight_.getLettingUserAttackWithStatus());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(TROUILLE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(PEUR);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, PEUR, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(TROUILLE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_SANS_DOMMAGE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, LONGUE_CONFUSION_SANS_DOMMAGE, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(LONGUE_CONFUSION_SANS_DOMMAGE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, LONGUE_CONFUSION_DOMMAGE, diff_, data);
        assertTrue(!fight_.getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack5Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, LONGUE_CONFUSION_DOMMAGE, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack6Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO,AMOUR_FOU);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, AMOUR_FOU, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(AMOUR_FOU, POKEMON_FOE_FIGHTER_ZERO)));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack7Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(PEUR);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).incrementRoundsStatus(PEUR);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, PEUR, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(PEUR));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack8Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, LONGUE_CONFUSION_DOMMAGE, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack9Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(ERE_GEL);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, ERE_GEL, diff_, data);
        assertTrue(!fight_.getLettingUserAttackWithStatus());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(ERE_GEL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack10Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(FEU_GEL);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, FEU_GEL, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(FEU_GEL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack11Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO,AMOUR_MOU);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, AMOUR_MOU, diff_, data);
        assertTrue(!fight_.getLettingUserAttackWithStatus());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(AMOUR_MOU, POKEMON_FOE_FIGHTER_ZERO)));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack12Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(PARALYSIE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, PARALYSIE, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(PARALYSIE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack13Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(PARALYSIE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, PARALYSIE, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(PARALYSIE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack14Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(PARALYSIE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, PARALYSIE, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(PARALYSIE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack15Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(ERE_GEL);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, ERE_GEL, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(ERE_GEL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack16Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(PARALYSIE_FORTE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, PARALYSIE_FORTE, diff_, data);
        assertTrue(!fight_.getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(PARALYSIE_FORTE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack17Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(PARALYSIE_FORTE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, PARALYSIE_FORTE, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(PARALYSIE_FORTE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack18Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(PARALYSIE_FORTE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, PARALYSIE_FORTE, diff_, data);
        assertTrue(!fight_.getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(PARALYSIE_FORTE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack19Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SOMMEIL, diff_, data);
        assertTrue(!fight_.getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack20Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(RONFLEMENT, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SOMMEIL, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack21Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SOMMEIL, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack22Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        //assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_FOE_FIGHTER_ZERO, LONGUE_CONFUSION_DOMMAGE, diff_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack23Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ZERO, PRISE_DE_TETE);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        //assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_FOE_FIGHTER_ZERO, PRISE_DE_TETE, diff_, data);
        assertEq(2, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(PRISE_DE_TETE, POKEMON_PLAYER_FIGHTER_ZERO)));
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("42756/2675"), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getRemainingHp());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack24Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ZERO, PRISE_DE_TETE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).incrementPseudoStatutCombattant(POKEMON_PLAYER_FIGHTER_ZERO, PRISE_DE_TETE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).incrementPseudoStatutCombattant(POKEMON_PLAYER_FIGHTER_ZERO, PRISE_DE_TETE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).incrementPseudoStatutCombattant(POKEMON_PLAYER_FIGHTER_ZERO, PRISE_DE_TETE);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatusRelat().put(new MoveTeamPosition(PRISE_DE_TETE, POKEMON_PLAYER_FIGHTER_ZERO), (short) 4);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        //assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_FOE_FIGHTER_ZERO, PRISE_DE_TETE, diff_, data);
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(PRISE_DE_TETE, POKEMON_PLAYER_FIGHTER_ZERO)));
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("42756/2675"), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getRemainingHp());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack25Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ZERO, PRISE_DE_TETE);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        //assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_FOE_FIGHTER_ZERO, PRISE_DE_TETE, diff_, data);
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(PRISE_DE_TETE, POKEMON_PLAYER_FIGHTER_ZERO)));
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("92/5"), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getRemainingHp());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack1SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.setSimulation(true);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ZERO, COUP_DE_BEC);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        //assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).incrementPseudoStatutCombattant(POKEMON_PLAYER_FIGHTER_ZERO, COUP_DE_BEC);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).incrementPseudoStatutCombattant(POKEMON_PLAYER_FIGHTER_ZERO, COUP_DE_BEC);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatusRelat().put(new MoveTeamPosition(COUP_DE_BEC, POKEMON_PLAYER_FIGHTER_ZERO), (short)3);
        FightRound.initRound(fight_);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_FOE_FIGHTER_ZERO, COUP_DE_BEC, diff_, data);
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(COUP_DE_BEC, POKEMON_PLAYER_FIGHTER_ZERO)));
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("92/5"), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getRemainingHp());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack2SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.setSimulation(true);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ZERO, COUP_DE_BEC);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        //assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, COUP_DE_BEC);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).incrementPseudoStatutCombattant(POKEMON_FOE_FIGHTER_ZERO, COUP_DE_BEC);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).incrementPseudoStatutCombattant(POKEMON_FOE_FIGHTER_ZERO, COUP_DE_BEC);
//        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusRelat().put(new MoveTeamPosition(COUP_DE_BEC, POKEMON_FOE_FIGHTER_ZERO), (short)3);
        FightRound.initRound(fight_);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, COUP_DE_BEC, diff_, data);
        assertEq(4, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(COUP_DE_BEC, POKEMON_FOE_FIGHTER_ZERO)));
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("2137186/27325"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack3SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.setSimulation(true);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_SANS_DOMMAGE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, LONGUE_CONFUSION_SANS_DOMMAGE, diff_, data);
        assertTrue(!fight_.getLettingUserAttackWithStatus());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(LONGUE_CONFUSION_SANS_DOMMAGE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack4SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.setSimulation(true);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        //assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_FOE_FIGHTER_ZERO, LONGUE_CONFUSION_DOMMAGE, diff_, data);
        assertTrue(!FightKo.endedFight(fight_, diff_));
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack5SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.setSimulation(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_SANS_DOMMAGE);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_FOE_FIGHTER_ZERO, LONGUE_CONFUSION_SANS_DOMMAGE, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatusNbRound(LONGUE_CONFUSION_SANS_DOMMAGE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack6SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.setSimulation(true);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO,AMOUR_FOU);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, AMOUR_FOU, diff_, data);
        assertTrue(!fight_.getLettingUserAttackWithStatus());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(AMOUR_FOU, POKEMON_FOE_FIGHTER_ZERO)));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack7SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.setSimulation(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ZERO,AMOUR_FOU);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_FOE_FIGHTER_ZERO, AMOUR_FOU, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(AMOUR_FOU, POKEMON_PLAYER_FIGHTER_ZERO)));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack8SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_);
        fight_.setSimulation(true);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        //assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, LONGUE_CONFUSION_DOMMAGE, diff_, data);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(!fight_.getAcceptableChoices());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
    }

    private Fight statusBeginRoundAttack(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ROUE_DE_FEU, (short) 10);
        moves_.put(RONFLEMENT, (short) 10);
        moves_.put(BLABLA_DODO, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void statusBeginRoundAttack1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = statusBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(RONFLEMENT, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.setKeepStatus(true);
        FightRound.statusBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SOMMEIL, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertTrue(fight_.isKeepStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void statusBeginRoundAttack2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = statusBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(BRULURE);
        fight_.setKeepStatus(true);
        FightRound.statusBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, BRULURE, diff_, data);
        assertTrue(fight_.getLettingUserAttackWithStatus());
        assertTrue(fight_.isKeepStatus());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(BRULURE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void statusBeginRoundAttack3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = statusBeginRoundAttack(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL_REPOS);
        fight_.setKeepStatus(true);
        FightRound.statusBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SOMMEIL_REPOS, diff_, data);
        assertTrue(!fight_.getLettingUserAttackWithStatus());
        assertTrue(!fight_.isKeepStatus());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL_REPOS));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void statusBeginRoundAttack4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = statusBeginRoundAttack(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        //assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.setKeepStatus(true);
        FightRound.statusBeginRoundAttack(fight_,POKEMON_FOE_FIGHTER_ZERO, LONGUE_CONFUSION_DOMMAGE, diff_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
    }

    @Test
    public void statusBeginRoundAttack1SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = statusBeginRoundAttack(diff_);
        fight_.setSimulation(true);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data);
        //assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.setKeepStatus(true);
        FightRound.statusBeginRoundAttack(fight_,POKEMON_PLAYER_FIGHTER_ZERO, LONGUE_CONFUSION_DOMMAGE, diff_, data);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(!fight_.getAcceptableChoices());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
    }

    @Test
    public void takers1Test() {
        Difficulty diff_ = new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(MIMIQUE, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,SAISIE,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(SAISIE,POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMoveTarget(PISTOLET_A_O,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        EqList<TeamPosition> takers_ = FightRound.takers(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data);
        assertEq(1, takers_.size());
        assertTrue(takers_.containsObj(POKEMON_FOE_FIGHTER_ZERO));
    }

    @Test
    public void takers2Test() {
        Difficulty diff_ = new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(MIMIQUE, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,SAISIE,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(SAISIE,POKEMON_PLAYER_TARGET_ONE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(PAR_ICI);
        FightRound.initRound(fight_);
        EqList<TeamPosition> takers_ = FightRound.takers(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data);
        assertEq(0, takers_.size());
    }

    @Test
    public void takers3Test() {
        Difficulty diff_ = new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(MIMIQUE, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,SAISIE,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PAR_ICI);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(PAR_ICI);
        FightRound.initRound(fight_);
        EqList<TeamPosition> takers_ = FightRound.takers(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data);
        assertEq(0, takers_.size());
    }

    private Fight pressure(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(MIMIQUE, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.setAbility(PRESSION);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.setAbility(TERA_VOLTAGE);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PRESSION);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void pressure1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = pressure(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        FightRound.pressure(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, diff_, data);
        assertEq(10, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).powerPointsMove(SEISME));
    }

    @Test
    public void pressure2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = pressure(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        FightRound.pressure(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, diff_, data);
        assertEq(10, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).powerPointsMove(SEISME));
    }

    @Test
    public void pressure3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = pressure(diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        FightRound.pressure(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ONE, SEISME, diff_, data);
        assertEq(10, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).powerPointsMove(SEISME));
    }

    @Test
    public void pressure4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = pressure(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        FightRound.pressure(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ONE, SEISME, diff_, data);
        assertEq(9, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).powerPointsMove(SEISME));
    }

    @Test
    public void pressure5Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = pressure(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setLastSufferedMove(SEISME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MIMIQUE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightInvoke.processInvokingMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightRound.pressure(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ONE, SEISME, diff_, data);
        assertEq(10, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).powerPointsMove(SEISME));
    }

    private Fight effectWhileFail(Difficulty _diff) {
        _diff.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(GLAS_DE_SOIN, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectWhileFail1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, diff_, data);
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ABRI_CAPE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("46/5"));
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, diff_, data);
        assertEq(new Rate("46/5"), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ABRI_CAPE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("46/5"));
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, diff_, data);
        assertEq(new Rate("46/5"), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ABRI_CAPE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("46/5"));
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, diff_, data);
        assertEq(new Rate("69/5"), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail5Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ABSORB_VOLT);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("46/5"));
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, diff_, data);
        assertEq(new Rate("69/5"), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail6Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ABSORB_VOLT);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("46/5"));
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, diff_, data);
        assertEq(new Rate("92/5"), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail7Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("46/5"));
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, diff_, data);
        assertEq(new Rate("46/5"), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail8Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, diff_, data);
        assertEq(new Rate("5619/400"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail9Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, diff_, data);
        assertEq(-1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(new Rate("1873/100"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail10Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, diff_, data);
        assertEq(new Rate("5619/400"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail11Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, diff_, data);
        assertEq(new Rate("1873/200"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail12Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, diff_, data);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(new Rate("1873/100"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail13Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(new Rate("1"));
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, diff_, data);
        assertEq(new Rate("0"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail14Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setFirstChosenMove(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(new Rate("1"));
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).successUsingMove();
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, ROUE_DE_FEU, diff_, data);
        assertEq(new Rate("1"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }


    @Test
    public void effectWhileFail15Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setFirstChosenMove(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(new Rate("1"));
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).successUsingMove();
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, ROUE_DE_FEU, diff_, data);
        assertEq(new Rate("1"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail16Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setFirstChosenMove(PICO_DEFENSE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(new Rate("1"));
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).successUsingMove();
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, ROUE_DE_FEU, diff_, data);
        assertEq(new Rate("1"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileFail1SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_);
        fight_.setSimulation(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(new Rate("1"));
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, diff_, data);
        assertEq(new Rate("0"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight healPartner() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(GLAS_DE_SOIN, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        return fight_;
    }

    @Test
    public void healPartner1Test() {
        Fight fight_ = healPartner();
        FightRound.healPartner(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, Rate.one(), data);
        assertTrue(!fight_.isEnabledHealingPartner());
    }

    @Test
    public void healPartner2Test() {
        Fight fight_ = healPartner();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ONE, AMOUR);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightRound.healPartner(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, Rate.one(), data);
        assertTrue(fight_.isEnabledHealingPartner());
        assertEq(new Rate("1873/100"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp());
    }

    @Test
    public void healPartner3Test() {
        Fight fight_ = healPartner();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ONE, AMOUR_FOU);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightRound.healPartner(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, Rate.one(), data);
        assertTrue(!fight_.isEnabledHealingPartner());
        assertEq(new Rate("1"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp());
    }

    @Test
    public void healPartner4Test() {
        Fight fight_ = healPartner();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ONE, CAUCHEMAR);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightRound.healPartner(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, Rate.one(), data);
        assertTrue(!fight_.isEnabledHealingPartner());
        assertEq(new Rate("1"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp());
    }
    private Fight useBoostForAccuracy() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(GLAS_DE_SOIN, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void useBoostForAccuracy1Test() {
        Fight fight_ = useBoostForAccuracy();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setUsingItem(false);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        FightRound.useBoostForAccuracy(fight_, thrower_, target_, NULL_REF, false, false, false, data);
        assertTrue(!fighter_.isUsingItem());
        assertTrue(fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, target_)));
    }

    @Test
    public void useBoostForAccuracy2Test() {
        Fight fight_ = useBoostForAccuracy();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setUsingItem(false);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        FightRound.useBoostForAccuracy(fight_, thrower_, target_, NULL_REF, false, false, true, data);
        assertTrue(!fighter_.isUsingItem());
        assertTrue(fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, target_)));
    }

    @Test
    public void useBoostForAccuracy3Test() {
        Fight fight_ = useBoostForAccuracy();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setUsingItem(false);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        FightRound.useBoostForAccuracy(fight_, thrower_, target_, NULL_REF, false, true, true, data);
        assertTrue(fighter_.isUsingItem());
        assertTrue(fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, target_)));
    }

    @Test
    public void useBoostForAccuracy4Test() {
        Fight fight_ = useBoostForAccuracy();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setUsingItem(false);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        FightRound.useBoostForAccuracy(fight_, thrower_, target_, LIRE_ESPRIT, true, false, true, data);
        assertTrue(!fighter_.isUsingItem());
        assertTrue(!fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, target_)));
    }

    @Test
    public void useBoostForAccuracy5Test() {
        Fight fight_ = useBoostForAccuracy();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setUsingItem(false);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        FightRound.useBoostForAccuracy(fight_, thrower_, target_, LIRE_ESPRIT, true, true, true, data);
        assertTrue(!fighter_.isUsingItem());
        assertTrue(!fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, target_)));
    }

    private Fight processAccurracy() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(GLAS_DE_SOIN, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void processAccurracy1Test() {
        Fight fight_ = processAccurracy();
        FightRound.processAccurracy(fight_,ABIME, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data);
        assertTrue(!fight_.isSuccessfulUse());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processAccurracy2Test() {
        Fight fight_ = processAccurracy();
        FightRound.processAccurracy(fight_,TONNERRE, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data);
        assertTrue(fight_.isSuccessfulUse());
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).isUsingItem());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processAccurracy3Test() {
        Fight fight_ = processAccurracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MICLE);
        FightRound.processAccurracy(fight_,BERCEUSE, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data);
        assertTrue(fight_.isSuccessfulUse());
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).isUsingItem());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processAccurracy4Test() {
        Fight fight_ = processAccurracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT,POKEMON_FOE_FIGHTER_ZERO), true);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MICLE);
        FightRound.processAccurracy(fight_,BERCEUSE, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data);
        assertTrue(fight_.isSuccessfulUse());
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT,POKEMON_FOE_FIGHTER_ZERO)));
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).isUsingItem());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processAccurracy5Test() {
        Fight fight_ = processAccurracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MICLE);
        FightRound.processAccurracy(fight_,TONNERRE, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data);
        assertTrue(fight_.isSuccessfulUse());
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).isUsingItem());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processAccurracy6Test() {
        Fight fight_ = processAccurracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT,POKEMON_FOE_FIGHTER_ZERO), true);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        FightRound.processAccurracy(fight_,BERCEUSE, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data);
        assertTrue(fight_.isSuccessfulUse());
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT,POKEMON_FOE_FIGHTER_ZERO)));
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).isUsingItem());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processAccurracy7Test() {
        Fight fight_ = processAccurracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT,POKEMON_FOE_FIGHTER_ZERO), true);
        FightRound.processAccurracy(fight_,TONNERRE, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data);
        assertTrue(fight_.isSuccessfulUse());
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT,POKEMON_FOE_FIGHTER_ZERO)));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processAccurracy1SimulationTest() {
        Fight fight_ = processAccurracy();
        fight_.setSimulation(true);
        FightRound.processAccurracy(fight_,BERCEUSE, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data);
        assertTrue(fight_.isSuccessfulUse());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processAccurracy2SimulationTest() {
        Fight fight_ = processAccurracy();
        fight_.setSimulation(true);
        FightRound.processAccurracy(fight_,BERCEUSE, POKEMON_FOE_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, data);
        assertTrue(fight_.isSuccessfulUse());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight processEffectTargets(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
            PkTrainer allyPokemon_ = new PkTrainer();
            allyPokemon_.setName(TARTARD);
            allyPokemon_.setItem(PLAQUE_DRACO);
            allyPokemon_.setAbility(MULTITYPE);
            allyPokemon_.setGender(Gender.NO_GENDER);
            allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
            allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
            allyTeam_.add(allyPokemon_);
        }
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void processEffectTargets1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertEq(new Rate("92/5"),fight_.getDamageByCurrentUser().getVal(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.affecterPseudoStatut(target_, AMOUR_FOU);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertEq(new Rate("92/5"),fight_.getDamageByCurrentUser().getVal(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.affecterPseudoStatut(target_, AMOUR_MOU);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COUD_BOUE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(VOL);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COUD_BOUE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = SEISME;
        fighter_.setFirstChosenMove(move_);
        fighter_.affecterPseudoStatut(target_, AMOUR);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("1"));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertEq(2, fight_.getDamageByCurrentUser().size());
        assertEq(new Rate("14288/1575"), fight_.getDamageByCurrentUser().getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("14288/1575"), fight_.getDamageByCurrentUser().getVal(POKEMON_FOE_FIGHTER_ONE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COUD_BOUE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        String move_ = SEISME;
        fighter_.setFirstChosenMove(move_);
        fighter_.affecterPseudoStatut(target_, AMOUR);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("1"));
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COUD_BOUE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.activerAttaqueImmu(VOL_MAGNETIK, data);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(PROVOC, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = PROVOC;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.creerClone(new Rate("1/2"));
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fighter_.getEnabledMoves().getVal(move_).isEnabled());
        assertEq(new Rate("46/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ABIME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = ABIME;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertEq(new Rate("92/5"),fight_.getDamageByCurrentUser().getVal(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_ENIGMA);
        fighter_.setRemainedHp(new Rate("18"));
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_ENIGMA);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COUD_BOUE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("28114/1625"),fighter_.getRemainingHp());
        assertEq(new Rate("1786/1625"),fight_.getDamageByCurrentUser().getVal(target_));
        FightRound.processEffectTargets(fight_, thrower_, thrower_, false, IndexConstants.SECOND_INDEX, new Ints(0), diff_, data);
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MUR_DE_FER, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,SAISIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = MUR_DE_FER;
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        String moveTarget_ = SAISIE;
        fighter_.setFirstChosenMoveTarget(moveTarget_, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, target_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        fight_.getSuccessfulEffects().clear();
        EqList<TeamPosition> list_ = FightRound.takers(fight_,thrower_, data);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(target_));
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertEq(2, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(GLAS_DE_SOIN, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,SAISIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = GLAS_DE_SOIN;
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterStatut(SOMMEIL);
        String moveTarget_ = SAISIE;
        fighter_.setFirstChosenMoveTarget(moveTarget_, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, target_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        fight_.getSuccessfulEffects().clear();
        EqList<TeamPosition> list_ = FightRound.takers(fight_,thrower_, data);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(target_));
        fight_.setChangeThrower(true);
        FightRound.processEffectTargets(fight_, thrower_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets16Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(FORCE_NATURE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,SAISIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = FORCE_NATURE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_THREE, AMOUR);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(PRESSION);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightInvoke.processInvokingMove(fight_, thrower_, diff_, data);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        assertEq(2, fight_.getDamageByCurrentUser().size());
        assertEq(new Rate("14288/1575"), fight_.getDamageByCurrentUser().getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("14288/1575"), fight_.getDamageByCurrentUser().getVal(POKEMON_FOE_FIGHTER_ONE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets17Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(PICOTS, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,REFLET_MAGIK);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = PICOTS;
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        String moveTarget_ = REFLET_MAGIK;
        fighter_.setFirstChosenMove(moveTarget_);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, target_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        fight_.getSuccessfulEffects().clear();
        assertEq(PointViewChangementType.MIRROR_AGAINST_THROWER, FightOrder.getPointViewChangementType(moveTarget_, data));
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(move_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets18Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BERCEUSE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,REFLET_MAGIK);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = BERCEUSE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        String moveTarget_ = REFLET_MAGIK;
        fighter_.setFirstChosenMove(moveTarget_);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, target_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getSuccessfulEffects().clear();
        assertEq(PointViewChangementType.MIRROR_AGAINST_THROWER, FightOrder.getPointViewChangementType(moveTarget_, data));
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets19Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BERCEUSE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,REFLET_MAGIK);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = BERCEUSE;
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets20Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COUD_BOUE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = SEISME;
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(VOL);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(!fight_.isEnabledHealingPartner());
        assertEq(2, fight_.getDamageByCurrentUser().size());
        assertEq(new Rate("7144/1575"), fight_.getDamageByCurrentUser().getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("7144/1575"), fight_.getDamageByCurrentUser().getVal(POKEMON_FOE_FIGHTER_ONE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets21Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(POSSESSIF, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,SAISIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = POSSESSIF;
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        String moveTarget_ = SAISIE;
        fighter_.setFirstChosenMoveTarget(moveTarget_, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, target_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        fight_.getSuccessfulEffects().clear();
        EqList<TeamPosition> list_ = FightRound.takers(fight_,thrower_, data);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(target_));
        fight_.setChangeThrower(true);
        FightRound.processEffectTargets(fight_, thrower_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(target_);
        StringList fobiddenMoves_ = fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF, POKEMON_PLAYER_FIGHTER_THREE));
        assertEq(2, fobiddenMoves_.size());
        assertTrue(StringUtil.contains(fobiddenMoves_, PAR_ICI));
        assertTrue(StringUtil.contains(fobiddenMoves_, JACKPOT));
        fobiddenMoves_ = fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF, POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(0, fobiddenMoves_.size());
        assertEq(2, fight_.getSuccessfulEffects().size());
        assertTrue(!fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, POKEMON_PLAYER_FIGHTER_ZERO)));
        assertTrue(fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, POKEMON_PLAYER_FIGHTER_THREE)));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets22Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COUD_BOUE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertEq(1, fight_.getSuccessfulEffects().size());
        assertTrue(fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("1"),fight_.getDamageByCurrentUser().getVal(target_));
        FightRound.processEffectTargets(fight_, thrower_, thrower_, false, IndexConstants.SECOND_INDEX, new Ints(0), diff_, data);
        assertEq(1, fight_.getSuccessfulEffects().size());
        assertTrue(fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets23Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COUD_BOUE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(TOUR_RAPIDE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = TOUR_RAPIDE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(SPECTRE);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.isEnabledHealingPartner());
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertEq(2, fight_.getSuccessfulEffects().size());
        assertTrue(fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        assertTrue(fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, thrower_)));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        FightRound.processEffectTargets(fight_, thrower_, thrower_, false, IndexConstants.SECOND_INDEX, new Ints(0), diff_, data);
        assertEq(3, fight_.getSuccessfulEffects().size());
        assertTrue(fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        assertTrue(fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, thrower_)));
        assertTrue(fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(1, thrower_)));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(LgInt.zero(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
    }

    @Test
    public void processEffectTargets24Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COUD_BOUE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(TOUR_RAPIDE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,ABRI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = TOUR_RAPIDE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(ABRI);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.isEnabledHealingPartner());
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertEq(1, fight_.getSuccessfulEffects().size());
        assertTrue(!fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        FightRound.processEffectTargets(fight_, thrower_, thrower_, false, IndexConstants.SECOND_INDEX, new Ints(0), diff_, data);
        assertEq(1, fight_.getSuccessfulEffects().size());
        assertTrue(!fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
    }

    @Test
    public void processEffectTargets25Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(POSSESSIF, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(GARDE_POUR_SOI);
        fighter_.backUpObject(NULL_REF);
        String move_ = POSSESSIF;
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(SAISIE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        fight_.setChangeThrower(true);
        FightRound.processEffectTargets(fight_, thrower_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.isEnabledHealingPartner());
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets26Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.affecterPseudoStatut(target_, PRISE_DE_TETE);
        fighter_.setRemainedHp(Rate.one());
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("0"),fighter_.getRemainingHp());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets27Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ROUE_DE_FEU, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = ROUE_DE_FEU;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        fighter_.enableCounteringMoves(NUEE_DE_POUDRE);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("0"),fighter_.getRemainingHp());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets28Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COUP_D_MAIN, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fighter_.setFirstChosenMove(COUP_D_MAIN);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        fighter_.enableCounteringMoves(NUEE_DE_POUDRE);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(COUP_D_MAIN));
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets29Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(VAMPIPOING, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fighter_.setFirstChosenMoveTarget(VAMPIPOING, POKEMON_FOE_TARGET_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        fighter_.enableCounteringMoves(NUEE_DE_POUDRE);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        assertEq(2, fight_.getSuccessfulEffects().size());
        assertTrue(fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        assertTrue(fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(VAMPIPOING));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("7144/1625"),fight_.getDamageByCurrentUser().getVal(target_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        FightRound.processEffectTargets(fight_, thrower_, thrower_, false, IndexConstants.SECOND_INDEX, new Ints(0), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("5197/1625"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.affecterPseudoStatut(target_, AMOUR_MOU);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BERCEUSE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = BERCEUSE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BERCEUSE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DRACO_RAGE,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEffectTargets4SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ROUE_DE_FEU, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = processEffectTargets(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = ROUE_DE_FEU;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        fighter_.enableCounteringMoves(NUEE_DE_POUDRE);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("0"),fighter_.getRemainingHp());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fight_.getDamageByCurrentUser().contains(target_));
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight endRoundThrower(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
            PkTrainer allyPokemon_ = new PkTrainer();
            allyPokemon_.setName(TARTARD);
            allyPokemon_.setItem(PLAQUE_DRACO);
            allyPokemon_.setAbility(MULTITYPE);
            allyPokemon_.setGender(Gender.NO_GENDER);
            allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
            allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
            allyTeam_.add(allyPokemon_);
        }
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void endRoundThrower1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = endRoundThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        FightRound.endRoundThrower(fight_, thrower_, move_, false, data);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0,fight_.getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = endRoundThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0,fight_.getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = endRoundThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = DETECTION;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        FightRound.endRoundThrower(fight_, thrower_, move_, false, data);
        assertEq(1, fighter_.getNbUsesMoves().getVal(move_));
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0,fight_.getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(AIRE_D_EAU, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = endRoundThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = AIRE_D_EAU;
        fighter_.setFirstChosenMove(move_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        FightRound.endRoundThrower(fight_, thrower_, move_, false, data);
        assertEq(1, fight_.getUserTeam().getNbUsesMovesRound().getVal(move_));
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0,fight_.getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(FORCE_NATURE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = endRoundThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = FORCE_NATURE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightInvoke.processInvokingMove(fight_, thrower_, diff_, data);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0,fight_.getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(FORCE_NATURE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 1);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = endRoundThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = SEISME;
        fighter_.setFirstChosenMove(move_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        fighter_.usePowerPointsByMove(diff_, move_, (short) 1);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0,fight_.getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(FORCE_NATURE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 1);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = endRoundThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = SEISME;
        fighter_.setFirstChosenMove(move_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        fighter_.usePowerPointsByMove(diff_, move_, (short) 1);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        FightKo.setKoMoveTeams(fight_, thrower_, diff_, data);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0,fight_.getDamageByCurrentUser().size());
        assertEq(LgInt.zero(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(FORCE_NATURE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 1);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = endRoundThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TURBO);
        fighter_.backUpObject(NULL_REF);
        String move_ = SEISME;
        fighter_.setFirstChosenMove(move_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        fighter_.usePowerPointsByMove(diff_, move_, (short) 1);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0,fight_.getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPEED));
    }

    @Test
    public void endRoundThrower9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(FORCE_NATURE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(ABIME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = endRoundThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TURBO);
        fighter_.backUpObject(NULL_REF);
        String move_ = ABIME;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ONE);
        FightRound.initRound(fight_);
        fighter_.usePowerPointsByMove(diff_, move_, (short) 1);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(0, successfulMoves_.size());
        assertEq(0,fight_.getDamageByCurrentUser().size());
        assertEq(LgInt.zero(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPEED));
    }

    @Test
    public void endRoundThrower10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(FORCE_NATURE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(ABIME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = endRoundThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TURBO);
        fighter_.backUpObject(BAIE_MEPO);
        String move_ = ABIME;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ONE);
        FightRound.initRound(fight_);
        fighter_.usePowerPointsByMove(diff_, move_, (short) 10);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(0, successfulMoves_.size());
        assertEq(0,fight_.getDamageByCurrentUser().size());
        assertEq(LgInt.zero(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
        assertEq(10, fighter_.powerPointsMove(move_));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPEED));
    }

    @Test
    public void endRoundThrower11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(FORCE_NATURE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(CASSE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)100,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = endRoundThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TURBO);
        fighter_.backUpObject(BAIE_MICLE);
        String move_ = CASSE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ONE);
        FightRound.initRound(fight_);
        FightRound.processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data);
        FightRound.endRoundThrower(fight_, thrower_, move_, false, data);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertEq(1, fight_.getUserTeam().getNbUsesMoves().getVal(CASSE));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
        assertEq(10, fighter_.powerPointsMove(move_));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPEED));
    }

    private Fight roundThrowerMove(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
            PkTrainer allyPokemon_ = new PkTrainer();
            allyPokemon_.setName(TARTARD);
            allyPokemon_.setItem(PLAQUE_DRACO);
            allyPokemon_.setAbility(MULTITYPE);
            allyPokemon_.setGender(Gender.NO_GENDER);
            allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
            allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
            allyTeam_.add(allyPokemon_);
        }
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void roundThrowerMove1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(ERE_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertEq(1, fighter_.getStatusNbRound(ERE_GEL));
        assertTrue(!fight_.isKeepStatus());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(COUD_BOUE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(FEU_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertEq(0, fighter_.getStatusNbRound(FEU_GEL));
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(9, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("28114/1625"),fighter_.getRemainingHp());
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(PLANNEUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = PLANNEUR;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertEq(1, fighter_.getNbPrepaRound());
        assertTrue(fighter_.isDisappeared());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(PLANNEUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = PLANNEUR;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.incrementRoundBeforeUsingMove();
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertEq(0, fighter_.getNbPrepaRound());
        assertTrue(!fighter_.isDisappeared());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(COUD_BOUE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TURBO);
        fighter_.affecterStatut(FEU_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.setNeedingToRecharge(true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertEq(0, fighter_.getStatusNbRound(FEU_GEL));
        assertTrue(fight_.isKeepStatus());
        assertTrue(!fighter_.isNeedingToRecharge());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(10, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(COUD_BOUE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(SANS_LIMITE);
        fighter_.affecterStatut(FEU_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_.setNeedingToRecharge(true);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertEq(0, fighter_.getStatusNbRound(FEU_GEL));
        assertTrue(fight_.isKeepStatus());
        assertTrue(!fighter_.isNeedingToRecharge());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(9, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("10607/625"),fighter_.getRemainingHp());
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(FORCE_NATURE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = FORCE_NATURE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(MIMIQUE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = MIMIQUE;
        fighter_.setLastSufferedMove(BOOST);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fight_.isKeepStatus());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(!fight_.isInvokedMove());
        assertTrue(!fight_.isSuccessfulInvokation());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(10, fighter_.powerPointsMove(move_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(ROULADE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = ROULADE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setLastUsedMove(move_);
        fighter_.setUsedMoveLastRound(move_);
        //validate fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(NULL_REF, target_), true);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(10, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertEq(new LgInt("2"), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(ROULADE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,DANSE_LUNE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = PAR_ICI;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(ROULADE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,DANSE_LUNE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = DANSE_LUNE;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(fight_.isKeepStatus());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(GLAS_DE_SOIN, (short) 10);
        moves_.put(ROULADE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,SAISIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.affecterStatut(BRULURE);
        String move_ = GLAS_DE_SOIN;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(GLAS_DE_SOIN, (short) 10);
        moves_.put(ROULADE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,SAISIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(SAISIE, POKEMON_PLAYER_TARGET_ZERO);
        fighter_.affecterStatut(BRULURE);
        fighter_ = fight_.getFighter(thrower_);
        String move_ = GLAS_DE_SOIN;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, target_, diff_, data);
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.isSuccessfulMove());
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(fight_.isKeepStatus());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(DEGOMMAGE, (short) 10);
        moves_.put(ROULADE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,SAISIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(BAIE_MEPO);
        String move_ = DEGOMMAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("238956/13375"),fighter_.getRemainingHp());
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(fight_.isKeepStatus());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(DEGOMMAGE, (short) 10);
        moves_.put(ROULADE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,SAISIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(HERBE_MENTAL);
        fighter_.activerAttaque(EMBARGO);
        String move_ = DEGOMMAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterPseudoStatut(thrower_, AMOUR);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("238956/13375"),fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, thrower_)));
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(fight_.isKeepStatus());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove16Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(DEGOMMAGE, (short) 10);
        moves_.put(ROULADE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,SAISIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(HERBE_MENTAL);
        String move_ = DEGOMMAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterPseudoStatut(thrower_, AMOUR);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("238956/13375"),fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, thrower_)));
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(fight_.isKeepStatus());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove17Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PERENITE, (short) 10);
        moves_.put(ROULADE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,SAISIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(SAISIE, POKEMON_PLAYER_TARGET_ZERO);
        fighter_.affecterStatut(BRULURE);
        fighter_ = fight_.getFighter(thrower_);
        String move_ = PERENITE;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove18Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PERENITE_BIS, (short) 10);
        moves_.put(ROULADE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,SAISIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(SAISIE, POKEMON_PLAYER_TARGET_ZERO);
        fighter_.affecterStatut(BRULURE);
        fighter_ = fight_.getFighter(thrower_);
        String move_ = PERENITE_BIS;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getDamageByCurrentUser().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove19Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PROTEEN);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(StringUtil.contains(fighter_.getTypes(), DRAGON));
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove20Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(StringUtil.contains(fighter_.getTypes(), ELECTRIQUE));
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove21Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_KRANE;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(!fighter_.isDisappeared());
        assertTrue(StringUtil.contains(fighter_.getTypes(), ELECTRIQUE));
        assertTrue(fight_.isKeepStatus());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove22Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = POSSESSIF;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(!fighter_.isDisappeared());
        assertTrue(StringUtil.contains(fighter_.getTypes(), ELECTRIQUE));
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(ERE_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertEq(1, fighter_.getStatusNbRound(ERE_GEL));
        assertTrue(!fight_.isKeepStatus());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DRACO_RAGE,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(ERE_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertEq(0, fighter_.getStatusNbRound(ERE_GEL));
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!fight_.getAcceptableChoices());
    }

    /*@Test
    public void roundThrowerMove21Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Map<String,Short> moves_ = new Map<String,Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DRACO_RAGE,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PROTEEN);
        fighter_.affecterStatut(ERE_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertEq(1, fighter_.getTypes().size());
        assertEq(1, fighter_.getTypes().size());
        assertEq(0, fighter_.getStatusNbRound(ERE_GEL));
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!fight_.getAcceptableChoices());
    }*/

    private Fight roundThrowerHealing(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
            PkTrainer allyPokemon_ = new PkTrainer();
            allyPokemon_.setName(TARTARD);
            allyPokemon_.setItem(PLAQUE_DRACO);
            allyPokemon_.setAbility(MULTITYPE);
            allyPokemon_.setGender(Gender.NO_GENDER);
            allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
            allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
            allyTeam_.add(allyPokemon_);
        }
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void roundThrowerHealing1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(DEGOMMAGE, (short) 10);
        moves_.put(ROULADE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT,PAR_ICI,SAISIE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightKo.setKoMoveTeams(fight_, f_, diff_, data);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = CENDRESACREE;
        fighter_.setChosenHealingObject(obj_, data);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.PLAYER, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
    }

    @Test
    public void roundThrowerHealing2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightKo.setKoMoveTeams(fight_, f_, diff_, data);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = EAU_FRAICHE;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(NULL_REF);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.PLAYER, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
    }

    @Test
    public void roundThrowerHealing3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightKo.setKoMoveTeams(fight_, f_, diff_, data);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = EAU_FRAICHE;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(74, fighter_.getHappiness());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.PLAYER, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
    }

    @Test
    public void roundThrowerHealing4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        String obj_ = TOTAL_SOIN;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightKo.setKoMoveTeams(fight_, f_, diff_, data);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = RAPPEL;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1873/200"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.PLAYER, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
    }

    @Test
    public void roundThrowerHealing6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = MAX_ELIXIR;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.usePowerPointsByMove(diff_, DRACO_RAGE, (short) 1);
        fighter_.usePowerPointsByMove(diff_, PICOTS, (short) 1);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(10, fighter_.powerPointsMove(DRACO_RAGE));
        assertEq(10, fighter_.powerPointsMove(PICOTS));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = ELIXIR;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.usePowerPointsByMove(diff_, DRACO_RAGE, (short) 1);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(10, fighter_.powerPointsMove(DRACO_RAGE));
        assertEq(10, fighter_.powerPointsMove(PICOTS));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, DRACO_RAGE, (short) 1);
        String obj_ = HUILE;
        fighter_.setChosenHealingObjectMove(obj_, DRACO_RAGE);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(10, fighter_.powerPointsMove(DRACO_RAGE));
        assertEq(10, fighter_.powerPointsMove(PICOTS));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = HUILE;
        fighter_.setChosenHealingObjectMove(obj_, DRACO_RAGE);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(10, fighter_.powerPointsMove(DRACO_RAGE));
        assertEq(10, fighter_.powerPointsMove(PICOTS));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, DRACO_RAGE, (short) 1);
        String obj_ = BAIE_MEPO;
        fighter_.setChosenHealingObjectMove(obj_, DRACO_RAGE);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(10, fighter_.powerPointsMove(DRACO_RAGE));
        assertEq(10, fighter_.powerPointsMove(PICOTS));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_MEPO;
        fighter_.setChosenHealingObjectMove(obj_, DRACO_RAGE);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(10, fighter_.powerPointsMove(DRACO_RAGE));
        assertEq(10, fighter_.powerPointsMove(PICOTS));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_PITAYE;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_ORAN;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.setRemainedHp(Rate.one());
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("11"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_GOWAV;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.setRemainedHp(Rate.one());
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("2673/800"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_ENIGMA;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(PARALYSIE);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("2273/400"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(1, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing16Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_CERIZ;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(PARALYSIE);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing17Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_CERIZ;
        fight_.getUsedItemsWhileRound().put(obj_, (short) 1);
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(PARALYSIE);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(2, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing18Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = GRAND_RAPPEL;
        fight_.getUsedItemsWhileRound().put(obj_, (short) 1);
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertEq(new Rate("6019/400"), fighter_.getRemainingHp());
        assertEq(2, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing19Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        TeamPosition f_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = CENDRESACREE;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertTrue(!fight_.getUsedItemsWhileRound().contains(obj_));
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.FOE, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
    }

    @Test
    public void roundThrowerHealing20Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition p_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightKo.setKoMoveTeams(fight_, p_, diff_, data);
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = CENDRESACREE;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertTrue(!fight_.getUsedItemsWhileRound().contains(obj_));
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(p_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());

        assertEq(2, fight_.getFirstPositPlayerFighters().size());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));

        assertEq(2, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.PLAYER, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ONE, animSwitch_.getSubstituted());
        assertEq(ARTIKODIN, animSwitch_.getSubstituteName());
        assertEq(3, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    @Test
    public void roundThrowerHealing21Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerHealing(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition p_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightKo.setKoMoveTeams(fight_, p_, diff_, data);
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = CENDRESACREE;
        fighter_.setChosenHealingObject(obj_, data);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data);
        assertTrue(!fight_.getUsedItemsWhileRound().contains(obj_));
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(p_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());

        assertEq(3, fight_.getFirstPositPlayerFighters().size());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));

        assertEq(2, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.PLAYER, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
        assertTrue(animation_.isPlayer());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animSwitch_.getSubstituted());
        assertEq(ARTIKODIN, animSwitch_.getSubstituteName());
        assertEq(3, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    private Fight roundThrowerSwitch(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
            PkTrainer allyPokemon_ = new PkTrainer();
            allyPokemon_.setName(TARTARD);
            allyPokemon_.setItem(PLAQUE_DRACO);
            allyPokemon_.setAbility(MULTITYPE);
            allyPokemon_.setGender(Gender.NO_GENDER);
            allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
            allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
            allyTeam_.add(allyPokemon_);
        }
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void roundThrowerSwitch1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerSwitch(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition exiting_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition entering_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(exiting_);
        fighter_.setSubstitute((byte) 1);
        FightRound.initRound(fight_);
        FightRound.roundThrowerSwitch(fight_, exiting_, diff_, data);
        assertEq(3, fight_.getFirstPositPlayerFighters().size());
        fighter_ = fight_.getFighter(entering_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(exiting_);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(1, fight_.getEffects().size());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animSwitch_.getSubstituted());
        assertEq(ARTIKODIN, animSwitch_.getSubstituteName());
        assertEq(4, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertTrue(animSwitch_.isPlayer());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    @Test
    public void roundThrowerSwitch2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerSwitch(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition exiting_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition entering_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter fighter_ = fight_.getFighter(exiting_);
        fighter_.setSubstitute((byte) 3);
        FightRound.initRound(fight_);
        FightRound.roundThrowerSwitch(fight_, exiting_, diff_, data);
        assertEq(4, fight_.getFirstPositPlayerFighters().size());
        fighter_ = fight_.getFighter(entering_);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(exiting_);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(1, fight_.getEffects().size());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ONE, animSwitch_.getSubstituted());
        assertEq(TARTARD, animSwitch_.getSubstituteName());
        assertEq(4, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    @Test
    public void roundThrowerSwitch3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(PICOTS, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(JACKPOT);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundThrowerSwitch(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition exiting_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition entering_ = POKEMON_FOE_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(exiting_);
        fighter_.setSubstitute((byte) 2);
        FightRound.initRound(fight_);
        FightRound.roundThrowerSwitch(fight_, exiting_, diff_, data);
        assertEq(3, fight_.getFirstPositFoeFighters().size());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1));
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 2));
        fighter_ = fight_.getFighter(entering_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(exiting_);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(1, fight_.getEffects().size());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(POKEMON_FOE_TARGET_ZERO, animSwitch_.getSubstituted());
        assertEq(TARTARD, animSwitch_.getSubstituteName());
        assertEq(3, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    private Fight substituingAfterRoundThrowerMove(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
            PkTrainer allyPokemon_ = new PkTrainer();
            allyPokemon_.setName(TARTARD);
            allyPokemon_.setItem(PLAQUE_DRACO);
            allyPokemon_.setAbility(MULTITYPE);
            allyPokemon_.setGender(Gender.NO_GENDER);
            allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
            allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
            allyTeam_.add(allyPokemon_);
        }
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void substituingAfterRoundThrowerMove1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substituingAfterRoundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(DETECTION);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, target_, diff_, data);
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.isSuccessfulMove());
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void substituingAfterRoundThrowerMove2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COUD_BOUE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substituingAfterRoundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(COUD_BOUE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void substituingAfterRoundThrowerMove3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COUD_BOUE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substituingAfterRoundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void substituingAfterRoundThrowerMove4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COUD_BOUE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substituingAfterRoundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void substituingAfterRoundThrowerMove5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COUD_BOUE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substituingAfterRoundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!fighter_.isActed());
        assertTrue(FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data));
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fighter_.isActed());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
    }

    @Test
    public void substituingAfterRoundThrowerMove6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COUD_BOUE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DEMI_TOUR);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substituingAfterRoundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO, (byte) 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
    }

    @Test
    public void substituingAfterRoundThrowerMove7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COUD_BOUE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DEMI_TOUR);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substituingAfterRoundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
    }

    @Test
    public void substituingAfterRoundThrowerMove8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(COUD_BOUE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substituingAfterRoundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
    }

    private Fight selectTargetHavingToPlayAfterThrower(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
            PkTrainer allyPokemon_ = new PkTrainer();
            allyPokemon_.setName(TARTARD);
            allyPokemon_.setItem(PLAQUE_DRACO);
            allyPokemon_.setAbility(MULTITYPE);
            allyPokemon_.setGender(Gender.NO_GENDER);
            allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
            allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
            allyTeam_.add(allyPokemon_);
        }
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DRACO_RAGE, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = selectTargetHavingToPlayAfterThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        FightRound.initRound(fight_);
        assertEq(0, FightRound.selectTargetHavingToPlayAfterThrower(fight_, POKEMON_PLAYER_FIGHTER_ONE, data).size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = selectTargetHavingToPlayAfterThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        assertEq(0, FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data).size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = selectTargetHavingToPlayAfterThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        assertEq(0, FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data).size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = selectTargetHavingToPlayAfterThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(APRES_VOUS, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(target_);
        fighter_.setActed(true);
        assertEq(0, FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data).size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = selectTargetHavingToPlayAfterThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(APRES_VOUS, POKEMON_PLAYER_TARGET_ONE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setSubstitute((byte) 3);
        FightRound.initRound(fight_);
        FightRound.roundThrowerSwitch(fight_, target_, diff_, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(target_);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        EqList<TeamPosition> targets_ = FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data);
        assertEq(0, targets_.size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = selectTargetHavingToPlayAfterThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(APRES_VOUS, POKEMON_PLAYER_TARGET_ONE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        EqList<TeamPosition> targets_ = FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data);
        assertEq(1, targets_.size());
        assertTrue(targets_.containsObj(target_));
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(A_LA_QUEUE, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = selectTargetHavingToPlayAfterThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(A_LA_QUEUE, POKEMON_PLAYER_TARGET_ONE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        EqList<TeamPosition> targets_ = FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data);
        assertEq(0, targets_.size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COUD_BOUE, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = selectTargetHavingToPlayAfterThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(COUD_BOUE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        EqList<TeamPosition> targets_ = FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data);
        assertEq(0, targets_.size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = selectTargetHavingToPlayAfterThrower(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(SEISME);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        EqList<TeamPosition> targets_ = FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data);
        assertEq(0, targets_.size());
    }

    private Fight calculateCatchingRate(
            CustList<LevelMoves> _userMoves,
            String _name,
            short _level,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, foePokemon_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void calculateCatchingRate1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        Fight fight_ = calculateCatchingRate(userMoves_, PIKACHU, (short) 3, diff_);
        fight_.wildPokemon().setRemainedHp(new Rate("89/10"));
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, HYPER_BALL, false, diff_, data));
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, HYPER_BALL, true, diff_, data));
        diff_.setAllowCatchingKo(false);
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, HYPER_BALL, true, diff_, data));
        fight_.wildPokemon().setRemainedHp(Rate.one());
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, HYPER_BALL, true, diff_, data));
    }

    @Test
    public void calculateCatchingRate2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        Fight fight_ = calculateCatchingRate(userMoves_, PIKACHU, (short) 3, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, HYPER_BALL, false, diff_, data));
    }

    @Test
    public void calculateCatchingRate3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        Fight fight_ = calculateCatchingRate(userMoves_, PIKACHU, (short) 3, diff_);
        fight_.wildPokemon().affecterStatut(SOMMEIL);
        fight_.wildPokemon().setRemainedHp(new Rate("89/10"));
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, HYPER_BALL, false, diff_, data));
    }

    @Test
    public void calculateCatchingRate4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        Fight fight_ = calculateCatchingRate(userMoves_, PIKACHU, (short) 3, diff_);
        fight_.wildPokemon().affecterStatut(SOMMEIL);
        assertEq(new Rate("0"),FightRound.calculateCatchingRate(fight_, HYPER_BALL, false, diff_, data));
    }

    @Test
    public void calculateCatchingRate5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        Fight fight_ = calculateCatchingRate(userMoves_, PIKACHU, (short) 3, diff_);
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, MASTER_BALL, false, diff_, data));
    }

    @Test
    public void calculateCatchingRate6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        Fight fight_ = calculateCatchingRate(userMoves_, TETARTE, (short) 3, diff_);
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, MASTER_BALL, false, diff_, data));
    }

    @Test
    public void calculateCatchingRate7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        Fight fight_ = calculateCatchingRate(userMoves_, TETARTE, (short) 3, diff_);
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, PAS_DE_BALL, false, diff_, data));
    }

    private Fight calculateFleeingRate(
            CustList<LevelMoves> _userMoves,
            String _name,
            short _level,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, foePokemon_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void calculateFleeingRate1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(true);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        Fight fight_ = calculateFleeingRate(userMoves_, PIKACHU, (short) 3, diff_);
        assertEq(new Rate("1"),FightRound.calculateFleeingRate(fight_, diff_, data));
    }

    @Test
    public void calculateFleeingRate2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        Fight fight_ = calculateFleeingRate(userMoves_, PIKACHU, (short) 3, diff_);
        assertEq(new Rate("1"),FightRound.calculateFleeingRate(fight_, diff_, data));
    }

    @Test
    public void calculateFleeingRate3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        Fight fight_ = calculateFleeingRate(userMoves_, PIKACHU, (short) 10, diff_);
        assertEq(new Rate("0"),FightRound.calculateFleeingRate(fight_, diff_, data));
    }

    @Test
    public void calculateFleeingRate4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        Fight fight_ = calculateFleeingRate(userMoves_, MELOFEE, (short) 10, diff_);
        assertEq(new Rate("0"),FightRound.calculateFleeingRate(fight_, diff_, data));
    }

    @Test
    public void calculateFleeingRate5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        Fight fight_ = calculateFleeingRate(userMoves_, TARINOR, (short) 10, diff_);
        assertEq(new Rate("0"),FightRound.calculateFleeingRate(fight_, diff_, data));
    }

    private Fight nextFighters(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _user,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_user, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_user, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void nextFighters1Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 0);
        diff_.setIvFoe((short) 0);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 18);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 19);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(BULLES_D_O);
        foesMoves_.add(new LevelMoves((short)16,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = nextFighters(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition foePkBis_ = POKEMON_FOE_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ONE);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePkBis_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        NextUsers p_;
        p_ = FightRound.nextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(1, p_.getNextFighters().size());
        assertTrue(p_.getNextFighters().containsObj(ally_));
        assertEq(0, p_.getItemUsers().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void nextFighters2Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 0);
        diff_.setIvFoe((short) 0);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 18);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 19);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(BULLES_D_O);
        foesMoves_.add(new LevelMoves((short)16,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = nextFighters(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition foePkBis_ = POKEMON_FOE_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ONE);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePkBis_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        NextUsers p_;
        p_ = FightRound.nextFighters(fight_, new EqList<TeamPosition>(userPk_), data);
        assertEq(1, p_.getNextFighters().size());
        assertTrue(p_.getNextFighters().containsObj(userPk_));
        assertEq(0, p_.getItemUsers().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void nextFighters3Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 0);
        diff_.setIvFoe((short) 0);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 18);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 19);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(BULLES_D_O);
        foesMoves_.add(new LevelMoves((short)16,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = nextFighters(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition foePkBis_ = POKEMON_FOE_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ONE);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePkBis_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        fight_.getFighter(userPk_).setActed(true);
        fight_.getFighter(ally_).setActed(true);
        fight_.getFighter(foePk_).setActed(true);
        fight_.getFighter(foePkBis_).setActed(true);
        NextUsers p_;
        p_ = FightRound.nextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(0, p_.getNextFighters().size());
        assertEq(0, p_.getItemUsers().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void nextFighters4Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 0);
        diff_.setIvFoe((short) 0);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 18);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 19);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(BULLES_D_O);
        foesMoves_.add(new LevelMoves((short)16,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = nextFighters(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ONE);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(userPk_).setActed(true);
        fight_.getFighter(ally_).setActed(true);
        fight_.getFighter(foePk_).setActed(true);
        NextUsers p_;
        p_ = FightRound.nextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(0, p_.getNextFighters().size());
        assertEq(0, p_.getItemUsers().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void nextFighters5Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 0);
        diff_.setIvFoe((short) 0);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 18);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 19);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(BULLES_D_O);
        foesMoves_.add(new LevelMoves((short)16,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = nextFighters(partnersMoves_, foesMoves_, player_, diff_, 2);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setSubstitute((byte) 2);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ONE);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        NextUsers p_;
        p_ = FightRound.nextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(1, p_.getNextFighters().size());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, p_.getNextFighters().first());
        assertEq(0, p_.getItemUsers().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void nextFighters6Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 0);
        diff_.setIvFoe((short) 0);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 18);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 19);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(BULLES_D_O);
        foesMoves_.add(new LevelMoves((short)16,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = nextFighters(partnersMoves_, foesMoves_, player_, diff_, 2);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setSubstitute((byte) 2);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(EAU_FRAICHE, data);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        NextUsers p_;
        p_ = FightRound.nextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(1, p_.getNextFighters().size());
        assertEq(POKEMON_PLAYER_FIGHTER_ONE, p_.getNextFighters().first());
        assertEq(0, p_.getItemUsers().size());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void nextFighters1SimulationTest(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 0);
        diff_.setIvFoe((short) 0);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 19);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(BULLES_D_O);
        foesMoves_.add(new LevelMoves((short)16,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = nextFighters(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.setSimulation(true);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition foePkBis_ = POKEMON_FOE_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ONE);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePkBis_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        FightRound.nextFighters(fight_, new EqList<TeamPosition>(), data);
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight endRoundShowActions(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _user,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_user, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_user, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, data);
        return fight_;
    }

    @Test
    public void endRoundShowActions1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundShowActions(partnersMoves_, foesMoves_, player_, diff_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundShowActions(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundShowActions(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NINGALE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 16);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundShowActions(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NINGALE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 16);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundShowActions(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NINGALE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 16);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundShowActions(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(FightFacade.loose(fight_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundShowActions(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.enableGlobalMove(TEMPETESABLE);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(FightFacade.loose(fight_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundShowActions(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.enableGlobalMove(TEMPETESABLE);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(FightFacade.win(fight_));
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundShowActions(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.enableGlobalMove(TEMPETESABLE);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(FightFacade.win(fight_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundShowActions(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
//        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundShowActions(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setSimulation(true);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.enableGlobalMove(TEMPETESABLE);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(FightFacade.loose(fight_));
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight calculateNextFighters(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _user,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_user, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_user, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, data);
        return fight_;
    }

    @Test
    public void calculateNextFighters1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = calculateNextFighters(partnersMoves_, foesMoves_, player_, diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(1, fight_.getRemainingFighters().size());
        assertTrue(fight_.getRemainingFighters().containsObj(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,fight_.getCurrentUser());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void calculateNextFighters2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = calculateNextFighters(partnersMoves_, foesMoves_, player_, diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fighter_.backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(1, fight_.getRemainingFighters().size());
        assertTrue(fight_.getRemainingFighters().containsObj(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(POKEMON_FOE_FIGHTER_ZERO,fight_.getCurrentUser());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertTrue(fighter_.isUsingItem());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void calculateNextFighters3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = calculateNextFighters(partnersMoves_, foesMoves_, player_, diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_.backUpObject(BAIE_CHERIM);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fighter_.backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(1, fight_.getRemainingFighters().size());
        assertTrue(fight_.getRemainingFighters().containsObj(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,fight_.getCurrentUser());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void calculateNextFighters4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = calculateNextFighters(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_.backUpObject(BAIE_CHERIM);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fighter_.backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(userPk_);
        fighter_.setActed(true);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setActed(true);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(0, fight_.getRemainingFighters().size());
        fighter_ = fight_.getFighter(userPk_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(foePk_);
        assertTrue(!fighter_.isUsingItem());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void calculateNextFighters5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = calculateNextFighters(partnersMoves_, foesMoves_, player_, diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.setKeepRound(true);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(1, fight_.getRemainingFighters().size());
        assertTrue(fight_.getRemainingFighters().containsObj(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,fight_.getCurrentUser());
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(fight_.isKeepRound());
    }

    @Test
    public void calculateNextFighters6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = calculateNextFighters(partnersMoves_, foesMoves_, player_, diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fighter_.backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        fight_.setKeepRound(true);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(1, fight_.getRemainingFighters().size());
        assertTrue(fight_.getRemainingFighters().containsObj(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(POKEMON_FOE_FIGHTER_ZERO,fight_.getCurrentUser());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertTrue(fighter_.isUsingItem());
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(fight_.isKeepRound());
    }

    @Test
    public void calculateNextFighters7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = calculateNextFighters(partnersMoves_, foesMoves_, player_, diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_.backUpObject(BAIE_CHERIM);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fighter_.backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        fight_.setKeepRound(true);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(1, fight_.getRemainingFighters().size());
        assertTrue(fight_.getRemainingFighters().containsObj(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,fight_.getCurrentUser());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(fight_.isKeepRound());
    }

    @Test
    public void calculateNextFighters8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = calculateNextFighters(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_.backUpObject(BAIE_CHERIM);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fighter_.backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(userPk_);
        fighter_.setActed(true);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setActed(true);
        fight_.setKeepRound(true);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        assertEq(0, fight_.getRemainingFighters().size());
        fighter_ = fight_.getFighter(userPk_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(foePk_);
        assertTrue(!fighter_.isUsingItem());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(!fight_.isKeepRound());
    }

    @Test
    public void calculateNextFighters1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 0);
        diff_.setIvFoe((short) 0);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 19);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(BULLES_D_O);
        foesMoves_.add(new LevelMoves((short)16,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = nextFighters(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.setSimulation(true);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition foePkBis_ = POKEMON_FOE_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ONE);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePkBis_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void calculateNextFighters2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 0);
        diff_.setIvFoe((short) 0);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 19);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(BULLES_D_O);
        foesMoves_.add(new LevelMoves((short)16,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = nextFighters(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.setSimulation(true);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition foePkBis_ = POKEMON_FOE_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_FOE_TARGET_ONE);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePkBis_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        fight_.setKeepRound(true);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        assertTrue(!fight_.getAcceptableChoices());
        assertTrue(!fight_.isKeepRound());
    }

    private Fight setAllyChoices(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _user,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_user, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_user, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, data);
        return fight_;
    }

    @Test
    public void setAllyChoices1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setAllyChoices(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getAllyChoice().put(new MoveTarget(NULL_REF,new TargetCoords()), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(CHARGE, action_.getFinalChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setAllyChoices2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setAllyChoices(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO), new MoveTarget(DETECTION,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(CHARGE, action_.getFinalChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setAllyChoices3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setAllyChoices(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ONE);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO), new MoveTarget(DETECTION,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(CHARGE, action_.getFinalChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setAllyChoices4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 15);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setAllyChoices(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getAllyChoice().put(new MoveTarget(SEISME,new TargetCoords()), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO), new MoveTarget(DETECTION,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(CHARGE, action_.getFinalChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setAllyChoices5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setAllyChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setSubstitute((byte) 1);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(CHARGE, action_.getFinalChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setAllyChoices6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setAllyChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(NULL_REF,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
    }

    private Fight roundUser(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _user,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_user, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_user, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, data);
        return fight_;
    }

    @Test
    public void roundUser1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundUser(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        fight_.setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(!fight_.isKeepRound());
    }

    @Test
    public void roundUser2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundUser(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setSubstitute((byte) 1);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        fight_.setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data);
        assertEq(POKEMON_PLAYER_FIGHTER_TWO, fight_.getCurrentUser());
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(fighter_.estArriere());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertEq(new Rate("92/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(fight_.isKeepRound());
        assertEq(1, fight_.getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void roundUser3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundUser(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        fight_.setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, fight_.getCurrentUser());
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(!fight_.isKeepRound());
        assertEq(1, fight_.getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void roundUser4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundUser(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        fight_.setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data);
        fight_.getFighter(userPk_).setSubstitute((byte) 1);
        TeamPosition currentUser_ = fight_.getCurrentUser();
        FightRound.roundThrowerSwitch(fight_, currentUser_, diff_, data);
        fight_.setState(FightState.ATTAQUES);
        EqList<TeamPosition> cbts_=FightRound.selectTargetHavingToPlayAfterThrower(fight_,currentUser_,data);
        fight_.getOrderedFighters().clear();
        fight_.getOrderedFighters().addAllElts(cbts_);
        FightOrder.sortFightersUsingMoveAmongList(fight_,data);
        cbts_ = fight_.getOrderedFighters();
        FightRound.calculateNextFighters(fight_, cbts_, data);
        fight_.setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data);
        assertEq(POKEMON_PLAYER_FIGHTER_TWO, fight_.getCurrentUser());
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(fight_.isKeepRound());
        assertEq(1, fight_.getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void roundUser5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundUser(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        fight_.setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data);
        fight_.setState(FightState.ATTAQUES);
        TeamPosition currentUser_ = fight_.getCurrentUser();
        EqList<TeamPosition> cbts_=FightRound.selectTargetHavingToPlayAfterThrower(fight_,currentUser_,data);
        fight_.getOrderedFighters().clear();
        fight_.getOrderedFighters().addAllElts(cbts_);
        FightOrder.sortFightersUsingMoveAmongList(fight_,data);
        cbts_ = fight_.getOrderedFighters();
        FightRound.calculateNextFighters(fight_, cbts_, data);
        fight_.setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data);
        assertEq(POKEMON_PLAYER_FIGHTER_TWO, fight_.getCurrentUser());
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(fight_.isKeepRound());
        assertEq(1, fight_.getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void roundUser6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundUser(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.getRemainingHp().affect(new Rate("1"));
        fighter_.setChosenHealingObject(EAU_FRAICHE, data);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        fight_.setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data);
        assertEq(POKEMON_FOE_FIGHTER_ZERO, fight_.getCurrentUser());
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fight_.isKeepRound());
        assertEq(1, fight_.getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void roundUser7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundUser(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        fight_.setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data);
        fight_.getFighter(userPk_).setSubstitute((byte) 1);
        TeamPosition currentUser_ = fight_.getCurrentUser();
        FightRound.roundThrowerSwitch(fight_, currentUser_, diff_, data);
        fight_.setState(FightState.ATTAQUES);
        EqList<TeamPosition> cbts_=FightRound.selectTargetHavingToPlayAfterThrower(fight_,currentUser_,data);
        fight_.getOrderedFighters().clear();
        fight_.getOrderedFighters().addAllElts(cbts_);
        FightOrder.sortFightersUsingMoveAmongList(fight_,data);
        cbts_ = fight_.getOrderedFighters();
        FightRound.calculateNextFighters(fight_, cbts_, data);
        fight_.setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data);
        FightRound.roundUser(fight_, diff_, data);
        assertEq(POKEMON_PLAYER_FIGHTER_TWO, fight_.getCurrentUser());
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("525511/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(!fight_.isKeepRound());
        assertEq(0, fight_.getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void roundUser1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundUser(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setSimulation(true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setRemainedHp(Rate.one());
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setSubstitute((byte) 1);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        fight_.setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data);
        assertTrue(!fight_.getAcceptableChoices());
        assertTrue(!fight_.isKeepRound());
    }

    @Test
    public void roundUser2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DRACO_RAGE,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setSimulation(true);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(DRACO_RAGE, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new EqList<TeamPosition>(), data);
        fight_.setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data);
        assertTrue(!fight_.getAcceptableChoices());
        assertTrue(!fight_.isKeepRound());
    }

    private Fight beginRound(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _user,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_user, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_user, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, data);
        return fight_;
    }

    @Test
    public void beginRound1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = beginRound(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.beginRound(fight_, diff_, data);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, fight_.getCurrentUser());
        assertTrue(fight_.isKeepRound());
        assertEq(CHARGE, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getFirstChosenMove());
        //assertTrue(!fight_.getBeginRound());
        assertEq(1, fight_.getRemainingFighters().size());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, fight_.getCurrentUser());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void beginRound2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = beginRound(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.beginRound(fight_, diff_, data);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, fight_.getCurrentUser());
        assertTrue(fight_.isKeepRound());
        //assertTrue(!fight_.getBeginRound());
        assertEq(1, fight_.getRemainingFighters().size());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, fight_.getCurrentUser());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void beginRound3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = beginRound(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.beginRound(fight_, diff_, data);
        FightRound.roundUser(fight_, diff_, data);
        fight_.getFighter(userPk_).setSubstitute((byte) 1);
        FightRound.beginRound(fight_, diff_, data);
        assertEq(POKEMON_FOE_FIGHTER_ZERO, fight_.getCurrentUser());
        //FightRound.roundUser(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(fight_.isKeepRound());
        assertEq(1, fight_.getRemainingFighters().size());
        assertEq(POKEMON_FOE_FIGHTER_ZERO, fight_.getCurrentUser());
        assertEq(1, fight_.getEffects().size());
        AnimationSwitch anim_ = (AnimationSwitch) fight_.getEffects().first();
        assertEq(PTITARD, anim_.getSubstituteName());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getSubstituted());
        assertEq(17,anim_.getLevel());
        assertEq(new LgInt("100"), anim_.getRateRemainHp());
        assertTrue(!anim_.isKo());
        assertEq(new LgInt("0"), anim_.getWonExpRate());
    }

    @Test
    public void beginRound4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = beginRound(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.beginRound(fight_, diff_, data);
        FightRound.roundUser(fight_, diff_, data);
        FightRound.beginRound(fight_, diff_, data);
        //FightRound.roundUser(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(fight_.isKeepRound());
        assertEq(1, fight_.getRemainingFighters().size());
        assertEq(POKEMON_FOE_FIGHTER_ZERO, fight_.getCurrentUser());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void beginRound5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = beginRound(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        FightRound.beginRound(fight_, diff_, data);
        FightRound.roundUser(fight_, diff_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fight_.getFighter(userPk_).setSubstitute((byte) 1);
        FightRound.beginRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(!fight_.isKeepRound());
        assertEq(0, fight_.getRemainingFighters().size());
        assertEq(1, fight_.getEffects().size());
        AnimationSwitch anim_ = (AnimationSwitch) fight_.getEffects().first();
        assertEq(PTITARD, anim_.getSubstituteName());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getSubstituted());
        assertEq(17,anim_.getLevel());
        assertEq(new LgInt("100"), anim_.getRateRemainHp());
        assertTrue(!anim_.isKo());
        assertEq(new LgInt("0"), anim_.getWonExpRate());
    }

    @Test
    public void beginRound1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = beginRound(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setSimulation(true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setRemainedHp(Rate.one());
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        FightRound.beginRound(fight_, diff_, data);
        FightRound.roundUser(fight_, diff_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
        fight_.getFighter(userPk_).setSubstitute((byte) 1);
        FightRound.beginRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("0"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight endRoundFight(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _user,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_user, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_user, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, data);
        return fight_;
    }

    @Test
    public void endRoundFight1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundFight(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.beginRound(fight_, diff_, data);
        FightRound.roundUser(fight_, diff_, data);
        FightRound.endRoundFight(fight_, diff_, player_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundFight2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = endRoundFight(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.beginRound(fight_, diff_, data);
        FightRound.roundUser(fight_, diff_, data);
        FightRound.endRoundFight(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundFight3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = endRoundFight(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.setAllyChoices(fight_, data);
        FightRound.beginRound(fight_, diff_, data);
        FightRound.roundUser(fight_, diff_, data);
        fight_.getFighter(userPk_).setSubstitute((byte) 1);
        FightRound.beginRound(fight_, diff_, data);
        FightRound.roundUser(fight_, diff_, data);
        FightRound.roundUser(fight_, diff_, data);
        FightRound.endRoundFight(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("525511/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(!fight_.isKeepRound());
        assertEq(0, fight_.getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    private Fight roundAllThrowers(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _user,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_user, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_user, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, data);
        return fight_;
    }

    @Test
    public void roundAllThrowers1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setSubstitute((byte) 1);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("10674449/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("40396/2675"), fighter_.getRemainingHp());
        assertEq(new Rate("92/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fight_.getFighter(userPk_).setSubstitute((byte) 1);
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("525511/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("525511/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("2571/25"), fighter_.getRemainingHp());
        assertEq(new Rate("2571/25"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("0"), fighter_.getRemainingHp());
        assertEq(new Rate("92/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertEq(new Rate("92/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(REVEIL_FORCE, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("271"), fighter_.getRemainingHp());
        assertEq(new Rate("271"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("0"), fighter_.getRemainingHp());
        assertEq(new Rate("92/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertEq(new Rate("92/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        //assertTrue(!FightEndRound.existSubstitute(fight_));
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
        //assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("525511/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fight_.getAllyChoice().clear();
        fight_.getFighter(userPk_).setChosenHealingObject(EAU_FRAICHE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObject(EAU_FRAICHE, data);
        fight_.getFighter(foePk_).setChosenHealingObject(EAU_FRAICHE, data);
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("218/5"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fight_.getFighter(userPk_).setSubstitute((byte) 1);
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DRACO_RAGE,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setSimulation(true);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(DRACO_RAGE, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        assertTrue(!fight_.getAcceptableChoices());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void roundAllThrowers2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DRACO_RAGE,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setSimulation(true);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setSubstitute((byte) 1);
        fight_.getFighter(partner_).setRemainedHp(Rate.one());
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        assertTrue(!fight_.getAcceptableChoices());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void roundAllThrowers3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundAllThrowers(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setSimulation(true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setRemainedHp(Rate.one());
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        TeamPosition userPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
        fight_.getFighter(userPk_).setSubstitute((byte) 1);
        FightRound.roundAllThrowers(fight_, diff_, player_, data);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(new Rate("0"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(!fight_.getAcceptableChoices());
    }
}
