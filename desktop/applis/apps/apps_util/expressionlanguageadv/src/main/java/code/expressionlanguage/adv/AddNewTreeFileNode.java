package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class AddNewTreeFileNode implements AbsActionListener {
    private final WindowCdmEditor editor;

    public AddNewTreeFileNode(WindowCdmEditor _ed) {
        this.editor = _ed;
    }

    @Override
    public void action() {
        editor.setSelectedNode(editor.getTree().selectEvt());
        editor.showFileOrFolder();
    }
}
