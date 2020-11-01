package aiki.map.characters;


import aiki.db.DataBase;

public final class TrainerLeague extends TrainerOneFight {

    private String name;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        validateTrainerOneFight(_data);
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

}
