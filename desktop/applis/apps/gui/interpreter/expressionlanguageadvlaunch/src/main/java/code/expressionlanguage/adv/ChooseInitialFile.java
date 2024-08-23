package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.gui.files.DefButtonsOpenPanelAct;
import code.gui.files.FileOpenFrame;

public final class ChooseInitialFile implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;

    public ChooseInitialFile(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        windowCdmEditor.getModal().set(true);
        FileOpenFrame.setFileSaveDialogByFrame(true,windowCdmEditor.getFrames().getHomePath(),windowCdmEditor.getFileOpenFrame(),new DefButtonsOpenPanelAct(new ElaContinueInitFile(windowCdmEditor)));
//        FileSaveFrame.setFileSaveDialogByFrame(true,windowCdmEditor.getCommonFrame().getFrames().getHomePath(),windowCdmEditor.getFileSaveFrame(),new DefButtonsSavePanelAct(new ElaSaveFile(windowCdmEditor),new ElaContinueFile(windowCdmEditor)));

//        String fileName_ = StringUtil.nullToEmpty(windowCdmEditor.getFileOpenDialogInt().input(windowCdmEditor.getCommonFrame(), true, "", windowCdmEditor.getCommonFrame().getFrames().getHomePath()));
//        if (fileName_.isEmpty()) {
//            return;
//        }
//        windowCdmEditor.updateEnv(fileName_);
    }
}
