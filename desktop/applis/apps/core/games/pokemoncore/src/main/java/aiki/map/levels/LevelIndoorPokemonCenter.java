package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.characters.*;
import aiki.map.tree.LevelArea;
import aiki.util.*;
import code.util.*;


public final class LevelIndoorPokemonCenter extends Level {

    private Points< Person> gerants;

    private NullablePoint storageCoords;

    @Override
    public void validate(DataBase _data, LevelArea _level) {
        super.validate(_data, _level);
        gerants = tryAdd(gerants, new IdPersonMapper<Point>(),new IdPersonMapper<GerantPokemon>(),new IdPersonMapper<Seller>());
        for (EntryCust<Point,Person> e : gerants.entryList()) {
            if (e.getValue() instanceof Seller) {
                ((Seller) e.getValue()).validate(_data);
            }
        }
    }
    public static Points< Person> tryAdd(Points<Person> _from, AbsPersonMapper<Point> _c, AbsPersonMapper<GerantPokemon> _g, AbsPersonMapper<Seller> _s) {
        int count_ = 0;
        for (EntryCust<Point,Person> e : _from.entryList()) {
            Person v_ = e.getValue();
            if (v_ instanceof GerantPokemon) {
                count_++;
            }
            if (v_ instanceof Seller) {
                count_++;
            }
        }
        Points< Person> n_= new PointsPerson(new CollCapacity(count_));
        for (EntryCust<Point,Person> e : _from.entryList()) {
            Point k_ = e.getKey();
            Person v_ = e.getValue();
            if (v_ instanceof GerantPokemon) {
                n_.addEntry(_c.map(k_),_g.map((GerantPokemon) v_));
            }
            if (v_ instanceof Seller) {
                n_.addEntry(_c.map(k_),_s.map((Seller) v_));
            }
        }
        return n_;
    }
    public CustList<NullablePoint> validateLinks() {
        CustList<NullablePoint> keys_ = new CustList<NullablePoint>();
        for (EntryCust<Point, Person> e : gerants.entryList()) {
            keys_.add(new NullablePoint(e.getKey()));
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

    public NullablePoint getStorageCoords() {
        return storageCoords;
    }

    public void setStorageCoords(Point _storageCoords) {
        setStorageCoords(new NullablePoint(_storageCoords));
    }

    public void setStorageCoords(NullablePoint _storageCoords) {
        this.storageCoords = _storageCoords;
    }
}
