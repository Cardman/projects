package code.gui.files;

import code.gui.AbsPlainButton;
import code.gui.FileOpenDialog;
import code.gui.events.AbsActionListener;

public class SearchingEvent implements AbsActionListener {

    private final FileOpenDialog dialog;
    private final AbsPlainButton button;

    public SearchingEvent(FileOpenDialog _dialog, AbsPlainButton _but) {
        dialog = _dialog;
        button =_but;
    }

    @Override
    public void action() {
        dialog.searchFile(button);
    }
}
