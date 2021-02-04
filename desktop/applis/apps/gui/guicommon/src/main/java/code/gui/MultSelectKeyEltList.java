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
        r_.setList(grList.getList());
        boolean sel_ = !_e.isShiftDown();
        int index_ = 0;
        CustList<T> array_ = grList.getList();
        int len_ = array_.size();
        for (int i = 0; i < len_; i++) {
            PreparedLabel c_ = grList.getListComponents().get(index_);
            r_.getListCellRendererComponent(c_, index_, sel_, false);
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
            grList.setLastIndex(array_.size()-1);
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
