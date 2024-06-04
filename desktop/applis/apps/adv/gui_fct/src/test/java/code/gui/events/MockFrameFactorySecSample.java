package code.gui.events;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.mock.MockAbsFrameFactory;

public final class MockFrameFactorySecSample extends MockAbsFrameFactory {
    public MockFrameFactorySecSample(AbstractProgramInfos _fr) {
        super(_fr);
    }

    @Override
    public AbsCommonFrame newCommonFrame(AbstractProgramInfos _frames, AbstractImage _imageIconFrame) {
        return new MockCommonFrameSecSample(_frames);
    }

    @Override
    public AbsOtherDialog newOtherDialog() {
        return new MockDialogSecSample(getProgramInfos());
    }

    @Override
    public AbsOtherFrame newOtherFrame() {
        return new MockCommonFrameSecSample(getProgramInfos());
    }
}
