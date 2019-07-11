package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.characters.GerantPokemon;
import aiki.map.characters.Person;
import aiki.map.characters.Seller;
import aiki.map.tree.LevelArea;
import aiki.util.Point;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;


public final class LevelIndoorPokemonCenter extends Level {

    private ObjectMap<Point, Person> gerants;

    private Point storageCoords;

    @Override
    public void validate(DataBase _data, LevelArea _level) {
        super.validate(_data, _level);
        EqList<Point> keys_ = new EqList<Point>();
        if (!_level.isValid(storageCoords, true)) {
            _data.setError(true);
        }
        keys_.add(storageCoords);
        for (EntryCust<Point, Person> e : gerants.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
            }
            if (!(e.getValue() instanceof GerantPokemon)) {
                if (!(e.getValue() instanceof Seller)) {
                    _data.setError(true);
                }
            }
            if (e.getValue() instanceof Seller) {
                ((Seller) e.getValue()).validate(_data);
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
        if (Point.eq(storageCoords, _point)) {
            return false;
        }
        if (gerants.contains(_point)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty(Point _point) {
        if (Point.eq(storageCoords, _point)) {
            return false;
        }
        if (gerants.contains(_point)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        if (!super.hasValidImage(_data)) {
            return false;
        }
        for (Person p : gerants.values()) {
            if (!p.hasValidImage(_data)) {
                return false;
            }
        }
        return true;
    }

    public ObjectMap<Point, Person> getGerants() {
        return gerants;
    }

    public void setGerants(ObjectMap<Point, Person> _gerants) {
        gerants = _gerants;
    }

    public Point getStorageCoords() {
        return storageCoords;
    }

    public void setStorageCoords(Point _storageCoords) {
        storageCoords = _storageCoords;
    }
}
