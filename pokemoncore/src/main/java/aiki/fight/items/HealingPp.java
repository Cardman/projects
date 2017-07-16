package aiki.fight.items;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.datacheck.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class HealingPp extends HealingItem {

    public static final String ITEM = "aiki.fight.items.HealingPp";

    private long healedMovePp;
    private long healingAllMovesFullpp;
    private boolean healingAllMovesPp;
    private boolean healingMoveFullpp;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (healedMovePp > 0) {
            if (healingAllMovesFullpp > 0) {
                throw new DataException();
            }
            if (healingAllMovesPp) {
                throw new DataException();
            }
            if (healingMoveFullpp) {
                throw new DataException();
            }
            return;
        }
        if (healingAllMovesFullpp > 0) {
            if (healingAllMovesPp) {
                throw new DataException();
            }
            if (healingMoveFullpp) {
                throw new DataException();
            }
            return;
        }
        if (healingAllMovesPp) {
            if (healingMoveFullpp) {
                throw new DataException();
            }
            return;
        }
        if (!healingMoveFullpp) {
            throw new DataException();
        }
    }

    public boolean healOneMove() {
        if (getHealingMoveFullpp()) {
            return true;
        }
        if (getHealedMovePp() > 0) {
            return true;
        }
        return false;
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
