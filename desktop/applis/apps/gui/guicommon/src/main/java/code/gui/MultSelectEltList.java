package code.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public final class MultSelectEltList<T> extends MouseAdapter implements IndexableListener {

    private final GraphicList<T> grList;

    private int index;

    public MultSelectEltList(GraphicList<T> _grList, int _index) {
        grList = _grList;
        index = _index;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        boolean sel_ = SwingUtilities.isLeftMouseButton(_e);
        if (!_e.isShiftDown()) {
            grList.setFirstIndex(index);
            grList.setLastIndex(index);
            CustCellRender<T> r_ = grList.getRender();
            T v_ = grList.getList().get(index);
            PreparedLabel c_;
            if (!sel_) {
                c_ = r_.getListCellRendererComponent(grList, v_, index, false, false);
            } else {
                c_ = r_.getListCellRendererComponent(grList, v_, index, true, false);
            }
            c_.requestFocus();
            r_.paintComponent(c_);
            if (sel_) {
                grList.addRange();
            } else {
                grList.clearRange();
            }
            grList.selectEvent(index, index, false);
            return;
        }
        grList.setLastIndex(index);
        int min_ = Math.min(grList.getFirstIndex(), grList.getLastIndex());
        int max_ = Math.max(grList.getFirstIndex(), grList.getLastIndex());
        CustCellRender<T> r_ = grList.getRender();
        for (int i = min_; i <= max_; i++) {
            T v_ = grList.getList().get(i);
            PreparedLabel c_;
            c_ = r_.getListCellRendererComponent(grList, v_, i, sel_, false);
            r_.paintComponent(c_);
        }
        if (sel_) {
            grList.addRange();
        } else {
            grList.clearRange();
        }
        grList.selectEvent(min_, max_, false);
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
