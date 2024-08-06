package aiki.fight.items;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;


public final class HealingPp extends HealingItem {

    private long healedMovePp;
    private long healingAllMovesFullpp;
    private boolean healingAllMovesPp;
    private boolean healingMoveFullpp;

    @Override
    public String getItemType() {
        return HEALING_PP;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        validateHealingItem(_data);
        if (healedMovePp > 0) {
            DataInfoChecker.checkGreater(0,healingAllMovesFullpp,_data);
            checkFull(_data);
            return;
        }
        if (healingAllMovesFullpp > 0) {
            checkFull(_data);
            return;
        }
        if (healingAllMovesPp) {
            if (healingMoveFullpp) {
                _data.setError(true);
            }
            return;
        }
        if (!healingMoveFullpp) {
            _data.setError(true);
        }
    }

    private void checkFull(DataBase _data) {
        if (healingAllMovesPp) {
            _data.setError(true);
        }
        if (healingMoveFullpp) {
            _data.setError(true);
        }
    }

    public boolean healOneMove() {
        if (getHealingMoveFullpp()) {
            return true;
        }
        return getHealedMovePp() > 0;
    }

    public long getHealedMovePp() {
        return healedMovePp;
    }

    public void setHealedMovePp(long _healedMovePp) {
        healedMovePp = _healedMovePp;
    }

    public long getHealingAllMovesFullpp() {
        return healingAllMovesFullpp;
    }

    public void setHealingAllMovesFullpp(long _healingAllMovesFullpp) {
        healingAllMovesFullpp = _healingAllMovesFullpp;
    }

    public boolean isHealingAllMovesPp() {
        return healingAllMovesPp;
    }

    public void setHealingAllMovesPp(boolean _healingAllMovesPp) {
        healingAllMovesPp = _healingAllMovesPp;
    }

    public boolean getHealingMoveFullpp() {
        return healingMoveFullpp;
    }

    public void setHealingMoveFullpp(boolean _healingMoveFullpp) {
        healingMoveFullpp = _healingMoveFullpp;
    }
}
