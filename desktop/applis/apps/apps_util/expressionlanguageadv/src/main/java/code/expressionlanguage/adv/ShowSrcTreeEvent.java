package code.expressionlanguage.adv;

import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;

public class ShowSrcTreeEvent implements AbsShortListTree {

    private final WindowCdmEditor dialog;

    public ShowSrcTreeEvent(WindowCdmEditor _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _e) {
        dialog.applyTreeChangeSelected(true);
    }

}
