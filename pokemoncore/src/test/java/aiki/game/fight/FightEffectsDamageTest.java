package aiki.game.fight;
import static aiki.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectDamage;
import aiki.game.fight.animations.AnimationEffectDamage;
import aiki.game.fight.enums.UsefulValueLaw;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.player.Player;
import aiki.map.characters.GymLeader;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.EnumMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class FightEffectsDamageTest extends InitializationDataBase {

    private static Fight randomRate() {
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
    public void randomRate1Test() {
        Fight fight_ = randomRate();
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("2"), LgInt.one());
        assertEq(new Rate("2"),FightEffects.randomRate(fight_, _data_,law_, POKEMON_FOE_FIGHTER_ZERO));
    }

    @Test
    public void randomRate2Test() {
        Fight fight_ = randomRate();
        assertTrue(FightEffects.randomRate(fight_, _data_, new Rate("1"), POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void randomRate1SimulationTest() {
        Fight fight_ = randomRate();
        fight_.setSimulation(true);
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1"), LgInt.one());
        law_.addEvent(new Rate("2"), LgInt.one());
        assertEq(new Rate("1"),FightEffects.randomRate(fight_, _data_,law_, POKEMON_FOE_FIGHTER_ZERO));
    }

    @Test
    public void randomRate2SimulationTest() {
        Fight fight_ = randomRate();
        fight_.setSimulation(true);
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate("1"), LgInt.one());
        law_.addEvent(new Rate("2"), LgInt.one());
        assertEq(new Rate("2"),FightEffects.randomRate(fight_, _data_,law_, POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void randomRate3SimulationTest() {
        Fight fight_ = randomRate();
        fight_.setSimulation(true);
        assertTrue(FightEffects.randomRate(fight_, _data_, new Rate("1"), POKEMON_FOE_FIGHTER_ZERO));
    }

    @Test
    public void randomRate4SimulationTest() {
        Fight fight_ = randomRate();
        fight_.setSimulation(true);
        assertTrue(FightEffects.randomRate(fight_, _data_, new Rate("1"), POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void randomRate5SimulationTest() {
        Fight fight_ = randomRate();
        fight_.setSimulation(true);
        assertTrue(!FightEffects.randomRate(fight_, _data_, new Rate("1/2"), POKEMON_PLAYER_FIGHTER_ZERO));
    }

    private static Fight multBasePower() {
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
    public void multBaseDamage1Test() {
        Fight fight_ = multBasePower();
        assertEq(new Rate("1"),FightEffects.multBaseDamage(fight_, ANTI_AIR, _data_));
    }

    @Test
    public void multBaseDamage2Test() {
        Fight fight_ = multBasePower();
        fight_.enableGlobalMove(GRAVITE);
        assertEq(new Rate("2"),FightEffects.multBaseDamage(fight_, ANTI_AIR, _data_));
    }

    private static StringMap<String> getBasicValues(Fight _fight, TeamPosition _thrower, TeamPosition _target) {
        StringMap<String> variables_;
        variables_ = FightValues.calculateValues(_fight, _thrower, _target,_data_);
        return variables_;
    }

    private static StringMap<String> getValues(Fight _fight, TeamPosition _thrower, TeamPosition _target, String _move,Rate _power) {
        Fighter fighter_ = _fight.getFighter(_thrower);
        StringMap<String> variables_;
        variables_ = FightValues.calculateValues(_fight, _thrower, _target,_data_);
        variables_.putAllMap(getBasicValues(_fight, _thrower, _target));
        MoveData move_ = _data_.getMove(_move);
        StringList typeAtt_=FightMoves.moveTypes(_fight, _thrower,_move,_data_);
        String nomActuelLanceur_=fighter_.getCurrentName();
        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.ATTAQUE_CATEGORIE), move_.getCategory());
        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_NOM), nomActuelLanceur_);
        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.ATTAQUE_TYPES), typeAtt_.join(_data_.getSepartorSetChar()));
        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.ATTAQUE_NOM), _move);
        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PUISSANCE_BASE), _power.toNumberString());
        return variables_;
    }

    private static Fight rateObjectPower() {
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
    public void rateObjectPower1Test() {
        Fight fight_ = rateObjectPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("20"));
        assertEq(new Rate("1"), FightEffects.rateObjectPower(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateObjectPower2Test() {
        Fight fight_ = rateObjectPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BOUE_NOIRE);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("20"));
        assertEq(new Rate("1"), FightEffects.rateObjectPower(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateObjectPower3Test() {
        Fight fight_ = rateObjectPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(ENCENS_VAGUE);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("20"));
        assertEq(new Rate("1"), FightEffects.rateObjectPower(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateObjectPower4Test() {
        Fight fight_ = rateObjectPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(ENCENS_VAGUE);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, EBULLITION, new Rate("20"));
        assertEq(new Rate("11/10"), FightEffects.rateObjectPower(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateObjectPower5Test() {
        Fight fight_ = rateObjectPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BAIE_MEPO);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("20"));
        assertEq(new Rate("1"), FightEffects.rateObjectPower(fight_, thrower_, variables_, _data_));
    }

    private static Fight rateAbilityPower() {
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
    public void rateAbilityPower1Test() {
        Fight fight_ = rateAbilityPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("20"));
        assertEq(new Rate("1"), FightEffects.rateAbilityPower(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateAbilityPower2Test() {
        Fight fight_ = rateAbilityPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(METEO);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("20"));
        assertEq(new Rate("1"), FightEffects.rateAbilityPower(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateAbilityPower3Test() {
        Fight fight_ = rateAbilityPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(SANS_LIMITE);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("20"));
        assertEq(new Rate("13/10"), FightEffects.rateAbilityPower(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateAbilityPower4Test() {
        Fight fight_ = rateAbilityPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PEAU_FEERIQUE);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, COMBO_GRIFFE, new Rate("20"));
        assertEq(new Rate("3/2"), FightEffects.rateAbilityPower(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateAbilityPower5Test() {
        Fight fight_ = rateAbilityPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(METEO);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, COMBO_GRIFFE, new Rate("20"));
        assertEq(new Rate("1"), FightEffects.rateAbilityPower(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateAbilityPower6Test() {
        Fight fight_ = rateAbilityPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PEAU_FEERIQUE);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, PISTOLET_A_O, new Rate("20"));
        assertEq(new Rate("1"), FightEffects.rateAbilityPower(fight_, thrower_, variables_, _data_));
    }

    private static Fight rateTypesPower() {
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
    public void rateTypesPower1Test() {
        Fight fight_ = rateTypesPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertEq(new Rate("1"), FightEffects.rateTypesPower(fight_, thrower_, target_, new StringList(EAU)));
    }

    @Test
    public void rateTypesPower2Test() {
        Fight fight_ = rateTypesPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertEq(new Rate("1"), FightEffects.rateTypesPower(fight_, thrower_, target_, new StringList(ELECTRIQUE)));
    }

    @Test
    public void rateTypesPower3Test() {
        Fight fight_ = rateTypesPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE).multiplyBy(new Rate("2"));
        assertEq(new Rate("2"), FightEffects.rateTypesPower(fight_, thrower_, target_, new StringList(ELECTRIQUE)));
    }

    @Test
    public void rateTypesPower4Test() {
        Fight fight_ = rateTypesPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.getDamageRateSufferedByType().getVal(FEU).multiplyBy(new Rate("1/2"));
        assertEq(new Rate("1/2"), FightEffects.rateTypesPower(fight_, thrower_, target_, new StringList(FEU)));
    }

    @Test
    public void rateTypesPower5Test() {
        Fight fight_ = rateTypesPower();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE).multiplyBy(new Rate("2"));
        fighter_ = fight_.getFighter(target_);
        fighter_.getDamageRateSufferedByType().getVal(FEU).multiplyBy(new Rate("1/2"));
        assertEq(new Rate("1"), FightEffects.rateTypesPower(fight_, thrower_, target_, new StringList(ELECTRIQUE,FEU)));
    }

    private static Fight attack() {
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
    public void attack1Test() {
        Fight fight_ = attack();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, PARADOXE, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) 1);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(PARADOXE);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("101/10"),FightEffects.attack(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    @Test
    public void attack2Test() {
        Fight fight_ = attack();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 1);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        fighter_.backUpObject(NULL_REF);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(DON_NATUREL);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("2679/200"),FightEffects.attack(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    @Test
    public void attack3Test() {
        Fight fight_ = attack();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 1);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(INCONSCIENT);
        fighter_.backUpObject(NULL_REF);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(DON_NATUREL);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("893/100"),FightEffects.attack(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    @Test
    public void attack4Test() {
        Fight fight_ = attack();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) -1);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(DON_NATUREL);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("893/150"),FightEffects.attack(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    @Test
    public void attack5Test() {
        Fight fight_ = attack();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, BATAILLE, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) -1);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(BATAILLE);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("893/100"),FightEffects.attack(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    @Test
    public void attack6Test() {
        Fight fight_ = attack();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, BATAILLE, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) -1);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(INCONSCIENT);
        fighter_.backUpObject(NULL_REF);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(BATAILLE);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("893/100"),FightEffects.attack(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    @Test
    public void attack7Test() {
        Fight fight_ = attack();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, BATAILLE, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 1);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(BATAILLE);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("2679/200"),FightEffects.attack(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    private static Fight defense() {
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
    public void defense1Test() {
        Fight fight_ = defense();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, PARADOXE, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) -1);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(PARADOXE);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("893/100"),FightEffects.defense(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    @Test
    public void defense2Test() {
        Fight fight_ = defense();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(METEO);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) 1);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(DON_NATUREL);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("321/20"),FightEffects.defense(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    @Test
    public void defense3Test() {
        Fight fight_ = defense();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(INCONSCIENT);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) 1);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(DON_NATUREL);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("107/10"),FightEffects.defense(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    @Test
    public void defense4Test() {
        Fight fight_ = defense();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) 1);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(DON_NATUREL);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("321/20"),FightEffects.defense(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    @Test
    public void defense5Test() {
        Fight fight_ = defense();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, ATTRITION, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) 1);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(ATTRITION);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("107/10"),FightEffects.defense(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    @Test
    public void defense6Test() {
        Fight fight_ = defense();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, ATTRITION, new Rate("60"));
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.DEFENSE, (byte) -1);
        DamagingMoveData move_ = (DamagingMoveData) _data_.getMove(ATTRITION);
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        assertEq(new Rate("107/15"),FightEffects.defense(fight_, thrower_, target_, eff_, variables_, _data_));
    }

    private static Fight ratePartnerMove() {
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
    public void ratePartnerMove1Test() {
        Fight fight_ = ratePartnerMove();
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"),FightEffects.ratePartnerMove(fighter_, _data_));
    }

    @Test
    public void ratePartnerMove2Test() {
        Fight fight_ = ratePartnerMove();
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, true);
        assertEq(new Rate("3/2"),FightEffects.ratePartnerMove(fighter_, _data_));
    }

    @Test
    public void ratePartnerMove3Test() {
        Fight fight_ = ratePartnerMove();
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.getEnabledMovesForAlly().put(AIDE, true);
        assertEq(new Rate("1"),FightEffects.ratePartnerMove(fighter_, _data_));
    }

    private static Fight rateDamageTargetAbility() {
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
    public void rateDamageTargetAbility1Test() {
        Fight fight_ = rateDamageTargetAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        assertEq(new Rate("1"),FightEffects.rateDamageTargetAbility(fight_, thrower_, target_, new StringList(EAU), new Rate("1"), _data_));
    }

    @Test
    public void rateDamageTargetAbility2Test() {
        Fight fight_ = rateDamageTargetAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        fighter_.backUpObject(NULL_REF);
        assertEq(new Rate("1"),FightEffects.rateDamageTargetAbility(fight_, thrower_, target_, new StringList(EAU), new Rate("1"), _data_));
    }

    @Test
    public void rateDamageTargetAbility3Test() {
        Fight fight_ = rateDamageTargetAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(IGNIFUGE);
        fighter_.backUpObject(NULL_REF);
        assertEq(new Rate("1/2"),FightEffects.rateDamageTargetAbility(fight_, thrower_, target_, new StringList(FEU), new Rate("2"), _data_));
    }

    @Test
    public void rateDamageTargetAbility4Test() {
        Fight fight_ = rateDamageTargetAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SOLIDE_ROC);
        fighter_.backUpObject(NULL_REF);
        assertEq(new Rate("3/4"),FightEffects.rateDamageTargetAbility(fight_, thrower_, target_, new StringList(FEU), new Rate("2"), _data_));
    }

    @Test
    public void rateDamageTargetAbility5Test() {
        Fight fight_ = rateDamageTargetAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SOLIDE_ROC);
        fighter_.backUpObject(NULL_REF);
        assertEq(new Rate("1"),FightEffects.rateDamageTargetAbility(fight_, thrower_, target_, new StringList(FEU), new Rate("1"), _data_));
    }

    private static Fight rateDamageTargetTeamMoves() {
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
    public void rateDamageTargetTeamMoves1Test() {
        Fight fight_ = rateDamageTargetTeamMoves();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertEq(new Rate("1"), FightEffects.rateDamageTargetTeamMoves(fight_, thrower_, target_, DON_NATUREL, _data_));
    }

    @Test
    public void rateDamageTargetTeamMoves2Test() {
        Fight fight_ = rateDamageTargetTeamMoves();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(METEO);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(MUR_LUMIERE);
        assertEq(new Rate("3/4"), FightEffects.rateDamageTargetTeamMoves(fight_, thrower_, target_, TONNERRE, _data_));
    }

    @Test
    public void rateDamageTargetTeamMoves3Test() {
        Fight fight_ = rateDamageTargetTeamMoves();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(INFILTRATION);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(MUR_LUMIERE);
        assertEq(new Rate("1"), FightEffects.rateDamageTargetTeamMoves(fight_, thrower_, target_, TONNERRE, _data_));
    }

    @Test
    public void rateDamageTargetTeamMoves4Test() {
        Fight fight_ = rateDamageTargetTeamMoves();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(INFILTRATION);
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(TOUR_RAPIDE);
        assertEq(new Rate("1"), FightEffects.rateDamageTargetTeamMoves(fight_, thrower_, target_, TONNERRE, _data_));
    }

    private static Fight rateDamageGlobalAbilities() {
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
    public void rateDamageGlobalAbilities1Test() {
        Fight fight_ = rateDamageGlobalAbilities();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        StringList types_ = new StringList();
        types_.add(EAU);
        assertEq(new Rate("1"), FightEffects.rateDamageGlobalAbilities(fight_, types_, _data_));
    }

    @Test
    public void rateDamageGlobalAbilities2Test() {
        Fight fight_ = rateDamageGlobalAbilities();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(AURA_TENEBREUSE);
        StringList types_ = new StringList();
        types_.add(EAU);
        assertEq(new Rate("1"), FightEffects.rateDamageGlobalAbilities(fight_, types_, _data_));
    }

    @Test
    public void rateDamageGlobalAbilities3Test() {
        Fight fight_ = rateDamageGlobalAbilities();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(AURA_TENEBREUSE);
        StringList types_ = new StringList();
        types_.add(TENEBRE);
        assertEq(new Rate("3/2"), FightEffects.rateDamageGlobalAbilities(fight_, types_, _data_));
    }

    @Test
    public void rateDamageGlobalAbilities4Test() {
        Fight fight_ = rateDamageGlobalAbilities();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(AURA_INVERSEE);
        StringList types_ = new StringList();
        types_.add(TENEBRE);
        assertEq(new Rate("1"), FightEffects.rateDamageGlobalAbilities(fight_, types_, _data_));
    }

    @Test
    public void rateDamageGlobalAbilities5Test() {
        Fight fight_ = rateDamageGlobalAbilities();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(AURA_INVERSEE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(AURA_TENEBREUSE);
        StringList types_ = new StringList();
        types_.add(EAU);
        assertEq(new Rate("1"), FightEffects.rateDamageGlobalAbilities(fight_, types_, _data_));
    }

    @Test
    public void rateDamageGlobalAbilities6Test() {
        Fight fight_ = rateDamageGlobalAbilities();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(AURA_INVERSEE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(AURA_TENEBREUSE);
        StringList types_ = new StringList();
        types_.add(TENEBRE);
        assertEq(new Rate("2/3"), FightEffects.rateDamageGlobalAbilities(fight_, types_, _data_));
    }

    @Test
    public void rateDamageGlobalAbilities7Test() {
        Fight fight_ = rateDamageGlobalAbilities();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(AURA_INVERSEE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(AURA_TENEBREUSE);
        StringList types_ = new StringList();
        types_.add(VOL);
        assertEq(new Rate("1"), FightEffects.rateDamageGlobalAbilities(fight_, types_, _data_));
    }

    private static Fight rateDamageGlobalMoves() {
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
    public void rateDamageGlobalMoves1Test() {
        Fight fight_ = rateDamageGlobalMoves();
        assertTrue(fight_.getEnabledMoves().getVal(ZENITH).isEnabled());
        assertEq(new Rate("1"),FightEffects.rateDamageGlobalMoves(fight_, new StringList(ELECTRIQUE), _data_));
        assertEq(new Rate("3/2"),FightEffects.rateDamageGlobalMoves(fight_, new StringList(FEU), _data_));
        assertEq(new Rate("1/2"),FightEffects.rateDamageGlobalMoves(fight_, new StringList(EAU), _data_));
        assertEq(new Rate("3/4"),FightEffects.rateDamageGlobalMoves(fight_, new StringList(FEU,EAU), _data_));
    }

    private static StringMap<String> getValues(Fight _fight, TeamPosition _thrower, TeamPosition _target, String _move,Rate _power, Rate _efficiency) {
        Fighter fighter_ = _fight.getFighter(_thrower);
        StringMap<String> variables_;
        variables_ = getValues(_fight, _thrower, _target, _move, _power);
        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.COEFF_EFF), _efficiency.toNumberString());
        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.NB_UTILISATION_CONSECUTIF), fighter_.getNbRepeatingSuccessfulMoves().toNumberString());
        return variables_;
    }

    private static Fight rateDamageThrowerObject() {
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
    public void rateDamageThrowerObject1Test() {
        Fight fight_ = rateDamageThrowerObject();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"), new Rate("2"));
        assertEq(new Rate("1"),FightEffects.rateDamageThrowerObject(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateDamageThrowerObject2Test() {
        Fight fight_ = rateDamageThrowerObject();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(BOUE_NOIRE);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"), new Rate("2"));
        assertEq(new Rate("1"),FightEffects.rateDamageThrowerObject(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateDamageThrowerObject3Test() {
        Fight fight_ = rateDamageThrowerObject();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(CEINTURE_PRO);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"), new Rate("2"));
        assertEq(new Rate("6/5"),FightEffects.rateDamageThrowerObject(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateDamageThrowerObject4Test() {
        Fight fight_ = rateDamageThrowerObject();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(CEINTURE_PRO);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"), new Rate("1/2"));
        assertEq(new Rate("1"),FightEffects.rateDamageThrowerObject(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateDamageThrowerObject5Test() {
        Fight fight_ = rateDamageThrowerObject();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(BAIE_MEPO);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"), new Rate("2"));
        assertEq(new Rate("1"),FightEffects.rateDamageThrowerObject(fight_, thrower_, variables_, _data_));
    }

    private static Fight rateDamageThrowerAbility() {
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
    public void rateDamageThrowerAbility1Test() {
        Fight fight_ = rateDamageThrowerAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"), new Rate("2"));
        assertEq(new Rate("1"),FightEffects.rateDamageThrowerAbility(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateDamageThrowerAbility2Test() {
        Fight fight_ = rateDamageThrowerAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(METEO);
        fighter_.backUpObject(NULL_REF);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"), new Rate("2"));
        assertEq(new Rate("1"),FightEffects.rateDamageThrowerAbility(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateDamageThrowerAbility3Test() {
        Fight fight_ = rateDamageThrowerAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(LENTITEINTEE);
        fighter_.backUpObject(NULL_REF);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"), new Rate("2"));
        assertEq(new Rate("1"),FightEffects.rateDamageThrowerAbility(fight_, thrower_, variables_, _data_));
    }

    @Test
    public void rateDamageThrowerAbility4Test() {
        Fight fight_ = rateDamageThrowerAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(LENTITEINTEE);
        fighter_.backUpObject(NULL_REF);
        StringMap<String> variables_ = getValues(fight_, thrower_, target_, DON_NATUREL, new Rate("60"), new Rate("1/2"));
        assertEq(new Rate("2"),FightEffects.rateDamageThrowerAbility(fight_, thrower_, variables_, _data_));
    }

    private static Fight rateDamageInvokedMove(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(FORCE_NATURE, (short) 10);
        moves_.put(MOI_D_ABORD, (short) 10);
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
    public void rateDamageInvokedMove1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = rateDamageInvokedMove(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MOI_D_ABORD, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setActed(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightInvoke.processInvokingMove(fight_, thrower_, diff_, _data_);
        assertTrue(fight_.isSuccessfulInvokation());
        assertEq(new Rate("1"), FightEffects.rateDamageInvokedMove(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageInvokedMove2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = rateDamageInvokedMove(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MOI_D_ABORD, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setActed(false);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightInvoke.processInvokingMove(fight_, thrower_, diff_, _data_);
        assertTrue(fight_.isSuccessfulInvokation());
        assertEq(new Rate("3/2"), FightEffects.rateDamageInvokedMove(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageInvokedMove3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = rateDamageInvokedMove(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setActed(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightInvoke.processInvokingMove(fight_, thrower_, diff_, _data_);
        assertTrue(fight_.isSuccessfulInvokation());
        assertEq(new Rate("1"), FightEffects.rateDamageInvokedMove(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageInvokedMove4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = rateDamageInvokedMove(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setActed(false);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightInvoke.processInvokingMove(fight_, thrower_, diff_, _data_);
        assertTrue(fight_.isSuccessfulInvokation());
        assertEq(new Rate("1"), FightEffects.rateDamageInvokedMove(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageInvokedMove5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Fight fight_ = rateDamageInvokedMove(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(FORCE_NATURE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setActed(false);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        FightInvoke.processInvokingMove(fight_, thrower_, diff_, _data_);
        assertTrue(fight_.isSuccessfulInvokation());
        assertEq(new Rate("1"), FightEffects.rateDamageInvokedMove(fight_, thrower_, target_, _data_));
    }

    private static Fight rateDamageTargetBerry() {
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
    public void rateDamageTargetBerry1Test() {
        Fight fight_ = rateDamageTargetBerry();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        assertEq(new Rate("1"), FightEffects.rateDamageTargetBerry(fight_, thrower_, target_, new StringList(EAU), _data_));
    }

    @Test
    public void rateDamageTargetBerry2Test() {
        Fight fight_ = rateDamageTargetBerry();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        assertEq(new Rate("1"), FightEffects.rateDamageTargetBerry(fight_, thrower_, target_, new StringList(EAU), _data_));
    }

    @Test
    public void rateDamageTargetBerry3Test() {
        Fight fight_ = rateDamageTargetBerry();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(ACIER);
        fighter_.backUpObject(BAIE_LAMPOU);
        assertEq(new Rate("1"), FightEffects.rateDamageTargetBerry(fight_, thrower_, target_, new StringList(TENEBRE), _data_));
    }

    @Test
    public void rateDamageTargetBerry4Test() {
        Fight fight_ = rateDamageTargetBerry();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(PSY);
        fighter_.backUpObject(BAIE_LAMPOU);
        assertEq(new Rate("1/2"), FightEffects.rateDamageTargetBerry(fight_, thrower_, target_, new StringList(TENEBRE), _data_));
    }

    private static Fight rateDamageTargetBeforeUsingMove() {
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
    public void rateDamageTargetBeforeUsingMove1Test() {
        Fight fight_ = rateDamageTargetBeforeUsingMove();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.affectNoRoundBeforeUsingMove();
        assertEq(new Rate("1"), FightEffects.rateDamageTargetBeforeUsingMove(fight_, target_, new StringList(EAU), _data_));
    }

    @Test
    public void rateDamageTargetBeforeUsingMove2Test() {
        Fight fight_ = rateDamageTargetBeforeUsingMove();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.incrementRoundBeforeUsingMove();
        assertEq(new Rate("1"), FightEffects.rateDamageTargetBeforeUsingMove(fight_, target_, new StringList(EAU), _data_));
    }

    @Test
    public void rateDamageTargetBeforeUsingMove3Test() {
        Fight fight_ = rateDamageTargetBeforeUsingMove();
        fight_.enableGlobalMove(ORAGE);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(PLANNEUR,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.incrementRoundBeforeUsingMove();
        assertEq(new Rate("1"), FightEffects.rateDamageTargetBeforeUsingMove(fight_, target_, new StringList(EAU), _data_));
    }

    @Test
    public void rateDamageTargetBeforeUsingMove4Test() {
        Fight fight_ = rateDamageTargetBeforeUsingMove();
        fight_.enableGlobalMove(ORAGE);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMoveTarget(PLANNEUR,POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.incrementRoundBeforeUsingMove();
        assertEq(new Rate("2"), FightEffects.rateDamageTargetBeforeUsingMove(fight_, target_, new StringList(ELECTRIQUE), _data_));
    }

    private static Fight rateDamageThrowerTarget() {
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
    public void rateDamageThrowerTarget1Test() {
        Fight fight_ = rateDamageThrowerTarget();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertEq(new Rate("1"), FightEffects.rateDamageThrowerTarget(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageThrowerTarget2Test() {
        Fight fight_ = rateDamageThrowerTarget();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterPseudoStatut(target_, VAMPIGRAINE);
        assertEq(new Rate("1"), FightEffects.rateDamageThrowerTarget(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageThrowerTarget3Test() {
        Fight fight_ = rateDamageThrowerTarget();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterPseudoStatut(target_, AMOUR);
        assertEq(new Rate("1"), FightEffects.rateDamageThrowerTarget(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageThrowerTarget4Test() {
        Fight fight_ = rateDamageThrowerTarget();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ONE, AMOUR);
        assertEq(new Rate("2"), FightEffects.rateDamageThrowerTarget(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageThrowerTarget5Test() {
        Fight fight_ = rateDamageThrowerTarget();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ONE, AMOUR);
        assertEq(new Rate("1"), FightEffects.rateDamageThrowerTarget(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageThrowerTarget6Test() {
        Fight fight_ = rateDamageThrowerTarget();
        fight_.enableGlobalMove(ORAGE);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterPseudoStatut(POKEMON_PLAYER_FIGHTER_ONE, AMOUR);
        assertEq(new Rate("4"), FightEffects.rateDamageThrowerTarget(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageThrowerTarget7Test() {
        Fight fight_ = rateDamageThrowerTarget();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterPseudoStatut(target_, VAMPIGRAINE);
        assertEq(new Rate("1"), FightEffects.rateDamageThrowerTarget(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageThrowerTarget8Test() {
        Fight fight_ = rateDamageThrowerTarget();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterPseudoStatut(target_, AMOUR_TRES_MOU);
        assertEq(new Rate("1"), FightEffects.rateDamageThrowerTarget(fight_, thrower_, target_, _data_));
    }

    private static Fight rateDamageBoostedTypes() {
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
    public void rateDamageBoostedTypes1Test() {
        Fight fight_ = rateDamageBoostedTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterTypes(SOL);
        assertEq(new Rate("1"),FightEffects.rateDamageBoostedTypes(fight_, thrower_, TONNERRE, _data_));
    }

    @Test
    public void rateDamageBoostedTypes2Test() {
        Fight fight_ = rateDamageBoostedTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setCurrentAbility(NULL_REF);
        assertEq(new Rate("3/2"),FightEffects.rateDamageBoostedTypes(fight_, thrower_, TONNERRE, _data_));
    }

    @Test
    public void rateDamageBoostedTypes3Test() {
        Fight fight_ = rateDamageBoostedTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setCurrentAbility(METEO);
        assertEq(new Rate("3/2"),FightEffects.rateDamageBoostedTypes(fight_, thrower_, TONNERRE, _data_));
    }

    @Test
    public void rateDamageBoostedTypes4Test() {
        Fight fight_ = rateDamageBoostedTypes();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setCurrentAbility(ADAPTABILITE);
        assertEq(new Rate("2"),FightEffects.rateDamageBoostedTypes(fight_, thrower_, TONNERRE, _data_));
    }

    private static Fight rateDamageThrowerTargetAbility() {
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
    public void rateDamageThrowerTargetAbility1Test() {
        Fight fight_ = rateDamageThrowerTargetAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertEq(new Rate("1"), FightEffects.rateDamageThrowerTargetAbility(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageThrowerTargetAbility2Test() {
        Fight fight_ = rateDamageThrowerTargetAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(GARDE_AMIE);
        assertEq(new Rate("1"), FightEffects.rateDamageThrowerTargetAbility(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageThrowerTargetAbility3Test() {
        Fight fight_ = rateDamageThrowerTargetAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        assertEq(new Rate("1"), FightEffects.rateDamageThrowerTargetAbility(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageThrowerTargetAbility4Test() {
        Fight fight_ = rateDamageThrowerTargetAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(GARDE_AMIE);
        assertEq(new Rate("3/4"), FightEffects.rateDamageThrowerTargetAbility(fight_, thrower_, target_, _data_));
    }

    @Test
    public void rateDamageThrowerTargetAbility5Test() {
        Fight fight_ = rateDamageThrowerTargetAbility();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        assertEq(new Rate("1"), FightEffects.rateDamageThrowerTargetAbility(fight_, thrower_, target_, _data_));
    }

    private static Fight calculateDamageBaseWithoutRandom() {
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
    public void calculateDamageBaseWithoutRandom1Test() {
        Fight fight_ = calculateDamageBaseWithoutRandom();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = DRACO_RAGE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertEq(new Rate("40"), FightEffects.calculateDamageBaseWithoutRandom(fight_, thrower_, target_, move_, _data_));
    }

    @Test
    public void calculateDamageBaseWithoutRandom2Test() {
        Fight fight_ = calculateDamageBaseWithoutRandom();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = CROC_FATAL;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        assertEq(new Rate("1"), FightEffects.calculateDamageBaseWithoutRandom(fight_, thrower_, target_, move_, _data_));
    }

    @Test
    public void calculateDamageBaseWithoutRandom3Test() {
        Fight fight_ = calculateDamageBaseWithoutRandom();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String move_ = FULMIFER;
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fighter_.getDamageSufferedCateg().put(PHYSIQUE, new Rate("1"));
        fighter_.getDamageSufferedCateg().put(SPECIALE, new Rate("2"));
        assertEq(new Rate("6"), FightEffects.calculateDamageBaseWithoutRandom(fight_, thrower_, target_, move_, _data_));
    }

    @Test
    public void calculateDamageBaseWithoutRandom4Test() {
        Fight fight_ = calculateDamageBaseWithoutRandom();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String move_ = TONNERRE;
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
//        893/100
//        107/10
        // 893/100 * 100 * ((5+3)/125):107/10
        //== 893 * 8/125 * 10 /107
        //== 893 * 16/25 * 1/107
        //== 14288/25 * 1/107 14288/2 = 7144
        //7144 * 3 = 21432
        //== 14288/2675
        assertEq(new Rate("21432/2675"), FightEffects.calculateDamageBaseWithoutRandom(fight_, thrower_, target_, move_, _data_));
    }

    @Test
    public void calculateDamageBaseWithoutRandom5Test() {
        Fight fight_ = calculateDamageBaseWithoutRandom();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String move_ = RIPOSTE;
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fighter_.getDamageSufferedCateg().put(SPECIALE, new Rate("20"));
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
//        893/100
//        107/10
        // 893/100 * 100 * ((5+3)/125):107/10
        //== 893 * 8/125 * 10 /107
        //== 893 * 16/25 * 1/107
        //== 14288/25 * 1/107 14288/2 = 7144
        //7144 * 3 = 21432
        //== 14288/2675
        assertEq(new Rate("0"), FightEffects.calculateDamageBaseWithoutRandom(fight_, thrower_, target_, move_, _data_));
    }

    private static Fight criticalHitCanHappen() {
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
    public void criticalHitCanHappen1Test() {
        Fight fight_ = criticalHitCanHappen();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertTrue(FightEffects.criticalHitCanHappen(fight_, thrower_, target_, _data_));
    }

    @Test
    public void criticalHitCanHappen2Test() {
        Fight fight_ = criticalHitCanHappen();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        assertTrue(!FightEffects.criticalHitCanHappen(fight_, thrower_, target_, _data_));
    }

    @Test
    public void criticalHitCanHappen3Test() {
        Fight fight_ = criticalHitCanHappen();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(INFILTRATION);
//        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(FightEffects.criticalHitCanHappen(fight_, thrower_, target_, _data_));
    }

    @Test
    public void criticalHitCanHappen4Test() {
        Fight fight_ = criticalHitCanHappen();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(INFILTRATION);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        //fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(!FightEffects.criticalHitCanHappen(fight_, thrower_, target_, _data_));
    }

    @Test
    public void criticalHitCanHappen5Test() {
        Fight fight_ = criticalHitCanHappen();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(INFILTRATION);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(ARMURBASTON);
        assertTrue(!FightEffects.criticalHitCanHappen(fight_, thrower_, target_, _data_));
    }

    @Test
    public void criticalHitCanHappen6Test() {
        Fight fight_ = criticalHitCanHappen();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(ARMURBASTON);
        assertTrue(FightEffects.criticalHitCanHappen(fight_, thrower_, target_, _data_));
    }

    @Test
    public void criticalHitCanHappen7Test() {
        Fight fight_ = criticalHitCanHappen();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        //fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(!FightEffects.criticalHitCanHappen(fight_, thrower_, target_, _data_));
    }

    @Test
    public void criticalHitCanHappen8Test() {
        Fight fight_ = criticalHitCanHappen();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        //fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(FightEffects.criticalHitCanHappen(fight_, thrower_, target_, _data_));
    }

    @Test
    public void criticalHitCanHappen9Test() {
        Fight fight_ = criticalHitCanHappen();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(TOUR_RAPIDE);
        assertTrue(FightEffects.criticalHitCanHappen(fight_, thrower_, target_, _data_));
    }

    private static Fight criticalHitEvent() {
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
    public void criticalHitEvent1Test() {
        Fight fight_ = criticalHitEvent();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_ONE;
        fighter_ = fight_.getFighter(partner_);
        fighter_.setCurrentAbility(NULL_REF);
        assertEq(new Rate("2"),FightEffects.criticalHitEvent(fight_, thrower_, new Rate("2"), _data_));
    }

    @Test
    public void criticalHitEvent2Test() {
        Fight fight_ = criticalHitEvent();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(METEO);
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_ONE;
        fighter_ = fight_.getFighter(partner_);
        fighter_.setCurrentAbility(NULL_REF);
        assertEq(new Rate("2"),FightEffects.criticalHitEvent(fight_, thrower_, new Rate("2"), _data_));
    }

    @Test
    public void criticalHitEvent3Test() {
        Fight fight_ = criticalHitEvent();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(SNIPER);
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_ONE;
        fighter_ = fight_.getFighter(partner_);
        fighter_.setCurrentAbility(NULL_REF);
        assertEq(new Rate("3"),FightEffects.criticalHitEvent(fight_, thrower_, new Rate("2"), _data_));
    }

    @Test
    public void criticalHitEvent4Test() {
        Fight fight_ = criticalHitEvent();
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(SNIPER);
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_ONE;
        fighter_ = fight_.getFighter(partner_);
        fighter_.setCurrentAbility(TIRS);
        assertEq(new Rate("6"),FightEffects.criticalHitEvent(fight_, thrower_, new Rate("2"), _data_));
    }

    private static Fight calculateLawsForDamage(Difficulty _diff) {
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
    public void calculateLawsForDamage1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        Fight fight_ = calculateLawsForDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = DRACO_RAGE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamage(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(1, laws_.getBase().size());
        assertEq(1, laws_.getRandomRate().events().size());
        assertEq(1, laws_.getCriticalHit().size());
        assertEq(1, laws_.getNumberHits().size());
        MonteCarloNumber law_;
        law_ = laws_.getBase().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("40")));
        law_ = laws_.getRandomRate();
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getCriticalHit().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getNumberHits().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
    }

    @Test
    public void calculateLawsForDamage2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        Fight fight_ = calculateLawsForDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = CROC_FATAL;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamage(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(1, laws_.getBase().size());
        assertEq(1, laws_.getRandomRate().events().size());
        assertEq(1, laws_.getCriticalHit().size());
        assertEq(1, laws_.getNumberHits().size());
        MonteCarloNumber law_;
        law_ = laws_.getBase().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("46/5")));
        law_ = laws_.getRandomRate();
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getCriticalHit().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getNumberHits().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
    }

    @Test
    public void calculateLawsForDamage3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        Fight fight_ = calculateLawsForDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = LOTO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamage(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(1, laws_.getBase().size());
        assertEq(1, laws_.getRandomRate().events().size());
        assertEq(1, laws_.getCriticalHit().size());
        assertEq(1, laws_.getNumberHits().size());
        MonteCarloNumber law_;
        law_ = laws_.getBase().getVal(thrower_);
        assertEq(2, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("0")));
        assertTrue(law_.events().containsObj(new Rate("46/5")));
        assertEq(new LgInt("1"),law_.rate(new Rate("0")));
        assertEq(new LgInt("1"),law_.rate(new Rate("46/5")));
        law_ = laws_.getRandomRate();
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getCriticalHit().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getNumberHits().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
    }

    @Test
    public void calculateLawsForDamage4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        Fight fight_ = calculateLawsForDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = FULMIFER;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fighter_.getDamageSufferedCateg().put(PHYSIQUE, new Rate("1"));
        fighter_.getDamageSufferedCateg().put(SPECIALE, new Rate("2"));
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamage(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(1, laws_.getBase().size());
        assertEq(1, laws_.getRandomRate().events().size());
        assertEq(1, laws_.getCriticalHit().size());
        assertEq(1, laws_.getNumberHits().size());
        MonteCarloNumber law_;
        law_ = laws_.getBase().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("6")));
        law_ = laws_.getRandomRate();
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getCriticalHit().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getNumberHits().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
    }

    @Test
    public void calculateLawsForDamage5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        Fight fight_ = calculateLawsForDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = TONNERRE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMove(move_);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        FightRound.initRound(fight_);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamage(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(1, laws_.getBase().size());
        assertEq(1, laws_.getRandomRate().events().size());
        assertEq(1, laws_.getCriticalHit().size());
        assertEq(1, laws_.getNumberHits().size());
        MonteCarloNumber law_;
        law_ = laws_.getBase().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("21432/2675")));
        law_ = laws_.getRandomRate();
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("17/20")));
        law_ = laws_.getCriticalHit().getVal(thrower_);
        assertEq(2, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        assertTrue(law_.events().containsObj(new Rate("2")));
        assertEq(new LgInt("15"),law_.rate(new Rate("1")));
        assertEq(new LgInt("1"),law_.rate(new Rate("2")));
        law_ = laws_.getNumberHits().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
    }

    @Test
    public void calculateLawsForDamage6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        Fight fight_ = calculateLawsForDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = TONNERRE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMove(move_);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        FightRound.initRound(fight_);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamage(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(1, laws_.getBase().size());
        assertEq(1, laws_.getRandomRate().events().size());
        assertEq(1, laws_.getCriticalHit().size());
        assertEq(1, laws_.getNumberHits().size());
        MonteCarloNumber law_;
        law_ = laws_.getBase().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("21432/2675")));
        law_ = laws_.getRandomRate();
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("17/20")));
        law_ = laws_.getCriticalHit().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("2")));
        law_ = laws_.getNumberHits().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
    }

    @Test
    public void calculateLawsForDamage7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        Fight fight_ = calculateLawsForDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = TONNERRE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMove(move_);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamage(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(1, laws_.getBase().size());
        assertEq(1, laws_.getRandomRate().events().size());
        assertEq(1, laws_.getCriticalHit().size());
        assertEq(1, laws_.getNumberHits().size());
        MonteCarloNumber law_;
        law_ = laws_.getBase().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("21432/2675")));
        law_ = laws_.getRandomRate();
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("17/20")));
        law_ = laws_.getCriticalHit().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getNumberHits().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
    }

    @Test
    public void calculateLawsForDamage8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        Fight fight_ = calculateLawsForDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = RELACHE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(METEO);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_.getNbUsesMoves().put(STOCKAGE, 1);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamage(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(1, laws_.getBase().size());
        assertEq(1, laws_.getRandomRate().events().size());
        assertEq(1, laws_.getCriticalHit().size());
        assertEq(1, laws_.getNumberHits().size());
        MonteCarloNumber law_;
        law_ = laws_.getBase().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1786/325")));
        law_ = laws_.getRandomRate();
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getCriticalHit().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getNumberHits().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
    }

    @Test
    public void calculateLawsForDamage9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        Fight fight_ = calculateLawsForDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = COMBO_GRIFFE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(METEO);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_.getNbUsesMoves().put(STOCKAGE, 1);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamage(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(1, laws_.getBase().size());
        assertEq(1, laws_.getRandomRate().events().size());
        assertEq(1, laws_.getCriticalHit().size());
        assertEq(1, laws_.getNumberHits().size());
        MonteCarloNumber law_;
        law_ = laws_.getBase().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("21432/13375")));
        law_ = laws_.getRandomRate();
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("17/20")));
        law_ = laws_.getCriticalHit().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getNumberHits().getVal(thrower_);
        assertEq(4, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("2")));
        assertTrue(law_.events().containsObj(new Rate("3")));
        assertTrue(law_.events().containsObj(new Rate("4")));
        assertTrue(law_.events().containsObj(new Rate("5")));
        assertEq(new LgInt("1"),law_.rate(new Rate("2")));
        assertEq(new LgInt("2"),law_.rate(new Rate("3")));
        assertEq(new LgInt("3"),law_.rate(new Rate("4")));
        assertEq(new LgInt("2"),law_.rate(new Rate("5")));
    }

    @Test
    public void calculateLawsForDamage10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        Fight fight_ = calculateLawsForDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = COMBO_GRIFFE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(MULTI_COUPS);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_.getNbUsesMoves().put(STOCKAGE, 1);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamage(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(1, laws_.getBase().size());
        assertEq(1, laws_.getRandomRate().events().size());
        assertEq(1, laws_.getCriticalHit().size());
        assertEq(1, laws_.getNumberHits().size());
        MonteCarloNumber law_;
        law_ = laws_.getBase().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("21432/13375")));
        law_ = laws_.getRandomRate();
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("17/20")));
        law_ = laws_.getCriticalHit().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getNumberHits().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("5")));
        assertEq(new LgInt("1"),law_.rate(new Rate("5")));
    }

    private static Fight calculateLawsForDamageByTeam(Difficulty _diff) {
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
    public void calculateLawsForDamageByTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = calculateLawsForDamageByTeam(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = TONNERRE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMove(move_);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamageByTeam(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(1, laws_.getBase().size());
        assertEq(1, laws_.getRandomRate().events().size());
        assertEq(1, laws_.getCriticalHit().size());
        assertEq(1, laws_.getNumberHits().size());
        MonteCarloNumber law_;
        law_ = laws_.getBase().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("21432/2675")));
        law_ = laws_.getRandomRate();
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getCriticalHit().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getNumberHits().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
    }

    @Test
    public void calculateLawsForDamageByTeam2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = calculateLawsForDamageByTeam(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = BASTON;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamageByTeam(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(3, laws_.getBase().size());
        assertEq(1, laws_.getRandomRate().events().size());
        assertEq(3, laws_.getCriticalHit().size());
        assertEq(3, laws_.getNumberHits().size());
        MonteCarloNumber law_;
        law_ = laws_.getBase().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("7144/13375")));
        law_ = laws_.getRandomRate();
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getCriticalHit().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getNumberHits().getVal(thrower_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        TeamPosition partnerOne_ = POKEMON_PLAYER_FIGHTER_ONE;
        law_ = laws_.getBase().getVal(partnerOne_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("7144/13375")));
        law_ = laws_.getCriticalHit().getVal(partnerOne_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getNumberHits().getVal(partnerOne_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        TeamPosition partnerTwo_ = POKEMON_PLAYER_FIGHTER_TWO;
        law_ = laws_.getBase().getVal(partnerTwo_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("7144/13375")));
        law_ = laws_.getCriticalHit().getVal(partnerTwo_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
        law_ = laws_.getNumberHits().getVal(partnerTwo_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(new Rate("1")));
    }

    @Test
    public void calculateMinMaxAvgVarForDamage1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = calculateLawsForDamageByTeam(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = BASTON;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        EnumMap<UsefulValueLaw,Rate> values_ = FightEffects.calculateMinMaxAvgVarForDamage(fight_, thrower_, target_, move_, diff_, _data_);
        assertEq(new Rate("21432/13375"),values_.getVal(UsefulValueLaw.MINI));
        assertEq(new Rate("21432/13375"),values_.getVal(UsefulValueLaw.MAXI));
        assertEq(new Rate("21432/13375"),values_.getVal(UsefulValueLaw.MOY));
        assertEq(new Rate("0"),values_.getVal(UsefulValueLaw.VAR));
    }

    private static Fight useBerryAgainstEfficiencyMoves(Difficulty _diff) {
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
    public void useBerryAgainstEfficiencyMoves1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = useBerryAgainstEfficiencyMoves(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = BASTON;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        fighter_.backUpObject(NULL_REF);
        FightRound.initRound(fight_);
        FightEffects.useBerryAgainstEfficiencyMoves(fight_, thrower_, target_, move_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void useBerryAgainstEfficiencyMoves2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = useBerryAgainstEfficiencyMoves(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = BASTON;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        fighter_.backUpObject(BAIE_MEPO);
        FightRound.initRound(fight_);
        FightEffects.useBerryAgainstEfficiencyMoves(fight_, thrower_, target_, move_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void useBerryAgainstEfficiencyMoves3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = useBerryAgainstEfficiencyMoves(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = BASTON;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        fighter_.backUpObject(BAIE_LAMPOU);
        FightRound.initRound(fight_);
        FightEffects.useBerryAgainstEfficiencyMoves(fight_, thrower_, target_, move_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void useBerryAgainstEfficiencyMoves4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = useBerryAgainstEfficiencyMoves(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = BASTON;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(PSY);
        fighter_.backUpObject(BAIE_LAMPOU);
        FightRound.initRound(fight_);
        FightEffects.useBerryAgainstEfficiencyMoves(fight_, thrower_, target_, move_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.isUsingItem());
    }

    private static Fight damageByUserOfMove(Difficulty _diff) {
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
    public void damageByUserOfMove1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = damageByUserOfMove(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = DRACO_RAGE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamageByTeam(fight_, thrower_, target_, move_, diff_, _data_);
        DamageMoveCountUser damage_ = FightEffects.damageByUserOfMove(fight_, _data_,thrower_, target_, laws_);
        //assertEq(new Rate("0"),damage_.getDamage());
        //assertEq(new Rate("0"),damage_.getDamageClone());
        assertEq(new Rate("40"),damage_.getDamageCount());
        assertEq(1, damage_.getHits());
        assertTrue(!damage_.isCriticalHit());
        assertTrue(!damage_.isKeepProcessing());
    }

    @Test
    public void damageByUserOfMove2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = damageByUserOfMove(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = TONNERRE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMove(move_);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamageByTeam(fight_, thrower_, target_, move_, diff_, _data_);
        DamageMoveCountUser damage_ = FightEffects.damageByUserOfMove(fight_, _data_,thrower_, target_, laws_);
        //assertEq(new Rate("0"),damage_.getDamage());
        //assertEq(new Rate("0"),damage_.getDamageClone());
        assertEq(new Rate("42864/2675"),damage_.getDamageCount());
        assertEq(1, damage_.getHits());
        assertTrue(damage_.isCriticalHit());
        assertTrue(!damage_.isKeepProcessing());
    }

    @Test
    public void koTarget1Test() {
        ThrowerDamageLaws law_ = new ThrowerDamageLaws();
        law_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setRandomRate(new MonteCarloNumber());
        law_.getRandomRate().addEvent(Rate.one(), LgInt.one());
        TeamPosition fighterOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        law_.getBase().put(fighterOne_, new MonteCarloNumber());
        law_.getBase().getVal(fighterOne_).addEvent(new Rate("4"), LgInt.one());
        law_.getBase().getVal(fighterOne_).addEvent(new Rate("8"), LgInt.one());
        law_.getCriticalHit().put(fighterOne_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(fighterOne_).addEvent(new Rate("1"), LgInt.one());
        law_.getCriticalHit().getVal(fighterOne_).addEvent(new Rate("2"), LgInt.one());
        law_.getNumberHits().put(fighterOne_, new MonteCarloNumber());
        law_.getNumberHits().getVal(fighterOne_).addEvent(new Rate("2"), LgInt.one());
        law_.getNumberHits().getVal(fighterOne_).addEvent(new Rate("3"), LgInt.one());
        law_.getNumberHits().getVal(fighterOne_).addEvent(new Rate("4"), LgInt.one());
        law_.getNumberHits().getVal(fighterOne_).addEvent(new Rate("5"), LgInt.one());
        TeamPosition fighterTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        law_.getBase().put(fighterTwo_, new MonteCarloNumber());
        law_.getBase().getVal(fighterTwo_).addEvent(new Rate("4"), LgInt.one());
        law_.getBase().getVal(fighterTwo_).addEvent(new Rate("8"), LgInt.one());
        law_.getCriticalHit().put(fighterTwo_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(fighterTwo_).addEvent(new Rate("1"), LgInt.one());
        law_.getCriticalHit().getVal(fighterTwo_).addEvent(new Rate("2"), LgInt.one());
        law_.getNumberHits().put(fighterTwo_, new MonteCarloNumber());
        law_.getNumberHits().getVal(fighterTwo_).addEvent(new Rate("2"), LgInt.one());
        law_.getNumberHits().getVal(fighterTwo_).addEvent(new Rate("3"), LgInt.one());
        law_.getNumberHits().getVal(fighterTwo_).addEvent(new Rate("4"), LgInt.one());
        law_.getNumberHits().getVal(fighterTwo_).addEvent(new Rate("5"), LgInt.one());
        assertTrue(FightEffects.koTarget(new Rate("1"), new Rate("0"), law_));
        assertTrue(FightEffects.koTarget(new Rate("16"), new Rate("0"), law_));
        assertTrue(!FightEffects.koTarget(new Rate("17"), new Rate("0"), law_));
        assertTrue(!FightEffects.koTarget(new Rate("1"), new Rate("17"), law_));
        assertTrue(!FightEffects.koTarget(new Rate("1"), new Rate("15"), law_));
        assertTrue(FightEffects.koTarget(new Rate("1"), new Rate("11"), law_));
        assertTrue(FightEffects.koTarget(new Rate("1"), new Rate("12"), law_));
        assertTrue(!FightEffects.koTarget(new Rate("1"), new Rate("13"), law_));
    }

    @Test
    public void koTarget2Test() {
        Difficulty diff_= new Difficulty();
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
        moves_.put(DRACO_RAGE, (short) 10);
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
        String move_ = DRACO_RAGE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_.setFirstChosenMoveTarget(move_, POKEMON_FOE_TARGET_ZERO);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(NORMAL);
        ThrowerDamageLaws laws_ = FightEffects.calculateLawsForDamageByTeam(fight_, thrower_, target_, move_, diff_, _data_);
        assertTrue(FightEffects.koTarget(fight_, target_, laws_));
    }

    private static Fight canReverseAbsorb(Difficulty _diff) {
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
    public void canReverseAbsorb1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = canReverseAbsorb(diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        assertTrue(!FightEffects.canReverseAbsorb(fight_, thrower_, target_, _data_));
    }

    @Test
    public void canReverseAbsorb2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = canReverseAbsorb(diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        assertTrue(!FightEffects.canReverseAbsorb(fight_, thrower_, target_, _data_));
    }

    @Test
    public void canReverseAbsorb3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = canReverseAbsorb(diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        assertTrue(FightEffects.canReverseAbsorb(fight_, thrower_, target_, _data_));
    }

    private static Fight remainingHp(Difficulty _diff) {
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
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,TENACITE));
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
    public void remainingHp1Test(){
        assertEq(new Rate("0"), FightEffects.remainingHp(DRACO_RAGE, _data_));
        assertEq(new Rate("0"), FightEffects.remainingHp(DETECTION, _data_));
        assertEq(new Rate("1"), FightEffects.remainingHp(TENACITE, _data_));
    }

    @Test
    public void remainingHp2Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = remainingHp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        assertEq(new Rate("0"),FightEffects.remainingHp(fight_, target_, DRACO_RAGE, _data_));
    }

    @Test
    public void remainingHp3Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = remainingHp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setFirstChosenMove(TENACITE);
        FightRound.initRound(fight_);
        assertEq(new Rate("0"),FightEffects.remainingHp(fight_, target_, DRACO_RAGE, _data_));
    }

    @Test
    public void remainingHp4Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = remainingHp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setFirstChosenMove(TENACITE);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(target_);
        fighter_.successUsingMove();
        assertEq(new Rate("1"),FightEffects.remainingHp(fight_, target_, DRACO_RAGE, _data_));
    }

    @Test
    public void remainingHp5Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = remainingHp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setFirstChosenMove(TENACITE);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(target_);
        fighter_.successUsingMove();
        assertEq(new Rate("0"),FightEffects.remainingHp(fight_, target_, SACRIFICE, _data_));
    }

    @Test
    public void remainingHp6Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = remainingHp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BAIE_MEPO);
        assertEq(new Rate("0"),FightEffects.remainingHp(fight_, target_, DRACO_RAGE, _data_));
    }

    @Test
    public void remainingHp7Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = remainingHp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        assertEq(new Rate("1"),FightEffects.remainingHp(fight_, target_, FAUX_CHAGE, _data_));
    }

    @Test
    public void remainingHp8Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = remainingHp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BOUE_NOIRE);
        assertEq(new Rate("0"),FightEffects.remainingHp(fight_, target_, DRACO_RAGE, _data_));
    }

    @Test
    public void remainingHp9Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = remainingHp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(BANDEAU);
        assertEq(new Rate("1"),FightEffects.remainingHp(fight_, target_, DRACO_RAGE, _data_));
    }

    @Test
    public void remainingHp10Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = remainingHp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(CEINT_FORCE);
        assertEq(new Rate("1"),FightEffects.remainingHp(fight_, target_, DRACO_RAGE, _data_));
    }

    @Test
    public void remainingHp11Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = remainingHp(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(CEINT_FORCE);
        fighter_.setRemainedHp(new Rate("18"));
        assertEq(new Rate("0"),FightEffects.remainingHp(fight_, target_, DRACO_RAGE, _data_));
    }

    private static Fight inflictDamageToTargetByUserOfMove(Difficulty _diff) {
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

    private static void initDamage(Fight _fight) {
        _fight.getDamage().getDamage().affectZero();
        _fight.getDamage().getDamageClone().affectZero();
        _fight.getDamage().getDamageCount().affectZero();
        _fight.getDamage().setCriticalHit(false);
        _fight.getDamage().setHits((byte) 0);
        _fight.getDamage().setKeepProcessing(true);
    }

    @Test
    public void inflictDamageToTargetByUserOfMove1Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = inflictDamageToTargetByUserOfMove(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        ThrowerDamageLaws law_ = new ThrowerDamageLaws();
        law_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setRandomRate(new MonteCarloNumber());
        law_.getRandomRate().addEvent(Rate.one(), LgInt.one());
        law_.getBase().put(thrower_, new MonteCarloNumber());
        law_.getBase().getVal(thrower_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(thrower_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(thrower_).addEvent(new Rate("1"), LgInt.one());
        law_.getNumberHits().put(thrower_, new MonteCarloNumber());
        law_.getNumberHits().getVal(thrower_).addEvent(new Rate("2"), LgInt.one());
        initDamage(fight_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, thrower_, target_, law_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fighter_.estKo());
        DamageMoveCountUser damage_ = fight_.getDamage();
        assertEq(new Rate("8"),damage_.getDamage());
        assertEq(new Rate("0"),damage_.getDamageClone());
        assertEq(new Rate("8"),damage_.getDamageCount());
        assertEq(2,damage_.getHits());
        assertTrue(!damage_.isCriticalHit());
        assertTrue(damage_.isKeepProcessing());
    }

    @Test
    public void inflictDamageToTargetByUserOfMove2Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = inflictDamageToTargetByUserOfMove(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        ThrowerDamageLaws law_ = new ThrowerDamageLaws();
        law_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setRandomRate(new MonteCarloNumber());
        law_.getRandomRate().addEvent(Rate.one(), LgInt.one());
        law_.getBase().put(thrower_, new MonteCarloNumber());
        law_.getBase().getVal(thrower_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(thrower_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(thrower_).addEvent(new Rate("2"), LgInt.one());
        law_.getNumberHits().put(thrower_, new MonteCarloNumber());
        law_.getNumberHits().getVal(thrower_).addEvent(new Rate("2"), LgInt.one());
        initDamage(fight_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, thrower_, target_, law_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fighter_.estKo());
        DamageMoveCountUser damage_ = fight_.getDamage();
        assertEq(new Rate("16"),damage_.getDamage());
        assertEq(new Rate("0"),damage_.getDamageClone());
        assertEq(new Rate("16"),damage_.getDamageCount());
        assertEq(2,damage_.getHits());
        assertTrue(damage_.isCriticalHit());
        assertTrue(damage_.isKeepProcessing());
    }

    @Test
    public void inflictDamageToTargetByUserOfMove3Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = inflictDamageToTargetByUserOfMove(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        ThrowerDamageLaws law_ = new ThrowerDamageLaws();
        law_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setRandomRate(new MonteCarloNumber());
        law_.getRandomRate().addEvent(Rate.one(), LgInt.one());
        law_.getBase().put(thrower_, new MonteCarloNumber());
        law_.getBase().getVal(thrower_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(thrower_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(thrower_).addEvent(new Rate("2"), LgInt.one());
        law_.getNumberHits().put(thrower_, new MonteCarloNumber());
        law_.getNumberHits().getVal(thrower_).addEvent(new Rate("3"), LgInt.one());
        initDamage(fight_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, thrower_, target_, law_, _data_);
        fighter_ = fight_.getFighter(target_);
        //assertTrue(fighter_.estKo());
        DamageMoveCountUser damage_ = fight_.getDamage();
        assertEq(new Rate("92/5"),damage_.getDamage());
        assertEq(new Rate("0"),damage_.getDamageClone());
        assertEq(3,damage_.getHits());
        assertTrue(damage_.isCriticalHit());
        assertTrue(!damage_.isKeepProcessing());
    }

    @Test
    public void inflictDamageToTargetByUserOfMove4Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = inflictDamageToTargetByUserOfMove(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        ThrowerDamageLaws law_ = new ThrowerDamageLaws();
        law_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setRandomRate(new MonteCarloNumber());
        law_.getRandomRate().addEvent(Rate.one(), LgInt.one());
        law_.getBase().put(thrower_, new MonteCarloNumber());
        law_.getBase().getVal(thrower_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(thrower_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(thrower_).addEvent(new Rate("2"), LgInt.one());
        law_.getNumberHits().put(thrower_, new MonteCarloNumber());
        law_.getNumberHits().getVal(thrower_).addEvent(new Rate("4"), LgInt.one());
        initDamage(fight_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, thrower_, target_, law_, _data_);
        fighter_ = fight_.getFighter(target_);
        //assertTrue(fighter_.estKo());
        DamageMoveCountUser damage_ = fight_.getDamage();
        assertEq(new Rate("92/5"),damage_.getDamage());
        assertEq(new Rate("0"),damage_.getDamageClone());
        assertEq(3,damage_.getHits());
        assertTrue(damage_.isCriticalHit());
        assertTrue(!damage_.isKeepProcessing());
    }

    @Test
    public void inflictDamageToTargetByUserOfMove5Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = inflictDamageToTargetByUserOfMove(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.creerClone(new Rate("1/2"));
        ThrowerDamageLaws law_ = new ThrowerDamageLaws();
        law_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setRandomRate(new MonteCarloNumber());
        law_.getRandomRate().addEvent(Rate.one(), LgInt.one());
        law_.getBase().put(thrower_, new MonteCarloNumber());
        law_.getBase().getVal(thrower_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(thrower_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(thrower_).addEvent(new Rate("1"), LgInt.one());
        law_.getNumberHits().put(thrower_, new MonteCarloNumber());
        law_.getNumberHits().getVal(thrower_).addEvent(new Rate("2"), LgInt.one());
        initDamage(fight_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, thrower_, target_, law_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fighter_.estKo());
        assertEq(new Rate("46/5"),fighter_.getRemainingHp());
        assertEq(new Rate("6/5"),fighter_.getClone());
        DamageMoveCountUser damage_ = fight_.getDamage();
        assertEq(new Rate("0"),damage_.getDamage());
        assertEq(new Rate("8"),damage_.getDamageClone());
        assertEq(2,damage_.getHits());
        assertTrue(!damage_.isCriticalHit());
        assertTrue(damage_.isKeepProcessing());
    }

    @Test
    public void inflictDamageToTargetByUserOfMove6Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = inflictDamageToTargetByUserOfMove(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.creerClone(new Rate("1/2"));
        ThrowerDamageLaws law_ = new ThrowerDamageLaws();
        law_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setRandomRate(new MonteCarloNumber());
        law_.getRandomRate().addEvent(Rate.one(), LgInt.one());
        law_.getBase().put(thrower_, new MonteCarloNumber());
        law_.getBase().getVal(thrower_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(thrower_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(thrower_).addEvent(new Rate("1"), LgInt.one());
        law_.getNumberHits().put(thrower_, new MonteCarloNumber());
        law_.getNumberHits().getVal(thrower_).addEvent(new Rate("3"), LgInt.one());
        initDamage(fight_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, thrower_, target_, law_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fighter_.estKo());
        assertEq(new Rate("46/5"),fighter_.getRemainingHp());
        assertEq(new Rate("0"),fighter_.getClone());
        DamageMoveCountUser damage_ = fight_.getDamage();
        assertEq(new Rate("0"),damage_.getDamage());
        assertEq(new Rate("46/5"),damage_.getDamageClone());
        assertEq(3,damage_.getHits());
        assertTrue(!damage_.isCriticalHit());
        assertTrue(damage_.isKeepProcessing());
    }

    @Test
    public void inflictDamageToTargetByUserOfMove7Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = inflictDamageToTargetByUserOfMove(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.creerClone(new Rate("1/2"));
        ThrowerDamageLaws law_ = new ThrowerDamageLaws();
        law_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setRandomRate(new MonteCarloNumber());
        law_.getRandomRate().addEvent(Rate.one(), LgInt.one());
        law_.getBase().put(thrower_, new MonteCarloNumber());
        law_.getBase().getVal(thrower_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(thrower_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(thrower_).addEvent(new Rate("1"), LgInt.one());
        law_.getNumberHits().put(thrower_, new MonteCarloNumber());
        law_.getNumberHits().getVal(thrower_).addEvent(new Rate("4"), LgInt.one());
        initDamage(fight_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, thrower_, target_, law_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(!fighter_.estKo());
        assertEq(new Rate("46/5"),fighter_.getRemainingHp());
        assertEq(new Rate("0"),fighter_.getClone());
        DamageMoveCountUser damage_ = fight_.getDamage();
        assertEq(new Rate("4"),damage_.getDamage());
        assertEq(new Rate("46/5"),damage_.getDamageClone());
        assertEq(4,damage_.getHits());
        assertTrue(!damage_.isCriticalHit());
        assertTrue(damage_.isKeepProcessing());
    }

    @Test
    public void inflictDamageToTargetByUserOfMove8Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = inflictDamageToTargetByUserOfMove(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.creerClone(new Rate("1/2"));
        ThrowerDamageLaws law_ = new ThrowerDamageLaws();
        law_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setRandomRate(new MonteCarloNumber());
        law_.getRandomRate().addEvent(Rate.one(), LgInt.one());
        law_.getBase().put(thrower_, new MonteCarloNumber());
        law_.getBase().getVal(thrower_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(thrower_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(thrower_).addEvent(new Rate("1"), LgInt.one());
        law_.getNumberHits().put(thrower_, new MonteCarloNumber());
        law_.getNumberHits().getVal(thrower_).addEvent(new Rate("3"), LgInt.one());
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_ONE;
        law_.getBase().put(partner_, new MonteCarloNumber());
        law_.getBase().getVal(partner_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(partner_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(partner_).addEvent(new Rate("2"), LgInt.one());
        law_.getNumberHits().put(partner_, new MonteCarloNumber());
        law_.getNumberHits().getVal(partner_).addEvent(new Rate("3"), LgInt.one());
        initDamage(fight_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, thrower_, target_, law_, _data_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, partner_, target_, law_, _data_);
        fighter_ = fight_.getFighter(target_);
        //assertTrue(fighter_.estKo());
        //assertEq(new Rate("0"),fighter_.getRemainingHp());
        assertEq(new Rate("0"),fighter_.getClone());
        DamageMoveCountUser damage_ = fight_.getDamage();
        assertEq(new Rate("46/5"),damage_.getDamage());
        assertEq(new Rate("46/5"),damage_.getDamageClone());
        assertEq(5,damage_.getHits());
        assertTrue(damage_.isCriticalHit());
        assertTrue(!damage_.isKeepProcessing());
    }

    @Test
    public void inflictDamageToTargetByUserOfMove9Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = inflictDamageToTargetByUserOfMove(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        fighter_.creerClone(new Rate("1/2"));
        ThrowerDamageLaws law_ = new ThrowerDamageLaws();
        law_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setRandomRate(new MonteCarloNumber());
        law_.getRandomRate().addEvent(Rate.one(), LgInt.one());
        law_.getBase().put(thrower_, new MonteCarloNumber());
        law_.getBase().getVal(thrower_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(thrower_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(thrower_).addEvent(new Rate("1"), LgInt.one());
        law_.getNumberHits().put(thrower_, new MonteCarloNumber());
        law_.getNumberHits().getVal(thrower_).addEvent(new Rate("3"), LgInt.one());
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_ONE;
        law_.getBase().put(partner_, new MonteCarloNumber());
        law_.getBase().getVal(partner_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(partner_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(partner_).addEvent(new Rate("2"), LgInt.one());
        law_.getNumberHits().put(partner_, new MonteCarloNumber());
        law_.getNumberHits().getVal(partner_).addEvent(new Rate("3"), LgInt.one());
        initDamage(fight_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, partner_, target_, law_, _data_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, thrower_, target_, law_, _data_);
        fighter_ = fight_.getFighter(target_);
        //assertTrue(fighter_.estKo());
        //assertEq(new Rate("0"),fighter_.getRemainingHp());
        assertEq(new Rate("0"),fighter_.getClone());
        DamageMoveCountUser damage_ = fight_.getDamage();
        assertEq(new Rate("46/5"),damage_.getDamage());
        assertEq(new Rate("46/5"),damage_.getDamageClone());
        assertEq(4,damage_.getHits());
        assertTrue(damage_.isCriticalHit());
        assertTrue(!damage_.isKeepProcessing());
    }

    @Test
    public void inflictDamageToTargetByUserOfMove10Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = inflictDamageToTargetByUserOfMove(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.backUpObject(NULL_REF);
        ThrowerDamageLaws law_ = new ThrowerDamageLaws();
        law_.setBase(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setCriticalHit(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setNumberHits(new ObjectMap<TeamPosition,MonteCarloNumber>());
        law_.setRandomRate(new MonteCarloNumber());
        law_.getRandomRate().addEvent(Rate.one(), LgInt.one());
        law_.getBase().put(thrower_, new MonteCarloNumber());
        law_.getBase().getVal(thrower_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(thrower_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(thrower_).addEvent(new Rate("2"), LgInt.one());
        law_.getNumberHits().put(thrower_, new MonteCarloNumber());
        law_.getNumberHits().getVal(thrower_).addEvent(new Rate("3"), LgInt.one());
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_ONE;
        law_.getBase().put(partner_, new MonteCarloNumber());
        law_.getBase().getVal(partner_).addEvent(new Rate("4"), LgInt.one());
        law_.getCriticalHit().put(partner_, new MonteCarloNumber());
        law_.getCriticalHit().getVal(partner_).addEvent(new Rate("2"), LgInt.one());
        law_.getNumberHits().put(partner_, new MonteCarloNumber());
        law_.getNumberHits().getVal(partner_).addEvent(new Rate("3"), LgInt.one());
        initDamage(fight_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, thrower_, target_, law_, _data_);
        FightEffects.inflictDamageToTargetByUserOfMove(fight_, partner_, target_, law_, _data_);
        fighter_ = fight_.getFighter(target_);
        //assertTrue(fighter_.estKo());
        DamageMoveCountUser damage_ = fight_.getDamage();
        assertEq(new Rate("92/5"),damage_.getDamage());
        assertEq(new Rate("0"),damage_.getDamageClone());
        assertEq(3,damage_.getHits());
        assertTrue(damage_.isCriticalHit());
        assertTrue(!damage_.isKeepProcessing());
    }

    private static Fight calculateDamageKo(Difficulty _diff) {
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
    public void calculateDamageKo1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = calculateDamageKo(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getDamageKo().affectZero();
        FightEffects.calculateDamageKo(fight_, target_, DRACO_RAGE, new LgInt("1"), diff_, _data_);
        assertEq(new Rate("92/5"), fight_.getDamageKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void calculateDamageKo2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = calculateDamageKo(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getDamageKo().affectZero();
        FightEffects.calculateDamageKo(fight_, target_, FAUX_CHAGE, new LgInt("1"), diff_, _data_);
        assertEq(new Rate("87/5"), fight_.getDamageKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void calculateDamageKo3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = calculateDamageKo(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(TENACITE);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(target_);
        fighter_.successUsingMove();
        fight_.getDamageKo().affectZero();
        FightEffects.calculateDamageKo(fight_, target_, DRACO_RAGE, new LgInt("1"), diff_, _data_);
        assertEq(new Rate("87/5"), fight_.getDamageKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void calculateDamageKo4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = calculateDamageKo(diff_);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(TENACITE);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(target_);
        fighter_.successUsingMove();
        fight_.getDamageKo().affectZero();
        FightEffects.calculateDamageKo(fight_, target_, DRACO_RAGE, new LgInt("2"), diff_, _data_);
        assertEq(new Rate("92/5"), fight_.getDamageKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void calculateDamageKo5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = calculateDamageKo(diff_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getDamageKo().affectZero();
        FightEffects.calculateDamageKo(fight_, target_, DRACO_RAGE, new LgInt("1"), diff_, _data_);
        assertEq(new Rate("1873/100"), fight_.getDamageKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void calculateDamageKo1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = calculateDamageKo(diff_);
        fight_.setSimulation(true);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getDamageKo().affectZero();
        FightEffects.calculateDamageKo(fight_, target_, DRACO_RAGE, new LgInt("1"), diff_, _data_);
        assertEq(new Rate("1873/100"), fight_.getDamageKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private static Fight rateAbsorb(Difficulty _diff) {
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
    public void rateAbsorb1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = rateAbsorb(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        assertEq(new Rate("0"), FightEffects.rateAbsorb(fight_, thrower_, _data_));
    }

    @Test
    public void rateAbsorb2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = rateAbsorb(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BAIE_MEPO);
        assertEq(new Rate("0"), FightEffects.rateAbsorb(fight_, thrower_, _data_));
    }

    @Test
    public void rateAbsorb3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = rateAbsorb(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(GRELOT_COQUE);
        assertEq(new Rate("1/8"), FightEffects.rateAbsorb(fight_, thrower_, _data_));
    }

    @Test
    public void rateAbsorb4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = rateAbsorb(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(GRELOT_COQUE);
        fight_.getFoeTeam().activerEffetEquipe(ANTI_SOIN);
        assertEq(new Rate("0"), FightEffects.rateAbsorb(fight_, thrower_, _data_));
    }

    private static Fight healedLostHp(Difficulty _diff) {
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
    public void healedLostHp1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = healedLostHp(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        assertEq(new Rate("0"), FightEffects.healedLostHp(fight_, thrower_, new Rate("2"), false, new Rate("1"), _data_));
    }

    @Test
    public void healedLostHp2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = healedLostHp(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(GRELOT_COQUE);
        assertEq(new Rate("1/8"), FightEffects.healedLostHp(fight_, thrower_, new Rate("1"), false, new Rate("2"), _data_));
    }

    @Test
    public void healedLostHp3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = healedLostHp(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(GRELOT_COQUE);
        assertEq(new Rate("1/8"), FightEffects.healedLostHp(fight_, thrower_, new Rate("2"), false, new Rate("1"), _data_));
    }

    @Test
    public void healedLostHp4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = healedLostHp(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(GRELOT_COQUE);
        assertEq(new Rate("-1/8"), FightEffects.healedLostHp(fight_, thrower_, new Rate("2"), true, new Rate("1"), _data_));
    }

    @Test
    public void healedLostHp5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = healedLostHp(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(GRELOT_COQUE);
        assertEq(new Rate("1/8"), FightEffects.healedLostHp(fight_, thrower_, new Rate("1"), true, new Rate("2"), _data_));
    }

    private static Fight enableTargetAbility(Difficulty _diff) {
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
    public void enableTargetAbility1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableTargetAbility(diff_);
        String move_ = BASTON;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(FEU);
        fighter_.setCurrentAbility(DEGUISEMENT);
        FightEffects.enableTargetAbility(fight_, thrower_, target_, false, (byte) 1, move_, _data_);
        fighter_ = fight_.getFighter(target_);
        StringList types_ = fighter_.getTypes();
        assertEq(1,types_.size());
        assertTrue(types_.containsObj(TENEBRE));
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
    public void enableTargetAbility2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableTargetAbility(diff_);
        String move_ = BASTON;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(FEU);
        fighter_.setCurrentAbility(COLERIQUE);
        FightEffects.enableTargetAbility(fight_, thrower_, target_, false, (byte) 1, move_, _data_);
        fighter_ = fight_.getFighter(target_);
        StringList types_ = fighter_.getTypes();
        assertEq(1,types_.size());
        assertTrue(types_.containsObj(FEU));
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
    public void enableTargetAbility3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableTargetAbility(diff_);
        String move_ = BASTON;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(FEU);
        fighter_.setCurrentAbility(COLERIQUE);
        FightEffects.enableTargetAbility(fight_, thrower_, target_, true, (byte) 1, move_, _data_);
        fighter_ = fight_.getFighter(target_);
        StringList types_ = fighter_.getTypes();
        assertEq(1,types_.size());
        assertTrue(types_.containsObj(FEU));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }

    @Test
    public void enableTargetAbility4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableTargetAbility(diff_);
        String move_ = BASTON;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(FEU);
        fighter_.setCurrentAbility(COEUR_NOBLE);
        FightEffects.enableTargetAbility(fight_, thrower_, target_, false, (byte) 2, move_, _data_);
        fighter_ = fight_.getFighter(target_);
        StringList types_ = fighter_.getTypes();
        assertEq(1,types_.size());
        assertTrue(types_.containsObj(FEU));
        assertEq(2, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }

    @Test
    public void enableTargetAbility5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableTargetAbility(diff_);
        String move_ = BASTON;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        fighter_.affecterTypes(FEU);
        fighter_.setCurrentAbility(ARMUROUILLEE);
        FightEffects.enableTargetAbility(fight_, thrower_, target_, false, (byte) 2, move_, _data_);
        fighter_ = fight_.getFighter(target_);
        StringList types_ = fighter_.getTypes();
        assertEq(1,types_.size());
        assertTrue(types_.containsObj(FEU));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(2, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }



    private static Fight enableFighterHavingToUseAbility(Difficulty _diff) {
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
    public void enableFighterHavingToUseAbility1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PYROMANE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(BRULURE).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(BRULURE).intValue());
    }

    @Test
    public void enableFighterHavingToUseAbility2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PYROMANE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, SEISME, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE).intValue());
    }

    @Test
    public void enableFighterHavingToUseAbility3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PYROMANE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(METEO);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatusNbRound(BRULURE).intValue());
    }

    @Test
    public void enableFighterHavingToUseAbility4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PYROMANE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE).intValue());
    }

    @Test
    public void enableFighterHavingToUseAbility5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(TERA_VOLTAGE, fighter_.getCurrentAbility());
    }

    @Test
    public void enableFighterHavingToUseAbility6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, SEISME, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(GARDE_MAGIK, fighter_.getCurrentAbility());
    }

    @Test
    public void enableFighterHavingToUseAbility7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(METEO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(GARDE_MAGIK, fighter_.getCurrentAbility());
    }

    @Test
    public void enableFighterHavingToUseAbility8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, false, ROULADE, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(TERA_VOLTAGE, fighter_.getCurrentAbility());
    }

    @Test
    public void enableFighterHavingToUseAbility9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(MULTITYPE);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
    }

    @Test
    public void enableFighterHavingToUseAbility10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(JOLI_SOURIRE);
        fighter_.setCurrentGender(Gender.MALE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(MULTITYPE);
        fighter_.setCurrentGender(Gender.FEMALE);
        fighter_.backUpObject(NOEUD_DESTIN);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)).intValue());
    }

    @Test
    public void enableFighterHavingToUseAbility11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(JOLI_SOURIRE);
        fighter_.affecterPseudoStatut(partner_, AMOUR);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(MULTITYPE);
        fighter_.backUpObject(NOEUD_DESTIN);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, partner_)).intValue());
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)).intValue());
    }

    @Test
    public void enableFighterHavingToUseAbility12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(JOLI_SOURIRE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(MULTITYPE);
        fighter_.backUpObject(NULL_REF);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)).intValue());
    }

    @Test
    public void enableFighterHavingToUseAbility13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(JOLI_SOURIRE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(MULTITYPE);
        fighter_.backUpObject(BAIE_MEPO);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)).intValue());
    }

    @Test
    public void enableFighterHavingToUseAbility14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(JOLI_SOURIRE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(BAIE_MEPO);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR, target_)).intValue());
    }

    @Test
    public void enableFighterHavingToUseAbility15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(MAGICIEN);
        fighter_.setItem(BAIE_MEPO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setItem(NULL_REF);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(BAIE_MEPO, fighter_.getItem());
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(NULL_REF, fighter_.getItem());
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableFighterHavingToUseAbility16Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(MAGICIEN);
        fighter_.setItem(BAIE_MEPO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setItem(NOEUD_DESTIN);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(BAIE_MEPO, fighter_.getItem());
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(NOEUD_DESTIN, fighter_.getItem());
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableFighterHavingToUseAbility17Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(MAGICIEN);
        fighter_.setItem(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setItem(NOEUD_DESTIN);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(NOEUD_DESTIN, fighter_.getItem());
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(NOEUD_DESTIN, fighter_.getItem());
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableFighterHavingToUseAbility18Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(POISSEUX);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(CORPS_SAIN);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
    }

    @Test
    public void enableFighterHavingToUseAbility19Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(POISSEUX);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        assertEq(-1, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
    }

    @Test
    public void enableFighterHavingToUseAbility20Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableFighterHavingToUseAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PYROMANE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SYNCHRO);
        FightKo.setKoMoveTeams(fight_, target_, diff_, _data_);
        FightEffects.enableFighterHavingToUseAbility(fight_, thrower_, target_, true, ROULADE, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatusNbRound(BRULURE).intValue());
    }

    private static Fight recoilAgainstTarget(Difficulty _diff) {
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
    public void recoilAgainstTarget1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(NULL_REF);
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, DRACO_RAGE, diff_, _data_);
        assertEq(new Rate("0"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(METEO);
        fighter_.backUpObject(NULL_REF);
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, DRACO_RAGE, diff_, _data_);
        assertEq(new Rate("0"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PEAU_DURE);
        fighter_.backUpObject(NULL_REF);
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, DRACO_RAGE, diff_, _data_);
        assertEq(new Rate("0"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PEAU_DURE);
        fighter_.backUpObject(NULL_REF);
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, ROULADE, diff_, _data_);
        assertEq(new Rate("23/10"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("161/10"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(BAIE_JABOCA);
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, ROULADE, diff_, _data_);
        assertEq(new Rate("23/10"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("161/10"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(PIQUANTS);
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, ROULADE, diff_, _data_);
        assertEq(new Rate("23/10"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("161/10"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(BAIE_JABOCA);
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, TONNERRE, diff_, _data_);
        assertEq(new Rate("0"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(PIQUANTS);
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, SEISME, diff_, _data_);
        assertEq(new Rate("0"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(BOUE_NOIRE);
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, SEISME, diff_, _data_);
        assertEq(new Rate("0"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(PIQUANTS);
        fighter_ = fight_.getFighter(target_);
        fighter_.creerClone(new Rate("1/2"));
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, ROULADE, diff_, _data_);
        assertEq(new Rate("0"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("46/5"), fighter_.getRemainingHp());
        assertEq(new Rate("69/10"), fighter_.getClone());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(PIQUANTS);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("22/10"));
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, ROULADE, diff_, _data_);
        assertEq(new Rate("23/10"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(BAIE_JABOCA);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("22/10"));
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, ROULADE, diff_, _data_);
        assertEq(new Rate("23/10"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(BAIE_JABOCA);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("22/10"));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, _data_);
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, ROULADE, diff_, _data_);
        assertEq(new Rate("0"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PEAU_DURE);
        fighter_.backUpObject(BAIE_JABOCA);
        fighter_ = fight_.getFighter(target_);
        fighter_.getRemainingHp().affectZero();
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, ROULADE, diff_, _data_);
        assertEq(new Rate("0"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(PIQUANTS);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("22/10"));
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, ROULADE, diff_, _data_);
        assertEq(new Rate("1873/800"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(PIQUANTS);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("22/10"));
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, ROULADE, diff_, _data_);
        assertEq(new Rate("0"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void recoilAgainstTarget2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = recoilAgainstTarget(diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.backUpObject(PIQUANTS);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("22/10"));
        FightEffects.recoilAgainstTarget(fight_, thrower_, target_, ROULADE, diff_, _data_);
        assertEq(new Rate("23/10"),fight_.getDamageKo());
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    private static Fight enableBoostEffectWhileKoTarget(Difficulty _diff) {
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
    public void enableBoostEffectWhileKoTarget1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableBoostEffectWhileKoTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        FightEffects.enableBoostEffectWhileKoTarget(fight_, thrower_, PISTOLET_A_O, _data_);
        fighter_ = fight_.getFighter(thrower_);
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
    public void enableBoostEffectWhileKoTarget2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableBoostEffectWhileKoTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        FightEffects.enableBoostEffectWhileKoTarget(fight_, thrower_, DARD_MORTEL, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }

    private static Fight enableAbilityWhileKoTarget(Difficulty _diff) {
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
    public void enableAbilityWhileKoTarget1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableAbilityWhileKoTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        FightEffects.enableAbilityWhileKoTarget(fight_, thrower_, _data_);
        fighter_ = fight_.getFighter(thrower_);
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
    public void enableAbilityWhileKoTarget2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableAbilityWhileKoTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(METEO);
        FightEffects.enableAbilityWhileKoTarget(fight_, thrower_, _data_);
        fighter_ = fight_.getFighter(thrower_);
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
    public void enableAbilityWhileKoTarget3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = enableAbilityWhileKoTarget(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        FightEffects.enableAbilityWhileKoTarget(fight_, thrower_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ACCURACY).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.EVASINESS).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.CRITICAL_HIT).intValue());
    }

    private static Fight effectDamage(Difficulty _diff) {
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
    public void effectDamage1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String move_ = PRESCIENCE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        Team equipeLanceur_ = fight_.getTeams().getVal(thrower_.getTeam());
        Anticipation attaqueAnticipe_=equipeLanceur_.getMovesAnticipation().getVal(move_).getVal(fighter_.getGroundPlace());
        assertEq(new Rate("1786/325"),attaqueAnticipe_.getDamage());
        assertTrue(attaqueAnticipe_.isIncrementing());
        assertEq(0, attaqueAnticipe_.getNbRounds());
        assertEq(Fight.FOE, attaqueAnticipe_.getTargetPosition().getTeam());
        assertEq(0, attaqueAnticipe_.getTargetPosition().getPosition());
        //assertTrue(!fighter_.getEnabledMovesConstChoices().getVal(move_).isEnabled());
        //1786/325
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(Rate.zero(), animation_.getDamage());
        assertEq(0, animation_.getTypes().size());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertEq(new Rate("92/5"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("92/5"),fight_.getDamageKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("92/5"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(DRAGON, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(animation_.isKoToFighter());
    }

    @Test
    public void effectDamage3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        String move_ = TONNERRE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("27788/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("21432/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("21432/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        //27788/2675
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("21432/2675"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ELECTRIQUE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.setRemainedHp(new Rate("15"));
        fighter_.backUpObject(NULL_REF);
        String move_ = DRACO_RAGE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(new Rate("15"), fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertEq(new Rate("92/5"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("92/5"),fight_.getDamageKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("92/5"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(DRAGON, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(animation_.isKoToFighter());
    }

    @Test
    public void effectDamage5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("15"));
        fighter_.backUpObject(GRELOT_COQUE);
        String move_ = DRACO_RAGE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(new Rate("173/10"), fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertEq(new Rate("92/5"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("92/5"),fight_.getDamageKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("92/5"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(DRAGON, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(animation_.isKoToFighter());
    }

    @Test
    public void effectDamage6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("15"));
        fighter_.backUpObject(GRELOT_COQUE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        String move_ = DRACO_RAGE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(new Rate("173/10"), fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertEq(new Rate("92/5"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("92/5"),fight_.getDamageKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("92/5"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(DRAGON, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(animation_.isKoToFighter());
    }

    @Test
    public void effectDamage7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("15"));
        fighter_.backUpObject(GRELOT_COQUE);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        String move_ = TONNERRE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(new Rate("37446/2675"), fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("27788/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("21432/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("21432/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("21432/2675"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ELECTRIQUE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.setRemainedHp(new Rate("1/2"));
        fighter_.backUpObject(GRELOT_COQUE);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        //fighter_.setRemainedHp(new Rate("1"));
        fighter_.setCurrentAbility(SUINTEMENT);
        String move_ = TONNERRE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        //assertEq(new Rate(37446/2675), fighter_.getRemainingHp());//21432/2675
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("27788/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("21432/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("21432/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("21432/2675"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ELECTRIQUE, animation_.getTypes().first());
        assertTrue(animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_.backUpObject(GRELOT_COQUE);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        //fighter_.setRemainedHp(new Rate("1"));
        fighter_.setCurrentAbility(COLERIQUE);
        String move_ = TONNERRE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        //assertEq(new Rate(37446/2675), fighter_.getRemainingHp());//21432/2675
        fighter_ = fight_.getFighter(target_);
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(new Rate("27788/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("21432/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("21432/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("21432/2675"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ELECTRIQUE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_.backUpObject(GRELOT_COQUE);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        //fighter_.setRemainedHp(new Rate("1"));
        fighter_.setCurrentAbility(ARMURBASTON);
        String move_ = TONNERRE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        //assertEq(new Rate(37446/2675), fighter_.getRemainingHp());//21432/2675
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("27788/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("21432/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("21432/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("21432/2675"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ELECTRIQUE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.backUpObject(BAIE_ORAN);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(ARMURBASTON);
        fighter_.backUpObject(BAIE_ORAN);
        String move_ = TONNERRE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("11"),fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("27788/2675"),fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
        assertEq(new Rate("21432/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("21432/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("21432/2675"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ELECTRIQUE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.backUpObject(BAIE_ORAN);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(ARMURBASTON);
        fighter_.setRemainedHp(new Rate("24107/2675"));
        fighter_.backUpObject(BAIE_ORAN);
        String move_ = TONNERRE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("11"),fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("11"),fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
        assertEq(new Rate("21432/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("21432/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("21432/2675"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ELECTRIQUE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        //fighter_.setRemainedHp(new Rate("1"));
        fighter_.backUpObject(BAIE_ORAN);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(ARMURBASTON);
        fighter_.backUpObject(BAIE_ORAN);
        String move_ = ROULADE;
        fight_.setInvokedMove(false);
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.getEnabledMovesConstChoices().getVal(move_).isEnabled());
        assertEq(0, fighter_.getEnabledMovesConstChoices().getVal(move_).getNbTurn());
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("203236/13375"),fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
        assertEq(new Rate("42864/13375"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("42864/13375"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("42864/13375"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ROCHE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_.backUpObject(BAIE_ORAN);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(ARMURBASTON);
        fighter_.backUpObject(BAIE_ORAN);
        fight_.setInvokedMove(true);
        String move_ = ROULADE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(!fighter_.getEnabledMovesConstChoices().getVal(move_).isEnabled());
        assertEq(0, fighter_.getEnabledMovesConstChoices().getVal(move_).getNbTurn());
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("203236/13375"),fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
        assertEq(new Rate("42864/13375"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("42864/13375"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("42864/13375"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ROCHE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.getEnabledMovesConstChoices().getVal(ROULADE).increment();
        fighter_.backUpObject(BAIE_ORAN);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(ARMURBASTON);
        fighter_.backUpObject(BAIE_ORAN);
        fight_.setInvokedMove(false);
        String move_ = ROULADE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.getEnabledMovesConstChoices().getVal(move_).isEnabled());
        assertEq(1, fighter_.getEnabledMovesConstChoices().getVal(move_).getNbTurn());
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("160372/13375"),fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
        assertEq(new Rate("85728/13375"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("85728/13375"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("85728/13375"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ROCHE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage16Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_.backUpObject(BAIE_ORAN);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(ARMURBASTON);
        fighter_.backUpObject(BAIE_ORAN);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        String move_ = DRACO_RAGE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
//        assertEq(POKEMON_FOE_TARGET_ONE, animation_.getToFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("92/5"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(DRAGON, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(animation_.isKoToFighter());
    }

    @Test
    public void effectDamage17Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLTAGE);
        fighter_.backUpObject(BAIE_ORAN);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(ARMURBASTON);
        fighter_.backUpObject(BAIE_ORAN);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, _data_);
        String move_ = DRACO_RAGE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
//        assertEq(POKEMON_FOE_TARGET_ONE, animation_.getToFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("92/5"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(DRAGON, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(animation_.isKoToFighter());
    }

    @Test
    public void effectDamage18Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PEAU_DURE);
        fighter_.backUpObject(BAIE_ORAN);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(ARMURBASTON);
        fighter_.backUpObject(BAIE_ORAN);
        fighter_.setFirstChosenMove(TENACITE);
        FightRound.initRound(fight_);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.successUsingMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, _data_);
        String move_ = ROULADE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
//        assertEq(POKEMON_FOE_TARGET_ONE, animation_.getToFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("1"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ROCHE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(animation_.isKoToFighter());
    }

    @Test
    public void effectDamage19Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.setRemainedHp(new Rate("1/2"));
        fighter_.backUpObject(GRELOT_COQUE);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        String move_ = TONNERRE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        assertTrue(FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("27788/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("21432/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("21432/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
//        assertEq(POKEMON_PLAYER_TARGET_ONE, animation_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("21432/2675"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ELECTRIQUE, animation_.getTypes().first());
        assertTrue(animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage20Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setItem(LICHEN_LUMINEUX);
        String move_ = PISTOLET_A_O;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(new Rate("21241/1300"),fighter_.getRemainingHp());
        assertEq(new Rate("2679/1300"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("2679/1300"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("2679/1300"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(EAU, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage21Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setItem(LICHEN_LUMINEUX);
        String move_ = TONNERRE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(new Rate("27788/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("21432/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("21432/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        //27788/2675
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("21432/2675"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ELECTRIQUE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage22Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setItem(VULNE_ASSURANCE);
        fighter_.setTypes(new StringList(FEU));
        String move_ = TONNERRE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(new Rate("6356/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("42864/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("42864/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        //27788/2675
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("42864/2675"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ELECTRIQUE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage23Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_ = fight_.getFighter(target_);
        fighter_.setItem(VULNE_ASSURANCE);
        fighter_.setTypes(new StringList(EAU));
        String move_ = TONNERRE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(new Rate("6356/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("42864/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("42864/2675"),fight_.getDamageKo());
        assertTrue(!fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("42864/2675"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ELECTRIQUE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage24Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_ = fight_.getFighter(target_);
        fighter_.setItem(BAIE_MANGA);
        fighter_.setTypes(new StringList(EAU));
        String move_ = TONNERRE;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(new Rate("6356/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("42864/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("42864/2675"),fight_.getDamageKo());
        assertTrue(!fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("42864/2675"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(ELECTRIQUE, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage25Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_ = fight_.getFighter(target_);
        fighter_.setItem(BAIE_MANGA);
        fighter_.setTypes(new StringList(EAU));
        String move_ = DOUBLE_PIED;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(new Rate("203236/13375"),fighter_.getRemainingHp());
        assertEq(new Rate("42864/13375"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("42864/13375"),fight_.getDamageKo());
        assertTrue(!fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("42864/13375"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(COMBAT, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage26Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.backUpObject(NULL_REF);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_ = fight_.getFighter(target_);
        fighter_.setItem(NULL_REF);
        fighter_.setTypes(new StringList(EAU));
        String move_ = DOUBLE_PIED;
        Effect effect_ = _data_.getMove(move_).getEffet(0);
        fight_.addEffect(thrower_, target_, effect_);
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(new Rate("203236/13375"),fighter_.getRemainingHp());
        assertEq(new Rate("42864/13375"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("42864/13375"),fight_.getDamageKo());
        assertTrue(!fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffectDamage animation_ = (AnimationEffectDamage) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertEq(new Rate("42864/13375"), animation_.getDamage());
        assertEq(1, animation_.getTypes().size());
        assertEq(COMBAT, animation_.getTypes().first());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectDamage1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String move_ = DRACO_RAGE;
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectDamage2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(PEAU_DURE);
        fighter_ = fight_.getFighter(target_);
        fighter_.setFirstChosenMove(TENACITE);
        FightRound.initRound(fight_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_ = fight_.getFighter(target_);
        fighter_.successUsingMove();
        fighter_.setRemainedHp(new Rate("1"));
        String move_ = ROULADE;
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(target_);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectDamage3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.setRemainedHp(new Rate("1/2"));
        fighter_.backUpObject(GRELOT_COQUE);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        String move_ = TONNERRE;
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("27788/2675"),fighter_.getRemainingHp());
        assertEq(new Rate("21432/2675"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("21432/2675"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectDamage4SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectDamage(diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(IMPUDENCE);
        fighter_.setRemainedHp(new Rate("1/2"));
        fighter_.backUpObject(GRELOT_COQUE);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(SUINTEMENT);
        String move_ = TONNERRE;
        FightEffects.effectDamage(fight_, thrower_, target_, move_, diff_, _data_);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("1123149/89300"),fighter_.getRemainingHp());
        assertEq(new Rate("27472/4465"),fight_.getDamageByCurrentUser().getVal(target_));
        assertEq(new Rate("27472/4465"),fight_.getDamageKo());
        assertTrue(fight_.getDamage().isCriticalHit());
        assertTrue(fight_.getAcceptableChoices());
    }
}
