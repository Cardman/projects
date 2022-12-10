package code.gui;

import code.gui.events.AbsMouseListenerIntRel;

public final class SimpleSelectEltList implements AbsMouseListenerIntRel, IndexableListener {

    private final AbsGraphicListDefBase grList;

    private int index;
    private final AbsGraphicListPainter graphicListPainter;

    private ListSelection selection;

    public SimpleSelectEltList(AbsGraphicListDefBase _grList, int _index, AbsGraphicListPainter _graphicListPainter) {
        grList = _grList;
        index = _index;
        graphicListPainter = _graphicListPainter;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        boolean sel_ = _buttons.isLeftMouseButton();
        graphicListPainter.selectPaint(grList,sel_,index);
        SelectionUtil.selectEvent(index, index, false, selection);
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
