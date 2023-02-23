package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ChangeAliasesEvent implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;
    private OutputDialogAliases outputDialogAliases;

    public ChangeAliasesEvent(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        outputDialogAliases = new OutputDialogAliases(windowCdmEditor);
    }

    public OutputDialogAliases getOutputDialogAliases() {
        return outputDialogAliases;
    }
}
