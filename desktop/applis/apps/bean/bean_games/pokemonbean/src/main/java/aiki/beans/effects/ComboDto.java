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

    public StringList getKey(int _index) {
        return combos.getKey(_index);
    }

}