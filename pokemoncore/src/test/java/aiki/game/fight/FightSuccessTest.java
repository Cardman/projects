package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.effects.EffectStatus;
import aiki.game.fight.util.NbEffectFighterCoords;
import aiki.game.fight.util.RandomBoolResults;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.characters.GymLeader;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.EnumList;
import code.util.StringList;
import code.util.StringMap;


public class FightSuccessTest extends InitializationDataBase {

    private static final String PIKA = "PIKA";

    private static Fight random() {
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
        moves_.put(SOIN, (short) 10);
        moves_.put(COUD_KRANE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
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
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        return fight_;
    }

    @Test
    public void random1Test() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(Rate.one(), LgInt.one());
        Rate event_ = FightSuccess.random(_data_, law_);
        assertEq(Rate.one(), event_);
    }

    @Test
    public void random2Test() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(Rate.one(), LgInt.one());
        law_.addEvent(Rate.zero(), LgInt.zero());
        Rate event_ = FightSuccess.random(_data_, law_);
        assertEq(Rate.one(), event_);
    }

    @Test
    public void random1SimulationTest() {
        Fight fight_ = random();
        fight_.setSimulation(true);
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(Rate.one(), LgInt.one());
        law_.addEvent(Rate.zero(), LgInt.zero());
        assertTrue(FightSuccess.isBadSimulation(fight_, law_));
    }

    @Test
    public void forbiddenStatus1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, _data_);
        FightInitialization.initFight(fight_, _data_);
        assertEq(0, FightSuccess.forbiddenStatus(fight_,_data_).size());
    }

    @Test
    public void forbiddenStatus2Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, _data_);
        FightInitialization.initFight(fight_, _data_);
        fight_.enableGlobalMove(ZENITH);
        StringList list_ = FightSuccess.forbiddenStatus(fight_,_data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(GEL));
    }

    @Test
    public void tirage1Test() {
        assertTrue(FightSuccess.tirage(_data_,Rate.one()));
        assertTrue(!FightSuccess.tirage(_data_,Rate.zero()));
        assertTrue(FightSuccess.tirage(_data_,new Rate("2")));
        assertTrue(FightSuccess.random(_data_,new MonteCarloString()).isEmpty());
    }

    private static Fight isProtectedAgainstMoveType() {
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
        moves_.put(SOIN, (short) 10);
        moves_.put(COUD_KRANE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
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
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        return fight_;
    }

    @Test
    public void isProtectedAgainstMoveType1Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType2Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueImmu(VOL_MAGNETIK, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType3Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueImmu(VOL_MAGNETIK, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType4Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(TENEBRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(OEIL_MIRACLE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PSY, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType5Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueImmu(VOL_MAGNETIK, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueImmu(TROU, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType6Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(SOL);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType7Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.disableGlobalMove(ZENITH);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(ELECTRIQUE);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType8Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType9Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueImmu(VOL_MAGNETIK, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueImmu(TROU, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType10Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType11Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType12Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType13Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, ELECTRIQUE, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType14Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType15Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType16Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType17Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(BALLON);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType18Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.enableGlobalMove(GRAVITE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(BALLON);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(VOL);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType19Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType20Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(TENEBRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(OEIL_MIRACLE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getProtectedAgainstMoveTypes().add(PSY);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PSY, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType21Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType22Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType23Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType24Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(LEVITATION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType25Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.enableGlobalMove(GRAVITE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(LEVITATION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType26Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ABRI_CAPE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(TONNERRE);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, _data_));
    }

    @Test
    public void isProtectedAgainstMoveType27Test() {
        Fight fight_ = isProtectedAgainstMoveType();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(BALLON);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    private static Fight rateEffAgainstTarget() {
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
        moves_.put(SOIN, (short) 10);
        moves_.put(COUD_KRANE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
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
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        return fight_;
    }

    @Test
    public void rateEffAgainstTarget1Test() {
        Fight fight_ = rateEffAgainstTarget();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(ROCHE);
        assertEq(new Rate("2"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, EAU, _data_));
    }

    @Test
    public void rateEffAgainstTarget2Test() {
        Fight fight_ = rateEffAgainstTarget();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(TENEBRE);
        assertEq(new Rate("0"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PSY, _data_));
    }

    @Test
    public void rateEffAgainstTarget3Test() {
        Fight fight_ = rateEffAgainstTarget();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(TENEBRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(OEIL_MIRACLE);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PSY, _data_));
    }

    @Test
    public void rateEffAgainstTarget4Test() {
        Fight fight_ = rateEffAgainstTarget();
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(ELECTRIQUE);
        assertEq(new Rate("0"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void rateEffAgainstTarget5Test() {
        Fight fight_ = rateEffAgainstTarget();
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(SOL);
        assertEq(new Rate("2"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, _data_));
    }

    @Test
    public void rateEffAgainstTarget6Test() {
        Fight fight_ = rateEffAgainstTarget();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(QUERELLEUR);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(SPECTRE);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, NORMAL, _data_));
    }

    @Test
    public void rateEffAgainstTarget7Test() {
        Fight fight_ = rateEffAgainstTarget();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(SPECTRE);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, NORMAL, _data_));
    }

    @Test
    public void rateEffAgainstTarget8Test() {
        Fight fight_ = rateEffAgainstTarget();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(VOL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void rateEffAgainstTarget9Test() {
        Fight fight_ = rateEffAgainstTarget();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(VOL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void rateEffAgainstTarget10Test() {
        Fight fight_ = rateEffAgainstTarget();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(VOL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, _data_));
    }

    @Test
    public void rateEffAgainstTarget11Test() {
        Fight fight_ = rateEffAgainstTarget();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(NORMAL);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, NORMAL, _data_));
    }

    @Test
    public void rateEffAgainstTarget12Test() {
        Fight fight_ = rateEffAgainstTarget();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(NORMAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, NORMAL, _data_));
    }

    private static Fight isProtectedAgainstMove() {
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
        moves_.put(SOIN, (short) 10);
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
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        return fight_;
    }

    @Test
    public void isProtectedAgainstMove1Test() {
        Fight fight_ = isProtectedAgainstMove();
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, SOIN, _data_));
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, INTERVERSION, _data_));
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, GLAS_DE_SOIN, _data_));
    }

    @Test
    public void isProtectedAgainstMove2Test() {
        Fight fight_ = isProtectedAgainstMove();
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, _data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(LEVITATION);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, _data_));
        fight_.enableGlobalMove(GRAVITE);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, _data_));
    }

    @Test
    public void isProtectedAgainstMove3Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(TELEPATHE);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, _data_));
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, LIRE_ESPRIT, _data_));
    }

    @Test
    public void isProtectedAgainstMove4Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(NULL_REF);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, _data_));
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, LIRE_ESPRIT, _data_));
    }

    @Test
    public void isProtectedAgainstMove5Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(GARDE_MYSTIK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(NORMAL);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, _data_));
    }

    @Test
    public void isProtectedAgainstMove6Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(TERA_VOLTAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(GARDE_MYSTIK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(NORMAL);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, _data_));
    }

    @Test
    public void isProtectedAgainstMove7Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(GARDE_MYSTIK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(NORMAL);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, _data_));
    }

    @Test
    public void isProtectedAgainstMove8Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(GARDE_MYSTIK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(NORMAL);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, IMPLORE, _data_));
    }

    @Test
    public void isProtectedAgainstMove9Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(GARDE_MYSTIK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(NORMAL);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, IMPLORE, _data_));
    }

    @Test
    public void isProtectedAgainstMove10Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(METEO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(NORMAL);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, IMPLORE, _data_));
    }

    @Test
    public void isProtectedAgainstMove11Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(ELECTRIQUE);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, _data_));
    }

    @Test
    public void isProtectedAgainstMove12Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(ELECTRIQUE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).backUpObject(BAIE_ENIGMA);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, _data_));
    }

    @Test
    public void isProtectedAgainstMove13Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(ELECTRIQUE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).backUpObject(BAIE_MEPO);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, _data_));
    }

    @Test
    public void isProtectedAgainstMove14Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(PLANTE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).backUpObject(BAIE_ENIGMA);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, _data_));
    }

    @Test
    public void isProtectedAgainstMove15Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(PLANTE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).backUpObject(BAIE_ENIGMA);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, _data_));
    }

    @Test
    public void isProtectedAgainstMove16Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(PLANTE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).backUpObject(BAIE_ENIGMA);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, _data_));
    }

    @Test
    public void isProtectedAgainstMove17Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(NULL_REF);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, EMBARGO, _data_));
    }

    @Test
    public void isProtectedAgainstMove18Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(AROMA_VOILE);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, EMBARGO, _data_));
    }

    @Test
    public void isProtectedAgainstMove19Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setItem(LUNETTES_FILTRE);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, POUDRE_TOXIK, _data_));
    }

    @Test
    public void isProtectedAgainstMove20Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setItem(BAIE_MEPO);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, POUDRE_TOXIK, _data_));
    }

    @Test
    public void isProtectedAgainstMove21Test() {
        Fight fight_ = isProtectedAgainstMove();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setItem(NULL_REF);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, POUDRE_TOXIK, _data_));
    }

    private static Fight sufferingDamageTypes() {
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
    public void sufferingDamageTypes1Test() {
        Fight fight_ = sufferingDamageTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        StringMap<Rate> map_;
        map_ = FightSuccess.sufferingDamageTypes(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, _data_);
        assertEq(0, map_.size());
    }

    @Test
    public void sufferingDamageTypes2Test() {
        Fight fight_ = sufferingDamageTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        StringMap<Rate> map_;
        map_ = FightSuccess.sufferingDamageTypes(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, _data_);
        assertEq(0, map_.size());
    }

    @Test
    public void sufferingDamageTypes3Test() {
        Fight fight_ = sufferingDamageTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        StringMap<Rate> map_;
        map_ = FightSuccess.sufferingDamageTypes(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, _data_);
        assertEq(1, map_.size());
        assertEq(new Rate("1/4"), map_.getVal(FEU));
    }

    @Test
    public void sufferingDamageTypes4Test() {
        Fight fight_ = sufferingDamageTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        StringMap<Rate> map_;
        map_ = FightSuccess.sufferingDamageTypes(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, _data_);
        assertEq(0, map_.size());
    }

    private static Fight droppedStatis() {
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
    public void droppedStatis1Test() {
        Fight fight_ = droppedStatis();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRISATION, true, _data_));
    }

    @Test
    public void droppedStatis2Test() {
        Fight fight_ = droppedStatis();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRISATION, true, _data_));
    }

    @Test
    public void droppedStatis3Test() {
        Fight fight_ = droppedStatis();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, true, _data_));
    }

    @Test
    public void droppedStatis4Test() {
        Fight fight_ = droppedStatis();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, false, _data_));
    }

    @Test
    public void droppedStatis5Test() {
        Fight fight_ = droppedStatis();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, true, _data_));
        assertTrue(FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, false, _data_));
    }

    @Test
    public void droppedStatis6Test() {
        Fight fight_ = droppedStatis();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, true, _data_));
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, false, _data_));
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, true, _data_));
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, false, _data_));
    }

    @Test
    public void droppedStatis7Test() {
        Fight fight_ = droppedStatis();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, true, _data_));
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, false, _data_));
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, true, _data_));
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, false, _data_));
    }

    private static Fight sufferingDirectMoves() {
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
    public void sufferingDirectMoves1Test() {
        Fight fight_ = sufferingDirectMoves();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRISATION, true, _data_));
    }

    @Test
    public void sufferingDirectMoves2Test() {
        Fight fight_ = sufferingDirectMoves();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRISATION, true, _data_));
    }

    @Test
    public void sufferingDirectMoves3Test() {
        Fight fight_ = sufferingDirectMoves();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, true, _data_));
    }

    @Test
    public void sufferingDirectMoves4Test() {
        Fight fight_ = sufferingDirectMoves();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, false, _data_));
    }

    @Test
    public void sufferingDirectMoves5Test() {
        Fight fight_ = sufferingDirectMoves();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, true, _data_));
        assertTrue(FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, false, _data_));
    }

    @Test
    public void sufferingDirectMoves6Test() {
        Fight fight_ = sufferingDirectMoves();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, true, _data_));
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, false, _data_));
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, true, _data_));
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, false, _data_));
    }

    @Test
    public void sufferingDirectMoves7Test() {
        Fight fight_ = sufferingDirectMoves();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, true, _data_));
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, false, _data_));
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, true, _data_));
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, false, _data_));
    }

    private static Fight protectedTargetAgainstMove() {
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
    public void protectedTargetAgainstMove1Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, _data_));
    }

    @Test
    public void protectedTargetAgainstMove2Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(VAS_Y, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(VAS_Y, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, VAS_Y, _data_));
    }

    @Test
    public void protectedTargetAgainstMove3Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, _data_));
    }

    @Test
    public void protectedTargetAgainstMove4Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(GARDE_LARGE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(GARDE_LARGE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, _data_));
    }

    @Test
    public void protectedTargetAgainstMove5Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(REFLET_MAGIK, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, REFLET_MAGIK, _data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, REFLET_MAGIK, _data_));
    }

    @Test
    public void protectedTargetAgainstMove6Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ZONE_ETRANGE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(ZONE_ETRANGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ZONE_ETRANGE, _data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ZONE_ETRANGE, _data_));
    }

    @Test
    public void protectedTargetAgainstMove7Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, IMPLORE, _data_));
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), false);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, IMPLORE, _data_));
    }

    @Test
    public void protectedTargetAgainstMove8Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(FONCE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(FONCE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, FONCE, _data_));
    }

    @Test
    public void protectedTargetAgainstMove9Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(FONCE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(GARDE_LARGE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(FONCE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(GARDE_LARGE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, FONCE, _data_));
    }

    @Test
    public void protectedTargetAgainstMove10Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ROUE_DE_FEU, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(NUEE_DE_POUDRE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, _data_));
    }

    @Test
    public void protectedTargetAgainstMove11Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(COMBO_GRIFFE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(BOUCLIER_ROYAL, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, _data_));
    }

    @Test
    public void protectedTargetAgainstMove12Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(COMBO_GRIFFE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PICO_DEFENSE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, _data_));
    }

    @Test
    public void protectedTargetAgainstMove13Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ROUE_DE_FEU, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(NUEE_DE_POUDRE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, _data_));
    }

    @Test
    public void protectedTargetAgainstMove14Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(COMBO_GRIFFE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(BOUCLIER_ROYAL, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, _data_));
    }

    @Test
    public void protectedTargetAgainstMove15Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(COMBO_GRIFFE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PICO_DEFENSE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, _data_));
    }

    @Test
    public void protectedTargetAgainstMove16Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ROUE_DE_FEU, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(NUEE_DE_POUDRE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, _data_));
    }

    @Test
    public void protectedTargetAgainstMove17Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(COMBO_GRIFFE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TATAMIGAESHI, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(TATAMIGAESHI);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, _data_));
    }

    @Test
    public void protectedTargetAgainstMove18Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ELECTRISATION, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TATAMIGAESHI, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ELECTRISATION, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(TATAMIGAESHI);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ELECTRISATION, _data_));
    }

    @Test
    public void protectedTargetAgainstMove19Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(COMBO_GRIFFE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(VIGILANCE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(VIGILANCE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, _data_));
    }

    @Test
    public void protectedTargetAgainstMove20Test() {
        Fight fight_ = protectedTargetAgainstMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ELECTRISATION, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(VIGILANCE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ELECTRISATION, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(VIGILANCE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ELECTRISATION, _data_));
    }

    private static Fight successfulEffectWhileIfTargetIsNotThrower() {
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
        return fight_;
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower1Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, GLAS_DE_SOIN, 0, true, _data_));
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, INTERVERSION, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower2Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 0, true, _data_));
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), false);
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 1, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower3Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), true);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 1, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower4Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), true);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 1, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower5Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(SEISME, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TUNNEL, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, 0, true, _data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower6Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TUNNEL, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, _data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower7Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TUNNEL, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, _data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower8Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT,POKEMON_FOE_FIGHTER_ZERO), true);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TUNNEL, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, _data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower9Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower10Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(VAS_Y, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(VAS_Y, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAS_Y, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower11Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower12Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(GARDE_LARGE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(GARDE_LARGE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower13Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(SEISME, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ECRAN_POUDRE);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 0, true, _data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), true);
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 1, true, _data_));
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, IMPLORE, 1, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower14Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(SEISME, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(SANS_LIMITE);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, IMPLORE, 0, true, _data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_PLAYER_FIGHTER_ZERO), true);
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, IMPLORE, 1, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower15Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(TONNERRE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PLANNEUR, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(TONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(PLANNEUR, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, 0, true, _data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower16Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(TONNERRE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PLANNEUR, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(TONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(PLANNEUR, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, 0, true, _data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).incrementRoundBeforeUsingMove();
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower17Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(TONNERRE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TUNNEL, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(TONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).incrementRoundBeforeUsingMove();
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower18Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(REFLET_MAGIK, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, REFLET_MAGIK, 0, true, _data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, REFLET_MAGIK, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower19Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(REFLET_MAGIK, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TUNNEL, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, REFLET_MAGIK, 0, true, _data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, REFLET_MAGIK, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower20Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ZONE_ETRANGE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(ZONE_ETRANGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ZONE_ETRANGE, 0, true, _data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ZONE_ETRANGE, 0, true, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower21Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 0, false, _data_));
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), false);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 1, false, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower22Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(TERA_VOLTAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ECRAN_POUDRE);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 0, false, _data_));
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), false);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 1, false, _data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower23Test() {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(SEISME, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).apprendreAttaqueEcrasant(GARDE_LARGE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setFirstChosenMove(GARDE_LARGE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).successUsingMove();
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, 0, true, _data_));
    }

    private static Fight rateEffAgainstTargetMove() {
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
        moves_.put(A_LA_QUEUE, (short) 10);
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
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
        foePokemon_.setMoves(new StringList(A_LA_QUEUE,APRES_VOUS,SEISME,BROUHAHA));
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
        return fight_;
    }

    @Test
    public void rateEffAgainstTargetMove1Test() {
        Fight fight_ = rateEffAgainstTargetMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(new StringList(FEU, ROCHE));
        FightRound.initRound(fight_);
        assertEq(new Rate("4"),FightSuccess.rateEffAgainstTargetMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, _data_));
    }

    @Test
    public void rateEffAgainstTargetMove2Test() {
        Fight fight_ = rateEffAgainstTargetMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(new StringList(VOL));
        FightRound.initRound(fight_);
        assertEq(new Rate("0"),FightSuccess.rateEffAgainstTargetMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, _data_));
    }

    @Test
    public void rateEffAgainstTargetMove3Test() {
        Fight fight_ = rateEffAgainstTargetMove();
        FightRound.initRound(fight_);
        assertEq(new Rate("0"),FightSuccess.rateEffAgainstTargetMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, _data_));
    }

    private static Fight successChangedStatisticProtect() {
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
        moves_.put(A_LA_QUEUE, (short) 10);
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
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
        foePokemon_.setMoves(new StringList(A_LA_QUEUE,APRES_VOUS,SEISME,BROUHAHA));
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
        return fight_;
    }

    @Test
    public void successChangedStatisticProtect1Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFoeTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect2Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect3Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(BRUME), _data_));
    }

    @Test
    public void successChangedStatisticProtect4Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect5Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(HYPER_CUTTER);
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(BRUME), _data_));
    }

    @Test
    public void successChangedStatisticProtect6Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(HYPER_CUTTER);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, true, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect7Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(HYPER_CUTTER);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, false, new StringList(BRUME), _data_));
    }

    @Test
    public void successChangedStatisticProtect8Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(BRUME), _data_));
    }

    @Test
    public void successChangedStatisticProtect9Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(ERE_GEL);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(BRUME), _data_));
    }

    @Test
    public void successChangedStatisticProtect10Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(ERE_GEL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(BRULURE);
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(BRUME), _data_));
    }

    @Test
    public void successChangedStatisticProtect11Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect12Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect13Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HERBEBLANCHE);
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect14Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HERBEBLANCHE);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect15Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) _data_.getMaxBoost());
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, false, new StringList(), _data_));
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect16Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) _data_.getMinBoost());
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, false, new StringList(), _data_));
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect17Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFoeTeam().activerEffetEquipe(TOUR_RAPIDE);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect18Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(EAU));
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect19Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect20Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.SPEED, (byte) -1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect21Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect22Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), _data_));
    }

    @Test
    public void successChangedStatisticProtect23Test() {
        Fight fight_ = successChangedStatisticProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(EAU));
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), _data_));
    }

    private static Fight successChangedStatistic() {
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
        moves_.put(A_LA_QUEUE, (short) 10);
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
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
        foePokemon_.setMoves(new StringList(A_LA_QUEUE,APRES_VOUS,SEISME,BROUHAHA));
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
        return fight_;
    }

    @Test
    public void successChangedStatistic1Test() {
        Fight fight_ = successChangedStatistic();
        assertTrue(FightSuccess.successChangedStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, _data_));
    }

    @Test
    public void successChangedStatistic2Test() {
        Fight fight_ = successChangedStatistic();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(!FightSuccess.successChangedStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, _data_));
    }

    @Test
    public void successChangedStatistic3Test() {
        Fight fight_ = successChangedStatistic();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(INFILTRATION);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(FightSuccess.successChangedStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, _data_));
    }

    @Test
    public void successChangedStatistic4Test() {
        Fight fight_ = successChangedStatistic();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(INFILTRATION);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        assertTrue(!FightSuccess.successChangedStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, Statistic.ATTACK, (byte) -1, _data_));
    }

    @Test
    public void successChangedStatistic5Test() {
        Fight fight_ = successChangedStatistic();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(!FightSuccess.successChangedStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, _data_));
    }

    private static Fight successfulChangedBoostedStatistics() {
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
        return fight_;
    }

    @Test
    public void successfulChangedBoostedStatistics1Test() {
        Fight fight_ = successfulChangedBoostedStatistics();
        MoveData move_ = _data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedBoostedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulChangedBoostedStatistics2Test() {
        Fight fight_ = successfulChangedBoostedStatistics();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        MoveData move_ = _data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedBoostedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(0, stats_.size());
    }

    @Test
    public void successfulChangedBoostedStatistics3Test() {
        Fight fight_ = successfulChangedBoostedStatistics();
        MoveData move_ = _data_.getMove(ACUPRESSION);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedBoostedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(7, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_ATTACK));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPEED));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
        assertTrue(stats_.containsObj(Statistic.EVASINESS));
    }

    private static Fight successfulChangedStatistics() {
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
    public void successfulChangedStatistics1Test() {
        Fight fight_ = successfulChangedStatistics();
        MoveData move_ = _data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulChangedStatistics2Test() {
        Fight fight_ = successfulChangedStatistics();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        MoveData move_ = _data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(0, stats_.size());
    }

    @Test
    public void successfulChangedStatistics3Test() {
        Fight fight_ = successfulChangedStatistics();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        MoveData move_ = _data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(0, stats_.size());
    }

    @Test
    public void successfulChangedStatistics4Test() {
        Fight fight_ = successfulChangedStatistics();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        MoveData move_ = _data_.getMove(MUR_DE_FER);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, stats_.size());
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
    }

    @Test
    public void successfulChangedStatistics5Test() {
        Fight fight_ = successfulChangedStatistics();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = _data_.getMove(DEGOMMAGE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(3, stats_.size());
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPEED));
    }

    @Test
    public void successfulChangedStatistics6Test() {
        Fight fight_ = successfulChangedStatistics();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        MoveData move_ = _data_.getMove(BOOST);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(3, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
        assertTrue(stats_.containsObj(Statistic.EVASINESS));
    }

    @Test
    public void successfulChangedStatistics7Test() {
        Fight fight_ = successfulChangedStatistics();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = _data_.getMove(BUEE_NOIRE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(7, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_ATTACK));
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPEED));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
        assertTrue(stats_.containsObj(Statistic.EVASINESS));
    }

    @Test
    public void successfulChangedStatistics8Test() {
        Fight fight_ = successfulChangedStatistics();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = _data_.getMove(PERMUCOEUR);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(5, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_ATTACK));
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_DEFENSE));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulChangedStatistics9Test() {
        Fight fight_ = successfulChangedStatistics();
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = _data_.getMove(PERMUCOEUR);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(3, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_ATTACK));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulChangedStatistics10Test() {
        Fight fight_ = successfulChangedStatistics();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) 1);
        MoveData move_ = _data_.getMove(PERMUCOEUR);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(3, stats_.size());
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_DEFENSE));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulChangedStatistics11Test() {
        Fight fight_ = successfulChangedStatistics();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = _data_.getMove(PERMUCOEUR);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(3, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_ATTACK));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulChangedStatistics12Test() {
        Fight fight_ = successfulChangedStatistics();
        MoveData move_ = _data_.getMove(TROU_BIS);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().first();
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, stats_.size());
    }

    @Test
    public void successfulChangedStatistics13Test() {
        Fight fight_ = successfulChangedStatistics();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ACCURACY, (byte) 3);
        MoveData move_ = _data_.getMove(PERMUCOEUR);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        EnumList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(5, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_ATTACK));
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_DEFENSE));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    private static Fight successfulAffectedStatusProtect() {
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
        moves_.put(A_LA_QUEUE, (short) 10);
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
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
        foePokemon_.setMoves(new StringList(A_LA_QUEUE,APRES_VOUS,SEISME,BROUHAHA));
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
    public void successfulAffectedStatusProtect1Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.enableGlobalMove(BROUHAHA);
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect2Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect3Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.getFoeTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect4Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.getFoeTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(RUNE_PROTECT), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect5Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, BRULURE, false, new StringList(RUNE_PROTECT), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect6Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, BRULURE, false, new StringList(RUNE_PROTECT), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect7Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, BRULURE, true, new StringList(RUNE_PROTECT), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect8Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(RUNE_PROTECT), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect9Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, BRULURE, false, new StringList(RUNE_PROTECT), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect10Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(RUNE_PROTECT), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect11Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(RUNE_PROTECT), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect12Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(RUNE_PROTECT), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect13Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HERBE_MENTAL);
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, AMOUR, false, new StringList(RUNE_PROTECT), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect14Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HERBE_MENTAL);
        fight_.getFoeTeam().activerEffetEquipe(TOUR_RAPIDE);
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, AMOUR, false, new StringList(RUNE_PROTECT), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect15Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(EAU));
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect16Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect17Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, BRULURE, false, new StringList(), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect18Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(EAU));
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), _data_));
    }

    @Test
    public void successfulAffectedStatusProtect19Test() {
        Fight fight_ = successfulAffectedStatusProtect();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), _data_));
    }

    private static Fight successfulAffectedStatus() {
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
        moves_.put(A_LA_QUEUE, (short) 10);
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
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
        foePokemon_.setMoves(new StringList(A_LA_QUEUE,APRES_VOUS,SEISME,BROUHAHA));
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
    public void successfulAffectedStatus1Test() {
        Fight fight_ = successfulAffectedStatus();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HERBE_MENTAL);
        assertTrue(!FightSuccess.successfulAffectedStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, AMOUR, _data_));
    }

    @Test
    public void successfulAffectedStatus2Test() {
        Fight fight_ = successfulAffectedStatus();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).backUpObject(HERBE_MENTAL);
        assertTrue(FightSuccess.successfulAffectedStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, AMOUR, _data_));
    }

    @Test
    public void successfulAffectedStatus3Test() {
        Fight fight_ = successfulAffectedStatus();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(!FightSuccess.successfulAffectedStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, _data_));
    }

    @Test
    public void successfulAffectedStatus4Test() {
        Fight fight_ = successfulAffectedStatus();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(INFILTRATION);
        fight_.getFoeTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(FightSuccess.successfulAffectedStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, _data_));
    }

    @Test
    public void successfulAffectedStatus5Test() {
        Fight fight_ = successfulAffectedStatus();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(INFILTRATION);
        fight_.getUserTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(!FightSuccess.successfulAffectedStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SOMMEIL, _data_));
    }

    @Test
    public void successfulAffectedStatus6Test() {
        Fight fight_ = successfulAffectedStatus();
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(INFILTRATION);
        fight_.getUserTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(FightSuccess.successfulAffectedStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, AMOUR_TRES_MOU, _data_));
    }

    private static Fight successfulEffectStatus() {
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
        moves_.put(A_LA_QUEUE, (short) 10);
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
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
        foePokemon_.setMoves(new StringList(A_LA_QUEUE,APRES_VOUS,SEISME,BROUHAHA));
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
    public void successfulEffectStatus1Test() {
        Fight fight_ = successfulEffectStatus();
        MoveData move_ = _data_.getMove(ECHANGE_PSY);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().last();
        assertTrue(!FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successfulEffectStatus2Test() {
        Fight fight_ = successfulEffectStatus();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(BRULURE);
        fight_.getFoeTeam().activerEffetEquipe(RUNE_PROTECT);
        MoveData move_ = _data_.getMove(ECHANGE_PSY);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().last();
        assertTrue(!FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successfulEffectStatus3Test() {
        Fight fight_ = successfulEffectStatus();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(BRULURE);
        MoveData move_ = _data_.getMove(ECHANGE_PSY);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().last();
        assertTrue(FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successfulEffectStatus4Test() {
        Fight fight_ = successfulEffectStatus();
        MoveData move_ = _data_.getMove(REPOS);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successfulEffectStatus5Test() {
        Fight fight_ = successfulEffectStatus();
        MoveData move_ = _data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successfulEffectStatus6Test() {
        Fight fight_ = successfulEffectStatus();
        MoveData move_ = _data_.getMove(BERCEUSE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successfulEffectStatus7Test() {
        Fight fight_ = successfulEffectStatus();
        fight_.enableGlobalMove(BROUHAHA);
        MoveData move_ = _data_.getMove(BERCEUSE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(!FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successfulEffectStatus8Test() {
        Fight fight_ = successfulEffectStatus();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        MoveData move_ = _data_.getMove(BERCEUSE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(!FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successfulEffectStatus9Test() {
        Fight fight_ = successfulEffectStatus();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        MoveData move_ = _data_.getMove(PSYKOUD_BOUL);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().last();
        assertTrue(!FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successfulEffectStatus10Test() {
        Fight fight_ = successfulEffectStatus();
        MoveData move_ = _data_.getMove(DODO);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successfulEffectStatus11Test() {
        Fight fight_ = successfulEffectStatus();
        MoveData move_ = _data_.getMove(DODO_PETIT);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    private static Fight successEffect() {
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
    public void successEffect1Test() {
        Fight fight_ = successEffect();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(MUR_DE_FER);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        Effect eff_ = _data_.getMove(MUR_DE_FER).getEffects().first();
        assertTrue(FightSuccess.successEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successEffect2Test() {
        Fight fight_ = successEffect();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(BERCEUSE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        Effect eff_ = _data_.getMove(BERCEUSE).getEffects().first();
        assertTrue(FightSuccess.successEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successEffect3Test() {
        Fight fight_ = successEffect();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(MUR_DE_FER);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) 6);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        Effect eff_ = _data_.getMove(MUR_DE_FER).getEffects().first();
        assertTrue(!FightSuccess.successEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successEffect4Test() {
        Fight fight_ = successEffect();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(BERCEUSE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        Effect eff_ = _data_.getMove(BERCEUSE).getEffects().first();
        assertTrue(!FightSuccess.successEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successEffect5Test() {
        Fight fight_ = successEffect();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(BERCEUSE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        Effect eff_ = _data_.getMove(PHOTOCOPIE).getEffects().first();
        assertTrue(!FightSuccess.successEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    @Test
    public void successEffect6Test() {
        Fight fight_ = successEffect();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(BERCEUSE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        Effect eff_ = _data_.getMove(METRONOME).getEffects().first();
        assertTrue(FightSuccess.successEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_));
    }

    private static Fight successfulMove() {
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
        return fight_;
    }

    @Test
    public void successfulMove1Test() {
        Fight fight_ = successfulMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(LEVITATION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, 0, true, _data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove2Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(LEVITATION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.setSending(true);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, PICOTS, 0, true, _data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove3Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, 0, true, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove4Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(VAMPIPOING,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAMPIPOING, 0, true, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0, POKEMON_FOE_FIGHTER_ZERO), true);
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAMPIPOING, 1, true, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove5Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(VAMPIPOING,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAMPIPOING, 0, true, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0, POKEMON_FOE_FIGHTER_ZERO), false);
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAMPIPOING, 1, true, _data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove6Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(VAMPIPOING,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAMPIPOING, 0, true, _data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove7Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SOIN);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, SOIN, 0, true, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove8Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(DEVOREVE,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, DEVOREVE, 0, true, _data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove9Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(DEVOREVE,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, DEVOREVE, 0, true, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove10Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(LEVITATION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, PICOTS, 0, true, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove11Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.setSending(true);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, PICOTS, 0, true, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove12Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.setSending(true);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, PIEGE_DE_ROC, 0, true, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove13Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(VAMPIPOING,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0, POKEMON_FOE_FIGHTER_ZERO), false);
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAMPIPOING, 1, false, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove14Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(MUR_DE_FER);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) 6);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, MUR_DE_FER, 0, false, _data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove15Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(TOUR_RAPIDE,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TOUR_RAPIDE, 0, true, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0, POKEMON_FOE_FIGHTER_ZERO), true);
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TOUR_RAPIDE, 1, true, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove16Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(TOUR_RAPIDE,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TOUR_RAPIDE, 0, true, _data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TOUR_RAPIDE, 1, true, _data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove17Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU,POKEMON_FOE_TARGET_TWO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(TATAMIGAESHI);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).successUsingMove();
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, 0, true, _data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove18Test() {
        Fight fight_ = successfulMove();
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, 0, true, _data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(pair_.isEffectIfFail());
    }

    private static Fight canUseDirectlyMove() {
        Difficulty diff_ = new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ROUE_DE_FEU, (short) 10);
        moves_.put(RONFLEMENT, (short) 10);
        moves_.put(COUD_KRANE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(diff_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(diff_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(diff_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void canUseDirectlyMove1Test() {
        Fight fight_ = canUseDirectlyMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(RONFLEMENT, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.canUseDirectlyMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, _data_));
    }

    @Test
    public void canUseDirectlyMove2Test() {
        Fight fight_ = canUseDirectlyMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(COUD_KRANE);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.canUseDirectlyMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, _data_));
    }

    @Test
    public void canUseDirectlyMove3Test() {
        Fight fight_ = canUseDirectlyMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(COUD_KRANE);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.canUseDirectlyMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, _data_));
    }

    @Test
    public void canUseDirectlyMove4Test() {
        Fight fight_ = canUseDirectlyMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(COUD_KRANE);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.canUseDirectlyMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, _data_));
    }

    @Test
    public void canUseDirectlyMove5Test() {
        Fight fight_ = canUseDirectlyMove();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HERBE_POUV);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(COUD_KRANE);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.canUseDirectlyMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, _data_));
    }

    private static Fight canSkipRecharge() {
        Difficulty diff_ = new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ROUE_DE_FEU, (short) 10);
        moves_.put(RONFLEMENT, (short) 10);
        moves_.put(COUD_KRANE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(diff_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(diff_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(diff_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void canSkipRecharge1Test() {
        Fight fight_ = canSkipRecharge();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertTrue(!FightSuccess.canSkipRecharge(fight_,POKEMON_PLAYER_FIGHTER_ZERO, _data_));
    }

    @Test
    public void canSkipRecharge2Test() {
        Fight fight_ = canSkipRecharge();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(METEO);
        assertTrue(!FightSuccess.canSkipRecharge(fight_,POKEMON_PLAYER_FIGHTER_ZERO, _data_));
    }

    @Test
    public void canSkipRecharge3Test() {
        Fight fight_ = canSkipRecharge();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(SANS_LIMITE);
        assertTrue(FightSuccess.canSkipRecharge(fight_,POKEMON_PLAYER_FIGHTER_ZERO, _data_));
    }

    private static Fight accuracy() {
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
    public void accuracy1Test() {
        Fight fight_ = accuracy();
        assertEq(new Rate("0"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ABIME, _data_));
    }

    @Test
    public void accuracy2Test() {
        Fight fight_ = accuracy();
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, _data_));
    }

    @Test
    public void accuracy3Test() {
        Fight fight_ = accuracy();
        fight_.enableGlobalMove(GRAVITE);
        assertEq(new Rate("8/5"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, _data_));
    }

    @Test
    public void accuracy4Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(new Rate("9/10"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROULADE, _data_));
    }

    @Test
    public void accuracy5Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        assertEq(new Rate("9/10"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROULADE, _data_));
    }

    @Test
    public void accuracy6Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ANNULE_GARDE);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROULADE, _data_));
    }

    @Test
    public void accuracy7Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ANNULE_GARDE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertEq(new Rate("9/10"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROULADE, _data_));
    }

    @Test
    public void accuracy8Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ACCURACY, (byte) -1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ANNULE_GARDE);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, _data_));
    }

    @Test
    public void accuracy9Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ACCURACY, (byte) -1);
        assertEq(new Rate("2/3"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, _data_));
    }

    @Test
    public void accuracy10Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ACCURACY, (byte) -1);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COPIE_TYPE, _data_));
    }

    @Test
    public void accuracy11Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.EVASINESS, (byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ANNULE_GARDE);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, _data_));
    }

    @Test
    public void accuracy12Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.EVASINESS, (byte) 1);
        assertEq(new Rate("2/3"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, _data_));
    }

    @Test
    public void accuracy13Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.EVASINESS, (byte) 1);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COPIE_TYPE, _data_));
    }

    @Test
    public void accuracy14Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.EVASINESS, (byte) 1);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, RELAIS, _data_));
    }

    @Test
    public void accuracy15Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PEAU_MIRACLE);
        assertEq(new Rate("11/40"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BERCEUSE, _data_));
    }

    @Test
    public void accuracy16Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(new Rate("11/20"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BERCEUSE, _data_));
    }

    @Test
    public void accuracy17Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PEAU_MIRACLE_BIS);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BERCEUSE, _data_));
    }

    @Test
    public void accuracy18Test() {
        Fight fight_ = accuracy();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PEAU_MIRACLE_TER);
        assertEq(new Rate("0"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BERCEUSE, _data_));
    }

    private static Fight rateCriticalHit() {
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
    public void rateCriticalHit1Test() {
        Fight fight_ = rateCriticalHit();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(NULL_REF);
        assertEq(new Rate("1/16"), FightSuccess.rateCriticalHit(fight_, fighter_, (byte) 0, _data_));
    }

    @Test
    public void rateCriticalHit2Test() {
        Fight fight_ = rateCriticalHit();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        assertEq(new Rate("1/16"), FightSuccess.rateCriticalHit(fight_, fighter_, (byte) 0, _data_));
    }

    @Test
    public void rateCriticalHit3Test() {
        Fight fight_ = rateCriticalHit();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(CHANCEUX);
        assertEq(new Rate("1/8"), FightSuccess.rateCriticalHit(fight_, fighter_, (byte) 0, _data_));
    }

    private static Fight multProbaByComboOfMoves() {
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
    public void multProbaByComboOfMoves1Test() {
        Fight fight_ = multProbaByComboOfMoves();
        assertEq(new Rate("1"),FightSuccess.multProbaByComboOfMoves(fight_, Fight.PLAYER, _data_));
    }

    @Test
    public void multProbaByComboOfMoves2Test() {
        Fight fight_ = multProbaByComboOfMoves();
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        assertEq(new Rate("1"),FightSuccess.multProbaByComboOfMoves(fight_, Fight.PLAYER, _data_));
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        assertEq(new Rate("4"),FightSuccess.multProbaByComboOfMoves(fight_, Fight.PLAYER, _data_));
    }

    @Test
    public void multProbaByComboOfMoves3Test() {
        Fight fight_ = multProbaByComboOfMoves();
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        assertEq(new Rate("2"),FightSuccess.multProbaByComboOfMoves(fight_, Fight.PLAYER, _data_));
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        assertEq(new Rate("4"),FightSuccess.multProbaByComboOfMoves(fight_, Fight.PLAYER, _data_));
    }

    private static Fight probaEffectStatistic() {
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
    public void probaEffectStatistic1Test() {
        Fight fight_ = probaEffectStatistic();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(SERENITE);
        assertEq(new Rate("1/2"), FightSuccess.probaEffectStatistic(fight_, fighter_, new Rate("1/2"), true, _data_));
    }

    @Test
    public void probaEffectStatistic2Test() {
        Fight fight_ = probaEffectStatistic();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(NULL_REF);
        assertEq(new Rate("1/2"), FightSuccess.probaEffectStatistic(fight_, fighter_, new Rate("1/2"), false, _data_));
    }

    @Test
    public void probaEffectStatistic3Test() {
        Fight fight_ = probaEffectStatistic();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        assertEq(new Rate("1/2"), FightSuccess.probaEffectStatistic(fight_, fighter_, new Rate("1/2"), false, _data_));
    }

    @Test
    public void probaEffectStatistic4Test() {
        Fight fight_ = probaEffectStatistic();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(SERENITE);
        assertEq(new Rate("1"), FightSuccess.probaEffectStatistic(fight_, fighter_, new Rate("1/2"), false, _data_));
    }

    @Test
    public void probaEffectStatistic5Test() {
        Fight fight_ = probaEffectStatistic();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        assertEq(new Rate("1"), FightSuccess.probaEffectStatistic(fight_, fighter_, new Rate("1/2"), false, _data_));
    }

    @Test
    public void probaEffectStatistic6Test() {
        Fight fight_ = probaEffectStatistic();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        assertEq(new Rate("2"), FightSuccess.probaEffectStatistic(fight_, fighter_, new Rate("1/2"), false, _data_));
    }

    @Test
    public void probaEffectStatistic7Test() {
        Fight fight_ = probaEffectStatistic();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        MoveData move_ = _data_.getMove(MUR_DE_FER);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("1"), FightSuccess.probaEffectStatistic(fight_, fighter_, eff_, false, _data_));
    }

    private static Fight probaEffectStatus() {
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
    public void probaEffectStatus1Test() {
        Fight fight_ = probaEffectStatus();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, LgInt.one());
        MonteCarloString res_ = FightSuccess.probaEffectStatus(fight_, fighter_, law_, _data_);
        assertEq(1, res_.getLaw().getKeys().size());
        assertTrue(res_.getLaw().getKeys().containsObj(NULL_REF));
    }

    @Test
    public void probaEffectStatus2Test() {
        Fight fight_ = probaEffectStatus();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, LgInt.one());
        law_.addEvent(BRULURE, LgInt.one());
        MonteCarloString res_ = FightSuccess.probaEffectStatus(fight_, fighter_, law_, _data_);
        assertEq(2, res_.getLaw().getKeys().size());
        assertTrue(res_.getLaw().getKeys().containsObj(NULL_REF));
        assertTrue(res_.getLaw().getKeys().containsObj(BRULURE));
    }

    @Test
    public void probaEffectStatus3Test() {
        Fight fight_ = probaEffectStatus();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, LgInt.one());
        law_.addEvent(BRULURE, LgInt.one());
        MonteCarloString res_ = FightSuccess.probaEffectStatus(fight_, fighter_, law_, _data_);
        assertEq(2, res_.getLaw().getKeys().size());
        assertTrue(res_.getLaw().getKeys().containsObj(NULL_REF));
        assertTrue(res_.getLaw().getKeys().containsObj(BRULURE));
    }

    @Test
    public void probaEffectStatus4Test() {
        Fight fight_ = probaEffectStatus();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(SERENITE);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, LgInt.one());
        law_.addEvent(BRULURE, LgInt.one());
        MonteCarloString res_ = FightSuccess.probaEffectStatus(fight_, fighter_, law_, _data_);
        assertEq(1, res_.getLaw().getKeys().size());
        assertTrue(res_.getLaw().getKeys().containsObj(BRULURE));
    }

    @Test
    public void probaEffectStatus5Test() {
        Fight fight_ = probaEffectStatus();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, new LgInt("3"));
        law_.addEvent(BRULURE, LgInt.one());
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        MonteCarloString res_ = FightSuccess.probaEffectStatus(fight_, fighter_, law_, _data_);
        assertEq(1, res_.getLaw().getKeys().size());
        assertTrue(res_.getLaw().getKeys().containsObj(BRULURE));
    }

    @Test
    public void probaEffectStatus6Test() {
        Fight fight_ = probaEffectStatus();
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(SERENITE);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent(NULL_REF, new LgInt("3"));
        law_.addEvent(BRULURE, LgInt.one());
        MonteCarloString res_ = FightSuccess.probaEffectStatus(fight_, fighter_, law_, _data_);
        assertEq(2, res_.getLaw().getKeys().size());
        assertTrue(res_.getLaw().getKeys().containsObj(NULL_REF));
        assertTrue(res_.getLaw().getKeys().containsObj(BRULURE));
        assertEq(new LgInt("2"),res_.rate(NULL_REF));
        assertEq(new LgInt("2"),res_.rate(BRULURE));
    }
}
