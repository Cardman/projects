package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class CreateInitialFile implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;

    public CreateInitialFile(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        String fileName_ = windowCdmEditor.getFileOpenDialogInt().input(windowCdmEditor.getCommonFrame(), windowCdmEditor.getCommonFrame().getLanguageKey(), true, "", windowCdmEditor.getCommonFrame().getFrames().getHomePath());
        if (fileName_.isEmpty()) {
            return;
        }
        windowCdmEditor.saveConf(fileName_);
    }
}
