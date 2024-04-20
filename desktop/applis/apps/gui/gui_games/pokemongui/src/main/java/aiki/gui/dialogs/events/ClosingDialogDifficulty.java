package aiki.gui.dialogs.events;

import aiki.gui.dialogs.DialogDifficulty;
import code.gui.events.AbsWindowListenerClosing;

public final class ClosingDialogDifficulty implements AbsWindowListenerClosing {
    private final DialogDifficulty dialogDifficulty;

    public ClosingDialogDifficulty(DialogDifficulty _d) {
        this.dialogDifficulty = _d;
    }

    @Override
    public void windowClosing() {
        dialogDifficulty.closeDial();
    }
}
