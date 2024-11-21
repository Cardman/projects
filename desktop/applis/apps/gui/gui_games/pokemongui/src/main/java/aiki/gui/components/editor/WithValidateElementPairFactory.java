package aiki.gui.components.editor;

import code.gui.*;
import code.util.ints.*;

public final class WithValidateElementPairFactory<K,V> implements ValidateElementPairFactory<K,V> {
    @Override
    public IntValidateElementAdd<EditedCrudPair<K, V>> build(Comparing<EditedCrudPair<K, V>> _cmp) {
        return new ValidateElementPair<K, V>(_cmp);
    }
}
