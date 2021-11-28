package code.vi.prot.impl;

import code.gui.AbsOtherDialog;
import code.gui.AbsOtherFrame;
import code.gui.initialize.AbsLightFrameFactory;

public final class DefLigFrameFactory implements AbsLightFrameFactory {
    @Override
    public AbsOtherDialog newOtherDialog() {
        return new LigDialog();
    }

    @Override
    public AbsOtherFrame newOtherFrame() {
        return new LigFrame();
    }
}
