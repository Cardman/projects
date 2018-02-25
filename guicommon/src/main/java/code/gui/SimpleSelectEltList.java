package code.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public final class SimpleSelectEltList extends MouseAdapter implements IndexableListener {

    private GraphicListable grList;

    private int index;

    public SimpleSelectEltList(GraphicListable _grList, int _index) {
        grList = _grList;
        index = _index;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        grList.setFirstIndex(index);
        grList.setLastIndex(index);
        CustCellRender r_ = grList.getRender();
        Object[] array_ = grList.getList().toArray();
        boolean sel_ = !_e.isPopupTrigger();
        if (!sel_) {
            Object v_ = array_[index];
            JLabel c_;
            c_ = r_.getListCellRendererComponent(grList, v_, index, false, false);
            r_.paintComponent(c_);
        } else {
            int len_ = grList.getListComponents().size();
            for (int i = 0; i < len_; i++) {
                Object v_ = array_[i];
                JLabel c_;
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
