package aiki.db;

import aiki.facade.enums.SelectedBoolean;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.fight.util.TypesDuo;
import aiki.game.fight.CheckNumericStringsFight;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.game.player.enums.Sex;
import aiki.instances.Instances;
import aiki.map.DataMap;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Place;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.TileMiniMap;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public final class DataBaseValidationTest extends DataBaseValidationCommon {
    private static final String TAB = "\t";
    private static final String MOVE_FORMULA = "move";
    private static final String CAT_FORMULA = "cat";
    private static final String STATIS_FORMULA = "statis";
    private static final String STATUS_FORMULA = "status";
    private static final String TYPE_FORMULA = "type";
    @Test
    public void failEvoTest() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKACHU2);
        data_.completeMembers(PIKACHU,pkData_);
        pkData_ = Instances.newPokemonData();
        pkData_.getEvolutions().addEntry(PIKACHU4,Instances.newEvolutionHappiness());
        pkData_.setBaseEvo(PIKACHU);
        data_.completeMembers(PIKACHU2,pkData_);
        pkData_ = Instances.newPokemonData();
        pkData_.getEvolutions().addEntry(PIKACHU4,Instances.newEvolutionHappiness());
        pkData_.setBaseEvo(PIKACHU);
        data_.completeMembers(PIKACHU3,pkData_);
        pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKACHU);
        data_.completeMembers(PIKACHU4,pkData_);
        pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKACHU3);
        data_.completeMembers(TREMPETTE,pkData_);
        pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKACHU4);
        data_.completeMembers(TREMPETTE2,pkData_);
        data_.validateEvolutions();
        assertTrue(data_.isError());
    }
    @Test
    public void fail1Test() {
        PokemonData pkData_ = Instances.newPokemonData();
        AbilityData ab_ = Instances.newAbilityData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage eff_ = Instances.newEffectDamage();
        eff_.setPower("INVALID");
        eff_.setFail("INVALID");
        eff_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(eff_);
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setTypes(new StringList(ELECTRICK));
        Ball ball_ = Instances.newBall();
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.completeMembers(ECLAIR,move_);
        pkData_.setTypes(new StringList(ELECTRICK));
        pkData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)0));
        pkData_.getLevMoves().add(new LevelMove((short)1,ECLAIR));
        data_.completeMembers(PIKACHU,pkData_);
        data_.completeMembers(POKE_BALL,ball_);
        data_.completeMembers(PARATONNERRE,ab_);
        data_.getTableTypes().put(new TypesDuo(ELECTRICK,ELECTRICK),new Rate("1"));
        data_.sortEndRound();
        data_.getMap().setPlaces(new ShortMap<Place>());
        data_.getMap().setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(2);
        DataMap map_ = data_.getMap();
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel((short) 7);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        data_.setRateBoost("-2");
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.initCombosTest();
        data_.initTypesByTable();
        data_.setCheckTranslation(false);
        CheckNumericStringsFight.validateNumericBooleanStrings(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail2Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.sortEndRound();
        data_.getMap().setPlaces(new ShortMap<Place>());
        data_.getMap().setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(-2);
        data_.getTypesColors().addEntry(ELECTRICK,ELECTRICK);
        data_.getTypesImages().addEntry(ELECTRICK,new int[0][0]);
        data_.setStorage(new int[1][1]);
        data_.setImageTmHm(new int[1][1]);
        data_.setAnimAbsorb(new int[1][1]);
        data_.completeMembers(PARATONNERRE,Instances.newStatusSimple());
        data_.getMiniPk().addEntry(TREMPETTE,new int[0][0]);
        data_.getMiniPk().addEntry(TREMPETTE2,new int[1][1]);
        data_.getAnimStatus().addEntry(TREMPETTE,new int[0][0]);
        data_.getAnimStatus().addEntry(TREMPETTE2,new int[1][1]);
        data_.getAnimStatis().addEntry(TREMPETTE,new int[0][0]);
        data_.getAnimStatis().addEntry(TREMPETTE2,new int[1][1]);
        data_.getMiniItems().addEntry(TREMPETTE,new int[0][0]);
        data_.getMiniItems().addEntry(TREMPETTE2,new int[1][1]);
        data_.getMaxiPkFront().addEntry(TREMPETTE,new int[0][0]);
        data_.getMaxiPkBack().addEntry(TREMPETTE,new int[0][0]);
        data_.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Sex.GIRL),new int[0][0]);
        data_.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Sex.BOY),new int[0][0]);
        data_.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Sex.GIRL),new int[0][0]);
        data_.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Sex.BOY),new int[0][0]);
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Sex.GIRL),new int[0][0]);
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Sex.BOY),new int[0][0]);
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,Sex.GIRL),new int[1][1]);
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,Sex.BOY),new int[1][1]);
        data_.getTrainers().addEntry(TREMPETTE,new int[0][0]);
        data_.getPeople().addEntry(TREMPETTE,new int[0][0]);
        data_.getPeople().addEntry(TREMPETTE2,new int[1][1]);
        data_.getLinks().addEntry(TREMPETTE,new int[0][0]);
        data_.getLinks().addEntry(TREMPETTE2,new int[1][1]);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail3Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectEndRoundIndividual effect_ = Instances.newEffectEndRoundIndividual();
        effect_.setEndRoundRank(1);
        move_.getEffects().add(effect_);
        move_.setTypes(new StringList(ELECTRICK));
        move_.setRankIncrementNbRound((short)1);
        move_.setCategory(DataBase.AUTRE);
        data_.completeMembers(TREMPETTE, move_);
        move_ = Instances.newDamagingMoveData();
        move_.getEffects().add(Instances.newEffectDamage());
        EffectStatus effectStatus_ = Instances.newEffectStatus();
        effectStatus_.setKoUserHealSubst(true);
        move_.getEffects().add(effectStatus_);
        data_.completeMembers(CHARGE, move_);
        data_.getTm().addEntry((short)0,TREMPETTE2);
        data_.getTm().addEntry((short)1,TREMPETTE2);
        data_.getTm().addEntry((short)2,LUTTE);
        data_.getTmPrice().addEntry((short)3, LgInt.newLgInt("-1"));
        data_.getHm().addEntry((short)0,TREMPETTE2);
        data_.getHm().addEntry((short)1,TREMPETTE2);
        data_.sortEndRound();
        data_.getMap().setPlaces(new ShortMap<Place>());
        data_.getMap().setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(-2);
        data_.getTableTypes().addEntry(new TypesDuo(ELECTRICK,TREMPETTE),Rate.newRate("0"));
        data_.getTableTypes().addEntry(new TypesDuo(ELECTRICK,ELECTRICK),Rate.newRate("-1"));
        data_.getTableTypes().addEntry(new TypesDuo(TREMPETTE,ELECTRICK),Rate.newRate("-1"));
        data_.completeVariables();
        initConstants(data_);
        data_.initCombosTest();
        data_.validateCore(new PerCentImpl());
        assertTrue(data_.isError());
    }

    @Test
    public void fail4Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedFctMath().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedStatus().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedPokemon().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedMoves().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedItems().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedClassesDescriptions().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedAbilities().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedTypes().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedCategories().addEntry(TREMPETTE, tr_);
        StringMap<EnumMap<EnvironmentType, String>> translatedEnvironment_ = data_.getTranslatedEnvironment();
        EnumMap<EnvironmentType, String> e_ = new EnumMap<EnvironmentType, String>();
        e_.addEntry(EnvironmentType.ROAD,TREMPETTE);
        e_.addEntry(EnvironmentType.WATER,TREMPETTE);
        translatedEnvironment_.addEntry(TREMPETTE, e_);
        StringMap<EnumMap<DifficultyModelLaw, String>> translatedDiffModelLaw_ = data_.getTranslatedDiffModelLaw();
        EnumMap<DifficultyModelLaw, String> dw_ = new EnumMap<DifficultyModelLaw, String>();
        dw_.addEntry(DifficultyModelLaw.CONSTANT_MAX,TREMPETTE);
        dw_.addEntry(DifficultyModelLaw.CONSTANT_MIN,TREMPETTE);
        translatedDiffModelLaw_.addEntry(TREMPETTE, dw_);
        StringMap<EnumMap<DifficultyWinPointsFight, String>> translatedDiffWinPts_ = data_.getTranslatedDiffWinPts();
        EnumMap<DifficultyWinPointsFight, String> dwp_ = new EnumMap<DifficultyWinPointsFight, String>();
        dwp_.addEntry(DifficultyWinPointsFight.DIFFICILE,TREMPETTE);
        dwp_.addEntry(DifficultyWinPointsFight.FACILE,TREMPETTE);
        translatedDiffWinPts_.addEntry(TREMPETTE, dwp_);
        StringMap<EnumMap<TargetChoice, String>> translatedTargets_ = data_.getTranslatedTargets();
        EnumMap<TargetChoice, String> targets_ = new EnumMap<TargetChoice, String>();
        targets_.addEntry(TargetChoice.LANCEUR,TREMPETTE);
        targets_.addEntry(TargetChoice.ANY_FOE,TREMPETTE);
        translatedTargets_.addEntry(TREMPETTE, targets_);
        StringMap<EnumMap<SelectedBoolean, String>> translatedBooleans_ = data_.getTranslatedBooleans();
        EnumMap<SelectedBoolean, String> bools_ = new EnumMap<SelectedBoolean, String>();
        bools_.addEntry(SelectedBoolean.YES,TREMPETTE);
        bools_.addEntry(SelectedBoolean.NO,TREMPETTE);
        translatedBooleans_.addEntry(TREMPETTE, bools_);
        StringMap<EnumMap<Gender, String>> translatedGenders_ = data_.getTranslatedGenders();
        EnumMap<Gender, String> genders_ = new EnumMap<Gender, String>();
        genders_.addEntry(Gender.FEMALE,TREMPETTE);
        genders_.addEntry(Gender.MALE,TREMPETTE);
        translatedGenders_.addEntry(TREMPETTE, genders_);
        StringMap<EnumMap<Statistic, String>> translatedStatistics_ = data_.getTranslatedStatistics();
        EnumMap<Statistic, String> stats_ = new EnumMap<Statistic, String>();
        stats_.addEntry(Statistic.HP,TREMPETTE);
        stats_.addEntry(Statistic.SPEED,TREMPETTE);
        translatedStatistics_.addEntry(TREMPETTE, stats_);
        data_.sortEndRound();
        data_.getMap().setPlaces(new ShortMap<Place>());
        data_.getMap().setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(-2);
        data_.completeVariables();
        initConstants(data_);
        data_.initCombosTest();
        data_.validateTranslations();
        assertTrue(data_.isError());
    }

    @Test
    public void fail5Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.completeMembers(TREMPETTE,Instances.newStatusSimple());
        data_.completeMembers(TREMPETTE,Instances.newDamagingMoveData());
        data_.completeMembers(TREMPETTE,Instances.newItemForBattle());
        data_.completeMembers(TREMPETTE,Instances.newPokemonData());
        data_.completeMembers(TREMPETTE,Instances.newAbilityData());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedFctMath().addEntry(LANGUAGE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedStatus().addEntry(LANGUAGE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedPokemon().addEntry(LANGUAGE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedMoves().addEntry(LANGUAGE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedItems().addEntry(LANGUAGE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedTypes().addEntry(LANGUAGE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedCategories().addEntry(LANGUAGE, tr_);
        StringMap<EnumMap<EnvironmentType, String>> translatedEnvironment_ = data_.getTranslatedEnvironment();
        EnumMap<EnvironmentType, String> e_ = new EnumMap<EnvironmentType, String>();
        e_.addEntry(EnvironmentType.ROAD,TREMPETTE);
        e_.addEntry(EnvironmentType.WATER,TREMPETTE);
        translatedEnvironment_.addEntry(LANGUAGE, e_);
        StringMap<EnumMap<DifficultyModelLaw, String>> translatedDiffModelLaw_ = data_.getTranslatedDiffModelLaw();
        EnumMap<DifficultyModelLaw, String> dw_ = new EnumMap<DifficultyModelLaw, String>();
        dw_.addEntry(DifficultyModelLaw.CONSTANT_MAX,TREMPETTE);
        dw_.addEntry(DifficultyModelLaw.CONSTANT_MIN,TREMPETTE);
        translatedDiffModelLaw_.addEntry(LANGUAGE, dw_);
        StringMap<EnumMap<DifficultyWinPointsFight, String>> translatedDiffWinPts_ = data_.getTranslatedDiffWinPts();
        EnumMap<DifficultyWinPointsFight, String> dwp_ = new EnumMap<DifficultyWinPointsFight, String>();
        dwp_.addEntry(DifficultyWinPointsFight.DIFFICILE,TREMPETTE);
        dwp_.addEntry(DifficultyWinPointsFight.FACILE,TREMPETTE);
        translatedDiffWinPts_.addEntry(LANGUAGE, dwp_);
        StringMap<EnumMap<TargetChoice, String>> translatedTargets_ = data_.getTranslatedTargets();
        EnumMap<TargetChoice, String> targets_ = new EnumMap<TargetChoice, String>();
        targets_.addEntry(TargetChoice.LANCEUR,TREMPETTE);
        targets_.addEntry(TargetChoice.ANY_FOE,TREMPETTE);
        translatedTargets_.addEntry(LANGUAGE, targets_);
        StringMap<EnumMap<SelectedBoolean, String>> translatedBooleans_ = data_.getTranslatedBooleans();
        EnumMap<SelectedBoolean, String> bools_ = new EnumMap<SelectedBoolean, String>();
        bools_.addEntry(SelectedBoolean.YES,TREMPETTE);
        bools_.addEntry(SelectedBoolean.NO,TREMPETTE);
        translatedBooleans_.addEntry(LANGUAGE, bools_);
        StringMap<EnumMap<Gender, String>> translatedGenders_ = data_.getTranslatedGenders();
        EnumMap<Gender, String> genders_ = new EnumMap<Gender, String>();
        genders_.addEntry(Gender.FEMALE,TREMPETTE);
        genders_.addEntry(Gender.MALE,TREMPETTE);
        translatedGenders_.addEntry(LANGUAGE, genders_);
        StringMap<EnumMap<Statistic, String>> translatedStatistics_ = data_.getTranslatedStatistics();
        EnumMap<Statistic, String> stats_ = new EnumMap<Statistic, String>();
        stats_.addEntry(Statistic.HP,TREMPETTE);
        stats_.addEntry(Statistic.SPEED,TREMPETTE);
        translatedStatistics_.addEntry(LANGUAGE, stats_);
        data_.sortEndRound();
        data_.getMap().setPlaces(new ShortMap<Place>());
        data_.getMap().setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(-2);
        data_.completeVariables();
        initConstants(data_);
        data_.initCombosTest();
        data_.validateTranslations();
        assertTrue(data_.isError());
    }

    @Test
    public void fail6Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.completeMembers(TREMPETTE,Instances.newStatusSimple());
        data_.completeMembers(TREMPETTE,Instances.newDamagingMoveData());
        data_.completeMembers(TREMPETTE,Instances.newItemForBattle());
        data_.completeMembers(TREMPETTE,Instances.newPokemonData());
        data_.completeMembers(TREMPETTE,Instances.newAbilityData());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedFctMath().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedStatus().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedPokemon().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedMoves().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedItems().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedClassesDescriptions().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedAbilities().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedTypes().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedCategories().addEntry(TREMPETTE, tr_);
        StringMap<EnumMap<EnvironmentType, String>> translatedEnvironment_ = data_.getTranslatedEnvironment();
        EnumMap<EnvironmentType, String> e_ = new EnumMap<EnvironmentType, String>();
        e_.addEntry(EnvironmentType.ROAD,TREMPETTE);
        e_.addEntry(EnvironmentType.WATER,TREMPETTE);
        translatedEnvironment_.addEntry(TREMPETTE, e_);
        StringMap<EnumMap<DifficultyModelLaw, String>> translatedDiffModelLaw_ = data_.getTranslatedDiffModelLaw();
        EnumMap<DifficultyModelLaw, String> dw_ = new EnumMap<DifficultyModelLaw, String>();
        dw_.addEntry(DifficultyModelLaw.CONSTANT_MAX,TREMPETTE);
        dw_.addEntry(DifficultyModelLaw.CONSTANT_MIN,TREMPETTE);
        translatedDiffModelLaw_.addEntry(TREMPETTE, dw_);
        StringMap<EnumMap<DifficultyWinPointsFight, String>> translatedDiffWinPts_ = data_.getTranslatedDiffWinPts();
        EnumMap<DifficultyWinPointsFight, String> dwp_ = new EnumMap<DifficultyWinPointsFight, String>();
        dwp_.addEntry(DifficultyWinPointsFight.DIFFICILE,TREMPETTE);
        dwp_.addEntry(DifficultyWinPointsFight.FACILE,TREMPETTE);
        translatedDiffWinPts_.addEntry(TREMPETTE, dwp_);
        StringMap<EnumMap<TargetChoice, String>> translatedTargets_ = data_.getTranslatedTargets();
        EnumMap<TargetChoice, String> targets_ = new EnumMap<TargetChoice, String>();
        targets_.addEntry(TargetChoice.LANCEUR,TREMPETTE);
        targets_.addEntry(TargetChoice.ANY_FOE,TREMPETTE);
        translatedTargets_.addEntry(TREMPETTE, targets_);
        StringMap<EnumMap<SelectedBoolean, String>> translatedBooleans_ = data_.getTranslatedBooleans();
        EnumMap<SelectedBoolean, String> bools_ = new EnumMap<SelectedBoolean, String>();
        bools_.addEntry(SelectedBoolean.YES,TREMPETTE);
        bools_.addEntry(SelectedBoolean.NO,TREMPETTE);
        translatedBooleans_.addEntry(TREMPETTE, bools_);
        StringMap<EnumMap<Gender, String>> translatedGenders_ = data_.getTranslatedGenders();
        EnumMap<Gender, String> genders_ = new EnumMap<Gender, String>();
        genders_.addEntry(Gender.FEMALE,TREMPETTE);
        genders_.addEntry(Gender.MALE,TREMPETTE);
        translatedGenders_.addEntry(TREMPETTE, genders_);
        StringMap<EnumMap<Statistic, String>> translatedStatistics_ = data_.getTranslatedStatistics();
        EnumMap<Statistic, String> stats_ = new EnumMap<Statistic, String>();
        stats_.addEntry(Statistic.HP,TREMPETTE);
        stats_.addEntry(Statistic.SPEED,TREMPETTE);
        translatedStatistics_.addEntry(TREMPETTE, stats_);
        data_.sortEndRound();
        data_.getMap().setPlaces(new ShortMap<Place>());
        data_.getMap().setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(-2);
        data_.completeVariables();
        initConstants(data_);
        data_.initCombosTest();
        data_.validateTranslations();
        assertTrue(data_.isError());
    }

    @Test
    public void fail7Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.completeMembers(Statistic.ATTACK.name(),Instances.newStatusSimple());
        data_.completeMembers(Gender.FEMALE.name(),Instances.newStatusSimple());
        data_.completeMembers(Gender.MALE.name(),Instances.newStatusSimple());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedFctMath().addEntry(LANGUAGE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedStatus().addEntry(LANGUAGE, tr_);
        StringMap<EnumMap<Statistic, String>> translatedStatistics_ = data_.getTranslatedStatistics();
        EnumMap<Statistic, String> stats_ = new EnumMap<Statistic, String>();
        stats_.addEntry(Statistic.ATTACK,TREMPETTE2);
        stats_.addEntry(Statistic.SPEED,TREMPETTE);
        translatedStatistics_.addEntry(LANGUAGE, stats_);
        StringMap<EnumMap<Gender, String>> translatedGenders_ = data_.getTranslatedGenders();
        EnumMap<Gender, String> genders_ = new EnumMap<Gender, String>();
        genders_.addEntry(Gender.FEMALE,TREMPETTE);
        genders_.addEntry(Gender.MALE,TREMPETTE);
        translatedGenders_.addEntry(LANGUAGE, genders_);
        data_.sortEndRound();
        data_.getMap().setPlaces(new ShortMap<Place>());
        data_.getMap().setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(-2);
        data_.completeVariables();
        initConstants(data_);
        data_.initCombosTest();
        data_.validateTranslations();
        assertTrue(data_.isError());
    }

    @Test
    public void fail8Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.completeMembers(Statistic.ATTACK.name(),Instances.newStatusSimple());
        data_.completeMembers(Gender.FEMALE.name(),Instances.newStatusSimple());
        data_.completeMembers(Gender.MALE.name(),Instances.newStatusSimple());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedFctMath().addEntry(TREMPETTE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedStatus().addEntry(TREMPETTE, tr_);
        StringMap<EnumMap<Statistic, String>> translatedStatistics_ = data_.getTranslatedStatistics();
        EnumMap<Statistic, String> stats_ = new EnumMap<Statistic, String>();
        stats_.addEntry(Statistic.ATTACK,TREMPETTE2);
        stats_.addEntry(Statistic.SPEED,TREMPETTE);
        translatedStatistics_.addEntry(TREMPETTE, stats_);
        StringMap<EnumMap<Gender, String>> translatedGenders_ = data_.getTranslatedGenders();
        EnumMap<Gender, String> genders_ = new EnumMap<Gender, String>();
        genders_.addEntry(Gender.FEMALE,TREMPETTE);
        genders_.addEntry(Gender.MALE,TREMPETTE);
        translatedGenders_.addEntry(TREMPETTE, genders_);
        data_.sortEndRound();
        data_.getMap().setPlaces(new ShortMap<Place>());
        data_.getMap().setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(-2);
        data_.completeVariables();
        initConstants(data_);
        data_.initCombosTest();
        data_.validateTranslations();
        assertTrue(data_.isError());
    }

    @Test
    public void fail9Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.completeMembers(Statistic.ATTACK.name(),Instances.newStatusSimple());
        data_.completeMembers(Gender.FEMALE.name(),Instances.newStatusSimple());
        data_.completeMembers(Gender.MALE.name(),Instances.newStatusSimple());
        StringMap<String> tr_ = new StringMap<String>();
        tr_.addEntry(TREMPETTE,TREMPETTE);
        tr_.addEntry(TREMPETTE2,TREMPETTE);
        data_.getTranslatedFctMath().addEntry(LANGUAGE, tr_);
        tr_ = new StringMap<String>();
        tr_.addEntry(Gender.FEMALE.name(),TREMPETTE2);
        tr_.addEntry(Gender.MALE.name(),TREMPETTE2);
        data_.getTranslatedStatus().addEntry(LANGUAGE, tr_);
        StringMap<EnumMap<Statistic, String>> translatedStatistics_ = data_.getTranslatedStatistics();
        EnumMap<Statistic, String> stats_ = new EnumMap<Statistic, String>();
        stats_.addEntry(Statistic.ATTACK,TREMPETTE2);
        stats_.addEntry(Statistic.SPEED,TREMPETTE);
        translatedStatistics_.addEntry(LANGUAGE, stats_);
        StringMap<EnumMap<Gender, String>> translatedGenders_ = data_.getTranslatedGenders();
        EnumMap<Gender, String> genders_ = new EnumMap<Gender, String>();
        genders_.addEntry(Gender.FEMALE,TREMPETTE);
        genders_.addEntry(Gender.MALE,TREMPETTE);
        translatedGenders_.addEntry(LANGUAGE, genders_);
        data_.sortEndRound();
        data_.getMap().setPlaces(new ShortMap<Place>());
        data_.getMap().setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(-2);
        data_.completeVariables();
        initConstants(data_);
        data_.initCombosTest();
        data_.validateTranslations();
        assertTrue(data_.isError());
    }
    @Test
    public void fail10Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.getVariables().add(TREMPETTE);
        data_.getLitterals().addEntry(LANGUAGE,new StringMap<String>());
        initConstants(data_);
        data_.initCombosTest();
        data_.validateTranslations();
        assertTrue(data_.isError());
    }
    @Test
    public void fail11Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.getVariables().add(StringList.concat(DataBase.VAR_PREFIX,TREMPETTE));
        data_.getVariables().add(StringList.concat(DataBase.VAR_PREFIX,TREMPETTE2));
        data_.getVariables().add(StringList.concat(DataBase.VAR_PREFIX,TREMPETTE3,DataBase.SEP_BETWEEN_KEYS,TREMPETTE));
        data_.getVariables().add(StringList.concat(DataBase.VAR_PREFIX,CHARGE2,DataBase.SEP_BETWEEN_KEYS,DataBase.AUTRE));
        data_.getCategories().add(DataBase.AUTRE);
        StringMap<String> litt_ = new StringMap<String>();
        litt_.addEntry(TREMPETTE,"");
        litt_.addEntry(TREMPETTE2,StringList.concat(TAB,TAB));
        litt_.addEntry(TREMPETTE3,StringList.concat(MOVE_FORMULA,TAB,TAB));
        litt_.addEntry(CHARGE2,StringList.concat(CAT_FORMULA,TAB,TAB));
        data_.getLitterals().addEntry(LANGUAGE, litt_);
        initConstants(data_);
        data_.initCombosTest();
        data_.validateTranslations();
        assertTrue(data_.isError());
    }
    @Test
    public void fail12Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.getVariables().add(StringList.concat(DataBase.VAR_PREFIX,TREMPETTE));
        data_.getVariables().add(StringList.concat(DataBase.VAR_PREFIX,TREMPETTE2));
        data_.getVariables().add(StringList.concat(DataBase.VAR_PREFIX,TREMPETTE3,DataBase.SEP_BETWEEN_KEYS,TREMPETTE));
        data_.getVariables().add(StringList.concat(DataBase.VAR_PREFIX,CHARGE2,DataBase.SEP_BETWEEN_KEYS,DataBase.AUTRE));
        StringMap<String> litt_ = new StringMap<String>();
        litt_.addEntry(TREMPETTE,"");
        litt_.addEntry(TREMPETTE2,StringList.concat(TAB,TAB));
        litt_.addEntry(TREMPETTE3,StringList.concat(MOVE_FORMULA,TAB,TAB));
        litt_.addEntry(CHARGE2,StringList.concat(CAT_FORMULA,TAB,TAB));
        data_.getLitterals().addEntry(LANGUAGE, litt_);
        initConstants(data_);
        data_.initCombosTest();
        data_.validateTranslations();
        assertTrue(data_.isError());
    }

    @Test
    public void fail13Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.getVariables().add(StringList.concat(DataBase.VAR_PREFIX,TREMPETTE,DataBase.SEP_BETWEEN_KEYS,CHARGE3,DataBase.SEP_BETWEEN_KEYS,CHARGE3));
        data_.getVariables().add(StringList.concat(DataBase.VAR_PREFIX,TREMPETTE2,DataBase.SEP_BETWEEN_KEYS,CHARGE4));
        data_.getVariables().add(StringList.concat(DataBase.VAR_PREFIX,TREMPETTE3,DataBase.SEP_BETWEEN_KEYS,TREMPETTE));
        data_.getVariables().add(StringList.concat(DataBase.VAR_PREFIX,CHARGE2,DataBase.SEP_BETWEEN_KEYS,DataBase.AUTRE));
        StringMap<String> litt_ = new StringMap<String>();
        litt_.addEntry(TREMPETTE,StringList.concat(TYPE_FORMULA,TAB,TAB));
        litt_.addEntry(TREMPETTE2,StringList.concat(TYPE_FORMULA,TAB,TAB));
        litt_.addEntry(TREMPETTE3,StringList.concat(STATIS_FORMULA,TAB,TAB));
        litt_.addEntry(CHARGE2,StringList.concat(STATUS_FORMULA,TAB,TAB));
        data_.getLitterals().addEntry(LANGUAGE, litt_);
        initConstants(data_);
        data_.initCombosTest();
        data_.validateTranslations();
        assertTrue(data_.isError());
    }

    @Test
    public void fail14Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.addConstNumTest(DataBase.MAX_EV, new Rate(-20));
        data_.addConstNumTest(DataBase.MAX_IV, new Rate(-31));
        data_.addConstNumTest("DEF_MAX_ATT", new Rate(-4));
        data_.addConstNumTest(DataBase.DEF_PKEQ, new Rate(-6));
        data_.addConstNumTest(DataBase.ARGENT, new Rate(-3000));
        data_.addConstNumTest(DataBase.NIVEAU_PK_ECLOSION, new Rate(-1));
        data_.addConstNumTest(DataBase.NIVEAU_PK_MAX, new Rate(-100));
        data_.addConstNumTest(DataBase.EVO_BONHEUR, new Rate(-110));
        data_.addConstNumTest(DataBase.MAX_BONHEUR, new Rate(-170));
        data_.addConstNumTest(DataBase.GAIN_BONHEUR_NIV, new Rate(-2));
        data_.addConstNumTest(DataBase.PAS_NECES_INCREMENT_BONHEUR, new Rate(-10));
        data_.addConstNumTest(DataBase.PP_MAX, new Rate(-80));
        data_.addConstNumTest(DataBase.VALEUR_DEF_STATIS, new Rate(0));
        data_.addConstNumTest(DataBase.MAX_BOOST, new Rate(-6));
        data_.addConstNumTest(DataBase.MIN_BOOST, new Rate(6));
        data_.addConstNumTest(DataBase.MIN_HP, new Rate(-1));
        data_.addConstNumTest(DataBase.BONUS_BOOST, new Rate("-3/2"));
        data_.addConstNumTest(DataBase.MAX_STEPS, new Rate("-1024"));
        data_.addConstNumTest(DataBase.MAX_STEPS_SAME_EVO_BASE, new Rate("-256"));
        data_.initDefaultConsts(POKE_BALL,
                "",
                "",
                "",
                "",
                "",
                LUTTE,
                "");
        data_.validateConstants();
        assertTrue(data_.isError());
    }

    @Test
    public void fail15Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.addConstNumTest(DataBase.MAX_EV, new Rate(2000000));
        data_.addConstNumTest(DataBase.MAX_IV, new Rate(2000000));
        data_.addConstNumTest("DEF_MAX_ATT", new Rate(2000000));
        data_.addConstNumTest(DataBase.DEF_PKEQ, new Rate(100));
        data_.addConstNumTest(DataBase.ARGENT, new Rate(0));
        data_.addConstNumTest(DataBase.NIVEAU_PK_ECLOSION, new Rate(2000000));
        data_.addConstNumTest(DataBase.NIVEAU_PK_MAX, new Rate(2000000));
        data_.addConstNumTest(DataBase.EVO_BONHEUR, new Rate(2000000));
        data_.addConstNumTest(DataBase.MAX_BONHEUR, new Rate(1000000));
        data_.addConstNumTest(DataBase.GAIN_BONHEUR_NIV, new Rate(2000000));
        data_.addConstNumTest(DataBase.PAS_NECES_INCREMENT_BONHEUR, new Rate(20000));
        data_.addConstNumTest(DataBase.PP_MAX, new Rate(2000000));
        data_.addConstNumTest(DataBase.VALEUR_DEF_STATIS, new Rate(0));
        data_.addConstNumTest(DataBase.MAX_BOOST, new Rate(2000000));
        data_.addConstNumTest(DataBase.MIN_BOOST, new Rate(2000000));
        data_.addConstNumTest(DataBase.MIN_HP, new Rate(-1));
        data_.addConstNumTest(DataBase.BONUS_BOOST, new Rate("-3/2"));
        data_.addConstNumTest(DataBase.MAX_STEPS, new Rate("20000"));
        data_.addConstNumTest(DataBase.MAX_STEPS_SAME_EVO_BASE, new Rate("-256"));
        data_.initDefaultConsts(POKE_BALL,
                "",
                "",
                "",
                "",
                "",
                LUTTE,
                "");
        data_.validateConstants();
        assertTrue(data_.isError());
    }
    @Test
    public void nextIteration1Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(Instances.newEffectStatistic());
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration2Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration3Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.ALLIES);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration4Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.ALLIE);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration5Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.ADJ_MULT);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.ADJ_MULT);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration6Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration7Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.GLOBALE);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.GLOBALE);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration8Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration9Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.getStatisVarRank().addEntry(Statistic.ATTACK, (byte) 1);
        effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration10Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.getStatisVarRank().addEntry(Statistic.ATTACK, (byte) -1);
        effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration11Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.ADJ_MULT);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.ADJ_MULT);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.ADJ_MULT);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration12Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.ADJ_ADV);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.ADJ_ADV);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.ADJ_ADV);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration13Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.ADJ_UNIQ);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.ADJ_UNIQ);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration14Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration15Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.GLOBALE);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.GLOBALE);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.GLOBALE);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration16Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration17Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.setTargetChoice(TargetChoice.TOUS_ADV);
        effectStatistic_.getStatisVarRank().addEntry(Statistic.EVASINESS, (byte) -1);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration18Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        EffectStatus effectStatistic_ = Instances.newEffectStatus();
        effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
        effectStatistic_.getLawStatus().addEvent(TREMPETTE,LgInt.one());
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration19Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        EffectStatus effectStatistic_ = Instances.newEffectStatus();
        effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration20Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        EffectStatus effectStatistic_ = Instances.newEffectStatus();
        effectStatistic_.setTargetChoice(TargetChoice.ANY_FOE);
        move_.getEffects().add(effectStatistic_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration21Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        EffectDamageRate effectDamageRate_ = Instances.newEffectDamageRate();
        effectDamageRate_.setTargetChoice(TargetChoice.LANCEUR);
        effectDamageRate_.setRateDamage(Rate.newRate("1"));
        move_.getEffects().add(effectDamageRate_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(!DataBase.nextIteration(move_,0));
    }
    @Test
    public void nextIteration22Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        EffectDamageRate effectDamageRate_ = Instances.newEffectDamageRate();
        effectDamageRate_.setTargetChoice(TargetChoice.LANCEUR);
        effectDamageRate_.setRateDamage(Rate.newRate("-1"));
        move_.getEffects().add(effectDamageRate_);
        data_.completeMembers(CHARGE, move_);
        assertTrue(DataBase.nextIteration(move_,0));
    }
    @Test
    public void strongMoves1Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("50");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("1");
        data_.completeMembers(CHARGE,move_);
        assertTrue(!data_.strongMoves(Rate.newRate("40")).isEmpty());
    }
    @Test
    public void strongMoves01Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("50");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.getEffects().add(Instances.newEffectStatistic());
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("1");
        data_.completeMembers(CHARGE,move_);
        assertTrue(!data_.strongMoves(Rate.newRate("40")).isEmpty());
    }
    @Test
    public void strongMoves02Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("30");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.getEffects().add(Instances.newEffectStatistic());
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("1");
        data_.completeMembers(CHARGE2,move_);
        move_ = Instances.newDamagingMoveData();
        effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("50");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.getEffects().add(Instances.newEffectStatistic());
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("1");
        data_.completeMembers(CHARGE,move_);
        assertTrue(!data_.strongMoves(Rate.newRate("40")).isEmpty());
    }
    @Test
    public void strongMoves03Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("50");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.getEffects().add(Instances.newEffectStatistic());
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("1");
        data_.completeMembers(CHARGE2,move_);
        move_ = Instances.newDamagingMoveData();
        effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("30");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.getEffects().add(Instances.newEffectStatistic());
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("1");
        data_.completeMembers(CHARGE,move_);
        assertTrue(!data_.strongMoves(Rate.newRate("40")).isEmpty());
    }
    @Test
    public void strongMoves04Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("50");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.getEffects().add(Instances.newEffectStatistic());
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("1");
        data_.completeMembers(CHARGE2,move_);
        move_ = Instances.newDamagingMoveData();
        effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("30");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.getEffects().add(Instances.newEffectStatistic());
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("1");
        data_.completeMembers(CHARGE,move_);
        data_.getTypes().add(ELECTRICK);
        assertTrue(!data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves2Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("50");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("1");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves3Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("1");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves4Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setFail("V");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.getEffects().add(Instances.newEffectStatistic());
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("1");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves5Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setFail("V");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.getEffects().add(Instances.newEffectStatistic());
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("1/2");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves6Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setFail("V");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        move_.getEffects().add(Instances.newEffectStatistic());
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("VAR");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves7Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setFail("V");
        effectDamage_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(effectDamage_);
        EffectStatistic effectStatistic_ = Instances.newEffectStatistic();
        effectStatistic_.getStatisVarRank().addEntry(Statistic.ATTACK, (byte) -1);
        effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
        move_.getEffects().add(effectStatistic_);
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setAccuracy("VAR");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves8Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setFail("V");
        effectDamage_.setTargetChoice(TargetChoice.ADJ_MULT);
        move_.getEffects().add(effectDamage_);
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.ADJ_MULT);
        move_.setAccuracy("VAR");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves9Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setFail("V");
        effectDamage_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.getEffects().add(effectDamage_);
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.setAccuracy("VAR");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves10Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setFail("V");
        effectDamage_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.getEffects().add(effectDamage_);
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.setConstUserChoice(true);
        move_.setAccuracy("VAR");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves11Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setFail("V");
        effectDamage_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.getEffects().add(effectDamage_);
        move_.setTypes(new StringList(ELECTRICK,TREMPETTE));
        move_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.setConstUserChoice(true);
        move_.setAccuracy("VAR");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves12Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setFail("V");
        effectDamage_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.getEffects().add(effectDamage_);
        move_.setTypes(new StringList(ELECTRICK,TREMPETTE));
        move_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.setRechargeRound(true);
        move_.setAccuracy("VAR");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves13Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setFail("V");
        effectDamage_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.getEffects().add(effectDamage_);
        move_.setTypes(new StringList(ELECTRICK,TREMPETTE));
        move_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.setNbPrepaRound((short) 1);
        move_.setAccuracy("VAR");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves14Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setFail("V");
        effectDamage_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.getEffects().add(effectDamage_);
        move_.setTypes(new StringList(ELECTRICK,TREMPETTE));
        move_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.setPriority((byte) -1);
        move_.setAccuracy("VAR");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
    @Test
    public void strongMoves15Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        StatusMoveData move_ = Instances.newStatusMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("VAR");
        effectDamage_.setFail("V");
        effectDamage_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.getEffects().add(effectDamage_);
        move_.setTypes(new StringList(ELECTRICK,TREMPETTE));
        move_.setTargetChoice(TargetChoice.PSEUDO_GLOBALE);
        move_.setPriority((byte) -1);
        move_.setAccuracy("VAR");
        data_.completeMembers(CHARGE,move_);
        assertTrue(data_.strongMoves(Rate.newRate("60")).isEmpty());
    }
}
