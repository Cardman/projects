package aiki.map.tree;

import aiki.map.buildings.Building;
import aiki.map.levels.Level;
import aiki.map.places.City;
import aiki.map.places.Place;
import aiki.util.*;
import code.util.*;


public class PlaceArea {

    private Points< BuildingArea> buildings;
    private CustList<LevelArea> levels;

    public void initialize(Place _place) {
        buildings = new PointsBuildingArea();
        levels = new CustList<LevelArea>();
        for (Level l : _place.getLevelsList()) {
            LevelArea l_ = new LevelArea();
            l_.initialize(l);
            levels.add(l_);
        }
        if (_place instanceof City) {
            Points< Building> buildings_ = ((City) _place)
                    .getBuildings();
            for (EntryCust<Point,Building> p : buildings_.entryList()) {
                BuildingArea b_ = new BuildingArea();
                b_.initialize(p.getValue());
                buildings.put(p.getKey(), b_);
            }
        }
    }

    public boolean isValid(Coords _coords, boolean _accessible) {
        LevelPoint levelPoint_ = _coords.getLevel();
        Point point_ = levelPoint_.getPoint();
        if (_coords.isInside()) {
            Point ptInside_ = _coords.getInsideBuilding();
            for (EntryCust<Point,BuildingArea> p : buildings.entryList()) {
                if (Point.eq(p.getKey(), ptInside_)) {
                    return p.getValue().isValid(point_);
                }
            }
            return false;
        }
        if (!levels.isValidIndex(levelPoint_.getLevelIndex())) {
            return false;
        }
        return levels.get(levelPoint_.getLevelIndex()).isValid(point_,
                _accessible);
    }

    public boolean isValidLevel(int _ind) {
        return levels.isValidIndex(_ind);
    }

    public LevelArea getLevel(int _ind) {
        return levels.get(_ind);
    }

    public CustList<LevelArea> getLevels() {
        return levels;
    }

    public Points< BuildingArea> getBuildings() {
        return buildings;
    }
}
