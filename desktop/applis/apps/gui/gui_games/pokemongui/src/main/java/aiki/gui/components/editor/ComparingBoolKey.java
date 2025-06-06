package aiki.gui.components.editor;

import code.gui.*;
import code.util.comparators.*;
import code.util.core.*;
import code.util.ints.*;

public final class ComparingBoolKey<V> implements Comparing<EditedCrudPair<BoolVal, V>> {
    @Override
    public int compare(EditedCrudPair<BoolVal, V> _one, EditedCrudPair<BoolVal, V> _two) {
        return ComparatorBoolean.cmp(_one.getKey(),_two.getKey());
    }
}
