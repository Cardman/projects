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
import aiki.map.levels.LevelOutdoor;
import aiki.map.levels.LevelRoad;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.util.PlaceInterConnect;
import code.util.*;
import code.util.ObjectMap;

public class DataMapClosestTileTest extends EquallablePkUtil {

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
        gym_.getLevel().setBlocks(new PointsBlock());
        block_ = new Block((short)9,(short)9, EnvironmentType.BUILDING, VOIE);
        gym_.getLevel().getBlocks().put(new Point((short)0,(short)0), block_);
        gym_.getIndoor().setGymLeaderCoords(new Point((short)1,(short)1));
        c_.getBuildings().put(new Point((short)4,(short)5), gym_);
        return c_;
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

    public static DataMap initDataMap() {
        City cityOne_ = city();
        Road roadOne_ = vroad();
        DataMap dataMap_ = new DataMap();
        dataMap_.setPlaces(new CustList<Place>());
        dataMap_.getPlaces().add(cityOne_);
        dataMap_.getPlaces().add(roadOne_);
        dataMap_.join((short)0, (short)1, new Point((short)4,(short)0), new Point((short)1,(short)5), Direction.UP);
        return dataMap_;
    }

    @Test
    public void closestTile1Test() {
        DataMap dataMap_ = initDataMap();
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)0,(short)0));
        Coords dest_ = dataMap_.closestTile(begin_, Direction.LEFT);
        assertTrue(!dest_.isValid());
    }

    @Test
    public void closestTile2Test() {
        DataMap dataMap_ = initDataMap();
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)0,(short)0));
        Coords dest_ = dataMap_.closestTile(begin_, Direction.RIGHT);
        assertEq(0, dest_.getNumberPlace());
        assertEq(0, dest_.getLevel().getLevelIndex());
        assertEq(0, dest_.getLevel().getPoint().gety());
        assertEq(1, dest_.getLevel().getPoint().getx());
    }

    @Test
    public void closestTile3Test() {
        DataMap dataMap_ = initDataMap();
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)2,(short)3));
        Coords dest_ = dataMap_.closestTile(begin_, Direction.RIGHT);
        assertEq(0, dest_.getNumberPlace());
        assertEq(0, dest_.getLevel().getLevelIndex());
        assertEq(3, dest_.getLevel().getPoint().gety());
        assertEq(3, dest_.getLevel().getPoint().getx());
    }

    @Test
    public void closestTile4Test() {
        DataMap dataMap_ = initDataMap();
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.getLevel().setPoint(new Point((short)3,(short)0));
        Coords dest_ = dataMap_.closestTile(begin_, Direction.UP);
        assertEq(1, dest_.getNumberPlace());
        assertEq(0, dest_.getLevel().getLevelIndex());
        assertEq(5, dest_.getLevel().getPoint().gety());
        assertEq(0, dest_.getLevel().getPoint().getx());
    }

    @Test
    public void closestTile5Test() {
        DataMap dataMap_ = initDataMap();
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.setInsideBuilding(new Point((short)4,(short)5));
        begin_.getLevel().setPoint(new Point((short)3,(short)0));
        Coords dest_ = dataMap_.closestTile(begin_, Direction.UP);
        assertTrue(!dest_.isValid());
    }

    @Test
    public void closestTile6Test() {
        DataMap dataMap_ = initDataMap();
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) 0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) 0);
        begin_.setInsideBuilding(new Point((short)4,(short)5));
        begin_.getLevel().setPoint(new Point((short)3,(short)1));
        Coords dest_ = dataMap_.closestTile(begin_, Direction.UP);
        assertEq(0, dest_.getNumberPlace());
        assertEq(0, dest_.getLevel().getLevelIndex());
        assertEq(5, dest_.getInsideBuilding().gety());
        assertEq(4, dest_.getInsideBuilding().getx());
        assertEq(0, dest_.getLevel().getPoint().gety());
        assertEq(3, dest_.getLevel().getPoint().getx());
    }
}
