package aiki.map.characters;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.util.annot.RwXml;

@RwXml
public abstract class Person {

    private String imageMiniFileName;

    public void validateForEditing(DataBase _data) {
        if (_data.getPerson(imageMiniFileName).length == 0) {
            throw new DataException();
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
