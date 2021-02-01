package code.gui;

import code.util.CustList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class MultSelectKeyEltList<T> extends KeyAdapter implements IndexableListener {

    private final GraphicList<T> grList;

    private int index;

    public MultSelectKeyEltList(GraphicList<T> _grList, int _index) {
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
        CustCellRender<T> r_ = grList.getRender();
        boolean sel_ = !_e.isShiftDown();
        int index_ = 0;
        CustList<T> array_ = grList.getList();
        for (T v: array_) {
            PreparedLabel c_;
            c_ = r_.getListCellRendererComponent(grList, v, index_, sel_, false);
            r_.paintComponent(c_);
            index_++;
        }
        if (!sel_) {
            grList.setFirstIndex(0);
            grList.setLastIndex(array_.size());
            grList.clearRange();
            grList.setFirstIndex(-1);
            grList.setLastIndex(-1);
        } else {
            grList.setFirstIndex(0);
            grList.setLastIndex(array_.size());
            grList.addRange();
        }
        grList.selectEvent(0, array_.size(), false);
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
