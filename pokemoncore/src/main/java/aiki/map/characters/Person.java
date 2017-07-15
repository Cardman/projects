package aiki.map.characters;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.datacheck.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public class Person {

    private String imageMiniFileName;

    public void validateForEditing(DataBase _data) {
        if (_data.getPerson(imageMiniFileName).isEmpty()) {
            throw new DataException();
        }
    }

    public boolean hasValidImage(DataBase _data) {
        if (_data.getPerson(imageMiniFileName).isEmpty()) {
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
