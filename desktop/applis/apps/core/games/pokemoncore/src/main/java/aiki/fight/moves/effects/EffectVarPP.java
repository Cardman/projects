package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;


public final class EffectVarPP extends Effect {

    private long deletePp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkPositive(deletePp,_data);
    }

    public long getDeletePp() {
        return deletePp;
    }

    public void setDeletePp(long _deletePp) {
        deletePp = _deletePp;
    }
}
