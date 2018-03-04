package aiki.map.characters;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class GymLeader extends TrainerOneFight implements Fightable{

    /**Technical move given to the user after the fight*/
    private short tm;

    private String name;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getTm().contains(tm)) {
            throw new DataException();
        }
        if (name == null) {
            throw new DataException();
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
