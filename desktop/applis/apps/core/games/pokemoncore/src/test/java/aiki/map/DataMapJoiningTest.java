package aiki.map;

import aiki.db.EquallablePkUtil;
import aiki.map.util.PlaceInterConnects;
import aiki.util.*;
import org.junit.Test;

import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.enums.Direction;
import aiki.map.levels.Block;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelOutdoor;
import aiki.map.levels.LevelRoad;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.util.PlaceInterConnect;
import code.util.CustList;


public class DataMapJoiningTest extends EquallablePkUtil {

    private static final String VOIE = "voie";

    private static City city() {
        City c_ = new City();
        c_.setSavedlinks(new PlaceInterConnects());
        c_.setBuildings(new PointsBuilding());
        LevelOutdoor city_ = new LevelOutdoor();
        city_.setBlocks(new PointsBlock());
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
        Gym gym_ = new Gym();
        gym_.setExitCity(new Point((short)1,(short)1));
        gym_.setLevel(new LevelIndoorGym());
        gym_.getIndoor().setGymLeaderCoords(new Point((short)1,(short)1));
        c_.getBuildings().put(new Point((short)4,(short)5), gym_);
        return c_;
    }
    private static Road hroad() {
        Road road_ = new Road();
        road_.setSavedlinks(new PlaceInterConnects());
        LevelRoad level_ = new LevelRoad();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block((short)6,(short)3, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        road_.setLevel(level_);
        return road_;
    }
    private static Road vroad() {
        Road road_ = new Road();
        road_.setSavedlinks(new PlaceInterConnects());
        LevelRoad level_ = new LevelRoad();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block((short)3,(short)6, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        road_.setLevel(level_);
        return road_;
    }
    private static League league(Coords _access) {
        League league_ = new League();
        league_.setAccessCoords(_access);
        league_.setRooms(new CustList<LevelLeague>());
        LevelLeague level_ = new LevelLeague();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block((short)5,(short)5, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        level_.setAccessPoint(new Point((short)2,(short)0));
        level_.setTrainerCoords(new Point((short)2,(short)2));
        league_.getRooms().add(level_);
        league_.setBegin(new Point((short)2,(short)4));
        return league_;
    }

    public static DataMap initDataMap() {
        City cityOne_ = city();
        City cityTwo_ = city();
        City cityThree_ = city();
        City cityFour_ = city();
        City cityFive_ = city();
        Road roadOne_ = vroad();
        Road roadTwo_ = hroad();
        Road roadThree_ = vroad();
        Road roadFour_ = hroad();
        Road roadFive_ = hroad();
        Road roadSix_ = hroad();
        DataMap dataMap_ = new DataMap();
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
        coordsAccessLeague_.setNumberPlace((short) 8);
        coordsAccessLeague_.setLevel(new LevelPoint());
        coordsAccessLeague_.getLevel().setLevelIndex((byte) 0);
        coordsAccessLeague_.getLevel().setPoint(new Point((short)5,(short)1));
        dataMap_.getPlaces().add(league(coordsAccessLeague_));
        dataMap_.getPlaces().add(roadSix_);
        dataMap_.getPlaces().add(cityFive_);
        return dataMap_;
    }
    @Test
    public void join1Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.join((short)0, (short)1, new Point((short)4,(short)0), new Point((short)1,(short)5), Direction.UP);
        assertTrue(dataMap_.validSavedLink());
        City cityZero_ = (City) dataMap_.getPlace((short) 0);
        assertEq(3, cityZero_.getPointsWithCitiesAndOtherRoads().size());
        assertEq(1, cityZero_.getSavedlinks().size());
        Road roadOne_ = (Road) dataMap_.getPlace((short) 1);
        assertEq(3, roadOne_.getPointsWithCitiesAndOtherRoads().size());
        assertEq(1, roadOne_.getSavedlinks().size());
        assertTrue(cityZero_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)3,(short)0), Direction.UP)));
        assertTrue(cityZero_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)4,(short)0), Direction.UP)));
        assertTrue(cityZero_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)5,(short)0), Direction.UP)));
        assertTrue(cityZero_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)4,(short)0), Direction.UP)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)0,(short)5), Direction.DOWN)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)1,(short)5), Direction.DOWN)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)2,(short)5), Direction.DOWN)));
        assertTrue(roadOne_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)1,(short)5), Direction.DOWN)));
        Coords coordsOne_ = cityZero_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)3,(short)0), Direction.UP));
        Coords coordsTwo_ = cityZero_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)4,(short)0), Direction.UP));
        Coords coordsThree_ = cityZero_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)5,(short)0), Direction.UP));
        Coords expectedOne_ = new Coords();
        expectedOne_.setNumberPlace((short) 1);
        expectedOne_.setLevel(new LevelPoint());
        expectedOne_.getLevel().setLevelIndex((byte) 0);
        expectedOne_.getLevel().setPoint(new Point((short)0,(short)5));
        Coords expectedTwo_ = new Coords(expectedOne_);
        Coords expectedThree_ = new Coords(expectedOne_);
        expectedTwo_.getLevel().setPoint(new Point((short)1,(short)5));
        expectedThree_.getLevel().setPoint(new Point((short)2,(short)5));
        assertEq(expectedOne_, coordsOne_);
        assertEq(expectedTwo_, coordsTwo_);
        assertEq(expectedTwo_, cityZero_.getSavedlinks().getVal(new PlaceInterConnect(new Point((short)4,(short)0), Direction.UP)));
        assertEq(expectedThree_, coordsThree_);
        Coords coordsFour_ = roadOne_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)0,(short)5), Direction.DOWN));
        Coords coordsFive_ = roadOne_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)1,(short)5), Direction.DOWN));
        Coords coordsSix_ = roadOne_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)2,(short)5), Direction.DOWN));
        Coords expectedFour_ = new Coords();
        expectedFour_.setNumberPlace((short) 0);
        expectedFour_.setLevel(new LevelPoint());
        expectedFour_.getLevel().setLevelIndex((byte) 0);
        expectedFour_.getLevel().setPoint(new Point((short)3,(short)0));
        Coords expectedFive_ = new Coords(expectedFour_);
        Coords expectedSix_ = new Coords(expectedFour_);
        expectedFive_.getLevel().setPoint(new Point((short)4,(short)0));
        expectedSix_.getLevel().setPoint(new Point((short)5,(short)0));
        assertEq(expectedFour_, coordsFour_);
        assertEq(expectedFive_, coordsFive_);
        assertEq(expectedFive_, roadOne_.getSavedlinks().getVal(new PlaceInterConnect(new Point((short)1,(short)5), Direction.DOWN)));
        assertEq(expectedSix_, coordsSix_);
        /*
        dataMap_.join((short)1, (short)2, new Point((short)1,(short)0), new Point((short)4,(short)8), Direction.UP, Direction.DOWN);
        dataMap_.join((short)2, (short)3, new Point((short)8,(short)4), new Point((short)0,(short)1), Direction.RIGHT, Direction.LEFT);
        dataMap_.join((short)3, (short)4, new Point((short)5,(short)1), new Point((short)0,(short)4), Direction.RIGHT, Direction.LEFT);
        dataMap_.join((short)4, (short)5, new Point((short)4,(short)8), new Point((short)1,(short)0), Direction.DOWN, Direction.UP);
        dataMap_.join((short)5, (short)6, new Point((short)1,(short)5), new Point((short)4,(short)0), Direction.DOWN, Direction.UP);
        dataMap_.join((short)6, (short)7, new Point((short)0,(short)4), new Point((short)5,(short)1), Direction.LEFT, Direction.RIGHT);
        dataMap_.join((short)7, (short)0, new Point((short)0,(short)1), new Point((short)8,(short)4), Direction.LEFT, Direction.RIGHT);
        dataMap_.join((short)8, (short)5, new Point((short)0,(short)1), new Point((short)2,(short)4), Direction.LEFT, Direction.RIGHT);
        dataMap_.join((short)10, (short)0, new Point((short)5,(short)1), new Point((short)0,(short)5), Direction.RIGHT, Direction.LEFT);
        dataMap_.join((short)10, (short)11, new Point((short)0,(short)1), new Point((short)8,(short)4), Direction.LEFT, Direction.RIGHT);
        */
    }

    @Test
    public void join2Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.join((short)2, (short)3, new Point((short)8,(short)4), new Point((short)0,(short)1), Direction.RIGHT);
        assertTrue(dataMap_.validSavedLink());
        City cityTwo_ = (City) dataMap_.getPlace((short) 2);
        assertEq(3, cityTwo_.getPointsWithCitiesAndOtherRoads().size());
        assertEq(1, cityTwo_.getSavedlinks().size());
        Road roadTwo_ = (Road) dataMap_.getPlace((short) 3);
        assertEq(3, roadTwo_.getPointsWithCitiesAndOtherRoads().size());
        assertEq(1, roadTwo_.getSavedlinks().size());
        assertTrue(cityTwo_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)8,(short)3), Direction.RIGHT)));
        assertTrue(cityTwo_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)8,(short)4), Direction.RIGHT)));
        assertTrue(cityTwo_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)8,(short)5), Direction.RIGHT)));
        assertTrue(cityTwo_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)8,(short)4), Direction.RIGHT)));
        assertTrue(roadTwo_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)0,(short)0), Direction.LEFT)));
        assertTrue(roadTwo_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)0,(short)1), Direction.LEFT)));
        assertTrue(roadTwo_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)0,(short)2), Direction.LEFT)));
        assertTrue(roadTwo_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)0,(short)1), Direction.LEFT)));
        Coords coordsOne_ = cityTwo_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)8,(short)3), Direction.RIGHT));
        Coords coordsTwo_ = cityTwo_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)8,(short)4), Direction.RIGHT));
        Coords coordsThree_ = cityTwo_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)8,(short)5), Direction.RIGHT));
        Coords expectedOne_ = new Coords();
        expectedOne_.setNumberPlace((short) 3);
        expectedOne_.setLevel(new LevelPoint());
        expectedOne_.getLevel().setLevelIndex((byte) 0);
        expectedOne_.getLevel().setPoint(new Point((short)0,(short)0));
        Coords expectedTwo_ = new Coords(expectedOne_);
        Coords expectedThree_ = new Coords(expectedOne_);
        expectedTwo_.getLevel().setPoint(new Point((short)0,(short)1));
        expectedThree_.getLevel().setPoint(new Point((short)0,(short)2));
        assertEq(expectedOne_, coordsOne_);
        assertEq(expectedTwo_, coordsTwo_);
        assertEq(expectedTwo_, cityTwo_.getSavedlinks().getVal(new PlaceInterConnect(new Point((short)8,(short)4), Direction.RIGHT)));
        assertEq(expectedThree_, coordsThree_);
        Coords coordsFour_ = roadTwo_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)0,(short)0), Direction.LEFT));
        Coords coordsFive_ = roadTwo_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)0,(short)1), Direction.LEFT));
        Coords coordsSix_ = roadTwo_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)0,(short)2), Direction.LEFT));
        Coords expectedFour_ = new Coords();
        expectedFour_.setNumberPlace((short) 2);
        expectedFour_.setLevel(new LevelPoint());
        expectedFour_.getLevel().setLevelIndex((byte) 0);
        expectedFour_.getLevel().setPoint(new Point((short)8,(short)3));
        Coords expectedFive_ = new Coords(expectedFour_);
        Coords expectedSix_ = new Coords(expectedFour_);
        expectedFive_.getLevel().setPoint(new Point((short)8,(short)4));
        expectedSix_.getLevel().setPoint(new Point((short)8,(short)5));
        assertEq(expectedFour_, coordsFour_);
        assertEq(expectedFive_, coordsFive_);
        assertEq(expectedFive_, roadTwo_.getSavedlinks().getVal(new PlaceInterConnect(new Point((short)0,(short)1), Direction.LEFT)));
        assertEq(expectedSix_, coordsSix_);
    }

    @Test
    public void join3Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.join((short)4, (short)5, new Point((short)4,(short)8), new Point((short)1,(short)0), Direction.DOWN);
        assertTrue(dataMap_.validSavedLink());
        City cityZero_ = (City) dataMap_.getPlace((short) 4);
        assertEq(3, cityZero_.getPointsWithCitiesAndOtherRoads().size());
        assertEq(1, cityZero_.getSavedlinks().size());
        Road roadOne_ = (Road) dataMap_.getPlace((short) 5);
        assertEq(3, roadOne_.getPointsWithCitiesAndOtherRoads().size());
        assertEq(1, roadOne_.getSavedlinks().size());
        assertTrue(cityZero_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)3,(short)8), Direction.DOWN)));
        assertTrue(cityZero_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)4,(short)8), Direction.DOWN)));
        assertTrue(cityZero_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)5,(short)8), Direction.DOWN)));
        assertTrue(cityZero_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)4,(short)8), Direction.DOWN)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)0,(short)0), Direction.UP)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)1,(short)0), Direction.UP)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)2,(short)0), Direction.UP)));
        assertTrue(roadOne_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)1,(short)0), Direction.UP)));
        Coords coordsOne_ = cityZero_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)3,(short)8), Direction.DOWN));
        Coords coordsTwo_ = cityZero_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)4,(short)8), Direction.DOWN));
        Coords coordsThree_ = cityZero_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)5,(short)8), Direction.DOWN));
        Coords expectedOne_ = new Coords();
        expectedOne_.setNumberPlace((short) 5);
        expectedOne_.setLevel(new LevelPoint());
        expectedOne_.getLevel().setLevelIndex((byte) 0);
        expectedOne_.getLevel().setPoint(new Point((short)0,(short)0));
        Coords expectedTwo_ = new Coords(expectedOne_);
        Coords expectedThree_ = new Coords(expectedOne_);
        expectedTwo_.getLevel().setPoint(new Point((short)1,(short)0));
        expectedThree_.getLevel().setPoint(new Point((short)2,(short)0));
        assertEq(expectedOne_, coordsOne_);
        assertEq(expectedTwo_, coordsTwo_);
        assertEq(expectedTwo_, cityZero_.getSavedlinks().getVal(new PlaceInterConnect(new Point((short)4,(short)8), Direction.DOWN)));
        assertEq(expectedThree_, coordsThree_);
        Coords coordsFour_ = roadOne_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)0,(short)0), Direction.UP));
        Coords coordsFive_ = roadOne_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)1,(short)0), Direction.UP));
        Coords coordsSix_ = roadOne_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)2,(short)0), Direction.UP));
        Coords expectedFour_ = new Coords();
        expectedFour_.setNumberPlace((short) 4);
        expectedFour_.setLevel(new LevelPoint());
        expectedFour_.getLevel().setLevelIndex((byte) 0);
        expectedFour_.getLevel().setPoint(new Point((short)3,(short)8));
        Coords expectedFive_ = new Coords(expectedFour_);
        Coords expectedSix_ = new Coords(expectedFour_);
        expectedFive_.getLevel().setPoint(new Point((short)4,(short)8));
        expectedSix_.getLevel().setPoint(new Point((short)5,(short)8));
        assertEq(expectedFour_, coordsFour_);
        assertEq(expectedFive_, coordsFive_);
        assertEq(expectedFive_, roadOne_.getSavedlinks().getVal(new PlaceInterConnect(new Point((short)1,(short)0), Direction.UP)));
        assertEq(expectedSix_, coordsSix_);
    }

    @Test
    public void join4Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.join((short)6, (short)7, new Point((short)0,(short)4), new Point((short)5,(short)1), Direction.LEFT);
        assertTrue(dataMap_.validSavedLink());
        City cityTwo_ = (City) dataMap_.getPlace((short) 6);
        assertEq(3, cityTwo_.getPointsWithCitiesAndOtherRoads().size());
        assertEq(1, cityTwo_.getSavedlinks().size());
        Road roadTwo_ = (Road) dataMap_.getPlace((short) 7);
        assertEq(3, roadTwo_.getPointsWithCitiesAndOtherRoads().size());
        assertEq(1, roadTwo_.getSavedlinks().size());
        assertTrue(cityTwo_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)0,(short)3), Direction.LEFT)));
        assertTrue(cityTwo_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)0,(short)4), Direction.LEFT)));
        assertTrue(cityTwo_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)0,(short)5), Direction.LEFT)));
        assertTrue(cityTwo_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)0,(short)4), Direction.LEFT)));
        assertTrue(roadTwo_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)5,(short)0), Direction.RIGHT)));
        assertTrue(roadTwo_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)5,(short)1), Direction.RIGHT)));
        assertTrue(roadTwo_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)5,(short)2), Direction.RIGHT)));
        assertTrue(roadTwo_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)5,(short)1), Direction.RIGHT)));
        Coords coordsOne_ = cityTwo_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)0,(short)3), Direction.LEFT));
        Coords coordsTwo_ = cityTwo_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)0,(short)4), Direction.LEFT));
        Coords coordsThree_ = cityTwo_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)0,(short)5), Direction.LEFT));
        Coords expectedOne_ = new Coords();
        expectedOne_.setNumberPlace((short) 7);
        expectedOne_.setLevel(new LevelPoint());
        expectedOne_.getLevel().setLevelIndex((byte) 0);
        expectedOne_.getLevel().setPoint(new Point((short)5,(short)0));
        Coords expectedTwo_ = new Coords(expectedOne_);
        Coords expectedThree_ = new Coords(expectedOne_);
        expectedTwo_.getLevel().setPoint(new Point((short)5,(short)1));
        expectedThree_.getLevel().setPoint(new Point((short)5,(short)2));
        assertEq(expectedOne_, coordsOne_);
        assertEq(expectedTwo_, coordsTwo_);
        assertEq(expectedTwo_, cityTwo_.getSavedlinks().getVal(new PlaceInterConnect(new Point((short)0,(short)4), Direction.LEFT)));
        assertEq(expectedThree_, coordsThree_);
        Coords coordsFour_ = roadTwo_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)5,(short)0), Direction.RIGHT));
        Coords coordsFive_ = roadTwo_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)5,(short)1), Direction.RIGHT));
        Coords coordsSix_ = roadTwo_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)5,(short)2), Direction.RIGHT));
        Coords expectedFour_ = new Coords();
        expectedFour_.setNumberPlace((short) 6);
        expectedFour_.setLevel(new LevelPoint());
        expectedFour_.getLevel().setLevelIndex((byte) 0);
        expectedFour_.getLevel().setPoint(new Point((short)0,(short)3));
        Coords expectedFive_ = new Coords(expectedFour_);
        Coords expectedSix_ = new Coords(expectedFour_);
        expectedFive_.getLevel().setPoint(new Point((short)0,(short)4));
        expectedSix_.getLevel().setPoint(new Point((short)0,(short)5));
        assertEq(expectedFour_, coordsFour_);
        assertEq(expectedFive_, coordsFive_);
        assertEq(expectedFive_, roadTwo_.getSavedlinks().getVal(new PlaceInterConnect(new Point((short)5,(short)1), Direction.RIGHT)));
        assertEq(expectedSix_, coordsSix_);
    }

    @Test
    public void initializeLinks1Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.join((short)0, (short)1, new Point((short)4,(short)0), new Point((short)1,(short)5), Direction.UP);
        City cityZero_ = (City) dataMap_.getPlace((short) 0);
        cityZero_.getPointsWithCitiesAndOtherRoads().clear();
        assertEq(1, cityZero_.getSavedlinks().size());
        Road roadOne_ = (Road) dataMap_.getPlace((short) 1);
        roadOne_.getPointsWithCitiesAndOtherRoads().clear();
        assertEq(1, roadOne_.getSavedlinks().size());
        dataMap_.initializeLinks();
        assertEq(3, cityZero_.getPointsWithCitiesAndOtherRoads().size());
        assertEq(3, roadOne_.getPointsWithCitiesAndOtherRoads().size());
        assertTrue(cityZero_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)3,(short)0), Direction.UP)));
        assertTrue(cityZero_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)4,(short)0), Direction.UP)));
        assertTrue(cityZero_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)5,(short)0), Direction.UP)));
        assertTrue(cityZero_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)4,(short)0), Direction.UP)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)0,(short)5), Direction.DOWN)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)1,(short)5), Direction.DOWN)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)2,(short)5), Direction.DOWN)));
        assertTrue(roadOne_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)1,(short)5), Direction.DOWN)));
        Coords coordsOne_ = cityZero_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)3,(short)0), Direction.UP));
        Coords coordsTwo_ = cityZero_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)4,(short)0), Direction.UP));
        Coords coordsThree_ = cityZero_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)5,(short)0), Direction.UP));
        Coords expectedOne_ = new Coords();
        expectedOne_.setNumberPlace((short) 1);
        expectedOne_.setLevel(new LevelPoint());
        expectedOne_.getLevel().setLevelIndex((byte) 0);
        expectedOne_.getLevel().setPoint(new Point((short)0,(short)5));
        Coords expectedTwo_ = new Coords(expectedOne_);
        Coords expectedThree_ = new Coords(expectedOne_);
        expectedTwo_.getLevel().setPoint(new Point((short)1,(short)5));
        expectedThree_.getLevel().setPoint(new Point((short)2,(short)5));
        assertEq(expectedOne_, coordsOne_);
        assertEq(expectedTwo_, coordsTwo_);
        assertEq(expectedTwo_, cityZero_.getSavedlinks().getVal(new PlaceInterConnect(new Point((short)4,(short)0), Direction.UP)));
        assertEq(expectedThree_, coordsThree_);
        Coords coordsFour_ = roadOne_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)0,(short)5), Direction.DOWN));
        Coords coordsFive_ = roadOne_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)1,(short)5), Direction.DOWN));
        Coords coordsSix_ = roadOne_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)2,(short)5), Direction.DOWN));
        Coords expectedFour_ = new Coords();
        expectedFour_.setNumberPlace((short) 0);
        expectedFour_.setLevel(new LevelPoint());
        expectedFour_.getLevel().setLevelIndex((byte) 0);
        expectedFour_.getLevel().setPoint(new Point((short)3,(short)0));
        Coords expectedFive_ = new Coords(expectedFour_);
        Coords expectedSix_ = new Coords(expectedFour_);
        expectedFive_.getLevel().setPoint(new Point((short)4,(short)0));
        expectedSix_.getLevel().setPoint(new Point((short)5,(short)0));
        assertEq(expectedFour_, coordsFour_);
        assertEq(expectedFive_, coordsFive_);
        assertEq(expectedFive_, roadOne_.getSavedlinks().getVal(new PlaceInterConnect(new Point((short)1,(short)5), Direction.DOWN)));
        assertEq(expectedSix_, coordsSix_);
    }
    @Test
    public void initializeLinks2Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.join((short)0, (short)1, new Point((short)4,(short)0), new Point((short)1,(short)5), Direction.UP);
        dataMap_.join((short)1, (short)2, new Point((short)1,(short)0), new Point((short)4,(short)8), Direction.UP);
        City cityZero_ = (City) dataMap_.getPlace((short) 0);
        cityZero_.getPointsWithCitiesAndOtherRoads().clear();
        assertEq(1, cityZero_.getSavedlinks().size());
        Road roadOne_ = (Road) dataMap_.getPlace((short) 1);
        roadOne_.getPointsWithCitiesAndOtherRoads().clear();
        assertEq(2, roadOne_.getSavedlinks().size());
        City cityOne_ = (City) dataMap_.getPlace((short) 2);
        cityOne_.getPointsWithCitiesAndOtherRoads().clear();
        assertEq(1, cityOne_.getSavedlinks().size());
        dataMap_.initializeLinks();
        assertEq(3, cityZero_.getPointsWithCitiesAndOtherRoads().size());
        assertEq(6, roadOne_.getPointsWithCitiesAndOtherRoads().size());
        assertTrue(cityZero_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)3,(short)0), Direction.UP)));
        assertTrue(cityZero_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)4,(short)0), Direction.UP)));
        assertTrue(cityZero_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)5,(short)0), Direction.UP)));
        assertTrue(cityZero_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)4,(short)0), Direction.UP)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)0,(short)5), Direction.DOWN)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)1,(short)5), Direction.DOWN)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)2,(short)5), Direction.DOWN)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)0,(short)0), Direction.UP)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)1,(short)0), Direction.UP)));
        assertTrue(roadOne_.getPointsWithCitiesAndOtherRoads().contains(new PlaceInterConnect(new Point((short)2,(short)0), Direction.UP)));
        assertTrue(roadOne_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)1,(short)5), Direction.DOWN)));
        assertTrue(roadOne_.getSavedlinks().contains(new PlaceInterConnect(new Point((short)1,(short)0), Direction.UP)));
        Coords coordsOne_ = cityZero_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)3,(short)0), Direction.UP));
        Coords coordsTwo_ = cityZero_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)4,(short)0), Direction.UP));
        Coords coordsThree_ = cityZero_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)5,(short)0), Direction.UP));
        Coords expectedOne_ = new Coords();
        expectedOne_.setNumberPlace((short) 1);
        expectedOne_.setLevel(new LevelPoint());
        expectedOne_.getLevel().setLevelIndex((byte) 0);
        expectedOne_.getLevel().setPoint(new Point((short)0,(short)5));
        Coords expectedTwo_ = new Coords(expectedOne_);
        Coords expectedThree_ = new Coords(expectedOne_);
        expectedTwo_.getLevel().setPoint(new Point((short)1,(short)5));
        expectedThree_.getLevel().setPoint(new Point((short)2,(short)5));
        assertEq(expectedOne_, coordsOne_);
        assertEq(expectedTwo_, coordsTwo_);
        assertEq(expectedTwo_, cityZero_.getSavedlinks().getVal(new PlaceInterConnect(new Point((short)4,(short)0), Direction.UP)));
        assertEq(expectedThree_, coordsThree_);
        Coords coordsFour_ = roadOne_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)0,(short)5), Direction.DOWN));
        Coords coordsFive_ = roadOne_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)1,(short)5), Direction.DOWN));
        Coords coordsSix_ = roadOne_.getPointsWithCitiesAndOtherRoads().getVal(new PlaceInterConnect(new Point((short)2,(short)5), Direction.DOWN));
        Coords expectedFour_ = new Coords();
        expectedFour_.setNumberPlace((short) 0);
        expectedFour_.setLevel(new LevelPoint());
        expectedFour_.getLevel().setLevelIndex((byte) 0);
        expectedFour_.getLevel().setPoint(new Point((short)3,(short)0));
        Coords expectedFive_ = new Coords(expectedFour_);
        Coords expectedSix_ = new Coords(expectedFour_);
        expectedFive_.getLevel().setPoint(new Point((short)4,(short)0));
        expectedSix_.getLevel().setPoint(new Point((short)5,(short)0));
        assertEq(expectedFour_, coordsFour_);
        assertEq(expectedFive_, coordsFive_);
        assertEq(expectedFive_, roadOne_.getSavedlinks().getVal(new PlaceInterConnect(new Point((short)1,(short)5), Direction.DOWN)));
        assertEq(expectedSix_, coordsSix_);
    }
}
