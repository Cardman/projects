package code.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class SimpleSelectCombo extends MouseAdapter implements IndexableListener {

    private GraphicStringList grList;

    private int index;

    public SimpleSelectCombo(GraphicStringList _grList, int _index) {
        grList = _grList;
        index = _index;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        CustCellRender r_ = grList.getRender();
        Object[] array_ = grList.toArray();
        Object v_ = array_[index];
        PreparedLabel c_;
        c_ = r_.getListCellRendererComponent(grList, v_, index, false, false);
        r_.paintComponent(c_);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        CustCellRender r_ = grList.getRender();
        Object[] array_ = grList.toArray();
        Object v_ = array_[index];
        PreparedLabel c_;
        c_ = r_.getListCellRendererComponent(grList, v_, index, true, false);
        r_.paintComponent(c_);
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        grList.setFirstIndex(index);
        grList.setLastIndex(index);
        grList.clearAllRange();
        grList.addRange();
        SelectionUtil.selectEvent(index, index, grList, false);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int _index) {
        index = _index;
    }
}
