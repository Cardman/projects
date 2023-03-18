package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class AnalyzeExpressionEvent implements AbsActionListener {
    private final WindowCdmEditor mainFrame;

    public AnalyzeExpressionEvent(WindowCdmEditor _e) {
        this.mainFrame = _e;
    }

    @Override
    public void action() {
        mainFrame.getService().submit(new AnalyzeExpressionSource(mainFrame));
    }
}
