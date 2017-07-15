package aiki.beans.facade.simulation.dto;
import code.bean.Accessible;
import aiki.beans.facade.dto.MoveLine;

public class RadioLineMove extends MoveLine {

    @Accessible
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }
}
