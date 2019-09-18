package code.gui;

import javax.swing.*;
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
        CustCellRender r_ = grList.getRender();
        Object[] array_ = grList.toArray();
        boolean sel_ = SwingUtilities.isLeftMouseButton(_e);
        if (!sel_) {
            Object v_ = array_[index];
            PreparedLabel c_;
            c_ = r_.getListCellRendererComponent(grList, v_, index, false, false);
            r_.paintComponent(c_);
        } else {
            int len_ = grList.getListComponents().size();
            for (int i = 0; i < len_; i++) {
                Object v_ = array_[i];
                PreparedLabel c_;
                c_ = r_.getListCellRendererComponent(grList, v_, i, index == i, false);
                r_.paintComponent(c_);
            }
        }
        grList.clearAllRange();
        if (sel_) {
            grList.addRange();
        }
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
