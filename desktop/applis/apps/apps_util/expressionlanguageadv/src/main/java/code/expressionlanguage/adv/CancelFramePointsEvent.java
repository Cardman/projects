package code.expressionlanguage.adv;

import code.gui.events.AbsWindowListenerClosing;

public final class CancelFramePointsEvent implements AbsWindowListenerClosing {
    private final AbsDebuggerGui mainFrame;

    public CancelFramePointsEvent(AbsDebuggerGui _i) {
        this.mainFrame = _i;
    }

    @Override
    public void windowClosing() {
        act(mainFrame);
    }

    static void act(AbsDebuggerGui _w) {
        _w.getFramePoints().getCommonFrame().setVisible(false);
        _w.getFramePoints().getFrameExcFormContent().setSelectedExc(null);
        _w.getFramePoints().getFrameStdFormContent().setSelectedMp(null);
    }
}
