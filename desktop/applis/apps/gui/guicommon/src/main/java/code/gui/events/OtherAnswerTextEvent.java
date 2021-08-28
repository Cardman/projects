package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.OtherConfirmDialog;

public class OtherAnswerTextEvent implements AbsActionListener {

    private OtherConfirmDialog dialog;

    private int answer;

    public OtherAnswerTextEvent(OtherConfirmDialog _dialog, int _answer) {
        dialog = _dialog;
        answer = _answer;
    }

    @Override
    public void action() {
        dialog.closeWindowText(answer);
    }
}
