package code.expressionlanguage.adv;

import code.gui.AbsShortListTree;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNodeCore;

public final class ShowSrcReadOnlyTreeEvent implements AbsShortListTree {

    private final AbsDebuggerGui dialog;
    private final AbsTreeGui tree;
    private final AbsOpeningReadOnlyFile opener;

    public ShowSrcReadOnlyTreeEvent(AbsDebuggerGui _dialog, AbsTreeGui _t, AbsOpeningReadOnlyFile _o) {
        dialog = _dialog;
        tree = _t;
        opener = _o;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _e) {
        dialog.applyTreeChangeSelected(opener,tree);
    }

}
