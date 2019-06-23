package aiki.map.places;

import aiki.db.DataBase;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.levels.Level;
import aiki.map.levels.LevelRoad;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.levels.Link;
import aiki.map.pokemon.WildPk;
import aiki.map.tree.LevelArea;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.map.util.PlaceInterConnect;
import aiki.util.Coords;
import aiki.util.Point;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.ObjectMap;


public final class Road extends Campaign implements InitializedPlace {

    private String name;

    private LevelRoad level;

    private ObjectMap<Point, Link> linksWithCaves;

    private ObjectMap<PlaceInterConnect, Coords> savedlinks;

    private ObjectMap<PlaceInterConnect, Coords> linksWithCitiesAndOtherRoads = new ObjectMap<PlaceInterConnect, Coords>();

    @Override
    public void addSavedLink(PlaceInterConnect _key, Coords _value) {
        savedlinks.put(_key, _value);
    }

    @Override
    public void deleteSavedLink(PlaceInterConnect _key) {
        savedlinks.removeKey(_key);
    }

    @Override
    public void validate(DataBase _data, PlaceArea _placeArea) {
        LevelArea levelArea_ = _placeArea.getLevel((byte) 0);
        for (PlaceInterConnect p : linksWithCitiesAndOtherRoads.getKeys()) {
            if (!levelArea_.isValid(p.getSource(), false)) {
                _data.setError(true);
                return;

            }
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
        getLevelRoad().validate(_data, levelArea_);
    }

    @Override
    public void validateForEditing(DataBase _data) {
        getLevelRoad().validateForEditing(_data);
    }

    @Override
    public boolean isEmptyForAdding(Coords _coords) {
        Level level_ = getLevelByCoords(_coords);
        return level_.isEmptyForAdding(_coords.getLevel().getPoint());
    }

    @Override
    public boolean validLinks(Tree _tree) {
        for (EntryCust<PlaceInterConnect, Coords> e : linksWithCitiesAndOtherRoads
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
        levels_.put(CustList.FIRST_INDEX, getLevelRoad());
        return levels_;
    }

    @Override
    public CustList<Level> getLevelsList() {
        return new CustList<Level>(getLevelRoad());
    }

    @Override
    public boolean containsPokemon(Coords _coords) {
        return getLevelRoad().containsPokemon(_coords.getLevel().getPoint());
    }

    @Override
    public void addPokemon(Coords _coords, WildPk _pk) {
        getLevelRoad().getLegendaryPks().put(_coords.getLevel().getPoint(), _pk);
    }

    @Override
    public WildPk getPokemon(Coords _coords) {
        return getLevelRoad().getPokemon(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsObject(Coords _coords) {
        return getLevelRoad().containsObject(_coords.getLevel().getPoint());
    }

    @Override
    public void addObject(Coords _coords, String _object) {
        getLevelRoad().getItems().put(_coords.getLevel().getPoint(), _object);
    }

    @Override
    public String getObject(Coords _coords) {
        return getLevelRoad().getObject(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsPerson(Coords _coords) {
        return getLevelRoad().containsPerson(_coords.getLevel().getPoint());
    }

    @Override
    public void addPerson(Coords _coords, Person _person) {
        getLevelRoad().getCharacters().put(_coords.getLevel().getPoint(),
                (CharacterInRoadCave) _person);
    }

    @Override
    public Person getPerson(Coords _coords) {
        return getLevelRoad().getPerson(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsDualFight(Coords _coords) {
        return getLevelRoad().containsDualFight(_coords.getLevel().getPoint());
    }

    @Override
    public void addDualFight(Coords _coords, DualFight _dualFight) {
        getLevelRoad().getDualFights().put(_coords.getLevel().getPoint(), _dualFight);
    }

    @Override
    public DualFight getDualFight(Coords _coords) {
        return getLevelRoad().getDualFight(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsHm(Coords _coords) {
        return getLevelRoad().containsHm(_coords.getLevel().getPoint());
    }

    @Override
    public void addHm(Coords _coords, short _hm) {
        getLevelRoad().getHm().put(_coords.getLevel().getPoint(), _hm);
    }

    @Override
    public short getHm(Coords _coords) {
        return getLevelRoad().getHm(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsTm(Coords _coords) {
        return getLevelRoad().containsTm(_coords.getLevel().getPoint());
    }

    @Override
    public void addTm(Coords _coords, short _tm) {
        getLevelRoad().getTm().put(_coords.getLevel().getPoint(), _tm);
    }

    @Override
    public short getTm(Coords _coords) {
        return getLevelRoad().getTm(_coords.getLevel().getPoint());
    }

    @Override
    public void setItem(Coords _coords, String _object) {
        getLevelRoad().setItem(_coords.getLevel().getPoint(), _object);
    }

    @Override
    public void setTm(Coords _coords, short _tm) {
        getLevelRoad().setTm(_coords.getLevel().getPoint(), _tm);
    }

    @Override
    public void setHm(Coords _coords, short _object) {
        getLevelRoad().setHm(_coords.getLevel().getPoint(), _object);
    }

    @Override
    public Level getLevelByCoords(Coords _coords) {
        return getLevelCompaignByCoords(_coords);
    }

    @Override
    public LevelWithWildPokemon getLevelCompaignByCoords(Coords _coords) {
        return getLevelRoad();
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
    public Level getLevel() {
        return getLevelRoad();
    }

    public LevelRoad getLevelRoad() {
        return level;
    }

    public void setLevel(LevelRoad _level) {
        level = _level;
    }

    @Override
    public void initializeWildPokemon() {
        getLevelRoad().initializeWildPokemon();
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
        return linksWithCitiesAndOtherRoads;
    }

    @Override
    public void setPointsWithCitiesAndOtherRoads(
            ObjectMap<PlaceInterConnect, Coords> _linksWithCitiesAndOtherRoads) {
        linksWithCitiesAndOtherRoads = _linksWithCitiesAndOtherRoads;
    }

    @Override
    public ObjectMap<Point, Link> getLinksWithCaves() {
        return linksWithCaves;
    }

    public void setLinksWithCaves(ObjectMap<Point, Link> _linksWithCaves) {
        linksWithCaves = _linksWithCaves;
    }

}
