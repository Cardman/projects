package code.mock;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;

public final class MockFrameFactory extends MockAbsFrameFactory {

    public MockFrameFactory(AbstractProgramInfos _fr) {
        super(_fr);
    }

    @Override
    public AbsCommonFrame newCommonFrame(String _languageKey, AbstractProgramInfos _fr, AbstractImage _imageIconFrame) {
        return new MockCommonFrame(_fr, _languageKey);
    }

    @Override
    public AbsDialog newDialog(AbsCloseableDialog _d) {
        return new MockDialog(_d, getProgramInfos());
    }

    @Override
    public AbsDialog newDialog() {
        return new MockDialog(getProgramInfos());
    }

    @Override
    public AbsOtherDialog newOtherDialog() {
        return new MockDialog(getProgramInfos());
    }

    @Override
    public AbsOtherFrame newOtherFrame() {
        return new MockCommonFrame(getProgramInfos(), "");
    }
}
