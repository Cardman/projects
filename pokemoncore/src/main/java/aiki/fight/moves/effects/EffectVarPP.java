package aiki.fight.moves.effects;

import aiki.DataBase;


public final class EffectVarPP extends Effect {

    private short deletePp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (deletePp <= 0) {
            _data.setError(true);
            return;

        }
    }

    public short getDeletePp() {
        return deletePp;
    }

    public void setDeletePp(short _deletePp) {
        deletePp = _deletePp;
    }
}
