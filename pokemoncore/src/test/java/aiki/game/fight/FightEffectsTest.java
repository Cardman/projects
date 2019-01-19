package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectCommonStatistics;
import aiki.fight.moves.effects.EffectCopyFighter;
import aiki.fight.moves.effects.EffectCopyMove;
import aiki.fight.moves.effects.EffectDamageRate;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundPositionRelation;
import aiki.fight.moves.effects.EffectEndRoundPositionTargetRelation;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import aiki.fight.moves.effects.EffectFullHpRate;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectMultSufferedMovePower;
import aiki.fight.moves.effects.EffectMultUsedMovePower;
import aiki.fight.moves.effects.EffectRemainedHpRate;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.effects.EffectSwitchAbilities;
import aiki.fight.moves.effects.EffectSwitchItems;
import aiki.fight.moves.effects.EffectSwitchTypes;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.moves.effects.EffectUnprotectFromTypes;
import aiki.fight.moves.effects.EffectVarPP;
import aiki.game.UsesOfMove;
import aiki.game.fight.animations.AnimationAbsorb;
import aiki.game.fight.animations.AnimationChangedPlace;
import aiki.game.fight.animations.AnimationEffect;
import aiki.game.fight.animations.AnimationEffectStatistic;
import aiki.game.fight.animations.AnimationEffectStatus;
import aiki.game.fight.animations.AnimationHealing;
import aiki.game.fight.animations.AnimationKo;
import aiki.game.fight.animations.AnimationRecoil;
import aiki.game.fight.animations.AnimationSwitch;
import aiki.game.fight.animations.InfosAnimationStatistic;
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
import code.util.EnumList;
import code.util.EnumMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;


public class FightEffectsTest extends InitializationDataBase {

    private static final String SOMMEIL_FAILURE = "cardinal(inter({VAR__CIBLE_STATUTS},{SOMMEIL;SOMMEIL_REPOS}))>0|VAR__CIBLE_CLONE>0";
    private static final String VAR_FAIL_SYNCHRONIZING_STATUS = "VAR__EXISTE_GENRE_ASSEXUE|VAR__GENRES_EGAUX|VAR__CIBLE_POSSEDE_STATUT_RELATION__AMOUR";

    private static Fight disableStatus(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightInitialization.initFight(fight_, player_, _diff, dual_, _data_);
        FightInitialization.initFight(fight_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void disableStatus1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = disableStatus(diff_);
        fight_.enableGlobalMove(ZENITH);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(GEL);
        FightEffects.disableStatus(fight_, POKEMON_PLAYER_FIGHTER_ZERO, _data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatusNbRound(GEL).intValue());
    }

    @Test
    public void disableStatus2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = disableStatus(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, AMOUR);
        fight_.enableGlobalMove(BROUHAHA);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(GEL);
        FightEffects.disableStatus(fight_, POKEMON_PLAYER_FIGHTER_ZERO, _data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(AMOUR, POKEMON_FOE_FIGHTER_ZERO)).intValue());
    }

    private static Fight deltaBoostStatistic() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void deltaBoostStatistic1Test() {
        Fight fight_ = deltaBoostStatistic();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(1, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, _data_));
    }

    @Test
    public void deltaBoostStatistic2Test() {
        Fight fight_ = deltaBoostStatistic();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(-1, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, _data_));
    }

    @Test
    public void deltaBoostStatistic3Test() {
        Fight fight_ = deltaBoostStatistic();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(6, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) 7, _data_));
    }

    @Test
    public void deltaBoostStatistic4Test() {
        Fight fight_ = deltaBoostStatistic();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(-6, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) -7, _data_));
    }

    @Test
    public void deltaBoostStatistic5Test() {
        Fight fight_ = deltaBoostStatistic();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(0, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) 0, _data_));
    }

    @Test
    public void deltaBoostStatistic6Test() {
        Fight fight_ = deltaBoostStatistic();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(CRAN);
        assertEq(-6, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) -7, _data_));
    }

    @Test
    public void deltaBoostStatistic7Test() {
        Fight fight_ = deltaBoostStatistic();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(SIMPLE);
        assertEq(2, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, _data_));
    }

    @Test
    public void deltaBoostStatistic8Test() {
        Fight fight_ = deltaBoostStatistic();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(SIMPLE);
        assertEq(-2, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, _data_));
    }

    @Test
    public void deltaBoostStatistic9Test() {
        Fight fight_ = deltaBoostStatistic();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(CONTRAIRE);
        assertEq(2, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, _data_));
        assertEq(2, FightEffects.deltaBoostStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, _data_));
    }

    private static Fight deltaBoostStatisticMap() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void deltaBoostStatisticMap1Test() {
        Fight fight_ = deltaBoostStatisticMap();
        EnumMap<Statistic,Byte> varsBase_ = new EnumMap<Statistic,Byte>();
        varsBase_.put(Statistic.ATTACK, (byte) 2);
        EnumMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, _data_);
        assertEq(1, vars_.size());
        assertEq(2, vars_.getVal(Statistic.ATTACK).intValue());
    }

    @Test
    public void deltaBoostStatisticMap2Test() {
        Fight fight_ = deltaBoostStatisticMap();
        EnumMap<Statistic,Byte> varsBase_ = new EnumMap<Statistic,Byte>();
        varsBase_.put(Statistic.ATTACK, (byte) -2);
        EnumMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, _data_);
        assertEq(1, vars_.size());
        assertEq(-2, vars_.getVal(Statistic.ATTACK).intValue());
    }

    @Test
    public void deltaBoostStatisticMap3Test() {
        Fight fight_ = deltaBoostStatisticMap();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        EnumMap<Statistic,Byte> varsBase_ = new EnumMap<Statistic,Byte>();
        varsBase_.put(Statistic.ATTACK, (byte) -2);
        EnumMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, _data_);
        assertEq(1, vars_.size());
        assertEq(-2, vars_.getVal(Statistic.ATTACK).intValue());
    }

    @Test
    public void deltaBoostStatisticMap4Test() {
        Fight fight_ = deltaBoostStatisticMap();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ACHARNE);
        EnumMap<Statistic,Byte> varsBase_ = new EnumMap<Statistic,Byte>();
        varsBase_.put(Statistic.ATTACK, (byte) 2);
        EnumMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, _data_);
        assertEq(1, vars_.size());
        assertEq(2, vars_.getVal(Statistic.ATTACK).intValue());
    }

    @Test
    public void deltaBoostStatisticMap5Test() {
        Fight fight_ = deltaBoostStatisticMap();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ACHARNE);
        EnumMap<Statistic,Byte> varsBase_ = new EnumMap<Statistic,Byte>();
        varsBase_.put(Statistic.ATTACK, (byte) -1);
        EnumMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, _data_);
        assertEq(1, vars_.size());
        assertEq(2, vars_.getVal(Statistic.ATTACK).intValue());
    }

    @Test
    public void deltaBoostStatisticMap6Test() {
        Fight fight_ = deltaBoostStatisticMap();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ACHARNE);
        EnumMap<Statistic,Byte> varsBase_ = new EnumMap<Statistic,Byte>();
        varsBase_.put(Statistic.SPECIAL_ATTACK, (byte) -1);
        EnumMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, _data_);
        assertEq(2, vars_.size());
        assertEq(0, vars_.getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(2, vars_.getVal(Statistic.ATTACK).intValue());
    }

    @Test
    public void deltaBoostStatisticMap7Test() {
        Fight fight_ = deltaBoostStatisticMap();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ACHARNE);
        EnumMap<Statistic,Byte> varsBase_ = new EnumMap<Statistic,Byte>();
        varsBase_.put(Statistic.DEFENSE, (byte) -2);
        varsBase_.put(Statistic.ATTACK, (byte) 1);
        EnumMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, _data_);
        assertEq(2, vars_.size());
        assertEq(0, vars_.getVal(Statistic.DEFENSE).intValue());
        assertEq(3, vars_.getVal(Statistic.ATTACK).intValue());
    }

    @Test
    public void deltaBoostStatisticMap8Test() {
        Fight fight_ = deltaBoostStatisticMap();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ACHARNE);
        EnumMap<Statistic,Byte> varsBase_ = new EnumMap<Statistic,Byte>();
        varsBase_.put(Statistic.DEFENSE, (byte) -2);
        varsBase_.put(Statistic.SPECIAL_ATTACK, (byte) 1);
        EnumMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, _data_);
        assertEq(3, vars_.size());
        assertEq(0, vars_.getVal(Statistic.DEFENSE).intValue());
        assertEq(1, vars_.getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(2, vars_.getVal(Statistic.ATTACK).intValue());
    }

    @Test
    public void deltaBoostStatisticMap9Test() {
        Fight fight_ = deltaBoostStatisticMap();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ACHARNE);
        EnumMap<Statistic,Byte> varsBase_ = new EnumMap<Statistic,Byte>();
        EnumMap<Statistic,Byte> vars_;
        vars_ = FightEffects.deltaBoostStatisticMap(fight_, POKEMON_PLAYER_FIGHTER_ZERO, varsBase_, _data_);
        assertEq(0, vars_.size());
    }

    private static Fight pairNewThrowerTarget() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void pairNewThrowerTarget1Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(false);
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, SEISME, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, _data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget2Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(false);
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, _data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget3Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(true);
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, _data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget4Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(SAISIE,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, _data_);
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getUser());
    }

    @Test
    public void pairNewThrowerTarget5Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, _data_);
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getUser());
    }

    @Test
    public void pairNewThrowerTarget6Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, _data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget7Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PAR_ICI);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, _data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget8Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANNEAU_HYDRO, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, _data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget9Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, MUR_DE_FER, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, _data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget10Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, PICOTS, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, _data_);
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget11Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, VANTARDISE, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, _data_);
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget12Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(SAISIE,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ACUPRESSION, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, _data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,pair_.getUser());
        assertEq(POKEMON_PLAYER_FIGHTER_ONE,pair_.getTarget());
    }

    @Test
    public void pairNewThrowerTarget13Test() {
        Fight fight_ = pairNewThrowerTarget();
        fight_.setChangeThrower(false);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(SAISIE,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        UserTarget pair_;
        pair_= FightEffects.pairNewThrowerTarget(fight_, ANTI_SOIN, CustList.FIRST_INDEX, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, _data_);
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getTarget());
        assertEq(POKEMON_FOE_FIGHTER_ZERO,pair_.getUser());
    }

    private static Fight effectRestriction() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectRestriction1Test() {
        Fight fight_ = effectRestriction();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).successUsingMove();
        MoveData fMove_ = _data_.getMove(ENCORE);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, ENCORE, _data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        ObjectMap<MoveTeamPosition,AffectedMove> map_ = fighter_.getTrackingMoves();
        AffectedMove value_;
        value_ = map_.getVal(new MoveTeamPosition(ENCORE, thrower_));
        assertEq(SEISME, value_.getMove());
        assertEq(0, value_.getActivity().getNbTurn());
        assertTrue(value_.getActivity().isEnabled());
    }

    @Test
    public void effectRestriction2Test() {
        Fight fight_ = effectRestriction();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).setFirstChosenMove(NULL_REF);
        FightRound.initRound(fight_);
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).successUsingMove();
        MoveData fMove_ = _data_.getMove(ENCORE);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, ENCORE, _data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        ObjectMap<MoveTeamPosition,AffectedMove> map_ = fighter_.getTrackingMoves();
        AffectedMove value_;
        value_ = map_.getVal(new MoveTeamPosition(ENCORE, thrower_));
        assertEq(NULL_REF, value_.getMove());
        assertEq(0, value_.getActivity().getNbTurn());
        assertTrue(!value_.getActivity().isEnabled());
    }

    @Test
    public void effectRestriction3Test() {
        Fight fight_ = effectRestriction();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).successUsingMove();
        MoveData fMove_ = _data_.getMove(ENTRAVE);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, ENTRAVE, _data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        ObjectMap<MoveTeamPosition,AffectedMove> map_ = fighter_.getTrackingMoves();
        AffectedMove value_;
        value_ = map_.getVal(new MoveTeamPosition(ENTRAVE, thrower_));
        assertEq(SEISME, value_.getMove());
        assertEq(0, value_.getActivity().getNbTurn());
        assertTrue(value_.getActivity().isEnabled());
    }

    @Test
    public void effectRestriction4Test() {
        Fight fight_ = effectRestriction();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).setFirstChosenMove(NULL_REF);
        FightRound.initRound(fight_);
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).successUsingMove();
        MoveData fMove_ = _data_.getMove(ENTRAVE);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, ENTRAVE, _data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        ObjectMap<MoveTeamPosition,AffectedMove> map_ = fighter_.getTrackingMoves();
        AffectedMove value_;
        value_ = map_.getVal(new MoveTeamPosition(ENTRAVE, thrower_));
        assertEq(NULL_REF, value_.getMove());
        assertEq(0, value_.getActivity().getNbTurn());
        assertTrue(!value_.getActivity().isEnabled());
    }

    @Test
    public void effectRestriction5Test() {
        Fight fight_ = effectRestriction();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(POSSESSIF);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, POSSESSIF, _data_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        ObjectMap<MoveTeamPosition,StringList> map_ = fighter_.getPrivateMoves();
        StringList value_;
        value_ = map_.getVal(new MoveTeamPosition(POSSESSIF, target_));
        assertEq(1, value_.size());
        assertTrue(value_.containsObj(COPIE));
    }

    @Test
    public void effectRestriction6Test() {
        Fight fight_ = effectRestriction();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(EMBARGO);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, EMBARGO, _data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMoves();
        ActivityOfMove value_;
        value_ = map_.getVal(EMBARGO);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
    }

    @Test
    public void effectRestriction7Test() {
        Fight fight_ = effectRestriction();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(PROVOC);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, PROVOC, _data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMoves();
        ActivityOfMove value_;
        value_ = map_.getVal(PROVOC);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
    }

    @Test
    public void effectRestriction8Test() {
        Fight fight_ = effectRestriction();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(TOURMENTE);
        EffectRestriction effect_ = (EffectRestriction) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectRestriction(fight_, thrower_, target_, effect_, TOURMENTE, _data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMoves();
        ActivityOfMove value_;
        value_ = map_.getVal(TOURMENTE);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
    }

    private static Fight effectUnprotectFromMoveTypes() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectUnprotectFromMoveTypes1Test() {
        Fight fight_ = effectUnprotectFromMoveTypes();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).activerAttaqueImmu(VOL_MAGNETIK, _data_);
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(ANTI_AIR);
        EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) fMove_.getEffet(CustList.SECOND_INDEX);
        FightEffects.effectUnprotectFromMoveTypes(fight_, target_, eff_, ANTI_AIR, _data_);
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
        Fight fight_ = effectUnprotectFromMoveTypes();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).activerAttaqueImmu(VOL_MAGNETIK, _data_);
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(RACINES);
        EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) fMove_.getEffet(CustList.SECOND_INDEX);
        FightEffects.effectUnprotectFromMoveTypes(fight_, target_, eff_, RACINES, _data_);
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
        Fight fight_ = effectUnprotectFromMoveTypes();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(OEIL_MIRACLE);
        EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectUnprotectFromMoveTypes(fight_, target_, eff_, OEIL_MIRACLE, _data_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        StringMap<ActivityOfMove> map_ = fighter_.getEnabledMovesUnprot();
        ActivityOfMove value_;
        value_ = map_.getVal(OEIL_MIRACLE);
        assertEq(0, value_.getNbTurn());
        assertTrue(value_.isEnabled());
    }

    @Test
    public void effectUnprotectFromMoveTypes4Test() {
        Fight fight_ = effectUnprotectFromMoveTypes();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).activerAttaqueImmu(TROU, _data_);
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(ANTI_SOL);
        EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) fMove_.getEffet(CustList.SECOND_INDEX);
        FightEffects.effectUnprotectFromMoveTypes(fight_, target_, eff_, ANTI_SOL, _data_);
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
        Fight fight_ = effectUnprotectFromMoveTypes();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).activerAttaqueImmu(TROU, _data_);
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(ANTI_CROISEUR);
        EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) fMove_.getEffet(CustList.SECOND_INDEX);
        FightEffects.effectUnprotectFromMoveTypes(fight_, target_, eff_, ANTI_CROISEUR, _data_);
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

    private static Fight effectSwitchAbilities() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectSwitchAbilities1Test() {
        Fight fight_ = effectSwitchAbilities();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightAbilities.enableAbility(fight_, thrower_, _data_);
        FightAbilities.enableAbility(fight_, target_, _data_);
        FightRound.initRound(fight_);
        StringList typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(typesThrower_.containsObj(FEU));
        StringList typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(typesTarget_.containsObj(DRAGON));
        MoveData fMove_ = _data_.getMove(ECHANGE);
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchAbilities(fight_, thrower_, target_, effect_, _data_);
        typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(typesThrower_.containsObj(DRAGON));
        typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(typesTarget_.containsObj(FEU));
    }

    @Test
    public void effectSwitchAbilities2Test() {
        Fight fight_ = effectSwitchAbilities();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightAbilities.enableAbility(fight_, thrower_, _data_);
        FightAbilities.enableAbility(fight_, target_, _data_);
        FightRound.initRound(fight_);
        StringList typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(typesThrower_.containsObj(FEU));
        StringList typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(typesTarget_.containsObj(DRAGON));
        MoveData fMove_ = _data_.getMove(TEN_DANSE);
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchAbilities(fight_, thrower_, target_, effect_, _data_);
        typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(typesThrower_.containsObj(FEU));
        typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(typesTarget_.containsObj(FEU));
    }

    @Test
    public void effectSwitchAbilities3Test() {
        Fight fight_ = effectSwitchAbilities();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightAbilities.enableAbility(fight_, thrower_, _data_);
        FightAbilities.enableAbility(fight_, target_, _data_);
        FightRound.initRound(fight_);
        StringList typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(typesThrower_.containsObj(FEU));
        StringList typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(typesTarget_.containsObj(DRAGON));
        MoveData fMove_ = _data_.getMove(IMITATION);
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchAbilities(fight_, thrower_, target_, effect_, _data_);
        typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(typesThrower_.containsObj(DRAGON));
        typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(typesTarget_.containsObj(DRAGON));
    }

    @Test
    public void effectSwitchAbilities4Test() {
        Fight fight_ = effectSwitchAbilities();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightAbilities.enableAbility(fight_, thrower_, _data_);
        FightAbilities.enableAbility(fight_, target_, _data_);
        FightRound.initRound(fight_);
        StringList typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(typesThrower_.containsObj(FEU));
        StringList typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(typesTarget_.containsObj(DRAGON));
        MoveData fMove_ = _data_.getMove(RAYON_SIMPLE);
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchAbilities(fight_, thrower_, target_, effect_, _data_);
        typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(typesThrower_.containsObj(FEU));
        typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(SIMPLE, fighter_.getCurrentAbility());
        assertEq(2, typesTarget_.size());
        assertTrue(typesTarget_.containsObj(EAU));
        assertTrue(typesTarget_.containsObj(COMBAT));
    }

    @Test
    public void effectSwitchAbilities5Test() {
        Fight fight_ = effectSwitchAbilities();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightAbilities.enableAbility(fight_, thrower_, _data_);
        FightAbilities.enableAbility(fight_, target_, _data_);
        FightRound.initRound(fight_);
        StringList typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(typesThrower_.containsObj(FEU));
        StringList typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(typesTarget_.containsObj(DRAGON));
        MoveData fMove_ = _data_.getMove(ECHANGE_BIS);
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchAbilities(fight_, thrower_, target_, effect_, _data_);
        typesThrower_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition()).getTypes();
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(1, typesThrower_.size());
        assertTrue(typesThrower_.containsObj(FEU));
        typesTarget_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition()).getTypes();
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(1, typesTarget_.size());
        assertTrue(typesTarget_.containsObj(DRAGON));
    }

    private static Fight effectSwitchObjects() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectSwitchObjects1Test() {
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        MoveData fMove_ = _data_.getMove(PASSE_PASSE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_MEPO,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(1, lostObjects_.size());
        assertTrue(lostObjects_.containsObj(BAIE_MEPO));
    }

    @Test
    public void effectSwitchObjects2Test() {
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(NULL_REF);
        MoveData fMove_ = _data_.getMove(PASSE_CADEAU);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(BAIE_MEPO,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(1, lostObjects_.size());
        assertTrue(lostObjects_.containsObj(BAIE_MEPO));
    }

    @Test
    public void effectSwitchObjects3Test() {
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_MEPO);
        MoveData fMove_ = _data_.getMove(IMPLORE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_MEPO,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects4Test() {
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_MEPO);
        MoveData fMove_ = _data_.getMove(CALCINATION);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, _data_);
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
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(HYPER_BALL);
        MoveData fMove_ = _data_.getMove(CALCINATION);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(HYPER_BALL,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects6Test() {
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(NULL_REF);
        MoveData fMove_ = _data_.getMove(CALCINATION);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects7Test() {
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(NULL_REF);
        MoveData fMove_ = _data_.getMove(PICORE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects8Test() {
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterStatut(PARALYSIE);
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_.setCurrentAbility(TENSION);
        MoveData fMove_ = _data_.getMove(PICORE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        assertEq(1,fighter_.getStatusNbRound(PARALYSIE).intValue());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        assertTrue(!fighter_.isUsingItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects9Test() {
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterStatut(PARALYSIE);
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        MoveData fMove_ = _data_.getMove(PICORE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        assertEq(0,fighter_.getStatusNbRound(PARALYSIE).intValue());
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
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterStatut(PARALYSIE);
        fighter_.backUpObject(BAIE_CERIZ);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(HYPER_BALL);
        MoveData fMove_ = _data_.getMove(PICORE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        assertEq(1,fighter_.getStatusNbRound(PARALYSIE).intValue());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(HYPER_BALL,fighter_.getItem());
        assertTrue(!fighter_.isUsingItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects11Test() {
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setLastUsedItem(BAIE_CERIZ);
        fighter_.setItem(NULL_REF);
        MoveData fMove_ = _data_.getMove(RECYCLAGE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, thrower_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_CERIZ,fighter_.getItem());
        assertEq(BAIE_CERIZ,fighter_.getLastUsedItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects12Test() {
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_CERIZ);
        MoveData fMove_ = _data_.getMove(LARCIN);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffects().last();
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        assertEq(PLAQUE_DRACO,fighter_.getLastUsedItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(0, lostObjects_.size());
    }

    @Test
    public void effectSwitchObjects13Test() {
        Fight fight_ = effectSwitchObjects();
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getUserTeam().getMembers().getVal(target_.getPosition());
        fighter_.backUpObject(BAIE_MEPO);
        MoveData fMove_ = _data_.getMove(IMPLORE);
        EffectSwitchItems effect_ = (EffectSwitchItems) fMove_.getEffet(CustList.SECOND_INDEX);
        FightEffects.effectSwitchObjects(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(BAIE_MEPO,fighter_.getItem());
        fighter_ = fight_.getUserTeam().getMembers().getVal(target_.getPosition());
        assertEq(NULL_REF,fighter_.getItem());
        StringList lostObjects_ = fight_.getLostObjects();
        assertEq(1, lostObjects_.size());
        assertTrue(lostObjects_.containsObj(BAIE_MEPO));
    }

    private static Fight effectSwitchTypes() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectSwitchTypes1Test() {
        Fight fight_ = effectSwitchTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(FEU);
        MoveData fMove_ = _data_.getMove(ADAPTATION);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(SOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes2Test() {
        Fight fight_ = effectSwitchTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(new StringList(COMBAT,ACIER));
        fighter_.getLastSufferedMoveTypes().add(ROCHE);
        MoveData fMove_ = _data_.getMove(CONVERSION_2);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(SOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes3Test() {
        Fight fight_ = effectSwitchTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(CAMOUFLAGE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, _data_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(SOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes4Test() {
        Fight fight_ = effectSwitchTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = _data_.getMove(COPIE_TYPE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(SOL));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(SOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes5Test() {
        Fight fight_ = effectSwitchTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = _data_.getMove(ECHANGE_TYPE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(SOL));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(VOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes6Test() {
        Fight fight_ = effectSwitchTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = _data_.getMove(FORCE_TYPE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(VOL));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(VOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes7Test() {
        Fight fight_ = effectSwitchTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = _data_.getMove(DETREMPAGE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(VOL));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(EAU));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes8Test() {
        Fight fight_ = effectSwitchTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = _data_.getMove(DETEINTE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(VOL));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(SOL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes9Test() {
        Fight fight_ = effectSwitchTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.affecterTypes(SOL);
        MoveData fMove_ = _data_.getMove(MALEFICE_SYLVAIN);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(VOL));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        types_ = fighter_.getTypes();
        assertEq(2, types_.size());
        assertTrue(types_.containsObj(SOL));
        assertTrue(types_.containsObj(SPECTRE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes10Test() {
        Fight fight_ = effectSwitchTypes();
        fight_.enableGlobalMove(CHAMP_BRUMEUX);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(VOL);
        MoveData fMove_ = _data_.getMove(CAMOUFLAGE);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(FEE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes1SimulationTest() {
        Fight fight_ = effectSwitchTypes();
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.affecterTypes(new StringList(ELECTRIQUE));
        fighter_.getLastSufferedMoveTypes().add(ROCHE);
        MoveData fMove_ = _data_.getMove(CONVERSION_2);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(ELECTRIQUE));
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchTypes2SimulationTest() {
        Fight fight_ = effectSwitchTypes();
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.getMoves().put(ECLAIR, new UsesOfMove((short)10));
        fighter_.getCurrentMoves().put(ECLAIR, new UsesOfMove((short)10));
        fighter_.affecterTypes(FEU);
        MoveData fMove_ = _data_.getMove(ADAPTATION);
        EffectSwitchTypes effect_ = (EffectSwitchTypes) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectSwitchTypes(fight_, thrower_, thrower_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(FEU));
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectSwitchMoveTypes1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        FightEffects.effectSwitchMoveTypes(fight_, ELECTRISATION, thrower_, _data_);
        assertEq(0, fighter_.getEnabledChangingTypesMoves().getVal(ELECTRISATION).getNbTurn());
        assertTrue(fighter_.getEnabledChangingTypesMoves().getVal(ELECTRISATION).isEnabled());
    }

    @Test
    public void effectCounterAttack1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        FightEffects.effectCounterAttack(fight_, NUEE_DE_POUDRE, thrower_, _data_);
        assertEq(0, fighter_.getEnabledCounteringMoves().getVal(NUEE_DE_POUDRE).getNbTurn());
        assertTrue(fighter_.getEnabledCounteringMoves().getVal(NUEE_DE_POUDRE).isEnabled());
    }

    private static Fight effectCommonStatistics() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectCommonStatistics1Test() {
        Fight fight_ = effectCommonStatistics();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setRemainedHp(new Rate("10"));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setRemainedHp(new Rate("5"));
        MoveData fMove_ = _data_.getMove(BALANCE);
        EffectCommonStatistics effect_ = (EffectCommonStatistics) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectCommonStatistics(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("15/2"),fighter_.getRemainingHp());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(new Rate("15/2"),fighter_.getRemainingHp());
    }

    @Test
    public void effectCommonStatistics2Test() {
        Fight fight_ = effectCommonStatistics();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(BALANCE);
        EffectCommonStatistics effect_ = (EffectCommonStatistics) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectCommonStatistics(fight_, thrower_, target_, effect_, _data_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("3713/200"),fighter_.getRemainingHp());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        assertEq(new Rate("1840/100"),fighter_.getRemainingHp());
    }

    @Test
    public void effectCommonStatistics3Test() {
        Fight fight_ = effectCommonStatistics();
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(BALANCE);
        EffectCommonStatistics effect_ = (EffectCommonStatistics) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectCommonStatistics(fight_, thrower_, target_, effect_, _data_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(target_.getPosition());
        assertEq(new Rate("3713/200"),fighter_.getRemainingHp());
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("1840/100"),fighter_.getRemainingHp());
    }

    @Test
    public void effectCommonStatistics4Test() {
        Fight fight_ = effectCommonStatistics();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("50"),fighter_.getStatisBase().getVal(Statistic.DEFENSE));
        assertEq(new Rate("50"),fighter_.getStatisBase().getVal(Statistic.SPECIAL_DEFENSE));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("95"),fighter_.getStatisBase().getVal(Statistic.DEFENSE));
        assertEq(new Rate("90"),fighter_.getStatisBase().getVal(Statistic.SPECIAL_DEFENSE));
        MoveData fMove_ = _data_.getMove(PARTAGE_GARDE);
        EffectCommonStatistics effect_ = (EffectCommonStatistics) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectCommonStatistics(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("145/2"),fighter_.getStatisBase().getVal(Statistic.DEFENSE));
        assertEq(new Rate("70"),fighter_.getStatisBase().getVal(Statistic.SPECIAL_DEFENSE));
        fighter_ = fight_.getFoeTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(new Rate("145/2"),fighter_.getStatisBase().getVal(Statistic.DEFENSE));
        assertEq(new Rate("70"),fighter_.getStatisBase().getVal(Statistic.SPECIAL_DEFENSE));
    }

    private static Fight effectCopyMove() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectCopyMove1Test() {
        Fight fight_ = effectCopyMove();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMove(COPIE);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setLastUsedMove(JACKPOT);
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(COPIE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        CopiedMove newMoveInfo_ = fighter_.getCopiedMoves().getVal(COPIE);
        assertEq(JACKPOT, newMoveInfo_.getMove());
        assertEq(5, newMoveInfo_.getPp());
    }

    @Test
    public void effectCopyMove2Test() {
        Fight fight_ = effectCopyMove();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMoveTarget(GRIBOUILLE, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setLastUsedMove(JACKPOT);
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(GRIBOUILLE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertTrue(!fighter_.getMovesSet().containsObj(JACKPOT));
        assertTrue(!fighter_.getCurrentMovesSet().containsObj(JACKPOT));
    }

    @Test
    public void effectCopyMove3Test() {
        Fight fight_ = effectCopyMove();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMove(COPIE);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setLastUsedMove(LUTTE);
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(COPIE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        CopiedMove newMoveInfo_ = fighter_.getCopiedMoves().getVal(COPIE);
        assertEq(NULL_REF, newMoveInfo_.getMove());
        assertEq(0, newMoveInfo_.getPp());
    }

    @Test
    public void effectCopyMove4Test() {
        Fight fight_ = effectCopyMove();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMove(COPIE);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(COPIE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        CopiedMove newMoveInfo_ = fighter_.getCopiedMoves().getVal(COPIE);
        assertEq(NULL_REF, newMoveInfo_.getMove());
        assertEq(0, newMoveInfo_.getPp());
    }

    @Test
    public void effectCopyMove5Test() {
        Fight fight_ = effectCopyMove();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMoveTarget(LUTTE, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setLastUsedMove(JACKPOT);
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(GRIBOUILLE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertTrue(!fighter_.getMoves().contains(LUTTE));
        assertTrue(!fighter_.getCurrentMoves().contains(LUTTE));
    }

    @Test
    public void effectCopyMove6Test() {
        Fight fight_ = effectCopyMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getMoves().put(GRIBOUILLE, new UsesOfMove((byte)10));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getCurrentMoves().put(GRIBOUILLE, new UsesOfMove((byte)10));
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        fighter_.setFirstChosenMoveTarget(GRIBOUILLE, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFoeTeam().getMembers().getVal(target_.getPosition());
        fighter_.setLastUsedMove(JACKPOT);
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(GRIBOUILLE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertEq(20, fighter_.getMove(JACKPOT).getCurrent());
        assertEq(20, fighter_.getMove(JACKPOT).getMax());
        assertEq(20, fighter_.getCurrentMove(JACKPOT).getCurrent());
        assertEq(20, fighter_.getCurrentMove(JACKPOT).getMax());
    }

    @Test
    public void effectCopyMove7Test() {
        Fight fight_ = effectCopyMove();
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
        MoveData fMove_ = _data_.getMove(GRIBOUILLE);
        EffectCopyMove effect_ = (EffectCopyMove) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectCopyMove(fight_, thrower_, target_, effect_, _data_);
        fighter_ = fight_.getUserTeam().getMembers().getVal(thrower_.getPosition());
        assertTrue(!fighter_.getMovesSet().containsObj(JACKPOT));
        assertTrue(fighter_.getMovesSet().containsObj(GRIBOUILLE));
        assertTrue(!fighter_.getCurrentMovesSet().containsObj(GRIBOUILLE));
        assertEq(20, fighter_.getCurrentMove(JACKPOT).getCurrent());
        assertEq(20, fighter_.getCurrentMove(JACKPOT).getMax());
    }

    @Test
    public void effectCopyFighter1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightRound.initRound(fight_);
        MoveData fMove_ = _data_.getMove(MORPHING);
        EffectCopyFighter effect_ = (EffectCopyFighter) fMove_.getEffet(CustList.FIRST_INDEX);
        FightEffects.effectCopyFighter(fight_, thrower_, target_, effect_, _data_);
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

    private static Fight generatedStatusLaw(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void generatedStatusLaw1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = generatedStatusLaw(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, LgInt.one());
        law_.addEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, _data_);
        assertEq(2, res_.events().size());
        assertTrue(res_.events().containsObj(NULL_REF));
        assertTrue(res_.events().containsObj(SOMMEIL));
        assertEq(new LgInt("1"),res_.rate(NULL_REF));
        assertEq(new LgInt("1"),res_.rate(SOMMEIL));
    }

    @Test
    public void generatedStatusLaw2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = generatedStatusLaw(diff_);
        fight_.enableGlobalMove(BROUHAHA);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, LgInt.one());
        law_.addEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, _data_);
        assertEq(1, res_.events().size());
        assertTrue(res_.events().containsObj(NULL_REF));
        assertTrue(res_.isValid());
    }

    @Test
    public void generatedStatusLaw3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = generatedStatusLaw(diff_);
        fight_.enableGlobalMove(BROUHAHA);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, _data_);
        assertEq(1, res_.events().size());
        assertTrue(res_.events().containsObj(NULL_REF));
        assertTrue(res_.isValid());
    }

    @Test
    public void generatedStatusLaw4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = generatedStatusLaw(diff_);
        fight_.enableGlobalMove(BROUHAHA);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterStatut(SOMMEIL);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, LgInt.one());
        law_.addEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, SOMMEIL_FAILURE);
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, _data_);
        assertEq(1, res_.events().size());
        assertTrue(res_.events().containsObj(NULL_REF));
        assertTrue(res_.isValid());
    }

    @Test
    public void generatedStatusLaw5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = generatedStatusLaw(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, LgInt.one());
        law_.addEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, SOMMEIL_FAILURE);
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, _data_);
        assertEq(2, res_.events().size());
        assertTrue(res_.events().containsObj(NULL_REF));
        assertTrue(res_.events().containsObj(SOMMEIL));
        assertEq(new LgInt("1"),res_.rate(NULL_REF));
        assertEq(new LgInt("1"),res_.rate(SOMMEIL));
    }

    @Test
    public void generatedStatusLaw6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = generatedStatusLaw(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, LgInt.one());
        law_.addEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, NULL_REF);
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, _data_);
        assertEq(2, res_.events().size());
        assertTrue(res_.events().containsObj(NULL_REF));
        assertTrue(res_.events().containsObj(SOMMEIL));
        assertEq(new LgInt("1"),res_.rate(NULL_REF));
        assertEq(new LgInt("1"),res_.rate(SOMMEIL));
    }

    @Test
    public void generatedStatusLaw7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = generatedStatusLaw(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterStatut(SOMMEIL);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(SOMMEIL, LgInt.one());
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, SOMMEIL_FAILURE);
        MonteCarloString res_ = FightEffects.generatedStatusLaw(fight_, thrower_, target_, law_, fails_, _data_);
        assertEq(1, res_.events().size());
        assertTrue(res_.events().display(),res_.events().containsObj(NULL_REF));
        assertTrue(res_.isValid());
    }

    private static Fight setStatus(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void setStatus1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = setStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_CERIZ);
        FightEffects.setStatus(fight_, thrower_, target_, PARALYSIE, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE).intValue());
        StringList status_ = fight_.getSufferingTargetStatus();
        assertEq(1, status_.size());
        assertTrue(status_.containsObj(PARALYSIE));
        assertTrue(fighter_.isUsingItem());
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }

    @Test
    public void setStatus2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = setStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        FightEffects.setStatus(fight_, thrower_, target_, SOMMEIL, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
        StringList status_ = fight_.getSufferingTargetStatus();
        assertEq(1, status_.size());
        assertTrue(status_.containsObj(SOMMEIL));
        assertTrue(!fighter_.isUsingItem());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }

    @Test
    public void setStatus3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = setStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.backUpObject(NULL_REF);
        FightEffects.setStatus(fight_, thrower_, target_, SOMMEIL, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
        StringList status_ = fight_.getSufferingTargetStatus();
        assertEq(0, status_.size());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }

    @Test
    public void setStatus4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = setStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        FightEffects.setStatus(fight_, thrower_, target_, VAMPIGRAINE, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, thrower_)).intValue());
        StringList status_ = fight_.getSufferingTargetStatus();
        assertEq(1, status_.size());
        assertTrue(status_.containsObj(VAMPIGRAINE));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }

    @Test
    public void setStatus5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = setStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.affecterPseudoStatut(thrower_, VAMPIGRAINE);
        FightEffects.setStatus(fight_, thrower_, target_, VAMPIGRAINE, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, thrower_)).intValue());
        StringList status_ = fight_.getSufferingTargetStatus();
        assertEq(0, status_.size());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }

    @Test
    public void setStatus6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = setStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(NULL_REF);
        FightEffects.setStatus(fight_, thrower_, target_, PEUR, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(PEUR).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }

    @Test
    public void setStatus7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = setStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(METEO);
        FightEffects.setStatus(fight_, thrower_, target_, PEUR, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(PEUR).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }

    @Test
    public void setStatus8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = setStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(IMPASSIBLE);
        FightEffects.setStatus(fight_, thrower_, target_, PEUR, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(PEUR).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }

    private static Fight processStatusLaw(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void processStatusLaw1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processStatusLaw(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(SOMMEIL, LgInt.one());
        FightEffects.processStatusLaw(fight_, thrower_, target_, law_, new StringMap<String>(), _data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processStatusLaw2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processStatusLaw(diff_);
        fight_.enableGlobalMove(BROUHAHA);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, LgInt.one());
        law_.addEvent(SOMMEIL, LgInt.one());
        FightEffects.processStatusLaw(fight_, thrower_, target_, law_, new StringMap<String>(), _data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL).intValue());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processStatusLaw1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processStatusLaw(diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, LgInt.one());
        law_.addEvent(SOMMEIL, LgInt.one());
        FightEffects.processStatusLaw(fight_, thrower_, target_, law_, new StringMap<String>(), _data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL).intValue());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private static Fight affectStatusToThrower(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void affectStatusToThrower1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = affectStatusToThrower(diff_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        FightEffects.affectStatusToThrower(fight_, thrower_, SOMMEIL, target_, new StringMap<String>(), _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
    }

    @Test
    public void affectStatusToThrower2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = affectStatusToThrower(diff_);
        fight_.getSufferingTargetStatus().add(VAMPIGRAINE);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        FightEffects.affectStatusToThrower(fight_, thrower_, VAMPIGRAINE, target_, new StringMap<String>(), _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, target_)).intValue());
    }

    @Test
    public void affectStatusToThrower3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = affectStatusToThrower(diff_);
        fight_.enableGlobalMove(BROUHAHA);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        FightEffects.affectStatusToThrower(fight_, thrower_, SOMMEIL, target_, new StringMap<String>(), _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL).intValue());
    }

    @Test
    public void affectStatusToThrower4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = affectStatusToThrower(diff_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, NULL_REF);
        FightEffects.affectStatusToThrower(fight_, thrower_, SOMMEIL, target_, fails_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
    }

    @Test
    public void affectStatusToThrower5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = affectStatusToThrower(diff_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, F);
        FightEffects.affectStatusToThrower(fight_, thrower_, SOMMEIL, target_, fails_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
    }

    @Test
    public void affectStatusToThrower6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = affectStatusToThrower(diff_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, V);
        FightEffects.affectStatusToThrower(fight_, thrower_, SOMMEIL, target_, fails_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL).intValue());
    }

    private static Fight synchronizeStatus(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void synchronizeStatus1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL).intValue());
    }

    @Test
    public void synchronizeStatus2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
        fight_.getSufferingTargetStatus().add(AMOUR);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), _data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR,target_)).intValue());
    }

    @Test
    public void synchronizeStatus3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
    }

    @Test
    public void synchronizeStatus4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
        fight_.getSufferingTargetStatus().add(AMOUR);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), _data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR,target_)).intValue());
    }

    @Test
    public void synchronizeStatus5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
        fight_.getSufferingTargetStatus().add(VAMPIGRAINE);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), _data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE,target_)).intValue());
    }

    @Test
    public void synchronizeStatus6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL).intValue());
    }

    @Test
    public void synchronizeStatus7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
        fight_.enableGlobalMove(BROUHAHA);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), new StringMap<String>(), _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL).intValue());
    }

    @Test
    public void synchronizeStatus8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, NULL_REF);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), fails_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
    }

    @Test
    public void synchronizeStatus9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
        fight_.getSufferingTargetStatus().add(SOMMEIL);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        StringMap<String> fails_ = new StringMap<String>();
        fails_.put(SOMMEIL, SOMMEIL_FAILURE);
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), fails_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
    }

    @Test
    public void synchronizeStatus10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
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
        FightEffects.synchronizeStatus(fight_, thrower_, target_, new StringMap<String>(), fails_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL).intValue());
    }

    @Test
    public void synchronizeStatus11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
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
        FightEffects.synchronizeStatus(fight_, thrower_, target_, fails_, new StringMap<String>(), _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)).intValue());
    }

    @Test
    public void synchronizeStatus12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
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
        FightEffects.synchronizeStatus(fight_, thrower_, target_, fails_, new StringMap<String>(), _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)).intValue());
    }

    @Test
    public void synchronizeStatus13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
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
        FightEffects.synchronizeStatus(fight_, thrower_, target_, fails_, new StringMap<String>(), _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)).intValue());
    }

    @Test
    public void synchronizeStatus14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
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
        FightEffects.synchronizeStatus(fight_, thrower_, target_, fails_, new StringMap<String>(), _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)).intValue());
    }

    @Test
    public void synchronizeStatus15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = synchronizeStatus(diff_);
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
        FightEffects.synchronizeStatus(fight_, thrower_, target_, fails_, new StringMap<String>(), _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)).intValue());
    }

    private static Fight effectSwitchPosition(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    private static Fight effectSwitchPosition2(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectSwitchPosition1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectSwitchPosition(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Effect effect_ = _data_.getMove(INTERVERSION).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectSwitchPosition(fight_, thrower_, target_, _data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(3, fight_.getFirstPositPlayerFighters().size());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(2, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(1, fight_.getEffects().size());
        AnimationChangedPlace animSwitch_ = (AnimationChangedPlace) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animSwitch_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ONE, animSwitch_.getToFighter());
        assertTrue(!animSwitch_.isKoFromFighter());
        assertTrue(!animSwitch_.isKoToFighter());
    }

    @Test
    public void effectSwitchPosition2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectSwitchPosition2(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_THREE;
        Effect effect_ = _data_.getMove(INTERVERSION).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectSwitchPosition(fight_, thrower_, target_, _data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
        assertEq(1, fight_.getEffects().size());
        AnimationChangedPlace animSwitch_ = (AnimationChangedPlace) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animSwitch_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ONE, animSwitch_.getToFighter());
        assertTrue(!animSwitch_.isKoFromFighter());
        assertTrue(!animSwitch_.isKoToFighter());
    }

    @Test
    public void effectSwitchPosition3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectSwitchPosition2(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Effect effect_ = _data_.getMove(INTERVERSION).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectSwitchPosition(fight_, thrower_, target_, _data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
        assertEq(1, fight_.getEffects().size());
        AnimationChangedPlace animSwitch_ = (AnimationChangedPlace) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ONE, animSwitch_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animSwitch_.getToFighter());
        assertTrue(!animSwitch_.isKoFromFighter());
        assertTrue(!animSwitch_.isKoToFighter());
    }

    @Test
    public void effectSwitchPosition4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectSwitchPosition2(diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ONE;
        Effect effect_ = _data_.getMove(INTERVERSION).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectSwitchPosition(fight_, thrower_, target_, _data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(3, fight_.getFirstPositFoeFighters().size());
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 0).intValue());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 2).intValue());
        assertEq(1, fight_.getEffects().size());
        AnimationChangedPlace animSwitch_ = (AnimationChangedPlace) fight_.getEffects().last();
        assertEq(POKEMON_FOE_TARGET_ZERO, animSwitch_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ONE, animSwitch_.getToFighter());
        assertTrue(!animSwitch_.isKoFromFighter());
        assertTrue(!animSwitch_.isKoToFighter());
    }

    private static Fight effectStatistic(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectStatistic1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatistic(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        MoveData move_ = _data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        fight_.addEffect(thrower_, target_, eff_);
        EnumList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        assertEq(1, statistics_.size());
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, statistics_, _data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
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
    public void effectStatistic2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatistic(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        MoveData move_ = _data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        fight_.addEffect(thrower_, target_, eff_);
        EnumList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, statistics_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fighter_.isUsingItem());
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatistic(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fighter_.variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = _data_.getMove(DEGOMMAGE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        fight_.addEffect(thrower_, target_, eff_);
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, _data_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatistic(diff_);
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
        MoveData move_ = _data_.getMove(BOOST);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        fight_.addEffect(thrower_, target_, eff_);
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(3, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(3, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatistic(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fighter_.variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = _data_.getMove(BUEE_NOIRE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        fight_.addEffect(thrower_, target_, eff_);
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatistic(diff_);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fighter_.variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = _data_.getMove(PERMUCOEUR);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        fight_.addEffect(thrower_, target_, eff_);
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(3, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatistic(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.variationBoostStatistique(Statistic.ACCURACY, (byte) -6);
        MoveData move_ = _data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        fight_.addEffect(thrower_, target_, eff_);
        EnumList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        assertEq(0, statistics_.size());
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, statistics_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(-6, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectStatistic anim_ = (AnimationEffectStatistic) fight_.getEffects().first();
        assertEq(0, anim_.getInfos().size());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectStatistic8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatistic(diff_);
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
        MoveData move_ = _data_.getMove(ACUPRESSION);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        fight_.addEffect(thrower_, target_, eff_);
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        assertEq(1, stats_.size());
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatistic(diff_);
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
        MoveData move_ = _data_.getMove(TROU_BIS);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        fight_.addEffect(thrower_, target_, eff_);
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        assertEq(1, stats_.size());
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(4, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatistic(diff_);
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
        MoveData move_ = _data_.getMove(ACUPRESSION);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        assertEq(2, stats_.size());
        FightEffects.effectStatistic(fight_, thrower_, target_, eff_, stats_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fight_.getAcceptableChoices());
    }

    private static Fight effectStatisticRandom(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectStatisticRandom1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatisticRandom(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        MoveData move_ = _data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        EnumList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        assertEq(1, statistics_.size());
        FightEffects.effectStatisticRandom(fight_, thrower_, target_, eff_, statistics_, DataBase.defRateProduct(), false, _data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
    }

    @Test
    public void effectStatisticRandom2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatisticRandom(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        MoveData move_ = _data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        EnumList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        FightEffects.effectStatisticRandom(fight_, thrower_, target_, eff_, statistics_, Rate.zero(), false, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
    }

    @Test
    public void effectStatisticRandom3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatisticRandom(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        MoveData move_ = _data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        EnumList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        assertEq(1, statistics_.size());
        FightEffects.effectStatisticRandom(fight_, thrower_, target_, eff_, statistics_, DataBase.defRateProduct(), true, _data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
    }

    @Test
    public void effectStatisticRandom4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatisticRandom(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        MoveData move_ = _data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        EnumList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(fight_,thrower_, target_, eff_, _data_);
        FightEffects.effectStatisticRandom(fight_, thrower_, target_, eff_, statistics_, Rate.zero(), true, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
    }

    private static Fight effectStatus(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectStatus1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterStatut(BRULURE);
        MoveData move_ = _data_.getMove(ECHANGE_PSY);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().last();
        fight_.addEffect(thrower_, target_, eff_);
        FightEffects.effectStatus(fight_, thrower_, target_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(BRULURE).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        MoveData move_ = _data_.getMove(REPOS);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, thrower_, eff_);
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL_REPOS).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition sub_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(sub_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 1);
        MoveData move_ = _data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, thrower_, eff_);
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        fighter_ = fight_.getFighter(sub_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(3, fight_.getEffects().size());
        AnimationEffectStatus animStatus_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animStatus_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animStatus_.getToFighter());
        assertEq(NULL_REF, animStatus_.getStatus());
        assertTrue(!animStatus_.isKoFromFighter());
        assertTrue(!animStatus_.isKoToFighter());
        AnimationKo animKo_ = (AnimationKo) fight_.getEffects().get(1);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        MoveData move_ = _data_.getMove(BERCEUSE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, target_, eff_);
        FightEffects.effectStatus(fight_, thrower_, target_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition sub_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(sub_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 1);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICS_TOXIK);
        MoveData move_ = _data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, thrower_, eff_);
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, _data_);
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
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(3, fight_.getEffects().size());
        AnimationEffectStatus animStatus_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animStatus_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animStatus_.getToFighter());
        assertEq(NULL_REF, animStatus_.getStatus());
        assertTrue(!animStatus_.isKoFromFighter());
        assertTrue(!animStatus_.isKoToFighter());
        AnimationKo animKo_ = (AnimationKo) fight_.getEffects().get(1);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition sub_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(sub_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(thrower_);
        MoveData move_ = _data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, thrower_, eff_);
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(0, fighter_.getGroundPlace());
        fighter_ = fight_.getFighter(sub_);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fight_.getFighter(target_).setCurrentAbility(SYNCHRO);
        MoveData move_ = _data_.getMove(BERCEUSE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, target_, eff_);
        FightEffects.effectStatus(fight_, thrower_, target_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fight_.getFighter(target_).setCurrentAbility(NULL_REF);
        MoveData move_ = _data_.getMove(BERCEUSE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, target_, eff_);
        FightEffects.effectStatus(fight_, thrower_, target_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatus(diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition sub_ = POKEMON_FOE_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(sub_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 2);
        MoveData move_ = _data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, thrower_, eff_);
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        fighter_ = fight_.getFighter(sub_);
        assertEq(new Rate("106/5"), fighter_.getRemainingHp());
        assertEq(3, fight_.getFirstPositFoeFighters().size());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0).intValue());
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1).intValue());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 2).intValue());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(3, fight_.getEffects().size());
        AnimationEffectStatus animStatus_ = (AnimationEffectStatus) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, animStatus_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animStatus_.getToFighter());
        assertEq(NULL_REF, animStatus_.getStatus());
        assertTrue(!animStatus_.isKoFromFighter());
        assertTrue(!animStatus_.isKoToFighter());
        AnimationKo animKo_ = (AnimationKo) fight_.getEffects().get(1);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatus(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterStatut(BRULURE);
        MoveData move_ = _data_.getMove(GLAS_DE_SOIN);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        fight_.addEffect(thrower_, target_, eff_);
        FightEffects.effectStatus(fight_, thrower_, target_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatus(diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition sub_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(sub_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 1);
        MoveData move_ = _data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        fighter_ = fight_.getFighter(sub_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectStatus2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectStatus(diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition sub_ = POKEMON_FOE_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(sub_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setSubstitute((byte) 2);
        MoveData move_ = _data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        FightEffects.effectStatus(fight_, thrower_, thrower_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        fighter_ = fight_.getFighter(sub_);
        assertEq(new Rate("106/5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    private static Fight effectTeam(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeam(diff_);
        fight_.getFoeTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFoeTeam().getNbUsesMoves().put(CASSE, 1);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, AMOUR);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, teamPosition_)).enable();
        EffectTeam eff_ = (EffectTeam) _data_.getMove(TOUR_RAPIDE).getEffects().last();
        FightEffects.effectTeam(fight_, teamPosition_, eff_, TOUR_RAPIDE, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        ActivityOfMove activity_;
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(LgInt.zero(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)).intValue());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, POKEMON_FOE_FIGHTER_ZERO)).intValue());
        assertEq(0, fight_.getFoeTeam().getNbUsesMoves().getVal(CASSE).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeam(diff_);
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
        EffectTeam eff_ = (EffectTeam) _data_.getMove(ANTI_BRUME).getEffects().last();
        FightEffects.effectTeam(fight_, teamPosition_, eff_, ANTI_BRUME, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        ActivityOfMove activity_;
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertEq(LgInt.zero(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)).intValue());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, POKEMON_FOE_FIGHTER_ZERO)).intValue());
        assertEq(1, fight_.getFoeTeam().getNbUsesMoves().getVal(CASSE).intValue());
        activity_ = fight_.getUserTeam().getEnabledMoves().getVal(ANTI_BRUME);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,teamPosition_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
    }

    @Test
    public void effectTeam3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeam(diff_);
        fight_.getFoeTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFoeTeam().getNbUsesMoves().put(CASSE, 1);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.affecterStatut(GEL);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, CAUCHEMAR);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        EffectTeam eff_ = (EffectTeam) _data_.getMove(RUNE_PROTECT).getEffects().last();
        FightEffects.effectTeam(fight_, teamPosition_, eff_, RUNE_PROTECT, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        ActivityOfMove activity_;
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(0, fighter_.getStatusNbRound(GEL).intValue());
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)).intValue());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(CAUCHEMAR, POKEMON_FOE_FIGHTER_ZERO)).intValue());
        assertEq(1, fight_.getFoeTeam().getNbUsesMoves().getVal(CASSE).intValue());
        activity_ = fight_.getUserTeam().getEnabledMoves().getVal(RUNE_PROTECT);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
    }

    @Test
    public void effectTeam4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeam(diff_);
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
        EffectTeam eff_ = (EffectTeam) _data_.getMove(BRUME).getEffects().last();
        FightEffects.effectTeam(fight_, teamPosition_, eff_, BRUME, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        ActivityOfMove activity_;
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(1, fighter_.getStatusNbRound(GEL).intValue());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)).intValue());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(CAUCHEMAR, POKEMON_FOE_FIGHTER_ZERO)).intValue());
        assertEq(1, fight_.getFoeTeam().getNbUsesMoves().getVal(CASSE).intValue());
        activity_ = fight_.getUserTeam().getEnabledMoves().getVal(BRUME);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
    }

    @Test
    public void effectTeam5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeam(diff_);
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
        EffectTeam eff_ = (EffectTeam) _data_.getMove(ANTI_BRUME).getEffects().last();
        FightEffects.effectTeam(fight_, teamPosition_, eff_, ANTI_BRUME, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        ActivityOfMove activity_;
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertEq(LgInt.zero(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(1, fighter_.getStatusNbRound(GEL).intValue());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)).intValue());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(CAUCHEMAR, POKEMON_FOE_FIGHTER_ZERO)).intValue());
        assertEq(1, fight_.getFoeTeam().getNbUsesMoves().getVal(CASSE).intValue());
        activity_ = fight_.getUserTeam().getEnabledMoves().getVal(ANTI_BRUME);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
    }

    private static Fight effectEndRound(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectEndRound1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectEndRound(diff_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EffectEndRoundPositionRelation eff_;
        eff_ = (EffectEndRoundPositionRelation) _data_.getMove(VOEU).getEffects().last();
        FightEffects.effectEndRound(fight_, teamPosition_, teamPosition_, VOEU, eff_, _data_);
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        StacksOfUses soinApres_=fight_.getUserTeam().getHealAfter().getVal(VOEU).getVal(fighter_.getGroundPlace());
        assertTrue(soinApres_.isFirstStacked());
        assertTrue(!soinApres_.isLastStacked());
        assertEq(0, soinApres_.getNbRounds());
    }

    @Test
    public void effectEndRound2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectEndRound(diff_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EffectEndRoundPositionRelation eff_;
        eff_ = (EffectEndRoundPositionRelation) _data_.getMove(VOEU).getEffects().last();
        FightEffects.effectEndRound(fight_, teamPosition_, teamPosition_, VOEU, eff_, _data_);
        FightEffects.effectEndRound(fight_, teamPosition_, teamPosition_, VOEU, eff_, _data_);
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        StacksOfUses soinApres_=fight_.getUserTeam().getHealAfter().getVal(VOEU).getVal(fighter_.getGroundPlace());
        assertTrue(soinApres_.isFirstStacked());
        assertTrue(soinApres_.isLastStacked());
        assertEq(0, soinApres_.getNbRounds());
    }

    @Test
    public void effectEndRound3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectEndRound(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) _data_.getMove(SIPHON).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, SIPHON, eff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
    }

    @Test
    public void effectEndRound4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectEndRound(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) _data_.getMove(SIPHON).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, SIPHON, eff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
    }

    @Test
    public void effectEndRound5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectEndRound(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) _data_.getMove(SIPHON).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, SIPHON, eff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
    }

    @Test
    public void effectEndRound6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectEndRound(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.creerClone(new Rate("1/2"));
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) _data_.getMove(SIPHON).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, SIPHON, eff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
    }

    @Test
    public void effectEndRound7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectEndRound(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        EffectEndRoundPositionTargetRelation eff_;
        eff_ = (EffectEndRoundPositionTargetRelation) _data_.getMove(PRESCIENCE).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, PRESCIENCE, eff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fight_.getUserTeam().getHealAfter().contains(PRESCIENCE));
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
    }

    @Test
    public void effectEndRound8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectEndRound(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        EffectEndRound eff_;
        eff_ = (EffectEndRound) _data_.getMove(ANNEAU_HYDRO).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, ANNEAU_HYDRO, eff_, _data_);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectEndRound(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.creerClone(new Rate("1/2"));
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) _data_.getMove(TIPHON).getEffects().last();
        FightEffects.effectEndRound(fight_, thrower_, target_, TIPHON, eff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(TIPHON,target_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
    }

    private static Fight effectBatonPass(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectBatonPass1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_);
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
        fighter_.activerAttaqueImmu(TROU, _data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, true);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), true);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        Effect effect_ = _data_.getMove(RELAIS).getEffet(0);
        fight_.addEffect(thrower_, thrower_, effect_);
        FightEffects.effectBatonPass(fight_, thrower_, diff_, _data_);
        Team userTeam_ = fight_.getUserTeam();
        NumberMap<Byte,Numbers<Byte>> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(2, map_.getVal((byte) 0).size());
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 1));
        assertEq(2, map_.getVal((byte) 1).size());
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 1));
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
        assertEq(0, partner_.getGroundPlace());
        assertEq(0, partner_.getGroundPlaceSubst());
        assertEq(1, partner_.getNbUsesMoves().getVal(BOUL_ARMURE).intValue());
        assertEq(Rate.one(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("2"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(JACKPOT, partner_.getLastSufferedMove());
        assertEq(new Rate("1873/100"), partner_.getRemainingHp());
        assertEq(new Rate("1873/200"), partner_.getClone());
        assertEq(1, partner_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_);
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
        fighter_.activerAttaqueImmu(TROU, _data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, true);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), true);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        assertEq(new Rate("561/25"), partner_.getRemainingHp());
        Effect effect_ = _data_.getMove(RELAIS).getEffet(0);
        fight_.addEffect(thrower_, thrower_, effect_);
        FightEffects.effectBatonPass(fight_, thrower_, diff_, _data_);
        Team userTeam_ = fight_.getUserTeam();
        NumberMap<Byte,Numbers<Byte>> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(3, map_.size());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
        assertEq(1, partner_.getNbUsesMoves().getVal(BOUL_ARMURE).intValue());
        assertEq(Rate.one(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("2"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(JACKPOT, partner_.getLastSufferedMove());
        assertEq(new Rate("561/25"), partner_.getRemainingHp());
        assertEq(new Rate("1933/200"), partner_.getClone());
        assertEq(1, partner_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_);
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
        fighter_.activerAttaqueImmu(TROU, _data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, true);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), true);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        assertEq(new Rate("1873/100"), partner_.getRemainingHp());
        Effect effect_ = _data_.getMove(RELAIS).getEffet(0);
        fight_.addEffect(thrower_, thrower_, effect_);
        FightEffects.effectBatonPass(fight_, thrower_, diff_, _data_);
        Team userTeam_ = fight_.getUserTeam();
        NumberMap<Byte,Numbers<Byte>> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(3, map_.size());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
        assertEq(0, partner_.getNbUsesMoves().getVal(BOUL_ARMURE).intValue());
        assertEq(Rate.zero(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("1"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("1"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("0"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(NULL_REF, partner_.getLastSufferedMove());
        assertEq(new Rate("1873/100"), partner_.getRemainingHp());
        assertEq(new Rate("0"), partner_.getClone());
        assertEq(0, partner_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectBatonPass4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_);
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
        fighter_.activerAttaqueImmu(TROU, _data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, true);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), true);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        assertEq(new Rate("561/25"), partner_.getRemainingHp());
        Effect effect_ = _data_.getMove(RELAIS).getEffet(0);
        fight_.addEffect(thrower_, thrower_, effect_);
        FightEffects.effectBatonPass(fight_, thrower_, diff_, _data_);
        Team userTeam_ = fight_.getUserTeam();
        NumberMap<Byte,Numbers<Byte>> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(3, map_.size());
        assertEq(5, fight_.getFirstPositPlayerFighters().size());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
        assertEq(0, partner_.getNbUsesMoves().getVal(BOUL_ARMURE).intValue());
        assertEq(Rate.zero(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("1"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("1"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("0"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(NULL_REF, partner_.getLastSufferedMove());
        assertEq(new Rate("561/25"), partner_.getRemainingHp());
        assertEq(new Rate("0"), partner_.getClone());
        assertEq(0, partner_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectBatonPass5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_);
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
        fighter_.activerAttaqueImmu(TROU, _data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, true);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), true);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        Effect effect_ = _data_.getMove(RELAIS).getEffet(0);
        fight_.addEffect(thrower_, thrower_, effect_);
        FightEffects.effectBatonPass(fight_, thrower_, diff_, _data_);
        Team userTeam_ = fight_.getUserTeam();
        NumberMap<Byte,Numbers<Byte>> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(3, map_.getVal((byte) 0).size());
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 1));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 2));
        assertEq(0, map_.getVal((byte) 1).size());
        assertEq(3, fight_.getFirstPositFoeFighters().size());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1).intValue());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 2).intValue());
        assertEq(0, partner_.getGroundPlace());
        assertEq(0, partner_.getGroundPlaceSubst());
        assertEq(1, partner_.getNbUsesMoves().getVal(BOUL_ARMURE).intValue());
        assertEq(Rate.one(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("2"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(JACKPOT, partner_.getLastSufferedMove());
        assertEq(new Rate("106/5"), partner_.getRemainingHp());
        assertEq(new Rate("46/5"), partner_.getClone());
        assertEq(1, partner_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectBatonPass(diff_);
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
        fighter_.activerAttaqueImmu(TROU, _data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, true);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), true);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        Effect effect_ = _data_.getMove(RELAIS).getEffet(0);
        fight_.addEffect(thrower_, thrower_, effect_);
        FightEffects.effectBatonPass(fight_, thrower_, diff_, _data_);
        Team userTeam_ = fight_.getUserTeam();
        NumberMap<Byte,Numbers<Byte>> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(2, map_.getVal((byte) 0).size());
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 1));
        assertEq(0, map_.getVal((byte) 1).size());
        assertEq(3, fight_.getFirstPositFoeFighters().size());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 0).intValue());
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 2).intValue());
        assertEq(Fighter.BACK, partner_.getGroundPlace());
        assertEq(Fighter.BACK, partner_.getGroundPlaceSubst());
        assertEq(0, partner_.getNbUsesMoves().getVal(BOUL_ARMURE).intValue());
        assertEq(Rate.zero(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("1"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("1"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("0"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(NULL_REF, partner_.getLastSufferedMove());
        assertEq(new Rate("106/5"), partner_.getRemainingHp());
        assertEq(new Rate("0"), partner_.getClone());
        assertEq(0, partner_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fight_.getEffects().size());
    }

    private static Fight effectGlobal(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectGlobal1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectGlobal(diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(GEL);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) _data_.getMove(ZENITH).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, ZENITH, _data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(ZENITH);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(!fight_.getStillEnabledMoves().getVal(ZENITH));
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(FEU));
        assertEq(0, fighter_.getStatusNbRound(GEL).intValue());
    }

    @Test
    public void effectGlobal2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectGlobal(diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(GEL);
        fight_.enableGlobalMove(ZENITH);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) _data_.getMove(ZENITH).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, ZENITH, _data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(ZENITH);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(!fight_.getStillEnabledMoves().getVal(ZENITH));
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(FEU));
        assertEq(0, fighter_.getStatusNbRound(GEL).intValue());
    }

    @Test
    public void effectGlobal3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectGlobal(diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.getProtectedAgainstMoveTypes().add(SOL);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) _data_.getMove(GRAVITE).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, GRAVITE, _data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(GRAVITE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(ELECTRIQUE));
        assertEq(0, fighter_.getProtectedAgainstMoveTypes().size());
    }

    @Test
    public void effectGlobal4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectGlobal(diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fight_.enableGlobalMove(DISTORSION);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) _data_.getMove(DISTORSION).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, DISTORSION, _data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(DISTORSION);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(ELECTRIQUE));
    }

    @Test
    public void effectGlobal5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectGlobal(diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(GEL);
        fight_.enableGlobalMove(TEMPETESABLE);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) _data_.getMove(ZENITH).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, ZENITH, _data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(TEMPETESABLE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(!fight_.getStillEnabledMoves().getVal(ZENITH));
        activity_ = fight_.getEnabledMoves().getVal(ZENITH);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(!fight_.getStillEnabledMoves().getVal(ZENITH));
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(FEU));
        assertEq(0, fighter_.getStatusNbRound(GEL).intValue());
    }

    @Test
    public void effectGlobal6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectGlobal(diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(GEL);
        fight_.enableGlobalMove(DANSE_PLUIE);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) _data_.getMove(ZENITH).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, ZENITH, _data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(DANSE_PLUIE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(!fight_.getStillEnabledMoves().getVal(DANSE_PLUIE));
        activity_ = fight_.getEnabledMoves().getVal(ZENITH);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(!fight_.getStillEnabledMoves().getVal(ZENITH));
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(2, types_.size());
        assertTrue(types_.containsObj(FEU));
        assertTrue(types_.containsObj(EAU));
        assertEq(0, fighter_.getStatusNbRound(GEL).intValue());
    }

    @Test
    public void effectGlobal7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectGlobal(diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) _data_.getMove(DISTORSION).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, DISTORSION, _data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(DISTORSION);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(ELECTRIQUE));
    }

    @Test
    public void effectGlobal8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectGlobal(diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.getStatisBoost().put(Statistic.ATTACK, (byte) 1);
        fighter_.getStatisBoost().put(Statistic.DEFENSE, (byte) -1);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) _data_.getMove(ZONE_ETRANGE).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, ZONE_ETRANGE, _data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(ZONE_ETRANGE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(ELECTRIQUE));
    }

    @Test
    public void effectGlobal9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectGlobal(diff_);
        fight_.enableGlobalMove(GRAVITE);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) _data_.getMove(REQUIEM).getEffects().first();
        FightEffects.effectGlobal(fight_, eff_, REQUIEM, _data_);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectGlobal(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, AMOUR);
        EffectGlobal eff_;
        eff_ = (EffectGlobal) _data_.getMove(BROUHAHA).getEffects().last();
        FightEffects.effectGlobal(fight_, eff_, BROUHAHA, _data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatusRelatNbRound(new MoveTeamPosition(AMOUR, POKEMON_FOE_FIGHTER_ZERO)).intValue());
    }

    @Test
    public void effectMultiplyUsedMovePower1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EffectMultUsedMovePower eff_;
        eff_ = (EffectMultUsedMovePower) _data_.getMove(CHARGEUR).getEffects().last();
        FightEffects.effectMultiplyUsedMovePower(fight_, thrower_, eff_, CHARGEUR, _data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("2"), fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
    }

    @Test
    public void effectMultiplyUndergoneMovePower1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EffectMultSufferedMovePower eff_;
        eff_ = (EffectMultSufferedMovePower) _data_.getMove(TOURNIQUET).getEffects().first();
        FightEffects.effectMultiplyUndergoneMovePower(fight_, thrower_, eff_, TOURNIQUET, _data_);
        Fighter fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1/2"), fighter_.getDamageRateSufferedByType().getVal(FEU));
    }

    private static Fight effectDamageRate(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectDamageRate1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) _data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectDamageRate2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
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
        eff_ = (EffectDamageRate) _data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
        assertTrue(fight_.getAcceptableChoices());
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectDamageRate3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        fight_.getDamageByCurrentUser().put(target_, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) _data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
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
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getFromFighter());
        assertTrue(animRecoil_.isKoFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getToFighter());
        assertTrue(animRecoil_.isKoToFighter());
    }

    @Test
    public void effectDamageRate4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setAbility(SUINTEMENT);
        fighter_.setCurrentAbility(SUINTEMENT);
        FightKo.setKoMoveTeams(fight_, target_, diff_, _data_);
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) _data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertTrue(anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectDamageRate5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_.setRemainedHp(Rate.one());
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) _data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectDamageRate6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(BOUE_NOIRE);
        fighter_.setRemainedHp(Rate.one());
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) _data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectDamageRate7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(GROSSERACINE);
        fighter_.setRemainedHp(Rate.one());
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) _data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("9"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectDamageRate8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(new Rate("5"));
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        fight_.getDamageByCurrentUser().put(POKEMON_FOE_FIGHTER_ZERO, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) _data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
//        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
//        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
//        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
//        assertTrue(!anim_.isKoFromFighter());
//        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectDamageRate9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
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
        eff_ = (EffectDamageRate) _data_.getMove(SACRIFICE).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("3"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil animRecoil_ = (AnimationRecoil) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
    }

    @Test
    public void effectDamageRate10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
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
        eff_ = (EffectDamageRate) _data_.getMove(SACRIFICE).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("3"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil animRecoil_ = (AnimationRecoil) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
    }

    @Test
    public void effectDamageRate11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
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
        eff_ = (EffectDamageRate) _data_.getMove(SACRIFICE).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertEq(new Rate("5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectDamageRate12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
        TeamPosition teamPosition_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        fight_.getDamageByCurrentUser().put(target_, new Rate("8"));
        EffectDamageRate eff_;
        eff_ = (EffectDamageRate) _data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
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
        assertEq(POKEMON_FOE_TARGET_ZERO, animRecoil_.getFromFighter());
        assertTrue(animRecoil_.isKoFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animRecoil_.getToFighter());
        assertTrue(animRecoil_.isKoToFighter());
    }

    @Test
    public void effectDamageRate13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
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
        eff_ = (EffectDamageRate) _data_.getMove(SACRIFICE).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil animRecoil_ = (AnimationRecoil) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectDamageRate1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamageRate(diff_);
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
        eff_ = (EffectDamageRate) _data_.getMove(VAMPIPOING).getEffects().last();
        FightEffects.effectDamageRate(fight_, teamPosition_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private static Fight effectFullHpRate(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectFullHpRate1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectFullHpRate(diff_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) _data_.getMove(SOIN).getEffects().last();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, _data_);
        assertEq(new Rate("2073/200"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animHeal_ = (AnimationHealing) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animHeal_.getHealed());
    }

    @Test
    public void effectFullHpRate2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectFullHpRate(diff_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) _data_.getMove(MALEDICTION_2).getEffects().first();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, _data_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo animRecoil_ = (AnimationKo) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectFullHpRate3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectFullHpRate(diff_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("2073/200"));
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) _data_.getMove(MALEDICTION_2).getEffects().first();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, _data_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil animRecoil_ = (AnimationRecoil) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
    }

    @Test
    public void effectFullHpRate4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectFullHpRate(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) _data_.getMove(MALEDICTION_2).getEffects().first();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, _data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo animRecoil_ = (AnimationKo) fight_.getEffects().last();
        assertEq(POKEMON_FOE_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectFullHpRate5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectFullHpRate(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) _data_.getMove(REBONDIFEU).getEffects().last();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(new Rate("371/20"), fighter_.getRemainingHp());
        //106/5 * 1/8 = 53/20 106/5 * 7/8 = 53/5 * 7/4 = 371/20
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil animRecoil_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ONE, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
    }

    @Test
    public void effectFullHpRate6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectFullHpRate(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.setRemainedHp(Rate.one());
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) _data_.getMove(REBONDIFEU).getEffects().last();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, _data_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo animRecoil_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ONE, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectFullHpRate7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectFullHpRate(diff_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        fighter_.setRemainedHp(new Rate("1/2"));
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) _data_.getMove(REBONDIFEU).getEffects().last();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        assertTrue(fighter_.getRemainingHp().toNumberString(),fighter_.estKo());
        //106/5 * 1/8 = 53/20 106/5 * 7/8 = 53/5 * 7/4 = 371/20
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo animRecoil_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ONE, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectFullHpRate1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectFullHpRate(diff_);
        fight_.setSimulation(true);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) _data_.getMove(MALEDICTION_2).getEffects().first();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, _data_);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectFullHpRate2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectFullHpRate(diff_);
        fight_.setSimulation(true);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        fighter_.setRemainedHp(new Rate("1/2"));
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) _data_.getMove(REBONDIFEU).getEffects().last();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, _data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        assertTrue(fighter_.getRemainingHp().toNumberString(),fighter_.estKo());
        //106/5 * 1/8 = 53/20 106/5 * 7/8 = 53/5 * 7/4 = 371/20
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectFullHpRate3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectFullHpRate(diff_);
        fight_.setSimulation(true);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.setRemainedHp(Rate.one());
        EffectFullHpRate eff_;
        eff_ = (EffectFullHpRate) _data_.getMove(REBONDIFEU).getEffects().last();
        FightEffects.effectFullHpRate(fight_, target_, eff_, diff_, _data_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    private static Fight effectLeftHpRate(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectLeftHpRate1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectLeftHpRate(diff_);
        EffectRemainedHpRate eff_;
        eff_ = (EffectRemainedHpRate) _data_.getMove(ABIME).getEffects().last();
        FightEffects.effectLeftHpRate(fight_, POKEMON_FOE_FIGHTER_ZERO, eff_, diff_, _data_);
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo animRecoil_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectLeftHpRate2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectLeftHpRate(diff_);
        EffectRemainedHpRate eff_;
        eff_ = (EffectRemainedHpRate) _data_.getMove(ABIME).getEffects().last();
        FightEffects.effectLeftHpRate(fight_, POKEMON_PLAYER_FIGHTER_ZERO, eff_, diff_, _data_);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo animRecoil_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(animRecoil_.isKoUser());
    }

    @Test
    public void effectLeftHpRate3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectLeftHpRate(diff_);
        EffectRemainedHpRate eff_;
        eff_ = (EffectRemainedHpRate) _data_.getMove(CASSE).getEffects().first();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        FightEffects.effectLeftHpRate(fight_, POKEMON_PLAYER_FIGHTER_ZERO, eff_, diff_, _data_);
        assertEq(new Rate("5619/400"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing animRecoil_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getHealed());
    }

    @Test
    public void effectLeftHpRate4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectLeftHpRate(diff_);
        EffectRemainedHpRate eff_;
        eff_ = (EffectRemainedHpRate) _data_.getMove(CASSE).getEffects().last();
        FightEffects.effectLeftHpRate(fight_, POKEMON_PLAYER_FIGHTER_ZERO, eff_, diff_, _data_);
        assertEq(new Rate("1873/200"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil animRecoil_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
    }

    @Test
    public void effectLeftHpRate1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectLeftHpRate(diff_);
        fight_.setSimulation(true);
        EffectRemainedHpRate eff_;
        eff_ = (EffectRemainedHpRate) _data_.getMove(ABIME).getEffects().last();
        FightEffects.effectLeftHpRate(fight_, POKEMON_PLAYER_FIGHTER_ZERO, eff_, diff_, _data_);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private static Fight effectVarPp(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectVarPp1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectVarPp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        EffectVarPP eff_;
        eff_ = (EffectVarPP) _data_.getMove(DEPIT).getEffects().first();
        FightEffects.effectVarPp(fight_, target_, eff_, diff_, _data_);
        assertEq(16, fighter_.powerPointsMove(JACKPOT));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void effectVarPp2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectVarPp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_.usePowerPointsByMove(diff_, JACKPOT, (short) 17);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        EffectVarPP eff_;
        eff_ = (EffectVarPP) _data_.getMove(DEPIT).getEffects().first();
        FightEffects.effectVarPp(fight_, target_, eff_, diff_, _data_);
        assertEq(10, fighter_.powerPointsMove(JACKPOT));
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void effectVarPp3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectVarPp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        EffectVarPP eff_;
        eff_ = (EffectVarPP) _data_.getMove(DEPIT).getEffects().first();
        FightEffects.effectVarPp(fight_, target_, eff_, diff_, _data_);
        assertEq(16, fighter_.powerPointsMove(JACKPOT));
        assertTrue(!fighter_.isUsingItem());
    }

    private static Fight processEffectTarget(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void processEffectTarget1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(PROVOC, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, PROVOC, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(OEIL_MIRACLE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, OEIL_MIRACLE, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(ECHANGE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, ECHANGE, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(METEO, fighter_.getCurrentAbility());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BOUE_NOIRE);
        fighter_.setFirstChosenMoveTarget(PASSE_PASSE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, PASSE_PASSE, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(BOUE_NOIRE, fighter_.getItem());
        fighter_ = fight_.getFighter(thrower_);
        assertEq(PLAQUE_DRACO, fighter_.getItem());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(CAMOUFLAGE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, CAMOUFLAGE, CustList.FIRST_INDEX, thrower_, thrower_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(SOL));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(CLONAGE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, CLONAGE, CustList.FIRST_INDEX, thrower_, thrower_, diff_, _data_);
        assertEq(new Rate("5619/400"), fighter_.getRemainingHp());
        assertEq(new Rate("1873/400"), fighter_.getClone());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.setFirstChosenMoveTarget(BALANCE, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("3"));
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, BALANCE, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("2"), fighter_.getRemainingHp());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("2"), fighter_.getRemainingHp());
    }

    @Test
    public void processEffectTarget8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(COPIE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setLastUsedMove(JACKPOT);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, COPIE, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        CopiedMove newMoveInfo_ = fighter_.getCopiedMoves().getVal(COPIE);
        assertEq(JACKPOT, newMoveInfo_.getMove());
        assertEq(5, newMoveInfo_.getPp());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(MORPHING);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, MORPHING, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(TARTARD, fighter_.getCurrentName());
        assertEq(ARTIKODIN, fighter_.getName());
        assertTrue(fighter_.isChanged());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_.setFirstChosenMove(TONNERRE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, TONNERRE, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertTrue(fighter_.isSuccessfulMove());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("27788/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("21432/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("21432/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
    }

    @Test
    public void processEffectTarget11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(INTERVERSION, POKEMON_PLAYER_TARGET_ZERO);
        FightEffects.processEffectTarget(fight_, INTERVERSION, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
    }

    @Test
    public void processEffectTarget12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
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
        FightEffects.processEffectTarget(fight_, ANTI_BRUME, CustList.FIRST_INDEX, thrower_, thrower_, diff_, _data_);
        fighter_ = fight_.getFighter(teamPosition_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertEq(LgInt.zero(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)).intValue());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, POKEMON_FOE_FIGHTER_ZERO)).intValue());
        activity_ = fight_.getUserTeam().getEnabledMoves().getVal(ANTI_BRUME);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,teamPosition_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
    }

    @Test
    public void processEffectTarget13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(PICOTS);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, PICOTS, CustList.FIRST_INDEX, thrower_, thrower_, diff_, _data_);
        assertEq(LgInt.one(), fight_.getUserTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(ANNEAU_HYDRO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, ANNEAU_HYDRO, CustList.FIRST_INDEX, thrower_, thrower_, diff_, _data_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getEnabledMovesEndRound().getVal(ANNEAU_HYDRO);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
    }

    @Test
    public void processEffectTarget15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, JACKPOT, CustList.SECOND_INDEX, thrower_, thrower_, diff_, _data_);
        assertEq(new Rate("240"), fight_.getWinningMoney());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget16Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterStatut(GEL);
        fighter_.setFirstChosenMove(ZENITH);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, ZENITH, CustList.FIRST_INDEX, thrower_, thrower_, diff_, _data_);
        ActivityOfMove activity_;
        activity_ = fight_.getEnabledMoves().getVal(ZENITH);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(!fight_.getStillEnabledMoves().getVal(ZENITH));
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(FEU));
        assertEq(0, fighter_.getStatusNbRound(GEL).intValue());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget17Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(TROU);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, TROU, CustList.FIRST_INDEX, thrower_, thrower_, diff_, _data_);
        ActivityOfMove activity_;
        activity_ = fighter_.getEnabledMovesProt().getVal(TROU);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        StringList types_ = fighter_.getProtectedAgainstMoveTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(VOL));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget18Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(CHARGEUR);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, CHARGEUR, CustList.SECOND_INDEX, thrower_, thrower_, diff_, _data_);
        assertEq(new Rate("2"), fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget19Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(TOURNIQUET);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, TOURNIQUET, CustList.FIRST_INDEX, thrower_, thrower_, diff_, _data_);
        assertEq(new Rate("1/2"), fighter_.getDamageRateSufferedByType().getVal(FEU));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget20Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(COUP_D_MAIN, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, COUP_D_MAIN, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.getEnabledMovesForAlly().getVal(COUP_D_MAIN));
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget21Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(LIRE_ESPRIT);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, LIRE_ESPRIT, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        assertTrue(fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, target_)));
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget22Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
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
        fighter_.activerAttaqueImmu(TROU, _data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, true);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), true);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        FightEffects.processEffectTarget(fight_, RELAIS, CustList.FIRST_INDEX, thrower_, thrower_, diff_, _data_);
        Team userTeam_ = fight_.getUserTeam();
        NumberMap<Byte,Numbers<Byte>> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(2, map_.getVal((byte) 0).size());
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 1));
        assertEq(2, map_.getVal((byte) 1).size());
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(0, partner_.getGroundPlace());
        assertEq(0, partner_.getGroundPlaceSubst());
        assertEq(1, partner_.getNbUsesMoves().getVal(BOUL_ARMURE).intValue());
        assertEq(Rate.one(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("2"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(JACKPOT, partner_.getLastSufferedMove());
        assertEq(new Rate("1873/100"), partner_.getRemainingHp());
        assertEq(new Rate("1873/200"), partner_.getClone());
        assertEq(1, partner_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
    }

    @Test
    public void processEffectTarget23Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(COUD_BOUE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, COUD_BOUE, CustList.SECOND_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget24Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(COUD_BOUE, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.creerClone(new Rate("1/2"));
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, COUD_BOUE, CustList.SECOND_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget25Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(BERCEUSE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, BERCEUSE, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL).intValue());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget26Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
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
        FightEffects.processEffectTarget(fight_, VAMPIPOING, CustList.SECOND_INDEX, thrower_, thrower_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("5"), fighter_.getRemainingHp());
    }

    @Test
    public void processEffectTarget27Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setFirstChosenMove(SOIN);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, SOIN, CustList.FIRST_INDEX, thrower_, thrower_, diff_, _data_);
        assertEq(new Rate("2073/200"), fighter_.getRemainingHp());
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget28Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(ABIME, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, ABIME, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget29Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(DEPIT);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, DEPIT, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(16, fighter_.powerPointsMove(JACKPOT));
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget30Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(PAR_ICI);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, PAR_ICI, CustList.FIRST_INDEX, thrower_, thrower_, diff_, _data_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget31Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(DETECTION);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, DETECTION, CustList.FIRST_INDEX, thrower_, thrower_, diff_, _data_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget32Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(APRES_VOUS, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, APRES_VOUS, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget33Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(ELECTRISATION, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, ELECTRISATION, CustList.FIRST_INDEX, thrower_, target_, diff_, _data_);
        assertTrue(fighter_.isSuccessfulMove());
    }

    @Test
    public void processEffectTarget34Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = processEffectTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        FightEffects.processEffectTarget(fight_, NUEE_DE_POUDRE, CustList.SECOND_INDEX, thrower_, target_, diff_, _data_);
        assertTrue(fighter_.isSuccessfulMove());
    }
}
