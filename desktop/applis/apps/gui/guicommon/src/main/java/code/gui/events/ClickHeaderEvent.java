package code.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.FileDialog;

public class ClickHeaderEvent extends MouseAdapter {

    private FileDialog dialog;

    public ClickHeaderEvent(FileDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseClicked(MouseEvent _e) {
        dialog.clickHeader(_e);
    }
}
