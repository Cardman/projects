package aiki.gui.components.editor;

import code.gui.*;
import code.util.ints.*;

public interface ValidateElementPairFactory<K,V> {
    IntValidateElementAdd<EditedCrudPair<K,V>> build(Comparing<EditedCrudPair<K, V>> _cmp);
}
