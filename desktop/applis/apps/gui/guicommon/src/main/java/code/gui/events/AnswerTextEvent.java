package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.ConfirmDialog;

public class AnswerTextEvent extends AbsMouseListenerRel {

    private ConfirmDialog dialog;

    private int answer;

    public AnswerTextEvent(ConfirmDialog _dialog, int _answer) {
        dialog = _dialog;
        answer = _answer;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.closeWindowText(answer);
    }
}
