package code.expressionlanguage.adv;

import code.gui.AbstractMutableTreeNode;
import code.gui.events.AbsActionListener;

public final class RefreshTreeAction implements AbsActionListener {
    private final WindowWithTree editor;

    public RefreshTreeAction(WindowWithTree _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        AbstractMutableTreeNode sel_ = editor.getTree().selectEvt();
        String str_ = WindowCdmEditor.buildPath(sel_);
        WindowCdmEditor.refresh(editor,sel_,str_);
    }
}
