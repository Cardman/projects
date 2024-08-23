package code.mock;

import code.gui.*;

public final class MockFrameFactorySample extends MockAbsFrameFactory{

    @Override
    public AbsCommonFrame newCommonFrame() {
        return new MockCommonFrameSample();
    }

    @Override
    public AbsOtherDialog newOtherDialog() {
        return new MockDialogSample();
    }

    @Override
    public AbsOtherFrame newOtherFrame() {
        return new MockCommonFrameSample();
    }
}
