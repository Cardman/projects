package aiki.map.buildings;

import aiki.db.DataBase;
import aiki.map.levels.Level;
import aiki.map.tree.BuildingArea;
import aiki.util.DataInfoChecker;
import aiki.util.Point;


public abstract class Building {

    private String imageFileName;

    /** point in the building */
    private Point exitCity;

    /**
     * @param _data
     */
    public void validate(DataBase _data, BuildingArea _buildingArea) {
        DataInfoChecker.checkKey(_data,_buildingArea,exitCity,true);
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
