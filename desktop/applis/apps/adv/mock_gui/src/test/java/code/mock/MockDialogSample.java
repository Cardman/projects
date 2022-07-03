package code.mock;

import code.gui.AbsCloseableDialog;
import code.gui.initialize.AbstractProgramInfos;

public final class MockDialogSample extends MockAbsDialog{
    public MockDialogSample(AbstractProgramInfos _fr) {
        super(_fr);
        pack();
    }

    public MockDialogSample(AbsCloseableDialog _cl, AbstractProgramInfos _fr) {
        super(_cl, _fr);
    }

    @Override
    public void pack() {
        setVisible(isVisible());
    }
}
