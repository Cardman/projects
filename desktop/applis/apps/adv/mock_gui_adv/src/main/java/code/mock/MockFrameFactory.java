package code.mock;

import code.gui.*;

public final class MockFrameFactory extends MockAbsFrameFactory {
    @Override
    public AbsCommonFrame newCommonFrame() {
        return new MockCommonFrame();
    }

    @Override
    public AbsOtherDialog newOtherDialog() {
        return new MockAbsDialog();
    }

    @Override
    public AbsOtherFrame newOtherFrame() {
        return new MockCommonFrame();
    }
}
