package aiki.map.tree;

import aiki.db.EquallablePkUtil;
import aiki.map.levels.*;
import aiki.map.util.PlaceInterConnects;
import aiki.util.*;
import org.junit.Test;

import aiki.map.DataMap;
import aiki.map.buildings.Gym;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.tree.util.Dims;
import code.util.CustList;


public class TreeTest extends EquallablePkUtil {

    private static City city() {
        City c_ = new City();
        c_.setSavedlinks(new PlaceInterConnects());
        c_.setLinksWithCaves(new PointsLink());
        LevelOutdoor city_ = new LevelOutdoor();
        city_.setBlocks(new PointsBlock());
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
        c_.setBuildings(new PointsBuilding());
        Gym gym_ = new Gym();
        gym_.setExitCity(new Point((short)1,(short)0));
        gym_.setLevel(new LevelIndoorGym());
        gym_.getLevel().setBlocks(new PointsBlock());
        block_ = new Block((short)6,(short)6, EnvironmentType.BUILDING, "voie");
        gym_.getLevel().getBlocks().put(new Point((short)0,(short)0), block_);
        gym_.getIndoor().setGymLeaderCoords(new Point((short)1,(short)1));
        gym_.getIndoor().setGymTrainers(new PointsGymTrainer());
        c_.getBuildings().put(new Point((short)4,(short)5), gym_);
        return c_;
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
        Block block_ = new Block((short)3,(short)6, EnvironmentType.ROAD, "voie");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        road_.setLevel(level_);
        return road_;
    }


    @Test
    public void initialize1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add( city_);
        Road road_ = vroad();
        AreaApparition areaApp_ = new AreaApparition();
        areaApp_.setMultFight((byte) 1);
        areaApp_.setAvgNbSteps((short) 1);
        areaApp_.setWildPokemon(new CustList<WildPk>());
        areaApp_.setWildPokemonFishing(new CustList<WildPk>());
        WildPk pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.FEMALE);
        pk_.setLevel((short) 2);
        areaApp_.getWildPokemon().add(pk_);
        pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.MALE);
        pk_.setLevel((short) 2);
        areaApp_.getWildPokemon().add(pk_);
        areaApp_.initializeWildPokemon();
        road_.getLevelRoad().getWildPokemonAreas().add(areaApp_);
        road_.getLevel().getBlocks().getVal(newPoint(0,0)).setIndexApparition((short) 0);
        dataMap_.getPlaces().add( road_);
        Tree tree_ = new Tree();
        tree_.initialize(dataMap_);
        assertEq(2, tree_.getPlaces().size());
        PlaceArea areaPl_ = tree_.getPlace((short) 0);
        BuildingArea building_ = areaPl_.getBuildings().getVal(newPoint(4,5));
        assertEq(6, building_.getLevel().getHeight());
        assertEq(6, building_.getLevel().getWidth());
        assertEq(0, building_.getLevel().getPokemon().size());
        assertEq(0, building_.getLevel().getIndexes().size());
        assertEq(newPoint(0,0), building_.getLevel().getLeftTop());
        assertEq(0, building_.getLevel().getInacessiblePoints().size());
        assertEq(1, building_.getLevel().getDimsBlocks().size());
        assertEq(new Dims(6, 6), building_.getLevel().getDimsBlocks().getVal(newPoint(0, 0)));
        assertEq(1, areaPl_.getLevels().size());
        LevelArea area_ = areaPl_.getLevel((byte) 0);
        assertEq(9, area_.getHeight());
        assertEq(9, area_.getWidth());
        assertEq(0, area_.getPokemon().size());
        assertEq(0, area_.getIndexes().size());
        assertEq(newPoint(0,0), area_.getLeftTop());
        assertEq(9, area_.getInacessiblePoints().size());
        assertTrue(area_.getInacessiblePoints().containsObj(newPoint(3,3)));
        assertTrue(area_.getInacessiblePoints().containsObj(newPoint(3,4)));
        assertTrue(area_.getInacessiblePoints().containsObj(newPoint(3,5)));
        assertTrue(area_.getInacessiblePoints().containsObj(newPoint(4,3)));
        assertTrue(area_.getInacessiblePoints().containsObj(newPoint(4,4)));
        assertTrue(area_.getInacessiblePoints().containsObj(newPoint(4,5)));
        assertTrue(area_.getInacessiblePoints().containsObj(newPoint(5,3)));
        assertTrue(area_.getInacessiblePoints().containsObj(newPoint(5,4)));
        assertTrue(area_.getInacessiblePoints().containsObj(newPoint(5,5)));
        assertEq(8, area_.getDimsBlocks().size());
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(newPoint(0, 0)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(newPoint(0, 3)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(newPoint(0, 6)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(newPoint(3, 0)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(newPoint(3, 6)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(newPoint(6, 0)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(newPoint(6, 3)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(newPoint(6, 6)));
        areaPl_ = tree_.getPlace((short) 1);
        assertEq(0, areaPl_.getBuildings().size());
        assertEq(1, areaPl_.getLevels().size());
        area_ = areaPl_.getLevel((byte) 0);
        assertEq(6, area_.getHeight());
        assertEq(3, area_.getWidth());
        assertEq(1, area_.getPokemon().size());
        assertEq(2, area_.getPokemon().first().size());
        assertEq("PIKACHU", area_.getPokemon().first().first().getName());
        assertEq(Gender.FEMALE, area_.getPokemon().first().first().getGender());
        assertEq("PIKACHU", area_.getPokemon().first().last().getName());
        assertEq(Gender.MALE, area_.getPokemon().first().last().getGender());
        assertEq(1, area_.getIndexes().size());
        assertEq(0, area_.getIndexes().getVal(newPoint(0,0)));
        assertEq(newPoint(0,0), area_.getLeftTop());
        assertEq(0, area_.getInacessiblePoints().size());
        assertEq(1, area_.getDimsBlocks().size());
        assertEq(new Dims(3,6), area_.getDimsBlocks().getVal(newPoint(0, 0)));
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
        dataMap_.getPlaces().add( city_);
        Road road_ = vroad();
        AreaApparition areaApp_ = new AreaApparition();
        areaApp_.setMultFight((byte) 1);
        areaApp_.setAvgNbSteps((short) 1);
        areaApp_.setWildPokemon(new CustList<WildPk>());
        areaApp_.setWildPokemonFishing(new CustList<WildPk>());
        WildPk pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.FEMALE);
        pk_.setLevel((short) 2);
        areaApp_.getWildPokemon().add(pk_);
        pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.MALE);
        pk_.setLevel((short) 2);
        areaApp_.getWildPokemon().add(pk_);
        areaApp_.initializeWildPokemon();
        road_.getLevelRoad().getWildPokemonAreas().add(areaApp_);
        road_.getLevel().getBlocks().getVal(newPoint(0,0)).setIndexApparition((short) 0);
        dataMap_.getPlaces().add( road_);
        Tree tree_ = new Tree();
        tree_.initialize(dataMap_);
        assertTrue(!tree_.isValid(newCoords(0, 0, 3, 3), true));
    }

    @Test
    public void isValid2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add( city_);
        Road road_ = vroad();
        AreaApparition areaApp_ = new AreaApparition();
        areaApp_.setMultFight((byte) 1);
        areaApp_.setAvgNbSteps((short) 1);
        areaApp_.setWildPokemon(new CustList<WildPk>());
        areaApp_.setWildPokemonFishing(new CustList<WildPk>());
        WildPk pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.FEMALE);
        pk_.setLevel((short) 2);
        areaApp_.getWildPokemon().add(pk_);
        pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.MALE);
        pk_.setLevel((short) 2);
        areaApp_.getWildPokemon().add(pk_);
        areaApp_.initializeWildPokemon();
        road_.getLevelRoad().getWildPokemonAreas().add(areaApp_);
        road_.getLevel().getBlocks().getVal(newPoint(0,0)).setIndexApparition((short) 0);
        dataMap_.getPlaces().add( road_);
        Tree tree_ = new Tree();
        tree_.initialize(dataMap_);
        assertTrue(tree_.isValid(newCoords(0, 4, 5, 3, 3), true));
    }
}
