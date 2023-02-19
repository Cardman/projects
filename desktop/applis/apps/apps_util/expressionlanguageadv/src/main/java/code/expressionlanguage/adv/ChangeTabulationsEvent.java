package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ChangeTabulationsEvent implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;
    private OutputDialogTab outputDialogTabs;

    public ChangeTabulationsEvent(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        outputDialogTabs = new OutputDialogTab(windowCdmEditor);
        outputDialogTabs.update();
    }

    public OutputDialogTab getOutputDialogTabs() {
        return outputDialogTabs;
    }
}
