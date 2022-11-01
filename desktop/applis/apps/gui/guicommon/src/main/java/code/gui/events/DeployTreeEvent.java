package code.gui.events;

import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.FileDialog;

public class DeployTreeEvent implements AbsShortListTree {

    private final FileDialog dialog;

    public DeployTreeEvent(FileDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _e) {
        dialog.applyTreeChangeSelected();
    }

}
