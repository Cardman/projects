package code.vi.prot.impl;

import code.gui.AbsOtherDialog;
import code.gui.AbsOtherFrame;
import code.gui.initialize.AbsLightFrameFactory;

public final class DefLigFrameFactory implements AbsLightFrameFactory {
    private String lightBuffer;
    @Override
    public AbsOtherDialog newOtherDialog() {
        return new LigDialog();
    }

    @Override
    public AbsOtherFrame newOtherFrame() {
        return new LigFrame();
    }

    @Override
    public String paste() {
        return lightBuffer;
    }

    @Override
    public void copy(String _c) {
        lightBuffer = _c;
    }
}
