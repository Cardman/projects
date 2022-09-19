package aiki.map.characters;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;


public abstract class Trainer extends Person {

    private byte multiplicityFight = 1;

    private String imageMaxiFileName;

    public void validate(DataBase _data) {
        DataInfoChecker.checkGreater(DataBase.MAX_MULT_FIGHT,multiplicityFight,_data);
        DataInfoChecker.checkLower(1,multiplicityFight,_data);
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        boolean val_ = super.hasValidImage(_data);
        if (_data.getTrainer(imageMaxiFileName).length == 0) {
            val_ = false;
        }
        return val_;
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
