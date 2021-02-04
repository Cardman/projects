package code.gui;

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
        r_.setList(grList.getList());
        boolean sel_ = SwingUtilities.isLeftMouseButton(_e);
        if (!sel_) {
            PreparedLabel c_ = grList.getListComponents().get(index);
            r_.getListCellRendererComponent(c_, index, false, false);
            r_.paintComponent(c_);
        } else {
            int len_ = grList.getListComponents().size();
            for (int i = 0; i < len_; i++) {
                PreparedLabel c_ = grList.getListComponents().get(i);
                r_.getListCellRendererComponent(c_, i, index == i, false);
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
