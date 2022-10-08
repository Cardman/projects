package aiki.comparators;

import aiki.map.util.PlaceLevel;
import code.util.AbsComparerTreeMap;

public final class DictionaryComparatorPlaceLevel<V> extends AbsComparerTreeMap<PlaceLevel, V> {

    public DictionaryComparatorPlaceLevel() {
        super(new ComparatorPlaceLevel());
    }

}
