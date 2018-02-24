package code.gui;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class MultSelectKeyEltList extends KeyAdapter {

    private GraphicList grList;

    private int index;

    public MultSelectKeyEltList(GraphicList _grList, int _index) {
        grList = _grList;
        index = _index;
    }

    @Override
    public void keyReleased(KeyEvent _e) {
        if (!_e.isControlDown()) {
            return;
        }
        if (_e.getKeyCode() != KeyEvent.VK_A) {
            return;
        }
        CustCellRender r_ = grList.getRender();
        boolean sel_ = !_e.isShiftDown();
        int index_ = 0;
        for (Object v: grList.getList()) {
            Component c_;
            c_ = r_.getListCellRendererComponent(grList, v, index_, sel_, false);
            r_.paintComponent(c_);
            index_++;
        }
        if (!sel_) {
            grList.setLastIndex(-1);
            grList.setFirstIndex(-1);
        } else {
            grList.setLastIndex(0);
            grList.setFirstIndex(grList.getList().size());
        }
    }
}
