package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class CancelNavLine implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;

    public CancelNavLine(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        windowCdmEditor.getDialogNavigLine().setVisible(false);
    }
}
