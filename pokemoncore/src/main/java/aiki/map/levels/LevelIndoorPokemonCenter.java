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
            return;

        }
        keys_.add(storageCoords);
        for (EntryCust<Point, Person> e : gerants.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
                return;

            }
            if (!(e.getValue() instanceof GerantPokemon)) {
                if (!(e.getValue() instanceof Seller)) {
                    _data.setError(true);
                    return;

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
            return;

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

    @Override
    public void clearElements(Point _point) {
        gerants.removeKey(_point);
        if (Point.eq(storageCoords, _point)) {
            storageCoords = new Point();
        }
    }

    @Override
    public void translateByLine(short _y, short _dir) {
        super.translateByLine(_y, _dir);
        Level.translatePersonLineData(gerants, _y, _dir);
        if (storageCoords.gety() > _y) {
            storageCoords.sety((short) (storageCoords.gety() + _dir));
        }
    }

    @Override
    public void translateByColumn(short _x, short _dir) {
        super.translateByColumn(_x, _dir);
        Level.translatePersonColumnData(gerants, _x, _dir);
        if (storageCoords.getx() > _x) {
            storageCoords.setx((short) (storageCoords.getx() + _dir));
        }
    }

    @Override
    public void translateElement(Point _id, Point _target) {
        if (!isEmptyForAdding(_target)) {
            return;
        }
        if (Point.eq(storageCoords, _id)) {
            storageCoords.affect(_id);
        }
        if (gerants.contains(_id)) {
            gerants.move(_id, _target);
        }
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
