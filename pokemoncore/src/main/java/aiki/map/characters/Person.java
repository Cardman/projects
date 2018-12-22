package aiki.map.characters;

import aiki.db.DataBase;


public abstract class Person {

    private String imageMiniFileName;

    public void validateForEditing(DataBase _data) {
        if (_data.getPerson(imageMiniFileName).length == 0) {
            _data.setError(true);
            return;

        }
    }

    public boolean hasValidImage(DataBase _data) {
        if (_data.getPerson(imageMiniFileName).length == 0) {
            return false;
        }
        return true;
    }

    public String getImageMiniFileName() {
        return imageMiniFileName;
    }

    public void setImageMiniFileName(String _imageMiniFileName) {
        imageMiniFileName = _imageMiniFileName;
    }
}
