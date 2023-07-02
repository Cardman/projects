package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;

public final class ExpAnalyzingDebugExpEvent extends AbsAnalyzingDebugExpEvent {

    public ExpAnalyzingDebugExpEvent(AbsOpenFrameInteract _menu, WindowWithTreeImpl _window, AbsDebuggerGui _g) {
        super(_menu, _window, _g);
    }

    @Override
    protected ResultContext base() {
        return getWindow().getMainFrame().getBaseResultDbg();
    }

}
