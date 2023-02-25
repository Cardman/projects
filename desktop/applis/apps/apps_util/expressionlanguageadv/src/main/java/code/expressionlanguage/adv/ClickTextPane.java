package code.expressionlanguage.adv;

import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.AbsTextPane;
import code.gui.events.AbsMouseListenerIntRel;

public final class ClickTextPane implements AbsMouseListenerIntRel {
    private final AbsTextPane textPane;

    public ClickTextPane(AbsTextPane _t) {
        this.textPane = _t;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        textPane.requestFocus();
    }
}
