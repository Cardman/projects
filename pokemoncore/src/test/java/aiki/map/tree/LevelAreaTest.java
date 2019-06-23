package aiki.map.tree;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.fight.pokemon.GenderName;
import aiki.map.DataMap;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymTrainer;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Block;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelOutdoor;
import aiki.map.levels.LevelRoad;
import aiki.map.levels.Link;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.tree.util.Dims;
import aiki.map.util.PlaceInterConnect;
import aiki.util.Coords;
import aiki.util.Point;
import code.util.CustList;
import code.util.EqList;
import code.util.*;
import code.util.ObjectMap;


public class LevelAreaTest {

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

    private static Road vroad() {
        Road road_ = new Road();
        road_.setSavedlinks(new ObjectMap<PlaceInterConnect,Coords>());
        road_.setLinksWithCaves(new ObjectMap<Point,Link>());
        LevelRoad level_ = new LevelRoad();
        level_.setCharacters(new ObjectMap<Point,CharacterInRoadCave>());
        level_.setDualFights(new ObjectMap<Point,DualFight>());
        level_.setBlocks(new ObjectMap<Point,Block>());
        level_.setLegendaryPks(new ObjectMap<Point,WildPk>());
        level_.setItems(new ObjectMap<Point,String>());
        level_.setHm(new ObjectMap<Point,Short>());
        level_.setTm(new ObjectMap<Point,Short>());
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        Block block_ = new Block((short)3,(short)6, EnvironmentType.ROAD, "voie");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        road_.setLevel(level_);
        return road_;
    }

    private static Point point(int _x, int _y) {
        return new Point((short) _x,(short) _y);
    }

    @Test
    public void initialize1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        dataMap_.setPlaces(new ShortMap<Place>());
        City city_ = city();
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(), city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertEq(9, area_.getHeight());
        assertEq(9, area_.getWidth());
        assertEq(0, area_.getPokemon().size());
        assertEq(0, area_.getIndexes().size());
        assertEq(point(0,0), area_.getLeftTop());
        assertEq(9, area_.getInacessiblePoints().size());
        assertTrue(area_.getInacessiblePoints().containsObj(point(3,3)));
        assertTrue(area_.getInacessiblePoints().containsObj(point(3,4)));
        assertTrue(area_.getInacessiblePoints().containsObj(point(3,5)));
        assertTrue(area_.getInacessiblePoints().containsObj(point(4,3)));
        assertTrue(area_.getInacessiblePoints().containsObj(point(4,4)));
        assertTrue(area_.getInacessiblePoints().containsObj(point(4,5)));
        assertTrue(area_.getInacessiblePoints().containsObj(point(5,3)));
        assertTrue(area_.getInacessiblePoints().containsObj(point(5,4)));
        assertTrue(area_.getInacessiblePoints().containsObj(point(5,5)));
        assertEq(8, area_.getDimsBlocks().size());
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(point(0, 0)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(point(0, 3)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(point(0, 6)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(point(3, 0)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(point(3, 6)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(point(6, 0)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(point(6, 3)));
        assertEq(new Dims(3,3), area_.getDimsBlocks().getVal(point(6, 6)));
    }

    @Test
    public void initialize2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        dataMap_.setPlaces(new ShortMap<Place>());
        Road road_ = vroad();
        AreaApparition areaApp_ = new AreaApparition();
        areaApp_.setMultFight((byte) 1);
        areaApp_.setAvgNbSteps((short) 1);
        areaApp_.setWildPokemon(new EqList<WildPk>());
        areaApp_.setWildPokemonFishing(new EqList<WildPk>());
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
        road_.getLevel().getBlocks().getVal(point(0,0)).setIndexApparition((short) 0);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(), road_);
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
        assertEq(0, area_.getIndexes().getVal(point(0,0)));
        assertEq(point(0,0), area_.getLeftTop());
        assertEq(0, area_.getInacessiblePoints().size());
        assertEq(1, area_.getDimsBlocks().size());
        assertEq(new Dims(3,6), area_.getDimsBlocks().getVal(point(0, 0)));
    }

    @Test
    public void initialize3Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        dataMap_.setPlaces(new ShortMap<Place>());
        Road road_ = vroad();
        AreaApparition areaApp_ = new AreaApparition();
        areaApp_.setMultFight((byte) 1);
        areaApp_.setAvgNbSteps((short) 1);
        areaApp_.setWildPokemon(new EqList<WildPk>());
        areaApp_.setWildPokemonFishing(new EqList<WildPk>());
        WildPk pk_ = new WildPk();
        pk_.setName("PTITARD");
        pk_.setItem("");
        pk_.setAbility("MOITEUR");
        pk_.setGender(Gender.FEMALE);
        pk_.setLevel((short) 2);
        areaApp_.getWildPokemon().add(pk_);
        pk_ = new WildPk();
        pk_.setName("PTITARD");
        pk_.setItem("");
        pk_.setAbility("MOITEUR");
        pk_.setGender(Gender.MALE);
        pk_.setLevel((short) 2);
        areaApp_.getWildPokemonFishing().add(pk_);
        areaApp_.initializeWildPokemon();
        road_.getLevelRoad().getWildPokemonAreas().add(areaApp_);
        road_.getLevel().getBlocks().getVal(point(0,0)).setIndexApparition((short) 0);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(), road_);
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
        assertEq(0, area_.getIndexes().getVal(point(0,0)));
        assertEq(point(0,0), area_.getLeftTop());
        assertEq(0, area_.getInacessiblePoints().size());
        assertEq(1, area_.getDimsBlocks().size());
        assertEq(new Dims(3,6), area_.getDimsBlocks().getVal(point(0, 0)));
    }

    @Test
    public void getPokemon1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        dataMap_.setPlaces(new ShortMap<Place>());
        City city_ = city();
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(), city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertEq(0, area_.getPokemon(point(0,0)).size());
        assertEq(0, area_.getPokemon(point(3,3)).size());
        assertEq(0, area_.getPokemon(point(6,6)).size());
        assertEq(0, area_.getPokemon(point(9,9)).size());
    }

    @Test
    public void getPokemon2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        dataMap_.setPlaces(new ShortMap<Place>());
        Road road_ = vroad();
        AreaApparition areaApp_ = new AreaApparition();
        areaApp_.setMultFight((byte) 1);
        areaApp_.setAvgNbSteps((short) 1);
        areaApp_.setWildPokemon(new EqList<WildPk>());
        areaApp_.setWildPokemonFishing(new EqList<WildPk>());
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
        road_.getLevel().getBlocks().getVal(point(0,0)).setIndexApparition((short) 0);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(), road_);
        LevelArea area_ = new LevelArea();
        area_.initialize(road_.getLevel());
        EqList<GenderName> list_;
        list_ = area_.getPokemon(point(0,0));
        assertEq(2, list_.size());
        assertEq("PIKACHU", list_.first().getName());
        assertEq(Gender.FEMALE, list_.first().getGender());
        assertEq("PIKACHU", list_.last().getName());
        assertEq(Gender.MALE, list_.last().getGender());
    }

    @Test
    public void isValid1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        dataMap_.setPlaces(new ShortMap<Place>());
        City city_ = city();
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(), city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
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
        dataMap_.setPlaces(new ShortMap<Place>());
        City city_ = city();
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(), city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertTrue(!area_.isValid(point(9,9), true));
        assertTrue(!area_.isValid(point(8,9), true));
        assertTrue(!area_.isValid(point(-1,-1), true));
        assertTrue(!area_.isValid(point(0,-1), true));
        assertTrue(area_.isValid(point(0,0), true));
        assertTrue(!area_.isValid(point(3,3), true));
    }

    @Test
    public void size1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        dataMap_.setPlaces(new ShortMap<Place>());
        City city_ = city();
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(), city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertEq(81, area_.size());
    }

    @Test
    public void allAccessible1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        dataMap_.setPlaces(new ShortMap<Place>());
        City city_ = city();
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(), city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertTrue(!area_.allAccessible());
    }

    @Test
    public void allAccessible2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        dataMap_.setPlaces(new ShortMap<Place>());
        Road road_ = vroad();
        AreaApparition areaApp_ = new AreaApparition();
        areaApp_.setMultFight((byte) 1);
        areaApp_.setAvgNbSteps((short) 1);
        areaApp_.setWildPokemon(new EqList<WildPk>());
        areaApp_.setWildPokemonFishing(new EqList<WildPk>());
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
        road_.getLevel().getBlocks().getVal(point(0,0)).setIndexApparition((short) 0);
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(), road_);
        LevelArea area_ = new LevelArea();
        area_.initialize(road_.getLevel());
        assertTrue(area_.allAccessible());
    }

    @Test
    public void isAccessible1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        dataMap_.setPlaces(new ShortMap<Place>());
        City city_ = city();
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(), city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertTrue(!area_.isAccessible(point(3, 3)));
    }

    @Test
    public void isAccessible2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new ObjectMap<Coords,EqList<Coords>>());
        dataMap_.setPlaces(new ShortMap<Place>());
        City city_ = city();
        dataMap_.getPlaces().put((short) dataMap_.getPlaces().size(), city_);
        LevelArea area_ = new LevelArea();
        area_.initialize(city_.getLevel());
        assertTrue(area_.isAccessible(point(2, 3)));
    }
}
