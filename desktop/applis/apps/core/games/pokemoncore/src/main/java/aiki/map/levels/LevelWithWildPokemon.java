package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.enums.Direction;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.WildPk;
import aiki.map.tree.LevelArea;
import aiki.util.*;
import code.util.CustList;


public abstract class LevelWithWildPokemon extends Level {

    private CustList<AreaApparition> wildPokemonAreas;

    private Points< CharacterInRoadCave> characters;

    private Points< DualFight> dualFights;

    private Points< WildPk> legendaryPks;

    private Points< String> items;

    private Points< Short> tm;

    private Points< Short> hm;

    protected final void validateLevelWithWildPokemon(DataBase _data, LevelArea _level) {
        checkAreasBlocks(_data);
        PointEqList keys_ = new PointEqList();
        for (CommonParam<Point,CharacterInRoadCave> e : characters.entryList()) {
            checkKey(_data, _level, e.getKey());
            e.getValue().validate(_data);
            keys_.add(e.getKey());
        }
        for (CommonParam<Point,DualFight> e : dualFights.entryList()) {
            Point id_ = e.getKey();
            checkKey(_data, _level, id_);
            DualFight dual_ = e.getValue();
            checkKey(_data, _level, dual_.getPt());
            boolean isNext_ = isNextDual(id_, dual_);
            if (!isNext_) {
                _data.setError(true);
            }
            dual_.validate(_data);
            keys_.add(e.getKey());
            keys_.add(dual_.getPt());
        }
        for (CommonParam<Point,WildPk> e : legendaryPks.entryList()) {
            checkKey(_data, _level, e.getKey());
            e.getValue().validateAsNpc(_data);
            DataInfoChecker.checkStringListContains(_data.getLegPks(),e.getValue().getName(),_data);
            keys_.add(e.getKey());
        }
        DataInfoChecker.checkStringListContains(_data.getItems().getKeys(),items.values(),_data);
        for (CommonParam<Point,String> e : items.entryList()) {
            checkKey(_data, _level, e.getKey());
            keys_.add(e.getKey());
        }
        DataInfoChecker.checkShortsContains(_data.getTm().getKeys(),tm.values(),_data);
        for (CommonParam<Point,Short> e : tm.entryList()) {
            checkKey(_data, _level, e.getKey());
            keys_.add(e.getKey());
        }
        DataInfoChecker.checkShortsContains(_data.getHm().getKeys(),hm.values(),_data);
        for (CommonParam<Point,Short> e : hm.entryList()) {
            checkKey(_data, _level, e.getKey());
            keys_.add(e.getKey());
        }
        if (keys_.hasDuplicates()) {
            _data.setError(true);
        }
    }

    private void checkAreasBlocks(DataBase _data) {
        int nbAreas_ = wildPokemonAreas.size();
        for (int i = 0; i < nbAreas_; i++) {
            wildPokemonAreas.get(i).validate(_data);
            boolean existBlock_ = existBlock(i);
            if (!existBlock_) {
                _data.setError(true);
            }
        }
        for (CommonParam<Point,Block> e : getBlocks().entryList()) {
            AreaApparition a_ = getAreaByBlockId(e.getKey());
            if (!a_.isVirtual() && !a_.getWildPokemonFishing().isEmpty() && e.getValue().getType() != EnvironmentType.WATER) {
                _data.setError(true);
            }
        }
    }

    private boolean isNextDual(Point _id, DualFight _dual) {
        boolean isNext_ = false;
        for (Direction d : Direction.values()) {
            Point next_ = new Point(_id);
            next_.moveTo(d);
            if (Point.eq(next_, _dual.getPt())) {
                isNext_ = true;
                break;
            }
        }
        return isNext_;
    }

    private boolean existBlock(int _index) {
        boolean existBlock_ = false;
        for (Block b : getBlocks().values()) {
            if (b.getIndexApparition() == _index) {
                existBlock_ = true;
                break;
            }
        }
        return existBlock_;
    }

    private void checkKey(DataBase _data, LevelArea _level, Point _key) {
        if (!_level.isValid(_key, true)) {
            _data.setError(true);
        }
    }

    @Override
    public boolean isEmptyForAdding(Point _point) {
        boolean empt_ = !characters.contains(_point);
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
        boolean val_ = super.hasValidImage(_data);
        for (CommonParam<Point,DualFight> e : dualFights.entryList()) {
            if (!e.getValue().getFoeTrainer().hasValidImage(_data)) {
                val_ = false;
            }
        }
        for (CommonParam<Point,CharacterInRoadCave> e : characters.entryList()) {
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

    public PointsTrainerMultiFights getTrainers() {
        PointsTrainerMultiFights trainers_ = new PointsTrainerMultiFights();
        for (CommonParam<Point,CharacterInRoadCave> e: getCharacters().entryList()) {
            CharacterInRoadCave val_ = e.getValue();
            if (val_ instanceof TrainerMultiFights) {
                trainers_.addEntry(e.getKey(),(TrainerMultiFights) val_);
            }
        }
        return trainers_;
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
