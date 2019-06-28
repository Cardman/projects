package aiki.map.characters;

import aiki.db.DataBase;


public abstract class Trainer extends Person {

    private byte multiplicityFight = 1;

    private String imageMaxiFileName;

    public void validate(DataBase _data) {
        if (multiplicityFight < 1) {
            _data.setError(true);
            return;

        }
        if (multiplicityFight > DataBase.MAX_MULT_FIGHT) {
            _data.setError(true);
            return;

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
