package aiki.map.places;

import aiki.db.DataBase;
import aiki.map.levels.Level;
import aiki.map.levels.LevelLeague;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.util.Coords;
import aiki.util.Point;
import code.util.CustList;
import code.util.*;


public final class League extends Place {

    private String name;

    private CustList<LevelLeague> rooms;

    private Coords accessCoords;

    private String fileName;

    private Point begin;

    @Override
    public void validate(DataBase _data, PlaceArea _placeArea) {
        if (rooms.isEmpty()) {
            _data.setError(true);
            return;
        }
        if (!_placeArea.getLevel(CustList.FIRST_INDEX).isValid(begin, true)) {
            _data.setError(true);
        }
        rooms.first()
                .validate(_data, _placeArea.getLevel(CustList.FIRST_INDEX));
        if (!rooms.first().isEmpty(begin)) {
            _data.setError(true);
        }
        int nbRooms_ = rooms.size();
        for (byte i = CustList.SECOND_INDEX; i < nbRooms_; i++) {
            getLevelLeague(i).validate(_data, _placeArea.getLevel(i));
            Point next_ = getLevelLeague((byte) (i - 1)).getNextLevelTarget();
            if (!getLevelLeague(i).isEmpty(next_)) {
                _data.setError(true);
            }
            if (!_placeArea.getLevel(i).isValid(next_, true)) {
                _data.setError(true);
            }
        }
        if (_data.getLink(fileName).length == 0) {
            _data.setError(true);
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

    @Override
    public Level getLevelByCoords(Coords _coords) {
        return getLevelLeague(_coords.getLevel().getLevelIndex());
    }

    public LevelLeague getLevelLeague(byte _levelIndex) {
        return rooms.get(_levelIndex);
    }

    @Override
    public ByteMap< Level> getLevelsMap() {
        ByteMap< Level> levels_ = new ByteMap< Level>();
        for (LevelLeague l : rooms) {
            levels_.put((byte) levels_.size(), l);
        }
        return levels_;
    }

    @Override
    public CustList<Level> getLevelsList() {
        CustList<Level> levels_ = new CustList<Level>();
        for (LevelLeague l : rooms) {
            levels_.add(l);
        }
        return levels_;
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
