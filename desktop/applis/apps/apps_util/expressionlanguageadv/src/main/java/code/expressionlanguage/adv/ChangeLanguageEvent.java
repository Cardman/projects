package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ChangeLanguageEvent implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;
    private OutputDialogLanguage outputDialogLanguage;

    public ChangeLanguageEvent(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        outputDialogLanguage = new OutputDialogLanguage(windowCdmEditor);
    }

    public OutputDialogLanguage getOutputDialogLanguage() {
        return outputDialogLanguage;
    }
}
