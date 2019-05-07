package code.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

public final class MultSelectKeyEltList extends KeyAdapter implements IndexableListener {

    private GraphicListable grList;

    private int index;

    public MultSelectKeyEltList(GraphicListable _grList, int _index) {
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
        Object[] array_ = grList.toArray();
        for (Object v: array_) {
            JLabel c_;
            c_ = r_.getListCellRendererComponent(grList, v, index_, sel_, false);
            r_.paintComponent(c_);
            index_++;
        }
        if (!sel_) {
            grList.setFirstIndex(0);
            grList.setLastIndex(array_.length);
            grList.clearRange();
            grList.setFirstIndex(-1);
            grList.setLastIndex(-1);
        } else {
            grList.setFirstIndex(0);
            grList.setLastIndex(array_.length);
            grList.addRange();
        }
        SelectionUtil.selectEvent(0, array_.length, grList, false);
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
