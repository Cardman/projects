package aiki.fight.items;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;


public final class Repel extends Item {

    private static final String ITEM = "aiki.fight.items.Repel";

    private long steps;

    @Override
    public String getItemType() {
        return ITEM;
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
