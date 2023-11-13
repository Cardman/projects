package code.gui.files;

import code.gui.AbsButton;
import code.gui.events.AbsActionListener;

public class SearchingEvent implements AbsActionListener {

    private final FileOpenDialog dialog;
    private final AbsButton button;

    public SearchingEvent(FileOpenDialog _dialog, AbsButton _but) {
        dialog = _dialog;
        button =_but;
    }

    @Override
    public void action() {
        dialog.searchFile(button);
    }
}
