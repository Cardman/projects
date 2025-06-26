package aiki.beans;

import code.util.comparators.*;
import code.util.core.*;

public class BeanChgBool implements IntBeanChgBool {
    private boolean selected;

    @Override
    public BoolVal genericValue() {
        return ComparatorBoolean.of(isSelected());
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean _v) {
        selected = _v;
    }
}
