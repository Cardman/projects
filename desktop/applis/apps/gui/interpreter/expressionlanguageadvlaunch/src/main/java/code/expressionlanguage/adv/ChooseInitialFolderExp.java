package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.gui.files.DefButtonsOpenFolderPanelAct;
import code.gui.files.FolderOpenFrame;

public final class ChooseInitialFolderExp implements AbsActionListener {
    private final OutputDialogExpresion dialog;

    public ChooseInitialFolderExp(OutputDialogExpresion _w) {
        this.dialog = _w;
    }

    @Override
    public void action() {
        dialog.getOwner().getModal().set(true);
        FolderOpenFrame.setFolderOpenDialog(true,dialog.getOwner().getFolderOpenFrame(),new DefButtonsOpenFolderPanelAct(new ElaContinueFolder(dialog)));
//        String fileName_ = StringUtil.nullToEmpty(dialog.getOwner().getFolderOpenDialogInt().input(dialog.getOwner().getCommonFrame(), true));
//        if (fileName_.isEmpty()) {
//            return;
//        }
//        dialog.folder(MemoryFileSystem.skipLastSep(fileName_));
    }
}
