package aiki.db;

import aiki.facade.SexListImpl;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.EvolutionMove;
import aiki.fight.pokemon.evolution.EvolutionMoveType;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.instances.Instances;
import aiki.map.Condition;
import aiki.map.DataMap;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.*;
import aiki.map.characters.enums.SellType;
import aiki.map.enums.Direction;
import aiki.map.levels.*;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.*;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonTeam;
import aiki.map.pokemon.WildPk;
import aiki.map.util.*;
import aiki.util.*;
import code.maths.montecarlo.DefaultGenerator;
import code.util.*;
import org.junit.Test;

public final class DataBaseValidationMapTest extends DataBaseValidationCommon {

//    @Test
//    public void link1Test() {
//        Link l_ = Link.newLink("l'"+DataBase.DEF_DIR_UP+"'5;10_8,4");
//        assertEq("l'"+DataBase.DEF_DIR_UP+"'5;10_8,4",l_.display());
//    }
//
//    @Test
//    public void link2Test() {
//        Link l_ = Link.newLink("l'U'5;10_8,4");
//        assertEq("l'"+DataBase.DEF_DIR_UP+"'5;10_8,4",l_.display());
//    }

    @Test
    public void linkTest() {
        Link l_ = Link.newLink("l'5;10_8,4");
        assertEq("l'5;10_8,4",l_.display());
        assertEq(Direction.UP,Direction.getDirectionByName(Direction.UP.getDirName()));
    }
    @Test
    public void fail1Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        map_.getPlaces().add(Instances.newRoad());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( -1);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail2Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        map_.getPlaces().add(Instances.newRoad());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail3Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road r_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        block_.setIndexApparition(10);
        AreaApparition area_ = Instances.newAreaApparition();
        area_.getWildPokemonFishing().add(Instances.newWildPk());
        area_.setMultFight(5);
        r_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = Instances.newAreaApparition();
        area_.setMultFight(-1);
        r_.getLevelRoad().getWildPokemonAreas().add(area_);
        r_.getLevelRoad().getBlocks().addEntry(newPoint(0,0), block_);
        TrainerMultiFights tr_ = Instances.newTrainerMultiFights();
        tr_.setMultiplicityFight( 5);
        PokemonTeam team_ = Instances.newPokemonTeam();
        PkTrainer pkTr_ = Instances.newPkTrainer();
        team_.getTeam().add(pkTr_);
        pkTr_ = Instances.newPkTrainer();
        pkTr_.getMoves().add(ELECTRICK);
        pkTr_.getMoves().add(CHARGE);
        pkTr_.getMoves().add(CHARGE2);
        pkTr_.getMoves().add(CHARGE3);
        pkTr_.getMoves().add(CHARGE4);
        pkTr_.getMoves().add(TREMPETTE);
        pkTr_.getMoves().add(TREMPETTE2);
        pkTr_.getMoves().add(TREMPETTE3);
        pkTr_.getMoves().add(LUTTE);
        team_.getTeam().add(pkTr_);
        tr_.getTeamsRewards().add(team_);
        tr_.getTeamsRewards().add(Instances.newPokemonTeam());
        r_.getLevelRoad().getCharacters().addEntry(newPoint(1,1), tr_);
        TrainerMultiFights trTwo_ = Instances.newTrainerMultiFights();
        trTwo_.setMultiplicityFight(0);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(2,2), trTwo_);
        DealerItem deal_ = Instances.newDealerItem();
        deal_.getItems().add(ELECTRICK);
        deal_.getTechnicalMoves().add(100);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(5,5), deal_);
        DualFight dual_ = Instances.newDualFight();
        dual_.setPt(newPoint(4,4));
        r_.getLevelRoad().getDualFights().addEntry(newPoint(3,3), dual_);
        r_.getLevelRoad().getItems().addEntry(newPoint(5,5),ELECTRICK);
        map_.getPlaces().add(r_);
        map_.getPlaces().add(Instances.newCity());
        City city_ = Instances.newCity();
        PokemonCenter pokemonCenter_ = Instances.newPokemonCenter();
        pokemonCenter_.getIndoor().getBlocks().addEntry(newPoint(0,0),Instances.newBlock());
        Seller seller_ = Instances.newSeller();
        pokemonCenter_.getIndoor().getGerants().addEntry(newPoint(1,1), seller_);
        seller_ = Instances.newSeller();
        seller_.getItems().add(ELECTRICK);
        seller_.setSell(SellType.TM);
        pokemonCenter_.getIndoor().getGerants().addEntry(newPoint(1,2),seller_);
        seller_ = Instances.newSeller();
        seller_.getTm().add(100);
        seller_.setSell(SellType.ITEM);
        pokemonCenter_.getIndoor().getGerants().addEntry(newPoint(1,3),seller_);
        seller_ = Instances.newSeller();
        seller_.getItems().add(ELECTRICK);
        seller_.getTm().add(100);
        seller_.setSell(SellType.MOVE);
        pokemonCenter_.getIndoor().getGerants().addEntry(newPoint(1,4),seller_);
        pokemonCenter_.getIndoor().getGerants().addEntry(newPoint(1,5),Instances.newGymTrainer());
        pokemonCenter_.getIndoor().setStorageCoords(newPoint(1,5));
        city_.getBuildings().addEntry(newPoint(0,0), pokemonCenter_);
        Gym gym_ = Instances.newGym();
        GymLeader gymLeader_ = Instances.newGymLeader();
        gymLeader_.setTm(100);
        block_ = Instances.newBlock();
        block_.setType(EnvironmentType.NOTHING);
        block_.setWidth(1);
        block_.setHeight(1);
        gym_.getIndoor().getBlocks().addEntry(newPoint(0,0),block_);
        gym_.getIndoor().setGymLeader(gymLeader_);
        gym_.getIndoor().getGymTrainers().addEntry(newPoint(2,2),Instances.newGymTrainer());
        gym_.getIndoor().setGymLeaderCoords(newPoint(2,2));
        gym_.setExitCity(newPoint(10,10));
        city_.getBuildings().addEntry(newPoint(1,1), gym_);
        map_.getPlaces().add(city_);
        city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(1,1),block_);
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,0),block_);
        block_ = Instances.newBlock();
        block_.setHeight(-2);
        block_.setWidth(2);
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(5,5),block_);
        block_ = Instances.newBlock();
        block_.setType(EnvironmentType.NOTHING);
        block_.setHeight(2);
        block_.setWidth(2);
        block_.setIndexApparition( 0);
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(10,0),block_);
        map_.getPlaces().add(city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail4Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road r_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        block_.setIndexApparition(0);
        AreaApparition area_ = Instances.newAreaApparition();
        WildPk wpk_ = Instances.newWildPk();
        wpk_.setAbility(ELECTRICK);
        area_.getWildPokemonFishing().add(wpk_);
        wpk_ = Instances.newWildPk();
        wpk_.setItem(ELECTRICK);
        area_.getWildPokemonFishing().add(wpk_);
        wpk_ = Instances.newWildPk();
        wpk_.setAbility(ELECTRICK);
        wpk_.setItem(ELECTRICK);
        area_.getWildPokemonFishing().add(wpk_);
        area_.setMultFight(5);
        r_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = Instances.newAreaApparition();
        area_.setMultFight(-1);
        r_.getLevelRoad().getWildPokemonAreas().add(area_);
        r_.getLevelRoad().getBlocks().addEntry(newPoint(0,0), block_);
        DualFight dual_ = Instances.newDualFight();
        dual_.setPt(newPoint(4,4));
        r_.getLevelRoad().getDualFights().addEntry(newPoint(3,3), dual_);
        map_.getPlaces().add( r_);
        map_.getPlaces().add( Instances.newCity());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        data_.initializeWildPokemon();
        assertTrue(data_.isError());
    }

    @Test
    public void fail5Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road r_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        block_.setIndexApparition(0);
        r_.getLevelRoad().getBlocks().addEntry(newPoint(0,0), block_);
        map_.getPlaces().add( r_);
        League league_ = Instances.newLeague();
        LevelLeague room_ = Instances.newLevelLeague();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        block_.setType(EnvironmentType.NOTHING);
        room_.getBlocks().addEntry(newPoint(0,0), block_);
        room_.setAccessPoint(newPoint(1,1));
        room_.setTrainerCoords(newPoint(1,1));
        room_.setNextLevelTarget(newPoint(1,1));
        league_.getRooms().add(room_);
        room_ = Instances.newLevelLeague();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        block_.setType(EnvironmentType.NOTHING);
        room_.getBlocks().addEntry(newPoint(0,0), block_);
        room_.setAccessPoint(new NullablePoint());
        room_.setTrainerCoords(newPoint(1,1));
        room_.setNextLevelTarget(newPoint(1,1));
        league_.getRooms().add(room_);
        league_.setBegin(newPoint(1,1));
        map_.getPlaces().add( league_);
        league_ = Instances.newLeague();
        league_.setAccessCoords(newCoords(1,5,2,2));
        map_.getPlaces().add( league_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        data_.initializeWildPokemon();
        assertTrue(data_.isError());
        IntMap<CustList<PlaceInterConnect>> v_ = new IntMap<CustList<PlaceInterConnect>>();
        DataMap.merge(v_,new PlaceInterConnect(newPoint(0,0),Direction.RIGHT),newPoint(0,0),0);
        DataMap.merge(v_,new PlaceInterConnect(newPoint(0,0),Direction.RIGHT),newPoint(0,0),0);
    }

    @Test
    public void fail6Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road r_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        block_.setIndexApparition(10);
        r_.getLevelRoad().getBlocks().addEntry(newPoint(0,0), block_);
        DealerItem deal_ = Instances.newDealerItem();
        deal_.getItems().add(ELECTRICK);
        deal_.getTechnicalMoves().add(100);
        r_.getLevelRoad().getCharacters().addEntry(newPoint(5,5), deal_);
        DualFight dual_ = Instances.newDualFight();
        dual_.setPt(newPoint(5,5));
        r_.getLevelRoad().getDualFights().addEntry(newPoint(5,5), dual_);
        r_.getLevelRoad().getLegendaryPks().addEntry(newPoint(5,5),Instances.newWildPk());
        r_.getLevelRoad().getTm().addEntry(newPoint(5,5),100);
        r_.getLevelRoad().getHm().addEntry(newPoint(5,5),100);
        map_.getPlaces().add( r_);
        map_.getPlaces().add( Instances.newCity());
        City city_ = Instances.newCity();
        Gym gym_ = Instances.newGym();
        gym_.setExitCity(newPoint(10,10));
        city_.getBuildings().addEntry(newPoint(1,1), gym_);
        city_.getBuildings().addEntry(newPoint(1,2), Instances.newGym());
        city_.getBuildings().addEntry(newPoint(1,3), Instances.newPokemonCenter());
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail7Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        block_.setIndexApparition(10);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(5,2,5,8,4,9)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setLevelIndex(5);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(5,2,5,8,4,9)));
        map_.getPlaces().add( r_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail8Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        block_.setIndexApparition(10);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(5,2,5,8,4,9)));
        map_.getPlaces().add( r_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail9Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        levelCave_.getDualFights().addEntry(newPoint(0,0),Instances.newDualFight());
        levelCave_.getCharacters().addEntry(newPoint(0,0),Instances.newTrainerMultiFights());
        levelCave_.getItems().addEntry(newPoint(0,0),ELECTRICK);
        levelCave_.getTm().addEntry(newPoint(0,0),0);
        levelCave_.getHm().addEntry(newPoint(0,0),0);
        levelCave_.getLegendaryPks().addEntry(newPoint(0,0),Instances.newWildPk());
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(newPoint(1,1));
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(5,2,5,8,4,9)));
        map_.getPlaces().add( r_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,0), block_);
        road_.getLinksWithCaves().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        road_.getLinksWithCaves().addEntry(newPoint(1,0),new Link(ELECTRICK,newCoords(0,0,0,0)));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getLinksWithCaves().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        city_.getLinksWithCaves().addEntry(newPoint(1,0),new Link(ELECTRICK,newCoords(0,0,0,0)));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail10Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        levelCave_.getItems().addEntry(newPoint(1,0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(newPoint(1,1));
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(5,2,5,8,4,9)));
        map_.getPlaces().add( r_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,0), block_);
        road_.getLinksWithCaves().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        road_.getLinksWithCaves().addEntry(newPoint(1,0),new Link(ELECTRICK,newCoords(0,0,1,0)));
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail11Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        levelCave_.getItems().addEntry(newPoint(1,0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(newPoint(1,1));
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,0), block_);
        road_.getLinksWithCaves().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        road_.getLinksWithCaves().addEntry(newPoint(1,0),new Link(ELECTRICK,newCoords(0,0,1,0)));
        road_.getLevelRoad().getItems().addEntry(newPoint(1,0),ELECTRICK);
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail112Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        levelCave_.getItems().addEntry(newPoint(1,0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        levelCave_.getItems().addEntry(newPoint(1,0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(newPoint(1,1));
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,0), block_);
        road_.getLinksWithCaves().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        road_.getLinksWithCaves().addEntry(newPoint(1,0),new Link(ELECTRICK,newCoords(0,0,1,0)));
        road_.getLevelRoad().getItems().addEntry(newPoint(1,0),ELECTRICK);
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    private static void initPlaces(DataMap _map) {
        _map.setPlaces(new CustList<Place>());
    }

    @Test
    public void fail13Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        levelCave_.getItems().addEntry(newPoint(1,1),ELECTRICK);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        levelCave_.getItems().addEntry(newPoint(1,0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(newPoint(1,1));
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,0), block_);
        road_.getLinksWithCaves().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        road_.getLinksWithCaves().addEntry(newPoint(1,0),new Link(ELECTRICK,newCoords(0,0,1,0)));
        road_.getLevelRoad().getItems().addEntry(newPoint(1,0),ELECTRICK);
        map_.getPlaces().add( road_);
        map_.getPlaces().add( Instances.newCave());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail14Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        levelCave_.getItems().addEntry(newPoint(1,0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        levelCave_.getItems().addEntry(newPoint(1,0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(newPoint(1,1));
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getLinksWithCaves().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        city_.getLinksWithCaves().addEntry(newPoint(1,0),new Link(ELECTRICK,newCoords(0,0,1,0)));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail15Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,0),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(newPoint(1,1));
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getLinksWithCaves().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        city_.getLinksWithCaves().addEntry(newPoint(1,0),new Link(ELECTRICK,newCoords(0,0,1,0)));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail16Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(newPoint(1,1));
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(2,2),Direction.RIGHT),newCoords(2,0,5,5));
        map_.getPlaces().add( city_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(2,2),Direction.RIGHT),newCoords(1,0,5,5));
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail17Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        map_.getPlaces().add(Instances.newCity());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( -1);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0,2,2, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail18Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        City city_ = Instances.newCity();
        city_.getBuildings().addEntry(newPoint(2,2),Instances.newPokemonCenter());
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( -1);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0,2,2, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
        assertTrue(data_.getMap().getAreaByCoords(new Coords()).isVirtual());
    }

    @Test
    public void fail19Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(newPoint(1,1));
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        Link lk_ = new Link(ELECTRICK, newCoords(2, 0, 1, 0));
        city_.getLinksWithCaves().addEntry(newPoint(0,0), lk_);
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(2,2),Direction.RIGHT),newCoords(2,0,5,5));
        map_.getPlaces().add( city_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(2,2),Direction.RIGHT),newCoords(1,0,5,5));
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail20Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(newPoint(1,1));
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,10)));
        map_.getPlaces().add( r_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        Link lk_ = new Link(ELECTRICK, newCoords(10, 0, 1, 1));
        city_.getLinksWithCaves().addEntry(newPoint(0,0), lk_);
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(2,2),Direction.RIGHT),newCoords(2,0,5,5));
        map_.getPlaces().add( city_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(2,2),Direction.RIGHT),newCoords(1,0,5,5));
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail21Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(newPoint(1,1));
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,10)));
        map_.getPlaces().add( r_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        Link lk_ = new Link(ELECTRICK, newCoords(0, 1, 1, 1));
        city_.getLinksWithCaves().addEntry(newPoint(0,0), lk_);
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(2,2),Direction.RIGHT),newCoords(2,0,5,5));
        map_.getPlaces().add( city_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(2,2),Direction.RIGHT),newCoords(1,0,5,5));
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail22Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getBuildings().addEntry(newPoint(1,1),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail23Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,1),Direction.RIGHT),newCoords(1,0,0,0));
        DualFight dualFight_ = Instances.newDualFight();
        dualFight_.setPt(newPoint(1,1));
        road_.getLevelRoad().getDualFights().addEntry(newPoint(0,1),dualFight_);
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getBuildings().addEntry(newPoint(1,0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        map_.getAccessCondition().put(newCoords(1,0,1,1),Condition.newList(newCoords(0,0,0,1)));
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail24Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("1");
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.getEffects().add(effectDamage_);
        damagingMoveData_.setAccuracy("1");
        damagingMoveData_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.setPp( 1);
        damagingMoveData_.setTypes(new StringList(ELECTRICK));
        data_.completeMembers(CHARGE,damagingMoveData_);
        PokemonData pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv(1,1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove(1,CHARGE));
        pokemonData_.setBaseEvo(PIKACHU);
        data_.completeMembers(PIKACHU,pokemonData_);
        pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv(1,1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove(1,CHARGE));
        pokemonData_.setGenderRep(GenderRepartition.LEGENDARY);
        pokemonData_.setBaseEvo(PIKACHU2);
        data_.completeMembers(PIKACHU2,pokemonData_);
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,1),Direction.RIGHT),newCoords(1,0,0,0));
        DualFight dualFight_ = Instances.newDualFight();
        dualFight_.setPt(newPoint(1,1));
        road_.getLevelRoad().getDualFights().addEntry(newPoint(0,1),dualFight_);
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getBuildings().addEntry(newPoint(1,0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        map_.getAccessCondition().put(newCoords(1,0,1,1),Condition.newList(newCoords(0,0,0,1)));
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail25Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("1");
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.getEffects().add(effectDamage_);
        damagingMoveData_.setAccuracy("1");
        damagingMoveData_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.setPp( 1);
        damagingMoveData_.setTypes(new StringList(ELECTRICK));
        data_.completeMembers(CHARGE,damagingMoveData_);
        PokemonData pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv(1,1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove(1,CHARGE));
        pokemonData_.setBaseEvo(PIKACHU);
        pokemonData_.setGenderRep(GenderRepartition.MIXED);
        data_.completeMembers(PIKACHU,pokemonData_);
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        block_.setIndexApparition(0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getBuildings().addEntry(newPoint(1,0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail26Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("1");
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.getEffects().add(effectDamage_);
        damagingMoveData_.setAccuracy("1");
        damagingMoveData_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.setPp( 1);
        damagingMoveData_.setTypes(new StringList(ELECTRICK));
        data_.completeMembers(CHARGE,damagingMoveData_);
        damagingMoveData_ = Instances.newDamagingMoveData();
        effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("1");
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.getEffects().add(effectDamage_);
        damagingMoveData_.setAccuracy("1");
        damagingMoveData_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.setPp( 1);
        damagingMoveData_.setTypes(new StringList(ELECTRICK));
        data_.completeMembers(CHARGE2,damagingMoveData_);
        PokemonData pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv(1,1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove(1,CHARGE));
        pokemonData_.setBaseEvo(PIKACHU);
        EvolutionMove evolutionMove_ = Instances.newEvolutionMove();
        evolutionMove_.setMove(CHARGE2);
        pokemonData_.getEvolutions().addEntry(PIKACHU2, evolutionMove_);
        pokemonData_.setGenderRep(GenderRepartition.MIXED);
        data_.completeMembers(PIKACHU,pokemonData_);
        pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv(1,1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove(1,CHARGE));
        pokemonData_.setBaseEvo(PIKACHU);
        pokemonData_.setGenderRep(GenderRepartition.MIXED);
        data_.completeMembers(PIKACHU2,pokemonData_);
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        block_.setIndexApparition(0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getBuildings().addEntry(newPoint(1,0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail27Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("1");
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.getEffects().add(effectDamage_);
        damagingMoveData_.setAccuracy("1");
        damagingMoveData_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.setPp( 1);
        damagingMoveData_.setTypes(new StringList(ELECTRICK));
        data_.completeMembers(CHARGE,damagingMoveData_);
        damagingMoveData_ = Instances.newDamagingMoveData();
        effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("1");
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.getEffects().add(effectDamage_);
        damagingMoveData_.setAccuracy("1");
        damagingMoveData_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.setPp( 1);
        damagingMoveData_.setTypes(new StringList(PARATONNERRE));
        data_.completeMembers(CHARGE2,damagingMoveData_);
        PokemonData pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv(1,1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove(1,CHARGE));
        pokemonData_.setBaseEvo(PIKACHU);
        EvolutionMoveType evolutionMoveType_ = Instances.newEvolutionMoveType();
        evolutionMoveType_.setType(PARATONNERRE);
        pokemonData_.getEvolutions().addEntry(PIKACHU2, evolutionMoveType_);
        pokemonData_.setGenderRep(GenderRepartition.MIXED);
        data_.completeMembers(PIKACHU,pokemonData_);
        pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv(1,1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv(1,1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove(1,CHARGE));
        pokemonData_.setBaseEvo(PIKACHU);
        pokemonData_.setGenderRep(GenderRepartition.MIXED);
        data_.completeMembers(PIKACHU2,pokemonData_);
        data_.completeMembers(TREMPETTE,Instances.newEvolvingStone());
        data_.completeMembers(TREMPETTE2,Instances.newEvolvingItem());
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        block_.setIndexApparition(0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getBuildings().addEntry(newPoint(1,0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail28Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        block_.setIndexApparition(0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getBuildings().addEntry(newPoint(1,0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        TileMiniMap tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE3);
        tile_.setPlace(-2);
        map_.getMiniMap().addEntry(new MiniMapCoords(-1,-1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE);
        tile_.setPlace(0);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE2);
        tile_.setPlace(1);
        map_.getMiniMap().addEntry(new MiniMapCoords(1,0), tile_);
        data_.getMiniMap().addEntry(TREMPETTE,instance(new int[2][2]));
        data_.getMiniMap().addEntry(TREMPETTE2,instance(new int[2][2]));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail29Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        block_.setIndexApparition(0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getBuildings().addEntry(newPoint(1,0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        TileMiniMap tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE3);
        tile_.setPlace(-2);
        map_.getMiniMap().addEntry(new MiniMapCoords(-1,-1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE);
        tile_.setPlace(0);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE2);
        tile_.setPlace(1);
        map_.getMiniMap().addEntry(new MiniMapCoords(1,0), tile_);
        data_.getMiniMap().addEntry(TREMPETTE,instance(new int[2][1]));
        data_.getMiniMap().addEntry(TREMPETTE2,instance(new int[2][2]));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail30Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        block_.setIndexApparition(0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getBuildings().addEntry(newPoint(1,0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        TileMiniMap tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE3);
        tile_.setPlace(-2);
        map_.getMiniMap().addEntry(new MiniMapCoords(-1,-1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE);
        tile_.setPlace(0);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE2);
        tile_.setPlace(1);
        map_.getMiniMap().addEntry(new MiniMapCoords(1,0), tile_);
        data_.getMiniMap().addEntry(TREMPETTE,instance(new int[1][2]));
        data_.getMiniMap().addEntry(TREMPETTE2,instance(new int[2][2]));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail31Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        block_.setIndexApparition(0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        Gym gym_ = Instances.newGym();
        city_.getBuildings().addEntry(newPoint(1,0), gym_);
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        map_.getAccessCondition().put(newCoords(0, 0, 0, 0),Condition.newList(newCoords(1, 0,1,0, 0, 0)));
        map_.getAccessCondition().put(newCoords(0, 0,0,0, 0, 0),Condition.newList(newCoords(1, 0, 1,0,0, 0)));
        map_.getAccessCondition().put(newCoords(0, 0,1, 0),new Condition());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail32Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        block_.setIndexApparition(0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        Gym gym_ = Instances.newGym();
        city_.getBuildings().addEntry(newPoint(1,0), gym_);
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.getPlaces().add( Instances.newLeague());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        map_.getAccessCondition().put(newCoords(2, 0, 0, 0),Condition.newList(newCoords(1, 0,1,0, 0, 0)));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail33Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        block_.setIndexApparition(0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        Gym gym_ = Instances.newGym();
        city_.getBuildings().addEntry(newPoint(1,0), gym_);
        city_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.getPlaces().add( Instances.newLeague());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        map_.getAccessCondition().put(newCoords(0, 0, 0, 0),Condition.newList(newCoords(2, 10, 0, 0)));
        map_.getAccessCondition().put(newCoords(0, 1, 0, 0),Condition.newList(newCoords(1, 10, 0, 0)));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail34Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail35Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setScreenWidth(1);
        map_.setSpaceBetweenLeftAndHeros(10);
        map_.setMiniMap(new MiniMapCoordsList());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail36Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setScreenHeight(1);
        map_.setSpaceBetweenTopAndHeros(10);
        map_.setMiniMap(new MiniMapCoordsList());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail37Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setSpaceBetweenLeftAndHeros(-2);
        map_.setMiniMap(new MiniMapCoordsList());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail38Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setSpaceBetweenTopAndHeros(-2);
        map_.setMiniMap(new MiniMapCoordsList());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail39Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setSideLength(-2);
        map_.setMiniMap(new MiniMapCoordsList());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail40Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setScreenHeight(-2);
        map_.setMiniMap(new MiniMapCoordsList());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail41Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setScreenWidth(-2);
        map_.setMiniMap(new MiniMapCoordsList());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void fail42Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight(1);
        block_.setWidth(1);
        block_.setIndexApparition(10);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(newPoint(1,1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        levelCave_.getBlocks().addEntry(newPoint(0,0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setLevelIndex(1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,2,5,8,4,9)));
        map_.getPlaces().add( r_);
        map_.getPlaces().add( Instances.newCave());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel( 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        validateImages(data_);
        assertTrue(data_.isError());
    }

    @Test
    public void calculateIntersectWithScreenDirection1Test() {
        DataBase data_ = InitializationDataBase.initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NULL_REF, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.getBackgroundImages().clear();
        map_.getForegroundImages().clear();

        data_.getMap().calculateIntersectWithScreenDirection(
                game_.getPlayerCoords());
        Direction playerOrientation_ = game_.getPlayerOrientation();
        data_.getMap().calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, playerOrientation_.getx(),
                playerOrientation_.gety());
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(121, foreGround_.size());
    }

    @Test
    public void calculateIntersectWithScreenDirection2Test() {
        DataBase data_ = InitializationDataBase.initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NULL_REF, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.LEFT);
        DataMap map_ = data_.getMap();
        map_.getBackgroundImages().clear();
        map_.getForegroundImages().clear();

        data_.getMap().calculateIntersectWithScreenDirection(
                game_.getPlayerCoords());

        Direction playerOrientation_ = game_.getPlayerOrientation();
        data_.getMap().calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, playerOrientation_.getx(),
                playerOrientation_.gety());
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(121, foreGround_.size());
    }

    @Test
    public void calculateIntersectWithScreenDirection3Test() {
        DataBase data_ = InitializationDataBase.initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NULL_REF, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        DataMap map_ = data_.getMap();
        map_.getBackgroundImages().clear();
        map_.getForegroundImages().clear();

        data_.getMap().calculateIntersectWithScreenDirection(
                game_.getPlayerCoords());
        Direction playerOrientation_ = game_.getPlayerOrientation();
        data_.getMap().calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, playerOrientation_.getx(),
                playerOrientation_.gety());
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(121, foreGround_.size());
    }

    @Test
    public void calculateIntersectWithScreenDirection4Test() {
        DataBase data_ = InitializationDataBase.initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NULL_REF, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.DOWN);
        DataMap map_ = data_.getMap();
        map_.getBackgroundImages().clear();
        map_.getForegroundImages().clear();

        data_.getMap().calculateIntersectWithScreenDirection(
                game_.getPlayerCoords());

        Direction playerOrientation_ = game_.getPlayerOrientation();
        data_.getMap().calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, playerOrientation_.getx(),
                playerOrientation_.gety());
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 1, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(121, foreGround_.size());
    }


    @Test
    public void getMapWidth1Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        Road road_ = Instances.newRoad();
        initPlaces(map_);
        map_.getPlaces().add(road_);
        map_.setMiniMap(new MiniMapCoordsList());
        TileMiniMap t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,0), t_);
        t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,1),t_);
        t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords(1,0),t_);
        t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords(1,1),t_);
        data_.getMiniMap().addEntry(ELECTRICK,instance(new int[1][1]));
        assertEq(2,map_.getMapWidth());
        assertEq(0,map_.getTrainerName(newCoords(0,0,1,1)).length());
        assertEq(4,map_.getImages(data_).size());
    }

    @Test
    public void getMapHeight1Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        City road_ = Instances.newCity();
        initPlaces(map_);
        map_.getPlaces().add(road_);
        map_.setMiniMap(new MiniMapCoordsList());
        TileMiniMap t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,0), t_);
        t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords(1,0),t_);
        t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,1),t_);
        t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords(1,1),t_);
        data_.getMiniMap().addEntry(ELECTRICK,instance(new int[1][1]));
        assertEq(2,map_.getMapHeight());
        assertEq(0,map_.getTrainerName(newCoords(0,0,1,1)).length());
        assertEq(4,map_.getImages(data_).size());
    }
    @Test
    public void bg1Test() {
        DataBase data_ = InitializationDataBase.initDbAccessSimple();
        assertEq(81,Level.getLevelBackgroundImage(data_,newCoords(6, 1, 4, 5)).size());
        assertEq(new Coords(),data_.getMap().getCity(new MiniMapCoords(0,0)));
        assertEq(newCoords(1,0,1,2), data_.getMap().getCity(new MiniMapCoords(0,1)));
        assertEq(new Coords(), data_.getMap().getCity(new MiniMapCoords(0,-1)));
        assertEq("R 1",data_.getMap().getName(0,0));
        assertEq(NULL_REF, data_.getMap().getName(0,9));
        assertEq(NULL_REF, data_.getMap().getName(0,-1));
        assertEq(2, data_.getMap().getImage(data_,0,9).length);
        assertEq(0, data_.getMap().getImage(data_,0,-1).length);
    }

    @Test
    public void fg1Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(2,Level.getLevelForegroundImage(data_,newCoords(6, 1, 4, 5)).size());
    }

    @Test
    public void fg2Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(2,Level.getLevelForegroundImage(data_,newCoords(0, 0, 0, 5)).size());
    }

    @Test
    public void fg3Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(10,Level.getLevelForegroundImage(data_,newCoords(2, 0, 9, 4)).size());
    }

    @Test
    public void fg4Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(4,Level.getLevelForegroundImage(data_,newCoords(5, 1, 6, 5)).size());
    }

    @Test
    public void fg5Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(6,Level.getLevelForegroundImage(data_,newCoords(5, 0, 6, 5)).size());
    }

    @Test
    public void fg6Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(2,Level.getLevelForegroundImage(data_,newCoords(4, 0, 4, 3)).size());
    }

    @Test
    public void fg7Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(2,Level.getLevelForegroundImage(data_,newCoords(6, 0, 4, 5)).size());
    }

    @Test
    public void fg8Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(4,Level.getLevelForegroundImage(data_,newCoords(1, 0, 5, 1, 4, 4)).size());
    }

    @Test
    public void fg9Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(6,Level.getLevelForegroundImage(data_,newCoords(1, 0, 1, 1, 4, 4)).size());
    }

    @Test
    public void fg10Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(4,Level.getLevelForegroundImage(data_,newCoords(3, 0, 2, 1, 4, 4)).size());
    }

    @Test
    public void fg11Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(1,Level.getLevelForegroundImage(data_,newCoords(8,0,0,0)).size());
    }

    @Test
    public void fg12Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(0,Level.getLevelForegroundImage(data_,newCoords(3, 0,  4, 4)).size());
    }

    @Test
    public void fg13Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(0,Level.getLevelForegroundImage(data_,newCoords(7,0,0,0)).size());
    }


    @Test
    public void validSavedLink1Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        City city_ = Instances.newCity();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,0),Direction.LEFT),newCoords(0,0,1,1));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,2),Direction.UP),newCoords(0,0,1,1));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,2),Direction.DOWN),newCoords(0,0,1,1));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,2),Direction.RIGHT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
         Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( road_);
        assertTrue(!map_.validSavedLink());
    }

    @Test
    public void validSavedLink2Test() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        initPlaces(map_);
        map_.setMiniMap(new MiniMapCoordsList());
        City city_ = Instances.newCity();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( city_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(2,0,1,1));
        map_.getPlaces().add( road_);
        assertTrue(!map_.validSavedLink());
    }
    @Test
    public void isEmptyForAddingTest() {
        DataBase data_ = InitializationDataBase.initDb();
        assertTrue(data_.getMap().isEmptyForAdding(newCoords(0,0,0,0)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(0, 0, 1, 1)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(0, 0, 0, 1)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(1, 0, 1, 1)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(1, 0, 1, 1,0, 4)));
        assertTrue(data_.getMap().isEmptyForAdding(newCoords(1, 0, 1, 1,1, 4)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(1, 0, 1, 1,8, 4)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(1, 0, 1, 1,4,8)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(1, 0, 1, 1,8, 5)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(1, 0, 1, 1,8, 6)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(2, 0, 2, 0)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(2, 0, 3, 0)));
        assertTrue(data_.getMap().isEmptyForAdding(newCoords(2, 0, 0, 0)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(2, 0, 11, 2)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(3, 0, 4, 1)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(4, 0, 5, 4)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(6, 0, 4, 4)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(6, 0, 4, 8)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(6, 1, 4, 8)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(6, 1, 4, 4)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(6, 1, 4, 0)));
        assertTrue(data_.getMap().isEmptyForAdding(newCoords(6, 0, 4, 1)));
        assertTrue(data_.getMap().isEmptyForAdding(newCoords(6, 1, 4, 1)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(5,0,7,5)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(5,1,7,5)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(5, 0, 7, 2)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(4, 0, 0, 2)));
        assertTrue(!data_.getMap().isEmptyForAdding(newCoords(8, 0, 0, 0)));
    }
    @Test
    public void getLevelImage1Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(36,data_.getLevelImage(0,0).size());
    }
    @Test
    public void getLevelImage2Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(81,data_.getLevelImage(1,0,newPoint(1,1)).size());
    }
    @Test
    public void getWhiteLevelImage1Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(36,data_.getWhiteLevelImage(0,0).size());
    }
    @Test
    public void getWhiteLevelImage2Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(81,data_.getWhiteLevelImage(1,0,newPoint(1,1)).size());
    }
    @Test
    public void getBackLevelImage1Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(36,data_.getBackLevelImage(0,0).size());
    }
    @Test
    public void getBackLevelImage2Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(81,data_.getBackLevelImage(1,0,newPoint(1,1)).size());
    }
    @Test
    public void updateBorders1() {
        DataBase data_ = InitializationDataBase.initDb();
        Points<int[][]> imgs_ = new PointsArr();
        imgs_.addEntry(newPoint(0,0),new int[1][1]);
        imgs_.addEntry(newPoint(0,1),new int[1][1]);
        imgs_.addEntry(newPoint(0,2),new int[1][1]);
        imgs_.addEntry(newPoint(1,0),new int[1][1]);
        imgs_.addEntry(newPoint(1,1),new int[1][1]);
        imgs_.addEntry(newPoint(1,2),new int[1][1]);
        DataBase.updateBorders(imgs_,data_.getMap().getSideLength());
        assertEq(20,imgs_.size());
    }
    @Test
    public void updateBorders2() {
        DataBase data_ = InitializationDataBase.initDb();
        Points<int[][]> imgs_ = new PointsArr();
        DataBase.updateBorders(imgs_,data_.getMap().getSideLength());
        assertEq(0,imgs_.size());
    }
    @Test
    public void getFighterNameTest() {
        DataBase data_ = newData();
        data_.setMessagesFight(new StringMap<String>());
        data_.getMessagesFight().addEntry("k","v");
        assertEq("v",data_.getFighterName(false,"f","k","name"));
    }
    @Test
    public void getImageTileTest() {
        DataBase data_ = newData();
        assertEq(0, data_.getImageTile("tile",new ScreenCoords()).length);
    }

    private static DataBase newData() {
        return new DataBase(DefaultGenerator.oneElt());
    }

    private void validateImages(DataBase _data) {
        _data.validateImages(new SexListImpl());
    }

}
