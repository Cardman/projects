package aiki.gui.components.editor;

import code.util.core.*;
import code.util.ints.*;

public final class IntComparator implements Comparing<Integer> {
    @Override
    public int compare(Integer _one, Integer _two) {
        return NumberUtil.compareLg(_one,_two);
    }
}
