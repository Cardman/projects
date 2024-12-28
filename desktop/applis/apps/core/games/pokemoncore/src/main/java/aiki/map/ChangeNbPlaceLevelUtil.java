package aiki.map;

import aiki.map.levels.*;
import aiki.map.places.*;
import code.util.*;

public final class ChangeNbPlaceLevelUtil {
    private final CustList<ChangeNbPlaceFieldAct> collection = new CustList<ChangeNbPlaceFieldAct>();
    private final int place;
    private final int level;

    public ChangeNbPlaceLevelUtil(int _p, int _l) {
        this.place = _p;
        this.level = _l;
    }

    public void add(ChangeNbPlaceFieldAct _map) {
        collection.add(_map);
    }
    public Level containedLevel(CustList<Place> _places) {
        if (!_places.isValidIndex(place)) {
            return null;
        }
        Place pl_ = _places.get(place);
        Level rem_ = null;
        if (pl_ instanceof Cave) {
            Cave cave_ = (Cave) pl_;
            CustList<LevelCave> levels_ = cave_.getLevels();
            if (!levels_.isValidIndex(level)) {
                return null;
            }
            Place r_ = tryRemove(pl_);
            if (r_ == null) {
                return null;
            }
            rem_ = levels_.get(level);
            cave_.getLevels().remove(level);
            renameLevel();
        }
        if (pl_ instanceof League) {
            League cave_ = (League) pl_;
            CustList<LevelLeague> levels_ = cave_.getRooms();
            if (!levels_.isValidIndex(level)) {
                return null;
            }
            Place r_ = tryRemove(pl_);
            if (r_ == null) {
                return null;
            }
            rem_ = levels_.get(level);
            cave_.getRooms().remove(level);
            renameLevel();
        }
        return rem_;
    }
    private Place tryRemove(Place _pl) {
        for (ChangeNbPlaceFieldAct e:collection) {
            if (e.matchLevel(place, level)) {
                return null;
            }
        }
        return _pl;
    }
    public Place containedPlace(CustList<Place> _places) {
        if (!_places.isValidIndex(place)) {
            return null;
        }
        for (ChangeNbPlaceFieldAct e:collection) {
            if (e.place() == place) {
                return null;
            }
        }
        Place pl_ = _places.get(place);
        _places.remove(place);
        renamePlace();
        return pl_;
    }
    public void renameLevel() {
        for (ChangeNbPlaceFieldAct e:collection) {
            e.decr(place,level);
        }
    }
    public void renamePlace() {
        for (ChangeNbPlaceFieldAct e:collection) {
            if (e.place() > place) {
                e.place(e.place() - 1);
            }
        }
    }
}
