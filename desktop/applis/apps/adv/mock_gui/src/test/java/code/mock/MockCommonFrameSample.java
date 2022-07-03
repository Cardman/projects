package code.mock;

import code.gui.initialize.AbstractProgramInfos;

public final class MockCommonFrameSample extends MockAbsCommonFrame{
    public MockCommonFrameSample(AbstractProgramInfos _fr) {
        super(_fr, "");
        pack();
    }

    @Override
    public void pack() {
        setVisible(isVisible());
    }
}
