package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public abstract class AbsOpenDebGuiImplEvent implements AbsActionListener {
    private final WindowWithTreeImpl expressionEditor;
    private final AbsDebuggerGui dbg;

    protected AbsOpenDebGuiImplEvent(WindowWithTreeImpl _e, AbsDebuggerGui _d) {
        this.expressionEditor = _e;
        this.dbg = _d;
    }

    @Override
    public void action() {
        if (dbg.getCommonFrame().isVisible()) {
            return;
        }
        dbg.build(event());
    }

    public WindowWithTreeImpl getExpressionEditor() {
        return expressionEditor;
    }

    public AbsDebuggerGui getDbg() {
        return dbg;
    }

    protected abstract AbsAnalyzingDebugExpEvent event();
}
