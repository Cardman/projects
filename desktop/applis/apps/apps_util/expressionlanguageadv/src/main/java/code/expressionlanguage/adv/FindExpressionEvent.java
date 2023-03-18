package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class FindExpressionEvent implements AbsActionListener {
    private final TabEditor editor;

    public FindExpressionEvent(TabEditor _e) {
        this.editor = _e;
    }

    @Override
    public void action() {
        editor.getFindingExpression().setEnabled(false);
        editor.getTaskManagerExp().submit(new FindExpressionTask(editor));

    }
}
