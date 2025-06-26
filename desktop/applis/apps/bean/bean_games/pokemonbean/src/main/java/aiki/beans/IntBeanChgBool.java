package aiki.beans;

import code.util.core.*;

public interface IntBeanChgBool extends IntBeanChgValue<BoolVal> {
    boolean isSelected();
    void setSelected(boolean _v);
}
