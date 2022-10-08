package aiki.beans.facade.comparators;

import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectCombo;
import code.util.AbsComparerTreeMap;
import code.util.StringList;

public final class DictionaryComparatorCombos extends AbsComparerTreeMap<StringList, EffectCombo> {
    public DictionaryComparatorCombos(DataBase _data, String _language) {
        super(new ComparatorStringList(_data, _language, false));
    }
}
