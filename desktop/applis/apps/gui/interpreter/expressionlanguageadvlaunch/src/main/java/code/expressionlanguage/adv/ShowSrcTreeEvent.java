package code.expressionlanguage.adv;

import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;

public final class ShowSrcTreeEvent implements AbsShortListTree {

    private final WindowWithTreeImpl dialog;

    public ShowSrcTreeEvent(WindowWithTreeImpl _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore<String> _e) {
        if (dialog.getSelectedNode() != null) {
            return;
        }
        dialog.applyTreeChangeSelected(true);
    }

}
