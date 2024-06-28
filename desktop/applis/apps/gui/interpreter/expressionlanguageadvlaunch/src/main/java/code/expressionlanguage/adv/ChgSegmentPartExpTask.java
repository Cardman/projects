package code.expressionlanguage.adv;

public final class ChgSegmentPartExpTask implements Runnable {
    private final TabEditor editor;
    private final int diff;

    public ChgSegmentPartExpTask(TabEditor _e, int _d) {
        this.editor = _e;
        diff = _d;
    }
    @Override
    public void run() {
        partUpdate(editor, diff);
        editor.updateNavSelectExp();
    }

    static void partUpdate(TabEditor _e, int _d) {
        int n_ = _e.getCurrentPartExp()+ _d;
        if (n_ < 0) {
            _e.setCurrentPartExp(_e.getPartsExp().getLastIndex());
        } else if (n_ >= _e.getPartsExp().size()) {
            _e.setCurrentPartExp(0);
        } else {
            _e.setCurrentPartExp(n_);
        }
    }
}
