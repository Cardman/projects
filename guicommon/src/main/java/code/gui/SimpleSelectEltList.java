package code.gui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ListSelectionEvent;

public final class SimpleSelectEltList extends MouseAdapter {

    private GraphicList grList;

    private int index;

    public SimpleSelectEltList(GraphicList _grList, int _index) {
        grList = _grList;
        index = _index;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        grList.setFirstIndex(index);
        grList.setLastIndex(index);
        CustCellRender r_ = grList.getRender();
        if (_e.isPopupTrigger()) {
            Object v_ = grList.getList().get(index);
            Component c_;
            c_ = r_.getListCellRendererComponent(grList, v_, index, false, false);
            r_.paintComponent(c_);
        } else {
            int len_ = grList.getListComponents().size();
            for (int i = 0; i < len_; i++) {
                Object v_ = grList.getList().get(i);
                Component c_;
                c_ = r_.getListCellRendererComponent(grList, v_, i, index == i, false);
                r_.paintComponent(c_);
            }
        }
        ListSelection listener_ = grList.getListener();
        if (listener_ != null) {
            Object s_ = grList.getListComponents().get(index);
            ListSelectionEvent ev_ = new ListSelectionEvent(s_, index, index, false);
            listener_.valueChanged(ev_);
        }
    }
}
