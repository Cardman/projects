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
        partUpdate(editor, diff);
        editor.updateNavSelect();
    }

    static void partUpdate(TabEditor _e, int _d) {
        int n_ = _e.getCurrentPart()+ _d;
        if (n_ < 0) {
            _e.setCurrentPart(_e.getParts().getLastIndex());
        } else if (n_ >= _e.getParts().size()) {
            _e.setCurrentPart(0);
        } else {
            _e.setCurrentPart(n_);
        }
    }
}
