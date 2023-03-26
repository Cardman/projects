package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class CancelAnalyzeExpressionEvent implements AbsActionListener {
    private final WindowCdmEditor mainFrame;

    public CancelAnalyzeExpressionEvent(WindowCdmEditor _e) {
        this.mainFrame = _e;
    }

    @Override
    public void action() {
        mainFrame.getAnalyzeEx().getInterrupt().set(true);
    }
}
