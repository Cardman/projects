package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ReplaceExpressionApply implements AbsActionListener {
    private final TabEditor current;

    public ReplaceExpressionApply(TabEditor _editor) {
        current = _editor;
    }

    @Override
    public void action() {
        current.getTaskManagerExp().submit(new ReplaceExpressionApplyTask(current));
    }

}
