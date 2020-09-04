package aiki.fight;

import aiki.db.DataBase;
import aiki.fight.util.ListEffectCombos;
import code.util.StringList;


public final class Combos {

    private ListEffectCombos effects;

    public void validate(DataBase _data) {
        for (StringList l : effects.getKeys()) {
            if (l.isEmpty()) {
                _data.setError(true);
            }
            if (!_data.getMoves().containsAllAsKeys(l)) {
                _data.setError(true);
            }
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
