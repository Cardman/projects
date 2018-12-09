package aiki.fight.items;

import aiki.DataBase;


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
        if (steps <= 0) {
            _data.setError(true);
            return;

        }
    }

    public long getSteps() {
        return steps;
    }

    public void setSteps(long _steps) {
        steps = _steps;
    }
}
