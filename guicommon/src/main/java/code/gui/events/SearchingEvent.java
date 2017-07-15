package code.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.FileOpenDialog;

public class SearchingEvent extends MouseAdapter {

    private FileOpenDialog dialog;

    public SearchingEvent(FileOpenDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.searchFile();
    }
}
