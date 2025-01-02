package aiki.map.tree;

import aiki.db.EquallablePkUtil;
import aiki.map.util.PlaceInterConnects;
import aiki.util.*;
import org.junit.Test;

import aiki.map.DataMap;
import aiki.map.buildings.Gym;
import aiki.map.levels.Block;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelOutdoor;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.Place;
import aiki.map.tree.util.Dims;
import code.util.*;



public class BuildingAreaTest extends EquallablePkUtil {

    private static City city() {
        City c_ = new City();
        c_.setSavedlinks(new PlaceInterConnects());
        c_.setLinksWithCaves(new PointsLink());
        LevelOutdoor city_ = new LevelOutdoor();
        city_.setBlocks(new PointsBlock());
        Block block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(0,0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(0,3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(0,6), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(3,0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.NOTHING, "voie");
        city_.getBlocks().put(newPoint(3,3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(3,6), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(6,0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(6,3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(6,6), block_);
        c_.setLevel(city_);
        c_.setBuildings(new PointsBuilding());
        Gym gym_ = new Gym();
        gym_.setExitCity(newPoint(1,0));
        gym_.setLevel(new LevelIndoorGym());
        gym_.getLevel().setBlocks(new PointsBlock());
        block_ = new Block((short)6,(short)6, EnvironmentType.BUILDING, "voie");
        gym_.getLevel().getBlocks().put(newPoint(0,0), block_);
        gym_.getIndoor().setGymLeaderCoords(newPoint(1,1));
        gym_.getIndoor().setGymTrainers(new PointsGymTrainer());
        c_.getBuildings().put(newPoint(4,5), gym_);
        return c_;
    }

    @Test
    public void initialize1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add(city_);
        BuildingArea area_ = new BuildingArea();
        area_.initialize(city_.getBuildings().getVal(newPoint(4,5)));
        assertEq(6, area_.getLevel().getHeight());
        assertEq(6, area_.getLevel().getWidth());
        assertEq(0, area_.getLevel().getPokemon().size());
        assertEq(0, area_.getLevel().getIndexes().size());
        assertEq(newPoint(0,0), area_.getLevel().getLeftTop());
        assertEq(0, area_.getLevel().getInacessiblePoints().size());
        assertEq(1, area_.getLevel().getDimsBlocks().size());
        assertEq(newPoint(0, 0), area_.getLevel().getDimsBlocks().getKey(0));
        assertEq(new Dims(6, 6), area_.getLevel().getDimsBlocks().getValue(0));
    }

    private static void initPlaces(DataMap _dataMap) {
        _dataMap.setPlaces(new CustList<Place>());
    }

    @Test
    public void isValid1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add(city_);
        BuildingArea area_ = new BuildingArea();
        area_.initialize(city_.getBuildings().getVal(newPoint(4,5)));
        assertTrue(!area_.isValid(newPoint(9,9)));
        assertTrue(!area_.isValid(newPoint(8,9)));
        assertTrue(!area_.isValid(newPoint(-1,-1)));
        assertTrue(!area_.isValid(newPoint(0,-1)));
        assertTrue(area_.isValid(newPoint(0,0)));
        assertTrue(area_.isValid(newPoint(3,3)));
    }

    @Test
    public void isValid2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add(city_);
        BuildingArea area_ = new BuildingArea();
        area_.initialize(city_.getBuildings().getVal(newPoint(4,5)));
        assertTrue(!area_.isValid(newPoint(9,9)));
        assertTrue(!area_.isValid(newPoint(8,9)));
        assertTrue(!area_.isValid(newPoint(-1,-1)));
        assertTrue(!area_.isValid(newPoint(0,-1)));
        assertTrue(area_.isValid(newPoint(0,0)));
    }
}
