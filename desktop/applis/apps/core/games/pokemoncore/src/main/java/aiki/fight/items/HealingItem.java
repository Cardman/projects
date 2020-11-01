package aiki.fight.items;

import aiki.db.DataBase;
import code.util.StringMap;


public abstract class HealingItem extends Item {

    public static final String ITEM = "aiki.fight.items.HealingItem";

    private StringMap<Short> happiness;
    private boolean healingTeam;

    @Override
    public String getItemType() {
        return ITEM;
    }

    protected final void validateHealingItem(DataBase _data) {
        for (String k : happiness.getKeys()) {
            if (happiness.getVal(k) < 0) {
                _data.setError(true);
            }
            Item obj_ = _data.getItem(k);
            if (!(obj_ instanceof Ball)) {
                _data.setError(true);
            }
        }
    }

    public StringMap<Short> getHappiness() {
        return happiness;
    }

    public void setHappiness(StringMap<Short> _happiness) {
        happiness = _happiness;
    }

    public boolean getHealingTeam() {
        return healingTeam;
    }

    public void setHealingTeam(boolean _healingTeam) {
        healingTeam = _healingTeam;
    }
}
