package aiki.map.places;

import aiki.db.DataBase;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.levels.Level;
import aiki.map.levels.LevelRoad;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.levels.Link;
import aiki.map.tree.LevelArea;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.map.util.PlaceInterConnect;
import aiki.map.util.PlaceInterConnects;
import aiki.util.Coords;
import aiki.util.DataInfoChecker;
import aiki.util.Points;
import code.util.ByteMap;
import code.util.CustList;
import code.util.core.IndexConstants;


public final class Road extends Campaign implements InitializedPlace {

    private String name;

    private LevelRoad level;

    private Points< Link> linksWithCaves;

    private PlaceInterConnects savedlinks;

    private PlaceInterConnects linksWithCitiesAndOtherRoads = new PlaceInterConnects();

    @Override
    public void addSavedLink(PlaceInterConnect _key, Coords _value) {
        savedlinks.put(_key, _value);
    }

    @Override
    public void validate(DataBase _data, PlaceArea _placeArea) {
        LevelArea levelArea_ = _placeArea.getLevel((byte) 0);
        for (PlaceInterConnect p : linksWithCitiesAndOtherRoads.getKeys()) {
            DataInfoChecker.checkKey(_data,levelArea_,p.getSource(),false);
        }
        validateLinksWithCaves(_data,levelArea_,linksWithCaves);
        getLevelRoad().validate(_data, levelArea_);
    }

    @Override
    public boolean isEmptyForAdding(Coords _coords) {
        Level level_ = getLevelByCoords(_coords);
        return level_.isEmptyForAdding(_coords.getLevel().getPoint());
    }

    @Override
    public boolean validLinks(short _place, Tree _tree) {
        return checkLinks(_tree,linksWithCitiesAndOtherRoads,linksWithCaves);
    }

    @Override
    public ByteMap< Level> getLevelsMap() {
        ByteMap< Level> levels_ = new ByteMap< Level>();
        levels_.put(IndexConstants.FIRST_INDEX, getLevelRoad());
        return levels_;
    }

    @Override
    public CustList<Level> getLevelsList() {
        return new CustList<Level>(getLevelRoad());
    }

    public void addObject(Coords _coords, String _object) {
        getLevelRoad().getItems().put(_coords.getLevel().getPoint(), _object);
    }

    public void addPerson(Coords _coords, Person _person) {
        getLevelRoad().getCharacters().put(_coords.getLevel().getPoint(),
                (CharacterInRoadCave) _person);
    }

    public void addDualFight(Coords _coords, DualFight _dualFight) {
        getLevelRoad().getDualFights().put(_coords.getLevel().getPoint(), _dualFight);
    }

    public void addHm(Coords _coords, short _hm) {
        getLevelRoad().getHm().put(_coords.getLevel().getPoint(), _hm);
    }

    public void addTm(Coords _coords, short _tm) {
        getLevelRoad().getTm().put(_coords.getLevel().getPoint(), _tm);
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
    public PlaceInterConnects getSavedlinks() {
        return savedlinks;
    }

    @Override
    public void setSavedlinks(PlaceInterConnects _savedlinks) {
        savedlinks = _savedlinks;
    }

    @Override
    public PlaceInterConnects getPointsWithCitiesAndOtherRoads() {
        return linksWithCitiesAndOtherRoads;
    }

    @Override
    public void setPointsWithCitiesAndOtherRoads(
            PlaceInterConnects _linksWithCitiesAndOtherRoads) {
        linksWithCitiesAndOtherRoads = _linksWithCitiesAndOtherRoads;
    }

    @Override
    public Points< Link> getLinksWithCaves() {
        return linksWithCaves;
    }

    public void setLinksWithCaves(Points< Link> _linksWithCaves) {
        linksWithCaves = _linksWithCaves;
    }

}
