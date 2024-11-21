package aiki.gui.components.editor;

import code.gui.*;
import code.util.core.*;
import code.util.ints.*;

public final class ComparingShortKey<V> implements Comparing<EditedCrudPair<Short, V>>  {

    @Override
    public int compare(EditedCrudPair<Short, V> _one, EditedCrudPair<Short, V> _two) {
        return NumberUtil.compareLg(_one.getKey(), _two.getKey());
    }
}
