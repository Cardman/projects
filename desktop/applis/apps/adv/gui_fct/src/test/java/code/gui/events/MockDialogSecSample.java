package code.gui.events;

import code.gui.AbsCloseableDialog;
import code.gui.initialize.AbstractProgramInfos;
import code.mock.MockAbsDialog;

public final class MockDialogSecSample extends MockAbsDialog {
    public MockDialogSecSample(AbstractProgramInfos _fr) {
        super(_fr);
        pack();
    }

    public MockDialogSecSample(AbsCloseableDialog _cl, AbstractProgramInfos _fr) {
        super(_cl, _fr);
    }

    @Override
    public void pack() {
        setVisible(isVisible());
    }
}
