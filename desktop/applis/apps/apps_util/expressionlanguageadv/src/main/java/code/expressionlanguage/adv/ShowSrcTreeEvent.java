package code.expressionlanguage.adv;

import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;

public class ShowSrcTreeEvent implements AbsShortListTree {

    private final WindowWithTree dialog;

    public ShowSrcTreeEvent(WindowWithTree _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _e) {
        WindowCdmEditor.applyTreeChangeSelected(dialog,true);
    }

}
