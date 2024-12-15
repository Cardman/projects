package aiki.map.buildings;

import aiki.db.DataBase;
import aiki.map.levels.Level;
import aiki.map.tree.BuildingArea;
import aiki.util.*;


public abstract class Building {

    private String imageFileName;

    /** point in the building */
    private Point exitCity;

    /**
     * @param _data
     */
    public void validate(DataBase _data, BuildingArea _buildingArea) {
        DataInfoChecker.checkKey(_data,_buildingArea,exitCity);
    }

    protected void validatePoints(DataBase _data, BuildingArea _buildingArea, PointEqList _pts) {
        if (!_buildingArea.getLevel().allAccessible()) {
            _data.setError(true);
        }
        for (Point p: _pts) {
            DataInfoChecker.checkKey(_data,_buildingArea.getLevel(),p,true);
        }
        _pts.add(getExitCity());
        if (_pts.hasDuplicates()) {
            _data.setError(true);
        }
    }
    public boolean hasValidImage(DataBase _data) {
        boolean val_ = _data.getLink(imageFileName).length != 0;
        if (!getLevel().hasValidImage(_data)) {
            val_ = false;
        }
        return val_;
    }

    public abstract Level getLevel();

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String _imageFileName) {
        imageFileName = _imageFileName;
    }

    public Point getExitCity() {
        return exitCity;
    }

    public void setExitCity(Point _exitCity) {
        exitCity = _exitCity;
    }
}
