package aiki.map;

import aiki.db.EquallablePkUtil;
import aiki.db.ImageArrayBaseSixtyFour;
import aiki.map.util.PlaceInterConnects;
import aiki.util.*;
import code.maths.montecarlo.DefaultGenerator;
import org.junit.Test;

import aiki.db.DataBase;
import aiki.map.buildings.Gym;
import aiki.map.enums.Direction;
import aiki.map.levels.Block;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelOutdoor;
import aiki.map.levels.LevelRoad;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.util.ScreenCoords;
import code.util.*;

import code.util.StringMap;

public class DataMapScreenTest extends EquallablePkUtil {

    private static final String VOIE3 = "voie3";
    private static final String VOIE2 = "voie2";
    private static final String VOIE = "voie";

    private static City city() {
        City c_ = new City();
        c_.setSavedlinks(new PlaceInterConnects());
        c_.setBuildings(new PointsBuilding());
        LevelOutdoor city_ = new LevelOutdoor();
        city_.setBlocks(new PointsBlock());
        Block block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(0,0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(0,3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(0,6), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(3,0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.NOTHING, VOIE);
        city_.getBlocks().put(newPoint(3,3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(3,6), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(6,0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(6,3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(6,6), block_);
        c_.setLevel(city_);
        Gym gym_ = new Gym();
        gym_.setExitCity(newPoint(1,1));
        gym_.setLevel(new LevelIndoorGym());
        gym_.getIndoor().setGymLeaderCoords(newPoint(1,1));
        c_.getBuildings().put(newPoint(4,5), gym_);
        return c_;
    }
    private static Road hroad() {
        Road road_ = new Road();
        road_.setSavedlinks(new PlaceInterConnects());
        LevelRoad level_ = new LevelRoad();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block((short)6,(short)3, EnvironmentType.ROAD, VOIE2);
        level_.getBlocks().put(newPoint(0,0), block_);
        road_.setLevel(level_);
        return road_;
    }
    private static Road vroad() {
        Road road_ = new Road();
        road_.setSavedlinks(new PlaceInterConnects());
        LevelRoad level_ = new LevelRoad();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block((short)3,(short)6, EnvironmentType.ROAD, VOIE3);
        level_.getBlocks().put(newPoint(0,0), block_);
        road_.setLevel(level_);
        return road_;
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
        coordsAccessLeague_.setNumberPlace(8);
        coordsAccessLeague_.setLevel(new LevelPoint());
        coordsAccessLeague_.getLevel().setLevelIndex(0);
        coordsAccessLeague_.getLevel().setPoint(newPoint(5,1));
        dataMap_.getPlaces().add(roadSix_);
        dataMap_.getPlaces().add(cityFive_);
        dataMap_.join((short)0, (short)1, newPoint(4,0), newPoint(1,5), Direction.UP);
        dataMap_.join((short)7, (short)0, newPoint(0,1), newPoint(8,4), Direction.LEFT);
        return dataMap_;
    }

    @Test
    public void intersectWithScreen1Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setScreenHeight(3);
        dataMap_.setScreenWidth(3);
        dataMap_.setSpaceBetweenLeftAndHeros(1);
        dataMap_.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace(0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(0);
        begin_.getLevel().setPoint(newPoint(1,1));
        ScreenCoordssCoords intersect_ = dataMap_.intersectWithScreen(begin_);
        assertEq(9, intersect_.size());
        assertTrue(intersect_.contains(new ScreenCoords(0,0)));
        assertTrue(intersect_.contains(new ScreenCoords(0,1)));
        assertTrue(intersect_.contains(new ScreenCoords(0,2)));
        assertTrue(intersect_.contains(new ScreenCoords(1,0)));
        assertTrue(intersect_.contains(new ScreenCoords(1,1)));
        assertTrue(intersect_.contains(new ScreenCoords(1,2)));
        assertTrue(intersect_.contains(new ScreenCoords(2,0)));
        assertTrue(intersect_.contains(new ScreenCoords(2,1)));
        assertTrue(intersect_.contains(new ScreenCoords(2,2)));
        assertEq(0, intersect_.getVal(new ScreenCoords(0,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,2)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getLevel().getLevelIndex());
        assertEq(newPoint(0,0), intersect_.getVal(new ScreenCoords(0,0)).getLevel().getPoint());
        assertEq(newPoint(0,1), intersect_.getVal(new ScreenCoords(0,1)).getLevel().getPoint());
        assertEq(newPoint(0,2), intersect_.getVal(new ScreenCoords(0,2)).getLevel().getPoint());
        assertEq(newPoint(1,0), intersect_.getVal(new ScreenCoords(1,0)).getLevel().getPoint());
        assertEq(newPoint(1,1), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(newPoint(1,2), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(newPoint(2,0), intersect_.getVal(new ScreenCoords(2,0)).getLevel().getPoint());
        assertEq(newPoint(2,1), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(newPoint(2,2), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
        assertEq(3,dataMap_.getScreenHeight());
        assertEq(3,dataMap_.getScreenWidth());
    }

    @Test
    public void intersectWithScreen2Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setScreenHeight(3);
        dataMap_.setScreenWidth(3);
        dataMap_.setSpaceBetweenLeftAndHeros(1);
        dataMap_.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace(0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(0);
        begin_.getLevel().setPoint(newPoint(0,0));
        ScreenCoordssCoords intersect_ = dataMap_.intersectWithScreen(begin_);
        assertEq(9, intersect_.size());
        assertTrue(intersect_.contains(new ScreenCoords(0,0)));
        assertTrue(intersect_.contains(new ScreenCoords(0,1)));
        assertTrue(intersect_.contains(new ScreenCoords(0,2)));
        assertTrue(intersect_.contains(new ScreenCoords(1,0)));
        assertTrue(intersect_.contains(new ScreenCoords(1,1)));
        assertTrue(intersect_.contains(new ScreenCoords(1,2)));
        assertTrue(intersect_.contains(new ScreenCoords(2,0)));
        assertTrue(intersect_.contains(new ScreenCoords(2,1)));
        assertTrue(intersect_.contains(new ScreenCoords(2,2)));
        assertTrue(!intersect_.getVal(new ScreenCoords(0,0)).isValid());
        assertTrue(!intersect_.getVal(new ScreenCoords(0,1)).isValid());
        assertTrue(!intersect_.getVal(new ScreenCoords(0,2)).isValid());
        assertTrue(!intersect_.getVal(new ScreenCoords(1,0)).isValid());
        assertTrue(!intersect_.getVal(new ScreenCoords(2,0)).isValid());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getLevel().getLevelIndex());
        assertEq(newPoint(0,0), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(newPoint(0,1), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(newPoint(1,0), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(newPoint(1,1), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
    }

    @Test
    public void intersectWithScreen3Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setScreenHeight(3);
        dataMap_.setScreenWidth(3);
        dataMap_.setSpaceBetweenLeftAndHeros(1);
        dataMap_.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace(0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(0);
        begin_.getLevel().setPoint(newPoint(4,0));
        ScreenCoordssCoords intersect_ = dataMap_.intersectWithScreen(begin_);
        assertEq(9, intersect_.size());
        assertTrue(intersect_.contains(new ScreenCoords(0,0)));
        assertTrue(intersect_.contains(new ScreenCoords(0,1)));
        assertTrue(intersect_.contains(new ScreenCoords(0,2)));
        assertTrue(intersect_.contains(new ScreenCoords(1,0)));
        assertTrue(intersect_.contains(new ScreenCoords(1,1)));
        assertTrue(intersect_.contains(new ScreenCoords(1,2)));
        assertTrue(intersect_.contains(new ScreenCoords(2,0)));
        assertTrue(intersect_.contains(new ScreenCoords(2,1)));
        assertTrue(intersect_.contains(new ScreenCoords(2,2)));
        assertEq(1, intersect_.getVal(new ScreenCoords(0,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,2)).getNumberPlace());
        assertEq(1, intersect_.getVal(new ScreenCoords(1,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getNumberPlace());
        assertEq(1, intersect_.getVal(new ScreenCoords(2,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,2)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getLevel().getLevelIndex());
        assertEq(newPoint(0,5), intersect_.getVal(new ScreenCoords(0,0)).getLevel().getPoint());
        assertEq(newPoint(3,0), intersect_.getVal(new ScreenCoords(0,1)).getLevel().getPoint());
        assertEq(newPoint(3,1), intersect_.getVal(new ScreenCoords(0,2)).getLevel().getPoint());
        assertEq(newPoint(1,5), intersect_.getVal(new ScreenCoords(1,0)).getLevel().getPoint());
        assertEq(newPoint(4,0), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(newPoint(4,1), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(newPoint(2,5), intersect_.getVal(new ScreenCoords(2,0)).getLevel().getPoint());
        assertEq(newPoint(5,0), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(newPoint(5,1), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
    }
    @Test
    public void moveCamera1Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setScreenHeight(3);
        dataMap_.setScreenWidth(3);
        dataMap_.setSpaceBetweenLeftAndHeros(1);
        dataMap_.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace(0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(0);
        begin_.getLevel().setPoint(newPoint(1,1));
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.moveCamera(Direction.RIGHT);
        ScreenCoordssCoords intersect_ = dataMap_.getTiles();
        assertEq(9, intersect_.size());
        assertTrue(intersect_.contains(new ScreenCoords(0,0)));
        assertTrue(intersect_.contains(new ScreenCoords(0,1)));
        assertTrue(intersect_.contains(new ScreenCoords(0,2)));
        assertTrue(intersect_.contains(new ScreenCoords(1,0)));
        assertTrue(intersect_.contains(new ScreenCoords(1,1)));
        assertTrue(intersect_.contains(new ScreenCoords(1,2)));
        assertTrue(intersect_.contains(new ScreenCoords(2,0)));
        assertTrue(intersect_.contains(new ScreenCoords(2,1)));
        assertTrue(intersect_.contains(new ScreenCoords(2,2)));
        assertEq(0, intersect_.getVal(new ScreenCoords(0,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,2)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getLevel().getLevelIndex());
        assertEq(newPoint(1,0), intersect_.getVal(new ScreenCoords(0,0)).getLevel().getPoint());
        assertEq(newPoint(1,1), intersect_.getVal(new ScreenCoords(0,1)).getLevel().getPoint());
        assertEq(newPoint(1,2), intersect_.getVal(new ScreenCoords(0,2)).getLevel().getPoint());
        assertEq(newPoint(2,0), intersect_.getVal(new ScreenCoords(1,0)).getLevel().getPoint());
        assertEq(newPoint(2,1), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(newPoint(2,2), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(newPoint(3,0), intersect_.getVal(new ScreenCoords(2,0)).getLevel().getPoint());
        assertEq(newPoint(3,1), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(newPoint(3,2), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
    }

    @Test
    public void moveCamera2Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setScreenHeight(3);
        dataMap_.setScreenWidth(3);
        dataMap_.setSpaceBetweenLeftAndHeros(1);
        dataMap_.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace(0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(0);
        begin_.getLevel().setPoint(newPoint(1,1));
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.moveCamera(Direction.LEFT);
        ScreenCoordssCoords intersect_ = dataMap_.getTiles();
        assertEq(9, intersect_.size());
        assertTrue(intersect_.contains(new ScreenCoords(0,0)));
        assertTrue(intersect_.contains(new ScreenCoords(0,1)));
        assertTrue(intersect_.contains(new ScreenCoords(0,2)));
        assertTrue(intersect_.contains(new ScreenCoords(1,0)));
        assertTrue(intersect_.contains(new ScreenCoords(1,1)));
        assertTrue(intersect_.contains(new ScreenCoords(1,2)));
        assertTrue(intersect_.contains(new ScreenCoords(2,0)));
        assertTrue(intersect_.contains(new ScreenCoords(2,1)));
        assertTrue(intersect_.contains(new ScreenCoords(2,2)));
        assertTrue(!intersect_.getVal(new ScreenCoords(0,0)).isValid());
        assertTrue(!intersect_.getVal(new ScreenCoords(0,1)).isValid());
        assertTrue(!intersect_.getVal(new ScreenCoords(0,2)).isValid());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getLevel().getLevelIndex());
        assertEq(newPoint(0,0), intersect_.getVal(new ScreenCoords(1,0)).getLevel().getPoint());
        assertEq(newPoint(0,1), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(newPoint(0,2), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(newPoint(1,0), intersect_.getVal(new ScreenCoords(2,0)).getLevel().getPoint());
        assertEq(newPoint(1,1), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(newPoint(1,2), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
    }

    @Test
    public void moveCamera3Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setScreenHeight(3);
        dataMap_.setScreenWidth(3);
        dataMap_.setSpaceBetweenLeftAndHeros(1);
        dataMap_.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace(0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(0);
        begin_.getLevel().setPoint(newPoint(0,0));
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.moveCamera(Direction.DOWN);
        ScreenCoordssCoords intersect_ = dataMap_.getTiles();
        assertEq(9, intersect_.size());
        assertTrue(intersect_.contains(new ScreenCoords(0,0)));
        assertTrue(intersect_.contains(new ScreenCoords(0,1)));
        assertTrue(intersect_.contains(new ScreenCoords(0,2)));
        assertTrue(intersect_.contains(new ScreenCoords(1,0)));
        assertTrue(intersect_.contains(new ScreenCoords(1,1)));
        assertTrue(intersect_.contains(new ScreenCoords(1,2)));
        assertTrue(intersect_.contains(new ScreenCoords(2,0)));
        assertTrue(intersect_.contains(new ScreenCoords(2,1)));
        assertTrue(intersect_.contains(new ScreenCoords(2,2)));
        assertTrue(!intersect_.getVal(new ScreenCoords(0,0)).isValid());
        assertTrue(!intersect_.getVal(new ScreenCoords(0,1)).isValid());
        assertTrue(!intersect_.getVal(new ScreenCoords(0,2)).isValid());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getLevel().getLevelIndex());
        assertEq(newPoint(0,0), intersect_.getVal(new ScreenCoords(1,0)).getLevel().getPoint());
        assertEq(newPoint(0,1), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(newPoint(0,2), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(newPoint(1,0), intersect_.getVal(new ScreenCoords(2,0)).getLevel().getPoint());
        assertEq(newPoint(1,1), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(newPoint(1,2), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
    }

    @Test
    public void moveCamera4Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setScreenHeight(3);
        dataMap_.setScreenWidth(3);
        dataMap_.setSpaceBetweenLeftAndHeros(1);
        dataMap_.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace(0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(0);
        begin_.getLevel().setPoint(newPoint(4,1));
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.moveCamera(Direction.UP);
        ScreenCoordssCoords intersect_ = dataMap_.getTiles();
        assertEq(9, intersect_.size());
        assertTrue(intersect_.contains(new ScreenCoords(0,0)));
        assertTrue(intersect_.contains(new ScreenCoords(0,1)));
        assertTrue(intersect_.contains(new ScreenCoords(0,2)));
        assertTrue(intersect_.contains(new ScreenCoords(1,0)));
        assertTrue(intersect_.contains(new ScreenCoords(1,1)));
        assertTrue(intersect_.contains(new ScreenCoords(1,2)));
        assertTrue(intersect_.contains(new ScreenCoords(2,0)));
        assertTrue(intersect_.contains(new ScreenCoords(2,1)));
        assertTrue(intersect_.contains(new ScreenCoords(2,2)));
        assertEq(1, intersect_.getVal(new ScreenCoords(0,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,2)).getNumberPlace());
        assertEq(1, intersect_.getVal(new ScreenCoords(1,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getNumberPlace());
        assertEq(1, intersect_.getVal(new ScreenCoords(2,0)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getNumberPlace());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(0,2)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(1,2)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,0)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,1)).getLevel().getLevelIndex());
        assertEq(0, intersect_.getVal(new ScreenCoords(2,2)).getLevel().getLevelIndex());
        assertEq(newPoint(0,5), intersect_.getVal(new ScreenCoords(0,0)).getLevel().getPoint());
        assertEq(newPoint(3,0), intersect_.getVal(new ScreenCoords(0,1)).getLevel().getPoint());
        assertEq(newPoint(3,1), intersect_.getVal(new ScreenCoords(0,2)).getLevel().getPoint());
        assertEq(newPoint(1,5), intersect_.getVal(new ScreenCoords(1,0)).getLevel().getPoint());
        assertEq(newPoint(4,0), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(newPoint(4,1), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(newPoint(2,5), intersect_.getVal(new ScreenCoords(2,0)).getLevel().getPoint());
        assertEq(newPoint(5,0), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(newPoint(5,1), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
    }

    @Test
    public void calculateBackgroundImagesFromTiles1Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        DataBase data_ = newData();
        StringMap<ImageArrayBaseSixtyFour> images_ = imgages();
        data_.getImages().putAllMap(images_);
        data_.setMap(dataMap_);
        data_.setupPseudoImages();
        Coords begin_ = new Coords();
        begin_.setNumberPlace(0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(0);
        begin_.getLevel().setPoint(newPoint(1,1));
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.calculateBackgroundImagesFromTiles(data_);
        ScreenCoordssInt backGroundImages_ = dataMap_.getBackgroundImages();
        assertEq(45, backGroundImages_.size());
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,8)));
    }

    private static DataBase newData() {
        return new DataBase(DefaultGenerator.oneElt());
    }

    @Test
    public void calculateBackgroundImagesFromTiles2Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        DataBase data_ = newData();
        StringMap<ImageArrayBaseSixtyFour> images_ = imgages();
        Coords begin_ = new Coords();
        begin_.setNumberPlace(0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(0);
        begin_.getLevel().setPoint(newPoint(1,0));
        data_.getImages().putAllMap(images_);
        data_.setMap(dataMap_);
        data_.setupPseudoImages();
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.calculateBackgroundImagesFromTiles(data_);
        ScreenCoordssInt backGroundImages_ = dataMap_.getBackgroundImages();
        assertEq(42, backGroundImages_.size());
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,8)));
    }

    @Test
    public void calculateBackgroundImagesFromTiles3Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        DataBase data_ = newData();
        StringMap<ImageArrayBaseSixtyFour> images_ = imgages();
        Coords begin_ = new Coords();
        begin_.setNumberPlace(0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(0);
        begin_.getLevel().setPoint(newPoint(0,1));
        data_.getImages().putAllMap(images_);
        data_.setMap(dataMap_);
        data_.setupPseudoImages();
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.calculateBackgroundImagesFromTiles(data_);
        ScreenCoordssInt backGroundImages_ = dataMap_.getBackgroundImages();
        assertEq(36, backGroundImages_.size());
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,8)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,5)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,6)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,7)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,8)));
    }

    @Test
    public void calculateBackgroundImagesFromTiles4Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        DataBase data_ = newData();
        StringMap<ImageArrayBaseSixtyFour> images_ = imgages();
        Coords begin_ = new Coords();
        begin_.setNumberPlace(0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(0);
        begin_.getLevel().setPoint(newPoint(8,8));
        data_.getImages().putAllMap(images_);
        data_.setMap(dataMap_);
        data_.setupPseudoImages();
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.calculateBackgroundImagesFromTiles(data_);
        ScreenCoordssInt backGroundImages_ = dataMap_.getBackgroundImages();
        assertEq(33, backGroundImages_.size());
        assertTrue(backGroundImages_.contains(new ScreenCoords(0,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(0,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(0,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(0,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(0,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(1,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(1,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(1,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(1,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(1,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(2,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(2,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(2,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(2,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(2,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(3,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,2)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,3)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(4,4)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(5,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(6,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(7,1)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,0)));
        assertTrue(backGroundImages_.contains(new ScreenCoords(8,1)));
    }

    private StringMap<ImageArrayBaseSixtyFour> imgages() {
        StringMap<ImageArrayBaseSixtyFour> images_ = new StringMap<ImageArrayBaseSixtyFour>();
        images_.put(VOIE, instance(new int[][]{shortRow1(), shortRow2(), shortRow1(), shortRow2(), shortRow1(), shortRow2()}));
        images_.put(VOIE2, instance(new int[][]{longRow(), longRow(), longRow(), longRow(), longRow(), longRow()}));
        images_.put(VOIE3, instance(new int[][]{shortRow1(), shortRow2(), shortRow1(), shortRow2(), shortRow1(), shortRow2(), shortRow1(), shortRow2(), shortRow1(), shortRow2(), shortRow1(), shortRow2()}));
        return images_;
    }

    private int[] longRow() {
        return new int[]{3, 4, 5, 6, 3, 4, 5, 6, 3, 4, 5, 6};
    }

    private int[] shortRow2() {
        return new int[]{5, 6, 3, 4, 5, 6};
    }

    private int[] shortRow1() {
        return new int[]{3, 4, 5, 6, 3, 4};
    }
}
