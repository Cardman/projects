package aiki.fight;

import aiki.DataBase;
import aiki.fight.moves.effects.EffectCombo;
import code.util.ObjectMap;
import code.util.StringList;


public final class Combos {

    private ObjectMap<StringList, EffectCombo> effects;

    public void validate(DataBase _data) {
        for (StringList l : effects.getKeys()) {
            if (l.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!_data.getMoves().containsAllAsKeys(l)) {
                _data.setError(true);
                return;

            }
            effects.getVal(l).validate(_data);
        }
    }

    public ObjectMap<StringList, EffectCombo> getEffects() {
        return effects;
    }

    public void setEffects(ObjectMap<StringList, EffectCombo> _effects) {
        effects = _effects;
    }

}
