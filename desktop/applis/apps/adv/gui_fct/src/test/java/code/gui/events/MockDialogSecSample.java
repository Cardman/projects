package code.gui.events;

import code.gui.initialize.AbstractProgramInfos;
import code.mock.MockAbsDialog;

public final class MockDialogSecSample extends MockAbsDialog {
    public MockDialogSecSample(AbstractProgramInfos _fr) {
        super(_fr);
        pack();
    }

    @Override
    public void pack() {
        setVisible(isVisible());
    }
}
