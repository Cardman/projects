package aiki.game;

import aiki.map.Condition;
import aiki.map.levels.*;
import aiki.map.util.PlaceInterConnects;
import aiki.util.*;
import org.junit.Test;

import aiki.db.DataBase;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.DataMap;
import aiki.map.buildings.Gym;
import aiki.map.enums.Direction;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.util.CustList;



public class GameUnlockPlacesTest extends InitializationDataBase {

    private static final String CST_L = "L";
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

    public static DataBase initLocalDataBase() {
        DataBase dataCore_ = InitializationDataBase.coreDataBase();
        dataCore_.sortEndRound();
        dataCore_.completeVariables();
        initRandomLaws(dataCore_);
        initExpPoints(dataCore_);
        initTmHm(dataCore_);
        dataCore_.initTypesByTable();
        initTranslations(dataCore_);
        initCondition(dataCore_);
        return dataCore_;
    }

    private static void initCondition(DataBase _data) {
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
        DataMap dataMap_ = _data.getMap();
        dataMap_.setAccessCondition(new CoordsLists());
        dataMap_.setPlaces(new CustList<Place>());
        dataMap_.getPlaces().add(cityOne_);
        dataMap_.getPlaces().add(roadOne_);
        dataMap_.getPlaces().add(cityTwo_);
        dataMap_.getPlaces().add(roadTwo_);
        dataMap_.getPlaces().add(cityThree_);
        dataMap_.getPlaces().add(roadThree_);
        dataMap_.getPlaces().add(cityFour_);
        dataMap_.getPlaces().add(roadFour_);
        dataMap_.getPlaces().add(roadFive_);
        Coords coordsAccessLeague_ = new Coords();
        coordsAccessLeague_.setNumberPlace(8);
        coordsAccessLeague_.setLevel(new LevelPoint());
        coordsAccessLeague_.getLevel().setLevelIndex(0);
        coordsAccessLeague_.getLevel().setPoint(newPoint(5,1));
        dataMap_.getPlaces().add(league(coordsAccessLeague_));
        dataMap_.getPlaces().add(roadSix_);
        dataMap_.getPlaces().add(cityFive_);
        dataMap_.join(0, 1, newPoint(4,0), newPoint(1,5), Direction.UP);
        dataMap_.join(1, 2, newPoint(1,0), newPoint(4,8), Direction.UP);
        dataMap_.join(2, 3, newPoint(8,4), newPoint(0,1), Direction.RIGHT);
        dataMap_.join(3, 4, newPoint(5,1), newPoint(0,4), Direction.RIGHT);
        dataMap_.join(4, 5, newPoint(4,8), newPoint(1,0), Direction.DOWN);
        dataMap_.join(5, 6, newPoint(1,5), newPoint(4,0), Direction.DOWN);
        dataMap_.join(6, 7, newPoint(0,4), newPoint(5,1), Direction.LEFT);
        dataMap_.join(7, 0, newPoint(0,1), newPoint(8,4), Direction.LEFT);
        dataMap_.join(8, 5, newPoint(0,1), newPoint(2,4), Direction.LEFT);
        dataMap_.join(10, 0, newPoint(5,1), newPoint(0,5), Direction.RIGHT);
        dataMap_.join(10, 11, newPoint(0,1), newPoint(8,4), Direction.LEFT);
        Condition leaders_ = new Condition();
        leaders_.add(newCoords(0,4,5,1,1));
        leaders_.add(newCoords(2,4,5,1,1));
        leaders_.add(newCoords(4,4,5,1,1));
        leaders_.add(newCoords(6,4,5,1,1));
        dataMap_.getAccessCondition().put(coordsAccessLeague_, leaders_);
        Coords coordsBlock_ = new Coords();
        coordsBlock_.setNumberPlace(7);
        coordsBlock_.setLevel(new LevelPoint());
        coordsBlock_.getLevel().setLevelIndex(0);
        coordsBlock_.getLevel().setPoint(newPoint(3,0));
        dataMap_.getAccessCondition().put(coordsBlock_, new Condition(leaders_));
        coordsBlock_ = new Coords();
        coordsBlock_.setNumberPlace(7);
        coordsBlock_.setLevel(new LevelPoint());
        coordsBlock_.getLevel().setLevelIndex(0);
        coordsBlock_.getLevel().setPoint(newPoint(3,1));
        dataMap_.getAccessCondition().put(coordsBlock_, new Condition(leaders_));
        coordsBlock_ = new Coords();
        coordsBlock_.setNumberPlace(7);
        coordsBlock_.setLevel(new LevelPoint());
        coordsBlock_.getLevel().setLevelIndex(0);
        coordsBlock_.getLevel().setPoint(newPoint(3,2));
        dataMap_.getAccessCondition().put(coordsBlock_, new Condition(leaders_));
        leaders_ = new Condition();
        leaders_.add(newCoords(0,4,5,1,1));
        Coords coords_;
        coords_ = new Coords();
        coords_.setNumberPlace(3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(2,0));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(2,1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(2,2));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        leaders_ = new Condition();
        leaders_.add(newCoords(2,4,5,1,1));
        coords_ = new Coords();
        coords_.setNumberPlace(3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(3,0));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(3,1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(3,2));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        leaders_ = new Condition();
        leaders_.add(newCoords(4,4,5,1,1));
        coords_ = new Coords();
        coords_.setNumberPlace(5);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(0,1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(5);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(1,1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(5);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(2,1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        Condition leagues_ = new Condition();
//        leagues_.add(newCoords(9,0,2,2));
        leagues_.add(newCoords(9,0,2,4));
        coords_ = new Coords();
        coords_.setNumberPlace(10);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(5,0));
        dataMap_.getAccessCondition().put(coords_, leagues_);
        coords_ = new Coords();
        coords_.setNumberPlace(10);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(5,1));
        dataMap_.getAccessCondition().put(coords_, leagues_);
        coords_ = new Coords();
        coords_.setNumberPlace(10);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(5,2));
        dataMap_.getAccessCondition().put(coords_, leagues_);
        coords_ = new Coords();
        coords_.setNumberPlace(0);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(0,0));
        dataMap_.setBegin(coords_);
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel( 7);
        dataMap_.setFirstPokemon(pkm_);
        dataMap_.initInteractiveElements();
        dataMap_.initializeAccessibility();
        assertTrue(!dataMap_.isError());
    }

    private static City city() {
        City c_ = new City();
        c_.setSavedlinks(new PlaceInterConnects());
        c_.setLinksWithCaves(new PointsLink());
        LevelOutdoor city_ = new LevelOutdoor();
        city_.setBlocks(new PointsBlock());
        Block block_ = new Block(3,3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(0,0), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(0,3), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(0,6), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(3,0), block_);
        block_ = new Block(3,3, EnvironmentType.NOTHING, VOIE);
        city_.getBlocks().put(newPoint(3,3), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(3,6), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(6,0), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(6,3), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(6,6), block_);
        c_.setLevel(city_);
        c_.setBuildings(new PointsBuilding());
        Gym gym_ = new Gym();
        gym_.setExitCity(newPoint(1,0));
        gym_.setLevel(new LevelIndoorGym());
        gym_.getLevel().setBlocks(new PointsBlock());
        block_ = new Block(6,6, EnvironmentType.BUILDING, VOIE);
        gym_.getLevel().getBlocks().put(newPoint(0,0), block_);
        gym_.getIndoor().setGymLeaderCoords(newPoint(1,1));
        gym_.getIndoor().setGymTrainers(new PointsGymTrainer());
        c_.getBuildings().put(newPoint(4,5), gym_);
        return c_;
    }

    private static Road hroad() {
        Road road_ = new Road();
        road_.setSavedlinks(new PlaceInterConnects());
        road_.setLinksWithCaves(new PointsLink());
        LevelRoad level_ = new LevelRoad();
        level_.setCharacters(new PointsCharacterInRoadCave());
        level_.setDualFights(new PointsDualFight());
        level_.setBlocks(new PointsBlock());
        level_.setLegendaryPks(new PointsWildPk());
        level_.setItems(new PointsString());
        level_.setHm(new PointsShort());
        level_.setTm(new PointsShort());
        level_.setWildPokemonAreas(new CustList<AbsAreaApparition>());
        Block block_ = new Block(6,3, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(newPoint(0,0), block_);
        road_.setLevel(level_);
        return road_;
    }
    private static Road vroad() {
        Road road_ = new Road();
        road_.setSavedlinks(new PlaceInterConnects());
        road_.setLinksWithCaves(new PointsLink());
        LevelRoad level_ = new LevelRoad();
        level_.setCharacters(new PointsCharacterInRoadCave());
        level_.setDualFights(new PointsDualFight());
        level_.setBlocks(new PointsBlock());
        level_.setLegendaryPks(new PointsWildPk());
        level_.setItems(new PointsString());
        level_.setHm(new PointsShort());
        level_.setTm(new PointsShort());
        level_.setWildPokemonAreas(new CustList<AbsAreaApparition>());
        Block block_ = new Block(3,6, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(newPoint(0,0), block_);
        road_.setLevel(level_);
        return road_;
    }

    private static League league(Coords _access) {
        League league_ = new League();
        league_.setName(CST_L);
        league_.setAccessCoords(_access);
        league_.setRooms(new CustList<LevelLeague>());
        LevelLeague level_ = new LevelLeague();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block(5,5, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(newPoint(0,0), block_);
        level_.setAccessPoint(newPoint(2,0));
        level_.setNextLevelTarget(newPoint(2,4));
        level_.setTrainerCoords(newPoint(2,2));
        league_.getRooms().add(level_);
        level_ = new LevelLeague();
        level_.setBlocks(new PointsBlock());
        block_ = new Block(5,5, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(newPoint(0,0), block_);
        level_.setAccessPoint(newPoint(2,0));
        level_.setNextLevelTarget(new NullablePoint());
        level_.setTrainerCoords(newPoint(2,2));
        league_.getRooms().add(level_);
//        league_.setBegin(newPoint(2,2));
        league_.setBegin(newPoint(2,4));
        return league_;
    }
    

    @Test
    public void addBeatenTrainer1Test() {
        DataBase dataCore_ = initLocalDataBase();
        Game game_ = new Game(dataCore_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), dataCore_);
        game_.beatGymLeader(newCoords(0,4,5,1,1));
        game_.addBeatenTrainer(newCoords(0,4,5,1,1), dataCore_);
        assertEq(1, game_.getPartiallyAccessiblePlaces().size());
        assertEq(ROAD_2, game_.getPartiallyAccessiblePlaces().get(0));
        assertEq(0, game_.getFullAccessiblePlaces().size());
    }

    @Test
    public void addBeatenTrainer2Test() {
        DataBase dataCore_ = initLocalDataBase();
        Game game_ = new Game(dataCore_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), dataCore_);
        game_.beatGymLeader(newCoords(0,4,5,1,1));
        game_.addBeatenTrainer(newCoords(0,4,5,1,2), dataCore_);
        assertEq(0, game_.getPartiallyAccessiblePlaces().size());
        assertEq(0, game_.getFullAccessiblePlaces().size());
    }

    @Test
    public void addBeatenTrainer3Test() {
        DataBase dataCore_ = initLocalDataBase();
        Game game_ = new Game(dataCore_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), dataCore_);
        game_.beatGymLeader(newCoords(0,4,5,1,1));
        game_.beatGymLeader(newCoords(2,4,5,1,1));
        game_.addBeatenTrainer(newCoords(2,4,5,1,1), dataCore_);
        assertEq(1, game_.getPartiallyAccessiblePlaces().size());
        assertEq(ROAD_3, game_.getPartiallyAccessiblePlaces().get(0));
        assertEq(2, game_.getFullAccessiblePlaces().size());
        assertEq(C_3, game_.getFullAccessiblePlaces().get(0));
        assertEq(ROAD_2, game_.getFullAccessiblePlaces().get(1));
    }

    @Test
    public void addBeatenTrainer4Test() {
        DataBase dataCore_ = initLocalDataBase();
        Game game_ = new Game(dataCore_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), dataCore_);
        game_.addBeatenTrainer(newCoords(0,4,5,1,2), dataCore_);
        assertEq(0, game_.getPartiallyAccessiblePlaces().size());
        assertEq(0, game_.getFullAccessiblePlaces().size());
    }

    @Test
    public void addBeatenTrainer5Test() {
        DataBase dataCore_ = initLocalDataBase();
        Game game_ = new Game(dataCore_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), dataCore_);
        game_.beatGymLeader(newCoords(0,4,5,1,1));
        game_.beatGymLeader(newCoords(2,4,5,1,1));
        game_.beatGymLeader(newCoords(4,4,5,1,1));
        game_.beatGymLeader(newCoords(6,4,5,1,1));
        game_.addBeatenTrainer(newCoords(6,4,5,1,1), dataCore_);
        assertEq(0, game_.getPartiallyAccessiblePlaces().size());
        assertEq(3, game_.getFullAccessiblePlaces().size());
        assertEq(CST_L, game_.getFullAccessiblePlaces().get(0));
        assertEq(ROAD_4, game_.getFullAccessiblePlaces().get(1));
        assertEq(ROAD_5, game_.getFullAccessiblePlaces().get(2));
    }

    @Test
    public void addBeatenTrainer6Test() {
        DataBase dataCore_ = initLocalDataBase();
        Game game_ = new Game(dataCore_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), dataCore_);
        game_.beatGymLeader(newCoords(0,4,5,1,1));
        game_.beatGymLeader(newCoords(2,4,5,1,1));
        game_.beatGymLeader(newCoords(4,4,5,1,1));
        game_.beatGymLeader(newCoords(6,4,5,1,1));
        game_.beatGymLeader(newCoords(9,0,2,4));
        game_.addBeatenTrainer(newCoords(9,0,2,4), dataCore_);
        assertEq(0, game_.getPartiallyAccessiblePlaces().size());
        assertEq(2, game_.getFullAccessiblePlaces().size());
        assertEq(C_5, game_.getFullAccessiblePlaces().get(0));
        assertEq(ROAD_6, game_.getFullAccessiblePlaces().get(1));
    }

    @Test
    public void addBeatenTrainer7Test() {
        DataBase dataCore_ = initLocalDataBase();
        Game game_ = new Game(dataCore_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), dataCore_);
        game_.beatGymLeader(newCoords(0,4,5,1,1));
        game_.beatGymLeader(newCoords(2,4,5,1,1));
        game_.beatGymLeader(newCoords(4,4,5,1,1));
        game_.addBeatenTrainer(newCoords(4,4,5,1,1), dataCore_);
        assertEq(2, game_.getPartiallyAccessiblePlaces().size());
        assertEq(ROAD_4, game_.getPartiallyAccessiblePlaces().get(0));
        assertEq(ROAD_5, game_.getPartiallyAccessiblePlaces().get(1));
        assertEq(2, game_.getFullAccessiblePlaces().size());
        assertEq(C_4, game_.getFullAccessiblePlaces().get(0));
        assertEq(ROAD_3, game_.getFullAccessiblePlaces().get(1));
    }

    @Test
    public void addBeatenTrainer8Test() {
        DataBase dataCore_ = initLocalDataBase();
        Game game_ = new Game(dataCore_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), dataCore_);
        game_.beatGymLeader(newCoords(0,4,5,1,1));
        game_.beatGymLeader(newCoords(2,4,5,1,1));
        game_.beatGymLeader(newCoords(4,4,5,1,1));
        game_.addBeatenTrainer(new Coords(), dataCore_);
        assertEq(0, game_.getPartiallyAccessiblePlaces().size());
        assertEq(0, game_.getFullAccessiblePlaces().size());
    }
}
