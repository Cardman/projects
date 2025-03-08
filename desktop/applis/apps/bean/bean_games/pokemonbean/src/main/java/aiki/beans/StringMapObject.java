package aiki.beans;

import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.comparators.DictionaryComparatorUtil;
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
import code.bean.nat.NaSt;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;

public final class StringMapObject {
    private final StringMap<Rate> mapRate = new StringMap<Rate>();
    private final StringMap<Integer> mapInt = new StringMap<Integer>();
    private final StringMap<Long> mapLong = new StringMap<Long>();
    private final StringMap<String> mapString = new StringMap<String>();
    private final StringMap<StringList> mapStringList = new StringMap<StringList>();
    private final StringMap<BoolVal> mapBoolean = new StringMap<BoolVal>();
    private final StringMap<NaSt> beansOthers = new StringMap<NaSt>();
    private final CustList<TranslatedKey> evts = new CustList<TranslatedKey>();
    private final CustList<CustList<TranslatedKey>> evtsGroups = new CustList<CustList<TranslatedKey>>();
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
    private final StringMap<AbsMap<TranslatedKey,MoveData>> mapMoves = new StringMap<AbsMap<TranslatedKey,MoveData>>();
    private final StringMap<AbsMap<TranslatedKey,PokemonData>> mapPokedex = new StringMap<AbsMap<TranslatedKey,PokemonData>>();
    private final StringMap<AbsMap<TranslatedKey,Item>> mapItems = new StringMap<AbsMap<TranslatedKey,Item>>();
    private final StringMap<AbsMap<TranslatedKey,AbilityData>> mapAbilities = new StringMap<AbsMap<TranslatedKey,AbilityData>>();
    private final StringMap<AbsMap<TranslatedKey,Status>> mapStatus = new StringMap<AbsMap<TranslatedKey,Status>>();

    public static BoolVal to(boolean _v) {
        if (_v) {
            return BoolVal.TRUE;
        } else {
            return BoolVal.FALSE;
        }
    }

    public boolean containsBase(String _key) {
        return mapRate.contains(_key)||
                mapInt.contains(_key)||
                mapLong.contains(_key)||
                mapString.contains(_key)||
                mapStringList.contains(_key)||
                mapBoolean.contains(_key)||beansOthers.contains(_key);
    }
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
            putMoves(_key, DictionaryComparatorUtil.buildMovesData());
        }
    }
    public void putMoves(String _key, AbsMap<TranslatedKey,MoveData> _v) {
        mapMoves.put(_key, _v);
    }

    public void safePokedex(String _key) {
        if (!contains(_key)) {
            putPokedex(_key, DictionaryComparatorUtil.buildPkData());
        }
    }
    public void putPokedex(String _key, AbsMap<TranslatedKey,PokemonData> _v) {
        mapPokedex.put(_key, _v);
    }

    public void safeAbilities(String _key) {
        if (!contains(_key)) {
            putAbilities(_key, DictionaryComparatorUtil.buildAbilitiesData());
        }
    }
    public void putAbilities(String _key, AbsMap<TranslatedKey,AbilityData> _v) {
        mapAbilities.put(_key, _v);
    }

    public void safeItems(String _key) {
        if (!contains(_key)) {
            putItems(_key, DictionaryComparatorUtil.buildItemsData());
        }
    }
    public void putItems(String _key, AbsMap<TranslatedKey,Item> _v) {
        mapItems.put(_key, _v);
    }

    public void safeStatus(String _key) {
        if (!contains(_key)) {
            putStatus(_key, DictionaryComparatorUtil.buildStatusData());
        }
    }
    public void putStatus(String _key, AbsMap<TranslatedKey,Status> _v) {
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
    public AbsMap<TranslatedKey,MoveData> getValMoveData(String _key) {
        return mapMoves.getVal(_key);
    }
    public AbsMap<TranslatedKey,PokemonData> getValPokemonData(String _key) {
        return mapPokedex.getVal(_key);
    }
    public AbsMap<TranslatedKey,AbilityData> getValAbilityData(String _key) {
        return mapAbilities.getVal(_key);
    }
    public AbsMap<TranslatedKey,Item> getValItemData(String _key) {
        return mapItems.getVal(_key);
    }
    public AbsMap<TranslatedKey,Status> getValStatusData(String _key) {
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


    public static boolean from(BoolVal _v) {
        return _v == BoolVal.TRUE;
    }

    public void removeKeyBase(String _key) {
        mapRate.removeKey(_key);
        mapInt.removeKey(_key);
        mapLong.removeKey(_key);
        mapString.removeKey(_key);
        mapStringList.removeKey(_key);
        mapBoolean.removeKey(_key);
        beansOthers.removeKey(_key);
    }
    public void put(String _key, Rate _v) {
        mapRate.put(_key, _v);
    }

    public void put(String _key, int _v) {
        mapInt.put(_key, _v);
    }

    public void put(String _key, long _v) {
        mapLong.put(_key, _v);
    }

    public void put(String _key, String _v) {
        mapString.put(_key, _v);
    }

    public void put(String _key, boolean _v) {
        put(_key, to(_v));
    }

    public void put(String _key, BoolVal _v) {
        mapBoolean.put(_key, _v);
    }

    public void put(String _key, StringList _v) {
        mapStringList.put(_key, _v);
    }

    public void put(String _key, NaSt _v) {
        beansOthers.put(_key, _v);
    }
    public Rate getValRate(String _key) {
        return mapRate.getVal(_key);
    }
    public boolean getValBool(String _key) {
        return from(mapBoolean.getVal(_key));
    }

    public StringList getValList(String _key) {
        return mapStringList.getVal(_key);
    }
    public String getValStr(String _key) {
        return mapString.getVal(_key);
    }

    public int getValInt(String _key) {
        return defInt(mapInt.getVal(_key));
    }

    public long getValLong(String _key) {
        return defLong(mapLong.getVal(_key));
    }
    private int defInt(Integer _i) {
        if (_i == null) {
            return 0;
        }
        return _i;
    }
    private long defLong(Long _i) {
        if (_i == null) {
            return 0;
        }
        return _i;
    }

    public StringMap<NaSt> getBeansOthers() {
        return beansOthers;
    }

    public CustList<TranslatedKey> getEvts() {
        return evts;
    }

    public CustList<CustList<TranslatedKey>> getEvtsGroups() {
        return evtsGroups;
    }
}
