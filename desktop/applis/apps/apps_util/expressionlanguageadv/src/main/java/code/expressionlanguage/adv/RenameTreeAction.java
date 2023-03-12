package code.expressionlanguage.adv;

import code.gui.AbstractMutableTreeNode;
import code.gui.events.AbsActionListener;

public final class RenameTreeAction implements AbsActionListener {
    private final WindowWithTreeImpl editor;

    public RenameTreeAction(WindowWithTreeImpl _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        AbstractMutableTreeNode sel_ = editor.getFolderSystem().selectEvt();
        editor.setSelectedNode(sel_);
        editor.showRenaming();
    }
}
