package code.mock;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;

public final class MockFrameFactorySample extends MockAbsFrameFactory{
    public MockFrameFactorySample(AbstractProgramInfos _fr) {
        super(_fr);
    }

    @Override
    public AbsCommonFrame newCommonFrame(AbstractProgramInfos _frames, AbstractImage _imageIconFrame) {
        return new MockCommonFrameSample(_frames);
    }

    @Override
    public AbsOtherDialog newOtherDialog() {
        return new MockDialogSample(getProgramInfos());
    }

    @Override
    public AbsOtherFrame newOtherFrame() {
        return new MockCommonFrameSample(getProgramInfos());
    }
}
