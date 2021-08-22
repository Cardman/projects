package code.gui.events;

import code.gui.FileDialog;

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
