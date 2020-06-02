package aiki.map.characters;

import aiki.db.DataBase;


public final class TempTrainer extends TrainerOneFight {

    private String imageMiniSecondTrainerFileName;

    public TempTrainer() {
        setMultiplicityFight((byte) 2);
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        setMultiplicityFight((byte) 2);
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        boolean val_ = true;
        if (!super.hasValidImage(_data)) {
            val_ = false;
        }
        if (_data.getPerson(imageMiniSecondTrainerFileName).length == 0) {
            val_ = false;
        }
        return val_;
    }

    public String getImageMiniSecondTrainerFileName() {
        return imageMiniSecondTrainerFileName;
    }

    public void setImageMiniSecondTrainerFileName(
            String _imageMiniSecondTrainerFileName) {
        imageMiniSecondTrainerFileName = _imageMiniSecondTrainerFileName;
    }
}
