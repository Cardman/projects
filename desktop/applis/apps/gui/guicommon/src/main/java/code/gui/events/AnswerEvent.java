package code.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.ConfirmDialog;

public class AnswerEvent extends MouseAdapter {

    private ConfirmDialog dialog;

    private int answer;

    public AnswerEvent(ConfirmDialog _dialog, int _answer) {
        dialog = _dialog;
        answer = _answer;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.closeWindow(answer);
    }
}
