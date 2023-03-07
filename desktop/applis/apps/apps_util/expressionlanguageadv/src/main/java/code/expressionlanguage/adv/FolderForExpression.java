package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class FolderForExpression implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;
    private OutputDialogExpresion dialogExpresion;

    public FolderForExpression(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }
    @Override
    public void action() {
        String f_ = windowCdmEditor.getFolderExpression();
        if (!f_.isEmpty()) {
            windowCdmEditor.folderExp(f_);
            return;
        }
        dialogExpresion = new OutputDialogExpresion(windowCdmEditor);
    }

    public OutputDialogExpresion getDialogExpresion() {
        return dialogExpresion;
    }
}
