package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class RefreshExpressionEvent implements AbsActionListener {
    private final TabEditor editor;

    public RefreshExpressionEvent(TabEditor _e) {
        this.editor = _e;
    }

    @Override
    public void action() {
        editor.getTaskManagerExp().submitLater(new RefreshExpressionTask(editor));
    }
}
