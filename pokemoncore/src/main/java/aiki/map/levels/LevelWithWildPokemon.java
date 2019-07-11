package aiki.map.levels;

import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.enums.Direction;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.WildPk;
import aiki.map.tree.LevelArea;
import aiki.util.Point;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;


public abstract class LevelWithWildPokemon extends Level {

    private CustList<AreaApparition> wildPokemonAreas;

    private ObjectMap<Point, CharacterInRoadCave> characters;

    private ObjectMap<Point, DualFight> dualFights;

    private ObjectMap<Point, WildPk> legendaryPks;

    private ObjectMap<Point, String> items;

    private ObjectMap<Point, Short> tm;

    private ObjectMap<Point, Short> hm;

    @Override
    public void validate(DataBase _data, LevelArea _level) {
        super.validate(_data, _level);
        int index_ = 0;
        for (AreaApparition a : wildPokemonAreas) {
            a.validate(_data);
            boolean existBlock_ = false;
            for (Block b : getBlocks().values()) {
                if (b.getIndexApparition() == index_) {
                    existBlock_ = true;
                    break;
                }
            }
            if (!existBlock_) {
                _data.setError(true);
                return;

            }
            index_++;
        }
        for (EntryCust<Point, Block> e : getBlocks().entryList()) {
            AreaApparition a_ = getAreaByBlockId(e.getKey());
            if (a_.isVirtual()) {
                continue;
            }
            if (!a_.getWildPokemonFishing().isEmpty()) {
                if (e.getValue().getType() != EnvironmentType.WATER) {
                    _data.setError(true);
                    return;

                }
            }
        }
        EqList<Point> keys_ = new EqList<Point>();
        for (EntryCust<Point, CharacterInRoadCave> e : characters.entryList()) {
            if (!(e.getValue() instanceof Person)) {
                continue;
            }
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
                return;

            }
            e.getValue().validate(_data);
            keys_.add(e.getKey());
        }
        for (EntryCust<Point, DualFight> e : dualFights.entryList()) {
            Point id_ = e.getKey();
            if (!_level.isValid(id_, true)) {
                _data.setError(true);
                return;

            }
            DualFight dual_ = e.getValue();
            if (!_level.isValid(dual_.getPt(), true)) {
                _data.setError(true);
                return;

            }
            boolean isNext_ = false;
            for (Direction d : Direction.values()) {
                Point next_ = new Point(id_);
                next_.moveTo(d);
                if (Point.eq(next_, dual_.getPt())) {
                    isNext_ = true;
                    break;
                }
            }
            if (!isNext_) {
                _data.setError(true);
                return;

            }
            dual_.validate(_data);
            keys_.add(e.getKey());
            keys_.add(dual_.getPt());
        }
        for (EntryCust<Point, WildPk> e : legendaryPks.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
                return;

            }
            e.getValue().validateAsNpc(_data);
            // if (!e.getValue().isValid(_data)) {
            // _data.setError(true);

            // }
            PokemonData fPk_ = _data.getPokemon(e.getValue().getName());
            if (fPk_.getGenderRep() != GenderRepartition.LEGENDARY) {
                _data.setError(true);
                return;

            }
            keys_.add(e.getKey());
        }
        for (EntryCust<Point, String> e : items.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
                return;

            }
            if (!_data.getItems().contains(e.getValue())) {
                _data.setError(true);
                return;

            }
            keys_.add(e.getKey());
        }
        for (EntryCust<Point, Short> e : tm.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
                return;

            }
            if (!_data.getTm().contains(e.getValue())) {
                _data.setError(true);
                return;

            }
            keys_.add(e.getKey());
        }
        for (EntryCust<Point, Short> e : hm.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
                return;

            }
            if (!_data.getHm().contains(e.getValue())) {
                _data.setError(true);
                return;

            }
            keys_.add(e.getKey());
        }
        int size_ = keys_.size();
        keys_.removeDuplicates();
        if (size_ != keys_.size()) {
            _data.setError(true);

        }
    }

    @Override
    public boolean isEmptyForAdding(Point _point) {
        if (characters.contains(_point)) {
            return false;
        }
        if (legendaryPks.contains(_point)) {
            return false;
        }
        if (items.contains(_point)) {
            return false;
        }
        if (tm.contains(_point)) {
            return false;
        }
        if (hm.contains(_point)) {
            return false;
        }
        if (dualFights.contains(_point)) {
            return false;
        }
        for (DualFight d : dualFights.values()) {
            if (Point.eq(d.getPt(), _point)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        if (!super.hasValidImage(_data)) {
            return false;
        }
        for (EntryCust<Point, DualFight> e : dualFights.entryList()) {
            if (!e.getValue().getFoeTrainer().hasValidImage(_data)) {
                return false;
            }
        }
        for (EntryCust<Point, CharacterInRoadCave> e : characters.entryList()) {
            if (!e.getValue().hasValidImage(_data)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty(Point _point) {
        return !characters.contains(_point);
    }

    public boolean containsPokemon(Point _point) {
        return legendaryPks.contains(_point);
    }

    public WildPk getPokemon(Point _point) {
        return legendaryPks.getVal(_point);
    }

    public Person getPerson(Point _point) {
        return (Person) characters.getVal(_point);
    }

    public boolean containsDualFight(Point _point) {
        return dualFights.contains(_point);
    }

    public DualFight getDualFight(Point _point) {
        return dualFights.getVal(_point);
    }

    public void initializeWildPokemon() {
        for (AreaApparition a : wildPokemonAreas) {
            a.initializeWildPokemon();
        }
    }

    public AreaApparition getAreaByBlockId(Point _key) {
        int index_ = getBlocks().getVal(_key).getIndexApparition();
        if (!wildPokemonAreas.isValidIndex(index_)) {
            return new AreaApparition();
        }
        return wildPokemonAreas.get(index_);
    }

    public AreaApparition getAreaByPoint(Point _point) {
        int index_ = getBlockByPoint(_point).getIndexApparition();
        if (!wildPokemonAreas.isValidIndex(index_)) {
            return new AreaApparition();
        }
        return wildPokemonAreas.get(index_);
    }

    public CustList<AreaApparition> getWildPokemonAreas() {
        return wildPokemonAreas;
    }

    public void setWildPokemonAreas(CustList<AreaApparition> _wildPokemonAreas) {
        wildPokemonAreas = _wildPokemonAreas;
    }

    public ObjectMap<Point, CharacterInRoadCave> getCharacters() {
        return characters;
    }

    public void setCharacters(ObjectMap<Point, CharacterInRoadCave> _characters) {
        characters = _characters;
    }

    public ObjectMap<Point, DualFight> getDualFights() {
        return dualFights;
    }

    public void setDualFights(ObjectMap<Point, DualFight> _dualFights) {
        dualFights = _dualFights;
    }

    public ObjectMap<Point, WildPk> getLegendaryPks() {
        return legendaryPks;
    }

    public void setLegendaryPks(ObjectMap<Point, WildPk> _legendaryPks) {
        legendaryPks = _legendaryPks;
    }

    public ObjectMap<Point, String> getItems() {
        return items;
    }

    public void setItems(ObjectMap<Point, String> _items) {
        items = _items;
    }

    public ObjectMap<Point, Short> getTm() {
        return tm;
    }

    public void setTm(ObjectMap<Point, Short> _tm) {
        tm = _tm;
    }

    public ObjectMap<Point, Short> getHm() {
        return hm;
    }

    public void setHm(ObjectMap<Point, Short> _hm) {
        hm = _hm;
    }

}
