package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class EffectVarPP extends Effect {

    private short deletePp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (deletePp <= 0) {
            throw new DataException();
        }
    }
    public short getDeletePp() {
        return deletePp;
    }

    public void setDeletePp(short _deletePp) {
        deletePp = _deletePp;
    }
}
