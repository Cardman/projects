package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class OpenFramePointsEvent implements AbsActionListener {
    private final AbsDebuggerGui mainWindow;
    private final FramePoints framePoints;

    public OpenFramePointsEvent(AbsDebuggerGui _m, FramePoints _f) {
        this.mainWindow = _m;
        this.framePoints = _f;
    }

    @Override
    public void action() {
        framePoints.init(mainWindow);
    }
}
