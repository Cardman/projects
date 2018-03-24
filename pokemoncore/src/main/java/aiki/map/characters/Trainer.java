package aiki.map.characters;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.util.annot.RwXml;

@RwXml
public abstract class Trainer extends Person {

    private byte multiplicityFight = 1;

    private String imageMaxiFileName;

    public void validate() {
        if (multiplicityFight < 1) {
            throw new DataException();
        }
        if (multiplicityFight > DataBase.MAX_MULT_FIGHT) {
            throw new DataException();
        }
    }

    @Override
    public void validateForEditing(DataBase _data) {
        super.validateForEditing(_data);
        if (!imageMaxiFileName.isEmpty()) {
            if (_data.getTrainer(imageMaxiFileName).length == 0) {
                throw new DataException();
            }
        }
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        if (!super.hasValidImage(_data)) {
            return false;
        }
        if (_data.getTrainer(imageMaxiFileName).length == 0) {
            return false;
        }
        return true;
    }

    public byte getMultiplicityFight() {
        return multiplicityFight;
    }

    public void setMultiplicityFight(byte _multiplicityFight) {
        multiplicityFight = _multiplicityFight;
    }

    public String getImageMaxiFileName() {
        return imageMaxiFileName;
    }

    public void setImageMaxiFileName(String _imageMaxiFileName) {
        imageMaxiFileName = _imageMaxiFileName;
    }
}
