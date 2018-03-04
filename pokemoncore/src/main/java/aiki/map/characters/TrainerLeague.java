package aiki.map.characters;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class TrainerLeague extends TrainerOneFight implements Fightable {

    private String name;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (name == null) {
            throw new DataException();
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
