package aiki.beans;

import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.map.characters.Ally;
import aiki.map.characters.Person;
import aiki.map.levels.AreaApparition;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.Point;
import code.bean.nat.StringMapObjectBase;
import code.util.StringMap;
import code.util.core.BoolVal;

public final class StringMapObject extends StringMapObjectBase {

    private final StringMap<Ally> mapAlly = new StringMap<Ally>();
    private final StringMap<WildPk> mapWildPk = new StringMap<WildPk>();
    private final StringMap<Person> mapPerson = new StringMap<Person>();
    private final StringMap<Point> mapPoint = new StringMap<Point>();
    private final StringMap<Coords> mapCoords = new StringMap<Coords>();
    private final StringMap<SimulationSteps> mapSimulationSteps = new StringMap<SimulationSteps>();
    private final StringMap<TeamCrud> mapTeamCrud = new StringMap<TeamCrud>();
    private final StringMap<Gender> mapGender = new StringMap<Gender>();
    private final StringMap<BoolVal> mapDirection = new StringMap<BoolVal>();
    private final StringMap<PokemonPlayerDto> mapPokemonPlayerDto = new StringMap<PokemonPlayerDto>();

    public void put(String _key, AreaApparition _v) {
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

    public void put(String _key, Person _v) {
        mapPerson.put(_key, _v);
    }

    public void put(String _key, Point _v) {
        mapPoint.put(_key, _v);
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
    public void putDir(String _key, BoolVal _v) {
        mapDirection.put(_key, _v);
    }

    public boolean contains(String _key) {
        return containsBase(_key)||
                mapAlly.contains(_key)||
                mapWildPk.contains(_key)||
                mapPerson.contains(_key)||
                mapPoint.contains(_key)||
                mapCoords.contains(_key)||
                mapSimulationSteps.contains(_key)||
                mapTeamCrud.contains(_key)||
                mapGender.contains(_key)||
                mapPokemonPlayerDto.contains(_key)||
                mapDirection.contains(_key);
    }

    public PokemonPlayerDto getVal(String _key) {
        return mapPokemonPlayerDto.getVal(_key);
    }
    public TeamCrud getValTeamCrud(String _key) {
        return mapTeamCrud.getVal(_key);
    }
    public WildPk getValPk(String _key) {
        return mapWildPk.getVal(_key);
    }
    public AreaApparition getValArea(String _key) {
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
    public Point getValPt(String _key) {
        return mapPoint.getVal(_key);
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
        mapPoint.putAllMap(_m.mapPoint);
        mapCoords.putAllMap(_m.mapCoords);
        mapSimulationSteps.putAllMap(_m.mapSimulationSteps);
        mapTeamCrud.putAllMap(_m.mapTeamCrud);
        mapGender.putAllMap(_m.mapGender);
        mapPokemonPlayerDto.putAllMap(_m.mapPokemonPlayerDto);
        mapDirection.putAllMap(_m.mapDirection);
    }

    public void removeKey(String _key) {
        removeKeyBase(_key);
        mapAlly.removeKey(_key);
        mapWildPk.removeKey(_key);
        mapPerson.removeKey(_key);
        mapPoint.removeKey(_key);
        mapCoords.removeKey(_key);
        mapSimulationSteps.removeKey(_key);
        mapTeamCrud.removeKey(_key);
        mapGender.removeKey(_key);
        mapPokemonPlayerDto.removeKey(_key);
        mapDirection.removeKey(_key);
    }

    public StringMap<BoolVal> getMapDirection() {
        return mapDirection;
    }
}
