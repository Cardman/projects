package code.gui;

import code.gui.events.AbsMouseListenerRel;

public final class Popup extends AbsMouseListenerRel {

    private WithPopup grInt;

    public Popup(WithPopup _grInt) {
        grInt = _grInt;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        grInt.popup();
    }
}
