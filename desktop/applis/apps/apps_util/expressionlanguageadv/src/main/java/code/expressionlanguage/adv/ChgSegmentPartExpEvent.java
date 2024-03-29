package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ChgSegmentPartExpEvent implements AbsActionListener {
    private final TabEditor editor;
    private final int diff;

    public ChgSegmentPartExpEvent(TabEditor _e, int _d) {
        this.editor = _e;
        diff = _d;
    }

    @Override
    public void action() {
        editor.getTaskManagerExp().submitLater(new ChgSegmentPartExpTask(editor,diff));
    }

}
