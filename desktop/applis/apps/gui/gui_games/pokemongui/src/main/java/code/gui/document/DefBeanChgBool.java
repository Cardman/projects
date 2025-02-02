package code.gui.document;

import code.gui.*;

public final class DefBeanChgBool implements IntBeanChgBool{
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
