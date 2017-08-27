package aiki.game;
import static code.util.opers.EquallableUtil.assertEq;

import org.junit.BeforeClass;
import org.junit.Test;

import code.util.CustList;
import code.util.EqList;
import code.util.NumberMap;
import code.util.ObjectMap;
import aiki.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.DataMap;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymTrainer;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Block;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelOutdoor;
import aiki.map.levels.LevelRoad;
import aiki.map.levels.Link;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.PlaceInterConnect;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;

@SuppressWarnings("static-method")
public class GameUnlockPlacesTest extends InitializationDataBase {

    private static final String L = "L";
    private static final String VOIE = "voie";
    private static final String ROAD_6 = "R 6";
    private static final String ROAD_5 = "R 5";
    private static final String ROAD_4 = "R 4";
    private static final String ROAD_3 = "R 3";
    private static final String ROAD_2 = "R 2";
    private static final String ROAD_1 = "R 1";
    private static final String C_5 = "C 5";
    private static final String C_4 = "C 4";
    private static final String C_3 = "C 3";
    private static final String C_2 = "C 2";
    private static final String C_1 = "C 1";
    private static DataBase _dataCore_;

    @BeforeClass
    public static void initLocalDataBase() {
        _dataCore_ = InitializationDataBase.coreDataBase();
        _dataCore_.sortEndRound();
        _dataCore_.completeVariables();
        initTableTypes(_dataCore_);
        initConstants(_dataCore_);
        initRandomLaws(_dataCore_);
        initExpPoints(_dataCore_);
        initTmHm(_dataCore_);
        _dataCore_.initTypesByTable();
        initTranslations(_dataCore_);
        initCondition();
    }

    private static void initCondition() {
        City cityOne_ = city();
        cityOne_.setName(C_1);
        City cityTwo_ = city();
        cityTwo_.setName(C_2);
        City cityThree_ = city();
        cityThree_.setName(C_3);
        City cityFour_ = city();
        cityFour_.setName(C_4);
        City cityFive_ = city();
        cityFive_.setName(C_5);
        Road roadOne_ = vroad();
        roadOne_.setName(ROAD_1);
        Road roadTwo_ = hroad();
        roadTwo_.setName(ROAD_2);
        Road roadThree_ = vroad();
        roadThree_.setName(ROAD_3);
        Road roadFour_ = hroad();
        roadFour_.setName(ROAD_4);
        Road roadFive_ = hroad();
        roadFive_.setName(ROAD_5);
        Road roadSix_ = hroad();
        roadSix_.setName(ROAD_6);
        DataMap dataMap_ = _dataCore_.getMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        dataMap_.setPlaces(new NumberMap<Short,Place>());
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),cityOne_);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),roadOne_);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),cityTwo_);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),roadTwo_);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),cityThree_);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),roadThree_);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),cityFour_);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),roadFour_);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),roadFive_);
        Coords coordsAccessLeague_ = new Coords();
        coordsAccessLeague_.setNumberPlace((short) 8);
        coordsAccessLeague_.setLevel(new LevelPoint());
        coordsAccessLeague_.getLevel().setLevelIndex((byte) 0);
        coordsAccessLeague_.getLevel().setPoint(new Point((short)5,(short)1));
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),league(coordsAccessLeague_));
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),roadSix_);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),cityFive_);
        dataMap_.join((short)0, (short)1, new Point((short)4,(short)0), new Point((short)1,(short)5), Direction.UP);
        dataMap_.join((short)1, (short)2, new Point((short)1,(short)0), new Point((short)4,(short)8), Direction.UP);
        dataMap_.join((short)2, (short)3, new Point((short)8,(short)4), new Point((short)0,(short)1), Direction.RIGHT);
        dataMap_.join((short)3, (short)4, new Point((short)5,(short)1), new Point((short)0,(short)4), Direction.RIGHT);
        dataMap_.join((short)4, (short)5, new Point((short)4,(short)8), new Point((short)1,(short)0), Direction.DOWN);
        dataMap_.join((short)5, (short)6, new Point((short)1,(short)5), new Point((short)4,(short)0), Direction.DOWN);
        dataMap_.join((short)6, (short)7, new Point((short)0,(short)4), new Point((short)5,(short)1), Direction.LEFT);
        dataMap_.join((short)7, (short)0, new Point((short)0,(short)1), new Point((short)8,(short)4), Direction.LEFT);
        dataMap_.join((short)8, (short)5, new Point((short)0,(short)1), new Point((short)2,(short)4), Direction.LEFT);
        dataMap_.join((short)10, (short)0, new Point((short)5,(short)1), new Point((short)0,(short)5), Direction.RIGHT);
        dataMap_.join((short)10, (short)11, new Point((short)0,(short)1), new Point((short)8,(short)4), Direction.LEFT);
        EqList<Coords> leaders_ = new EqList<Coords>();
        leaders_.add(coords(0,4,5,1,1));
        leaders_.add(coords(2,4,5,1,1));
        leaders_.add(coords(4,4,5,1,1));
        leaders_.add(coords(6,4,5,1,1));
        dataMap_.getAccessCondition().put(coordsAccessLeague_, leaders_);
        Coords coordsBlock_ = new Coords();
        coordsBlock_.setNumberPlace((short) 7);
        coordsBlock_.setLevel(new LevelPoint());
        coordsBlock_.getLevel().setLevelIndex((byte) 0);
        coordsBlock_.getLevel().setPoint(new Point((short)3,(short)0));
        dataMap_.getAccessCondition().put(coordsBlock_, new EqList<Coords>(leaders_));
        coordsBlock_ = new Coords();
        coordsBlock_.setNumberPlace((short) 7);
        coordsBlock_.setLevel(new LevelPoint());
        coordsBlock_.getLevel().setLevelIndex((byte) 0);
        coordsBlock_.getLevel().setPoint(new Point((short)3,(short)1));
        dataMap_.getAccessCondition().put(coordsBlock_, new EqList<Coords>(leaders_));
        coordsBlock_ = new Coords();
        coordsBlock_.setNumberPlace((short) 7);
        coordsBlock_.setLevel(new LevelPoint());
        coordsBlock_.getLevel().setLevelIndex((byte) 0);
        coordsBlock_.getLevel().setPoint(new Point((short)3,(short)2));
        dataMap_.getAccessCondition().put(coordsBlock_, new EqList<Coords>(leaders_));
        leaders_ = new EqList<Coords>();
        leaders_.add(coords(0,4,5,1,1));
        Coords coords_;
        coords_ = new Coords();
        coords_.setNumberPlace((short) 3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)2,(short)0));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)2,(short)1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)2,(short)2));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        leaders_ = new EqList<Coords>();
        leaders_.add(coords(2,4,5,1,1));
        coords_ = new Coords();
        coords_.setNumberPlace((short) 3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)3,(short)0));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)3,(short)1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)3,(short)2));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        leaders_ = new EqList<Coords>();
        leaders_.add(coords(4,4,5,1,1));
        coords_ = new Coords();
        coords_.setNumberPlace((short) 5);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)0,(short)1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 5);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)1,(short)1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 5);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)2,(short)1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        EqList<Coords> leagues_ = new EqList<Coords>();
//        leagues_.add(coords(9,0,2,2));
        leagues_.add(coords(9,0,2,4));
        coords_ = new Coords();
        coords_.setNumberPlace((short) 10);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)5,(short)0));
        dataMap_.getAccessCondition().put(coords_, leagues_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 10);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)5,(short)1));
        dataMap_.getAccessCondition().put(coords_, leagues_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 10);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)5,(short)2));
        dataMap_.getAccessCondition().put(coords_, leagues_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 0);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)0,(short)0));
        dataMap_.setBegin(coords_);
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel((short) 7);
        dataMap_.setFirstPokemon(pkm_);
        dataMap_.initInteractiveElements();
        dataMap_.initializeAccessibility();
    }

    private static City city() {
        City c_ = new City();
        c_.setSavedlinks(new ObjectMap<PlaceInterConnect,Coords>());
        c_.setLinksWithCaves(new ObjectMap<Point,Link>());
        LevelOutdoor city_ = new LevelOutdoor();
        city_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(new Point((short)0,(short)0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(new Point((short)0,(short)3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(new Point((short)0,(short)6), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(new Point((short)3,(short)0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.NOTHING, VOIE);
        city_.getBlocks().put(new Point((short)3,(short)3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(new Point((short)3,(short)6), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(new Point((short)6,(short)0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(new Point((short)6,(short)3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(new Point((short)6,(short)6), block_);
        c_.setLevel(city_);
        c_.setBuildings(new ObjectMap<Point,Building>());
        Gym gym_ = new Gym();
        gym_.setExitCity(new Point((short)1,(short)0));
        gym_.setLevel(new LevelIndoorGym());
        gym_.getLevel().setBlocks(new ObjectMap<Point,Block>());
        block_ = new Block((short)6,(short)6, EnvironmentType.BUILDING, VOIE);
        gym_.getLevel().getBlocks().put(new Point((short)0,(short)0), block_);
        gym_.getLevel().setGymLeaderCoords(new Point((short)1,(short)1));
        gym_.getLevel().setGymTrainers(new ObjectMap<Point,GymTrainer>());
        c_.getBuildings().put(new Point((short)4,(short)5), gym_);
        return c_;
    }

    private static Road hroad() {
        Road road_ = new Road();
        road_.setSavedlinks(new ObjectMap<PlaceInterConnect,Coords>());
        road_.setLinksWithCaves(new ObjectMap<Point,Link>());
        LevelRoad level_ = new LevelRoad();
        level_.setCharacters(new ObjectMap<Point,CharacterInRoadCave>());
        level_.setDualFights(new ObjectMap<Point,DualFight>());
        level_.setBlocks(new ObjectMap<Point,Block>());
        level_.setLegendaryPks(new ObjectMap<Point,WildPk>());
        level_.setItems(new ObjectMap<Point,String>());
        level_.setHm(new ObjectMap<Point,Short>());
        level_.setTm(new ObjectMap<Point,Short>());
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        Block block_ = new Block((short)6,(short)3, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        road_.setLevel(level_);
        return road_;
    }
    private static Road vroad() {
        Road road_ = new Road();
        road_.setSavedlinks(new ObjectMap<PlaceInterConnect,Coords>());
        road_.setLinksWithCaves(new ObjectMap<Point,Link>());
        LevelRoad level_ = new LevelRoad();
        level_.setCharacters(new ObjectMap<Point,CharacterInRoadCave>());
        level_.setDualFights(new ObjectMap<Point,DualFight>());
        level_.setBlocks(new ObjectMap<Point,Block>());
        level_.setLegendaryPks(new ObjectMap<Point,WildPk>());
        level_.setItems(new ObjectMap<Point,String>());
        level_.setHm(new ObjectMap<Point,Short>());
        level_.setTm(new ObjectMap<Point,Short>());
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        Block block_ = new Block((short)3,(short)6, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        road_.setLevel(level_);
        return road_;
    }

    private static League league(Coords _access) {
        League league_ = new League();
        league_.setName(L);
        league_.setAccessCoords(_access);
        league_.setRooms(new CustList<LevelLeague>());
        LevelLeague level_ = new LevelLeague();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5,(short)5, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        level_.setAccessPoint(new Point((short)2,(short)0));
        level_.setNextLevelTarget(new Point((short)2,(short)4));
        level_.setTrainerCoords(new Point((short)2,(short)2));
        league_.getRooms().add(level_);
        level_ = new LevelLeague();
        level_.setBlocks(new ObjectMap<Point,Block>());
        block_ = new Block((short)5,(short)5, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        level_.setAccessPoint(new Point((short)2,(short)0));
        level_.setNextLevelTarget(new Point());
        level_.setTrainerCoords(new Point((short)2,(short)2));
        league_.getRooms().add(level_);
//        league_.setBegin(new Point((short)2,(short)2));
        league_.setBegin(new Point((short)2,(short)4));
        return league_;
    }
    private static Coords coords(int _pl, int _level, int _x,int _y) {
        Coords c_ = new Coords();
        c_.setNumberPlace((short) _pl);
        c_.setLevel(new LevelPoint());
        c_.getLevel().setLevelIndex((byte) _level);
        c_.getLevel().setPoint(new Point((short)_x,(short)_y));
        return c_;
    }
    private static Coords coords(int _pl,int _xi,int _yi,int _x,int _y) {
        Coords c_ = new Coords();
        c_.setNumberPlace((short) _pl);
        c_.setInsideBuilding(new Point((short)_xi,(short)_yi));
        c_.setLevel(new LevelPoint());
        c_.getLevel().setLevelIndex((byte) 0);
        c_.getLevel().setPoint(new Point((short)_x,(short)_y));
        return c_;
    }

    @Test
    public void addBeatenTrainer1Test() {
        Game game_ = new Game(_dataCore_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _dataCore_);
        game_.beatGymLeader(coords(0,4,5,1,1));
        game_.addBeatenTrainer(coords(0,4,5,1,1), _dataCore_);
        assertEq(1, game_.getPartiallyAccessiblePlaces().size());
        assertEq(ROAD_2, game_.getPartiallyAccessiblePlaces().get(0));
        assertEq(0, game_.getFullAccessiblePlaces().size());
    }

    @Test
    public void addBeatenTrainer2Test() {
        Game game_ = new Game(_dataCore_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _dataCore_);
        game_.beatGymLeader(coords(0,4,5,1,1));
        game_.addBeatenTrainer(coords(0,4,5,1,2), _dataCore_);
        assertEq(0, game_.getPartiallyAccessiblePlaces().size());
        assertEq(0, game_.getFullAccessiblePlaces().size());
    }

    @Test
    public void addBeatenTrainer3Test() {
        Game game_ = new Game(_dataCore_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _dataCore_);
        game_.beatGymLeader(coords(0,4,5,1,1));
        game_.beatGymLeader(coords(2,4,5,1,1));
        game_.addBeatenTrainer(coords(2,4,5,1,1), _dataCore_);
        assertEq(1, game_.getPartiallyAccessiblePlaces().size());
        assertEq(ROAD_3, game_.getPartiallyAccessiblePlaces().get(0));
        assertEq(2, game_.getFullAccessiblePlaces().size());
        assertEq(C_3, game_.getFullAccessiblePlaces().get(0));
        assertEq(ROAD_2, game_.getFullAccessiblePlaces().get(1));
    }

    @Test
    public void addBeatenTrainer4Test() {
        Game game_ = new Game(_dataCore_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _dataCore_);
        game_.addBeatenTrainer(coords(0,4,5,1,2), _dataCore_);
        assertEq(0, game_.getPartiallyAccessiblePlaces().size());
        assertEq(0, game_.getFullAccessiblePlaces().size());
    }

    @Test
    public void addBeatenTrainer5Test() {
        Game game_ = new Game(_dataCore_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _dataCore_);
        game_.beatGymLeader(coords(0,4,5,1,1));
        game_.beatGymLeader(coords(2,4,5,1,1));
        game_.beatGymLeader(coords(4,4,5,1,1));
        game_.beatGymLeader(coords(6,4,5,1,1));
        game_.addBeatenTrainer(coords(6,4,5,1,1), _dataCore_);
        assertEq(0, game_.getPartiallyAccessiblePlaces().size());
        assertEq(3, game_.getFullAccessiblePlaces().size());
        assertEq(L, game_.getFullAccessiblePlaces().get(0));
        assertEq(ROAD_4, game_.getFullAccessiblePlaces().get(1));
        assertEq(ROAD_5, game_.getFullAccessiblePlaces().get(2));
    }

    @Test
    public void addBeatenTrainer6Test() {
        Game game_ = new Game(_dataCore_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _dataCore_);
        game_.beatGymLeader(coords(0,4,5,1,1));
        game_.beatGymLeader(coords(2,4,5,1,1));
        game_.beatGymLeader(coords(4,4,5,1,1));
        game_.beatGymLeader(coords(6,4,5,1,1));
        game_.beatGymLeader(coords(9,0,2,4));
        game_.addBeatenTrainer(coords(9,0,2,4), _dataCore_);
        assertEq(0, game_.getPartiallyAccessiblePlaces().size());
        assertEq(2, game_.getFullAccessiblePlaces().size());
        assertEq(C_5, game_.getFullAccessiblePlaces().get(0));
        assertEq(ROAD_6, game_.getFullAccessiblePlaces().get(1));
    }

    @Test
    public void addBeatenTrainer7Test() {
        Game game_ = new Game(_dataCore_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _dataCore_);
        game_.beatGymLeader(coords(0,4,5,1,1));
        game_.beatGymLeader(coords(2,4,5,1,1));
        game_.beatGymLeader(coords(4,4,5,1,1));
        game_.addBeatenTrainer(coords(4,4,5,1,1), _dataCore_);
        assertEq(2, game_.getPartiallyAccessiblePlaces().size());
        assertEq(ROAD_4, game_.getPartiallyAccessiblePlaces().get(0));
        assertEq(ROAD_5, game_.getPartiallyAccessiblePlaces().get(1));
        assertEq(2, game_.getFullAccessiblePlaces().size());
        assertEq(C_4, game_.getFullAccessiblePlaces().get(0));
        assertEq(ROAD_3, game_.getFullAccessiblePlaces().get(1));
    }

    @Test
    public void addBeatenTrainer8Test() {
        Game game_ = new Game(_dataCore_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _dataCore_);
        game_.beatGymLeader(coords(0,4,5,1,1));
        game_.beatGymLeader(coords(2,4,5,1,1));
        game_.beatGymLeader(coords(4,4,5,1,1));
        game_.addBeatenTrainer(new Coords(), _dataCore_);
        assertEq(0, game_.getPartiallyAccessiblePlaces().size());
        assertEq(0, game_.getFullAccessiblePlaces().size());
    }
}
