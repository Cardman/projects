package code.mock;

import code.gui.AbsCustComponent;
import code.gui.initialize.*;

public abstract class MockAbsFrameFactory implements AbsFrameFactory, AbsLightFrameFactory {

    @Override
    public void setCursor(AbsCustComponent _c, int _wCurs, int _hCurs, int[] _pixels) {
        _c.setHandCursor();
    }

    @Override
    public AbsStringBuffer newStringBuffer() {
        return new DefStringBuffer();
    }

}
