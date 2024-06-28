package code.expressionlanguage.adv;

import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;

public abstract class AdvAbsResultContextNext extends AbsAdvResultContextNext {
    private final WindowCdmEditor mainWindow;
    protected AdvAbsResultContextNext(WindowCdmEditor _w, AbstractProgramInfos _frames, CdmFactory _progressingTests) {
        super(_frames, _progressingTests);
        mainWindow = _w;
    }

    public WindowCdmEditor getMainWindow() {
        return mainWindow;
    }
}
