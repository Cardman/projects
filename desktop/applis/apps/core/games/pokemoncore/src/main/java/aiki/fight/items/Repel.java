package aiki.fight.items;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;


public final class Repel extends Item {

    private long steps;

    @Override
    public String getItemType() {
        return REPEL;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkPositive(steps,_data);
    }

    public long getSteps() {
        return steps;
    }

    public void setSteps(long _steps) {
        steps = _steps;
    }
}
