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
    public AbsCommonFrame newCommonFrame(String _languageKey, AbstractProgramInfos _frames, AbstractImage _imageIconFrame) {
        return new MockCommonFrameSecSample(_frames);
    }

    @Override
    public AbsDialog newDialog(AbsCloseableDialog _closeable) {
        return new MockDialogSecSample(_closeable,getProgramInfos());
    }

    @Override
    public AbsDialog newDialog() {
        return new MockDialogSecSample(getProgramInfos());
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
