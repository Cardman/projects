package aiki.gui.components.editor;

import code.gui.EditedCrudPair;
import code.util.core.*;
import code.util.ints.*;

public final class IntIdComparator implements Comparing<EditedCrudPair<Integer, String>> {
    @Override
    public int compare(EditedCrudPair<Integer, String> _one, EditedCrudPair<Integer, String> _two) {
        return NumberUtil.compareLg(_one.getKey(),_two.getKey());
    }
}
