package aiki.gui;

import aiki.db.DataBase;
import aiki.db.ImageHeroKey;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Item;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.fight.util.TypesDuo;
import aiki.game.Game;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.game.player.enums.Sex;
import aiki.instances.Instances;
import aiki.map.DataMap;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.GerantPokemon;
import aiki.map.characters.Person;
import aiki.map.characters.Seller;
import aiki.map.characters.enums.GeranceType;
import aiki.map.characters.enums.SellType;
import aiki.map.enums.Direction;
import aiki.map.levels.Block;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.Road;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LawNumber;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.DefaultGenerator;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbGuiAiki extends EquallableAikiGuiUtil {
    public static final String MINI6 = "mini6";
    public static final String MINI5 = "mini5";
    public static final String MINI4 = "mini4";
    public static final String MINI3 = "mini3";
    public static final String MINI2 = "mini2";
    public static final String MINI1 = "mini1";
    public static final String MINI = "mini";
    public static final String PIKACHU ="PIKACHU";
    public static final String RAICHU ="RAICHU";
    public static final String EVOLI ="EVOLI";
    public static final String PROG_PK2="PK_2";
    public static final String ECLAIR = "ECLAIR";
    public static final String ECLAIR_2 = "ECLAIR2";
    public static final String ECLAIR_3 = "ECLAIR3";
    public static final String ECLAIR_4 = "AMOUR";
    public static final String ECLAIR_5 = "TOI";
    public static final String ECLAIR_6 = "BOLT";
    public static final String ECLAIR_7 = "PIZZA";
    public static final String PARATONNERRE = "PARATONNERRE";
    public static final String PARAFEU = "PARAFEU";
    public static final String ELECTRICK = "ELECTRICK";
    public static final String POKE_BALL = "POKE_BALL";
    public static final String HUILE = "HUILE";
    public static final String HUILE_MAX = "HUILE_MAX";
    public static final String ALLY = "ally";
    public static final String SNOW = "snow";
    public static final String GRASS = "grass";
    public static final String DAFAULT = "dafault";
    public static final String GERANT = "gerant";
    public static final String LINK = "link";
    public static final String TRAINER_TWO = "trainer_two";
    public static final String TRAINER_ONE = "trainer_one";
    public static final String PERSON = "person";
    public static final String TRAINER = "trainer";
    public static final String DEFAULT = "default";
    public static final String NOTHING = "nothing";
    public static final String DESERT = "desert";
    public static final String ROCK = "rock";
    public static final String WATER = "water";
    public static final String ROAD = "road";
    public static final String BUILDING = "building";
    public static final String NULL_REF = DataBase.EMPTY_STRING;
    public static final String TAB = "\t";
    public static final String PIKACHU_TR = "chou";
    public static final String RAICHU_TR = "rey";

    public static Game build(FacadeGame _db) {
        return build(_db.getData());
    }

    public static void loadRom(WindowAiki _window, DataBase _db) {
        _window.getFacade().setData(_db);
    }
    public static Game build(DataBase _db) {
        Game g_ = new Game(_db);
        g_.initUserInteract("__", Sex.NO,g_.getDifficulty(), _db);
        return g_;
    }

    public static DataBase coreDataBase() {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        DataBase mv_ = withMv(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz");
        DataBase res_ = withPk(mv_, PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball");
        initBegin(data_);

        data_.getMap().addPlace(withBlocks(Instances.newRoad()));


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        compute(data_);


        return ball_;
    }

    public static DataBase coreDataBaseCity(Person _interact) {
        DataBase data_ = init();
        data_.addPerson(GERANT,new int[][]{new int[1]});
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        DataBase mv_ = withMv(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz");
        DataBase res_ = withPk(mv_, PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball");
        initBeginCity(data_);

        data_.getMap().addPlace(withBlocksPkCenter(Instances.newCity(),_interact));


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        compute(data_);


        return ball_;
    }

    public static DataBase coreDataBaseIt(StringMap<String> _trsIt, StringMap<String> _trsPk) {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, _trsPk);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, _trsIt);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        DataBase mv_ = withMv(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz");
        return withPk(mv_, PIKACHU, _trsPk, PIKACHU_TR);
    }

    public static DataBase coreDataBaseItEvo() {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        return data_;
    }
    public static void compute(DataBase _data) {
        _data.calculateAvgPound();
        _data.boundsPk();
        _data.setupPseudoImages();
        _data.completeVariables();
        _data.initTypesByTable();
        _data.completeMoveTutors();
        _data.getMap().initializeLinks();
        _data.getMap().initInteractiveElements();
        _data.getMap().initializeTree();
        _data.getMap().initializeAccessibility();
        _data.initializeWildPokemon();
        _data.initFamilies();
    }

    public static DataBase init() {
        DataBase data_ = new DataBase(DefaultGenerator.oneElt());
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initTranslations();
        data_.initializeMembers();
        initConstants(data_);
        initExpPoints(data_);
        initRandomLaws(data_);
        initTranslationsEnum(data_);
        data_.getTableTypes().addEntry(new TypesDuo(ELECTRICK,ELECTRICK),Rate.one());
        data_.setMap(Instances.newDataMap());
        data_.setCombos(Instances.newCombos());
        data_.getMap().setSideLength(1);
        data_.getConstNum().addEntry(DataBase.STRONG_MOVE,Rate.newRate("90"));
        data_.getAnimStatis().addEntry(Statistic.ATTACK.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.SPECIAL_ATTACK.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.DEFENSE.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.SPECIAL_DEFENSE.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.SPEED.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.ACCURACY.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.EVASINESS.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.CRITICAL_HIT.getStatName(), new int[][]{new int[]{3}});
        data_.getTypesImages().addEntry(ELECTRICK, new int[][]{new int[1]});
        data_.getTypesColors().addEntry(ELECTRICK,"255;255;0");
        data_.setEndGameImage(new int[][]{new int[1]});
        data_.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Sex.NO), new int[][]{new int[1]});
        data_.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Sex.NO), new int[][]{new int[1]});
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.UP, Sex.NO), new int[][]{new int[1]});
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.DOWN, Sex.NO), new int[][]{new int[1]});
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.LEFT, Sex.NO), new int[][]{new int[1]});
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.RIGHT, Sex.NO), new int[][]{new int[1]});
        data_.addImage(ROAD, new int[][]{new int[1]});
        return data_;
    }
    public static Road withBlocks(Road _road) {
        _road.setName("___");
        _road.getLevel().getBlocks().addEntry(newPoint(0, 0), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        _road.getLevel().getBlocks().addEntry(newPoint(0, 1), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        _road.getLevel().getBlocks().addEntry(newPoint(0, 2), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        _road.getLevel().getBlocks().addEntry(newPoint(1, 0), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        _road.getLevel().getBlocks().addEntry(newPoint(1, 1), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        _road.getLevel().getBlocks().addEntry(newPoint(1, 2), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        _road.getLevel().getBlocks().addEntry(newPoint(2, 0), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        _road.getLevel().getBlocks().addEntry(newPoint(2, 1), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        _road.getLevel().getBlocks().addEntry(newPoint(2, 2), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        return _road;
    }
    public static City withBlocksPkCenter(City _city, Person _gerant) {
        return withBlocksPkCenter(newPoint(0,0),_city,_gerant);
    }
    public static City withBlocksPkCenter(Point _pt,City _city, Person _gerant) {
        _city.setName("___");
        PokemonCenter center_ = Instances.newPokemonCenter();
        _city.getBuildings().addEntry(_pt, center_);
        center_.setExitCity(newPoint(-1,-1));
        center_.getIndoor().getGerants().addEntry(newPoint(1, 1),_gerant);
        center_.getIndoor().getBlocks().addEntry(newPoint(0, 0), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        center_.getIndoor().getBlocks().addEntry(newPoint(0, 1), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        center_.getIndoor().getBlocks().addEntry(newPoint(0, 2), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        center_.getIndoor().getBlocks().addEntry(newPoint(1, 0), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        center_.getIndoor().getBlocks().addEntry(newPoint(1, 1), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        center_.getIndoor().getBlocks().addEntry(newPoint(1, 2), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        center_.getIndoor().getBlocks().addEntry(newPoint(2, 0), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        center_.getIndoor().getBlocks().addEntry(newPoint(2, 1), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        center_.getIndoor().getBlocks().addEntry(newPoint(2, 2), newBlock(1, 1, EnvironmentType.ROAD, ROAD, -1));
        return _city;
    }
    public static DataBase withPk(DataBase _data, String _key, StringMap<String> _trs, String _val) {
        _data.completeQuickMembers(_key,pkData(_data,_key));
        _data.getMiniPk().addEntry(_key,new int[][]{new int[1]});
        _data.getMaxiPkBack().addEntry(_key,new int[][]{new int[1]});
        _data.getMaxiPkFront().addEntry(_key,new int[][]{new int[1]});
        _trs.addEntry(_key,_val);
        return _data;
    }
    public static DataBase withMv(DataBase _data, String _key, StringMap<String> _trs, String _val) {
        _data.completeQuickMembers(_key,def());
        _trs.addEntry(_key,_val);
        return _data;
    }

    public static DataBase withAb(DataBase _data, String _key, StringMap<String> _trs, String _val) {
        _data.completeQuickMembers(_key,Instances.newAbilityData());
        _trs.addEntry(_key,_val);
        return _data;
    }

    public static DataBase withIt(DataBase _data, String _key, StringMap<String> _trs, String _val) {
        Ball ball_ = Instances.newBall();
        ball_.setCatchingRate("1");
        return withIt(_data, _key, _trs, _val, ball_);
    }

    public static DataBase withIt(DataBase _data, String _key, StringMap<String> _trs, String _val, Item _it) {
        _data.completeQuickMembers(_key, _it);
        _trs.addEntry(_key,_val);
        _data.getMiniItems().addEntry(_key,new int[][]{new int[1]});
        return _data;
    }

    public static DataBase withIt(DataBase _data, String _key, StringMap<String> _trs, String _val, Item _it, StringMap<String> _trsDesc, String _valDesc) {
        _data.completeQuickMembers(_key, _it);
        _trs.addEntry(_key,_val);
        _data.getMiniItems().addEntry(_key,new int[][]{new int[1]});
        _trsDesc.addEntry(_it.getItemType(),_valDesc);
        return _data;
    }

    public static void initConstants(DataBase _data) {
        _data.addConstNumTest(DataBase.MAX_EV, new Rate(20));
        _data.addConstNumTest(DataBase.MAX_IV, new Rate(31));
        _data.addConstNumTest(DataBase.DEF_MAX_ATT, new Rate(4));
        _data.addConstNumTest(DataBase.DEF_PKEQ, new Rate(6));
        _data.addConstNumTest(DataBase.ARGENT, new Rate(3000));
        _data.addConstNumTest(DataBase.NIVEAU_PK_ECLOSION, new Rate(1));
        _data.addConstNumTest(DataBase.NIVEAU_PK_MAX, new Rate(100));
        _data.addConstNumTest(DataBase.EVO_BONHEUR, new Rate(110));
        _data.addConstNumTest(DataBase.MAX_BONHEUR, new Rate(170));
        _data.addConstNumTest(DataBase.GAIN_BONHEUR_NIV, new Rate(2));
        _data.addConstNumTest(DataBase.PAS_NECES_INCREMENT_BONHEUR, new Rate(10));
        _data.addConstNumTest(DataBase.PP_MAX, new Rate(80));
        _data.addConstNumTest(DataBase.VALEUR_DEF_STATIS, new Rate(0));
        _data.addConstNumTest(DataBase.MAX_BOOST, new Rate(6));
        _data.addConstNumTest(DataBase.MIN_BOOST, new Rate(-6));
        _data.addConstNumTest(DataBase.MIN_HP, new Rate(1));
        _data.addConstNumTest(DataBase.BONUS_BOOST, new Rate("3/2"));
        _data.addConstNumTest(DataBase.MAX_STEPS, new Rate("1024"));
        _data.addConstNumTest(DataBase.MAX_STEPS_SAME_EVO_BASE, new Rate("256"));
        _data.addConstNumTest(DataBase.DEF_BASE_MOVE, new Rate("0"));
    }
    public static void initExpPoints(DataBase _data) {
        _data.getExpGrowth().addEntry(ExpType.E,"VAR__NIVEAU");
        _data.getExpGrowth().addEntry(ExpType.L,"VAR__NIVEAU");
        _data.getExpGrowth().addEntry(ExpType.M,"VAR__NIVEAU");
        _data.getExpGrowth().addEntry(ExpType.P,"VAR__NIVEAU");
        _data.getExpGrowth().addEntry(ExpType.F,"VAR__NIVEAU");
        _data.getExpGrowth().addEntry(ExpType.R,"VAR__NIVEAU");
        _data.getRates().addEntry(DifficultyWinPointsFight.TRES_FACILE, "4");
        _data.getRates().addEntry(DifficultyWinPointsFight.FACILE, "2");
        _data.getRates().addEntry(DifficultyWinPointsFight.DIFFICILE, "1");
        _data.getRates().addEntry(DifficultyWinPointsFight.TRES_DIFFICILE, "1/2");
    }
    public static void initTranslationsEnum(DataBase _data) {
        IdMap<SelectedBoolean,String> bools_;
        bools_ = new IdMap<SelectedBoolean,String>();
        bools_.addEntry(SelectedBoolean.YES, SelectedBoolean.YES.getBoolName());
        bools_.addEntry(SelectedBoolean.NO, SelectedBoolean.NO.getBoolName());
        bools_.addEntry(SelectedBoolean.YES_AND_NO, SelectedBoolean.YES_AND_NO.getBoolName());
        _data.getTranslatedBooleans().addEntry(LANGUAGE, bools_);
        IdMap<DifficultyWinPointsFight,String> diffsWin_;
        diffsWin_ = new IdMap<DifficultyWinPointsFight,String>();
        diffsWin_.addEntry(DifficultyWinPointsFight.TRES_FACILE, DifficultyWinPointsFight.TRES_FACILE.getWinName());
        diffsWin_.addEntry(DifficultyWinPointsFight.FACILE, DifficultyWinPointsFight.FACILE.getWinName());
        diffsWin_.addEntry(DifficultyWinPointsFight.DIFFICILE, DifficultyWinPointsFight.DIFFICILE.getWinName());
        diffsWin_.addEntry(DifficultyWinPointsFight.TRES_DIFFICILE, DifficultyWinPointsFight.TRES_DIFFICILE.getWinName());
        _data.getTranslatedDiffWinPts().addEntry(LANGUAGE, diffsWin_);
        IdMap<DifficultyModelLaw,String> diffsLaw_;
        diffsLaw_ = new IdMap<DifficultyModelLaw,String>();
        diffsLaw_.addEntry(DifficultyModelLaw.CONSTANT_MIN, DifficultyModelLaw.CONSTANT_MIN.getModelName());
        diffsLaw_.addEntry(DifficultyModelLaw.CROISSANT, DifficultyModelLaw.CROISSANT.getModelName());
        diffsLaw_.addEntry(DifficultyModelLaw.UNIFORME, DifficultyModelLaw.UNIFORME.getModelName());
        diffsLaw_.addEntry(DifficultyModelLaw.DECROISSANT, DifficultyModelLaw.DECROISSANT.getModelName());
        diffsLaw_.addEntry(DifficultyModelLaw.CONSTANT_MAX, DifficultyModelLaw.CONSTANT_MAX.getModelName());
        _data.getTranslatedDiffModelLaw().addEntry(LANGUAGE, diffsLaw_);
        IdMap<EnvironmentType,String> envs_;
        envs_ = new IdMap<EnvironmentType,String>();
        envs_.addEntry(EnvironmentType.NOTHING, EnvironmentType.NOTHING.getEnvName());
        envs_.addEntry(EnvironmentType.ROAD, EnvironmentType.ROAD.getEnvName());
        envs_.addEntry(EnvironmentType.DESERT, EnvironmentType.DESERT.getEnvName());
        envs_.addEntry(EnvironmentType.ROCK, EnvironmentType.ROCK.getEnvName());
        envs_.addEntry(EnvironmentType.BUILDING, EnvironmentType.BUILDING.getEnvName());
        envs_.addEntry(EnvironmentType.WATER, EnvironmentType.WATER.getEnvName());
        envs_.addEntry(EnvironmentType.GRASS, EnvironmentType.GRASS.getEnvName());
        envs_.addEntry(EnvironmentType.SNOW, EnvironmentType.SNOW.getEnvName());
        envs_.addEntry(EnvironmentType.ICE, EnvironmentType.ICE.getEnvName());
        _data.getTranslatedEnvironment().addEntry(LANGUAGE, envs_);
        IdMap<Gender,String> genders_;
        genders_ = new IdMap<Gender,String>();
        genders_.addEntry(Gender.FEMALE, Gender.FEMALE.getGenderName());
        genders_.addEntry(Gender.NO_GENDER, Gender.NO_GENDER.getGenderName());
        genders_.addEntry(Gender.MALE, Gender.MALE.getGenderName());
        _data.getTranslatedGenders().addEntry(LANGUAGE, genders_);
        IdMap<Statistic,String> statistics_;
        statistics_ = new IdMap<Statistic,String>();
        statistics_.addEntry(Statistic.ATTACK, Statistic.ATTACK.getStatName());
        statistics_.addEntry(Statistic.DEFENSE, Statistic.DEFENSE.getStatName());
        statistics_.addEntry(Statistic.SPECIAL_ATTACK, Statistic.SPECIAL_ATTACK.getStatName());
        statistics_.addEntry(Statistic.SPECIAL_DEFENSE, Statistic.SPECIAL_DEFENSE.getStatName());
        statistics_.addEntry(Statistic.SPEED, Statistic.SPEED.getStatName());
        statistics_.addEntry(Statistic.CRITICAL_HIT, Statistic.CRITICAL_HIT.getStatName());
        statistics_.addEntry(Statistic.ACCURACY, Statistic.ACCURACY.getStatName());
        statistics_.addEntry(Statistic.EVASINESS, Statistic.EVASINESS.getStatName());
        statistics_.addEntry(Statistic.PV_RESTANTS, Statistic.PV_RESTANTS.getStatName());
        statistics_.addEntry(Statistic.HP, Statistic.HP.getStatName());
        _data.getTranslatedStatistics().addEntry(LANGUAGE, statistics_);
        IdMap<TargetChoice,String> targets_;
        targets_ = new IdMap<TargetChoice,String>();
        targets_.addEntry(TargetChoice.ADJ_ADV, TargetChoice.ADJ_ADV.getTargetName());
        targets_.addEntry(TargetChoice.ADJ_MULT, TargetChoice.ADJ_MULT.getTargetName());
        targets_.addEntry(TargetChoice.ADJ_UNIQ, TargetChoice.ADJ_UNIQ.getTargetName());
        targets_.addEntry(TargetChoice.ALLIE, TargetChoice.ALLIE.getTargetName());
        targets_.addEntry(TargetChoice.ALLIES, TargetChoice.ALLIES.getTargetName());
        targets_.addEntry(TargetChoice.ANY_FOE, TargetChoice.ANY_FOE.getTargetName());
        targets_.addEntry(TargetChoice.AUTRE_UNIQ, TargetChoice.AUTRE_UNIQ.getTargetName());
        targets_.addEntry(TargetChoice.GLOBALE, TargetChoice.GLOBALE.getTargetName());
        targets_.addEntry(TargetChoice.LANCEUR, TargetChoice.LANCEUR.getTargetName());
        targets_.addEntry(TargetChoice.PSEUDO_GLOBALE, TargetChoice.PSEUDO_GLOBALE.getTargetName());
        targets_.addEntry(TargetChoice.TOUS_ADV, TargetChoice.TOUS_ADV.getTargetName());
        targets_.addEntry(TargetChoice.UNIQUE_IMPORTE, TargetChoice.UNIQUE_IMPORTE.getTargetName());
        targets_.addEntry(TargetChoice.NOTHING, TargetChoice.NOTHING.getTargetName());
        _data.getTranslatedTargets().addEntry(LANGUAGE, targets_);

    }
    public static void initDefaultConsts(String _ballDef, String _rateCatching,
                                         String _rateFleeing, String _rateBoost,
                                         String _rateBoostCriticalHit, String _damageFormula,
                                         String _defMove, String _defaultEggGoup, DataBase _db) {
        _db.setBallDef(_ballDef);
        _db.setRateCatching(_rateCatching);
        _db.setRateFleeing(_rateFleeing);
        _db.setRateBoost(_rateBoost);
        _db.setRateBoostCriticalHit(_rateBoostCriticalHit);
        _db.setDamageFormula(_damageFormula);
        _db.setDefMove(_defMove);
        _db.setDefaultEggGroup(_defaultEggGoup);
        _db.setDefCategory("_");
    }

    private static PokemonData pkData(DataBase _data, String _base) {
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(_base);
        pkData_.setHatchingSteps(new LgInt(145));
        pkData_.setEggGroups(new StringList(_data.getDefaultEggGroup()));
        pkData_.setTypes(new StringList(ELECTRICK));
        statBase(pkData_);
        pkData_.getLevMoves().add(new LevelMove((short)1,ECLAIR));
//        pkData_.getLevMoves().add(new LevelMove((short)10,ECLAIR_2));
//        pkData_.getLevMoves().add(new LevelMove((short)20,ECLAIR_5));
//        pkData_.getLevMoves().add(new LevelMove((short)30,ECLAIR_6));
//        pkData_.getLevMoves().add(new LevelMove((short)32,ECLAIR_7));
        pkData_.setExpRate(1);
        pkData_.setHeight(Rate.one());
        pkData_.setWeight(Rate.one());
        pkData_.setCatchingRate((short) 1);
        pkData_.setHappiness((short) 1);
        pkData_.setHappinessHatch((short) 1);
        pkData_.setAbilities(new StringList(PARATONNERRE));
//        pkData_.getMoveTutors().add(ECLAIR_2);
        pkData_.getTechnicalMoves().add((short)3);
        return pkData_;
    }

    public static DamagingMoveData def() {
        EffectDamage eff_;
        DamagingMoveData sec_ = Instances.newDamagingMoveData();
        eff_ = Instances.newEffectDamage();
        eff_.setPower("100");
        eff_.setFail("");
        eff_.setTargetChoice(TargetChoice.TOUS_ADV);
        eff_.setStatisAtt(Statistic.ATTACK);
        eff_.patch();
        sec_.getEffects().add(eff_);
        sec_.setTargetChoice(TargetChoice.TOUS_ADV);
        sec_.setTypes(new StringList(ELECTRICK));
        sec_.setCategory("SPEC");
        sec_.setAccuracy("1");
        sec_.setPp((short) 1);
        return sec_;
    }

    public static void initBegin(DataBase _data) {
        DataMap map_ = _data.getMap();
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel((short) 1);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 2, 1));
    }

    public static void initBeginCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel((short) 1);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0,0,0, 2, 1));
    }

    protected static void statBase(PokemonData _pk) {
        _pk.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)0));
        _pk.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)0));
        _pk.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)0));
        _pk.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)0));
        _pk.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)0));
        _pk.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)0));
    }

    public static void initRandomLaws(DataBase _data) {
        MonteCarloNumber monteCarloNumber_;
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CROISSANT,new LawNumber(monteCarloNumber_,(short)4));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.UNIFORME,new LawNumber(monteCarloNumber_,(short)3));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.DECROISSANT,new LawNumber(monteCarloNumber_,(short)2));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MIN,new LawNumber(monteCarloNumber_,(short)1));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MAX,new LawNumber(monteCarloNumber_,(short)5));
    }

    public static Block newBlock(int _h, int _w, EnvironmentType _type, String _tileFileName, int _index) {
        //black
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        block_.setType(_type);
        block_.setTileFileName(_tileFileName);
        block_.setIndexApparition((short) _index);
        return block_;
    }

    public static GerantPokemon newGerantPokemon(GeranceType _gerance) {
        GerantPokemon gerant_ = Instances.newGerantPokemon();
        gerant_.setGerance(_gerance);
        gerant_.setImageMiniFileName(GERANT);
        return gerant_;
    }

    public static Seller newSellerItems() {
        Seller gerant_ = Instances.newSeller();
        gerant_.setSell(SellType.ITEM);
        gerant_.setImageMiniFileName(GERANT);
        return gerant_;
    }

    public static Seller sellerWithItem(Seller _seller,String _item) {
        _seller.getItems().add(_item);
        return _seller;
    }
    public static LevelPoint newLevelPoint(int _level, int _x, int _y) {
        LevelPoint begin_ = new LevelPoint();
        begin_.setLevelIndex((byte) _level);
        begin_.setPoint(newPoint(_x, _y));
        return begin_;
    }

    public static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    public static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.affectInside(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    public static Point newPoint(int _x, int _y) {
        return new Point((short)_x, (short)_y);
    }

}
