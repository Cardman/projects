package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.characters.GerantPokemon;
import aiki.map.characters.Person;
import aiki.map.characters.Seller;
import aiki.map.tree.LevelArea;
import aiki.util.*;
import code.util.*;


public final class LevelIndoorPokemonCenter extends Level {

    private Points< Person> gerants;

    private Point storageCoords;

    @Override
    public void validate(DataBase _data, LevelArea _level) {
        super.validate(_data, _level);
        for (EntryCust<Point,Person> e : gerants.entryList()) {
            if (!(e.getValue() instanceof GerantPokemon) && !(e.getValue() instanceof Seller)) {
                _data.setError(true);
            }
            if (e.getValue() instanceof Seller) {
                ((Seller) e.getValue()).validate(_data);
            }
        }
    }
    public PointEqList validateLinks() {
        PointEqList keys_ = new PointEqList();
        for (EntryCust<Point, Person> e : gerants.entryList()) {
            keys_.add(e.getKey());
        }
        keys_.add(storageCoords);
        return keys_;
    }

    @Override
    public boolean isEmpty(Point _point) {
        return isEmptyForAdding(_point);
    }

    @Override
    public boolean isEmptyForAdding(Point _point) {
        if (Point.eq(storageCoords, _point)) {
            return false;
        }
        return !gerants.contains(_point);
    }
    @Override
    public boolean hasValidImage(DataBase _data) {
        boolean val_ = super.hasValidImage(_data);
        for (Person p : gerants.values()) {
            if (!p.hasValidImage(_data)) {
                val_ = false;
            }
        }
        return val_;
    }

    public Points< Person> getGerants() {
        return gerants;
    }

    public void setGerants(Points< Person> _gerants) {
        gerants = _gerants;
    }

    public Point getStorageCoords() {
        return storageCoords;
    }

    public void setStorageCoords(Point _storageCoords) {
        storageCoords = _storageCoords;
    }
}
