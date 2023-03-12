package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ValidateFileFolderTree implements AbsActionListener {
    private final WindowWithTreeImpl editor;

    public ValidateFileFolderTree(WindowWithTreeImpl _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        editor.fileOrFolder();
    }
}
