package aiki.beans;

import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.fight.abilities.AbilityData;
import aiki.fight.items.Item;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.status.Status;
import aiki.map.characters.Ally;
import aiki.map.characters.Person;
import aiki.map.levels.AbsAreaApparition;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import code.bean.nat.StringMapObjectBase;
import code.util.AbsMap;
import code.util.StringMap;

public final class StringMapObject extends StringMapObjectBase {

    private final StringMap<Ally> mapAlly = new StringMap<Ally>();
    private final StringMap<WildPk> mapWildPk = new StringMap<WildPk>();
    private final StringMap<Person> mapPerson = new StringMap<Person>();
//    private final StringMap<SelectedPlaceLevelIndexes> mapPlacesLevels = new StringMap<SelectedPlaceLevelIndexes>();
//    private final StringMap<Point> mapPoint = new StringMap<Point>();
    private final StringMap<Coords> mapCoords = new StringMap<Coords>();
    private final StringMap<SimulationSteps> mapSimulationSteps = new StringMap<SimulationSteps>();
    private final StringMap<TeamCrud> mapTeamCrud = new StringMap<TeamCrud>();
    private final StringMap<Gender> mapGender = new StringMap<Gender>();
    private final StringMap<PokemonPlayerDto> mapPokemonPlayerDto = new StringMap<PokemonPlayerDto>();
    private final StringMap<AbsMap<String,MoveData>> mapMoves = new StringMap<AbsMap<String,MoveData>>();
    private final StringMap<AbsMap<String,PokemonData>> mapPokedex = new StringMap<AbsMap<String,PokemonData>>();
    private final StringMap<AbsMap<String,Item>> mapItems = new StringMap<AbsMap<String,Item>>();
    private final StringMap<AbsMap<String,AbilityData>> mapAbilities = new StringMap<AbsMap<String,AbilityData>>();
    private final StringMap<AbsMap<String,Status>> mapStatus = new StringMap<AbsMap<String,Status>>();

    public void put(String _key, AbsAreaApparition _v) {
        getBeansOthers().put(_key,new AreaApparitionStruct(_v));
    }

    public void put(String _key, Ally _v) {
        mapAlly.put(_key, _v);
    }

    public void put(String _key, WildPk _v) {
        mapWildPk.put(_key, _v);
    }

    public void put(String _key, PokemonPlayerDto _v) {
        mapPokemonPlayerDto.put(_key, _v);
    }

    public void safeMoves(String _key) {
        if (!contains(_key)) {
            putMoves(_key, new StringMap<MoveData>());
        }
    }
    public void putMoves(String _key, AbsMap<String,MoveData> _v) {
        mapMoves.put(_key, _v);
    }

    public void safePokedex(String _key) {
        if (!contains(_key)) {
            putPokedex(_key, new StringMap<PokemonData>());
        }
    }
    public void putPokedex(String _key, AbsMap<String,PokemonData> _v) {
        mapPokedex.put(_key, _v);
    }

    public void safeAbilities(String _key) {
        if (!contains(_key)) {
            putAbilities(_key, new StringMap<AbilityData>());
        }
    }
    public void putAbilities(String _key, AbsMap<String,AbilityData> _v) {
        mapAbilities.put(_key, _v);
    }

    public void safeItems(String _key) {
        if (!contains(_key)) {
            putItems(_key, new StringMap<Item>());
        }
    }
    public void putItems(String _key, AbsMap<String,Item> _v) {
        mapItems.put(_key, _v);
    }

    public void safeStatus(String _key) {
        if (!contains(_key)) {
            putStatus(_key, new StringMap<Status>());
        }
    }
    public void putStatus(String _key, AbsMap<String,Status> _v) {
        mapStatus.put(_key, _v);
    }
    public void put(String _key, Person _v) {
        mapPerson.put(_key, _v);
    }

    public void putPlaceLevel(String _key, Coords _coords) {
        put(_key, _coords);
    }

    public void put(String _key, int _pl, int _lev) {
        Coords c_ = new Coords();
        c_.setNumberPlace(_pl);
        c_.getLevel().setLevelIndex(_lev);
        put(_key,c_);
    }

    public void put(String _key, Coords _v) {
        mapCoords.put(_key, _v);
    }
    public void put(String _key, SimulationSteps _v) {
        mapSimulationSteps.put(_key, _v);
    }
    public void put(String _key, TeamCrud _v) {
        mapTeamCrud.put(_key, _v);
    }
    public void put(String _key, Gender _v) {
        mapGender.put(_key, _v);
    }

    public boolean contains(String _key) {
        return containsBase(_key)||
                mapAlly.contains(_key)||
                mapWildPk.contains(_key)||
                mapPerson.contains(_key)||
                mapCoords.contains(_key)||
                mapSimulationSteps.contains(_key)||
                mapTeamCrud.contains(_key)||
                mapGender.contains(_key)||
                mapPokemonPlayerDto.contains(_key)||
                mapMoves.contains(_key)||
                mapPokedex.contains(_key)||
                mapItems.contains(_key)||
                mapAbilities.contains(_key)||
                mapStatus.contains(_key);
    }

    public PokemonPlayerDto getVal(String _key) {
        return mapPokemonPlayerDto.getVal(_key);
    }
    public AbsMap<String,MoveData> getValMoveData(String _key) {
        return mapMoves.getVal(_key);
    }
    public AbsMap<String,PokemonData> getValPokemonData(String _key) {
        return mapPokedex.getVal(_key);
    }
    public AbsMap<String,AbilityData> getValAbilityData(String _key) {
        return mapAbilities.getVal(_key);
    }
    public AbsMap<String,Item> getValItemData(String _key) {
        return mapItems.getVal(_key);
    }
    public AbsMap<String,Status> getValStatusData(String _key) {
        return mapStatus.getVal(_key);
    }
    public TeamCrud getValTeamCrud(String _key) {
        return mapTeamCrud.getVal(_key);
    }
    public WildPk getValPk(String _key) {
        return mapWildPk.getVal(_key);
    }
    public AbsAreaApparition getValArea(String _key) {
        return ((AreaApparitionStruct)getBeansOthers().getVal(_key)).getWildPk();
    }
    public Person getValPers(String _key) {
        return mapPerson.getVal(_key);
    }
    public Ally getValAlly(String _key) {
        return mapAlly.getVal(_key);
    }
    public Gender getValGen(String _key) {
        return mapGender.getVal(_key);
    }
    public SimulationSteps getValSimStep(String _key) {
        return mapSimulationSteps.getVal(_key);
    }
    public Coords getValCoords(String _key) {
        return mapCoords.getVal(_key);
    }

    public void putAllMapGene(StringMapObjectBase _m) {
        if (_m instanceof StringMapObject) {
            putAllMap((StringMapObject) _m);
        } else {
            putAllMapBase(_m);
        }
    }
    public void putAllMap(StringMapObject _m) {
        putAllMapBase(_m);
        mapAlly.putAllMap(_m.mapAlly);
        mapWildPk.putAllMap(_m.mapWildPk);
        mapPerson.putAllMap(_m.mapPerson);
        mapCoords.putAllMap(_m.mapCoords);
        mapSimulationSteps.putAllMap(_m.mapSimulationSteps);
        mapTeamCrud.putAllMap(_m.mapTeamCrud);
        mapGender.putAllMap(_m.mapGender);
        mapPokemonPlayerDto.putAllMap(_m.mapPokemonPlayerDto);
        mapMoves.putAllMap(_m.mapMoves);
        mapPokedex.putAllMap(_m.mapPokedex);
        mapItems.putAllMap(_m.mapItems);
        mapAbilities.putAllMap(_m.mapAbilities);
        mapStatus.putAllMap(_m.mapStatus);
    }

    public void removeKey(String _key) {
        removeKeyBase(_key);
        mapAlly.removeKey(_key);
        mapWildPk.removeKey(_key);
        mapPerson.removeKey(_key);
        mapCoords.removeKey(_key);
        mapSimulationSteps.removeKey(_key);
        mapTeamCrud.removeKey(_key);
        mapGender.removeKey(_key);
        mapPokemonPlayerDto.removeKey(_key);
        mapMoves.removeKey(_key);
        mapPokedex.removeKey(_key);
        mapItems.removeKey(_key);
        mapAbilities.removeKey(_key);
        mapStatus.removeKey(_key);
    }

}
