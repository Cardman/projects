package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.fwd.Forwards;
import code.threads.Locking;

public class RunnableContextEl extends ContextEl implements Locking {

    private final ThreadStruct thread;
    private String idDate;
    private String currentDir;

    public RunnableContextEl(InitPhase _state, CommonExecutionInfos _executionInfos) {
        super(_executionInfos);
        thread = new ThreadStruct(Thread.currentThread());
        LgNamesWithNewAliases standards_ = (LgNamesWithNewAliases) _executionInfos.getStandards();
        currentDir = standards_.getExecutingOptions().getBaseFiles();
    }

    @Override
    public void forwardAndClear(AnalyzedPageEl _ana, Forwards _forwards) {
        super.forwardAndClear(_ana, _forwards);
        LgNamesWithNewAliases standards_ = (LgNamesWithNewAliases) getStandards();
        standards_.forwardAndClear(getClasses());
    }

    @Override
    public boolean isCurrentThreadEnded() {
        return thread.isEnded();
    }

    @Override
    public Thread getCurrentThread() {
        return thread.getThread();
    }

    public ThreadStruct getThread() {
        return thread;
    }

    String getIdDate() {
        return idDate;
    }

    void setIdDate(String _idDate) {
        idDate = _idDate;
    }

    public String getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String _currentDir) {
        this.currentDir = _currentDir;
    }

    public CustInitializer getCustInit() {
        return (CustInitializer)getInit();
    }

    public ExecutingOptions getExecutingOptions() {
        return ((LgNamesWithNewAliases) getStandards()).getExecutingOptions();
    }

    @Override
    public boolean callsOrException(StackCall _stack) {
        if (stopped()) {
            return true;
        }
        return super.callsOrException(_stack);
    }

    boolean stopped() {
        return ((LgNamesWithNewAliases) getStandards()).getExecutingOptions().getInterrupt().get() || isCurrentThreadEnded();
    }

    public void interrupt() {
        ((LgNamesWithNewAliases) getStandards()).getExecutingOptions().getInterrupt().set(true);
    }

}
