package code.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public final class MultSelectEltList extends MouseAdapter implements IndexableListener {

    private GraphicListable grList;

    private int index;

    public MultSelectEltList(GraphicListable _grList, int _index) {
        grList = _grList;
        index = _index;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        Object[] array_ = grList.toArray();
        boolean sel_ = SwingUtilities.isLeftMouseButton(_e);
        if (!_e.isShiftDown()) {
            grList.setFirstIndex(index);
            grList.setLastIndex(index);
            CustCellRender r_ = grList.getRender();
            Object v_ = array_[index];
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
            SelectionUtil.selectEvent(index, index, grList, false);
            return;
        }
        grList.setLastIndex(index);
        int min_ = Math.min(grList.getFirstIndex(), grList.getLastIndex());
        int max_ = Math.max(grList.getFirstIndex(), grList.getLastIndex());
        CustCellRender r_ = grList.getRender();
        for (int i = min_; i <= max_; i++) {
            Object v_ = array_[i];
            PreparedLabel c_;
            c_ = r_.getListCellRendererComponent(grList, v_, i, sel_, false);
            r_.paintComponent(c_);
        }
        if (sel_) {
            grList.addRange();
        } else {
            grList.clearRange();
        }
        SelectionUtil.selectEvent(min_, max_, grList, false);
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
