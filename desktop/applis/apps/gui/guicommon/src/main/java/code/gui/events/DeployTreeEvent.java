package code.gui.events;

import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNode;
import code.gui.FileDialog;

public class DeployTreeEvent implements AbsShortListTree {

    private FileDialog dialog;

    public DeployTreeEvent(FileDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNode _e) {
        dialog.applyTreeChangeSelected();
    }

}
