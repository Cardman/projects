package aiki.map.tree;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.map.DataMap;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.characters.GymTrainer;
import aiki.map.levels.Block;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelOutdoor;
import aiki.map.levels.Link;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.Place;
import aiki.map.tree.util.Dims;
import aiki.map.util.PlaceInterConnect;
import aiki.util.Coords;
import aiki.util.Point;
import code.util.EqList;
import code.util.*;
import code.util.ObjectMap;


public class BuildingAreaTest {

    private static City city() {
        City c_ = new City();
        c_.setSavedlinks(new ObjectMap<PlaceInterConnect,Coords>());
        c_.setLinksWithCaves(new ObjectMap<Point,Link>());
        LevelOutdoor city_ = new LevelOutdoor();
        city_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(new Point((short)0,(short)0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(new Point((short)0,(short)3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(new Point((short)0,(short)6), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(new Point((short)3,(short)0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.NOTHING, "voie");
        city_.getBlocks().put(new Point((short)3,(short)3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(new Point((short)3,(short)6), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(new Point((short)6,(short)0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(new Point((short)6,(short)3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(new Point((short)6,(short)6), block_);
        c_.setLevel(city_);
        c_.setBuildings(new ObjectMap<Point,Building>());
        Gym gym_ = new Gym();
        gym_.setExitCity(new Point((short)1,(short)0));
        gym_.setLevel(new LevelIndoorGym());
        gym_.getLevel().setBlocks(new ObjectMap<Point,Block>());
        block_ = new Block((short)6,(short)6, EnvironmentType.BUILDING, "voie");
        gym_.getLevel().getBlocks().put(new Point((short)0,(short)0), block_);
        gym_.getIndoor().setGymLeaderCoords(new Point((short)1,(short)1));
        gym_.getIndoor().setGymTrainers(new ObjectMap<Point,GymTrainer>());
        c_.getBuildings().put(new Point((short)4,(short)5), gym_);
        return c_;
    }

    private static Point point(int _x, int _y) {
        return new Point((short) _x,(short) _y);
    }

    @Test
    public void initialize1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add(city_);
        BuildingArea area_ = new BuildingArea();
        area_.initialize(city_.getBuildings().getVal(point(4,5)));
        assertEq(6, area_.getLevel().getHeight());
        assertEq(6, area_.getLevel().getWidth());
        assertEq(0, area_.getLevel().getPokemon().size());
        assertEq(0, area_.getLevel().getIndexes().size());
        assertEq(point(0,0), area_.getLevel().getLeftTop());
        assertEq(0, area_.getLevel().getInacessiblePoints().size());
        assertEq(1, area_.getLevel().getDimsBlocks().size());
        assertEq(new Dims(6, 6), area_.getLevel().getDimsBlocks().getVal(point(0, 0)));
    }

    private static void initPlaces(DataMap _dataMap) {
        _dataMap.setPlaces(new CustList<Place>());
    }

    @Test
    public void isValid1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add(city_);
        BuildingArea area_ = new BuildingArea();
        area_.initialize(city_.getBuildings().getVal(point(4,5)));
        assertTrue(!area_.isValid(point(9,9), false));
        assertTrue(!area_.isValid(point(8,9), false));
        assertTrue(!area_.isValid(point(-1,-1), false));
        assertTrue(!area_.isValid(point(0,-1), false));
        assertTrue(area_.isValid(point(0,0), false));
        assertTrue(area_.isValid(point(3,3), false));
    }

    @Test
    public void isValid2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add(city_);
        BuildingArea area_ = new BuildingArea();
        area_.initialize(city_.getBuildings().getVal(point(4,5)));
        assertTrue(!area_.isValid(point(9,9), true));
        assertTrue(!area_.isValid(point(8,9), true));
        assertTrue(!area_.isValid(point(-1,-1), true));
        assertTrue(!area_.isValid(point(0,-1), true));
        assertTrue(area_.isValid(point(0,0), true));
    }
}
