package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ClearUndoRedoAction implements AbsActionListener {
    private final TabEditor current;

    public ClearUndoRedoAction(TabEditor _u) {
        this.current = _u;
    }

    @Override
    public void action() {
        current.getTexts().clear();
        current.getUndo().setEnabled(false);
        current.getRedo().setEnabled(false);
    }
}
