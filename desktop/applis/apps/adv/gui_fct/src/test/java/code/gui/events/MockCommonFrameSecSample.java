package code.gui.events;

import code.gui.initialize.AbstractProgramInfos;
import code.mock.MockAbsCommonFrame;

public final class MockCommonFrameSecSample extends MockAbsCommonFrame {
    public MockCommonFrameSecSample(AbstractProgramInfos _fr) {
        super(_fr, "");
        pack();
    }

    @Override
    public void pack() {
        setVisible(isVisible());
    }
}
