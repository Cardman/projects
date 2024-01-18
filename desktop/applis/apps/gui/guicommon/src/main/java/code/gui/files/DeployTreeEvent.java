package code.gui.files;

import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;

public class DeployTreeEvent implements AbsShortListTree {

    private final FileDialogContent dialog;

    public DeployTreeEvent(FileDialogContent _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore<String> _e) {
        dialog.applyTreeChangeSelected();
    }

}
