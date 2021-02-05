package code.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class SimpleSelectCombo extends MouseAdapter implements IndexableListener {

    private final GraphicStringList grList;

    private int index;

    public SimpleSelectCombo(GraphicStringList _grList, int _index) {
        grList = _grList;
        index = _index;
    }

    @Override
    public void mouseExited(MouseEvent _e) {
        grList.repaintSelect(index,false);
    }

    @Override
    public void mouseEntered(MouseEvent _e) {
        grList.repaintSelect(index,true);
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        grList.setFirstIndex(index);
        grList.setLastIndex(index);
        grList.clearAllRange();
        grList.addRange();
        grList.selectEvent(index, index, false);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int _index) {
        index = _index;
    }

    @Override
    public ListSelection getSelection() {
        return grList.getListener();
    }

    @Override
    public void setSelection(ListSelection _sel) {
        grList.simpleSetListener(_sel);
    }
}
