package aiki.map.places;

import aiki.db.DataBase;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.enums.Direction;
import aiki.map.levels.Level;
import aiki.map.levels.LevelOutdoor;
import aiki.map.levels.Link;
import aiki.map.tree.LevelArea;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.map.util.PlaceInterConnect;
import aiki.map.util.PlaceInterConnects;
import aiki.util.*;
import code.util.CustList;
import code.util.*;

import code.util.core.IndexConstants;


public final class City extends Place implements InitializedPlace {

    /** key access to building, which is shown as a block */
    private Points< Building> buildings;

    private LevelOutdoor level;

    private String name;

    private PlaceInterConnects savedlinks;

    private PlaceInterConnects linksPointsWithCitiesAndOtherRoads = new PlaceInterConnects();

    private Points< Link> linksWithCaves;

    @Override
    public void addSavedLink(PlaceInterConnect _key, Coords _value) {
        savedlinks.put(_key, _value);
    }

    @Override
    public void validate(DataBase _data, PlaceArea _placeArea) {
        LevelArea levelArea_ = _placeArea.getLevel((byte) 0);
        boolean existPkCenter_ = false;
        int nbGyms_ = 0;
        PointEqList ids_ = new PointEqList();
        ids_.addAllElts(linksWithCaves.getKeys());
        ids_.addAllElts(buildings.getKeys());
        for (CommonParam<Point,Building> e : buildings.entryList()) {
            if (levelArea_.isAccessible(e.getKey())) {
                _data.setError(true);
            }
            DataInfoChecker.checkKey(_data, levelArea_, e.getKey(), false);
            Point pt_ = new Point(e.getKey());
            pt_.moveTo(Direction.DOWN);
            DataInfoChecker.checkKey(_data, levelArea_, pt_, true);
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
        }
        for (PlaceInterConnect p : linksPointsWithCitiesAndOtherRoads.getKeys()) {
            DataInfoChecker.checkKey(_data, levelArea_, p.getSource(), false);
        }
        if (ids_.hasDuplicates()) {
            _data.setError(true);
        }
        validateLinksWithCaves(_data, levelArea_, linksWithCaves);
        getLevelOutdoor().validate(_data, levelArea_);
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        boolean val_ = super.hasValidImage(_data);
        for (Building b : buildings.values()) {
            if (!b.hasValidImage(_data)) {
                val_ = false;
            }
        }
        return val_;
    }

    @Override
    public boolean isEmptyForAdding(Coords _coords) {
        Level level_ = getLevelByCoords(_coords);
        return level_.isEmptyForAdding(_coords.getLevel().getPoint());
    }

    @Override
    public boolean validLinks(short _place, Tree _tree) {
        return checkLinks(_tree, linksPointsWithCitiesAndOtherRoads, linksWithCaves);
    }

    @Override
    public ByteMap< Level> getLevelsMap() {
        ByteMap< Level> levels_ = new ByteMap< Level>();
        levels_.put(IndexConstants.FIRST_INDEX, getLevelOutdoor());
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

    public Points< Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Points< Building> _buildings) {
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
    public PlaceInterConnects getSavedlinks() {
        return savedlinks;
    }

    @Override
    public void setSavedlinks(PlaceInterConnects _savedlinks) {
        savedlinks = _savedlinks;
    }

    @Override
    public PlaceInterConnects getPointsWithCitiesAndOtherRoads() {
        return linksPointsWithCitiesAndOtherRoads;
    }

    @Override
    public void setPointsWithCitiesAndOtherRoads(
            PlaceInterConnects _linksPointsWithCitiesAndOtherRoads) {
        linksPointsWithCitiesAndOtherRoads = _linksPointsWithCitiesAndOtherRoads;
    }

    @Override
    public Points< Link> getLinksWithCaves() {
        return linksWithCaves;
    }

    public void setLinksWithCaves(Points< Link> _linksWithCaves) {
        linksWithCaves = _linksWithCaves;
    }

}
