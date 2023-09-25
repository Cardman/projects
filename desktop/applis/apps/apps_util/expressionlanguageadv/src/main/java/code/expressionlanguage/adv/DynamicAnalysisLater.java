package code.expressionlanguage.adv;

import code.expressionlanguage.exec.WatchResults;
import code.gui.AbsTreeGui;

public final class DynamicAnalysisLater implements Runnable {
    private final AbsDebuggerGui window;
    private final WatchResults watchResults;
    private final AbsTreeGui tree;
    private final DbgRootStruct root;

    public DynamicAnalysisLater(AbsDebuggerGui _w, WatchResults _r, AbsTreeGui _tr, DbgRootStruct _root) {
        this.window = _w;
        this.watchResults = _r;
        this.tree = _tr;
        this.root = _root;
    }

    @Override
    public void run() {
        window.refreshDynamic(watchResults,tree,root);
    }
}
