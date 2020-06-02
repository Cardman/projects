package aiki.beans.effects;

import aiki.fight.moves.effects.EffectCombo;
import code.util.AbsMap;
import code.util.StringList;
import code.util.TreeMap;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;

public final class ComboDto {

    private TreeMap<StringList, EffectCombo> combos;

    public ComboDto(TreeMap<StringList, EffectCombo> _combos) {
        combos = _combos;
    }

    public void putAllMap(ListableEntries<StringList, EffectCombo> _m) {
        combos.putAllMap(_m);
    }

    public EffectCombo getValue(int _index) {
        return combos.getValue(_index);
    }

    public boolean isEmpty() {
        return combos.isEmpty();
    }

    public StringList getKey(int _index) {
        return combos.getKey(_index);
    }

    public int size() {
        return combos.size();
    }

    public Listable<StringList> getKeys() {
        return combos.getKeys();
    }

    public void put(StringList _key, EffectCombo _value) {
        combos.put(_key, _value);
    }

    public EffectCombo getVal(StringList _key) {
        return combos.getVal(_key);
    }

    public Listable<EffectCombo> values() {
        return combos.values();
    }

}