package aiki.gui.components.editor;

import code.gui.*;
import code.util.core.*;
import code.util.ints.*;

public final class ComparingLongKey<V> implements Comparing<EditedCrudPair<Long, V>>  {

    @Override
    public int compare(EditedCrudPair<Long, V> _one, EditedCrudPair<Long, V> _two) {
        return NumberUtil.compareLg(_one.getKey(), _two.getKey());
    }
}
