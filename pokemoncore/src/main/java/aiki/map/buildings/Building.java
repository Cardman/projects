package aiki.map.buildings;

import aiki.db.DataBase;
import aiki.map.levels.Level;
import aiki.map.tree.BuildingArea;
import aiki.util.Point;


public abstract class Building {

    private String imageFileName;

    /** point in the building */
    private Point exitCity;

    /**
     * @param _data
     */
    public void validate(DataBase _data, BuildingArea _buildingArea) {
        if (!_buildingArea.isValid(exitCity, true)) {
            _data.setError(true);
            return;

        }
    }

    public void validateForEditing(DataBase _data) {
        if (_data.getLink(imageFileName).length == 0) {
            _data.setError(true);
            return;

        }
    }

    public boolean hasValidImage(DataBase _data) {
        if (_data.getLink(imageFileName).length == 0) {
            return false;
        }
        return getLevel().hasValidImage(_data);
    }

    // public Pair<Integer,Integer> getDimensionsByImageFileName(
    // Map<String,Image> _images,
    // int _standardWidthTile,int _standardHeigthTile) {
    // return
    // getDimensionsByImageIcon(_images.getVal(imageFileName),_standardWidthTile,_standardHeigthTile);
    // }
    //
    // public static Pair<Integer,Integer> getDimensionsByImageIcon(Image
    // _image,int _standardWidthTile,int _standardHeigthTile) {
    // Pair<Integer,Integer> dims_ = new Pair<>();
    // dims_.first = _image.getWidth()/_standardWidthTile;
    // dims_.second = _image.getHeight()/_standardHeigthTile;
    // return dims_;
    // }
    public abstract void clearElements(Point _point);

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
