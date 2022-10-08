package aiki.beans.effects;

import aiki.beans.facade.comparators.DictionaryComparatorCombos;
import aiki.fight.moves.effects.EffectCombo;
import code.util.StringList;

public final class ComboDto {

    private final DictionaryComparatorCombos combos;

    public ComboDto(DictionaryComparatorCombos _combos) {
        combos = _combos;
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

    public void put(StringList _key, EffectCombo _value) {
        combos.put(_key, _value);
    }

    public EffectCombo getVal(StringList _key) {
        return combos.getVal(_key);
    }

}