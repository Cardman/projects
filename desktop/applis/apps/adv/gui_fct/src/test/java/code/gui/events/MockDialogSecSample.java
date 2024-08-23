package code.gui.events;

import code.mock.MockAbsDialog;

public final class MockDialogSecSample extends MockAbsDialog {
    public MockDialogSecSample() {
        pack();
    }

    @Override
    public void pack() {
        setVisible(isVisible());
    }
}
