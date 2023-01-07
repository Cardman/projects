package code.gui.events;

import code.gui.*;

public class OtherAnswerEvent implements AbsActionListener {

    private final OtherConfirmDialog dialog;

    private final int answer;

    public OtherAnswerEvent(OtherConfirmDialog _dialog, int _answer) {
        dialog = _dialog;
        answer = _answer;
    }

    @Override
    public void action() {
        dialog.closeWindow(answer);
    }
}
