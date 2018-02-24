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
        Object v_ = grList.getList().get(index);
        Component c_;
        if (_e.isPopupTrigger()) {
            c_ = r_.getListCellRendererComponent(grList, v_, index, false, false);
        } else {
            c_ = r_.getListCellRendererComponent(grList, v_, index, true, false);
        }
        r_.paintComponent(c_);
        ListSelection listener_ = grList.getListener();
        if (listener_ != null) {
            Object s_ = grList.getListComponents().get(index);
            ListSelectionEvent ev_ = new ListSelectionEvent(s_, index, index, false);
            listener_.valueChanged(ev_);
        }
    }
}
