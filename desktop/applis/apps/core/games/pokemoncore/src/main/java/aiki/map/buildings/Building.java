package aiki.map.buildings;

import aiki.db.DataBase;
import aiki.map.levels.Level;
import aiki.map.tree.BuildingArea;
import aiki.util.*;
import code.util.CustList;


public abstract class Building {

    private String imageFileName;

    /** point in the building */
    private NullablePoint exitCity;

    /**
     * @param _data
     */
    public void validate(DataBase _data, BuildingArea _buildingArea) {
        DataInfoChecker.checkKey(_data,_buildingArea,exitCity);
    }

    protected void validatePoints(DataBase _data, BuildingArea _buildingArea, CustList<NullablePoint> _pts) {
        if (!_buildingArea.getLevel().allAccessible()) {
            _data.setError(true);
        }
        PointEqList ret_ = new PointEqList();
        for (NullablePoint p: _pts) {
            DataInfoChecker.checkKey(_data,_buildingArea.getLevel(),p,true);
            NullablePoint.tryAdd(ret_,p);
        }
        NullablePoint.tryAdd(ret_,getExitCity());
        if (ret_.hasDuplicates()) {
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

    public NullablePoint getExitCity() {
        return exitCity;
    }

    public void setExitCity(Point _exitCity) {
        setExitCity(new NullablePoint(_exitCity));
    }
    public void setExitCity(NullablePoint _exitCity) {
        exitCity = _exitCity;
    }
}
