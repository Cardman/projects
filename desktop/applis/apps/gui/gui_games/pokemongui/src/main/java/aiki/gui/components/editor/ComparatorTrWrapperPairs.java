package aiki.gui.components.editor;

import aiki.comparators.ComparatorTr;
import code.gui.EditedCrudPair;
import code.util.AbsMap;
import code.util.ints.Comparing;

public final class ComparatorTrWrapperPairs<K,V> {
    public Comparing<EditedCrudPair<K,V>> wrap(AbsMap<K,String> _map) {
        return new ComparatorTr<K,EditedCrudPair<K,V>>(_map, new KeyRetriever<K,V>());
    }
}
