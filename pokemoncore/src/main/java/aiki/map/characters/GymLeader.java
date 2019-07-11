package aiki.map.characters;

import aiki.db.DataBase;


public final class GymLeader extends TrainerOneFight implements Fightable {

    /** Technical move given to the user after the fight */
    private short tm;

    private String name;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getTm().contains(tm)) {
            _data.setError(true);
        }
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

    @Override
    public int getMult() {
        return getMultiplicityFight();
    }
}
