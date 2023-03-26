package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.events.AbsActionListener;

public final class FindExpressionStop implements AbsActionListener {
    private final TabEditor editor;

    public FindExpressionStop(TabEditor _e) {
        this.editor = _e;
    }
    @Override
    public void action() {
        RunnableContextEl rCont_ = editor.getAction();
        rCont_.getExecutingOptions().getInterrupt().set(true);
        rCont_.getThread().getThread().stopJoinSleep();
    }
}
