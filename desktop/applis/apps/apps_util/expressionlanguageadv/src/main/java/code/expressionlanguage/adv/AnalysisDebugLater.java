package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;

public final class AnalysisDebugLater implements Runnable {

    private final AbsDebuggerGui window;
    private final ResultContext res;
    private final StringMap<String> src;

    public AnalysisDebugLater(AbsDebuggerGui _win, ResultContext _r, StringMap<String> _s) {
        this.window = _win;
        this.res = _r;
        this.src = _s;
    }

    @Override
    public void run() {
        window.coreUpdate(res, src);
    }
}
