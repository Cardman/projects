package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.enums.Direction;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.WildPk;
import aiki.map.tree.LevelArea;
import aiki.util.Point;
import aiki.util.PointParam;
import aiki.util.Points;
import code.util.*;
import code.util.core.StringUtil;


public abstract class LevelWithWildPokemon extends Level {

    private CustList<AreaApparition> wildPokemonAreas;

    private Points< CharacterInRoadCave> characters;

    private Points< DualFight> dualFights;

    private Points< WildPk> legendaryPks;

    private Points< String> items;

    private Points< Short> tm;

    private Points< Short> hm;

    protected final void validateLevelWithWildPokemon(DataBase _data, LevelArea _level) {
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
            }
            index_++;
        }
        for (PointParam<Block> e : getBlocks().entryList()) {
            AreaApparition a_ = getAreaByBlockId(e.getKey());
            if (a_.isVirtual()) {
                continue;
            }
            if (!a_.getWildPokemonFishing().isEmpty()) {
                if (e.getValue().getType() != EnvironmentType.WATER) {
                    _data.setError(true);
                }
            }
        }
        EqList<Point> keys_ = new EqList<Point>();
        for (PointParam<CharacterInRoadCave> e : characters.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
            }
            e.getValue().validate(_data);
            keys_.add(e.getKey());
        }
        for (PointParam<DualFight> e : dualFights.entryList()) {
            Point id_ = e.getKey();
            if (!_level.isValid(id_, true)) {
                _data.setError(true);
            }
            DualFight dual_ = e.getValue();
            if (!_level.isValid(dual_.getPt(), true)) {
                _data.setError(true);
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
            }
            dual_.validate(_data);
            keys_.add(e.getKey());
            keys_.add(dual_.getPt());
        }
        for (PointParam<WildPk> e : legendaryPks.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
            }
            e.getValue().validateAsNpc(_data);
            if (!StringUtil.contains(_data.getLegPks(),e.getValue().getName())) {
                _data.setError(true);
            }
            keys_.add(e.getKey());
        }
        for (PointParam<String> e : items.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
            }
            if (!_data.getItems().contains(e.getValue())) {
                _data.setError(true);
            }
            keys_.add(e.getKey());
        }
        for (PointParam<Short> e : tm.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
            }
            if (!_data.getTm().contains(e.getValue())) {
                _data.setError(true);
            }
            keys_.add(e.getKey());
        }
        for (PointParam<Short> e : hm.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
            }
            if (!_data.getHm().contains(e.getValue())) {
                _data.setError(true);
            }
            keys_.add(e.getKey());
        }
        if (keys_.hasDuplicates()) {
            _data.setError(true);
        }
    }

    @Override
    public boolean isEmptyForAdding(Point _point) {
        boolean empt_ = true;
        if (characters.contains(_point)) {
            empt_ = false;
        }
        if (legendaryPks.contains(_point)) {
            empt_ = false;
        }
        if (items.contains(_point)) {
            empt_ = false;
        }
        if (tm.contains(_point)) {
            empt_ = false;
        }
        if (hm.contains(_point)) {
            empt_ = false;
        }
        if (dualFights.contains(_point)) {
            empt_ = false;
        }
        for (DualFight d : dualFights.values()) {
            if (Point.eq(d.getPt(), _point)) {
                empt_ = false;
            }
        }
        return empt_;
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        boolean val_ = true;
        if (!super.hasValidImage(_data)) {
            val_ = false;
        }
        for (PointParam<DualFight> e : dualFights.entryList()) {
            if (!e.getValue().getFoeTrainer().hasValidImage(_data)) {
                val_ = false;
            }
        }
        for (PointParam<CharacterInRoadCave> e : characters.entryList()) {
            if (!e.getValue().hasValidImage(_data)) {
                val_ = false;
            }
        }
        return val_;
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

    public Points< CharacterInRoadCave> getCharacters() {
        return characters;
    }

    public void setCharacters(Points< CharacterInRoadCave> _characters) {
        characters = _characters;
    }

    public Points< DualFight> getDualFights() {
        return dualFights;
    }

    public void setDualFights(Points< DualFight> _dualFights) {
        dualFights = _dualFights;
    }

    public Points< WildPk> getLegendaryPks() {
        return legendaryPks;
    }

    public void setLegendaryPks(Points< WildPk> _legendaryPks) {
        legendaryPks = _legendaryPks;
    }

    public Points< String> getItems() {
        return items;
    }

    public void setItems(Points< String> _items) {
        items = _items;
    }

    public Points< Short> getTm() {
        return tm;
    }

    public void setTm(Points< Short> _tm) {
        tm = _tm;
    }

    public Points< Short> getHm() {
        return hm;
    }

    public void setHm(Points< Short> _hm) {
        hm = _hm;
    }

}
