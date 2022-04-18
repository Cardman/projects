package code.gui;

import code.gui.events.AbsMouseListenerIntRel;

public final class MultSelectEltList implements AbsMouseListenerIntRel, IndexableListener {

    private final AbsGraphicListDefBase grList;

    private int index;

    private ListSelection selection;

    private final AbsGraphicListPainter painter;
    public MultSelectEltList(AbsGraphicListDefBase _grList, int _index, AbsGraphicListPainter _painter) {
        grList = _grList;
        index = _index;
        painter = _painter;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        boolean sel_ = _buttons.isLeftMouseButton();
        if (!_keyState.isShiftDown()) {
            boolean selected_ = painter.selectOneAmongIntervalPaint(grList, sel_, index);
            if (!selected_) {
                return;
            }
            FrameUtil.selectEvent(index, index, false,selection);
            AbsPreparedLabel elt_ = painter.selectedOneAmongIntervalPaint(grList, sel_, index);
            if (elt_ != null) {
                elt_.requestFocus();
            }
            return;
        }
        Interval interval_ = painter.selectIntervalPaint(grList, sel_, index);
        if (interval_ == null) {
            return;
        }
        FrameUtil.selectEvent(interval_.getMin(), interval_.getMax(), false,selection);
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
