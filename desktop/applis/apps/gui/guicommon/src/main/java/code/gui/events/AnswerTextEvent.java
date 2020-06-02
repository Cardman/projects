package code.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.ConfirmDialog;

public class AnswerTextEvent extends MouseAdapter {

    private ConfirmDialog dialog;

    private int answer;

    public AnswerTextEvent(ConfirmDialog _dialog, int _answer) {
        dialog = _dialog;
        answer = _answer;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.closeWindowText(answer);
    }
}
