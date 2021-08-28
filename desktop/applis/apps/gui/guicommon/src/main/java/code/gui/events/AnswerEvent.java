package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.ConfirmDialog;

public class AnswerEvent implements AbsActionListener {

    private ConfirmDialog dialog;

    private int answer;

    public AnswerEvent(ConfirmDialog _dialog, int _answer) {
        dialog = _dialog;
        answer = _answer;
    }

    @Override
    public void action() {
        dialog.closeWindow(answer);
    }
}
