package aiki.db;

import aiki.instances.Instances;
import aiki.map.DataMap;
import aiki.map.characters.DealerItem;
import aiki.map.characters.DualFight;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Block;
import aiki.map.places.City;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonTeam;
import aiki.map.pokemon.WildPk;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.TileMiniMap;
import aiki.util.Coords;
import aiki.util.Point;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.ShortMap;
import code.util.StringList;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public final class DataBaseValidationMapTest extends DataBaseValidationCommon {

    @Test
    public void fail1Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setPlaces(new ShortMap<Place>());
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        map_.getPlaces().addEntry((short)0,Instances.newRoad());
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
        assertTrue(data_.isError());
    }

    @Test
    public void fail2Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setPlaces(new ShortMap<Place>());
        map_.setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        map_.getPlaces().addEntry((short)0,Instances.newRoad());
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
        assertTrue(data_.isError());
    }

    @Test
    public void fail3Test() {
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new ObjectMap<Coords, EqList<Coords>>());
        map_.setPlaces(new ShortMap<Place>());
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
        map_.getPlaces().addEntry((short)0, r_);
        map_.getPlaces().addEntry((short)1, Instances.newCity());
        City city_ = Instances.newCity();
        city_.getBuildings().addEntry(new Point((short)0,(short)0),Instances.newPokemonCenter());
        map_.getPlaces().addEntry((short)2, city_);
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
        assertTrue(data_.isError());
    }


}
