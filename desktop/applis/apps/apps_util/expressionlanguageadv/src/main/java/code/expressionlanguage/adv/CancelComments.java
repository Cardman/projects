package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class CancelComments implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;

    public CancelComments(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        windowCdmEditor.getDialogComments().setVisible(false);
    }
}
