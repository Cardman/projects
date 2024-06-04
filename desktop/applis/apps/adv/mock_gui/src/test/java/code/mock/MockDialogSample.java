package code.mock;

import code.gui.initialize.AbstractProgramInfos;

public final class MockDialogSample extends MockAbsDialog{
    public MockDialogSample(AbstractProgramInfos _fr) {
        super(_fr);
        pack();
    }

    @Override
    public void pack() {
        setVisible(isVisible());
    }
}
