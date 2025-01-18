package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;


public final class EffectVarPP extends Effect {

    private int deletePp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkPositive(deletePp,_data);
    }

    public int getDeletePp() {
        return deletePp;
    }

    public void setDeletePp(int _deletePp) {
        deletePp = _deletePp;
    }
}
