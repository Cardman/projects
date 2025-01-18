package aiki.map;

import aiki.db.*;
import aiki.instances.*;
import aiki.map.buildings.*;
import aiki.map.enums.*;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.map.places.*;
import aiki.map.util.*;
import aiki.util.*;
import code.maths.montecarlo.*;
import code.util.*;
import org.junit.Test;

public final class DataMapModifTest extends EquallablePkUtil {

    private static final String VOIE3 = "voie3";
    private static final String VOIE2 = "voie2";
    private static final String VOIE = "voie";
    @Test
    public void deletePlace1() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deletePlace(-1));
    }
    @Test
    public void deletePlace2() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.setBegin(begin());
        assertNotNull(dataMap_.deletePlace(10));
    }
    @Test
    public void deletePlace3() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deletePlace(0));
    }
    @Test
    public void deletePlace4() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.setBegin(begin());
        assertNotNull(dataMap_.deletePlace(3));
    }
    @Test
    public void deletePlace5() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        dataMap_.setBegin(begin());
        TileMiniMap tile_ = Instances.newTileMiniMap();
        tile_.setPlace(12);
        dataMap_.getMiniMap().addEntry(new MiniMapCoords(0,0), tile_);
        assertNotNull(dataMap_.deletePlace(11));
        assertEq(11,tile_.getPlace());
    }
    @Test
    public void deletePlace6() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        ((Road)dataMap_.getPlace(3)).getLinksWithCaves().addEntry(newPoint(0,0), new Link("",newCoords(11,0,0,0)));
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deletePlace(11));
    }
    @Test
    public void deletePlace7() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        ((Cave)dataMap_.getPlace(11)).getLevels().get(1).getLinksOtherLevels().addEntry(newPoint(0,0),new Link("",newCoords(3,0,0,0)));
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deletePlace(3));
    }
    //
    @Test
    public void deletePlace8() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(league(newCoords(3,0,0,0)));
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deletePlace(3));
    }
    @Test
    public void deletePlace9() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        Condition c_ = new Condition();
        c_.add(newCoords(3,0,0,0));
        dataMap_.getAccessCondition().addEntry(newCoords(3,0,0,0), c_);
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deletePlace(3));
    }
    @Test
    public void deletePlace10() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        ((Cave)dataMap_.getPlace(11)).getLinksWithOtherPlaces().addEntry(newLevelPoint(0,0,0),new Link("",newCoords(3,0,0,0)));
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deletePlace(3));
    }
    @Test
    public void deleteLevelPlace1() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deleteLevelPlace(-1,-1));
    }
    @Test
    public void deleteLevelPlace2() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deleteLevelPlace(0,-1));
    }
    @Test
    public void deleteLevelPlace3() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deleteLevelPlace(10,-1));
    }
    @Test
    public void deleteLevelPlace4() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        dataMap_.setBegin(begin());
        TileMiniMap tile_ = Instances.newTileMiniMap();
        tile_.setPlace(12);
        dataMap_.getMiniMap().addEntry(new MiniMapCoords(0,0), tile_);
        assertNotNull(dataMap_.deleteLevelPlace(11,0));
        assertEq(12,tile_.getPlace());
    }
    @Test
    public void deleteLevelPlace5() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        ((Cave)dataMap_.getPlace(11)).getLevels().get(1).getLinksOtherLevels().addEntry(newPoint(0,0),new Link("",newCoords(11,0,0,0)));
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deleteLevelPlace(11,0));
    }
    @Test
    public void deleteLevelPlace6() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        ((Road)dataMap_.getPlace(3)).getLinksWithCaves().addEntry(newPoint(0,0), new Link("",newCoords(11,0,0,0)));
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deleteLevelPlace(11,0));
    }
    @Test
    public void deleteLevelPlace7() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        dataMap_.setBegin(begin());
        TileMiniMap tile_ = Instances.newTileMiniMap();
        tile_.setPlace(11);
        dataMap_.getMiniMap().addEntry(new MiniMapCoords(0,0), tile_);
        assertNotNull(dataMap_.deleteLevelPlace(11,0));
        assertEq(11,tile_.getPlace());
    }
    @Test
    public void deleteLevelPlace8() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        ((Cave)dataMap_.getPlace(11)).getLevels().get(1).getLinksOtherLevels().addEntry(newPoint(0,0),new Link("",newCoords(11,1,0,1)));
        dataMap_.setBegin(begin());
        assertNotNull(dataMap_.deleteLevelPlace(11,0));
    }
    @Test
    public void deleteLevelPlace9() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deleteLevelPlace(11,-1));
    }
    @Test
    public void deleteLevelPlace10() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        ((Cave)dataMap_.getPlace(11)).getLinksWithOtherPlaces().addEntry(newLevelPoint(1,0,0),new Link("",newCoords(3,0,0,0)));
        dataMap_.setBegin(begin());
        assertNotNull(dataMap_.deleteLevelPlace(11,0));
        assertEq(0, ((Cave)dataMap_.getPlace(11)).getLinksWithOtherPlaces().getList().get(0).getLevelPoint().getLevelIndex());
    }
    @Test
    public void deleteLevelPlace11() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        ((Cave)dataMap_.getPlace(12)).getLinksWithOtherPlaces().addEntry(newLevelPoint(1,0,0),new Link("",newCoords(3,0,0,0)));
        dataMap_.setBegin(begin());
        assertNotNull(dataMap_.deleteLevelPlace(11,0));
        assertEq(1, ((Cave)dataMap_.getPlace(12)).getLinksWithOtherPlaces().getList().get(0).getLevelPoint().getLevelIndex());
    }
    @Test
    public void deleteLevelPlace12() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(cave());
        dataMap_.getPlaces().add(cave());
        ((Cave)dataMap_.getPlace(11)).getLinksWithOtherPlaces().addEntry(newLevelPoint(0,0,0),new Link("",newCoords(3,0,0,0)));
        dataMap_.setBegin(begin());
        assertNotNull(dataMap_.deleteLevelPlace(11,1));
        assertEq(0, ((Cave)dataMap_.getPlace(11)).getLinksWithOtherPlaces().getList().get(0).getLevelPoint().getLevelIndex());
    }
    @Test
    public void deleteLevelPlace13() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(league(newCoords(3,0,0,0)));
        dataMap_.getPlaces().add(cave());
        dataMap_.setBegin(begin());
        TileMiniMap tile_ = Instances.newTileMiniMap();
        tile_.setPlace(12);
        dataMap_.getMiniMap().addEntry(new MiniMapCoords(0,0), tile_);
        assertNotNull(dataMap_.deleteLevelPlace(11,0));
        assertEq(12,tile_.getPlace());
    }
    @Test
    public void deleteLevelPlace14() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(league(newCoords(3,0,0,0)));
        dataMap_.getPlaces().add(cave());
        ((Road)dataMap_.getPlace(3)).getLinksWithCaves().addEntry(newPoint(0,0), new Link("",newCoords(11,0,0,0)));
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deleteLevelPlace(11,0));
    }
    @Test
    public void deleteLevelPlace15() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(league(newCoords(3,0,0,0)));
        dataMap_.getPlaces().add(cave());
        dataMap_.setBegin(begin());
        TileMiniMap tile_ = Instances.newTileMiniMap();
        tile_.setPlace(11);
        dataMap_.getMiniMap().addEntry(new MiniMapCoords(0,0), tile_);
        assertNotNull(dataMap_.deleteLevelPlace(11,0));
        assertEq(11,tile_.getPlace());
    }
    @Test
    public void deleteLevelPlace16() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(league(newCoords(3,0,0,0)));
        dataMap_.getPlaces().add(cave());
        dataMap_.setBegin(begin());
        assertNull(dataMap_.deleteLevelPlace(11,-1));
    }
    @Test
    public void deleteLevelPlace17() {
        DataMap dataMap_ = initDataMap();
        dataMap_.setSideLength(2);
        dataMap_.getPlaces().add(league(newCoords(3,0,0,0)));
        dataMap_.getPlaces().add(cave());
        ((Cave)dataMap_.getPlace(12)).getLinksWithOtherPlaces().addEntry(newLevelPoint(1,0,0),new Link("",newCoords(3,0,0,0)));
        dataMap_.setBegin(begin());
        assertNotNull(dataMap_.deleteLevelPlace(11,0));
        assertEq(1, ((Cave)dataMap_.getPlace(12)).getLinksWithOtherPlaces().getList().get(0).getLevelPoint().getLevelIndex());
    }
    private Coords begin() {
        Coords begin_ = new Coords();
        begin_.setNumberPlace(0);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(0);
        begin_.getLevel().setPoint(newPoint(8,8));
        return begin_;
    }

    private static DataBase newData() {
        return new DataBase(DefaultGenerator.oneElt());
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
        DataMap dataMap_ = Instances.newDataMap();
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
        dataMap_.join(0, 1, newPoint(4,0), newPoint(1,5), Direction.UP);
        dataMap_.join(7, 0, newPoint(0,1), newPoint(8,4), Direction.LEFT);
        return dataMap_;
    }

    private static Cave cave() {
        Cave cave_ = Instances.newCave();
        cave_.setLevels(new CustList<LevelCave>());
        cave_.setLinksWithOtherPlaces(new LevelPoints());
        cave_.getLevels().add(levelCaveOne());
        cave_.getLevels().add(levelCaveTwo());
        return cave_;
    }
    private static LevelCave levelCaveOne() {
        LevelCave levelCave_;
        levelCave_ = new LevelCave();
        levelCave_.setBlocks(new PointsBlock());
        levelCave_.setCharacters(new PointsCharacterInRoadCave());
        levelCave_.setDualFights(new PointsDualFight());
        levelCave_.setLinksOtherLevels(new PointsLink());
        levelCave_.setLegendaryPks(new PointsWildPk());
        levelCave_.setItems(new PointsString());
        levelCave_.setHm(new PointsShort());
        levelCave_.setTm(new PointsShort());
        levelCave_.setWildPokemonAreas(new CustList<AbsAreaApparition>());
        Block block_ = new Block(3,6, EnvironmentType.ROCK, VOIE);
        levelCave_.getBlocks().put(newPoint(0,0), block_);
        return levelCave_;
    }
    private static LevelCave levelCaveTwo() {
        LevelCave levelCave_;
        levelCave_ = new LevelCave();
        levelCave_.setBlocks(new PointsBlock());
        levelCave_.setCharacters(new PointsCharacterInRoadCave());
        levelCave_.setDualFights(new PointsDualFight());
        levelCave_.setLinksOtherLevels(new PointsLink());
        levelCave_.setLegendaryPks(new PointsWildPk());
        levelCave_.setItems(new PointsString());
        levelCave_.setHm(new PointsShort());
        levelCave_.setTm(new PointsShort());
        levelCave_.setWildPokemonAreas(new CustList<AbsAreaApparition>());
        Block block_ = new Block(3,3, EnvironmentType.ROCK, VOIE);
        levelCave_.getBlocks().put(newPoint(0,0), block_);
        block_ = new Block(3,3, EnvironmentType.NOTHING, VOIE);
        levelCave_.getBlocks().put(newPoint(0,3), block_);
        return levelCave_;
    }
    private static League league(Coords _access) {
        League league_ = Instances.newLeague();
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
        league_.setBegin(newPoint(2,4));
        return league_;
    }
    private static City city() {
        City c_ = Instances.newCity();
        c_.setSavedlinks(new PlaceInterConnects());
        c_.setBuildings(new PointsBuilding());
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
        Gym gym_ = new Gym();
        gym_.setExitCity(newPoint(1,1));
        gym_.setLevel(new LevelIndoorGym());
        gym_.getIndoor().setGymLeaderCoords(newPoint(1,1));
        c_.getBuildings().put(newPoint(4,5), gym_);
        return c_;
    }
    private static Road hroad() {
        Road road_ = Instances.newRoad();
        road_.setSavedlinks(new PlaceInterConnects());
        LevelRoad level_ = new LevelRoad();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block(6,3, EnvironmentType.ROAD, VOIE2);
        level_.getBlocks().put(newPoint(0,0), block_);
        road_.setLevel(level_);
        return road_;
    }
    private static Road vroad() {
        Road road_ = Instances.newRoad();
        road_.setSavedlinks(new PlaceInterConnects());
        LevelRoad level_ = new LevelRoad();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block(3,6, EnvironmentType.ROAD, VOIE3);
        level_.getBlocks().put(newPoint(0,0), block_);
        road_.setLevel(level_);
        return road_;
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
