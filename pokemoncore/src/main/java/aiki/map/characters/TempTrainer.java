package aiki.map.characters;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.datacheck.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public class TempTrainer extends TrainerOneFight {

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
        if (_data.getPerson(imageMiniSecondTrainerFileName).isEmpty()) {
            throw new DataException();
        }
        setMultiplicityFight((byte) 2);
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        if (!super.hasValidImage(_data)) {
            return false;
        }
        if (_data.getPerson(imageMiniSecondTrainerFileName).isEmpty()) {
            return false;
        }
        return true;
    }

    public String getImageMiniSecondTrainerFileName() {
        return imageMiniSecondTrainerFileName;
    }

    public void setImageMiniSecondTrainerFileName(String _imageMiniSecondTrainerFileName) {
        imageMiniSecondTrainerFileName = _imageMiniSecondTrainerFileName;
    }
}
