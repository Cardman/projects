package aiki.beans.facade.comparators;

import aiki.beans.*;
import aiki.fight.moves.effects.*;
import code.util.*;

public final class DictionaryComparatorCombos extends AbsComparerTreeMap<CustList<TranslatedKey>, EffectCombo> {
    public DictionaryComparatorCombos() {
        super(new ComparatorTranslatedKeyList());
    }
}
