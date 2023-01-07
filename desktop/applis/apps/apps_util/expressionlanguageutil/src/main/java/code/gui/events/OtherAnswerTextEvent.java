package code.gui.events;

import code.gui.*;

public class OtherAnswerTextEvent implements AbsActionListener {

    private final OtherConfirmDialog dialog;

    private final int answer;

    public OtherAnswerTextEvent(OtherConfirmDialog _dialog, int _answer) {
        dialog = _dialog;
        answer = _answer;
    }

    @Override
    public void action() {
        dialog.closeWindowText(answer);
    }
}
