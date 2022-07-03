package code.mock;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;

public final class MockFrameFactorySample extends MockAbsFrameFactory{
    public MockFrameFactorySample(AbstractProgramInfos _fr) {
        super(_fr);
    }

    @Override
    public AbsCommonFrame newCommonFrame(String _languageKey, AbstractProgramInfos _frames, AbstractImage _imageIconFrame) {
        return new MockCommonFrameSample(_frames);
    }

    @Override
    public AbsDialog newDialog(AbsCloseableDialog _closeable) {
        return new MockDialogSample(_closeable,getProgramInfos());
    }

    @Override
    public AbsDialog newDialog() {
        return new MockDialogSample(getProgramInfos());
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
