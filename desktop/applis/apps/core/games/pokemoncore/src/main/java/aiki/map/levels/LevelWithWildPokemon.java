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
import code.util.*;


public abstract class LevelWithWildPokemon extends Level {

    private CustList<AbsAreaApparition> wildPokemonAreas;

    private Points< CharacterInRoadCave> characters;

    private Points< DualFight> dualFights;

    private Points< WildPk> legendaryPks;

    private Points< String> items;

    private Points< Integer> tm;

    private Points< Integer> hm;

    protected final void validateLevelWithWildPokemon(DataBase _data, LevelArea _level) {
        checkAreasBlocks(_data);
        PointEqList keys_ = new PointEqList();
        for (EntryCust<Point,CharacterInRoadCave> e : characters.entryList()) {
            DataInfoChecker.checkKey(_data, _level, e.getKey(),true);
            e.getValue().validate(_data);
            keys_.add(e.getKey());
        }
        for (EntryCust<Point,DualFight> e : dualFights.entryList()) {
            Point id_ = e.getKey();
            DataInfoChecker.checkKey(_data, _level, id_,true);
            DualFight dual_ = e.getValue();
            DataInfoChecker.checkKey(_data, _level, dual_.getPt(),true);
            boolean isNext_ = isNextDual(id_, dual_);
            if (!isNext_) {
                _data.setError(true);
            }
            dual_.validate(_data);
            keys_.add(e.getKey());
            NullablePoint.tryAdd(keys_,dual_.getPt());
        }
        for (EntryCust<Point,WildPk> e : legendaryPks.entryList()) {
            DataInfoChecker.checkKey(_data, _level, e.getKey(),true);
            e.getValue().validateAsNpc(_data);
            DataInfoChecker.checkStringListContains(_data.getLegPks(),e.getValue().getName(),_data);
            keys_.add(e.getKey());
        }
        DataInfoChecker.checkStringListContains(_data.getItems().getKeys(),items.values(),_data);
        for (EntryCust<Point,String> e : items.entryList()) {
            DataInfoChecker.checkKey(_data, _level, e.getKey(),true);
            keys_.add(e.getKey());
        }
        DataInfoChecker.checkShortsContains(_data.getTm().getKeys(),tm.values(),_data);
        for (EntryCust<Point,Integer> e : tm.entryList()) {
            DataInfoChecker.checkKey(_data, _level, e.getKey(),true);
            keys_.add(e.getKey());
        }
        DataInfoChecker.checkShortsContains(_data.getHm().getKeys(),hm.values(),_data);
        for (EntryCust<Point,Integer> e : hm.entryList()) {
            DataInfoChecker.checkKey(_data, _level, e.getKey(),true);
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
        for (EntryCust<Point,Block> e : getBlocks().entryList()) {
            AbsAreaApparition a_ = getAreaByBlockId(e.getKey());
            if (!a_.isVirtual() && !a_.getWildPokemonFishing().isEmpty() && e.getValue().getType() != EnvironmentType.WATER) {
                _data.setError(true);
            }
        }
    }

    private boolean isNextDual(Point _id, DualFight _dual) {
        boolean isNext_ = false;
        for (Direction d : Direction.all()) {
            Point next_ = new Point(_id);
            next_.moveTo(d);
            if (Point.eq(_dual.getPt(), next_)) {
                isNext_ = true;
                break;
            }
        }
        return isNext_;
    }

    private boolean existBlock(int _index) {
        return existBlock(_index, getBlocks());
    }

    public static boolean existBlock(int _index, Points<Block> _bk) {
        boolean existBlock_ = false;
        for (Block b : _bk.values()) {
            if (b.getIndexApparition() == _index) {
                existBlock_ = true;
                break;
            }
        }
        return existBlock_;
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
        for (EntryCust<Point,DualFight> e : dualFights.entryList()) {
            if (!e.getValue().getFoeTrainer().hasValidImage(_data)) {
                val_ = false;
            }
        }
        for (EntryCust<Point,CharacterInRoadCave> e : characters.entryList()) {
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
        for (AbsAreaApparition a : wildPokemonAreas) {
            a.initializeWildPokemon();
        }
    }

    public AbsAreaApparition getAreaByBlockId(Point _key) {
        int index_ = getBlocks().getVal(_key).getIndexApparition();
        if (!wildPokemonAreas.isValidIndex(index_)) {
            return new AreaApparition();
        }
        return wildPokemonAreas.get(index_);
    }

    public AbsAreaApparition getAreaByPoint(Point _point) {
        int index_ = getBlockByPoint(_point).getIndexApparition();
        if (!wildPokemonAreas.isValidIndex(index_)) {
            return new AreaApparition();
        }
        return wildPokemonAreas.get(index_);
    }

    public PointsTrainerMultiFights getTrainers() {
        PointsTrainerMultiFights trainers_ = new PointsTrainerMultiFights();
        for (EntryCust<Point,CharacterInRoadCave> e: getCharacters().entryList()) {
            CharacterInRoadCave val_ = e.getValue();
            if (val_ instanceof TrainerMultiFights) {
                trainers_.addEntry(e.getKey(),(TrainerMultiFights) val_);
            }
        }
        return trainers_;
    }

    public CustList<AbsAreaApparition> getWildPokemonAreas() {
        return wildPokemonAreas;
    }

    public void setWildPokemonAreas(CustList<AbsAreaApparition> _wildPokemonAreas) {
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

    public Points< Integer> getTm() {
        return tm;
    }

    public void setTm(Points< Integer> _tm) {
        tm = _tm;
    }

    public Points< Integer> getHm() {
        return hm;
    }

    public void setHm(Points< Integer> _hm) {
        hm = _hm;
    }

}
