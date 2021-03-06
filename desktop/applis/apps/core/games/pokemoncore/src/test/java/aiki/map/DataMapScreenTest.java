package aiki.map;

import aiki.db.EquallablePkUtil;
import aiki.game.fight.Image;
import aiki.map.util.PlaceInterConnects;
import aiki.util.*;
import code.maths.montecarlo.DefaultGenerator;
import code.util.core.StringUtil;
import org.junit.Test;

import aiki.db.DataBase;
import aiki.map.buildings.Building;
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
import aiki.map.util.PlaceInterConnect;
import aiki.map.util.ScreenCoords;
import code.util.*;
import code.util.ObjectMap;
import code.util.StringList;
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
        Block block_ = new Block((short)6,(short)3, EnvironmentType.ROAD, VOIE2);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        road_.setLevel(level_);
        return road_;
    }
    private static Road vroad() {
        Road road_ = new Road();
        road_.setSavedlinks(new PlaceInterConnects());
        LevelRoad level_ = new LevelRoad();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block((short)3,(short)6, EnvironmentType.ROAD, VOIE3);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
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
        coordsAccessLeague_.setNumberPlace((short) 8);
        coordsAccessLeague_.setLevel(new LevelPoint());
        coordsAccessLeague_.getLevel().setLevelIndex((byte) 0);
        coordsAccessLeague_.getLevel().setPoint(new Point((short)5,(short)1));
        dataMap_.getPlaces().add(roadSix_);
        dataMap_.getPlaces().add(cityFive_);
        dataMap_.join((short)0, (short)1, new Point((short)4,(short)0), new Point((short)1,(short)5), Direction.UP);
        dataMap_.join((short)7, (short)0, new Point((short)0,(short)1), new Point((short)8,(short)4), Direction.LEFT);
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
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)1,(short)1));
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap_.intersectWithScreen(begin_);
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
        assertEq(new Point((short)0,(short)0), intersect_.getVal(new ScreenCoords(0,0)).getLevel().getPoint());
        assertEq(new Point((short)0,(short)1), intersect_.getVal(new ScreenCoords(0,1)).getLevel().getPoint());
        assertEq(new Point((short)0,(short)2), intersect_.getVal(new ScreenCoords(0,2)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)0), intersect_.getVal(new ScreenCoords(1,0)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)1), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)2), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(new Point((short)2,(short)0), intersect_.getVal(new ScreenCoords(2,0)).getLevel().getPoint());
        assertEq(new Point((short)2,(short)1), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(new Point((short)2,(short)2), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
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
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)0,(short)0));
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap_.intersectWithScreen(begin_);
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
        assertEq(new Point((short)0,(short)0), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(new Point((short)0,(short)1), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)0), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)1), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
    }

    @Test
    public void intersectWithScreen3Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setScreenHeight(3);
        dataMap_.setScreenWidth(3);
        dataMap_.setSpaceBetweenLeftAndHeros(1);
        dataMap_.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)4,(short)0));
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap_.intersectWithScreen(begin_);
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
        assertEq(new Point((short)0,(short)5), intersect_.getVal(new ScreenCoords(0,0)).getLevel().getPoint());
        assertEq(new Point((short)3,(short)0), intersect_.getVal(new ScreenCoords(0,1)).getLevel().getPoint());
        assertEq(new Point((short)3,(short)1), intersect_.getVal(new ScreenCoords(0,2)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)5), intersect_.getVal(new ScreenCoords(1,0)).getLevel().getPoint());
        assertEq(new Point((short)4,(short)0), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(new Point((short)4,(short)1), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(new Point((short)2,(short)5), intersect_.getVal(new ScreenCoords(2,0)).getLevel().getPoint());
        assertEq(new Point((short)5,(short)0), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(new Point((short)5,(short)1), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
    }
    @Test
    public void moveCamera1Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setScreenHeight(3);
        dataMap_.setScreenWidth(3);
        dataMap_.setSpaceBetweenLeftAndHeros(1);
        dataMap_.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)1,(short)1));
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.moveCamera(Direction.RIGHT);
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap_.getTiles();
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
        assertEq(new Point((short)1,(short)0), intersect_.getVal(new ScreenCoords(0,0)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)1), intersect_.getVal(new ScreenCoords(0,1)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)2), intersect_.getVal(new ScreenCoords(0,2)).getLevel().getPoint());
        assertEq(new Point((short)2,(short)0), intersect_.getVal(new ScreenCoords(1,0)).getLevel().getPoint());
        assertEq(new Point((short)2,(short)1), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(new Point((short)2,(short)2), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(new Point((short)3,(short)0), intersect_.getVal(new ScreenCoords(2,0)).getLevel().getPoint());
        assertEq(new Point((short)3,(short)1), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(new Point((short)3,(short)2), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
    }

    @Test
    public void moveCamera2Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setScreenHeight(3);
        dataMap_.setScreenWidth(3);
        dataMap_.setSpaceBetweenLeftAndHeros(1);
        dataMap_.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)1,(short)1));
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.moveCamera(Direction.LEFT);
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap_.getTiles();
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
        assertEq(new Point((short)0,(short)0), intersect_.getVal(new ScreenCoords(1,0)).getLevel().getPoint());
        assertEq(new Point((short)0,(short)1), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(new Point((short)0,(short)2), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)0), intersect_.getVal(new ScreenCoords(2,0)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)1), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)2), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
    }

    @Test
    public void moveCamera3Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setScreenHeight(3);
        dataMap_.setScreenWidth(3);
        dataMap_.setSpaceBetweenLeftAndHeros(1);
        dataMap_.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)0,(short)0));
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.moveCamera(Direction.DOWN);
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap_.getTiles();
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
        assertEq(new Point((short)0,(short)0), intersect_.getVal(new ScreenCoords(1,0)).getLevel().getPoint());
        assertEq(new Point((short)0,(short)1), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(new Point((short)0,(short)2), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)0), intersect_.getVal(new ScreenCoords(2,0)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)1), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)2), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
    }

    @Test
    public void moveCamera4Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setScreenHeight(3);
        dataMap_.setScreenWidth(3);
        dataMap_.setSpaceBetweenLeftAndHeros(1);
        dataMap_.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)4,(short)1));
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.moveCamera(Direction.UP);
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap_.getTiles();
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
        assertEq(new Point((short)0,(short)5), intersect_.getVal(new ScreenCoords(0,0)).getLevel().getPoint());
        assertEq(new Point((short)3,(short)0), intersect_.getVal(new ScreenCoords(0,1)).getLevel().getPoint());
        assertEq(new Point((short)3,(short)1), intersect_.getVal(new ScreenCoords(0,2)).getLevel().getPoint());
        assertEq(new Point((short)1,(short)5), intersect_.getVal(new ScreenCoords(1,0)).getLevel().getPoint());
        assertEq(new Point((short)4,(short)0), intersect_.getVal(new ScreenCoords(1,1)).getLevel().getPoint());
        assertEq(new Point((short)4,(short)1), intersect_.getVal(new ScreenCoords(1,2)).getLevel().getPoint());
        assertEq(new Point((short)2,(short)5), intersect_.getVal(new ScreenCoords(2,0)).getLevel().getPoint());
        assertEq(new Point((short)5,(short)0), intersect_.getVal(new ScreenCoords(2,1)).getLevel().getPoint());
        assertEq(new Point((short)5,(short)1), intersect_.getVal(new ScreenCoords(2,2)).getLevel().getPoint());
    }

    @Test
    public void calculateBackgroundImagesFromTiles1Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        DataBase data_ = newData();
        StringMap<int[][]> images_ = new StringMap<int[][]>();
        StringList list_ = new StringList("3","4","5","6");
        StringList voie_ = new StringList("6");
        for (int i = 0; i < 9; i++) {
            voie_.addAllElts(list_);
        }
        images_.put(VOIE, getImageByString(StringUtil.join(voie_, Image.SEPARATOR_CHAR)));
        StringList voieTwo_ = new StringList("12");
        for (int i = 0; i < 18; i++) {
            voieTwo_.addAllElts(list_);
        }
        images_.put(VOIE2, getImageByString(StringUtil.join(voieTwo_, Image.SEPARATOR_CHAR)));
        StringList voieThree_ = new StringList("6");
        for (int i = 0; i < 18; i++) {
            voieThree_.addAllElts(list_);
        }
        images_.put(VOIE3, getImageByString(StringUtil.join(voieThree_, Image.SEPARATOR_CHAR)));
        data_.getImages().putAllMap(images_);
        data_.setMap(dataMap_);
        data_.setupPseudoImages();
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)1,(short)1));
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.calculateBackgroundImagesFromTiles(data_);
        ObjectMap<ScreenCoords, int[][]> backGroundImages_ = dataMap_.getBackgroundImages();
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
        return new DataBase(new DefaultGenerator());
    }

    @Test
    public void calculateBackgroundImagesFromTiles2Test() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        DataBase data_ = newData();
        StringMap<int[][]> images_ = new StringMap<int[][]>();
        StringList list_ = new StringList("3","4","5","6");
        StringList voie_ = new StringList("6");
        for (int i = 0; i < 9; i++) {
            voie_.addAllElts(list_);
        }
        images_.put(VOIE, getImageByString(StringUtil.join(voie_, Image.SEPARATOR_CHAR)));
        StringList voieTwo_ = new StringList("12");
        for (int i = 0; i < 18; i++) {
            voieTwo_.addAllElts(list_);
        }
        images_.put(VOIE2, getImageByString(StringUtil.join(voieTwo_, Image.SEPARATOR_CHAR)));
        StringList voieThree_ = new StringList("6");
        for (int i = 0; i < 18; i++) {
            voieThree_.addAllElts(list_);
        }
        images_.put(VOIE3, getImageByString(StringUtil.join(voieThree_, Image.SEPARATOR_CHAR)));
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)1,(short)0));
        data_.getImages().putAllMap(images_);
        data_.setMap(dataMap_);
        data_.setupPseudoImages();
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.calculateBackgroundImagesFromTiles(data_);
        ObjectMap<ScreenCoords, int[][]> backGroundImages_ = dataMap_.getBackgroundImages();
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
        StringMap<int[][]> images_ = new StringMap<int[][]>();
        StringList list_ = new StringList("3","4","5","6");
        StringList voie_ = new StringList("6");
        for (int i = 0; i < 9; i++) {
            voie_.addAllElts(list_);
        }
        images_.put(VOIE, getImageByString(StringUtil.join(voie_, Image.SEPARATOR_CHAR)));
        StringList voieTwo_ = new StringList("12");
        for (int i = 0; i < 18; i++) {
            voieTwo_.addAllElts(list_);
        }
        images_.put(VOIE2, getImageByString(StringUtil.join(voieTwo_, Image.SEPARATOR_CHAR)));
        StringList voieThree_ = new StringList("6");
        for (int i = 0; i < 18; i++) {
            voieThree_.addAllElts(list_);
        }
        images_.put(VOIE3, getImageByString(StringUtil.join(voieThree_, Image.SEPARATOR_CHAR)));
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)0,(short)1));
        data_.getImages().putAllMap(images_);
        data_.setMap(dataMap_);
        data_.setupPseudoImages();
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.calculateBackgroundImagesFromTiles(data_);
        ObjectMap<ScreenCoords, int[][]> backGroundImages_ = dataMap_.getBackgroundImages();
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
        StringMap<int[][]> images_ = new StringMap<int[][]>();
        StringList list_ = new StringList("3","4","5","6");
        StringList voie_ = new StringList("6");
        for (int i = 0; i < 9; i++) {
            voie_.addAllElts(list_);
        }
        images_.put(VOIE, getImageByString(StringUtil.join(voie_, Image.SEPARATOR_CHAR)));
        StringList voieTwo_ = new StringList("12");
        for (int i = 0; i < 18; i++) {
            voieTwo_.addAllElts(list_);
        }
        images_.put(VOIE2, getImageByString(StringUtil.join(voieTwo_, Image.SEPARATOR_CHAR)));
        StringList voieThree_ = new StringList("6");
        for (int i = 0; i < 18; i++) {
            voieThree_.addAllElts(list_);
        }
        images_.put(VOIE3, getImageByString(StringUtil.join(voieThree_, Image.SEPARATOR_CHAR)));
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)8,(short)8));
        data_.getImages().putAllMap(images_);
        data_.setMap(dataMap_);
        data_.setupPseudoImages();
        dataMap_.calculateIntersectWithScreen(begin_);
        dataMap_.calculateBackgroundImagesFromTiles(data_);
        ObjectMap<ScreenCoords, int[][]> backGroundImages_ = dataMap_.getBackgroundImages();
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

    private static int[][] getImageByString(String _string) {
        Image i_ = new Image(_string);
        Ints pixels_ = i_.getPixels();
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
