package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.AbsShortListTree;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNodeCore;

public final class ShowSrcReadOnlyTreeEvent implements AbsShortListTree {

    private final AbsDebuggerGui dialog;
    private final ResultContext resultContext;
    private final AbsTreeGui tree;
    private final AbsOpeningReadOnlyFile opener;

    public ShowSrcReadOnlyTreeEvent(AbsDebuggerGui _dialog, ResultContext _r, AbsTreeGui _t, AbsOpeningReadOnlyFile _o) {
        dialog = _dialog;
        resultContext = _r;
        tree = _t;
        opener = _o;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _e) {
        dialog.applyTreeChangeSelected(opener,resultContext,tree);
    }

}
