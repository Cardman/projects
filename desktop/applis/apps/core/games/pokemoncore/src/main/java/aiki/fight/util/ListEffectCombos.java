package aiki.fight.util;

import aiki.fight.moves.effects.EffectCombo;
import aiki.instances.Instances;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;

public final class ListEffectCombos extends CustList<ListEffectCombo> {

    public ListEffectCombos() {
    }

    public ListEffectCombos(CollCapacity _capacity) {
        super(_capacity);
    }

    public EffectCombo getVal(StringList _key) {
        for (ListEffectCombo l:this) {
            if (_key.eq(l.getList())) {
                return l.getCombo();
            }
        }
        return Instances.newEffectCombo();
    }

    public CustList<StringList> getKeys() {
        CustList<StringList> k_ = new CustList<StringList>();
        for (ListEffectCombo l:this) {
            k_.add(l.getList());
        }
        return k_;
    }

    public CustList<EffectCombo> values() {
        CustList<EffectCombo> k_ = new CustList<EffectCombo>();
        for (ListEffectCombo l:this) {
            k_.add(l.getCombo());
        }
        return k_;
    }
}
