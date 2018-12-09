package aiki.map.characters;

import aiki.DataBase;


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
    public void validateForEditing(DataBase _data) {
        super.validateForEditing(_data);
        if (_data.getPerson(imageMiniSecondTrainerFileName).length == 0) {
            _data.setError(true);
            return;

        }
        setMultiplicityFight((byte) 2);
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        if (!super.hasValidImage(_data)) {
            return false;
        }
        if (_data.getPerson(imageMiniSecondTrainerFileName).length == 0) {
            return false;
        }
        return true;
    }

    public String getImageMiniSecondTrainerFileName() {
        return imageMiniSecondTrainerFileName;
    }

    public void setImageMiniSecondTrainerFileName(
            String _imageMiniSecondTrainerFileName) {
        imageMiniSecondTrainerFileName = _imageMiniSecondTrainerFileName;
    }
}
