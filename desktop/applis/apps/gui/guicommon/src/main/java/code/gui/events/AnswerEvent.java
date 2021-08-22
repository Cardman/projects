package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.ConfirmDialog;

public class AnswerEvent extends AbsMouseListenerRel {

    private ConfirmDialog dialog;

    private int answer;

    public AnswerEvent(ConfirmDialog _dialog, int _answer) {
        dialog = _dialog;
        answer = _answer;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.closeWindow(answer);
    }
}
