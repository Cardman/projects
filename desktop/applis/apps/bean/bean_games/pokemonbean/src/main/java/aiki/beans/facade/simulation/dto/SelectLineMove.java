package aiki.beans.facade.simulation.dto;
import aiki.beans.BeanChgBool;
import aiki.beans.IntBeanChgBool;
import aiki.beans.facade.dto.MoveLine;

public final class SelectLineMove extends MoveLine {
    private IntBeanChgBool selected = new BeanChgBool();

    public IntBeanChgBool getSelected() {
        return selected;
    }

    public boolean isSelected() {
        return selected.isSelected();
    }

    public void setSelected(IntBeanChgBool _selected) {
        selected = _selected;
    }

}