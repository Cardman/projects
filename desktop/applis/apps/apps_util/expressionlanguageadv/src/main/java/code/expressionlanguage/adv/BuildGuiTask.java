package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.util.StringMap;

public final class BuildGuiTask implements Runnable {
    private final ResultContext base;
    private final AbsDebuggerGui gui;
    private final ManageOptions manageOpt;
    private final StringMap<String> src;

    public BuildGuiTask(ResultContext _b, AbsDebuggerGui _g, ManageOptions _man, StringMap<String> _s) {
        this.base = _b;
        this.gui = _g;
        this.manageOpt = _man;
        this.src = _s;
    }

    @Override
    public void run() {
        gui.build(base,manageOpt,src);
    }
}
