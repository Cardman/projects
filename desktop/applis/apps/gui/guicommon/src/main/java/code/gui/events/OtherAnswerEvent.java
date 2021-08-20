package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.OtherConfirmDialog;

public class OtherAnswerEvent extends AbsMouseListenerRel {

    private OtherConfirmDialog dialog;

    private int answer;

    public OtherAnswerEvent(OtherConfirmDialog _dialog, int _answer) {
        dialog = _dialog;
        answer = _answer;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.closeWindow(answer);
    }
}
