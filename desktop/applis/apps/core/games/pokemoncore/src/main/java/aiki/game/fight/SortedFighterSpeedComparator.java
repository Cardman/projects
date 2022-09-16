package aiki.game.fight;

import aiki.db.DataBase;
import code.util.ints.Comparing;

public abstract class SortedFighterSpeedComparator implements Comparing<TeamPosition> {

    private final Fight fight;

    private final DataBase data;

    private final boolean revertedSpeed;

    protected SortedFighterSpeedComparator(Fight _fight, DataBase _data,
                                           boolean _revertedSpeed) {
        fight = _fight;
        data = _data;
        revertedSpeed = _revertedSpeed;
    }

    public DataBase getData() {
        return data;
    }

    public Fight getFight() {
        return fight;
    }

    public boolean isRevertedSpeed() {
        return revertedSpeed;
    }
}
