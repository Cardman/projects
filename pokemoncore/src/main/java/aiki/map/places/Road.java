package aiki.map.places;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.levels.Level;
import aiki.map.levels.LevelRoad;
import aiki.map.levels.Link;
import aiki.map.pokemon.WildPk;
import aiki.map.tree.LevelArea;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.map.util.PlaceInterConnect;
import aiki.util.Coords;
import aiki.util.Point;
import code.serialize.CheckedData;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NumberMap;
import code.util.ObjectMap;
import code.util.annot.RwXml;

@RwXml
public class Road extends Campaign implements InitializedPlace{

    @CheckedData
    private String name;

    private LevelRoad level;

    private ObjectMap<Point,Link> linksWithCaves;

    private ObjectMap<PlaceInterConnect,Coords> savedlinks;

    private transient ObjectMap<PlaceInterConnect,Coords> linksWithCitiesAndOtherRoads = new ObjectMap<PlaceInterConnect,Coords>();

    @Override
    public void addSavedLink(PlaceInterConnect _key, Coords _value) {
        savedlinks.put(_key, _value);
    }
    @Override
    public void deleteSavedLink(PlaceInterConnect _key) {
        savedlinks.removeKey(_key);
    }
    @Override
    public void validate(DataBase _data,PlaceArea _placeArea) {
        if (name == null) {
            throw new DataException();
        }
        LevelArea levelArea_ = _placeArea.getLevel((byte) 0);
        for (PlaceInterConnect p :linksWithCitiesAndOtherRoads.getKeys()) {
            if (!levelArea_.isValid(p.getSource(),false)) {
                throw new DataException();
            }
        }
        for (Point p :linksWithCaves.getKeys()) {
//            if (!levelArea_.isValid(p,true)) {
//                throw new DataException();
//            }
            if (!levelArea_.isValid(p,false)) {
                throw new DataException();
            }
            Coords c_ = linksWithCaves.getVal(p).getCoords();
            Place tar_ = _data.getMap().getPlaces().getVal(c_.getNumberPlace());
            Level tarLevel_ = tar_.getLevelByCoords(c_);
            if (!tarLevel_.isEmptyForAdding(c_.getLevel().getPoint())) {
                throw new DataException();
            }
        }
        level.validate(_data, levelArea_);
    }

    @Override
    public void validateForEditing(DataBase _data) {
        level.validateForEditing(_data);
    }

    @Override
    public boolean isEmptyForAdding(Coords _coords) {
        Level level_ = getLevelByCoords(_coords);
        return level_.isEmptyForAdding(_coords.getLevel().getPoint());
    }

    @Override
    public boolean validLinks(Tree _tree) {
        for (EntryCust<PlaceInterConnect,Coords> e: linksWithCitiesAndOtherRoads.entryList()) {
            if (!_tree.isValid(e.getValue(), false)) {
                return false;
            }
        }
        for (EntryCust<Point,Link> e: linksWithCaves.entryList()) {
            Link link_ = e.getValue();
            if (!_tree.isValid(link_.getCoords(), true)) {
                return false;
            }
//            if (!link_.isValidDir()) {
//                if (!_tree.isValid(link_.getCoords(), true)) {
//                    return false;
//                }
//            } else {
//                Coords coords_ = new Coords(link_.getCoords());
//                coords_.getLevel().getPoint().moveTo(link_.getDir());
//                if (!_tree.isValid(coords_, true)) {
//                    return false;
//                }
//            }

        }
        return true;
    }
    @Override
    public NumberMap<Byte,Level> getLevels() {
        NumberMap<Byte,Level> levels_ = new NumberMap<Byte,Level>();
        levels_.put(CustList.FIRST_INDEX, level);
        return levels_;
    }

    @Override
    public CustList<Level> getLevelsList() {
        return new CustList<Level>(level);
    }

    @Override
    public boolean containsPokemon(Coords _coords) {
        return level.containsPokemon(_coords.getLevel().getPoint());
    }

    @Override
    public void addPokemon(Coords _coords, WildPk _pk) {
        level.getLegendaryPks().put(_coords.getLevel().getPoint(), _pk);
    }

    @Override
    public WildPk getPokemon(Coords _coords) {
        return level.getPokemon(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsObject(Coords _coords) {
        return level.containsObject(_coords.getLevel().getPoint());
    }

    @Override
    public void addObject(Coords _coords, String _object) {
        level.getItems().put(_coords.getLevel().getPoint(), _object);
    }

    @Override
    public String getObject(Coords _coords) {
        return level.getObject(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsPerson(Coords _coords) {
        return level.containsPerson(_coords.getLevel().getPoint());
    }

    @Override
    public void addPerson(Coords _coords, Person _person) {
        level.getCharacters().put(_coords.getLevel().getPoint(), (CharacterInRoadCave) _person);
    }

    @Override
    public Person getPerson(Coords _coords) {
        return level.getPerson(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsDualFight(Coords _coords) {
        return level.containsDualFight(_coords.getLevel().getPoint());
    }

    @Override
    public void addDualFight(Coords _coords, DualFight _dualFight) {
        level.getDualFights().put(_coords.getLevel().getPoint(), _dualFight);
    }

    @Override
    public DualFight getDualFight(Coords _coords) {
        return level.getDualFight(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsHm(Coords _coords) {
        return level.containsHm(_coords.getLevel().getPoint());
    }

    @Override
    public void addHm(Coords _coords, short _hm) {
        level.getHm().put(_coords.getLevel().getPoint(), _hm);
    }

    @Override
    public short getHm(Coords _coords) {
        return level.getHm(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsTm(Coords _coords) {
        return level.containsTm(_coords.getLevel().getPoint());
    }

    @Override
    public void addTm(Coords _coords, short _tm) {
        level.getTm().put(_coords.getLevel().getPoint(), _tm);
    }

    @Override
    public short getTm(Coords _coords) {
        return level.getTm(_coords.getLevel().getPoint());
    }

    @Override
    public void setItem(Coords _coords, String _object) {
        level.setItem(_coords.getLevel().getPoint(), _object);
    }

    @Override
    public void setTm(Coords _coords, short _tm) {
        level.setTm(_coords.getLevel().getPoint(), _tm);
    }

    @Override
    public void setHm(Coords _coords, short _object) {
        level.setHm(_coords.getLevel().getPoint(), _object);
    }

    @Override
    public LevelRoad getLevelByCoords(Coords _coords) {
        return getLevel();
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
    public LevelRoad getLevel() {
        return level;
    }

    public void setLevel(LevelRoad _level) {
        level = _level;
    }

    @Override
    public void initializeWildPokemon() {
        level.initializeWildPokemon();
    }

    @Override
    public ObjectMap<PlaceInterConnect,Coords> getSavedlinks() {
        return savedlinks;
    }
    @Override
    public void setSavedlinks(ObjectMap<PlaceInterConnect,Coords> _savedlinks) {
        savedlinks = _savedlinks;
    }

    @Override
    public ObjectMap<PlaceInterConnect,Coords> getPointsWithCitiesAndOtherRoads() {
        return linksWithCitiesAndOtherRoads;
    }

    @Override
    public void setPointsWithCitiesAndOtherRoads(ObjectMap<PlaceInterConnect,Coords> _linksWithCitiesAndOtherRoads) {
        linksWithCitiesAndOtherRoads = _linksWithCitiesAndOtherRoads;
    }
    @Override
    public ObjectMap<Point,Link> getLinksWithCaves() {
        return linksWithCaves;
    }
    public void setLinksWithCaves(ObjectMap<Point,Link> _linksWithCaves) {
        linksWithCaves = _linksWithCaves;
    }

}
