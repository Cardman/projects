package code.gui;

import code.gui.events.AbsMouseListener;

public final class SimpleSelectCombo implements AbsMouseListener,IndexableListener {

    private final GraphicStringList grList;

    private int index;

    public SimpleSelectCombo(GraphicStringList _grList, int _index) {
        grList = _grList;
        index = _index;
    }

    @Override
    public void mouseExited(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        grList.repaintSelect(index,false);
    }

    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        grList.repaintSelect(index,true);
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        grList.setFirstIndex(index);
        grList.setLastIndex(index);
        grList.clearAllRange();
        grList.addRange();
        grList.selectEvent(index, index, false);
    }

    @Override
    public void mouseClicked(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        //
    }

    @Override
    public void mousePressed(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        //
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
        return grList.getListener();
    }

    @Override
    public void setSelection(ListSelection _sel) {
        grList.simpleSetListener(_sel);
    }
}
