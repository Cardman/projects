package aiki.fight.items;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.util.EntryCust;
import code.util.StringMap;


public abstract class HealingItem extends Item {

    private StringMap<Long> happiness;
    private boolean healingTeam;

    @Override
    public String getItemType() {
        return HEALING_ITEM;
    }

    protected final void validateHealingItem(DataBase _data) {
        DataInfoChecker.checkStringListContains(DataInfoChecker.itemsBall(_data).getKeys(),happiness.getKeys(),_data);
        for (EntryCust<String, Long> k : happiness.entryList()) {
            DataInfoChecker.checkPositiveOrZero(k.getValue(),_data);
        }
    }

    public StringMap<Long> getHappiness() {
        return happiness;
    }

    public void setHappiness(StringMap<Long> _happiness) {
        happiness = _happiness;
    }

    public boolean getHealingTeam() {
        return healingTeam;
    }

    public void setHealingTeam(boolean _healingTeam) {
        healingTeam = _healingTeam;
    }
}
