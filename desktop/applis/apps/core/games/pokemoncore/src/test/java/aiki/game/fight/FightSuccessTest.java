package aiki.game.fight;

import aiki.db.DataBase;
import code.util.core.BoolVal;
import code.util.core.StringUtil;
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
import code.util.IdList;
import code.util.StringList;
import code.util.StringMap;


public class FightSuccessTest extends InitializationDataBase {

    private static final String PIKA = "PIKA";

    private static Fight random(DataBase _data) {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false, _data);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data);
        return fight_;
    }

    @Test
    public void random1Test() {
        DataBase data_ = initDb();
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(Rate.one(), LgInt.one());
        Rate event_ = FightSuccess.random(data_, law_);
        assertEq(Rate.one(), event_);
    }

    @Test
    public void random2Test() {
        DataBase data_ = initDb();
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(Rate.one(), LgInt.one());
        law_.addQuickEvent(Rate.zero(), LgInt.zero());
        Rate event_ = FightSuccess.random(data_, law_);
        assertEq(Rate.one(), event_);
    }

    @Test
    public void random1SimulationTest() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.setSimulation(true);
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(Rate.one(), LgInt.one());
        law_.addQuickEvent(Rate.zero(), LgInt.zero());
        assertTrue(FightSuccess.isBadSimulation(fight_, law_));
    }

    @Test
    public void forbiddenStatus1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
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
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data_);
        FightInitialization.initFight(fight_, data_);
        assertEq(0, FightSuccess.forbiddenStatus(fight_,data_).size());
    }

    @Test
    public void forbiddenStatus2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
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
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data_);
        FightInitialization.initFight(fight_, data_);
        fight_.enableGlobalMove(ZENITH);
        StringList list_ = FightSuccess.forbiddenStatus(fight_,data_);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, GEL));
    }

    @Test
    public void tirage1Test() {
        DataBase data_ = initDb();
        assertTrue(FightSuccess.tirage(data_,Rate.one()));
        assertTrue(!FightSuccess.tirage(data_,Rate.zero()));
        assertTrue(FightSuccess.tirage(data_,new Rate("2")));
        assertTrue(FightSuccess.random(data_,new MonteCarloString()).isEmpty());
    }

    @Test
    public void isProtectedAgainstMoveType1Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType2Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueImmu(VOL_MAGNETIK, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType3Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueImmu(VOL_MAGNETIK, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType4Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(TENEBRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(OEIL_MIRACLE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PSY, data_));
    }

    @Test
    public void isProtectedAgainstMoveType5Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueImmu(VOL_MAGNETIK, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueImmu(TROU, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType6Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(SOL);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, data_));
    }

    @Test
    public void isProtectedAgainstMoveType7Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
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
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType8Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, data_));
    }

    @Test
    public void isProtectedAgainstMoveType9Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueImmu(VOL_MAGNETIK, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueImmu(TROU, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType10Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, data_));
    }

    @Test
    public void isProtectedAgainstMoveType11Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, data_));
    }

    @Test
    public void isProtectedAgainstMoveType12Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, data_));
    }

    @Test
    public void isProtectedAgainstMoveType13Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, ELECTRIQUE, data_));
    }

    @Test
    public void isProtectedAgainstMoveType14Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType15Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType16Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType17Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(BALLON);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType18Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.enableGlobalMove(GRAVITE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(BALLON);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(VOL);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType19Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, data_));
    }

    @Test
    public void isProtectedAgainstMoveType20Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
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
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PSY, data_));
    }

    @Test
    public void isProtectedAgainstMoveType21Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType22Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType23Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType24Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(LEVITATION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType25Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.enableGlobalMove(GRAVITE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(LEVITATION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void isProtectedAgainstMoveType26Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ABRI_CAPE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(TONNERRE);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, data_));
    }

    @Test
    public void isProtectedAgainstMoveType27Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PARATONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(BALLON);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.isProtectedAgainstMoveType(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void rateEffAgainstTarget1Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(ROCHE);
        assertEq(new Rate("2"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, EAU, data_));
    }

    @Test
    public void rateEffAgainstTarget2Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(TENEBRE);
        assertEq(new Rate("0"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PSY, data_));
    }

    @Test
    public void rateEffAgainstTarget3Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(TENEBRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(OEIL_MIRACLE);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PSY, data_));
    }

    @Test
    public void rateEffAgainstTarget4Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(ELECTRIQUE);
        assertEq(new Rate("0"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void rateEffAgainstTarget5Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(SOL);
        assertEq(new Rate("2"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRIQUE, data_));
    }

    @Test
    public void rateEffAgainstTarget6Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(QUERELLEUR);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(SPECTRE);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, NORMAL, data_));
    }

    @Test
    public void rateEffAgainstTarget7Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(SPECTRE);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, NORMAL, data_));
    }

    @Test
    public void rateEffAgainstTarget8Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(VOL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void rateEffAgainstTarget9Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(VOL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void rateEffAgainstTarget10Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(VOL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOL, data_));
    }

    @Test
    public void rateEffAgainstTarget11Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(NORMAL);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, NORMAL, data_));
    }

    @Test
    public void rateEffAgainstTarget12Test() {
        DataBase data_ = initDb();
        Fight fight_ = random(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(NORMAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaqueAntiImmu(ANTI_AIR);
        assertEq(new Rate("1"), FightSuccess.rateEffAgainstTarget(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, NORMAL, data_));
    }

    private static Fight isProtectedAgainstMove(DataBase _data) {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false, _data);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data);
        return fight_;
    }

    @Test
    public void isProtectedAgainstMove1Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, SOIN, data_));
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, INTERVERSION, data_));
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, GLAS_DE_SOIN, data_));
    }

    @Test
    public void isProtectedAgainstMove2Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(LEVITATION);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, data_));
        fight_.enableGlobalMove(GRAVITE);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, data_));
    }

    @Test
    public void isProtectedAgainstMove3Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(TELEPATHE);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, data_));
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, LIRE_ESPRIT, data_));
    }

    @Test
    public void isProtectedAgainstMove4Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(NULL_REF);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, data_));
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, LIRE_ESPRIT, data_));
    }

    @Test
    public void isProtectedAgainstMove5Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(GARDE_MYSTIK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(NORMAL);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, data_));
    }

    @Test
    public void isProtectedAgainstMove6Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(TERA_VOLTAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(GARDE_MYSTIK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(NORMAL);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, data_));
    }

    @Test
    public void isProtectedAgainstMove7Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(GARDE_MYSTIK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(NORMAL);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, data_));
    }

    @Test
    public void isProtectedAgainstMove8Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PT_DE_MIRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(GARDE_MYSTIK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(NORMAL);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, IMPLORE, data_));
    }

    @Test
    public void isProtectedAgainstMove9Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(GARDE_MYSTIK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(NORMAL);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, IMPLORE, data_));
    }

    @Test
    public void isProtectedAgainstMove10Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(METEO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(NORMAL);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, IMPLORE, data_));
    }

    @Test
    public void isProtectedAgainstMove11Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(ELECTRIQUE);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, data_));
    }

    @Test
    public void isProtectedAgainstMove12Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(ELECTRIQUE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).backUpObject(BAIE_ENIGMA);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, data_));
    }

    @Test
    public void isProtectedAgainstMove13Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(ELECTRIQUE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).backUpObject(BAIE_MEPO);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, data_));
    }

    @Test
    public void isProtectedAgainstMove14Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(PLANTE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).backUpObject(BAIE_ENIGMA);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, data_));
    }

    @Test
    public void isProtectedAgainstMove15Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(PLANTE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).backUpObject(BAIE_ENIGMA);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, data_));
    }

    @Test
    public void isProtectedAgainstMove16Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(PLANTE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).backUpObject(BAIE_ENIGMA);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, data_));
    }

    @Test
    public void isProtectedAgainstMove17Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(NULL_REF);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, EMBARGO, data_));
    }

    @Test
    public void isProtectedAgainstMove18Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(AROMA_VOILE);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, EMBARGO, data_));
    }

    @Test
    public void isProtectedAgainstMove19Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setItem(LUNETTES_FILTRE);
        assertTrue(FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, POUDRE_TOXIK, data_));
    }

    @Test
    public void isProtectedAgainstMove20Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setItem(BAIE_MEPO);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, POUDRE_TOXIK, data_));
    }

    @Test
    public void isProtectedAgainstMove21Test() {
        DataBase data_ = initDb();
        Fight fight_ = isProtectedAgainstMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setItem(NULL_REF);
        assertTrue(!FightSuccess.isProtectedAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, POUDRE_TOXIK, data_));
    }

    private static Fight sufferingDamageTypes(DataBase _data) {
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(_data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void sufferingDamageTypes1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        StringMap<Rate> map_;
        map_ = FightSuccess.sufferingDamageTypes(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, data_);
        assertEq(0, map_.size());
    }

    @Test
    public void sufferingDamageTypes2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        StringMap<Rate> map_;
        map_ = FightSuccess.sufferingDamageTypes(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, data_);
        assertEq(0, map_.size());
    }

    @Test
    public void sufferingDamageTypes3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        StringMap<Rate> map_;
        map_ = FightSuccess.sufferingDamageTypes(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, data_);
        assertEq(1, map_.size());
        assertEq(new Rate("1/4"), map_.getVal(FEU));
    }

    @Test
    public void sufferingDamageTypes4Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        StringMap<Rate> map_;
        map_ = FightSuccess.sufferingDamageTypes(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, data_);
        assertEq(0, map_.size());
    }

    @Test
    public void sufferingDamageTypes5Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        FightRound.initRound(fight_);
        StringMap<Rate> map_;
        map_ = FightSuccess.sufferingDamageTypes(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PICO_DEFENSE, data_);
        assertEq(0, map_.size());
    }
    @Test
    public void droppedStatis1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRISATION, true, data_));
    }

    @Test
    public void droppedStatis2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRISATION, true, data_));
    }

    @Test
    public void droppedStatis3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, true, data_));
    }

    @Test
    public void droppedStatis4Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, false, data_));
    }

    @Test
    public void droppedStatis5Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, true, data_));
        assertTrue(FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, false, data_));
    }

    @Test
    public void droppedStatis6Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, true, data_));
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, false, data_));
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, true, data_));
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, false, data_));
    }

    @Test
    public void droppedStatis7Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, true, data_));
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, false, data_));
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, true, data_));
        assertTrue(!FightSuccess.droppedStatis(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, false, data_));
    }

    @Test
    public void sufferingDirectMoves1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRISATION, true, data_));
    }

    @Test
    public void sufferingDirectMoves2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ELECTRISATION, true, data_));
    }

    @Test
    public void sufferingDirectMoves3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, true, data_));
    }

    @Test
    public void sufferingDirectMoves4Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, false, data_));
    }

    @Test
    public void sufferingDirectMoves5Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, true, data_));
        assertTrue(FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, false, data_));
    }

    @Test
    public void sufferingDirectMoves6Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, true, data_));
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, false, data_));
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, true, data_));
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, false, data_));
    }

    @Test
    public void sufferingDirectMoves7Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, true, data_));
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, false, data_));
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, true, data_));
        assertTrue(!FightSuccess.sufferingDirectMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, false, data_));
    }

    @Test
    public void protectedTargetAgainstMove1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, data_));
    }

    @Test
    public void protectedTargetAgainstMove2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(VAS_Y, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(VAS_Y, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, VAS_Y, data_));
    }

    @Test
    public void protectedTargetAgainstMove3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, data_));
    }

    @Test
    public void protectedTargetAgainstMove4Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(GARDE_LARGE, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(GARDE_LARGE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, data_));
    }

    @Test
    public void protectedTargetAgainstMove5Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(REFLET_MAGIK, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, REFLET_MAGIK, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, REFLET_MAGIK, data_));
    }

    @Test
    public void protectedTargetAgainstMove6Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ZONE_ETRANGE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(ZONE_ETRANGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ZONE_ETRANGE, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ZONE_ETRANGE, data_));
    }

    @Test
    public void protectedTargetAgainstMove7Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, IMPLORE, data_));
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), BoolVal.FALSE);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, IMPLORE, data_));
    }

    @Test
    public void protectedTargetAgainstMove8Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(FONCE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(FONCE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, FONCE, data_));
    }

    @Test
    public void protectedTargetAgainstMove9Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(FONCE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(GARDE_LARGE, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(FONCE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(GARDE_LARGE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, FONCE, data_));
    }

    @Test
    public void protectedTargetAgainstMove10Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ROUE_DE_FEU, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(NUEE_DE_POUDRE, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, data_));
    }

    @Test
    public void protectedTargetAgainstMove11Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(COMBO_GRIFFE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(BOUCLIER_ROYAL, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, data_));
    }

    @Test
    public void protectedTargetAgainstMove12Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(COMBO_GRIFFE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PICO_DEFENSE, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, data_));
    }

    @Test
    public void protectedTargetAgainstMove13Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ROUE_DE_FEU, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(NUEE_DE_POUDRE, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, data_));
    }

    @Test
    public void protectedTargetAgainstMove14Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(COMBO_GRIFFE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(BOUCLIER_ROYAL, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(BOUCLIER_ROYAL);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(BOUCLIER_ROYAL);
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, data_));
    }

    @Test
    public void protectedTargetAgainstMove15Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(COMBO_GRIFFE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PICO_DEFENSE, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PICO_DEFENSE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(PICO_DEFENSE);
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, data_));
    }

    @Test
    public void protectedTargetAgainstMove16Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ROUE_DE_FEU, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(NUEE_DE_POUDRE, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ROUE_DE_FEU, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(NUEE_DE_POUDRE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).enableCounteringMoves(NUEE_DE_POUDRE);
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, data_));
    }

    @Test
    public void protectedTargetAgainstMove17Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(COMBO_GRIFFE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TATAMIGAESHI, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(TATAMIGAESHI);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, data_));
    }

    @Test
    public void protectedTargetAgainstMove18Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ELECTRISATION, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TATAMIGAESHI, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ELECTRISATION, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(TATAMIGAESHI);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ELECTRISATION, data_));
    }

    @Test
    public void protectedTargetAgainstMove19Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(COMBO_GRIFFE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(VIGILANCE, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(COMBO_GRIFFE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(VIGILANCE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, COMBO_GRIFFE, data_));
    }

    @Test
    public void protectedTargetAgainstMove20Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ELECTRISATION, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(VIGILANCE, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ELECTRISATION, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(VIGILANCE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.protectedTargetAgainstMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, ELECTRISATION, data_));
    }

    private static Fight successfulEffectWhileIfTargetIsNotThrower(DataBase _data) {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false, _data);
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
        return fight_;
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower1Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, GLAS_DE_SOIN, 0, true, data_));
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, INTERVERSION, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower2Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 0, true, data_));
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), BoolVal.FALSE);
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 1, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower3Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 1, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower4Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 1, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower5Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(SEISME, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TUNNEL, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, 0, true, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower6Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TUNNEL, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower7Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TUNNEL, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower8Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT,POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TUNNEL, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower9Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower10Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(VAS_Y, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(VAS_Y, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAS_Y, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower11Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower12Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BROUHAHA, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(GARDE_LARGE, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(GARDE_LARGE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BROUHAHA, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower13Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(SEISME, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ECRAN_POUDRE);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 0, true, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 1, true, data_));
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, IMPLORE, 1, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower14Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(SEISME, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(SANS_LIMITE);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, IMPLORE, 0, true, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_PLAYER_FIGHTER_ZERO), BoolVal.TRUE);
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, IMPLORE, 1, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower15Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(TONNERRE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PLANNEUR, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(TONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(PLANNEUR, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, 0, true, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower16Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(TONNERRE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PLANNEUR, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(TONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(PLANNEUR, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, 0, true, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).incrementRoundBeforeUsingMove();
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower17Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.enableGlobalMove(ORAGE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(TONNERRE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TUNNEL, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(TONNERRE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setDisappeared(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).incrementRoundBeforeUsingMove();
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower18Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(REFLET_MAGIK, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, REFLET_MAGIK, 0, true, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, REFLET_MAGIK, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower19Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(REFLET_MAGIK, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(TUNNEL, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(REFLET_MAGIK);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, REFLET_MAGIK, 0, true, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, REFLET_MAGIK, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower20Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ZONE_ETRANGE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(ZONE_ETRANGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ZONE_ETRANGE, 0, true, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ZONE_ETRANGE, 0, true, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower21Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 0, false, data_));
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), BoolVal.FALSE);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 1, false, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower22Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(PREVENTION, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(PREVENTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(TERA_VOLTAGE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(ECRAN_POUDRE);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 0, false, data_));
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0,POKEMON_FOE_FIGHTER_ZERO), BoolVal.FALSE);
        assertTrue(FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, IMPLORE, 1, false, data_));
    }

    @Test
    public void successfulEffectWhileIfTargetIsNotThrower23Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(SEISME, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).apprendreAttaqueEcrasant(GARDE_LARGE, COPIE, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setFirstChosenMove(GARDE_LARGE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).successUsingMove();
        assertTrue(!FightSuccess.successfulEffectWhileIfTargetIsNotThrower(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SEISME, 0, true, data_));
    }

    private static Fight rateEffAgainstTargetMove(DataBase _data) {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false, _data);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data);
        return fight_;
    }

    @Test
    public void rateEffAgainstTargetMove1Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(new StringList(FEU, ROCHE));
        FightRound.initRound(fight_);
        assertEq(new Rate("4"),FightSuccess.rateEffAgainstTargetMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data_));
    }

    @Test
    public void rateEffAgainstTargetMove2Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(new StringList(VOL));
        FightRound.initRound(fight_);
        assertEq(new Rate("0"),FightSuccess.rateEffAgainstTargetMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data_));
    }

    @Test
    public void rateEffAgainstTargetMove3Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        FightRound.initRound(fight_);
        assertEq(new Rate("0"),FightSuccess.rateEffAgainstTargetMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, data_));
    }

    @Test
    public void successChangedStatisticProtect1Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFoeTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect2Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect3Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(BRUME), data_));
    }

    @Test
    public void successChangedStatisticProtect4Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect5Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(HYPER_CUTTER);
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(BRUME), data_));
    }

    @Test
    public void successChangedStatisticProtect6Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(HYPER_CUTTER);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, true, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect7Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(HYPER_CUTTER);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, false, new StringList(BRUME), data_));
    }

    @Test
    public void successChangedStatisticProtect8Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(BRUME), data_));
    }

    @Test
    public void successChangedStatisticProtect9Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(ERE_GEL);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(BRUME), data_));
    }

    @Test
    public void successChangedStatisticProtect10Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(CRAN);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(ERE_GEL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(BRULURE);
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(BRUME), data_));
    }

    @Test
    public void successChangedStatisticProtect11Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect12Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect13Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HERBEBLANCHE);
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect14Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HERBEBLANCHE);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect15Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) data_.getMaxBoost());
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, false, new StringList(), data_));
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect16Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) data_.getMinBoost());
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, false, new StringList(), data_));
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect17Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFoeTeam().activerEffetEquipe(TOUR_RAPIDE);
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect18Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(EAU));
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect19Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect20Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.SPEED, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect21Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) 1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect22Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(!FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatisticProtect23Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(EAU));
        assertTrue(FightSuccess.successChangedStatisticProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, false, new StringList(), data_));
    }

    @Test
    public void successChangedStatistic1Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        assertTrue(FightSuccess.successChangedStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, data_));
    }

    @Test
    public void successChangedStatistic2Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(!FightSuccess.successChangedStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, data_));
    }

    @Test
    public void successChangedStatistic3Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(INFILTRATION);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(FightSuccess.successChangedStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, data_));
    }

    @Test
    public void successChangedStatistic4Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(INFILTRATION);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        assertTrue(!FightSuccess.successChangedStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, Statistic.ATTACK, (byte) -1, data_));
    }

    @Test
    public void successChangedStatistic5Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(!FightSuccess.successChangedStatistic(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, Statistic.ATTACK, (byte) -1, data_));
    }

    @Test
    public void successfulChangedBoostedStatistics1Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        MoveData move_ = data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        IdList<Statistic> stats_ = FightSuccess.successfulChangedBoostedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(1, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulChangedBoostedStatistics2Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        MoveData move_ = data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        IdList<Statistic> stats_ = FightSuccess.successfulChangedBoostedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(0, stats_.size());
    }

    @Test
    public void successfulChangedBoostedStatistics3Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        MoveData move_ = data_.getMove(ACUPRESSION);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        IdList<Statistic> stats_ = FightSuccess.successfulChangedBoostedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(7, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_ATTACK));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPEED));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
        assertTrue(stats_.containsObj(Statistic.EVASINESS));
    }

    @Test
    public void successfulChangedStatistics1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        MoveData move_ = data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(1, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulChangedStatistics2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        MoveData move_ = data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(0, stats_.size());
    }

    @Test
    public void successfulChangedStatistics3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        MoveData move_ = data_.getMove(COUD_BOUE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(1+move_.indexOfPrimaryEffect());
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(0, stats_.size());
    }

    @Test
    public void successfulChangedStatistics4Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        MoveData move_ = data_.getMove(MUR_DE_FER);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(1, stats_.size());
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
    }

    @Test
    public void successfulChangedStatistics5Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = data_.getMove(DEGOMMAGE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(3, stats_.size());
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPEED));
    }

    @Test
    public void successfulChangedStatistics6Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        MoveData move_ = data_.getMove(BOOST);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(3, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
        assertTrue(stats_.containsObj(Statistic.EVASINESS));
    }

    @Test
    public void successfulChangedStatistics7Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = data_.getMove(BUEE_NOIRE);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
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
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = data_.getMove(PERMUCOEUR);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(5, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_ATTACK));
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_DEFENSE));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulChangedStatistics9Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = data_.getMove(PERMUCOEUR);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(3, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_ATTACK));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulChangedStatistics10Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) 1);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) 1);
        MoveData move_ = data_.getMove(PERMUCOEUR);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(3, stats_.size());
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_DEFENSE));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulChangedStatistics11Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ATTACK, (byte) 3);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPEED, (byte) -1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.SPECIAL_DEFENSE, (byte) -1);
        MoveData move_ = data_.getMove(PERMUCOEUR);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(3, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_ATTACK));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulChangedStatistics12Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        MoveData move_ = data_.getMove(TROU_BIS);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().first();
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(1, stats_.size());
    }

    @Test
    public void successfulChangedStatistics13Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ACCURACY, (byte) 3);
        MoveData move_ = data_.getMove(PERMUCOEUR);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffects().last();
        IdList<Statistic> stats_ = FightSuccess.successfulChangedStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_);
        assertEq(5, stats_.size());
        assertTrue(stats_.containsObj(Statistic.ATTACK));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_ATTACK));
        assertTrue(stats_.containsObj(Statistic.DEFENSE));
        assertTrue(stats_.containsObj(Statistic.SPECIAL_DEFENSE));
        assertTrue(stats_.containsObj(Statistic.ACCURACY));
    }

    @Test
    public void successfulAffectedStatusProtect1Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.enableGlobalMove(BROUHAHA);
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), data_));
    }

    @Test
    public void successfulAffectedStatusProtect2Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), data_));
    }

    @Test
    public void successfulAffectedStatusProtect3Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.getFoeTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), data_));
    }

    @Test
    public void successfulAffectedStatusProtect4Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.getFoeTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(RUNE_PROTECT), data_));
    }

    @Test
    public void successfulAffectedStatusProtect5Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, BRULURE, false, new StringList(RUNE_PROTECT), data_));
    }

    @Test
    public void successfulAffectedStatusProtect6Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, BRULURE, false, new StringList(RUNE_PROTECT), data_));
    }

    @Test
    public void successfulAffectedStatusProtect7Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, BRULURE, true, new StringList(RUNE_PROTECT), data_));
    }

    @Test
    public void successfulAffectedStatusProtect8Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(RUNE_PROTECT), data_));
    }

    @Test
    public void successfulAffectedStatusProtect9Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, BRULURE, false, new StringList(RUNE_PROTECT), data_));
    }

    @Test
    public void successfulAffectedStatusProtect10Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(GARDE_MAGIK);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(RUNE_PROTECT), data_));
    }

    @Test
    public void successfulAffectedStatusProtect11Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(RUNE_PROTECT), data_));
    }

    @Test
    public void successfulAffectedStatusProtect12Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(RUNE_PROTECT), data_));
    }

    @Test
    public void successfulAffectedStatusProtect13Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HERBE_MENTAL);
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, AMOUR, false, new StringList(RUNE_PROTECT), data_));
    }

    @Test
    public void successfulAffectedStatusProtect14Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HERBE_MENTAL);
        fight_.getFoeTeam().activerEffetEquipe(TOUR_RAPIDE);
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, AMOUR, false, new StringList(RUNE_PROTECT), data_));
    }

    @Test
    public void successfulAffectedStatusProtect15Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(EAU));
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), data_));
    }

    @Test
    public void successfulAffectedStatusProtect16Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), data_));
    }

    @Test
    public void successfulAffectedStatusProtect17Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, BRULURE, false, new StringList(), data_));
    }

    @Test
    public void successfulAffectedStatusProtect18Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(EAU));
        assertTrue(FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), data_));
    }

    @Test
    public void successfulAffectedStatusProtect19Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(FLORA_VOILE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setTypes(new StringList(PLANTE));
        assertTrue(!FightSuccess.successfulAffectedStatusProtect(fight_,POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, false, new StringList(), data_));
    }

    @Test
    public void successfulAffectedStatus1Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(HERBE_MENTAL);
        assertTrue(!FightSuccess.successfulAffectedStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, AMOUR, data_));
    }

    @Test
    public void successfulAffectedStatus2Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).backUpObject(HERBE_MENTAL);
        assertTrue(FightSuccess.successfulAffectedStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, AMOUR, data_));
    }

    @Test
    public void successfulAffectedStatus3Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(!FightSuccess.successfulAffectedStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, data_));
    }

    @Test
    public void successfulAffectedStatus4Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(INFILTRATION);
        fight_.getFoeTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(FightSuccess.successfulAffectedStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SOMMEIL, data_));
    }

    @Test
    public void successfulAffectedStatus5Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(INFILTRATION);
        fight_.getUserTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(!FightSuccess.successfulAffectedStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, SOMMEIL, data_));
    }

    @Test
    public void successfulAffectedStatus6Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.disableGlobalMove(ZENITH);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(INFILTRATION);
        fight_.getUserTeam().activerEffetEquipe(RUNE_PROTECT);
        assertTrue(FightSuccess.successfulAffectedStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ONE, AMOUR_TRES_MOU, data_));
    }

    private static Fight rateEffAgainstTargetMoveRoad(DataBase _data) {
        Fight fight_ = rateEffAgainstTargetMove(_data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void successfulEffectStatus1Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        MoveData move_ = data_.getMove(ECHANGE_PSY);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().last();
        assertTrue(!FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successfulEffectStatus2Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(BRULURE);
        fight_.getFoeTeam().activerEffetEquipe(RUNE_PROTECT);
        MoveData move_ = data_.getMove(ECHANGE_PSY);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().last();
        assertTrue(!FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successfulEffectStatus3Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(BRULURE);
        MoveData move_ = data_.getMove(ECHANGE_PSY);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().last();
        assertTrue(FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successfulEffectStatus4Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        MoveData move_ = data_.getMove(REPOS);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successfulEffectStatus5Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        MoveData move_ = data_.getMove(DANSE_LUNE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successfulEffectStatus6Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        MoveData move_ = data_.getMove(BERCEUSE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successfulEffectStatus7Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.enableGlobalMove(BROUHAHA);
        MoveData move_ = data_.getMove(BERCEUSE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(!FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successfulEffectStatus8Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        MoveData move_ = data_.getMove(BERCEUSE);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(!FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successfulEffectStatus9Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).creerClone(new Rate("1/2"));
        MoveData move_ = data_.getMove(PSYKOUD_BOUL);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().last();
        assertTrue(!FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successfulEffectStatus10Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        MoveData move_ = data_.getMove(DODO);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successfulEffectStatus11Test() {
        DataBase data_ = initDb();
        Fight fight_ = rateEffAgainstTargetMoveRoad(data_);
        MoveData move_ = data_.getMove(DODO_PETIT);
        EffectStatus eff_ = (EffectStatus) move_.getEffects().first();
        assertTrue(FightSuccess.successfulEffectStatus(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successEffect1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(MUR_DE_FER);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        Effect eff_ = data_.getMove(MUR_DE_FER).getEffects().first();
        assertTrue(FightSuccess.successEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successEffect2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(BERCEUSE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        Effect eff_ = data_.getMove(BERCEUSE).getEffects().first();
        assertTrue(FightSuccess.successEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successEffect3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(MUR_DE_FER);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.DEFENSE, (byte) 6);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        Effect eff_ = data_.getMove(MUR_DE_FER).getEffects().first();
        assertTrue(!FightSuccess.successEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successEffect4Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(BERCEUSE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        Effect eff_ = data_.getMove(BERCEUSE).getEffects().first();
        assertTrue(!FightSuccess.successEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successEffect5Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(BERCEUSE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        Effect eff_ = data_.getMove(PHOTOCOPIE).getEffects().first();
        assertTrue(!FightSuccess.successEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successEffect6Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(BERCEUSE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterStatut(SOMMEIL);
        FightRound.initRound(fight_);
        Effect eff_ = data_.getMove(METRONOME).getEffects().first();
        assertTrue(FightSuccess.successEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data_));
    }

    @Test
    public void successfulMove1Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(LEVITATION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, 0, true, data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove2Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
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
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, PICOTS, 0, true, data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove3Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, SEISME, 0, true, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove4Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(VAMPIPOING,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAMPIPOING, 0, true, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0, POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAMPIPOING, 1, true, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove5Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(VAMPIPOING,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAMPIPOING, 0, true, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0, POKEMON_FOE_FIGHTER_ZERO), BoolVal.FALSE);
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAMPIPOING, 1, true, data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove6Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
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
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAMPIPOING, 0, true, data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove7Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
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
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, SOIN, 0, true, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove8Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(DEVOREVE,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, DEVOREVE, 0, true, data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove9Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
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
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, DEVOREVE, 0, true, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove10Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(LEVITATION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, PICOTS, 0, true, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove11Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
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
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, PICOTS, 0, true, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove12Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
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
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, PIEGE_DE_ROC, 0, true, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove13Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
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
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0, POKEMON_FOE_FIGHTER_ZERO), BoolVal.FALSE);
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, VAMPIPOING, 1, false, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove14Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
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
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, MUR_DE_FER, 0, false, data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove15Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(TOUR_RAPIDE,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TOUR_RAPIDE, 0, true, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        fight_.getSuccessfulEffects().put(new NbEffectFighterCoords(0, POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TOUR_RAPIDE, 1, true, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove16Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(TOUR_RAPIDE,POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        RandomBoolResults pair_;
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TOUR_RAPIDE, 0, true, data_);
        assertTrue(pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).successUsingMove();
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TOUR_RAPIDE, 1, true, data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove17Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
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
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, 0, true, data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(!pair_.isEffectIfFail());
    }

    @Test
    public void successfulMove18Test() {
        DataBase data_ = initDb();
        Fight fight_ = successfulEffectWhileIfTargetIsNotThrower(data_);
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
        pair_ = FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROUE_DE_FEU, 0, true, data_);
        assertTrue(!pair_.isSuccessful());
        assertTrue(pair_.isEffectIfFail());
    }

    private static Fight canUseDirectlyMove(DataBase _data) {
        Difficulty diff_ = new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false, _data);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(diff_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(diff_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void canUseDirectlyMove1Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseDirectlyMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(RONFLEMENT, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.canUseDirectlyMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void canUseDirectlyMove2Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseDirectlyMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(COUD_KRANE);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.canUseDirectlyMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void canUseDirectlyMove3Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseDirectlyMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(COUD_KRANE);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.canUseDirectlyMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void canUseDirectlyMove4Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseDirectlyMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(COUD_KRANE);
        FightRound.initRound(fight_);
        assertTrue(!FightSuccess.canUseDirectlyMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void canUseDirectlyMove5Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseDirectlyMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HERBE_POUV);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(COUD_KRANE);
        FightRound.initRound(fight_);
        assertTrue(FightSuccess.canUseDirectlyMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void canSkipRecharge1Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseDirectlyMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertTrue(!FightSuccess.canSkipRecharge(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void canSkipRecharge2Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseDirectlyMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(METEO);
        assertTrue(!FightSuccess.canSkipRecharge(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void canSkipRecharge3Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseDirectlyMove(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(SANS_LIMITE);
        assertTrue(FightSuccess.canSkipRecharge(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void accuracy1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        assertEq(new Rate("0"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ABIME, data_));
    }

    @Test
    public void accuracy2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, data_));
    }

    @Test
    public void accuracy3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.enableGlobalMove(GRAVITE);
        assertEq(new Rate("8/5"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, data_));
    }

    @Test
    public void accuracy4Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(new Rate("9/10"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROULADE, data_));
    }

    @Test
    public void accuracy5Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ATTENTION);
        assertEq(new Rate("9/10"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROULADE, data_));
    }

    @Test
    public void accuracy6Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ANNULE_GARDE);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROULADE, data_));
    }

    @Test
    public void accuracy7Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(DETECTION, COPIE, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ANNULE_GARDE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        assertEq(new Rate("9/10"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, ROULADE, data_));
    }

    @Test
    public void accuracy8Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ACCURACY, (byte) -1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ANNULE_GARDE);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, data_));
    }

    @Test
    public void accuracy9Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ACCURACY, (byte) -1);
        assertEq(new Rate("2/3"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, data_));
    }

    @Test
    public void accuracy10Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.ACCURACY, (byte) -1);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COPIE_TYPE, data_));
    }

    @Test
    public void accuracy11Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.EVASINESS, (byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ANNULE_GARDE);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, data_));
    }

    @Test
    public void accuracy12Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.EVASINESS, (byte) 1);
        assertEq(new Rate("2/3"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, TONNERRE, data_));
    }

    @Test
    public void accuracy13Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.EVASINESS, (byte) 1);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, COPIE_TYPE, data_));
    }

    @Test
    public void accuracy14Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).variationBoostStatistique(Statistic.EVASINESS, (byte) 1);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_PLAYER_FIGHTER_ZERO, RELAIS, data_));
    }

    @Test
    public void accuracy15Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PEAU_MIRACLE);
        assertEq(new Rate("11/40"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BERCEUSE, data_));
    }

    @Test
    public void accuracy16Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(new Rate("11/20"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BERCEUSE, data_));
    }

    @Test
    public void accuracy17Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(PEAU_MIRACLE_BIS);
        assertEq(new Rate("1"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BERCEUSE, data_));
    }

    @Test
    public void accuracy18Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PEAU_MIRACLE_TER);
        assertEq(new Rate("0"), FightSuccess.accuracy(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, BERCEUSE, data_));
    }

    @Test
    public void rateCriticalHit1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(NULL_REF);
        assertEq(new Rate("1/16"), FightSuccess.rateCriticalHit(fight_, fighter_, (byte) 0, data_));
    }

    @Test
    public void rateCriticalHit2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        assertEq(new Rate("1/16"), FightSuccess.rateCriticalHit(fight_, fighter_, (byte) 0, data_));
    }

    @Test
    public void rateCriticalHit3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(CHANCEUX);
        assertEq(new Rate("1/8"), FightSuccess.rateCriticalHit(fight_, fighter_, (byte) 0, data_));
    }

    @Test
    public void multProbaByComboOfMoves1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        assertEq(new Rate("1"),FightSuccess.multProbaByComboOfMoves(fight_, Fight.CST_PLAYER, data_));
    }

    @Test
    public void multProbaByComboOfMoves2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        assertEq(new Rate("1"),FightSuccess.multProbaByComboOfMoves(fight_, Fight.CST_PLAYER, data_));
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        assertEq(new Rate("4"),FightSuccess.multProbaByComboOfMoves(fight_, Fight.CST_PLAYER, data_));
    }

    @Test
    public void multProbaByComboOfMoves3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        assertEq(new Rate("2"),FightSuccess.multProbaByComboOfMoves(fight_, Fight.CST_PLAYER, data_));
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        assertEq(new Rate("4"),FightSuccess.multProbaByComboOfMoves(fight_, Fight.CST_PLAYER, data_));
    }

    @Test
    public void probaEffectStatistic1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(SERENITE);
        assertEq(new Rate("1/2"), FightSuccess.probaEffectStatistic(fight_, fighter_, new Rate("1/2"), true, data_));
    }

    @Test
    public void probaEffectStatistic2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(NULL_REF);
        assertEq(new Rate("1/2"), FightSuccess.probaEffectStatistic(fight_, fighter_, new Rate("1/2"), false, data_));
    }

    @Test
    public void probaEffectStatistic3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        assertEq(new Rate("1/2"), FightSuccess.probaEffectStatistic(fight_, fighter_, new Rate("1/2"), false, data_));
    }

    @Test
    public void probaEffectStatistic4Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(SERENITE);
        assertEq(new Rate("1"), FightSuccess.probaEffectStatistic(fight_, fighter_, new Rate("1/2"), false, data_));
    }

    @Test
    public void probaEffectStatistic5Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        assertEq(new Rate("1"), FightSuccess.probaEffectStatistic(fight_, fighter_, new Rate("1/2"), false, data_));
    }

    @Test
    public void probaEffectStatistic6Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        assertEq(new Rate("2"), FightSuccess.probaEffectStatistic(fight_, fighter_, new Rate("1/2"), false, data_));
    }

    @Test
    public void probaEffectStatistic7Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        MoveData move_ = data_.getMove(MUR_DE_FER);
        EffectStatistic eff_ = (EffectStatistic) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("1"), FightSuccess.probaEffectStatistic(fight_, fighter_, eff_, false, data_));
    }

    @Test
    public void probaEffectStatus1Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, LgInt.one());
        MonteCarloString res_ = FightSuccess.probaEffectStatus(fight_, fighter_, law_, data_);
        assertEq(1, res_.nbEvents());
        assertTrue(res_.containsEvent(NULL_REF));
    }

    @Test
    public void probaEffectStatus2Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(NULL_REF);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, LgInt.one());
        law_.addQuickEvent(BRULURE, LgInt.one());
        MonteCarloString res_ = FightSuccess.probaEffectStatus(fight_, fighter_, law_, data_);
        assertEq(2, res_.nbEvents());
        assertTrue(res_.containsEvent(NULL_REF));
        assertTrue(res_.containsEvent(BRULURE));
    }

    @Test
    public void probaEffectStatus3Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, LgInt.one());
        law_.addQuickEvent(BRULURE, LgInt.one());
        MonteCarloString res_ = FightSuccess.probaEffectStatus(fight_, fighter_, law_, data_);
        assertEq(2, res_.nbEvents());
        assertTrue(res_.containsEvent(NULL_REF));
        assertTrue(res_.containsEvent(BRULURE));
    }

    @Test
    public void probaEffectStatus4Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(SERENITE);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, LgInt.one());
        law_.addQuickEvent(BRULURE, LgInt.one());
        MonteCarloString res_ = FightSuccess.probaEffectStatus(fight_, fighter_, law_, data_);
        assertEq(1, res_.nbEvents());
        assertTrue(res_.containsEvent(BRULURE));
    }

    @Test
    public void probaEffectStatus5Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(METEO);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, new LgInt("3"));
        law_.addQuickEvent(BRULURE, LgInt.one());
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        MonteCarloString res_ = FightSuccess.probaEffectStatus(fight_, fighter_, law_, data_);
        assertEq(1, res_.nbEvents());
        assertTrue(res_.containsEvent(BRULURE));
    }

    @Test
    public void probaEffectStatus6Test() {
        DataBase data_ = initDb();
        Fight fight_ = sufferingDamageTypes(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(SERENITE);
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent(NULL_REF, new LgInt("3"));
        law_.addQuickEvent(BRULURE, LgInt.one());
        MonteCarloString res_ = FightSuccess.probaEffectStatus(fight_, fighter_, law_, data_);
        assertEq(2, res_.nbEvents());
        assertTrue(res_.containsEvent(NULL_REF));
        assertTrue(res_.containsEvent(BRULURE));
        assertEq(new LgInt("2"),res_.rate(NULL_REF));
        assertEq(new LgInt("2"),res_.rate(BRULURE));
    }
}
