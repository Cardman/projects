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
import code.maths.Rate;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class StringMapObject {

    private final StringMap<AreaApparition> mapArea = new StringMap<AreaApparition>();
    private final StringMap<Ally> mapAlly = new StringMap<Ally>();
    private final StringMap<WildPk> mapWildPk = new StringMap<WildPk>();
    private final StringMap<Person> mapPerson = new StringMap<Person>();
    private final StringMap<Rate> mapRate = new StringMap<Rate>();
    private final StringMap<Point> mapPoint = new StringMap<Point>();
    private final StringMap<Coords> mapCoords = new StringMap<Coords>();
    private final StringMap<Integer> mapInt = new StringMap<Integer>();
    private final StringMap<String> mapString = new StringMap<String>();
    private final StringMap<StringList> mapStringList = new StringMap<StringList>();
    private final StringMap<Boolean> mapBoolean = new StringMap<Boolean>();
    private final StringMap<SimulationSteps> mapSimulationSteps = new StringMap<SimulationSteps>();
    private final StringMap<TeamCrud> mapTeamCrud = new StringMap<TeamCrud>();
    private final StringMap<Gender> mapGender = new StringMap<Gender>();
    private final StringMap<PokemonPlayerDto> mapPokemonPlayerDto = new StringMap<PokemonPlayerDto>();

    public CustList<String> getKeys() {
        CustList<String> ls_ = new CustList<String>();
        ls_.addAllElts(mapArea.getKeys());
        ls_.addAllElts(mapAlly.getKeys());
        ls_.addAllElts(mapWildPk.getKeys());
        ls_.addAllElts(mapPerson.getKeys());
        ls_.addAllElts(mapRate.getKeys());
        ls_.addAllElts(mapPoint.getKeys());
        ls_.addAllElts(mapCoords.getKeys());
        ls_.addAllElts(mapInt.getKeys());
        ls_.addAllElts(mapString.getKeys());
        ls_.addAllElts(mapStringList.getKeys());
        ls_.addAllElts(mapBoolean.getKeys());
        ls_.addAllElts(mapSimulationSteps.getKeys());
        ls_.addAllElts(mapTeamCrud.getKeys());
        ls_.addAllElts(mapGender.getKeys());
        ls_.addAllElts(mapPokemonPlayerDto.getKeys());
        return ls_;
    }

    public void put(String _key, AreaApparition _v) {
        mapArea.put(_key, _v);
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

    public void put(String _key, Rate _v) {
        mapRate.put(_key, _v);
    }

    public void put(String _key, Point _v) {
        mapPoint.put(_key, _v);
    }

    public void put(String _key, Coords _v) {
        mapCoords.put(_key, _v);
    }

    public void put(String _key, int _v) {
        mapInt.put(_key, _v);
    }

    public void put(String _key, String _v) {
        mapString.put(_key, _v);
    }

    public void put(String _key, boolean _v) {
        mapBoolean.put(_key, _v);
    }

    public void put(String _key, StringList _v) {
        mapStringList.put(_key, _v);
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
        return mapArea.contains(_key)||
                mapAlly.contains(_key)||
                mapWildPk.contains(_key)||
                mapPerson.contains(_key)||
                mapRate.contains(_key)||
                mapPoint.contains(_key)||
                mapCoords.contains(_key)||
                mapInt.contains(_key)||
                mapString.contains(_key)||
                mapStringList.contains(_key)||
                mapBoolean.contains(_key)||
                mapSimulationSteps.contains(_key)||
                mapTeamCrud.contains(_key)||
                mapGender.contains(_key)||
                mapPokemonPlayerDto.contains(_key);
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
        return mapArea.getVal(_key);
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
    public Rate getValRate(String _key) {
        return mapRate.getVal(_key);
    }
    public Boolean getValBool(String _key) {
        return mapBoolean.getVal(_key);
    }

    public StringList getValList(String _key) {
        return mapStringList.getVal(_key);
    }
    public String getValStr(String _key) {
        return mapString.getVal(_key);
    }

    public Integer getValInt(String _key) {
        return mapInt.getVal(_key);
    }

    public void putAllMap(StringMapObject _m) {
        for (EntryCust<String, AreaApparition> e: _m.mapArea.entryList()) {
            mapArea.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, Ally> e: _m.mapAlly.entryList()) {
            mapAlly.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, WildPk> e: _m.mapWildPk.entryList()) {
            mapWildPk.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, Person> e: _m.mapPerson.entryList()) {
            mapPerson.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, Rate> e: _m.mapRate.entryList()) {
            mapRate.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, Point> e: mapPoint.entryList()) {
            mapPoint.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, Coords> e: mapCoords.entryList()) {
            mapCoords.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, Integer> e: mapInt.entryList()) {
            mapInt.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, String> e: mapString.entryList()) {
            mapString.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, StringList> e: mapStringList.entryList()) {
            mapStringList.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, Boolean> e: mapBoolean.entryList()) {
            mapBoolean.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, SimulationSteps> e: mapSimulationSteps.entryList()) {
            mapSimulationSteps.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, TeamCrud> e: mapTeamCrud.entryList()) {
            mapTeamCrud.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, Gender> e: mapGender.entryList()) {
            mapGender.put(e.getKey(), e.getValue());
        }
        for (EntryCust<String, PokemonPlayerDto> e: mapPokemonPlayerDto.entryList()) {
            mapPokemonPlayerDto.put(e.getKey(), e.getValue());
        }
    }

    public void removeKey(String _key) {
        mapArea.removeKey(_key);
        mapAlly.removeKey(_key);
        mapWildPk.removeKey(_key);
        mapPerson.removeKey(_key);
        mapRate.removeKey(_key);
        mapPoint.removeKey(_key);
        mapCoords.removeKey(_key);
        mapInt.removeKey(_key);
        mapString.removeKey(_key);
        mapStringList.removeKey(_key);
        mapBoolean.removeKey(_key);
        mapSimulationSteps.removeKey(_key);
        mapTeamCrud.removeKey(_key);
        mapGender.removeKey(_key);
        mapPokemonPlayerDto.removeKey(_key);
    }

}
