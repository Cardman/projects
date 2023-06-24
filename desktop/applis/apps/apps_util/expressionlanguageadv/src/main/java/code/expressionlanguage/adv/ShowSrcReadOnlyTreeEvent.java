package code.expressionlanguage.adv;

import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;

public final class ShowSrcReadOnlyTreeEvent implements AbsShortListTree {

    private final AbsDebuggerGui dialog;

    public ShowSrcReadOnlyTreeEvent(AbsDebuggerGui _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _e) {
        dialog.applyTreeChangeSelected();
    }

}
