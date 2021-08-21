package code.gui;

import code.gui.images.AbstractImage;
import code.gui.initialize.AbsFrameFactory;
import code.gui.initialize.AbstractProgramInfos;

public final class DefFrameFactory implements AbsFrameFactory {
    @Override
    public AbsCommonFrame newCommonFrame(String _languageKey, AbstractProgramInfos _frames, AbstractImage _imageIconFrame) {
        return new CommonFrame(_languageKey, _frames, _imageIconFrame);
    }

    @Override
    public AbsDialog newDialog(AbsCloseableDialog _closeable) {
        return new Dialog(_closeable);
    }

    @Override
    public AbsDialog newDialog() {
        return new Dialog();
    }

    @Override
    public AbsOtherDialog newOtherDialog() {
        return new OtherDialog();
    }

    @Override
    public AbsOtherFrame newOtherFrame() {
        return new OtherFrame();
    }
}
