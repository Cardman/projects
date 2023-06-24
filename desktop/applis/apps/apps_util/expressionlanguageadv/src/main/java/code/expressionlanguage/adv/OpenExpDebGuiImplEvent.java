package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class OpenExpDebGuiImplEvent implements AbsActionListener {
    private final WindowExpressionEditor expressionEditor;

    public OpenExpDebGuiImplEvent(WindowExpressionEditor _e) {
        this.expressionEditor = _e;
    }

    @Override
    public void action() {
        ExpDebGuiImpl g_ = expressionEditor.getSessionExp();
        g_.build(new AnalyzingDebugExpEvent(expressionEditor.getSession(),expressionEditor,g_));
    }
}
