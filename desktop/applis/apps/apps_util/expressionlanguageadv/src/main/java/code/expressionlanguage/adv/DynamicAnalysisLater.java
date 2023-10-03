package code.expressionlanguage.adv;

import code.expressionlanguage.exec.WatchResults;

public final class DynamicAnalysisLater implements Runnable {
    private final DynamicAnalysisTask dynamic;
    private final WatchResults watchResults;

    public DynamicAnalysisLater(DynamicAnalysisTask _d, WatchResults _r) {
        this.dynamic = _d;
        this.watchResults = _r;
    }

    @Override
    public void run() {
        dynamic.getWindow().refreshDynamic(dynamic,watchResults);
    }
}
