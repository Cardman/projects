package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OpenFramePointsEvent implements AbsActionListener {
    private final AbsDebuggerGui mainWindow;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OpenFramePointsEvent(AbsDebuggerGui _m, FramePoints _f, ResultContext _res) {
        this.mainWindow = _m;
        this.framePoints = _f;
        currentResult = _res;
    }

    @Override
    public void action() {
        framePoints.init(mainWindow, currentResult);
    }

    public ResultContext getCurrentResult() {
        return currentResult;
    }
}
