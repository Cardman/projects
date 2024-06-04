package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.gui.files.DefButtonsOpenFolderPanelAct;
import code.gui.files.FolderOpenFrame;

public final class ChooseInitialFolder implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;

    public ChooseInitialFolder(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        windowCdmEditor.getModal().set(true);
        FolderOpenFrame.setFolderOpenDialog(true,windowCdmEditor.getFolderOpenFrame(),new DefButtonsOpenFolderPanelAct(new ElaContinueFolder(windowCdmEditor)));
//        String fileName_ = StringUtil.nullToEmpty(windowCdmEditor.getFolderOpenDialogInt().input(windowCdmEditor.getCommonFrame(), true));
//        if (fileName_.isEmpty()) {
//            return;
//        }
//        windowCdmEditor.folder(MemoryFileSystem.skipLastSep(fileName_));
    }
}
