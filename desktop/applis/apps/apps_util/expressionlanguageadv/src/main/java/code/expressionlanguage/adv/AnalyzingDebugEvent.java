package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.events.AbsActionListener;
import code.threads.AbstractBaseExecutorService;
import code.util.StringMap;

public final class AnalyzingDebugEvent implements AbsActionListener {
    private final AbstractBaseExecutorService manageGui;
    private final ResultContext base;
    private final AbsDebuggerGui gui;
    private final ManageOptions manageOpt;
    private final StringMap<String> src;

    public AnalyzingDebugEvent(AbstractBaseExecutorService _m, ResultContext _b, AbsDebuggerGui _g, ManageOptions _man, StringMap<String> _s) {
        this.manageGui = _m;
        this.base = _b;
        this.gui = _g;
        this.manageOpt = _man;
        this.src = _s;
    }

    @Override
    public void action() {
        manageGui.submit(new BuildGuiTask(base,gui,manageOpt,src));
    }
}
