package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.gui.files.DefButtonsSavePanelAct;
import code.gui.files.FileSaveFrame;

public final class CreateInitialFile implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;

    public CreateInitialFile(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        windowCdmEditor.getModal().set(true);
        FileSaveFrame.setFileSaveDialogByFrame(true,windowCdmEditor.getFrames().getHomePath(),windowCdmEditor.getFileSaveFrame(),new DefButtonsSavePanelAct(new ElaSaveFile(windowCdmEditor),new ElaContinueSavedFile(windowCdmEditor)));
//        String fileName_ = StringUtil.nullToEmpty(windowCdmEditor.getFileSaveDialogInt().input(windowCdmEditor.getCommonFrame(), true, "", windowCdmEditor.getCommonFrame().getFrames().getHomePath()));
//        if (fileName_.isEmpty()) {
//            return;
//        }
//        windowCdmEditor.saveConf(fileName_);
    }
}
