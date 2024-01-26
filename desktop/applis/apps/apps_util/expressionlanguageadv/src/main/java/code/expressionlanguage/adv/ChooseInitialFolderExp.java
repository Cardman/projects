package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.MemoryFileSystem;
import code.gui.events.AbsActionListener;
import code.util.core.StringUtil;

public final class ChooseInitialFolderExp implements AbsActionListener {
    private final OutputDialogExpresion dialog;

    public ChooseInitialFolderExp(OutputDialogExpresion _w) {
        this.dialog = _w;
    }

    @Override
    public void action() {
        String fileName_ = StringUtil.nullToEmpty(dialog.getOwner().getFolderOpenDialogInt().input(dialog.getOwner().getCommonFrame(), true));
        if (fileName_.isEmpty()) {
            return;
        }
        dialog.folder(MemoryFileSystem.skipLastSep(fileName_));
    }
}
