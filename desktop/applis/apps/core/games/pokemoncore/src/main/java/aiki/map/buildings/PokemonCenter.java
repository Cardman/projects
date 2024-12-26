package aiki.map.buildings;
import aiki.db.DataBase;
import aiki.map.enums.Direction;
import aiki.map.levels.Level;
import aiki.map.levels.LevelIndoorPokemonCenter;
import aiki.map.tree.BuildingArea;
import aiki.util.Point;
import aiki.util.PointEqList;


public final class PokemonCenter extends Building {

    private LevelIndoorPokemonCenter level;

    @Override
    public void validate(DataBase _data,BuildingArea _buildingArea) {
        super.validate(_data, _buildingArea);
//        if (_buildingArea == null) {
//            _data.setError(true);
//            return;
//        }
        level.validate(_data, _buildingArea.getLevel());
        validatePoints(_data, _buildingArea, level.validateLinks());
        PointEqList all_ = new PointEqList();
        all_.add(getExitCity().value());
        PointEqList allEmpty_ = new PointEqList();
        allEmpty_.add(getExitCity().value());
        PointEqList current_ = new PointEqList();
        current_.add(getExitCity().value());
        while (true) {
            PointEqList next_ = new PointEqList();
            for (Point c: current_) {
                for (Point n: next(c,_buildingArea,all_,allEmpty_)) {
                    next_.add(n);
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        PointEqList must_ = new PointEqList();
        must_.add(level.getStorageCoords().value());
        must_.addAllElts(level.getGerants().getKeys());
        if (!all_.containsAllObj(must_)) {
            _data.setError(true);
        }
    }
    private PointEqList next(Point _pt, BuildingArea _buildingArea, PointEqList _all, PointEqList _empty) {
        PointEqList n_ = new PointEqList();
        if (!_empty.containsObj(_pt)) {
            return n_;
        }
        for (Direction d: Direction.all()) {
            Point next_ = new Point(_pt);
            next_.moveTo(d);
            if (_all.containsObj(next_)) {
                continue;
            }
            if (_buildingArea.isValid(next_)) {
                if (level.isEmpty(next_)) {
                    _empty.add(next_);
                }
                _all.add(next_);
                n_.add(next_);
            }
        }
        return n_;
    }

    @Override
    public Level getLevel() {
        return getIndoor();
    }
    public LevelIndoorPokemonCenter getIndoor() {
        return level;
    }

    public void setLevel(LevelIndoorPokemonCenter _level) {
        level = _level;
    }
}
