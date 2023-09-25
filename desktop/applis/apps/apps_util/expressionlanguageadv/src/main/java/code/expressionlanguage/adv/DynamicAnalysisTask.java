package code.expressionlanguage.adv;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.WatchResults;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.gui.AbsTreeGui;
import code.threads.AbstractAtomicBoolean;
import code.util.core.StringUtil;

public final class DynamicAnalysisTask implements Runnable {
    private final AbsDebuggerGui window;
    private final String dynamic;
    private final StackCall stackCall;
    private final AbstractPageEl call;
    private final ResultContext resultContext;
    private final AbsResultContextNext resultContextNext;
    private final AbstractAtomicBoolean interrupted;
    private final AbsTreeGui tree;
    private final DbgRootStruct root;

    public DynamicAnalysisTask(AbsDebuggerGui _w, AbstractPageEl _c, ResultContext _rc, AbsTreeGui _tr, DbgRootStruct _root) {
        this.window = _w;
        this.dynamic = StringUtil.nullToEmpty(_w.getDynamicEval().getText());
        this.stackCall = _w.getStackCall();
        this.call = _c;
        this.resultContext = _rc;
        this.resultContextNext = _w.getResultContextNext();
        this.interrupted = _w.getThreadFactory().newAtomicBoolean();
        this.tree = _tr;
        this.root = _root;
    }

    public String getDynamic() {
        return dynamic;
    }

    public AbstractAtomicBoolean getInterrupted() {
        return interrupted;
    }

    @Override
    public void run() {
        WatchResults wr_ = AbsDebuggerGui.dynamicAnalyze(dynamic, stackCall, call, resultContext, resultContextNext.generateAdv(interrupted));
        window.getCompoFactory().invokeNow(new DynamicAnalysisLater(window,dynamic,wr_,tree,root));
    }
}
