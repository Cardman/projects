package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ShowAnalyzeStatusEvent implements AbsActionListener {
    private final WindowCdmEditor mainFrame;

    public ShowAnalyzeStatusEvent(WindowCdmEditor _e) {
        this.mainFrame = _e;
    }
    @Override
    public void action() {
        mainFrame.getStatusAnalyze().setVisible(true);
    }
}
