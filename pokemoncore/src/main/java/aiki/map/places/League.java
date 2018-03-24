package aiki.map.places;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.map.characters.Person;
import aiki.map.levels.Level;
import aiki.map.levels.LevelLeague;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.util.Coords;
import aiki.util.Point;
import code.util.CustList;
import code.util.NumberMap;
import code.util.annot.RwXml;

@RwXml
public final class League extends Place {

    private String name;

    private CustList<LevelLeague> rooms;

    private Coords accessCoords;

    private String fileName;

    private Point begin;

    @Override
    public void validate(DataBase _data,PlaceArea _placeArea) {
        if (name == null) {
            throw new DataException();
        }
        if (rooms.isEmpty()) {
            throw new DataException();
        }
        if (!_placeArea.getLevel(CustList.FIRST_INDEX).isValid(begin, true)) {
            throw new DataException();
        }
        rooms.first().validate(_data, _placeArea.getLevel(CustList.FIRST_INDEX));
        if (!rooms.first().isEmpty(begin)) {
            throw new DataException();
        }
        int nbRooms_ = rooms.size();
        for (byte i=CustList.SECOND_INDEX;i<nbRooms_;i++) {
            rooms.get(i).validate(_data, _placeArea.getLevel(i));
            Point next_ = rooms.get(i-1).getNextLevelTarget();
            if (!rooms.get(i).isEmpty(next_)) {
                throw new DataException();
            }
            if (!_placeArea.getLevel(i).isValid(next_, true)) {
                throw new DataException();
            }
        }
        if (_data.getLink(fileName).length == 0) {
            throw new DataException();
        }
    }

    @Override
    public void validateForEditing(DataBase _data) {
        if (rooms.isEmpty()) {
            throw new DataException();
        }
        for (LevelLeague l: rooms) {
            l.validateForEditing(_data);
        }
        if (_data.getLink(fileName).length == 0) {
            throw new DataException();
        }
    }

    @Override
    public boolean isEmptyForAdding(Coords _coords) {
        Level level_ = getLevelByCoords(_coords);
        return level_.isEmptyForAdding(_coords.getLevel().getPoint());
    }

    public boolean validLinks(Tree _tree) {
        return _tree.isValid(accessCoords, true);
    }

    public void deleteRoom(int _index) {
        LevelLeague level_ = rooms.get(_index);
        if (_index > CustList.FIRST_INDEX) {
            LevelLeague previousLevel_ = rooms.get(_index - 1);
            previousLevel_.getNextLevelTarget().affect(level_.getNextLevelTarget());
            rooms.removeAt(_index);
        } else if (rooms.size() > CustList.SECOND_INDEX){
            LevelLeague nextLevel_ = rooms.get(CustList.SECOND_INDEX);
            if (nextLevel_.isEmptyForAdding(begin)) {
                rooms.removeAt(_index);
            }
        } else {
            rooms.removeAt(_index);
        }
    }

    @Override
    public LevelLeague getLevelByCoords(Coords _coords) {
        return rooms.get(_coords.getLevel().getLevelIndex());
    }

    @Override
    public NumberMap<Byte,Level> getLevelsMap() {
        NumberMap<Byte,Level> levels_ = new NumberMap<Byte,Level>();
        for (LevelLeague l: rooms) {
            levels_.put((byte) levels_.size(), l);
        }
        return levels_;
    }

    @Override
    public CustList<Level> getLevelsList() {
        CustList<Level> levels_ = new CustList<Level>();
        for (LevelLeague l: rooms) {
            levels_.add(l);
        }
        return levels_;
    }

    @Override
    public boolean containsPerson(Coords _coords) {
        LevelLeague level_ = getLevelByCoords(_coords);
        if (Point.eq(_coords.getLevel().getPoint(), level_.getTrainerCoords())) {
            return true;
        }
        return false;
    }

    @Override
    public Person getPerson(Coords _coords) {
        LevelLeague level_ = getLevelByCoords(_coords);
        if (Point.eq(_coords.getLevel().getPoint(), level_.getTrainerCoords())) {
            return level_.getTrainer();
        }
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String _name) {
        name = _name;
    }

    public CustList<LevelLeague> getRooms() {
        return rooms;
    }

    public void setRooms(CustList<LevelLeague> _rooms) {
        rooms = _rooms;
    }

    public Coords getAccessCoords() {
        return accessCoords;
    }

    public void setAccessCoords(Coords _accessCoords) {
        accessCoords = _accessCoords;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

    public Point getBegin() {
        return begin;
    }

    public void setBegin(Point _begin) {
        begin = _begin;
    }
}
