package code.gui.files;

import code.gui.events.AbsActionListener;

public class AnswerTextEvent implements AbsActionListener {

    private final ConfirmDialog dialog;

    private final int answer;

    public AnswerTextEvent(ConfirmDialog _dialog, int _answer) {
        dialog = _dialog;
        answer = _answer;
    }

    @Override
    public void action() {
        dialog.closeWindowText(answer);
    }
}
