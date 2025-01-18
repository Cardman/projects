package aiki.gui.components.editor;

import code.gui.*;
import code.util.core.*;
import code.util.ints.*;

public final class ComparingShortKey<V> implements Comparing<EditedCrudPair<Integer, V>>  {

    @Override
    public int compare(EditedCrudPair<Integer, V> _one, EditedCrudPair<Integer, V> _two) {
        return NumberUtil.compareLg(_one.getKey(), _two.getKey());
    }
}
