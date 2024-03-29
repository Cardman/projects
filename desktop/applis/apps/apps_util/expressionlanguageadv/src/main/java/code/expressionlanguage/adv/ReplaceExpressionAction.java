package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ReplaceExpressionAction implements AbsActionListener {
    private final TabEditor current;
    private final boolean previousReplace;
    private final boolean nextReplace;

    public ReplaceExpressionAction(TabEditor _editor, boolean _p, boolean _n) {
        current = _editor;
        previousReplace = _p;
        nextReplace = _n;
    }

    @Override
    public void action() {
        current.getTaskManagerExp().submitLater(new ReplaceExpressionTask(current,previousReplace,nextReplace));
    }

}
