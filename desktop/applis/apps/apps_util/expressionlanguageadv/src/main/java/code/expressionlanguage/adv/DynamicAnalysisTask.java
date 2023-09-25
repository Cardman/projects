package code.expressionlanguage.adv;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.WatchResults;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.threads.AbstractAtomicBoolean;

public final class DynamicAnalysisTask implements Runnable {
    private final AbsDebuggerGui window;
    private final String dynamic;
    private final StackCall stackCall;
    private final AbstractPageEl call;
    private final ResultContext resultContext;
    private final AbsResultContextNext resultContextNext;
    private final AbstractAtomicBoolean interruped;

    public DynamicAnalysisTask(AbsDebuggerGui _w,String _d, StackCall _s, AbstractPageEl _c, ResultContext _rc, AbsResultContextNext _rcn, AbstractAtomicBoolean _i) {
        this.window = _w;
        this.dynamic = _d;
        this.stackCall = _s;
        this.call = _c;
        this.resultContext = _rc;
        this.resultContextNext = _rcn;
        this.interruped = _i;
    }

    @Override
    public void run() {
        WatchResults wr_ = AbsDebuggerGui.dynamicAnalyze(dynamic, stackCall, call, resultContext, resultContextNext.generateAdv(interruped));
        window.getCompoFactory().invokeNow(new DynamicAnalysisLater(window,wr_));
    }
}
