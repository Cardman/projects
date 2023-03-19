package code.expressionlanguage.adv;

import code.gui.AbstractMutableTreeNode;
import code.gui.events.AbsActionListener;

public final class RemoveTreeAction implements AbsActionListener {
    private final WindowWithTreeImpl editor;

    public RemoveTreeAction(WindowWithTreeImpl _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        AbstractMutableTreeNode sel_ = editor.getFolderSystem().selectEvt();
        editor.setSelectedNode(sel_);
        editor.showRemoving();
//        String str_ = WindowCdmEditor.buildPath(sel_);
//        String lg_ = editor.getCommonFrame().getLanguageKey();
//        int confirmDialog_ = editor.getConfirmDialogAns().input(editor.getCommonFrame(), str_, "",lg_, GuiConstants.YES_OPTION);
//        editor.remove(confirmDialog_,sel_,str_);
    }
}