package code.gui.files;

import code.gui.FileDialog;
import code.gui.events.AbsListSelectionListener;

public class ClickRowEvent implements AbsListSelectionListener {

    private FileDialog dialog;

    public ClickRowEvent(FileDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(int _first, int _last) {
        dialog.clickRow();
    }
}
