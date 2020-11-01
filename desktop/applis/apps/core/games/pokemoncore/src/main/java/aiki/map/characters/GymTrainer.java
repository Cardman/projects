package aiki.map.characters;

import aiki.db.DataBase;

public final class GymTrainer extends TrainerOneFight {

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        validateTrainerOneFight(_data);
    }

}
