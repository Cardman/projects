package code.gui;

import code.gui.events.AbsMouseListenerIntRel;

public final class Popup implements AbsMouseListenerIntRel {

    private WithPopup grInt;

    public Popup(WithPopup _grInt) {
        grInt = _grInt;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        grInt.popup();
    }
}
