package code.gui.files;

import code.gui.ConfirmDialog;
import code.gui.events.AbsActionListener;

public class AnswerEvent implements AbsActionListener {

    private final ConfirmDialog dialog;

    private final int answer;

    public AnswerEvent(ConfirmDialog _dialog, int _answer) {
        dialog = _dialog;
        answer = _answer;
    }

    @Override
    public void action() {
        dialog.closeWindow(answer);
    }
}
