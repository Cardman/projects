package code.expressionlanguage.adv;

import code.gui.AbstractMutableTreeNode;
import code.gui.events.AbsActionListener;

public final class RefreshTreeAction implements AbsActionListener {
    private final WindowWithTreeImpl editor;

    public RefreshTreeAction(WindowWithTreeImpl _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        AbstractMutableTreeNode sel_ = editor.getTree().selectEvt();
        String str_ = AbsEditorTabList.buildPath(sel_);
        editor.refresh(sel_,str_);
    }
}
