package code.gui.document;

import aiki.beans.*;
import code.gui.*;

public final class DefBeanChgBool extends BeanChgBool {
    private final AbsCustCheckBox check;
    public DefBeanChgBool(AbsCustCheckBox _ch) {
        check = _ch;
    }
    @Override
    public boolean isSelected() {
        return check.isSelected();
    }

    @Override
    public void setSelected(boolean _v) {
        check.setSelected(_v);
    }

}
