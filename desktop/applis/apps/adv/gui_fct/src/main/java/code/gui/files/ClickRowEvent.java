package code.gui.files;

import code.gui.events.AbsListSelectionListener;

public class ClickRowEvent implements AbsListSelectionListener {

    private FileDialogContent dialog;

    public ClickRowEvent(FileDialogContent _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(int _first, int _last) {
        dialog.clickRow();
    }
}
