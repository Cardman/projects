package code.expressionlanguage.adv;

import code.gui.AbstractMutableTreeNode;
import code.gui.events.AbsActionListener;

public final class RefreshTreeAction implements AbsActionListener {
    private final WindowCdmEditor editor;

    public RefreshTreeAction(WindowCdmEditor _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        AbstractMutableTreeNode sel_ = editor.getFolderSystem().selectEvt();
        String str_ = WindowCdmEditor.buildPath(sel_);
        editor.refresh(sel_,str_);
    }
}
