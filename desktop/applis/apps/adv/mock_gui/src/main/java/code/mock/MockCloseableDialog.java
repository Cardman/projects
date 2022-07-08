package code.mock;

import code.gui.AbsCloseableDialog;
import code.gui.AbsDialog;

public final class MockCloseableDialog implements AbsCloseableDialog {
    private final AbsDialog closeableDialog;

    public MockCloseableDialog(AbsDialog _cl) {
        this.closeableDialog = _cl;
    }

    @Override
    public void closeWindow() {
        closeableDialog.closeWindow();
    }
}
