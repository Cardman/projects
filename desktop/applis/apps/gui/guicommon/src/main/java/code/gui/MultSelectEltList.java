package code.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public final class MultSelectEltList extends MouseAdapter implements IndexableListener {

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
    public void mouseReleased(MouseEvent _e) {
        boolean sel_ = SwingUtilities.isLeftMouseButton(_e);
        if (!_e.isShiftDown()) {
            boolean selected_ = painter.selectOneAmongIntervalPaint(grList, sel_, index);
            if (!selected_) {
                return;
            }
            GraphicList.selectEvent(index, index, false,selection);
            painter.afterSelectOneAmongIntervalPaint(grList,sel_,index);
            return;
        }
        Interval interval_ = painter.selectIntervalPaint(grList, sel_, index);
        if (interval_ == null) {
            return;
        }
        GraphicList.selectEvent(interval_.getMin(), interval_.getMax(), false,selection);
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
