package aiki.map;

import aiki.db.EquallablePkUtil;
import aiki.map.util.PlaceInterConnects;
import aiki.util.*;
import code.util.core.StringUtil;
import org.junit.Test;

import aiki.fight.pokemon.GenderName;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymTrainer;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Block;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelOutdoor;
import aiki.map.levels.LevelRoad;
import aiki.map.levels.Link;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.tree.Tree;
import aiki.map.util.PlaceInterConnect;
import aiki.map.util.PlaceLevel;
import code.util.CustList;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;


public class SolutionTest extends EquallablePkUtil {

    private static final String VOIE = "voie";

    private static City city() {
        City c_ = new City();
        c_.setSavedlinks(new PlaceInterConnects());
        c_.setLinksWithCaves(new PointsLink());
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
        c_.setBuildings(new PointsBuilding());
        Gym gym_ = new Gym();
        gym_.setExitCity(new Point((short)1,(short)0));
        gym_.setLevel(new LevelIndoorGym());
        gym_.getLevel().setBlocks(new PointsBlock());
        block_ = new Block((short)6,(short)6, EnvironmentType.BUILDING, VOIE);
        gym_.getLevel().getBlocks().put(new Point((short)0,(short)0), block_);
        gym_.getIndoor().setGymLeaderCoords(new Point((short)1,(short)1));
        gym_.getIndoor().setGymTrainers(new PointsGymTrainer());
        c_.getBuildings().put(new Point((short)4,(short)5), gym_);
        return c_;
    }

    private static Road hroad() {
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
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        Block block_ = new Block((short)6,(short)3, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        road_.setLevel(level_);
        return road_;
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
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        Block block_ = new Block((short)3,(short)6, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        road_.setLevel(level_);
        return road_;
    }

    private static League league(Coords _access) {
        League league_ = new League();
        league_.setAccessCoords(_access);
        league_.setRooms(new CustList<LevelLeague>());
        LevelLeague level_ = new LevelLeague();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block((short)5,(short)5, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        level_.setAccessPoint(new Point((short)2,(short)0));
        level_.setNextLevelTarget(new Point((short)2,(short)4));
        level_.setTrainerCoords(new Point((short)2,(short)2));
        league_.getRooms().add(level_);
        level_ = new LevelLeague();
        level_.setBlocks(new PointsBlock());
        block_ = new Block((short)5,(short)5, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        level_.setAccessPoint(new Point((short)2,(short)0));
        level_.setNextLevelTarget(new Point());
        level_.setTrainerCoords(new Point((short)2,(short)2));
        league_.getRooms().add(level_);
        league_.setBegin(new Point((short)2,(short)4));
        return league_;
    }
    private static Coords coords(int _pl, int _level, int _x,int _y) {
        Coords c_ = new Coords();
        c_.setNumberPlace((short) _pl);
        c_.setLevel(new LevelPoint());
        c_.getLevel().setLevelIndex((byte) _level);
        c_.getLevel().setPoint(new Point((short)_x,(short)_y));
        return c_;
    }
    private static Coords coords(int _pl,int _xi,int _yi,int _x,int _y) {
        Coords c_ = new Coords();
        c_.setNumberPlace((short) _pl);
        c_.setInsideBuilding(new Point((short)_xi,(short)_yi));
        c_.setLevel(new LevelPoint());
        c_.getLevel().setLevelIndex((byte) 0);
        c_.getLevel().setPoint(new Point((short)_x,(short)_y));
        return c_;
    }
    private static Point point(int _x, int _y) {
        return new Point((short) _x,(short) _y);
    }
    private static DataMap dataMap() {
        City cityOne_ = city();
        City cityTwo_ = city();
        City cityThree_ = city();
        City cityFour_ = city();
        City cityFive_ = city();
        Road roadOne_ = vroad();
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
        roadOne_.getLevelRoad().getWildPokemonAreas().add(areaApp_);
        roadOne_.getLevel().getBlocks().getVal(point(0,0)).setIndexApparition((short) 0);
        Road roadTwo_ = hroad();
        Road roadThree_ = vroad();
        Road roadFour_ = hroad();
        pk_ = new WildPk();
        pk_.setName("ELECTHOR");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        roadFour_.getLevelRoad().getLegendaryPks().put(point(5, 0), pk_);
        Road roadFive_ = hroad();
        DualFight dual_ = new DualFight();
        dual_.setNames(new StringList("TRAINER_ONE","TRAINER_TWO"));
        dual_.setPt(point(1, 1));
        roadFive_.getLevelRoad().getDualFights().put(point(2, 1), dual_);
        Road roadSix_ = hroad();
        DataMap dataMap_ = new DataMap();
        dataMap_.setAccessCondition(new CoordsLists());
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
        dataMap_.getPlaces().add(league(coordsAccessLeague_));
        dataMap_.getPlaces().add(roadSix_);
        dataMap_.getPlaces().add(cityFive_);
        dataMap_.join((short)0, (short)1, new Point((short)4,(short)0), new Point((short)1,(short)5), Direction.UP);
        dataMap_.join((short)1, (short)2, new Point((short)1,(short)0), new Point((short)4,(short)8), Direction.UP);
        dataMap_.join((short)2, (short)3, new Point((short)8,(short)4), new Point((short)0,(short)1), Direction.RIGHT);
        dataMap_.join((short)3, (short)4, new Point((short)5,(short)1), new Point((short)0,(short)4), Direction.RIGHT);
        dataMap_.join((short)4, (short)5, new Point((short)4,(short)8), new Point((short)1,(short)0), Direction.DOWN);
        dataMap_.join((short)5, (short)6, new Point((short)1,(short)5), new Point((short)4,(short)0), Direction.DOWN);
        dataMap_.join((short)6, (short)7, new Point((short)0,(short)4), new Point((short)5,(short)1), Direction.LEFT);
        dataMap_.join((short)7, (short)0, new Point((short)0,(short)1), new Point((short)8,(short)4), Direction.LEFT);
        dataMap_.join((short)8, (short)5, new Point((short)0,(short)1), new Point((short)2,(short)4), Direction.LEFT);
        dataMap_.join((short)10, (short)0, new Point((short)5,(short)1), new Point((short)0,(short)5), Direction.RIGHT);
        dataMap_.join((short)10, (short)11, new Point((short)0,(short)1), new Point((short)8,(short)4), Direction.LEFT);
        Condition leaders_ = new Condition();
        leaders_.add(coords(0,4,5,1,1));
        leaders_.add(coords(2,4,5,1,1));
        leaders_.add(coords(4,4,5,1,1));
        leaders_.add(coords(6,4,5,1,1));
        dataMap_.getAccessCondition().put(coordsAccessLeague_, leaders_);
        Coords coordsBlock_ = new Coords();
        coordsBlock_.setNumberPlace((short) 7);
        coordsBlock_.setLevel(new LevelPoint());
        coordsBlock_.getLevel().setLevelIndex((byte) 0);
        coordsBlock_.getLevel().setPoint(new Point((short)3,(short)0));
        dataMap_.getAccessCondition().put(coordsBlock_, new Condition(leaders_));
        coordsBlock_ = new Coords();
        coordsBlock_.setNumberPlace((short) 7);
        coordsBlock_.setLevel(new LevelPoint());
        coordsBlock_.getLevel().setLevelIndex((byte) 0);
        coordsBlock_.getLevel().setPoint(new Point((short)3,(short)1));
        dataMap_.getAccessCondition().put(coordsBlock_, new Condition(leaders_));
        coordsBlock_ = new Coords();
        coordsBlock_.setNumberPlace((short) 7);
        coordsBlock_.setLevel(new LevelPoint());
        coordsBlock_.getLevel().setLevelIndex((byte) 0);
        coordsBlock_.getLevel().setPoint(new Point((short)3,(short)2));
        dataMap_.getAccessCondition().put(coordsBlock_, new Condition(leaders_));
        leaders_ = new Condition();
        leaders_.add(coords(0,4,5,1,1));
        Coords coords_;
        coords_ = new Coords();
        coords_.setNumberPlace((short) 3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)2,(short)0));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)2,(short)1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)2,(short)2));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        leaders_ = new Condition();
        leaders_.add(coords(2,4,5,1,1));
        coords_ = new Coords();
        coords_.setNumberPlace((short) 3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)3,(short)0));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)3,(short)1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)3,(short)2));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        leaders_ = new Condition();
        leaders_.add(coords(4,4,5,1,1));
        coords_ = new Coords();
        coords_.setNumberPlace((short) 5);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)0,(short)1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 5);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)1,(short)1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 5);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)2,(short)1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        leaders_ = new Condition();
        leaders_.add(coords(8,0,2,1));
        coords_ = new Coords();
        coords_.setNumberPlace((short) 8);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)3,(short)0));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 8);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)3,(short)1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 8);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)3,(short)2));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        Condition leagues_ = new Condition();
        leagues_.add(coords(9,0,2,4));
        coords_ = new Coords();
        coords_.setNumberPlace((short) 10);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)5,(short)0));
        dataMap_.getAccessCondition().put(coords_, leagues_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 10);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)5,(short)1));
        dataMap_.getAccessCondition().put(coords_, leagues_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 10);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)5,(short)2));
        dataMap_.getAccessCondition().put(coords_, leagues_);
        coords_ = new Coords();
        coords_.setNumberPlace((short) 0);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) 0);
        coords_.getLevel().setPoint(new Point((short)0,(short)0));
        dataMap_.setBegin(coords_);
        return dataMap_;
    }

    @Test
    public void new_Solution_Map_Map_Tree_1Test() {
        DataMap map_ = dataMap();
        Tree tree_ = new Tree();
        tree_.initialize(map_);
        map_.setTree(tree_);
        map_.initializeAccessibility();
        Solution solution_ = map_.getSolution();
        assertEq(6, solution_.getSteps().size());
        Step step_ = solution_.getSteps().get(0);
        assertEq(2, step_.getImportantsTrainers().size());
        assertTrue(step_.getImportantsTrainers().containsObj(coords(0, 4, 5, 1, 1)));
        assertTrue(step_.getImportantsTrainers().containsObj(coords(2, 4, 5, 1, 1)));
        assertEq(1, step_.getCaughtPokemonPlaceLevel().size());
        assertTrue(step_.getCaughtPokemonPlaceLevel().contains(new PlaceLevel((short)1,(byte)0)));
        assertEq(2, count(step_.getCaughtPokemonPlaceLevel().getVal(new PlaceLevel((short)1,(byte)0))));
        assertTrue(containsGenderName(step_.getCaughtPokemonPlaceLevel().getVal(new PlaceLevel((short)1,(byte)0)),new GenderName(Gender.FEMALE,"PIKACHU")));
        assertTrue(containsGenderName(step_.getCaughtPokemonPlaceLevel().getVal(new PlaceLevel((short)1,(byte)0)),new GenderName(Gender.MALE,"PIKACHU")));
        assertEq(18, step_.getCaughtPokemon().size());
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 0, 0)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 0, 1)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 0, 2)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 0, 3)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 0, 4)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 1, 0)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 1, 1)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 1, 2)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 1, 3)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 1, 4)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 1, 5)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 2, 0)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 2, 1)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 2, 2)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 2, 3)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 2, 4)));
        assertTrue(step_.getCaughtPokemon().contains(coords(1, 0, 2, 5)));
        assertEq(179, step_.getAccessibleCoords().size());
        step_ = solution_.getSteps().get(1);
        assertEq(1, step_.getImportantsTrainers().size());
        assertTrue(step_.getImportantsTrainers().containsObj(coords(4, 4, 5, 1, 1)));
        assertEq(0, step_.getCaughtPokemonPlaceLevel().size());
        assertEq(0, step_.getCaughtPokemon().size());
        assertEq(267, step_.getAccessibleCoords().size());
        step_ = solution_.getSteps().get(2);
        assertEq(2, step_.getImportantsTrainers().size());
        assertTrue(step_.getImportantsTrainers().containsObj(coords(6, 4, 5, 1, 1)));
        assertTrue(step_.getImportantsTrainers().containsObj(coords(8, 0, 2, 1)));
        assertEq(1, step_.getCaughtPokemonPlaceLevel().size());
        assertTrue(step_.getCaughtPokemonPlaceLevel().contains(new PlaceLevel((short)7,(byte)0)));
        assertEq(1, step_.getCaughtPokemonPlaceLevel().getVal(new PlaceLevel((short)7,(byte)0)).size());
        assertTrue(containsGenderName(step_.getCaughtPokemonPlaceLevel().getVal(new PlaceLevel((short)7,(byte)0)),new GenderName(Gender.NO_GENDER,"ELECTHOR")));
        assertEq(1, step_.getCaughtPokemon().size());
        assertTrue(step_.getCaughtPokemon().contains(coords(7, 0, 5, 0)));
        assertEq(370, step_.getAccessibleCoords().size());
        step_ = solution_.getSteps().get(3);
        assertEq(1, step_.getImportantsTrainers().size());
        assertTrue(step_.getImportantsTrainers().containsObj(coords(9, 0, 2, 4)));
        assertEq(0, step_.getCaughtPokemonPlaceLevel().size());
        assertEq(0, step_.getCaughtPokemon().size());
        assertEq(430, step_.getAccessibleCoords().size());
        step_ = solution_.getSteps().get(4);
        assertEq(1, step_.getImportantsTrainers().size());
        assertTrue(step_.getImportantsTrainers().containsObj(coords(11, 4, 5, 1, 1)));
        assertEq(0, step_.getCaughtPokemonPlaceLevel().size());
        assertEq(0, step_.getCaughtPokemon().size());
        assertEq(521, step_.getAccessibleCoords().size());
        step_ = solution_.getSteps().get(5);
        assertEq(0, step_.getImportantsTrainers().size());
        assertEq(0, step_.getCaughtPokemonPlaceLevel().size());
        assertEq(0, step_.getCaughtPokemon().size());
        assertEq(521, step_.getAccessibleCoords().size());
    }

    private static int count(CustList<GenderName> _list) {
        CustList<GenderName> g_ = new CustList<GenderName>();
        for (GenderName pk_: _list) {
            boolean cont_ = false;
            for (GenderName s : g_) {
                if (!StringUtil.quickEq(s.getName(), pk_.getName())) {
                    continue;
                }
                if (s.getGender() != pk_.getGender()) {
                    continue;
                }
                cont_ = true;
            }
            if (cont_) {
                continue;
            }
            g_.add(pk_);
        }
        return g_.size();
    }
    private static boolean containsGenderName(CustList<GenderName> _list, GenderName _t) {
        for (GenderName t: _list) {
            if (eq(_t, t)) {
                return true;
            }
        }
        return false;
    }

    private static boolean eq(GenderName _current, GenderName _g) {
        if (!StringUtil.quickEq(_current.getName(), _g.getName())) {
            return false;
        }
        return _current.getGender() == _g.getGender();
    }
}
