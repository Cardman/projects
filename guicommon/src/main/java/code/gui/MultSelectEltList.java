package code.gui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ListSelectionEvent;

public final class MultSelectEltList extends MouseAdapter {

    private GraphicList grList;

    private int index;

    public MultSelectEltList(GraphicList _grList, int _index) {
        grList = _grList;
        index = _index;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        if (!_e.isShiftDown()) {
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
            c_.requestFocus();
            r_.paintComponent(c_);
            ListSelection listener_ = grList.getListener();
            if (listener_ != null) {
                Object s_ = grList.getListComponents().get(index);
                ListSelectionEvent ev_ = new ListSelectionEvent(s_, index, index, false);
                listener_.valueChanged(ev_);
            }
            return;
        }
        grList.setLastIndex(index);
        boolean sel_ = !_e.isPopupTrigger();
        int min_ = Math.min(grList.getFirstIndex(), grList.getLastIndex());
        int max_ = Math.max(grList.getFirstIndex(), grList.getLastIndex());
        CustCellRender r_ = grList.getRender();
        for (int i = min_; i <= max_; i++) {
            Object v_ = grList.getList().get(i);
            Component c_;
            c_ = r_.getListCellRendererComponent(grList, v_, i, sel_, false);
            r_.paintComponent(c_);
        }
        ListSelection listener_ = grList.getListener();
        if (listener_ != null) {
            Object s_ = grList.getListComponents().get(index);
            ListSelectionEvent ev_ = new ListSelectionEvent(s_, index, index, false);
            listener_.valueChanged(ev_);
        }
    }
}
