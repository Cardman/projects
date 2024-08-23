package code.gui.events;

import code.gui.*;
import code.mock.MockAbsFrameFactory;

public final class MockFrameFactorySecSample extends MockAbsFrameFactory {

    @Override
    public AbsCommonFrame newCommonFrame() {
        return new MockCommonFrameSecSample();
    }

    @Override
    public AbsOtherDialog newOtherDialog() {
        return new MockDialogSecSample();
    }

    @Override
    public AbsOtherFrame newOtherFrame() {
        return new MockCommonFrameSecSample();
    }
}
