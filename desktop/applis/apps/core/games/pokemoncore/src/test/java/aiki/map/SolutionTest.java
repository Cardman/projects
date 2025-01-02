package aiki.map;

import aiki.db.EquallablePkUtil;
import aiki.map.levels.*;
import aiki.map.util.PlaceInterConnects;
import aiki.util.*;
import org.junit.Test;

import aiki.fight.pokemon.GenderName;
import aiki.map.buildings.Gym;
import aiki.map.characters.DualFight;
import aiki.map.enums.Direction;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.tree.Tree;
import aiki.map.util.PlaceLevel;
import code.util.CustList;

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
        city_.getBlocks().put(newPoint(0,0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(0,3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(0,6), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(3,0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.NOTHING, VOIE);
        city_.getBlocks().put(newPoint(3,3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(3,6), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(6,0), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(6,3), block_);
        block_ = new Block((short)3,(short)3, EnvironmentType.ROAD, VOIE);
        city_.getBlocks().put(newPoint(6,6), block_);
        c_.setLevel(city_);
        c_.setBuildings(new PointsBuilding());
        Gym gym_ = new Gym();
        gym_.setExitCity(newPoint(1,0));
        gym_.setLevel(new LevelIndoorGym());
        gym_.getLevel().setBlocks(new PointsBlock());
        block_ = new Block((short)6,(short)6, EnvironmentType.BUILDING, VOIE);
        gym_.getLevel().getBlocks().put(newPoint(0,0), block_);
        gym_.getIndoor().setGymLeaderCoords(newPoint(1,1));
        gym_.getIndoor().setGymTrainers(new PointsGymTrainer());
        c_.getBuildings().put(newPoint(4,5), gym_);
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
        level_.setWildPokemonAreas(new CustList<AbsAreaApparition>());
        Block block_ = new Block((short)6,(short)3, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(newPoint(0,0), block_);
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
        level_.setWildPokemonAreas(new CustList<AbsAreaApparition>());
        Block block_ = new Block((short)3,(short)6, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(newPoint(0,0), block_);
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
        level_.getBlocks().put(newPoint(0,0), block_);
        level_.setAccessPoint(newPoint(2,0));
        level_.setNextLevelTarget(newPoint(2,4));
        level_.setTrainerCoords(newPoint(2,2));
        league_.getRooms().add(level_);
        level_ = new LevelLeague();
        level_.setBlocks(new PointsBlock());
        block_ = new Block((short)5,(short)5, EnvironmentType.ROAD, VOIE);
        level_.getBlocks().put(newPoint(0,0), block_);
        level_.setAccessPoint(newPoint(2,0));
        level_.setNextLevelTarget(new NullablePoint());
        level_.setTrainerCoords(newPoint(2,2));
        league_.getRooms().add(level_);
        league_.setBegin(newPoint(2,4));
        return league_;
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
        roadOne_.getLevel().getBlocks().getVal(newPoint(0,0)).setIndexApparition(0);
        Road roadTwo_ = hroad();
        Road roadThree_ = vroad();
        Road roadFour_ = hroad();
        pk_ = new WildPk();
        pk_.setName("ELECTHOR");
        pk_.setItem("");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 2);
        roadFour_.getLevelRoad().getLegendaryPks().put(newPoint(5, 0), pk_);
        Road roadFive_ = hroad();
        DualFight dual_ = new DualFight();
        dual_.setNames(new StringList("TRAINER_ONE","TRAINER_TWO"));
        dual_.setPt(newPoint(1, 1));
        roadFive_.getLevelRoad().getDualFights().put(newPoint(2, 1), dual_);
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
        coordsAccessLeague_.setNumberPlace(8);
        coordsAccessLeague_.setLevel(new LevelPoint());
        coordsAccessLeague_.getLevel().setLevelIndex(0);
        coordsAccessLeague_.getLevel().setPoint(newPoint(5,1));
        dataMap_.getPlaces().add(league(coordsAccessLeague_));
        dataMap_.getPlaces().add(roadSix_);
        dataMap_.getPlaces().add(cityFive_);
        dataMap_.join((short)0, (short)1, newPoint(4,0), newPoint(1,5), Direction.UP);
        dataMap_.join((short)1, (short)2, newPoint(1,0), newPoint(4,8), Direction.UP);
        dataMap_.join((short)2, (short)3, newPoint(8,4), newPoint(0,1), Direction.RIGHT);
        dataMap_.join((short)3, (short)4, newPoint(5,1), newPoint(0,4), Direction.RIGHT);
        dataMap_.join((short)4, (short)5, newPoint(4,8), newPoint(1,0), Direction.DOWN);
        dataMap_.join((short)5, (short)6, newPoint(1,5), newPoint(4,0), Direction.DOWN);
        dataMap_.join((short)6, (short)7, newPoint(0,4), newPoint(5,1), Direction.LEFT);
        dataMap_.join((short)7, (short)0, newPoint(0,1), newPoint(8,4), Direction.LEFT);
        dataMap_.join((short)8, (short)5, newPoint(0,1), newPoint(2,4), Direction.LEFT);
        dataMap_.join((short)10, (short)0, newPoint(5,1), newPoint(0,5), Direction.RIGHT);
        dataMap_.join((short)10, (short)11, newPoint(0,1), newPoint(8,4), Direction.LEFT);
        Condition leaders_ = new Condition();
        leaders_.add(newCoords(0,4,5,1,1));
        leaders_.add(newCoords(2,4,5,1,1));
        leaders_.add(newCoords(4,4,5,1,1));
        leaders_.add(newCoords(6,4,5,1,1));
        dataMap_.getAccessCondition().put(coordsAccessLeague_, leaders_);
        Coords coordsBlock_ = new Coords();
        coordsBlock_.setNumberPlace(7);
        coordsBlock_.setLevel(new LevelPoint());
        coordsBlock_.getLevel().setLevelIndex(0);
        coordsBlock_.getLevel().setPoint(newPoint(3,0));
        dataMap_.getAccessCondition().put(coordsBlock_, new Condition(leaders_));
        coordsBlock_ = new Coords();
        coordsBlock_.setNumberPlace(7);
        coordsBlock_.setLevel(new LevelPoint());
        coordsBlock_.getLevel().setLevelIndex(0);
        coordsBlock_.getLevel().setPoint(newPoint(3,1));
        dataMap_.getAccessCondition().put(coordsBlock_, new Condition(leaders_));
        coordsBlock_ = new Coords();
        coordsBlock_.setNumberPlace(7);
        coordsBlock_.setLevel(new LevelPoint());
        coordsBlock_.getLevel().setLevelIndex(0);
        coordsBlock_.getLevel().setPoint(newPoint(3,2));
        dataMap_.getAccessCondition().put(coordsBlock_, new Condition(leaders_));
        leaders_ = new Condition();
        leaders_.add(newCoords(0,4,5,1,1));
        Coords coords_;
        coords_ = new Coords();
        coords_.setNumberPlace(3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(2,0));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(2,1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(2,2));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        leaders_ = new Condition();
        leaders_.add(newCoords(2,4,5,1,1));
        coords_ = new Coords();
        coords_.setNumberPlace(3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(3,0));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(3,1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(3);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(3,2));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        leaders_ = new Condition();
        leaders_.add(newCoords(4,4,5,1,1));
        coords_ = new Coords();
        coords_.setNumberPlace(5);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(0,1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(5);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(1,1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(5);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(2,1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        leaders_ = new Condition();
        leaders_.add(newCoords(8,0,2,1));
        coords_ = new Coords();
        coords_.setNumberPlace(8);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(3,0));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(8);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(3,1));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        coords_ = new Coords();
        coords_.setNumberPlace(8);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(3,2));
        dataMap_.getAccessCondition().put(coords_, leaders_);
        Condition leagues_ = new Condition();
        leagues_.add(newCoords(9,0,2,4));
        coords_ = new Coords();
        coords_.setNumberPlace(10);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(5,0));
        dataMap_.getAccessCondition().put(coords_, leagues_);
        coords_ = new Coords();
        coords_.setNumberPlace(10);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(5,1));
        dataMap_.getAccessCondition().put(coords_, leagues_);
        coords_ = new Coords();
        coords_.setNumberPlace(10);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(5,2));
        dataMap_.getAccessCondition().put(coords_, leagues_);
        coords_ = new Coords();
        coords_.setNumberPlace(0);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(0);
        coords_.getLevel().setPoint(newPoint(0,0));
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
        assertTrue(step_.getImportantsTrainers().containsObj(newCoords(0, 4, 5, 1, 1)));
        assertTrue(step_.getImportantsTrainers().containsObj(newCoords(2, 4, 5, 1, 1)));
        assertEq(1, step_.getCaughtPokemonPlaceLevel().size());
        assertTrue(step_.getCaughtPokemonPlaceLevel().contains(new PlaceLevel((short)1,(byte)0)));
        assertEq(2, count(step_.getCaughtPokemonPlaceLevel().getVal(new PlaceLevel((short)1,(byte)0))));
        assertTrue(containsGenderName(step_.getCaughtPokemonPlaceLevel().getVal(new PlaceLevel((short)1,(byte)0)),new GenderName(Gender.FEMALE,"PIKACHU")));
        assertTrue(containsGenderName(step_.getCaughtPokemonPlaceLevel().getVal(new PlaceLevel((short)1,(byte)0)),new GenderName(Gender.MALE,"PIKACHU")));
        assertEq(18, step_.getCaughtPokemon().size());
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 0, 0)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 0, 1)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 0, 2)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 0, 3)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 0, 4)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 1, 0)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 1, 1)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 1, 2)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 1, 3)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 1, 4)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 1, 5)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 2, 0)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 2, 1)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 2, 2)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 2, 3)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 2, 4)));
        assertTrue(step_.getCaughtPokemon().contains(newCoords(1, 0, 2, 5)));
        assertEq(179, step_.getAccessibleCoords().size());
        step_ = solution_.getSteps().get(1);
        assertEq(1, step_.getImportantsTrainers().size());
        assertTrue(step_.getImportantsTrainers().containsObj(newCoords(4, 4, 5, 1, 1)));
        assertEq(0, step_.getCaughtPokemonPlaceLevel().size());
        assertEq(0, step_.getCaughtPokemon().size());
        assertEq(267, step_.getAccessibleCoords().size());
        step_ = solution_.getSteps().get(2);
        assertEq(2, step_.getImportantsTrainers().size());
        assertTrue(step_.getImportantsTrainers().containsObj(newCoords(6, 4, 5, 1, 1)));
        assertTrue(step_.getImportantsTrainers().containsObj(newCoords(8, 0, 2, 1)));
        assertEq(1, step_.getCaughtPokemonPlaceLevel().size());
        assertTrue(step_.getCaughtPokemonPlaceLevel().contains(new PlaceLevel((short)7,(byte)0)));
        assertEq(1, step_.getCaughtPokemonPlaceLevel().getVal(new PlaceLevel((short)7,(byte)0)).size());
        assertTrue(containsGenderName(step_.getCaughtPokemonPlaceLevel().getVal(new PlaceLevel((short)7,(byte)0)),new GenderName(Gender.NO_GENDER,"ELECTHOR")));
        assertEq(1, step_.getCaughtPokemon().size());
        assertTrue(step_.getCaughtPokemon().contains(newCoords(7, 0, 5, 0)));
        assertEq(370, step_.getAccessibleCoords().size());
        step_ = solution_.getSteps().get(3);
        assertEq(1, step_.getImportantsTrainers().size());
        assertTrue(step_.getImportantsTrainers().containsObj(newCoords(9, 0, 2, 4)));
        assertEq(0, step_.getCaughtPokemonPlaceLevel().size());
        assertEq(0, step_.getCaughtPokemon().size());
        assertEq(430, step_.getAccessibleCoords().size());
        step_ = solution_.getSteps().get(4);
        assertEq(1, step_.getImportantsTrainers().size());
        assertTrue(step_.getImportantsTrainers().containsObj(newCoords(11, 4, 5, 1, 1)));
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
//        CustList<GenderName> g_ = new CustList<GenderName>();
//        for (GenderName pk_: _list) {
//            boolean cont_ = false;
//            for (GenderName s : g_) {
//                if (!StringUtil.quickEq(s.getName(), pk_.getName())) {
//                    continue;
//                }
//                if (s.getGender() != pk_.getGender()) {
//                    continue;
//                }
//                cont_ = true;
//            }
//            if (cont_) {
//                continue;
//            }
//            g_.add(pk_);
//        }
//        return g_.size();
        return PlaceLevelsCustListGenderName.filter(_list).size();
    }
    private static boolean containsGenderName(CustList<GenderName> _list, GenderName _t) {
//        for (GenderName t: _list) {
//            if (eq(_t, t)) {
//                return true;
//            }
//        }
//        return false;
        return PlaceLevelsCustListGenderName.containsGender(_list, _t);
    }

//    private static boolean eq(GenderName _current, GenderName _g) {
//        return PlaceLevelsCustListGenderName.match(_current, _g);
////        if (!StringUtil.quickEq(_current.getName(), _g.getName())) {
////            return false;
////        }
////        return _current.getGender() == _g.getGender();
//    }
}
