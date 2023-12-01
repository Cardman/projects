package code.vi.prot.impl;

import code.gui.AbsOtherDialog;
import code.gui.AbsOtherFrame;
import code.gui.initialize.AbsLightFrameFactory;
import code.gui.initialize.AbsStringBuffer;
import code.gui.initialize.DefStringBuffer;

public final class DefLigFrameFactory implements AbsLightFrameFactory {

    @Override
    public AbsOtherDialog newOtherDialog() {
        return new LigDialog();
    }

    @Override
    public AbsOtherFrame newOtherFrame() {
        return new LigFrame();
    }

    @Override
    public AbsStringBuffer newStringBuffer() {
        return new DefStringBuffer();
    }

}
