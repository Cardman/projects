package aiki.map.characters;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;


public final class GymLeader extends TrainerOneFight {

    /** Technical move given to the user after the fight */
    private short tm;

    private String name;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        validateTrainerOneFight(_data);
        DataInfoChecker.checkShortsContains(_data.getTm().getKeys(),tm,_data);
    }

    public short getTm() {
        return tm;
    }

    public void setTm(short _tm) {
        tm = _tm;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

}
