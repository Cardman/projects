package aiki.game.fight;
import aiki.db.DataBase;
import aiki.db.EquallablePkUtil;
import aiki.fight.pokemon.NameLevel;
import aiki.map.Condition;
import aiki.map.DataMap;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.Ally;
import aiki.map.characters.DealerItem;
import aiki.map.characters.DualFight;
import aiki.map.characters.GerantPokemon;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.characters.Seller;
import aiki.map.characters.TempTrainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.characters.enums.GeranceType;
import aiki.map.characters.enums.SellType;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Block;
import aiki.map.levels.LevelCave;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelIndoorPokemonCenter;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.League;
import aiki.map.places.Road;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonTeam;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.MiniMapCoordsList;
import aiki.map.util.TileMiniMap;
import aiki.tsts.TstsPk;
import aiki.util.*;
import code.images.ImageCsv;
import code.util.CustList;
import code.util.*;

import code.util.StringList;
import code.util.core.StringUtil;

final class InitializationMap  extends EquallablePkUtil {

    static final String CAVE = "Cave";
    static final String R_4 = "R 4";
    static final String R_2 = "R 2";
    static final String R_1 = "R 1";
    static final String CITY_TWO = "CITY_TWO";
    static final String CITY = "CITY";
    static final String MOTORWAY_9 = "Motorway 9";
    static final String CITY_8 = "CITY 8";
    static final String CITY_7 = "CITY 7";
    static final String LIGUE = "ligue";
    static final String LEAGUE_TR_TWO = "LEAGUE_TR_TWO";
    static final String LEAGUE_TR_ONE = "LEAGUE_TR_ONE";
    static final String DUAL_THREE_TR_TWO = "DUAL_THREE_TR_TWO";
    static final String DUAL_THREE_TR_ONE = "DUAL_THREE_TR_ONE";
    static final String GYM_TR_TWO = "GYM_TR_TWO";
    static final String DUAL_TWO_TR_TWO = "DUAL_TWO_TR_TWO";
    static final String DUAL_TWO_TR_ONE = "DUAL_TWO_TR_ONE";
    static final String DUAL_ONE_TR_TWO = "DUAL_ONE_TR_TWO";
    static final String DUAL_ONE_TR_ONE = "DUAL_ONE_TR_ONE";
    static final String GYM_TR_ONE = "GYM_TR_ONE";

    static final String MINI6 = "mini6";
    static final String MINI5 = "mini5";
    static final String MINI4 = "mini4";
    static final String MINI3 = "mini3";
    static final String MINI2 = "mini2";
    static final String MINI1 = "mini1";
    static final String MINI = "mini";
    static final String ALLY = "ally";
    static final String SNOW = "snow";
    static final String GRASS = "grass";
    static final String DAFAULT = "dafault";
    static final String GERANT = "gerant";
    static final String LINK = "link";
    static final String TRAINER_TWO = "trainer_two";
    static final String TRAINER_ONE = "trainer_one";
    static final String PERSON = "person";
    static final String TRAINER = "trainer";
    static final String DEFAULT = "default";
    static final String NOTHING = "nothing";
    static final String DESERT = "desert";
    static final String ROCK = "rock";
    static final String WATER = "water";
    static final String ROAD = "road";
    static final String BUILDING = "building";

    static final String FILE = "file";


    private static final String CARAPUCE = InitializationPokedex.CARAPUCE;
    private static final String LIMAGMA_M = InitializationPokedex.LIMAGMA_M;
    private static final String LIMAGMA_F = InitializationPokedex.LIMAGMA_F;
    private static final String PICHU = InitializationPokedex.PICHU;
    private static final String MELOFEE = InitializationPokedex.MELOFEE;
    private static final String LIMAGMA = InitializationPokedex.LIMAGMA;
    private static final String MEW = InitializationPokedex.MEW;
    private static final String ARTIKODIN = InitializationPokedex.ARTIKODIN;
    private static final String PIKACHU = InitializationPokedex.PIKACHU;
    private static final String NINGALE = InitializationPokedex.NINGALE;
    private static final String BABIMANTA = InitializationPokedex.BABIMANTA;
    private static final String REMORAID = InitializationPokedex.REMORAID;
    private static final String CHENITI = InitializationPokedex.CHENITI;
    private static final String TARINOR = InitializationPokedex.TARINOR;
    private static final String YANMA = InitializationPokedex.YANMA;
    private static final String TARTARD = InitializationPokedex.TARTARD;
    private static final String TETARTE = InitializationPokedex.TETARTE;
    private static final String PTITARD = InitializationPokedex.PTITARD;
    private static final String NUCLEOS = InitializationPokedex.NUCLEOS;

    private static final String PISTOLET_A_O = InitializationMoves.PISTOLET_A_O;
    private static final String CHARGE = InitializationMoves.CHARGE;
    private static final String JACKPOT = InitializationMoves.JACKPOT;
    private static final String TONNERRE = InitializationMoves.TONNERRE;
    private static final String ATTENTION = InitializationAbilities.ATTENTION;

    private static final String ABSORB_EAU = InitializationAbilities.ABSORB_EAU;

    private static final String PARATONNERRE = InitializationAbilities.PARATONNERRE;

    private static final String PIERRE_GLACE = InitializationItems.PIERRE_GLACE;
    private static final String PIERRE_SOLEIL = InitializationItems.PIERRE_SOLEIL;
    private static final String PIERRE_LUNE = InitializationItems.PIERRE_LUNE;
    private static final String PIERRE_EAU = InitializationItems.PIERRE_EAU;
    private static final String ROCHE_ROYALE = InitializationItems.ROCHE_ROYALE;
    private static final String PT_DE_MIRE = InitializationItems.PT_DE_MIRE;
    private static final String HYPER_BALL = InitializationItems.HYPER_BALL;
    private static final String POKE_BALL = InitializationItems.POKE_BALL;
    private static final String NULL_REF = InitializationDataBase.NULL_REF;


    private InitializationMap() {
    }
    static void initBegin(DataBase _data) {
        DataMap map_ = _data.getMap();
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel((short) 7);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
    }

    static void initBlockFirstRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addRoad();
        Road road_ = (Road) map_.getPlace((short) 0);
        road_.setName(R_1);
        Block block_;
        block_ = newRoadBlock(2, 2, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newRoadBlock(2, 2, 1);
        road_.getLevel().getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newWaterBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newRoadBlock(2, 2, 3);
        road_.getLevel().getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newWaterBlock(2, 2, 4);
        road_.getLevel().getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newWaterBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newWaterBlock(2, 2, 5);
        road_.getLevel().getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newWaterBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(4,4), block_);
    }

    static void initTrainersFirstRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        Road road_ = (Road) map_.getPlace((short) 0);
        CustList<PokemonTeam> foeTeamsList_;
        foeTeamsList_ = new CustList<PokemonTeam>();
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 3), new StringList(JACKPOT)));
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 4), new StringList(JACKPOT)));
        PokemonTeam foeTeamList_ = nvTeam((short) 200, team_);
        foeTeamsList_.add(foeTeamList_);
        CustList<PkTrainer> teamTwo_ = new CustList<PkTrainer>();
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 13), new StringList(JACKPOT)));
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 14), new StringList(JACKPOT)));
        foeTeamList_ = nvTeam((short) 200, teamTwo_);
        foeTeamsList_.add(foeTeamList_);
        TrainerMultiFights trainer_;
        trainer_ = newTrainer(foeTeamsList_, 1);
        road_.addPerson(newCoords(0, 0, 1, 1), trainer_);
    }

    static void initFirstRoadAreas(DataBase _data) {
        DataMap map_ = _data.getMap();
        Road road_ = (Road) map_.getPlace((short) 0);
        AreaApparition area_;
        WildPk wild_;
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 1);
        area_.setMultFight((byte) 1);
        wild_ = new WildPk();
        wild_.setName(PIKACHU);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.setWildPokemon(new CustList<WildPk>());
        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(PIKACHU);
        wild_.setLevel((short) 3);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 1);
        area_.setMultFight((byte) 1);
        wild_ = new WildPk();
        wild_.setName(ARTIKODIN);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.setWildPokemon(new CustList<WildPk>());
        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.getWildPokemon().add(wild_);
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 2);
        area_.setMultFight((byte) 1);
        wild_ = new WildPk();
        wild_.setName(ARTIKODIN);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.setWildPokemon(new CustList<WildPk>());
        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.getWildPokemon().add(wild_);
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 2);
        area_.setMultFight((byte) 1);
        wild_ = new WildPk();
        wild_.setName(ARTIKODIN);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.setWildPokemon(new CustList<WildPk>());
        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(MEW);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 1);
        area_.setMultFight((byte) 1);
        area_.setWildPokemon(new CustList<WildPk>());
        area_.setWildPokemonFishing(new CustList<WildPk>());
        wild_ = new WildPk();
        wild_.setName(PTITARD);
        wild_.setLevel((short) 1);
        wild_.setAbility(ABSORB_EAU);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(PTITARD);
        wild_.setLevel((short) 1);
        wild_.setAbility(ABSORB_EAU);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonFishing().add(wild_);
        wild_ = new WildPk();
        wild_.setName(TETARTE);
        wild_.setLevel((short) 26);
        wild_.setAbility(ABSORB_EAU);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(TETARTE);
        wild_.setLevel((short) 26);
        wild_.setAbility(ABSORB_EAU);
        wild_.setGender(Gender.NO_GENDER);
        wild_.setItem(PIERRE_EAU);
        area_.getWildPokemonFishing().add(wild_);
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 1);
        area_.setMultFight((byte) 1);
        area_.setWildPokemon(new CustList<WildPk>());
        area_.setWildPokemonFishing(new CustList<WildPk>());
        wild_ = new WildPk();
        wild_.setName(TETARTE);
        wild_.setLevel((short) 26);
        wild_.setAbility(ABSORB_EAU);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(TETARTE);
        wild_.setLevel((short) 26);
        wild_.setAbility(ABSORB_EAU);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonFishing().add(wild_);
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
    }

    static void initBlockFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addCity(CITY);
        City city_ = (City) map_.getPlace((short) 1);
        Block block_;
        block_ = newBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(4,4), block_);
    }

    static void initBuildingsFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlace((short) 1);
        Gym gym_;
        gym_ = new Gym();
        gym_.setImageFileName(LINK);
        gym_.setLevel(new LevelIndoorGym());
        gym_.setExitCity(newPoint(4,8));
        gym_.getLevel().setBlocks(new PointsBlock());
        gym_.getLevel().getBlocks().addEntry(newPoint(0,0), newBuildingBlock(9, 9));
        gym_.getIndoor().setGymTrainers(new PointsGymTrainer());
        city_.setBuildings(new PointsBuilding());
        city_.getBuildings().addEntry(newPoint(5, 1), gym_);
        PokemonCenter pkCenter_;
        pkCenter_ = new PokemonCenter();
        pkCenter_.setImageFileName(LINK);
        pkCenter_.setLevel(new LevelIndoorPokemonCenter());
        pkCenter_.setExitCity(newPoint(4,8));
        pkCenter_.getLevel().setBlocks(new PointsBlock());
        pkCenter_.getLevel().getBlocks().addEntry(newPoint(0,0), newBuildingBlock(9, 9));
        pkCenter_.getIndoor().setStorageCoords(newPoint(4, 0));
        pkCenter_.getIndoor().setGerants(new PointsPerson());
        city_.getBuildings().addEntry(newPoint(1, 1), pkCenter_);
    }

    static void initTrainersFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlace((short) 1);
        Gym gym_;
        GymTrainer gymTrainer_;
        GymLeader gymLeader_;
        gym_ = (Gym) city_.getBuildings().getVal(newPoint(5, 1));
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 3), new StringList(JACKPOT)));
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 4), new StringList(JACKPOT)));
        gymTrainer_ = nvGymTrainer((short) 200, (byte) 1, team_);
        gym_.getIndoor().getGymTrainers().addEntry(newPoint(1, 7), gymTrainer_);
        CustList<PkTrainer> teamTwo_ = new CustList<PkTrainer>();
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 3), new StringList(JACKPOT)));
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 4), new StringList(JACKPOT)));
        gymTrainer_ = nvGymTrainer((short) 200, (byte) 1, teamTwo_);
        gym_.getIndoor().getGymTrainers().addEntry(newPoint(7, 7), gymTrainer_);
        gym_.getIndoor().setGymLeaderCoords(newPoint(4, 1));
        CustList<PkTrainer> teamThree_ = new CustList<PkTrainer>();
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 5), new StringList(JACKPOT)));
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 8), new StringList(JACKPOT)));
        gymLeader_ = nvGymLeader((short) 500, (byte) 1, teamThree_);
        gymLeader_.setName(GYM_TR_ONE);
        gym_.getIndoor().setGymLeader(gymLeader_);
    }

    static void initPokemonCenterFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlace((short) 1);
        PokemonCenter pk_;
        pk_ = (PokemonCenter) city_.getBuildings().getVal(newPoint(1, 1));
        pk_.getIndoor().getGerants().addEntry(newPoint(0, 4), newGerantPokemon(GeranceType.HEAL));
        Seller seller_;
        seller_ = new Seller();
        seller_.setItems(new StringList(POKE_BALL,HYPER_BALL,PT_DE_MIRE, PIERRE_EAU,ROCHE_ROYALE,PIERRE_LUNE,PIERRE_SOLEIL,PIERRE_GLACE));
        seller_.setTm(Shorts.newList());
        seller_.setSell(SellType.ITEM);
        seller_.setImageMiniFileName(GERANT);
        pk_.getIndoor().getGerants().addEntry(newPoint(8, 4), seller_);
        seller_ = new Seller();
        seller_.setItems(new StringList());
        seller_.setTm(Shorts.newList((short)2));
        seller_.setSell(SellType.TM);
        seller_.setImageMiniFileName(GERANT);
        pk_.getIndoor().getGerants().addEntry(newPoint(8, 5), seller_);
        seller_ = new Seller();
        seller_.setItems(new StringList());
        seller_.setTm(Shorts.newList());
        seller_.setSell(SellType.MOVE);
        seller_.setImageMiniFileName(GERANT);
        pk_.getIndoor().getGerants().addEntry(newPoint(8, 6), seller_);
    }

    static void initBlockSecondRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addRoad();
        Road road_ = (Road) map_.getPlace((short) 2);
        road_.setName(R_2);
        Block block_;
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newWaterBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(6,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(8,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(10,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(6,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(8,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(10,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(4,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(6,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(8,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(10,4), block_);
    }

    static void initTrainersSecondRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        TrainerMultiFights trainer_;
        PokemonTeam foeTeamList_;
        CustList<PokemonTeam> foeTeamsList_;
        Road road_ = (Road) map_.getPlace((short) 2);
        DualFight dual_;
        CustList<PkTrainer> teamAl_ = new CustList<PkTrainer>();
        teamAl_.add(toPkTrainer(new NameLevel(PIKACHU, 25), new StringList(JACKPOT, CHARGE)));
        teamAl_.add(toPkTrainer(new NameLevel(PIKACHU, 28), new StringList(TONNERRE)));
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 5), new StringList(JACKPOT)));
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 8), new StringList(JACKPOT)));
        dual_ = nvDualFight((short) 300, teamAl_, team_);
        dual_.setNames(new StringList(DUAL_ONE_TR_ONE,DUAL_ONE_TR_TWO));
        dual_.setPt(newPoint(3, 0));
        road_.addDualFight(newCoords(2, 0, 2, 0), dual_);
        //map_.getBeatGymLeader().add(newCoords(2, 0, 2, 0));
        road_ = (Road) map_.getPlace((short) 2);
        CustList<PkTrainer> teamAlTwo_ = new CustList<PkTrainer>();
        teamAlTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 25), new StringList(JACKPOT, CHARGE)));
        teamAlTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 28), new StringList(TONNERRE)));
        CustList<PkTrainer> teamTwo_ = new CustList<PkTrainer>();
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 5), new StringList(JACKPOT)));
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 8), new StringList(JACKPOT)));
        dual_ = nvDualFight((short) 300, teamAlTwo_, teamTwo_);
        dual_.setNames(new StringList(DUAL_TWO_TR_ONE,DUAL_TWO_TR_TWO));
        dual_.setPt(newPoint(5, 0));
        road_.addDualFight(newCoords(2, 0, 4, 0), dual_);
        //map_.getBeatGymLeader().add(newCoords(2, 0, 4, 0));
        foeTeamsList_ = new CustList<PokemonTeam>();
        CustList<PkTrainer> teamThree_ = new CustList<PkTrainer>();
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 3), new StringList(JACKPOT)));
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 4), new StringList(JACKPOT)));
        foeTeamList_ = nvTeam((short) 200, teamThree_);
        foeTeamsList_.add(foeTeamList_);
        trainer_ = newTrainer(foeTeamsList_, 1);
        road_.addPerson(newCoords(2, 0, 11, 4), trainer_);
    }

    static void initSecondRoadAreas(DataBase _data) {
        DataMap map_ = _data.getMap();
        WildPk wild_;
        Road road_ = (Road) map_.getPlace((short) 2);
        wild_ = new WildPk();
        wild_.setName(MEW);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        road_.getLevelRoad().getLegendaryPks().addEntry(newPoint(11, 2), wild_);
        //map_.getTakenPokemon().add(newCoords(2, 0, 11, 2));
        wild_ = new WildPk();
        wild_.setName(ARTIKODIN);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        road_.getLevelRoad().getLegendaryPks().addEntry(newPoint(9, 5), wild_);
    }

    static void initItemsSecondRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        Road road_ = (Road) map_.getPlace((short) 2);
        road_.addObject(newCoords(2, 0, 6, 5), HYPER_BALL);
        //map_.getTakenObjects().add(newCoords(2, 0, 6, 5));
        road_.addTm(newCoords(2, 0, 7, 5), (short) 2);
        //map_.getTakenObjects().add(newCoords(2, 0, 7, 5));
        road_.addHm(newCoords(2, 0, 8, 5), (short) 1);
    }

    static void initBlockSecondCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addCity(CITY_TWO);
        City city_ = (City) map_.getPlace((short) 3);
        Block block_;
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(4,4), block_);
    }

    static void initBuildingsSecondCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_;
        Gym gym_;
        PokemonCenter pkCenter_;
        city_ = (City) map_.getPlace((short) 3);
        gym_ = new Gym();
        gym_.setImageFileName(LINK);
        gym_.setLevel(new LevelIndoorGym());
        gym_.setExitCity(newPoint(4,8));
        gym_.getLevel().setBlocks(new PointsBlock());
        gym_.getLevel().getBlocks().addEntry(newPoint(0,0), newBuildingBlock(9, 9));
        gym_.getIndoor().setGymTrainers(new PointsGymTrainer());
        city_.setBuildings(new PointsBuilding());
        city_.getBuildings().addEntry(newPoint(4, 1), gym_);
        pkCenter_ = new PokemonCenter();
        pkCenter_.setImageFileName(LINK);
        pkCenter_.setLevel(new LevelIndoorPokemonCenter());
        pkCenter_.setExitCity(newPoint(4,8));
        pkCenter_.getLevel().setBlocks(new PointsBlock());
        pkCenter_.getLevel().getBlocks().addEntry(newPoint(0,0), newBuildingBlock(9, 9));
        pkCenter_.getIndoor().setStorageCoords(newPoint(4, 0));
        pkCenter_.getIndoor().setGerants(new PointsPerson());
        city_.getBuildings().addEntry(newPoint(2, 1), pkCenter_);
//        map_.getCities().add(newCoords(1, 0, 1, 2));
//        map_.getCities().add(newCoords(3, 0, 2, 2));
    }

    static void initTrainersSecondCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlace((short) 3);
        GymTrainer gymTrainer_;
        GymLeader gymLeader_;
        Gym gym_ = (Gym) city_.getBuildings().getVal(newPoint(4, 1));
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 3), new StringList(JACKPOT)));
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 4), new StringList(JACKPOT)));
        gymTrainer_ = nvGymTrainer((short) 200, (byte) 1, team_);
        gym_.getIndoor().getGymTrainers().addEntry(newPoint(1, 7), gymTrainer_);
        CustList<PkTrainer> teamTwo_ = new CustList<PkTrainer>();
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 3), new StringList(JACKPOT)));
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 4), new StringList(JACKPOT)));
        gymTrainer_ = nvGymTrainer((short) 200, (byte) 1, teamTwo_);
        gym_.getIndoor().getGymTrainers().addEntry(newPoint(7, 7), gymTrainer_);
        gym_.getIndoor().setGymLeaderCoords(newPoint(4, 1));
        CustList<PkTrainer> teamThree_ = new CustList<PkTrainer>();
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 5), new StringList(JACKPOT)));
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 8), new StringList(JACKPOT)));
        gymLeader_ = nvGymLeader((short) 500, (byte) 1, teamThree_);
        gymLeader_.setName(GYM_TR_TWO);
        gym_.getIndoor().setGymLeader(gymLeader_);
    }

    static void initPokemonCenterSecondCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        //map_.getTakenObjects().add(newCoords(0, 0, 0, 1));
        City city_;
        PokemonCenter pk_;
        city_ = (City) map_.getPlace((short) 3);
        pk_ = (PokemonCenter) city_.getBuildings().getVal(newPoint(2, 1));
        pk_.getIndoor().getGerants().addEntry(newPoint(0, 4), newGerantPokemon(GeranceType.FOSSILE));
        pk_.getIndoor().getGerants().addEntry(newPoint(8, 4), newGerantPokemon(GeranceType.HOST));
    }

    static void initBlockThirdRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addRoad();
        Road road_ = (Road) map_.getPlace((short) 4);
        road_.setName(R_4);
        Block block_;
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newDesertBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newDesertBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newDesertBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(4,4), block_);
    }

    static void initBlockCave(DataBase _data) {
        DataMap map_ = _data.getMap();
        InitializationDataBase.addCave(map_);
        Cave cave_ = (Cave) map_.getPlace((short) 5);
        cave_.setName(CAVE);
        InitializationDataBase.addLevelCave(map_, (short) 5);
        LevelCave level_;
        Block block_;
        level_ = (LevelCave)cave_.getLevelsMap().getVal((byte) 0);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(6,0), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(6,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(4,4), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(6,4), block_);
        level_ = (LevelCave)cave_.getLevelsMap().getVal((byte) 1);
        block_ = newRockBlock(2, 2, 0);
        level_.getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newRockBlock(2, 2, 1);
        level_.getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newRockBlock(2, 2, 1);
        level_.getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(6,0), block_);
        block_ = newRockBlock(2, 2, 0);
        level_.getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(6,2), block_);
        block_ = newRockBlock(2, 2, 0);
        level_.getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(4,4), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().addEntry(newPoint(6,4), block_);
    }

    static void initCaveAreas(DataBase _data) {
        DataMap map_ = _data.getMap();
        Cave road_ = (Cave) map_.getPlace((short) 5);
        LevelCave level_;
        //map level_ = road_.getLevels().getVal((byte) 0);
        level_ = (LevelCave)road_.getLevelsMap().getVal((byte) 1);
        AreaApparition area_;
        WildPk wild_;
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 1);
        area_.setMultFight((byte) 2);
        wild_ = new WildPk();
        wild_.setName(TARINOR);
        wild_.setLevel((short) 10);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.setWildPokemon(new CustList<WildPk>());
        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(NINGALE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        level_.getWildPokemonAreas().add(area_);
        area_ = new AreaApparition();
        area_.setWildPokemon(new CustList<WildPk>());
        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.setAvgNbSteps((short) 1);
        area_.setMultFight((byte) 2);
        wild_ = new WildPk();
        wild_.setName(NINGALE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(NUCLEOS);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(NUCLEOS);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(TARINOR);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(TARINOR);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(NINGALE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(NINGALE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(CARAPUCE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(PICHU);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(YANMA);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(YANMA);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(BABIMANTA);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(BABIMANTA);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(PTITARD);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(PTITARD);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(REMORAID);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(MELOFEE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(LIMAGMA);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(CHENITI);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(CHENITI);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(LIMAGMA_F);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(LIMAGMA_M);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemon().add(wild_);
        level_.getWildPokemonAreas().add(area_);
    }

    static void initTrainersCave(DataBase _data) {
        DataMap map_ = _data.getMap();
        //Road road_ = (Road) map_.getPlace((short) 0);
        PokemonTeam foeTeamList_;
        CustList<PokemonTeam> foeTeamsList_;
        TrainerMultiFights trainer_;
        DualFight dual_;
        Cave cave_ = (Cave) map_.getPlace((short) 5);
        CustList<PkTrainer> teamAl_ = new CustList<PkTrainer>();
        teamAl_.add(toPkTrainer(new NameLevel(PIKACHU, 35), new StringList(JACKPOT, CHARGE)));
        teamAl_.add(toPkTrainer(new NameLevel(PIKACHU, 38), new StringList(TONNERRE)));
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 15), new StringList(JACKPOT)));
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 18), new StringList(JACKPOT)));
        dual_ = nvDualFight((short) 300, teamAl_, team_);
        dual_.setNames(new StringList(DUAL_THREE_TR_ONE,DUAL_THREE_TR_TWO));
        dual_.setPt(newPoint(3, 0));
        ((LevelCave)cave_.getLevelsMap().getVal((byte) 0)).getDualFights().addEntry(newPoint(2, 0), dual_);
        //map_.getBeatGymLeader().add(newCoords(5, 0, 2, 0));
        foeTeamsList_ = new CustList<PokemonTeam>();
        CustList<PkTrainer> teamTwo_ = new CustList<PkTrainer>();
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 13), new StringList(JACKPOT)));
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 14), new StringList(JACKPOT)));
        foeTeamList_ = nvTeam((short) 200, teamTwo_);
        foeTeamsList_.add(foeTeamList_);
        trainer_ = newTrainer(foeTeamsList_, 1);
        ((LevelCave)cave_.getLevelsMap().getVal((byte) 0)).getCharacters().addEntry(newPoint(1, 5), trainer_);
        //map_.getBeatTrainer().add(new NbFightCoords(newCoords(5, 0, 1, 5),0));
        foeTeamsList_ = new CustList<PokemonTeam>();
        CustList<PkTrainer> teamThree_ = new CustList<PkTrainer>();
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 13), new StringList(JACKPOT)));
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 14), new StringList(JACKPOT)));
        foeTeamList_ = nvTeam((short) 200, teamThree_);
        foeTeamsList_.add(foeTeamList_);
        trainer_ = newTrainer(foeTeamsList_, 1);
        ((LevelCave)cave_.getLevelsMap().getVal((byte) 1)).getCharacters().addEntry(newPoint(5, 1), trainer_);
        //map_.getBeatTrainer().add(new NbFightCoords(newCoords(5, 1, 5, 1),0));
    }

    static void initOtherCharactersFirstRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        Road road_ = (Road) map_.getPlace((short) 0);
        road_.addPerson(newCoords(0, 0, 0, 1), newDealerObject(new StringList(HYPER_BALL), Shorts.newList((short)5)));
    }

    static void initLeague(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addLeague(LINK, newCoords(4, 0, 5, 4));
        League league_ =(League) map_.getPlace((short) 6);
        league_.getRooms().last().setFileName(LINK);
        league_.setName(LIGUE);
        map_.addLevelLeague((short) 6);
        league_.getRooms().last().setFileName(LINK);
    }

    static void initBlockLeague(DataBase _data) {
        DataMap map_ = _data.getMap();
        League road_ = (League) map_.getPlace((short) 6);
        LevelLeague level_;
        Block block_;
        level_ = (LevelLeague) road_.getLevelsMap().getVal((byte) 0);
        block_ = newBuildingBlock(9, 9);
        level_.getBlocks().addEntry(newPoint(0,0), block_);
        level_ = (LevelLeague) road_.getLevelsMap().getVal((byte) 1);
        block_ = newBuildingBlock(9, 9);
        level_.getBlocks().addEntry(newPoint(0,0), block_);
        road_.setBegin(newPoint(4,8));
        level_ = (LevelLeague) road_.getLevelsMap().getVal((byte) 0);
        level_.setAccessPoint(newPoint(4, 0));
        level_.setNextLevelTarget(newPoint(4, 8));
        level_ = (LevelLeague) road_.getLevelsMap().getVal((byte) 1);
        level_.setAccessPoint(newPoint(4, 0));
    }

    static void initLeagueTrainers(DataBase _data) {
        DataMap map_ = _data.getMap();
        League league_ = (League) map_.getPlace((short) 6);
        league_.getRooms().get(0).setTrainerCoords(newPoint(4, 4));
        league_.getRooms().get(0).setTrainer(trainerLeagueOne());
        league_.getRooms().get(0).getTrainer().setName(LEAGUE_TR_ONE);
        league_.getRooms().get(1).setTrainerCoords(newPoint(4, 4));
        league_.getRooms().get(1).setTrainer(trainerLeagueTwo());
        league_.getRooms().get(1).getTrainer().setName(LEAGUE_TR_TWO);
        //map_.getBeatGymLeader().add(newCoords(6, 0, 4, 8));
        map_.setAccessCondition(new CoordsLists());
        map_.getAccessCondition().addEntry(newCoords(4, 0, 0, 4), Condition.newList(newCoords(1, 0, 5, 1, 4, 1),newCoords(3, 0, 4, 1, 4, 1)));
        map_.getAccessCondition().addEntry(newCoords(4, 0, 1, 4), Condition.newList(newCoords(1, 0, 5, 1, 4, 1),newCoords(3, 0, 4, 1, 4, 1)));
        map_.getAccessCondition().addEntry(newCoords(4, 0, 2, 4), Condition.newList(newCoords(1, 0, 5, 1, 4, 1),newCoords(3, 0, 4, 1, 4, 1)));
        map_.getAccessCondition().addEntry(newCoords(4, 0, 3, 4), Condition.newList(newCoords(1, 0, 5, 1, 4, 1),newCoords(3, 0, 4, 1, 4, 1)));
        map_.getAccessCondition().addEntry(newCoords(4, 0, 4, 4), Condition.newList(newCoords(1, 0, 5, 1, 4, 1),newCoords(3, 0, 4, 1, 4, 1)));
        map_.getAccessCondition().addEntry(newCoords(4, 0, 5, 4), Condition.newList(newCoords(1, 0, 5, 1, 4, 1),newCoords(3, 0, 4, 1, 4, 1)));
    }

    private static TrainerLeague trainerLeagueTwo() {
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        team_.add(toPkTrainer(new NameLevel(TARTARD, 35), new StringList(PISTOLET_A_O)));
        team_.add(toPkTrainer(new NameLevel(TARTARD, 38), new StringList(PISTOLET_A_O)));
        return nvTrainerLeague((short) 2000, (byte) 1, team_);
    }

    private static TrainerLeague trainerLeagueOne() {
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 35), new StringList(JACKPOT)));
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 38), new StringList(JACKPOT)));
        return nvTrainerLeague((short) 2000, (byte) 1, team_);
    }

    static void initBlockThirdCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addCity(CITY_7);
        City city_ = (City) map_.getPlace((short) 7);
        Block block_;
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(4,4), block_);
    }

    static void initBuildingsThirdCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlace((short) 7);
        PokemonCenter pkCenter_;
        pkCenter_ = new PokemonCenter();
        pkCenter_.setImageFileName(LINK);
        pkCenter_.setLevel(new LevelIndoorPokemonCenter());
        pkCenter_.setExitCity(newPoint(4,8));
        pkCenter_.getLevel().setBlocks(new PointsBlock());
        pkCenter_.getLevel().getBlocks().addEntry(newPoint(0,0), newBuildingBlock(9, 9));
        pkCenter_.getIndoor().setStorageCoords(newPoint(4, 0));
        pkCenter_.getIndoor().setGerants(new PointsPerson());
        pkCenter_.getIndoor().getGerants().addEntry(newPoint(0, 4), newGerantPokemon(GeranceType.FOSSILE));
        city_.getBuildings().addEntry(newPoint(3,3), pkCenter_);
        //5, 1, 4, 0
    }

    static void initBlockFourthCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addCity(CITY_8);
        City city_ = (City) map_.getPlace((short) 8);
        Block block_;
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().addEntry(newPoint(4,4), block_);
    }

    static void initBuildingsFourthCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlace((short) 8);
        PokemonCenter pkCenter_;
        pkCenter_ = new PokemonCenter();
        pkCenter_.setImageFileName(LINK);
        pkCenter_.setLevel(new LevelIndoorPokemonCenter());
        pkCenter_.setExitCity(newPoint(4,8));
        pkCenter_.getLevel().setBlocks(new PointsBlock());
        pkCenter_.getLevel().getBlocks().addEntry(newPoint(0,0), newBuildingBlock(9, 9));
        pkCenter_.getIndoor().setStorageCoords(newPoint(4, 0));
        pkCenter_.getIndoor().setGerants(new PointsPerson());
        pkCenter_.getIndoor().getGerants().addEntry(newPoint(0, 4), newGerantPokemon(GeranceType.HOST));
        city_.getBuildings().addEntry(newPoint(3,3), pkCenter_);
        //5, 1, 4, 0
    }

    static void initBlockFourthRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addRoad();
        Road road_ = (Road) map_.getPlace((short) 9);
        road_.setName(MOTORWAY_9);
        Block block_;
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newDesertBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newDesertBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newDesertBlock(2, 2);
        road_.getLevel().getBlocks().addEntry(newPoint(4,4), block_);
    }

    static void initTrainersFourthRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        Road road_ = (Road) map_.getPlace((short) 9);
        PokemonTeam foeTeamList_;
        CustList<PokemonTeam> foeTeamsList_;
        foeTeamsList_ = new CustList<PokemonTeam>();
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        team_.add(toPkTrainer(new NameLevel(PTITARD,3),new StringList(JACKPOT)));
        team_.add(toPkTrainer(new NameLevel(PTITARD,4),new StringList(JACKPOT)));
        team_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(JACKPOT)));
        team_.add(toPkTrainer(new NameLevel(PIKACHU,4),new StringList(JACKPOT)));
        foeTeamList_ = nvTeam((short) 200, team_);
        foeTeamsList_.add(foeTeamList_);
        CustList<PkTrainer> teamTwo_ = new CustList<PkTrainer>();
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU,13),new StringList(JACKPOT)));
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU,14),new StringList(JACKPOT)));
        teamTwo_.add(toPkTrainer(new NameLevel(PTITARD,13),new StringList(JACKPOT)));
        teamTwo_.add(toPkTrainer(new NameLevel(PTITARD,14),new StringList(JACKPOT)));
        foeTeamList_ = nvTeam((short) 200, teamTwo_);
        foeTeamsList_.add(foeTeamList_);
        TrainerMultiFights trainer_;
        trainer_ = newTrainer(foeTeamsList_, 2);
        road_.addPerson(newCoords(0, 0, 1, 1), trainer_);
    }

    static void joinPlaces(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.join((short)0,(short) 1, newPoint(0,0), newPoint(0,5), Direction.UP);
        map_.join((short)0,(short) 2, newPoint(5,0), newPoint(0,0), Direction.RIGHT);
        map_.join((short)2,(short) 3, newPoint(0,0), newPoint(0,5), Direction.UP);
        map_.join((short)4,(short) 2, newPoint(0,0), newPoint(0,5), Direction.UP);
        // right of cave
        DataMap.joinCavePlace(map_, newCoords(5, 0, 7, 2), newCoords(4, 0, 0, 2), LINK, LINK);
        DataMap.joinLevelCave(map_, (short) 5, newLevelPoint(0,7,5), newLevelPoint(1,7,5), LINK, LINK);
        DataMap.joinLevelCave(map_, (short) 5, newLevelPoint(0,2,3), newLevelPoint(1,2,3), LINK, LINK);

//        map_.joinCavePlace(newCoords(5, 1, 4, 0), newCoords(7, 0, 2, 0), "file", "file");
//        map_.joinCavePlace(newCoords(5, 1, 4, 0), newCoords(7, 0, 2, 0), "file", "file");
        DataMap.joinCavePlace(map_, newCoords(5, 1, 4, 0), newCoords(8, 0, 0, 0), LINK, LINK);
//        map_.getAccessCondition().addEntry(newCoords(5, 1, 4, 0), new List<Coords>(newCoords(6, 0, 4, 8)));
        map_.join((short)7,(short) 4, newPoint(0,0), newPoint(0,5), Direction.UP);
        //map_.join(_pl1, _pl2, _p1, _p2, _dir1);
        map_.getAccessCondition().addEntry(newCoords(5, 1, 4, 0), Condition.newList(newCoords(6, 0, 4, 8)));
        map_.getAccessCondition().addEntry(newCoords(7, 0, 0, 0), Condition.newList(newCoords(6, 0, 4, 8)));
        map_.getAccessCondition().addEntry(newCoords(7, 0, 1, 0), Condition.newList(newCoords(6, 0, 4, 8)));
        map_.getAccessCondition().addEntry(newCoords(7, 0, 2, 0), Condition.newList(newCoords(6, 0, 4, 8)));
        map_.getAccessCondition().addEntry(newCoords(7, 0, 3, 0), Condition.newList(newCoords(6, 0, 4, 8)));
        map_.getAccessCondition().addEntry(newCoords(7, 0, 4, 0), Condition.newList(newCoords(6, 0, 4, 8)));
        map_.getAccessCondition().addEntry(newCoords(7, 0, 5, 0), Condition.newList(newCoords(6, 0, 4, 8)));
        //map_.getAccessCondition().addEntry(newCoords(7, 0, 6, 0), new List<Coords>(newCoords(6, 0, 4, 8)));
        //map_.getAccessCondition().addEntry(newCoords(7, 0, 7, 0), new List<Coords>(newCoords(6, 0, 4, 8)));
        map_.join((short) 9, (short)7, newPoint(0,0), newPoint(0,5), Direction.UP);
    }

    static void initImages(DataBase _data) {
        DataMap map_ = _data.getMap();
        CustList<String> pkKeys_ = _data.getPokedex().getKeys();
        TstsPk.commonImage(pkKeys_,"2;-13;-15;-15;-15",_data.getMaxiPkBack());
        TstsPk.commonImage(pkKeys_,"2;-14;-15;-15;-15",_data.getMaxiPkFront());
        TstsPk.commonImage(pkKeys_,"2;-15;-15;-15;-15",_data.getMiniPk());
//        for (String p: pkKeys_) {
//            _data.getMaxiPkBack().addEntry(p, getImageByString("2;-13;-15;-15;-15"));
//            _data.getMaxiPkFront().addEntry(p, getImageByString("2;-14;-15;-15;-15"));
//            _data.getMiniPk().addEntry(p, getImageByString("2;-15;-15;-15;-15"));
//        }
        TstsPk.commonImage(_data.getItems().getKeys(), "2;-16;-16;-16;-16", _data.getMiniItems());
        TstsPk.commonImage(_data.getStatus().getKeys(), "2;-17;-16;-16;-16", _data.getAnimStatus());
        TstsPk.commonImage(DataBase.statisNames(), "2;-18;-16;-16;-16", _data.getAnimStatis());
        TstsPk.typesColorsInit(_data);
        StringList building_ = TstsPk.csvImg("18", 324, "-32985");
        _data.addImage(BUILDING, getImageByString(StringUtil.join(building_, ";")));
        StringList default_ = TstsPk.csvImg("4", 16, "-1");
        _data.addImage(DAFAULT, getImageByString(StringUtil.join(default_, ";")));
        StringList desert_ = TstsPk.csvImg("4", 16, "-3584");
        _data.addImage(DESERT, getImageByString(StringUtil.join(desert_, ";")));
        StringList grass_ = TstsPk.csvImg("4", 16, "-14503604");
        _data.addImage(GRASS, getImageByString(StringUtil.join(grass_, ";")));
        StringList nothing_ = TstsPk.csvImg("4", 16, "-16777216");
        _data.addImage(NOTHING, getImageByString(StringUtil.join(nothing_, ";")));
        StringList road_ = TstsPk.csvImg("4", 16, "-7369361");
        _data.addImage(ROAD, getImageByString(StringUtil.join(road_, ";")));
        StringList rock_ = TstsPk.csvImg("4", 16, "-4621737");
        _data.addImage(ROCK, getImageByString(StringUtil.join(rock_, ";")));
        StringList snow_ = TstsPk.csvImg("4", 16, "-1");
        _data.addImage(SNOW, getImageByString(StringUtil.join(snow_, ";")));
        StringList water_ = TstsPk.csvImg("4", 16, "-16776961");
        _data.addImage(WATER, getImageByString(StringUtil.join(water_, ";")));
//        _data.getPeople().addEntry(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"trainer", "2;-18000;-18000;-18000;-18000");
//        _data.getPeople().addEntry(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"person", "2;-1800;-1800;-1800;-1800");
//        _data.getPeople().addEntry(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"trainer_one", "2;-19000;-19000;-19000;-19000");
//        _data.getPeople().addEntry(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"trainer_two", "2;-19008;-19008;-19008;-19008");
//        _data.getPeople().addEntry(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"ally", "2;-19508;-19508;-19508;-19508");
//        _data.getPeople().addEntry(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"gerant", "2;-20508;-20508;-20508;-20508");
        _data.addPerson(TRAINER, getImageByString("2;-18000;-18000;-18000;-18000"));
        _data.addPerson(PERSON, getImageByString("2;-1800;-1800;-1800;-1800"));
        _data.addPerson(TRAINER_ONE, getImageByString("2;-19000;-19000;-19000;-19000"));
        _data.addPerson(TRAINER_TWO, getImageByString("2;-19008;-19008;-19008;-19008"));
        _data.addPerson(ALLY, getImageByString("2;-19508;-19508;-19508;-19508"));
        _data.addPerson(GERANT, getImageByString("2;-20508;-20508;-20508;-20508"));

        //begin insertion
        _data.addTrainerImage(TRAINER, getImageByString("2;-18000;-18000;-18000;-18000"));
        _data.addTrainerImage(TRAINER_ONE, getImageByString("2;-19000;-19000;-19000;-19000"));
        _data.addTrainerImage(TRAINER_TWO, getImageByString("2;-19008;-19008;-19008;-19008"));
        _data.addTrainerImage(ALLY, getImageByString("2;-19508;-19508;-19508;-19508"));
        //end insertion

        TstsPk.heroInit(_data);
        _data.addLink(LINK, getImageByString("2;-255;-255;-255;-255"));
        _data.setImageTmHm(getImageByString("2;-800;-800;-800;-800"));
        _data.setAnimAbsorb(getImageByString("2;-700;-800;-800;-800"));
        _data.setStorage(getImageByString("2;-3;-3;-3;-3"));
        _data.getMiniMap().addEntry(MINI, getImageByString("2;118;218;112;200"));
        _data.getMiniMap().addEntry(MINI1, getImageByString("2;218;118;112;200"));
        _data.getMiniMap().addEntry(MINI2, getImageByString("2;218;112;118;200"));
        _data.getMiniMap().addEntry(MINI3, getImageByString("2;218;112;200;118"));
        _data.getMiniMap().addEntry(MINI4, getImageByString("2;218;200;112;118"));
        _data.getMiniMap().addEntry(MINI5, getImageByString("2;200;218;112;118"));
        _data.getMiniMap().addEntry(MINI6, getImageByString("2;200;218;212;118"));
        _data.setEndGameImage(getImageByString("1;1"));
    }

    static void initMiniMap(DataBase _data) {
        DataMap map_ = _data.getMap();
        TileMiniMap tile_;
        map_.setMiniMap(new MiniMapCoordsList());
        tile_ = new TileMiniMap();
        tile_.setFile(MINI);
        tile_.setPlace((short) 0);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 0), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace((short) 1);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI);
        tile_.setPlace((short) 2);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 2), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace((short) 3);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 3), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI);
        tile_.setPlace((short) 4);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 4), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI2);
        tile_.setPlace((short) 5);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 5), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI3);
        tile_.setPlace((short) 6);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 6), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace((short) 7);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 7), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace((short) 8);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 8), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI4);
        tile_.setPlace((short) -1);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 9), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI6);
        tile_.setPlace((short) 9);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 10), tile_);
        map_.setUnlockedCity(MINI5);
    }

    private static GymLeader nvGymLeader(short _reward, byte _mult, CustList<PkTrainer> _team) {
        GymLeader gymLeader_ = new GymLeader();
        gymLeader_.setTeam(_team);
        gymLeader_.setReward(_reward);
        gymLeader_.setMultiplicityFight(_mult);
        gymLeader_.setName(NULL_REF);
        gymLeader_.setTm((short) 2);
        gymLeader_.setImageMiniFileName(TRAINER);
        gymLeader_.setImageMaxiFileName(TRAINER);
        return gymLeader_;
    }

    private static GymTrainer nvGymTrainer(short _reward, byte _mult, CustList<PkTrainer> _team) {
        GymTrainer gymTrainer_ = new GymTrainer();
        gymTrainer_.setTeam(_team);
        gymTrainer_.setReward(_reward);
        gymTrainer_.setMultiplicityFight(_mult);
        gymTrainer_.setImageMiniFileName(TRAINER);
        gymTrainer_.setImageMaxiFileName(TRAINER);
        return gymTrainer_;
    }

    private static TrainerLeague nvTrainerLeague(short _reward, byte _mult, CustList<PkTrainer> _team) {
        TrainerLeague trainerLeague_ = new TrainerLeague();
        trainerLeague_.setTeam(_team);
        trainerLeague_.setReward(_reward);
        trainerLeague_.setMultiplicityFight(_mult);
        trainerLeague_.setImageMiniFileName(TRAINER);
        trainerLeague_.setImageMaxiFileName(TRAINER);
        trainerLeague_.setName(NULL_REF);
        return trainerLeague_;
    }

    private static DualFight nvDualFight(short _reward, CustList<PkTrainer> _teamAl, CustList<PkTrainer> _team) {
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        ally_.setTeam(_teamAl);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(_team);
        trainer_.setReward(_reward);
        trainer_.setImageMaxiFileName(TRAINER_ONE);
        trainer_.setImageMiniFileName(TRAINER_ONE);
        trainer_.setImageMiniSecondTrainerFileName(TRAINER_TWO);
        dual_.setAlly(ally_);
        dual_.setFoeTrainer(trainer_);
        dual_.setNames(new StringList());
        return dual_;
    }

    private static PokemonTeam nvTeam(short _reward, CustList<PkTrainer> _team) {
        PokemonTeam teamReward_ = new PokemonTeam();
        teamReward_.setTeam(_team);
        teamReward_.setReward(_reward);
        return teamReward_;
    }

    private static TrainerMultiFights newTrainer(
            CustList<PokemonTeam> _teams, int _mult) {
        TrainerMultiFights trainer_ = new TrainerMultiFights();
        trainer_.setTeamsRewards(_teams);
        trainer_.setMultiplicityFight((byte) _mult);
        trainer_.setImageMiniFileName(TRAINER);
        trainer_.setImageMaxiFileName(TRAINER);
        return trainer_;
    }

    private static GerantPokemon newGerantPokemon(GeranceType _gerance) {
        GerantPokemon gerant_ = new GerantPokemon();
        gerant_.setGerance(_gerance);
        gerant_.setImageMiniFileName(GERANT);
        return gerant_;
    }

    private static DealerItem newDealerObject(StringList _obj, Shorts _tm) {
        DealerItem dealer_ = new DealerItem();
        dealer_.setItems(new StringList(_obj));
        dealer_.setTechnicalMoves(_tm);
        dealer_.setImageMiniFileName(PERSON);
        return dealer_;
    }

    private static Block newRoadBlock(int _h, int _w, int _index) {
        Block block_ = newRoadBlock(_h, _w);
        block_.setIndexApparition((short) _index);
        return block_;
    }

    private static Block newRoadBlock(int _h, int _w) {
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        block_.setType(EnvironmentType.ROAD);
        //grey
        block_.setTileFileName(ROAD);
        return block_;
    }
    private static Block newWaterBlock(int _h, int _w, int _index) {
        Block block_ = newWaterBlock(_h, _w);
        block_.setIndexApparition((short) _index);
        return block_;
    }

    private static Block newWaterBlock(int _h, int _w) {
        //blue
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        block_.setType(EnvironmentType.WATER);
        block_.setTileFileName(WATER);
        return block_;
    }

    private static Block newRockBlock(int _h, int _w) {
        //brown
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        block_.setType(EnvironmentType.ROCK);
        block_.setTileFileName(ROCK);
        return block_;
    }

    private static Block newRockBlock(int _h, int _w, int _index) {
        Block block_ = newRockBlock(_h, _w);
        block_.setIndexApparition((short) _index);
        return block_;
    }

    private static Block newDesertBlock(int _h, int _w) {
        //yellow
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        block_.setType(EnvironmentType.DESERT);
        block_.setTileFileName(DESERT);
        return block_;
    }

    private static Block newBuildingBlock(int _h, int _w) {
        //orange
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        block_.setType(EnvironmentType.BUILDING);
        block_.setTileFileName(BUILDING);
        return block_;
    }

    private static Block newBlock(int _h, int _w) {
        //black
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        block_.setType(EnvironmentType.NOTHING);
        block_.setTileFileName(NOTHING);
        return block_;
    }

    private static LevelPoint newLevelPoint(int _level, int _x, int _y) {
        LevelPoint begin_ = new LevelPoint();
        begin_.setLevelIndex((byte) _level);
        begin_.setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setInsideBuilding(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static PkTrainer toPkTrainer(NameLevel _nameLevel, StringList _moves) {
        PkTrainer pk_ = new PkTrainer();
        pk_.setName(_nameLevel.getName());
        pk_.setLevel(_nameLevel.getLevel());
        pk_.setAbility(ATTENTION);
        pk_.setItem(NULL_REF);
        pk_.setMoves(_moves);
        return pk_;
    }

    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }

    private static int[][] getImageByString(String _string) {
        return ImageCsv.getImageByString(_string);
    }
}
