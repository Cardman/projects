package aiki.game.fight;

import aiki.db.DataBase;
import aiki.fight.moves.enums.SwitchType;
import aiki.util.TeamPositionList;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
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
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class FightRoundTest extends InitializationDataBase {

    @Test
    public void initRound1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(GRELOT_ZEN);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(ULTRASON,10L);
        moves_.put(BROUHAHA,10L);
        moves_.put(POURSUITE,10L);
        PokemonPlayer pokemonUser_ = pkMoves(data_, diff_, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(data_, diff_, pokemon_, moves_);
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
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        Team userTeam_ = fight_.getUserTeam();
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(POURSUITE, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        Team trainerTeam_ = fight_.getFoeTeam();
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(JACKPOT, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        //assertTrue(fight_.getBeginRound());
        FightRound.initRound(fight_);
        assertEq(0, userTeam_.getSuccessfulMovesRound().size());
        assertEq(0, userTeam_.getNbUsesMovesRound().getVal(AIRE_DE_FEU));
        assertEq(0, userTeam_.getNbUsesMovesRound().getVal(AIRE_D_EAU));
        assertEq(0, userTeam_.getNbUsesMovesRound().getVal(AIRE_D_HERBE));
        assertEq(0, userTeam_.getNbKoPreviousRound());
        assertEq(0, userTeam_.getNbKoRound());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        assertTrue(!fighter_.isActed());
        assertEq(POURSUITE, fighter_.getFinalChosenMove());
        assertEq(0, trainerTeam_.getSuccessfulMovesRound().size());
        assertEq(0, trainerTeam_.getNbUsesMovesRound().getVal(AIRE_DE_FEU));
        assertEq(0, trainerTeam_.getNbUsesMovesRound().getVal(AIRE_D_EAU));
        assertEq(0, trainerTeam_.getNbUsesMovesRound().getVal(AIRE_D_HERBE));
        assertEq(0, trainerTeam_.getNbKoPreviousRound());
        assertEq(0, trainerTeam_.getNbKoRound());
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        assertEq(JACKPOT, fighter_.getFinalChosenMove());
        assertTrue(!fighter_.isActed());
        //assertTrue(!fight_.getBeginRound());
    }

    private static Fight autoDamage(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME,_diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(A_LA_QUEUE,10L);
        moves_.put(APRES_VOUS,10L);
        moves_.put(SEISME,10L);
        moves_.put(BROUHAHA,10L);
        PokemonPlayer pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
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
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void autoDamage1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_, data_);
        FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        assertEq(new Rate("12724889/136625"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void autoDamage2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_, data_);
        FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void autoDamage3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void autoDamage4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void autoDamage5Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(POUDRE_ATTAQUE);
        FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void autoDamage1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_, data_);
        fight_.getTemp().setSimulation(true);
        FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void disableRandomlyStatus1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(POISON_GRAVE);
        MonteCarloBoolean law_ = new MonteCarloBoolean();
        law_.addQuickEvent(BoolVal.TRUE, LgInt.one());
        FightRound.disableRandomlyStatus(fight_,law_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), POISON_GRAVE, true, data_);
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
        FightRound.disableRandomlyStatus(fight_,law_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), POISON_GRAVE, false, data_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
    }

    @Test
    public void disableRandomlyStatus2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(POISON_GRAVE);
        MonteCarloBoolean law_ = new MonteCarloBoolean();
        law_.addQuickEvent(BoolVal.FALSE, LgInt.one());
        FightRound.disableRandomlyStatus(fight_,law_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), POISON_GRAVE, true, data_);
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
        FightRound.disableRandomlyStatus(fight_,law_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), POISON_GRAVE, false, data_);
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
    }

    @Test
    public void disableRandomlyStatus3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(POISON_GRAVE);
        MonteCarloBoolean law_ = new MonteCarloBoolean();
        FightRound.disableRandomlyStatus(fight_,law_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), POISON_GRAVE, true, data_);
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
    }

    @Test
    public void disableRandomlyStatus1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_, data_);
        fight_.getTemp().setSimulation(true);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(POISON_GRAVE);
        MonteCarloBoolean law_ = new MonteCarloBoolean();
        law_.addQuickEvent(BoolVal.TRUE, LgInt.one());
        law_.addQuickEvent(BoolVal.FALSE, LgInt.one());
        FightRound.disableRandomlyStatus(fight_,law_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), POISON_GRAVE, false, data_);
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
    }

    @Test
    public void disableRandomlyStatus2SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = autoDamage(diff_, data_);
        fight_.getTemp().setSimulation(true);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).affecterStatut(POISON_GRAVE);
        MonteCarloBoolean law_ = new MonteCarloBoolean();
        law_.addQuickEvent(BoolVal.TRUE, LgInt.one());
        law_.addQuickEvent(BoolVal.FALSE, LgInt.one());
        FightRound.disableRandomlyStatus(fight_,law_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), POISON_GRAVE, false, data_);
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatusNbRound(POISON_GRAVE));
    }

    private static Fight effectBeginRoundAttack(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME,_diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(ROUE_DE_FEU,10L);
        moves_.put(RONFLEMENT,10L);
        moves_.put(BLABLA_DODO,10L);
        moves_.put(BROUHAHA,10L);
        PokemonPlayer pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
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
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectBeginRoundAttack1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(TROUILLE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), TROUILLE, diff_, data_);
        assertTrue(!fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(TROUILLE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(PEUR);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PEUR, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(TROUILLE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_SANS_DOMMAGE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), LONGUE_CONFUSION_SANS_DOMMAGE, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(LONGUE_CONFUSION_SANS_DOMMAGE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), LONGUE_CONFUSION_DOMMAGE, diff_, data_);
        assertTrue(!fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack5Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), LONGUE_CONFUSION_DOMMAGE, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack6Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_FOE, POKEMON_FIGHTER_ZERO),AMOUR_FOU);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), AMOUR_FOU, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(AMOUR_FOU, tp(KEY_FOE, POKEMON_FIGHTER_ZERO))));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack7Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(PEUR);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).incrementRoundsStatus(PEUR);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PEUR, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(PEUR));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack8Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).incrementRoundsStatus(LONGUE_CONFUSION_DOMMAGE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), LONGUE_CONFUSION_DOMMAGE, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack9Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(ERE_GEL);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), ERE_GEL, diff_, data_);
        assertTrue(!fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(ERE_GEL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack10Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(FEU_GEL);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), FEU_GEL, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(FEU_GEL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack11Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_FOE, POKEMON_FIGHTER_ZERO),AMOUR_MOU);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), AMOUR_MOU, diff_, data_);
        assertTrue(!fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(AMOUR_MOU, tp(KEY_FOE, POKEMON_FIGHTER_ZERO))));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack12Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(PARALYSIE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PARALYSIE, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(PARALYSIE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack13Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(PARALYSIE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PARALYSIE, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(PARALYSIE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack14Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(PARALYSIE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PARALYSIE, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(PARALYSIE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack15Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(ERE_GEL);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), ERE_GEL, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(ERE_GEL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack16Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(PARALYSIE_FORTE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PARALYSIE_FORTE, diff_, data_);
        assertTrue(!fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(PARALYSIE_FORTE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack17Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(PARALYSIE_FORTE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PARALYSIE_FORTE, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(PARALYSIE_FORTE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack18Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(PARALYSIE_FORTE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PARALYSIE_FORTE, diff_, data_);
        assertTrue(!fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(PARALYSIE_FORTE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack19Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), SOMMEIL, diff_, data_);
        assertTrue(!fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack20Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(RONFLEMENT, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), SOMMEIL, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack21Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), SOMMEIL, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack22Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        //assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        //fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.getTemp().setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), LONGUE_CONFUSION_DOMMAGE, diff_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack23Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PRISE_DE_TETE);
        //fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        //assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        //fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.getTemp().setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), PRISE_DE_TETE, diff_, data_);
        assertEq(2, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(PRISE_DE_TETE, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO))));
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("42756/2675"), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getRemainingHp());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack24Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PRISE_DE_TETE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).incrementPseudoStatutCombattant(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PRISE_DE_TETE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).incrementPseudoStatutCombattant(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PRISE_DE_TETE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).incrementPseudoStatutCombattant(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PRISE_DE_TETE);
        //fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatusRelat().put(new MoveTeamPosition(PRISE_DE_TETE, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)),  4);
        //fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        //assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        //fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.getTemp().setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), PRISE_DE_TETE, diff_, data_);
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(PRISE_DE_TETE, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO))));
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("42756/2675"), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getRemainingHp());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack25Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), PRISE_DE_TETE);
        //fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        //assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        FightRound.initRound(fight_);
        //fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.getTemp().setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), PRISE_DE_TETE, diff_, data_);
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(PRISE_DE_TETE, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO))));
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("92/5"), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getRemainingHp());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getTemp().setSimulation(true);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), COUP_DE_BEC);
        //fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        //assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).incrementPseudoStatutCombattant(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), COUP_DE_BEC);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).incrementPseudoStatutCombattant(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), COUP_DE_BEC);
        //fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatusRelat().put(new MoveTeamPosition(COUP_DE_BEC, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)), 3);
        FightRound.initRound(fight_);
        //fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.getTemp().setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), COUP_DE_BEC, diff_, data_);
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(COUP_DE_BEC, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO))));
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("92/5"), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getRemainingHp());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack2SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getTemp().setSimulation(true);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), COUP_DE_BEC);
        //fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        //assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), COUP_DE_BEC);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).incrementPseudoStatutCombattant(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), COUP_DE_BEC);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).incrementPseudoStatutCombattant(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), COUP_DE_BEC);
//        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusRelat().put(new MoveTeamPosition(COUP_DE_BEC, tp(KEY_FOE, POKEMON_FIGHTER_ZERO)), 3);
        FightRound.initRound(fight_);
        //fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.getTemp().setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), COUP_DE_BEC, diff_, data_);
        assertEq(4, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(COUP_DE_BEC, tp(KEY_FOE, POKEMON_FIGHTER_ZERO))));
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("2137186/27325"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack3SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getTemp().setSimulation(true);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_SANS_DOMMAGE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), LONGUE_CONFUSION_SANS_DOMMAGE, diff_, data_);
        assertTrue(!fight_.getTemp().getLettingUserAttackWithStatus());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(LONGUE_CONFUSION_SANS_DOMMAGE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack4SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getTemp().setSimulation(true);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        //assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        //fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.getTemp().setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), LONGUE_CONFUSION_DOMMAGE, diff_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack5SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getTemp().setSimulation(true);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_SANS_DOMMAGE);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), LONGUE_CONFUSION_SANS_DOMMAGE, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatusNbRound(LONGUE_CONFUSION_SANS_DOMMAGE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack6SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getTemp().setSimulation(true);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_FOE, POKEMON_FIGHTER_ZERO),AMOUR_FOU);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), AMOUR_FOU, diff_, data_);
        assertTrue(!fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(AMOUR_FOU, tp(KEY_FOE, POKEMON_FIGHTER_ZERO))));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack7SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getTemp().setSimulation(true);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO),AMOUR_FOU);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), AMOUR_FOU, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertEq(1, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(AMOUR_FOU, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO))));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectBeginRoundAttack8SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getTemp().setSimulation(true);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        //assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        //fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.getTemp().setKeepStatus(true);
        FightRound.effectBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), LONGUE_CONFUSION_DOMMAGE, diff_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(!fight_.getTemp().getAcceptableChoices());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
    }

    @Test
    public void statusBeginRoundAttack1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(RONFLEMENT, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.getTemp().setKeepStatus(true);
        FightRound.statusBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), SOMMEIL, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertTrue(fight_.getTemp().isKeepStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void statusBeginRoundAttack2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(BRULURE);
        fight_.getTemp().setKeepStatus(true);
        FightRound.statusBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), BRULURE, diff_, data_);
        assertTrue(fight_.getTemp().getLettingUserAttackWithStatus());
        assertTrue(fight_.getTemp().isKeepStatus());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(BRULURE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void statusBeginRoundAttack3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL_REPOS);
        fight_.getTemp().setKeepStatus(true);
        FightRound.statusBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), SOMMEIL_REPOS, diff_, data_);
        assertTrue(!fight_.getTemp().getLettingUserAttackWithStatus());
        assertTrue(!fight_.getTemp().isKeepStatus());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL_REPOS));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void statusBeginRoundAttack4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        //assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        //fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.getTemp().setKeepStatus(true);
        FightRound.statusBeginRoundAttack(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), LONGUE_CONFUSION_DOMMAGE, diff_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
    }

    @Test
    public void statusBeginRoundAttack1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectBeginRoundAttack(diff_, data_);
        fight_.getTemp().setSimulation(true);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        //FightRound.autoDamage(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("1000"), Statistic.ATTACK, Statistic.DEFENSE, diff_, data_);
        //assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).estKo());
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        //fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        fight_.getTemp().setKeepStatus(true);
        FightRound.statusBeginRoundAttack(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), LONGUE_CONFUSION_DOMMAGE, diff_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(!fight_.getTemp().getAcceptableChoices());
//        assertTrue(!fight_.isKeepStatus());
//        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatusNbRound(SOMMEIL));
    }

    @Test
    public void takers1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(COPIE,10L);
        moves_.put(MIMIQUE,10L);
        moves_.put(INTERVERSION,10L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, SAISIE, COPIE));
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
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(SAISIE,tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).setFirstChosenMoveTarget(PISTOLET_A_O,tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        TeamPositionList takers_ = FightRound.takers(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), data_);
        assertEq(1, takers_.size());
        assertTrue(takers_.containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
    }

    @Test
    public void takers2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(COPIE,10L);
        moves_.put(MIMIQUE,10L);
        moves_.put(INTERVERSION,10L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, SAISIE, COPIE));
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
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(SAISIE,tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).setFirstChosenMove(PAR_ICI);
        FightRound.initRound(fight_);
        TeamPositionList takers_ = FightRound.takers(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), data_);
        assertEq(0, takers_.size());
    }

    @Test
    public void takers3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(COPIE,10L);
        moves_.put(MIMIQUE,10L);
        moves_.put(INTERVERSION,10L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, SAISIE, COPIE));
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
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(PAR_ICI);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).setFirstChosenMove(PAR_ICI);
        FightRound.initRound(fight_);
        TeamPositionList takers_ = FightRound.takers(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), data_);
        assertEq(0, takers_.size());
    }

    private static Fight pressure(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME,_diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(COPIE,10L);
        moves_.put(MIMIQUE,10L);
        moves_.put(INTERVERSION,10L);
        PokemonPlayer lasPk_ = pkMoves(_data, _diff, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(_data, _diff, pokemon_, moves_);
        lasPk_.setAbility(PRESSION);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(_data, _diff, pokemon_, moves_);
        lasPk_.setAbility(TERA_VOLTAGE);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PAR_ICI, COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PRESSION);
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
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void pressure1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = pressure(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        FightRound.pressure(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), SEISME, diff_, data_);
        assertEq(10, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).powerPointsMove(SEISME));
    }

    @Test
    public void pressure2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = pressure(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        FightRound.pressure(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), SEISME, diff_, data_);
        assertEq(10, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).powerPointsMove(SEISME));
    }

    @Test
    public void pressure3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = pressure(diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        FightRound.pressure(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ONE), SEISME, diff_, data_);
        assertEq(10, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).powerPointsMove(SEISME));
    }

    @Test
    public void pressure4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = pressure(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        FightRound.pressure(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ONE), SEISME, diff_, data_);
        assertEq(9, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).powerPointsMove(SEISME));
    }

    @Test
    public void pressure5Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = pressure(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setLastSufferedMove(SEISME);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(MIMIQUE, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightInvoke.processInvokingMove(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.pressure(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ONE), SEISME, diff_, data_);
        assertEq(10, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).powerPointsMove(SEISME));
    }

    private static Fight effectWhileFail(Difficulty _diff, DataBase _data) {
        _diff.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,_diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(COPIE,10L);
        moves_.put(GLAS_DE_SOIN,10L);
        moves_.put(INTERVERSION,10L);
        PokemonPlayer lasPk_ = pkMoves(_data, _diff, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(_data, _diff, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(_data, _diff, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PAR_ICI, COPIE));
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
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectWhileFail1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), TONNERRE, diff_, data_);
        assertEq(1, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(ABRI_CAPE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(new Rate("46/5"));
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), TONNERRE, diff_, data_);
        assertEq(new Rate("46/5"), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(ABRI_CAPE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(new Rate("46/5"));
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), BROUHAHA, diff_, data_);
        assertEq(new Rate("46/5"), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(ABRI_CAPE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(new Rate("46/5"));
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), TONNERRE, diff_, data_);
        assertEq(new Rate("69/5"), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail5Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(ABSORB_VOLT);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(new Rate("46/5"));
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), TONNERRE, diff_, data_);
        assertEq(new Rate("69/5"), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail6Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(ABSORB_VOLT);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(new Rate("46/5"));
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), TONNERRE, diff_, data_);
        assertEq(new Rate("92/5"), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail7Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(new Rate("46/5"));
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), TONNERRE, diff_, data_);
        assertEq(new Rate("46/5"), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail8Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), ROUE_DE_FEU, diff_, data_);
        assertEq(new Rate("5619/400"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail9Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), COMBO_GRIFFE, diff_, data_);
        assertEq(-1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(new Rate("1873/100"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail10Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), COMBO_GRIFFE, diff_, data_);
        assertEq(new Rate("5619/400"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail11Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), ROUE_DE_FEU, diff_, data_);
        assertEq(new Rate("1873/200"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail12Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), COMBO_GRIFFE, diff_, data_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(new Rate("1873/100"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail13Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setRemainedHp(new Rate("1"));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), ROUE_DE_FEU, diff_, data_);
        assertEq(new Rate("0"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail14Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(NUEE_DE_POUDRE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setRemainedHp(new Rate("1"));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).successUsingMove();
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), ROUE_DE_FEU, diff_, data_);
        assertEq(new Rate("1"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }


    @Test
    public void effectWhileFail15Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(BOUCLIER_ROYAL);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setRemainedHp(new Rate("1"));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).successUsingMove();
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), ROUE_DE_FEU, diff_, data_);
        assertEq(new Rate("1"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail16Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMove(PICO_DEFENSE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setRemainedHp(new Rate("1"));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).successUsingMove();
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), ROUE_DE_FEU, diff_, data_);
        assertEq(new Rate("1"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void effectWhileFail1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = effectWhileFail(diff_, data_);
        fight_.getTemp().setSimulation(true);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setRemainedHp(new Rate("1"));
        FightRound.initRound(fight_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).successUsingMove();
        FightRound.effectWhileFail(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), ROUE_DE_FEU, diff_, data_);
        assertEq(new Rate("0"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    private static Fight healPartner(DataBase _data) {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(COPIE,10L);
        moves_.put(GLAS_DE_SOIN,10L);
        moves_.put(INTERVERSION,10L);
        PokemonPlayer lasPk_ = pkMoves(_data, diff_, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(_data, diff_, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(_data, diff_, pokemon_, moves_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer( 3, new StringList(JACKPOT, PAR_ICI, COPIE));
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
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data);
        return fight_;
    }

    @Test
    public void healPartner1Test() {
        DataBase data_ = initDb();
        Fight fight_ = healPartner(data_);
        FightRound.healPartner(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), Rate.one(), data_);
        assertTrue(!fight_.getTemp().isEnabledHealingPartner());
    }

    @Test
    public void healPartner2Test() {
        DataBase data_ = initDb();
        Fight fight_ = healPartner(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), AMOUR);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightRound.healPartner(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), Rate.one(), data_);
        assertTrue(fight_.getTemp().isEnabledHealingPartner());
        assertEq(new Rate("1873/100"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getRemainingHp());
    }

    @Test
    public void healPartner3Test() {
        DataBase data_ = initDb();
        Fight fight_ = healPartner(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), AMOUR_FOU);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightRound.healPartner(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), Rate.one(), data_);
        assertTrue(!fight_.getTemp().isEnabledHealingPartner());
        assertEq(new Rate("1"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getRemainingHp());
    }

    @Test
    public void healPartner4Test() {
        DataBase data_ = initDb();
        Fight fight_ = healPartner(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).affecterPseudoStatut(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), CAUCHEMAR);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightRound.healPartner(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), Rate.one(), data_);
        assertTrue(!fight_.getTemp().isEnabledHealingPartner());
        assertEq(new Rate("1"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getRemainingHp());
    }

    private static Fight effectWhileFail(DataBase _data) {
        Difficulty diff_ = new Difficulty();
        return effectWhileFail(diff_, _data);
    }

    @Test
    public void useBoostForAccuracy1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setUsingItem(false);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), BoolVal.TRUE);
        useBoostForAccuracy(fight_, thrower_, target_, NULL_REF, false, false, false, data_);
        assertTrue(!fighter_.isUsingItem());
        assertSame(BoolVal.TRUE,fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, target_)));
    }

    @Test
    public void useBoostForAccuracy2Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setUsingItem(false);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), BoolVal.TRUE);
        useBoostForAccuracy(fight_, thrower_, target_, NULL_REF, false, false, true, data_);
        assertTrue(!fighter_.isUsingItem());
        assertSame(BoolVal.TRUE,fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, target_)));
    }

    @Test
    public void useBoostForAccuracy3Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setUsingItem(false);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), BoolVal.TRUE);
        useBoostForAccuracy(fight_, thrower_, target_, NULL_REF, false, true, true, data_);
        assertTrue(fighter_.isUsingItem());
        assertSame(BoolVal.TRUE,fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, target_)));
    }

    @Test
    public void useBoostForAccuracy4Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setUsingItem(false);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), BoolVal.TRUE);
        useBoostForAccuracy(fight_, thrower_, target_, LIRE_ESPRIT, true, false, true, data_);
        assertTrue(!fighter_.isUsingItem());
        assertSame(BoolVal.FALSE,fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, target_)));
    }

    @Test
    public void useBoostForAccuracy5Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setUsingItem(false);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), BoolVal.TRUE);
        useBoostForAccuracy(fight_, thrower_, target_, LIRE_ESPRIT, true, true, true, data_);
        assertTrue(!fighter_.isUsingItem());
        assertSame(BoolVal.FALSE,fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, target_)));
    }

    @Test
    public void processAccurracy1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        FightRound.processAccurracy(fight_,ABIME, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_);
        assertTrue(!fight_.getTemp().isSuccessfulUse());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processAccurracy2Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        FightRound.processAccurracy(fight_,TONNERRE, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_);
        assertTrue(fight_.getTemp().isSuccessfulUse());
        assertTrue(!fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).isUsingItem());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processAccurracy3Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(BAIE_MICLE);
        FightRound.processAccurracy(fight_,BERCEUSE, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_);
        assertTrue(fight_.getTemp().isSuccessfulUse());
        assertTrue(fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).isUsingItem());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processAccurracy4Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT,tp(KEY_FOE, POKEMON_FIGHTER_ZERO)), BoolVal.TRUE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(BAIE_MICLE);
        FightRound.processAccurracy(fight_,BERCEUSE, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_);
        assertTrue(fight_.getTemp().isSuccessfulUse());
        assertSame(BoolVal.FALSE,fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT,tp(KEY_FOE, POKEMON_FIGHTER_ZERO))));
        assertTrue(!fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).isUsingItem());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processAccurracy5Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(BAIE_MICLE);
        FightRound.processAccurracy(fight_,TONNERRE, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_);
        assertTrue(fight_.getTemp().isSuccessfulUse());
        assertTrue(!fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).isUsingItem());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processAccurracy6Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT,tp(KEY_FOE, POKEMON_FIGHTER_ZERO)), BoolVal.TRUE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(NULL_REF);
        FightRound.processAccurracy(fight_,BERCEUSE, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_);
        assertTrue(fight_.getTemp().isSuccessfulUse());
        assertSame(BoolVal.FALSE,fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT,tp(KEY_FOE, POKEMON_FIGHTER_ZERO))));
        assertTrue(!fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).isUsingItem());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processAccurracy7Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT,tp(KEY_FOE, POKEMON_FIGHTER_ZERO)), BoolVal.TRUE);
        FightRound.processAccurracy(fight_,TONNERRE, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_);
        assertTrue(fight_.getTemp().isSuccessfulUse());
        assertSame(BoolVal.TRUE,fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT,tp(KEY_FOE, POKEMON_FIGHTER_ZERO))));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processAccurracy1SimulationTest() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        fight_.getTemp().setSimulation(true);
        FightRound.processAccurracy(fight_,BERCEUSE, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tp(KEY_FOE, POKEMON_FIGHTER_ZERO), data_);
        assertTrue(fight_.getTemp().isSuccessfulUse());
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processAccurracy2SimulationTest() {
        DataBase data_ = initDb();
        Fight fight_ = effectWhileFail(data_);
        fight_.getTemp().setSimulation(true);
        FightRound.processAccurracy(fight_,BERCEUSE, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), data_);
        assertTrue(fight_.getTemp().isSuccessfulUse());
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertEq(new Rate("92/5"), fight_.getTemp().getDamageByCurrentUser().getVal(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.affecterPseudoStatut(target_, AMOUR_FOU);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertEq(new Rate("92/5"), fight_.getTemp().getDamageByCurrentUser().getVal(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.affecterPseudoStatut(target_, AMOUR_MOU);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(COUD_BOUE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(VOL);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(COUD_BOUE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_PLAYER, POKEMON_FIGHTER_THREE);
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
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertEq(2, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(new Rate("14288/1575"), fight_.getTemp().getDamageByCurrentUser().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertEq(new Rate("14288/1575"), fight_.getTemp().getDamageByCurrentUser().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(COUD_BOUE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_PLAYER, POKEMON_FIGHTER_THREE);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT,6L);
        String move_ = SEISME;
        fighter_.setFirstChosenMove(move_);
        fighter_.affecterPseudoStatut(target_, AMOUR);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("1"));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(COUD_BOUE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(target_);
        fighter_.activerAttaqueImmu(VOL_MAGNETIK, data_);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(PROVOC,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = PROVOC;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(target_);
        fighter_.creerClone(new Rate("1/2"));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fighter_.getEnabledMoves().getVal(move_).isEnabled());
        assertEq(new Rate("46/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(ABIME,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = ABIME;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(target_);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertEq(new Rate("92/5"), fight_.getTemp().getDamageByCurrentUser().getVal(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_ENIGMA);
        fighter_.setRemainedHp(new Rate("18"));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_ENIGMA);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(COUD_BOUE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("28114/1625"),fighter_.getRemainingHp());
        assertEq(new Rate("1786/1625"), fight_.getTemp().getDamageByCurrentUser().getVal(target_));
        processEffectTargets(fight_, thrower_, thrower_, false, IndexConstants.SECOND_INDEX, Ints.newList(0), diff_, data_);
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(MUR_DE_FER,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets3(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = MUR_DE_FER;
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        String moveTarget_ = SAISIE;
        fighter_.setFirstChosenMoveTarget(moveTarget_, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, target_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertTrue(fighter_.isSuccessfulMove());
        fight_.getTemp().getSuccessfulEffects().clear();
        TeamPositionList list_ = FightRound.takers(fight_,thrower_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(target_));
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertEq(2, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(GLAS_DE_SOIN,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets3(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = GLAS_DE_SOIN;
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterStatut(SOMMEIL);
        String moveTarget_ = SAISIE;
        fighter_.setFirstChosenMoveTarget(moveTarget_, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, target_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertTrue(fighter_.isSuccessfulMove());
        fight_.getTemp().getSuccessfulEffects().clear();
        TeamPositionList list_ = FightRound.takers(fight_,thrower_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(target_));
        fight_.getTemp().setChangeThrower(true);
        processEffectTargets(fight_, thrower_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets16Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(FORCE_NATURE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets3(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = FORCE_NATURE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.affecterPseudoStatut(tp(KEY_PLAYER, POKEMON_FIGHTER_THREE), AMOUR);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(PRESSION);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightInvoke.processInvokingMove(fight_, thrower_, diff_, data_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        assertEq(2, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(new Rate("14288/1575"), fight_.getTemp().getDamageByCurrentUser().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertEq(new Rate("14288/1575"), fight_.getTemp().getDamageByCurrentUser().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets17Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(PICOTS,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets4(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE), new StringList(JACKPOT, PAR_ICI, REFLET_MAGIK));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = PICOTS;
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        String moveTarget_ = REFLET_MAGIK;
        fighter_.setFirstChosenMove(moveTarget_);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, target_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertTrue(fighter_.isSuccessfulMove());
        fight_.getTemp().getSuccessfulEffects().clear();
        assertEq(PointViewChangementType.MIRROR_AGAINST_THROWER, FightOrder.getPointViewChangementType(moveTarget_, data_));
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(move_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets18Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(BERCEUSE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets4(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE), new StringList(JACKPOT, PAR_ICI, REFLET_MAGIK));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = BERCEUSE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(target_);
        String moveTarget_ = REFLET_MAGIK;
        fighter_.setFirstChosenMove(moveTarget_);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, target_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), BoolVal.TRUE);
        fight_.getTemp().getSuccessfulEffects().clear();
        assertEq(PointViewChangementType.MIRROR_AGAINST_THROWER, FightOrder.getPointViewChangementType(moveTarget_, data_));
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets19Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(BERCEUSE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets4(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE), new StringList(JACKPOT, PAR_ICI, REFLET_MAGIK));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = BERCEUSE;
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), BoolVal.TRUE);
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets20Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(COUD_BOUE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_PLAYER, POKEMON_FIGHTER_THREE);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = SEISME;
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(VOL);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().isEnabledHealingPartner());
        assertEq(2, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(new Rate("7144/1575"), fight_.getTemp().getDamageByCurrentUser().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertEq(new Rate("7144/1575"), fight_.getTemp().getDamageByCurrentUser().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ONE)));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets21Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(POSSESSIF,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets3(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        String move_ = POSSESSIF;
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        String moveTarget_ = SAISIE;
        fighter_.setFirstChosenMoveTarget(moveTarget_, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, target_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertTrue(fighter_.isSuccessfulMove());
        fight_.getTemp().getSuccessfulEffects().clear();
        TeamPositionList list_ = FightRound.takers(fight_,thrower_, data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(target_));
        fight_.getTemp().setChangeThrower(true);
        processEffectTargets(fight_, thrower_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(target_);
        CustList<String> fobiddenMoves_ = fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF, tp(KEY_PLAYER, POKEMON_FIGHTER_THREE)));
        assertEq(2, fobiddenMoves_.size());
        assertTrue(StringUtil.contains(fobiddenMoves_, PAR_ICI));
        assertTrue(StringUtil.contains(fobiddenMoves_, JACKPOT));
        fobiddenMoves_ = fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertEq(0, fobiddenMoves_.size());
        assertEq(2, fight_.getTemp().getSuccessfulEffects().size());
        assertSame(BoolVal.FALSE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO))));
        assertSame(BoolVal.TRUE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, tp(KEY_PLAYER, POKEMON_FIGHTER_THREE))));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets22Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(COUD_BOUE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertEq(1, fight_.getTemp().getSuccessfulEffects().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("1"), fight_.getTemp().getDamageByCurrentUser().getVal(target_));
        processEffectTargets(fight_, thrower_, thrower_, false, IndexConstants.SECOND_INDEX, Ints.newList(0), diff_, data_);
        assertEq(1, fight_.getTemp().getSuccessfulEffects().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets23Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(COUD_BOUE,10L);
        moves_.put(COPIE,10L);
        moves_.put(TOUR_RAPIDE,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = TOUR_RAPIDE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(SPECTRE);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().isEnabledHealingPartner());
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(2, fight_.getTemp().getSuccessfulEffects().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        assertSame(BoolVal.TRUE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, thrower_)));
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        processEffectTargets(fight_, thrower_, thrower_, false, IndexConstants.SECOND_INDEX, Ints.newList(0), diff_, data_);
        assertEq(3, fight_.getTemp().getSuccessfulEffects().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        assertSame(BoolVal.TRUE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, thrower_)));
        assertSame(BoolVal.TRUE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(1, thrower_)));
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertEq(LgInt.zero(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
    }

    @Test
    public void processEffectTargets24Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(COUD_BOUE,10L);
        moves_.put(COPIE,10L);
        moves_.put(TOUR_RAPIDE,10L);
        Fight fight_ = processEffectTargets4(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE), new StringList(JACKPOT, ABRI, COPIE));
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = TOUR_RAPIDE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(ABRI);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().isEnabledHealingPartner());
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(1, fight_.getTemp().getSuccessfulEffects().size());
        assertSame(BoolVal.FALSE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        processEffectTargets(fight_, thrower_, thrower_, false, IndexConstants.SECOND_INDEX, Ints.newList(0), diff_, data_);
        assertEq(1, fight_.getTemp().getSuccessfulEffects().size());
        assertSame(BoolVal.FALSE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
    }

    @Test
    public void processEffectTargets25Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(POSSESSIF,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets6(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(GARDE_POUR_SOI);
        fighter_.backUpObject(NULL_REF);
        String move_ = POSSESSIF;
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(SAISIE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        fight_.getTemp().setChangeThrower(true);
        processEffectTargets(fight_, thrower_, target_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().isEnabledHealingPartner());
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets26Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets5(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.affecterPseudoStatut(target_, PRISE_DE_TETE);
        fighter_.setRemainedHp(Rate.one());
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("0"),fighter_.getRemainingHp());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets27Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(ROUE_DE_FEU,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets5(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = ROUE_DE_FEU;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        fighter_.enableCounteringMoves(NUEE_DE_POUDRE);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("0"),fighter_.getRemainingHp());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets28Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(COUP_D_MAIN,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets5(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
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
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(COUP_D_MAIN));
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets29Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(VAMPIPOING,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets5(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fighter_.setFirstChosenMoveTarget(VAMPIPOING, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        fighter_.enableCounteringMoves(NUEE_DE_POUDRE);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        assertEq(2, fight_.getTemp().getSuccessfulEffects().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, target_)));
        assertSame(BoolVal.TRUE, fight_.getTemp().getSuccessfulEffects().getVal(new NbEffectFighterCoords(0, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(VAMPIPOING));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("7144/1625"), fight_.getTemp().getDamageByCurrentUser().getVal(target_));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        processEffectTargets(fight_, thrower_, thrower_, false, IndexConstants.SECOND_INDEX, Ints.newList(0), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("5197/1625"), fighter_.getRemainingHp());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        fight_.getTemp().setSimulation(true);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.affecterPseudoStatut(target_, AMOUR_MOU);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets2SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(BERCEUSE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        fight_.getTemp().setSimulation(true);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = BERCEUSE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets3SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(BERCEUSE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets4(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE), new StringList(DRACO_RAGE, PAR_ICI, COPIE));
        fight_.getTemp().setSimulation(true);
        TeamPosition thrower_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void processEffectTargets4SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(ROUE_DE_FEU,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets5(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE));
        fight_.getTemp().setSimulation(true);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = ROUE_DE_FEU;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        fighter_.enableCounteringMoves(NUEE_DE_POUDRE);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertEq(new Rate("0"),fighter_.getRemainingHp());
        assertEq(10, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fight_.getTemp().getDamageByCurrentUser().contains(target_));
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundThrower1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        FightRound.endRoundThrower(fight_, thrower_, move_, false, data_);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data_);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DETECTION,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = DETECTION;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        FightRound.endRoundThrower(fight_, thrower_, move_, false, data_);
        assertEq(1, fighter_.getNbUsesMoves().getVal(move_));
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(AIRE_D_EAU,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = AIRE_D_EAU;
        fighter_.setFirstChosenMove(move_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        FightRound.endRoundThrower(fight_, thrower_, move_, false, data_);
        assertEq(1, fight_.getUserTeam().getNbUsesMovesRound().getVal(move_));
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(FORCE_NATURE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = FORCE_NATURE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightInvoke.processInvokingMove(fight_, thrower_, diff_, data_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data_);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(FORCE_NATURE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,1L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = SEISME;
        fighter_.setFirstChosenMove(move_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        fighter_.usePowerPointsByMove(diff_, move_,  1);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data_);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(FORCE_NATURE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,1L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = SEISME;
        fighter_.setFirstChosenMove(move_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        fighter_.usePowerPointsByMove(diff_, move_,  1);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        FightKo.setKoMoveTeams(fight_, thrower_, diff_, data_);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data_);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(LgInt.zero(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
    }

    @Test
    public void endRoundThrower8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(FORCE_NATURE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,1L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TURBO);
        fighter_.backUpObject(NULL_REF);
        String move_ = SEISME;
        fighter_.setFirstChosenMove(move_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        fighter_.usePowerPointsByMove(diff_, move_,  1);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data_);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertTrue(StringUtil.contains(successfulMoves_, move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPEED));
    }

    @Test
    public void endRoundThrower9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(FORCE_NATURE,10L);
        moves_.put(COPIE,10L);
        moves_.put(ABIME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TURBO);
        fighter_.backUpObject(NULL_REF);
        String move_ = ABIME;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ONE));
        FightRound.initRound(fight_);
        fighter_.usePowerPointsByMove(diff_, move_,  1);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data_);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(0, successfulMoves_.size());
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(LgInt.zero(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPEED));
    }

    @Test
    public void endRoundThrower10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(FORCE_NATURE,10L);
        moves_.put(COPIE,10L);
        moves_.put(ABIME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TURBO);
        fighter_.backUpObject(BAIE_MEPO);
        String move_ = ABIME;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ONE));
        FightRound.initRound(fight_);
        fighter_.usePowerPointsByMove(diff_, move_,  10);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        FightRound.endRoundThrower(fight_, thrower_, move_, true, data_);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(0, successfulMoves_.size());
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(LgInt.zero(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
        assertEq(10, fighter_.powerPointsMove(move_));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPEED));
    }

    @Test
    public void endRoundThrower11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(FORCE_NATURE,10L);
        moves_.put(COPIE,10L);
        moves_.put(CASSE,10L);
        Fight fight_ = processEffectTargets7(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE), new StringList(JACKPOT, PAR_ICI, COPIE));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TURBO);
        fighter_.backUpObject(BAIE_MICLE);
        String move_ = CASSE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ONE));
        FightRound.initRound(fight_);
        processEffectTargets(fight_, thrower_, thrower_, true, IndexConstants.FIRST_INDEX, new Ints(), diff_, data_);
        FightRound.endRoundThrower(fight_, thrower_, move_, false, data_);
        StringList successfulMoves_ = fight_.getUserTeam().getSuccessfulMovesRound();
        assertEq(1, successfulMoves_.size());
        assertEq(1, fight_.getUserTeam().getNbUsesMoves().getVal(CASSE));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(LgInt.one(), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(!fighter_.isNeedingToRecharge());
        assertEq(10, fighter_.powerPointsMove(move_));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPEED));
    }

    @Test
    public void roundThrowerMove1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(ERE_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertEq(1, fighter_.getStatusNbRound(ERE_GEL));
        assertTrue(!fight_.getTemp().isKeepStatus());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(COUD_BOUE,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(FEU_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(!fight_.getTemp().isInvokedMove());
        assertTrue(fight_.getTemp().isSuccessfulInvokation());
        assertEq(0, fighter_.getStatusNbRound(FEU_GEL));
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(9, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("28114/1625"),fighter_.getRemainingHp());
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(PLANNEUR,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = PLANNEUR;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertEq(1, fighter_.getNbPrepaRound());
        assertTrue(fighter_.isDisappeared());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(PLANNEUR,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = PLANNEUR;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.incrementRoundBeforeUsingMove();
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertEq(0, fighter_.getNbPrepaRound());
        assertTrue(!fighter_.isDisappeared());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(COUD_BOUE,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TURBO);
        fighter_.affecterStatut(FEU_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.setNeedingToRecharge(true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertEq(0, fighter_.getStatusNbRound(FEU_GEL));
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(!fighter_.isNeedingToRecharge());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(10, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(COUD_BOUE,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(SANS_LIMITE);
        fighter_.affecterStatut(FEU_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_BOUE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_.setNeedingToRecharge(true);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertEq(0, fighter_.getStatusNbRound(FEU_GEL));
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(!fighter_.isNeedingToRecharge());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(9, fighter_.powerPointsMove(move_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("10607/625"),fighter_.getRemainingHp());
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(FORCE_NATURE,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = FORCE_NATURE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fight_.getTemp().isInvokedMove());
        assertTrue(fight_.getTemp().isSuccessfulInvokation());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(MIMIQUE,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = MIMIQUE;
        fighter_.setLastSufferedMove(BOOST);
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(!fight_.getTemp().isInvokedMove());
        assertTrue(!fight_.getTemp().isSuccessfulInvokation());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(10, fighter_.powerPointsMove(move_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(ROULADE,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = ROULADE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setLastUsedMove(move_);
        fighter_.setUsedMoveLastRound(move_);
        //validate fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(NULL_REF, target_), true);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), BoolVal.TRUE);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(!fight_.getTemp().isInvokedMove());
        assertTrue(fight_.getTemp().isSuccessfulInvokation());
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(10, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertEq(new LgInt("2"), fighter_.getNbRepeatingSuccessfulMoves());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(ROULADE,10L);
        Fight fight_ = processEffectTargets4(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE), new StringList(JACKPOT, PAR_ICI, DANSE_LUNE));
        TeamPosition thrower_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = PAR_ICI;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(!fight_.getTemp().isInvokedMove());
        assertTrue(fight_.getTemp().isSuccessfulInvokation());
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(ROULADE,10L);
        Fight fight_ = processEffectTargets4(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE), new StringList(JACKPOT, PAR_ICI, DANSE_LUNE));
        TeamPosition thrower_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = DANSE_LUNE;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(!fight_.getTemp().isInvokedMove());
        assertTrue(fight_.getTemp().isSuccessfulInvokation());
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(GLAS_DE_SOIN,10L);
        moves_.put(ROULADE,10L);
        Fight fight_ = processEffectTargets3(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.affecterStatut(BRULURE);
        String move_ = GLAS_DE_SOIN;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(!fight_.getTemp().isInvokedMove());
        assertTrue(fight_.getTemp().isSuccessfulInvokation());
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(GLAS_DE_SOIN,10L);
        moves_.put(ROULADE,10L);
        Fight fight_ = processEffectTargets3(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(SAISIE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_.affecterStatut(BRULURE);
        fighter_ = fight_.getFighter(thrower_);
        String move_ = GLAS_DE_SOIN;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, target_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.isSuccessfulMove());
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(!fight_.getTemp().isInvokedMove());
        assertTrue(fight_.getTemp().isSuccessfulInvokation());
        assertTrue(fight_.getTemp().isKeepStatus());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(DEGOMMAGE,10L);
        moves_.put(ROULADE,10L);
        Fight fight_ = processEffectTargets3(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(BAIE_MEPO);
        String move_ = DEGOMMAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("238956/13375"),fighter_.getRemainingHp());
        assertTrue(!fight_.getTemp().isInvokedMove());
        assertTrue(fight_.getTemp().isSuccessfulInvokation());
        assertTrue(fight_.getTemp().isKeepStatus());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(DEGOMMAGE,10L);
        moves_.put(ROULADE,10L);
        Fight fight_ = processEffectTargets3(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(HERBE_MENTAL);
        fighter_.activerAttaque(EMBARGO);
        String move_ = DEGOMMAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterPseudoStatut(thrower_, AMOUR);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("238956/13375"),fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, thrower_)));
        assertTrue(!fight_.getTemp().isInvokedMove());
        assertTrue(fight_.getTemp().isSuccessfulInvokation());
        assertTrue(fight_.getTemp().isKeepStatus());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove16Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(DEGOMMAGE,10L);
        moves_.put(ROULADE,10L);
        Fight fight_ = processEffectTargets3(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(HERBE_MENTAL);
        String move_ = DEGOMMAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterPseudoStatut(thrower_, AMOUR);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("238956/13375"),fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, thrower_)));
        assertTrue(!fight_.getTemp().isInvokedMove());
        assertTrue(fight_.getTemp().isSuccessfulInvokation());
        assertTrue(fight_.getTemp().isKeepStatus());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove17Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PERENITE,10L);
        moves_.put(ROULADE,10L);
        Fight fight_ = processEffectTargets3(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(SAISIE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_.affecterStatut(BRULURE);
        fighter_ = fight_.getFighter(thrower_);
        String move_ = PERENITE;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(!fight_.getTemp().isInvokedMove());
        assertTrue(fight_.getTemp().isSuccessfulInvokation());
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove18Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PERENITE_BIS,10L);
        moves_.put(ROULADE,10L);
        Fight fight_ = processEffectTargets3(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(SAISIE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_.affecterStatut(BRULURE);
        fighter_ = fight_.getFighter(thrower_);
        String move_ = PERENITE_BIS;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(!fight_.getTemp().isInvokedMove());
        assertTrue(fight_.getTemp().isSuccessfulInvokation());
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
        assertEq(9, fighter_.powerPointsMove(move_));
        assertEq(0, fight_.getTemp().getDamageByCurrentUser().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove19Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PROTEEN);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(StringUtil.contains(fighter_.getTypes(), DRAGON));
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove20Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(StringUtil.contains(fighter_.getTypes(), ELECTRIQUE));
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove21Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = COUD_KRANE;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(!fighter_.isDisappeared());
        assertTrue(StringUtil.contains(fighter_.getTypes(), ELECTRIQUE));
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove22Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        String move_ = POSSESSIF;
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(!fighter_.isDisappeared());
        assertTrue(StringUtil.contains(fighter_.getTypes(), ELECTRIQUE));
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets2(data_, diff_, moves_);
        fight_.getTemp().setSimulation(true);
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(ERE_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertEq(1, fighter_.getStatusNbRound(ERE_GEL));
        assertTrue(!fight_.getTemp().isKeepStatus());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundThrowerMove2SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        Fight fight_ = processEffectTargets4(data_, diff_, moves_, new StringList(JACKPOT, PAR_ICI, COPIE), new StringList(DRACO_RAGE, PAR_ICI, COPIE));
        fight_.getTemp().setSimulation(true);
        TeamPosition thrower_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(ERE_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertEq(0, fighter_.getStatusNbRound(ERE_GEL));
        assertTrue(fight_.getTemp().isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    /*@Test
    public void roundThrowerMove21Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Map<String,Integer> moves_ = new Map<String,Integer>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COPIE,10L);
        moves_.put(SEISME,10L);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves(3,moves_));
        userMoves_.add(new LevelMoves(3,moves_));
        userMoves_.add(new LevelMoves(3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT,PAR_ICI,COPIE);
        partnersMoves_.add(new LevelMoves(3,partnerMoves_));
        partnersMoves_.add(new LevelMoves(4,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DRACO_RAGE,PAR_ICI,COPIE);
        foesMoves_.add(new LevelMoves(3,foeMoves_));
        foesMoves_.add(new LevelMoves(4,foeMoves_));
        Fight fight_ = roundThrowerMove(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PROTEEN);
        fighter_.affecterStatut(ERE_GEL);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        fighter_.setFirstChosenMoveTarget(move_, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertEq(1, fighter_.getTypes().size());
        assertEq(1, fighter_.getTypes().size());
        assertEq(0, fighter_.getStatusNbRound(ERE_GEL));
        assertTrue(fight_.isKeepStatus());
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!fight_.getAcceptableChoices());
    }*/

    @Test
    public void roundThrowerHealing1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(DEGOMMAGE,10L);
        moves_.put(ROULADE,10L);
        Fight fight_ = processEffectTargets3(data_, diff_, moves_);
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        FightKo.setKoMoveTeams(fight_, f_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = CENDRESACREE;
        fighter_.setChosenHealingObject(obj_, data_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.CST_PLAYER, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
    }

    @Test
    public void roundThrowerHealing2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        FightKo.setKoMoveTeams(fight_, f_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = EAU_FRAICHE;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(NULL_REF);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.CST_PLAYER, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
    }

    @Test
    public void roundThrowerHealing3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        FightKo.setKoMoveTeams(fight_, f_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = EAU_FRAICHE;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(74, fighter_.getHappiness());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.CST_PLAYER, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
    }

    @Test
    public void roundThrowerHealing4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        String obj_ = TOTAL_SOIN;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        FightKo.setKoMoveTeams(fight_, f_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = RAPPEL;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1873/200"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.CST_PLAYER, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
    }

    @Test
    public void roundThrowerHealing6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = MAX_ELIXIR;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.usePowerPointsByMove(diff_, DRACO_RAGE,  1);
        fighter_.usePowerPointsByMove(diff_, PICOTS,  1);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(10, fighter_.powerPointsMove(DRACO_RAGE));
        assertEq(10, fighter_.powerPointsMove(PICOTS));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = ELIXIR;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.usePowerPointsByMove(diff_, DRACO_RAGE,  1);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(10, fighter_.powerPointsMove(DRACO_RAGE));
        assertEq(10, fighter_.powerPointsMove(PICOTS));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, DRACO_RAGE,  1);
        String obj_ = HUILE;
        fighter_.setChosenHealingObjectMove(obj_, DRACO_RAGE);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(10, fighter_.powerPointsMove(DRACO_RAGE));
        assertEq(10, fighter_.powerPointsMove(PICOTS));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = HUILE;
        fighter_.setChosenHealingObjectMove(obj_, DRACO_RAGE);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(10, fighter_.powerPointsMove(DRACO_RAGE));
        assertEq(10, fighter_.powerPointsMove(PICOTS));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, DRACO_RAGE,  1);
        String obj_ = BAIE_MEPO;
        fighter_.setChosenHealingObjectMove(obj_, DRACO_RAGE);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(10, fighter_.powerPointsMove(DRACO_RAGE));
        assertEq(10, fighter_.powerPointsMove(PICOTS));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_MEPO;
        fighter_.setChosenHealingObjectMove(obj_, DRACO_RAGE);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(10, fighter_.powerPointsMove(DRACO_RAGE));
        assertEq(10, fighter_.powerPointsMove(PICOTS));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_PITAYE;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(LUXE_BALL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_ORAN;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.setRemainedHp(Rate.one());
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("11"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_GOWAV;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.setRemainedHp(Rate.one());
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("2673/800"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_ENIGMA;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(PARALYSIE);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("2273/400"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(1, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing16Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_CERIZ;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(PARALYSIE);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(1, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing17Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = BAIE_CERIZ;
        fight_.getUsedItemsWhileRound().put(obj_,1L);
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(PARALYSIE);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(2, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing18Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = GRAND_RAPPEL;
        fight_.getUsedItemsWhileRound().put(obj_,1L);
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setUsedBallCatching(LUXE_BALL);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertEq(new Rate("6019/400"), fighter_.getRemainingHp());
        assertEq(2, fight_.getUsedItemsWhileRound().getVal(obj_));
        assertEq(72, fighter_.getHappiness());
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animation_.getHealed());
    }

    @Test
    public void roundThrowerHealing19Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        TeamPosition f_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = CENDRESACREE;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
        assertTrue(!fight_.getUsedItemsWhileRound().contains(obj_));
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.CST_FOE, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
    }

    @Test
    public void roundThrowerHealing20Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition p_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        FightKo.setKoMoveTeams(fight_, p_, diff_, data_);
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = CENDRESACREE;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
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
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal( 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal( 1));

        assertEq(2, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.CST_PLAYER, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ONE), animSwitch_.getSubstituted());
        assertEq(ARTIKODIN, animSwitch_.getSubstituteName());
        assertEq(3, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    @Test
    public void roundThrowerHealing21Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets10(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition p_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        FightKo.setKoMoveTeams(fight_, p_, diff_, data_);
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        TeamPosition f_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        Fighter fighter_ = fight_.getFighter(f_);
        String obj_ = CENDRESACREE;
        fighter_.setChosenHealingObject(obj_, data_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, f_, diff_, data_);
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
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal( 0));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal( 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal( 2));

        assertEq(2, fight_.getEffects().size());
        AnimationHealing animation_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(Fight.CST_PLAYER, animation_.getHealed().getTeam());
        assertTrue(animation_.isBackOrTeam());
        assertTrue(animation_.isPlayer());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animSwitch_.getSubstituted());
        assertEq(ARTIKODIN, animSwitch_.getSubstituteName());
        assertEq(3, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }
    @Test
    public void roundThrowerSwitch0Test() {
        DataBase data_ = initDb();
        assertSame(SwitchType.NOTHING, FightRound.swType(data_,""));
    }
    @Test
    public void roundThrowerSwitch1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets14(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition exiting_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition entering_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        Fighter fighter_ = fight_.getFighter(exiting_);
        fighter_.setSubstitute( 1);
        FightRound.initRound(fight_);
        FightRound.roundThrowerSwitch(fight_, exiting_, diff_, data_);
        assertEq(3, fight_.getFirstPositPlayerFighters().size());
        fighter_ = fight_.getFighter(entering_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(exiting_);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal( 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal( 1));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal( 2));
        assertEq(1, fight_.getEffects().size());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), animSwitch_.getSubstituted());
        assertEq(ARTIKODIN, animSwitch_.getSubstituteName());
        assertEq(4, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertTrue(animSwitch_.isPlayer());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    @Test
    public void roundThrowerSwitch2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets8(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition exiting_ = tp(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        TeamPosition entering_ = tp(KEY_PLAYER, POKEMON_FIGHTER_THREE);
        Fighter fighter_ = fight_.getFighter(exiting_);
        fighter_.setSubstitute( 3);
        FightRound.initRound(fight_);
        FightRound.roundThrowerSwitch(fight_, exiting_, diff_, data_);
        assertEq(4, fight_.getFirstPositPlayerFighters().size());
        fighter_ = fight_.getFighter(entering_);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(exiting_);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal( 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal( 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal( 2));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal( 3));
        assertEq(1, fight_.getEffects().size());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ONE), animSwitch_.getSubstituted());
        assertEq(TARTARD, animSwitch_.getSubstituteName());
        assertEq(4, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    @Test
    public void roundThrowerSwitch3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(PICOTS,10L);
        Fight fight_ = processEffectTargets13(data_, diff_, moves_, new StringList(JACKPOT), new StringList(JACKPOT));
        TeamPosition exiting_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        TeamPosition entering_ = tp(KEY_FOE, POKEMON_FIGHTER_TWO);
        Fighter fighter_ = fight_.getFighter(exiting_);
        fighter_.setSubstitute( 2);
        FightRound.initRound(fight_);
        FightRound.roundThrowerSwitch(fight_, exiting_, diff_, data_);
        assertEq(3, fight_.getFirstPositFoeFighters().size());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal( 0));
        assertEq(1, fight_.getFirstPositFoeFighters().getVal( 1));
        assertEq(0, fight_.getFirstPositFoeFighters().getVal( 2));
        fighter_ = fight_.getFighter(entering_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(exiting_);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal( 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal( 1));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal( 2));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal( 3));
        assertEq(1, fight_.getEffects().size());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(tc(KEY_FOE, POKEMON_TARGET_ZERO), animSwitch_.getSubstituted());
        assertEq(TARTARD, animSwitch_.getSubstituteName());
        assertEq(3, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    @Test
    public void substituingAfterRoundThrowerMove1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = processEffectTargets1(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(DETECTION);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, target_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.isSuccessfulMove());
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data_));
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void substituingAfterRoundThrowerMove2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COUD_BOUE,10L);
        Fight fight_ = processEffectTargets1(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(COUD_BOUE, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data_));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void substituingAfterRoundThrowerMove3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COUD_BOUE,10L);
        Fight fight_ = processEffectTargets12(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data_));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void substituingAfterRoundThrowerMove4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COUD_BOUE,10L);
        Fight fight_ = processEffectTargets1(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), diff_, data_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data_));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void substituingAfterRoundThrowerMove5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COUD_BOUE,10L);
        Fight fight_ = processEffectTargets1(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!fighter_.isActed());
        assertTrue(FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data_));
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(fighter_.isActed());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal( 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal( 1));
    }

    @Test
    public void substituingAfterRoundThrowerMove6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COUD_BOUE,10L);
        Fight fight_ = processEffectTargets11(data_, diff_, moves_, new StringList(DEMI_TOUR), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO),  3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data_));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal( 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal( 1));
    }

    @Test
    public void substituingAfterRoundThrowerMove7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COUD_BOUE,10L);
        Fight fight_ = processEffectTargets11(data_, diff_, moves_, new StringList(DEMI_TOUR), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data_));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal( 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal( 1));
    }

    @Test
    public void substituingAfterRoundThrowerMove8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(COUD_BOUE,10L);
        Fight fight_ = processEffectTargets10(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertTrue(!FightRound.substituingAfterRoundThrowerMove(fight_, thrower_, diff_, data_));
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal( 0));
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(DRACO_RAGE,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = processEffectTargets1(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        FightRound.initRound(fight_);
        assertEq(0, FightRound.selectTargetHavingToPlayAfterThrower(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), data_).size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = processEffectTargets1(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        assertEq(0, FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data_).size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = processEffectTargets1(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        fighter_.successUsingMove();
        assertEq(0, FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data_).size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(APRES_VOUS,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = processEffectTargets1(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(APRES_VOUS, tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(target_);
        fighter_.setActed(true);
        assertEq(0, FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data_).size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(APRES_VOUS,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = processEffectTargets8(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(APRES_VOUS, tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        fighter_ = fight_.getFighter(target_);
        fighter_.setSubstitute( 3);
        FightRound.initRound(fight_);
        FightRound.roundThrowerSwitch(fight_, target_, diff_, data_);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(target_);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal( 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal( 1));
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        TeamPositionList targets_ = FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data_);
        assertEq(0, targets_.size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(APRES_VOUS,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = processEffectTargets1(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(APRES_VOUS, tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        TeamPositionList targets_ = FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data_);
        assertEq(1, targets_.size());
        assertTrue(targets_.containsObj(target_));
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(A_LA_QUEUE,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = processEffectTargets1(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(A_LA_QUEUE, tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        TeamPositionList targets_ = FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data_);
        assertEq(0, targets_.size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(COUD_BOUE,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = processEffectTargets9(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(COUD_BOUE, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        TeamPositionList targets_ = FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data_);
        assertEq(0, targets_.size());
    }

    @Test
    public void selectTargetHavingToPlayAfterThrower9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = processEffectTargets1(data_, diff_, moves_, new StringList(JACKPOT), new StringList(DETECTION));
        TeamPosition thrower_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition target_ = tp(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(SEISME);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        TeamPositionList targets_ = FightRound.selectTargetHavingToPlayAfterThrower(fight_, thrower_, data_);
        assertEq(0, targets_.size());
    }

    @Test
    public void calculateCatchingRate1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, PIKACHU, 3);
        fight_.wildPokemon().setRemainedHp(new Rate("89/10"));
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, fight_.getUserTeam().playerFighterAtIndex( 0).first(), HYPER_BALL, false, diff_, data_, fight_.wildPokemon()));
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, fight_.getUserTeam().playerFighterAtIndex( 0).first(), HYPER_BALL, true, diff_, data_, fight_.wildPokemon()));
        diff_.setAllowCatchingKo(false);
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, fight_.getUserTeam().playerFighterAtIndex( 0).first(), HYPER_BALL, true, diff_, data_, fight_.wildPokemon()));
        fight_.wildPokemon().setRemainedHp(Rate.one());
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, fight_.getUserTeam().playerFighterAtIndex( 0).first(), HYPER_BALL, true, diff_, data_, fight_.wildPokemon()));
    }

    @Test
    public void calculateCatchingRate2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, PIKACHU, 3);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, fight_.getUserTeam().playerFighterAtIndex( 0).first(), HYPER_BALL, false, diff_, data_, fight_.wildPokemon()));
    }

    @Test
    public void calculateCatchingRate3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, PIKACHU, 3);
        fight_.wildPokemon().affecterStatut(SOMMEIL);
        fight_.wildPokemon().setRemainedHp(new Rate("89/10"));
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, fight_.getUserTeam().playerFighterAtIndex( 0).first(), HYPER_BALL, false, diff_, data_, fight_.wildPokemon()));
    }

    @Test
    public void calculateCatchingRate4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, PIKACHU, 3);
        fight_.wildPokemon().affecterStatut(SOMMEIL);
        assertEq(new Rate("0"),FightRound.calculateCatchingRate(fight_, fight_.getUserTeam().playerFighterAtIndex( 0).first(), HYPER_BALL, false, diff_, data_, fight_.wildPokemon()));
    }

    @Test
    public void calculateCatchingRate5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, PIKACHU, 3);
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, fight_.getUserTeam().playerFighterAtIndex( 0).first(), MASTER_BALL, false, diff_, data_, fight_.wildPokemon()));
    }

    @Test
    public void calculateCatchingRate6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, TETARTE, 3);
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, fight_.getUserTeam().playerFighterAtIndex( 0).first(), MASTER_BALL, false, diff_, data_, fight_.wildPokemon()));
    }

    @Test
    public void calculateCatchingRate7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, TETARTE, 3);
        assertEq(new Rate("1"),FightRound.calculateCatchingRate(fight_, fight_.getUserTeam().playerFighterAtIndex( 0).first(), PAS_DE_BALL, false, diff_, data_, fight_.wildPokemon()));
    }

    @Test
    public void calculateFleeingRate1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(true);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, PIKACHU, 3);
        MonteCarloNumber mcn_ = FightRound.calculateFleeingRate(fight_, diff_, data_);
        assertEq(1,mcn_.nbEvents());
        assertEq(new Rate("1"), mcn_.getEvent(0));
    }

    @Test
    public void calculateFleeingRate2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, PIKACHU, 3);
        MonteCarloNumber mcn_ = FightRound.calculateFleeingRate(fight_, diff_, data_);
        assertEq(1,mcn_.nbEvents());
        assertEq(new Rate("1"), mcn_.getEvent(0));
    }

    @Test
    public void calculateFleeingRate3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, PIKACHU, 10);
        MonteCarloNumber mcn_ = FightRound.calculateFleeingRate(fight_, diff_, data_);
        assertEq(1,mcn_.nbEvents());
        assertEq(new Rate("0"), mcn_.getEvent(0));
    }

    @Test
    public void calculateFleeingRate4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, MELOFEE, 10);
        MonteCarloNumber mcn_ = FightRound.calculateFleeingRate(fight_, diff_, data_);
        assertEq(1,mcn_.nbEvents());
        assertEq(new Rate("0"), mcn_.getEvent(0));
    }

    @Test
    public void calculateFleeingRate5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, TARINOR, 10);
        MonteCarloNumber mcn_ = FightRound.calculateFleeingRate(fight_, diff_, data_);
        assertEq(1,mcn_.nbEvents());
        assertEq(new Rate("0"), mcn_.getEvent(0));
    }

    @Test
    public void calculateFleeingRate6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, TARINOR, 10);
        fight_.wildPokemon().setRemainingHp(Rate.zero());
        MonteCarloNumber mcn_ = FightRound.calculateFleeingRate(fight_, diff_, data_);
        assertEq(1,mcn_.nbEvents());
        assertEq(new Rate("1"), mcn_.getEvent(0));
    }

    @Test
    public void calculateFleeingRate7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(SEISME,10L);
        moves_.put(DEMI_TOUR,10L);
        Fight fight_ = calculateCatchingRate1(data_, diff_, moves_, TARINOR, 10);
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setCaught(true);
        MonteCarloNumber mcn_ = FightRound.calculateFleeingRate(fight_, diff_, data_);
        assertEq(1,mcn_.nbEvents());
        assertEq(new Rate("1"), mcn_.getEvent(0));
    }
    @Test
    public void nextFighters1Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer( 0);
        diff_.setIvFoe( 0);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 18);
//        lasPk_ = new PokemonPlayer(pokemon_,data_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 19);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = nextFighters2(data_, diff_, player_, new StringList(BULLES_D_O));
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition ally_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        TeamPosition foePkBis_ = tp(KEY_FOE, POKEMON_FIGHTER_ONE);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ONE));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePkBis_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        FightRound.initRound(fight_);
        NextUsers p_;
        p_ = FightRound.nextFighters(fight_, new TeamPositionList(), data_);
        assertEq(1, p_.getNextFighters().size());
        assertTrue(p_.getNextFighters().containsObj(ally_));
        assertEq(0, p_.getItemUsers().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void nextFighters2Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer( 0);
        diff_.setIvFoe( 0);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 18);
//        lasPk_ = new PokemonPlayer(pokemon_,data_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 19);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = nextFighters2(data_, diff_, player_, new StringList(BULLES_D_O));
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition ally_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        TeamPosition foePkBis_ = tp(KEY_FOE, POKEMON_FIGHTER_ONE);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ONE));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePkBis_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        FightRound.initRound(fight_);
        NextUsers p_;
        p_ = FightRound.nextFighters(fight_, TeamPositionList.newList(userPk_), data_);
        assertEq(1, p_.getNextFighters().size());
        assertTrue(p_.getNextFighters().containsObj(userPk_));
        assertEq(0, p_.getItemUsers().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void nextFighters3Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer( 0);
        diff_.setIvFoe( 0);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 18);
//        lasPk_ = new PokemonPlayer(pokemon_,data_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 19);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = nextFighters2(data_, diff_, player_, new StringList(BULLES_D_O));
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition ally_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        TeamPosition foePkBis_ = tp(KEY_FOE, POKEMON_FIGHTER_ONE);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ONE));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePkBis_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        FightRound.initRound(fight_);
        fight_.getFighter(userPk_).setActed(true);
        fight_.getFighter(ally_).setActed(true);
        fight_.getFighter(foePk_).setActed(true);
        fight_.getFighter(foePkBis_).setActed(true);
        NextUsers p_;
        p_ = FightRound.nextFighters(fight_, new TeamPositionList(), data_);
        assertEq(0, p_.getNextFighters().size());
        assertEq(0, p_.getItemUsers().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void nextFighters4Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer( 0);
        diff_.setIvFoe( 0);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 18);
//        lasPk_ = new PokemonPlayer(pokemon_,data_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 19);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = nextFighters2(data_, diff_, player_, new StringList(BULLES_D_O));
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition ally_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ONE));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getFighter(userPk_).setActed(true);
        fight_.getFighter(ally_).setActed(true);
        fight_.getFighter(foePk_).setActed(true);
        NextUsers p_;
        p_ = FightRound.nextFighters(fight_, new TeamPositionList(), data_);
        assertEq(0, p_.getNextFighters().size());
        assertEq(0, p_.getItemUsers().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void nextFighters5Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer( 0);
        diff_.setIvFoe( 0);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 18);
//        lasPk_ = new PokemonPlayer(pokemon_,data_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 19);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = nextFighters2(data_, diff_, player_, new StringList(BULLES_D_O));
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.setSubstitute( 2);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ONE));
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        NextUsers p_;
        p_ = FightRound.nextFighters(fight_, new TeamPositionList(), data_);
        assertEq(1, p_.getNextFighters().size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), p_.getNextFighters().first());
        assertEq(0, p_.getItemUsers().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void nextFighters6Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer( 0);
        diff_.setIvFoe( 0);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 18);
//        lasPk_ = new PokemonPlayer(pokemon_,data_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 19);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = nextFighters2(data_, diff_, player_, new StringList(BULLES_D_O));
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.setSubstitute( 2);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(EAU_FRAICHE, data_);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        NextUsers p_;
        p_ = FightRound.nextFighters(fight_, new TeamPositionList(), data_);
        assertEq(1, p_.getNextFighters().size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), p_.getNextFighters().first());
        assertEq(0, p_.getItemUsers().size());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void nextFighters1SimulationTest(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer( 0);
        diff_.setIvFoe( 0);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
//        lasPk_ = new PokemonPlayer(pokemon_,data_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 19);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = nextFighters2(data_, diff_, player_, new StringList(BULLES_D_O));
        fight_.getTemp().setSimulation(true);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition ally_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        TeamPosition foePkBis_ = tp(KEY_FOE, POKEMON_FIGHTER_ONE);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ONE));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePkBis_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        FightRound.initRound(fight_);
        FightRound.nextFighters(fight_, new TeamPositionList(), data_);
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 24);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending4(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 100);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending4(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NINGALE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 16);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NINGALE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 16);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NINGALE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 16);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION), 3);
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(FightFacade.loose(fight_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION), 3);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.enableGlobalMove(TEMPETESABLE);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(FightFacade.loose(fight_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION), 3);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.enableGlobalMove(TEMPETESABLE);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(FightFacade.win(fight_));
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 100);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION), 3);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.enableGlobalMove(TEMPETESABLE);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(FightFacade.win(fight_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 100);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending4(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
//        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundShowActions1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION), 3);
        fight_.getTemp().setSimulation(true);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.enableGlobalMove(TEMPETESABLE);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(FightFacade.loose(fight_));
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void calculateNextFighters1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertTrue(fight_.getTemp().getRemainingFighters().containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO),fight_.getCurrentUser());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void calculateNextFighters2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_.backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertTrue(fight_.getTemp().getRemainingFighters().containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO),fight_.getCurrentUser());
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        assertTrue(fighter_.isUsingItem());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void calculateNextFighters3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.backUpObject(BAIE_CHERIM);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_.backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertTrue(fight_.getTemp().getRemainingFighters().containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO),fight_.getCurrentUser());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void calculateNextFighters4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.backUpObject(BAIE_CHERIM);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_.backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(userPk_);
        fighter_.setActed(true);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setActed(true);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        assertEq(0, fight_.getTemp().getRemainingFighters().size());
        fighter_ = fight_.getFighter(userPk_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(foePk_);
        assertTrue(!fighter_.isUsingItem());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void calculateNextFighters5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        fight_.getTemp().setKeepRound(true);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertTrue(fight_.getTemp().getRemainingFighters().containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO),fight_.getCurrentUser());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(fight_.getTemp().isKeepRound());
    }

    @Test
    public void calculateNextFighters6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_.backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        fight_.getTemp().setKeepRound(true);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertTrue(fight_.getTemp().getRemainingFighters().containsObj(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)));
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO),fight_.getCurrentUser());
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        assertTrue(fighter_.isUsingItem());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(fight_.getTemp().isKeepRound());
    }

    @Test
    public void calculateNextFighters7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        Fighter fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.backUpObject(BAIE_CHERIM);
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_.backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        fight_.getTemp().setKeepRound(true);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertTrue(fight_.getTemp().getRemainingFighters().containsObj(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)));
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO),fight_.getCurrentUser());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(fight_.getTemp().isKeepRound());
    }

    @Test
    public void calculateNextFighters8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_.backUpObject(BAIE_CHERIM);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_.backUpObject(BAIE_CHERIM);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(userPk_);
        fighter_.setActed(true);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setActed(true);
        fight_.getTemp().setKeepRound(true);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        assertEq(0, fight_.getTemp().getRemainingFighters().size());
        fighter_ = fight_.getFighter(userPk_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(foePk_);
        assertTrue(!fighter_.isUsingItem());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(!fight_.getTemp().isKeepRound());
    }

    @Test
    public void calculateNextFighters1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer( 0);
        diff_.setIvFoe( 0);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
//        lasPk_ = new PokemonPlayer(pokemon_,data_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 19);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = nextFighters2(data_, diff_, player_, new StringList(BULLES_D_O));
        fight_.getTemp().setSimulation(true);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition ally_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        TeamPosition foePkBis_ = tp(KEY_FOE, POKEMON_FIGHTER_ONE);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ONE));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePkBis_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void calculateNextFighters2SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer( 0);
        diff_.setIvFoe( 0);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
//        lasPk_ = new PokemonPlayer(pokemon_,data_);
        pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 19);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = nextFighters2(data_, diff_, player_, new StringList(BULLES_D_O));
        fight_.getTemp().setSimulation(true);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition ally_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        TeamPosition foePkBis_ = tp(KEY_FOE, POKEMON_FIGHTER_ONE);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(ally_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_FOE, POKEMON_TARGET_ONE));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePkBis_);
        fighter_.setFirstChosenMoveTarget(BULLES_D_O, tc(KEY_PLAYER, POKEMON_TARGET_ONE));
        FightRound.initRound(fight_);
        fight_.getTemp().setKeepRound(true);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        assertTrue(!fight_.getTemp().getAcceptableChoices());
        assertTrue(!fight_.getTemp().isKeepRound());
    }

    @Test
    public void setAllyChoices1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition ally_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        fight_.getAllyChoice().put(MoveTarget.def(), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(CHARGE, action_.getFinalChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setAllyChoices2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition ally_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        fight_.getAllyChoice().put(new MoveTarget(TORGNOLES,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(DETECTION,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(CHARGE, action_.getFinalChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setAllyChoices3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition ally_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ONE));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        fight_.getAllyChoice().put(new MoveTarget(TORGNOLES,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(DETECTION,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(CHARGE, action_.getFinalChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setAllyChoices4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(SEISME,15L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition ally_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getAllyChoice().put(new MoveTarget(SEISME,TargetCoords.def()), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        fight_.getAllyChoice().put(new MoveTarget(TORGNOLES,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(DETECTION,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(CHARGE, action_.getFinalChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setAllyChoices5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setSubstitute( 1);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(CHARGE, action_.getFinalChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setAllyChoices6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(NULL_REF,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
    }

    @Test
    public void roundUser1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        fight_.getTemp().setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(!fight_.getTemp().isKeepRound());
    }

    @Test
    public void roundUser2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setSubstitute( 1);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        fight_.getTemp().setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data_);
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), fight_.getCurrentUser());
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertTrue(fighter_.estArriere());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertEq(new Rate("92/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(fight_.getTemp().isKeepRound());
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void roundUser3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        fight_.getTemp().setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data_);
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fight_.getCurrentUser());
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(!fight_.getTemp().isKeepRound());
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void roundUser4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        fight_.getTemp().setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data_);
        fight_.getFighter(userPk_).setSubstitute( 1);
        TeamPosition currentUser_ = fight_.getCurrentUser();
        FightRound.roundThrowerSwitch(fight_, currentUser_, diff_, data_);
        fight_.setState(FightState.ATTAQUES);
        TeamPositionList cbts_=FightRound.selectTargetHavingToPlayAfterThrower(fight_,currentUser_,data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(cbts_);
        FightOrder.sortFightersUsingMoveAmongList(fight_,data_);
        cbts_ = fight_.getTemp().getOrderedFighters();
        FightRound.calculateNextFighters(fight_, cbts_, data_);
        fight_.getTemp().setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data_);
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), fight_.getCurrentUser());
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(fight_.getTemp().isKeepRound());
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void roundUser5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        fight_.getTemp().setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data_);
        fight_.setState(FightState.ATTAQUES);
        TeamPosition currentUser_ = fight_.getCurrentUser();
        TeamPositionList cbts_=FightRound.selectTargetHavingToPlayAfterThrower(fight_,currentUser_,data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(cbts_);
        FightOrder.sortFightersUsingMoveAmongList(fight_,data_);
        cbts_ = fight_.getTemp().getOrderedFighters();
        FightRound.calculateNextFighters(fight_, cbts_, data_);
        fight_.getTemp().setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data_);
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), fight_.getCurrentUser());
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(fight_.getTemp().isKeepRound());
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void roundUser6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 12);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.getRemainingHp().affect(new Rate("1"));
        fighter_.setChosenHealingObject(EAU_FRAICHE, data_);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        fight_.getTemp().setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data_);
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), fight_.getCurrentUser());
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fight_.getTemp().isKeepRound());
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void roundUser7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        fight_.getTemp().setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data_);
        fight_.getFighter(userPk_).setSubstitute( 1);
        TeamPosition currentUser_ = fight_.getCurrentUser();
        FightRound.roundThrowerSwitch(fight_, currentUser_, diff_, data_);
        fight_.setState(FightState.ATTAQUES);
        TeamPositionList cbts_=FightRound.selectTargetHavingToPlayAfterThrower(fight_,currentUser_,data_);
        fight_.getTemp().getOrderedFighters().clear();
        fight_.getTemp().getOrderedFighters().addAllElts(cbts_);
        FightOrder.sortFightersUsingMoveAmongList(fight_,data_);
        cbts_ = fight_.getTemp().getOrderedFighters();
        FightRound.calculateNextFighters(fight_, cbts_, data_);
        fight_.getTemp().setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data_);
        FightRound.roundUser(fight_, diff_, data_);
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), fight_.getCurrentUser());
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("525511/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(!fight_.getTemp().isKeepRound());
        assertEq(0, fight_.getTemp().getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void roundUser1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 12);
        fight_.getTemp().setSimulation(true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setRemainedHp(Rate.one());
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setSubstitute( 1);
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        fight_.getTemp().setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data_);
        assertTrue(!fight_.getTemp().getAcceptableChoices());
        assertTrue(!fight_.getTemp().isKeepRound());
    }

    @Test
    public void roundUser2SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DRACO_RAGE, CHARGE), 3);
        fight_.getTemp().setSimulation(true);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(DRACO_RAGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.initRound(fight_);
        FightRound.calculateNextFighters(fight_, new TeamPositionList(), data_);
        fight_.getTemp().setKeepRound(true);
        FightRound.roundUser(fight_, diff_, data_);
        assertTrue(!fight_.getTemp().getAcceptableChoices());
        assertTrue(!fight_.getTemp().isKeepRound());
    }

    @Test
    public void beginRound1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.beginRound(fight_, diff_, data_);
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fight_.getCurrentUser());
        assertTrue(fight_.getTemp().isKeepRound());
        assertEq(CHARGE, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getFirstChosenMove());
        //assertTrue(!fight_.getBeginRound());
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fight_.getCurrentUser());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void beginRound2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.beginRound(fight_, diff_, data_);
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fight_.getCurrentUser());
        assertTrue(fight_.getTemp().isKeepRound());
        //assertTrue(!fight_.getBeginRound());
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fight_.getCurrentUser());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void beginRound3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.beginRound(fight_, diff_, data_);
        FightRound.roundUser(fight_, diff_, data_);
        fight_.getFighter(userPk_).setSubstitute( 1);
        FightRound.beginRound(fight_, diff_, data_);
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), fight_.getCurrentUser());
        //FightRound.roundUser(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(fight_.getTemp().isKeepRound());
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), fight_.getCurrentUser());
        assertEq(1, fight_.getEffects().size());
        AnimationSwitch anim_ = (AnimationSwitch) fight_.getEffects().first();
        assertEq(PTITARD, anim_.getSubstituteName());
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), anim_.getSubstituted());
        assertEq(17,anim_.getLevel());
        assertEq(new LgInt("100"), anim_.getRateRemainHp());
        assertTrue(!anim_.isKo());
        assertEq(new LgInt("0"), anim_.getWonExpRate());
    }

    @Test
    public void beginRound4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.beginRound(fight_, diff_, data_);
        FightRound.roundUser(fight_, diff_, data_);
        FightRound.beginRound(fight_, diff_, data_);
        //FightRound.roundUser(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(fight_.getTemp().isKeepRound());
        assertEq(1, fight_.getTemp().getRemainingFighters().size());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), fight_.getCurrentUser());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void beginRound5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 12);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.beginRound(fight_, diff_, data_);
        FightRound.roundUser(fight_, diff_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fight_.getFighter(userPk_).setSubstitute( 1);
        FightRound.beginRound(fight_, diff_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(!fight_.getTemp().isKeepRound());
        assertEq(0, fight_.getTemp().getRemainingFighters().size());
        assertEq(1, fight_.getEffects().size());
        AnimationSwitch anim_ = (AnimationSwitch) fight_.getEffects().first();
        assertEq(PTITARD, anim_.getSubstituteName());
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ZERO), anim_.getSubstituted());
        assertEq(17,anim_.getLevel());
        assertEq(new LgInt("100"), anim_.getRateRemainHp());
        assertTrue(!anim_.isKo());
        assertEq(new LgInt("0"), anim_.getWonExpRate());
    }

    @Test
    public void beginRound1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 12);
        fight_.getTemp().setSimulation(true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setRemainedHp(Rate.one());
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.beginRound(fight_, diff_, data_);
        FightRound.roundUser(fight_, diff_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        fight_.getFighter(userPk_).setSubstitute( 1);
        FightRound.beginRound(fight_, diff_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("0"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundFight1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.beginRound(fight_, diff_, data_);
        FightRound.roundUser(fight_, diff_, data_);
        FightRound.endRoundFight(fight_, diff_, player_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundFight2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.beginRound(fight_, diff_, data_);
        FightRound.roundUser(fight_, diff_, data_);
        FightRound.endRoundFight(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundFight3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.setAllyChoices(fight_, data_);
        FightRound.beginRound(fight_, diff_, data_);
        FightRound.roundUser(fight_, diff_, data_);
        fight_.getFighter(userPk_).setSubstitute( 1);
        FightRound.beginRound(fight_, diff_, data_);
        FightRound.roundUser(fight_, diff_, data_);
        FightRound.roundUser(fight_, diff_, data_);
        FightRound.endRoundFight(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("525511/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        assertTrue(!fight_.getTemp().isKeepRound());
        assertEq(0, fight_.getTemp().getRemainingFighters().size());
        assertTrue(!FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void changeKo() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.FALSE);
        fight_.getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        assertFalse(FightRound.changeKo(fight_, data_,true));
    }

    @Test
    public void roundAllThrowers1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending3(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setSubstitute( 1);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("10674449/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("40396/2675"), fighter_.getRemainingHp());
        assertEq(new Rate("92/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fight_.getFighter(userPk_).setSubstitute( 1);
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("525511/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("525511/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("2571/25"), fighter_.getRemainingHp());
        assertEq(new Rate("2571/25"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("0"), fighter_.getRemainingHp());
        assertEq(new Rate("92/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertEq(new Rate("92/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 100);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(REVEIL_FORCE, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("271"), fighter_.getRemainingHp());
        assertEq(new Rate("271"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("0"), fighter_.getRemainingHp());
        assertEq(new Rate("92/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertEq(new Rate("92/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        //assertTrue(!FightEndRound.existSubstitute(fight_));
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
        //assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(CHARGE,tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("9481049/238700"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("525511/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fight_.getAllyChoice().clear();
        fight_.getFighter(userPk_).setChosenHealingObject(EAU_FRAICHE, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setChosenHealingObject(EAU_FRAICHE, data_);
        fight_.getFighter(foePk_).setChosenHealingObject(EAU_FRAICHE, data_);
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        assertEq(new Rate("1933/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1933/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("218/5"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 12);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fight_.getFighter(userPk_).setSubstitute( 1);
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowers1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DRACO_RAGE, CHARGE), 3);
        fight_.getTemp().setSimulation(true);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(DRACO_RAGE, tc(KEY_PLAYER, POKEMON_TARGET_ZERO));
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        assertTrue(!fight_.getTemp().getAcceptableChoices());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void roundAllThrowers2SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 3);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DRACO_RAGE, CHARGE), 3);
        fight_.getTemp().setSimulation(true);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition partner_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setSubstitute( 1);
        fight_.getFighter(partner_).setRemainedHp(Rate.one());
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        assertTrue(!fight_.getTemp().getAcceptableChoices());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void roundAllThrowers3SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 17);
        StringMap<Long> map_ = new StringMap<Long>();
        map_.put(DEMI_TOUR,20L);
        PokemonPlayer lasPk_ = pkMoves(data_, diff_, pokemon_, map_);
        player_.getTeam().add(lasPk_);
        lasPk_ = pkMoves(data_, diff_, pokemon_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = nextFightersSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), 12);
        fight_.getTemp().setSimulation(true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setRemainedHp(Rate.one());
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        TeamPosition userPk_ = tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        TeamPosition foePk_ = tp(KEY_FOE, POKEMON_FIGHTER_ZERO);
        Fighter fighter_ = fight_.getFighter(userPk_);
        fighter_.setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
        fight_.getFighter(userPk_).setSubstitute( 1);
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        fighter_ = fight_.getFighter(userPk_);
        assertEq(new Rate("4587/100"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE);
        assertEq(new Rate("0"), fighter_.getRemainingHp());
        assertEq(new Rate("4587/100"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(foePk_);
        assertEq(new Rate("547571/17375"), fighter_.getRemainingHp());
        assertEq(new Rate("218/5"), fighter_.pvMax());
        assertTrue(!fighter_.isSuccessfulMove());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(!fight_.getTemp().getAcceptableChoices());
    }

    private Fight nextFightersSending4(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        foeTeam_.add(pkTrainer(3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        Fight f_ = nextFightersDual(_player, _diff, _data, foeTeam_, allyTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, f_);
    }

    private Fight nextFightersSending3(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        Fight f_ = nextFightersDual(_player, _diff, _data, foeTeam_, allyTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, f_);
    }

    private Fight nextFightersSending2(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(12, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        Fight f_ = nextFightersDual(_player, _diff, _data, foeTeam_, allyTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, f_);
    }

    private Fight nextFighters2(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(16, _foeMoves));
        foeTeam_.add(pkTrainer(15, _foeMoves));
        foeTeam_.add(pkTrainer(15, _foeMoves));
        return nextFightersGym(_player, _diff, _data, foeTeam_, 2);
    }

    private static Fight firstEffectWhileSendingTeams(Difficulty _diff, DataBase _data, Fight _fight) {
        FightSending.firstEffectWhileSendingTeams(_fight, _diff, _data);
        return _fight;
    }

    private static Fight nextFightersGym(Player _user, Difficulty _diff, DataBase _data, CustList<PkTrainer> _foeTeam) {
        Fight fight_ = FightFacade.newFight();
        GymLeader leader_ = new GymLeader();
        leader_.setTeam(_foeTeam);
        leader_.setReward( 200);
        FightFacade.initFight(fight_, _user, _diff, leader_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    private static Fight nextFightersGym(Player _user, Difficulty _diff, DataBase _data, CustList<PkTrainer> _foeTeam, int _mult) {
        Fight fight_ = FightFacade.newFight();
        GymLeader leader_ = new GymLeader();
        leader_.setTeam(_foeTeam);
        leader_.setMultiplicityFight( _mult);
        leader_.setReward( 200);
        FightFacade.initFight(fight_, _user, _diff, leader_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    private static Fight nextFightersDual(Player _user, Difficulty _diff, DataBase _data, CustList<PkTrainer> _foeTeam, CustList<PkTrainer> _allyTeam) {
        Fight fight_ = FightFacade.newFight();
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        ally_.setTeam(_allyTeam);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(_foeTeam);
        trainer_.setReward( 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_, _user, _diff, dual_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    private Fight calculateCatchingRate1(DataBase _data, Difficulty _diff, StringMap<Long> _moves, String _name, int _level) {
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

    private Fight processEffectTargets14(DataBase _data, Difficulty _diff, StringMap<Long> _moves, StringList _partnerMoves, StringList _foeMoves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data, 4, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        return processEffectTargetsDual(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight processEffectTargets13(DataBase _data, Difficulty _diff, StringMap<Long> _moves, StringList _partnerMoves, StringList _foeMoves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data, 4, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        allyTeam_.add(pkTrainer(4, _partnerMoves));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        foeTeam_.add(pkTrainer(3, _foeMoves));
        foeTeam_.add(pkTrainer(3, _foeMoves));
        return processEffectTargetsDual(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight processEffectTargets12(DataBase _data, Difficulty _diff, StringMap<Long> _moves, StringList _partnerMoves, StringList _foeMoves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        return processEffectTargetsDual(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight processEffectTargets11(DataBase _data, Difficulty _diff, StringMap<Long> _moves, StringList _partnerMoves, StringList _foeMoves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data, 4, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        return processEffectTargetsDual(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight processEffectTargets10(DataBase _data, Difficulty _diff, StringMap<Long> _moves, StringList _partnerMoves, StringList _foeMoves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        return processEffectTargetsDual(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight processEffectTargets9(DataBase _data, Difficulty _diff, StringMap<Long> _moves, StringList _partnerMoves, StringList _foeMoves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data, 4, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        foeTeam_.add(pkTrainer(3, _foeMoves));
        return processEffectTargetsDual(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight processEffectTargets8(DataBase _data, Difficulty _diff, StringMap<Long> _moves, StringList _partnerMoves, StringList _foeMoves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data, 4, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        allyTeam_.add(pkTrainer(4, _partnerMoves));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        return processEffectTargetsDual(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight processEffectTargets7(DataBase _data, Difficulty _diff, StringMap<Long> _moves, StringList _partnerMoves, StringList _foeMoves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data, 100, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        allyTeam_.add(pkTrainer(4, _partnerMoves));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        foeTeam_.add(pkTrainer(4, _foeMoves));
        return processEffectTargetsDual(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight processEffectTargets6(DataBase _data, Difficulty _diff, StringMap<Long> _moves, StringList _foeMoves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        foeTeam_.add(pkTrainer(4, _foeMoves));
        return processEffectTargetsDual(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight processEffectTargets5(DataBase _data, Difficulty _diff, StringMap<Long> _moves, StringList _foeMoves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        foeTeam_.add(pkTrainer(4, _foeMoves));
        return processEffectTargetsDual(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight processEffectTargets4(DataBase _data, Difficulty _diff, StringMap<Long> _moves, StringList _partnerMoves, StringList _foeMoves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        allyTeam_.add(pkTrainer(4, _partnerMoves));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        foeTeam_.add(pkTrainer(4, _foeMoves));
        return processEffectTargetsDual(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight processEffectTargets3(DataBase _data, Difficulty _diff, StringMap<Long> _moves) {
        return processEffectTargets4(_data, _diff, _moves, new StringList(JACKPOT, PAR_ICI, COPIE), new StringList(JACKPOT, PAR_ICI, SAISIE));
    }

    private Fight processEffectTargets1(DataBase _data, Difficulty _diff, StringMap<Long> _moves, StringList _partnerMoves, StringList _foeMoves) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        player_.getTeam().add(pkPlayer(_diff, _data, 3, _moves));
        player_.getTeam().add(pkPlayer(_diff, _data, 4, _moves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer(3, _partnerMoves));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(3, _foeMoves));
        return processEffectTargetsDual(_diff, _data, player_, allyTeam_, foeTeam_);
    }

    private Fight processEffectTargets2(DataBase _data, Difficulty _diff, StringMap<Long> _moves) {
        return processEffectTargets4(_data, _diff, _moves, new StringList(JACKPOT, PAR_ICI, COPIE), new StringList(JACKPOT, PAR_ICI, COPIE));
    }

    private Fight nextFightersSending1(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves, int _l) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer(_l, _foeMoves));
        Fight f_ = nextFightersGym(_player, _diff, _data, foeTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, f_);
    }

    private static Fight processEffectTargetsDual(Difficulty _diff, DataBase _data, Player _player, CustList<PkTrainer> _allyTeam, CustList<PkTrainer> _foeTeam) {
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

    private static PokemonPlayer pkPlayer(Difficulty _diff, DataBase _data, int _level, StringMap<Long> _moves) {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel(_level);
        PokemonPlayer lasPk_ = pkMoves(_data, _diff, pokemon_, _moves);
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

    private static void processEffectTargets(Fight _fight, TeamPosition _thrower, TeamPosition _target, boolean _firstEffect, int _firstIndex, Ints _previousEffect, Difficulty _diff, DataBase _data) {
        FightRound.processEffectTargets(_fight, _thrower, _target,new FightEffectState(_firstEffect, _firstIndex, _previousEffect), _diff, _data);
    }

    private static void useBoostForAccuracy(Fight _fight, TeamPosition _thrower, TeamPosition _target, String _move, boolean _maxAccuracy, boolean _withoutFailObject, boolean _partialAccuracy, DataBase _data) {
        FightRound.useBoostForAccuracy(_fight, _thrower, _target, _move, _data, FightRound.result(_maxAccuracy, _withoutFailObject, _partialAccuracy));
    }

}
