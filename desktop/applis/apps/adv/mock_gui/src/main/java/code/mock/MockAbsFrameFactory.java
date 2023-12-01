package code.mock;

import code.gui.AbsCustComponent;
import code.gui.initialize.*;

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

    @Override
    public AbsStringBuffer newStringBuffer() {
        return new DefStringBuffer();
    }

}
