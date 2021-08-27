package code.gui;

import code.gui.events.AbsKeyListenerRel;

import java.awt.event.KeyEvent;

public final class MultSelectKeyEltList extends AbsKeyListenerRel implements IndexableListener {

    private final AbsGraphicListDefBase grList;

    private int index;

    private ListSelection selection;
    private final AbsGraphicListPainter painter;

    public MultSelectKeyEltList(AbsGraphicListDefBase _grList, int _index, AbsGraphicListPainter _painter) {
        grList = _grList;
        index = _index;
        painter = _painter;
    }

    @Override
    public void keyReleased(AbsCtrlKeyState _e, char _keyChar, int _keyCode) {
        if (!_e.isControlDown()) {
            return;
        }
        if (_keyCode != KeyEvent.VK_A) {
            return;
        }
        boolean sel_ = !_e.isShiftDown();
        Interval interval_ = painter.selectIntervalKeyPaint(grList, sel_, index);
        if (interval_ == null) {
            return;
        }
        FrameUtil.selectEvent(interval_.getMin(),  interval_.getMax(), false, selection);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int _index) {
        index = _index;
    }

    @Override
    public ListSelection getSelection() {
        return selection;
    }

    @Override
    public void setSelection(ListSelection _selection) {
        this.selection = _selection;
    }

}
