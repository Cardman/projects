package code.mock;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;

public final class MockFrameFactory extends MockAbsFrameFactory {

    public MockFrameFactory(AbstractProgramInfos _fr) {
        super(_fr);
    }

    @Override
    public AbsCommonFrame newCommonFrame(AbstractProgramInfos _fr, AbstractImage _imageIconFrame) {
        return new MockCommonFrame(_fr);
    }

    @Override
    public AbsOtherDialog newOtherDialog() {
        return new MockDialog(getProgramInfos());
    }

    @Override
    public AbsOtherFrame newOtherFrame() {
        return new MockCommonFrame(getProgramInfos());
    }
}
