package aiki.gui.dialogs.events;

import aiki.gui.dialogs.DialogDifficulty;
import code.gui.AbsCloseableDialog;

public final class ClosingDialogDifficulty implements AbsCloseableDialog {
    private final DialogDifficulty dialogDifficulty;

    public ClosingDialogDifficulty(DialogDifficulty _d) {
        this.dialogDifficulty = _d;
    }

    @Override
    public void closeWindow() {
        dialogDifficulty.closeDial();
    }
}
