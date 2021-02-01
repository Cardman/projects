package code.gui;

import code.util.CustList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public final class SimpleSelectEltList<T> extends MouseAdapter implements IndexableListener {

    private final GraphicList<T> grList;

    private int index;

    public SimpleSelectEltList(GraphicList<T> _grList, int _index) {
        grList = _grList;
        index = _index;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        grList.setFirstIndex(index);
        grList.setLastIndex(index);
        CustCellRender<T> r_ = grList.getRender();
        CustList<T> array_ = grList.getList();
        boolean sel_ = SwingUtilities.isLeftMouseButton(_e);
        if (!sel_) {
            T v_ = array_.get(index);
            PreparedLabel c_;
            c_ = r_.getListCellRendererComponent(grList, v_, index, false, false);
            r_.paintComponent(c_);
        } else {
            int len_ = grList.getListComponents().size();
            for (int i = 0; i < len_; i++) {
                T v_ = array_.get(i);
                PreparedLabel c_;
                c_ = r_.getListCellRendererComponent(grList, v_, i, index == i, false);
                r_.paintComponent(c_);
            }
        }
        grList.clearAllRange();
        if (sel_) {
            grList.addRange();
        }
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
}
