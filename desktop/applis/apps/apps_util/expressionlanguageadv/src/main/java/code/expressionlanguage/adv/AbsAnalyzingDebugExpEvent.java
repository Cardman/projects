package code.expressionlanguage.adv;

import code.expressionlanguage.utilimpl.ManageOptions;
import code.util.StringMap;

public abstract class AbsAnalyzingDebugExpEvent extends AbsAnalyzingDebugEvent {
    private final AbsOpenFrameInteract acting;
    private final WindowWithTreeImpl window;

    protected AbsAnalyzingDebugExpEvent(AbsOpenFrameInteract _menu, WindowWithTreeImpl _window, AbsDebuggerGui _g) {
        super(_g);
        this.window = _window;
        this.acting = _menu;
    }

    public WindowWithTreeImpl getWindow() {
        return window;
    }

    @Override
    protected AbsOpenFrameInteract act() {
        return acting;
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
