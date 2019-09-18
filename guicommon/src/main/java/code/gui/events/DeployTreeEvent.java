package code.gui.events;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import code.gui.FileDialog;

public class DeployTreeEvent implements TreeSelectionListener {

    private FileDialog dialog;

    public DeployTreeEvent(FileDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(TreeSelectionEvent _e) {
        dialog.applyTreeChangeSelected();
    }

}
