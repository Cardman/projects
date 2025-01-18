package aiki.map.tree;

import aiki.db.EquallablePkUtil;
import aiki.map.levels.*;
import aiki.map.util.PlaceInterConnects;
import aiki.util.*;
import org.junit.Test;

import aiki.fight.pokemon.GenderName;
import aiki.map.DataMap;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymTrainer;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.tree.util.Dims;
import aiki.map.util.PlaceInterConnect;
import code.util.CustList;
import code.util.*;



public class LevelAreaTest extends EquallablePkUtil {

    private static City city() {
        City c_ = new City();
        c_.setSavedlinks(new PlaceInterConnects());
        c_.setLinksWithCaves(new PointsLink());
        LevelOutdoor city_ = new LevelOutdoor();
        city_.setBlocks(new PointsBlock());
        Block block_ = new Block(3,3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(0,0), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(0,3), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(0,6), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(3,0), block_);
        block_ = new Block(3,3, EnvironmentType.NOTHING, "voie");
        city_.getBlocks().put(newPoint(3,3), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(3,6), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(6,0), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(6,3), block_);
        block_ = new Block(3,3, EnvironmentType.ROAD, "voie");
        city_.getBlocks().put(newPoint(6,6), block_);
        c_.setLevel(city_);
        c_.setBuildings(new PointsBuilding());
        Gym gym_ = new Gym();
        gym_.setExitCity(newPoint(1,0));
        gym_.setLevel(new LevelIndoorGym());
        gym_.getLevel().setBlocks(new PointsBlock());
        block_ = new Block(6,6, EnvironmentType.BUILDING, "voie");
        gym_.getLevel().getBlocks().put(newPoint(0,0), block_);
        gym_.getIndoor().setGymLeaderCoords(newPoint(1,1));
        gym_.getIndoor().setGymTrainers(new PointsGymTrainer());
        c_.getBuildings().put(newPoint(4,5), gym_);
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
        Block block_ = new Block(3,6, EnvironmentType.ROAD, "voie");
        level_.getBlocks().put(newPoint(0,0), block_);
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
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
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
        assertEq(newPoint(0, 0),area_.getDimsBlocks().getKey(0));
        assertEq(newPoint(0, 3),area_.getDimsBlocks().getKey(1));
        assertEq(newPoint(0, 6),area_.getDimsBlocks().getKey(2));
        assertEq(newPoint(3, 0),area_.getDimsBlocks().getKey(3));
        assertEq(newPoint(3, 6),area_.getDimsBlocks().getKey(4));
        assertEq(newPoint(6, 0),area_.getDimsBlocks().getKey(5));
        assertEq(newPoint(6, 3),area_.getDimsBlocks().getKey(6));
        assertEq(newPoint(6, 6),area_.getDimsBlocks().getKey(7));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getValue(0));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getValue(1));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getValue(2));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getValue(3));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getValue(4));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getValue(5));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getValue(6));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getValue(7));
    }

    @Test
    public void initialize2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        Road road_ = vroad();
        AreaApparition areaApp_ = new AreaApparition();
        areaApp_.setMultFight( 1);
        areaApp_.setAvgNbSteps( 1);
        areaApp_.setWildPokemon(new CustList<WildPk>());
        areaApp_.setWildPokemonFishing(new CustList<WildPk>());
        WildPk pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.FEMALE);
        pk_.setLevel( 2);
        areaApp_.getWildPokemon().add(pk_);
        pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.MALE);
        pk_.setLevel( 2);
        areaApp_.getWildPokemon().add(pk_);
        areaApp_.initializeWildPokemon();
        road_.getLevelRoad().getWildPokemonAreas().add(areaApp_);
        road_.getLevel().getBlocks().getVal(newPoint(0,0)).setIndexApparition( 0);
        dataMap_.getPlaces().add( road_);
        LevelArea area_ = new LevelArea();
        area_.initialize(road_.getLevel());
        assertEq(6, area_.getHeight());
        assertEq(3, area_.getWidth());
        assertEq(1, area_.getPokemon().size());
        assertEq(2, area_.getPokemon().first().size());
        assertEq("PIKACHU", area_.getPokemon().first().first().getName());
        assertEq(Gender.FEMALE, area_.getPokemon().first().first().getGender());
        assertEq("PIKACHU", area_.getPokemon().first().last().getName());
        assertEq(Gender.MALE, area_.getPokemon().first().last().getGender());
        assertEq(1, area_.getIndexes().size());
        assertEq(newPoint(0, 0),area_.getIndexes().getKey(0));
        assertEq(0, area_.getIndexes().getValue(0));
        assertEq(newPoint(0,0), area_.getLeftTop());
        assertEq(0, area_.getInacessiblePoints().size());
        assertEq(1, area_.getDimsBlocks().size());
        assertEq(newPoint(0, 0),area_.getDimsBlocks().getKey(0));
        assertEq(new Dims(3,6), area_.getDimsBlocks().getValue(0));
    }

    @Test
    public void initialize3Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        Road road_ = vroad();
        AreaApparition areaApp_ = new AreaApparition();
        areaApp_.setMultFight( 1);
        areaApp_.setAvgNbSteps( 1);
        areaApp_.setWildPokemon(new CustList<WildPk>());
        areaApp_.setWildPokemonFishing(new CustList<WildPk>());
        WildPk pk_ = new WildPk();
        pk_.setName("PTITARD");
        pk_.setItem("");
        pk_.setAbility("MOITEUR");
        pk_.setGender(Gender.FEMALE);
        pk_.setLevel( 2);
        WildPk first_ = pk_;
        areaApp_.getWildPokemon().add(pk_);
        pk_ = new WildPk();
        pk_.setName("PTITARD");
        pk_.setItem("");
        pk_.setAbility("MOITEUR");
        pk_.setGender(Gender.MALE);
        pk_.setLevel( 2);
        areaApp_.getWildPokemonFishing().add(pk_);
        areaApp_.initializeWildPokemon();
        assertTrue(WildPk.eq(first_,areaApp_.getWildPokemon(0)));
        assertTrue(WildPk.eq(pk_,areaApp_.getPokemonFishing(0)));
        road_.getLevelRoad().getWildPokemonAreas().add(areaApp_);
        road_.getLevel().getBlocks().getVal(newPoint(0,0)).setIndexApparition( 0);
        dataMap_.getPlaces().add( road_);
        LevelArea area_ = new LevelArea();
        area_.initialize(road_.getLevel());
        assertEq(6, area_.getHeight());
        assertEq(3, area_.getWidth());
        assertEq(1, area_.getPokemon().size());
        assertEq(2, area_.getPokemon().first().size());
        assertEq("PTITARD", area_.getPokemon().first().first().getName());
        assertEq(Gender.FEMALE, area_.getPokemon().first().first().getGender());
        assertEq("PTITARD", area_.getPokemon().first().last().getName());
        assertEq(Gender.MALE, area_.getPokemon().first().last().getGender());
        assertEq(1, area_.getIndexes().size());
        assertEq(newPoint(0, 0),area_.getIndexes().getKey(0));
        assertEq(0, area_.getIndexes().getValue(0));
        assertEq(newPoint(0,0), area_.getLeftTop());
        assertEq(0, area_.getInacessiblePoints().size());
        assertEq(1, area_.getDimsBlocks().size());
        assertEq(newPoint(0, 0),area_.getDimsBlocks().getKey(0));
        assertEq(new Dims(3,6), area_.getDimsBlocks().getValue(0));
    }

    @Test
    public void getPokemon1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add( city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertEq(0, area_.getPokemon(newPoint(0,0)).size());
        assertEq(0, area_.getPokemon(newPoint(3,3)).size());
        assertEq(0, area_.getPokemon(newPoint(6,6)).size());
        assertEq(0, area_.getPokemon(newPoint(9,9)).size());
    }

    @Test
    public void getPokemon2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        Road road_ = vroad();
        AreaApparition areaApp_ = new AreaApparition();
        areaApp_.setMultFight( 1);
        areaApp_.setAvgNbSteps( 1);
        areaApp_.setWildPokemon(new CustList<WildPk>());
        areaApp_.setWildPokemonFishing(new CustList<WildPk>());
        WildPk pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.FEMALE);
        pk_.setLevel( 2);
        areaApp_.getWildPokemon().add(pk_);
        pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.MALE);
        pk_.setLevel( 2);
        areaApp_.getWildPokemon().add(pk_);
        areaApp_.initializeWildPokemon();
        road_.getLevelRoad().getWildPokemonAreas().add(areaApp_);
        road_.getLevel().getBlocks().getVal(newPoint(0,0)).setIndexApparition( 0);
        dataMap_.getPlaces().add( road_);
        LevelArea area_ = new LevelArea();
        area_.initialize(road_.getLevel());
        CustList<GenderName> list_;
        list_ = area_.getPokemon(newPoint(0,0));
        assertEq(2, list_.size());
        assertEq("PIKACHU", list_.first().getName());
        assertEq(Gender.FEMALE, list_.first().getGender());
        assertEq("PIKACHU", list_.last().getName());
        assertEq(Gender.MALE, list_.last().getGender());
    }

    @Test
    public void isValid1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add( city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertTrue(!area_.isValid(newPoint(9,9), false));
        assertTrue(!area_.isValid(newPoint(8,9), false));
        assertTrue(!area_.isValid(newPoint(-1,-1), false));
        assertTrue(!area_.isValid(newPoint(0,-1), false));
        assertTrue(area_.isValid(newPoint(0,0), false));
        assertTrue(area_.isValid(newPoint(3,3), false));
    }

    @Test
    public void isValid2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add( city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertTrue(!area_.isValid(newPoint(9,9), true));
        assertTrue(!area_.isValid(newPoint(8,9), true));
        assertTrue(!area_.isValid(newPoint(-1,-1), true));
        assertTrue(!area_.isValid(newPoint(0,-1), true));
        assertTrue(area_.isValid(newPoint(0,0), true));
        assertTrue(!area_.isValid(newPoint(3,3), true));
    }

    @Test
    public void size1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add( city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertEq(81, area_.size());
    }

    @Test
    public void allAccessible1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add( city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertTrue(!area_.allAccessible());
    }

    private static void initPlaces(DataMap _dataMap) {
        _dataMap.setPlaces(new CustList<Place>());
    }

    @Test
    public void allAccessible2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        Road road_ = vroad();
        AreaApparition areaApp_ = new AreaApparition();
        areaApp_.setMultFight( 1);
        areaApp_.setAvgNbSteps( 1);
        areaApp_.setWildPokemon(new CustList<WildPk>());
        areaApp_.setWildPokemonFishing(new CustList<WildPk>());
        WildPk pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.FEMALE);
        pk_.setLevel( 2);
        areaApp_.getWildPokemon().add(pk_);
        pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.MALE);
        pk_.setLevel( 2);
        areaApp_.getWildPokemon().add(pk_);
        areaApp_.initializeWildPokemon();
        road_.getLevelRoad().getWildPokemonAreas().add(areaApp_);
        road_.getLevel().getBlocks().getVal(newPoint(0,0)).setIndexApparition( 0);
        dataMap_.getPlaces().add( road_);
        LevelArea area_ = new LevelArea();
        area_.initialize(road_.getLevel());
        assertTrue(area_.allAccessible());
    }

    @Test
    public void isAccessible1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add( city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertTrue(!area_.isAccessible(newPoint(3, 3)));
    }

    @Test
    public void isAccessible2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
        initPlaces(dataMap_);
        City city_ = city();
        dataMap_.getPlaces().add( city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertTrue(area_.isAccessible(newPoint(2, 3)));
    }
}
