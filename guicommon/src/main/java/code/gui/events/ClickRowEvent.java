package code.gui.events;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import code.gui.FileDialog;

public class ClickRowEvent implements ListSelectionListener {

    private FileDialog dialog;

    public ClickRowEvent(FileDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(ListSelectionEvent _e) {
        dialog.clickRow();
    }
}
