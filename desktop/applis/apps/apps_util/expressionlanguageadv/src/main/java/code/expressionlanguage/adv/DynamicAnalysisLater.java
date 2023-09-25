package code.expressionlanguage.adv;

import code.expressionlanguage.exec.WatchResults;

public final class DynamicAnalysisLater implements Runnable {
    private final AbsDebuggerGui window;
    private final WatchResults watchResults;

    public DynamicAnalysisLater(AbsDebuggerGui _w, WatchResults _r) {
        this.window = _w;
        this.watchResults = _r;
    }

    @Override
    public void run() {
        window.refreshDynamic(watchResults);
    }
}
