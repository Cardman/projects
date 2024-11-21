package aiki.gui.components.editor;

import code.gui.EditedCrudPair;
import code.gui.IntValidateElementAdd;
import code.util.ints.Comparing;

public final class NoValidateElementPairFactory<K,V> implements ValidateElementPairFactory<K,V> {
    @Override
    public IntValidateElementAdd<EditedCrudPair<K, V>> build(Comparing<EditedCrudPair<K, V>> _cmp) {
        return null;
    }
}
