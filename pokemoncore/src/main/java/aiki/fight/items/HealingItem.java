package aiki.fight.items;

import aiki.DataBase;
import code.util.StringMap;


public abstract class HealingItem extends Item {

    public static final String ITEM = "aiki.fight.items.HealingItem";

    private StringMap<Short> happiness;
    private boolean healingTeam;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (String k : happiness.getKeys()) {
            if (happiness.getVal(k) < 0) {
                _data.setError(true);
                return;

            }
            Item obj_ = _data.getItem(k);
            if (obj_ == null) {
                _data.setError(true);
                return;

            }
            if (!(obj_ instanceof Ball)) {
                _data.setError(true);
                return;

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
