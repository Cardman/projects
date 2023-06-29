package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.AbsMenuItem;
import code.util.StringMap;

public final class AnalyzingDebugExpEvent extends AbsAnalyzingDebugEvent {
    private final AbsOpenFrameInteract acting;
    private final WindowWithTreeImpl window;

    public AnalyzingDebugExpEvent(AbsMenuItem _menu, WindowWithTreeImpl _window, AbsDebuggerGui _g) {
        super(_g);
        this.window = _window;
        this.acting = new ExpMenuFrameInteract(_menu);
    }

    @Override
    protected AbsOpenFrameInteract act() {
        return acting;
    }

    @Override
    protected ResultContext base() {
        return window.getMainFrame().getBaseResultDbg();
    }

    @Override
    protected ManageOptions manageOpt() {
        return window.getManageOptions();
    }

    @Override
    protected StringMap<String> src() {
        return AnalyzeExpressionSource.added(window);
    }
}
