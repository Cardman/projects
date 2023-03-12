package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ValidateFileFolderTree implements AbsActionListener {
    private final WindowCdmEditor editor;

    public ValidateFileFolderTree(WindowCdmEditor _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        editor.fileOrFolder();
    }
}
