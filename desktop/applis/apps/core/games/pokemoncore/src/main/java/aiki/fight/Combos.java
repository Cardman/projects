package aiki.fight;

import aiki.db.DataBase;
import aiki.fight.util.ListEffectCombos;
import aiki.util.DataInfoChecker;
import code.util.StringList;


public final class Combos {

    private ListEffectCombos effects;

    public void validate(DataBase _data) {
        for (StringList l : effects.getKeys()) {
            DataInfoChecker.checkEmptyNotStringList(l,_data);
            DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),l,_data);
            effects.getVal(l).validate(_data);
        }
    }

    public ListEffectCombos getEffects() {
        return effects;
    }

    public void setEffects(ListEffectCombos _effects) {
        effects = _effects;
    }

}
