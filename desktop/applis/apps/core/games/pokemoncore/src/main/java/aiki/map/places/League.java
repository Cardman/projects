package aiki.map.places;

import aiki.db.DataBase;
import aiki.map.levels.Level;
import aiki.map.levels.LevelLeague;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.util.*;
import code.util.*;
import code.util.core.IndexConstants;


public final class League extends Place {

    private String name;

    private CustList<LevelLeague> rooms;

    private Coords accessCoords;

    private String fileName;

    private NullablePoint begin;

    @Override
    public void validate(DataBase _data, PlaceArea _placeArea) {
        if (rooms.isEmpty()) {
            _data.setError(true);
            return;
        }
        DataInfoChecker.checkKey(_data,_placeArea.getLevel(IndexConstants.FIRST_INDEX),begin,true);
        rooms.first()
                .validate(_data, _placeArea.getLevel(IndexConstants.FIRST_INDEX));
        DataInfoChecker.checkEmpty(_data,rooms.first(),begin);
        int nbRooms_ = rooms.size();
        for (int i = IndexConstants.SECOND_INDEX; i < nbRooms_; i++) {
            getLevelLeague(i).validate(_data, _placeArea.getLevel(i));
            NullablePoint next_ = getLevelLeague(i - 1).getNextLevelTarget();
            DataInfoChecker.checkEmpty(_data,getLevelLeague(i),next_);
            DataInfoChecker.checkKey(_data,_placeArea.getLevel(i),next_,true);
        }
        rooms.last().setNextLevelTarget(new NullablePoint());
        if (_data.getLink(fileName).length == 0) {
            _data.setError(true);
        }
    }

    @Override
    public boolean isEmptyForAdding(Coords _coords) {
        Level level_ = getLevelByCoords(_coords);
        return level_.isEmptyForAdding(_coords.getLevel().getPoint());
    }

    @Override
    public boolean validLinks(int _p, Tree _tree) {
        return _tree.isValid(accessCoords, true);
    }

    @Override
    public Level getLevelByCoords(Coords _coords) {
        return getLevelLeague(_coords.getLevel().getLevelIndex());
    }

    public LevelLeague getLevelLeague(int _levelIndex) {
        return rooms.get(_levelIndex);
    }

    @Override
    public IntMap< Level> getLevelsMap() {
        IntMap< Level> levels_ = new IntMap< Level>();
        for (LevelLeague l : rooms) {
            levels_.put(levels_.size(), l);
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

    public NullablePoint getBegin() {
        return begin;
    }

    public void setBegin(Point _begin) {
        setBegin(new NullablePoint(_begin));
    }

    public void setBegin(NullablePoint _b) {
        this.begin = _b;
    }
}
