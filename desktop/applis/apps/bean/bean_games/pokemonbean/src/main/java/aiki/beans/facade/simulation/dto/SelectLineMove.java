package aiki.beans.facade.simulation.dto;
import aiki.beans.facade.dto.MoveLine;

public final class SelectLineMove extends MoveLine {
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

}