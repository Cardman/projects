package code.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public final class SimpleSelectEltList extends MouseAdapter implements IndexableListener {

    private final AbsBasicGraphicList grList;

    private int index;
    private final AbsGraphicListPainter graphicListPainter;

    private ListSelection selection;

    public SimpleSelectEltList(AbsBasicGraphicList _grList, int _index, AbsGraphicListPainter _graphicListPainter) {
        grList = _grList;
        index = _index;
        graphicListPainter = _graphicListPainter;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        boolean sel_ = SwingUtilities.isLeftMouseButton(_e);
        graphicListPainter.selectPaint(grList,sel_,index);
        GraphicList.selectEvent(index, index, false, selection);
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
