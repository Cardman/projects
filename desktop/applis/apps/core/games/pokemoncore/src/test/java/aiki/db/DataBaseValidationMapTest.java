package aiki.db;

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
import aiki.map.util.MiniMapCoords;
import aiki.map.util.PlaceInterConnect;
import aiki.map.util.ScreenCoords;
import aiki.map.util.TileMiniMap;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.montecarlo.DefaultGenerator;
import code.util.*;
import org.junit.Test;

public final class DataBaseValidationMapTest extends DataBaseValidationCommon {

    @Test
    public void link1Test() {
        Link l_ = Link.newLink("l'UP'5;10_8,4");
        assertEq("l'UP'5;10_8,4",l_.display());
    }

    @Test
    public void link2Test() {
        Link l_ = Link.newLink("l'U'5;10_8,4");
        assertEq("l'UP'5;10_8,4",l_.display());
        new PokemonCenter().validate(newData(),null);
    }

    private static DataBase newData() {
        return new DataBase(new DefaultGenerator());
    }

    @Test
    public void link3Test() {
        Link l_ = Link.newLink("l'5;10_8,4");
        assertEq("l'5;10_8,4",l_.display());
    }
    @Test
    public void fail1Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        map_.getPlaces().add(Instances.newRoad());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) -1);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail2Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        map_.getPlaces().add(Instances.newRoad());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail3Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road r_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        block_.setIndexApparition((short)10);
        AreaApparition area_ = Instances.newAreaApparition();
        area_.getWildPokemonFishing().add(Instances.newWildPk());
        area_.setMultFight((byte)5);
        r_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = Instances.newAreaApparition();
        area_.setMultFight((byte)-1);
        r_.getLevelRoad().getWildPokemonAreas().add(area_);
        r_.getLevelRoad().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        TrainerMultiFights tr_ = Instances.newTrainerMultiFights();
        tr_.setMultiplicityFight((byte) 5);
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
        r_.getLevelRoad().getCharacters().addEntry(new Point((short)1,(short)1), tr_);
        TrainerMultiFights trTwo_ = Instances.newTrainerMultiFights();
        trTwo_.setMultiplicityFight((byte)0);
        r_.getLevelRoad().getCharacters().addEntry(new Point((short)2,(short)2), trTwo_);
        DealerItem deal_ = Instances.newDealerItem();
        deal_.getItems().add(ELECTRICK);
        deal_.getTechnicalMoves().add((short)100);
        r_.getLevelRoad().getCharacters().addEntry(new Point((short)5,(short)5), deal_);
        DualFight dual_ = Instances.newDualFight();
        dual_.setPt(new Point((short)4,(short)4));
        r_.getLevelRoad().getDualFights().addEntry(new Point((short)3,(short)3), dual_);
        r_.getLevelRoad().getItems().addEntry(new Point((short)5,(short)5),ELECTRICK);
        map_.getPlaces().add(r_);
        map_.getPlaces().add(Instances.newCity());
        City city_ = Instances.newCity();
        PokemonCenter pokemonCenter_ = Instances.newPokemonCenter();
        pokemonCenter_.getIndoor().getBlocks().addEntry(new Point((short)0,(short)0),Instances.newBlock());
        Seller seller_ = Instances.newSeller();
        pokemonCenter_.getIndoor().getGerants().addEntry(new Point((short)1,(short)1), seller_);
        seller_ = Instances.newSeller();
        seller_.getItems().add(ELECTRICK);
        seller_.setSell(SellType.TM);
        pokemonCenter_.getIndoor().getGerants().addEntry(new Point((short)1,(short)2),seller_);
        seller_ = Instances.newSeller();
        seller_.getTm().add((short)100);
        seller_.setSell(SellType.ITEM);
        pokemonCenter_.getIndoor().getGerants().addEntry(new Point((short)1,(short)3),seller_);
        seller_ = Instances.newSeller();
        seller_.getItems().add(ELECTRICK);
        seller_.getTm().add((short)100);
        seller_.setSell(SellType.MOVE);
        pokemonCenter_.getIndoor().getGerants().addEntry(new Point((short)1,(short)4),seller_);
        pokemonCenter_.getIndoor().getGerants().addEntry(new Point((short)1,(short)5),Instances.newGymTrainer());
        pokemonCenter_.getIndoor().setStorageCoords(new Point((short)1,(short)5));
        city_.getBuildings().addEntry(new Point((short)0,(short)0), pokemonCenter_);
        Gym gym_ = Instances.newGym();
        GymLeader gymLeader_ = Instances.newGymLeader();
        gymLeader_.setTm((short)100);
        block_ = Instances.newBlock();
        block_.setType(EnvironmentType.NOTHING);
        block_.setWidth((short)1);
        block_.setHeight((short)1);
        gym_.getIndoor().getBlocks().addEntry(new Point((short)0,(short)0),block_);
        gym_.getIndoor().setGymLeader(gymLeader_);
        gym_.getIndoor().getGymTrainers().addEntry(new Point((short)2,(short)2),Instances.newGymTrainer());
        gym_.getIndoor().setGymLeaderCoords(new Point((short)2,(short)2));
        gym_.setExitCity(new Point((short)10,(short)10));
        city_.getBuildings().addEntry(new Point((short)1,(short)1), gym_);
        map_.getPlaces().add(city_);
        city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevelOutdoor().getBlocks().addEntry(new Point((short)1,(short)1),block_);
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevelOutdoor().getBlocks().addEntry(new Point((short)0,(short)0),block_);
        block_ = Instances.newBlock();
        block_.setHeight((short) -2);
        block_.setWidth((short) 2);
        city_.getLevelOutdoor().getBlocks().addEntry(new Point((short)5,(short)5),block_);
        block_ = Instances.newBlock();
        block_.setType(EnvironmentType.NOTHING);
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        block_.setIndexApparition((short) 0);
        city_.getLevelOutdoor().getBlocks().addEntry(new Point((short)10,(short)0),block_);
        map_.getPlaces().add(city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail4Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road r_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        block_.setIndexApparition((short)0);
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
        area_.setMultFight((byte)5);
        r_.getLevelRoad().getWildPokemonAreas().add(area_);
        area_ = Instances.newAreaApparition();
        area_.setMultFight((byte)-1);
        r_.getLevelRoad().getWildPokemonAreas().add(area_);
        r_.getLevelRoad().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        DualFight dual_ = Instances.newDualFight();
        dual_.setPt(new Point((short)4,(short)4));
        r_.getLevelRoad().getDualFights().addEntry(new Point((short)3,(short)3), dual_);
        map_.getPlaces().add( r_);
        map_.getPlaces().add( Instances.newCity());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        data_.initializeWildPokemon();
        assertTrue(data_.isError());
    }

    @Test
    public void fail5Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road r_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        block_.setIndexApparition((short)0);
        r_.getLevelRoad().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        map_.getPlaces().add( r_);
        League league_ = Instances.newLeague();
        LevelLeague room_ = Instances.newLevelLeague();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        block_.setType(EnvironmentType.NOTHING);
        room_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        room_.setAccessPoint(new Point((short)1,(short)1));
        room_.setTrainerCoords(new Point((short)1,(short)1));
        room_.setNextLevelTarget(new Point((short)1,(short)1));
        league_.getRooms().add(room_);
        room_ = Instances.newLevelLeague();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        block_.setType(EnvironmentType.NOTHING);
        room_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        room_.setAccessPoint(new Point((short)1,(short)1));
        room_.setTrainerCoords(new Point((short)1,(short)1));
        room_.setNextLevelTarget(new Point((short)1,(short)1));
        league_.getRooms().add(room_);
        league_.setBegin(new Point((short)1,(short)1));
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
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        data_.initializeWildPokemon();
        assertTrue(data_.isError());
        ShortMap<EqList<PlaceInterConnect>> v_ = new ShortMap<EqList<PlaceInterConnect>>();
        DataMap.merge(v_,new PlaceInterConnect(new Point((short)0,(short)0),Direction.RIGHT),new Point((short)0,(short)0),(byte)0);
        DataMap.merge(v_,new PlaceInterConnect(new Point((short)0,(short)0),Direction.RIGHT),new Point((short)0,(short)0),(byte)0);
    }

    @Test
    public void fail6Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road r_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        block_.setIndexApparition((short)10);
        r_.getLevelRoad().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        DealerItem deal_ = Instances.newDealerItem();
        deal_.getItems().add(ELECTRICK);
        deal_.getTechnicalMoves().add((short)100);
        r_.getLevelRoad().getCharacters().addEntry(new Point((short)5,(short)5), deal_);
        DualFight dual_ = Instances.newDualFight();
        dual_.setPt(new Point((short)5,(short)5));
        r_.getLevelRoad().getDualFights().addEntry(new Point((short)5,(short)5), dual_);
        r_.getLevelRoad().getLegendaryPks().addEntry(new Point((short)5,(short)5),Instances.newWildPk());
        r_.getLevelRoad().getTm().addEntry(new Point((short)5,(short)5),(short)100);
        r_.getLevelRoad().getHm().addEntry(new Point((short)5,(short)5),(short)100);
        map_.getPlaces().add( r_);
        map_.getPlaces().add( Instances.newCity());
        City city_ = Instances.newCity();
        Gym gym_ = Instances.newGym();
        gym_.setExitCity(new Point((short)10,(short)10));
        city_.getBuildings().addEntry(new Point((short)1,(short)1), gym_);
        city_.getBuildings().addEntry(new Point((short)1,(short)2), Instances.newGym());
        city_.getBuildings().addEntry(new Point((short)1,(short)3), Instances.newPokemonCenter());
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail7Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        block_.setIndexApparition((short)10);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(5,2,5,8,4,9)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setLevelIndex((byte) 5);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(5,2,5,8,4,9)));
        map_.getPlaces().add( r_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail8Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        block_.setIndexApparition((short)10);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(5,2,5,8,4,9)));
        map_.getPlaces().add( r_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail9Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        levelCave_.getDualFights().addEntry(new Point((short)0,(short)0),Instances.newDualFight());
        levelCave_.getCharacters().addEntry(new Point((short)0,(short)0),Instances.newTrainerMultiFights());
        levelCave_.getItems().addEntry(new Point((short)0,(short)0),ELECTRICK);
        levelCave_.getTm().addEntry(new Point((short)0,(short)0),(short)0);
        levelCave_.getHm().addEntry(new Point((short)0,(short)0),(short)0);
        levelCave_.getLegendaryPks().addEntry(new Point((short)0,(short)0),Instances.newWildPk());
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(new Point((short)1,(short)1));
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(5,2,5,8,4,9)));
        map_.getPlaces().add( r_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        road_.getLevelRoad().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.getLinksWithCaves().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        road_.getLinksWithCaves().addEntry(new Point((short)1,(short)0),new Link(ELECTRICK,newCoords(0,0,0,0)));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        city_.getLevelOutdoor().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getLinksWithCaves().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        city_.getLinksWithCaves().addEntry(new Point((short)1,(short)0),new Link(ELECTRICK,newCoords(0,0,0,0)));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail10Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        levelCave_.getItems().addEntry(new Point((short)1,(short)0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(new Point((short)1,(short)1));
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(5,2,5,8,4,9)));
        map_.getPlaces().add( r_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        road_.getLevelRoad().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.getLinksWithCaves().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        road_.getLinksWithCaves().addEntry(new Point((short)1,(short)0),new Link(ELECTRICK,newCoords(0,0,1,0)));
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail11Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        levelCave_.getItems().addEntry(new Point((short)1,(short)0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(new Point((short)1,(short)1));
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        road_.getLevelRoad().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.getLinksWithCaves().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        road_.getLinksWithCaves().addEntry(new Point((short)1,(short)0),new Link(ELECTRICK,newCoords(0,0,1,0)));
        road_.getLevelRoad().getItems().addEntry(new Point((short)1,(short)0),ELECTRICK);
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail112Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        levelCave_.getItems().addEntry(new Point((short)1,(short)0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        levelCave_.getItems().addEntry(new Point((short)1,(short)0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(new Point((short)1,(short)1));
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        road_.getLevelRoad().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.getLinksWithCaves().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        road_.getLinksWithCaves().addEntry(new Point((short)1,(short)0),new Link(ELECTRICK,newCoords(0,0,1,0)));
        road_.getLevelRoad().getItems().addEntry(new Point((short)1,(short)0),ELECTRICK);
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    private static void initPlaces(DataMap _map) {
        _map.setPlaces(new CustList<Place>());
    }

    @Test
    public void fail13Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        levelCave_.getItems().addEntry(new Point((short)1,(short)1),ELECTRICK);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        levelCave_.getItems().addEntry(new Point((short)1,(short)0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(new Point((short)1,(short)1));
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        road_.getLevelRoad().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.getLinksWithCaves().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        road_.getLinksWithCaves().addEntry(new Point((short)1,(short)0),new Link(ELECTRICK,newCoords(0,0,1,0)));
        road_.getLevelRoad().getItems().addEntry(new Point((short)1,(short)0),ELECTRICK);
        map_.getPlaces().add( road_);
        map_.getPlaces().add( Instances.newCave());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail14Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        levelCave_.getItems().addEntry(new Point((short)1,(short)0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        levelCave_.getItems().addEntry(new Point((short)1,(short)0),ELECTRICK);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(new Point((short)1,(short)1));
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getLinksWithCaves().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        city_.getLinksWithCaves().addEntry(new Point((short)1,(short)0),new Link(ELECTRICK,newCoords(0,0,1,0)));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail15Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)0),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(new Point((short)1,(short)1));
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getLinksWithCaves().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        city_.getLinksWithCaves().addEntry(new Point((short)1,(short)0),new Link(ELECTRICK,newCoords(0,0,1,0)));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail16Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(new Point((short)1,(short)1));
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)2,(short)2),Direction.RIGHT),newCoords(2,0,5,5));
        map_.getPlaces().add( city_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)2,(short)2),Direction.RIGHT),newCoords(1,0,5,5));
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail17Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        map_.getPlaces().add(Instances.newCity());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) -1);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0,2,2, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail18Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        City city_ = Instances.newCity();
        city_.getBuildings().addEntry(new Point((short)2,(short)2),Instances.newPokemonCenter());
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) -1);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0,2,2, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
        assertTrue(data_.getMap().getAreaByCoords(new Coords()).isVirtual());
    }

    @Test
    public void fail19Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(new Point((short)1,(short)1));
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,0)));
        map_.getPlaces().add( r_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        Link lk_ = new Link(ELECTRICK, newCoords(2, 0, 0, 0));
        lk_.setDir(Direction.RIGHT);
        city_.getLinksWithCaves().addEntry(new Point((short)0,(short)0), lk_);
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)2,(short)2),Direction.RIGHT),newCoords(2,0,5,5));
        map_.getPlaces().add( city_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)2,(short)2),Direction.RIGHT),newCoords(1,0,5,5));
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail20Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(new Point((short)1,(short)1));
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,10)));
        map_.getPlaces().add( r_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        Link lk_ = new Link(ELECTRICK, newCoords(0, 0, 1, 1));
        lk_.setDir(Direction.RIGHT);
        city_.getLinksWithCaves().addEntry(new Point((short)0,(short)0), lk_);
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)2,(short)2),Direction.RIGHT),newCoords(2,0,5,5));
        map_.getPlaces().add( city_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)2,(short)2),Direction.RIGHT),newCoords(1,0,5,5));
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail21Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,1,1,0)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setPoint(new Point((short)1,(short)1));
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,0,1,10)));
        map_.getPlaces().add( r_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        Link lk_ = new Link(ELECTRICK, newCoords(0, 1, 1, 1));
        city_.getLinksWithCaves().addEntry(new Point((short)0,(short)0), lk_);
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)2,(short)2),Direction.RIGHT),newCoords(2,0,5,5));
        map_.getPlaces().add( city_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 2);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)2,(short)2),Direction.RIGHT),newCoords(1,0,5,5));
        map_.getPlaces().add( road_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail22Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)1,(short)1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getBuildings().addEntry(new Point((short)1,(short)1),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail23Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)1,(short)1),Direction.RIGHT),newCoords(1,0,0,0));
        DualFight dualFight_ = Instances.newDualFight();
        dualFight_.setPt(new Point((short)1,(short)1));
        road_.getLevelRoad().getDualFights().addEntry(new Point((short)0, (short) 1),dualFight_);
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getBuildings().addEntry(new Point((short)1,(short)0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        map_.getAccessCondition().put(newCoords(1,0,1,1),new EqList<Coords>(newCoords(0,0,0,1)));
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail24Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("1");
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.getEffects().add(effectDamage_);
        damagingMoveData_.setAccuracy("1");
        damagingMoveData_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.setPp((short) 1);
        damagingMoveData_.setTypes(new StringList(ELECTRICK));
        data_.completeMembers(CHARGE,damagingMoveData_);
        PokemonData pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove((short)1,CHARGE));
        pokemonData_.setBaseEvo(PIKACHU);
        data_.completeMembers(PIKACHU,pokemonData_);
        pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove((short)1,CHARGE));
        pokemonData_.setGenderRep(GenderRepartition.LEGENDARY);
        pokemonData_.setBaseEvo(PIKACHU2);
        data_.completeMembers(PIKACHU2,pokemonData_);
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)1,(short)1),Direction.RIGHT),newCoords(1,0,0,0));
        DualFight dualFight_ = Instances.newDualFight();
        dualFight_.setPt(new Point((short)1,(short)1));
        road_.getLevelRoad().getDualFights().addEntry(new Point((short)0, (short) 1),dualFight_);
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getBuildings().addEntry(new Point((short)1,(short)0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        map_.getAccessCondition().put(newCoords(1,0,1,1),new EqList<Coords>(newCoords(0,0,0,1)));
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail25Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("1");
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.getEffects().add(effectDamage_);
        damagingMoveData_.setAccuracy("1");
        damagingMoveData_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.setPp((short) 1);
        damagingMoveData_.setTypes(new StringList(ELECTRICK));
        data_.completeMembers(CHARGE,damagingMoveData_);
        PokemonData pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove((short)1,CHARGE));
        pokemonData_.setBaseEvo(PIKACHU);
        pokemonData_.setGenderRep(GenderRepartition.MIXED);
        data_.completeMembers(PIKACHU,pokemonData_);
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        block_.setIndexApparition((short)0);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)1,(short)1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getBuildings().addEntry(new Point((short)1,(short)0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail26Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("1");
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.getEffects().add(effectDamage_);
        damagingMoveData_.setAccuracy("1");
        damagingMoveData_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.setPp((short) 1);
        damagingMoveData_.setTypes(new StringList(ELECTRICK));
        data_.completeMembers(CHARGE,damagingMoveData_);
        damagingMoveData_ = Instances.newDamagingMoveData();
        effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("1");
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.getEffects().add(effectDamage_);
        damagingMoveData_.setAccuracy("1");
        damagingMoveData_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.setPp((short) 1);
        damagingMoveData_.setTypes(new StringList(ELECTRICK));
        data_.completeMembers(CHARGE2,damagingMoveData_);
        PokemonData pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove((short)1,CHARGE));
        pokemonData_.setBaseEvo(PIKACHU);
        EvolutionMove evolutionMove_ = Instances.newEvolutionMove();
        evolutionMove_.setMove(CHARGE2);
        pokemonData_.getEvolutions().addEntry(PIKACHU2, evolutionMove_);
        pokemonData_.setGenderRep(GenderRepartition.MIXED);
        data_.completeMembers(PIKACHU,pokemonData_);
        pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove((short)1,CHARGE));
        pokemonData_.setBaseEvo(PIKACHU);
        pokemonData_.setGenderRep(GenderRepartition.MIXED);
        data_.completeMembers(PIKACHU2,pokemonData_);
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        block_.setIndexApparition((short)0);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)1,(short)1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getBuildings().addEntry(new Point((short)1,(short)0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail27Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
        EffectDamage effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("1");
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.getEffects().add(effectDamage_);
        damagingMoveData_.setAccuracy("1");
        damagingMoveData_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.setPp((short) 1);
        damagingMoveData_.setTypes(new StringList(ELECTRICK));
        data_.completeMembers(CHARGE,damagingMoveData_);
        damagingMoveData_ = Instances.newDamagingMoveData();
        effectDamage_ = Instances.newEffectDamage();
        effectDamage_.setPower("1");
        effectDamage_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.getEffects().add(effectDamage_);
        damagingMoveData_.setAccuracy("1");
        damagingMoveData_.setTargetChoice(TargetChoice.ANY_FOE);
        damagingMoveData_.setPp((short) 1);
        damagingMoveData_.setTypes(new StringList(PARATONNERRE));
        data_.completeMembers(CHARGE2,damagingMoveData_);
        PokemonData pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove((short)1,CHARGE));
        pokemonData_.setBaseEvo(PIKACHU);
        EvolutionMoveType evolutionMoveType_ = Instances.newEvolutionMoveType();
        evolutionMoveType_.setType(PARATONNERRE);
        pokemonData_.getEvolutions().addEntry(PIKACHU2, evolutionMoveType_);
        pokemonData_.setGenderRep(GenderRepartition.MIXED);
        data_.completeMembers(PIKACHU,pokemonData_);
        pokemonData_ = Instances.newPokemonData();
        pokemonData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)1));
        pokemonData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)1));
        pokemonData_.setTypes(new StringList(ELECTRICK));
        pokemonData_.getLevMoves().add(new LevelMove((short)1,CHARGE));
        pokemonData_.setBaseEvo(PIKACHU);
        pokemonData_.setGenderRep(GenderRepartition.MIXED);
        data_.completeMembers(PIKACHU2,pokemonData_);
        data_.completeMembers(TREMPETTE,Instances.newEvolvingStone());
        data_.completeMembers(TREMPETTE2,Instances.newEvolvingItem());
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        block_.setIndexApparition((short)0);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)1,(short)1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getBuildings().addEntry(new Point((short)1,(short)0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail28Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        block_.setIndexApparition((short)0);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)1,(short)1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getBuildings().addEntry(new Point((short)1,(short)0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        TileMiniMap tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE3);
        tile_.setPlace((short)-2);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)-1,(short)-1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE);
        tile_.setPlace((short)0);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)0,(short)1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE2);
        tile_.setPlace((short)1);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)1,(short)0), tile_);
        data_.getMiniMap().addEntry(TREMPETTE,new int[2][2]);
        data_.getMiniMap().addEntry(TREMPETTE2,new int[2][2]);
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail29Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        block_.setIndexApparition((short)0);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)1,(short)1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getBuildings().addEntry(new Point((short)1,(short)0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        TileMiniMap tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE3);
        tile_.setPlace((short)-2);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)-1,(short)-1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE);
        tile_.setPlace((short)0);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)0,(short)1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE2);
        tile_.setPlace((short)1);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)1,(short)0), tile_);
        data_.getMiniMap().addEntry(TREMPETTE,new int[2][1]);
        data_.getMiniMap().addEntry(TREMPETTE2,new int[2][2]);
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail30Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        block_.setIndexApparition((short)0);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)1,(short)1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getBuildings().addEntry(new Point((short)1,(short)0),Instances.newPokemonCenter());
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        TileMiniMap tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE3);
        tile_.setPlace((short)-2);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)-1,(short)-1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE);
        tile_.setPlace((short)0);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)0,(short)1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(TREMPETTE2);
        tile_.setPlace((short)1);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)1,(short)0), tile_);
        data_.getMiniMap().addEntry(TREMPETTE,new int[1][2]);
        data_.getMiniMap().addEntry(TREMPETTE2,new int[2][2]);
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail31Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        block_.setIndexApparition((short)0);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)1,(short)1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        Gym gym_ = Instances.newGym();
        city_.getBuildings().addEntry(new Point((short)1,(short)0), gym_);
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        map_.getAccessCondition().put(newCoords(0, 0, 0, 0),new EqList<Coords>(newCoords(1, 0,1,0, 0, 0)));
        map_.getAccessCondition().put(newCoords(0, 0,0,0, 0, 0),new EqList<Coords>(newCoords(1, 0, 1,0,0, 0)));
        map_.getAccessCondition().put(newCoords(0, 0,1, 0),new EqList<Coords>());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail32Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        block_.setIndexApparition((short)0);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)1,(short)1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        Gym gym_ = Instances.newGym();
        city_.getBuildings().addEntry(new Point((short)1,(short)0), gym_);
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.getPlaces().add( Instances.newLeague());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        map_.getAccessCondition().put(newCoords(2, 0, 0, 0),new EqList<Coords>(newCoords(1, 0,1,0, 0, 0)));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail33Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Road road_ = Instances.newRoad();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        block_.setIndexApparition((short)0);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        AreaApparition areaApparition_ = Instances.newAreaApparition();
        WildPk wildPk_ = Instances.newWildPk();
        wildPk_.setName(PIKACHU);
        areaApparition_.getWildPokemon().add(wildPk_);
        road_.getLevelRoad().getWildPokemonAreas().add(areaApparition_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)1,(short)1),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( road_);
        City city_ = Instances.newCity();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        Gym gym_ = Instances.newGym();
        city_.getBuildings().addEntry(new Point((short)1,(short)0), gym_);
        city_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        city_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
        map_.getPlaces().add( Instances.newLeague());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        map_.getAccessCondition().put(newCoords(0, 0, 0, 0),new EqList<Coords>(newCoords(2, 10, 0, 0)));
        map_.getAccessCondition().put(newCoords(0, 1, 0, 0),new EqList<Coords>(newCoords(1, 10, 0, 0)));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail34Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail35Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setScreenWidth(1);
        map_.setSpaceBetweenLeftAndHeros(10);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail36Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setScreenHeight(1);
        map_.setSpaceBetweenTopAndHeros(10);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail37Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setSpaceBetweenLeftAndHeros(-2);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail38Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setSpaceBetweenTopAndHeros(-2);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail39Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setSideLength(-2);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail40Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setScreenHeight(-2);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail41Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setScreenWidth(-2);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        assertTrue(data_.isError());
    }

    @Test
    public void fail42Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        Cave r_ = Instances.newCave();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 1);
        block_.setWidth((short) 1);
        block_.setIndexApparition((short)10);
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getLinksOtherLevels().addEntry(new Point((short)1,(short)1),new Link(ELECTRICK,newCoords(0,0,5,8,4,9)));
        levelCave_.getBlocks().addEntry(new Point((short)0,(short)0), block_);
        r_.getLevels().add(levelCave_);
        levelCave_ = Instances.newLevelCave();
        r_.getLevels().add(levelCave_);
        LevelPoint lPt_ = new LevelPoint();
        lPt_.setLevelIndex((byte) 1);
        r_.getLinksWithOtherPlaces().addEntry(lPt_,new Link(ELECTRICK,newCoords(1,2,5,8,4,9)));
        map_.getPlaces().add( r_);
        map_.getPlaces().add( Instances.newCave());
        map_.setUnlockedCity(NULL_REF);
        map_.setSideLength(2);
        WildPk pkm_ = new WildPk();
        pkm_.setName(ELECTRICK);
        pkm_.setAbility(ELECTRICK);
        pkm_.setItem(ELECTRICK);
        pkm_.setLevel((short) 1200);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
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
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
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
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
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
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
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
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(121, foreGround_.size());
    }


    @Test
    public void getMapWidth1Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        Road road_ = Instances.newRoad();
        initPlaces(map_);
        map_.getPlaces().add(road_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords, TileMiniMap>());
        TileMiniMap t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)0,(short)0), t_);
        t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)0,(short)1),t_);
        t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)1,(short)0),t_);
        t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)1,(short)1),t_);
        data_.getMiniMap().addEntry(ELECTRICK,new int[1][1]);
        assertEq(2,map_.getMapWidth());
        assertEq(0,map_.getTrainerName(newCoords(0,0,1,1)).length());
        assertEq(4,map_.getImages(data_).size());
    }

    @Test
    public void getMapHeight1Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        City road_ = Instances.newCity();
        initPlaces(map_);
        map_.getPlaces().add(road_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords, TileMiniMap>());
        TileMiniMap t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)0,(short)0), t_);
        t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)1,(short)0),t_);
        t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)0,(short)1),t_);
        t_ = new TileMiniMap();
        t_.setFile(ELECTRICK);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)1,(short)1),t_);
        data_.getMiniMap().addEntry(ELECTRICK,new int[1][1]);
        assertEq(2,map_.getMapHeight());
        assertEq(0,map_.getTrainerName(newCoords(0,0,1,1)).length());
        assertEq(4,map_.getImages(data_).size());
    }
    @Test
    public void bg1Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(81,Level.getLevelBackgroundImage(data_,newCoords(6, 1, 4, 5)).size());
        assertEq(new Coords(),data_.getMap().getCity(new MiniMapCoords((short)0,(short)0)));
        assertEq(newCoords(1,0,1,2), data_.getMap().getCity(new MiniMapCoords((short)0,(short)1)));
        assertEq(new Coords(), data_.getMap().getCity(new MiniMapCoords((short)0,(short)-1)));
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
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        City city_ = Instances.newCity();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getSavedlinks().addEntry(new PlaceInterConnect(new Point((short)2,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(new Point((short)0,(short)2),Direction.UP),newCoords(0,0,1,1));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(new Point((short)0,(short)2),Direction.DOWN),newCoords(0,0,1,1));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(new Point((short)0,(short)2),Direction.RIGHT),newCoords(0,0,1,1));
        map_.getPlaces().add( city_);
         Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        road_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(0,0,1,1));
        map_.getPlaces().add( road_);
        assertTrue(!map_.validSavedLink());
    }

    @Test
    public void validSavedLink2Test() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        initPlaces(map_);
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        City city_ = Instances.newCity();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getSavedlinks().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( city_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.getSavedlinks().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(2,0,1,1));
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
        assertEq(36,data_.getLevelImage((short)0,(byte)0).size());
    }
    @Test
    public void getLevelImage2Test() {
        DataBase data_ = InitializationDataBase.initDb();
        assertEq(81,data_.getLevelImage((short)1,(byte)0,new Point((short)1,(short)1)).size());
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
}
