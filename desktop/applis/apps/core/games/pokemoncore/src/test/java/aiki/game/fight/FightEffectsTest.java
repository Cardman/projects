package aiki.game.fight;

import aiki.fight.moves.effects.*;
import aiki.game.fight.animations.*;
import aiki.game.player.enums.Sex;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import org.junit.Test;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.MoveData;
import aiki.game.UsesOfMove;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.CopiedMove;
import aiki.game.fight.util.UserTarget;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.player.Player;
import aiki.map.characters.Ally;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymLeader;
import aiki.map.characters.TempTrainer;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;

import code.util.StringList;
import code.util.StringMap;
import aiki.util.*;

public class FightEffectsTest extends InitializationDataBase {

    private static final String SOMMEIL_FAILURE = "cardinal(inter({VAR__CIBLE_STATUTS},{SOMMEIL;SOMMEIL_REPOS}))>0|VAR__CIBLE_CLONE>0";
    private static final String VAR_FAIL_SYNCHRONIZING_STATUS = "VAR__EXISTE_GENRE_ASSEXUE|VAR__GENRES_EGAUX|VAR__CIBLE_POSSEDE_STATUT_RELATION__AMOUR";

    private static Fight disableStatus(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME,_diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
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
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 3);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(MAGNET);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 4);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
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
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, _diff, dual_, _data);
        FightInitialization.initFight(fight_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void disableStatus1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = disableStatus(diff_, data_);
        fight_.enableGlobalMove(ZENITH);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(GEL);
        FightEffects.disableStatus(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatusNbRound(GEL));
    }

    @Test
    public void disableStatus2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = disableStatus(diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, AMOUR);
        fight_.enableGlobalMove(BROUHAHA);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(GEL);
        FightEffects.disableStatus(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(AMOUR, POKEMON_FOE_FIGHTER_ZERO)));
    }

    @Test
    public void deltaBoostStatistic1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(1, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, data_));
    }

    @Test
    public void deltaBoostStatistic2Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(-1, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, data_));
    }

    @Test
    public void deltaBoostStatistic3Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(6, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) 7, data_));
    }

    @Test
    public void deltaBoostStatistic4Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(-6, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) -7, data_));
    }

    @Test
    public void deltaBoostStatistic5Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(0, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) 0, data_));
    }

    @Test
    public void deltaBoostStatistic6Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(CRAN);
        assertEq(-6, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) -7, data_));
    }

    @Test
    public void deltaBoostStatistic7Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(SIMPLE);
        assertEq(2, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, data_));
    }

    @Test
    public void deltaBoostStatistic8Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(SIMPLE);
        assertEq(-2, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, data_));
    }

    @Test
    public void deltaBoostStatistic9Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(CONTRAIRE);
        assertEq(2, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, data_));
        assertEq(2, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, data_));
    }

    @Test
    public void deltaBoostStatisticMap1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        IdMap<Statistic,Byte> varsBase_ = new IdMap<Statistic,Byte>();
        varsBase_.put(Statistic.ATTACK, (byte) 2);
        IdMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, data_);
        assertEq(1, vars_.size());
        assertEq(2, vars_.getVal(Statistic.ATTACK));
    }

    @Test
    public void deltaBoostStatisticMap2Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        IdMap<Statistic,Byte> varsBase_ = new IdMap<Statistic,Byte>();
        varsBase_.put(Statistic.ATTACK, (byte) -2);
        IdMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, data_);
        assertEq(1, vars_.size());
        assertEq(-2, vars_.getVal(Statistic.ATTACK));
    }

    @Test
    public void deltaBoostStatisticMap3Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        IdMap<Statistic,Byte> varsBase_ = new IdMap<Statistic,Byte>();
        varsBase_.put(Statistic.ATTACK, (byte) -2);
        IdMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, data_);
        assertEq(1, vars_.size());
        assertEq(-2, vars_.getVal(Statistic.ATTACK));
    }

    @Test
    public void deltaBoostStatisticMap4Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ACHARNE);
        IdMap<Statistic,Byte> varsBase_ = new IdMap<Statistic,Byte>();
        varsBase_.put(Statistic.ATTACK, (byte) 2);
        IdMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, data_);
        assertEq(1, vars_.size());
        assertEq(2, vars_.getVal(Statistic.ATTACK));
    }

    @Test
    public void deltaBoostStatisticMap5Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ACHARNE);
        IdMap<Statistic,Byte> varsBase_ = new IdMap<Statistic,Byte>();
        varsBase_.put(Statistic.ATTACK, (byte) -1);
        IdMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, data_);
        assertEq(1, vars_.size());
        assertEq(2, vars_.getVal(Statistic.ATTACK));
    }

    @Test
    public void deltaBoostStatisticMap6Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ACHARNE);
        IdMap<Statistic,Byte> varsBase_ = new IdMap<Statistic,Byte>();
        varsBase_.put(Statistic.SPECIAL_ATTACK, (byte) -1);
        IdMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, data_);
        assertEq(2, vars_.size());
        assertEq(0, vars_.getVal(Statistic.SPECIAL_ATTACK));
        assertEq(2, vars_.getVal(Statistic.ATTACK));
    }

    @Test
    public void deltaBoostStatisticMap7Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ACHARNE);
        IdMap<Statistic,Byte> varsBase_ = new IdMap<Statistic,Byte>();
        varsBase_.put(Statistic.DEFENSE, (byte) -2);
        varsBase_.put(Statistic.ATTACK, (byte) 1);
        IdMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, data_);
        assertEq(2, vars_.size());
        assertEq(0, vars_.getVal(Statistic.DEFENSE));
        assertEq(3, vars_.getVal(Statistic.ATTACK));
    }

    @Test
    public void deltaBoostStatisticMap8Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ACHARNE);
        IdMap<Statistic,Byte> varsBase_ = new IdMap<Statistic,Byte>();
        varsBase_.put(Statistic.DEFENSE, (byte) -2);
        varsBase_.put(Statistic.SPECIAL_ATTACK, (byte) 1);
        IdMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, data_);
        assertEq(3, vars_.size());
        assertEq(0, vars_.getVal(Statistic.DEFENSE));
        assertEq(1, vars_.getVal(Statistic.SPECIAL_ATTACK));
        assertEq(2, vars_.getVal(Statistic.ATTACK));
    }

    @Test
    public void deltaBoostStatisticMap9Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ACHARNE);
        IdMap<Statistic,Byte> varsBase_ = new IdMap<Statistic,Byte>();
        IdMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, data_);
        assertEq(0, vars_.size());
    }

    @Test
    public void pairNewThrowerTarget1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(false);
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, SEISME, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget2Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(false);
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget3Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(true);
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget4Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(SAISIE,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getUser());
    }

    @Test
    public void pairNewThrowerTarget5Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getUser());
    }

    @Test
    public void pairNewThrowerTarget6Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget7Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PAR_ICI);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget8Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANNEAU_HYDRO, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget9Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, MUR_DE_FER, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget10Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, PICOTS, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget11Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, VANTARDISE, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data_);
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget12Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(SAISIE,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ACUPRESSION, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_PLAYER_FIGHTER_ONE,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget13Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(SAISIE,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, IndexConstants.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data_);
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getUser());
    }

    @Test
    public void effectRestriction1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).successUsingMove();
        MoveData fMove_ = data_.getMove(ENCORE);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, ENCORE, data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        MoveTeamPositionsAffectedMove map_ = fighter_.getTrackingMoves();
        AffectedMove value_;
        value_ = map_.getVal(new MoveTeamPosition(ENCORE, thrower_));
        assertEq(SEISME, value_.getMove());
        assertEq(0, value_.getActivity().getNbTurn());
        assertTrue(value_.getActivity().isEnabled());
    }

    @Test
    public void effectRestriction2Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).setFirstChosenMove(NULL_REF);
        FightRound.initRound(fight_);
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).successUsingMove();
        MoveData fMove_ = data_.getMove(ENCORE);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, ENCORE, data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        MoveTeamPositionsAffectedMove map_ = fighter_.getTrackingMoves();
        AffectedMove value_;
        value_ = map_.getVal(new MoveTeamPosition(ENCORE, thrower_));
        assertEq(NULL_REF, value_.getMove());
        assertEq(0, value_.getActivity().getNbTurn());
        assertTrue(!value_.getActivity().isEnabled());
    }

    @Test
    public void effectRestriction3Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).successUsingMove();
        MoveData fMove_ = data_.getMove(ENTRAVE);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, ENTRAVE, data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        MoveTeamPositionsAffectedMove map_ = fighter_.getTrackingMoves();
        AffectedMove value_;
        value_ = map_.getVal(new MoveTeamPosition(ENTRAVE, thrower_));
        assertEq(SEISME, value_.getMove());
        assertEq(0, value_.getActivity().getNbTurn());
        assertTrue(value_.getActivity().isEnabled());
    }

    @Test
    public void effectRestriction4Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).setFirstChosenMove(NULL_REF);
        FightRound.initRound(fight_);
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).successUsingMove();
        MoveData fMove_ = data_.getMove(ENTRAVE);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, ENTRAVE, data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        MoveTeamPositionsAffectedMove map_ = fighter_.getTrackingMoves();
        AffectedMove value_;
        value_ = map_.getVal(new MoveTeamPosition(ENTRAVE, thrower_));
        assertEq(NULL_REF, value_.getMove());
        assertEq(0, value_.getActivity().getNbTurn());
        assertTrue(!value_.getActivity().isEnabled());
    }

    @Test
    public void effectRestriction5Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(POSSESSIF);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, POSSESSIF, data_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        MoveTeamPositionsStringList map_ = fighter_.getPrivateMoves();
        StringList value_;
        value_ = map_.getVal(new MoveTeamPosition(POSSESSIF, target_));
        assertEq(1, value_.size());
        assertTrue(StringUtil.contains(value_, COPIE));
    }

    @Test
    public void effectRestriction6Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(EMBARGO);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, EMBARGO, data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMoves();
        ActivityOfMove value_;
        value_ = map_.getVal(EMBARGO);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
    }

    @Test
    public void effectRestriction7Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(PROVOC);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, PROVOC, data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMoves();
        ActivityOfMove value_;
        value_ = map_.getVal(PROVOC);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
    }

    @Test
    public void effectRestriction8Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(TOURMENTE);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, TOURMENTE, data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMoves();
        ActivityOfMove value_;
        value_ = map_.getVal(TOURMENTE);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
    }

    private static Fight effectUnprotectFromMoveTypes(DataBase _data) {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false, _data);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_, _data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_, _data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_, _data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectUnprotectFromMoveTypes1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).activerAttaqueImmu(VOL_MAGNETIK, data_);
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(ANTI_AIR);
        EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) fMove_.getEffet(IndexConstants.SECOND_INDEX);
        FightEffects.effectUnprotectFromMoveTypes(fight_, target_, eff_, ANTI_AIR, data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMovesProt();
        ActivityOfMove value_;
        value_ = map_.getVal(VOL_MAGNETIK);
        assertEq(0, value_.getNbTurn());
        assertTrue(!value_.isEnabled());
        map_ = fighter_.getEnabledMovesUnprot();
        value_ = map_.getVal(ANTI_AIR);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
        StringList types_ = fighter_.getProtectedAgainstMoveTypes();
        assertEq(0, types_.size());
    }

    @Test
    public void effectUnprotectFromMoveTypes2Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).activerAttaqueImmu(VOL_MAGNETIK, data_);
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(RACINES);
        EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) fMove_.getEffet(IndexConstants.SECOND_INDEX);
        FightEffects.effectUnprotectFromMoveTypes(fight_, target_, eff_, RACINES, data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMovesProt();
        ActivityOfMove value_;
        value_ = map_.getVal(VOL_MAGNETIK);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
        map_ = fighter_.getEnabledMovesUnprot();
        value_ = map_.getVal(RACINES);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
        StringList types_ = fighter_.getProtectedAgainstMoveTypes();
        assertEq(0, types_.size());
    }

    @Test
    public void effectUnprotectFromMoveTypes3Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(OEIL_MIRACLE);
        EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectUnprotectFromMoveTypes(fight_, target_, eff_, OEIL_MIRACLE, data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMovesUnprot();
        ActivityOfMove value_;
        value_ = map_.getVal(OEIL_MIRACLE);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
    }

    @Test
    public void effectUnprotectFromMoveTypes4Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).activerAttaqueImmu(TROU, data_);
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(ANTI_SOL);
        EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) fMove_.getEffet(IndexConstants.SECOND_INDEX);
        FightEffects.effectUnprotectFromMoveTypes(fight_, target_, eff_, ANTI_SOL, data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMovesProt();
        ActivityOfMove value_;
        value_ = map_.getVal(TROU);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
        map_ = fighter_.getEnabledMovesUnprot();
        value_ = map_.getVal(ANTI_SOL);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
        StringList types_ = fighter_.getProtectedAgainstMoveTypes();
        assertEq(0, types_.size());
    }

    @Test
    public void effectUnprotectFromMoveTypes5Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).activerAttaqueImmu(TROU, data_);
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(ANTI_CROISEUR);
        EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) fMove_.getEffet(IndexConstants.SECOND_INDEX);
        FightEffects.effectUnprotectFromMoveTypes(fight_, target_, eff_, ANTI_CROISEUR, data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMovesProt();
        ActivityOfMove value_;
        value_ = map_.getVal(TROU);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
        map_ = fighter_.getEnabledMovesUnprot();
        value_ = map_.getVal(ANTI_CROISEUR);
        assertEq(0, value_.getNbTurn());
        assertTrue(!value_.isEnabled());
        StringList types_ = fighter_.getProtectedAgainstMoveTypes();
        assertEq(1, types_.size());
        assertEq(VOL, types_.first());
    }

    @Test
    public void effectSwitchAbilities1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightAbilities.enableAbility(fight_, thrower_, data_);
        FightAbilities.enableAbility(fight_, target_, data_);
        FightRound.initRound(fight_);
        StringList typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(StringUtil.contains(typesThrower_, FEU));
        StringList typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(StringUtil.contains(typesTarget_, DRAGON));
        MoveData fMove_ = data_.getMove(ECHANGE);
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchAbilities(fight_, thrower_, target_, effect_, data_);
        typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(StringUtil.contains(typesThrower_, DRAGON));
        typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(StringUtil.contains(typesTarget_, FEU));
    }

    @Test
    public void effectSwitchAbilities2Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightAbilities.enableAbility(fight_, thrower_, data_);
        FightAbilities.enableAbility(fight_, target_, data_);
        FightRound.initRound(fight_);
        StringList typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(StringUtil.contains(typesThrower_, FEU));
        StringList typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(StringUtil.contains(typesTarget_, DRAGON));
        MoveData fMove_ = data_.getMove(TEN_DANSE);
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchAbilities(fight_, thrower_, target_, effect_, data_);
        typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(StringUtil.contains(typesThrower_, FEU));
        typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(StringUtil.contains(typesTarget_, FEU));
    }

    @Test
    public void effectSwitchAbilities3Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightAbilities.enableAbility(fight_, thrower_, data_);
        FightAbilities.enableAbility(fight_, target_, data_);
        FightRound.initRound(fight_);
        StringList typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(StringUtil.contains(typesThrower_, FEU));
        StringList typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(StringUtil.contains(typesTarget_, DRAGON));
        MoveData fMove_ = data_.getMove(IMITATION);
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchAbilities(fight_, thrower_, target_, effect_, data_);
        typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(StringUtil.contains(typesThrower_, DRAGON));
        typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(StringUtil.contains(typesTarget_, DRAGON));
    }

    @Test
    public void effectSwitchAbilities4Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightAbilities.enableAbility(fight_, thrower_, data_);
        FightAbilities.enableAbility(fight_, target_, data_);
        FightRound.initRound(fight_);
        StringList typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(StringUtil.contains(typesThrower_, FEU));
        StringList typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(StringUtil.contains(typesTarget_, DRAGON));
        MoveData fMove_ = data_.getMove(RAYON_SIMPLE);
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchAbilities(fight_, thrower_, target_, effect_, data_);
        typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(StringUtil.contains(typesThrower_, FEU));
        typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(SIMPLE, fighter_.getCurrentAbility());
        assertEq(2, typesTarget_.size());
        assertTrue(StringUtil.contains(typesTarget_, EAU));
        assertTrue(StringUtil.contains(typesTarget_, COMBAT));
    }

    @Test
    public void effectSwitchAbilities5Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightAbilities.enableAbility(fight_, thrower_, data_);
        FightAbilities.enableAbility(fight_, target_, data_);
        FightRound.initRound(fight_);
        StringList typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(StringUtil.contains(typesThrower_, FEU));
        StringList typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(StringUtil.contains(typesTarget_, DRAGON));
        MoveData fMove_ = data_.getMove(ECHANGE_BIS);
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchAbilities(fight_, thrower_, target_, effect_, data_);
        typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(StringUtil.contains(typesThrower_, FEU));
        typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(StringUtil.contains(typesTarget_, DRAGON));
    }

    @Test
    public void effectSwitchAbilities6Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightAbilities.enableAbility(fight_, thrower_, data_);
        FightAbilities.enableAbility(fight_, target_, data_);
        FightRound.initRound(fight_);
        StringList typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(StringUtil.contains(typesThrower_, FEU));
        StringList typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(StringUtil.contains(typesTarget_, DRAGON));
        MoveData fMove_ = data_.getMove(RAYON_UV);
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchAbilities(fight_, thrower_, target_, effect_, data_);
        typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(StringUtil.contains(typesThrower_, FEU));
        typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(NULL_REF, fighter_.getCurrentAbility());
        assertEq(2, typesTarget_.size());
        assertTrue(StringUtil.contains(typesTarget_, EAU));
        assertTrue(StringUtil.contains(typesTarget_, COMBAT));
    }

    @Test
    public void effectSwitchObjects1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        MoveData fMove_ = data_.getMove(PASSE_PASSE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_MEPO,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(1, lostObjects_.size());
        assertTrue(StringUtil.contains(lostObjects_, BAIE_MEPO));
    }

    @Test
    public void effectSwitchObjects2Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(NULL_REF);
        MoveData fMove_ = data_.getMove(PASSE_CADEAU);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(BAIE_MEPO,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(1, lostObjects_.size());
        assertTrue(StringUtil.contains(lostObjects_, BAIE_MEPO));
    }

    @Test
    public void effectSwitchObjects3Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_MEPO);
        MoveData fMove_ = data_.getMove(IMPLORE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_MEPO,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects4Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_MEPO);
        MoveData fMove_ = data_.getMove(CALCINATION);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        assertEq(BAIE_MEPO,fighter_.getLastUsedItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects5Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(HYPER_BALL);
        MoveData fMove_ = data_.getMove(CALCINATION);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(HYPER_BALL,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects6Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(NULL_REF);
        MoveData fMove_ = data_.getMove(CALCINATION);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects7Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(NULL_REF);
        MoveData fMove_ = data_.getMove(PICORE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects8Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterStatut(PARALYSIE);
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_.setCurrentAbility(TENSION);
        MoveData fMove_ = data_.getMove(PICORE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        assertEq(1,fighter_.getStatusNbRound(PARALYSIE));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        assertTrue(!fighter_.isUsingItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects9Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterStatut(PARALYSIE);
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        MoveData fMove_ = data_.getMove(PICORE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        assertEq(0,fighter_.getStatusNbRound(PARALYSIE));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        assertTrue(fighter_.isUsingItem());
        fighter_.tossLastUsedObject();
        assertEq(NULL_REF,fighter_.getItem());
        assertEq(BAIE_CERIZ,fighter_.getLastUsedItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects10Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterStatut(PARALYSIE);
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(HYPER_BALL);
        MoveData fMove_ = data_.getMove(PICORE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        assertEq(1,fighter_.getStatusNbRound(PARALYSIE));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(HYPER_BALL,fighter_.getItem());
        assertTrue(!fighter_.isUsingItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects11Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setLastUsedItem(BAIE_CERIZ);
        fighter_.setItem(NULL_REF);
        MoveData fMove_ = data_.getMove(RECYCLAGE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, thrower_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        assertEq(BAIE_CERIZ,fighter_.getLastUsedItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects12Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        MoveData fMove_ = data_.getMove(LARCIN);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        assertEq(PLAQUE_DRACO,fighter_.getLastUsedItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects13Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectUnprotectFromMoveTypes(data_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getUserTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_MEPO);
        MoveData fMove_ = data_.getMove(IMPLORE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffet(IndexConstants.SECOND_INDEX);
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_MEPO,fighter_.getItem());
        fighter_ = fight_.getUserTeam().getMembers().getVal(target_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(1, lostObjects_.size());
        assertTrue(StringUtil.contains(lostObjects_, BAIE_MEPO));
    }

    @Test
    public void effectSwitchTypes1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(FEU);
        MoveData fMove_ = data_.getMove(ADAPTATION);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes2Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(new StringList(COMBAT,ACIER));
        fighter_.getLastSufferedMoveTypes().add(ROCHE);
        MoveData fMove_ = data_.getMove(CONVERSION_2);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes3Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(CAMOUFLAGE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, data_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes4Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = data_.getMove(COPIE_TYPE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes5Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = data_.getMove(ECHANGE_TYPE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, VOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes6Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = data_.getMove(FORCE_TYPE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, VOL));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, VOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes7Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = data_.getMove(DETREMPAGE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, VOL));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, EAU));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes8Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = data_.getMove(DETEINTE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, VOL));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes9Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = data_.getMove(MALEFICE_SYLVAIN);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, VOL));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        types_ = fighter_.getTypes();
        assertEq(2, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
        assertTrue(StringUtil.contains(types_, SPECTRE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes10Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        fight_.enableGlobalMove(CHAMP_BRUMEUX);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        MoveData fMove_ = data_.getMove(CAMOUFLAGE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, FEE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes11Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = data_.getMove(ADAPTATION);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes12Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(new StringList(COMBAT,ACIER));
        fighter_.getLastSufferedMoveTypes().clear();
        MoveData fMove_ = data_.getMove(CONVERSION_2);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(2, types_.size());
        assertTrue(StringUtil.contains(types_, COMBAT));
        assertTrue(StringUtil.contains(types_, ACIER));
        assertTrue(fight_.getAcceptableChoices());
    }
    @Test
    public void effectSwitchTypes1SimulationTest() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(new StringList(ELECTRIQUE));
        fighter_.getLastSufferedMoveTypes().add(ROCHE);
        MoveData fMove_ = data_.getMove(CONVERSION_2);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes2SimulationTest() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.getMoves().put(ECLAIR, new UsesOfMove((short)10));
        fighter_.getCurrentMoves().put(ECLAIR, new UsesOfMove((short)10));
        fighter_.affecterTypes(FEU);
        MoveData fMove_ = data_.getMove(ADAPTATION);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, FEU));
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchMoveTypes1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        FightEffects.effectSwitchMoveTypes(fight_, ELECTRISATION, thrower_, data_);
        assertEq(0, fighter_.getEnabledChangingTypesMoves().getVal(ELECTRISATION).getNbTurn());
        assertTrue(fighter_.getEnabledChangingTypesMoves().getVal(ELECTRISATION).isEnabled());
    }

    @Test
    public void effectCounterAttack1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        FightEffects.effectCounterAttack(fight_, NUEE_DE_POUDRE, thrower_, data_);
        assertEq(0, fighter_.getEnabledCounteringMoves().getVal(NUEE_DE_POUDRE).getNbTurn());
        assertTrue(fighter_.getEnabledCounteringMoves().getVal(NUEE_DE_POUDRE).isEnabled());
    }

    private static Fight effectCommonStatistics(DataBase _data) {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = Player.build(NICKNAME,diff_,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_, _data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_, _data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_, _data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectCommonStatistics1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setRemainedHp(new Rate("10"));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setRemainedHp(new Rate("5"));
        MoveData fMove_ = data_.getMove(BALANCE);
        EffectCommonStatistics effect_ = (EffectCommonStatistics) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectCommonStatistics(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("15/2"),fighter_.getRemainingHp());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(new Rate("15/2"),fighter_.getRemainingHp());
    }

    @Test
    public void effectCommonStatistics2Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(BALANCE);
        EffectCommonStatistics effect_ = (EffectCommonStatistics) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectCommonStatistics(fight_, thrower_, target_, effect_, data_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("3713/200"),fighter_.getRemainingHp());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(new Rate("1840/100"),fighter_.getRemainingHp());
    }

    @Test
    public void effectCommonStatistics3Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(BALANCE);
        EffectCommonStatistics effect_ = (EffectCommonStatistics) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectCommonStatistics(fight_, thrower_, target_, effect_, data_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(target_.getPosition());
        assertEq(new Rate("3713/200"),fighter_.getRemainingHp());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("1840/100"),fighter_.getRemainingHp());
    }

    @Test
    public void effectCommonStatistics4Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCommonStatistics(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("50"),fighter_.getStatisBase().getVal(Statistic.DEFENSE));
        assertEq(new Rate("50"),fighter_.getStatisBase().getVal(Statistic.SPECIAL_DEFENSE));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("95"),fighter_.getStatisBase().getVal(Statistic.DEFENSE));
        assertEq(new Rate("90"),fighter_.getStatisBase().getVal(Statistic.SPECIAL_DEFENSE));
        MoveData fMove_ = data_.getMove(PARTAGE_GARDE);
        EffectCommonStatistics effect_ = (EffectCommonStatistics) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectCommonStatistics(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("145/2"),fighter_.getStatisBase().getVal(Statistic.DEFENSE));
        assertEq(new Rate("70"),fighter_.getStatisBase().getVal(Statistic.SPECIAL_DEFENSE));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("145/2"),fighter_.getStatisBase().getVal(Statistic.DEFENSE));
        assertEq(new Rate("70"),fighter_.getStatisBase().getVal(Statistic.SPECIAL_DEFENSE));
    }

    private static Fight effectCopyMove(DataBase _data) {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        return synchronizeStatus(diff_, _data);
    }

    @Test
    public void effectCopyMove1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCopyMove(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMove(COPIE);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setLastUsedMove(JACKPOT);
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(COPIE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        CopiedMove newMoveInfo_ = fighter_.getCopiedMoves().getVal(COPIE);
        assertEq(JACKPOT, newMoveInfo_.getMove());
        assertEq(5, newMoveInfo_.getPp());
    }

    @Test
    public void effectCopyMove2Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCopyMove(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMoveTarget(GRIBOUILLE, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setLastUsedMove(JACKPOT);
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(GRIBOUILLE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertTrue(!StringUtil.contains(fighter_.getMovesSet(), JACKPOT));
        assertTrue(!StringUtil.contains(fighter_.getCurrentMovesSet(), JACKPOT));
    }

    @Test
    public void effectCopyMove3Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCopyMove(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMove(COPIE);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setLastUsedMove(LUTTE);
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(COPIE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        CopiedMove newMoveInfo_ = fighter_.getCopiedMoves().getVal(COPIE);
        assertEq(NULL_REF, newMoveInfo_.getMove());
        assertEq(0, newMoveInfo_.getPp());
    }

    @Test
    public void effectCopyMove4Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCopyMove(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMove(COPIE);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(COPIE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        CopiedMove newMoveInfo_ = fighter_.getCopiedMoves().getVal(COPIE);
        assertEq(NULL_REF, newMoveInfo_.getMove());
        assertEq(0, newMoveInfo_.getPp());
    }

    @Test
    public void effectCopyMove5Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCopyMove(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMoveTarget(LUTTE, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setLastUsedMove(JACKPOT);
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(GRIBOUILLE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertTrue(!fighter_.getMoves().contains(LUTTE));
        assertTrue(!fighter_.getCurrentMoves().contains(LUTTE));
    }

    @Test
    public void effectCopyMove6Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCopyMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getMoves().put(GRIBOUILLE, new UsesOfMove((byte)10));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getCurrentMoves().put(GRIBOUILLE, new UsesOfMove((byte)10));
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMoveTarget(GRIBOUILLE, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setLastUsedMove(JACKPOT);
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(GRIBOUILLE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(20, fighter_.getMove(JACKPOT).getCurrent());
        assertEq(20, fighter_.getMove(JACKPOT).getMax());
        assertEq(20, fighter_.getCurrentMove(JACKPOT).getCurrent());
        assertEq(20, fighter_.getCurrentMove(JACKPOT).getMax());
    }

    @Test
    public void effectCopyMove7Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCopyMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getMoves().put(GRIBOUILLE, new UsesOfMove((byte)10));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getCurrentMoves().put(GRIBOUILLE, new UsesOfMove((byte)10));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setChanged(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMoveTarget(GRIBOUILLE, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setLastUsedMove(JACKPOT);
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(GRIBOUILLE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertTrue(!StringUtil.contains(fighter_.getMovesSet(), JACKPOT));
        assertTrue(StringUtil.contains(fighter_.getMovesSet(), GRIBOUILLE));
        assertTrue(!StringUtil.contains(fighter_.getCurrentMovesSet(), GRIBOUILLE));
        assertEq(20, fighter_.getCurrentMove(JACKPOT).getCurrent());
        assertEq(20, fighter_.getCurrentMove(JACKPOT).getMax());
    }

    @Test
    public void effectCopyFighter1Test() {
        DataBase data_ = initDb();
        Fight fight_ = effectCopyMove(data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = data_.getMove(MORPHING);
        EffectCopyFighter effect_ = (EffectCopyFighter) fMove_.getEffet(IndexConstants.FIRST_INDEX);
        FightEffects.effectCopyFighter(fight_, thrower_, target_, effect_, data_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertTrue(fighter_.isChanged());
        assertEq(ARTIKODIN,fighter_.getName());
        assertEq(TARTARD,fighter_.getCurrentName());
    }

    @Test
    public void rateNoStatus1Test() {
        StringMap<LgInt> map_ = new StringMap<LgInt>();
        assertEq(new LgInt("0"), FightEffects.rateNoStatus(map_));
        map_.put(SOMMEIL, new LgInt("1"));
        assertEq(new LgInt("0"), FightEffects.rateNoStatus(map_));
        map_.put(NULL_REF, new LgInt("1"));
        assertEq(new LgInt("1"), FightEffects.rateNoStatus(map_));
        map_ = new StringMap<LgInt>();
        map_.put(NULL_REF, new LgInt("2"));
        assertEq(new LgInt("2"), FightEffects.rateNoStatus(map_));
    }

    @Test
    public void generatedStatusLaw1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, LgInt.one());
        law_.addQuickEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, data_);
        assertEq(2, res_.nbEvents());
        assertTrue(res_.containsEvent(NULL_REF));
        assertTrue(res_.containsEvent(SOMMEIL));
        assertEq(new LgInt("1"),res_.rate(NULL_REF));
        assertEq(new LgInt("1"),res_.rate(SOMMEIL));
    }

    @Test
    public void generatedStatusLaw2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.enableGlobalMove(BROUHAHA);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, LgInt.one());
        law_.addQuickEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, data_);
        assertEq(1, res_.nbEvents());
        assertTrue(res_.containsEvent(NULL_REF));
        assertTrue(res_.isValid());
    }

    @Test
    public void generatedStatusLaw3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.enableGlobalMove(BROUHAHA);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, data_);
        assertEq(1, res_.nbEvents());
        assertTrue(res_.containsEvent(NULL_REF));
        assertTrue(res_.isValid());
    }

    @Test
    public void generatedStatusLaw4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.enableGlobalMove(BROUHAHA);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterStatut(SOMMEIL);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, LgInt.one());
        law_.addQuickEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, SOMMEIL_FAILURE);
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, data_);
        assertEq(1, res_.nbEvents());
        assertTrue(res_.containsEvent(NULL_REF));
        assertTrue(res_.isValid());
    }

    @Test
    public void generatedStatusLaw5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, LgInt.one());
        law_.addQuickEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, SOMMEIL_FAILURE);
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, data_);
        assertEq(2, res_.nbEvents());
        assertTrue(res_.containsEvent(NULL_REF));
        assertTrue(res_.containsEvent(SOMMEIL));
        assertEq(new LgInt("1"),res_.rate(NULL_REF));
        assertEq(new LgInt("1"),res_.rate(SOMMEIL));
    }

    @Test
    public void generatedStatusLaw6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, LgInt.one());
        law_.addQuickEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, NULL_REF);
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, data_);
        assertEq(2, res_.nbEvents());
        assertTrue(res_.containsEvent(NULL_REF));
        assertTrue(res_.containsEvent(SOMMEIL));
        assertEq(new LgInt("1"),res_.rate(NULL_REF));
        assertEq(new LgInt("1"),res_.rate(SOMMEIL));
    }

    @Test
    public void generatedStatusLaw7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterStatut(SOMMEIL);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, SOMMEIL_FAILURE);
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, data_);
        assertEq(1, res_.nbEvents());
        assertTrue(res_.containsEvent(NULL_REF));
        assertTrue(res_.isValid());
    }

    @Test
    public void setStatus1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_CERIZ);
        FightEffects.setStatus(fight_, thrower_, target_, PARALYSIE, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        StringList status_ = fight_.getSufferingTargetStatus();
        assertEq(1, status_.size());
        assertTrue(StringUtil.contains(status_, PARALYSIE));
        assertTrue(fighter_.isUsingItem());
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
    }

    @Test
    public void setStatus2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        FightEffects.setStatus(fight_, thrower_, target_, SOMMEIL, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        StringList status_ = fight_.getSufferingTargetStatus();
        assertEq(1, status_.size());
        assertTrue(StringUtil.contains(status_, SOMMEIL));
        assertTrue(!fighter_.isUsingItem());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
    }

    @Test
    public void setStatus3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.backUpObject(NULL_REF);
        FightEffects.setStatus(fight_, thrower_, target_, SOMMEIL, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        StringList status_ = fight_.getSufferingTargetStatus();
        assertEq(0, status_.size());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
    }

    @Test
    public void setStatus4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        FightEffects.setStatus(fight_, thrower_, target_, VAMPIGRAINE, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, thrower_)));
        StringList status_ = fight_.getSufferingTargetStatus();
        assertEq(1, status_.size());
        assertTrue(StringUtil.contains(status_, VAMPIGRAINE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
    }

    @Test
    public void setStatus5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.affecterPseudoStatut(thrower_, VAMPIGRAINE);
        FightEffects.setStatus(fight_, thrower_, target_, VAMPIGRAINE, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, thrower_)));
        StringList status_ = fight_.getSufferingTargetStatus();
        assertEq(0, status_.size());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
    }

    @Test
    public void setStatus6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        FightEffects.setStatus(fight_, thrower_, target_, PEUR, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(PEUR));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
    }

    @Test
    public void setStatus7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(METEO);
        FightEffects.setStatus(fight_, thrower_, target_, PEUR, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(PEUR));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
    }

    @Test
    public void setStatus8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(IMPASSIBLE);
        FightEffects.setStatus(fight_, thrower_, target_, PEUR, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(PEUR));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
    }

    @Test
    public void processStatusLaw1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(SOMMEIL, LgInt.one());
        FightEffects.processStatusLaw(fight_, thrower_, target_, law_, new StringMap<String>(), data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processStatusLaw2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.enableGlobalMove(BROUHAHA);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, LgInt.one());
        law_.addQuickEvent(SOMMEIL, LgInt.one());
        FightEffects.processStatusLaw(fight_, thrower_, target_, law_, new StringMap<String>(), data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processStatusLaw1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, LgInt.one());
        law_.addQuickEvent(SOMMEIL, LgInt.one());
        FightEffects.processStatusLaw(fight_, thrower_, target_, law_, new StringMap<String>(), data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void affectStatusToThrower1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        FightEffects.affectStatusToThrower(fight_, thrower_, SOMMEIL, target_, new StringMap<String>(), data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void affectStatusToThrower2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(VAMPIGRAINE);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        FightEffects.affectStatusToThrower(fight_, thrower_, VAMPIGRAINE, target_, new StringMap<String>(), data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, target_)));
    }

    @Test
    public void affectStatusToThrower3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.enableGlobalMove(BROUHAHA);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        FightEffects.affectStatusToThrower(fight_, thrower_, SOMMEIL, target_, new StringMap<String>(), data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void affectStatusToThrower4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, NULL_REF);
        FightEffects.affectStatusToThrower(fight_, thrower_, SOMMEIL, target_, fails_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void affectStatusToThrower5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, F);
        FightEffects.affectStatusToThrower(fight_, thrower_, SOMMEIL, target_, fails_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void affectStatusToThrower6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, V);
        FightEffects.affectStatusToThrower(fight_, thrower_, SOMMEIL, target_, fails_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
    }

    private static Fight synchronizeStatus(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
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
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void synchronizeStatus1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void synchronizeStatus2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(AMOUR);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR,target_)));
    }

    @Test
    public void synchronizeStatus3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void synchronizeStatus4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(AMOUR);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR,target_)));
    }

    @Test
    public void synchronizeStatus5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(VAMPIGRAINE);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE,target_)));
    }

    @Test
    public void synchronizeStatus6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void synchronizeStatus7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.enableGlobalMove(BROUHAHA);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void synchronizeStatus8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, NULL_REF);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), fails_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void synchronizeStatus9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, SOMMEIL_FAILURE);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), fails_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void synchronizeStatus10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.creerClone(new Rate("1/2"));
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, SOMMEIL_FAILURE);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), fails_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void synchronizeStatus11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(AMOUR);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(JOLI_SOURIRE);
        fighter_.setCurrentGender(Gender.FEMALE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(MULTITYPE);
        fighter_.backUpObject(NOEUD_DESTIN);
        fighter_.setCurrentGender(Gender.MALE);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(AMOUR, VAR_FAIL_SYNCHRONIZING_STATUS);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, fails_, new StringMap<String>(), data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)));
    }

    @Test
    public void synchronizeStatus12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(AMOUR);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(JOLI_SOURIRE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(MULTITYPE);
        fighter_.backUpObject(NULL_REF);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(AMOUR, VAR_FAIL_SYNCHRONIZING_STATUS);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, fails_, new StringMap<String>(), data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)));
    }

    @Test
    public void synchronizeStatus13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(AMOUR);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(JOLI_SOURIRE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(MULTITYPE);
        fighter_.backUpObject(BOUE_NOIRE);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(AMOUR, VAR_FAIL_SYNCHRONIZING_STATUS);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, fails_, new StringMap<String>(), data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)));
    }

    @Test
    public void synchronizeStatus14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(AMOUR);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(JOLI_SOURIRE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(MULTITYPE);
        fighter_.backUpObject(BAIE_MEPO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(AMOUR, VAR_FAIL_SYNCHRONIZING_STATUS);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, fails_, new StringMap<String>(), data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)));
    }

    @Test
    public void synchronizeStatus15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        fight_.getSufferingTargetStatus().add(AMOUR_TRES_MOU);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(JOLI_SOURIRE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(MULTITYPE);
        fighter_.backUpObject(BAIE_MEPO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(AMOUR, VAR_FAIL_SYNCHRONIZING_STATUS);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, fails_, new StringMap<String>(), data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)));
    }

    @Test
    public void effectSwitchPosition1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Effect effect_ = data_.getMove(INTERVERSION).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectSwitchPosition(fight_, thrower_, target_, data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(3, fight_.getFirstPositPlayerFighters().size());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(2, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(1, fight_.getEffects().size());
        AnimationEffect animSwitch_ = (AnimationEffect) fight_.getEffects().last();
        assertSame(EffectKind.CHANGED_PLACE,animSwitch_.getEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animSwitch_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ONE, animSwitch_.getToFighter());
        assertTrue(!animSwitch_.isKoFromFighter());
        assertTrue(!animSwitch_.isKoToFighter());
    }

    @Test
    public void effectSwitchPosition2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_THREE;
        Effect effect_ = data_.getMove(INTERVERSION).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectSwitchPosition(fight_, thrower_, target_, data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
        assertEq(1, fight_.getEffects().size());
        AnimationEffect animSwitch_ = (AnimationEffect) fight_.getEffects().last();
        assertSame(EffectKind.CHANGED_PLACE,animSwitch_.getEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animSwitch_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ONE, animSwitch_.getToFighter());
        assertTrue(!animSwitch_.isKoFromFighter());
        assertTrue(!animSwitch_.isKoToFighter());
    }

    @Test
    public void effectSwitchPosition3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Effect effect_ = data_.getMove(INTERVERSION).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectSwitchPosition(fight_, thrower_, target_, data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
        assertEq(1, fight_.getEffects().size());
        AnimationEffect animSwitch_ = (AnimationEffect) fight_.getEffects().last();
        assertSame(EffectKind.CHANGED_PLACE,animSwitch_.getEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ONE, animSwitch_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animSwitch_.getToFighter());
        assertTrue(!animSwitch_.isKoFromFighter());
        assertTrue(!animSwitch_.isKoToFighter());
    }

    @Test
    public void effectSwitchPosition4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ONE;
        Effect effect_ = data_.getMove(INTERVERSION).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectSwitchPosition(fight_, thrower_, target_, data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(3, fight_.getFirstPositFoeFighters().size());
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 2));
        assertEq(1, fight_.getEffects().size());
        AnimationEffect animSwitch_ = (AnimationEffect) fight_.getEffects().last();
        assertSame(EffectKind.CHANGED_PLACE,animSwitch_.getEffectKind());
        assertEq(POKEMON_FOE_TARGET_ZERO, animSwitch_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ONE, animSwitch_.getToFighter());
        assertTrue(!animSwitch_.isKoFromFighter());
        assertTrue(!animSwitch_.isKoToFighter());
    }

    @Test
    public void effectStatistic1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        MoveData move_ = data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        fight_.addEffect(thrower_, target_, eff_);
        IdList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        assertEq(1, statistics_.size());
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, statistics_, data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatistic anim_ = (AnimationEffectStatistic) fight_.getEffects().first();
        assertEq(1, anim_.getInfos().size());
        InfosAnimationStatistic infos_ = anim_.getInfos().first();
        assertEq(Statistic.ACCURACY, infos_.getStatistic());
        assertEq(-1, infos_.getVariation());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
        assertTrue(!infos_.isSwap());
    }

    @Test
    public void effectStatistic2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        MoveData move_ = data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        fight_.addEffect(thrower_, target_, eff_);
        IdList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, statistics_, data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fighter_.isUsingItem());
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatistic anim_ = (AnimationEffectStatistic) fight_.getEffects().first();
        assertEq(1, anim_.getInfos().size());
        InfosAnimationStatistic infos_ = anim_.getInfos().first();
        assertEq(Statistic.ACCURACY, infos_.getStatistic());
        assertEq(-1, infos_.getVariation());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatistic3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fighter_.variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = data_.getMove(DEGOMMAGE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        fight_.addEffect(thrower_, target_, eff_);
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, data_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatistic anim_ = (AnimationEffectStatistic) fight_.getEffects().first();
        assertEq(3, anim_.getInfos().size());
        InfosAnimationStatistic infos_ = anim_.getInfos().first();
        assertEq(Statistic.DEFENSE, infos_.getStatistic());
        assertEq(1, infos_.getVariation());
        infos_ = anim_.getInfos().get(1);
        assertEq(Statistic.SPECIAL_DEFENSE, infos_.getStatistic());
        assertEq(1, infos_.getVariation());
        infos_ = anim_.getInfos().get(2);
        assertEq(Statistic.SPEED, infos_.getStatistic());
        assertEq(1, infos_.getVariation());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatistic4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 1);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 3);
        fighter_ = fight_.getFighter(target_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fighter_.variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        MoveData move_ = data_.getMove(BOOST);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        fight_.addEffect(thrower_, target_, eff_);
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(3, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(3, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatistic anim_ = (AnimationEffectStatistic) fight_.getEffects().first();
        assertEq(3, anim_.getInfos().size());
        InfosAnimationStatistic infos_ = anim_.getInfos().first();
        assertEq(Statistic.ATTACK, infos_.getStatistic());
        assertEq(2, infos_.getVariation());
        infos_ = anim_.getInfos().get(1);
        assertEq(Statistic.ACCURACY, infos_.getStatistic());
        assertEq(0, infos_.getVariation());
        infos_ = anim_.getInfos().get(2);
        assertEq(Statistic.EVASINESS, infos_.getStatistic());
        assertEq(0, infos_.getVariation());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatistic5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fighter_.variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = data_.getMove(BUEE_NOIRE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        fight_.addEffect(thrower_, target_, eff_);
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatistic anim_ = (AnimationEffectStatistic) fight_.getEffects().first();
        assertEq(7, anim_.getInfos().size());
        InfosAnimationStatistic infos_ = anim_.getInfos().first();
        assertEq(Statistic.ATTACK, infos_.getStatistic());
        assertEq(-3, infos_.getVariation());
        infos_ = anim_.getInfos().get(1);
        assertEq(Statistic.DEFENSE, infos_.getStatistic());
        assertEq(1, infos_.getVariation());
        infos_ = anim_.getInfos().get(2);
        assertEq(Statistic.SPECIAL_ATTACK, infos_.getStatistic());
        assertEq(-1, infos_.getVariation());
        infos_ = anim_.getInfos().get(3);
        assertEq(Statistic.SPECIAL_DEFENSE, infos_.getStatistic());
        assertEq(1, infos_.getVariation());
        infos_ = anim_.getInfos().get(4);
        assertEq(Statistic.SPEED, infos_.getStatistic());
        assertEq(1, infos_.getVariation());
        infos_ = anim_.getInfos().get(5);
        assertEq(Statistic.ACCURACY, infos_.getStatistic());
        assertEq(0, infos_.getVariation());
        infos_ = anim_.getInfos().get(6);
        assertEq(Statistic.EVASINESS, infos_.getStatistic());
        assertEq(0, infos_.getVariation());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatistic6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fighter_.variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = data_.getMove(PERMUCOEUR);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        fight_.addEffect(thrower_, target_, eff_);
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(3, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatistic anim_ = (AnimationEffectStatistic) fight_.getEffects().first();
        assertEq(3, anim_.getInfos().size());
        InfosAnimationStatistic infos_ = anim_.getInfos().first();
        assertEq(Statistic.ATTACK, infos_.getStatistic());
        assertEq(0, infos_.getVariation());
        infos_ = anim_.getInfos().get(1);
        assertEq(Statistic.SPECIAL_ATTACK, infos_.getStatistic());
        assertEq(0, infos_.getVariation());
        infos_ = anim_.getInfos().get(2);
        assertEq(Statistic.ACCURACY, infos_.getStatistic());
        assertEq(0, infos_.getVariation());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatistic7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.variationBoostStatistique(Statistic.ACCURACY, (byte) -6);
        MoveData move_ = data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        fight_.addEffect(thrower_, target_, eff_);
        IdList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        assertEq(0, statistics_.size());
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, statistics_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(-6, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatistic anim_ = (AnimationEffectStatistic) fight_.getEffects().first();
        assertEq(0, anim_.getInfos().size());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
        assertEq(0, anim_.getIndex());
    }

    @Test
    public void effectStatistic8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.SPEED, (byte) 4);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.ACCURACY, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.EVASINESS, (byte) 6);
        MoveData move_ = data_.getMove(ACUPRESSION);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        fight_.addEffect(thrower_, target_, eff_);
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        assertEq(1, stats_.size());
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatistic anim_ = (AnimationEffectStatistic) fight_.getEffects().first();
        assertEq(2, anim_.getInfos().size());
        InfosAnimationStatistic infos_ = anim_.getInfos().first();
        assertEq(Statistic.SPEED, infos_.getStatistic());
        assertEq(2, infos_.getVariation());
        infos_ = anim_.getInfos().get(1);
        assertEq(Statistic.SPEED, infos_.getStatistic());
        assertEq(0, infos_.getVariation());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatistic9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.SPEED, (byte) 4);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.ACCURACY, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.EVASINESS, (byte) 6);
        MoveData move_ = data_.getMove(TROU_BIS);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        fight_.addEffect(thrower_, target_, eff_);
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        assertEq(1, stats_.size());
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(4, fighter_.getStatisBoost().getVal(Statistic.SPEED));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatistic anim_ = (AnimationEffectStatistic) fight_.getEffects().first();
        assertEq(1, anim_.getInfos().size());
        InfosAnimationStatistic infos_ = anim_.getInfos().first();
        assertEq(Statistic.DEFENSE, infos_.getStatistic());
        assertEq(0, infos_.getVariation());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatistic1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        fight_.setSimulation(true);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.SPEED, (byte) 4);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) 4);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.ACCURACY, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.EVASINESS, (byte) 6);
        MoveData move_ = data_.getMove(ACUPRESSION);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        assertEq(2, stats_.size());
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectStatisticRandom1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        MoveData move_ = data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        IdList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        assertEq(1, statistics_.size());
        effectStatisticRandom(fight_, thrower_, target_, eff_, statistics_, DataBase.defRateProduct(), false, data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
    }

    @Test
    public void effectStatisticRandom2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        MoveData move_ = data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        IdList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        effectStatisticRandom(fight_, thrower_, target_, eff_, statistics_, Rate.zero(), false, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
    }

    @Test
    public void effectStatisticRandom3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        MoveData move_ = data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        IdList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        assertEq(1, statistics_.size());
        effectStatisticRandom(fight_, thrower_, target_, eff_, statistics_, DataBase.defRateProduct(), true, data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
    }

    @Test
    public void effectStatisticRandom4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        MoveData move_ = data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        IdList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, data_);
        effectStatisticRandom(fight_, thrower_, target_, eff_, statistics_, Rate.zero(), true, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
    }

    @Test
    public void effectStatus1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterStatut(BRULURE);
        MoveData move_ = data_.getMove(ECHANGE_PSY);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().last();
        fight_.addEffect(thrower_, target_, eff_);
        FightEffects.effectStatus(fight_, thrower_, target_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(BRULURE));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatus anim_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertEq(NULL_REF, anim_.getStatus());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatus2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        MoveData move_ = data_.getMove(REPOS);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, thrower_, eff_);
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL_REPOS));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatus anim_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertEq(SOMMEIL_REPOS, anim_.getStatus());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatus3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition sub_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(sub_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 1);
        MoveData move_ = data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, thrower_, eff_);
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        fighter_ = fight_.getFighter(sub_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(3, fight_.getEffects().size());
        AnimationEffectStatus animStatus_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animStatus_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animStatus_.getToFighter());
        assertEq(NULL_REF, animStatus_.getStatus());
        assertTrue(!animStatus_.isKoFromFighter());
        assertTrue(!animStatus_.isKoToFighter());
        AnimationAutoEffect animKo_ = (AnimationAutoEffect) fight_.getEffects().get(1);
        assertSame(AutoEffectKind.KO,animKo_.getAutoEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animSwitch_.getSubstituted());
        assertEq(ARTIKODIN, animSwitch_.getSubstituteName());
        assertEq(3, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    @Test
    public void effectStatus4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        MoveData move_ = data_.getMove(BERCEUSE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, target_, eff_);
        FightEffects.effectStatus(fight_, thrower_, target_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatus anim_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertEq(SOMMEIL, anim_.getStatus());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatus5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition sub_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(sub_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 1);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICS_TOXIK);
        MoveData move_ = data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, thrower_, eff_);
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, data_);
        assertEq(LgInt.one(),fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICS_TOXIK));
        assertEq(LgInt.one(),fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        fighter_ = fight_.getFighter(sub_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(17,fighter_.getStatus().size());
//        assertEq(17,fighter_.getStatus().getKeys((short) 0).size());
        assertEq(17,fighter_.getNbStatusByRounds((short) 0));
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(3, fight_.getEffects().size());
        AnimationEffectStatus animStatus_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animStatus_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animStatus_.getToFighter());
        assertEq(NULL_REF, animStatus_.getStatus());
        assertTrue(!animStatus_.isKoFromFighter());
        assertTrue(!animStatus_.isKoToFighter());
        AnimationAutoEffect animKo_ = (AnimationAutoEffect) fight_.getEffects().get(1);
        assertSame(AutoEffectKind.KO,animKo_.getAutoEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animSwitch_.getSubstituted());
        assertEq(ARTIKODIN, animSwitch_.getSubstituteName());
        assertEq(3, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    @Test
    public void effectStatus6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition sub_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(sub_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(thrower_);
        MoveData move_ = data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, thrower_, eff_);
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(0, fighter_.getGroundPlace());
        fighter_ = fight_.getFighter(sub_);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatus animStatus_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animStatus_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animStatus_.getToFighter());
        assertEq(NULL_REF, animStatus_.getStatus());
        assertTrue(!animStatus_.isKoFromFighter());
        assertTrue(!animStatus_.isKoToFighter());
    }

    @Test
    public void effectStatus7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fight_.getFighter(target_).setCurrentAbility(SYNCHRO);
        MoveData move_ = data_.getMove(BERCEUSE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, target_, eff_);
        FightEffects.effectStatus(fight_, thrower_, target_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatus anim_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertEq(SOMMEIL, anim_.getStatus());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatus8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fight_.getFighter(target_).setCurrentAbility(NULL_REF);
        MoveData move_ = data_.getMove(BERCEUSE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, target_, eff_);
        FightEffects.effectStatus(fight_, thrower_, target_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatus anim_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertEq(SOMMEIL, anim_.getStatus());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatus9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition sub_ = POKEMON_FOE_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(sub_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 2);
        MoveData move_ = data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, thrower_, eff_);
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        fighter_ = fight_.getFighter(sub_);
        assertEq(new Rate("106/5"), fighter_.getRemainingHp());
        assertEq(3, fight_.getFirstPositFoeFighters().size());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1));
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 2));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(3, fight_.getEffects().size());
        AnimationEffectStatus animStatus_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, animStatus_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animStatus_.getToFighter());
        assertEq(NULL_REF, animStatus_.getStatus());
        assertTrue(!animStatus_.isKoFromFighter());
        assertTrue(!animStatus_.isKoToFighter());
        AnimationAutoEffect animKo_ = (AnimationAutoEffect) fight_.getEffects().get(1);
        assertSame(AutoEffectKind.KO,animKo_.getAutoEffectKind());
        assertEq(POKEMON_FOE_TARGET_ZERO, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(POKEMON_FOE_TARGET_ZERO, animSwitch_.getSubstituted());
        assertEq(TARTARD, animSwitch_.getSubstituteName());
        assertEq(4, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    @Test
    public void effectStatus10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterStatut(BRULURE);
        MoveData move_ = data_.getMove(GLAS_DE_SOIN);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, target_, eff_);
        FightEffects.effectStatus(fight_, thrower_, target_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
//        assertEq(17, fighter_.getStatus().getKeys((short) 0).size());
        assertEq(17, fighter_.getNbStatusByRounds((short) 0));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatus anim_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertEq(NULL_REF, anim_.getStatus());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatus1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition sub_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(sub_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 1);
        MoveData move_ = data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        fighter_ = fight_.getFighter(sub_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectStatus2SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition sub_ = POKEMON_FOE_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(sub_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 2);
        MoveData move_ = data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        fighter_ = fight_.getFighter(sub_);
        assertEq(new Rate("106/5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeam1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        fight_.getFoeTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFoeTeam().getNbUsesMoves().put(CASSE, 1);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, AMOUR);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, teamPosition_)).enable();
        EffectTeam eff_ = (EffectTeam) data_.getMove(TOUR_RAPIDE).getEffects().last();
        FightEffects.effectTeam(fight_, teamPosition_, eff_, TOUR_RAPIDE, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        ActivityOfMove activity_;
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(LgInt.zero(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0, fight_.getFoeTeam().getNbUsesMoves().getVal(CASSE));
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        activity_ = fight_.getUserTeam().getEnabledMoves().getVal(TOUR_RAPIDE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,teamPosition_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
    }

    @Test
    public void effectTeam2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        fight_.getFoeTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFoeTeam().getNbUsesMoves().put(CASSE, 1);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, AMOUR);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.getStatisBoost().put(Statistic.EVASINESS, (byte) 1);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, teamPosition_)).enable();
        EffectTeam eff_ = (EffectTeam) data_.getMove(ANTI_BRUME).getEffects().last();
        FightEffects.effectTeam(fight_, teamPosition_, eff_, ANTI_BRUME, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        ActivityOfMove activity_;
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertEq(LgInt.zero(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(1, fight_.getFoeTeam().getNbUsesMoves().getVal(CASSE));
        activity_ = fight_.getUserTeam().getEnabledMoves().getVal(ANTI_BRUME);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,teamPosition_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
    }

    @Test
    public void effectTeam3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        fight_.getFoeTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFoeTeam().getNbUsesMoves().put(CASSE, 1);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.affecterStatut(GEL);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, CAUCHEMAR);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        EffectTeam eff_ = (EffectTeam) data_.getMove(RUNE_PROTECT).getEffects().last();
        FightEffects.effectTeam(fight_, teamPosition_, eff_, RUNE_PROTECT, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        ActivityOfMove activity_;
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(0, fighter_.getStatusNbRound(GEL));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(CAUCHEMAR, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(1, fight_.getFoeTeam().getNbUsesMoves().getVal(CASSE));
        activity_ = fight_.getUserTeam().getEnabledMoves().getVal(RUNE_PROTECT);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
    }

    @Test
    public void effectTeam4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        fight_.getFoeTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFoeTeam().getNbUsesMoves().put(CASSE, 1);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.affecterStatut(GEL);
        fighter_.getStatisBoost().put(Statistic.ACCURACY,(byte) 1);
        fighter_.getStatisBoost().put(Statistic.EVASINESS,(byte) -1);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, CAUCHEMAR);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        EffectTeam eff_ = (EffectTeam) data_.getMove(BRUME).getEffects().last();
        FightEffects.effectTeam(fight_, teamPosition_, eff_, BRUME, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        ActivityOfMove activity_;
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(1, fighter_.getStatusNbRound(GEL));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(CAUCHEMAR, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(1, fight_.getFoeTeam().getNbUsesMoves().getVal(CASSE));
        activity_ = fight_.getUserTeam().getEnabledMoves().getVal(BRUME);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
    }

    @Test
    public void effectTeam5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        fight_.getFoeTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFoeTeam().getNbUsesMoves().put(CASSE, 1);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.affecterStatut(GEL);
        fighter_.getStatisBoost().put(Statistic.ACCURACY,(byte) 1);
        fighter_.getStatisBoost().put(Statistic.EVASINESS,(byte) -1);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, CAUCHEMAR);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        EffectTeam eff_ = (EffectTeam) data_.getMove(ANTI_BRUME).getEffects().last();
        FightEffects.effectTeam(fight_, teamPosition_, eff_, ANTI_BRUME, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        ActivityOfMove activity_;
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertEq(LgInt.zero(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(1, fighter_.getStatusNbRound(GEL));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(CAUCHEMAR, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(1, fight_.getFoeTeam().getNbUsesMoves().getVal(CASSE));
        activity_ = fight_.getUserTeam().getEnabledMoves().getVal(ANTI_BRUME);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
    }

    @Test
    public void effectTeam6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getUserTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getUserTeam().getNbUsesMoves().put(CASSE, 1);
        TeamPosition teamPosition_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.affecterStatut(GEL);
        fighter_.getStatisBoost().put(Statistic.ACCURACY,(byte) 1);
        fighter_.getStatisBoost().put(Statistic.EVASINESS,(byte) -1);
        fighter_.affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ZERO, CAUCHEMAR);
        fighter_.affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ZERO, VAMPIGRAINE);
        EffectTeam eff_ = (EffectTeam) data_.getMove(ANTI_BRUME).getEffects().last();
        FightEffects.effectTeam(fight_, teamPosition_, eff_, ANTI_BRUME, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        ActivityOfMove activity_;
        activity_ = fight_.getUserTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertEq(LgInt.zero(), fight_.getUserTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(1, fighter_.getStatusNbRound(GEL));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_PLAYER_FIGHTER_ZERO)));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(CAUCHEMAR, POKEMON_PLAYER_FIGHTER_ZERO)));
        assertEq(1, fight_.getUserTeam().getNbUsesMoves().getVal(CASSE));
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(ANTI_BRUME);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
    }

    @Test
    public void effectEndRound1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EffectEndRoundPositionRelation eff_;
        eff_ = (EffectEndRoundPositionRelation) data_.getMove(VOEU).getEffects().last();
        FightEffects.effectEndRound(fight_, teamPosition_, teamPosition_, VOEU, eff_, data_);
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        StacksOfUses soinApres_=fight_.getUserTeam().getHealAfter().getVal(VOEU).getVal(fighter_.getGroundPlace());
        assertTrue(soinApres_.isFirstStacked());
        assertTrue(!soinApres_.isLastStacked());
        assertEq(0, soinApres_.getNbRounds());
    }

    @Test
    public void effectEndRound2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EffectEndRoundPositionRelation eff_;
        eff_ = (EffectEndRoundPositionRelation) data_.getMove(VOEU).getEffects().last();
        FightEffects.effectEndRound(fight_, teamPosition_, teamPosition_, VOEU, eff_, data_);
        FightEffects.effectEndRound(fight_, teamPosition_, teamPosition_, VOEU, eff_, data_);
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        StacksOfUses soinApres_=fight_.getUserTeam().getHealAfter().getVal(VOEU).getVal(fighter_.getGroundPlace());
        assertTrue(soinApres_.isFirstStacked());
        assertTrue(soinApres_.isLastStacked());
        assertEq(0, soinApres_.getNbRounds());
    }

    @Test
    public void effectEndRound3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data_.getMove(SIPHON).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, SIPHON, eff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
    }

    @Test
    public void effectEndRound4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data_.getMove(SIPHON).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, SIPHON, eff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
    }

    @Test
    public void effectEndRound5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data_.getMove(SIPHON).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, SIPHON, eff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
    }

    @Test
    public void effectEndRound6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.creerClone(new Rate("1/2"));
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data_.getMove(SIPHON).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, SIPHON, eff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
    }

    @Test
    public void effectEndRound7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        EffectEndRoundPositionTargetRelation eff_;
        eff_ = (EffectEndRoundPositionTargetRelation) data_.getMove(PRESCIENCE).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, PRESCIENCE, eff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fight_.getUserTeam().getHealAfter().contains(PRESCIENCE));
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
    }

    @Test
    public void effectEndRound8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        EffectEndRound eff_;
        eff_ = (EffectEndRound) data_.getMove(ANNEAU_HYDRO).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, ANNEAU_HYDRO, eff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fight_.getUserTeam().getHealAfter().contains(ANNEAU_HYDRO));
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        activity_ = fighter_.getEnabledMovesEndRound().getVal(ANNEAU_HYDRO);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
    }

    @Test
    public void effectEndRound9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.creerClone(new Rate("1/2"));
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data_.getMove(TIPHON).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, TIPHON, eff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(TIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
    }

    private static Fight effectBatonPass(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
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
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 3);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(MAGNET);
        allyPokemon_.setAbility(SECHERESSE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 4);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
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
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectBatonPass1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter partner_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 1);
        FightRound.initRound(fight_);
        fighter_.getStatisBoost().put(Statistic.ATTACK, (byte) 1);
        fighter_.creerClone(new Rate("1/2"));
        fighter_.getNbUsesMoves().put(BOUL_ARMURE, 1);
        fighter_.getDamageSufferedCateg().put(PHYSIQUE, Rate.one());
        fighter_.getDamageSufferedCategRound().put(PHYSIQUE, Rate.one());
        fighter_.getDamageRateSufferedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.setLastSufferedMove(JACKPOT);
        fighter_.getNbRepeatingSuccessfulMoves().affect(new LgInt("2"));
        fighter_.activerAttaque(EMBARGO);
        fighter_.activerAttaqueAntiImmu(RACINES);
        fighter_.activerAttaqueImmu(TROU, data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, BoolVal.TRUE);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        Effect effect_ = data_.getMove(RELAIS).getEffet(0);
        fight_.addEffect(thrower_, thrower_, effect_);
        FightEffects.effectBatonPass(fight_, thrower_, diff_, data_);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(2, map_.getVal((byte) 0).size());
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 1));
        assertEq(2, map_.getVal((byte) 1).size());
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 1));
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
        assertEq(0, partner_.getGroundPlace());
        assertEq(0, partner_.getGroundPlaceSubst());
        assertEq(1, partner_.getNbUsesMoves().getVal(BOUL_ARMURE));
        assertEq(Rate.one(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("2"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(JACKPOT, partner_.getLastSufferedMove());
        assertEq(new Rate("1873/100"), partner_.getRemainingHp());
        assertEq(new Rate("1873/200"), partner_.getClone());
        assertEq(1, partner_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fight_.getEffects().size());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animSwitch_.getSubstituted());
        assertEq(ARTIKODIN, animSwitch_.getSubstituteName());
        assertEq(3, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    @Test
    public void effectBatonPass2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter partner_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 4);
        FightRound.initRound(fight_);
        fighter_.getStatisBoost().put(Statistic.ATTACK, (byte) 1);
        fighter_.creerClone(new Rate("1/2"));
        fighter_.getNbUsesMoves().put(BOUL_ARMURE, 1);
        fighter_.getDamageSufferedCateg().put(PHYSIQUE, Rate.one());
        fighter_.getDamageSufferedCategRound().put(PHYSIQUE, Rate.one());
        fighter_.getDamageRateSufferedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.setLastSufferedMove(JACKPOT);
        fighter_.getNbRepeatingSuccessfulMoves().affect(new LgInt("2"));
        fighter_.activerAttaque(EMBARGO);
        fighter_.activerAttaqueAntiImmu(RACINES);
        fighter_.activerAttaqueImmu(TROU, data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, BoolVal.TRUE);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        assertEq(new Rate("561/25"), partner_.getRemainingHp());
        Effect effect_ = data_.getMove(RELAIS).getEffet(0);
        fight_.addEffect(thrower_, thrower_, effect_);
        FightEffects.effectBatonPass(fight_, thrower_, diff_, data_);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(3, map_.size());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
        assertEq(1, partner_.getNbUsesMoves().getVal(BOUL_ARMURE));
        assertEq(Rate.one(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("2"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(JACKPOT, partner_.getLastSufferedMove());
        assertEq(new Rate("561/25"), partner_.getRemainingHp());
        assertEq(new Rate("1933/200"), partner_.getClone());
        assertEq(1, partner_.getStatisBoost().getVal(Statistic.ATTACK));
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
    public void effectBatonPass3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter partner_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        FightRound.initRound(fight_);
        fighter_.getStatisBoost().put(Statistic.ATTACK, (byte) 1);
        fighter_.creerClone(new Rate("1/2"));
        fighter_.getNbUsesMoves().put(BOUL_ARMURE, 1);
        fighter_.getDamageSufferedCateg().put(PHYSIQUE, Rate.one());
        fighter_.getDamageSufferedCategRound().put(PHYSIQUE, Rate.one());
        fighter_.getDamageRateSufferedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.setLastSufferedMove(JACKPOT);
        fighter_.getNbRepeatingSuccessfulMoves().affect(new LgInt("2"));
        fighter_.activerAttaque(EMBARGO);
        fighter_.activerAttaqueAntiImmu(RACINES);
        fighter_.activerAttaqueImmu(TROU, data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, BoolVal.TRUE);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        assertEq(new Rate("1873/100"), partner_.getRemainingHp());
        Effect effect_ = data_.getMove(RELAIS).getEffet(0);
        fight_.addEffect(thrower_, thrower_, effect_);
        FightEffects.effectBatonPass(fight_, thrower_, diff_, data_);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(3, map_.size());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
        assertEq(0, partner_.getNbUsesMoves().getVal(BOUL_ARMURE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("1"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("1"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("0"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(NULL_REF, partner_.getLastSufferedMove());
        assertEq(new Rate("1873/100"), partner_.getRemainingHp());
        assertEq(new Rate("0"), partner_.getClone());
        assertEq(0, partner_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectBatonPass4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter partner_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        Fighter fighter_ = fight_.getFighter(thrower_);
        FightRound.initRound(fight_);
        fighter_.getStatisBoost().put(Statistic.ATTACK, (byte) 1);
        fighter_.creerClone(new Rate("1/2"));
        fighter_.getNbUsesMoves().put(BOUL_ARMURE, 1);
        fighter_.getDamageSufferedCateg().put(PHYSIQUE, Rate.one());
        fighter_.getDamageSufferedCategRound().put(PHYSIQUE, Rate.one());
        fighter_.getDamageRateSufferedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.setLastSufferedMove(JACKPOT);
        fighter_.getNbRepeatingSuccessfulMoves().affect(new LgInt("2"));
        fighter_.activerAttaque(EMBARGO);
        fighter_.activerAttaqueAntiImmu(RACINES);
        fighter_.activerAttaqueImmu(TROU, data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, BoolVal.TRUE);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        assertEq(new Rate("561/25"), partner_.getRemainingHp());
        Effect effect_ = data_.getMove(RELAIS).getEffet(0);
        fight_.addEffect(thrower_, thrower_, effect_);
        FightEffects.effectBatonPass(fight_, thrower_, diff_, data_);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(3, map_.size());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
        assertEq(0, partner_.getNbUsesMoves().getVal(BOUL_ARMURE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("1"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("1"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("0"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(NULL_REF, partner_.getLastSufferedMove());
        assertEq(new Rate("561/25"), partner_.getRemainingHp());
        assertEq(new Rate("0"), partner_.getClone());
        assertEq(0, partner_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectBatonPass5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter partner_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 2);
        FightRound.initRound(fight_);
        fighter_.getStatisBoost().put(Statistic.ATTACK, (byte) 1);
        fighter_.creerClone(new Rate("1/2"));
        fighter_.getNbUsesMoves().put(BOUL_ARMURE, 1);
        fighter_.getDamageSufferedCateg().put(PHYSIQUE, Rate.one());
        fighter_.getDamageSufferedCategRound().put(PHYSIQUE, Rate.one());
        fighter_.getDamageRateSufferedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.setLastSufferedMove(JACKPOT);
        fighter_.getNbRepeatingSuccessfulMoves().affect(new LgInt("2"));
        fighter_.activerAttaque(EMBARGO);
        fighter_.activerAttaqueAntiImmu(RACINES);
        fighter_.activerAttaqueImmu(TROU, data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, BoolVal.TRUE);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        Effect effect_ = data_.getMove(RELAIS).getEffet(0);
        fight_.addEffect(thrower_, thrower_, effect_);
        FightEffects.effectBatonPass(fight_, thrower_, diff_, data_);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(3, map_.getVal((byte) 0).size());
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 1));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 2));
        assertEq(0, map_.getVal((byte) 1).size());
        assertEq(3, fight_.getFirstPositFoeFighters().size());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1));
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 2));
        assertEq(0, partner_.getGroundPlace());
        assertEq(0, partner_.getGroundPlaceSubst());
        assertEq(1, partner_.getNbUsesMoves().getVal(BOUL_ARMURE));
        assertEq(Rate.one(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("2"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(JACKPOT, partner_.getLastSufferedMove());
        assertEq(new Rate("106/5"), partner_.getRemainingHp());
        assertEq(new Rate("46/5"), partner_.getClone());
        assertEq(1, partner_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fight_.getEffects().size());
        AnimationSwitch animSwitch_ = (AnimationSwitch) fight_.getEffects().last();
        assertEq(POKEMON_FOE_TARGET_ZERO, animSwitch_.getSubstituted());
        assertEq(TARTARD, animSwitch_.getSubstituteName());
        assertEq(4, animSwitch_.getLevel());
        assertTrue(!animSwitch_.isKo());
        assertEq(new LgInt("100"), animSwitch_.getRateRemainHp());
        assertEq(new LgInt("0"), animSwitch_.getWonExpRate());
    }

    @Test
    public void effectBatonPass6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_, data_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter partner_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        Fighter fighter_ = fight_.getFighter(thrower_);
        FightRound.initRound(fight_);
        fighter_.getStatisBoost().put(Statistic.ATTACK, (byte) 1);
        fighter_.creerClone(new Rate("1/2"));
        fighter_.getNbUsesMoves().put(BOUL_ARMURE, 1);
        fighter_.getDamageSufferedCateg().put(PHYSIQUE, Rate.one());
        fighter_.getDamageSufferedCategRound().put(PHYSIQUE, Rate.one());
        fighter_.getDamageRateSufferedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.setLastSufferedMove(JACKPOT);
        fighter_.getNbRepeatingSuccessfulMoves().affect(new LgInt("2"));
        fighter_.activerAttaque(EMBARGO);
        fighter_.activerAttaqueAntiImmu(RACINES);
        fighter_.activerAttaqueImmu(TROU, data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, BoolVal.TRUE);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        Effect effect_ = data_.getMove(RELAIS).getEffet(0);
        fight_.addEffect(thrower_, thrower_, effect_);
        FightEffects.effectBatonPass(fight_, thrower_, diff_, data_);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(2, map_.getVal((byte) 0).size());
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 1));
        assertEq(0, map_.getVal((byte) 1).size());
        assertEq(3, fight_.getFirstPositFoeFighters().size());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, partner_.getGroundPlace());
        assertEq(Fighter.BACK, partner_.getGroundPlaceSubst());
        assertEq(0, partner_.getNbUsesMoves().getVal(BOUL_ARMURE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("1"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("1"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("0"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(NULL_REF, partner_.getLastSufferedMove());
        assertEq(new Rate("106/5"), partner_.getRemainingHp());
        assertEq(new Rate("0"), partner_.getClone());
        assertEq(0, partner_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectGlobal1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(GEL);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) data_.getMove(ZENITH).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, ZENITH, data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(ZENITH);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertSame(BoolVal.FALSE,fight_.getStillEnabledMoves().getVal(ZENITH));
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, FEU));
        assertEq(0, fighter_.getStatusNbRound(GEL));
    }

    @Test
    public void effectGlobal2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(GEL);
        fight_.enableGlobalMove(ZENITH);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) data_.getMove(ZENITH).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, ZENITH, data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(ZENITH);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertSame(BoolVal.FALSE,fight_.getStillEnabledMoves().getVal(ZENITH));
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, FEU));
        assertEq(0, fighter_.getStatusNbRound(GEL));
    }

    @Test
    public void effectGlobal3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.getProtectedAgainstMoveTypes().add(SOL);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) data_.getMove(GRAVITE).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, GRAVITE, data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(GRAVITE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
        assertEq(0, fighter_.getProtectedAgainstMoveTypes().size());
    }

    @Test
    public void effectGlobal4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fight_.enableGlobalMove(DISTORSION);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) data_.getMove(DISTORSION).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, DISTORSION, data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(DISTORSION);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
    }

    @Test
    public void effectGlobal5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(GEL);
        fight_.enableGlobalMove(TEMPETESABLE);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) data_.getMove(ZENITH).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, ZENITH, data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(TEMPETESABLE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertSame(BoolVal.FALSE,fight_.getStillEnabledMoves().getVal(ZENITH));
        activity_ = fight_.getEnabledMoves().getVal(ZENITH);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertSame(BoolVal.FALSE,fight_.getStillEnabledMoves().getVal(ZENITH));
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, FEU));
        assertEq(0, fighter_.getStatusNbRound(GEL));
    }

    @Test
    public void effectGlobal6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(GEL);
        fight_.enableGlobalMove(DANSE_PLUIE);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) data_.getMove(ZENITH).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, ZENITH, data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(DANSE_PLUIE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertSame(BoolVal.FALSE,fight_.getStillEnabledMoves().getVal(DANSE_PLUIE));
        activity_ = fight_.getEnabledMoves().getVal(ZENITH);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertSame(BoolVal.FALSE,fight_.getStillEnabledMoves().getVal(ZENITH));
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(2, types_.size());
        assertTrue(StringUtil.contains(types_, FEU));
        assertTrue(StringUtil.contains(types_, EAU));
        assertEq(0, fighter_.getStatusNbRound(GEL));
    }

    @Test
    public void effectGlobal7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) data_.getMove(DISTORSION).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, DISTORSION, data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(DISTORSION);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
    }

    @Test
    public void effectGlobal8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.getStatisBoost().put(Statistic.ATTACK, (byte) 1);
        fighter_.getStatisBoost().put(Statistic.DEFENSE, (byte) -1);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) data_.getMove(ZONE_ETRANGE).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, ZONE_ETRANGE, data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(ZONE_ETRANGE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE));
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
    }

    @Test
    public void effectGlobal9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        fight_.enableGlobalMove(GRAVITE);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) data_.getMove(REQUIEM).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, REQUIEM, data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(REQUIEM);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        activity_ = fight_.getEnabledMoves().getVal(GRAVITE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
    }

    @Test
    public void effectGlobal10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, AMOUR);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) data_.getMove(BROUHAHA).getEffects().last();
        FightEffects.effectGlobal(fight_, eff_, BROUHAHA, data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(AMOUR, POKEMON_FOE_FIGHTER_ZERO)));
    }

    @Test
    public void effectMultiplyUsedMovePower1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setMultiplicityFight((byte) 1);
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EffectMultUsedMovePower eff_;
        eff_ = (EffectMultUsedMovePower) data_.getMove(CHARGEUR).getEffects().last();
        FightEffects.effectMultiplyUsedMovePower(fight_, thrower_, eff_, CHARGEUR, data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("2"), fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
    }

    @Test
    public void effectMultiplyUndergoneMovePower1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setMultiplicityFight((byte) 1);
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EffectMultSufferedMovePower eff_;
        eff_ = (EffectMultSufferedMovePower) data_.getMove(TOURNIQUET).getEffects().first();
        FightEffects.effectMultiplyUndergoneMovePower(fight_, thrower_, eff_, TOURNIQUET, data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1/2"), fighter_.getDamageRateSufferedByType().getVal(FEU));
    }

    private static Fight effectDamageRate(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
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
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 3);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(MAGNET);
        allyPokemon_.setAbility(SECHERESSE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 4);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
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
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectDamageRate1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffect anim_ = (AnimationEffect) fight_.getEffects().first();
        assertSame(EffectKind.ABSORB,anim_.getEffectKind());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectDamageRate2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(ANTI_SOIN);
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        assertTrue(fight_.getAcceptableChoices());
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectDamageRate3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        fight_.getDamageByCurrentUser().put(target_, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
//        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
//        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
//        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
//        assertTrue(!anim_.isKoFromFighter());
//        assertTrue(!anim_.isKoToFighter());
        AnimationEffect animRecoil_ = (AnimationEffect) fight_.getEffects().last();
        assertSame(EffectKind.SIMPLE,animRecoil_.getEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getFromFighter());
        assertTrue(animRecoil_.isKoFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getToFighter());
        assertTrue(animRecoil_.isKoToFighter());
    }

    @Test
    public void effectDamageRate4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setAbility(SUINTEMENT);
        fighter_.setCurrentAbility(SUINTEMENT);
        FightKo.setKoMoveTeams(fight_, target_, diff_, data_);
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffect anim_ = (AnimationEffect) fight_.getEffects().first();
        assertSame(EffectKind.ABSORB,anim_.getEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertTrue(anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectDamageRate5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_.setRemainedHp(Rate.one());
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffect anim_ = (AnimationEffect) fight_.getEffects().first();
        assertSame(EffectKind.ABSORB,anim_.getEffectKind());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectDamageRate6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(BOUE_NOIRE);
        fighter_.setRemainedHp(Rate.one());
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffect anim_ = (AnimationEffect) fight_.getEffects().first();
        assertSame(EffectKind.ABSORB,anim_.getEffectKind());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectDamageRate7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(GROSSERACINE);
        fighter_.setRemainedHp(Rate.one());
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("9"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffect anim_ = (AnimationEffect) fight_.getEffects().first();
        assertSame(EffectKind.ABSORB,anim_.getEffectKind());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectDamageRate8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(new Rate("5"));
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect anim_ = (AnimationAutoEffect) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertSame(AutoEffectKind.RECOIL,anim_.getAutoEffectKind());
        assertTrue(!anim_.isKoUser());
//        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
//        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
//        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
//        assertTrue(!anim_.isKoFromFighter());
//        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectDamageRate9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(new Rate("5"));
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(SACRIFICE).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("3"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect animRecoil_ = (AnimationAutoEffect) fight_.getEffects().last();
        assertSame(AutoEffectKind.RECOIL,animRecoil_.getAutoEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
    }

    @Test
    public void effectDamageRate10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(new Rate("5"));
        fighter_.setCurrentAbility(METEO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(SACRIFICE).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("3"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect animRecoil_ = (AnimationAutoEffect) fight_.getEffects().last();
        assertSame(AutoEffectKind.RECOIL,animRecoil_.getAutoEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
    }

    @Test
    public void effectDamageRate11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(new Rate("5"));
        fighter_.setCurrentAbility(TETE_DE_ROC);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(SACRIFICE).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectDamageRate12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        fight_.getDamageByCurrentUser().put(target_, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
//        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
//        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
//        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
//        assertTrue(!anim_.isKoFromFighter());
//        assertTrue(!anim_.isKoToFighter());
        AnimationEffect animRecoil_ = (AnimationEffect) fight_.getEffects().last();
        assertSame(EffectKind.SIMPLE,animRecoil_.getEffectKind());
        assertEq(POKEMON_FOE_TARGET_ZERO, animRecoil_.getFromFighter());
        assertTrue(animRecoil_.isKoFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animRecoil_.getToFighter());
        assertTrue(animRecoil_.isKoToFighter());
    }

    @Test
    public void effectDamageRate13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(new Rate("2"));
        fighter_.setCurrentAbility(METEO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(SACRIFICE).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect animRecoil_ = (AnimationAutoEffect) fight_.getEffects().last();
        assertSame(AutoEffectKind.RECOIL,animRecoil_.getAutoEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectDamageRate1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_, data_);
        fight_.setSimulation(true);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        fight_.getDamageByCurrentUser().put(target_, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectFullHpRate1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) data_.getMove(SOIN).getEffects().last();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, data_);
        assertEq(new Rate("2073/200"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animHeal_ = (AnimationHealing) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animHeal_.getHealed());
    }

    @Test
    public void effectFullHpRate2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) data_.getMove(MALEDICTION_2).getEffects().first();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, data_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect animRecoil_ = (AnimationAutoEffect) fight_.getEffects().last();
        assertSame(AutoEffectKind.KO,animRecoil_.getAutoEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectFullHpRate3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("2073/200"));
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) data_.getMove(MALEDICTION_2).getEffects().first();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, data_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect animRecoil_ = (AnimationAutoEffect) fight_.getEffects().last();
        assertSame(AutoEffectKind.RECOIL,animRecoil_.getAutoEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
    }

    @Test
    public void effectFullHpRate4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) data_.getMove(MALEDICTION_2).getEffects().first();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect animRecoil_ = (AnimationAutoEffect) fight_.getEffects().last();
        assertSame(AutoEffectKind.KO,animRecoil_.getAutoEffectKind());
        assertEq(POKEMON_FOE_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectFullHpRate5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) data_.getMove(REBONDIFEU).getEffects().last();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(new Rate("371/20"), fighter_.getRemainingHp());
        //106/5 * 1/8 = 53/20 106/5 * 7/8 = 53/5 * 7/4 = 371/20
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect animRecoil_ = (AnimationAutoEffect) fight_.getEffects().first();
        assertSame(AutoEffectKind.RECOIL,animRecoil_.getAutoEffectKind());
        assertEq(POKEMON_FOE_TARGET_ONE, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
    }

    @Test
    public void effectFullHpRate6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.setRemainedHp(Rate.one());
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) data_.getMove(REBONDIFEU).getEffects().last();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, data_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect animRecoil_ = (AnimationAutoEffect) fight_.getEffects().first();
        assertSame(AutoEffectKind.KO,animRecoil_.getAutoEffectKind());
        assertEq(POKEMON_FOE_TARGET_ONE, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectFullHpRate7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        fighter_.setRemainedHp(new Rate("1/2"));
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) data_.getMove(REBONDIFEU).getEffects().last();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        assertTrue(fighter_.getRemainingHp().toNumberString(),fighter_.estKo());
        //106/5 * 1/8 = 53/20 106/5 * 7/8 = 53/5 * 7/4 = 371/20
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect animRecoil_ = (AnimationAutoEffect) fight_.getEffects().first();
        assertSame(AutoEffectKind.KO,animRecoil_.getAutoEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ONE, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectFullHpRate1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        fight_.setSimulation(true);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) data_.getMove(MALEDICTION_2).getEffects().first();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, data_);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectFullHpRate2SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        fight_.setSimulation(true);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        fighter_.setRemainedHp(new Rate("1/2"));
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) data_.getMove(REBONDIFEU).getEffects().last();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        assertTrue(fighter_.getRemainingHp().toNumberString(),fighter_.estKo());
        //106/5 * 1/8 = 53/20 106/5 * 7/8 = 53/5 * 7/4 = 371/20
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectFullHpRate3SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        fight_.setSimulation(true);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.setRemainedHp(Rate.one());
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) data_.getMove(REBONDIFEU).getEffects().last();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, data_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectLeftHpRate1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        EffectRemainedHpRate eff_;
        eff_ = (EffectRemainedHpRate) data_.getMove(ABIME).getEffects().last();
        FightEffects.effectLeftHpRate(fight_, POKEMON_FOE_FIGHTER_ZERO, eff_, diff_, data_);
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect animRecoil_ = (AnimationAutoEffect) fight_.getEffects().first();
        assertSame(AutoEffectKind.KO,animRecoil_.getAutoEffectKind());
        assertEq(POKEMON_FOE_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectLeftHpRate2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        EffectRemainedHpRate eff_;
        eff_ = (EffectRemainedHpRate) data_.getMove(ABIME).getEffects().last();
        FightEffects.effectLeftHpRate(fight_, POKEMON_PLAYER_FIGHTER_ZERO, eff_, diff_, data_);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect animRecoil_ = (AnimationAutoEffect) fight_.getEffects().first();
        assertSame(AutoEffectKind.KO,animRecoil_.getAutoEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectLeftHpRate3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        EffectRemainedHpRate eff_;
        eff_ = (EffectRemainedHpRate) data_.getMove(CASSE).getEffects().first();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        FightEffects.effectLeftHpRate(fight_, POKEMON_PLAYER_FIGHTER_ZERO, eff_, diff_, data_);
        assertEq(new Rate("5619/400"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animRecoil_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getHealed());
    }

    @Test
    public void effectLeftHpRate4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        EffectRemainedHpRate eff_;
        eff_ = (EffectRemainedHpRate) data_.getMove(CASSE).getEffects().last();
        FightEffects.effectLeftHpRate(fight_, POKEMON_PLAYER_FIGHTER_ZERO, eff_, diff_, data_);
        assertEq(new Rate("1873/200"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAutoEffect animRecoil_ = (AnimationAutoEffect) fight_.getEffects().first();
        assertSame(AutoEffectKind.RECOIL,animRecoil_.getAutoEffectKind());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
    }

    @Test
    public void effectLeftHpRate1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        fight_.setSimulation(true);
        EffectRemainedHpRate eff_;
        eff_ = (EffectRemainedHpRate) data_.getMove(ABIME).getEffects().last();
        FightEffects.effectLeftHpRate(fight_, POKEMON_PLAYER_FIGHTER_ZERO, eff_, diff_, data_);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectVarPp1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        EffectVarPP eff_;
        eff_ = (EffectVarPP) data_.getMove(DEPIT).getEffects().first();
        FightEffects.effectVarPp(fight_, target_, eff_, diff_, data_);
        assertEq(16, fighter_.powerPointsMove(JACKPOT));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void effectVarPp2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_.usePowerPointsByMove(diff_, JACKPOT, (short) 17);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        EffectVarPP eff_;
        eff_ = (EffectVarPP) data_.getMove(DEPIT).getEffects().first();
        FightEffects.effectVarPp(fight_, target_, eff_, diff_, data_);
        assertEq(10, fighter_.powerPointsMove(JACKPOT));
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void effectVarPp3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        EffectVarPP eff_;
        eff_ = (EffectVarPP) data_.getMove(DEPIT).getEffects().first();
        FightEffects.effectVarPp(fight_, target_, eff_, diff_, data_);
        assertEq(16, fighter_.powerPointsMove(JACKPOT));
        assertTrue(!fighter_.isUsingItem());
    }

    private static Fight processEffectTarget(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME, _diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
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
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 3);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(MAGNET);
        allyPokemon_.setAbility(SECHERESSE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 4);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
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
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void processEffectTarget1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(PROVOC, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, PROVOC, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMoves();
        ActivityOfMove value_;
        value_ = map_.getVal(PROVOC);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(OEIL_MIRACLE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, OEIL_MIRACLE, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMovesUnprot();
        ActivityOfMove value_;
        value_ = map_.getVal(OEIL_MIRACLE);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(ECHANGE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, ECHANGE, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(METEO, fighter_.getCurrentAbility());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BOUE_NOIRE);
        fighter_.setFirstChosenMoveTarget(PASSE_PASSE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, PASSE_PASSE, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(BOUE_NOIRE, fighter_.getItem());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(PLAQUE_DRACO, fighter_.getItem());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(CAMOUFLAGE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, CAMOUFLAGE, IndexConstants.FIRST_INDEX, thrower_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(CLONAGE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, CLONAGE, IndexConstants.FIRST_INDEX, thrower_, thrower_, diff_, data_);
        assertEq(new Rate("5619/400"), fighter_.getRemainingHp());
        assertEq(new Rate("1873/400"), fighter_.getClone());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.setFirstChosenMoveTarget(BALANCE, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("3"));
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, BALANCE, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("2"), fighter_.getRemainingHp());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("2"), fighter_.getRemainingHp());
    }

    @Test
    public void processEffectTarget8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(COPIE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setLastUsedMove(JACKPOT);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, COPIE, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        CopiedMove newMoveInfo_ = fighter_.getCopiedMoves().getVal(COPIE);
        assertEq(JACKPOT, newMoveInfo_.getMove());
        assertEq(5, newMoveInfo_.getPp());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(MORPHING);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, MORPHING, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(TARTARD, fighter_.getCurrentName());
        assertEq(ARTIKODIN, fighter_.getName());
        assertTrue(fighter_.isChanged());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_.setFirstChosenMove(TONNERRE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, TONNERRE, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("27788/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("21432/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("21432/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
    }

    @Test
    public void processEffectTarget11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(INTERVERSION, POKEMON_PLAYER_TARGET_ZERO);
        FightEffects.processEffectTarget(fight_, INTERVERSION, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
    }

    @Test
    public void processEffectTarget12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFoeTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, AMOUR);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.getStatisBoost().put(Statistic.EVASINESS, (byte) 1);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, teamPosition_)).enable();
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(ANTI_BRUME);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, ANTI_BRUME, IndexConstants.FIRST_INDEX, thrower_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertEq(LgInt.zero(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, POKEMON_FOE_FIGHTER_ZERO)));
        activity_ = fight_.getUserTeam().getEnabledMoves().getVal(ANTI_BRUME);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,teamPosition_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS));
    }

    @Test
    public void processEffectTarget13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(PICOTS);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, PICOTS, IndexConstants.FIRST_INDEX, thrower_, thrower_, diff_, data_);
        assertEq(LgInt.one(), fight_.getUserTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(ANNEAU_HYDRO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, ANNEAU_HYDRO, IndexConstants.FIRST_INDEX, thrower_, thrower_, diff_, data_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getEnabledMovesEndRound().getVal(ANNEAU_HYDRO);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
    }

    @Test
    public void processEffectTarget15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, JACKPOT, IndexConstants.SECOND_INDEX, thrower_, thrower_, diff_, data_);
        assertEq(new Rate("240"), fight_.getWinningMoney());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget16Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterStatut(GEL);
        fighter_.setFirstChosenMove(ZENITH);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, ZENITH, IndexConstants.FIRST_INDEX, thrower_, thrower_, diff_, data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(ZENITH);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertSame(BoolVal.FALSE,fight_.getStillEnabledMoves().getVal(ZENITH));
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, FEU));
        assertEq(0, fighter_.getStatusNbRound(GEL));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget17Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(TROU);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, TROU, IndexConstants.FIRST_INDEX, thrower_, thrower_, diff_, data_);
        ActivityOfMove activity_;
        activity_ = fighter_.getEnabledMovesProt().getVal(TROU);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        StringList types_ = fighter_.getProtectedAgainstMoveTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, VOL));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget18Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(CHARGEUR);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, CHARGEUR, IndexConstants.SECOND_INDEX, thrower_, thrower_, diff_, data_);
        assertEq(new Rate("2"), fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget19Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(TOURNIQUET);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, TOURNIQUET, IndexConstants.FIRST_INDEX, thrower_, thrower_, diff_, data_);
        assertEq(new Rate("1/2"), fighter_.getDamageRateSufferedByType().getVal(FEU));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget20Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(COUP_D_MAIN, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, COUP_D_MAIN, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertSame(BoolVal.TRUE,fighter_.getEnabledMovesForAlly().getVal(COUP_D_MAIN));
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget21Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(LIRE_ESPRIT);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, LIRE_ESPRIT, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        assertSame(BoolVal.TRUE,fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, target_)));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget22Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter partner_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(RELAIS);
        fighter_.setSubstituteForMove((byte) 1);
        //fighter_.setFirstChosenMoveSubstitute(move_, (byte) 1);
        FightRound.initRound(fight_);
        fighter_.getStatisBoost().put(Statistic.ATTACK, (byte) 1);
        fighter_.creerClone(new Rate("1/2"));
        fighter_.getNbUsesMoves().put(BOUL_ARMURE, 1);
        fighter_.getDamageSufferedCateg().put(PHYSIQUE, Rate.one());
        fighter_.getDamageSufferedCategRound().put(PHYSIQUE, Rate.one());
        fighter_.getDamageRateSufferedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.setLastSufferedMove(JACKPOT);
        fighter_.getNbRepeatingSuccessfulMoves().affect(new LgInt("2"));
        fighter_.activerAttaque(EMBARGO);
        fighter_.activerAttaqueAntiImmu(RACINES);
        fighter_.activerAttaqueImmu(TROU, data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, BoolVal.TRUE);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        FightEffects.processEffectTarget(fight_, RELAIS, IndexConstants.FIRST_INDEX, thrower_, thrower_, diff_, data_);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(2, map_.getVal((byte) 0).size());
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 1));
        assertEq(2, map_.getVal((byte) 1).size());
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(0, partner_.getGroundPlace());
        assertEq(0, partner_.getGroundPlaceSubst());
        assertEq(1, partner_.getNbUsesMoves().getVal(BOUL_ARMURE));
        assertEq(Rate.one(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("2"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(JACKPOT, partner_.getLastSufferedMove());
        assertEq(new Rate("1873/100"), partner_.getRemainingHp());
        assertEq(new Rate("1873/200"), partner_.getClone());
        assertEq(1, partner_.getStatisBoost().getVal(Statistic.ATTACK));
    }

    @Test
    public void processEffectTarget23Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(COUD_BOUE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, COUD_BOUE, IndexConstants.SECOND_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget24Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(COUD_BOUE, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.creerClone(new Rate("1/2"));
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, COUD_BOUE, IndexConstants.SECOND_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY));
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget25Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(BERCEUSE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, BERCEUSE, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget26Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setFirstChosenMoveTarget(VAMPIPOING, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getDamageByCurrentUser().put(target_, new Rate("8"));
        FightEffects.processEffectTarget(fight_, VAMPIPOING, IndexConstants.SECOND_INDEX, thrower_, thrower_, diff_, data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("5"), fighter_.getRemainingHp());
    }

    @Test
    public void processEffectTarget27Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setFirstChosenMove(SOIN);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, SOIN, IndexConstants.FIRST_INDEX, thrower_, thrower_, diff_, data_);
        assertEq(new Rate("2073/200"), fighter_.getRemainingHp());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget28Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(ABIME, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, ABIME, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget29Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(DEPIT);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, DEPIT, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(16, fighter_.powerPointsMove(JACKPOT));
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget30Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(PAR_ICI);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, PAR_ICI, IndexConstants.FIRST_INDEX, thrower_, thrower_, diff_, data_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget31Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(DETECTION);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, DETECTION, IndexConstants.FIRST_INDEX, thrower_, thrower_, diff_, data_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget32Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(APRES_VOUS, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, APRES_VOUS, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget33Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(ELECTRISATION, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, ELECTRISATION, IndexConstants.FIRST_INDEX, thrower_, target_, diff_, data_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget34Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, NUEE_DE_POUDRE, IndexConstants.SECOND_INDEX, thrower_, target_, diff_, data_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    private void effectStatisticRandom(Fight _fight, TeamPosition _thrower, TeamPosition _target, EffectStatistic _eff, IdList<Statistic> _statistics, Rate _zero, boolean _begin, DataBase _data) {
        FightEffects.effectStatisticRandom(_fight, _thrower, _target, _eff, _statistics, _data, FightSuccess.probaEffectStatistic(_fight, _thrower, _zero, _begin, _data));
    }

}
