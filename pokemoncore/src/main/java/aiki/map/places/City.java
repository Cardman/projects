package aiki.map.places;

import aiki.db.DataBase;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.GymTrainer;
import aiki.map.characters.Person;
import aiki.map.enums.Direction;
import aiki.map.levels.Level;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelIndoorPokemonCenter;
import aiki.map.levels.LevelOutdoor;
import aiki.map.levels.Link;
import aiki.map.tree.LevelArea;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.map.util.PlaceInterConnect;
import aiki.util.Coords;
import aiki.util.Point;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.*;
import code.util.ObjectMap;


public final class City extends Place implements InitializedPlace {

    /** key access to building, which is shown as a block */
    private ObjectMap<Point, Building> buildings;

    private LevelOutdoor level;

    private String name;

    private ObjectMap<PlaceInterConnect, Coords> savedlinks;

    private ObjectMap<PlaceInterConnect, Coords> linksPointsWithCitiesAndOtherRoads = new ObjectMap<PlaceInterConnect, Coords>();

    private ObjectMap<Point, Link> linksWithCaves;

    @Override
    public void addSavedLink(PlaceInterConnect _key, Coords _value) {
        savedlinks.put(_key, _value);
    }

    @Override
    public void deleteSavedLink(PlaceInterConnect _key) {
        savedlinks.removeKey(_key);
    }

    public void clearElements(Point _idBuilding, Point _point) {
        buildings.getVal(_idBuilding).clearElements(_point);
    }

    @Override
    public void validate(DataBase _data, PlaceArea _placeArea) {
        LevelArea levelArea_ = _placeArea.getLevel((byte) 0);
        boolean existPkCenter_ = false;
        int nbGyms_ = 0;
        EqList<Point> ids_ = new EqList<Point>();
        ids_.addAllElts(linksWithCaves.getKeys());
        ids_.addAllElts(buildings.getKeys());
        for (EntryCust<Point, Building> e : buildings.entryList()) {
            if (levelArea_.isAccessible(e.getKey())) {
                _data.setError(true);
                return;

            }
            if (!levelArea_.isValid(e.getKey(), false)) {
                _data.setError(true);
                return;

            }
            Point pt_ = new Point(e.getKey());
            pt_.moveTo(Direction.DOWN);
            if (!levelArea_.isValid(pt_, true)) {
                _data.setError(true);
                return;

            }
            ids_.add(pt_);
            Building building_ = e.getValue();
            building_.validate(_data,
                    _placeArea.getBuildings().getVal(e.getKey()));
            if (building_ instanceof PokemonCenter) {
                existPkCenter_ = true;
            }
            if (building_ instanceof Gym) {
                nbGyms_++;
            }
        }
        if (!existPkCenter_ || nbGyms_ > 1) {
            _data.setError(true);
            return;

        }
        for (PlaceInterConnect p : linksPointsWithCitiesAndOtherRoads.getKeys()) {
            if (!levelArea_.isValid(p.getSource(), false)) {
                _data.setError(true);
                return;

            }
        }
        int len_ = ids_.size();
        ids_.removeDuplicates();
        if (len_ != ids_.size()) {
            _data.setError(true);
            return;

        }
        for (Point p : linksWithCaves.getKeys()) {
            if (!levelArea_.isValid(p, false)) {
                _data.setError(true);
                return;

            }
            Coords c_ = linksWithCaves.getVal(p).getCoords();
            if (!_data.getMap().existCoords(c_)) {
                _data.setError(true);
                return;
            }
            Place tar_ = _data.getMap().getPlaces().getVal(c_.getNumberPlace());
            Level tarLevel_ = tar_.getLevelByCoords(c_);
            if (!tarLevel_.isEmptyForAdding(c_.getLevel().getPoint())) {
                _data.setError(true);
                return;

            }
        }
        getLevelOutdoor().validate(_data, levelArea_);
    }

    @Override
    public void validateForEditing(DataBase _data) {
        for (EntryCust<Point, Building> e : buildings.entryList()) {
            e.getValue().validateForEditing(_data);
        }
        getLevelOutdoor().validateForEditing(_data);
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        if (!super.hasValidImage(_data)) {
            return false;
        }
        for (Building b : buildings.values()) {
            if (!b.hasValidImage(_data)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmptyForAdding(Coords _coords) {
        Level level_ = getLevelByCoords(_coords);
        return level_.isEmptyForAdding(_coords.getLevel().getPoint());
    }

    @Override
    public boolean validLinks(Tree _tree) {
        for (EntryCust<PlaceInterConnect, Coords> e : linksPointsWithCitiesAndOtherRoads
                .entryList()) {
            if (!_tree.isValid(e.getValue(), false)) {
                return false;
            }
        }
        for (EntryCust<Point, Link> e : linksWithCaves.entryList()) {
            Link link_ = e.getValue();
            if (!_tree.isValid(link_.getCoords(), true)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ByteMap< Level> getLevelsMap() {
        ByteMap< Level> levels_ = new ByteMap< Level>();
        levels_.put(CustList.FIRST_INDEX, getLevelOutdoor());
        return levels_;
    }

    @Override
    public CustList<Level> getLevelsList() {
        return new CustList<Level>(getLevelOutdoor());
    }

    @Override
    public Level getLevelByCoords(Coords _coords) {
        if (_coords.isInside()) {
            Point bIncome_ = _coords.getInsideBuilding();
            return buildings.getVal(bIncome_).getLevel();
        }
        return getLevelOutdoor();
    }

    @Override
    public boolean containsPerson(Coords _coords) {
        Level level_ = getLevelByCoords(_coords);
        if (level_ instanceof LevelIndoorPokemonCenter) {
            return ((LevelIndoorPokemonCenter) level_).getGerants().contains(
                    _coords.getLevel().getPoint());
        }
        if (level_ instanceof LevelIndoorGym) {
            LevelIndoorGym gym_ = (LevelIndoorGym) level_;
            if (Point.eq(_coords.getLevel().getPoint(),
                    gym_.getGymLeaderCoords())) {
                return true;
            }
            return gym_.getGymTrainers()
                    .contains(_coords.getLevel().getPoint());
        }
        return false;
    }

    @Override
    public void addPerson(Coords _coords, Person _person) {
        Level level_ = getLevelByCoords(_coords);
        if (level_ instanceof LevelIndoorPokemonCenter) {
            ((LevelIndoorPokemonCenter) level_).getGerants().put(
                    _coords.getLevel().getPoint(), _person);
        }
        if (level_ instanceof LevelIndoorGym) {
            LevelIndoorGym gym_ = (LevelIndoorGym) level_;
            gym_.getGymTrainers().put(_coords.getLevel().getPoint(),
                    (GymTrainer) _person);
        }
    }

    @Override
    public Person getPerson(Coords _coords) {
        Level level_ = getLevelByCoords(_coords);
        if (level_ instanceof LevelIndoorPokemonCenter) {
            return ((LevelIndoorPokemonCenter) level_).getGerants().getVal(
                    _coords.getLevel().getPoint());
        }
        if (level_ instanceof LevelIndoorGym) {
            LevelIndoorGym gym_ = (LevelIndoorGym) level_;
            if (Point.eq(_coords.getLevel().getPoint(),
                    gym_.getGymLeaderCoords())) {
                return gym_.getGymLeader();
            }
            return gym_.getGymTrainers().getVal(_coords.getLevel().getPoint());
        }
        return null;
    }

    public ObjectMap<Point, Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ObjectMap<Point, Building> _buildings) {
        buildings = _buildings;
    }

    @Override
    public Level getLevel() {
        return getLevelOutdoor();
    }

    public LevelOutdoor getLevelOutdoor() {
        return level;
    }

    public void setLevel(LevelOutdoor _level) {
        level = _level;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String _name) {
        name = _name;
    }

    @Override
    public ObjectMap<PlaceInterConnect, Coords> getSavedlinks() {
        return savedlinks;
    }

    @Override
    public void setSavedlinks(ObjectMap<PlaceInterConnect, Coords> _savedlinks) {
        savedlinks = _savedlinks;
    }

    @Override
    public ObjectMap<PlaceInterConnect, Coords> getPointsWithCitiesAndOtherRoads() {
        return linksPointsWithCitiesAndOtherRoads;
    }

    @Override
    public void setPointsWithCitiesAndOtherRoads(
            ObjectMap<PlaceInterConnect, Coords> _linksPointsWithCitiesAndOtherRoads) {
        linksPointsWithCitiesAndOtherRoads = _linksPointsWithCitiesAndOtherRoads;
    }

    @Override
    public ObjectMap<Point, Link> getLinksWithCaves() {
        return linksWithCaves;
    }

    public void setLinksWithCaves(ObjectMap<Point, Link> _linksWithCaves) {
        linksWithCaves = _linksWithCaves;
    }

}
