package code.mock;

import code.gui.AbsCustComponent;
import code.gui.initialize.AbsFrameFactory;
import code.gui.initialize.AbsLightFrameFactory;
import code.gui.initialize.AbstractProgramInfos;

public abstract class MockAbsFrameFactory implements AbsFrameFactory, AbsLightFrameFactory {
    private final AbstractProgramInfos programInfos;

    protected MockAbsFrameFactory(AbstractProgramInfos _p) {
        this.programInfos = _p;
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

    @Override
    public void setCursor(AbsCustComponent _c, int _wCurs, int _hCurs, int[] _pixels) {
        _c.setHandCursor();
    }
}
