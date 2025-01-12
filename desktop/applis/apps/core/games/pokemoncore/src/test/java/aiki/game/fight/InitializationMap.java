package aiki.game.fight;
import aiki.db.DataBase;
import aiki.db.EquallablePkUtil;
import aiki.db.ImageArrayBaseSixtyFour;
import aiki.db.ImageHeroKey;
import aiki.fight.pokemon.NameLevel;
import aiki.game.player.enums.Sex;
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
import aiki.map.levels.*;
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
import aiki.util.*;
import code.util.CustList;
import code.util.*;

import code.util.StringList;

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
        MultAreaApparition area_;
        WildPk wild_;
        area_ = new MultAreaApparition();
        area_.setAvgNbSteps((short) 1);
//        area_.setMultFight((byte) 1);
        wild_ = new WildPk();
        wild_.setName(PIKACHU);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
//        area_.setWildPokemon(new CustList<WildPk>());
//        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(PIKACHU);
        wild_.setLevel((short) 3);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = new MultAreaApparition();
        area_.setAvgNbSteps((short) 1);
//        area_.setMultFight((byte) 1);
        wild_ = new WildPk();
        wild_.setName(ARTIKODIN);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
//        area_.setWildPokemon(new CustList<WildPk>());
//        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = new MultAreaApparition();
        area_.setAvgNbSteps((short) 2);
//        area_.setMultFight((byte) 1);
        wild_ = new WildPk();
        wild_.setName(ARTIKODIN);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
//        area_.setWildPokemon(new CustList<WildPk>());
//        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = new MultAreaApparition();
        area_.setAvgNbSteps((short) 2);
//        area_.setMultFight((byte) 1);
        wild_ = new WildPk();
        wild_.setName(ARTIKODIN);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
//        area_.setWildPokemon(new CustList<WildPk>());
//        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(MEW);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = new MultAreaApparition();
        area_.setAvgNbSteps((short) 1);
//        area_.setMultFight((byte) 1);
//        area_.setWildPokemon(new CustList<WildPk>());
//        area_.setWildPokemonFishing(new CustList<WildPk>());
        wild_ = new WildPk();
        wild_.setName(PTITARD);
        wild_.setLevel((short) 1);
        wild_.setAbility(ABSORB_EAU);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(PTITARD);
        wild_.setLevel((short) 1);
        wild_.setAbility(ABSORB_EAU);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonFishingList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(TETARTE);
        wild_.setLevel((short) 26);
        wild_.setAbility(ABSORB_EAU);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(TETARTE);
        wild_.setLevel((short) 26);
        wild_.setAbility(ABSORB_EAU);
        wild_.setGender(Gender.NO_GENDER);
        wild_.setItem(PIERRE_EAU);
        area_.getWildPokemonFishingList().add(new CustList<WildPk>(wild_));
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = new MultAreaApparition();
        area_.setAvgNbSteps((short) 1);
//        area_.setMultFight((byte) 1);
//        area_.setWildPokemon(new CustList<WildPk>());
//        area_.setWildPokemonFishing(new CustList<WildPk>());
        wild_ = new WildPk();
        wild_.setName(TETARTE);
        wild_.setLevel((short) 26);
        wild_.setAbility(ABSORB_EAU);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(TETARTE);
        wild_.setLevel((short) 26);
        wild_.setAbility(ABSORB_EAU);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonFishingList().add(new CustList<WildPk>(wild_));
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
        level_ = (LevelCave)cave_.getLevelsMap().getVal(0);
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
        level_ = (LevelCave)cave_.getLevelsMap().getVal(1);
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
        level_ = (LevelCave)road_.getLevelsMap().getVal(1);
        MultAreaApparition area_;
        WildPk wild_;
        area_ = new MultAreaApparition();
        area_.setAvgNbSteps((short) 1);
//        area_.setMultFight((byte) 2);
        wild_ = new WildPk();
        wild_.setName(TARINOR);
        wild_.setLevel((short) 10);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
//        area_.setWildPokemon(new CustList<WildPk>());
//        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(NINGALE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        level_.getWildPokemonAreas().add(area_);
        area_ = new MultAreaApparition();
//        area_.setWildPokemon(new CustList<WildPk>());
//        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.setAvgNbSteps((short) 1);
//        area_.setMultFight((byte) 2);
        wild_ = new WildPk();
        wild_.setName(NINGALE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(NUCLEOS);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(NUCLEOS);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(TARINOR);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(TARINOR);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(NINGALE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(NINGALE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(CARAPUCE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(PICHU);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(YANMA);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(YANMA);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(BABIMANTA);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(BABIMANTA);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(PTITARD);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(PTITARD);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(REMORAID);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(MELOFEE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(LIMAGMA);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(CHENITI);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(CHENITI);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(LIMAGMA_F);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.FEMALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
        wild_ = new WildPk();
        wild_.setName(LIMAGMA_M);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.MALE);
        area_.getWildPokemonList().add(new CustList<WildPk>(wild_));
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
        ((LevelCave)cave_.getLevelsMap().getVal(0)).getDualFights().addEntry(newPoint(2, 0), dual_);
        //map_.getBeatGymLeader().add(newCoords(5, 0, 2, 0));
        foeTeamsList_ = new CustList<PokemonTeam>();
        CustList<PkTrainer> teamTwo_ = new CustList<PkTrainer>();
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 13), new StringList(JACKPOT)));
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 14), new StringList(JACKPOT)));
        foeTeamList_ = nvTeam((short) 200, teamTwo_);
        foeTeamsList_.add(foeTeamList_);
        trainer_ = newTrainer(foeTeamsList_, 1);
        ((LevelCave)cave_.getLevelsMap().getVal(0)).getCharacters().addEntry(newPoint(1, 5), trainer_);
        //map_.getBeatTrainer().add(new NbFightCoords(newCoords(5, 0, 1, 5),0));
        foeTeamsList_ = new CustList<PokemonTeam>();
        CustList<PkTrainer> teamThree_ = new CustList<PkTrainer>();
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 13), new StringList(JACKPOT)));
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 14), new StringList(JACKPOT)));
        foeTeamList_ = nvTeam((short) 200, teamThree_);
        foeTeamsList_.add(foeTeamList_);
        trainer_ = newTrainer(foeTeamsList_, 1);
        ((LevelCave)cave_.getLevelsMap().getVal(1)).getCharacters().addEntry(newPoint(5, 1), trainer_);
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
        map_.addLevelLeague(6);
        league_.getRooms().last().setFileName(LINK);
    }

    static void initBlockLeague(DataBase _data) {
        DataMap map_ = _data.getMap();
        League road_ = (League) map_.getPlace((short) 6);
        LevelLeague level_;
        Block block_;
        level_ = (LevelLeague) road_.getLevelsMap().getVal(0);
        block_ = newBuildingBlock(9, 9);
        level_.getBlocks().addEntry(newPoint(0,0), block_);
        level_ = (LevelLeague) road_.getLevelsMap().getVal(1);
        block_ = newBuildingBlock(9, 9);
        level_.getBlocks().addEntry(newPoint(0,0), block_);
        road_.setBegin(newPoint(4,8));
        level_ = (LevelLeague) road_.getLevelsMap().getVal(0);
        level_.setAccessPoint(newPoint(4, 0));
        level_.setNextLevelTarget(newPoint(4, 8));
        level_ = (LevelLeague) road_.getLevelsMap().getVal(1);
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
//        DataMap map_ = _data.getMap();
//        CustList<String> pkKeys_ = _data.getPokedex().getKeys();
//        TstsPk.commonImage(pkKeys_,"2;-13;-15;-15;-15",_data.getMaxiPkBack());
//        TstsPk.commonImage(pkKeys_,"2;-14;-15;-15;-15",_data.getMaxiPkFront());
//        TstsPk.commonImage(pkKeys_,"2;-15;-15;-15;-15",_data.getMiniPk());

        initImgsPk(_data, InitializationPokedex.NUCLEOS);
        initImgsPk(_data, InitializationPokedex.MEIOS);
        initImgsPk(_data, InitializationPokedex.SYMBIOS);
        initImgsPk(_data, InitializationPokedex.PTITARD);
        initImgsPk(_data, InitializationPokedex.TETARTE);
        initImgsPk(_data, InitializationPokedex.TARPAUD);
        initImgsPk(_data, InitializationPokedex.TARTARD);
        initImgsPk(_data, InitializationPokedex.YANMA);
        initImgsPk(_data, InitializationPokedex.YANMEGA);
        initImgsPk(_data, InitializationPokedex.TARINOR);
        initImgsPk(_data, InitializationPokedex.TARINORME);
        initImgsPk(_data, InitializationPokedex.CHENITI);
        initImgsPk(_data, InitializationPokedex.PAPILORD);
        initImgsPk(_data, InitializationPokedex.CHENISELLE);
        initImgsPk(_data, InitializationPokedex.BABIMANTA);
        initImgsPk(_data, InitializationPokedex.DEMANTA);
        initImgsPk(_data, InitializationPokedex.NINGALE);
        initImgsPk(_data, InitializationPokedex.MUNJA);
        initImgsPk(_data, InitializationPokedex.NINJASK);
        initImgsPk(_data, InitializationPokedex.PIKACHU);
        initImgsPk(_data, InitializationPokedex.ARTIKODIN);
        initImgsPk(_data, InitializationPokedex.MEW);
        initImgsPk(_data, InitializationPokedex.LIMAGMA);
        initImgsPk(_data, InitializationPokedex.MELOFEE);
        initImgsPk(_data, InitializationPokedex.MELODELFE);
        initImgsPk(_data, InitializationPokedex.MELODELFE_2);
        initImgsPk(_data, InitializationPokedex.PICHU);
        initImgsPk(_data, InitializationPokedex.REMORAID);
        initImgsPk(_data, InitializationPokedex.LIMAGMA_F);
        initImgsPk(_data, InitializationPokedex.LIMAGMA_M);
        initImgsPk(_data, InitializationPokedex.CARAPUCE);
        initImgsPk(_data, InitializationPokedex.CARABAFFE);


        initImgsIt(_data, InitializationItems.REPOUSSE);
        initImgsIt(_data, InitializationItems.PEPITE);
        initImgsIt(_data, InitializationItems.MAGNET);
        initImgsIt(_data, InitializationItems.METRONOME_OBJ);
        initImgsIt(_data, InitializationItems.GROSSERACINE);
        initImgsIt(_data, InitializationItems.GRELOT);
        initImgsIt(_data, InitializationItems.LUNETTES_FILTRE);
        initImgsIt(_data, InitializationItems.LICHEN_LUMINEUX);
        initImgsIt(_data, InitializationItems.VULNE_ASSURANCE);
        initImgsIt(_data, InitializationItems.HUILE);
        initImgsIt(_data, InitializationItems.RASP_BERRY);
        initImgsIt(_data, InitializationItems.BLACK_BERRY);
        initImgsIt(_data, InitializationItems.BAIE_ENIGMA);
        initImgsIt(_data, InitializationItems.MAX_ELIXIR);
        initImgsIt(_data, InitializationItems.CENDRESACREE);
        initImgsIt(_data, InitializationItems.BAIE_LAMPOU);
        initImgsIt(_data, InitializationItems.BAIE_MANGA);
        initImgsIt(_data, InitializationItems.NOEUD_DESTIN);
        initImgsIt(_data, InitializationItems.LUMARGILE);
        initImgsIt(_data, InitializationItems.GRAND_RAPPEL);
        initImgsIt(_data, InitializationItems.PETIT_RAPPEL);
        initImgsIt(_data, InitializationItems.RAPPEL);
        initImgsIt(_data, InitializationItems.POTION_MAX);
        initImgsIt(_data, InitializationItems.BOUTON_FUITE);
        initImgsIt(_data, InitializationItems.PLAQUE_DRACO);
        initImgsIt(_data, InitializationItems.ORBE_VIE);
        initImgsIt(_data, InitializationItems.EAU_FRAICHE);
        initImgsIt(_data, InitializationItems.POTION);
        initImgsIt(_data, InitializationItems.HAPPY_POTION);
        initImgsIt(_data, InitializationItems.RESTES);
        initImgsIt(_data, InitializationItems.CEINT_FORCE);
        initImgsIt(_data, InitializationItems.LENTILSCOPE);
        initImgsIt(_data, InitializationItems.MULTI_EXP);
        initImgsIt(_data, InitializationItems.PIERRE_STASE);
        initImgsIt(_data, InitializationItems.GRAIN_MIRACL);
        initImgsIt(_data, InitializationItems.BRAC_MACHO);
        initImgsIt(_data, InitializationItems.BAIE_MICLE);
        initImgsIt(_data, InitializationItems.BAIE_ORAN);
        initImgsIt(_data, InitializationItems.ENCENS_PLEIN);
        initImgsIt(_data, InitializationItems.CEINTURE_PRO);
        initImgsIt(_data, InitializationItems.MAX_REPOUSSE);
        initImgsIt(_data, InitializationItems.PV_PLUS);
        initImgsIt(_data, InitializationItems.BAIE_GOWAV);
        initImgsIt(_data, InitializationItems.ORBE_FLAMME);
        initImgsIt(_data, InitializationItems.PP_PLUS);
        initImgsIt(_data, InitializationItems.PP_PLUS_BIS);
        initImgsIt(_data, InitializationItems.MUSCLE);
        initImgsIt(_data, InitializationItems.BOLT);
        initImgsIt(_data, InitializationItems.BAIE_MEPO);
        initImgsIt(_data, InitializationItems.PT_DE_MIRE);
        initImgsIt(_data, InitializationItems.TOTAL_SOIN);
        initImgsIt(_data, InitializationItems.REVEIL);
        initImgsIt(_data, InitializationItems.PIQUANTS);
        initImgsIt(_data, InitializationItems.PIECE_RUNE);
        initImgsIt(_data, InitializationItems.PIERRALLEGEE);
        initImgsIt(_data, InitializationItems.CABLE);
        initImgsIt(_data, InitializationItems.BAIE_CHERIM);
        initImgsIt(_data, InitializationItems.CEINT_POUV);
        initImgsIt(_data, InitializationItems.BATON);
        initImgsIt(_data, InitializationItems.CARTE_ROUGE);
        initImgsIt(_data, InitializationItems.BANDEAU_ETREINTE);
        initImgsIt(_data, InitializationItems.HYPER_BALL);
        initImgsIt(_data, InitializationItems.MASTER_BALL);
        initImgsIt(_data, InitializationItems.SUPER_BALL);
        initImgsIt(_data, InitializationItems.POKE_BALL);
        initImgsIt(_data, InitializationItems.LUXE_BALL);
        initImgsIt(_data, InitializationItems.PAS_DE_BALL);
        initImgsIt(_data, InitializationItems.ROCHE_ROYALE);
        initImgsIt(_data, InitializationItems.PIERRE_EAU);
        initImgsIt(_data, InitializationItems.PIERRE_LUNE);
        initImgsIt(_data, InitializationItems.PIERRE_SOLEIL);
        initImgsIt(_data, InitializationItems.PIERRE_GLACE);
        initImgsIt(_data, InitializationItems.POUDRE_VITE);
        initImgsIt(_data, InitializationItems.POUDRE_ATTAQUE);
        initImgsIt(_data, InitializationItems.BOUE_NOIRE);
        initImgsIt(_data, InitializationItems.BOUE_BLANCHE);
        initImgsIt(_data, InitializationItems.HERBE_MENTAL);
        initImgsIt(_data, InitializationItems.ENCENS_VAGUE);
        initImgsIt(_data, InitializationItems.ELIXIR);
        initImgsIt(_data, InitializationItems.BANDEAU);
        initImgsIt(_data, InitializationItems.BAIE_PITAYE);
        initImgsIt(_data, InitializationItems.BAIE_LANSAT);
        initImgsIt(_data, InitializationItems.HERBE_POUV);
        initImgsIt(_data, InitializationItems.BAIE_CERIZ);
        initImgsIt(_data, InitializationItems.GRELOT_ZEN);
        initImgsIt(_data, InitializationItems.ACCRO_GRIFFE);
        initImgsIt(_data, InitializationItems.ENCENS_PUR);
        initImgsIt(_data, InitializationItems.BAIE_JABOCA);
        initImgsIt(_data, InitializationItems.OEUF_CHANCE);
        initImgsIt(_data, InitializationItems.VIVE_GRIFFE);
        initImgsIt(_data, InitializationItems.VIVE_GRIFFE_TRUE);
        initImgsIt(_data, InitializationItems.VIVE_GRIFFE_TRUE_FALSE);
        initImgsIt(_data, InitializationItems.VIVE_GRIFFE_FALSE);
        initImgsIt(_data, InitializationItems.GRELOT_COQUE);
        initImgsIt(_data, InitializationItems.VIEIL_AMBRE);
        initImgsIt(_data, InitializationItems.LAVA);
        initImgsIt(_data, InitializationItems.BALLON);
        initImgsIt(_data, InitializationItems.HUILE_MAX);
        initImgsIt(_data, InitializationItems.HERBEBLANCHE);
        initImgsIt(_data, InitializationItems.ROCHE_LISSE);
        initImgsSt(_data, InitializationStatus.CONFUSION);
        initImgsSt(_data, InitializationStatus.LONGUE_CONFUSION_DOMMAGE);
        initImgsSt(_data, InitializationStatus.LONGUE_CONFUSION_SANS_DOMMAGE);
        initImgsSt(_data, InitializationStatus.PRISE_DE_TETE);
        initImgsSt(_data, InitializationStatus.COUP_DE_BEC);
        initImgsSt(_data, InitializationStatus.PEUR);
        initImgsSt(_data, InitializationStatus.TROUILLE);
        initImgsSt(_data, InitializationStatus.POISON_GRAVE);
        initImgsSt(_data, InitializationStatus.VAMPIGRAINE);
        initImgsSt(_data, InitializationStatus.AMOUR);
        initImgsSt(_data, InitializationStatus.AMOUR_FOU);
        initImgsSt(_data, InitializationStatus.AMOUR_MOU);
        initImgsSt(_data, InitializationStatus.AMOUR_TRES_MOU);
        initImgsSt(_data, InitializationStatus.CAUCHEMAR);
        initImgsSt(_data, InitializationStatus.NUIT_BLANCHE);
        initImgsSt(_data, InitializationStatus.NUIT_BLANCHE_BIS);
        initImgsSt(_data, InitializationStatus.NUIT_GRISE);
        initImgsSt(_data, InitializationStatus.NUIT_NOIRE);
        initImgsSt(_data, InitializationStatus.GEL);
        initImgsSt(_data, InitializationStatus.ERE_GEL);
        initImgsSt(_data, InitializationStatus.FEU_GEL);
        initImgsSt(_data, InitializationStatus.BRULURE);
        initImgsSt(_data, InitializationStatus.CRAME);
        initImgsSt(_data, InitializationStatus.CRAME_BIS);
        initImgsSt(_data, InitializationStatus.POISON_ST);
        initImgsSt(_data, InitializationStatus.PARALYSIE);
        initImgsSt(_data, InitializationStatus.PARALYSIE_FORTE);
        initImgsSt(_data, InitializationStatus.SOMMEIL);
        initImgsSt(_data, InitializationStatus.SOMMEIL_REPOS);
        initImgsSta(_data, DataBase.DEF_STAT_ATTACK);
        initImgsSta(_data, DataBase.DEF_STAT_DEFENSE);
        initImgsSta(_data, DataBase.DEF_STAT_SPECIAL_ATTACK);
        initImgsSta(_data, DataBase.DEF_STAT_SPECIAL_DEFENSE);
        initImgsSta(_data, DataBase.DEF_STAT_SPEED);
        initImgsSta(_data, DataBase.DEF_STAT_ACCURACY);
        initImgsSta(_data, DataBase.DEF_STAT_EVASINESS);
        initImgsSta(_data, DataBase.DEF_STAT_CRITICAL_HIT);
//        TstsPk.commonImage(_data.getItems().getKeys(), "2;-16;-16;-16;-16", _data.getMiniItems());
//        TstsPk.commonImage(_data.getStatus().getKeys(), "2;-17;-16;-16;-16", _data.getAnimStatus());
//        TstsPk.commonImage(DataBase.statisNames(), "2;-18;-16;-16;-16", _data.getAnimStatis());
        initTypesImages(_data);
        _data.addImage(BUILDING, longRows());
        _data.addImage(DAFAULT, rows(-1));
        _data.addImage(DESERT, rows(-3584));
        _data.addImage(GRASS, rows(-14503604));
        _data.addImage(NOTHING, rows(-16777216));
        _data.addImage(ROAD, rows(-7369361));
        _data.addImage(ROCK, rows(-4621737));
        _data.addImage(SNOW, rows(-1));
        _data.addImage(WATER, rows(-16776961));
//        _data.getPeople().addEntry(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"trainer", "2;-18000;-18000;-18000;-18000");
//        _data.getPeople().addEntry(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"person", "2;-1800;-1800;-1800;-1800");
//        _data.getPeople().addEntry(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"trainer_one", "2;-19000;-19000;-19000;-19000");
//        _data.getPeople().addEntry(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"trainer_two", "2;-19008;-19008;-19008;-19008");
//        _data.getPeople().addEntry(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"ally", "2;-19508;-19508;-19508;-19508");
//        _data.getPeople().addEntry(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"gerant", "2;-20508;-20508;-20508;-20508");
        _data.addPerson(TRAINER, square(-18000,-18000,-18000,-18000));
        _data.addPerson(PERSON, square(-1800,-1800,-1800,-1800));
        _data.addPerson(TRAINER_ONE, square(-19000,-19000,-19000,-19000));
        _data.addPerson(TRAINER_TWO, square(-19008,-19008,-19008,-19008));
        _data.addPerson(ALLY, square(-19508,-19508,-19508,-19508));
        _data.addPerson(GERANT, square(-20508,-20508,-20508,-20508));

        //begin insertion
        _data.addTrainerImage(TRAINER, square(-18000,-18000,-18000,-18000));
        _data.addTrainerImage(TRAINER_ONE, square(-19000,-19000,-19000,-19000));
        _data.addTrainerImage(TRAINER_TWO, square(-19008,-19008,-19008,-19008));
        _data.addTrainerImage(ALLY, square(-19508,-19508,-19508,-19508));
        //end insertion

        heroInit(_data);
        _data.addLink(LINK, square(-255,-255,-255,-255));
        _data.setImageTmHm(square(-800,-800,-800,-800));
        _data.setAnimAbsorb(square(-700,-800,-800,-800));
        _data.setStorage(square(-3,-3,-3,-3));
        _data.getMiniMap().addEntry(MINI, square(118,218,112,200));
        _data.getMiniMap().addEntry(MINI1, square(218,118,112,200));
        _data.getMiniMap().addEntry(MINI2, square(218,112,118,200));
        _data.getMiniMap().addEntry(MINI3, square(218,112,200,118));
        _data.getMiniMap().addEntry(MINI4, square(218,200,112,118));
        _data.getMiniMap().addEntry(MINI5, square(200,218,112,118));
        _data.getMiniMap().addEntry(MINI6, square(200,218,212,118));
        _data.setEndGameImage(ImageArrayBaseSixtyFour.instance(new int[][]{new int[]{1}},NULL_REF));
    }

    private static void initImgsSta(DataBase _data, String _key) {
        _data.getAnimStatis().addEntry(_key,square(-18,-16,-16,-16));
    }

    private static void initImgsSt(DataBase _data, String _key) {
        _data.getAnimStatus().addEntry(_key,square(-17,-16,-16,-16));
    }

    private static void initImgsIt(DataBase _data, String _key) {
        _data.getMiniItems().addEntry(_key,square(-16,-16,-16,-16));
    }

    private static void initImgsPk(DataBase _data, String _key) {
        _data.getMaxiPkBack().addEntry(_key,square(-13,-15,-15,-15));
        _data.getMaxiPkFront().addEntry(_key,square(-14,-15,-15,-15));
        _data.getMiniPk().addEntry(_key,square(-15,-15,-15,-15));
    }

    static void initTypesImages(DataBase _data) {
        addType(_data, InitializationDataBase.ACIER);
        addType(_data, InitializationDataBase.COMBAT);
        addType(_data, InitializationDataBase.DRAGON);
        addType(_data, InitializationDataBase.EAU);
        addType(_data, InitializationDataBase.ELECTRIQUE);
        addType(_data, InitializationDataBase.FEU);
        addType(_data, InitializationDataBase.GLACE);
        addType(_data, InitializationDataBase.INSECTE);
        addType(_data, InitializationDataBase.NORMAL);
        addType(_data, InitializationDataBase.PLANTE);
        addType(_data, InitializationDataBase.POISON);
        addType(_data, InitializationDataBase.PSY);
        addType(_data, InitializationDataBase.ROCHE);
        addType(_data, InitializationDataBase.SOL);
        addType(_data, InitializationDataBase.SPECTRE);
        addType(_data, InitializationDataBase.TENEBRE);
        addType(_data, InitializationDataBase.VOL);
        addType(_data, InitializationDataBase.FEE);
    }

    private static void addType(DataBase _data, String _type) {
        _data.getTypesColors().addEntry(_type,color(_data));
        _data.getTypesImages().addEntry(_type, squareUni(_data));
    }

    private static ImageArrayBaseSixtyFour squareUni(DataBase _data) {
        return square(_data.getTypesColors().size(), _data.getTypesColors().size(), _data.getTypesColors().size(), _data.getTypesColors().size());
    }

    static String color(DataBase _data) {
        int nb_ = _data.getTypesColors().size() + 1;
        return nb_ +DataBase.SEPARATOR_RGB+ nb_ +DataBase.SEPARATOR_RGB+ nb_;
    }
    static ImageArrayBaseSixtyFour square(int _one, int _two, int _three, int _four) {
        return instance(new int[][]{new int[]{_one,_two}, new int[]{_three,_four}});
    }
    static ImageArrayBaseSixtyFour rows(int _v) {
        return instance(new int[][]{new int[]{_v, _v, _v, _v}, new int[]{_v, _v, _v, _v}, new int[]{_v, _v, _v, _v}, new int[]{_v, _v, _v, _v}});
    }
    static ImageArrayBaseSixtyFour longRows() {
        return instance(new int[][]{longRow(),longRow(),longRow(),longRow(),longRow(),longRow(),longRow(),longRow(),longRow(),longRow(),longRow(),longRow(),longRow(),longRow(),longRow(),longRow(),longRow(),longRow()});
    }
    static int[] longRow() {
        return new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985};
    }

    static void initMiniMap(DataBase _data) {
        DataMap map_ = _data.getMap();
        TileMiniMap tile_;
        map_.setMiniMap(new MiniMapCoordsList());
        tile_ = new TileMiniMap();
        tile_.setFile(MINI);
        tile_.setPlace(0);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,0), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace(1);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI);
        tile_.setPlace(2);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,2), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace(3);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,3), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI);
        tile_.setPlace(4);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,4), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI2);
        tile_.setPlace(5);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,5), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI3);
        tile_.setPlace(6);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,6), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace(7);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,7), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace(8);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,8), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI4);
        tile_.setPlace(-1);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,9), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI6);
        tile_.setPlace(9);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,10), tile_);
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
        block_.setIndexApparition( _index);
        return block_;
    }

    private static Block newRoadBlock(int _h, int _w) {
        Block block_;
        block_ = new Block();
        block_.setHeight(_h);
        block_.setWidth(_w);
        block_.setType(EnvironmentType.ROAD);
        //grey
        block_.setTileFileName(ROAD);
        return block_;
    }
    private static Block newWaterBlock(int _h, int _w, int _index) {
        Block block_ = newWaterBlock(_h, _w);
        block_.setIndexApparition( _index);
        return block_;
    }

    private static Block newWaterBlock(int _h, int _w) {
        //blue
        Block block_;
        block_ = new Block();
        block_.setHeight(_h);
        block_.setWidth(_w);
        block_.setType(EnvironmentType.WATER);
        block_.setTileFileName(WATER);
        return block_;
    }

    private static Block newRockBlock(int _h, int _w) {
        //brown
        Block block_;
        block_ = new Block();
        block_.setHeight(_h);
        block_.setWidth(_w);
        block_.setType(EnvironmentType.ROCK);
        block_.setTileFileName(ROCK);
        return block_;
    }

    private static Block newRockBlock(int _h, int _w, int _index) {
        Block block_ = newRockBlock(_h, _w);
        block_.setIndexApparition( _index);
        return block_;
    }

    private static Block newDesertBlock(int _h, int _w) {
        //yellow
        Block block_;
        block_ = new Block();
        block_.setHeight(_h);
        block_.setWidth(_w);
        block_.setType(EnvironmentType.DESERT);
        block_.setTileFileName(DESERT);
        return block_;
    }

    private static Block newBuildingBlock(int _h, int _w) {
        //orange
        Block block_;
        block_ = new Block();
        block_.setHeight(_h);
        block_.setWidth(_w);
        block_.setType(EnvironmentType.BUILDING);
        block_.setTileFileName(BUILDING);
        return block_;
    }

    private static Block newBlock(int _h, int _w) {
        //black
        Block block_;
        block_ = new Block();
        block_.setHeight(_h);
        block_.setWidth(_w);
        block_.setType(EnvironmentType.NOTHING);
        block_.setTileFileName(NOTHING);
        return block_;
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

    public static void heroInit(DataBase _data) {
        CustList<Sex> sex_ = Sex.all();
        Sex one_ = sex_.first();
        Sex two_ = sex_.last();
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.BUILDING,one_),instance(new int[][]{new int[]{0,1},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.BUILDING,two_),instance(new int[][]{new int[]{3,4},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.DESERT,one_),instance(new int[][]{new int[]{6,7},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.DESERT,two_),instance(new int[][]{new int[]{9,10},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.WATER,one_),instance(new int[][]{new int[]{12,13},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.WATER,two_),instance(new int[][]{new int[]{15,16},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.ICE,one_),instance(new int[][]{new int[]{18,19},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.ICE,two_),instance(new int[][]{new int[]{21,22},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.GRASS,one_),instance(new int[][]{new int[]{24,25},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.GRASS,two_),instance(new int[][]{new int[]{27,28},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.SNOW,one_),instance(new int[][]{new int[]{30,31},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.SNOW,two_),instance(new int[][]{new int[]{33,34},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,one_),instance(new int[][]{new int[]{36,37},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,two_),instance(new int[][]{new int[]{39,40},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,one_),instance(new int[][]{new int[]{42,43},new int[]{-2,-2}}));
        _data.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,two_),instance(new int[][]{new int[]{45,46},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.BUILDING,one_),instance(new int[][]{new int[]{0,1},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.BUILDING,two_),instance(new int[][]{new int[]{3,4},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.DESERT,one_),instance(new int[][]{new int[]{6,7},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.DESERT,two_),instance(new int[][]{new int[]{9,10},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.WATER,one_),instance(new int[][]{new int[]{12,13},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.WATER,two_),instance(new int[][]{new int[]{15,16},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.ICE,one_),instance(new int[][]{new int[]{18,19},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.ICE,two_),instance(new int[][]{new int[]{21,22},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.GRASS,one_),instance(new int[][]{new int[]{24,25},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.GRASS,two_),instance(new int[][]{new int[]{27,28},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.SNOW,one_),instance(new int[][]{new int[]{30,31},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.SNOW,two_),instance(new int[][]{new int[]{33,34},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,one_),instance(new int[][]{new int[]{36,37},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,two_),instance(new int[][]{new int[]{39,40},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,one_),instance(new int[][]{new int[]{42,43},new int[]{-2,-2}}));
        _data.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,two_),instance(new int[][]{new int[]{45,46},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.BUILDING,Direction.UP,one_),instance(new int[][]{new int[]{0,0},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.BUILDING,Direction.UP,two_),instance(new int[][]{new int[]{1,1},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.BUILDING,Direction.DOWN,one_),instance(new int[][]{new int[]{2,2},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.BUILDING,Direction.DOWN,two_),instance(new int[][]{new int[]{3,3},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.BUILDING,Direction.LEFT,one_),instance(new int[][]{new int[]{4,4},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.BUILDING,Direction.LEFT,two_),instance(new int[][]{new int[]{5,5},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.BUILDING,Direction.RIGHT,one_),instance(new int[][]{new int[]{6,6},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.BUILDING,Direction.RIGHT,two_),instance(new int[][]{new int[]{7,7},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.DESERT,Direction.UP,one_),instance(new int[][]{new int[]{8,8},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.DESERT,Direction.UP,two_),instance(new int[][]{new int[]{9,9},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.DESERT,Direction.DOWN,one_),instance(new int[][]{new int[]{10,10},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.DESERT,Direction.DOWN,two_),instance(new int[][]{new int[]{11,11},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.DESERT,Direction.LEFT,one_),instance(new int[][]{new int[]{12,12},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.DESERT,Direction.LEFT,two_),instance(new int[][]{new int[]{13,13},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.DESERT,Direction.RIGHT,one_),instance(new int[][]{new int[]{14,14},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.DESERT,Direction.RIGHT,two_),instance(new int[][]{new int[]{15,15},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.WATER,Direction.UP,one_),instance(new int[][]{new int[]{16,16},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.WATER,Direction.UP,two_),instance(new int[][]{new int[]{17,17},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.WATER,Direction.DOWN,one_),instance(new int[][]{new int[]{18,18},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.WATER,Direction.DOWN,two_),instance(new int[][]{new int[]{19,19},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.WATER,Direction.LEFT,one_),instance(new int[][]{new int[]{20,20},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.WATER,Direction.LEFT,two_),instance(new int[][]{new int[]{21,21},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.WATER,Direction.RIGHT,one_),instance(new int[][]{new int[]{22,22},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.WATER,Direction.RIGHT,two_),instance(new int[][]{new int[]{23,23},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ICE,Direction.UP,one_),instance(new int[][]{new int[]{24,24},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ICE,Direction.UP,two_),instance(new int[][]{new int[]{25,25},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ICE,Direction.DOWN,one_),instance(new int[][]{new int[]{26,26},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ICE,Direction.DOWN,two_),instance(new int[][]{new int[]{27,27},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ICE,Direction.LEFT,one_),instance(new int[][]{new int[]{28,28},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ICE,Direction.LEFT,two_),instance(new int[][]{new int[]{29,29},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ICE,Direction.RIGHT,one_),instance(new int[][]{new int[]{30,30},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ICE,Direction.RIGHT,two_),instance(new int[][]{new int[]{31,31},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.GRASS,Direction.UP,one_),instance(new int[][]{new int[]{32,32},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.GRASS,Direction.UP,two_),instance(new int[][]{new int[]{33,33},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.GRASS,Direction.DOWN,one_),instance(new int[][]{new int[]{34,34},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.GRASS,Direction.DOWN,two_),instance(new int[][]{new int[]{35,35},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.GRASS,Direction.LEFT,one_),instance(new int[][]{new int[]{36,36},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.GRASS,Direction.LEFT,two_),instance(new int[][]{new int[]{37,37},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.GRASS,Direction.RIGHT,one_),instance(new int[][]{new int[]{38,38},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.GRASS,Direction.RIGHT,two_),instance(new int[][]{new int[]{39,39},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.SNOW,Direction.UP,one_),instance(new int[][]{new int[]{40,40},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.SNOW,Direction.UP,two_),instance(new int[][]{new int[]{41,41},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.SNOW,Direction.DOWN,one_),instance(new int[][]{new int[]{42,42},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.SNOW,Direction.DOWN,two_),instance(new int[][]{new int[]{43,43},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.SNOW,Direction.LEFT,one_),instance(new int[][]{new int[]{44,44},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.SNOW,Direction.LEFT,two_),instance(new int[][]{new int[]{45,45},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.SNOW,Direction.RIGHT,one_),instance(new int[][]{new int[]{46,46},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.SNOW,Direction.RIGHT,two_),instance(new int[][]{new int[]{47,47},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,Direction.UP,one_),instance(new int[][]{new int[]{48,48},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,Direction.UP,two_),instance(new int[][]{new int[]{49,49},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,Direction.DOWN,one_),instance(new int[][]{new int[]{50,50},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,Direction.DOWN,two_),instance(new int[][]{new int[]{51,51},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,Direction.LEFT,one_),instance(new int[][]{new int[]{52,52},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,Direction.LEFT,two_),instance(new int[][]{new int[]{53,53},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,Direction.RIGHT,one_),instance(new int[][]{new int[]{54,54},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD,Direction.RIGHT,two_),instance(new int[][]{new int[]{55,55},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Direction.UP,one_),instance(new int[][]{new int[]{56,56},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Direction.UP,two_),instance(new int[][]{new int[]{57,57},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Direction.DOWN,one_),instance(new int[][]{new int[]{58,58},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Direction.DOWN,two_),instance(new int[][]{new int[]{59,59},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Direction.LEFT,one_),instance(new int[][]{new int[]{60,60},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Direction.LEFT,two_),instance(new int[][]{new int[]{61,61},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Direction.RIGHT,one_),instance(new int[][]{new int[]{62,62},new int[]{-2,-2}}));
        _data.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING,Direction.RIGHT,two_),instance(new int[][]{new int[]{63,63},new int[]{-2,-2}}));
    }
}
