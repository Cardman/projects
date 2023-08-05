package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class StoreUndoRedoAction implements AbsActionListener {
    private final TabEditor current;

    public StoreUndoRedoAction(TabEditor _u) {
        this.current = _u;
    }

    @Override
    public void action() {
        current.getTexts().add(current.centerText());
        current.setCurrentText(current.getTexts().size()-1);
        UndoRedoAction.updateRedoUndo(current);
    }
}
