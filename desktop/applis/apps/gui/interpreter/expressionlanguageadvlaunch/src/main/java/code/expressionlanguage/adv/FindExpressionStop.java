package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class FindExpressionStop implements AbsActionListener {
    private final TabEditor editor;

    public FindExpressionStop(TabEditor _e) {
        this.editor = _e;
    }
    @Override
    public void action() {
        editor.tryInterrupt();
    }
}
