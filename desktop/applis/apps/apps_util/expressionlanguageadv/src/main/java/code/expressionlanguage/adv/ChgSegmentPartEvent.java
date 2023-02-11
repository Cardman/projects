package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ChgSegmentPartEvent implements AbsActionListener {
    private final TabEditor editor;
    private final int diff;

    public ChgSegmentPartEvent(TabEditor _e, int _d) {
        this.editor = _e;
        diff = _d;
    }

    @Override
    public void action() {
        int n_ = editor.getCurrentPart()+diff;
        editor.setCurrentPart(n_);
        editor.updateNavSelect();
    }
}
