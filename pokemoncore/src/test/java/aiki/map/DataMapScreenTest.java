package aiki.map;
import static aiki.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.images.ConverterBufferedImage;
import code.images.Image;
import code.util.NumberMap;
import code.util.ObjectMap;
import code.util.PairNumber;
import code.util.StringList;
import code.util.StringMap;

public class DataMapScreenTest {

    private static final String VOIE3 = "voie3";
    private static final String VOIE2 = "voie2";
    private static final String VOIE = "voie";
    private DataMap dataMap;

    private static City city() {
        City c_ = new City();
        c_.setSavedlinks(new ObjectMap<PlaceInterConnect,Coords>());
        c_.setBuildings(new ObjectMap<Point,Building>());
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
        Gym gym_ = new Gym();
        gym_.setExitCity(new Point((short)1,(short)1));
        gym_.setLevel(new LevelIndoorGym());
        gym_.getLevel().setGymLeaderCoords(new Point((short)1,(short)1));
        c_.getBuildings().put(new Point((short)4,(short)5), gym_);
        return c_;
    }
    private static Road hroad() {
        Road road_ = new Road();
        road_.setSavedlinks(new ObjectMap<PlaceInterConnect,Coords>());
        LevelRoad level_ = new LevelRoad();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)6,(short)3, EnvironmentType.ROAD, VOIE2);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        road_.setLevel(level_);
        return road_;
    }
    private static Road vroad() {
        Road road_ = new Road();
        road_.setSavedlinks(new ObjectMap<PlaceInterConnect,Coords>());
        LevelRoad level_ = new LevelRoad();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)3,(short)6, EnvironmentType.ROAD, VOIE3);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        road_.setLevel(level_);
        return road_;
    }

    @Before
    public void initDataMap() {
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
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),roadSix_);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(),cityFive_);
        dataMap_.join((short)0, (short)1, new Point((short)4,(short)0), new Point((short)1,(short)5), Direction.UP);
        dataMap_.join((short)7, (short)0, new Point((short)0,(short)1), new Point((short)8,(short)4), Direction.LEFT);
        dataMap = dataMap_;
    }

    @Test
    public void intersectWithScreen1Test() {
        dataMap.setScreenHeight(3);
        dataMap.setScreenWidth(3);
        dataMap.setSpaceBetweenLeftAndHeros(1);
        dataMap.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)1,(short)1));
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap.intersectWithScreen(begin_);
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
    }

    @Test
    public void intersectWithScreen2Test() {
        dataMap.setScreenHeight(3);
        dataMap.setScreenWidth(3);
        dataMap.setSpaceBetweenLeftAndHeros(1);
        dataMap.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)0,(short)0));
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap.intersectWithScreen(begin_);
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
        dataMap.setScreenHeight(3);
        dataMap.setScreenWidth(3);
        dataMap.setSpaceBetweenLeftAndHeros(1);
        dataMap.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)4,(short)0));
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap.intersectWithScreen(begin_);
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
        dataMap.setScreenHeight(3);
        dataMap.setScreenWidth(3);
        dataMap.setSpaceBetweenLeftAndHeros(1);
        dataMap.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)1,(short)1));
        dataMap.calculateIntersectWithScreen(begin_);
        dataMap.moveCamera(Direction.RIGHT);
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap.getTiles();
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
        dataMap.setScreenHeight(3);
        dataMap.setScreenWidth(3);
        dataMap.setSpaceBetweenLeftAndHeros(1);
        dataMap.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)1,(short)1));
        dataMap.calculateIntersectWithScreen(begin_);
        dataMap.moveCamera(Direction.LEFT);
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap.getTiles();
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
        dataMap.setScreenHeight(3);
        dataMap.setScreenWidth(3);
        dataMap.setSpaceBetweenLeftAndHeros(1);
        dataMap.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)0,(short)0));
        dataMap.calculateIntersectWithScreen(begin_);
        dataMap.moveCamera(Direction.DOWN);
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap.getTiles();
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
        dataMap.setScreenHeight(3);
        dataMap.setScreenWidth(3);
        dataMap.setSpaceBetweenLeftAndHeros(1);
        dataMap.setSpaceBetweenTopAndHeros(1);
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)4,(short)1));
        dataMap.calculateIntersectWithScreen(begin_);
        dataMap.moveCamera(Direction.UP);
        ObjectMap<ScreenCoords, Coords> intersect_ = dataMap.getTiles();
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

    @Ignore
    @Test
    public void calculateBackgroundImagesFromTiles1Test() {
        dataMap.setSideLength(2);
        StringMap<String> images_ = new StringMap<String>();
        StringList list_ = new StringList("3","4","5","6");
        StringList voie_ = new StringList("6");
        for (int i = 0; i < 9; i++) {
            voie_.addAllElts(list_);
        }
        images_.put(VOIE, ConverterBufferedImage.toBaseSixtyFourQuick(voie_.join(Image.SEPARATOR_CHAR)));
        StringList voieTwo_ = new StringList("12");
        for (int i = 0; i < 18; i++) {
            voieTwo_.addAllElts(list_);
        }
        images_.put(VOIE2, ConverterBufferedImage.toBaseSixtyFourQuick(voieTwo_.join(Image.SEPARATOR_CHAR)));
        StringList voieThree_ = new StringList("6");
        for (int i = 0; i < 18; i++) {
            voieThree_.addAllElts(list_);
        }
        images_.put(VOIE3, ConverterBufferedImage.toBaseSixtyFourQuick(voieThree_.join(Image.SEPARATOR_CHAR)));
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)1,(short)1));
        dataMap.calculateIntersectWithScreen(begin_);
        dataMap.calculateBackgroundImagesFromTiles(null, 0, 0);
        ObjectMap<ScreenCoords, String> backGroundImages_ = dataMap.getBackgroundImages();
        assertEq(5, backGroundImages_.size());
        PairNumber<Integer,Integer> dims_;
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(6,0)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(3,3)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(6,3)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(3,6)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(6,6)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
    }

    @Ignore
    @Test
    public void calculateBackgroundImagesFromTiles2Test() {
        dataMap.setSideLength(2);
        StringMap<String> images_ = new StringMap<String>();
        StringList list_ = new StringList("3","4","5","6");
        StringList voie_ = new StringList("6");
        for (int i = 0; i < 9; i++) {
            voie_.addAllElts(list_);
        }
        images_.put(VOIE, ConverterBufferedImage.toBaseSixtyFourQuick(voie_.join(Image.SEPARATOR_CHAR)));
        StringList voieTwo_ = new StringList("12");
        for (int i = 0; i < 18; i++) {
            voieTwo_.addAllElts(list_);
        }
        images_.put(VOIE2, ConverterBufferedImage.toBaseSixtyFourQuick(voieTwo_.join(Image.SEPARATOR_CHAR)));
        StringList voieThree_ = new StringList("6");
        for (int i = 0; i < 18; i++) {
            voieThree_.addAllElts(list_);
        }
        images_.put(VOIE3, ConverterBufferedImage.toBaseSixtyFourQuick(voieThree_.join(Image.SEPARATOR_CHAR)));
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)1,(short)0));
        dataMap.calculateIntersectWithScreen(begin_);
        dataMap.calculateBackgroundImagesFromTiles(null, 0, 0);
        ObjectMap<ScreenCoords, String> backGroundImages_ = dataMap.getBackgroundImages();
        assertEq(5, backGroundImages_.size());
        PairNumber<Integer,Integer> dims_;
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(6,0)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(8, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(3,4)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(6,4)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(3,7)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(4, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(6,7)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(4, dims_.getSecond().intValue());
    }

    @Ignore
    @Test
    public void calculateBackgroundImagesFromTiles3Test() {
        dataMap.setSideLength(2);
        StringMap<String> images_ = new StringMap<String>();
        StringList list_ = new StringList("3","4","5","6");
        StringList voie_ = new StringList("6");
        for (int i = 0; i < 9; i++) {
            voie_.addAllElts(list_);
        }
        images_.put(VOIE, ConverterBufferedImage.toBaseSixtyFourQuick(voie_.join(Image.SEPARATOR_CHAR)));
        StringList voieTwo_ = new StringList("12");
        for (int i = 0; i < 18; i++) {
            voieTwo_.addAllElts(list_);
        }
        images_.put(VOIE2, ConverterBufferedImage.toBaseSixtyFourQuick(voieTwo_.join(Image.SEPARATOR_CHAR)));
        StringList voieThree_ = new StringList("6");
        for (int i = 0; i < 18; i++) {
            voieThree_.addAllElts(list_);
        }
        images_.put(VOIE3, ConverterBufferedImage.toBaseSixtyFourQuick(voieThree_.join(Image.SEPARATOR_CHAR)));
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)0,(short)1));
        dataMap.calculateIntersectWithScreen(begin_);
        dataMap.calculateBackgroundImagesFromTiles(null, 0, 0);
        ObjectMap<ScreenCoords, String> backGroundImages_ = dataMap.getBackgroundImages();
        assertEq(5, backGroundImages_.size());
        PairNumber<Integer,Integer> dims_;
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(7,0)));
        assertEq(4, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(4,3)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(7,3)));
        assertEq(4, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(4,6)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(7,6)));
        assertEq(4, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
    }

    @Ignore
    @Test
    public void calculateBackgroundImagesFromTiles4Test() {
        dataMap.setSideLength(2);
        StringMap<String> images_ = new StringMap<String>();
        StringList list_ = new StringList("3","4","5","6");
        StringList voie_ = new StringList("6");
        for (int i = 0; i < 9; i++) {
            voie_.addAllElts(list_);
        }
        images_.put(VOIE, ConverterBufferedImage.toBaseSixtyFourQuick(voie_.join(Image.SEPARATOR_CHAR)));
        StringList voieTwo_ = new StringList("12");
        for (int i = 0; i < 18; i++) {
            voieTwo_.addAllElts(list_);
        }
        images_.put(VOIE2, ConverterBufferedImage.toBaseSixtyFourQuick(voieTwo_.join(Image.SEPARATOR_CHAR)));
        StringList voieThree_ = new StringList("6");
        for (int i = 0; i < 18; i++) {
            voieThree_.addAllElts(list_);
        }
        images_.put(VOIE3, ConverterBufferedImage.toBaseSixtyFourQuick(voieThree_.join(Image.SEPARATOR_CHAR)));
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)8,(short)8));
        dataMap.calculateIntersectWithScreen(begin_);
        dataMap.calculateBackgroundImagesFromTiles(null, 0, 0);
        ObjectMap<ScreenCoords, String> backGroundImages_ = dataMap.getBackgroundImages();
        assertEq(5, backGroundImages_.size());
        PairNumber<Integer,Integer> dims_;
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(0,0)));
        assertEq(4, dims_.getFirst().intValue());
        assertEq(4, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(2,0)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(4, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(5,0)));
        assertEq(8, dims_.getFirst().intValue());
        assertEq(4, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(0,2)));
        assertEq(4, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
        dims_ = ConverterBufferedImage.getDimensions(backGroundImages_.getVal(new ScreenCoords(2,2)));
        assertEq(6, dims_.getFirst().intValue());
        assertEq(6, dims_.getSecond().intValue());
    }
}
