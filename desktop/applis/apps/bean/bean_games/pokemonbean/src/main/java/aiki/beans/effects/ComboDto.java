package aiki.beans.effects;

import aiki.beans.*;
import aiki.beans.facade.comparators.*;
import aiki.fight.moves.effects.*;
import code.util.*;

public final class ComboDto {

    private final DictionaryComparatorCombos combos;

    public ComboDto(DictionaryComparatorCombos _combos) {
        combos = _combos;
    }

    public EffectCombo getValue(int _index) {
        return combos.getValue(_index);
    }

    public CustList<TranslatedKey> getKey(int _index) {
        return combos.getKey(_index);
    }

    public int size() {
        return combos.size();
    }
}