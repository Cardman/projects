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
        if (!(pl_ instanceof Cave)) {
            return null;
        }
        Cave cave_ = (Cave) pl_;
        CustList<LevelCave> levels_ = cave_.getLevels();
        if (!levels_.isValidIndex(level)) {
            return null;
        }
        for (ChangeNbPlaceFieldAct e:collection) {
            if (e.place() == place && e.value() == level) {
                return null;
            }
        }
        LevelCave l_ = levels_.get(level);
        cave_.getLevels().remove(level);
        renameLevel();
        return l_;
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
            if (e.place() == place && e.value() > level) {
                e.value(e.value() - 1);
            }
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
