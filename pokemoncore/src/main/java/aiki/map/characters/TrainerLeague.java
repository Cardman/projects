package aiki.map.characters;

import aiki.DataBase;
import code.util.annot.RwXml;

@RwXml
public final class TrainerLeague extends TrainerOneFight implements Fightable {

    private String name;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (name == null) {
            _data.setError(true);
            return;

        }
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
