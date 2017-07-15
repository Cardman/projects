package aiki.beans.facade.simulation.dto;
import code.bean.Accessible;
import aiki.beans.facade.dto.MoveLine;

public class SelectLineMove extends MoveLine {

    @Accessible
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }
}
