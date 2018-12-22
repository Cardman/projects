package aiki.game.fight;
import aiki.db.DataBase;
import aiki.db.ImageHeroKey;
import aiki.fight.pokemon.NameLevel;
import aiki.game.player.enums.Sex;
import aiki.map.DataMap;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.Ally;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DealerItem;
import aiki.map.characters.DualFight;
import aiki.map.characters.GerantPokemon;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.characters.Person;
import aiki.map.characters.Seller;
import aiki.map.characters.TempTrainer;
import aiki.map.characters.Trainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.characters.enums.GeranceType;
import aiki.map.characters.enums.SellType;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Block;
import aiki.map.levels.Level;
import aiki.map.levels.LevelCave;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelIndoorPokemonCenter;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.levels.Link;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.InitializedPlace;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonTeam;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.TileMiniMap;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.images.Image;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringList;

final class InitializationMap {

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

//    static void initPlaces(DataBase _data) {
//        DataMap map_ = _data.getMap();
//        Road road_;
//        map_.addRoad();
//        road_ =(Road) map_.getPlaces().getVal((short) 0);
//        road_.setName(R_1);
//        map_.addCity(CITY);
//        map_.addRoad();
//        road_ =(Road) map_.getPlaces().getVal((short) 2);
//        road_.setName(R_2);
//        map_.addCity(CITY_TWO);
//        map_.addRoad();
//        road_ =(Road) map_.getPlaces().getVal((short) 4);
//        road_.setName(R_4);
//        map_.addCave();
//        Cave cave_ =(Cave) map_.getPlaces().getVal((short) 5);
//        cave_.setName(CAVE);
        //map map_.addLevelCave((short) 5);
//        map_.addLevelCave((short) 5);
//        map_.setSideLength(2);
//    }

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
        Road road_ = (Road) map_.getPlaces().getVal((short) 0);
        road_.setName(R_1);
        Block block_;
        block_ = newRoadBlock(2, 2, 0);
        road_.getLevel().getBlocks().put(newPoint(0,0), block_);
        block_ = newRoadBlock(2, 2, 1);
        road_.getLevel().getBlocks().put(newPoint(2,0), block_);
        block_ = newWaterBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2, 2);
        road_.getLevel().getBlocks().put(newPoint(0,2), block_);
        block_ = newRoadBlock(2, 2, 3);
        road_.getLevel().getBlocks().put(newPoint(2,2), block_);
        block_ = newWaterBlock(2, 2, 4);
        road_.getLevel().getBlocks().put(newPoint(4,2), block_);
        block_ = newWaterBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(0,4), block_);
        block_ = newWaterBlock(2, 2, 5);
        road_.getLevel().getBlocks().put(newPoint(2,4), block_);
        block_ = newWaterBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(4,4), block_);
    }

    static void initTrainersFirstRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        Road road_ = (Road) map_.getPlaces().getVal((short) 0);
        PokemonTeam foeTeamList_;
        CustList<NameLevelMoves> list_;
        CustList<PokemonTeam> foeTeamsList_;
        foeTeamsList_ = new CustList<PokemonTeam>();
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,3),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,4),new StringList(JACKPOT)));
        foeTeamList_ = newTeam(list_, 200);
        foeTeamsList_.add(foeTeamList_);
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,13),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,14),new StringList(JACKPOT)));
        foeTeamList_ = newTeam(list_, 200);
        foeTeamsList_.add(foeTeamList_);
        TrainerMultiFights trainer_;
        trainer_ = newTrainer(foeTeamsList_, 1);
        road_.addPerson(newCoords(0, 0, 1, 1), trainer_);
    }

    static void initFirstRoadAreas(DataBase _data) {
        DataMap map_ = _data.getMap();
        Road road_ = (Road) map_.getPlaces().getVal((short) 0);
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
        area_.setWildPokemon(new EqList<WildPk>());
        area_.setWildPokemonFishing(new EqList<WildPk>());
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(PIKACHU);
        wild_.setLevel((short) 3);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        road_.getLevel().getWildPokemonAreas().add(area_);
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 1);
        area_.setMultFight((byte) 1);
        wild_ = new WildPk();
        wild_.setName(ARTIKODIN);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.setWildPokemon(new EqList<WildPk>());
        area_.setWildPokemonFishing(new EqList<WildPk>());
        area_.getWildPokemon().add(wild_);
        road_.getLevel().getWildPokemonAreas().add(area_);
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 2);
        area_.setMultFight((byte) 1);
        wild_ = new WildPk();
        wild_.setName(ARTIKODIN);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.setWildPokemon(new EqList<WildPk>());
        area_.setWildPokemonFishing(new EqList<WildPk>());
        area_.getWildPokemon().add(wild_);
        road_.getLevel().getWildPokemonAreas().add(area_);
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 2);
        area_.setMultFight((byte) 1);
        wild_ = new WildPk();
        wild_.setName(ARTIKODIN);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.setWildPokemon(new EqList<WildPk>());
        area_.setWildPokemonFishing(new EqList<WildPk>());
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(MEW);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        road_.getLevel().getWildPokemonAreas().add(area_);
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 1);
        area_.setMultFight((byte) 1);
        area_.setWildPokemon(new EqList<WildPk>());
        area_.setWildPokemonFishing(new EqList<WildPk>());
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
        area_.getWildPokemonFishing().add(wild_);
        road_.getLevel().getWildPokemonAreas().add(area_);
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 1);
        area_.setMultFight((byte) 1);
        area_.setWildPokemon(new EqList<WildPk>());
        area_.setWildPokemonFishing(new EqList<WildPk>());
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
        road_.getLevel().getWildPokemonAreas().add(area_);
    }

    static void initBlockFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addCity(CITY);
        City city_ = (City) map_.getPlaces().getVal((short) 1);
        Block block_;
        block_ = newBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(0,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(2,0), block_);
        block_ = newBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(0,2), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(2,2), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(4,2), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(0,4), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(2,4), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(4,4), block_);
    }

    static void initBuildingsFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlaces().getVal((short) 1);
        Gym gym_;
        gym_ = new Gym();
        gym_.setLevel(new LevelIndoorGym());
        gym_.setExitCity(newPoint(4,8));
        gym_.getLevel().setBlocks(new ObjectMap<Point,Block>());
        gym_.getLevel().getBlocks().put(newPoint(0,0), newBuildingBlock(9, 9));
        gym_.getLevel().setGymTrainers(new ObjectMap<Point,GymTrainer>());
        city_.setBuildings(new ObjectMap<Point,Building>());
        city_.getBuildings().put(newPoint(5, 1), gym_);
        PokemonCenter pkCenter_;
        pkCenter_ = new PokemonCenter();
        pkCenter_.setLevel(new LevelIndoorPokemonCenter());
        pkCenter_.setExitCity(newPoint(4,8));
        pkCenter_.getLevel().setBlocks(new ObjectMap<Point,Block>());
        pkCenter_.getLevel().getBlocks().put(newPoint(0,0), newBuildingBlock(9, 9));
        pkCenter_.getLevel().setStorageCoords(newPoint(4, 0));
        pkCenter_.getLevel().setGerants(new ObjectMap<Point,Person>());
        city_.getBuildings().put(newPoint(1, 1), pkCenter_);
    }

    static void initTrainersFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlaces().getVal((short) 1);
        Gym gym_;
        GymTrainer gymTrainer_;
        GymLeader gymLeader_;
        CustList<NameLevelMoves> list_;
        gym_ = (Gym) city_.getBuildings().getVal(newPoint(5, 1));
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,3),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,4),new StringList(JACKPOT)));
        gymTrainer_ = newGymTrainer(list_, 200, 1);
        gym_.getLevel().getGymTrainers().put(newPoint(1, 7), gymTrainer_);
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,3),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,4),new StringList(JACKPOT)));
        gymTrainer_ = newGymTrainer(list_, 200, 1);
        gym_.getLevel().getGymTrainers().put(newPoint(7, 7), gymTrainer_);
        gym_.getLevel().setGymLeaderCoords(newPoint(4, 1));
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,5),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,8),new StringList(JACKPOT)));
        gymLeader_ = newGymLeader(list_, 500, 1);
        gymLeader_.setName(GYM_TR_ONE);
        gym_.getLevel().setGymLeader(gymLeader_);
    }

    static void initPokemonCenterFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlaces().getVal((short) 1);
        PokemonCenter pk_;
        pk_ = (PokemonCenter) city_.getBuildings().getVal(newPoint(1, 1));
        pk_.getLevel().getGerants().put(newPoint(0, 4), newGerantPokemon(GeranceType.HEAL));
        Seller seller_;
        seller_ = new Seller();
        seller_.setItems(new StringList(POKE_BALL,HYPER_BALL,PT_DE_MIRE, PIERRE_EAU,ROCHE_ROYALE,PIERRE_LUNE,PIERRE_SOLEIL,PIERRE_GLACE));
        seller_.setTm(new Numbers<Short>());
        seller_.setSell(SellType.ITEM);
        pk_.getLevel().getGerants().put(newPoint(8, 4), seller_);
        seller_ = new Seller();
        seller_.setItems(new StringList());
        seller_.setTm(new Numbers<Short>((short)2));
        seller_.setSell(SellType.TM);
        pk_.getLevel().getGerants().put(newPoint(8, 5), seller_);
        seller_ = new Seller();
        seller_.setItems(new StringList());
        seller_.setTm(new Numbers<Short>());
        seller_.setSell(SellType.MOVE);
        pk_.getLevel().getGerants().put(newPoint(8, 6), seller_);
    }

    static void initBlockSecondRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addRoad();
        Road road_ = (Road) map_.getPlaces().getVal((short) 2);
        road_.setName(R_2);
        Block block_;
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(0,0), block_);
        block_ = newWaterBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(2,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(6,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(8,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(10,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(0,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(2,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(4,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(6,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(8,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(10,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(0,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(2,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(4,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(6,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(8,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(10,4), block_);
    }

    static void initTrainersSecondRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        CustList<NameLevelMoves> allyList_;
        TrainerMultiFights trainer_;
        PokemonTeam foeTeamList_;
        CustList<PokemonTeam> foeTeamsList_;
        allyList_ = new CustList<NameLevelMoves>();
        allyList_.add(new NameLevelMoves(new NameLevel(PIKACHU,25),new StringList(JACKPOT,CHARGE)));
        allyList_.add(new NameLevelMoves(new NameLevel(PIKACHU,28),new StringList(TONNERRE)));
        CustList<NameLevelMoves> list_;
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,5),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,8),new StringList(JACKPOT)));
        Road road_ = (Road) map_.getPlaces().getVal((short) 2);
        DualFight dual_;
        dual_ = newDualFight(allyList_, list_, 300);
        dual_.setNames(new StringList(DUAL_ONE_TR_ONE,DUAL_ONE_TR_TWO));
        dual_.setPt(newPoint(3, 0));
        road_.addDualFight(newCoords(2, 0, 2, 0), dual_);
        //map_.getBeatGymLeader().add(newCoords(2, 0, 2, 0));
        allyList_ = new CustList<NameLevelMoves>();
        allyList_.add(new NameLevelMoves(new NameLevel(PIKACHU,25),new StringList(JACKPOT,CHARGE)));
        allyList_.add(new NameLevelMoves(new NameLevel(PIKACHU,28),new StringList(TONNERRE)));
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,5),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,8),new StringList(JACKPOT)));
        road_ = (Road) map_.getPlaces().getVal((short) 2);
        dual_ = newDualFight(allyList_, list_, 300);
        dual_.setNames(new StringList(DUAL_TWO_TR_ONE,DUAL_TWO_TR_TWO));
        dual_.setPt(newPoint(5, 0));
        road_.addDualFight(newCoords(2, 0, 4, 0), dual_);
        //map_.getBeatGymLeader().add(newCoords(2, 0, 4, 0));
        foeTeamsList_ = new CustList<PokemonTeam>();
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,3),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,4),new StringList(JACKPOT)));
        foeTeamList_ = newTeam(list_, 200);
        foeTeamsList_.add(foeTeamList_);
        trainer_ = newTrainer(foeTeamsList_, 1);
        road_.addPerson(newCoords(2, 0, 11, 4), trainer_);
    }

    static void initSecondRoadAreas(DataBase _data) {
        DataMap map_ = _data.getMap();
        WildPk wild_;
        Road road_ = (Road) map_.getPlaces().getVal((short) 2);
        wild_ = new WildPk();
        wild_.setName(MEW);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        road_.getLevel().getLegendaryPks().put(newPoint(11, 2), wild_);
        //map_.getTakenPokemon().add(newCoords(2, 0, 11, 2));
        wild_ = new WildPk();
        wild_.setName(ARTIKODIN);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        road_.getLevel().getLegendaryPks().put(newPoint(9, 5), wild_);
    }

    static void initItemsSecondRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        Road road_ = (Road) map_.getPlaces().getVal((short) 2);
        road_.addObject(newCoords(2, 0, 6, 5), HYPER_BALL);
        //map_.getTakenObjects().add(newCoords(2, 0, 6, 5));
        road_.addTm(newCoords(2, 0, 7, 5), (short) 2);
        //map_.getTakenObjects().add(newCoords(2, 0, 7, 5));
        road_.addHm(newCoords(2, 0, 8, 5), (short) 1);
    }

    static void initBlockSecondCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addCity(CITY_TWO);
        City city_ = (City) map_.getPlaces().getVal((short) 3);
        Block block_;
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(0,0), block_);
        block_ = newBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(2,0), block_);
        block_ = newBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(0,2), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(2,2), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(4,2), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(0,4), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(2,4), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(4,4), block_);
    }

    static void initBuildingsSecondCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_;
        // = (City) map_.getPlaces().getVal((short) 1);
        Gym gym_;
//        gym_ = new Gym();
//        gym_.setLevel(new LevelIndoorGym());
//        gym_.setExitCity(newPoint(4,8));
//        gym_.getLevel().setBlocks(new Map<Point,Block>());
//        gym_.getLevel().getBlocks().put(newPoint(0,0), newBuildingBlock(9, 9));
//        gym_.getLevel().setGymTrainers(new Map<Point,GymTrainer>());
//        city_.setBuildings(new Map<Point,Building>());
//        city_.getBuildings().put(newPoint(5, 1), gym_);
        PokemonCenter pkCenter_;
//        pkCenter_ = new PokemonCenter();
//        pkCenter_.setLevel(new LevelIndoorPokemonCenter());
//        pkCenter_.setExitCity(newPoint(4,8));
//        pkCenter_.getLevel().setBlocks(new Map<Point,Block>());
//        pkCenter_.getLevel().getBlocks().put(newPoint(0,0), newBuildingBlock(9, 9));
//        pkCenter_.getLevel().setStorageCoords(newPoint(4, 0));
//        pkCenter_.getLevel().setGerants(new Map<Point,Person>());
//        city_.getBuildings().put(newPoint(1, 1), pkCenter_);
        city_ = (City) map_.getPlaces().getVal((short) 3);
        gym_ = new Gym();
        gym_.setLevel(new LevelIndoorGym());
        gym_.setExitCity(newPoint(4,8));
        gym_.getLevel().setBlocks(new ObjectMap<Point,Block>());
        gym_.getLevel().getBlocks().put(newPoint(0,0), newBuildingBlock(9, 9));
        gym_.getLevel().setGymTrainers(new ObjectMap<Point,GymTrainer>());
        city_.setBuildings(new ObjectMap<Point,Building>());
        city_.getBuildings().put(newPoint(4, 1), gym_);
        pkCenter_ = new PokemonCenter();
        pkCenter_.setLevel(new LevelIndoorPokemonCenter());
        pkCenter_.setExitCity(newPoint(4,8));
        pkCenter_.getLevel().setBlocks(new ObjectMap<Point,Block>());
        pkCenter_.getLevel().getBlocks().put(newPoint(0,0), newBuildingBlock(9, 9));
        pkCenter_.getLevel().setStorageCoords(newPoint(4, 0));
        pkCenter_.getLevel().setGerants(new ObjectMap<Point,Person>());
        city_.getBuildings().put(newPoint(2, 1), pkCenter_);
//        map_.getCities().add(newCoords(1, 0, 1, 2));
//        map_.getCities().add(newCoords(3, 0, 2, 2));
    }

    static void initTrainersSecondCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlaces().getVal((short) 3);
        CustList<NameLevelMoves> list_;
        GymTrainer gymTrainer_;
        GymLeader gymLeader_;
        Gym gym_ = (Gym) city_.getBuildings().getVal(newPoint(4, 1));
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,3),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,4),new StringList(JACKPOT)));
        gymTrainer_ = newGymTrainer(list_, 200, 1);
        gym_.getLevel().getGymTrainers().put(newPoint(1, 7), gymTrainer_);
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,3),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,4),new StringList(JACKPOT)));
        gymTrainer_ = newGymTrainer(list_, 200, 1);
        gym_.getLevel().getGymTrainers().put(newPoint(7, 7), gymTrainer_);
        gym_.getLevel().setGymLeaderCoords(newPoint(4, 1));
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,5),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,8),new StringList(JACKPOT)));
        gymLeader_ = newGymLeader(list_, 500, 1);
        gymLeader_.setName(GYM_TR_TWO);
        gym_.getLevel().setGymLeader(gymLeader_);
    }

    static void initPokemonCenterSecondCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        //map_.getTakenObjects().add(newCoords(0, 0, 0, 1));
        City city_;
        PokemonCenter pk_;
        city_ = (City) map_.getPlaces().getVal((short) 3);
        pk_ = (PokemonCenter) city_.getBuildings().getVal(newPoint(2, 1));
        pk_.getLevel().getGerants().put(newPoint(0, 4), newGerantPokemon(GeranceType.FOSSILE));
        pk_.getLevel().getGerants().put(newPoint(8, 4), newGerantPokemon(GeranceType.HOST));
    }

    static void initBlockThirdRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addRoad();
        Road road_ = (Road) map_.getPlaces().getVal((short) 4);
        road_.setName(R_4);
        Block block_;
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(0,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(2,0), block_);
        block_ = newDesertBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(0,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(2,2), block_);
        block_ = newDesertBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(4,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(0,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(2,4), block_);
        block_ = newDesertBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(4,4), block_);
    }

    static void initBlockCave(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addCave();
        Cave cave_ = (Cave) map_.getPlaces().getVal((short) 5);
        cave_.setName(CAVE);
        map_.addLevelCave((short) 5);
        LevelCave level_;
        Block block_;
        level_ = (LevelCave)cave_.getLevelsMap().getVal((byte) 0);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(0,0), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(2,0), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(4,0), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(6,0), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(0,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(2,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(4,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(6,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(0,4), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(2,4), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(4,4), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(6,4), block_);
        level_ = (LevelCave)cave_.getLevelsMap().getVal((byte) 1);
        block_ = newRockBlock(2, 2, 0);
        level_.getBlocks().put(newPoint(0,0), block_);
        block_ = newRockBlock(2, 2, 1);
        level_.getBlocks().put(newPoint(2,0), block_);
        block_ = newRockBlock(2, 2, 1);
        level_.getBlocks().put(newPoint(4,0), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(6,0), block_);
        block_ = newRockBlock(2, 2, 0);
        level_.getBlocks().put(newPoint(0,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(2,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(4,2), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(6,2), block_);
        block_ = newRockBlock(2, 2, 0);
        level_.getBlocks().put(newPoint(0,4), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(2,4), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(4,4), block_);
        block_ = newRockBlock(2, 2);
        level_.getBlocks().put(newPoint(6,4), block_);
    }

//    static void initRoadAreas(DataBase _data) {
//        DataMap map_ = _data.getMap();
//        Road road_;// = (Road) map_.getPlaces().getVal((short) 0);
//        //AreaApparition area_;
//        Pokemon wild_;
////        area_ = new AreaApparition();
////        area_.setAvgNbSteps((short) 1);
////        area_.setMultFight((byte) 1);
////        wild_ = new Pokemon();
////        wild_.setName("PIKACHU");
////        wild_.setLevel((short) 1);
////        wild_.setAbility("PARATONNERRE");
////        wild_.setGender(Gender.NO_GENDER);
////        area_.setWildPokemon(new List<Pokemon>());
////        area_.setWildPokemonFishing(new List<Pokemon>());
////        area_.getWildPokemon().add(wild_);
////        wild_ = new Pokemon();
////        wild_.setName("PIKACHU");
////        wild_.setLevel((short) 3);
////        wild_.setAbility("PARATONNERRE");
////        wild_.setGender(Gender.NO_GENDER);
////        area_.getWildPokemon().add(wild_);
////        road_.getLevel().getWildPokemonAreas().add(area_);
////        area_ = new AreaApparition();
////        area_.setAvgNbSteps((short) 1);
////        area_.setMultFight((byte) 1);
////        wild_ = new Pokemon();
////        wild_.setName("ARTIKODIN");
////        wild_.setLevel((short) 1);
////        wild_.setAbility("PARATONNERRE");
////        wild_.setGender(Gender.NO_GENDER);
////        area_.setWildPokemon(new List<Pokemon>());
////        area_.setWildPokemonFishing(new List<Pokemon>());
////        area_.getWildPokemon().add(wild_);
////        road_.getLevel().getWildPokemonAreas().add(area_);
////        area_ = new AreaApparition();
////        area_.setAvgNbSteps((short) 2);
////        area_.setMultFight((byte) 1);
////        wild_ = new Pokemon();
////        wild_.setName("ARTIKODIN");
////        wild_.setLevel((short) 1);
////        wild_.setAbility("PARATONNERRE");
////        wild_.setGender(Gender.NO_GENDER);
////        area_.setWildPokemon(new List<Pokemon>());
////        area_.setWildPokemonFishing(new List<Pokemon>());
////        area_.getWildPokemon().add(wild_);
////        road_.getLevel().getWildPokemonAreas().add(area_);
////        area_ = new AreaApparition();
////        area_.setAvgNbSteps((short) 2);
////        area_.setMultFight((byte) 1);
////        wild_ = new Pokemon();
////        wild_.setName("ARTIKODIN");
////        wild_.setLevel((short) 1);
////        wild_.setAbility("PARATONNERRE");
////        wild_.setGender(Gender.NO_GENDER);
////        area_.setWildPokemon(new List<Pokemon>());
////        area_.setWildPokemonFishing(new List<Pokemon>());
////        area_.getWildPokemon().add(wild_);
////        wild_ = new Pokemon();
////        wild_.setName("MEW");
////        wild_.setLevel((short) 1);
////        wild_.setAbility("PARATONNERRE");
////        wild_.setGender(Gender.NO_GENDER);
////        area_.getWildPokemon().add(wild_);
////        road_.getLevel().getWildPokemonAreas().add(area_);
////        area_ = new AreaApparition();
////        area_.setAvgNbSteps((short) 1);
////        area_.setMultFight((byte) 1);
////        area_.setWildPokemon(new List<Pokemon>());
////        area_.setWildPokemonFishing(new List<Pokemon>());
////        wild_ = new Pokemon();
////        wild_.setName("PTITARD");
////        wild_.setLevel((short) 1);
////        wild_.setAbility("ABSORB_EAU");
////        wild_.setGender(Gender.NO_GENDER);
////        area_.getWildPokemon().add(wild_);
////        wild_ = new Pokemon();
////        wild_.setName("PTITARD");
////        wild_.setLevel((short) 1);
////        wild_.setAbility("ABSORB_EAU");
////        wild_.setGender(Gender.NO_GENDER);
////        area_.getWildPokemonFishing().add(wild_);
////        wild_ = new Pokemon();
////        wild_.setName("TETARTE");
////        wild_.setLevel((short) 26);
////        wild_.setAbility("ABSORB_EAU");
////        wild_.setGender(Gender.NO_GENDER);
////        area_.getWildPokemon().add(wild_);
////        wild_ = new Pokemon();
////        wild_.setName("TETARTE");
////        wild_.setLevel((short) 26);
////        wild_.setAbility("ABSORB_EAU");
////        wild_.setGender(Gender.NO_GENDER);
////        area_.getWildPokemonFishing().add(wild_);
////        road_.getLevel().getWildPokemonAreas().add(area_);
////        area_ = new AreaApparition();
////        area_.setAvgNbSteps((short) 1);
////        area_.setMultFight((byte) 1);
////        area_.setWildPokemon(new List<Pokemon>());
////        area_.setWildPokemonFishing(new List<Pokemon>());
////        wild_ = new Pokemon();
////        wild_.setName("TETARTE");
////        wild_.setLevel((short) 26);
////        wild_.setAbility("ABSORB_EAU");
////        wild_.setGender(Gender.NO_GENDER);
////        area_.getWildPokemon().add(wild_);
////        wild_ = new Pokemon();
////        wild_.setName("TETARTE");
////        wild_.setLevel((short) 26);
////        wild_.setAbility("ABSORB_EAU");
////        wild_.setGender(Gender.NO_GENDER);
////        area_.getWildPokemonFishing().add(wild_);
////        road_.getLevel().getWildPokemonAreas().add(area_);
//        road_ = (Road) map_.getPlaces().getVal((short) 2);
//        wild_ = new Pokemon();
//        wild_.setName("MEW");
//        wild_.setLevel((short) 1);
//        wild_.setAbility("PARATONNERRE");
//        wild_.setGender(Gender.NO_GENDER);
//        road_.getLevel().getLegendaryPks().put(newPoint(11, 2), wild_);
//        //map_.getTakenPokemon().add(newCoords(2, 0, 11, 2));
//        wild_ = new Pokemon();
//        wild_.setName("ARTIKODIN");
//        wild_.setLevel((short) 1);
//        wild_.setAbility("PARATONNERRE");
//        wild_.setGender(Gender.NO_GENDER);
//        road_.getLevel().getLegendaryPks().put(newPoint(9, 5), wild_);
//        //map_.getTakenPokemon().add(newCoords(2, 0, 9, 5));
//    }

    static void initCaveAreas(DataBase _data) {
        DataMap map_ = _data.getMap();
        Cave road_ = (Cave) map_.getPlaces().getVal((short) 5);
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
        area_.setWildPokemon(new EqList<WildPk>());
        area_.setWildPokemonFishing(new EqList<WildPk>());
        area_.getWildPokemon().add(wild_);
        wild_ = new WildPk();
        wild_.setName(NINGALE);
        wild_.setLevel((short) 13);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.getWildPokemon().add(wild_);
        level_.getWildPokemonAreas().add(area_);
        area_ = new AreaApparition();
        area_.setWildPokemon(new EqList<WildPk>());
        area_.setWildPokemonFishing(new EqList<WildPk>());
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
        //Road road_ = (Road) map_.getPlaces().getVal((short) 0);
        PokemonTeam foeTeamList_;
        CustList<NameLevelMoves> list_;
        CustList<PokemonTeam> foeTeamsList_;
//        foeTeamsList_ = new List<PokemonTeam>();
//        list_ = new List<NameLevelMoves>();
//        list_.add(new Pair<>(new Pair<>("PIKACHU",3),new StringList("JACKPOT")));
//        list_.add(new Pair<>(new Pair<>("PIKACHU",4),new StringList("JACKPOT")));
//        foeTeamList_ = newTeam(list_, 200);
//        foeTeamsList_.add(foeTeamList_);
//        list_ = new List<NameLevelMoves>();
//        list_.add(new Pair<>(new Pair<>("PIKACHU",13),new StringList("JACKPOT")));
//        list_.add(new Pair<>(new Pair<>("PIKACHU",14),new StringList("JACKPOT")));
//        foeTeamList_ = newTeam(list_, 200);
//        foeTeamsList_.add(foeTeamList_);
        TrainerMultiFights trainer_;
//        trainer_ = newTrainer(foeTeamsList_, 1);
//        road_.addPerson(newCoords(0, 0, 1, 1), trainer_);
        //map_.getBeatTrainer().add(new NbFightCoords(newCoords(0, 0, 1, 1),0));
        //map_.getBeatTrainer().add(new NbFightCoords(newCoords(0, 0, 1, 1),1));
//        City city_ = (City) map_.getPlaces().getVal((short) 1);
//        Gym gym_;
//        GymTrainer gymTrainer_;
//        GymLeader gymLeader_;
//        gym_ = (Gym) city_.getBuildings().getVal(newPoint(5, 1));
//        list_ = new List<NameLevelMoves>();
//        list_.add(new Pair<>(new Pair<>("PIKACHU",3),new StringList("JACKPOT")));
//        list_.add(new Pair<>(new Pair<>("PIKACHU",4),new StringList("JACKPOT")));
//        gymTrainer_ = newGymTrainer(list_, 200, 1);
//        gym_.getLevel().getGymTrainers().put(newPoint(1, 7), gymTrainer_);
//        list_ = new List<NameLevelMoves>();
//        list_.add(new Pair<>(new Pair<>("PIKACHU",3),new StringList("JACKPOT")));
//        list_.add(new Pair<>(new Pair<>("PIKACHU",4),new StringList("JACKPOT")));
//        gymTrainer_ = newGymTrainer(list_, 200, 1);
//        gym_.getLevel().getGymTrainers().put(newPoint(7, 7), gymTrainer_);
//        gym_.getLevel().setGymLeaderCoords(newPoint(4, 1));
//        list_ = new List<NameLevelMoves>();
//        list_.add(new Pair<>(new Pair<>("PIKACHU",5),new StringList("JACKPOT")));
//        list_.add(new Pair<>(new Pair<>("PIKACHU",8),new StringList("JACKPOT")));
//        gymLeader_ = newGymLeader(list_, 500, 1);
//        gym_.getLevel().setGymLeader(gymLeader_);
        //map_.getBeatGymTrainer().put((short) 1, new List<>(newPoint(1, 7),newPoint(7, 7)));
        //map_.getBeatGymLeader().add(newCoords(1, 0, 5, 1, 4, 1));
        CustList<NameLevelMoves> allyList_;
//        allyList_ = new List<NameLevelMoves>();
//        allyList_.add(new Pair<>(new Pair<>("PIKACHU",25),new StringList("JACKPOT","CHARGE")));
//        allyList_.add(new Pair<>(new Pair<>("PIKACHU",28),new StringList("TONNERRE")));
//        list_ = new List<NameLevelMoves>();
//        list_.add(new Pair<>(new Pair<>("PIKACHU",5),new StringList("JACKPOT")));
//        list_.add(new Pair<>(new Pair<>("PIKACHU",8),new StringList("JACKPOT")));
//        road_ = (Road) map_.getPlaces().getVal((short) 2);
        DualFight dual_;
//        dual_ = newDualFight(allyList_, list_, 300);
//        dual_.setPt(newPoint(3, 0));
//        road_.addDualFight(newCoords(2, 0, 2, 0), dual_);
//        //map_.getBeatGymLeader().add(newCoords(2, 0, 2, 0));
//        allyList_ = new List<NameLevelMoves>();
//        allyList_.add(new Pair<>(new Pair<>("PIKACHU",25),new StringList("JACKPOT","CHARGE")));
//        allyList_.add(new Pair<>(new Pair<>("PIKACHU",28),new StringList("TONNERRE")));
//        list_ = new List<NameLevelMoves>();
//        list_.add(new Pair<>(new Pair<>("PIKACHU",5),new StringList("JACKPOT")));
//        list_.add(new Pair<>(new Pair<>("PIKACHU",8),new StringList("JACKPOT")));
//        road_ = (Road) map_.getPlaces().getVal((short) 2);
//        dual_ = newDualFight(allyList_, list_, 300);
//        dual_.setPt(newPoint(5, 0));
//        road_.addDualFight(newCoords(2, 0, 4, 0), dual_);
//        //map_.getBeatGymLeader().add(newCoords(2, 0, 4, 0));
//        foeTeamsList_ = new List<PokemonTeam>();
//        list_ = new List<NameLevelMoves>();
//        list_.add(new Pair<>(new Pair<>("PIKACHU",3),new StringList("JACKPOT")));
//        list_.add(new Pair<>(new Pair<>("PIKACHU",4),new StringList("JACKPOT")));
//        foeTeamList_ = newTeam(list_, 200);
//        foeTeamsList_.add(foeTeamList_);
//        trainer_ = newTrainer(foeTeamsList_, 1);
//        road_.addPerson(newCoords(2, 0, 11, 4), trainer_);
        //map_.getBeatTrainer().add(new NbFightCoords(newCoords(2, 0, 11, 4),0));
//        city_ = (City) map_.getPlaces().getVal((short) 3);
//        gym_ = (Gym) city_.getBuildings().getVal(newPoint(4, 1));
//        list_ = new List<NameLevelMoves>();
//        list_.add(new Pair<>(new Pair<>("PIKACHU",3),new StringList("JACKPOT")));
//        list_.add(new Pair<>(new Pair<>("PIKACHU",4),new StringList("JACKPOT")));
//        gymTrainer_ = newGymTrainer(list_, 200, 1);
//        gym_.getLevel().getGymTrainers().put(newPoint(1, 7), gymTrainer_);
//        list_ = new List<NameLevelMoves>();
//        list_.add(new Pair<>(new Pair<>("PIKACHU",3),new StringList("JACKPOT")));
//        list_.add(new Pair<>(new Pair<>("PIKACHU",4),new StringList("JACKPOT")));
//        gymTrainer_ = newGymTrainer(list_, 200, 1);
//        gym_.getLevel().getGymTrainers().put(newPoint(7, 7), gymTrainer_);
//        gym_.getLevel().setGymLeaderCoords(newPoint(4, 1));
//        list_ = new List<NameLevelMoves>();
//        list_.add(new Pair<>(new Pair<>("PIKACHU",5),new StringList("JACKPOT")));
//        list_.add(new Pair<>(new Pair<>("PIKACHU",8),new StringList("JACKPOT")));
//        gymLeader_ = newGymLeader(list_, 500, 1);
//        gym_.getLevel().setGymLeader(gymLeader_);
        //map_.getBeatGymTrainer().put((short) 3, new List<>(newPoint(1, 7),newPoint(7, 7)));
        //map_.getBeatGymLeader().add(newCoords(3, 0, 4, 1, 4, 1));
        Cave cave_ = (Cave) map_.getPlaces().getVal((short) 5);
        allyList_ = new CustList<NameLevelMoves>();
        allyList_.add(new NameLevelMoves(new NameLevel(PIKACHU,35),new StringList(JACKPOT,CHARGE)));
        allyList_.add(new NameLevelMoves(new NameLevel(PIKACHU,38),new StringList(TONNERRE)));
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,15),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,18),new StringList(JACKPOT)));
        dual_ = newDualFight(allyList_, list_, 300);
        dual_.setNames(new StringList(DUAL_THREE_TR_ONE,DUAL_THREE_TR_TWO));
        dual_.setPt(newPoint(3, 0));
        ((LevelCave)cave_.getLevelsMap().getVal((byte) 0)).getDualFights().put(newPoint(2, 0), dual_);
        //map_.getBeatGymLeader().add(newCoords(5, 0, 2, 0));
        foeTeamsList_ = new CustList<PokemonTeam>();
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,13),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,14),new StringList(JACKPOT)));
        foeTeamList_ = newTeam(list_, 200);
        foeTeamsList_.add(foeTeamList_);
        trainer_ = newTrainer(foeTeamsList_, 1);
        ((LevelCave)cave_.getLevelsMap().getVal((byte) 0)).getCharacters().put(newPoint(1, 5), trainer_);
        //map_.getBeatTrainer().add(new NbFightCoords(newCoords(5, 0, 1, 5),0));
        foeTeamsList_ = new CustList<PokemonTeam>();
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,13),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,14),new StringList(JACKPOT)));
        foeTeamList_ = newTeam(list_, 200);
        foeTeamsList_.add(foeTeamList_);
        trainer_ = newTrainer(foeTeamsList_, 1);
        ((LevelCave)cave_.getLevelsMap().getVal((byte) 1)).getCharacters().put(newPoint(5, 1), trainer_);
        //map_.getBeatTrainer().add(new NbFightCoords(newCoords(5, 1, 5, 1),0));
    }

    static void initOtherCharactersFirstRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        Road road_ = (Road) map_.getPlaces().getVal((short) 0);
        road_.addPerson(newCoords(0, 0, 0, 1), newDealerObject(new StringList(HYPER_BALL), new Numbers<Short>((short)5)));
        //map_.getTakenObjects().add(newCoords(0, 0, 0, 1));
//        City city_ = (City) map_.getPlaces().getVal((short) 1);
//        PokemonCenter pk_;
//        pk_ = (PokemonCenter) city_.getBuildings().getVal(newPoint(1, 1));
//        pk_.getLevel().getGerants().put(newPoint(0, 4), newGerantPokemon(GeranceType.HEAL));
//        Seller seller_;
//        seller_ = new Seller();
//        seller_.setItems(new StringList("HYPER_BALL","PT_DE_MIRE"));
//        seller_.setTm(new List<Short>());
//        seller_.setSell(SellType.ITEM);
//        pk_.getLevel().getGerants().put(newPoint(8, 4), seller_);
//        seller_ = new Seller();
//        seller_.setItems(new StringList("PIERRE_EAU","ROCHE_ROYALE","PIERRE_LUNE","PIERRE_SOLEIL","PIERRE_GLACE"));
//        seller_.setTm(new List<>((short)1,(short)2));
//        seller_.setTm(new List<>((short)2));
//        seller_.setSell(SellType.TM);
//        pk_.getLevel().getGerants().put(newPoint(8, 5), seller_);
//        seller_ = new Seller();
//        seller_.setItems(new StringList());
//        seller_.setTm(new List<Short>());
//        seller_.setSell(SellType.MOVE);
//        pk_.getLevel().getGerants().put(newPoint(8, 6), seller_);
//        city_ = (City) map_.getPlaces().getVal((short) 3);
//        pk_ = (PokemonCenter) city_.getBuildings().getVal(newPoint(2, 1));
//        pk_.getLevel().getGerants().put(newPoint(0, 4), newGerantPokemon(GeranceType.FOSSILE));
//        pk_.getLevel().getGerants().put(newPoint(8, 4), newGerantPokemon(GeranceType.HOST));
        //map_.getHostPokemons().add(newCoords(3, 0, 2, 1, 8, 4));
    }

//    static void initObjects(DataBase _data) {
//        DataMap map_ = _data.getMap();
//        Road road_ = (Road) map_.getPlaces().getVal((short) 2);
//        road_.addObject(newCoords(2, 0, 6, 5), "HYPER_BALL");
//        //map_.getTakenObjects().add(newCoords(2, 0, 6, 5));
//        road_.addTm(newCoords(2, 0, 7, 5), (short) 2);
//        //map_.getTakenObjects().add(newCoords(2, 0, 7, 5));
//        road_.addHm(newCoords(2, 0, 8, 5), (short) 1);
//        //map_.getTakenObjects().add(newCoords(2, 0, 8, 5));
//    }

//    static void joinPlaces(DataBase _data) {
//        DataMap map_ = _data.getMap();
//        map_.join((short)0,(short) 1, newPoint(0,0), newPoint(0,5), Direction.UP);
//        map_.join((short)0,(short) 2, newPoint(5,0), newPoint(0,0), Direction.RIGHT);
//        map_.join((short)2,(short) 3, newPoint(0,0), newPoint(0,5), Direction.UP);
//        map_.join((short)4,(short) 2, newPoint(0,0), newPoint(0,5), Direction.UP);
//        // right of cave
//        map_.joinCavePlace(newCoords(5, 0, 7, 2), newCoords(4, 0, 0, 2), "file", "file");
//        map_.joinLevelCave((short) 5, newLevelPoint(0,7,5), newLevelPoint(1,7,5), "file", "file");
//        map_.joinLevelCave((short) 5, newLevelPoint(0,2,3), newLevelPoint(1,2,3), "file", "file");
//        //    map_.addLeague("file", newCoords(4, 0, 4, 5));
////        map_.addLeague("file", newCoords(4, 0, 5, 4));
////        League league_ =(League) map_.getPlaces().getVal((short) 6);
////        league_.setName("ligue");
////        map_.addLevelLeague((short) 6);
//    }

    static void initLeague(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addLeague(FILE, newCoords(4, 0, 5, 4));
        League league_ =(League) map_.getPlaces().getVal((short) 6);
        league_.setName(LIGUE);
        map_.addLevelLeague((short) 6);
    }

    static void initBlockLeague(DataBase _data) {
        DataMap map_ = _data.getMap();
        League road_ = (League) map_.getPlaces().getVal((short) 6);
        LevelLeague level_;
        Block block_;
        level_ = (LevelLeague) road_.getLevelsMap().getVal((byte) 0);
        block_ = newBuildingBlock(9, 9);
        level_.getBlocks().put(newPoint(0,0), block_);
        level_ = (LevelLeague) road_.getLevelsMap().getVal((byte) 1);
        block_ = newBuildingBlock(9, 9);
        level_.getBlocks().put(newPoint(0,0), block_);
        road_.setBegin(newPoint(4,8));
        level_ = (LevelLeague) road_.getLevelsMap().getVal((byte) 0);
        level_.setAccessPoint(newPoint(4, 0));
        level_.setNextLevelTarget(newPoint(4, 8));
        level_ = (LevelLeague) road_.getLevelsMap().getVal((byte) 1);
        level_.setAccessPoint(newPoint(4, 0));
    }

    static void initLeagueTrainers(DataBase _data) {
        DataMap map_ = _data.getMap();
        League league_ = (League) map_.getPlaces().getVal((short) 6);
        CustList<NameLevelMoves> list_;
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,35),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,38),new StringList(JACKPOT)));
        league_.getRooms().get(0).setTrainerCoords(newPoint(4, 4));
        league_.getRooms().get(0).setTrainer(newTrainerLeague(list_, 2000, 1));
        league_.getRooms().get(0).getTrainer().setName(LEAGUE_TR_ONE);
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(TARTARD,35),new StringList(PISTOLET_A_O)));
        list_.add(new NameLevelMoves(new NameLevel(TARTARD,38),new StringList(PISTOLET_A_O)));
        league_.getRooms().get(1).setTrainerCoords(newPoint(4, 4));
        league_.getRooms().get(1).setTrainer(newTrainerLeague(list_, 2000, 1));
        league_.getRooms().get(1).getTrainer().setName(LEAGUE_TR_TWO);
        //map_.getBeatGymLeader().add(newCoords(6, 0, 4, 8));
        map_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        map_.getAccessCondition().put(newCoords(4, 0, 0, 4), new EqList<Coords>(newCoords(1, 0, 5, 1, 4, 1),newCoords(3, 0, 4, 1, 4, 1)));
        map_.getAccessCondition().put(newCoords(4, 0, 1, 4), new EqList<Coords>(newCoords(1, 0, 5, 1, 4, 1),newCoords(3, 0, 4, 1, 4, 1)));
        map_.getAccessCondition().put(newCoords(4, 0, 2, 4), new EqList<Coords>(newCoords(1, 0, 5, 1, 4, 1),newCoords(3, 0, 4, 1, 4, 1)));
        map_.getAccessCondition().put(newCoords(4, 0, 3, 4), new EqList<Coords>(newCoords(1, 0, 5, 1, 4, 1),newCoords(3, 0, 4, 1, 4, 1)));
        map_.getAccessCondition().put(newCoords(4, 0, 4, 4), new EqList<Coords>(newCoords(1, 0, 5, 1, 4, 1),newCoords(3, 0, 4, 1, 4, 1)));
        map_.getAccessCondition().put(newCoords(4, 0, 5, 4), new EqList<Coords>(newCoords(1, 0, 5, 1, 4, 1),newCoords(3, 0, 4, 1, 4, 1)));
    }

    static void initBlockThirdCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addCity(CITY_7);
        City city_ = (City) map_.getPlaces().getVal((short) 7);
        Block block_;
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(0,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(2,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(0,2), block_);
        block_ = newBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(2,2), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(4,2), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(0,4), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(2,4), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(4,4), block_);
    }

    static void initBuildingsThirdCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlaces().getVal((short) 7);
        PokemonCenter pkCenter_;
        pkCenter_ = new PokemonCenter();
        pkCenter_.setLevel(new LevelIndoorPokemonCenter());
        pkCenter_.setExitCity(newPoint(4,8));
        pkCenter_.getLevel().setBlocks(new ObjectMap<Point,Block>());
        pkCenter_.getLevel().getBlocks().put(newPoint(0,0), newBuildingBlock(9, 9));
        pkCenter_.getLevel().setStorageCoords(newPoint(4, 0));
        pkCenter_.getLevel().setGerants(new ObjectMap<Point,Person>());
        pkCenter_.getLevel().getGerants().put(newPoint(0, 4), newGerantPokemon(GeranceType.FOSSILE));
        city_.getBuildings().put(newPoint(3,3), pkCenter_);
        //5, 1, 4, 0
    }

    static void initBlockFourthCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addCity(CITY_8);
        City city_ = (City) map_.getPlaces().getVal((short) 8);
        Block block_;
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(0,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(2,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(0,2), block_);
        block_ = newBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(2,2), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(4,2), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(0,4), block_);
        block_ = newWaterBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(2,4), block_);
        block_ = newRoadBlock(2, 2);
        city_.getLevel().getBlocks().put(newPoint(4,4), block_);
    }

    static void initBuildingsFourthCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlaces().getVal((short) 8);
        PokemonCenter pkCenter_;
        pkCenter_ = new PokemonCenter();
        pkCenter_.setLevel(new LevelIndoorPokemonCenter());
        pkCenter_.setExitCity(newPoint(4,8));
        pkCenter_.getLevel().setBlocks(new ObjectMap<Point,Block>());
        pkCenter_.getLevel().getBlocks().put(newPoint(0,0), newBuildingBlock(9, 9));
        pkCenter_.getLevel().setStorageCoords(newPoint(4, 0));
        pkCenter_.getLevel().setGerants(new ObjectMap<Point,Person>());
        pkCenter_.getLevel().getGerants().put(newPoint(0, 4), newGerantPokemon(GeranceType.HOST));
        city_.getBuildings().put(newPoint(3,3), pkCenter_);
        //5, 1, 4, 0
    }

    static void initBlockFourthRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addRoad();
        Road road_ = (Road) map_.getPlaces().getVal((short) 9);
        road_.setName(MOTORWAY_9);
        Block block_;
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(0,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(2,0), block_);
        block_ = newDesertBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(4,0), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(0,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(2,2), block_);
        block_ = newDesertBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(4,2), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(0,4), block_);
        block_ = newRoadBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(2,4), block_);
        block_ = newDesertBlock(2, 2);
        road_.getLevel().getBlocks().put(newPoint(4,4), block_);
    }

    static void initTrainersFourthRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        Road road_ = (Road) map_.getPlaces().getVal((short) 9);
        PokemonTeam foeTeamList_;
        CustList<NameLevelMoves> list_;
        CustList<PokemonTeam> foeTeamsList_;
        foeTeamsList_ = new CustList<PokemonTeam>();
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PTITARD,3),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PTITARD,4),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,3),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,4),new StringList(JACKPOT)));
        foeTeamList_ = newTeam(list_, 200);
        foeTeamsList_.add(foeTeamList_);
        list_ = new CustList<NameLevelMoves>();
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,13),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PIKACHU,14),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PTITARD,13),new StringList(JACKPOT)));
        list_.add(new NameLevelMoves(new NameLevel(PTITARD,14),new StringList(JACKPOT)));
        foeTeamList_ = newTeam(list_, 200);
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
        map_.joinCavePlace(newCoords(5, 0, 7, 2), newCoords(4, 0, 0, 2), FILE, FILE);
        map_.joinLevelCave((short) 5, newLevelPoint(0,7,5), newLevelPoint(1,7,5), FILE, FILE);
        map_.joinLevelCave((short) 5, newLevelPoint(0,2,3), newLevelPoint(1,2,3), FILE, FILE);

//        map_.joinCavePlace(newCoords(5, 1, 4, 0), newCoords(7, 0, 2, 0), "file", "file");
//        map_.joinCavePlace(newCoords(5, 1, 4, 0), newCoords(7, 0, 2, 0), "file", "file");
        map_.joinCavePlace(newCoords(5, 1, 4, 0), newCoords(8, 0, 0, 0), FILE, FILE);
//        map_.getAccessCondition().put(newCoords(5, 1, 4, 0), new List<Coords>(newCoords(6, 0, 4, 8)));
        map_.join((short)7,(short) 4, newPoint(0,0), newPoint(0,5), Direction.UP);
        //map_.join(_pl1, _pl2, _p1, _p2, _dir1);
        map_.getAccessCondition().put(newCoords(5, 1, 4, 0), new EqList<Coords>(newCoords(6, 0, 4, 8)));
        map_.getAccessCondition().put(newCoords(7, 0, 0, 0), new EqList<Coords>(newCoords(6, 0, 4, 8)));
        map_.getAccessCondition().put(newCoords(7, 0, 1, 0), new EqList<Coords>(newCoords(6, 0, 4, 8)));
        map_.getAccessCondition().put(newCoords(7, 0, 2, 0), new EqList<Coords>(newCoords(6, 0, 4, 8)));
        map_.getAccessCondition().put(newCoords(7, 0, 3, 0), new EqList<Coords>(newCoords(6, 0, 4, 8)));
        map_.getAccessCondition().put(newCoords(7, 0, 4, 0), new EqList<Coords>(newCoords(6, 0, 4, 8)));
        map_.getAccessCondition().put(newCoords(7, 0, 5, 0), new EqList<Coords>(newCoords(6, 0, 4, 8)));
        //map_.getAccessCondition().put(newCoords(7, 0, 6, 0), new List<Coords>(newCoords(6, 0, 4, 8)));
        //map_.getAccessCondition().put(newCoords(7, 0, 7, 0), new List<Coords>(newCoords(6, 0, 4, 8)));
        map_.join((short) 9, (short)7, newPoint(0,0), newPoint(0,5), Direction.UP);
    }

    static void initImages(DataBase _data) {
        DataMap map_ = _data.getMap();
        for (Place p: map_.getPlaces().values()) {
            if (p instanceof City) {
                for (Building b: ((City)p).getBuildings().values()) {
                    for (Block c: b.getLevel().getBlocks().values()) {
                        c.setTileFileName(BUILDING);
                    }
                }
            }
            for (Level l: p.getLevelsMap().values()) {
                for (Block b: l.getBlocks().values()) {
                    if (b.getType() == EnvironmentType.ROAD) {
                        //grey
                        b.setTileFileName(ROAD);
                    } else if (b.getType() == EnvironmentType.WATER) {
                        //blue
                        b.setTileFileName(WATER);
                    } else if (b.getType() == EnvironmentType.ROCK) {
                        //brown
                        b.setTileFileName(ROCK);
                    } else if (b.getType() == EnvironmentType.DESERT) {
                        //yellow
                        b.setTileFileName(DESERT);
                    } else if (b.getType() == EnvironmentType.BUILDING) {
                        //orange
                        b.setTileFileName(BUILDING);
                    } else if (b.getType() == EnvironmentType.NOTHING) {
                        //black
                        b.setTileFileName(NOTHING);
                    } else {
                        //green
                        b.setTileFileName(DEFAULT);
                        break;
                    }
                }
            }
        }
        for (Place p: map_.getPlaces().values()) {
            for (Level l: p.getLevelsMap().values()) {
                if (l instanceof LevelWithWildPokemon) {
                    for (CharacterInRoadCave c: ((LevelWithWildPokemon)l).getCharacters().values()) {
                        if (c instanceof Trainer) {
                            ((Trainer)c).setImageMiniFileName(TRAINER);
                            ((Trainer)c).setImageMaxiFileName(TRAINER);
                        } else if (c instanceof Person) {
                            ((Person)c).setImageMiniFileName(PERSON);
                        }
                    }
                    for (DualFight d: ((LevelWithWildPokemon)l).getDualFights().values()) {
                        d.getFoeTrainer().setImageMiniFileName(TRAINER_ONE);
                        d.getFoeTrainer().setImageMaxiFileName(TRAINER_ONE);
                        d.getFoeTrainer().setImageMiniSecondTrainerFileName(TRAINER_TWO);
                    }
                }
                if (l instanceof LevelLeague) {
                    ((LevelLeague)l).getTrainer().setImageMiniFileName(TRAINER);
                    ((LevelLeague)l).getTrainer().setImageMaxiFileName(TRAINER);
                    ((LevelLeague)l).setFileName(LINK);
                }
                if (l instanceof LevelCave) {
                    for (Link m: ((LevelCave)l).getLinksOtherLevels().values()) {
                        m.setFileName(LINK);
                    }
                }
            }
            if (p instanceof League) {
                ((League)p).setFileName(LINK);
            }
            if (p instanceof City) {
                for (Building b: ((City)p).getBuildings().values()) {
                    b.setImageFileName(LINK);
                    if (b.getLevel() instanceof LevelIndoorGym) {
                        for (GymTrainer g: ((LevelIndoorGym)b.getLevel()).getGymTrainers().values()) {
                            g.setImageMiniFileName(TRAINER);
                            g.setImageMaxiFileName(TRAINER);
                        }
                        ((LevelIndoorGym)b.getLevel()).getGymLeader().setImageMiniFileName(TRAINER);
                        ((LevelIndoorGym)b.getLevel()).getGymLeader().setImageMaxiFileName(TRAINER);
                    }
                    if (b.getLevel() instanceof LevelIndoorPokemonCenter) {
                        for (Person g: ((LevelIndoorPokemonCenter)b.getLevel()).getGerants().values()) {
                            g.setImageMiniFileName(GERANT);
                        }
                    }
                }
            }
            if (p instanceof Cave) {
                for (Link l: ((Cave)p).getLinksWithOtherPlaces().values()) {
                    l.setFileName(LINK);
                }
            }
            if (p instanceof InitializedPlace) {
                for (Link l: ((InitializedPlace)p).getLinksWithCaves().values()) {
                    l.setFileName(LINK);
                }
            }
        }
        for (String p: _data.getPokedex().getKeys()) {
            _data.getMiniPk().put(p, getImageByString("2;-15;-15;-15;-15"));
        }
        for (String p: _data.getItems().getKeys()) {
            _data.getMiniItems().put(p, getImageByString("2;-16;-16;-16;-16"));
        }
        StringList building_ = new StringList("18");
        for (int i = 0; i < 324; i++) {
            building_.add("-32985");
        }
        _data.addImage(BUILDING, getImageByString(building_.join(";")));
        StringList default_ = new StringList("4");
        for (int i = 0; i < 16; i++) {
            default_.add("-1");
        }
        _data.addImage(DAFAULT, getImageByString(default_.join(";")));
        StringList desert_ = new StringList("4");
        for (int i = 0; i < 16; i++) {
            desert_.add("-3584");
        }
        _data.addImage(DESERT, getImageByString(desert_.join(";")));
        StringList grass_ = new StringList("4");
        for (int i = 0; i < 16; i++) {
            grass_.add("-14503604");
        }
        _data.addImage(GRASS, getImageByString(grass_.join(";")));
        StringList nothing_ = new StringList("4");
        for (int i = 0; i < 16; i++) {
            nothing_.add("-16777216");
        }
        _data.addImage(NOTHING, getImageByString(nothing_.join(";")));
        StringList road_ = new StringList("4");
        for (int i = 0; i < 16; i++) {
            road_.add("-7369361");
        }
        _data.addImage(ROAD, getImageByString(road_.join(";")));
        StringList rock_ = new StringList("4");
        for (int i = 0; i < 16; i++) {
            rock_.add("-4621737");
        }
        _data.addImage(ROCK, getImageByString(rock_.join(";")));
        StringList snow_ = new StringList("4");
        for (int i = 0; i < 16; i++) {
            snow_.add("-1");
        }
        _data.addImage(SNOW, getImageByString(snow_.join(";")));
        StringList water_ = new StringList("4");
        for (int i = 0; i < 16; i++) {
            water_.add("-16776961");
        }
        _data.addImage(WATER, getImageByString(water_.join(";")));
//        _data.getPeople().put(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"trainer", "2;-18000;-18000;-18000;-18000");
//        _data.getPeople().put(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"person", "2;-1800;-1800;-1800;-1800");
//        _data.getPeople().put(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"trainer_one", "2;-19000;-19000;-19000;-19000");
//        _data.getPeople().put(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"trainer_two", "2;-19008;-19008;-19008;-19008");
//        _data.getPeople().put(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"ally", "2;-19508;-19508;-19508;-19508");
//        _data.getPeople().put(DataBase.PEOPLE_FOLDER+DataBase.SEPARATOR_FILES+"gerant", "2;-20508;-20508;-20508;-20508");
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

        StringList herosBottom_ = new StringList("-2","-2","-2","-2","-2","-2");
        int iHeros_ = 0;
        int iHerosBis_ = 0;
        for (EnvironmentType e: EnvironmentType.values()) {
            if (e == EnvironmentType.ROCK) {
                continue;
            }
            for (Direction d: Direction.values()) {
                for (Sex s: Sex.values()) {
                    ImageHeroKey key_;
                    key_ = new ImageHeroKey(e, d, s);
                    StringList herosTop_ = new StringList("6");
                    for (int i = 0; i < 6; i++) {
                        herosTop_.add(String.valueOf(iHeros_));
                    }
                    herosTop_.addAllElts(herosBottom_);
                    _data.getOverWorldHeros().put(key_, getImageByString(herosTop_.join(";")));
                    iHeros_++;
                }
            }
            for (Sex s: Sex.values()) {
                ImageHeroKey key_;
                key_ = new ImageHeroKey(e, s);
                StringList herosTop_ = new StringList("6");
                for (int i = 0; i < 6; i++) {
                    herosTop_.add(String.valueOf(iHerosBis_));
                    iHerosBis_++;
                }
                herosTop_.addAllElts(herosBottom_);
                _data.getBackHeros().put(key_, getImageByString(herosTop_.join(";")));
                iHerosBis_++;
            }
        }
        _data.addLink(LINK, getImageByString("2;-255;-255;-255;-255"));
        _data.setImageTmHm(getImageByString("2;-800;-800;-800;-800"));
        _data.setStorage(getImageByString("2;-3;-3;-3;-3"));
        _data.getMiniMap().put(MINI, getImageByString("2;118;218;112;200"));
        _data.getMiniMap().put(MINI1, getImageByString("2;218;118;112;200"));
        _data.getMiniMap().put(MINI2, getImageByString("2;218;112;118;200"));
        _data.getMiniMap().put(MINI3, getImageByString("2;218;112;200;118"));
        _data.getMiniMap().put(MINI4, getImageByString("2;218;200;112;118"));
        _data.getMiniMap().put(MINI5, getImageByString("2;200;218;112;118"));
        _data.getMiniMap().put(MINI6, getImageByString("2;200;218;212;118"));
    }

    static void initMiniMap(DataBase _data) {
        DataMap map_ = _data.getMap();
        TileMiniMap tile_;
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        tile_ = new TileMiniMap();
        tile_.setFile(MINI);
        tile_.setPlace((short) 0);
        map_.getMiniMap().put(new MiniMapCoords((short) 0,(short) 0), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace((short) 1);
        map_.getMiniMap().put(new MiniMapCoords((short) 0,(short) 1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI);
        tile_.setPlace((short) 2);
        map_.getMiniMap().put(new MiniMapCoords((short) 0,(short) 2), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace((short) 3);
        map_.getMiniMap().put(new MiniMapCoords((short) 0,(short) 3), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI);
        tile_.setPlace((short) 4);
        map_.getMiniMap().put(new MiniMapCoords((short) 0,(short) 4), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI2);
        tile_.setPlace((short) 5);
        map_.getMiniMap().put(new MiniMapCoords((short) 0,(short) 5), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI3);
        tile_.setPlace((short) 6);
        map_.getMiniMap().put(new MiniMapCoords((short) 0,(short) 6), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace((short) 7);
        map_.getMiniMap().put(new MiniMapCoords((short) 0,(short) 7), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace((short) 8);
        map_.getMiniMap().put(new MiniMapCoords((short) 0,(short) 8), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI4);
        tile_.setPlace((short) -1);
        map_.getMiniMap().put(new MiniMapCoords((short) 0,(short) 9), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI6);
        tile_.setPlace((short) 9);
        map_.getMiniMap().put(new MiniMapCoords((short) 0,(short) 10), tile_);
        map_.setUnlockedCity(MINI5);
    }

    private static GymLeader newGymLeader(
            CustList<NameLevelMoves> _pksLevels,
            int _reward, int _mult) {
        GymLeader gymLeader_ = new GymLeader();
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        for (NameLevelMoves i: _pksLevels) {
            PkTrainer pk_ = new PkTrainer();
            pk_.setName(i.getNameLevel().getName());
            pk_.setLevel(i.getNameLevel().getLevel());
            pk_.setAbility(ATTENTION);
            pk_.setItem(NULL_REF);
            pk_.setMoves(i.getMoves());
            team_.add(pk_);
        }
        gymLeader_.setTeam(team_);
        gymLeader_.setReward((short) _reward);
        gymLeader_.setMultiplicityFight((byte) _mult);
        gymLeader_.setName(NULL_REF);
        gymLeader_.setTm((short) 2);
        gymLeader_.setImageMaxiFileName(NULL_REF);
        gymLeader_.setImageMiniFileName(NULL_REF);
        return gymLeader_;
    }

    private static GymTrainer newGymTrainer(
            CustList<NameLevelMoves> _pksLevels,
            int _reward, int _mult) {
        GymTrainer gymTrainer_ = new GymTrainer();
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        for (NameLevelMoves i: _pksLevels) {
            PkTrainer pk_ = new PkTrainer();
            pk_.setName(i.getNameLevel().getName());
            pk_.setLevel(i.getNameLevel().getLevel());
            pk_.setAbility(ATTENTION);
            pk_.setItem(NULL_REF);
            pk_.setMoves(i.getMoves());
            team_.add(pk_);
        }
        gymTrainer_.setTeam(team_);
        gymTrainer_.setReward((short) _reward);
        gymTrainer_.setMultiplicityFight((byte) _mult);
        gymTrainer_.setImageMaxiFileName(NULL_REF);
        gymTrainer_.setImageMiniFileName(NULL_REF);
        return gymTrainer_;
    }

    private static TrainerLeague newTrainerLeague(
            CustList<NameLevelMoves> _pksLevels,
            int _reward, int _mult) {
        TrainerLeague trainerLeague_ = new TrainerLeague();
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        for (NameLevelMoves i: _pksLevels) {
            PkTrainer pk_ = new PkTrainer();
            pk_.setName(i.getNameLevel().getName());
            pk_.setLevel(i.getNameLevel().getLevel());
            pk_.setAbility(ATTENTION);
            pk_.setItem(NULL_REF);
            pk_.setMoves(i.getMoves());
            team_.add(pk_);
        }
        trainerLeague_.setTeam(team_);
        trainerLeague_.setReward((short) _reward);
        trainerLeague_.setMultiplicityFight((byte) _mult);
        trainerLeague_.setImageMaxiFileName(NULL_REF);
        trainerLeague_.setImageMiniFileName(NULL_REF);
        trainerLeague_.setName(NULL_REF);
        return trainerLeague_;
    }

    private static DualFight newDualFight(
            CustList<NameLevelMoves> _pksAllyLevels,
            CustList<NameLevelMoves> _pksLevels,
            int _reward) {
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> team_;
        team_ = new CustList<PkTrainer>();
        for (NameLevelMoves i: _pksAllyLevels) {
            PkTrainer pk_ = new PkTrainer();
            pk_.setName(i.getNameLevel().getName());
            pk_.setLevel(i.getNameLevel().getLevel());
            pk_.setAbility(ATTENTION);
            pk_.setItem(NULL_REF);
            pk_.setMoves(i.getMoves());
            team_.add(pk_);
        }
        ally_.setTeam(team_);
        TempTrainer trainer_ = new TempTrainer();
        team_ = new CustList<PkTrainer>();
        for (NameLevelMoves i: _pksLevels) {
            PkTrainer pk_ = new PkTrainer();
            pk_.setName(i.getNameLevel().getName());
            pk_.setLevel(i.getNameLevel().getLevel());
            pk_.setAbility(ATTENTION);
            pk_.setItem(NULL_REF);
            pk_.setMoves(i.getMoves());
            team_.add(pk_);
        }
        trainer_.setTeam(team_);
        trainer_.setReward((short) _reward);
        trainer_.setImageMaxiFileName(NULL_REF);
        trainer_.setImageMiniFileName(NULL_REF);
        trainer_.setImageMiniSecondTrainerFileName(NULL_REF);
        dual_.setAlly(ally_);
        dual_.setFoeTrainer(trainer_);
        dual_.setNames(new StringList());
        return dual_;
    }

    private static PokemonTeam newTeam(
            CustList<NameLevelMoves> _pksLevels,
            int _reward) {
        PokemonTeam teamReward_ = new PokemonTeam();
        CustList<PkTrainer> team_;
        team_ = new CustList<PkTrainer>();
        for (NameLevelMoves i: _pksLevels) {
            PkTrainer pk_ = new PkTrainer();
            pk_.setName(i.getNameLevel().getName());
            pk_.setLevel(i.getNameLevel().getLevel());
            pk_.setAbility(ATTENTION);
            pk_.setItem(NULL_REF);
            pk_.setMoves(i.getMoves());
            team_.add(pk_);
        }
        teamReward_.setTeam(team_);
        teamReward_.setReward((short) _reward);
        return teamReward_;
    }

    private static TrainerMultiFights newTrainer(
            CustList<PokemonTeam> _teams, int _mult) {
        TrainerMultiFights trainer_ = new TrainerMultiFights();
        trainer_.setTeamsRewards(_teams);
        trainer_.setMultiplicityFight((byte) _mult);
        trainer_.setImageMaxiFileName(NULL_REF);
        trainer_.setImageMiniFileName(NULL_REF);
        return trainer_;
    }

    private static GerantPokemon newGerantPokemon(GeranceType _gerance) {
        GerantPokemon gerant_ = new GerantPokemon();
        gerant_.setGerance(_gerance);
        return gerant_;
    }

    private static DealerItem newDealerObject(StringList _obj, Numbers<Short> _tm) {
        DealerItem dealer_ = new DealerItem();
        dealer_.setItems(new StringList(_obj));
        dealer_.setTechnicalMoves(_tm);
        return dealer_;
    }

    private static Block newRoadBlock(int _h, int _w, int... _index) {
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        if (_index.length > 0) {
            block_.setIndexApparition((short) _index[0]);
        }
        block_.setType(EnvironmentType.ROAD);
        return block_;
    }

    private static Block newWaterBlock(int _h, int _w, int... _index) {
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        if (_index.length > 0) {
            block_.setIndexApparition((short) _index[0]);
        }
        block_.setType(EnvironmentType.WATER);
        return block_;
    }

    private static Block newRockBlock(int _h, int _w, int... _index) {
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        if (_index.length > 0) {
            block_.setIndexApparition((short) _index[0]);
        }
        block_.setType(EnvironmentType.ROCK);
        return block_;
    }

    private static Block newDesertBlock(int _h, int _w, int... _index) {
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        if (_index.length > 0) {
            block_.setIndexApparition((short) _index[0]);
        }
        block_.setType(EnvironmentType.DESERT);
        return block_;
    }

    private static Block newBuildingBlock(int _h, int _w) {
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        block_.setType(EnvironmentType.BUILDING);
        return block_;
    }

    private static Block newBlock(int _h, int _w) {
        Block block_;
        block_ = new Block();
        block_.setHeight((short) _h);
        block_.setWidth((short) _w);
        block_.setType(EnvironmentType.NOTHING);
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

    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }

    private static int[][] getImageByString(String _string) {
        Image i_ = new Image(_string);
        Numbers<Integer> pixels_ = i_.getPixels();
        int width_ = i_.getWidth();
        int height_ = i_.getHeight();
        int[][] img_ = new int[height_][width_];
        for (int i = 0; i < height_; i++) {
            for (int j = 0; j < width_; j++) {
                img_[i][j] = pixels_.get(j + width_ * i);
            }
        }
        return img_;
    }
}
