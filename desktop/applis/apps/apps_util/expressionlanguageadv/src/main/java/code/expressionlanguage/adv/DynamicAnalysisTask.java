package code.expressionlanguage.adv;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.WatchResults;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.gui.AbsTreeGui;
import code.threads.AbstractAtomicBoolean;

public final class DynamicAnalysisTask implements Runnable {
    private final AbsDebuggerGui window;
    private final String dynamic;
    private final StackCall stackCall;
    private final AbstractPageEl call;
    private final ResultContext resultContext;
    private final AbsResultContextNext resultContextNext;
    private final AbstractAtomicBoolean interruped;
    private final AbsTreeGui tree;
    private final DbgRootStruct root;

    public DynamicAnalysisTask(AbsDebuggerGui _w, AbstractPageEl _c, ResultContext _rc, AbsTreeGui _tr, DbgRootStruct _root) {
        this.window = _w;
        this.dynamic = _w.getDynamicEval().getText();
        this.stackCall = _w.getStackCall();
        this.call = _c;
        this.resultContext = _rc;
        this.resultContextNext = _w.getResultContextNext();
        this.interruped = _w.getThreadFactory().newAtomicBoolean();
        this.tree = _tr;
        this.root = _root;
    }

    @Override
    public void run() {
        WatchResults wr_ = AbsDebuggerGui.dynamicAnalyze(dynamic, stackCall, call, resultContext, resultContextNext.generateAdv(interruped));
        window.getCompoFactory().invokeNow(new DynamicAnalysisLater(window,wr_,tree,root));
    }
}
