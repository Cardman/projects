package code.gui;

import code.util.core.*;
import code.util.ints.*;

public class ComparingStringKey<V> implements Comparing<EditedCrudPair<String, V>> {
    @Override
    public int compare(EditedCrudPair<String, V> _one, EditedCrudPair<String, V> _two) {
        return StringUtil.compareStrings(_one.getKey(), _two.getKey());
    }
}
