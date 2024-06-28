package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class AddNewTreeFileNode implements AbsActionListener {
    private final WindowWithTreeImpl editor;

    public AddNewTreeFileNode(WindowWithTreeImpl _ed) {
        this.editor = _ed;
    }

    @Override
    public void action() {
        editor.setSelectedNode(editor.getTree().selectEvt());
        editor.showFileOrFolder();
    }
}
